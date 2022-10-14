package com.stackroute.Rabbitmq;

public class EmailDTO {

	private String receiver;
	private String subject;
	private String message;

	public EmailDTO() {
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPassword() {
		return null;
	}

	@Override
	public String toString() {
		return "EmailDTO [receiver=" + receiver + ", subject=" + subject + ", message=" + message + "]";
	}

}
