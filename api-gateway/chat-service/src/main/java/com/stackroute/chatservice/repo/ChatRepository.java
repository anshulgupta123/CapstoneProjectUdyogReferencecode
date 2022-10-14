package com.stackroute.chatservice.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Repository;

import com.stackroute.chatservice.model.Chat;

import reactor.core.publisher.Flux;
@Repository
public interface ChatRepository extends MongoRepository<Chat, String> {
}

