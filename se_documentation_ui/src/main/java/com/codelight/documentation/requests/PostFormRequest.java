package com.codelight.documentation.requests;

import java.util.Map;

public class PostFormRequest extends WebRequest {
	private Map<String,String> form;

	public Map<String, String> getForm() {
		return form;
	}

	public void setForm(Map<String, String> form) {
		this.form = form;
	}
	
	
}
