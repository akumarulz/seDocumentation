package com.codelight.documentation.response;

import java.util.List;

import com.codelight.documentation.requests.WebRequest;
import com.michael.documentation.resources.model.archiving.ArchiveEntry;

public class GetArchiveEntryRecordsResponse extends WebRequest {
	private Integer archiveRecordCount;
	private List<ArchiveEntry> archiveRecordList;

	public Integer getArchiveRecordCount() {
		return archiveRecordCount;
	}

	public void setArchiveRecordCount(Integer archiveRecordCount) {
		this.archiveRecordCount = archiveRecordCount;
	}

	public List<ArchiveEntry> getArchiveRecordList() {
		return archiveRecordList;
	}

	public void setArchiveRecordList(List<ArchiveEntry> archiveRecordList) {
		this.archiveRecordList = archiveRecordList;
	}

}
