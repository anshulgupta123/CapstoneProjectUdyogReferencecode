package com.stackroute.userservice.rabbitmq;

public class UserDTO {

	private String email;
	private String password;
	private String userType;

	public UserDTO() {
		super();
	}

	public String getEmail() {
		return email;
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

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "UserDTO [email=" + email + ", password=" + password + ", userType=" + userType + "]";
	}

}
