package com.stackroute.jobservice.dto;

import java.util.List;

public abstract class JobPostingFields {

	private String position;
	private List<String> skillSet;
	private Long jobPackage;
	private Integer experiencedRequired;
	private Integer noOfEmployeesRequired;
	private String description;
	private String location;
	private String company;
	private String companyUrl;
	private String employerEmail;

	public String getEmployerEmail() {
		return employerEmail;
	}

	public void setEmployerEmail(String employerEmail) {
		this.employerEmail = employerEmail;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public List<String> getSkillSet() {
		return skillSet;
	}

	public void setSkillSet(List<String> skillSet) {
		this.skillSet = skillSet;
	}

	public Long getJobPackage() {
		return jobPackage;
	}

	public void setJobPackage(Long jobPackage) {
		this.jobPackage = jobPackage;
	}

	public Integer getExperiencedRequired() {
		return experiencedRequired;
	}

	public void setExperiencedRequired(Integer experiencedRequired) {
		this.experiencedRequired = experiencedRequired;
	}

	public Integer getNoOfEmployeesRequired() {
		return noOfEmployeesRequired;
	}

	public void setNoOfEmployeesRequired(Integer noOfEmployeesRequired) {
		this.noOfEmployeesRequired = noOfEmployeesRequired;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompanyUrl() {
		return companyUrl;
	}

	public void setCompanyUrl(String companyUrl) {
		this.companyUrl = companyUrl;
	}

	@Override
	public String toString() {
		return "JobPostingFields [position=" + position + ", skillSet=" + skillSet + ", jobPackage=" + jobPackage
				+ ", experiencedRequired=" + experiencedRequired + ", noOfEmployeesRequired=" + noOfEmployeesRequired
				+ ", description=" + description + ", location=" + location + ", company=" + company + ", companyUrl="
				+ companyUrl + ", employerEmail=" + employerEmail + "]";
	}

}
