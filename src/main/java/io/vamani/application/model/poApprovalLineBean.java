package io.vamani.application.model;

import java.math.BigDecimal;

public class poApprovalLineBean {


	private String currencycode; 
	private String orderline;
	private String itemcode; 
	private String hsncode; 
	private String itemdesc; 
	private String uom; 
	private BigDecimal qty; 
	private BigDecimal price; 
	private BigDecimal netvalue; 
	private BigDecimal grossvaluewoheader; 
	private BigDecimal igsttaxpers; 
	private BigDecimal igstvalue; 
	private BigDecimal sgsttaxpers; 
	private BigDecimal sgstvalue; 
	private BigDecimal cgstpers; 
	private BigDecimal cgstvalue; 
	private BigDecimal freight; 
	private BigDecimal discount;
	public String getCurrencycode() {
		return currencycode;
	}
	public void setCurrencycode(String currencycode) {
		this.currencycode = currencycode;
	}
	public String getOrderline() {
		return orderline;
	}
	public void setOrderline(String orderline) {
		this.orderline = orderline;
	}
	public String getItemcode() {
		return itemcode;
	}
	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}
	public String getHsncode() {
		return hsncode;
	}
	public void setHsncode(String hsncode) {
		this.hsncode = hsncode;
	}
	
	public BigDecimal getGrossvaluewoheader() {
		return grossvaluewoheader;
	}
	public void setGrossvaluewoheader(BigDecimal grossvaluewoheader) {
		this.grossvaluewoheader = grossvaluewoheader;
	}
	public String getItemdesc() {
		return itemdesc;
	}
	public void setItemdesc(String itemdesc) {
		this.itemdesc = itemdesc;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public BigDecimal getQty() {
		return qty;
	}
	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getNetvalue() {
		return netvalue;
	}
	public void setNetvalue(BigDecimal netvalue) {
		this.netvalue = netvalue;
	}
	public BigDecimal getIgsttaxpers() {
		return igsttaxpers;
	}
	public void setIgsttaxpers(BigDecimal igsttaxpers) {
		this.igsttaxpers = igsttaxpers;
	}
	public BigDecimal getIgstvalue() {
		return igstvalue;
	}
	public void setIgstvalue(BigDecimal igstvalue) {
		this.igstvalue = igstvalue;
	}
	public BigDecimal getSgsttaxpers() {
		return sgsttaxpers;
	}
	public void setSgsttaxpers(BigDecimal sgsttaxpers) {
		this.sgsttaxpers = sgsttaxpers;
	}
	public BigDecimal getSgstvalue() {
		return sgstvalue;
	}
	public void setSgstvalue(BigDecimal sgstvalue) {
		this.sgstvalue = sgstvalue;
	}
	public BigDecimal getCgstpers() {
		return cgstpers;
	}
	public void setCgstpers(BigDecimal cgstpers) {
		this.cgstpers = cgstpers;
	}
	public BigDecimal getCgstvalue() {
		return cgstvalue;
	}
	public void setCgstvalue(BigDecimal cgstvalue) {
		this.cgstvalue = cgstvalue;
	}
	public BigDecimal getFreight() {
		return freight;
	}
	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	
	
}
