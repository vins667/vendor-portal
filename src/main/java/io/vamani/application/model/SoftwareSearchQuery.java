package io.vamani.application.model;

import org.springframework.data.domain.Pageable;

public class SoftwareSearchQuery {
	    private String assetCode;
	    private String publisher;
	    private String software;
	    private Pageable page;
	    private int size;
	    private int pageNo;
		public String getAssetCode() {
			return assetCode;
		}
		public void setAssetCode(String assetCode) {
			this.assetCode = assetCode;
		}
		public String getPublisher() {
			return publisher;
		}
		public void setPublisher(String publisher) {
			this.publisher = publisher;
		}
		public String getSoftware() {
			return software;
		}
		public void setSoftware(String software) {
			this.software = software;
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
