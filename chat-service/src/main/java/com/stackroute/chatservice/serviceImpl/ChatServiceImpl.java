package com.stackroute.chatservice.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.stackroute.chatservice.controller.ChatServiceController;
import com.stackroute.chatservice.exception.ChatException;

import com.stackroute.chatservice.model.Chat;
import com.stackroute.chatservice.model.Messages;
import com.stackroute.chatservice.repo.ChatRepository;
import com.stackroute.chatservice.service.ChatService;

@Service
public class ChatServiceImpl implements ChatService {
	Logger logger = LoggerFactory.getLogger(ChatServiceController.class);
	@Autowired
	private final ChatRepository chatRepository = null;

	@Override
	public Chat getChatMessages(String chatId) {
		try {
			Optional<Chat> chatFromRepo = chatRepository.findById(chatId);
			if (!chatFromRepo.isPresent()) {
				throw new ChatException("Id Not Found");
			}
			return chatFromRepo.get();
		} catch (Exception e) {

			throw new ChatException(e.getMessage());
		}
	}

	@Override
	public Chat newChatMessage(Chat chat) {
		Chat newChat = null;
		try {
			Chat chatFromrepo = chatRepository.findBySeekerEmailAndEmployerEmail(chat.getSeekerEmail(),
					chat.getEmployerEmail());
			logger.info("chat is :{}", chatFromrepo);
		if (chatFromrepo == null) {
			List<Messages> messages = new ArrayList<>();
			chat.setMessage(messages);
			newChat = chatRepository.save(chat);
			logger.info("Saved a chat");
		}
		}
		catch(Exception e) {
			logger.error("Exception in creating chat :{}", e);
		}
		return newChat;
	}

	@Override
	public Chat updateChatMessage(String chatId, Messages message) {
		try {

			Optional<Chat> chatFromRepo = chatRepository.findById(chatId);
			Chat chat = chatFromRepo.get();
			if (!chatFromRepo.isPresent()) {
				throw new ChatException("Chat Not Found");
			}

			List<Messages> messages = chat.getMessage();
			messages.add(message);
			chat.setMessage(messages);
			return chatRepository.save(chat);
		} catch (Exception e) {

			throw new ChatException(e.getMessage());
		}
	}

	@Override
	public List<Chat> getChatSeekerEmail(String seekerEmail) {
		try {
			return chatRepository.findBySeekerEmail(seekerEmail);
		} catch (Exception e) {

			throw new ChatException(e.getMessage());
		}
	}

	@Override
	public List<Chat> getChatEmployerEmail(String employerEmail) {
		try {
			return chatRepository.findByEmployerEmail(employerEmail);
		} catch (Exception e) {

			throw new ChatException(e.getMessage());
		}
	}

}
