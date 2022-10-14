package com.stackroute.userservice.service;

import org.springframework.data.domain.Pageable;

import com.stackroute.userservice.dto.SeekerDto;

public interface SeekerService {
	
	public Object addSeeker(SeekerDto seekerDto);

	public Object getAllSeeker(Pageable pageable, String searchParam);

	public Object getSeekerByEmail(String email);

	public Object updateSeeker(SeekerDto seekerDto);


}
