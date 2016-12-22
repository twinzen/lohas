package com.lohas.api.constant;

public class ErrorCode {

	public static final String ERROR = "E00000"; // don't know what error...
	public static final String INVALID_PARAMS = "E00001"; // invalid parameters
	public static final String EMAIL_ALREADY_USED = "E00002"; // someone registered this email already
	public static final String NOT_LOGGED_IN = "E00003"; // require logged in, but not logged in
	public static final String LOGIN_FAIL = "E00004"; // login fail, either email not found or password incorrect
	public static final String ACCOUNT_NOT_FOUND = "E00005"; // account cannot be found by given account code and bank id
	
}
