package com.stackroute.userservice.dto;

import java.io.Serializable;
import java.util.List;

import org.bson.types.Binary;
import org.springframework.web.multipart.MultipartFile;

import com.stackroute.userservice.enums.SeekerDomain;
import com.stackroute.userservice.enums.SeekerStatus;

public class SeekerResponseDto extends SeekerFields implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long dob;
	private Binary resume;
	private Binary profilePhoto;
	private List<Object[]> education;
	private Long createdOn;
	private Long modifiedOn;
	private SeekerStatus status;

	public SeekerResponseDto() {
		super();
	}

	public Long getDob() {
		return dob;
	}

	public void setDob(Long dob) {
		this.dob = dob;
	}

	public Binary getResume() {
		return resume;
	}

	public void setResume(Binary resume) {
		this.resume = resume;
	}

	public Binary getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(Binary profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	public List<Object[]> getEducation() {
		return education;
	}

	public void setEducation(List<Object[]> education) {
		this.education = education;
	}

	public Long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Long createdOn) {
		this.createdOn = createdOn;
	}

	public Long getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Long modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public SeekerStatus getStatus() {
		return status;
	}

	public void setStatus(SeekerStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SeekerResponseDto [dob=" + dob + ", resume=" + resume + ", profilePhoto=" + profilePhoto
				+ ", education=" + education + ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn + ", status="
				+ status + "]";
	}

	

	
}
