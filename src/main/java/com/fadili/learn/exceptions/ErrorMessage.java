package com.fadili.learn.exceptions;

public enum ErrorMessage {

	MISSING_REQUIRED_FIELD("Missing a required field, Please check documentation for required fields"),
	RECORD_ALREADY_EXISTS("Record already exists"),
	INTERNAL_SERVER_ERROR("Internal server error"),
	NO_RECORD_FOUND("Record with the provided id is not found"),
	AUTHENTICATION_FAILED("Authentication failed"),
	COULD_NOT_UPDATE_RECORD("Could not update a record"),
	COULD_NOT_DELETE_RECORD("Could not delete a record"),
	EMAIL_ADDRESS_NOT_VERIFIED("Email address could not be verified");
	
	String message;

	ErrorMessage(String message) {
		
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
