package com.stackroute.chatservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.stackroute.chatservice.model.Chat;
import com.stackroute.chatservice.model.Messages;
import com.stackroute.chatservice.repo.ChatRepository;
import com.stackroute.chatservice.service.ChatService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin
@RequestMapping("/api/v1")
@RestController
public class ChatServiceController {
	
	@Autowired
	ChatService chatService;
	
	private final ChatRepository chatRepository;

	public ChatServiceController(ChatRepository chatRepository) {
		this.chatRepository = chatRepository;
	}
	
	@GetMapping(value = ("/chat/id/{chatId}"))
	public ResponseEntity<Object> getMessages(@PathVariable String chatId) {
		
		Object response =chatService.getChatMessages(chatId);
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}


	@PostMapping("/chatservicemodel")
	public ResponseEntity<Chat> newMessage(@RequestBody Chat chat) {
		return new ResponseEntity<Chat>(chatService.newChatMessage(chat), HttpStatus.OK);
		
	}

	@PutMapping("/chat/{chatId}")
	@ResponseBody
	public ResponseEntity<Object> updateMessage(@PathVariable String chatId, @RequestBody Messages message) {	
		Object response = chatService.updateChatMessage(chatId,message);
		return new ResponseEntity<Object>(response, HttpStatus.OK);

	}
	
	@GetMapping(value = ("/getChatOfSeeker/{seekerEmail}"))
	public ResponseEntity<Object> getSeekerEmail(@PathVariable String seekerEmail) {
		Object response = chatService.getChatSeekerEmail(seekerEmail);
	
		return new ResponseEntity<Object>(response, HttpStatus.OK);

	}

	@GetMapping(value = ("/getChatOfEmployer/{employerEmail}"))
	public ResponseEntity<Object> getEmployerEmail(@PathVariable String employerEmail) {	
		Object response = chatService.getChatEmployerEmail(employerEmail);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
}
