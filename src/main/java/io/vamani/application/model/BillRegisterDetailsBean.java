package io.vamani.application.model;

import io.vamani.application.domain.BillRegisterMaster;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;

public class BillRegisterDetailsBean {
    private Long id;
    private String companycode;
    private String divisioncode;
    private String invoicetypecode;
    private LocalDate invoicedate;
    private String billtype;
    private String code;
    private String style;
    private String customercode;
    private String customername;
    private BigDecimal quantity;
    private BigDecimal price;
    private BigDecimal grossvalue;
    private LocalDate submitdate;
    private String status;
    private String shipmentMode;
    private BigDecimal perpcsrate;

    private LocalDate receiveDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    public String getDivisioncode() {
        return divisioncode;
    }

    public void setDivisioncode(String divisioncode) {
        this.divisioncode = divisioncode;
    }

    public String getInvoicetypecode() {
        return invoicetypecode;
    }

    public void setInvoicetypecode(String invoicetypecode) {
        this.invoicetypecode = invoicetypecode;
    }

    public LocalDate getInvoicedate() {
        return invoicedate;
    }

    public void setInvoicedate(LocalDate invoicedate) {
        this.invoicedate = invoicedate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
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

    public BigDecimal getGrossvalue() {
        return grossvalue;
    }

    public void setGrossvalue(BigDecimal grossvalue) {
        this.grossvalue = grossvalue;
    }

    public LocalDate getSubmitdate() {
        return submitdate;
    }

    public void setSubmitdate(LocalDate submitdate) {
        this.submitdate = submitdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShipmentMode() {
        return shipmentMode;
    }

    public void setShipmentMode(String shipmentMode) {
        this.shipmentMode = shipmentMode;
    }

    public BigDecimal getPerpcsrate() {
        return perpcsrate;
    }

    public void setPerpcsrate(BigDecimal perpcsrate) {
        this.perpcsrate = perpcsrate;
    }

    public String getCustomercode() {
        return customercode;
    }

    public void setCustomercode(String customercode) {
        this.customercode = customercode;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public LocalDate getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(LocalDate receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getBilltype() {
        return billtype;
    }

    public void setBilltype(String billtype) {
        this.billtype = billtype;
    }
}
