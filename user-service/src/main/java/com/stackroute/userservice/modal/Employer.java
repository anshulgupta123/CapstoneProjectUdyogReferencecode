package com.stackroute.userservice.modal;

import java.io.Serializable;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.stackroute.userservice.dto.EmployerFields;
import com.stackroute.userservice.enums.EmployerStatus;

@Document(collection = "employer")
public class Employer extends EmployerFields implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	private String _id;
	private Binary profilePhoto;
	private EmployerStatus status;

	
	public Employer() {
		super();
		// TODO Auto-generated constructor stub
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
	
	
		
	
	

}
