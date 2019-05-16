package com.spz.entity;

public class UserRoles {
		private Integer ur_id;
		private Integer u_id;
		private Integer r_id;
		public Integer getUr_id() {
			return ur_id;
		}
		public void setUr_id(Integer ur_id) {
			this.ur_id = ur_id;
		}
		public Integer getU_id() {
			return u_id;
		}
		public void setU_id(Integer u_id) {
			this.u_id = u_id;
		}
		public Integer getR_id() {
			return r_id;
		}
		public void setR_id(Integer r_id) {
			this.r_id = r_id;
		}
		public UserRoles(Integer ur_id, Integer u_id, Integer r_id) {
			super();
			this.ur_id = ur_id;
			this.u_id = u_id;
			this.r_id = r_id;
		}
		public UserRoles() {
			super();
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "Userroles [ur_id=" + ur_id + ", u_id=" + u_id + ", r_id=" + r_id + "]";
		}
		
}
