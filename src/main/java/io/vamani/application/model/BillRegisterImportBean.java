package io.vamani.application.model;

import io.vamani.application.domain.BillRegisterImportDetails;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class BillRegisterImportBean {
    private Long id;
    private String company;
    private String division;
    private String businessunitcompanycode;
    private String businessunitcode;
    private String billtype;
    private String billnumber;
    private LocalDate billdate;
    private String billdateFormat;
    private String customersuppliertype;
    private String customersuppliercode;
    private String customersuppliername;
    private String currencycode;
    private BigDecimal currencyrate;
    private BigDecimal totalQuantity;
    private BigDecimal totalValue;
    private String remarks;

    private LocalDate submitDate;

    private LocalDate receiveDate;
    private String createdby;
    private Instant createddate;
    private String updatedby;
    private Instant updateddate;
    private Boolean received;

    private Boolean submitted;

    private List<BillRegisterImportDetailsBean> billRegisterDetailsBeans;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getBusinessunitcompanycode() {
        return businessunitcompanycode;
    }

    public void setBusinessunitcompanycode(String businessunitcompanycode) {
        this.businessunitcompanycode = businessunitcompanycode;
    }

    public String getBusinessunitcode() {
        return businessunitcode;
    }

    public void setBusinessunitcode(String businessunitcode) {
        this.businessunitcode = businessunitcode;
    }

    public String getBilltype() {
        return billtype;
    }

    public void setBilltype(String billtype) {
        this.billtype = billtype;
    }

    public String getBillnumber() {
        return billnumber;
    }

    public void setBillnumber(String billnumber) {
        this.billnumber = billnumber;
    }

    public LocalDate getBilldate() {
        return billdate;
    }

    public void setBilldate(LocalDate billdate) {
        this.billdate = billdate;
    }

    public String getBilldateFormat() {
        return billdateFormat;
    }

    public void setBilldateFormat(String billdateFormat) {
        this.billdateFormat = billdateFormat;
    }

    public String getCustomersuppliertype() {
        return customersuppliertype;
    }

    public void setCustomersuppliertype(String customersuppliertype) {
        this.customersuppliertype = customersuppliertype;
    }

    public String getCustomersuppliercode() {
        return customersuppliercode;
    }

    public void setCustomersuppliercode(String customersuppliercode) {
        this.customersuppliercode = customersuppliercode;
    }

    public String getCustomersuppliername() {
        return customersuppliername;
    }

    public void setCustomersuppliername(String customersuppliername) {
        this.customersuppliername = customersuppliername;
    }

    public String getCurrencycode() {
        return currencycode;
    }

    public void setCurrencycode(String currencycode) {
        this.currencycode = currencycode;
    }

    public BigDecimal getCurrencyrate() {
        return currencyrate;
    }

    public void setCurrencyrate(BigDecimal currencyrate) {
        this.currencyrate = currencyrate;
    }

    public BigDecimal getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(BigDecimal totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public LocalDate getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(LocalDate submitDate) {
        this.submitDate = submitDate;
    }

    public LocalDate getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(LocalDate receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Instant getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Instant createddate) {
        this.createddate = createddate;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    public Instant getUpdateddate() {
        return updateddate;
    }

    public void setUpdateddate(Instant updateddate) {
        this.updateddate = updateddate;
    }

    public Boolean getReceived() {
        return received;
    }

    public void setReceived(Boolean received) {
        this.received = received;
    }

    public Boolean getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Boolean submitted) {
        this.submitted = submitted;
    }

    public List<BillRegisterImportDetailsBean> getBillRegisterDetailsBeans() {
        return billRegisterDetailsBeans;
    }

    public void setBillRegisterDetailsBeans(List<BillRegisterImportDetailsBean> billRegisterDetailsBeans) {
        this.billRegisterDetailsBeans = billRegisterDetailsBeans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillRegisterImportBean that = (BillRegisterImportBean) o;
        return Objects.equals(id, that.id) && Objects.equals(company, that.company) && Objects.equals(division, that.division) && Objects.equals(businessunitcompanycode, that.businessunitcompanycode) && Objects.equals(businessunitcode, that.businessunitcode) && Objects.equals(billtype, that.billtype) && Objects.equals(billnumber, that.billnumber) && Objects.equals(billdate, that.billdate) && Objects.equals(billdateFormat, that.billdateFormat) && Objects.equals(customersuppliertype, that.customersuppliertype) && Objects.equals(customersuppliercode, that.customersuppliercode) && Objects.equals(customersuppliername, that.customersuppliername) && Objects.equals(currencycode, that.currencycode) && Objects.equals(currencyrate, that.currencyrate) && Objects.equals(totalQuantity, that.totalQuantity) && Objects.equals(totalValue, that.totalValue) && Objects.equals(remarks, that.remarks) && Objects.equals(submitDate, that.submitDate) && Objects.equals(receiveDate, that.receiveDate) && Objects.equals(createdby, that.createdby) && Objects.equals(createddate, that.createddate) && Objects.equals(updatedby, that.updatedby) && Objects.equals(updateddate, that.updateddate) && Objects.equals(received, that.received) && Objects.equals(submitted, that.submitted) && Objects.equals(billRegisterDetailsBeans, that.billRegisterDetailsBeans);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, company, division, businessunitcompanycode, businessunitcode, billtype, billnumber, billdate, billdateFormat, customersuppliertype, customersuppliercode, customersuppliername, currencycode, currencyrate, totalQuantity, totalValue, remarks, submitDate, receiveDate, createdby, createddate, updatedby, updateddate, received, submitted, billRegisterDetailsBeans);
    }
}
