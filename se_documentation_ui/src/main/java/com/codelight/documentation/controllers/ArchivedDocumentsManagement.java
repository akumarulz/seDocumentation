package com.codelight.documentation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/archivedDocumentsManagement")
public class ArchivedDocumentsManagement {

	@GetMapping(value = "")
	public String getArchivedTopics() {
		return "archivedDocumentsManagement";
	}
}
