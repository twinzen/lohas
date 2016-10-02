package com.lohas.data;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class SessionJdo {
	
	/*
	 * Session ID
	 * Primary key
	 */
	@Id
	@Index
	private Long sessionId;
	
	/*
	 * Token
	 * The request should contain a token which can get bank the session
	 */
	@Index
	private String token;
	
	/*
	 * This session owned by banker or customer?
	 */
	private String userType;
	
	/*
	 * Either bankerId or customerId
	 */
	@Index
	private Long userId;
	
	/*
	 * Meta data
	 * E.g. device, browser
	 */
	private String metaData;
	
	/*
	 * Session start date time
	 */
	@Index
	private Date startDateTime;

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getMetaData() {
		return metaData;
	}

	public void setMetaData(String metaData) {
		this.metaData = metaData;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

}
