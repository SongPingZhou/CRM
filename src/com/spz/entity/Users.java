package com.spz.entity;

public class Users {
	
	private Integer u_id;
	private String u_name;
	private String u_pwd;
	private Integer u_isLockout;
	private String u_lastLoginTime;
	private String u_createTime;
	private Integer u_pwdWrongTime;
	private String u_lockTime;
	private String u_protectEmail;
	private String u_protectMtel;
	private Integer u_state;
	
	private Integer page;
	private Integer rows;
	
	private String beginCreateTime; //最早创建时间
	private String endCreateTime;	//最晚创建时间
	
	private String beginlastLoginTime; //最早登录时间 
	private String endlastLoginTime; //最晚登录时间 
	
	
	
	public Integer getU_id() {
		return u_id;
	}
	public void setU_id(Integer u_id) {
		this.u_id = u_id;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getU_pwd() {
		return u_pwd;
	}
	public void setU_pwd(String u_pwd) {
		this.u_pwd = u_pwd;
	}
	public Integer getU_isLockout() {
		return u_isLockout;
	}
	public void setU_isLockout(Integer u_isLockout) {
		this.u_isLockout = u_isLockout;
	}
	public String getU_lastLoginTime() {
		return u_lastLoginTime;
	}
	public void setU_lastLoginTime(String u_lastLoginTime) {
		this.u_lastLoginTime = u_lastLoginTime;
	}
	public String getU_createTime() {
		return u_createTime;
	}
	public void setU_createTime(String u_createTime) {
		this.u_createTime = u_createTime;
	}
	public Integer getU_pwdWrongTime() {
		return u_pwdWrongTime;
	}
	public void setU_pwdWrongTime(Integer u_pwdWrongTime) {
		this.u_pwdWrongTime = u_pwdWrongTime;
	}
	
	public String getU_lockTime() {
		return u_lockTime;
	}
	public void setU_lockTime(String u_lockTime) {
		this.u_lockTime = u_lockTime;
	}
	public String getU_protectEmail() {
		return u_protectEmail;
	}
	public void setU_protectEmail(String u_protectEmail) {
		this.u_protectEmail = u_protectEmail;
	}
	public String getU_protectMtel() {
		return u_protectMtel;
	}
	public void setU_protectMtel(String u_protectMtel) {
		this.u_protectMtel = u_protectMtel;
	}
	
	public Integer getU_state() {
		return u_state;
	}
	public void setU_state(Integer u_state) {
		this.u_state = u_state;
	}
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
	
	public String getBeginCreateTime() {
		return beginCreateTime;
	}
	public void setBeginCreateTime(String beginCreateTime) {
		this.beginCreateTime = beginCreateTime;
	}
	public String getEndCreateTime() {
		return endCreateTime;
	}
	public void setEndCreateTime(String endCreateTime) {
		this.endCreateTime = endCreateTime;
	}
	public String getBeginlastLoginTime() {
		return beginlastLoginTime;
	}
	public void setBeginlastLoginTime(String beginlastLoginTime) {
		this.beginlastLoginTime = beginlastLoginTime;
	}
	public String getEndlastLoginTime() {
		return endlastLoginTime;
	}
	public void setEndlastLoginTime(String endlastLoginTime) {
		this.endlastLoginTime = endlastLoginTime;
	}
	@Override
	public String toString() {
		return "Users [u_id=" + u_id + ", u_name=" + u_name + ", u_pwd=" + u_pwd + ", u_isLockout=" + u_isLockout
				+ ", u_lastLoginTime=" + u_lastLoginTime + ", u_createTime=" + u_createTime + ", u_pwdWrongTime="
				+ u_pwdWrongTime + ", u_lockTime=" + u_lockTime + ", u_protectEmail=" + u_protectEmail
				+ ", u_protectMtel=" + u_protectMtel + ", u_state=" + u_state + ", page=" + page + ", rows=" + rows
				+ ", beginCreateTime=" + beginCreateTime + ", endCreateTime=" + endCreateTime + ", beginlastLoginTime="
				+ beginlastLoginTime + ", endlastLoginTime=" + endlastLoginTime + "]";
	}
	
	
}
