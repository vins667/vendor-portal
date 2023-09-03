package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A AssetFileUploadDetails.
 */
@Entity
@Table(name = "asset_file_upload_details")
public class AssetFileUploadDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="assetFileUploadDetailsSeq", sequenceName="asset_file_upload_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="assetFileUploadDetailsSeq")
    private Long id;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "asset_file_upload_master_id")
    @NotNull
    @JsonIgnoreProperties("")
    private AssetFileUploadMaster assetFileUploadMaster;

    @ManyToOne(optional = false)
    @JoinColumn(name = "asset_master_id")
    @NotNull
    @JsonIgnoreProperties("")
    private AssetMaster assetMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public AssetFileUploadDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public AssetFileUploadDetails createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public AssetFileUploadMaster getAssetFileUploadMaster() {
        return assetFileUploadMaster;
    }

    public AssetFileUploadDetails assetFileUploadMaster(AssetFileUploadMaster assetFileUploadMaster) {
        this.assetFileUploadMaster = assetFileUploadMaster;
        return this;
    }

    public void setAssetFileUploadMaster(AssetFileUploadMaster assetFileUploadMaster) {
        this.assetFileUploadMaster = assetFileUploadMaster;
    }

    public AssetMaster getAssetMaster() {
        return assetMaster;
    }

    public AssetFileUploadDetails assetMaster(AssetMaster assetMaster) {
        this.assetMaster = assetMaster;
        return this;
    }

    public void setAssetMaster(AssetMaster assetMaster) {
        this.assetMaster = assetMaster;
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
        AssetFileUploadDetails assetFileUploadDetails = (AssetFileUploadDetails) o;
        if (assetFileUploadDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), assetFileUploadDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AssetFileUploadDetails{" +
            "id=" + getId() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}
