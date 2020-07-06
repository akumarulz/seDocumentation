package com.codelight.documentation.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codelight.documentation.docexceptions.SEdocumentationException;
import com.codelight.documentation.requests.DeleteSubTopicRequest;
import com.codelight.documentation.requests.PostFormRequest;
import com.codelight.documentation.response.PostFormResponse;
import com.codelight.documentation.service.interfaces.ServiceInterface;
import com.codelight.documentation.service.interfaces.ServiceTopicValidationInterface;
import com.codelight.documentation.utils.DeleteSubTopicWrapper;
import com.codelight.documentation.utils.SaveTopicWrapper;
import com.codelight.documentation.utils.Utils;
import com.michael.documentation.resources.model.pojo.NavigationWrapper;
import com.michael.documentation.resources.model.topics.Topic;
import com.michael.documentation.resources.utils.DocumentConstants;


@Controller
@RequestMapping(value = { "/addDocument" })
public class AddDoc {

	private static Logger logger = LogManager.getLogger(Documentation.class);
	
	@Autowired
	private ServiceInterface serviceImple;
	
	@Autowired
	private ServiceTopicValidationInterface serviceTopicValidationImpl;
	
	@Autowired
	private NavigationWrapper navigationWrapper;
	
	@RequestMapping
	public String home(Model model) {
		
		Properties props = null;
		try {
			props = Utils.getProperties();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		List<Topic> currentTopics = null;
		try {
			currentTopics = serviceImple.getAllTopics();
		} catch (SEdocumentationException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("currentTopics", currentTopics);
		model.addAttribute("messageProperties",props);
		model.addAttribute("navigationList", navigationWrapper.getNavList());
		return DocumentConstants.PAGE_SE_DOCUMENT_ADD_DOC;
	}
	
	@ResponseBody
	@RequestMapping(value = "/postDocument", method = RequestMethod.POST, 
	consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PostFormResponse postForm(@RequestBody PostFormRequest request) {
		var formMap = request.getForm();
		
		Topic topic = serviceTopicValidationImpl.validateTopic(formMap);
		
		SaveTopicWrapper wrapper = new SaveTopicWrapper();
		wrapper.setTopic(topic);
		var restResponse = serviceImple.postDocument(wrapper);
		var uiResponse = new PostFormResponse();
		uiResponse.setSuccessful(restResponse.getSuccess());
		logger.debug("in post Form");
		
		return uiResponse;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteSubtopic", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean deleteSubtopic(@RequestBody DeleteSubTopicRequest request) {
		var map = request.getDeleteSubTopicRequest();
		int subTopicId = Integer.parseInt(map.get(DocumentConstants.SUBTOPIC_DELETE_ID_VALUE));
		DeleteSubTopicWrapper wrapper = new DeleteSubTopicWrapper();
		wrapper.setSubTopicId(subTopicId);
		return serviceImple.deleteSubTopic(wrapper);
		
	}
	
	
	
}
