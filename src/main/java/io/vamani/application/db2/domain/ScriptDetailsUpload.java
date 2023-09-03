package io.vamani.application.db2.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A ScriptDetailsUpload.
 */
@Entity
@Table(name = "DI_SCRIPTDETAILSUPLOAD")
public class ScriptDetailsUpload implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="scriptDetailsUploadSeq", sequenceName="DI_SCRIPTDETAILSUPLOAD_SEQ", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="scriptDetailsUploadSeq")
    private Long id;

    @Size(max = 30)
    @Column(name = "S_NO", length = 30, nullable = true)
    private String sNo;

    @Size(max = 50)
    @Column(name = "SHIPPING_BILL_NO", length = 50, nullable = true)
    private String shippingBillNo;

    @Size(max = 50)
    @Column(name = "INVOICE_NO", length = 50, nullable = true)
    private String invoiceNo;

    @Column(name = "SHIPPING_BILL_DATE")
    private LocalDate shippingBillDate;

    @Size(max = 50)
    @Column(name = "SCROLL_NO", length = 50, nullable = true)
    private String scrollNo;

    @Size(max = 50)
    @Column(name = "PORT_CODE", length = 50, nullable = true)
    private String portCode;

    @Column(name = "SANCTIONED_VALUE",  nullable = true)
    private BigDecimal sanctionedValue;

    @Column(name = "FOB_IN_FC",  nullable = true)
    private BigDecimal fobInFc;

    @Column(name = "FOB_IN_INR",  nullable = true)
    private BigDecimal fobInInr;

    @Size(max = 50)
    @Column(name = "BRC_NUMBER", length = 50, nullable = true)
    private String brcNumber;

    @Size(max = 100)
    @Column(name = "CUSTOMER_NAME", length = 100, nullable = true)
    private String customerName;

    @Column(name = "ENTRY_DATE")
    private LocalDate entryDate;

    @Column(name = "BRC_REALISED_VALUE",  nullable = true)
    private BigDecimal brcRealisedValue;

    @Size(max = 30)
    @Column(name = "IFSC_CODE", length = 30, nullable = true)
    private String ifscCode;

    @Size(max = 50)
    @Column(name = "SCRIPT_NO", length = 30, nullable = true)
    private String scriptNo;

    @Column(name = "SCRIPT_AMOUNT",  nullable = true)
    private BigDecimal scriptAmount;

    @Size(max = 50)
    @Column(name = "CREATEDBY", length = 50, nullable = true)
    private String createdby;

    @Column(name = "CREATEDDATE")
    private Instant createddate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getsNo() {
        return sNo;
    }

    public void setsNo(String sNo) {
        this.sNo = sNo;
    }

    public String getShippingBillNo() {
        return shippingBillNo;
    }

    public void setShippingBillNo(String shippingBillNo) {
        this.shippingBillNo = shippingBillNo;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public LocalDate getShippingBillDate() {
        return shippingBillDate;
    }

    public void setShippingBillDate(LocalDate shippingBillDate) {
        this.shippingBillDate = shippingBillDate;
    }

    public String getScrollNo() {
        return scrollNo;
    }

    public void setScrollNo(String scrollNo) {
        this.scrollNo = scrollNo;
    }

    public String getPortCode() {
        return portCode;
    }

    public void setPortCode(String portCode) {
        this.portCode = portCode;
    }

    public BigDecimal getSanctionedValue() {
        return sanctionedValue;
    }

    public void setSanctionedValue(BigDecimal sanctionedValue) {
        this.sanctionedValue = sanctionedValue;
    }

    public BigDecimal getFobInFc() {
        return fobInFc;
    }

    public void setFobInFc(BigDecimal fobInFc) {
        this.fobInFc = fobInFc;
    }

    public BigDecimal getFobInInr() {
        return fobInInr;
    }

    public void setFobInInr(BigDecimal fobInInr) {
        this.fobInInr = fobInInr;
    }

    public String getBrcNumber() {
        return brcNumber;
    }

    public void setBrcNumber(String brcNumber) {
        this.brcNumber = brcNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public BigDecimal getBrcRealisedValue() {
        return brcRealisedValue;
    }

    public void setBrcRealisedValue(BigDecimal brcRealisedValue) {
        this.brcRealisedValue = brcRealisedValue;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getScriptNo() {
        return scriptNo;
    }

    public void setScriptNo(String scriptNo) {
        this.scriptNo = scriptNo;
    }

    public BigDecimal getScriptAmount() {
        return scriptAmount;
    }

    public void setScriptAmount(BigDecimal scriptAmount) {
        this.scriptAmount = scriptAmount;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScriptDetailsUpload that = (ScriptDetailsUpload) o;
        return Objects.equals(id, that.id) && Objects.equals(sNo, that.sNo) && Objects.equals(shippingBillNo, that.shippingBillNo) && Objects.equals(invoiceNo, that.invoiceNo) && Objects.equals(shippingBillDate, that.shippingBillDate) && Objects.equals(scrollNo, that.scrollNo) && Objects.equals(portCode, that.portCode) && Objects.equals(sanctionedValue, that.sanctionedValue) && Objects.equals(fobInFc, that.fobInFc) && Objects.equals(fobInInr, that.fobInInr) && Objects.equals(brcNumber, that.brcNumber) && Objects.equals(customerName, that.customerName) && Objects.equals(entryDate, that.entryDate) && Objects.equals(brcRealisedValue, that.brcRealisedValue) && Objects.equals(ifscCode, that.ifscCode) && Objects.equals(scriptNo, that.scriptNo) && Objects.equals(scriptAmount, that.scriptAmount) && Objects.equals(createdby, that.createdby) && Objects.equals(createddate, that.createddate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sNo, shippingBillNo, invoiceNo, shippingBillDate, scrollNo, portCode, sanctionedValue, fobInFc, fobInInr, brcNumber, customerName, entryDate, brcRealisedValue, ifscCode, scriptNo, scriptAmount, createdby, createddate);
    }
}
