package io.vamani.application.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A BillRegisterImport.
 */
@Entity
@Table(name = "bill_register_import")
public class BillRegisterImport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "billRegisterImportSeq", sequenceName = "bill_register_import_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "billRegisterImportSeq")
    private Long id;

    @NotNull
    @Size(max = 3)
    @Column(name = "company", length = 3, nullable = false)
    private String company;

    @NotNull
    @Size(max = 3)
    @Column(name = "division", length = 3, nullable = false)
    private String division;

    @NotNull
    @Size(max = 3)
    @Column(name = "businessunitcompanycode", length = 3, nullable = false)
    private String businessunitcompanycode;

    @NotNull
    @Size(max = 10)
    @Column(name = "businessunitcode", length = 10, nullable = false)
    private String businessunitcode;

    @NotNull
    @Size(max = 20)
    @Column(name = "billtype", length = 20, nullable = false)
    private String billtype;

    @NotNull
    @Size(max = 50)
    @Column(name = "billnumber", length = 50, nullable = false)
    private String billnumber;

    @Column(name = "billdate")
    private LocalDate billdate;

    @NotNull
    @Size(max = 1)
    @Column(name = "customersuppliertype", length = 1, nullable = false)
    private String customersuppliertype;

    @NotNull
    @Size(max = 20)
    @Column(name = "customersuppliercode", length = 20, nullable = false)
    private String customersuppliercode;

    @NotNull
    @Size(max = 100)
    @Column(name = "customersuppliername", length = 100, nullable = false)
    private String customersuppliername;

    @Size(max = 3)
    @Column(name = "currencycode", length = 3)
    private String currencycode;

    @Column(name = "currencyrate")
    private BigDecimal currencyrate;

    @Column(name = "total_quantity")
    private BigDecimal totalQuantity;

    @Column(name = "total_value")
    private BigDecimal totalValue;

    @Size(max = 2000)
    @Column(name = "remarks", length = 2000)
    private String remarks;

    @Column(name = "submit_date")
    private LocalDate submitDate;

    @Column(name = "receive_date")
    private LocalDate receiveDate;

    @Size(max = 50)
    @Column(name = "createdby", length = 50)
    private String createdby;

    @Column(name = "createddate")
    private Instant createddate;

    @Size(max = 50)
    @Column(name = "updatedby", length = 50)
    private String updatedby;

    @Column(name = "updateddate")
    private Instant updateddate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public BillRegisterImport company(String company) {
        this.company = company;
        return this;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDivision() {
        return division;
    }

    public BillRegisterImport division(String division) {
        this.division = division;
        return this;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getBusinessunitcompanycode() {
        return businessunitcompanycode;
    }

    public BillRegisterImport businessunitcompanycode(String businessunitcompanycode) {
        this.businessunitcompanycode = businessunitcompanycode;
        return this;
    }

    public void setBusinessunitcompanycode(String businessunitcompanycode) {
        this.businessunitcompanycode = businessunitcompanycode;
    }

    public String getBusinessunitcode() {
        return businessunitcode;
    }

    public BillRegisterImport businessunitcode(String businessunitcode) {
        this.businessunitcode = businessunitcode;
        return this;
    }

    public void setBusinessunitcode(String businessunitcode) {
        this.businessunitcode = businessunitcode;
    }

    public String getBilltype() {
        return billtype;
    }

    public BillRegisterImport billtype(String billtype) {
        this.billtype = billtype;
        return this;
    }

    public void setBilltype(String billtype) {
        this.billtype = billtype;
    }

    public String getBillnumber() {
        return billnumber;
    }

    public BillRegisterImport billnumber(String billnumber) {
        this.billnumber = billnumber;
        return this;
    }

    public void setBillnumber(String billnumber) {
        this.billnumber = billnumber;
    }

    public LocalDate getBilldate() {
        return billdate;
    }

    public BillRegisterImport billdate(LocalDate billdate) {
        this.billdate = billdate;
        return this;
    }

    public void setBilldate(LocalDate billdate) {
        this.billdate = billdate;
    }

    public String getCustomersuppliertype() {
        return customersuppliertype;
    }

    public BillRegisterImport customersuppliertype(String customersuppliertype) {
        this.customersuppliertype = customersuppliertype;
        return this;
    }

    public void setCustomersuppliertype(String customersuppliertype) {
        this.customersuppliertype = customersuppliertype;
    }

    public String getCustomersuppliercode() {
        return customersuppliercode;
    }

    public BillRegisterImport customersuppliercode(String customersuppliercode) {
        this.customersuppliercode = customersuppliercode;
        return this;
    }

    public void setCustomersuppliercode(String customersuppliercode) {
        this.customersuppliercode = customersuppliercode;
    }

    public String getCustomersuppliername() {
        return customersuppliername;
    }

    public BillRegisterImport customersuppliername(String customersuppliername) {
        this.customersuppliername = customersuppliername;
        return this;
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

    public String getCreatedby() {
        return createdby;
    }

    public BillRegisterImport createdby(String createdby) {
        this.createdby = createdby;
        return this;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Instant getCreateddate() {
        return createddate;
    }

    public BillRegisterImport createddate(Instant createddate) {
        this.createddate = createddate;
        return this;
    }

    public void setCreateddate(Instant createddate) {
        this.createddate = createddate;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public BillRegisterImport updatedby(String updatedby) {
        this.updatedby = updatedby;
        return this;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    public Instant getUpdateddate() {
        return updateddate;
    }

    public BillRegisterImport updateddate(Instant updateddate) {
        this.updateddate = updateddate;
        return this;
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
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillRegisterImport that = (BillRegisterImport) o;
        return Objects.equals(id, that.id) && Objects.equals(company, that.company) && Objects.equals(division, that.division) && Objects.equals(businessunitcompanycode, that.businessunitcompanycode) && Objects.equals(businessunitcode, that.businessunitcode) && Objects.equals(billtype, that.billtype) && Objects.equals(billnumber, that.billnumber) && Objects.equals(billdate, that.billdate) && Objects.equals(customersuppliertype, that.customersuppliertype) && Objects.equals(customersuppliercode, that.customersuppliercode) && Objects.equals(customersuppliername, that.customersuppliername) && Objects.equals(currencycode, that.currencycode) && Objects.equals(currencyrate, that.currencyrate) && Objects.equals(totalQuantity, that.totalQuantity) && Objects.equals(totalValue, that.totalValue) && Objects.equals(remarks, that.remarks) && Objects.equals(submitDate, that.submitDate) && Objects.equals(receiveDate, that.receiveDate) && Objects.equals(createdby, that.createdby) && Objects.equals(createddate, that.createddate) && Objects.equals(updatedby, that.updatedby) && Objects.equals(updateddate, that.updateddate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, company, division, businessunitcompanycode, businessunitcode, billtype, billnumber, billdate, customersuppliertype, customersuppliercode, customersuppliername, currencycode, currencyrate, totalQuantity, totalValue, remarks, submitDate, receiveDate, createdby, createddate, updatedby, updateddate);
    }
}
