package com.codelight.documentation.service.imple.archivemanagement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import org.springframework.stereotype.Service;

import com.codelight.documentation.response.GetArchivedRecordsResponse;
import com.codelight.documentation.service.interfaces.archivemanagement.archivedDocumentsManagementInterface;
import com.michael.documentation.resources.model.archiving.ArchiveEntry;

@Service
public class archivedDocumentsManagementImple implements archivedDocumentsManagementInterface {

	private BiFunction<Integer,Integer,List<ArchiveEntry>> getArchiveEntries = (limit,offset) -> {
		
		return new ArrayList<ArchiveEntry>(Arrays.asList(
				new ArchiveEntry(1l,"one"),
				new ArchiveEntry(2l,"two"),
				new ArchiveEntry(3l,"three"),
				new ArchiveEntry(4l,"four")
				));
	};
	
	private Supplier<Integer> getNumTotalEntries = () -> {
		return 10;
	};
	
	private BiFunction<List<ArchiveEntry>, Integer, GetArchivedRecordsResponse> f = GetArchivedRecordsResponse::new;
	
	@Override
	public GetArchivedRecordsResponse getAllArchived(int limit, int offset) {
		return f.apply(getArchiveEntries.apply(limit, offset), getNumTotalEntries.get());
	}

}
