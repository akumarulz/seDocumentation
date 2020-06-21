package com.codelight.documentation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.codelight.documentation.inteceptors.SeDocumentationInterceptor;

public class SEdocumentationInterfaceAppConfig implements WebMvcConfigurer{

	@Autowired
	SeDocumentationInterceptor seDocumentationInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(seDocumentationInterceptor);
	}
	
	
	
}
