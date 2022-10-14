package com.stackroute.jobservice.modal;

import java.io.Serializable;

import org.bson.BsonTimestamp;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.stackroute.jobservice.dto.JobApplicationFields;
import com.stackroute.jobservice.enums.JobApplicationStatus;

@Document(collection = "jobapplication")
public class JobApplication extends  JobApplicationFields implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private BsonTimestamp createdOn;
	private BsonTimestamp modifiedOn;
	private Binary coverLetter;
	private Binary updatedResume;

	public JobApplication() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BsonTimestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(BsonTimestamp createdOn) {
		this.createdOn = createdOn;
	}

	public BsonTimestamp getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(BsonTimestamp modifiedOn) {
		this.modifiedOn = modifiedOn;
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

}
