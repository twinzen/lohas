package com.lohas.api.model.common;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.lohas.data.BlobImageJdo;

public class Customer {

	/*
	 * Customer ID
	 * Primary key
	 */
	private Long customerId;
	
	/*
	 * Customer Name
	 * What should I call you?
	 */
	private String customerName;
	
	/*
	 * Gender
	 * Boy or Girl, or others?
	 */
	private String gender;
	
	
	/*
	 * Date Of Birth
	 * I just want to know your age
	 */
	private Date dateOfBirth;
	
	
	/*
	 * Profile Picture
	 * Your face your pic - URL
	 */
	private String profilePicUrl;
	
	
	/*
	 * User Name
	 * Login name
	 */
	private String username;
	
	
	/*
	 * Email
	 * Email for communication only
	 */
	private String email;
	
	/*
	 * Total asset value in base currency
	 */
	private BigDecimal totalAssetValueAmount;
	
	/*
	 * Total gain & loss in base currency
	 */
	private BigDecimal totalGainLossAmount;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getTotalAssetValueAmount() {
		return totalAssetValueAmount;
	}

	public void setTotalAssetValueAmount(BigDecimal totalAssetValueAmount) {
		this.totalAssetValueAmount = totalAssetValueAmount;
	}

	public BigDecimal getTotalGainLossAmount() {
		return totalGainLossAmount;
	}

	public void setTotalGainLossAmount(BigDecimal totalGainLossAmount) {
		this.totalGainLossAmount = totalGainLossAmount;
	}

}
