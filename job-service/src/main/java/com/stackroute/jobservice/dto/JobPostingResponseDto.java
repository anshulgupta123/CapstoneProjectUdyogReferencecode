package com.stackroute.jobservice.dto;

import java.io.Serializable;

import org.bson.types.Binary;

import com.stackroute.jobservice.enums.JobStatus;

public class JobPostingResponseDto extends JobPostingFields implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private Long createdOn;
	private JobStatus status;
	private Long modifiedOn;
	private Long expiredDate;
	private Long expectedDateOfJoining;
	private Binary companyLogo;

	public JobPostingResponseDto() {
		super();
	}

	public Binary getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(Binary companyLogo) {
		this.companyLogo = companyLogo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Long createdOn) {
		this.createdOn = createdOn;
	}

	public JobStatus getStatus() {
		return status;
	}

	public void setStatus(JobStatus status) {
		this.status = status;
	}

	public Long getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Long modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public Long getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(Long expiredDate) {
		this.expiredDate = expiredDate;
	}

	public Long getExpectedDateOfJoining() {
		return expectedDateOfJoining;
	}

	public void setExpectedDateOfJoining(Long expectedDateOfJoining) {
		this.expectedDateOfJoining = expectedDateOfJoining;
	}

	@Override
	public String toString() {
		return "JobPostingResponseDto [id=" + id + ", createdOn=" + createdOn + ", status=" + status + ", modifiedOn="
				+ modifiedOn + ", expiredDate=" + expiredDate + ", expectedDateOfJoining=" + expectedDateOfJoining
				+ ", companyLogo=" + companyLogo + "]";
	}

}
