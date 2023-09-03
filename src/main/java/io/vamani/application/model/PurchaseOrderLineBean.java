package io.vamani.application.model;

import java.math.BigDecimal;

public class PurchaseOrderLineBean {

	private String purchaseordercompanycode;

	private String purchaseordercountercode;

	private String purchaseordercode;

	private String projectcode;

	private String salesordercode;

	private double orderline;

	private String itemcode;

	private String itemdesc;

	private BigDecimal exchangerate;

	private double tariffcode;

	private BigDecimal grossvaluewoheader;

	private String uom;

	private BigDecimal qty;

	private BigDecimal price;

	private BigDecimal netvalue;

	private BigDecimal igsttaxpers;

	private BigDecimal igstvalue;

	private BigDecimal sgsttaxpers;

	private BigDecimal sgstvalue;

	private BigDecimal cgstpers;

	private BigDecimal cgstvalue;

	private BigDecimal freight;

	private BigDecimal discount;

	public double getOrderline() {
		return orderline;
	}

	public void setOrderline(double orderline) {
		this.orderline = orderline;
	}

	public double getTariffcode() {
		return tariffcode;
	}

	public void setTariffcode(double tariffcode) {
		this.tariffcode = tariffcode;
	}

	public String getPurchaseordercompanycode() {
		return purchaseordercompanycode;
	}

	public void setPurchaseordercompanycode(String purchaseordercompanycode) {
		this.purchaseordercompanycode = purchaseordercompanycode;
	}

	public BigDecimal getExchangerate() {
		return exchangerate;
	}

	public void setExchangerate(BigDecimal exchangerate) {
		this.exchangerate = exchangerate;
	}

	public String getPurchaseordercountercode() {
		return purchaseordercountercode;
	}

	public void setPurchaseordercountercode(String purchaseordercountercode) {
		this.purchaseordercountercode = purchaseordercountercode;
	}

	public String getPurchaseordercode() {
		return purchaseordercode;
	}

	public void setPurchaseordercode(String purchaseordercode) {
		this.purchaseordercode = purchaseordercode;
	}

	public String getItemcode() {
		return itemcode;
	}

	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}

	public String getItemdesc() {
		return itemdesc;
	}

	public void setItemdesc(String itemdesc) {
		this.itemdesc = itemdesc;
	}

	public BigDecimal getGrossvaluewoheader() {
		return grossvaluewoheader;
	}

	public void setGrossvaluewoheader(BigDecimal grossvaluewoheader) {
		this.grossvaluewoheader = grossvaluewoheader;
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

	public String getProjectcode() {
		return projectcode;
	}

	public void setProjectcode(String projectcode) {
		this.projectcode = projectcode;
	}

	public String getSalesordercode() {
		return salesordercode;
	}

	public void setSalesordercode(String salesordercode) {
		this.salesordercode = salesordercode;
	}



}
