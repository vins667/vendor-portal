package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A VendorAuditQuesDetails.
 */
@Entity
@Table(name = "vendor_audit_ques_details")
public class VendorAuditQuesDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="vendorAuditQuesDetailsSeq", sequenceName="vendor_audit_ques_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="vendorAuditQuesDetailsSeq")
    private Long id;

    @NotNull
    @Size(max = 500)
    @Column(name = "audit_question", length = 500, nullable = false)
    private String auditQuestion;

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
    @JsonIgnore
    @JoinColumn(name = "vendor_audit_ques_master_id")
    private VendorAuditQuesMaster vendorAuditQuesMaster;

    @ManyToOne(optional = false)
    @JsonIgnoreProperties("")
    @JoinColumn(name = "audit_group_master_id")
    private AuditGroupMaster auditGroupMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuditQuestion() {
        return auditQuestion;
    }

    public VendorAuditQuesDetails auditQuestion(String auditQuestion) {
        this.auditQuestion = auditQuestion != null ? auditQuestion.trim().toUpperCase() : "";
        return this;
    }

    public void setAuditQuestion(String auditQuestion) {
        this.auditQuestion = auditQuestion != null ? auditQuestion.trim().toUpperCase() : "";
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public VendorAuditQuesDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public VendorAuditQuesDetails createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public VendorAuditQuesDetails lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public VendorAuditQuesDetails lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public VendorAuditQuesMaster getVendorAuditQuesMaster() {
        return vendorAuditQuesMaster;
    }

    public VendorAuditQuesDetails vendorAuditQuesMaster(VendorAuditQuesMaster vendorAuditQuesMaster) {
        this.vendorAuditQuesMaster = vendorAuditQuesMaster;
        return this;
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
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VendorAuditQuesDetails vendorAuditQuesDetails = (VendorAuditQuesDetails) o;
        if (vendorAuditQuesDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vendorAuditQuesDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VendorAuditQuesDetails{" +
            "id=" + getId() +
            ", auditQuestion='" + getAuditQuestion() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            "}";
    }
}
