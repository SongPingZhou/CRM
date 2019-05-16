package com.spz.entity;

public class Userchecks {
	private Integer us_id;
	private String u_id;
	private String us_userName;
	private String us_checkinTime;
	private String us_checkState;
	private String us_isCancel;
	private String us_checkoutTime;
	public Integer getUs_id() {
		return us_id;
	}
	public void setUs_id(Integer us_id) {
		this.us_id = us_id;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getUs_userName() {
		return us_userName;
	}
	public void setUs_userName(String us_userName) {
		this.us_userName = us_userName;
	}
	public String getUs_checkinTime() {
		return us_checkinTime;
	}
	public void setUs_checkinTime(String us_checkinTime) {
		this.us_checkinTime = us_checkinTime;
	}
	public String getUs_checkState() {
		return us_checkState;
	}
	public void setUs_checkState(String us_checkState) {
		this.us_checkState = us_checkState;
	}
	public String getUs_isCancel() {
		return us_isCancel;
	}
	public void setUs_isCancel(String us_isCancel) {
		this.us_isCancel = us_isCancel;
	}
	public String getUs_checkoutTime() {
		return us_checkoutTime;
	}
	public void setUs_checkoutTime(String us_checkoutTime) {
		this.us_checkoutTime = us_checkoutTime;
	}
	public Userchecks(Integer us_id, String u_id, String us_userName, String us_checkinTime, String us_checkState,
			String us_isCancel, String us_checkoutTime) {
		super();
		this.us_id = us_id;
		this.u_id = u_id;
		this.us_userName = us_userName;
		this.us_checkinTime = us_checkinTime;
		this.us_checkState = us_checkState;
		this.us_isCancel = us_isCancel;
		this.us_checkoutTime = us_checkoutTime;
	}
	public Userchecks() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Userchecks [us_id=" + us_id + ", u_id=" + u_id + ", us_userName=" + us_userName + ", us_checkinTime="
				+ us_checkinTime + ", us_checkState=" + us_checkState + ", us_isCancel=" + us_isCancel
				+ ", us_checkoutTime=" + us_checkoutTime + "]";
	}
	
}
