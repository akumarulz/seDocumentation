package com.codelight.documentation.requests;

public class GetArchiveEntryRecordsRequest extends WebRequest {

	private int limit;
	private int offset;

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

}
