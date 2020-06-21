package com.codelight.dbservice.service.interf.topics;

import java.util.Set;

import com.codelight.dbservice.model.EntitySubTopic;
import com.codelight.dbservice.model.EntityTopic;
import com.michael.documentation.resources.model.topics.SubTopic;
import com.michael.documentation.resources.model.topics.Topic;

public interface DbServicePojoToEntityInterface {

	public EntityTopic fromTopicPojoToEntityTopic(Topic topic);
	public Set<EntitySubTopic> fromSubTopicPojoToEntitySubTopic(Set<SubTopic> subtopic, EntityTopic topic);
	public Topic fromEntityToPojoTopic(EntityTopic entityTopic);
	public Topic fromEntityToPojo(EntityTopic entityTopic);
}
