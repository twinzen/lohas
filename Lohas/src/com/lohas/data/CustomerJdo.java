package com.lohas.data;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class CustomerJdo {
	
	/*
	 * Customer ID
	 * Primary key
	 */
	@Id
	@Index
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
	@Index
	private Date dateOfBirth;
	
	
	/*
	 * Icon Picture
	 * Your face your pic 
	 */
	private BlobImageJdo profilePic;
	
	
	/*
	 * User Name
	 * Login name
	 */
	@Index
	private String username;
	
	
	/*
	 * Email
	 * Email for communication only
	 */
	@Index
	private String email;
	
	/*
	 * Password
	 * You need it for login
	 */
	private String password;
	
	/*
	 * Bank ID
	 * This customer is belong to which bank?
	 */
	@Index
	private Long bankId;

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

	public BlobImageJdo getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(BlobImageJdo profilePic) {
		this.profilePic = profilePic;
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
