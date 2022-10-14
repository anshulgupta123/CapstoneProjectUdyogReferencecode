package com.stackroute.service;

public interface EmailSenderService {
	
    void sendEmail(String receiver, String subject, String message);
}
