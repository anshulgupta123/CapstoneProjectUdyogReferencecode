package com.stackroute.ratingservice.service;


import java.util.List;

import com.stackroute.ratingservice.model.Comment;
import com.stackroute.ratingservice.model.CompanyRating;

public interface CommentService {
	
	public Comment addComment(Comment comment);
	public List<Comment> getCommentByName(String companyName);
	
	public CompanyRating getCompanyRating(String companyName);
	
	public Comment getCompanyById(String id);

}
