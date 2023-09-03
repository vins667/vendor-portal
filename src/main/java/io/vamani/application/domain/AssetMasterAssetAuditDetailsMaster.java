package io.vamani.application.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "asset_master_asset_audit_details")
public class AssetMasterAssetAuditDetailsMaster implements Serializable {
    @EmbeddedId
    private AssetMasterAssetAuditDetailsMasterId id;

    public AssetMasterAssetAuditDetailsMasterId getId() {
        return id;
    }

    public void setId(AssetMasterAssetAuditDetailsMasterId id) {
        this.id = id;
    }
}
