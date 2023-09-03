package io.vamani.application.domain;
import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
/**
 * A TermsConditionMaster.
 */
@Entity
@Table(name = "terms_condition_master")
public class TermsConditionMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="termsConditionMasterSeq", sequenceName="terms_condition_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="termsConditionMasterSeq")
    private Long id;

    @NotNull
    @Column(name = "applicable_date", nullable = false)
    private LocalDate applicableDate;

    @Column(name = "closed_date")
    private LocalDate closedDate;

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

    @ManyToOne
    @JoinColumn(name="report_type_master_id")
    @JsonIgnoreProperties("")
    private ReportTypeMaster reportTypeMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getApplicableDate() {
        return applicableDate;
    }

    public TermsConditionMaster applicableDate(LocalDate applicableDate) {
        this.applicableDate = applicableDate;
        return this;
    }

    public void setApplicableDate(LocalDate applicableDate) {
        this.applicableDate = applicableDate;
    }

    public LocalDate getClosedDate() {
        return closedDate;
    }

    public TermsConditionMaster closedDate(LocalDate closedDate) {
        this.closedDate = closedDate;
        return this;
    }

    public void setClosedDate(LocalDate closedDate) {
        this.closedDate = closedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public TermsConditionMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public TermsConditionMaster createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public TermsConditionMaster lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public TermsConditionMaster lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public ReportTypeMaster getReportTypeMaster() {
        return reportTypeMaster;
    }

    public TermsConditionMaster reportTypeMaster(ReportTypeMaster reportTypeMaster) {
        this.reportTypeMaster = reportTypeMaster;
        return this;
    }

    public void setReportTypeMaster(ReportTypeMaster reportTypeMaster) {
        this.reportTypeMaster = reportTypeMaster;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TermsConditionMaster)) {
            return false;
        }
        return id != null && id.equals(((TermsConditionMaster) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TermsConditionMaster{" +
            "id=" + getId() +
            ", applicableDate='" + getApplicableDate() + "'" +
            ", closedDate='" + getClosedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            "}";
    }
}
