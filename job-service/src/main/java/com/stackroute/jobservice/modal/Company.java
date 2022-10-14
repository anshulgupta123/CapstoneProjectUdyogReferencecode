package com.stackroute.jobservice.modal;

import java.io.Serializable;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.stackroute.jobservice.dto.CompanyFields;

@Document(collection ="company")
public class Company extends CompanyFields implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String _id;
	private Binary companyLogo;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public Binary getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(Binary companyLogo) {
		this.companyLogo = companyLogo;
	}

	@Override
	public String toString() {
		return "Company [_id=" + _id + ", companyLogo=" + companyLogo + "]";
	}

}
