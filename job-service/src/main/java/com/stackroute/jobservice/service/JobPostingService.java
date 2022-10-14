package com.stackroute.jobservice.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.stackroute.jobservice.dto.JobApplicationRequestDto;
import com.stackroute.jobservice.dto.JobPostingRequestDto;

@Service
public interface JobPostingService {

	Object addJob(JobPostingRequestDto jobPostingRequestDto);

	Object updateJob(JobPostingRequestDto jobPostingRequestDto);

	Object getJobPostingByEmployerEmail(String employerEmail, Pageable pageable);

	Object getAllJobPostings(String searchParam, Pageable pageable);

}
