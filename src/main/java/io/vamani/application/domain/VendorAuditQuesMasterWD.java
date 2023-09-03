package io.vamani.application.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A VendorAuditQuesMaster.
 */
@Entity
@Table(name = "vendor_audit_ques_master")
public class VendorAuditQuesMasterWD implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="vendorAuditQuesMasterSeq", sequenceName="vendor_audit_ques_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="vendorAuditQuesMasterSeq")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "audit_name", length = 100, nullable = false)
    private String auditName;

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
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuditName() {
        return auditName;
    }

    public VendorAuditQuesMasterWD auditName(String auditName) {
        this.auditName = auditName != null ? auditName.trim().toUpperCase() : "";
        return this;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName != null ? auditName.trim().toUpperCase() : "";
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public VendorAuditQuesMasterWD createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public VendorAuditQuesMasterWD createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public VendorAuditQuesMasterWD lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public VendorAuditQuesMasterWD lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
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
        VendorAuditQuesMasterWD vendorAuditQuesMaster = (VendorAuditQuesMasterWD) o;
        if (vendorAuditQuesMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vendorAuditQuesMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VendorAuditQuesMaster{" +
            "id=" + getId() +
            ", auditName='" + getAuditName() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            "}";
    }
}
