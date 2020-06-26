package com.michael.archiving.services.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.michael.archiving.archivingpojos.ArchiveSubTopic;
import com.michael.archiving.archivingpojos.ArchiveTopic;
import com.michael.archiving.services.interf.ValidateTopicServiceinter;
import com.michael.documentation.resources.model.topics.SubTopic;
import com.michael.documentation.resources.model.topics.Topic;

@Service
public class ValidateTopicServiceImpl implements ValidateTopicServiceinter {

	@Override
	public ArchiveTopic validateTopic(Topic topic) {
		var archiveTopic = new ArchiveTopic();
		archiveTopic.setArchivedTopicid(topic.getId());
		archiveTopic.setArchivTopicTitle(topic.getTopicTitle());
		
		archiveTopic.setArchiveSubTopics(
				topic.getSubTopics()
				.stream().collect(
						ArrayList::new, 
						(arrayList,subTopic) -> {
						var archiveSubTopic = new ArchiveSubTopic
							.Builder()
							.setContent(subTopic.getContent())
							.setId(subTopic.getId())
							.setTitle(subTopic.getTitle())
							.build();
							arrayList.add(archiveSubTopic);
				},(s1,s2) -> s2.addAll(s1)));
		return archiveTopic;
	}

	@Override
	public Topic validateArchiveTopic(ArchiveTopic archiveTopic) {
		Topic topic = new Topic();
		topic.setId(archiveTopic.getArchivedTopicid());
		topic.setTopicTitle(archiveTopic.getArchivTopicTitle());
		archiveTopic.getArchiveSubTopics().stream().forEach(archiveSubTopic -> {
			var subTopic = new SubTopic.Builder()
					.setContent(archiveSubTopic.getContent())
					.setTitle(archiveSubTopic.getTitle())
					.setId(archiveSubTopic.getId())
					.build();
			topic.addSubTopic(subTopic);
		});
		return topic;
	}

}
