package io.vamani.application.model;

import java.util.List;

public class MMRDepartmentBean {
	private Long id;
	private String department;
	private String departmentDesc;
	private List<MMRDesignationBean> mmrDesignationBean;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDepartmentDesc() {
		return departmentDesc;
	}
	public void setDepartmentDesc(String departmentDesc) {
		this.departmentDesc = departmentDesc;
	}
	public List<MMRDesignationBean> getMmrDesignationBean() {
		return mmrDesignationBean;
	}
	public void setMmrDesignationBean(List<MMRDesignationBean> mmrDesignationBean) {
		this.mmrDesignationBean = mmrDesignationBean;
	}
	
}
