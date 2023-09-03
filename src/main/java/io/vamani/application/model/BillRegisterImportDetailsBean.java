package io.vamani.application.model;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

public class BillRegisterImportDetailsBean {
    private Long id;
    private String companycode;
    private String countercode;
    private String code;
    private LocalDate orderdate;
    private String projectcode;
    private String summarizeddescription;
    private String userprimaryuomcode;
    private BigDecimal quantity;
    private BigDecimal price;
    private BigDecimal grossvalue;
    private LocalDate submitdate;
    private LocalDate receiveDate;
    private String shipmentMode;

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

    public String getCountercode() {
        return countercode;
    }

    public void setCountercode(String countercode) {
        this.countercode = countercode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(LocalDate orderdate) {
        this.orderdate = orderdate;
    }

    public String getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    public String getSummarizeddescription() {
        return summarizeddescription;
    }

    public void setSummarizeddescription(String summarizeddescription) {
        this.summarizeddescription = summarizeddescription;
    }

    public String getUserprimaryuomcode() {
        return userprimaryuomcode;
    }

    public void setUserprimaryuomcode(String userprimaryuomcode) {
        this.userprimaryuomcode = userprimaryuomcode;
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

    public LocalDate getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(LocalDate receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getShipmentMode() {
        return shipmentMode;
    }

    public void setShipmentMode(String shipmentMode) {
        this.shipmentMode = shipmentMode;
    }
}
