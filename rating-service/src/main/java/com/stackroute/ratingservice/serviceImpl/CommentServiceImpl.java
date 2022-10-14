package com.stackroute.ratingservice.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.ratingservice.exception.NameNotFoundException;
import com.stackroute.ratingservice.model.Comment;
import com.stackroute.ratingservice.model.CompanyRating;
import com.stackroute.ratingservice.repository.CommentRepository;
import com.stackroute.ratingservice.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepository commentRepository;
	
	@Override
	public Comment addComment(Comment comment) {
		
		comment.setCreatedOn(LocalDate.now());
		return commentRepository.save(comment);
	}

	@Override
	public List<Comment> getCommentByName(String companyName) {

		List<Comment> comment = commentRepository.findByCompanyName(companyName);
		if(comment.isEmpty())
		{
			throw new NameNotFoundException("company name not found for given name");
		}
		else
		{
		return comment;
		}
	}

	@Override
	public CompanyRating getCompanyRating(String companyName) {
		
		List<Comment> dataFromRepo = commentRepository.findByCompanyName(companyName);
		if(dataFromRepo.isEmpty())
		{
		throw new NameNotFoundException("company name not found for given name");
		}
		else
		{
			int count=0;
			double totalRating=0.0;
			for(Comment company:dataFromRepo){
			count++;
			totalRating = totalRating+company.getRating();
			}
			Double averageRating = totalRating/count;
			CompanyRating companyRating = new CompanyRating();
			
			if(dataFromRepo.size()==0)
			{
				companyRating.setAverageRating(0.0);
				companyRating.setNoOfRating(0);
			}
			else
			{
				companyRating.setAverageRating(averageRating);
				companyRating.setNoOfRating(count);
				companyRating.setCompanyName(companyName);
			}
			return companyRating;
		}
	
		
		
	}

	@Override
	public Comment getCompanyById(String id) {
	
		return commentRepository.findById(id).get();
	}
		

}
