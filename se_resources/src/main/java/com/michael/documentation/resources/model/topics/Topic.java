package com.michael.documentation.resources.model.topics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Topic {

	private String topicTitle;
	private Long id;

	private List<SubTopic> subTopics = new ArrayList<>();

	public Topic() {

	}

	public Topic (String topicTitle) {
		this(topicTitle,-1L);
	}
	
	public Topic(String topicTitle, Long id) {
		this.topicTitle = topicTitle;
		this.id = id;
	}
	
	public void setTopicTitle(String topicTitle) {
		this.topicTitle = topicTitle;
	}

	public void addSubTopic(SubTopic subTopic) {
		if (subTopics == null) {
			this.subTopics = new ArrayList<>();
		}

		this.subTopics.add(subTopic);
	}

	public Set<SubTopic> getSubTopics() {
		Collections.sort(subTopics, new Comparator<SubTopic>() {

			@Override
			public int compare(SubTopic o1, SubTopic o2) {
				return o1.getTitle().compareTo(o2.getTitle());
			}
		});

		return new HashSet<SubTopic>(subTopics);
	}

	public String getTopicTitle() {
		return topicTitle;
	}

	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Topic [topicTitle=" + topicTitle + ", subTopicList=" + subTopics + "]";
	}

}
