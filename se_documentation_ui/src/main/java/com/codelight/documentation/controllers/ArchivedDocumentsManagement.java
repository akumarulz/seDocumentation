package com.codelight.documentation.controllers;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codelight.documentation.requests.GetArchiveEntryRecordsRequest;
import com.codelight.documentation.response.GetArchiveEntryRecordsResponse;
import com.codelight.documentation.service.interfaces.archivemanagement.archivedDocumentsManagementInterface;
import com.codelight.documentation.utils.Utils;
import com.michael.documentation.resources.model.pojo.NavigationWrapper;

@Controller
@RequestMapping(value = "/archivedDocumentsManagement")
public class ArchivedDocumentsManagement {

	@Autowired
	private archivedDocumentsManagementInterface archivedDocumentsManagementImple;
	
	@Autowired
	private NavigationWrapper navigationWrapper;
	
	@GetMapping
	public String getArchivedTopics(Model model) {
		Properties props = null;
		try {
			props = Utils.getProperties();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		model.addAttribute("messageProperties",props);
		model.addAttribute("navigationList", navigationWrapper.getNavList());
		return "archivedDocumentsManagement";
	}
	
	@PostMapping(value="/getArchivedEntries")
	@ResponseBody
	public GetArchiveEntryRecordsResponse getArchivedRecords(@RequestBody GetArchiveEntryRecordsRequest request) {
		var response = new GetArchiveEntryRecordsResponse();
		var reply = archivedDocumentsManagementImple.getAllArchived(request.getLimit(),request.getOffset());
		response.setArchiveRecordCount(reply.getTotalEntries());
		response.setArchiveRecordList(reply.getArchivedEntryList());
		return response;
	}
}
