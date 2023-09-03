package io.vamani.application.model;

import java.time.Instant;

/**
 * @author Abhishek
 *
 */
/**
 * @author Abhishek
 *
 */
public class MmrReport {

	private Instant dateFrom;
	private Instant dateTo;
	private String factoryCode;
	private String dayno;
	private String depCodeDesc;
	private String desCodeDesc;
	private String subSname;
	private Double pcountOnroll;
	private Double abcountOnroll;
	private Double pcountCutpic;
	private Double abcountCutpic;
	private Double pcountOnrollPrs;
	private Double abcountOnrollPrs;
	private Double pcountCutpicPrs;
	private Double abcountCutpicPrs;
	private String swcode;
	private Double salary;
	private Double pcsRate;
	
	public Instant getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Instant dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Instant getDateTo() {
		return dateTo;
	}
	public void setDateTo(Instant dateTo) {
		this.dateTo = dateTo;
	}
	public String getFactoryCode() {
		return factoryCode;
	}
	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}
	
	public String getSwcode() {
		return swcode;
	}
	public void setSwcode(String swcode) {
		this.swcode = swcode;
	}
	public String getDayno() {
		return dayno;
	}
	public void setDayno(String dayno) {
		this.dayno = dayno;
	}
	public String getDepCodeDesc() {
		return depCodeDesc;
	}
	public void setDepCodeDesc(String depCodeDesc) {
		this.depCodeDesc = depCodeDesc;
	}
	public String getDesCodeDesc() {
		return desCodeDesc;
	}
	public void setDesCodeDesc(String desCodeDesc) {
		this.desCodeDesc = desCodeDesc;
	}
	public String getSubSname() {
		return subSname;
	}
	public void setSubSname(String subSname) {
		this.subSname = subSname;
	}
	public Double getPcountOnroll() {
		return pcountOnroll;
	}
	public void setPcountOnroll(Double pcountOnroll) {
		this.pcountOnroll = pcountOnroll;
	}
	 
	public Double getAbcountOnroll() {
		return abcountOnroll;
	}
	public void setAbcountOnroll(Double abcountOnroll) {
		this.abcountOnroll = abcountOnroll;
	}
	public Double getPcountCutpic() {
		return pcountCutpic;
	}
	public void setPcountCutpic(Double pcountCutpic) {
		this.pcountCutpic = pcountCutpic;
	}
	public Double getAbcountCutpic() {
		return abcountCutpic;
	}
	public void setAbcountCutpic(Double abcountCutpic) {
		this.abcountCutpic = abcountCutpic;
	}
	public Double getPcountOnrollPrs() {
		return pcountOnrollPrs;
	}
	public void setPcountOnrollPrs(Double pcountOnrollPrs) {
		this.pcountOnrollPrs = pcountOnrollPrs;
	}
	public Double getAbcountOnrollPrs() {
		return abcountOnrollPrs;
	}
	public void setAbcountOnrollPrs(Double abcountOnrollPrs) {
		this.abcountOnrollPrs = abcountOnrollPrs;
	}
	public Double getPcountCutpicPrs() {
		return pcountCutpicPrs;
	}
	public void setPcountCutpicPrs(Double pcountCutpicPrs) {
		this.pcountCutpicPrs = pcountCutpicPrs;
	}
	public Double getAbcountCutpicPrs() {
		return abcountCutpicPrs;
	}
	public void setAbcountCutpicPrs(Double abcountCutpicPrs) {
		this.abcountCutpicPrs = abcountCutpicPrs;
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
