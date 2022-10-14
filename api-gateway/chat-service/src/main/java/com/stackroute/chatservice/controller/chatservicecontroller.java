package com.stackroute.chatservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.stackroute.chatservice.model.Chat;
import com.stackroute.chatservice.repo.ChatRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RequestMapping("/api/v1")
@RestController
public class chatservicecontroller {
	
@Autowired
    private final ChatRepository chatRepository;

    public chatservicecontroller(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @GetMapping(value = "/chat/id/{chatId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Chat getMessages(@PathVariable String chatId) {
        return chatRepository.findById(chatId).get();
              
    }

    @PostMapping("/chatservicemodel")
    public Chat newMessage(@RequestBody Chat chat) {
    	chat.setCreatedAt(LocalDateTime.now());
    	Chat newChat = chatRepository.save(chat);
    	return newChat;
    	
    }
}



