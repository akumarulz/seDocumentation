package com.codelight.dbservice.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codelight.dbservice.requests.RequestArchive;
import com.codelight.dbservice.service.impl.archiving.DbServiceArchivingManualImpl;
import com.codelight.dbservice.service.interf.archiving.DbServiceArchivingInterf;
import com.codelight.dbservice.service.interf.archiving.DbServiceArchivingManualInterf;
import com.michael.documentation.resources.model.archiving.ArchivingResponse;
import com.michael.documentation.resources.response.Response;
import com.michael.documentation.resources.utils.DocumentConstants;

@RestController
@RequestMapping("/"+ DocumentConstants.URL_SE_DOCUMENT_DB_ACCESS_ARCHIVECONTROLLER)
public class DbArchiveRestController {

	@Autowired
	private DbServiceArchivingInterf dbServiceArchivingImpl; 
	
	@Autowired
	private DbServiceArchivingManualInterf dbServiceArchivingManualImpl;
	
	@PostMapping(value = DocumentConstants.URL_ARCHIVE_ENTRY)
	public ResponseEntity<Response> archiveEntry(@RequestBody RequestArchive request) {
		var bool = dbServiceArchivingImpl.storeArchiveRecord(request.getArchiveEntry());
		var response = new Response();
		
		response.setSuccess(bool);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/getArchivedRecords/{limit}/{offset}")
	public ResponseEntity<ArchivingResponse> getArchivedRecords(
			@PathVariable(name = "limit", required = true) Integer limit,
			@PathVariable(name = "offset", required = true) Integer offset){
		var response = new ArchivingResponse();
		response.setArchiveEntryList(dbServiceArchivingManualImpl.getEntries(limit, offset));
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/getCountAllArchivedRecords")
	public ResponseEntity<ArchivingResponse> getCountAllArchivedRecords(){
		var response = new ArchivingResponse();
		response.setCountArchiveRecords(dbServiceArchivingManualImpl.getCountAllArchiveRecords());
		response.setSuccess(true);
		return ResponseEntity.ok(response);
	}
}
