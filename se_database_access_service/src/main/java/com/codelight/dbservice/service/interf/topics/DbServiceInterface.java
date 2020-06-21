package com.codelight.dbservice.service.interf.topics;

import java.util.List;
import java.util.Optional;

import com.michael.documentation.resources.model.topics.Topic;
import com.michael.documentation.resources.utils.Tuple;

public interface DbServiceInterface {

	/**
	 * It saves a topic and subtopic
	 * @param topic
	 */
	public void save(Topic topic);
	
	/**
	 * It finds a topic based on a given id
	 * @param TpoicId
	 * @return
	 */
	public Topic findTopicById(Long topicId);
	
	/**
	 * It finds a topic based on a given id and return the object and all children objects
	 * @param TpoicId
	 * @return
	 */
	public Topic findTopicByIdComplete(Long topicId);
	
	/**
	 * It gets all the topics and associated subtopics
	 * @return
	 */
	public Optional<List<Topic>> findAllTopics();
	
	/**
	 * It gets all the topics without the subtopics
	 * @return
	 */
	public Optional<List<Topic>> findAllTopicsOnly();
	
	/**
	 * It deletes a subtopic by id
	 * @param subtopicID
	 * @return
	 */
	public boolean deleteSubtopic(Long subtopicID);
	
	/**
	 * It deletes a Topic with the given id
	 * @param topicId
	 * @return
	 */
	public Tuple<Boolean,Topic> deleteTopic(Long topicId);
}
