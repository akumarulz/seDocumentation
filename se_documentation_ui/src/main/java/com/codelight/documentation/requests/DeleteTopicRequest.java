package com.codelight.documentation.requests;

public class DeleteTopicRequest extends WebRequest {
	private Long topicId;

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}
}
