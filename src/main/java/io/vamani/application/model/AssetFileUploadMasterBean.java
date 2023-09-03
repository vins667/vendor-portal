package io.vamani.application.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.vamani.application.domain.AssetDocumentMaster;
import io.vamani.application.domain.AssetSupplierMaster;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A AssetFileUploadMaster.
 */
public class AssetFileUploadMasterBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String fileName;

    private String displayFileName;

    private String createdBy;

    private Instant createdDate;

    private String invoiceNumber;

    private Boolean detailExist;

    private AssetSupplierMaster assetSupplierMaster;

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

    public AssetFileUploadMasterBean fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDisplayFileName() {
        return displayFileName;
    }

    public AssetFileUploadMasterBean displayFileName(String displayFileName) {
        this.displayFileName = displayFileName;
        return this;
    }

    public void setDisplayFileName(String displayFileName) {
        this.displayFileName = displayFileName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public AssetFileUploadMasterBean createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public AssetFileUploadMasterBean createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public AssetFileUploadMasterBean invoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
        return this;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public AssetSupplierMaster getAssetSupplierMaster() {
        return assetSupplierMaster;
    }

    public AssetFileUploadMasterBean assetSupplierMaster(AssetSupplierMaster assetSupplierMaster) {
        this.assetSupplierMaster = assetSupplierMaster;
        return this;
    }

    public void setAssetSupplierMaster(AssetSupplierMaster assetSupplierMaster) {
        this.assetSupplierMaster = assetSupplierMaster;
    }

    public AssetDocumentMaster getAssetDocumentMaster() {
        return assetDocumentMaster;
    }

    public AssetFileUploadMasterBean assetDocumentMaster(AssetDocumentMaster assetDocumentMaster) {
        this.assetDocumentMaster = assetDocumentMaster;
        return this;
    }

    public void setAssetDocumentMaster(AssetDocumentMaster assetDocumentMaster) {
        this.assetDocumentMaster = assetDocumentMaster;
    }

    public Boolean getDetailExist() {
        return detailExist;
    }

    public void setDetailExist(Boolean detailExist) {
        this.detailExist = detailExist;
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
        AssetFileUploadMasterBean assetFileUploadMaster = (AssetFileUploadMasterBean) o;
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
