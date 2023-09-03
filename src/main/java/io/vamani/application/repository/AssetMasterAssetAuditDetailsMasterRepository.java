package io.vamani.application.repository;

import io.vamani.application.domain.AssetLocationMaster;
import io.vamani.application.domain.AssetMasterAssetAuditDetailsMaster;
import io.vamani.application.domain.AssetMasterAssetAuditDetailsMasterId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AssetFileUploadMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssetMasterAssetAuditDetailsMasterRepository extends JpaRepository<AssetMasterAssetAuditDetailsMaster, AssetMasterAssetAuditDetailsMasterId> {

}
