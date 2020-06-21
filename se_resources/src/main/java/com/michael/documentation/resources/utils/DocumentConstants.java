package com.michael.documentation.resources.utils;

public interface DocumentConstants {

	public static final String FORM_CONTENT = "content";
	public static final String FORM_TITLE = "title";
	public static final String FORM_CURRENT_TOPIC = "currentTopic";
	public static final String FORM_TOPIC_NEW = "newTopic";
	public static final String SUBTOPIC_DELETE_ID_VALUE = "subtopicValue";
	
	public static final String URL_SAVE_TOPIC = "/saveTopic";
	public static final String URL_GET_TOPICS_LIST = "/getTopicsList";
	public static final String URL_GET_TOPICS ="/getTopics";
	public static final String URL_DELETE_SUBTOPIC = "/deleteSubTopic";
	public static final String URL_GET_TOPIC = "/getTopic";
	public static final String URL_DELETE_TOPIC = "/deleteTopic";
	public static final String URL_ARCHIVE_ENTRY="/archiveEntry";
	
	public static final String URL_SE_DOCUMENT_DB_ACCESS_PROTOCOL = "http";
	public static final String URL_SE_DOCUMENT_DB_ACCESS_NAME = "sedatabaseAccessService";
	public static final String URL_SE_DOCUMENT_DB_ACCESS_CONTEXTPATH = "dbaccess";
	public static final String URL_SE_DOCUMENT_DB_ACCESS_ARCHIVECONTROLLER = "archivingService";
	
	public static final String URL_SE_DOCUMENT_ARCHIVING_PROTOCOL = "http";
	public static final String URL_SE_DOCUMENT_ARCHIVING_NAME = "SeDocumentationArchiving";
	public static final String URL_SE_DOCUMENT_ARCHIVING_CONTEXTPATH = "seDocumentationArchiving";
	public static final String URL_SE_DOCUMENT_ARCHIVING_ARCHIVE_TOPIC="/archiveTopic";
	
	public static final String PAGE_SE_DOCUMENT_ADD_DOC = "addDoc";
	public static final String PAGE_SE_DOCUMENT_DOCUMENTATION = "documentation";
}
