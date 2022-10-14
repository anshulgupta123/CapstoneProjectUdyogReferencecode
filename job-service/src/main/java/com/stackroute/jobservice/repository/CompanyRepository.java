package com.stackroute.jobservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.jobservice.modal.Company;

@Repository
public interface CompanyRepository extends MongoRepository<Company, String> {

	Company findByCompanyName(String companyName);

}
