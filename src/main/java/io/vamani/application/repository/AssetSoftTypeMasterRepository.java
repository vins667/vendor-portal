package io.vamani.application.repository;

import io.vamani.application.domain.AssetSoftTypeMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AssetSoftTypeMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssetSoftTypeMasterRepository extends JpaRepository<AssetSoftTypeMaster, Long> {

}
