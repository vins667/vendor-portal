package io.vamani.application.repository;

import io.vamani.application.domain.AssetAuditDetails;
import io.vamani.application.domain.AssetMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


/**
 * Spring Data  repository for the AssetMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssetMasterRepository extends JpaRepository<AssetMaster, Long> {

    @Query("select assetMaster from AssetMaster assetMaster join assetMaster.assetAuditDetails assetAuditDetails where assetAuditDetails.uuid=?1")
    List<AssetMaster> findByUUID(String uuid);

    @Query("select asset_master from AssetMaster asset_master left join fetch asset_master.assetAuditDetails where asset_master.id =:id")
    Optional<AssetMaster> findOneWithEagerRelationships(@Param("id") Long id);

    @Query("select b from AssetMasterAssetAuditDetails a, AssetAuditDetails b where a.assetAuditDetailsUuid = b.uuid and  a.assetMastersId=?1 and (b.createdDate, b.uuid) in(select max(b.createdDate), b.uuid from AssetMasterAssetAuditDetails a, AssetAuditDetails b where a.assetAuditDetailsUuid = b.uuid and a.assetMastersId=?2 group by b.uuid)")
    Set<AssetAuditDetails> findByAssetId(Long assetMasterId1, Long assetMasterId2);
}
