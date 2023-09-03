package io.vamani.application.repository;

import io.vamani.application.domain.AssetWarrantyTypeMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AssetWarrantyTypeMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssetWarrantyTypeMasterRepository extends JpaRepository<AssetWarrantyTypeMaster, Long> {

}
