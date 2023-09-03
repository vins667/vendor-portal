package io.vamani.application.model;

import org.springframework.data.domain.Pageable;

public class IgnoreSoftwareSearchMaster {
    
    private String swName;
    private String swPublisher;
    private Pageable page;
    private int size;
    private int pageNo;
    
	public String getSwName() {
		return swName;
	}
	public void setSwName(String swName) {
		this.swName = swName;
	}
	public String getSwPublisher() {
		return swPublisher;
	}
	public void setSwPublisher(String swPublisher) {
		this.swPublisher = swPublisher;
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
