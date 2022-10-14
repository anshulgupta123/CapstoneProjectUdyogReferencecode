package com.stackroute.ratingservice.model;

public class CompanyRating {

private String companyName;
private Double averageRating;
private Integer noOfRating;
public String getCompanyName() {
	return companyName;
}
public void setCompanyName(String companyName) {
	this.companyName = companyName;
}
public Double getAverageRating() {
	return averageRating;
}
public void setAverageRating(Double averageRating) {
	this.averageRating = averageRating;
}
public Integer getNoOfRating() {
	return noOfRating;
}
public void setNoOfRating(Integer noOfRating) {
	this.noOfRating = noOfRating;
}
@Override
public String toString() {
	return "CompanyRating [companyName=" + companyName + ", averageRating=" + averageRating + ", noOfRating="
			+ noOfRating + "]";
}



	
}
