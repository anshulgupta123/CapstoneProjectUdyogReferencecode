package com.stackroute.jobservice.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class JobApplicationRequestDto extends JobApplicationFields implements Serializable {

	private static final long serialVersionUID = 1L;

	private String jobApplicationId;
	private MultipartFile coverLetter;
	private MultipartFile updatedResume;

	public String getJobApplicationId() {
		return jobApplicationId;
	}

	public void setJobApplicationId(String jobApplicationId) {
		this.jobApplicationId = jobApplicationId;
	}

	public MultipartFile getCoverLetter() {
		return coverLetter;
	}

	public void setCoverLetter(MultipartFile coverLetter) {
		this.coverLetter = coverLetter;
	}

	public MultipartFile getUpdatedResume() {
		return updatedResume;
	}

	public void setUpdatedResume(MultipartFile updatedResume) {
		this.updatedResume = updatedResume;
	}

	@Override
	public String toString() {
		return "JobApplicationRequestDto [jobApplicationId=" + jobApplicationId + ", coverLetter=" + coverLetter
				+ ", updatedResume=" + updatedResume + "]";
	}

	
}
