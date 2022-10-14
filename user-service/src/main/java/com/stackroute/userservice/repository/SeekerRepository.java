package com.stackroute.userservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.userservice.enums.SeekerStatus;
import com.stackroute.userservice.modal.Seeker;

@Repository
public interface SeekerRepository extends MongoRepository<Seeker, String>{

	Seeker findByEmail(String email);

	Seeker findByEmailAndStatus(String email, SeekerStatus active);

	Seeker findByEmailAndStatusOrderByName(String email, SeekerStatus active, String name);

	Page<Seeker> findByNameLikeOrLocationLikeOrNoticePeriodLikeOrDomainLikeOrderByName(String name, String location, String noticePeriod, String domain, Pageable pageable);

}
