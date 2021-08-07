package com.deloitte.bu.models;

import java.io.Serializable;
import java.util.List;

import com.deloitte.bu.utils.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ResponseDto implements Serializable {
	
	@JsonIgnore
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(Constants.STATUS_CODE)
	private String return_status_code;
	
	@JsonProperty(Constants.STATUS_DESCRIPTION)
	private String return_status_description;
	
	@JsonProperty(Constants.BUID_CREATED)
	private String buid_created;
	
	//@JsonProperty(Constants.USERS)
	//private List<String> users;
	private List<String> buid_match;
	
	@JsonProperty(Constants.ERRORS)
	private List<String> errors;

	public ResponseDto(
			//List<String> users, 
			List<String> buid_match,
			String return_status_code, 
			String return_status_description, 
			String buid_created
		) {
		//this.users = users;
		this.buid_match = buid_match;
		this.return_status_code = return_status_code;
		this.return_status_description = return_status_description;
		this.buid_created = buid_created;
	}

	public String getReturn_status_code() {
		return return_status_code;
	}

	public void setReturn_status_code(String return_status_code) {
		this.return_status_code = return_status_code;
	}
	
	public String getReturn_status_description() {
		return return_status_description;
	}

	public void setReturn_status_description(String return_status_description) {
		this.return_status_description = return_status_description;
	}
	
	public String getBuid_created() {
		return buid_created;
	}

	public void setBuid_created(String buid_created) {
		this.buid_created = buid_created;
	}
	
//	public List<String> getUsers() {
//		return buid_match;
//	}
//
//	public void setUsers(List<String> users) {
//		this.buid_match = users;
//	}
	
	public List<String> getErrors() {
		return errors;
	}

	public List<String> getBuid_match() {
		return buid_match;
	}

	public void setBuid_match(List<String> buid_match) {
		this.buid_match = buid_match;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
}
