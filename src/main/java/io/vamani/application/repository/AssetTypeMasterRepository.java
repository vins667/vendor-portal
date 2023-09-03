package io.vamani.application.repository;

import io.vamani.application.domain.AssetTypeMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AssetTypeMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssetTypeMasterRepository extends JpaRepository<AssetTypeMaster, Long> {

}
