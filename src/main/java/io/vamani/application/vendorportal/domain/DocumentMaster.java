package io.vamani.application.vendorportal.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DocumentMaster.
 */
@Entity
@Table(name = "document_master")
public class DocumentMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="documentMasterSeq", sequenceName="document_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="documentMasterSeq")
    private Long id;

    @Size(max = 45)
    @Column(name = "document_name", length = 45)
    private String documentName;

    @Size(max = 45)
    @Column(name = "document_description", length = 45)
    private String documentDescription;

    @NotNull
    @Column(name = "required_field", nullable = false)
    private Boolean requiredField;

    @Size(max = 1)
    @Column(name = "status", length = 1)
    private String status;

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

    @Column(name = "doc_country")
    private String docCountry;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentName() {
        return documentName;
    }

    public DocumentMaster documentName(String documentName) {
        this.documentName = documentName;
        return this;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentDescription() {
        return documentDescription;
    }

    public DocumentMaster documentDescription(String documentDescription) {
        this.documentDescription = documentDescription;
        return this;
    }

    public void setDocumentDescription(String documentDescription) {
        this.documentDescription = documentDescription;
    }

    public Boolean isRequiredField() {
        return requiredField;
    }

    public DocumentMaster requiredField(Boolean requiredField) {
        this.requiredField = requiredField;
        return this;
    }

    public void setRequiredField(Boolean requiredField) {
        this.requiredField = requiredField;
    }

    public String getStatus() {
        return status;
    }

    public DocumentMaster status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public DocumentMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public DocumentMaster createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public DocumentMaster lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public DocumentMaster lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getDocCountry() {
        return docCountry;
    }

    public void setDocCountry(String docCountry) {
        this.docCountry = docCountry;
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
        DocumentMaster documentMaster = (DocumentMaster) o;
        if (documentMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), documentMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DocumentMaster{" +
            "id=" + getId() +
            ", documentName='" + getDocumentName() + "'" +
            ", documentDescription='" + getDocumentDescription() + "'" +
            ", requiredField='" + isRequiredField() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            "}";
    }
}
