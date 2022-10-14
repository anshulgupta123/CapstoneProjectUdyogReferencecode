package com.stackroute.authenticationservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.authenticationservice.domain.User;

public interface UserRepository extends JpaRepository<User,String> {
User findByEmailAndPassword(String email, String password);
}
