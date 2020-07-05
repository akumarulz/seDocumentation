package com.codelight.dbservice.service.impl.archiving;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codelight.dbservice.model.EntityArchiveRecord;
import com.codelight.dbservice.repository.ArchiveRespositoryInterface;
import com.codelight.dbservice.service.interf.archiving.DbServiceArchivingInterf;
import com.michael.documentation.resources.model.archiving.ArchiveEntry;

@Service
public class DbServiceArchivingImpl implements DbServiceArchivingInterf {

	@Autowired
	private ArchiveRespositoryInterface archiveRespositoryInterface;
	
	
	@Override
	public boolean storeArchiveRecord(String recordName) {
		if(recordName == null | recordName.isBlank()) {
			return false;
		}
		var record = new EntityArchiveRecord();
		record.setArchiveEntry(recordName);
		archiveRespositoryInterface.save(record);
		return true;
	}

	@Override
	public List<ArchiveEntry> getAllTopicArchives() {
		
		return archiveRespositoryInterface.findAll().stream().collect(ArrayList::new, 
				(arrayList, entry) -> {
					arrayList.add(
							new ArchiveEntry(
									entry.getId(),
									entry.getArchiveEntry()
									)
							);
				}, (l1,l2)->{
					l2.addAll(l1);
				});
		
	}

	

}
