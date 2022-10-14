package com.stackroute.ratingservice.model;



import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Comment")
public class Comment {

	@Id
	private String id;
	private double rating;
	private String companyName;
	private String description;
	private String givenBy;
	private LocalDate createdOn;
	public Comment() {
		super();
	}
	public Comment(String id, double rating, String companyName, String description, String givenBy, LocalDate createdOn) {
		super();
		this.id = id;
		this.rating = rating;
		this.companyName = companyName;
		this.description = description;
		this.givenBy = givenBy;
		this.createdOn = createdOn;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGivenBy() {
		return givenBy;
	}
	public void setGivenBy(String givenBy) {
		this.givenBy = givenBy;
	}
	public LocalDate getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}
	
	
	
	
	
}