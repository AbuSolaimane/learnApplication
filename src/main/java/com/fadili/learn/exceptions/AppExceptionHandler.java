package com.fadili.learn.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ExceptionMessage> handleRequiredFieldException(RequiredFieldException requiredFieldException) {
		
		ExceptionMessage exceptionMessage = new ExceptionMessage();
		exceptionMessage.setTimeStamp(new Date());
		exceptionMessage.setMessage(requiredFieldException.getMessage());
		
		return new ResponseEntity<ExceptionMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<ExceptionMessage> handleUserAlreadyExistException(UserAlreadyExistException alreadyExistException) {
		
		ExceptionMessage exceptionMessage = new ExceptionMessage();
		exceptionMessage.setTimeStamp(new Date());
		exceptionMessage.setMessage(alreadyExistException.getMessage());
		
		return new ResponseEntity<ExceptionMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<ExceptionMessage> handleAllExceptions(Exception exception) {
		
		ExceptionMessage exceptionMessage = new ExceptionMessage();
		exceptionMessage.setTimeStamp(new Date());
		exceptionMessage.setMessage(exception.getMessage());
		
		return new ResponseEntity<ExceptionMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

}
