package io.vamani.application.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class BillRegisterImportReportBean implements Serializable {
    private Long id;
	private String billdateFormat;
	private String summarizeddescription;
	private String customersuppliername;
	private String projectcode;
	private String billtype;
	private String code;
	private String billnumber;
	private String orderDateFormat;
	private BigDecimal grossvalue;
	private BigDecimal quantity;
	private BigDecimal price;
	private String fxsuppliername;
	private String remarks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBilldateFormat() {
        return billdateFormat;
    }

    public void setBilldateFormat(String billdateFormat) {
        this.billdateFormat = billdateFormat;
    }

    public String getSummarizeddescription() {
        return summarizeddescription;
    }

    public void setSummarizeddescription(String summarizeddescription) {
        this.summarizeddescription = summarizeddescription;
    }

    public String getCustomersuppliername() {
        return customersuppliername;
    }

    public void setCustomersuppliername(String customersuppliername) {
        this.customersuppliername = customersuppliername;
    }

    public String getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    public String getBilltype() {
        return billtype;
    }

    public void setBilltype(String billtype) {
        this.billtype = billtype;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBillnumber() {
        return billnumber;
    }

    public void setBillnumber(String billnumber) {
        this.billnumber = billnumber;
    }

    public String getOrderDateFormat() {
        return orderDateFormat;
    }

    public void setOrderDateFormat(String orderDateFormat) {
        this.orderDateFormat = orderDateFormat;
    }

    public BigDecimal getGrossvalue() {
        return grossvalue;
    }

    public void setGrossvalue(BigDecimal grossvalue) {
        this.grossvalue = grossvalue;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getFxsuppliername() {
        return fxsuppliername;
    }

    public void setFxsuppliername(String fxsuppliername) {
        this.fxsuppliername = fxsuppliername;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
