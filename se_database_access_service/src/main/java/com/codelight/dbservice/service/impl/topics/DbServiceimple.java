package com.codelight.dbservice.service.impl.topics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codelight.dbservice.model.EntitySubTopic;
import com.codelight.dbservice.model.EntityTopic;
import com.codelight.dbservice.repository.SubTopicRepositoryInterface;
import com.codelight.dbservice.repository.TopicRepositoryInterface;
import com.codelight.dbservice.service.interf.topics.DbServiceInterface;
import com.codelight.dbservice.service.interf.topics.DbServicePojoToEntityInterface;
import com.michael.documentation.resources.model.topics.SubTopic;
import com.michael.documentation.resources.model.topics.Topic;
import com.michael.documentation.resources.utils.Tuple;

@Service
public class DbServiceimple implements DbServiceInterface {

	@Autowired
	private DbServicePojoToEntityInterface dbServicePojoToEntityimpl;
	
	@Autowired
	private SubTopicRepositoryInterface subtopicRepo;
	
	@Autowired
	private TopicRepositoryInterface topicRepo;

	@Override
	public void save(Topic topic) {
		Long topicId = topic.getId();
		Optional<EntityTopic> entTop = Optional.empty();
		
		if(topicId != null) {
			entTop = topicRepo.findById(topic.getId());
		}
		
		if(entTop.isPresent()) {
			updateTopicSubTopicList(topic.getSubTopics(), entTop.get());
		}else {
			saveTopic(topic);
		}
		
		
	}
	
	private void saveTopic(Topic topic) {
				
				EntityTopic entityTopic = dbServicePojoToEntityimpl.fromTopicPojoToEntityTopic(topic);
				
				Set<EntitySubTopic> entitySubTopic = dbServicePojoToEntityimpl
						.fromSubTopicPojoToEntitySubTopic(topic.getSubTopics(), entityTopic);
				
				entityTopic.setSubtopic_list(entitySubTopic);
				
				topicRepo.save(entityTopic);
	}
	
	private void updateTopicSubTopicList(Set<SubTopic> subtopic, EntityTopic entityTopic) {
				
				Set<EntitySubTopic> entitySubTopic = dbServicePojoToEntityimpl
						.fromSubTopicPojoToEntitySubTopic(new HashSet<>(subtopic), entityTopic);
				
				subtopicRepo.saveAll(entitySubTopic);
	}

	@Override
	public Topic findTopicById(Long topicId) {
		Optional<EntityTopic> entityTopic = topicRepo.findById(topicId);
		if(entityTopic.isPresent()){
			return dbServicePojoToEntityimpl.fromEntityToPojoTopic(entityTopic.get());
		}
	return null;
	}

	@Override
	public Topic findTopicByIdComplete(Long topicId) {
		Optional<EntityTopic> entityTopic = topicRepo.findById(topicId);
		return dbServicePojoToEntityimpl.fromEntityToPojo(entityTopic.get());
	}
	
	@Override
	public Optional<List<Topic>> findAllTopics() {
		List<Topic> topicList = new ArrayList<>();
		
		List<EntityTopic> entityTopicList = topicRepo.findAll();
		entityTopicList.forEach(entityTopic -> {
			Topic topic = dbServicePojoToEntityimpl.fromEntityToPojo(entityTopic);
			topicList.add(topic);
			} );
		
		if(!topicList.isEmpty()) {
			return Optional.of(topicList);			
		}else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<List<Topic>> findAllTopicsOnly() {
		List<EntityTopic> entityTopicList = topicRepo.findAll();
		
		List<Topic> topicList = entityTopicList.stream()
				.collect(ArrayList::new, //set the object (list, map, Set or other object)
						 (arrayList,entTopic) -> { 
							 Topic topic = dbServicePojoToEntityimpl.fromEntityToPojoTopic(entTopic);
							 arrayList.add(topic);
						 }, 
						 (list1,list2) -> list2.addAll(list1));
		
		if(!topicList.isEmpty()) {
			return Optional.of(topicList);
		}else {
			return Optional.empty();
		}
	}

	@Override
	public boolean deleteSubtopic(Long subtopicID) {
		Optional<EntitySubTopic> optionalSubtopic = subtopicRepo.findById(subtopicID);
		if(optionalSubtopic.isPresent()) {
			subtopicRepo.delete(optionalSubtopic.get());
			return true;
		}
		
		return false;
	}

	@Override
	public Tuple<Boolean,Topic> deleteTopic(Long topicId) {
		Topic topic = this.findTopicByIdComplete(topicId); //get the topic to be returned later
		topicRepo.deleteById(topicId); // delete the topic
		return new Tuple<>(
				this.findTopicById(topicId) == null, // should be false 
				topic); //return the topic so its archived in another service
		
	}
}
