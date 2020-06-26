package com.codelight.documentation.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codelight.documentation.docexceptions.SEdocumentationException;
import com.codelight.documentation.requests.DeleteTopicRequest;
import com.codelight.documentation.requests.GetSubTopicRequest;
import com.codelight.documentation.requests.GetTopicRequest;
import com.codelight.documentation.response.DeleteSubTopicResponse;
import com.codelight.documentation.response.GetSubTopicResponse;
import com.codelight.documentation.response.GetTopicResponse;
import com.codelight.documentation.service.interfaces.ServiceArchivingInterface;
import com.codelight.documentation.service.interfaces.ServiceInterface;
import com.codelight.documentation.utils.Utils;
import com.michael.documentation.resources.model.topics.SubTopic;
import com.michael.documentation.resources.model.topics.Topic;
import com.michael.documentation.resources.utils.DocumentConstants;
import com.michael.documentation.resources.utils.ResourceUtils;


@Controller
@RequestMapping(value= {"/","/documentation"})
public class Documentation {
	
	private static Logger logger = LogManager.getLogger(Documentation.class);
	private static Map<Long, SubTopic> subTopicsMap = new HashMap<>();
	
	@Autowired 
	private ServiceInterface serviceInterface;
	
	@Autowired
	private ServiceArchivingInterface serviceArchivingImpl;
	
	@RequestMapping(value= {""})
	public String home(Model model) throws SEdocumentationException  {
		
	
		List<Topic> topicsList = serviceInterface.getTopics();
		
		//get the subtopics from the topics. and put the in a map for later access
		topicsList.stream().forEach(topic -> {
			
			topic.getSubTopics().stream().forEach(subTopic ->{
				subTopicsMap.put(subTopic.getId(), subTopic);
			});
			
		});
		
		model.addAttribute("topicList", topicsList);
		
		Properties props = null;
		try {
			props = Utils.getProperties();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		model.addAttribute("messageProperties",props);
		return DocumentConstants.PAGE_SE_DOCUMENT_DOCUMENTATION;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getsubtopic", method= RequestMethod.POST)
	public GetSubTopicResponse getSubTopic(@RequestBody GetSubTopicRequest request) {
		
		logger.debug("inside getsubtopic");
		
		GetSubTopicResponse response = new GetSubTopicResponse();
		response.setSubtopic(subTopicsMap.get(request.getSubtopicId()));
		
		return response;
	}
	
	@ResponseBody
	@PostMapping(value = "/getTopic")
	public GetTopicResponse getTopic(@RequestBody GetTopicRequest request) throws SEdocumentationException {
		var response = new GetTopicResponse();
		
		serviceInterface.getTopic(request.getTopicId()).ifPresent(topic -> {
			response.setTopic(topic);
			response.setSuccessful(true);
		});
		
		
		return response;
	}
	
	@ResponseBody
	@PostMapping(value = "/deleteTopic")
	public DeleteSubTopicResponse deleteTopic(@RequestBody DeleteTopicRequest request) {
		var response = new DeleteSubTopicResponse();
		var success = serviceInterface.deleteTopic(request.getTopicId());
		ExecutorService service = ResourceUtils.getExecutorService();
		
		var archiveCompleted = false;
		Future<Boolean> future = service.submit(() -> serviceArchivingImpl.archiveTopic(success.u));
		try {
			archiveCompleted = future.get().booleanValue();
		} catch (InterruptedException |ExecutionException e) {
			e.printStackTrace();
		}
		response.setSuccessful(success.t ==true && archiveCompleted == true);
		return response;
	}

}
