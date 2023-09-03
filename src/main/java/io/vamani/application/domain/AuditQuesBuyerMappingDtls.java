package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A AuditQuesBuyerMappingDtls.
 */
@Entity
@Table(name = "audit_ques_buyer_mapping_dtls")
public class AuditQuesBuyerMappingDtls implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="auditQuesBuyerMappingDtlsSeq", sequenceName="audit_ques_buyer_mapping_dtls_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="auditQuesBuyerMappingDtlsSeq")
    private Long id;

    @NotNull
    @Column(name = "allow_valid", nullable = false)
    private Boolean allowValid;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Size(max = 50)
    @Column(name = "last_update_by", length = 50)
    private String lastUpdateBy;

    @Column(name = "last_updated_date")
    private Instant lastUpdatedDate;

    @Column(name = "vendor_audit_ques_details_id")
    private Long vendorAuditQuesDetailsId;

    @Column(name = "buyer_master_id")
    private String buyerMasterId;

    @Column(name = "audit_name")
    private String auditName;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")

    @JoinColumn(name = "audit_ques_buyer_mapping_id")
    private AuditQuesBuyerMapping auditQuesBuyerMapping;

    /*@ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    private VendorAuditQuesDetails vendorAuditQuesDetails;


    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    private BuyerMaster buyerMaster;*/

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isAllowValid() {
        return allowValid;
    }

    public AuditQuesBuyerMappingDtls allowValid(Boolean allowValid) {
        this.allowValid = allowValid;
        return this;
    }

    public void setAllowValid(Boolean allowValid) {
        this.allowValid = allowValid;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public AuditQuesBuyerMappingDtls createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public AuditQuesBuyerMappingDtls createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public AuditQuesBuyerMappingDtls lastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
        return this;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public AuditQuesBuyerMappingDtls lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Long getVendorAuditQuesDetailsId() {
        return vendorAuditQuesDetailsId;
    }

    public void setVendorAuditQuesDetailsId(Long vendorAuditQuesDetailsId) {
        this.vendorAuditQuesDetailsId = vendorAuditQuesDetailsId;
    }

    public String getBuyerMasterId() {
        return buyerMasterId;
    }

    public void setBuyerMasterId(String buyerMasterId) {
        this.buyerMasterId = buyerMasterId;
    }

    public AuditQuesBuyerMapping getAuditQuesBuyerMapping() {
        return auditQuesBuyerMapping;
    }

    public void setAuditQuesBuyerMapping(AuditQuesBuyerMapping auditQuesBuyerMapping) {
        this.auditQuesBuyerMapping = auditQuesBuyerMapping;
    }

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
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
        AuditQuesBuyerMappingDtls auditQuesBuyerMappingDtls = (AuditQuesBuyerMappingDtls) o;
        if (auditQuesBuyerMappingDtls.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), auditQuesBuyerMappingDtls.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AuditQuesBuyerMappingDtls{" +
            "id=" + getId() +
            ", allowValid='" + isAllowValid() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdateBy='" + getLastUpdateBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            "}";
    }
}
