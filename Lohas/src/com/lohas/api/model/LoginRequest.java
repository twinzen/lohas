package com.lohas.api.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.lohas.validation.constraints.CheckCurrency;

public class LoginRequest extends CommonRequest {

	/*
	 * Email of the first banker
	 */
	@Email(message="Please provide a valid email address")
	@NotNull
	@NotEmpty
	private String email;
	

	/*
	 * Password of the first banker login
	 */
	@NotNull
	@NotEmpty
	@Pattern(regexp = "^[A-Za-z0-9]+$")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
