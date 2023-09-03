package io.vamani.application.model;

public class MMRDesignationBean {
	private Long id;
	private String designation;
	private String designationDesc;
	private String swCode;
    private Double salary;
    private Double pcsRate;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDesignationDesc() {
		return designationDesc;
	}
	public void setDesignationDesc(String designationDesc) {
		this.designationDesc = designationDesc;
	}
	public String getSwCode() {
		return swCode;
	}
	public void setSwCode(String swCode) {
		this.swCode = swCode;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public Double getPcsRate() {
		return pcsRate;
	}
	public void setPcsRate(Double pcsRate) {
		this.pcsRate = pcsRate;
	}
	 
}
