package io.vamani.application.repository;

import io.vamani.application.domain.AssetSupplierMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AssetSupplierMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssetSupplierMasterRepository extends JpaRepository<AssetSupplierMaster, Long> {

}
