package com.stackroute.jobservice.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class JobPostingRequestDto extends JobPostingFields implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private Long expiredDate;
	private Long expectedDateOfJoining;
	private MultipartFile companyLogo;

	public JobPostingRequestDto() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public MultipartFile getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(MultipartFile companyLogo) {
		this.companyLogo = companyLogo;
	}

	@Override
	public String toString() {
		return "JobPostingRequestDto [id=" + id + ", expiredDate=" + expiredDate + ", expectedDateOfJoining="
				+ expectedDateOfJoining + ", companyLogo=" + companyLogo + "]";
	}

}
