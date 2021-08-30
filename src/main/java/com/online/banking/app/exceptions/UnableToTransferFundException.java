package com.online.banking.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UnableToTransferFundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnableToTransferFundException(String string) {
		super(string);
		// TODO Auto-generated constructor stub
	}

	
}
