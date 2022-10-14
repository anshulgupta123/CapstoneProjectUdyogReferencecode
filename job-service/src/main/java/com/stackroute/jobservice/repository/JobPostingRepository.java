package com.stackroute.jobservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.jobservice.enums.JobStatus;
import com.stackroute.jobservice.modal.JobPosting;

@Repository
public interface JobPostingRepository extends MongoRepository<JobPosting, String> {

	JobPosting findByIdAndStatus(String id, JobStatus active);

	Page<JobPosting> findByEmployerEmail(String employerEmail, Pageable pageable);

	Page<JobPosting> findByLocationLikeOrStatusLikeOrCompanyLikeOrderByCompany(String searchParam, String searchParam2,
			String searchParam3, Pageable pageable);

}
