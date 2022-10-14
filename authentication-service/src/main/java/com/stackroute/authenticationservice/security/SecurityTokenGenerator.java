package com.stackroute.authenticationservice.security;



import java.util.Map;

import com.stackroute.authenticationservice.domain.User;

public interface SecurityTokenGenerator {
    Map<String,String> generateToken(User user);//token and message -> the return type can be String also
}
