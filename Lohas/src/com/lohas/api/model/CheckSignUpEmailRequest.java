package com.lohas.api.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

public class CheckSignUpEmailRequest extends CommonRequest {

	/*
	 * Email
	 * The email address of primary banker who want to open a new bank
	 */
	@Email(message="Please provide a valid email address")
	@NotNull
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
