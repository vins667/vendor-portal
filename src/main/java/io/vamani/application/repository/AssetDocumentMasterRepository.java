package io.vamani.application.repository;

import io.vamani.application.domain.AssetDocumentMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AssetDocumentMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssetDocumentMasterRepository extends JpaRepository<AssetDocumentMaster, Long> {

}
