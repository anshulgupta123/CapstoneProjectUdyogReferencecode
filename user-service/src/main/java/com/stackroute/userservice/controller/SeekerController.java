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

import com.stackroute.userservice.dto.SeekerDto;
import com.stackroute.userservice.service.SeekerService;
import com.stackroute.userservice.utility.Constants;
import com.stackroute.userservice.utility.UrlConstants;

@RestController
//@CrossOrigin
public class SeekerController {
	
	Logger logger = LoggerFactory.getLogger(SeekerController.class);
	
	@Autowired
	private SeekerService seekerService;
	
	@PostMapping(value = UrlConstants.ADD_SEEKER)
	public ResponseEntity<Object> addSeeker(@ModelAttribute SeekerDto seekerDto){
		logger.info("Request for addSeeker of SeekerController :{}", seekerDto);
		Object response = seekerService.addSeeker(seekerDto);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@GetMapping(value = UrlConstants.GET_SEEKER_BY_EMAIL)
	public ResponseEntity<Object> getSeekerByEmail(@PathVariable String email){
		logger.info("Request for getSeekerByEmail of SeekerController");
		Object response = seekerService.getSeekerByEmail(email);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@GetMapping(value = UrlConstants.GET_ALL_SEEKERS)
	public ResponseEntity<Object> getAllSeeker(@RequestParam(defaultValue = Constants.DEFAULT_PAGE) int page, 
			@RequestParam(defaultValue = Constants.DEFAULT_SIZE) int size,
			@RequestParam(defaultValue = "") String searchParam){
		logger.info("Request for getAllSeeker of SeekerController with page :{}, size :{}, searchParam :{}", page, size,
				searchParam);
		Pageable pageable = PageRequest.of(page, size);
		Object response = seekerService.getAllSeeker(pageable, searchParam);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@PutMapping(value = UrlConstants.UPDATE_SEEKER)
	public ResponseEntity<Object> updateSeeker(@ModelAttribute SeekerDto seekerDto){
		logger.info("Request for updateSeeker of SeekerController :{}", seekerDto);
		Object response = seekerService.updateSeeker(seekerDto);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	

}
