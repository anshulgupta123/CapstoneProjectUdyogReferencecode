package com.stackroute.chatservice.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> handlerExceptionForIdNotFound(Exception exception)
	{
		
	ErrorInfo errorInfo = new ErrorInfo();
	errorInfo.setErrorMessage(exception.getMessage());
	errorInfo.setErrorCode("404");
	errorInfo.setTime(LocalDate.now());;
	return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
}
}
