package io.vamani.application.repository;

import io.vamani.application.domain.AssetSubTypeDetailMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the AssetSubTypeDetailMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssetSubTypeDetailMasterRepository extends JpaRepository<AssetSubTypeDetailMaster, Long> {
    @Query("select assetSubTypeDetailMaster from AssetSubTypeDetailMaster assetSubTypeDetailMaster where assetSubTypeDetailMaster.assetSubTypeMaster.id=?1 order by assetSubTypeDetailMaster.description")
    List<AssetSubTypeDetailMaster> findByAssetSubTypeMaster(Long assetTypeMasterId);
}
