package io.vamani.application.model;

import java.util.List;

public class poApprovalHeaderBean {

	private String currentstatus; 
	private String vngstnumber; 
	private String vnstatename; 
	private String templatecode; 
	private String projectcode; 
	private String countercode; 
	private String code;  
	private String orderdate; 
	private String vnname; 
	private String vnaddress; 
	private String warehousecode;
	private List<poApprovalLineBean> polineBean;
	
	public String getCurrentstatus() {
		return currentstatus;
	}
	public void setCurrentstatus(String currentstatus) {
		this.currentstatus = currentstatus;
	}
	public String getVngstnumber() {
		return vngstnumber;
	}
	public void setVngstnumber(String vngstnumber) {
		this.vngstnumber = vngstnumber;
	}
	public String getVnstatename() {
		return vnstatename;
	}
	public void setVnstatename(String vnstatename) {
		this.vnstatename = vnstatename;
	}
	public String getTemplatecode() {
		return templatecode;
	}
	public void setTemplatecode(String templatecode) {
		this.templatecode = templatecode;
	}
	public String getProjectcode() {
		return projectcode;
	}
	public void setProjectcode(String projectcode) {
		this.projectcode = projectcode;
	}
	public String getCountercode() {
		return countercode;
	}
	public void setCountercode(String countercode) {
		this.countercode = countercode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}
	public String getVnname() {
		return vnname;
	}
	public void setVnname(String vnname) {
		this.vnname = vnname;
	}
	public String getVnaddress() {
		return vnaddress;
	}
	public void setVnaddress(String vnaddress) {
		this.vnaddress = vnaddress;
	}
	public String getWarehousecode() {
		return warehousecode;
	}
	public void setWarehousecode(String warehousecode) {
		this.warehousecode = warehousecode;
	}
	public List<poApprovalLineBean> getPolineBean() {
		return polineBean;
	}
	public void setPolineBean(List<poApprovalLineBean> polineBean) {
		this.polineBean = polineBean;
	} 
	
	
}
