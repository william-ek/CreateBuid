package com.deloitte.bu.models;

import java.util.List;

public class MatchLog {
	private String match;
	private String buids;
	private String buid_created;
	public MatchLog(String matchType, List<String> buidsList, String buid_created) {
		this.match = matchType;
		this.buid_created = buid_created;
		if(buidsList != null) {
			this.buids = buidsList.toString().replace(',', ' ');
		}else {
			this.buids = null;
		}
	}
	public String getMatch() {
		return match;
	}
	public void setMatch(String match) {
		this.match = match;
	}
	public String getBuids() {
		return buids;
	}
	public void setBuids(String buids) {
		this.buids = buids;
	}
	public String getBuid_created() {
		return buid_created;
	}
	public void setBuid_created(String buid_created) {
		this.buid_created = buid_created;
	}
	@Override
	public String toString() {
		return match + ", " + buids + ", " + buid_created;
	}
}
