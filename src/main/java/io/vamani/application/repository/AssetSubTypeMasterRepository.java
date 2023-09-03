package io.vamani.application.repository;

import io.vamani.application.domain.AssetSubTypeMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the AssetSubTypeMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssetSubTypeMasterRepository extends JpaRepository<AssetSubTypeMaster, Long> {
    @Query("select assetSubTypeMaster from AssetSubTypeMaster assetSubTypeMaster where assetSubTypeMaster.assetTypeMaster.id=?1 order by assetSubTypeMaster.description")
    List<AssetSubTypeMaster> findByAssetTypeMaster(Long assetTypeMasterId);
}
