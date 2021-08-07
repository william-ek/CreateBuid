package com.deloitte.bu.models;

import javax.naming.Name;
import javax.naming.ldap.LdapName;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entry(objectClasses = {"BostonSchema","top"})
public class User {
	
	@Id
	@JsonIgnore
	private Name dn;
	
    @DnAttribute(value = "buid")
	private String buid;
	
    @Attribute(name = "personFirstName")
	private String personFirstName;
	
    @Attribute(name = "personLastName")
	private String personLastName;
	
    @Attribute(name = "personDateOfBirth")
	private String personDateOfBirth;
	
    @Attribute(name = "personEmail")
	private String personEmail;
	
    @Attribute(name = "personSsn")
	private String personSsn;
    
    @Attribute(name = "personPassport")
	private String personPassport;
	
    @Attribute(name = "createdOn")
	private String createdOn;
    
    @Attribute(name = "createdBy")
	private String createdBy;
    
    @Attribute(name = "modifiedOn")
	private String modifiedOn;
    
    @Attribute(name = "modifiedBy")
	private String modifiedBy;
    
    @Attribute(name = "personMiddleName")
	private String personMiddleName;
	
    @Attribute(name = "personNamePrefix")
	private String personNamePrefix;
    
    @Attribute(name = "personNameSuffix")
	private String personNameSuffix;
	
    @Attribute(name = "MATCHIND")
    private String matchInd;
    
	public User() {
		super();
	}

	public User(String buid, String personFirstName, String personLastName, 
				String personDateOfBirth, String personEmail, String personSsn, 
				String personPassport, String createdOn, String createdBy, String modifiedOn, 
				String modifiedBy) {
		super();
		this.buid = buid;
		this.personFirstName = personFirstName;
		this.personLastName = personLastName;
		this.personDateOfBirth = personDateOfBirth;
		this.personEmail = personEmail;
		this.personSsn = personSsn;
		this.personPassport = personPassport;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.modifiedOn = modifiedOn;
		this.modifiedBy = modifiedBy;
	}
	
	public void setDn(LdapName buildDn) {

		this.dn = buildDn;
		
	}

	public Name getDn() {

		return this.dn;
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
	
	
	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(String modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
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

	public String getMatchInd() {
		return matchInd;
	}

	public void setMatchInd(String matchInd) {
		this.matchInd = matchInd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buid == null) ? 0 : buid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (buid == null) {
			if (other.buid != null)
				return false;
		} else if (!buid.equals(other.buid))
			return false;
		return true;
	}
	
	
	
}
