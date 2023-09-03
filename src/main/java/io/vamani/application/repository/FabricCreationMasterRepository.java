package io.vamani.application.repository;

import io.vamani.application.domain.FabricCreationMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FabricCreationMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FabricCreationMasterRepository extends JpaRepository<FabricCreationMaster, Long> {
    @Query("select fabricCreationMaster from FabricCreationMaster fabricCreationMaster where fabricCreationMaster.description = ?1")
    FabricCreationMaster findByDescription(String description);

    @Query("select fabricCreationMaster from FabricCreationMaster fabricCreationMaster where fabricCreationMaster.code like ?1 or fabricCreationMaster.description like ?2")
    Page<FabricCreationMaster> findAllByCodeAndDesc(String code, String description, Pageable pageable);

    @Query(value = "select COALESCE(max(TO_NUMBER(substring(code,3,6), '99G999D9S') ),'0') +1 ctr from fabric_creation_master where substring(code,1,2) = ?1", nativeQuery = true)
    Long findMaxSeries(String subtractCode);
}
