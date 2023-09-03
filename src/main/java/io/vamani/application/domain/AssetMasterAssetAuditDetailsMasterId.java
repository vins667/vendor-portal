package io.vamani.application.domain;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class AssetMasterAssetAuditDetailsMasterId implements Serializable {
    @Column(name = "asset_masters_id")
    private Long assetMastersId;

    @Column(name = "asset_audit_details_uuid")
    private String assetAuditDetailsUuid;

    public Long getAssetMastersId() {
        return assetMastersId;
    }

    public void setAssetMastersId(Long assetMastersId) {
        this.assetMastersId = assetMastersId;
    }

    public String getAssetAuditDetailsUuid() {
        return assetAuditDetailsUuid;
    }

    public void setAssetAuditDetailsUuid(String assetAuditDetailsUuid) {
        this.assetAuditDetailsUuid = assetAuditDetailsUuid;
    }

    public AssetMasterAssetAuditDetailsMasterId() {
    }

    public AssetMasterAssetAuditDetailsMasterId(Long assetMastersId, String assetAuditDetailsUuid) {
        this.assetMastersId = assetMastersId;
        this.assetAuditDetailsUuid = assetAuditDetailsUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssetMasterAssetAuditDetailsMasterId that = (AssetMasterAssetAuditDetailsMasterId) o;
        return Objects.equals(assetMastersId, that.assetMastersId) &&
            Objects.equals(assetAuditDetailsUuid, that.assetAuditDetailsUuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assetMastersId, assetAuditDetailsUuid);
    }
}
