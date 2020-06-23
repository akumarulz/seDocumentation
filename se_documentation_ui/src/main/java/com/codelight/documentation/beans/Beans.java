package com.codelight.documentation.beans;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

import com.michael.documentation.resources.utils.DocumentConstants;
import com.michael.documentation.resources.utils.ResourceUtils;

/**
 * 
 * @author michaelTaylor
 *
 */
@Configuration
public class Beans {

	@Bean(name="DBAccessWebclient")
	@LoadBalanced
	public WebClient.Builder getWebClientBuilder(){
		//@// @formatter:off
		Builder clientBuilder = WebClient
			.builder()
			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
			.baseUrl(ResourceUtils.constructBaseRestUrl(DocumentConstants.URL_SE_DOCUMENT_DB_ACCESS_PROTOCOL,
					DocumentConstants.URL_SE_DOCUMENT_DB_ACCESS_NAME,
					DocumentConstants.URL_SE_DOCUMENT_DB_ACCESS_CONTEXTPATH,
					null));
			
		return clientBuilder;
		// @formatter:on
	}
	
	@Bean(name="ArchivingWebClient")
	@LoadBalanced
	public WebClient.Builder getWebClientBuilderArchiving(){
		//@// @formatter:off
		Builder clientBuilder = WebClient
				.builder()
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
				.baseUrl(ResourceUtils.constructBaseRestUrl(DocumentConstants.URL_SE_DOCUMENT_ARCHIVING_PROTOCOL,
						DocumentConstants.URL_SE_DOCUMENT_ARCHIVING_NAME,
						DocumentConstants.URL_SE_DOCUMENT_ARCHIVING_CONTEXTPATH,
						null));
		
		return clientBuilder;
		// @formatter:on

	}
	
	
}