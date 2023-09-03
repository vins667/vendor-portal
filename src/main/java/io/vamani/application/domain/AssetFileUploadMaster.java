package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A AssetFileUploadMaster.
 */
@Entity
@Table(name = "asset_file_upload_master")
public class AssetFileUploadMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="assetFileUploadMasterSeq", sequenceName="asset_file_upload_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="assetFileUploadMasterSeq")
    private Long id;

    @NotNull
    @Size(max = 500)
    @Column(name = "file_name", length = 500, nullable = false)
    private String fileName;

    @NotNull
    @Size(max = 500)
    @Column(name = "display_file_name", length = 500, nullable = false)
    private String displayFileName;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Size(max = 100)
    @Column(name = "invoice_number", length = 100)
    private String invoiceNumber;

    @ManyToOne(optional = false)
    @JoinColumn(name = "asset_supplier_master_id")
    @NotNull
    @JsonIgnoreProperties("")
    private AssetSupplierMaster assetSupplierMaster;

    @ManyToOne(optional = false)
    @JoinColumn(name = "asset_document_master_id")
    @NotNull
    @JsonIgnoreProperties("")
    private AssetDocumentMaster assetDocumentMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public AssetFileUploadMaster fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDisplayFileName() {
        return displayFileName;
    }

    public AssetFileUploadMaster displayFileName(String displayFileName) {
        this.displayFileName = displayFileName;
        return this;
    }

    public void setDisplayFileName(String displayFileName) {
        this.displayFileName = displayFileName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public AssetFileUploadMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public AssetFileUploadMaster createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public AssetFileUploadMaster invoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
        return this;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public AssetSupplierMaster getAssetSupplierMaster() {
        return assetSupplierMaster;
    }

    public AssetFileUploadMaster assetSupplierMaster(AssetSupplierMaster assetSupplierMaster) {
        this.assetSupplierMaster = assetSupplierMaster;
        return this;
    }

    public void setAssetSupplierMaster(AssetSupplierMaster assetSupplierMaster) {
        this.assetSupplierMaster = assetSupplierMaster;
    }

    public AssetDocumentMaster getAssetDocumentMaster() {
        return assetDocumentMaster;
    }

    public AssetFileUploadMaster assetDocumentMaster(AssetDocumentMaster assetDocumentMaster) {
        this.assetDocumentMaster = assetDocumentMaster;
        return this;
    }

    public void setAssetDocumentMaster(AssetDocumentMaster assetDocumentMaster) {
        this.assetDocumentMaster = assetDocumentMaster;
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
        AssetFileUploadMaster assetFileUploadMaster = (AssetFileUploadMaster) o;
        if (assetFileUploadMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), assetFileUploadMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AssetFileUploadMaster{" +
            "id=" + getId() +
            ", fileName='" + getFileName() + "'" +
            ", displayFileName='" + getDisplayFileName() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", invoiceNumber='" + getInvoiceNumber() + "'" +
            "}";
    }
}
