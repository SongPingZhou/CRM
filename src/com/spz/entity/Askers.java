package com.spz.entity;

public class Askers {
	private Integer a_askerId;
	private String a_askerName;
	private String a_checkState;
	private String a_checkInTime;
	private String a_changeState;
	private Integer a_weight;
	private String a_roleName;
	private String a_bakContent;
	private Integer us_id;
	
	public Integer getA_askerId() {
		return a_askerId;
	}
	public void setA_askerId(Integer a_askerId) {
		this.a_askerId = a_askerId;
	}
	public String getA_askerName() {
		return a_askerName;
	}
	public void setA_askerName(String a_askerName) {
		this.a_askerName = a_askerName;
	}
	public String getA_checkState() {
		return a_checkState;
	}
	public void setA_checkState(String a_checkState) {
		this.a_checkState = a_checkState;
	}
	public String getA_checkInTime() {
		return a_checkInTime;
	}
	public void setA_checkInTime(String a_checkInTime) {
		this.a_checkInTime = a_checkInTime;
	}
	public String getA_changeState() {
		return a_changeState;
	}
	public void setA_changeState(String a_changeState) {
		this.a_changeState = a_changeState;
	}
	public Integer getA_weight() {
		return a_weight;
	}
	public void setA_weight(Integer a_weight) {
		this.a_weight = a_weight;
	}
	public String getA_roleName() {
		return a_roleName;
	}
	public void setA_roleName(String a_roleName) {
		this.a_roleName = a_roleName;
	}
	public String getA_bakContent() {
		return a_bakContent;
	}
	public void setA_bakContent(String a_bakContent) {
		this.a_bakContent = a_bakContent;
	}
	public Integer getUs_id() {
		return us_id;
	}
	public void setUs_id(Integer us_id) {
		this.us_id = us_id;
	}
	@Override
	public String toString() {
		return "Askers [a_askerId=" + a_askerId + ", a_askerName=" + a_askerName + ", a_checkState=" + a_checkState
				+ ", a_checkInTime=" + a_checkInTime + ", a_changeState=" + a_changeState + ", a_weight=" + a_weight
				+ ", a_roleName=" + a_roleName + ", a_bakContent=" + a_bakContent + ", us_id=" + us_id + "]";
	}
	
	
}
