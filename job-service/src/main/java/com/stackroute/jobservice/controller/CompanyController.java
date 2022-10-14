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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.jobservice.service.CompanyService;
import com.stackroute.jobservice.utility.Constants;
import com.stackroute.jobservice.utility.UrlConstants;

@RestController
//@CrossOrigin
public class CompanyController {

	Logger logger = LoggerFactory.getLogger(CompanyController.class);

	@Autowired
	CompanyService companyService;

	@GetMapping(value = UrlConstants.GET_COMPANY_BY_NAME)
	public ResponseEntity<Object> getCompanyByName(@PathVariable String companyName) {
		logger.info("Request for getCompanyByName of CompanyController companyName :{}", companyName);
		Object response = companyService.getCompanyByName(companyName);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = UrlConstants.GET_ALL_COMPANY)
	public ResponseEntity<Object> getAllComapnies(@RequestParam(defaultValue = Constants.DEFAULT_PAGE) int page,
			@RequestParam(defaultValue = Constants.DEFAULT_SIZE) int size,
			@RequestParam(defaultValue = "") String searchParam) {
		logger.info("Request for getAllJobApplication of JobApplicationController page :{}, size :{}, searchParam:{}",
				page, size, searchParam);
		Pageable pageable = PageRequest.of(page, size);
		Object response = companyService.getAllCompanies(pageable);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
