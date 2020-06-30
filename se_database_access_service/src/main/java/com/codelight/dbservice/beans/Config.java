package com.codelight.dbservice.beans;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.codelight.dbservice.propertiesclasses.MysqlProperties;

@Configuration
public class Config {

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource") 
	public MysqlProperties getMysqlProperties() {
		return new MysqlProperties();
	}
}
