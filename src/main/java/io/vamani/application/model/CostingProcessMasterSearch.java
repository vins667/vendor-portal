package io.vamani.application.model;
import java.io.Serializable;
import org.springframework.data.domain.Pageable;

public class CostingProcessMasterSearch implements Serializable{
	private String processcode;
    private String processdesc;
    private Double fromQuantity;
    private Double toQuantity;
    private Pageable page;
    private int size;
    private int pageNo;
	public String getProcesscode() {
		return processcode;
	}
	public void setProcesscode(String processcode) {
		this.processcode = processcode;
	}
	public String getProcessdesc() {
		return processdesc;
	}
	public void setProcessdesc(String processdesc) {
		this.processdesc = processdesc;
	}
	public Double getFromQuantity() {
		return fromQuantity;
	}
	public void setFromQuantity(Double fromQuantity) {
		this.fromQuantity = fromQuantity;
	}
	public Double getToQuantity() {
		return toQuantity;
	}
	public void setToQuantity(Double toQuantity) {
		this.toQuantity = toQuantity;
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
