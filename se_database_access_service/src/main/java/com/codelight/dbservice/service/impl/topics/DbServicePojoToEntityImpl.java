package com.codelight.dbservice.service.impl.topics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import org.springframework.stereotype.Service;

import com.codelight.dbservice.model.EntitySubTopic;
import com.codelight.dbservice.model.EntityTopic;
import com.codelight.dbservice.service.interf.topics.DbServicePojoToEntityInterface;
import com.michael.documentation.resources.model.topics.SubTopic;
import com.michael.documentation.resources.model.topics.Topic;

@Service
public class DbServicePojoToEntityImpl implements DbServicePojoToEntityInterface {

	@Override
	public EntityTopic fromTopicPojoToEntityTopic(Topic topic) {
		EntityTopic entityTopic = new EntityTopic();
		
		if(topic.getId() != null) {
			entityTopic.setTopic_id(topic.getId());			
		}
		entityTopic.setTopicTitle(topic.getTopicTitle());
		
		return entityTopic;
	}

	@Override
	public Set<EntitySubTopic> fromSubTopicPojoToEntitySubTopic(Set<SubTopic> subtopicSet, EntityTopic topic) {
		Set<EntitySubTopic> entitySubTopicSet = new HashSet<>();
		
		Consumer<SubTopic> SetEntitySubTopicSet = subtopic -> {
			
			EntitySubTopic entitySubTopic = new EntitySubTopic();
			
			if(subtopic != null) {
				
				entitySubTopic.setSubtopic_id(subtopic.getId() > -1 ? subtopic.getId() : null);
			}
			
			entitySubTopic.setContent(subtopic.getContent());
			entitySubTopic.setTitle(subtopic.getTitle());
			entitySubTopic.setTopic(topic);
			
			entitySubTopicSet.add(entitySubTopic);
		};
		
		subtopicSet.forEach(SetEntitySubTopicSet);
		
		return entitySubTopicSet;
	}

	@Override
	public Topic fromEntityToPojoTopic(EntityTopic entityTopic) {
		Topic topic = new Topic();
		topic.setId(entityTopic.getTopic_id());
		topic.setTopicTitle(entityTopic.getTopicTitle());
		return topic;
	}

	@Override
	public Topic fromEntityToPojo(EntityTopic entityTopic) {
		Topic topic = new Topic();
		topic.setId(entityTopic.getTopic_id());
		topic.setTopicTitle(entityTopic.getTopicTitle());
		
		/*
		 * turn a set of entitysubtopics into a list of subtopics 
		 */
		List<SubTopic> list = entityTopic.getSubtopic_list().stream().collect(ArrayList::new, (subTopicList,entitySubtopic) ->{
			SubTopic subtopic = new SubTopic.Builder()
			.setContent(entitySubtopic.getContent())
			.setId(entitySubtopic.getSubtopic_id())
			.setTitle(entitySubtopic.getTitle())
			.build();
			
			subTopicList.add(subtopic);
			
		}, (list1,list2) -> list1.addAll(list2) );
		
		list.forEach(item -> topic.addSubTopic(item));
		return topic;
	}

}
