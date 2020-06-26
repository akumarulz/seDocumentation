package com.codelight.documentation.service.interfaces.archivemanagement;

import com.codelight.documentation.response.GetArchivedRecordsResponse;

public interface archivedDocumentsManagementInterface {

	/**
	 * It gets
	 * @param limit
	 * @param offset
	 * @return
	 */
	public GetArchivedRecordsResponse getAllArchived(int limit, int offset);
}
