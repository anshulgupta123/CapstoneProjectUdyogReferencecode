package com.stackroute.authenticationservice.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.stackroute.authenticationservice.domain.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTSecurityTokenGeneratorImpl implements SecurityTokenGenerator {
	
	Logger logger = LoggerFactory.getLogger(JWTSecurityTokenGeneratorImpl.class);

    public Map<String, String> generateToken(User user) {
    	logger.info("User is :{}", user);
    	Map<String ,Object> claims=new HashMap<>();
    	claims.put("userType", user.getUserType());
    	//claims.put("password", user.getPassword());
    	logger.info("Cliams are :{}", claims);
        String jwtToken = Jwts.builder().setIssuer("UDYOG")
                .setSubject(user.getEmail())
                .setIssuedAt(new Date()).addClaims(claims)
                .signWith(SignatureAlgorithm.HS256,"mysecret")
                .compact();
        Map<String,String> map = new HashMap<>();
        map.put("token",jwtToken);
        map.put("email", user.getEmail());
        map.put("userType", user.getUserType());
        map.put("message","Authentication Successful");
        return map;
    }
}
