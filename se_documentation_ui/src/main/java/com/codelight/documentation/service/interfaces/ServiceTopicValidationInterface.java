package com.codelight.documentation.service.interfaces;

import java.util.Map;

import com.michael.documentation.resources.model.topics.Topic;

public interface ServiceTopicValidationInterface {
	public Topic validateTopic(Map<String,String> topicMap);
}
