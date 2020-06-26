package com.codelight.documentation.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.http.ResponseEntity;

import com.codelight.documentation.docexceptions.SEdocumentationException;
import com.michael.documentation.resources.response.Response;

public class Utils {
	private static Properties properties;
	private Utils() {}
	
	
	public static <T extends Response> T getResponse(ResponseEntity<T> responseEntity, Class<T> clazz) throws SEdocumentationException{
		T rtndObject = clazz.cast(responseEntity.getBody());
		
		switch(responseEntity.getStatusCode()){
		case OK:
			return rtndObject;
		case ACCEPTED:
			
		case BAD_GATEWAY:
			
		case BAD_REQUEST:
		
		default:
			throw new SEdocumentationException(rtndObject.getErrorCode() ,rtndObject.getErrorMsgs().toString());
		}
	}
	public static Properties getProperties() throws IOException {
		if(properties == null) {
			properties = getMessageProperties();
		}
		
		return properties;
	}
	
	private static Properties getMessageProperties() throws IOException{
		try(InputStream input = Utils.class.getClassLoader().getResourceAsStream("messages.properties")){
			Properties props = new Properties();
			
			if(input == null) {
				return null;
			}
			props.load(input);
			
			return props;
		}
	}
}
