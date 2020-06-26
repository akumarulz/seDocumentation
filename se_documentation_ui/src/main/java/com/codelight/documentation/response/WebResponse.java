package com.codelight.documentation.response;

public abstract class WebResponse {

	private boolean successful = true;

	public boolean isSuccessful() {
		return successful;
	}

	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}
}
