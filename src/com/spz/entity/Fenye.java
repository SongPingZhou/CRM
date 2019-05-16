package com.spz.entity;

import java.util.List;

public class Fenye<T> {
	
	private Integer total;
	private List<T> rows;
	
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	@Override
	public String toString() {
		return "Fenye [total=" + total + ", rows=" + rows + "]";
	}
	
	
}
