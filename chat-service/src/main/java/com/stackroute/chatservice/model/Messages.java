package com.stackroute.chatservice.model;

public class Messages {

	private String reciver;
	private String sender;
	private String content;


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReciver() {
		return reciver;
	}

	public void setReciver(String reciver) {
		this.reciver = reciver;
	}

	@Override
	public String toString() {
		return "Messages [reciver=" + reciver + ", sender=" + sender + ", content=" + content + "]";
	}
	
}
