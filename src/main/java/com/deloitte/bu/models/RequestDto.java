package com.deloitte.bu.models;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.deloitte.bu.utils.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestDto implements Serializable{
	
	@JsonIgnore
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(Constants.SOR)
	@NotNull(message="{request.sourcerequired}")
	private String sor;
	
	@Valid
	@JsonProperty(Constants.ATTRIBUTES)
	@NotNull(message="{request.attributesrequired}")
	private UserAttributes attributes;
	
	@Valid
	@JsonProperty(Constants.REPLN)
	@NotNull(message="{request.replenishmentrequired}")
	private List<Replenishment> repl;

	public String getSor() {
		return sor;
	}

	public void setSor(String sor) {
		this.sor = sor;
	}

	public UserAttributes getAttributes() {
		return attributes;
	}

	public void setAttributes(UserAttributes attributes) {
		this.attributes = attributes;
	}

	public List<Replenishment> getRepl() {
		return repl;
	}

	public void setRepl(List<Replenishment> repl) {
		this.repl = repl;
	}
	
}
