package io.vamani.application.model;

import io.vamani.application.domain.VendorAuditQuesMaster;
import io.vamani.application.domain.AuditGroupMaster;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class VendorAuditQuesDetailsBean implements Serializable {
    private Long id;

    private String auditQuestion;

    private String createdBy;

    private Instant createdDate;

    private String lastUpdatedBy;

    private Instant lastUpdatedDate;

    private VendorAuditQuesMaster vendorAuditQuesMaster;

    private AuditGroupMaster auditGroupMaster;

    private Map<String, Boolean> buyerMastersMap;

    private List<BuyerMap> buyerMasters;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuditQuestion() {
        return auditQuestion;
    }

    public void setAuditQuestion(String auditQuestion) {
        this.auditQuestion = auditQuestion;
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

    public VendorAuditQuesMaster getVendorAuditQuesMaster() {
        return vendorAuditQuesMaster;
    }

    public void setVendorAuditQuesMaster(VendorAuditQuesMaster vendorAuditQuesMaster) {
        this.vendorAuditQuesMaster = vendorAuditQuesMaster;
    }

    public AuditGroupMaster getAuditGroupMaster() {
        return auditGroupMaster;
    }

    public void setAuditGroupMaster(AuditGroupMaster auditGroupMaster) {
        this.auditGroupMaster = auditGroupMaster;
    }

    public Map<String, Boolean> getBuyerMastersMap() {
        return buyerMastersMap;
    }

    public void setBuyerMastersMap(Map<String, Boolean> buyerMastersMap) {
        this.buyerMastersMap = buyerMastersMap;
    }

    public List<BuyerMap> getBuyerMasters() {
        return buyerMasters;
    }

    public void setBuyerMasters(List<BuyerMap> buyerMasters) {
        this.buyerMasters = buyerMasters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendorAuditQuesDetailsBean that = (VendorAuditQuesDetailsBean) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(auditQuestion, that.auditQuestion) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(createdDate, that.createdDate) &&
            Objects.equals(lastUpdatedBy, that.lastUpdatedBy) &&
            Objects.equals(lastUpdatedDate, that.lastUpdatedDate) &&
            Objects.equals(vendorAuditQuesMaster, that.vendorAuditQuesMaster) &&
            Objects.equals(auditGroupMaster, that.auditGroupMaster) &&
            Objects.equals(buyerMastersMap, that.buyerMastersMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, auditQuestion, createdBy, createdDate, lastUpdatedBy, lastUpdatedDate, vendorAuditQuesMaster, auditGroupMaster, buyerMastersMap);
    }

    @Override
    public String toString() {
        return "VendorAuditQuesDetailsBean{" +
            "id=" + id +
            ", auditQuestion='" + auditQuestion + '\'' +
            ", createdBy='" + createdBy + '\'' +
            ", createdDate=" + createdDate +
            ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
            ", lastUpdatedDate=" + lastUpdatedDate +
            ", vendorAuditQuesMaster=" + vendorAuditQuesMaster +
            ", auditGroupMaster=" + auditGroupMaster +
            ", buyerMastersMap=" + buyerMastersMap +
            '}';
    }
}
