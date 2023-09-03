package io.vamani.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A CutPlanProgressEntry.
 */
@Entity
@Table(name = "cut_plan_progress_entry")
public class CutPlanProgressEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "cutPlanProgressEntrySeq", sequenceName = "cut_plan_progress_entry_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "cutPlanProgressEntrySeq")
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "operation_code", length = 20, nullable = false)
    private String operationCode;

    @NotNull
    @Size(max = 100)
    @Column(name = "operation_description", length = 100, nullable = false)
    private String operationDescription;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private Instant startDate;

    @NotNull
    @Column(name = "end_date", nullable = false)
    private Instant endDate;

    @Column(name = "no_cutters")
    private Long noCutters;

    @Column(name = "total_hour")
    private Double totalHour;

    @Size(max = 1)
    @Column(name = "last_progress", length = 1)
    private String lastProgress;

    @Size(max = 50)
    @Column(name = "progress_entry_by", length = 50)
    private String progressEntryBy;

    @Column(name = "progress_entry_date")
    private Instant progressEntryDate;

    @Column(name = "progressimportid")
    private Long progressimportid;

    @Size(max = 50)
    @Column(name = "progress_posted_by", length = 50)
    private String progressPostedBy;

    @Column(name = "progress_posted_date")
    private Instant progressPostedDate;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "cut_plan_entry_id")
    private CutPlanEntry cutPlanEntry;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperationCode() {
        return operationCode;
    }

    public CutPlanProgressEntry operationCode(String operationCode) {
        this.operationCode = operationCode;
        return this;
    }

    public void setOperationCode(String operationCode) {
        this.operationCode = operationCode;
    }

    public String getOperationDescription() {
        return operationDescription;
    }

    public CutPlanProgressEntry operationDescription(String operationDescription) {
        this.operationDescription = operationDescription;
        return this;
    }

    public void setOperationDescription(String operationDescription) {
        this.operationDescription = operationDescription;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public CutPlanProgressEntry startDate(Instant startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public CutPlanProgressEntry endDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Long getNoCutters() {
        return noCutters;
    }

    public CutPlanProgressEntry noCutters(Long noCutters) {
        this.noCutters = noCutters;
        return this;
    }

    public void setNoCutters(Long noCutters) {
        this.noCutters = noCutters;
    }

    public Double getTotalHour() {
        return totalHour;
    }

    public CutPlanProgressEntry totalHour(Double totalHour) {
        this.totalHour = totalHour;
        return this;
    }

    public void setTotalHour(Double totalHour) {
        this.totalHour = totalHour;
    }

    public String getProgressEntryBy() {
        return progressEntryBy;
    }

    public CutPlanProgressEntry progressEntryBy(String progressEntryBy) {
        this.progressEntryBy = progressEntryBy;
        return this;
    }

    public void setProgressEntryBy(String progressEntryBy) {
        this.progressEntryBy = progressEntryBy;
    }

    public Instant getProgressEntryDate() {
        return progressEntryDate;
    }

    public CutPlanProgressEntry progressEntryDate(Instant progressEntryDate) {
        this.progressEntryDate = progressEntryDate;
        return this;
    }

    public void setProgressEntryDate(Instant progressEntryDate) {
        this.progressEntryDate = progressEntryDate;
    }

    public Long getProgressimportid() {
        return progressimportid;
    }

    public CutPlanProgressEntry progressimportid(Long progressimportid) {
        this.progressimportid = progressimportid;
        return this;
    }

    public void setProgressimportid(Long progressimportid) {
        this.progressimportid = progressimportid;
    }

    public String getProgressPostedBy() {
        return progressPostedBy;
    }

    public CutPlanProgressEntry progressPostedBy(String progressPostedBy) {
        this.progressPostedBy = progressPostedBy;
        return this;
    }

    public void setProgressPostedBy(String progressPostedBy) {
        this.progressPostedBy = progressPostedBy;
    }

    public CutPlanEntry getCutPlanEntry() {
        return cutPlanEntry;
    }

    public CutPlanProgressEntry cutPlanEntry(CutPlanEntry cutPlanEntry) {
        this.cutPlanEntry = cutPlanEntry;
        return this;
    }

    public void setCutPlanEntry(CutPlanEntry cutPlanEntry) {
        this.cutPlanEntry = cutPlanEntry;
    }

    public Instant getProgressPostedDate() {
        return progressPostedDate;
    }

    public void setProgressPostedDate(Instant progressPostedDate) {
        this.progressPostedDate = progressPostedDate;
    }

    public String getLastProgress() {
        return lastProgress;
    }

    public void setLastProgress(String lastProgress) {
        this.lastProgress = lastProgress;
    }
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CutPlanProgressEntry)) {
            return false;
        }
        return id != null && id.equals(((CutPlanProgressEntry) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CutPlanProgressEntry{" +
            "id=" + getId() +
            ", operationCode='" + getOperationCode() + "'" +
            ", operationDescription='" + getOperationDescription() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", noCutters=" + getNoCutters() +
            ", totalHour=" + getTotalHour() +
            ", progressEntryBy='" + getProgressEntryBy() + "'" +
            ", progressEntryDate='" + getProgressEntryDate() + "'" +
            ", progressimportid=" + getProgressimportid() +
            ", progressPostedBy='" + getProgressPostedBy() + "'" +
            "}";
    }
}
