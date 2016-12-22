package com.lohas.api.model;


public class GetCustomersRequest extends CommonRequest {

	/*
	 * usernames
	 * Not mandatory, if no usernames given, all customers returned
	 */
	private String[] usernames;

	public String[] getUsernames() {
		return usernames;
	}

	public void setUsernames(String[] usernames) {
		this.usernames = usernames;
	}


	
}
