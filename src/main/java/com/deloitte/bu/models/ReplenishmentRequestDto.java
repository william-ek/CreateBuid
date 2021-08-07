package com.deloitte.bu.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ReplenishmentRequestDto {
	@JsonIgnore
	private static final long serialVersionUID = 1L;
	
	private ReplenishmentMessage message;
	
	public ReplenishmentRequestDto(ReplenishmentMessage msg) {
		this.message = msg;
	}
	public ReplenishmentMessage getMessage() {
		return message;
	}
	public void setMessage(ReplenishmentMessage message) {
		this.message = message;
	}
}
