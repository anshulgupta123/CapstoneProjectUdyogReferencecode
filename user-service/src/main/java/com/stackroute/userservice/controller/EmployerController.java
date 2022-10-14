package com.stackroute.userservice.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.stackroute.userservice.dto.EmployerRequestDto;
import com.stackroute.userservice.service.EmployerService;
import com.stackroute.userservice.utility.Constants;
import com.stackroute.userservice.utility.UrlConstants;

@RestController
//@CrossOrigin
public class EmployerController {

Logger logger = LoggerFactory.getLogger(EmployerController.class);
	
	@Autowired
	private EmployerService employerService;
	
	@PostMapping(value = UrlConstants.ADD_EMPLOYER)
	public ResponseEntity<Object> addEmployer(@ModelAttribute EmployerRequestDto employerRequestDto){
		logger.info("Request for addEmployer of EmployerController :{}", employerRequestDto);
		Object response = employerService.addEmployer(employerRequestDto);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@GetMapping(value = UrlConstants.GET_EMPLOYER_BY_EMAIL)
	public ResponseEntity<Object> getEmployerByEmail(@PathVariable String email){
		logger.info("Request for getEmployerByEmail of EmployerController");
		Object response = employerService.getEmployerByEmail(email);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@PutMapping(value = UrlConstants.UPDATE_EMPLOYER)
	public ResponseEntity<Object> updateEmployer(@ModelAttribute EmployerRequestDto employerRequestDto){
		logger.info("Request for updateEmployer of EmployerController :{}", employerRequestDto);
		Object response = employerService.updateEmployer(employerRequestDto);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@GetMapping(value = UrlConstants.GET_ALL_EMPLOYERS)
	public ResponseEntity<Object> getAllEmployers(@RequestParam(defaultValue = Constants.DEFAULT_PAGE) int page, 
			@RequestParam(defaultValue = Constants.DEFAULT_SIZE) int size,
			@RequestParam(defaultValue = "") String searchParam){
		logger.info("Request for getAllEmployers of EmployerController with page :{}, size :{}, searchParam :{}", page, size,
				searchParam);
		Pageable pageable = PageRequest.of(page, size);
		Object response = employerService.getAllEmployers(pageable, searchParam);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
}
