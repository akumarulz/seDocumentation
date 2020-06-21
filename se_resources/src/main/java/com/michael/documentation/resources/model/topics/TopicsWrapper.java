package com.michael.documentation.resources.model.topics;

import java.io.Serializable;
import java.util.List;

import com.michael.documentation.resources.response.Response;

public class TopicsWrapper extends Response implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Topic> topicsList;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public TopicsWrapper() {
		
	}

	public List<Topic> getTopicsList() {
		return topicsList;
	}

	public void setTopicsList(List<Topic> topicsList) {
		this.topicsList = topicsList;
	}

}
