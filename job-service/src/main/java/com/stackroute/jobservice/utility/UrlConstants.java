package com.stackroute.jobservice.utility;

public interface UrlConstants {

	String JOB_POSTING = "/v1/jobPosting";
	String JOB_UPDATION = "/v1/jobUpdation";
	String JOB_APPLICATION_CREATION = "/v1/jobApplicationCreation";
	String JOB_APPLICATION_UPDATION = "/v1/jobApplicationUpdation";
	String GET_JOB_POSTING_BY_EMAIL = "/v1/getJobPostingByEmployerEmail/{employerEmail}";
	String GET_ALL_JOB_POSTINGS = "/v1/getAllJobPostings";
	String GET_JOB_APPLICATION_BY_EMAIL = "/v1/getJobApplicationByEmail/{email}";
	String GET_ALL_JOB_APPLICATIONS = "/v1/getAllJobApplications";
	String GET_COMPANY_BY_NAME = "/v1/getCompanyByName/{companyName}";
	String GET_ALL_COMPANY = "/v1/getAllCompany";

}
