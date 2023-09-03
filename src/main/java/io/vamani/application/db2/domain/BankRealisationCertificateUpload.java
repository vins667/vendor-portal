package io.vamani.application.db2.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

/**
 * A BankRealisationCertificateUpload.
 */
@Entity
@Table(name = "DI_BANKREALISATIONCERTIFICATEUPLOAD")
public class BankRealisationCertificateUpload implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="bankRealisationCertificateUploadSeq", sequenceName="DI_BANKREALISATIONCERTIFICATEUPLOAD_SEQ", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="bankRealisationCertificateUploadSeq")
    private Long id;

    @Size(max = 100)
    @Column(name = "SB_NO", length = 100, nullable = true)
    private String sbNo;

    @Column(name = "SB_DATE")
    private LocalDate sbDate;


    @Size(max = 50)
    @Column(name = "BRC_NO", length = 50, nullable = true)
    private String brcNo;

    @Column(name = "BRC_DATE")
    private LocalDate brcDate;


    @Size(max = 50)
    @Column(name = "PORT_CODE", length = 50, nullable = true)
    private String portCode;


    @Column(name = "FOB", nullable = true)
    private BigDecimal fob;


    @Size(max = 50)
    @Column(name = "CURRENCY", length = 50, nullable = true)
    private String currency;


    @Column(name = "REALISATION_DATE")
    private LocalDate realisationDate;


    @Size(max = 50)
    @Column(name = "STATUS", length = 50, nullable = true)
    private String status;

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

    public String getSbNo() {
        return sbNo;
    }

    public BankRealisationCertificateUpload sbNo(String sbNo) {
        this.sbNo = sbNo;
        return this;
    }

    public void setSbNo(String sbNo) {
        this.sbNo = sbNo;
    }

    public LocalDate getSbDate() {
        return sbDate;
    }

    public BankRealisationCertificateUpload sbDate(LocalDate sbDate) {
        this.sbDate = sbDate;
        return this;
    }

    public void setSbDate(LocalDate sbDate) {
        this.sbDate = sbDate;
    }

    public String getBrcNo() {
        return brcNo;
    }

    public BankRealisationCertificateUpload brcNo(String brcNo) {
        this.brcNo = brcNo;
        return this;
    }

    public void setBrcNo(String brcNo) {
        this.brcNo = brcNo;
    }

    public LocalDate getBrcDate() {
        return brcDate;
    }

    public BankRealisationCertificateUpload brcDate(LocalDate brcDate) {
        this.brcDate = brcDate;
        return this;
    }

    public void setBrcDate(LocalDate brcDate) {
        this.brcDate = brcDate;
    }

    public String getPortCode() {
        return portCode;
    }

    public BankRealisationCertificateUpload portCode(String portCode) {
        this.portCode = portCode;
        return this;
    }

    public void setPortCode(String portCode) {
        this.portCode = portCode;
    }

    public BigDecimal getFob() {
        return fob;
    }

    public BankRealisationCertificateUpload fob(BigDecimal fob) {
        this.fob = fob;
        return this;
    }

    public void setFob(BigDecimal fob) {
        this.fob = fob;
    }

    public String getCurrency() {
        return currency;
    }

    public BankRealisationCertificateUpload currency(String currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDate getRealisationDate() {
        return realisationDate;
    }

    public BankRealisationCertificateUpload realisationDate(LocalDate realisationDate) {
        this.realisationDate = realisationDate;
        return this;
    }

    public void setRealisationDate(LocalDate realisationDate) {
        this.realisationDate = realisationDate;
    }

    public String getStatus() {
        return status;
    }

    public BankRealisationCertificateUpload status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
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
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BankRealisationCertificateUpload)) {
            return false;
        }
        return id != null && id.equals(((BankRealisationCertificateUpload) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "BankRealisationCertificateUpload{" +
            "id=" + getId() +
            ", sbNo='" + getSbNo() + "'" +
            ", sbDate='" + getSbDate() + "'" +
            ", brcNo='" + getBrcNo() + "'" +
            ", brcDate='" + getBrcDate() + "'" +
            ", portCode='" + getPortCode() + "'" +
            ", fob='" + getFob() + "'" +
            ", currency='" + getCurrency() + "'" +
            ", realisationDate='" + getRealisationDate() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
