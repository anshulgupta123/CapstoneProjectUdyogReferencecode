package com.stackroute.ratingservice.exception;

import java.io.Serializable;
import java.time.LocalDate;

public class ErrorInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	private String errorCode;
	private String errorMessage;
	private LocalDate time;
	public ErrorInfo(String errorCode, String errorMessage, LocalDate time) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.time = time;
	}
	public ErrorInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public LocalDate getTime() {
		return time;
	}
	public void setTime(LocalDate time) {
		this.time = time;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "ErrorInfo [errorCode=" + errorCode + ", errorMessage=" + errorMessage + ", time=" + time + "]";
	}
	
	
	
}
