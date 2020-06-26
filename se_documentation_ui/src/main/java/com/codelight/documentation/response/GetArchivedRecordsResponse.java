package com.codelight.documentation.response;

import java.util.List;

import com.michael.documentation.resources.model.archiving.ArchiveEntry;

public class GetArchivedRecordsResponse {

	private List<ArchiveEntry> archivedEntryList;
	private Integer totalEntries;
	
	
	
	public GetArchivedRecordsResponse(List<ArchiveEntry> archivedEntryList, Integer totalEntries) {
		super();
		this.archivedEntryList = archivedEntryList;
		this.totalEntries = totalEntries;
	}
	
	public List<ArchiveEntry> getArchivedEntryList() {
		return archivedEntryList;
	}
	public void setArchivedEntryList(List<ArchiveEntry> archivedEntryList) {
		this.archivedEntryList = archivedEntryList;
	}
	public Integer getTotalEntries() {
		return totalEntries;
	}
	public void setTotalEntries(Integer totalEntries) {
		this.totalEntries = totalEntries;
	}
}
