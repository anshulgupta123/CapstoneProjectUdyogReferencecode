package com.stackroute.jobservice.service;

import org.springframework.data.domain.Pageable;

import com.stackroute.jobservice.dto.JobApplicationRequestDto;

public interface JobApplicationService {

	Object createJobApplication(JobApplicationRequestDto jobApplicationRequestDto);

	Object updateJobApplication(JobApplicationRequestDto jobApplicationRequestDto);

	Object getJobApplicationByEmail(String email, Pageable pageable);

	Object getAllJobApplications(String searchParam, Pageable pageable);

}
