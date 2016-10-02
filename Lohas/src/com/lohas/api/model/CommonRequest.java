package com.lohas.api.model;

public class CommonRequest {
	
	/*
	 * Authentication token
	 * If user already logged in, it should provide a token to verify
	 */
	private String token;
	
	/*
	 * User ID
	 * It is either bankerId or customerId
	 */
	private String userId;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
