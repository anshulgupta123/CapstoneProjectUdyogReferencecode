package com.stackroute.userservice.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class EmployerRequestDto extends EmployerFields implements Serializable {

	private static final long serialVersionUID = 1L;
	

	private MultipartFile profilePhoto;
	
	

	public EmployerRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MultipartFile getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(MultipartFile profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	@Override
	public String toString() {
		return "EmployerRequestDto [profilePhoto=" + profilePhoto + "]";
	}

}
