package com.codelight.documentation.response;

import com.michael.documentation.resources.model.topics.Topic;

public class GetTopicResponse extends WebResponse {

	private Topic topic;

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	
	
}
