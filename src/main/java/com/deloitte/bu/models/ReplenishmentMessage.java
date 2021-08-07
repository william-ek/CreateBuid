package com.deloitte.bu.models;

import java.util.List;

import com.deloitte.bu.utils.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ReplenishmentMessage {
	@JsonIgnore
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(Constants.SOR)
	private String sor;
	
	@JsonProperty(Constants.BUID)
	private String buid;
	
	@JsonProperty(Constants.PERSON_FIRST_NAME)
	private String personFirstName;
	
	@JsonProperty(Constants.PERSON_LAST_NAME)
	private String personLastName;
	
	@JsonProperty(Constants.PERSON_MIDDLE_NAME)
	private String personMiddleName;
	
	@JsonProperty(Constants.PERSON_NAME_PREFIX)
	private String personNamePrefix;
	
	@JsonProperty(Constants.PERSON_NAME_SUFFIX)
	private String personNameSuffix;
	
	@JsonProperty(Constants.PERSON_DATE_OF_BIRTH)
	private String personDateOfBirth;
	
	@JsonProperty(Constants.PERSON_SSN)
	private String personSsn;
	
	@JsonProperty(Constants.PERSON_EMAIL)
	private String personEmail;
	
	@JsonProperty(Constants.PERSON_PASSPORT)
	private String personPassport;
	
	@JsonProperty(Constants.CREATED_BY)
	private String createdBy;
	
	@JsonProperty(Constants.CREATED_ON)
	private String createdOn;
	
	@JsonProperty(Constants.UPDATED_BY)
	private String updatedBy;
	
	@JsonProperty(Constants.UPDATED_ON)
	private String updatedOn;
	
	@JsonProperty(Constants.REPLN)
	private List<Replenishment> repln;

	public String getSor() {
		return sor;
	}

	public void setSor(String sor) {
		this.sor = sor;
	}

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

	public String getPersonDateOfBirth() {
		return personDateOfBirth;
	}

	public void setPersonDateOfBirth(String personDateOfBirth) {
		this.personDateOfBirth = personDateOfBirth;
	}

	public String getPersonSsn() {
		return personSsn;
	}

	public void setPersonSsn(String personSsn) {
		this.personSsn = personSsn;
	}

	public String getPersonEmail() {
		return personEmail;
	}

	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
	}

	public String getPersonPassport() {
		return personPassport;
	}

	public void setPersonPassport(String personPassport) {
		this.personPassport = personPassport;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}

	public List<Replenishment> getRepln() {
		return repln;
	}

	public void setRepln(List<Replenishment> repln) {
		this.repln = repln;
	}
	
}
