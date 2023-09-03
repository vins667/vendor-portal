package io.vamani.application.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A RecruitmentDocumentMaster.
 */
@Entity
@Table(name = "recruitment_document_master")
public class RecruitmentDocumentMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="recruitmentDocumentMasterSeq", sequenceName="recruitment_document_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="recruitmentDocumentMasterSeq")
    private Long id;

    @Size(max = 200)
    @Column(name = "description", length = 200)
    private String description;

    @NotNull
    @Column(name = "document_mandatory", nullable = false)
    private Boolean documentMandatory;

    @Size(max = 1)
    @Column(name = "force_document_type", length = 1)
    private String forceDocumentType;

    @Size(max = 1)
    @Column(name = "attach_type", length = 1)
    private String attachType;

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

    @Size(max = 1)
    @Column(name = "document_type", length = 1)
    private String documentType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public RecruitmentDocumentMaster description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isDocumentMandatory() {
        return documentMandatory;
    }

    public RecruitmentDocumentMaster documentMandatory(Boolean documentMandatory) {
        this.documentMandatory = documentMandatory;
        return this;
    }

    public void setDocumentMandatory(Boolean documentMandatory) {
        this.documentMandatory = documentMandatory;
    }

    public String getForceDocumentType() {
        return forceDocumentType;
    }

    public RecruitmentDocumentMaster forceDocumentType(String forceDocumentType) {
        this.forceDocumentType = forceDocumentType;
        return this;
    }

    public void setForceDocumentType(String forceDocumentType) {
        this.forceDocumentType = forceDocumentType;
    }

    public String getAttachType() {
        return attachType;
    }

    public RecruitmentDocumentMaster attachType(String attachType) {
        this.attachType = attachType;
        return this;
    }

    public void setAttachType(String attachType) {
        this.attachType = attachType;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public RecruitmentDocumentMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public RecruitmentDocumentMaster createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public RecruitmentDocumentMaster lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public RecruitmentDocumentMaster lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getDocumentType() {
        return documentType;
    }

    public RecruitmentDocumentMaster documentType(String documentType) {
        this.documentType = documentType;
        return this;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
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
        RecruitmentDocumentMaster recruitmentDocumentMaster = (RecruitmentDocumentMaster) o;
        if (recruitmentDocumentMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), recruitmentDocumentMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RecruitmentDocumentMaster{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", documentMandatory='" + isDocumentMandatory() + "'" +
            ", forceDocumentType='" + getForceDocumentType() + "'" +
            ", attachType='" + getAttachType() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", documentType='" + getDocumentType() + "'" +
            "}";
    }
}
