package com.codelight.documentation.requests;

public class GetSubTopicRequest extends WebRequest {

	private Long subtopicId;

	public Long getSubtopicId() {
		return subtopicId;
	}

	public void setSubtopicId(Long subtopicId) {
		this.subtopicId = subtopicId;
	}
}
