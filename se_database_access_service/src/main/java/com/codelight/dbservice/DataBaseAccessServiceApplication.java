package com.codelight.dbservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DataBaseAccessServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataBaseAccessServiceApplication.class, args);
	}

}
