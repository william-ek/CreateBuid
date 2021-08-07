package com.deloitte.bu.models;

public class ReplenishmentLog {
	private String buid;
	private String createdBy;
	private String sourceOfRecord;
	private String replPayload;
	private String createdDateTime;
	
	public ReplenishmentLog(String buid, String createdDateTime, String createdBy, String replPayload) {
		this.buid = buid;
		this.createdDateTime = createdDateTime;
		this.createdBy = createdBy;
		this.sourceOfRecord = createdBy;
		this.replPayload = replPayload;
	}
	public String getCreatedDateTime() {
		return createdDateTime;
	}
	public void setCreatedDateTime(String createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getSourceOfRecord() {
		return sourceOfRecord;
	}
	public void setSourceOfRecord(String sourceOfRecord) {
		this.sourceOfRecord = sourceOfRecord;
	}
	public String getReplPayload() {
		return replPayload;
	}
	public void setReplPayload(String replPayload) {
		this.replPayload = replPayload;
	}
	
	public String getBuid() {
		return buid;
	}
	public void setBuid(String buid) {
		this.buid = buid;
	}
	@Override
	public String toString() {
		return buid + ", " + createdDateTime + ", " + createdBy + ", " + sourceOfRecord + ", " + replPayload ;
	}
	
	
	
}
