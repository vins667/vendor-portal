package io.vamani.application.vendorportal.domain;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A VendorDocumentTrancation.
 */
@Entity
@Table(name = "vendor_document_transaction")
public class VendorDocumentTrancation implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name="vendorDocumentTransSeq", sequenceName="vendor_document_trans_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="vendorDocumentTransSeq")
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

    @Column(name = "approved_date")
    private Instant approvedDate;

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

    public VendorDocumentTrancation documentId(Long documentId) {
        this.documentId = documentId;
        return this;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public VendorDocumentTrancation vendorId(Long vendorId) {
        this.vendorId = vendorId;
        return this;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getDocumentFilePath() {
        return documentFilePath;
    }

    public VendorDocumentTrancation documentFilePath(String documentFilePath) {
        this.documentFilePath = documentFilePath;
        return this;
    }

    public void setDocumentFilePath(String documentFilePath) {
        this.documentFilePath = documentFilePath;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public VendorDocumentTrancation createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public VendorDocumentTrancation createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public VendorDocumentTrancation lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public VendorDocumentTrancation lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Instant getApprovedDate() {
        return approvedDate;
    }

    public VendorDocumentTrancation approvedDate(Instant approvedDate) {
        this.approvedDate = approvedDate;
        return this;
    }

    public void setApprovedDate(Instant approvedDate) {
        this.approvedDate = approvedDate;
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
        VendorDocumentTrancation vendorDocumentTrancation = (VendorDocumentTrancation) o;
        if (vendorDocumentTrancation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vendorDocumentTrancation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VendorDocumentTrancation{" +
            "id=" + getId() +
            ", documentId=" + getDocumentId() +
            ", vendorId=" + getVendorId() +
            ", documentFilePath='" + getDocumentFilePath() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", approvedDate='" + getApprovedDate() + "'" +
            "}";
    }
}
