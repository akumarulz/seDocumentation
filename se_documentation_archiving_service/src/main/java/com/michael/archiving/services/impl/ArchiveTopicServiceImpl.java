package com.michael.archiving.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.michael.archiving.archivingpojos.ArchiveTopic;
import com.michael.archiving.services.interf.ArchiveTopicServiceIntef;
import com.michael.documentation.resources.model.topics.Topic;
import com.michael.documentation.resources.response.Response;
import com.michael.documentation.resources.utils.DocumentConstants;
import com.michael.documentation.resources.utils.ResourceUtils;
import com.michael.documentation.resources.utils.Tuple;

@Service
public class ArchiveTopicServiceImpl implements ArchiveTopicServiceIntef {
	
	@Value("${com.michael.archiving.services.ArchiveTopicServiceIntef.performArchive}")
	private String archiveFolder;
	
	@Autowired
	private WebClient.Builder webClient;
	
	@Override
	public Tuple<String,Boolean> performArchive(ArchiveTopic archiveTopic) {
			
			var bool = Boolean.FALSE;
			var fileName = String.format("%s_%s_%s.dat", archiveTopic.getArchivTopicTitle(), 
					ResourceUtils.getNowDateAsString(), ResourceUtils.getNowTimeAsString());
			var save = archiveFolder + File.separator + fileName;
			
			Path path = Paths.get(archiveFolder);
			if(!path.toFile().exists()) {
				try {
					Files.createDirectories(path);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			try(FileOutputStream outputStream = new FileOutputStream(new File(save));
					ObjectOutputStream objectoutStream = new ObjectOutputStream(outputStream)) {
				objectoutStream.writeObject(archiveTopic);
				bool = Boolean.TRUE;
			} catch (IOException e) {
				e.printStackTrace();
				return new Tuple<String,Boolean>(null,bool);
			}
		return new Tuple<String,Boolean>(fileName,bool);
	}

	@Override
	public boolean saveArchiveNameToDb(String ArchiveTopicName) {
		class Wrapper{
			public Wrapper(String archiveEntry) {
				this.archiveEntry = archiveEntry;
			}
			
			@SuppressWarnings("unused")
			public Wrapper() {}
			
			private String archiveEntry;

			@SuppressWarnings("unused")
			public String getArchiveEntry() {
				return archiveEntry;
			}

			@SuppressWarnings("unused")
			public void setArchiveEntry(String archiveEntry) {
				this.archiveEntry = archiveEntry;
			}
		}
		
		Wrapper wrapper = new Wrapper(ArchiveTopicName);
		
		Response response = this.performRequest(HttpMethod.POST, wrapper, DocumentConstants.URL_ARCHIVE_ENTRY);
		
		return response.getSuccess();
	}
	
	private <T> Response performRequest(HttpMethod method,T body, String url) {
		ResponseEntity<Response> response = webClient
				.build()
				.method(method)
				.uri(url)
				.bodyValue(body)
				.exchange()
				.flatMap(res -> res.toEntity(Response.class))
				.block();
				
		return response.getBody();
	}

	@Override
	public Optional<ArchiveTopic> restoreArchivedTopic(String archiveTopicName) {
		if(archiveTopicName== null || archiveTopicName.isBlank()) {
			return Optional.empty();
		}
		String fileLocation = String.format("%s"+File.separator+"%s",archiveFolder, archiveTopicName);
		
		Path filePath = Paths.get(fileLocation);
		File fileCheck = filePath.toFile();
		
		if(!fileCheck.exists() && !fileCheck.isFile()) {
			return Optional.empty();
		}
		
		try(FileInputStream ips =  new FileInputStream(new File(fileLocation));
				ObjectInputStream oips = new ObjectInputStream(ips);){
			ArchiveTopic topic = (ArchiveTopic)oips.readObject();
			
			return Optional.of(topic);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}
	

}
