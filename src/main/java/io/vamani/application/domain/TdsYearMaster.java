package io.vamani.application.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A TdsYearMaster.
 */
@Entity
@Table(name = "tds_year_master")
public class TdsYearMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "tdsYearMasterSeq", sequenceName = "tds_year_master_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "tdsYearMasterSeq")
    private Long id;

    @NotNull
    @Size(max = 4)
    @Column(name = "code", length = 4, nullable = false)
    private String code;

    @NotNull
    @Size(max = 100)
    @Column(name = "finance_year", length = 100, nullable = false)
    private String financeYear;

    @NotNull
    @Column(name = "active", nullable = false)
    private Boolean active;

    @NotNull
    @Column(name = "expired", nullable = false)
    private Boolean expired;

    @NotNull
    @Column(name = "salary_slab", nullable = false)
    private Double salarySlab;

    @NotNull
    @Column(name = "standard_deduction", nullable = false)
    private Double standardDeduction;

    @NotNull
    @Column(name = "temp_lock", nullable = false)
    private Boolean tempLock;

    @NotNull
    @Column(name = "upload_doc", nullable = false)
    private Boolean uploadDoc;

    @Column(name = "regime")
    private Boolean regime;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public TdsYearMaster code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFinanceYear() {
        return financeYear;
    }

    public TdsYearMaster financeYear(String financeYear) {
        this.financeYear = financeYear;
        return this;
    }

    public void setFinanceYear(String financeYear) {
        this.financeYear = financeYear;
    }

    public Boolean isActive() {
        return active;
    }

    public TdsYearMaster active(Boolean active) {
        this.active = active;
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean isExpired() {
        return expired;
    }

    public TdsYearMaster expired(Boolean expired) {
        this.expired = expired;
        return this;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public TdsYearMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public TdsYearMaster createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public Double getSalarySlab() {
        return salarySlab;
    }

    public void setSalarySlab(Double salarySlab) {
        this.salarySlab = salarySlab;
    }

    public Boolean getTempLock() {
        return tempLock;
    }

    public void setTempLock(Boolean tempLock) {
        this.tempLock = tempLock;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Double getStandardDeduction() {
        return standardDeduction;
    }

    public void setStandardDeduction(Double standardDeduction) {
        this.standardDeduction = standardDeduction;
    }

    public Boolean getUploadDoc() {
        return uploadDoc;
    }

    public void setUploadDoc(Boolean uploadDoc) {
        this.uploadDoc = uploadDoc;
    }

    public Boolean getRegime() {
        return regime;
    }

    public void setRegime(Boolean regime) {
        this.regime = regime;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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
        TdsYearMaster tdsYearMaster = (TdsYearMaster) o;
        if (tdsYearMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tdsYearMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TdsYearMaster{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", financeYear='" + getFinanceYear() + "'" +
            ", active='" + isActive() + "'" +
            ", expired='" + isExpired() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}
