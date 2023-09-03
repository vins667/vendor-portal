package io.vamani.application.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "asset_master_asset_audit_details")
public class AssetMasterAssetAuditDetails implements Serializable {

    @Id
    @Column(name = "asset_masters_id")
    private Long assetMastersId;

    @Id
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssetMasterAssetAuditDetails that = (AssetMasterAssetAuditDetails) o;
        return Objects.equals(assetMastersId, that.assetMastersId) &&
            Objects.equals(assetAuditDetailsUuid, that.assetAuditDetailsUuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assetMastersId, assetAuditDetailsUuid);
    }

    @Override
    public String toString() {
        return "AssetMasterAssetAuditDetails{" +
            "assetMastersId=" + assetMastersId +
            ", assetAuditDetailsUuid='" + assetAuditDetailsUuid + '\'' +
            '}';
    }
}
