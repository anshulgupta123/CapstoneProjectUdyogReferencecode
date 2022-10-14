package com.stackroute.authenticationservice.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stackroute.authenticationservice.config.Consumer;
import com.stackroute.authenticationservice.domain.User;
import com.stackroute.authenticationservice.exception.InvalidCredentialsException;
import com.stackroute.authenticationservice.exception.UserAlreadyExistsException;
import com.stackroute.authenticationservice.security.SecurityTokenGenerator;
import com.stackroute.authenticationservice.service.UserService;

import java.util.Map;

//@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class UserController {
	Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;
    private SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    private Consumer consumer;

    @Autowired
    public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }
    @PostMapping("/user")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlreadyExistsException {
        return new ResponseEntity<>(userService.saveUser(user),HttpStatus.CREATED);
    }
//    @PostMapping("/user")
//    public ResponseEntity<?> saveUser() throws UserAlreadyExistsException {
//        User user = consumer.
//         return new ResponseEntity<>(userService.saveUser(user),HttpStatus.CREATED);
//    }

//    @PostMapping("/consume")
//    public ResponseEntity<?> viewUserFromRabbitMq(){
//        return new ResponseEntity<>(consumer.getUserDtoFromRabbitMq());
//    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws InvalidCredentialsException
    {
        User retrievedUser = userService.findByEmailAndPassword(user.getEmail(),user.getPassword());
        logger.info("Resterived user is :{}",retrievedUser);
        if(retrievedUser==null)
        {
            throw new InvalidCredentialsException();
        }
        Map<String,String> map = securityTokenGenerator.generateToken(retrievedUser);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }


}
