package io.vamani.application.model;

import org.springframework.data.domain.Pageable;

public class SoftwareKeyDetailsSearch {
	private String name;
    private String jhiKey;
    private Pageable page;
    private int size;
    private int pageNo;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getJhiKey() {
		return jhiKey;
	}
	public void setJhiKey(String jhiKey) {
		this.jhiKey = jhiKey;
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
	@Override
	public String toString() {
		return "SoftwareKeyDetailsSearch [name=" + name + ", jhiKey=" + jhiKey + ", page=" + page + ", size=" + size
				+ ", pageNo=" + pageNo + "]";
	}
	
}
