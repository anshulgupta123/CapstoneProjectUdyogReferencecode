package com.stackroute.userservice.dto;


public abstract class EmployerFields {

	private String email;
	private String name;
	private String company;
	private Integer yearsOfExperience;
	private String companyUrl;
	private String mobileNumber;
	private String password;
	
	public EmployerFields() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Integer getYearsOfExperience() {
		return yearsOfExperience;
	}
	public void setYearsOfExperience(Integer yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}
	public String getCompanyUrl() {
		return companyUrl;
	}
	public void setCompanyUrl(String companyUrl) {
		this.companyUrl = companyUrl;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "EmployerFields [email=" + email + ", name=" + name + ", company=" + company + ", yearsOfExperience="
				+ yearsOfExperience + ", companyUrl=" + companyUrl + ", mobileNumber=" + mobileNumber + ", password="
				+ password + "]";
	}
	
	
	
}
