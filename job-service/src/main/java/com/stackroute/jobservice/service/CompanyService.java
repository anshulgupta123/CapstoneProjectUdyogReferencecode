package com.stackroute.jobservice.service;

import org.springframework.data.domain.Pageable;

import com.stackroute.jobservice.dto.CompanyRequestDto;

public interface CompanyService {

	public Object addCompanyService(CompanyRequestDto companyRequestDto);

	public Object getCompanyByName(String companyName);
	
	public Object getAllCompanies(Pageable pageable);


}
