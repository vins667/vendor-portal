package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.WhereJoinTable;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A AssetMaster.
 */
@Entity
@Table(name = "asset_master")
public class AssetMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "assetMasterSeq", sequenceName = "asset_master_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "assetMasterSeq")
    private Long id;

    @Size(max = 100)
    @Column(name = "asset_code", length = 100)
    private String assetCode;

    @Size(max = 100)
    @Column(name = "model", length = 100)
    private String model;

    @Size(max = 100)
    @Column(name = "asset_tag", length = 100)
    private String assetTag;

    @Column(name = "warranty_date")
    private LocalDate warrantyDate;

    @Column(name = "warranty_end_date")
    private LocalDate warrantyEndDate;

    @Size(max = 100)
    @Column(name = "po_number", length = 100)
    private String poNumber;

    @Size(max = 100)
    @Column(name = "invoice_number", length = 100)
    private String invoiceNumber;

    @Column(name = "invoice_date")
    private LocalDate invoiceDate;

    @Column(name = "quantity")
    private Long quantity;

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
    @JoinColumn(name = "asset_ownership_master_id")
    @NotNull
    @JsonIgnoreProperties("")
    private AssetOwnershipMaster assetOwnershipMaster;

    @ManyToOne(optional = false)
    @JoinColumn(name = "asset_type_master_id")
    @NotNull
    @JsonIgnoreProperties("")
    private AssetTypeMaster assetTypeMaster;

    @ManyToOne(optional = false)
    @JoinColumn(name = "asset_sub_type_master_id")
    @NotNull
    @JsonIgnoreProperties("")
    private AssetSubTypeMaster assetSubTypeMaster;

    @ManyToOne(optional = false)
    @JoinColumn(name = "asset_sub_type_detail_master_id")
    @NotNull
    @JsonIgnoreProperties("")
    private AssetSubTypeDetailMaster assetSubTypeDetailMaster;

    @ManyToOne(optional = false)
    @JoinColumn(name = "asset_company_master_id")
    @NotNull
    @JsonIgnoreProperties("")
    private AssetCompanyMaster assetCompanyMaster;

    @ManyToOne(optional = false)
    @JoinColumn(name = "asset_supplier_master_id")
    @NotNull
    @JsonIgnoreProperties("")
    private AssetSupplierMaster assetSupplierMaster;

    @ManyToOne(optional = false)
    @JoinColumn(name = "asset_location_master_id")
    @NotNull
    @JsonIgnoreProperties("")
    private AssetLocationMaster assetLocationMaster;

    @ManyToMany
    @NotNull
    @JoinTable(name = "asset_master_asset_audit_details",
        joinColumns = @JoinColumn(name = "asset_masters_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "asset_audit_details_uuid", referencedColumnName = "uuid"))
        private Set<AssetAuditDetails> assetAuditDetails = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public AssetMaster assetCode(String assetCode) {
        this.assetCode = assetCode;
        return this;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getModel() {
        return model;
    }

    public AssetMaster model(String model) {
        this.model = model;
        return this;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAssetTag() {
        return assetTag;
    }

    public AssetMaster assetTag(String assetTag) {
        this.assetTag = assetTag;
        return this;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    public LocalDate getWarrantyDate() {
        return warrantyDate;
    }

    public AssetMaster warrantyDate(LocalDate warrantyDate) {
        this.warrantyDate = warrantyDate;
        return this;
    }

    public void setWarrantyDate(LocalDate warrantyDate) {
        this.warrantyDate = warrantyDate;
    }

    public LocalDate getWarrantyEndDate() {
        return warrantyEndDate;
    }

    public AssetMaster warrantyEndDate(LocalDate warrantyEndDate) {
        this.warrantyEndDate = warrantyEndDate;
        return this;
    }

    public void setWarrantyEndDate(LocalDate warrantyEndDate) {
        this.warrantyEndDate = warrantyEndDate;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public AssetMaster poNumber(String poNumber) {
        this.poNumber = poNumber;
        return this;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public AssetMaster invoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
        return this;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public AssetMaster invoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
        return this;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public AssetMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public AssetMaster createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public AssetMaster lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public AssetMaster lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public AssetOwnershipMaster getAssetOwnershipMaster() {
        return assetOwnershipMaster;
    }

    public AssetMaster assetOwnershipMaster(AssetOwnershipMaster assetOwnershipMaster) {
        this.assetOwnershipMaster = assetOwnershipMaster;
        return this;
    }

    public void setAssetOwnershipMaster(AssetOwnershipMaster assetOwnershipMaster) {
        this.assetOwnershipMaster = assetOwnershipMaster;
    }

    public AssetTypeMaster getAssetTypeMaster() {
        return assetTypeMaster;
    }

    public AssetMaster assetTypeMaster(AssetTypeMaster assetTypeMaster) {
        this.assetTypeMaster = assetTypeMaster;
        return this;
    }

    public void setAssetTypeMaster(AssetTypeMaster assetTypeMaster) {
        this.assetTypeMaster = assetTypeMaster;
    }

    public AssetSubTypeMaster getAssetSubTypeMaster() {
        return assetSubTypeMaster;
    }

    public AssetMaster assetSubTypeMaster(AssetSubTypeMaster assetSubTypeMaster) {
        this.assetSubTypeMaster = assetSubTypeMaster;
        return this;
    }

    public void setAssetSubTypeMaster(AssetSubTypeMaster assetSubTypeMaster) {
        this.assetSubTypeMaster = assetSubTypeMaster;
    }

    public AssetSubTypeDetailMaster getAssetSubTypeDetailMaster() {
        return assetSubTypeDetailMaster;
    }

    public AssetMaster assetSubTypeDetailMaster(AssetSubTypeDetailMaster assetSubTypeDetailMaster) {
        this.assetSubTypeDetailMaster = assetSubTypeDetailMaster;
        return this;
    }

    public void setAssetSubTypeDetailMaster(AssetSubTypeDetailMaster assetSubTypeDetailMaster) {
        this.assetSubTypeDetailMaster = assetSubTypeDetailMaster;
    }

    public AssetCompanyMaster getAssetCompanyMaster() {
        return assetCompanyMaster;
    }

    public AssetMaster assetCompanyMaster(AssetCompanyMaster assetCompanyMaster) {
        this.assetCompanyMaster = assetCompanyMaster;
        return this;
    }

    public void setAssetCompanyMaster(AssetCompanyMaster assetCompanyMaster) {
        this.assetCompanyMaster = assetCompanyMaster;
    }

    public AssetSupplierMaster getAssetSupplierMaster() {
        return assetSupplierMaster;
    }

    public AssetMaster assetSupplierMaster(AssetSupplierMaster assetSupplierMaster) {
        this.assetSupplierMaster = assetSupplierMaster;
        return this;
    }

    public void setAssetSupplierMaster(AssetSupplierMaster assetSupplierMaster) {
        this.assetSupplierMaster = assetSupplierMaster;
    }

    public AssetLocationMaster getAssetLocationMaster() {
        return assetLocationMaster;
    }

    public AssetMaster assetLocationMaster(AssetLocationMaster assetLocationMaster) {
        this.assetLocationMaster = assetLocationMaster;
        return this;
    }

    public void setAssetLocationMaster(AssetLocationMaster assetLocationMaster) {
        this.assetLocationMaster = assetLocationMaster;
    }

    public Set<AssetAuditDetails> getAssetAuditDetails() {
        return assetAuditDetails;
    }

    public AssetMaster assetAuditDetails(Set<AssetAuditDetails> assetAuditDetails) {
        this.assetAuditDetails = assetAuditDetails;
        return this;
    }

    public void setAssetAuditDetails(Set<AssetAuditDetails> assetAuditDetails) {
        this.assetAuditDetails = assetAuditDetails;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
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
        AssetMaster assetMaster = (AssetMaster) o;
        if (assetMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), assetMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AssetMaster{" +
            "id=" + getId() +
            ", assetCode='" + getAssetCode() + "'" +
            ", model='" + getModel() + "'" +
            ", assetTag='" + getAssetTag() + "'" +
            ", warrantyDate='" + getWarrantyDate() + "'" +
            ", warrantyEndDate='" + getWarrantyEndDate() + "'" +
            ", poNumber='" + getPoNumber() + "'" +
            ", invoiceNumber='" + getInvoiceNumber() + "'" +
            ", invoiceDate='" + getInvoiceDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            "}";
    }
}
