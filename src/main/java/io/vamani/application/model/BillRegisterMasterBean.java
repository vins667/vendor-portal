package io.vamani.application.model;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public class BillRegisterMasterBean {
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
    private String remarks;
    private BigDecimal totalQuantity;
    private BigDecimal totalValue;

    private LocalDate submitDate;

    private LocalDate receiveDate;
    private Boolean queryFlag;
    private String queryRemarks;
    private String createdby;
    private Instant createddate;
    private String updatedby;
    private Instant updateddate;
    private Boolean received;

    private Boolean submitted;

    private List<BillRegisterDetailsBean> billRegisterDetailsBeans;

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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public Boolean getQueryFlag() {
        return queryFlag;
    }

    public void setQueryFlag(Boolean queryFlag) {
        this.queryFlag = queryFlag;
    }

    public String getQueryRemarks() {
        return queryRemarks;
    }

    public void setQueryRemarks(String queryRemarks) {
        this.queryRemarks = queryRemarks;
    }

    public String getBilldateFormat() {
        return billdateFormat;
    }

    public void setBilldateFormat(String billdateFormat) {
        this.billdateFormat = billdateFormat;
    }

    public Boolean getReceived() {
        return received;
    }

    public void setReceived(Boolean received) {
        this.received = received;
    }

    public List<BillRegisterDetailsBean> getBillRegisterDetailsBeans() {
        return billRegisterDetailsBeans;
    }

    public void setBillRegisterDetailsBeans(List<BillRegisterDetailsBean> billRegisterDetailsBeans) {
        this.billRegisterDetailsBeans = billRegisterDetailsBeans;
    }

    public Boolean getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Boolean submitted) {
        this.submitted = submitted;
    }
}
