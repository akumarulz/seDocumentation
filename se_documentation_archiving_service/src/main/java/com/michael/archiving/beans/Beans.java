package com.michael.archiving.beans;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

import com.michael.documentation.resources.utils.DocumentConstants;
import com.michael.documentation.resources.utils.ResourceUtils;

@Configuration
public class Beans {

	@Bean
	@LoadBalanced
	public WebClient.Builder getWebClientBuilder() {
		// @formatter:off
		Builder clientBuilder = WebClient.builder()
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
				.baseUrl(ResourceUtils.constructBaseRestUrl(DocumentConstants.URL_SE_DOCUMENT_DB_ACCESS_PROTOCOL,
						DocumentConstants.URL_SE_DOCUMENT_DB_ACCESS_NAME,
						DocumentConstants.URL_SE_DOCUMENT_DB_ACCESS_CONTEXTPATH,
						DocumentConstants.URL_SE_DOCUMENT_DB_ACCESS_ARCHIVECONTROLLER));

		return clientBuilder;
		// @formatter:on
	}

}
