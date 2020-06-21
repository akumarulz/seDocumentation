package com.codelight.documentation.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.codelight.documentation.docexceptions.SEdocumentationException;
import com.michael.documentation.resources.model.topics.Topic;
import com.michael.documentation.resources.response.Response;
import com.michael.documentation.resources.utils.Tuple;

public interface ServiceInterface {
	
	/**
	 * It gets all the topics and subtopic
	 * @return
	 * @throws SEdocumentationException
	 */
	public List<Topic> getTopics() throws SEdocumentationException;
	
	/**
	 * Gets all the topics and not the subtopics
	 * @return
	 * @throws SEdocumentationException
	 */
	public List<Topic> getAllTopics() throws SEdocumentationException;
	
	/**
	 * It posts a new subtopic that will be persisted into the database
	 * @param <T>
	 * @param wrapper
	 * @return
	 */
	public <T> Response postDocument(T wrapper);
	
	/**
	 * It deletes the selected subtopic
	 * @param <T>
	 * @param wrapper
	 * @return
	 */
	public <T> boolean deleteSubTopic(T wrapper);
	
	/**
	 * It get a single topic
	 * @param <T>
	 * @param wrapper
	 * @return
	 * @throws SEdocumentationException
	 */
	public Optional<Topic> getTopic(Long topicId) throws SEdocumentationException;
	
	/**
	 * It deletes a topic and returns the deleted topic
	 * @param topicId
	 * @return
	 */
	public Tuple<Boolean, Topic> deleteTopic(Long topicId);
}
