package com.stackroute.jobservice.dto;

public abstract class CompanyFields {
	private String companyName;
	private String companyUrl;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyUrl() {
		return companyUrl;
	}

	public void setCompanyUrl(String companyUrl) {
		this.companyUrl = companyUrl;
	}

	@Override
	public String toString() {
		return "CompanyFields [companyName=" + companyName + ", companyUrl=" + companyUrl + "]";
	}

}
