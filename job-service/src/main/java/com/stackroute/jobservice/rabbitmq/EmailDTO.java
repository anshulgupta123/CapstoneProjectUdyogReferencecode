package com.stackroute.jobservice.rabbitmq;

public class EmailDTO {

	public String receiver;
	public String subject;
	public String message;

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

	@Override
	public String toString() {
		return "EmailDTO [receiver=" + receiver + ", subject=" + subject + ", message=" + message + "]";
	}

}
