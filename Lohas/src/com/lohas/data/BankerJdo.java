package com.lohas.data;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class BankerJdo {

	/*
	 * Banker ID
	 * Primary key
	 */
	@Id
	@Index
	private Long bankerId;
	
	/*
	 * Banker Name
	 * What is your name?
	 */
	private String bankerName;
	
	/*
	 * Appellation
	 */
	private String appellation;
	
	/*
	 * Primary Email
	 * The email for login
	 */
	@Index
	private String primaryEmail;
	
	/*
	 * Password
	 * You must need a password when login
	 */
	private String password;
	
	
	/*
	 * Bank ID
	 * This banker belong to which bank?
	 * Foreign key of Bank
	 */
	@Index
	private Long bankId;


	public Long getBankerId() {
		return bankerId;
	}


	public void setBankerId(Long bankerId) {
		this.bankerId = bankerId;
	}


	public String getBankerName() {
		return bankerName;
	}


	public void setBankerName(String bankerName) {
		this.bankerName = bankerName;
	}


	public String getAppellation() {
		return appellation;
	}


	public void setAppellation(String appellation) {
		this.appellation = appellation;
	}


	public String getPrimaryEmail() {
		return primaryEmail;
	}


	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Long getBankId() {
		return bankId;
	}


	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}
	
}
