package com.michael.archiving.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.michael.archiving.archivingpojos.ArchiveTopic;
import com.michael.archiving.services.interf.ArchiveTopicServiceIntef;
import com.michael.archiving.services.interf.ValidateTopicServiceinter;
import com.michael.documentation.resources.model.archiving.ArchiveTopicRequest;
import com.michael.documentation.resources.model.topics.TopicWrapper;
import com.michael.documentation.resources.response.Response;
import com.michael.documentation.resources.utils.DocumentConstants;

@RestController
public class ArchiveRestController {

	@Autowired
	private ValidateTopicServiceinter validateTopicServiceinter;
	
	@Autowired
	private ArchiveTopicServiceIntef archiveTopicServiceImpl;
	
	@PostMapping(value=DocumentConstants.URL_SE_DOCUMENT_ARCHIVING_ARCHIVE_TOPIC)
	public ResponseEntity<Response> archiveTopic(@RequestBody TopicWrapper request) {
		ArchiveTopic archiveTopic = validateTopicServiceinter.validateTopic(request.getTopic());
		var tuple = archiveTopicServiceImpl.performArchive(archiveTopic);
		
		var bool = tuple.u;
		var saveResp = archiveTopicServiceImpl.saveArchiveNameToDb(tuple.t);
		var response = new Response();
		if(bool ==true && saveResp == true) {
			response.setSuccess(true);			
		}else {
		
		}
		return ResponseEntity.ok(response);
	
	}
	
	@GetMapping(value = "/restoreTopic")
	public ResponseEntity<Object> restoreTopic(@RequestBody ArchiveTopicRequest request){
		var response = new  TopicWrapper();
		archiveTopicServiceImpl.restoreArchivedTopic(request.getTopicArchiveName())
		.ifPresent(archiveTopic -> {
			var topic = validateTopicServiceinter.validateArchiveTopic(archiveTopic);
			response.setTopic(topic);
		});
		
		return ResponseEntity.ok(response);
	}
}
