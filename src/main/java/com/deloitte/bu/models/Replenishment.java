package com.deloitte.bu.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Replenishment implements Serializable{
	
	@JsonIgnore
	private static final long serialVersionUID = 1L;
	
	private String replenishmentAttribute;
	
	private String replenishmentValue;

	public String getReplenishmentAttribute() {
		return replenishmentAttribute;
	}

	public void setReplenishmentAttribute(String replenishmentAttribute) {
		this.replenishmentAttribute = replenishmentAttribute;
	}

	public String getReplenishmentValue() {
		return replenishmentValue;
	}

	public void setReplenishmentValue(String replenishmentValue) {
		this.replenishmentValue = replenishmentValue;
	}
	
}
