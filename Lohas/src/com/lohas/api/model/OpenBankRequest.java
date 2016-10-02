package com.lohas.api.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.lohas.validation.constraints.CheckCurrency;

public class OpenBankRequest extends CommonRequest {

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
	@Length(min = 6, message = "The field must be at least 6 characters")
	@Pattern(regexp = "^[A-Za-z0-9]+$")
	private String password;
	
	/*
	 * Name of the first banker
	 */
	@NotNull
	@NotEmpty
	private String adminName;
	
	/*
	 * Name of the first banker
	 */
	@NotNull
	@NotEmpty
	@Pattern(regexp = "^[A-Za-z0-9]+$")
	private String appellation;
	
	/*
	 * Base currency of this bank
	 */
	@NotNull
	@NotEmpty
	@CheckCurrency
	private String baseCurrency;

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

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAppellation() {
		return appellation;
	}

	public void setAppellation(String appellation) {
		this.appellation = appellation;
	}

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}
	
}
