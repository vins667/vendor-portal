package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * A TdsDeclaration.
 */
@Entity
@Table(name = "tds_declaration")
public class TdsDeclaration implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="tdsDeclarationSeq", sequenceName="tds_declaration_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="tdsDeclarationSeq")
    private Long id;

    @Size(max = 50)
    @Column(name = "card_no", length = 50)
    private String cardNo;

    @Column(name = "amount")
    private Double  amount;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Size(max = 50)
    @Column(name = "last_updated_by", length = 50)
    private String lastUpdatedBy;

    @Column(name = "last_updated_date")
    private Instant lastUpdatedDate;

    @Size(max = 100)
    @Column(name = "month_rent", length = 100)
    private String monthRent;

    @Size(max = 100)
    @Column(name = "land_loard_name", length = 100)
    private String landLoardName;

    @Column(name = "land_loard_pan_no")
    private String landLoardPanNo;

    @Size(max = 500)
    @Column(name = "land_loard_address", length = 500)
    private String landLoardAddress;

    @Column(name = "temp_lock", length = 1)
    private String tempLock;

    @Column(name = "temp_lock_time")
    private Instant tempLockTime;

    @Column(name = "year_lock", length = 1)
    private String yearLock;

    @Column(name = "year_lock_time")
    private Instant yearLockTime;

    @Column(name = "regime_type", length = 3)
    private String regimeType;

    @Column(name = "previous_employer_amount")
    private BigDecimal previousEmployerAmount;

    @Column(name = "previous_employer_tds_deduction")
    private BigDecimal previousEmployerTdsDeduction;

    @Column(name = "incentive_amount")
    private BigDecimal incentiveAmount;

    @ManyToOne
    @JoinColumn(name = "tds_group_details_id")
    @JsonIgnoreProperties("")
    private TdsGroupDetails tdsGroupDetails;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNo() {
        return cardNo;
    }

    public TdsDeclaration cardNo(String cardNo) {
        this.cardNo = cardNo;
        return this;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Double getAmount() {
        return amount;
    }

    public TdsDeclaration amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public TdsDeclaration createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public TdsDeclaration createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public TdsDeclaration lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public TdsDeclaration lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getMonthRent() {
        return monthRent;
    }

    public TdsDeclaration monthRent(String monthRent) {
        this.monthRent = monthRent;
        return this;
    }

    public void setMonthRent(String monthRent) {
        this.monthRent = monthRent;
    }

    public String getLandLoardName() {
        return landLoardName;
    }

    public TdsDeclaration landLoardName(String landLoardName) {
        this.landLoardName = landLoardName;
        return this;
    }

    public void setLandLoardName(String landLoardName) {
        this.landLoardName = landLoardName;
    }

    public String getLandLoardPanNo() {
        return landLoardPanNo;
    }

    public TdsDeclaration landLoardPanNo(String landLoardPanNo) {
        this.landLoardPanNo = landLoardPanNo;
        return this;
    }

    public void setLandLoardPanNo(String landLoardPanNo) {
        this.landLoardPanNo = landLoardPanNo;
    }

    public String getLandLoardAddress() {
        return landLoardAddress;
    }

    public TdsDeclaration landLoardAddress(String landLoardAddress) {
        this.landLoardAddress = landLoardAddress;
        return this;
    }

    public void setLandLoardAddress(String landLoardAddress) {
        this.landLoardAddress = landLoardAddress;
    }

    public TdsGroupDetails getTdsGroupDetails() {
        return tdsGroupDetails;
    }

    public TdsDeclaration tdsGroupDetails(TdsGroupDetails tdsGroupDetails) {
        this.tdsGroupDetails = tdsGroupDetails;
        return this;
    }

    public void setTdsGroupDetails(TdsGroupDetails tdsGroupDetails) {
        this.tdsGroupDetails = tdsGroupDetails;
    }

    public String getTempLock() {
        return tempLock;
    }

    public void setTempLock(String tempLock) {
        this.tempLock = tempLock;
    }

    public String getYearLock() {
        return yearLock;
    }

    public void setYearLock(String yearLock) {
        this.yearLock = yearLock;
    }

    public Instant getTempLockTime() {
        return tempLockTime;
    }

    public void setTempLockTime(Instant tempLockTime) {
        this.tempLockTime = tempLockTime;
    }

    public Instant getYearLockTime() {
        return yearLockTime;
    }

    public void setYearLockTime(Instant yearLockTime) {
        this.yearLockTime = yearLockTime;
    }

    public String getRegimeType() {
        return regimeType;
    }

    public void setRegimeType(String regimeType) {
        this.regimeType = regimeType;
    }

    public BigDecimal getPreviousEmployerAmount() {
        return previousEmployerAmount;
    }

    public void setPreviousEmployerAmount(BigDecimal previousEmployerAmount) {
        this.previousEmployerAmount = previousEmployerAmount;
    }

    public BigDecimal getPreviousEmployerTdsDeduction() {
        return previousEmployerTdsDeduction;
    }

    public void setPreviousEmployerTdsDeduction(BigDecimal previousEmployerTdsDeduction) {
        this.previousEmployerTdsDeduction = previousEmployerTdsDeduction;
    }

    public BigDecimal getIncentiveAmount() {
        return incentiveAmount;
    }

    public void setIncentiveAmount(BigDecimal incentiveAmount) {
        this.incentiveAmount = incentiveAmount;
    }
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TdsDeclaration tdsDeclaration = (TdsDeclaration) o;
        if (tdsDeclaration.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tdsDeclaration.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TdsDeclaration{" +
            "id=" + getId() +
            ", cardNo='" + getCardNo() + "'" +
            ", amount='" + getAmount() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", monthRent='" + getMonthRent() + "'" +
            ", landLoardName='" + getLandLoardName() + "'" +
            ", landLoardPanNo='" + getLandLoardPanNo() + "'" +
            ", landLoardAddress='" + getLandLoardAddress() + "'" +
            "}";
    }
}
