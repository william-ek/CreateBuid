package com.deloitte.bu.repository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Repository;

import com.deloitte.bu.models.SearchDto;
import com.deloitte.bu.models.SessionParameters;
import com.deloitte.bu.models.User;

import javax.naming.ldap.LdapName;

import java.util.List;

@Repository
public class UserLdapRepository {
	
	protected static final Log logger = LogFactory.getLog(UserLdapRepository.class);
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	SessionParameters sessionParameters;

	@Autowired
	private LdapTemplate ldapTemplate;

    
	public void create(User user) throws Exception{
		
	    if (user.getPersonPassport() == null || user.getPersonPassport().length() == 0) {
	    	user.setPersonPassport(null);
	    }
	    
	    if (user.getPersonSsn() == null || user.getPersonSsn().length() == 0) {
	    	user.setPersonSsn(null);
	    }
	    
	    user.setDn(buildDn(user));
	    user.setMatchInd("Y");
	    
	    ldapTemplate.create(user);
	}
	
	public User findByPrimaryKey(String cn) throws Exception{
		LdapName dn = buildDn(cn);
        User user = ldapTemplate.findByDn(dn, User.class);

        return user;
	}
	
	public List<User> find(SearchDto request) throws Exception{
		
		StringBuilder filter = new StringBuilder();
		
		filter.append("(&");
		
	    if (request.getPersonFirstName() != null && request.getPersonFirstName().length() > 0) {
	    	
	    	filter.append("(|");
	    	filter.append("(personFirstName=" + request.getPersonFirstName() + ")");
	    	filter.append("(personfirstnamehistory=" + request.getPersonFirstName() + " [*)");
	    	filter.append(")");
	    }
	    
	    if (request.getPersonLastName() != null && request.getPersonLastName().length() > 0) {
	    	
	    	filter.append("(|");
	    	filter.append("(personLastName=" + request.getPersonLastName() + ")");
	    	filter.append("(personlastnamehistory=" + request.getPersonLastName() + " [*)");
	    	filter.append(")");
	    }
	    
	    if (request.getPersonDateOfBirth() != null && request.getPersonDateOfBirth().length() > 0) {
	    	
	    	filter.append("(personDateOfBirth=" + request.getPersonDateOfBirth() + ")");

	    }
	    
	    if (request.getPersonEmail() != null && request.getPersonEmail().length() > 0) {
	    	
	    	filter.append("(|");
	    	filter.append("(personEmail=" + request.getPersonEmail() + ")");
	    	filter.append("(personemailhistory=" + request.getPersonEmail() + " [*)");
	    	filter.append(")");
	    	
	    }
	    
	    if (request.getPersonPassport() != null && request.getPersonPassport().length() > 0) {
	    	
	    	filter.append("(|");
	    	filter.append("(personPassport=" + request.getPersonPassport() + ")");
	    	filter.append("(personpassporthistory=" + request.getPersonPassport() + " [*)");
	    	filter.append(")");

	    }
	    
	    if (request.getPersonSsn() != null && request.getPersonSsn().length() > 0) {
	    	
	    	filter.append("(personSsn=" + request.getPersonSsn() + ")");

	    }
	    
	    filter.append("(MATCHIND=Y)");
	    filter.append(")");
		
		LdapQuery query = LdapQueryBuilder.query().base(sessionParameters.getLdapBaseDN()).filter(filter.toString());
		
		List<User> users = ldapTemplate.find(query, User.class);
		
		return users;
		
	}

	private LdapName buildDn(User user) {
		return buildDn(user.getBuid());
	}

	public LdapName buildDn(String buid) {
        return LdapNameBuilder.newInstance()
                .add(sessionParameters.getLdapBaseDN())
                .add("buid", buid)
                .build();
	} 
	
	public LdapName buildBaseDn() {
        return LdapNameBuilder.newInstance()
                .add(sessionParameters.getLdapBaseDN())
                .build();
	} 

	public void setLdapTemplate(LdapTemplate ldapTemplate) {
		this.ldapTemplate = ldapTemplate;
	}
}


