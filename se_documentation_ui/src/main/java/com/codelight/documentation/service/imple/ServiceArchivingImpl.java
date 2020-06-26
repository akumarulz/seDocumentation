package com.codelight.documentation.service.imple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.codelight.documentation.service.interfaces.ServiceArchivingInterface;
import com.michael.documentation.resources.model.topics.Topic;
import com.michael.documentation.resources.model.topics.TopicWrapper;
import com.michael.documentation.resources.response.Response;
import com.michael.documentation.resources.utils.DocumentConstants;

@Service
public class ServiceArchivingImpl implements ServiceArchivingInterface {

	@Autowired
	@Qualifier("ArchivingWebClient")
	private WebClient.Builder webClient;
	
	
	@Override
	public Boolean archiveTopic(Topic topic) {
		TopicWrapper wrapper = new TopicWrapper();
		wrapper.setTopic(topic);
		Response response = this.performRequest(
				HttpMethod.POST, 
				wrapper,
				DocumentConstants.URL_SE_DOCUMENT_ARCHIVING_ARCHIVE_TOPIC);
		return response.getSuccess();
	}
	
	private <T> Response performRequest(HttpMethod method,T body, String url) {
		ResponseEntity<Response> response = webClient
				.build()
				.method(method)
				.uri(url)
				.bodyValue(body)
				.exchange()
				.flatMap(res -> res.toEntity(Response.class))
				.block();
				
		return response.getBody();
	}
}
