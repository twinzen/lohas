package com.lohas.api.model;

import java.util.List;

public class ErrorResponse extends CommonResponse {

	/*
	 * Reason Code
	 * If Warning or Error, tell me why?
	 */
	private String reasonCode;
	
	/*
	 * Reason details
	 * Tell me more detail what is happening.
	 */
	private List<String> reasonDetails;
	
	public ErrorResponse () {
		this.setResponseCode(8); // Error, response code is 8
	}
	
	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public List<String> getReasonDetails() {
		return reasonDetails;
	}

	public void setReasonDetails(List<String> reasonDetails) {
		this.reasonDetails = reasonDetails;
	} 
	
}
