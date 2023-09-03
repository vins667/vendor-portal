package io.vamani.application.model;

import io.vamani.application.domain.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class AssetMasterBean implements Serializable {
    private Long id;

    private String assetCode;

    private String model;

    private String assetTag;

    private LocalDate warrantyDate;

    private LocalDate warrantyEndDate;

    private String poNumber;

    private String invoiceNumber;

    private LocalDate invoiceDate;

    private Long quantity;

    private String createdBy;

    private Instant createdDate;

    private String lastUpdatedBy;

    private Instant lastUpdatedDate;

    private AssetOwnershipMaster assetOwnershipMaster;

    private AssetTypeMaster assetTypeMaster;

    private AssetSubTypeMaster assetSubTypeMaster;

    private AssetSubTypeDetailMaster assetSubTypeDetailMaster;

    private AssetCompanyMaster assetCompanyMaster;

    private AssetSupplierMaster assetSupplierMaster;

    private AssetLocationMaster assetLocationMaster;

    private Set<AssetAuditDetails> assetAuditDetails = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAssetTag() {
        return assetTag;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    public LocalDate getWarrantyDate() {
        return warrantyDate;
    }

    public void setWarrantyDate(LocalDate warrantyDate) {
        this.warrantyDate = warrantyDate;
    }

    public LocalDate getWarrantyEndDate() {
        return warrantyEndDate;
    }

    public void setWarrantyEndDate(LocalDate warrantyEndDate) {
        this.warrantyEndDate = warrantyEndDate;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public AssetOwnershipMaster getAssetOwnershipMaster() {
        return assetOwnershipMaster;
    }

    public void setAssetOwnershipMaster(AssetOwnershipMaster assetOwnershipMaster) {
        this.assetOwnershipMaster = assetOwnershipMaster;
    }

    public AssetTypeMaster getAssetTypeMaster() {
        return assetTypeMaster;
    }

    public void setAssetTypeMaster(AssetTypeMaster assetTypeMaster) {
        this.assetTypeMaster = assetTypeMaster;
    }

    public AssetSubTypeMaster getAssetSubTypeMaster() {
        return assetSubTypeMaster;
    }

    public void setAssetSubTypeMaster(AssetSubTypeMaster assetSubTypeMaster) {
        this.assetSubTypeMaster = assetSubTypeMaster;
    }

    public AssetSubTypeDetailMaster getAssetSubTypeDetailMaster() {
        return assetSubTypeDetailMaster;
    }

    public void setAssetSubTypeDetailMaster(AssetSubTypeDetailMaster assetSubTypeDetailMaster) {
        this.assetSubTypeDetailMaster = assetSubTypeDetailMaster;
    }

    public AssetCompanyMaster getAssetCompanyMaster() {
        return assetCompanyMaster;
    }

    public void setAssetCompanyMaster(AssetCompanyMaster assetCompanyMaster) {
        this.assetCompanyMaster = assetCompanyMaster;
    }

    public AssetSupplierMaster getAssetSupplierMaster() {
        return assetSupplierMaster;
    }

    public void setAssetSupplierMaster(AssetSupplierMaster assetSupplierMaster) {
        this.assetSupplierMaster = assetSupplierMaster;
    }

    public AssetLocationMaster getAssetLocationMaster() {
        return assetLocationMaster;
    }

    public void setAssetLocationMaster(AssetLocationMaster assetLocationMaster) {
        this.assetLocationMaster = assetLocationMaster;
    }

    public Set<AssetAuditDetails> getAssetAuditDetails() {
        return assetAuditDetails;
    }

    public void setAssetAuditDetails(Set<AssetAuditDetails> assetAuditDetails) {
        this.assetAuditDetails = assetAuditDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssetMasterBean that = (AssetMasterBean) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(assetCode, that.assetCode) &&
            Objects.equals(model, that.model) &&
            Objects.equals(assetTag, that.assetTag) &&
            Objects.equals(warrantyDate, that.warrantyDate) &&
            Objects.equals(warrantyEndDate, that.warrantyEndDate) &&
            Objects.equals(poNumber, that.poNumber) &&
            Objects.equals(invoiceNumber, that.invoiceNumber) &&
            Objects.equals(invoiceDate, that.invoiceDate) &&
            Objects.equals(quantity, that.quantity) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(createdDate, that.createdDate) &&
            Objects.equals(lastUpdatedBy, that.lastUpdatedBy) &&
            Objects.equals(lastUpdatedDate, that.lastUpdatedDate) &&
            Objects.equals(assetOwnershipMaster, that.assetOwnershipMaster) &&
            Objects.equals(assetTypeMaster, that.assetTypeMaster) &&
            Objects.equals(assetSubTypeMaster, that.assetSubTypeMaster) &&
            Objects.equals(assetSubTypeDetailMaster, that.assetSubTypeDetailMaster) &&
            Objects.equals(assetCompanyMaster, that.assetCompanyMaster) &&
            Objects.equals(assetSupplierMaster, that.assetSupplierMaster) &&
            Objects.equals(assetLocationMaster, that.assetLocationMaster) &&
            Objects.equals(assetAuditDetails, that.assetAuditDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, assetCode, model, assetTag, warrantyDate, warrantyEndDate, poNumber, invoiceNumber, invoiceDate, quantity, createdBy, createdDate, lastUpdatedBy, lastUpdatedDate, assetOwnershipMaster, assetTypeMaster, assetSubTypeMaster, assetSubTypeDetailMaster, assetCompanyMaster, assetSupplierMaster, assetLocationMaster, assetAuditDetails);
    }

    @Override
    public String toString() {
        return "AssetMasterBean{" +
            "id=" + id +
            ", assetCode='" + assetCode + '\'' +
            ", model='" + model + '\'' +
            ", assetTag='" + assetTag + '\'' +
            ", warrantyDate=" + warrantyDate +
            ", warrantyEndDate=" + warrantyEndDate +
            ", poNumber='" + poNumber + '\'' +
            ", invoiceNumber='" + invoiceNumber + '\'' +
            ", invoiceDate=" + invoiceDate +
            ", quantity=" + quantity +
            ", createdBy='" + createdBy + '\'' +
            ", createdDate=" + createdDate +
            ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
            ", lastUpdatedDate=" + lastUpdatedDate +
            ", assetOwnershipMaster=" + assetOwnershipMaster +
            ", assetTypeMaster=" + assetTypeMaster +
            ", assetSubTypeMaster=" + assetSubTypeMaster +
            ", assetSubTypeDetailMaster=" + assetSubTypeDetailMaster +
            ", assetCompanyMaster=" + assetCompanyMaster +
            ", assetSupplierMaster=" + assetSupplierMaster +
            ", assetLocationMaster=" + assetLocationMaster +
            ", assetAuditDetails=" + assetAuditDetails +
            '}';
    }
}
