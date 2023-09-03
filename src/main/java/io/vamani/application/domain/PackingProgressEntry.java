package io.vamani.application.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "packing_progress_entry")
public class PackingProgressEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="packingProgressEntrySeq", sequenceName="packing_progress_entry_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.AUTO, generator="packingProgressEntrySeq")
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

    @Size(max = 50)
    @Column(name = "progress_posted_by", length = 50)
    private String progressPostedBy;

    @Column(name = "progress_posted_date")
    private Instant progressPostedDate;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "packing_production_entry_id")
    private PackingProductionEntry packingProductionEntry;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(String operationCode) {
        this.operationCode = operationCode;
    }

    public String getOperationDescription() {
        return operationDescription;
    }

    public void setOperationDescription(String operationDescription) {
        this.operationDescription = operationDescription;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Long getNoCutters() {
        return noCutters;
    }

    public void setNoCutters(Long noCutters) {
        this.noCutters = noCutters;
    }

    public Double getTotalHour() {
        return totalHour;
    }

    public void setTotalHour(Double totalHour) {
        this.totalHour = totalHour;
    }

    public String getLastProgress() {
        return lastProgress;
    }

    public void setLastProgress(String lastProgress) {
        this.lastProgress = lastProgress;
    }

    public String getProgressEntryBy() {
        return progressEntryBy;
    }

    public void setProgressEntryBy(String progressEntryBy) {
        this.progressEntryBy = progressEntryBy;
    }

    public Instant getProgressEntryDate() {
        return progressEntryDate;
    }

    public void setProgressEntryDate(Instant progressEntryDate) {
        this.progressEntryDate = progressEntryDate;
    }

    public String getProgressPostedBy() {
        return progressPostedBy;
    }

    public void setProgressPostedBy(String progressPostedBy) {
        this.progressPostedBy = progressPostedBy;
    }

    public Instant getProgressPostedDate() {
        return progressPostedDate;
    }

    public void setProgressPostedDate(Instant progressPostedDate) {
        this.progressPostedDate = progressPostedDate;
    }

    public PackingProductionEntry getPackingProductionEntry() {
        return packingProductionEntry;
    }

    public void setPackingProductionEntry(PackingProductionEntry packingProductionEntry) {
        this.packingProductionEntry = packingProductionEntry;
    }
}
