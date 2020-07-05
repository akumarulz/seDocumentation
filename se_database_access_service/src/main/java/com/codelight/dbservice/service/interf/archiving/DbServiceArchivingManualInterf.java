package com.codelight.dbservice.service.interf.archiving;

import java.util.List;

import com.michael.documentation.resources.model.archiving.ArchiveEntry;

public interface DbServiceArchivingManualInterf {

	/**
	 * It gets archive entries based on params
	 * @param limit
	 * @param offset
	 * @return
	 */
	public List<ArchiveEntry> getEntries(Integer limit, Integer offset);
	
	/**
	 * It Get A count of all Archived Records
	 * @return
	 */
	public Integer getCountAllArchiveRecords();
}
