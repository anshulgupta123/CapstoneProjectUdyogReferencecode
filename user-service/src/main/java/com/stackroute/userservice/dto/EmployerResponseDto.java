package com.stackroute.userservice.dto;

import java.io.Serializable;

import org.bson.types.Binary;

import com.stackroute.userservice.enums.EmployerStatus;

public class EmployerResponseDto extends EmployerFields implements Serializable {

	private static final long serialVersionUID = 1L;
	private String _id;
	private Binary profilePhoto;
	private EmployerStatus status;

	public EmployerResponseDto() {
		super();
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public Binary getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(Binary profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	public EmployerStatus getStatus() {
		return status;
	}

	public void setStatus(EmployerStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "EmployerResponseDto [_id=" + _id + ", profilePhoto=" + profilePhoto + ", status=" + status + "]";
	}

}
