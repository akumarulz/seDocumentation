package com.codelight.dbservice.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity(name = "topic")
@Table(name = "topic_table")
public class EntityTopic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long topic_id;

	private String topicTitle;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "topic_id")
	private Set<EntitySubTopic> subtopic_list = new HashSet<EntitySubTopic>();

	public Long getTopic_id() {
		return topic_id;
	}

	public void setTopic_id(Long topic_id) {
		this.topic_id = topic_id;
	}

	public String getTopicTitle() {
		return topicTitle;
	}

	public void setTopicTitle(String topicTitle) {
		this.topicTitle = topicTitle;
	}

	public Set<EntitySubTopic> getSubtopic_list() {
		return subtopic_list;
	}

	public void setSubtopic_list(Set<EntitySubTopic> subtopic_list) {
		this.subtopic_list = subtopic_list;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((topicTitle == null) ? 0 : topicTitle.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntityTopic other = (EntityTopic) obj;
		if (topicTitle == null) {
			if (other.topicTitle != null)
				return false;
		} else if (!topicTitle.equals(other.topicTitle))
			return false;
		return true;
	}

}
