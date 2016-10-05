package com.lohas.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lohas.api.constant.ErrorCode;
import com.lohas.api.exception.ApplicationException;
import com.lohas.api.model.ErrorResponse;

public class CommonAPI {

	/*
	 * Handle invalid input parameter exception
	 */
	@ExceptionHandler(BindException.class)
	public @ResponseBody ErrorResponse handleInputParametersException(BindException e) {
		
		List<String> reasonDetails = new ArrayList<String>();
		
		for (FieldError error : e.getFieldErrors()) {
			String reasonDetail = error.getField()+"|"+error.getCode();
			reasonDetails.add(reasonDetail);
		}

		ErrorResponse resp = new ErrorResponse();
		resp.setReasonCode(ErrorCode.INVALID_PARAMS);
		resp.setReasonDetails(reasonDetails);
		
		return resp;
	}
	
	/*
	 * Handle application exception
	 */
	@ExceptionHandler(ApplicationException.class)
	public @ResponseBody ErrorResponse handleApplicationException(ApplicationException e) {
		
		List<String> reasonDetails = new ArrayList<String>();
		if (e.getMessage() != null) {
			reasonDetails.add(e.getMessage());
		}

		ErrorResponse resp = new ErrorResponse();
		resp.setReasonCode(e.getReasonCode());
		resp.setReasonDetails(reasonDetails);
		
		return resp;
	}
	
}
