package com.online.banking.app.exceptions;

public class ExistingBeneficiaryException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ExistingBeneficiaryException() {
    }

    public ExistingBeneficiaryException(String msg) {
        super(msg);
    }
}