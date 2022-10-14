package com.stackroute.jobservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.stackroute.jobservice.enums.JobApplicationStatus;
import com.stackroute.jobservice.modal.JobApplication;

public interface JobApplicationRepository extends MongoRepository<JobApplication, String>{

	Object findByJobIdAndAppliedByAndStatus(String jobId, String appliedBy, JobApplicationStatus applied);

	JobApplication findByIdAndStatus(String jobApplicationId, JobApplicationStatus applied);

	Page<JobApplication> findByAppliedBy(String email, Pageable pageable);

	Page<JobApplication> findByAppliedByLikeOrStatusLikeOrderByCreatedOnDesc(String searchParam, String searchParam2,
			Pageable pageable);

}
