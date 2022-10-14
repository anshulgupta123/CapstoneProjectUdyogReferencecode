package com.stackroute.ratingservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.ratingservice.model.Comment;
import com.stackroute.ratingservice.model.CompanyRating;
import com.stackroute.ratingservice.service.CommentService;


//@CrossOrigin
@RestController
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@PostMapping(value = "comment")
	public ResponseEntity<Comment> addComment(@RequestBody Comment comment)
	{
		return new ResponseEntity<Comment>(commentService.addComment(comment), HttpStatus.OK);
	}
	
	@GetMapping(value = "comment/{companyName}")
	public List<Comment> getCommentByName(@PathVariable String companyName)
	{
		return commentService.getCommentByName(companyName);
	}
	
	@GetMapping(value = "companyRating/{companyName}")
	public CompanyRating getCompanyRating(@PathVariable String companyName)
	{
		return commentService.getCompanyRating(companyName);
	}
	
	@GetMapping(value = "getComment/{id}")
	public Comment getCompanyById(@PathVariable String id)
	{
		return commentService.getCompanyById(id);
	}

}
