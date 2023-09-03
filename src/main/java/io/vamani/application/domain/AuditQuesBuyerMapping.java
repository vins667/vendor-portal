package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A AuditQuesBuyerMapping.
 */
@Entity
@Table(name = "audit_ques_buyer_mapping")
public class AuditQuesBuyerMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="auditQuesBuyerMappingSeq", sequenceName="audit_ques_buyer_mapping_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="auditQuesBuyerMappingSeq")
    private Long id;

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

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    @JoinColumn(name = "vendor_audit_ques_master_id")
    private VendorAuditQuesMasterWD vendorAuditQuesMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public AuditQuesBuyerMapping createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public AuditQuesBuyerMapping createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public AuditQuesBuyerMapping lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public AuditQuesBuyerMapping lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public VendorAuditQuesMasterWD getVendorAuditQuesMaster() {
        return vendorAuditQuesMaster;
    }

    public AuditQuesBuyerMapping vendorAuditQuesMaster(VendorAuditQuesMasterWD vendorAuditQuesMaster) {
        this.vendorAuditQuesMaster = vendorAuditQuesMaster;
        return this;
    }

    public void setVendorAuditQuesMaster(VendorAuditQuesMasterWD vendorAuditQuesMaster) {
        this.vendorAuditQuesMaster = vendorAuditQuesMaster;
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
        AuditQuesBuyerMapping auditQuesBuyerMapping = (AuditQuesBuyerMapping) o;
        if (auditQuesBuyerMapping.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), auditQuesBuyerMapping.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AuditQuesBuyerMapping{" +
            "id=" + getId() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            "}";
    }
}
