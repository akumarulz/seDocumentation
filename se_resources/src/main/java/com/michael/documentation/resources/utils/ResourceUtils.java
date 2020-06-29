package com.michael.documentation.resources.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.ObjectMapper;


public class ResourceUtils {
	private ResourceUtils() {
	}

	public static String constructBaseRestUrl(String protocol, String restAppName,
			String contextPath, String controller) {
		if(controller == null || controller.isBlank()) {
			controller = "";
		}
		
		//@formatter:off
		return new StringBuilder()
				.append(protocol)
				.append("://")
				.append(restAppName)
				.append("/")
				.append(contextPath)
				.append("/")
				.append(controller)
				.toString();
		// @formatter:on
	}
	
	public static String getNowDateAsString() {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE; 
		return date.format(formatter).toString();
		
	}
	
	public static String getNowTimeAsString() {
		LocalTime time = LocalTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH-mm-ss");
		return time.format(formatter);
		
	}
	
	public static ExecutorService getExecutorService() {
		int numOfThreads = Runtime.getRuntime().availableProcessors() * 2;
		
		ExecutorService service = Executors.newFixedThreadPool(numOfThreads);
		return service;
	}
	
	public static <T> Optional<T> getJsonObj(Class<T> clazz, String fileName) {
		
		try(InputStream input = ResourceUtils.class.getClassLoader().getResourceAsStream(fileName)){
			
			String in = IOUtils.toString(input, StandardCharsets.UTF_8);
			

            ObjectMapper mapper = new ObjectMapper();
            return Optional.of(mapper.readValue(in, clazz));
            
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Optional.empty();
		
	}
}
