package com.michael.documentation.resources.model.archiving;

public class ArchiveEntry {

	private Long id;
	
	private String archiveEntry;
	
	

	public ArchiveEntry(Long id, String archiveEntry) {
		super();
		this.id = id;
		this.archiveEntry = archiveEntry;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getArchiveEntry() {
		return archiveEntry;
	}

	public void setArchiveEntry(String archiveEntry) {
		this.archiveEntry = archiveEntry;
	}
}
