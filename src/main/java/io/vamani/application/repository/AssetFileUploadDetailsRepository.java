package io.vamani.application.repository;

import io.vamani.application.domain.AssetFileUploadDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the AssetFileUploadDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssetFileUploadDetailsRepository extends JpaRepository<AssetFileUploadDetails, Long> {
    @Query("select assetFileUploadDetails from AssetFileUploadDetails assetFileUploadDetails where assetFileUploadDetails.assetMaster.id=?1 order by assetFileUploadDetails.id")
    List<AssetFileUploadDetails> findAllByAssetMasterId(Long assetMasterId);

    @Query("select assetFileUploadDetails from AssetFileUploadDetails assetFileUploadDetails where assetFileUploadDetails.assetFileUploadMaster.id=?1 order by assetFileUploadDetails.id")
    List<AssetFileUploadDetails> findAllByAssetFileUploadMasterId(Long assetFileUploadMasterId);
}
