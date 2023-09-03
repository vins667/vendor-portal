package io.vamani.application.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A GstReconciliation.
 */
@Entity
@Table(name = "gst_reconciliation")
public class GstReconciliation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 5)
    @Column(name = "unit_code", length = 5, nullable = false)
    private String unitCode;

    @NotNull
    @Size(max = 20)
    @Column(name = "gstin", length = 20, nullable = false)
    private String gstin;

    @NotNull
    @Size(max = 5)
    @Column(name = "invoice_type", length = 5, nullable = false)
    private String invoiceType;

    @NotNull
    @Size(max = 20)
    @Column(name = "invoice_no", length = 20, nullable = false)
    private String invoiceNo;

    @Column(name = "invoice_date")
    private Instant invoiceDate;

    @NotNull
    @Size(max = 200)
    @Column(name = "supplier_name", length = 200, nullable = false)
    private String supplierName;

    @Size(max = 40)
    @Column(name = "state", length = 40)
    private String state;

    @Column(name = "invoice_amount")
    private Double invoiceAmount;

    @Column(name = "reverse_amount")
    private Double reverseAmount;

    @Column(name = "cgst_amount")
    private Double cgstAmount;

    @Column(name = "sgst_amount")
    private Double sgstAmount;

    @Column(name = "igst_amount")
    private Double igstAmount;

    @Column(name = "cess_amount")
    private Double cessAmount;

    @Size(max = 20)
    @Column(name = "location", length = 20)
    private String location;

    @Column(name = "srl_number")
    private String srlNumber;

    @Size(max = 5)
    @Column(name = "status", length = 5)
    private String status;

    @Column(name = "creation_date")
    private Instant creationDate;

    @Column(name = "govt_invoice_amount")
    private Double govtInvoiceAmount;

    @Column(name = "govt_cgst_amount")
    private Double govtCgstAmount;

    @Column(name = "govt_sgst_amount")
    private Double govtSgstAmount;

    @Column(name = "govt_igst_amount")
    private Double govtIgstAmount;

    @Column(name = "difference_amt")
    private Double differenceAmt;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public GstReconciliation unitCode(String unitCode) {
        this.unitCode = unitCode;
        return this;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getGstin() {
        return gstin;
    }

    public GstReconciliation gstin(String gstin) {
        this.gstin = gstin;
        return this;
    }

    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public GstReconciliation invoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
        return this;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public GstReconciliation invoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
        return this;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Instant getInvoiceDate() {
        return invoiceDate;
    }

    public GstReconciliation invoiceDate(Instant invoiceDate) {
        this.invoiceDate = invoiceDate;
        return this;
    }

    public void setInvoiceDate(Instant invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public GstReconciliation supplierName(String supplierName) {
        this.supplierName = supplierName;
        return this;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getState() {
        return state;
    }

    public GstReconciliation state(String state) {
        this.state = state;
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getInvoiceAmount() {
        return invoiceAmount;
    }

    public GstReconciliation invoiceAmount(Double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
        return this;
    }

    public void setInvoiceAmount(Double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public Double getReverseAmount() {
        return reverseAmount;
    }

    public GstReconciliation reverseAmount(Double reverseAmount) {
        this.reverseAmount = reverseAmount;
        return this;
    }

    public void setReverseAmount(Double reverseAmount) {
        this.reverseAmount = reverseAmount;
    }

    public Double getCgstAmount() {
        return cgstAmount;
    }

    public GstReconciliation cgstAmount(Double cgstAmount) {
        this.cgstAmount = cgstAmount;
        return this;
    }

    public void setCgstAmount(Double cgstAmount) {
        this.cgstAmount = cgstAmount;
    }

    public Double getSgstAmount() {
        return sgstAmount;
    }

    public GstReconciliation sgstAmount(Double sgstAmount) {
        this.sgstAmount = sgstAmount;
        return this;
    }

    public void setSgstAmount(Double sgstAmount) {
        this.sgstAmount = sgstAmount;
    }

    public Double getIgstAmount() {
        return igstAmount;
    }

    public GstReconciliation igstAmount(Double igstAmount) {
        this.igstAmount = igstAmount;
        return this;
    }

    public void setIgstAmount(Double igstAmount) {
        this.igstAmount = igstAmount;
    }

    public Double getCessAmount() {
        return cessAmount;
    }

    public GstReconciliation cessAmount(Double cessAmount) {
        this.cessAmount = cessAmount;
        return this;
    }

    public void setCessAmount(Double cessAmount) {
        this.cessAmount = cessAmount;
    }

    public String getLocation() {
        return location;
    }

    public GstReconciliation location(String location) {
        this.location = location;
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSrlNumber() {
        return srlNumber;
    }

    public GstReconciliation srlNumber(String srlNumber) {
        this.srlNumber = srlNumber;
        return this;
    }

    public void setSrlNumber(String srlNumber) {
        this.srlNumber = srlNumber;
    }

    public String getStatus() {
        return status;
    }

    public GstReconciliation status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public GstReconciliation creationDate(Instant creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Double getGovtInvoiceAmount() {
        return govtInvoiceAmount;
    }

    public GstReconciliation govtInvoiceAmount(Double govtInvoiceAmount) {
        this.govtInvoiceAmount = govtInvoiceAmount;
        return this;
    }

    public void setGovtInvoiceAmount(Double govtInvoiceAmount) {
        this.govtInvoiceAmount = govtInvoiceAmount;
    }

    public Double getGovtCgstAmount() {
        return govtCgstAmount;
    }

    public GstReconciliation govtCgstAmount(Double govtCgstAmount) {
        this.govtCgstAmount = govtCgstAmount;
        return this;
    }

    public void setGovtCgstAmount(Double govtCgstAmount) {
        this.govtCgstAmount = govtCgstAmount;
    }

    public Double getGovtSgstAmount() {
        return govtSgstAmount;
    }

    public GstReconciliation govtSgstAmount(Double govtSgstAmount) {
        this.govtSgstAmount = govtSgstAmount;
        return this;
    }

    public void setGovtSgstAmount(Double govtSgstAmount) {
        this.govtSgstAmount = govtSgstAmount;
    }

    public Double getGovtIgstAmount() {
        return govtIgstAmount;
    }

    public GstReconciliation govtIgstAmount(Double govtIgstAmount) {
        this.govtIgstAmount = govtIgstAmount;
        return this;
    }

    public void setGovtIgstAmount(Double govtIgstAmount) {
        this.govtIgstAmount = govtIgstAmount;
    }

    public Double getDifferenceAmt() {
        return differenceAmt;
    }

    public GstReconciliation differenceAmt(Double differenceAmt) {
        this.differenceAmt = differenceAmt;
        return this;
    }

    public void setDifferenceAmt(Double differenceAmt) {
        this.differenceAmt = differenceAmt;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GstReconciliation)) {
            return false;
        }
        return id != null && id.equals(((GstReconciliation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "GstReconciliation{" +
            "id=" + getId() +
            ", unitCode='" + getUnitCode() + "'" +
            ", gstin='" + getGstin() + "'" +
            ", invoiceType='" + getInvoiceType() + "'" +
            ", invoiceNo='" + getInvoiceNo() + "'" +
            ", invoiceDate='" + getInvoiceDate() + "'" +
            ", supplierName='" + getSupplierName() + "'" +
            ", state='" + getState() + "'" +
            ", invoiceAmount=" + getInvoiceAmount() +
            ", reverseAmount=" + getReverseAmount() +
            ", cgstAmount=" + getCgstAmount() +
            ", sgstAmount=" + getSgstAmount() +
            ", igstAmount=" + getIgstAmount() +
            ", cessAmount=" + getCessAmount() +
            ", location='" + getLocation() + "'" +
            ", srlNumber='" + getSrlNumber() + "'" +
            ", status='" + getStatus() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", govtInvoiceAmount=" + getGovtInvoiceAmount() +
            ", govtCgstAmount=" + getGovtCgstAmount() +
            ", govtSgstAmount=" + getGovtSgstAmount() +
            ", govtIgstAmount=" + getGovtIgstAmount() +
            ", differenceAmt=" + getDifferenceAmt() +
            "}";
    }
}
