package com.codelight.documentation.response;

import com.michael.documentation.resources.model.topics.SubTopic;

public class GetSubTopicResponse extends WebResponse {
	private SubTopic subtopic;

	public SubTopic getSubtopic() {
		return subtopic;
	}

	public void setSubtopic(SubTopic subtopic) {
		this.subtopic = subtopic;
	}
	
	
}
