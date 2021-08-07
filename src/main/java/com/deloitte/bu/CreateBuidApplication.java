package com.deloitte.bu;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.deloitte.bu.models.RequestDto;
import com.deloitte.bu.models.ResponseDto;
import com.deloitte.bu.models.SessionParameters;
import com.deloitte.bu.services.LdapService;
import com.deloitte.bu.utils.CertificateTrust;

@ComponentScan(basePackages = "com.deloitte.bu")
@SpringBootApplication
public class CreateBuidApplication {
	
	@Autowired
	SessionParameters sessionParameters;
	
	@Autowired
	LdapService ldapService;

	public static void main(String[] args) {
		SpringApplication.run(CreateBuidApplication.class, args);
	}
	
    @Bean
    public Function<RequestDto, ResponseDto> getBuid() {
    	return (request) -> {
    		ResponseDto response = ldapService.getBuid(request);
    		return response;
    	};
    }
    
    @Bean
    public LdapContextSource contextSource() throws Exception {
        LdapContextSource contextSource= new LdapContextSource();
        contextSource.setUrl(sessionParameters.getLdapUrl());
        contextSource.setUserDn(sessionParameters.getLdapBindDN());
        contextSource.setPassword(sessionParameters.getLdapBindPassword());
        return contextSource;
    }

    @Bean
    public LdapTemplate ldapTemplate() throws Exception {
    	CertificateTrust.trustSelfSignedSSL();
        return new LdapTemplate(contextSource());        
    }
    
    public MessageSource messageSource() {
	    ReloadableResourceBundleMessageSource messageSource
	      = new ReloadableResourceBundleMessageSource();
	     
	    messageSource.setBasename("classpath:messages");
	    messageSource.setDefaultEncoding("UTF-8");
	    return messageSource;
	}
    
    @Bean
	public LocalValidatorFactoryBean getValidator() {
	    LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
	    bean.setValidationMessageSource(messageSource());
	    return bean;
	}

}
