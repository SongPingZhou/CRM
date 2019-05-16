package com.spz.entity;

public class Netfollows {
	private Integer n_id;
	private Integer s_id;
	private String n_cname;
	private String n_followTime;
	private String n_nextfollowTime;
	private String n_content;
	private String u_id;
	private String n_followType;
	private String n_createTime;
	private String n_followState;
	public Integer getN_id() {
		return n_id;
	}
	public void setN_id(Integer n_id) {
		this.n_id = n_id;
	}
	public Integer getS_id() {
		return s_id;
	}
	public void setS_id(Integer s_id) {
		this.s_id = s_id;
	}
	public String getN_cname() {
		return n_cname;
	}
	public void setN_cname(String n_cname) {
		this.n_cname = n_cname;
	}
	public String getN_followTime() {
		return n_followTime;
	}
	public void setN_followTime(String n_followTime) {
		this.n_followTime = n_followTime;
	}
	public String getN_nextfollowTime() {
		return n_nextfollowTime;
	}
	public void setN_nextfollowTime(String n_nextfollowTime) {
		this.n_nextfollowTime = n_nextfollowTime;
	}
	public String getN_content() {
		return n_content;
	}
	public void setN_content(String n_content) {
		this.n_content = n_content;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getN_followType() {
		return n_followType;
	}
	public void setN_followType(String n_followType) {
		this.n_followType = n_followType;
	}
	public String getN_createTime() {
		return n_createTime;
	}
	public void setN_createTime(String n_createTime) {
		this.n_createTime = n_createTime;
	}
	public String getN_followState() {
		return n_followState;
	}
	public void setN_followState(String n_followState) {
		this.n_followState = n_followState;
	}
	public Netfollows(Integer n_id, Integer s_id, String n_cname, String n_followTime, String n_nextfollowTime,
			String n_content, String u_id, String n_followType, String n_createTime, String n_followState) {
		super();
		this.n_id = n_id;
		this.s_id = s_id;
		this.n_cname = n_cname;
		this.n_followTime = n_followTime;
		this.n_nextfollowTime = n_nextfollowTime;
		this.n_content = n_content;
		this.u_id = u_id;
		this.n_followType = n_followType;
		this.n_createTime = n_createTime;
		this.n_followState = n_followState;
	}
	public Netfollows() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Netfollows [n_id=" + n_id + ", s_id=" + s_id + ", n_cname=" + n_cname + ", n_followTime=" + n_followTime
				+ ", n_nextfollowTime=" + n_nextfollowTime + ", n_content=" + n_content + ", u_id=" + u_id
				+ ", n_followType=" + n_followType + ", n_createTime=" + n_createTime + ", n_followState="
				+ n_followState + "]";
	}
	
	
}
