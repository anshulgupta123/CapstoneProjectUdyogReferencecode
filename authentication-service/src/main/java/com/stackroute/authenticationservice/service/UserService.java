package com.stackroute.authenticationservice.service;


import com.stackroute.authenticationservice.domain.User;
import com.stackroute.authenticationservice.exception.InvalidCredentialsException;
import com.stackroute.authenticationservice.exception.UserAlreadyExistsException;

public interface UserService {

    User saveUser(User user) throws UserAlreadyExistsException;
    //user name and pwd is in db ot not, if not save
    User findByEmailAndPassword(String email,String password) throws InvalidCredentialsException;



}
