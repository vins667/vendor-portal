package io.vamani.application.model;

import io.vamani.application.domain.VendorAuditQuesDetails;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class VendorAuditQuesMasterBean implements Serializable {
    private Long id;

    private String auditName;

    private String createdBy;

    private Instant createdDate;

    private String lastUpdatedBy;

    private Instant lastUpdatedDate;

    private Set<VendorAuditQuesDetails> vendorAuditQuesDetails = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
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

    public Set<VendorAuditQuesDetails> getVendorAuditQuesDetails() {
        return vendorAuditQuesDetails;
    }

    public void setVendorAuditQuesDetails(Set<VendorAuditQuesDetails> vendorAuditQuesDetails) {
        this.vendorAuditQuesDetails = vendorAuditQuesDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendorAuditQuesMasterBean that = (VendorAuditQuesMasterBean) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(auditName, that.auditName) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(createdDate, that.createdDate) &&
            Objects.equals(lastUpdatedBy, that.lastUpdatedBy) &&
            Objects.equals(lastUpdatedDate, that.lastUpdatedDate) &&
            Objects.equals(vendorAuditQuesDetails, that.vendorAuditQuesDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, auditName, createdBy, createdDate, lastUpdatedBy, lastUpdatedDate, vendorAuditQuesDetails);
    }

    @Override
    public String toString() {
        return "VendorAuditQuesMasterBean{" +
            "id=" + id +
            ", auditName='" + auditName + '\'' +
            ", createdBy='" + createdBy + '\'' +
            ", createdDate=" + createdDate +
            ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
            ", lastUpdatedDate=" + lastUpdatedDate +
            ", vendorAuditQuesDetails=" + vendorAuditQuesDetails +
            '}';
    }
}
