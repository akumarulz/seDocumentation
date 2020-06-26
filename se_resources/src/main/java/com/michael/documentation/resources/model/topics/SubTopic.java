package com.michael.documentation.resources.model.topics;
/**
 * 
 * @author darda
 *
 */
public class SubTopic {

	private String title;
	private Long id;
	private String content;
	
	
	public SubTopic() {}
	
	public SubTopic(String title, Long id) {
		this.title = title;
		this.id = id;
	}
	public SubTopic(Builder builder) {
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
		return "SubTopic [title=" + title + ", id=" + id + "]";
	}
	
	
	

	public static class Builder{
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
		
		public SubTopic build() {
			return new SubTopic(this);
		}
	}
	
	
}
