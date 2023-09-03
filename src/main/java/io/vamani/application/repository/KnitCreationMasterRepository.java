package io.vamani.application.repository;

import io.vamani.application.domain.KnitCreationMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the KnitCreationMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KnitCreationMasterRepository extends JpaRepository<KnitCreationMaster, Long> {
    @Query("select knitCreationMaster from KnitCreationMaster knitCreationMaster where knitCreationMaster.description = ?1")
    KnitCreationMaster findByDescription(String description);

    @Query("select knitCreationMaster from KnitCreationMaster knitCreationMaster where knitCreationMaster.code like ?1 or knitCreationMaster.description like ?2")
    Page<KnitCreationMaster> findAllByCodeAndDesc(String code, String description, Pageable pageable);
}
