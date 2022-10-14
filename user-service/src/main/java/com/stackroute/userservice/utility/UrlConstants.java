package com.stackroute.userservice.utility;

public interface UrlConstants {

	String ADD_SEEKER = "/v1/seekerRegistration";
	String GET_ALL_SEEKERS = "/v1/getAllSeekers";
	String GET_SEEKER_BY_EMAIL = "/v1/getSeeker/{email}";
	String UPDATE_SEEKER = "/v1/profieUpdationSeeker";
	String ADD_EMPLOYER = "/v1/employerRegistration";
	String GET_ALL_EMPLOYERS = "/v1/getAllEmployers";
	String GET_EMPLOYER_BY_EMAIL = "/v1/getEmployer/{email}";
	String UPDATE_EMPLOYER = "/v1/profieUpdationEmployer";

}
