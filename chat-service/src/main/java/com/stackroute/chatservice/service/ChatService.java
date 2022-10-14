package com.stackroute.chatservice.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import com.stackroute.chatservice.model.Chat;
import com.stackroute.chatservice.model.Messages;

public interface ChatService {

	public Chat getChatMessages(String chatId);
	public Chat newChatMessage(Chat chat);
	public Chat updateChatMessage(String chatId, Messages message);
	public List<Chat> getChatSeekerEmail(String seekerEmail) ;
	public List<Chat> getChatEmployerEmail(String employerEmail);
}
