package com.stackroute.jobservice.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class CompanyRequestDto extends CompanyFields implements Serializable {

	private static final long serialVersionUID = 1L;
	private MultipartFile companyLogo;

	public MultipartFile getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(MultipartFile companyLogo) {
		this.companyLogo = companyLogo;
	}

	@Override
	public String toString() {
		return "CompanyRequestDto [companyLogo=" + companyLogo + "]";
	}

}
