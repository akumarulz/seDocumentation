package com.michael.documentation.resources.model.archiving;

import java.util.List;

import com.michael.documentation.resources.response.Response;

public class ArchivingResponse extends Response {

	private List<String> archives;
	private List<ArchiveEntry> archiveEntryList;
	private Integer countArchiveRecords;
	
	public List<String> getArchives() {
		return archives;
	}

	public void setArchives(List<String> archives) {
		this.archives = archives;
	}

	public List<ArchiveEntry> getArchiveEntryList() {
		return archiveEntryList;
	}

	public void setArchiveEntryList(List<ArchiveEntry> archiveEntryList) {
		this.archiveEntryList = archiveEntryList;
	}

	public Integer getCountArchiveRecords() {
		return countArchiveRecords;
	}

	public void setCountArchiveRecords(Integer countArchiveRecords) {
		this.countArchiveRecords = countArchiveRecords;
	}
}
