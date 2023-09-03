package io.vamani.application.repository;

import io.vamani.application.domain.AssetFileUploadMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the AssetFileUploadMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssetFileUploadMasterRepository extends JpaRepository<AssetFileUploadMaster, Long> {
    @Query("select assetFileUploadMaster from AssetFileUploadMaster assetFileUploadMaster where assetFileUploadMaster.invoiceNumber = ?1 and assetFileUploadMaster.assetSupplierMaster.id = ?2 order by assetFileUploadMaster.id")
    List<AssetFileUploadMaster> findAllByInvoiceNumberAndAssetSupplierMasterId(String invoiceNumber, Long supplierId);
}
