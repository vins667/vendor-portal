package io.vamani.application.repository;

import io.vamani.application.domain.AssetLocationMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AssetLocationMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssetLocationMasterRepository extends JpaRepository<AssetLocationMaster, Long> {

}
