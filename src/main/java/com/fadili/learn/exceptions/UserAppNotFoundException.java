package com.fadili.learn.exceptions;

public class UserAppNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 866162884579516607L;

	public UserAppNotFoundException() {
		super();
	}

	public UserAppNotFoundException(String UserID) {
		super("the user with id: " + UserID + " doesn't exist");
	}

}
