package com.stackroute.userservice.dto;

import java.io.Serializable;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.stackroute.userservice.enums.SeekerDomain;

public class SeekerDto extends SeekerFields implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long dob;
	private MultipartFile resume;
	private MultipartFile profilePhoto;

	public SeekerDto() {
		super();
	}

	public Long getDob() {
		return dob;
	}

	public void setDob(Long dob) {
		this.dob = dob;
	}

	public MultipartFile getResume() {
		return resume;
	}

	public void setResume(MultipartFile resume) {
		this.resume = resume;
	}

	public MultipartFile getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(MultipartFile profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	@Override
	public String toString() {
		return "SeekerDto [dob=" + dob + ", resume=" + resume + ", profilePhoto=" + profilePhoto + "]";
	}
}
