package io.vamani.application.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class GstReconciliationBean {

	private Long id;
    private String unitCode;
    private String gstin;
    private String invoiceType;
    private String invoiceNo;
    private Instant invoiceDate;
    private String supplierName;
    private String state;
    private Double invoiceAmount;
    private Double reverseAmount;
    private Double cgstAmount;
    private Double sgstAmount;
    private Double igstAmount;
    private Double cessAmount;
    private String location;
    private String srlNumber;
    private String status;
    private Instant creationDate;
    private Double govtInvoiceAmount;
    private Double govtCgstAmount;
    private Double govtSgstAmount;
    private Double govtIgstAmount;
    private Double differenceAmt;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUnitCode() {
		return unitCode;
	}
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	public String getGstin() {
		return gstin;
	}
	public void setGstin(String gstin) {
		this.gstin = gstin;
	}
	
	public Double getDifferenceAmt() {
		return differenceAmt;
	}
	public void setDifferenceAmt(Double differenceAmt) {
		this.differenceAmt = differenceAmt;
	}
	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	
	public Double getGovtInvoiceAmount() {
		return govtInvoiceAmount;
	}
	public void setGovtInvoiceAmount(Double govtInvoiceAmount) {
		this.govtInvoiceAmount = govtInvoiceAmount;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public Instant getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Instant invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Double getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(Double invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	public Double getReverseAmount() {
		return reverseAmount;
	}
	public void setReverseAmount(Double reverseAmount) {
		this.reverseAmount = reverseAmount;
	}
	public Double getCgstAmount() {
		return cgstAmount;
	}
	public void setCgstAmount(Double cgstAmount) {
		this.cgstAmount = cgstAmount;
	}
	public Double getSgstAmount() {
		return sgstAmount;
	}
	public void setSgstAmount(Double sgstAmount) {
		this.sgstAmount = sgstAmount;
	}
	public Double getIgstAmount() {
		return igstAmount;
	}
	public void setIgstAmount(Double igstAmount) {
		this.igstAmount = igstAmount;
	}
	public Double getCessAmount() {
		return cessAmount;
	}
	public void setCessAmount(Double cessAmount) {
		this.cessAmount = cessAmount;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getSrlNumber() {
		return srlNumber;
	}
	public void setSrlNumber(String srlNumber) {
		this.srlNumber = srlNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Instant getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Instant creationDate) {
		this.creationDate = creationDate;
	}
	public Double getGovtCgstAmount() {
		return govtCgstAmount;
	}
	public void setGovtCgstAmount(Double govtCgstAmount) {
		this.govtCgstAmount = govtCgstAmount;
	}
	public Double getGovtSgstAmount() {
		return govtSgstAmount;
	}
	public void setGovtSgstAmount(Double govtSgstAmount) {
		this.govtSgstAmount = govtSgstAmount;
	}
	public Double getGovtIgstAmount() {
		return govtIgstAmount;
	}
	public void setGovtIgstAmount(Double govtIgstAmount) {
		this.govtIgstAmount = govtIgstAmount;
	}
    
    
    
}
