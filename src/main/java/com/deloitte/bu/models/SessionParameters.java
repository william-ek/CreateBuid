package com.deloitte.bu.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SessionParameters {
	
	@Value("${ldapBindPassword}")
	private String ldapBindPassword;

	@Value("${ldapUrl}")
	private String ldapUrl;

	@Value("${ldapBindDN}")
	private String ldapBindDN;

	@Value("${ldapBaseDN}")
	private String ldapBaseDN;

	public String getLdapBindPassword() {
		return ldapBindPassword;
	}

	public void setLdapBindPassword(String ldapBindPassword) {
		this.ldapBindPassword = ldapBindPassword;
	}
	
	
	public String getLdapUrl() {
		return ldapUrl;
	}
	
	public void setLdapUrl(String ldapUrl) {
		this.ldapUrl = ldapUrl;
	}
	
	public String getLdapBindDN() {
		return ldapBindDN;
	}
	
	public void setLdapBindDN(String LdapBindDN) {
		this.ldapBindDN = LdapBindDN;
	}
	
	public String getLdapBaseDN() {
		return ldapBaseDN;
	}
	
	public void setLdapBaseDN(String ldapBaseDN) {
		this.ldapBaseDN = ldapBaseDN;
	}
	
}


