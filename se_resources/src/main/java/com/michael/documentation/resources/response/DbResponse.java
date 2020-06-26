package com.michael.documentation.resources.response;

public class DbResponse<T> extends Response {
	
	private T t;
	
	public DbResponse (T t) {
		this.setT(t);
	}
	
	public DbResponse () {
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}
	
	
	
	
}
