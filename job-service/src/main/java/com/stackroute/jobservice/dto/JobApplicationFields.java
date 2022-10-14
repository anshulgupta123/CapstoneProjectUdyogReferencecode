package com.stackroute.jobservice.dto;

import com.stackroute.jobservice.enums.JobApplicationStatus;

public abstract class JobApplicationFields {

	private String jobId;
	private JobApplicationStatus status;
	private String appliedBy;
	private String commentFromOrganisation;
	private Long expectedSalary;

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public JobApplicationStatus getStatus() {
		return status;
	}

	public void setStatus(JobApplicationStatus status) {
		this.status = status;
	}

	public String getAppliedBy() {
		return appliedBy;
	}

	public void setAppliedBy(String appliedBy) {
		this.appliedBy = appliedBy;
	}

	public String getCommentFromOrganisation() {
		return commentFromOrganisation;
	}

	public void setCommentFromOrganisation(String commentFromOrganisation) {
		this.commentFromOrganisation = commentFromOrganisation;
	}

	public Long getExpectedSalary() {
		return expectedSalary;
	}

	public void setExpectedSalary(Long expectedSalary) {
		this.expectedSalary = expectedSalary;
	}

	@Override
	public String toString() {
		return "JobApplicationFields [jobId=" + jobId + ", status=" + status + ", appliedBy=" + appliedBy
				+ ", commentFromOrganisation=" + commentFromOrganisation + ", expectedSalary=" + expectedSalary + "]";
	}

}
