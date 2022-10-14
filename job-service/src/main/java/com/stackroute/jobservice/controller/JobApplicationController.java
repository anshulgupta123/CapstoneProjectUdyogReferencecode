package com.stackroute.jobservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.jobservice.dto.JobApplicationRequestDto;
import com.stackroute.jobservice.dto.JobPostingRequestDto;
import com.stackroute.jobservice.service.JobApplicationService;
import com.stackroute.jobservice.service.JobPostingService;
import com.stackroute.jobservice.utility.Constants;
import com.stackroute.jobservice.utility.UrlConstants;

@RestController
//@CrossOrigin
public class JobApplicationController {

	Logger logger = LoggerFactory.getLogger(JobApplicationController.class);

	@Autowired
	JobApplicationService jobApplicationService;

	@PostMapping(value = UrlConstants.JOB_APPLICATION_CREATION)
	public ResponseEntity<Object> postJobApplication(
			@ModelAttribute JobApplicationRequestDto jobApplicationRequestDto) {
		logger.info("Request For  postJobApplication of JobApplicationController :{}", jobApplicationRequestDto);
		Object response = jobApplicationService.createJobApplication(jobApplicationRequestDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping(value = UrlConstants.JOB_APPLICATION_UPDATION)
	public ResponseEntity<Object> updateJobApplication(
			@ModelAttribute JobApplicationRequestDto jobApplicationRequestDto) {
		logger.info("Request For  updateJobApplication of JobApplicationController :{}", jobApplicationRequestDto);
		Object response = jobApplicationService.updateJobApplication(jobApplicationRequestDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = UrlConstants.GET_JOB_APPLICATION_BY_EMAIL)
	public ResponseEntity<Object> getJobApplicationByEmail(@PathVariable String email,
			@RequestParam(defaultValue = Constants.DEFAULT_PAGE) int page,
			@RequestParam(defaultValue = Constants.DEFAULT_SIZE) int size) {
		logger.info("Request for getJobApplicationByEmail of JobApplicationController page :{}, size :{}", page, size);
		Pageable pageable = PageRequest.of(page, size);
		Object response = jobApplicationService.getJobApplicationByEmail(email, pageable);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = UrlConstants.GET_ALL_JOB_APPLICATIONS)
	public ResponseEntity<Object> getAllJobApplication(@RequestParam(defaultValue = Constants.DEFAULT_PAGE) int page,
			@RequestParam(defaultValue = Constants.DEFAULT_SIZE) int size,
			@RequestParam(defaultValue = "") String searchParam) {
		logger.info("Request for getAllJobApplication of JobApplicationController page :{}, size :{}, searchParam",
				page, size, searchParam);
		Pageable pageable = PageRequest.of(page, size);
		Object response = jobApplicationService.getAllJobApplications(searchParam, pageable);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}