package com.spz.entity;

public class Roles {
		private Integer r_id;
		private String r_name;
		private Integer r_int0;
		private String r_string0;
		
		private Integer page;
		private Integer rows;
		
		public Integer getR_id() {
			return r_id;
		}
		public void setR_id(Integer r_id) {
			this.r_id = r_id;
		}
		public String getR_name() {
			return r_name;
		}
		public void setR_name(String r_name) {
			this.r_name = r_name;
		}
		public Integer getR_int0() {
			return r_int0;
		}
		public void setR_int0(Integer r_int0) {
			this.r_int0 = r_int0;
		}
		public String getR_string0() {
			return r_string0;
		}
		public void setR_string0(String r_string0) {
			this.r_string0 = r_string0;
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
		@Override
		public String toString() {
			return "Roles [r_id=" + r_id + ", r_name=" + r_name + ", r_int0=" + r_int0 + ", r_string0=" + r_string0
					+ ", page=" + page + ", rows=" + rows + "]";
		}
		
		
}
