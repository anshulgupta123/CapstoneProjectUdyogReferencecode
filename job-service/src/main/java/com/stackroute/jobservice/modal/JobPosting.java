package com.stackroute.jobservice.modal;

import java.io.Serializable;
import java.util.Date;

import org.bson.BsonTimestamp;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.stackroute.jobservice.dto.JobPostingFields;
import com.stackroute.jobservice.enums.JobStatus;

@Document(collection = "jobposting")
public class JobPosting extends JobPostingFields implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private BsonTimestamp createdOn;
	private JobStatus status;
	private BsonTimestamp modifiedOn;
	private BsonTimestamp expiredDate;
	private Date expectedDateOfJoining;
	private Binary companyLogo;

	public JobPosting() {
		super();
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

	public JobStatus getStatus() {
		return status;
	}

	public void setStatus(JobStatus status) {
		this.status = status;
	}

	public BsonTimestamp getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(BsonTimestamp modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public BsonTimestamp getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(BsonTimestamp expiredDate) {
		this.expiredDate = expiredDate;
	}

	public Date getExpectedDateOfJoining() {
		return expectedDateOfJoining;
	}

	public void setExpectedDateOfJoining(Date expectedDateOfJoining) {
		this.expectedDateOfJoining = expectedDateOfJoining;
	}

	public Binary getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(Binary companyLogo) {
		this.companyLogo = companyLogo;
	}

}
