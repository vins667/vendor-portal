package io.vamani.application.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

/**
 * A BillRegisterMaster.
 */
@Entity
@Table(name = "bill_register_master")
public class BillRegisterMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="billRegisterMasterSeq", sequenceName="bill_register_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="billRegisterMasterSeq")
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

    @Size(max = 2000)
    @Column(name = "remarks", length = 2000)
    private String remarks;

    @Column(name = "total_quantity")
    private BigDecimal totalQuantity;

    @Column(name = "total_value")
    private BigDecimal totalValue;

    @Column(name = "submit_date")
    private LocalDate submitDate;

    @Column(name = "receive_date")
    private LocalDate receiveDate;

    @Column(name = "query_flag")
    private Boolean queryFlag;

    @Size(max = 2000)
    @Column(name = "query_remarks", length = 2000)
    private String queryRemarks;

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

    public BillRegisterMaster company(String company) {
        this.company = company;
        return this;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDivision() {
        return division;
    }

    public BillRegisterMaster division(String division) {
        this.division = division;
        return this;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getBusinessunitcompanycode() {
        return businessunitcompanycode;
    }

    public BillRegisterMaster businessunitcompanycode(String businessunitcompanycode) {
        this.businessunitcompanycode = businessunitcompanycode;
        return this;
    }

    public void setBusinessunitcompanycode(String businessunitcompanycode) {
        this.businessunitcompanycode = businessunitcompanycode;
    }

    public String getBusinessunitcode() {
        return businessunitcode;
    }

    public BillRegisterMaster businessunitcode(String businessunitcode) {
        this.businessunitcode = businessunitcode;
        return this;
    }

    public void setBusinessunitcode(String businessunitcode) {
        this.businessunitcode = businessunitcode;
    }

    public String getBilltype() {
        return billtype;
    }

    public BillRegisterMaster billtype(String billtype) {
        this.billtype = billtype;
        return this;
    }

    public void setBilltype(String billtype) {
        this.billtype = billtype;
    }

    public String getBillnumber() {
        return billnumber;
    }

    public BillRegisterMaster billnumber(String billnumber) {
        this.billnumber = billnumber;
        return this;
    }

    public void setBillnumber(String billnumber) {
        this.billnumber = billnumber;
    }

    public LocalDate getBilldate() {
        return billdate;
    }

    public BillRegisterMaster billdate(LocalDate billdate) {
        this.billdate = billdate;
        return this;
    }

    public void setBilldate(LocalDate billdate) {
        this.billdate = billdate;
    }

    public String getCustomersuppliertype() {
        return customersuppliertype;
    }

    public BillRegisterMaster customersuppliertype(String customersuppliertype) {
        this.customersuppliertype = customersuppliertype;
        return this;
    }

    public void setCustomersuppliertype(String customersuppliertype) {
        this.customersuppliertype = customersuppliertype;
    }

    public String getCustomersuppliercode() {
        return customersuppliercode;
    }

    public BillRegisterMaster customersuppliercode(String customersuppliercode) {
        this.customersuppliercode = customersuppliercode;
        return this;
    }

    public void setCustomersuppliercode(String customersuppliercode) {
        this.customersuppliercode = customersuppliercode;
    }

    public String getCustomersuppliername() {
        return customersuppliername;
    }

    public BillRegisterMaster customersuppliername(String customersuppliername) {
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

    public String getCreatedby() {
        return createdby;
    }

    public BillRegisterMaster createdby(String createdby) {
        this.createdby = createdby;
        return this;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Instant getCreateddate() {
        return createddate;
    }

    public BillRegisterMaster createddate(Instant createddate) {
        this.createddate = createddate;
        return this;
    }

    public void setCreateddate(Instant createddate) {
        this.createddate = createddate;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public BillRegisterMaster updatedby(String updatedby) {
        this.updatedby = updatedby;
        return this;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    public Instant getUpdateddate() {
        return updateddate;
    }

    public BillRegisterMaster updateddate(Instant updateddate) {
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
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BillRegisterMaster)) {
            return false;
        }
        return id != null && id.equals(((BillRegisterMaster) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "BillRegisterMaster{" +
            "id=" + getId() +
            ", billtype='" + getBilltype() + "'" +
            ", billnumber='" + getBillnumber() + "'" +
            ", billdate='" + getBilldate() + "'" +
            ", customersuppliertype='" + getCustomersuppliertype() + "'" +
            ", customersuppliercode='" + getCustomersuppliercode() + "'" +
            ", customersuppliername='" + getCustomersuppliername() + "'" +
            ", createdby='" + getCreatedby() + "'" +
            ", createddate='" + getCreateddate() + "'" +
            ", updatedby='" + getUpdatedby() + "'" +
            ", updateddate='" + getUpdateddate() + "'" +
            "}";
    }
}
