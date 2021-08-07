package com.deloitte.bu.models;

public class AuditLog {
	private String buid;
	private String createdBy;
	private String sourceOfRecord;
	private String modifiedDateTime;
	private String modifiedBy;
	private String modifiedSourceOfRecord;
	private String createdDateTime;
	
	public AuditLog(String buid, String createdDateTime, String createdBy, String modifiedOn, String modifiedBy) {
		this.buid = buid;
		this.createdDateTime = createdDateTime;
		this.createdBy = createdBy;
		this.sourceOfRecord = createdBy;
		this.modifiedDateTime = modifiedOn;
		this.modifiedBy = modifiedBy;
		this.modifiedSourceOfRecord = modifiedBy;
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
	public String getModifiedDateTime() {
		return modifiedDateTime;
	}
	public void setModifiedDateTime(String modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getModifiedSourceOfRecord() {
		return modifiedSourceOfRecord;
	}
	public void setModifiedSourceOfRecord(String modifiedSourceOfRecord) {
		this.modifiedSourceOfRecord = modifiedSourceOfRecord;
	}
	public String getBuid() {
		return buid;
	}
	public void setBuid(String buid) {
		this.buid = buid;
	}
	@Override
	public String toString() {
		return buid + ", " + createdDateTime + ", " + createdBy + ", " + sourceOfRecord + ", " + modifiedDateTime + ", " + modifiedBy + ", "
				+ modifiedSourceOfRecord;
	}
	
	
	
}
