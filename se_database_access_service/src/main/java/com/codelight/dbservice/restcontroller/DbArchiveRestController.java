package com.codelight.dbservice.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codelight.dbservice.requests.RequestArchive;
import com.codelight.dbservice.service.interf.archiving.DbServiceArchivingInterf;
import com.michael.documentation.resources.model.archiving.ArchivingResponse;
import com.michael.documentation.resources.response.Response;
import com.michael.documentation.resources.utils.DocumentConstants;

@RestController
@RequestMapping("/"+ DocumentConstants.URL_SE_DOCUMENT_DB_ACCESS_ARCHIVECONTROLLER)
public class DbArchiveRestController {

	@Autowired
	private DbServiceArchivingInterf dbServiceArchivingInterf; 
	
	@PostMapping(value = DocumentConstants.URL_ARCHIVE_ENTRY)
	public ResponseEntity<Response> archiveEntry(@RequestBody RequestArchive request) {
		var bool = dbServiceArchivingInterf.storeArchiveRecord(request.getArchiveEntry());
		var response = new Response();
		
		response.setSuccess(bool);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/getAllArchivedRecords")
	public ResponseEntity<ArchivingResponse> getAllArchiveRecords(){
		dbServiceArchivingInterf.getAllTopicArchives();
		
		return ResponseEntity.ok().build();
	}
}
