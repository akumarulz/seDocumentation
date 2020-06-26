package com.codelight.documentation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codelight.documentation.response.GetArchivedRecordsResponse;
import com.codelight.documentation.service.interfaces.archivemanagement.archivedDocumentsManagementInterface;

@Controller
@RequestMapping(value = "/archivedDocumentsManagement")
public class ArchivedDocumentsManagement {

	@Autowired
	private archivedDocumentsManagementInterface archivedDocumentsManagementImple;
	
	@GetMapping
	public String getArchivedTopics() {
		GetArchivedRecordsResponse r = archivedDocumentsManagementImple.getAllArchived(1,2);
		return "archivedDocumentsManagement";
	}
}
