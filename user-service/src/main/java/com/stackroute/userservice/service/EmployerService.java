package com.stackroute.userservice.service;

import org.springframework.data.domain.Pageable;

import com.stackroute.userservice.dto.EmployerRequestDto;

public interface EmployerService {

	Object addEmployer(EmployerRequestDto employerRequestDto);

	Object getEmployerByEmail(String email);

	Object updateEmployer(EmployerRequestDto employerRequestDto);

	Object getAllEmployers(Pageable pageable, String searchParam);

}
