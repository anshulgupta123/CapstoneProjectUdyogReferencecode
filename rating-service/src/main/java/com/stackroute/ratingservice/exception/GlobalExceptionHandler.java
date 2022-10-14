package com.stackroute.ratingservice.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(NameNotFoundException.class)
	public ResponseEntity<ErrorInfo> handlerExceptionForIdNotFound(NameNotFoundException exception)
	{
		
	ErrorInfo errorInfo = new ErrorInfo();
	errorInfo.setErrorMessage(exception.getMessage());
	errorInfo.setErrorCode("404");
	errorInfo.setTime(LocalDate.now());;
	return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
}
}
