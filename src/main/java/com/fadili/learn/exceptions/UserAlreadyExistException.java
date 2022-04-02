package com.fadili.learn.exceptions;

public class UserAlreadyExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7675484728822013290L;

	public UserAlreadyExistException() {
		super();
	}

	public UserAlreadyExistException(String message) {
		super(message);
	}
	
	
	

}
