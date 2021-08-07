package com.deloitte.bu.models;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.deloitte.bu.utils.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserAttributes implements Serializable{
	
	@JsonIgnore
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(Constants.PERSON_FIRST_NAME)
	@NotNull(message="{request.firstnamerequired}")
	private String personFirstName;
	
	@JsonProperty(Constants.PERSON_LAST_NAME)
	@NotNull(message="{request.lastnamerequired}")
	private String personLastName;
	
	@JsonProperty(Constants.PERSON_DATE_OF_BIRTH)
	@NotNull(message="{request.dateofbirthrequired}")
	private String personDateOfBirth;
	
	@JsonProperty(Constants.PERSON_EMAIL)
	@NotNull(message="{request.personalemailrequired}")
	private String personEmail;
	
	@JsonProperty(Constants.PERSON_SSN)
	private String personSsn;
	
	@JsonProperty(Constants.PERSON_PASSPORT)
	private String personPassport;
	
	@JsonProperty(Constants.PERSON_MIDDLE_NAME)
	private String personMiddleName;
	
	@JsonProperty(Constants.PERSON_NAME_PREFIX)
	private String personNamePrefix;

	@JsonProperty(Constants.PERSON_NAME_SUFFIX)
	private String personNameSuffix;
	
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

	public String getPersonMiddleName() {
		return personMiddleName;
	}

	public void setPersonMiddleName(String personMiddleName) {
		this.personMiddleName = personMiddleName;
	}

	public String getPersonNamePrefix() {
		return personNamePrefix;
	}

	public void setPersonNamePrefix(String personNamePrefix) {
		this.personNamePrefix = personNamePrefix;
	}

	public String getPersonNameSuffix() {
		return personNameSuffix;
	}

	public void setPersonNameSuffix(String personNameSuffix) {
		this.personNameSuffix = personNameSuffix;
	}

}
