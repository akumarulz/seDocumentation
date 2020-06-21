package com.codelight.dbservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="sub_topic")
@Table(name = "sub_topic")
public class EntitySubTopic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long subtopic_id;

	private String title;

	private String content;

	@ManyToOne(optional = false)
	@JoinColumn(name="topic_id")
	@JsonIgnore
	private EntityTopic topic;

	
	public Long getSubtopic_id() {
		return subtopic_id;
	}

	public void setSubtopic_id(Long subtopic_id) {
		this.subtopic_id = subtopic_id;
	}

	public EntityTopic getTopic() {
		return topic;
	}

	public void setTopic(EntityTopic topic) {
		this.topic = topic;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		EntitySubTopic other = (EntitySubTopic) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	
	
}
