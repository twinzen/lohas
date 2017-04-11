package com.lohas.api.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.lohas.data.BlobImageJdo;

public class UpdateCustomerProfileRequest extends CommonRequest {

	/* 
	 * customer username 
	 */
	@NotEmpty
	private String username;
	
	/*
	 * Customer Name
	 */
	@NotEmpty
	private String customerName;
	
	/*
	 * Gender
	 */
	@NotEmpty
	private String gender;
	
	/*
	 * Date Of Birth
	 */
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
	
	/*
	 * Profile Picture Static URL
	 */
	private String profilePicUrl;
	
	
	/*
	 * Profile Picture Blob Key
	 */
	private String profilePicBlobKey;
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getProfilePicUrl() {
		return profilePicUrl;
	}

	public void setProfilePicUrl(String profilePicUrl) {
		this.profilePicUrl = profilePicUrl;
	}

	public String getProfilePicBlobKey() {
		return profilePicBlobKey;
	}

	public void setProfilePicBlobKey(String profilePicBlobKey) {
		this.profilePicBlobKey = profilePicBlobKey;
	}
	
	
	
	
}
