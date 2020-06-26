package com.codelight.dbservice.service.interf.archiving;

import java.util.List;

import com.michael.documentation.resources.model.archiving.ArchiveEntry;

public interface DbServiceArchivingInterf {

	/**
	 * stores a record name in the database
	 * @param recordName
	 * @return
	 */
	public boolean storeArchiveRecord(String recordName);
	
	/**
	 * It gets all the archive entries
	 * @return
	 */
	public List<ArchiveEntry> getAllTopicArchives();
}
