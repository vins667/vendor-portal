package io.vamani.application.vendorportal.domain;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A VendorDocument.
 */
@Entity
@Table(name = "vendor_document")
public class VendorDocument implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="vendorDocumentSeq", sequenceName="vendor_document_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="vendorDocumentSeq")
    private Long id;

    @Column(name = "document_id")
    private Long documentId;

    @Column(name = "vendor_id")
    private Long vendorId;

    @Size(max = 200)
    @Column(name = "document_file_path", length = 200)
    private String documentFilePath;

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
    @Column(name = "data_flag", length = 1)
    private String dataFlag;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public VendorDocument documentId(Long documentId) {
        this.documentId = documentId;
        return this;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public VendorDocument vendorId(Long vendorId) {
        this.vendorId = vendorId;
        return this;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getDocumentFilePath() {
        return documentFilePath;
    }

    public VendorDocument documentFilePath(String documentFilePath) {
        this.documentFilePath = documentFilePath;
        return this;
    }

    public void setDocumentFilePath(String documentFilePath) {
        this.documentFilePath = documentFilePath;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public VendorDocument createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public VendorDocument createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public VendorDocument lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public VendorDocument lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
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
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VendorDocument vendorDocument = (VendorDocument) o;
        if (vendorDocument.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vendorDocument.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VendorDocument{" +
            "id=" + getId() +
            ", documentId=" + getDocumentId() +
            ", vendorId=" + getVendorId() +
            ", documentFilePath='" + getDocumentFilePath() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            "}";
    }
}
