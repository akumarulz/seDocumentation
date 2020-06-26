package com.codelight.documentation.service.imple;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.codelight.documentation.docexceptions.SEdocumentationException;
import com.codelight.documentation.service.interfaces.ServiceInterface;
import com.codelight.documentation.utils.Utils;
import com.michael.documentation.resources.model.topics.Topic;
import com.michael.documentation.resources.model.topics.TopicsWrapper;
import com.michael.documentation.resources.model.topics.TopicsWrapperList;
import com.michael.documentation.resources.response.Response;
import com.michael.documentation.resources.utils.DocumentConstants;
import com.michael.documentation.resources.utils.Tuple;

@Service
public class ServiceImple implements ServiceInterface {
	

	@Autowired
	@Qualifier("DBAccessWebclient")
	private WebClient.Builder webClientBuilder;

	
	private <T extends Response> T performGetRequest(String url, Class<T> clazz ) throws SEdocumentationException {
		ResponseEntity<T> response2 = webClientBuilder
				.build()
				.method(HttpMethod.GET)
				.uri(url)
				.exchange()
				.flatMap(response -> response.toEntity(clazz))
				.block();
		return Utils.<T>getResponse(response2, clazz);
	}

	private <T> Response performRequest(HttpMethod method,T body, String url) {
		ResponseEntity<Response> response = webClientBuilder
				.build()
				.method(method)
				.uri(url)
				.bodyValue(body)
				.exchange()
				.flatMap(res -> res.toEntity(Response.class))
				.block();
				
		return response.getBody();
	}
	
	@Override
	public List<Topic> getTopics() throws SEdocumentationException {

		TopicsWrapper wrapper = this.<TopicsWrapper>performGetRequest(DocumentConstants.URL_GET_TOPICS,
				TopicsWrapper.class);

		return wrapper.getTopicsList();
	}

	@Override
	public List<Topic> getAllTopics() throws SEdocumentationException {
		TopicsWrapperList response = this.<TopicsWrapperList>performGetRequest(DocumentConstants.URL_GET_TOPICS_LIST, TopicsWrapperList.class);
		return response.getTopics();
	}

	@Override
	public <T> Response postDocument(T wrapper) {
		return this.performRequest(HttpMethod.POST ,wrapper, DocumentConstants.URL_SAVE_TOPIC);

	}

	
	
	@Override
	public <T> boolean deleteSubTopic(T wrapper) {
		
		var response = performRequest(HttpMethod.DELETE, wrapper, DocumentConstants.URL_DELETE_SUBTOPIC);
		return response.getSuccess();
	}

	@Override
	public Optional<Topic> getTopic(Long topicId) throws SEdocumentationException {
		return this.<TopicsWrapperList>performGetRequest(DocumentConstants.URL_GET_TOPIC + "/" + topicId.longValue(), TopicsWrapperList.class )
				.getTopics()
				.stream()
				.findFirst();
	}

	@Override
	public Tuple<Boolean,Topic> deleteTopic(Long topicId) {
		var response = performRequest(HttpMethod.DELETE, topicId, DocumentConstants.URL_DELETE_TOPIC + "/" + topicId);
		Topic topic = response.getResponseTopic();
		
		return new Tuple<>(response.getSuccess(), topic);
	}

}
