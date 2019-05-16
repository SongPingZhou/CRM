package com.spz.entity;

public class Modules {
	private Integer m_id;
	private String m_name;
	private Integer m_parentId;
	private String m_path;
	private Integer m_weight;
	private String m_ops;
	private Integer m_int0;
	public Integer getM_id() {
		return m_id;
	}
	public void setM_id(Integer m_id) {
		this.m_id = m_id;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public Integer getM_parentId() {
		return m_parentId;
	}
	public void setM_parentId(Integer m_parentId) {
		this.m_parentId = m_parentId;
	}
	public String getM_path() {
		return m_path;
	}
	public void setM_path(String m_path) {
		this.m_path = m_path;
	}
	public Integer getM_weight() {
		return m_weight;
	}
	public void setM_weight(Integer m_weight) {
		this.m_weight = m_weight;
	}
	public String getM_ops() {
		return m_ops;
	}
	public void setM_ops(String m_ops) {
		this.m_ops = m_ops;
	}
	public Integer getM_int0() {
		return m_int0;
	}
	public void setM_int0(Integer m_int0) {
		this.m_int0 = m_int0;
	}
	public Modules(Integer m_id, String m_name, Integer m_parentId, String m_path, Integer m_weight, String m_ops,
			Integer m_int0) {
		super();
		this.m_id = m_id;
		this.m_name = m_name;
		this.m_parentId = m_parentId;
		this.m_path = m_path;
		this.m_weight = m_weight;
		this.m_ops = m_ops;
		this.m_int0 = m_int0;
	}
	public Modules() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Modules [m_id=" + m_id + ", m_name=" + m_name + ", m_parentId=" + m_parentId + ", m_path=" + m_path
				+ ", m_weight=" + m_weight + ", m_ops=" + m_ops + ", m_int0=" + m_int0 + "]";
	}
	
	
}
