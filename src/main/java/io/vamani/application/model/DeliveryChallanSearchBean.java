package io.vamani.application.model;

import org.springframework.data.domain.Pageable;

public class DeliveryChallanSearchBean {
	private String factCode;
	private String status;
	private Pageable page;
    private int size;
    private int pageNo;
    
	public String getFactCode() {
		return factCode;
	}
	public void setFactCode(String factCode) {
		this.factCode = factCode;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
