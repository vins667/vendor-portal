package io.vamani.application.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A TdsSlabMaster.
 */
@Entity
@Table(name = "tds_slab_master")
public class TdsSlabMaster implements Serializable, Comparable<TdsSlabMaster> {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="tdsSlabMasterSeq", sequenceName="tds_slab_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="tdsSlabMasterSeq")
    private Long id;

    @NotNull
    @Size(max = 6)
    @Column(name = "fin_year", length = 6, nullable = false)
    private String finYear;

    @NotNull
    @Size(max = 1)
    @Column(name = "gender", length = 1, nullable = false)
    private String gender;

    @NotNull
    @Column(name = "age_start", nullable = false)
    private Integer ageStart;

    @NotNull
    @Column(name = "age_end", nullable = false)
    private Integer ageEnd;

    @NotNull
    @Column(name = "amount_start", nullable = false)
    private Double amountStart;

    @NotNull
    @Column(name = "amount_end", nullable = false)
    private Double amountEnd;

    @NotNull
    @Column(name = "tax_percentage", nullable = false)
    private Double taxPercentage;

    @NotNull
    @Column(name = "tax_surcharge", nullable = false)
    private Double taxSurcharge;

    @NotNull
    @Column(name = "exemption_limit", nullable = false)
    private Double exemptionLimit;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "regime_type", length = 3)
    private String regimeType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFinYear() {
        return finYear;
    }

    public TdsSlabMaster finYear(String finYear) {
        this.finYear = finYear;
        return this;
    }

    public void setFinYear(String finYear) {
        this.finYear = finYear;
    }

    public String getGender() {
        return gender;
    }

    public TdsSlabMaster gender(String gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAgeStart() {
        return ageStart;
    }

    public TdsSlabMaster ageStart(Integer ageStart) {
        this.ageStart = ageStart;
        return this;
    }

    public void setAgeStart(Integer ageStart) {
        this.ageStart = ageStart;
    }

    public Integer getAgeEnd() {
        return ageEnd;
    }

    public TdsSlabMaster ageEnd(Integer ageEnd) {
        this.ageEnd = ageEnd;
        return this;
    }

    public void setAgeEnd(Integer ageEnd) {
        this.ageEnd = ageEnd;
    }

    public Double getAmountStart() {
        return amountStart;
    }

    public TdsSlabMaster amountStart(Double amountStart) {
        this.amountStart = amountStart;
        return this;
    }

    public void setAmountStart(Double amountStart) {
        this.amountStart = amountStart;
    }

    public Double getAmountEnd() {
        return amountEnd;
    }

    public TdsSlabMaster amountEnd(Double amountEnd) {
        this.amountEnd = amountEnd;
        return this;
    }

    public void setAmountEnd(Double amountEnd) {
        this.amountEnd = amountEnd;
    }

    public Double getTaxPercentage() {
        return taxPercentage;
    }

    public TdsSlabMaster taxPercentage(Double taxPercentage) {
        this.taxPercentage = taxPercentage;
        return this;
    }

    public void setTaxPercentage(Double taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public Double getTaxSurcharge() {
        return taxSurcharge;
    }

    public TdsSlabMaster taxSurcharge(Double taxSurcharge) {
        this.taxSurcharge = taxSurcharge;
        return this;
    }

    public void setTaxSurcharge(Double taxSurcharge) {
        this.taxSurcharge = taxSurcharge;
    }

    public Double getExemptionLimit() {
        return exemptionLimit;
    }

    public TdsSlabMaster exemptionLimit(Double exemptionLimit) {
        this.exemptionLimit = exemptionLimit;
        return this;
    }

    public void setExemptionLimit(Double exemptionLimit) {
        this.exemptionLimit = exemptionLimit;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public TdsSlabMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public TdsSlabMaster createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getRegimeType() {
        return regimeType;
    }

    public void setRegimeType(String regimeType) {
        this.regimeType = regimeType;
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
        TdsSlabMaster tdsSlabMaster = (TdsSlabMaster) o;
        if (tdsSlabMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tdsSlabMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TdsSlabMaster{" +
            "id=" + getId() +
            ", finYear='" + getFinYear() + "'" +
            ", gender='" + getGender() + "'" +
            ", ageStart=" + getAgeStart() +
            ", ageEnd=" + getAgeEnd() +
            ", amountStart=" + getAmountStart() +
            ", amountEnd=" + getAmountEnd() +
            ", taxPercentage=" + getTaxPercentage() +
            ", taxSurcharge=" + getTaxSurcharge() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }

    @Override
    public int compareTo(TdsSlabMaster o) {
        return this.getAmountStart().compareTo(o.getAmountStart());
    }
}
