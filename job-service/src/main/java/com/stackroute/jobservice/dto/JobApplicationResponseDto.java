package com.stackroute.jobservice.dto;

import java.io.Serializable;

import org.bson.BsonTimestamp;
import org.bson.types.Binary;

public class JobApplicationResponseDto extends JobApplicationFields implements Serializable {

	private static final long serialVersionUID = 1L;
	private String jobApplicationId;
	private Long createdOn;
	private Long modifiedOn;
	private Binary coverLetter;
	private Binary updatedResume;

	public String getJobApplicationId() {
		return jobApplicationId;
	}

	public void setJobApplicationId(String jobApplicationId) {
		this.jobApplicationId = jobApplicationId;
	}

	public Long getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Long modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public Long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Long createdOn) {
		this.createdOn = createdOn;
	}

	public Binary getCoverLetter() {
		return coverLetter;
	}

	public void setCoverLetter(Binary coverLetter) {
		this.coverLetter = coverLetter;
	}

	public Binary getUpdatedResume() {
		return updatedResume;
	}

	public void setUpdatedResume(Binary updatedResume) {
		this.updatedResume = updatedResume;
	}

	@Override
	public String toString() {
		return "JobApplicationResponseDto [jobApplicationId=" + jobApplicationId + ", createdOn=" + createdOn
				+ ", modifiedOn=" + modifiedOn + ", coverLetter=" + coverLetter + ", updatedResume=" + updatedResume
				+ "]";
	}

}
