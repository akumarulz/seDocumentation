package com.codelight.documentation.service.imple;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.codelight.documentation.service.interfaces.ServiceTopicValidationInterface;
import com.michael.documentation.resources.model.topics.SubTopic;
import com.michael.documentation.resources.model.topics.Topic;
import com.michael.documentation.resources.utils.DocumentConstants;
@Service
public class ServiceTopicValidationImpl implements ServiceTopicValidationInterface {

	@Override
	public Topic validateTopic(Map<String, String> formMap) {
		
		
		//make a new  subtopic
				SubTopic subTopic = getSubtopicdetails(formMap);
				
				//make a topic
				String title = formMap.get(DocumentConstants.FORM_TOPIC_NEW);
				Topic topic = new Topic();
				if(title == null) {
					topic.setId(Long.parseLong(formMap.get(DocumentConstants.FORM_CURRENT_TOPIC)));
				}else {
					topic.setTopicTitle(title);
				}
				
				topic.addSubTopic(subTopic);
				
		return topic;

	}
	
	private SubTopic getSubtopicdetails(Map<String,String> form){
			
			return new SubTopic.Builder()
					.setContent(form.get(DocumentConstants.FORM_CONTENT))
					.setTitle(form.get(DocumentConstants.FORM_TITLE))
					.build();
			
		}


}
