package io.vamani.application.model;

import io.vamani.application.domain.PackingProductionEntry;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

public class PackingProgressEntryBean implements Serializable {
    private Long id;

    private String scannedBy;

    private String operationCode;

    private String operationDescription;

    private Instant startDate;

    private Instant endDate;

    private Long noCutters;

    private Double totalHour;

    private String lastProgress;

    private String progressEntryBy;

    private Instant progressEntryDate;

    private String progressPostedBy;

    private Instant progressPostedDate;

    private PackingProductionEntry packingProductionEntry;

    private List<StitchIssuePackDetailsBean> stitchIssuePackDetails;

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

    public List<StitchIssuePackDetailsBean> getStitchIssuePackDetails() {
        return stitchIssuePackDetails;
    }

    public void setStitchIssuePackDetails(List<StitchIssuePackDetailsBean> stitchIssuePackDetails) {
        this.stitchIssuePackDetails = stitchIssuePackDetails;
    }

    public String getScannedBy() {
        return scannedBy;
    }

    public void setScannedBy(String scannedBy) {
        this.scannedBy = scannedBy;
    }
}
