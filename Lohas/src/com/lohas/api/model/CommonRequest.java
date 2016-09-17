package com.lohas.api.model;

public class CommonRequest {
	
	/*
	 * Authentication token
	 * If user already logged in, it should provide a token to verify
	 */
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
