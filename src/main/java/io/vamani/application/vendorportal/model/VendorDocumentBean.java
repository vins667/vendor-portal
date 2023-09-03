package io.vamani.application.vendorportal.model;

import java.time.Instant;
import java.util.Objects;

public class VendorDocumentBean {
    private Long id;

    private Long documentId;

    private Long vendorId;

    private String documentFilePath;

    private String createdBy;

    private Instant createdDate;

    private String lastUpdatedBy;

    private Instant lastUpdatedDate;

    private String dataFlag;

    private String tableType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getDocumentFilePath() {
        return documentFilePath;
    }

    public void setDocumentFilePath(String documentFilePath) {
        this.documentFilePath = documentFilePath;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getDataFlag() {
        return dataFlag;
    }

    public void setDataFlag(String dataFlag) {
        this.dataFlag = dataFlag;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendorDocumentBean that = (VendorDocumentBean) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(documentId, that.documentId) &&
            Objects.equals(vendorId, that.vendorId) &&
            Objects.equals(documentFilePath, that.documentFilePath) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(createdDate, that.createdDate) &&
            Objects.equals(lastUpdatedBy, that.lastUpdatedBy) &&
            Objects.equals(lastUpdatedDate, that.lastUpdatedDate) &&
            Objects.equals(dataFlag, that.dataFlag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, documentId, vendorId, documentFilePath, createdBy, createdDate, lastUpdatedBy, lastUpdatedDate, dataFlag);
    }

    @Override
    public String toString() {
        return "VendorDocumentBean{" +
            "id=" + id +
            ", documentId=" + documentId +
            ", vendorId=" + vendorId +
            ", documentFilePath='" + documentFilePath + '\'' +
            ", createdBy='" + createdBy + '\'' +
            ", createdDate=" + createdDate +
            ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
            ", lastUpdatedDate=" + lastUpdatedDate +
            ", dataFlag='" + dataFlag + '\'' +
            '}';
    }
}
