package com.codelight.dbservice.restcontroller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codelight.dbservice.requests.RequestDeleteSubTopic;
import com.codelight.dbservice.requests.RequestSave;
import com.codelight.dbservice.service.interf.topics.DbServiceInterface;
import com.michael.documentation.resources.model.topics.Topic;
import com.michael.documentation.resources.model.topics.TopicsWrapper;
import com.michael.documentation.resources.model.topics.TopicsWrapperList;
import com.michael.documentation.resources.response.Response;
import com.michael.documentation.resources.utils.DocumentConstants;
import com.michael.documentation.resources.utils.Tuple;

@RestController
@RequestMapping({"/"})
public class DbAccessController {
	
	@Autowired
	private DbServiceInterface service;

	@Value("${greeting.hello}")
	private String greeting;
	
	
	@GetMapping("/test")
	public String test() {
		return greeting;
	}
	
	@GetMapping(value = DocumentConstants.URL_GET_TOPICS)
	public ResponseEntity<TopicsWrapper> getTopics() {
		
		List<Topic> topicsList = new ArrayList<>();
		Optional<List<Topic>> optionalTopicList = service.findAllTopics();
		
		if(optionalTopicList.isPresent()) {
			List<Topic> dbList = optionalTopicList.get();
			topicsList.addAll(dbList);
		}
		
		TopicsWrapper wrapper = new TopicsWrapper();
		wrapper.setTopicsList(topicsList);
	
		return ResponseEntity.ok(wrapper);
	}
	
	@GetMapping(value = DocumentConstants.URL_GET_TOPICS_LIST)
	public ResponseEntity<TopicsWrapperList> getAllTopicsOnly() {
		TopicsWrapperList wrapper = new TopicsWrapperList();
		
		List<Topic> subTopicList = new ArrayList<>();
		
		Optional<List<Topic>> optionalTopicList = service.findAllTopicsOnly();
		if(optionalTopicList.isPresent()) {
			subTopicList.addAll(optionalTopicList.get());
		}
		
		wrapper.setTopics(subTopicList);
		
		return ResponseEntity.ok(wrapper);
	}
	
	@PostMapping(value = DocumentConstants.URL_SAVE_TOPIC)
	public ResponseEntity<Response> saveTopic(@RequestBody RequestSave request){
		service.save(request.getTopic());
		return ResponseEntity.ok(new Response(true));
	}
	
	@DeleteMapping(value = DocumentConstants.URL_DELETE_SUBTOPIC)
	public ResponseEntity<Response> deleteSubTopic(@RequestBody RequestDeleteSubTopic request){
		boolean deleted = service.deleteSubtopic(request.getSubTopicId());
		return ResponseEntity.ok(new Response(deleted));
		
	}
	
	@GetMapping(value = DocumentConstants.URL_GET_TOPIC + "/{topicId}")
	public ResponseEntity<TopicsWrapperList> getTopic(@PathVariable("topicId") Long topicId){
		TopicsWrapperList topicsWrapperList = new TopicsWrapperList();
		
		Topic topic = service.findTopicByIdComplete(topicId);
		System.out.println(topic.getSubTopics());
		if(topic != null) {
			topicsWrapperList.setTopics(Arrays.asList(topic));
			topicsWrapperList.setSuccess(true);
		}
		
		return ResponseEntity.ok(topicsWrapperList);
	}
	
	@DeleteMapping(value = DocumentConstants.URL_DELETE_TOPIC + "/{topicId}")
	public ResponseEntity<Response> deleteTopic(@PathVariable("topicId") Long topicId){
		Response response = new Response();
		
		Tuple<Boolean,Topic> deletedTopic = service.deleteTopic(topicId);
				
		response.setSuccess(deletedTopic.t);
		response.setResponseTopic(deletedTopic.u);
		return ResponseEntity.ok(response);
	}
}

