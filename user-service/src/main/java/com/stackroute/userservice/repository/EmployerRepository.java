package com.stackroute.userservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.userservice.enums.EmployerStatus;
import com.stackroute.userservice.modal.Employer;

@Repository
public interface EmployerRepository extends MongoRepository<Employer, String> {

	Employer findByEmailAndStatus(String email, EmployerStatus active);

	Page<Employer> findByNameLikeOrCompanyLikeOrderByName(String searchParam, String searchParam2, Pageable pageable);

}
