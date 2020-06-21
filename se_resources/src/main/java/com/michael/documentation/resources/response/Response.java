package com.michael.documentation.resources.response;

import java.util.List;

import com.michael.documentation.resources.model.topics.Topic;

public class Response {
	private boolean success;
	private String errorCode;
	private List<String> errorMsgs;
	private Topic responseTopic;
	
	public Response() {

	}

	public Response(boolean e) {
		this.success = e;
	}

	public boolean getSuccess() {
		return this.success;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public List<String> getErrorMsgs() {
		return errorMsgs;
	}

	public void setErrorMsgs(List<String> errorMsgs) {
		this.errorMsgs = errorMsgs;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Topic getResponseTopic() {
		return responseTopic;
	}

	public void setResponseTopic(Topic responseTopic) {
		this.responseTopic = responseTopic;
	}

}
