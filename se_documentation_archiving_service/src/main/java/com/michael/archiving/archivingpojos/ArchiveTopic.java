package com.michael.archiving.archivingpojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ArchiveTopic implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String archivTopicTitle;
	private Long archivedTopicid;

	private List<ArchiveSubTopic> archiveSubTopics = new ArrayList<>();

	public ArchiveTopic() {

	}

	public String getArchivTopicTitle() {
		return archivTopicTitle;
	}

	public void setArchivTopicTitle(String archivTopicTitle) {
		this.archivTopicTitle = archivTopicTitle;
	}

	public Long getArchivedTopicid() {
		return archivedTopicid;
	}

	public void setArchivedTopicid(Long archivedTopicid) {
		this.archivedTopicid = archivedTopicid;
	}

	public List<ArchiveSubTopic> getArchiveSubTopics() {
		return archiveSubTopics;
	}

	public void setArchiveSubTopics(List<ArchiveSubTopic> archiveSubTopics) {
		this.archiveSubTopics = archiveSubTopics;
	}
}
