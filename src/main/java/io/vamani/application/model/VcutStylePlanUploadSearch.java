package io.vamani.application.model;
import java.time.LocalDate;

import org.springframework.data.domain.Pageable;

public class VcutStylePlanUploadSearch {
	private LocalDate planDate;
	private LocalDate planDateTo;
	private String poNo;
    private String style;
    private Pageable page;
    private int size;
    private int pageNo;
	
	public LocalDate getPlanDate() {
		return planDate;
	}
	public void setPlanDate(LocalDate planDate) {
		this.planDate = planDate;
	}
	
	public LocalDate getPlanDateTo() {
		return planDateTo;
	}
	public void setPlanDateTo(LocalDate planDateTo) {
		this.planDateTo = planDateTo;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public Pageable getPage() {
		return page;
	}
	public void setPage(Pageable page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
    
}
