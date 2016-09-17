package com.lohas.api.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;

import com.lohas.validation.constraints.CheckCurrency;

public class OpenBankRequest extends CommonRequest {

	/*
	 * Email of the first banker
	 */
	@Email(message="Please provide a valid email address")
	@NotNull
	private String email;
	

	/*
	 * Password of the first banker login
	 */
	@NotNull
	@Min(6)
	@Pattern(regexp = "^[A-Za-z0-9]+$")
	private String password;
	
	/*
	 * Name of the first banker
	 */
	@NotNull
	@Pattern(regexp = "^[A-Za-z0-9]+$")
	private String adminName;
	
	/*
	 * Name of the first banker
	 */
	@NotNull
	@Pattern(regexp = "^[A-Za-z0-9]+$")
	private String appellation;
	
	/*
	 * Base currency of this bank
	 */
	@NotNull
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
