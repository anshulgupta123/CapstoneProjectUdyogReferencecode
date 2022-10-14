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
import com.stackroute.jobservice.dto.JobPostingRequestDto;
import com.stackroute.jobservice.service.JobPostingService;
import com.stackroute.jobservice.utility.Constants;
import com.stackroute.jobservice.utility.UrlConstants;

@RestController
//@CrossOrigin
public class JobPostingController {

	Logger logger = LoggerFactory.getLogger(JobPostingController.class);

	@Autowired
	JobPostingService jobPostingService;

	@PostMapping(value = UrlConstants.JOB_POSTING)
	public ResponseEntity<Object> postJob(@ModelAttribute JobPostingRequestDto jobPostingRequestDto) {
		logger.info("Request For  postJob of JobPostingController :{}", jobPostingRequestDto);
		Object response = jobPostingService.addJob(jobPostingRequestDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping(value = UrlConstants.JOB_UPDATION)
	public ResponseEntity<Object> updateJob(@ModelAttribute JobPostingRequestDto jobPostingRequestDto) {
		logger.info("Request For  updateJob of JobPostingController :{}", jobPostingRequestDto);
		Object response = jobPostingService.updateJob(jobPostingRequestDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = UrlConstants.GET_JOB_POSTING_BY_EMAIL)
	public ResponseEntity<Object> getJobPostingByEmail(@PathVariable String employerEmail,
			@RequestParam(defaultValue = Constants.DEFAULT_PAGE) int page,
			@RequestParam(defaultValue = Constants.DEFAULT_SIZE) int size) {
		logger.info("Request for getJobPostingByEmail of JobPostingController page :{}, size :{}", page, size);
		Pageable pageable = PageRequest.of(page, size);
		Object response = jobPostingService.getJobPostingByEmployerEmail(employerEmail, pageable);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = UrlConstants.GET_ALL_JOB_POSTINGS)
	public ResponseEntity<Object> getAllJobPosting(@RequestParam(defaultValue = Constants.DEFAULT_PAGE) int page,
			@RequestParam(defaultValue = Constants.DEFAULT_SIZE) int size,
			@RequestParam(defaultValue = "") String searchParam) {
		logger.info("Request for getAllJobPosting of JobPostingController page :{}, size :{}, searchParam", page, size,
				searchParam);
		Pageable pageable = PageRequest.of(page, size);
		Object response = jobPostingService.getAllJobPostings(searchParam, pageable);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
