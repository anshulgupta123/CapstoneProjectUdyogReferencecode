package com.stackroute.jobservice.dto;

import java.io.Serializable;

import org.bson.types.Binary;

public class CompanyResponseDto extends CompanyFields implements Serializable {

	private static final long serialVersionUID = 1L;
	private String companyId;
	private Binary companyLogo;

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public Binary getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(Binary companyLogo) {
		this.companyLogo = companyLogo;
	}

	@Override
	public String toString() {
		return "CompanyResponseDto [companyId=" + companyId + ", companyLogo=" + companyLogo + "]";
	}

}
