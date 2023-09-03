package io.vamani.application.model;

import org.springframework.data.domain.Pageable;

public class HardwareSearchQuery {
	private Long storageMin;
	private Long storageMax;
	private Long memoryMax;
	private Long memoryMin;
    private Pageable page;
    private int size;
    private int pageNo;
	public Long getStorageMin() {
		return storageMin;
	}
	public void setStorageMin(Long storageMin) {
		this.storageMin = storageMin;
	}
	public Long getStorageMax() {
		return storageMax;
	}
	public void setStorageMax(Long storageMax) {
		this.storageMax = storageMax;
	}
	public Long getMemoryMax() {
		return memoryMax;
	}
	public void setMemoryMax(Long memoryMax) {
		this.memoryMax = memoryMax;
	}
	public Long getMemoryMin() {
		return memoryMin;
	}
	public void setMemoryMin(Long memoryMin) {
		this.memoryMin = memoryMin;
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
		return "HardwareSearchQuery [storageMin=" + storageMin + ", storageMax=" + storageMax + ", memoryMax="
				+ memoryMax + ", memoryMin=" + memoryMin + ", page=" + page + ", size=" + size + ", pageNo=" + pageNo
				+ "]";
	}
	
	
    
}
