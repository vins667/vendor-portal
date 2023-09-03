package io.vamani.application.db2.domain;
import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Locale;
import java.util.Objects;

@Entity
@Table(name = "DI_DEBITNOTEENTRYDETAILS")
public class DebitNoteEntry implements Serializable {

    @Id
    @SequenceGenerator(name="debitNoteEntrySeq", sequenceName="di_debitnoteentrydetails_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="debitNoteEntrySeq")
    @Column(name = "ID")
    private Long id;

    @Column(name = "COMPANYCODE", nullable = true, length = 3)
    private String companycode;

    @Column(name = "BUSINESSUNITCODE", nullable = true, length = 3)
    private String businessunitcode;

    @Column(name = "FINANCIALYEARCODE", nullable = true, length = 5)
    private String financialyearcode;

    @Column(name = "DOCUMENTTEMPLATECODE", nullable = true, length = 5)
    private String documenttemplatecode;

    @Column(name = "CODE", nullable = true, length = 15)
    private String code;

    @Column(name = "HSNCODE", nullable = true, length = 15)
    private String hsncode;

    @Column(name = "DESCRIPTION", nullable = true, length = 200)
    private String description;

    @Column(name = "UOM", nullable = true, length = 5)
    private String uom;

    @Column(name = "QTY")
    private Double qty;

    @Column(name = "RATE")
    private Double rate;

    @Column(name = "BASICVALUE")
    private Double basicvalue;

    @Column(name = "GSTRATE")
    private Double gstrate;

    @Column(name = "CGSTVALUE")
    private Double cgstvalue;

    @Column(name = "SGSTVALUE")
    private Double sgstvalue;

    @Column(name = "IGSTVALUE")
    private Double igstvalue;

    @Column(name = "OTHERCHARGES")
    private Double othercharges;

    @Column(name = "TOTALVALUE")
    private Double totalValue;

    @Column(name = "CREATEDBY", nullable = true, length = 200)
    private String createdby;

    @Column(name = "CREATEDDATE")
    private Instant createddate;

    @Column(name = "LOCKEDBY", nullable = true, length = 200)
    private String lockedby;

    @Column(name = "LOCKEDDATE")
    private Instant lockeddate;

    @Column(name = "REMARKS", nullable = true, length = 200)
    private String remarks;

    @Column(name = "ITEMTYPE", nullable = true, length = 3)
    private String itemtype;

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

    public String getBusinessunitcode() {
        return businessunitcode;
    }

    public void setBusinessunitcode(String businessunitcode) {
        this.businessunitcode = businessunitcode;
    }

    public String getFinancialyearcode() {
        return financialyearcode;
    }

    public void setFinancialyearcode(String financialyearcode) {
        this.financialyearcode = financialyearcode;
    }

    public String getDocumenttemplatecode() {
        return documenttemplatecode;
    }

    public void setDocumenttemplatecode(String documenttemplatecode) {
        this.documenttemplatecode = documenttemplatecode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHsncode() {
        return hsncode;
    }

    public void setHsncode(String hsncode) {
        this.hsncode = hsncode != null ? hsncode.toUpperCase() : hsncode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description != null ? description.toUpperCase() : description;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom != null ? uom.toUpperCase() : uom;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getBasicvalue() {
        return basicvalue;
    }

    public void setBasicvalue(Double basicvalue) {
        this.basicvalue = basicvalue;
    }

    public Double getGstrate() {
        return gstrate;
    }

    public void setGstrate(Double gstrate) {
        this.gstrate = gstrate;
    }

    public Double getCgstvalue() {
        return cgstvalue;
    }

    public void setCgstvalue(Double cgstvalue) {
        this.cgstvalue = cgstvalue;
    }

    public Double getSgstvalue() {
        return sgstvalue;
    }

    public void setSgstvalue(Double sgstvalue) {
        this.sgstvalue = sgstvalue;
    }

    public Double getIgstvalue() {
        return igstvalue;
    }

    public void setIgstvalue(Double igstvalue) {
        this.igstvalue = igstvalue;
    }

    public Double getOthercharges() {
        return othercharges;
    }

    public void setOthercharges(Double othercharges) {
        this.othercharges = othercharges;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks != null ? remarks.toUpperCase() : remarks;
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

    public String getLockedby() {
        return lockedby;
    }

    public void setLockedby(String lockedby) {
        this.lockedby = lockedby;
    }

    public Instant getLockeddate() {
        return lockeddate;
    }

    public void setLockeddate(Instant lockeddate) {
        this.lockeddate = lockeddate;
    }

    public String getItemtype() {
        return itemtype;
    }

    public void setItemtype(String itemtype) {
        this.itemtype = itemtype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DebitNoteEntry that = (DebitNoteEntry) o;
        return id == that.id && Double.compare(that.qty, qty) == 0 && Double.compare(that.rate, rate) == 0 && Double.compare(that.basicvalue, basicvalue) == 0 && Double.compare(that.gstrate, gstrate) == 0 && Double.compare(that.cgstvalue, cgstvalue) == 0 && Double.compare(that.sgstvalue, sgstvalue) == 0 && Double.compare(that.igstvalue, igstvalue) == 0 && Double.compare(that.othercharges, othercharges) == 0 && Objects.equals(companycode, that.companycode) && Objects.equals(businessunitcode, that.businessunitcode) && Objects.equals(financialyearcode, that.financialyearcode) && Objects.equals(documenttemplatecode, that.documenttemplatecode) && Objects.equals(code, that.code) && Objects.equals(hsncode, that.hsncode) && Objects.equals(description, that.description) && Objects.equals(uom, that.uom) && Objects.equals(remarks, that.remarks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companycode, businessunitcode, financialyearcode, documenttemplatecode, code, hsncode, description, uom, qty, rate, basicvalue, gstrate, cgstvalue, sgstvalue, igstvalue, othercharges, remarks);
    }
}
