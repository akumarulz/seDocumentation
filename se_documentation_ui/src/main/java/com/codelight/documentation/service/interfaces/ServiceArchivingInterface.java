package com.codelight.documentation.service.interfaces;

import com.michael.documentation.resources.model.topics.Topic;

public interface ServiceArchivingInterface {

	/**
	 * It archives a topic
	 * @param topic
	 */
	public Boolean archiveTopic(Topic topic);
}
