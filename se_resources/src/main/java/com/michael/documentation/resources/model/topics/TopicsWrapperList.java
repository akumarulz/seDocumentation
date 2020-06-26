package com.michael.documentation.resources.model.topics;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.michael.documentation.resources.response.Response;

public class TopicsWrapperList extends Response {

	private List<Topic> topicsList;

	public TopicsWrapperList() {}
	
	public List<Topic> getTopics() {
		Collections.sort(topicsList, new Comparator<Topic>() {

			@Override
			public int compare(Topic o1, Topic o2) {
				return o1.getTopicTitle().compareTo(o2.getTopicTitle());
			}
			
		});
		return topicsList;
	}

	public void setTopics(List<Topic> subtopics) {
		this.topicsList = subtopics;
	}
	
	
}
