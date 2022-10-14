package com.stackroute.authenticationservice.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
public class User {
	@Id
	@Email(message = "Invalid email id")
	private String email;
	private String password;
	@NotEmpty(message = "role should not be empty")
	private String userType;

	public User() {
	}

	public User(String email, String password, String userType) {
		super();
		this.email = email;
		this.password = password;
		this.userType = userType;
	}

	public String getEmail() {
		return email;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", userType=" + userType + "]";
	}

}
