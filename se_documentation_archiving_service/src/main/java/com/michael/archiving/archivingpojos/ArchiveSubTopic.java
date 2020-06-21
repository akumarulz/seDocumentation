package com.michael.archiving.archivingpojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.michael.documentation.resources.model.topics.SubTopic;
import com.michael.documentation.resources.model.topics.SubTopic.Builder;

public class ArchiveSubTopic implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String title;
	private Long id;
	private String content;

	public ArchiveSubTopic() {
	}

	public ArchiveSubTopic(String title, Long id) {
		this.title = title;
		this.id = id;
	}

	public ArchiveSubTopic(Builder builder) {
		this.content = builder.content;
		this.id = builder.id;
		this.title = builder.title;

	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return this.title;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "ArchiveSubTopic [title=" + title + ", id=" + id + ", content=" + content + "]";
	}

	public static class Builder {
		private String title;
		private String content;
		private Long id = -1L;

		public Builder() {

		}

		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}

		public Builder setContent(String content) {
			this.content = content;
			return this;
		}

		public Builder setId(Long id) {
			this.id = id;
			return this;
		}

		public ArchiveSubTopic build() {
			return new ArchiveSubTopic(this);
		}
	}

}
