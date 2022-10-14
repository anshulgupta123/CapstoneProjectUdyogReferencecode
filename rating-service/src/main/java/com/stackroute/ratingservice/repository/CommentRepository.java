package com.stackroute.ratingservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import com.stackroute.ratingservice.model.Comment;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String>{

	List<Comment> findByCompanyName(String companyName);
	
	

}
