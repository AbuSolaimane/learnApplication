package com.fadili.learn.exceptions;

public class RequiredFieldException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2604656404787670641L;
	
	public RequiredFieldException(String fieldName) {
		super("the " + fieldName + " is a required field !!");
	}
	
}
