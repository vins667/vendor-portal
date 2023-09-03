package io.vamani.application.model;

import io.vamani.application.domain.BillRegisterDetails;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public class BillRegisterBean {
    private Long id;

    private String companycode;

    private String divisioncode;

    private String invoicetypecode;

    private String code;

    private LocalDate invoicedate;

    private String style;

    private String customercode;

    private String customername;

    private String createdby;

    private Instant createddate;

    private String updatedby;

    private Instant updateddate;

    private List<BillRegisterDetails> billRegisterDetailsCHA;

    private List<BillRegisterDetails> billRegisterDetailsFOR;

    private List<BillRegisterDetails> billRegisterDetailsTRA;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getInvoicedate() {
        return invoicedate;
    }

    public void setInvoicedate(LocalDate invoicedate) {
        this.invoicedate = invoicedate;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
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

    public List<BillRegisterDetails> getBillRegisterDetailsCHA() {
        return billRegisterDetailsCHA;
    }

    public void setBillRegisterDetailsCHA(List<BillRegisterDetails> billRegisterDetailsCHA) {
        this.billRegisterDetailsCHA = billRegisterDetailsCHA;
    }

    public List<BillRegisterDetails> getBillRegisterDetailsFOR() {
        return billRegisterDetailsFOR;
    }

    public void setBillRegisterDetailsFOR(List<BillRegisterDetails> billRegisterDetailsFOR) {
        this.billRegisterDetailsFOR = billRegisterDetailsFOR;
    }

    public List<BillRegisterDetails> getBillRegisterDetailsTRA() {
        return billRegisterDetailsTRA;
    }

    public void setBillRegisterDetailsTRA(List<BillRegisterDetails> billRegisterDetailsTRA) {
        this.billRegisterDetailsTRA = billRegisterDetailsTRA;
    }
}
