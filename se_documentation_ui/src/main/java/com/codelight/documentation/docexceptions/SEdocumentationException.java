package com.codelight.documentation.docexceptions;

public class SEdocumentationException extends Exception {
	private String errorCode;
	private String errorMsg;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3672007751916055984L;

	public SEdocumentationException() {
		super();
	}

	public SEdocumentationException(String errorCode, String errorMsg) {
		this();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
