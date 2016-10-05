package com.lohas.api.exception;

public class ApplicationException extends Exception {

	private String reasonCode;
	
	private String message;

	public ApplicationException (String reasonCode, String message) {
		this.reasonCode = reasonCode;
		this.message = message;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
