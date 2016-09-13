package com.lohas.api.model;

import java.util.List;

public class CommonResponse {

	/*
	 * Response Code
	 * Normal, Warning, Error?
	 */
	private Integer responseCode;
	
	public CommonResponse () {
		this.setResponseCode(0); // Default normal response = 0
	}

	public Integer getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}

	
}
