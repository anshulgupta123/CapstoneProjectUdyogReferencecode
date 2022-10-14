package com.stackroute.userservice.modal;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.bson.BsonTimestamp;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.stackroute.userservice.dto.SeekerFields;
import com.stackroute.userservice.enums.SeekerDomain;
import com.stackroute.userservice.enums.SeekerStatus;

@Document(collection = "seeker")
public class Seeker extends SeekerFields implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String _id;
	private Date dob;
	private BsonTimestamp createdOn;
	private BsonTimestamp modifiedOn;
	private Binary resume;
	private Binary profilePhoto;
	private SeekerStatus status;

	public Seeker() {
		super();
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
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

	public Binary getResume() {
		return resume;
	}

	public void setResume(Binary resume) {
		this.resume = resume;
	}

	public Binary getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(Binary profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	

	public SeekerStatus getStatus() {
		return status;
	}

	public void setStatus(SeekerStatus status) {
		this.status = status;
	}

}
