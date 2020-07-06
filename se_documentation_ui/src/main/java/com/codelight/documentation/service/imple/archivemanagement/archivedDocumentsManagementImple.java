package com.codelight.documentation.service.imple.archivemanagement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.codelight.documentation.docexceptions.SEdocumentationException;
import com.codelight.documentation.response.GetArchivedRecordsResponse;
import com.codelight.documentation.service.interfaces.archivemanagement.archivedDocumentsManagementInterface;
import com.codelight.documentation.utils.Utils;
import com.michael.documentation.resources.model.archiving.ArchiveEntry;
import com.michael.documentation.resources.model.archiving.ArchivingResponse;
import com.michael.documentation.resources.response.Response;
import com.michael.documentation.resources.utils.DocumentConstants;

@Service
public class archivedDocumentsManagementImple implements archivedDocumentsManagementInterface {

	@Autowired
	@Qualifier("ArchiveManagementWebClient")
	private WebClient.Builder webClient;
	
	private BiFunction<Integer,Integer,List<ArchiveEntry>> getArchiveEntries = (limit,offset) -> {
		String url = String.format(DocumentConstants.URL_ARCHIVE_MANAGEMENT_GET_RECORDS, limit,offset);
		try {
			return this.<ArchivingResponse>performGetRequest(url, ArchivingResponse.class).getArchiveEntryList();
			
		} catch (SEdocumentationException e) {
			e.printStackTrace();
		}
		return new ArrayList<ArchiveEntry>(Arrays.asList(
				new ArchiveEntry(1l,"one"),
				new ArchiveEntry(2l,"two"),
				new ArchiveEntry(3l,"three"),
				new ArchiveEntry(4l,"four")
				));
	};
	
	private Supplier<Integer> getNumTotalEntries = () -> {
		var count = 0;
		try {
			count =  this.<ArchivingResponse>performGetRequest(
					DocumentConstants.URL_ARCHIVE_MANAGEMENT_GET_COUNT_ARCHIVE_RECORDS, 
					ArchivingResponse.class).getCountArchiveRecords();
		} catch (SEdocumentationException e) {
			e.printStackTrace();
		}
		return count;
	};
	
	private BiFunction<List<ArchiveEntry>, Integer, GetArchivedRecordsResponse> f = GetArchivedRecordsResponse::new;
	
	@Override
	public GetArchivedRecordsResponse getAllArchived(int limit, int offset) {
		return f.apply(getArchiveEntries.apply(limit, offset), getNumTotalEntries.get());
	}
	
	private <T extends Response> T performGetRequest(String url, Class<T> clazz ) throws SEdocumentationException {
		ResponseEntity<T> response2 = webClient
				.build()
				.method(HttpMethod.GET)
				.uri(url)
				.exchange()
				.flatMap(response -> response.toEntity(clazz))
				.block();
		return Utils.<T>getResponse(response2, clazz);
	}

}
