package com.michael.archiving.services.interf;

import com.michael.archiving.archivingpojos.ArchiveTopic;
import com.michael.documentation.resources.model.topics.Topic;

public interface ValidateTopicServiceinter {
	/**
	 * It validates a topic and returns the archivable equivalent object
	 * @param topic
	 * @return
	 */
	public ArchiveTopic validateTopic(Topic topic);
	
	/**
	 * It validate an Archived Topic and return the original Topic object
	 * @param archiveTopic
	 * @return
	 */
	public Topic validateArchiveTopic(ArchiveTopic archiveTopic);
}
