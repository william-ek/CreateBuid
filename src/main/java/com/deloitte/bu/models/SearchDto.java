package com.deloitte.bu.models;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SearchDto implements Serializable {
	
	@JsonIgnore
	private static final long serialVersionUID = 1L;
	
	private String buid;
	
	private String personFirstName;

	private String personLastName;

	private String personDateOfBirth;

	private String personEmail;
	
	private String personSsn;
	
	private String personPassport;

	public String getBuid() {
		return buid;
	}

	public void setBuid(String buid) {
		this.buid = buid;
	}

	public String getPersonFirstName() {
		return personFirstName;
	}

	public void setPersonFirstName(String personFirstName) {
		this.personFirstName = personFirstName;
	}

	public String getPersonLastName() {
		return personLastName;
	}

	public void setPersonLastName(String personLastName) {
		this.personLastName = personLastName;
	}

	public String getPersonDateOfBirth() {
		return personDateOfBirth;
	}

	public void setPersonDateOfBirth(String personDateOfBirth) {
		this.personDateOfBirth = personDateOfBirth;
	}

	public String getPersonEmail() {
		return personEmail;
	}

	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
	}

	public String getPersonSsn() {
		return personSsn;
	}

	public void setPersonSsn(String personSsn) {
		this.personSsn = personSsn;
	}

	public String getPersonPassport() {
		return personPassport;
	}

	public void setPersonPassport(String personPassport) {
		this.personPassport = personPassport;
	}

}
