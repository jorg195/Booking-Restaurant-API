package com.jamc.bookingrestaurantapi.exceptions;

import java.util.ArrayList;
import java.util.List;

import com.jamc.bookingrestaurantapi.dtos.ErrorDto;

public class BookingException extends Exception{
	
	public BookingException(String code, int responseCode, String message) {
		super(message);
		this.code = code;
		this.responseCode = responseCode;
	}
	
	public BookingException(String code, int responseCode, String message, List<ErrorDto> errorList) {
		super(message);
		this.code = code;
		this.responseCode = responseCode;
		this.errorList.addAll(errorList);
	}

	private final String code;
	
	private final int responseCode;
	
	private final List<ErrorDto> errorList = new ArrayList<ErrorDto>();
	
	private static final long serialVersionUID = 1L;

	public String getCode() {
		return code;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public List<ErrorDto> getErrorList() {
		return errorList;
	}
	
}
