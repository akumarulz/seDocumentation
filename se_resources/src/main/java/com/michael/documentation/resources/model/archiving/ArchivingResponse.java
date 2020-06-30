package com.michael.documentation.resources.model.archiving;

import java.util.List;

public class ArchivingResponse {

	private List<String> archives;
	private List<ArchiveEntry> archiveEntryList;
	
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
}
