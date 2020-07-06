package com.codelight.dbservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.codelight.dbservice.utils.Utils;

@Table(name = Utils.ARCHIVE_TABLE)
@Entity
public class EntityArchiveRecord {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String archiveEntry;

	public String getArchiveEntry() {
		return archiveEntry;
	}

	public void setArchiveEntry(String archiveEntry) {
		this.archiveEntry = archiveEntry;
	}
	
	public Long getId() {
		return id;
	}
	
}
