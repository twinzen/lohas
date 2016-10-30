package com.lohas.api.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.lohas.data.BlobImageJdo;

public class RegisterCustomerRequest extends CommonRequest {

	/***********************************************************************/
	/** Customer general information ***************************************/
	/***********************************************************************/
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
	
	/***********************************************************************/
	/** Customer login information *****************************************/
	/***********************************************************************/
	/*
	 * Login Username
	 */
	@NotEmpty
	private String username;
	
	/*
	 * Login Password
	 */
	@NotEmpty
	private String password;
	
	/***********************************************************************/
	/** First account setting information **********************************/
	/***********************************************************************/
	/*
	 * Account Initial Amount
	 */
	@NotNull
	private BigDecimal initialAmount;
	
	/*
	 * Interest Rate
	 */
	@NotNull
	private BigDecimal interestRate;
	
	/*
	 * Auto Instruction Frequency
	 */
	@NotNull
	private String autoInstructionFrequency;
	
	/*
	 * Auto Instruction Amount
	 */
	@NotNull
	private BigDecimal autoInstructionAmount;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigDecimal getInitialAmount() {
		return initialAmount;
	}

	public void setInitialAmount(BigDecimal initialAmount) {
		this.initialAmount = initialAmount;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public String getAutoInstructionFrequency() {
		return autoInstructionFrequency;
	}

	public void setAutoInstructionFrequency(String autoInstructionFrequency) {
		this.autoInstructionFrequency = autoInstructionFrequency;
	}

	public BigDecimal getAutoInstructionAmount() {
		return autoInstructionAmount;
	}

	public void setAutoInstructionAmount(BigDecimal autoInstructionAmount) {
		this.autoInstructionAmount = autoInstructionAmount;
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
