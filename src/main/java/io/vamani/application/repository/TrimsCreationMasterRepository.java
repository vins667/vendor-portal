package io.vamani.application.repository;

import io.vamani.application.domain.TrimsCreationMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TrimsCreationMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TrimsCreationMasterRepository extends JpaRepository<TrimsCreationMaster, Long> {
    @Query("select trimsCreationMaster from TrimsCreationMaster trimsCreationMaster where trimsCreationMaster.description = ?1")
    TrimsCreationMaster findByDescription(String description);

    @Query("select trimsCreationMaster from TrimsCreationMaster trimsCreationMaster where trimsCreationMaster.code like ?1 or trimsCreationMaster.description like ?2")
    Page<TrimsCreationMaster> findAllByCodeAndDesc(String code, String description, Pageable pageable);

    @Query(value = "select COALESCE(max(TO_NUMBER(substring(code,3,6), '99G999D9S') ),'0') +1 ctr from trims_creation_master where substring(code,1,2) = ?1", nativeQuery = true)
    Long findMaxSeries(String subtractCode);
}
