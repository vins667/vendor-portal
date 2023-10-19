package io.vamani.application.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stitching_ctc_master")
public class StitchingCtcMaster implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long id;
	
	@Column(name = "dept_name")
	private String deptName;
	
	@Column(name = "day_no")
	private Instant DayNo;
	
	@Column(name = "factory_code")
	private String factoryCode;
	
	@Column(name = "emp_ctc")
	private Double empCtc;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Instant getDayNo() {
		return DayNo;
	}
	public void setDayNo(Instant dayNo) {
		DayNo = dayNo;
	}
	public String getFactoryCode() {
		return factoryCode;
	}
	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}
	public Double getEmpCtc() {
		return empCtc;
	}
	public void setEmpCtc(Double empCtc) {
		this.empCtc = empCtc;
	}
	@Override
	public String toString() {
		return "StitchingCtcMaster [id=" + id + ", deptName=" + deptName + ", DayNo=" + DayNo + ", factoryCode="
				+ factoryCode + ", empCtc=" + empCtc + "]";
	}
	
}
