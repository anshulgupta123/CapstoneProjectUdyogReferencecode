package com.stackroute.userservice.dto;

import java.util.List;

import com.stackroute.userservice.enums.SeekerDomain;

public abstract class SeekerFields {

	private String email;
	private String name;
	private String description;
	private String location;
	private String higherEducation;
	private String currentRole;
	private String currentOrganisation;
	private List<String> skillSet;
	private List<Object[]> experience;
	private String mobileNumber;
	private List<Object[]> education;
	private Integer noticePeriod;
	private SeekerDomain domain;
	private String password;
	
	
	public SeekerFields() {
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
	public String getHigherEducation() {
		return higherEducation;
	}
	public void setHigherEducation(String higherEducation) {
		this.higherEducation = higherEducation;
	}
	public String getCurrentRole() {
		return currentRole;
	}
	public void setCurrentRole(String currentRole) {
		this.currentRole = currentRole;
	}
	public String getCurrentOrganisation() {
		return currentOrganisation;
	}
	public void setCurrentOrganisation(String currentOrganisation) {
		this.currentOrganisation = currentOrganisation;
	}
	public List<String> getSkillSet() {
		return skillSet;
	}
	public void setSkillSet(List<String> skillSet) {
		this.skillSet = skillSet;
	}
	public List<Object[]> getExperience() {
		return experience;
	}
	public void setExperience(List<Object[]> experience) {
		this.experience = experience;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public List<Object[]> getEducation() {
		return education;
	}
	public void setEducation(List<Object[]> education) {
		this.education = education;
	}
	public Integer getNoticePeriod() {
		return noticePeriod;
	}
	public void setNoticePeriod(Integer noticePeriod) {
		this.noticePeriod = noticePeriod;
	}
	public SeekerDomain getDomain() {
		return domain;
	}
	public void setDomain(SeekerDomain domain) {
		this.domain = domain;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "SeekerFields [email=" + email + ", name=" + name + ", description=" + description + ", location="
				+ location + ", higherEducation=" + higherEducation + ", currentRole=" + currentRole
				+ ", currentOrganisation=" + currentOrganisation + ", skillSet=" + skillSet + ", experience="
				+ experience + ", mobileNumber=" + mobileNumber + ", education=" + education + ", noticePeriod="
				+ noticePeriod + ", domain=" + domain + ", password=" + password + "]";
	}
	
	
}
