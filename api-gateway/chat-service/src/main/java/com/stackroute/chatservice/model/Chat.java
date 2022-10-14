package com.stackroute.chatservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import lombok.Data;

import java.time.LocalDateTime;

@Data
@Document(collection = "chat")

public class Chat {
    @Id
    private String id;
    private String message;
    private String sender;
    private String receiver;
   

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	private LocalDateTime createdAt;

	public void setCreatedAt(LocalDateTime now) {
	}
    }
	

