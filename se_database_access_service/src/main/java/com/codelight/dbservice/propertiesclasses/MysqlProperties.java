package com.codelight.dbservice.propertiesclasses;

public class MysqlProperties {

	private String url;
	private String username;
	private String password;
	private String archiveTable = "archive_records";

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getArchiveTable() {
		return archiveTable;
	}
}
