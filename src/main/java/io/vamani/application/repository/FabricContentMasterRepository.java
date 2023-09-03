package io.vamani.application.repository;

import io.vamani.application.domain.FabricContentMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FabricContentMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FabricContentMasterRepository extends JpaRepository<FabricContentMaster, Long> {
    @Query("select fabricContentMaster from FabricContentMaster fabricContentMaster where fabricContentMaster.code like ?1 or fabricContentMaster.description like ?2")
    Page<FabricContentMaster> findAllByCodeAndDesc(String code, String description, Pageable pageable);
}
