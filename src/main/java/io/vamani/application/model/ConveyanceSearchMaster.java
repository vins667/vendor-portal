package io.vamani.application.model;

import java.time.Instant;

import org.springframework.data.domain.Pageable;

public class ConveyanceSearchMaster {
	private Instant conveyanceDate;
	private Instant conveyanceDateTo;
    private String conveyanceDateFormat;
	private String status;
	private Pageable page;
    private int size;
    private int pageNo;

	public Instant getConveyanceDate() {
		return conveyanceDate;
	}
	public void setConveyanceDate(Instant conveyanceDate) {
		this.conveyanceDate = conveyanceDate;
	}


	public Instant getConveyanceDateTo() {
		return conveyanceDateTo;
	}

	public void setConveyanceDateTo(Instant conveyanceDateTo) {
		this.conveyanceDateTo = conveyanceDateTo;
	}

    public String getConveyanceDateFormat() {
        return conveyanceDateFormat;
    }

    public void setConveyanceDateFormat(String conveyanceDateFormat) {
        this.conveyanceDateFormat = conveyanceDateFormat;
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
