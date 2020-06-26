package com.michael.archiving.services.interf;

import java.util.Optional;

import com.michael.archiving.archivingpojos.ArchiveTopic;
import com.michael.documentation.resources.model.topics.Topic;
import com.michael.documentation.resources.utils.Tuple;

public interface ArchiveTopicServiceIntef {
	/**
	 * It Archives an ArchiveTopic
	 * @param archiveTopic
	 * @return
	 */
	public Tuple<String,Boolean> performArchive(ArchiveTopic archiveTopic);
	
	/**
	 * It saves an ArchiveEntry name
	 * @param ArchiveTopicName
	 * @return
	 */
	public boolean saveArchiveNameToDb(String archiveTopicName);
	
	/**
	 * It restores a topic from the archive
	 * @param archiveTopicName
	 * @return
	 */
	public Optional<ArchiveTopic> restoreArchivedTopic(String archiveTopicName);
}
