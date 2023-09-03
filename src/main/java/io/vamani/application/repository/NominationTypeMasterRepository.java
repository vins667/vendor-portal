package io.vamani.application.repository;

import io.vamani.application.domain.NominationTypeMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the NominationTypeMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NominationTypeMasterRepository extends JpaRepository<NominationTypeMaster, Long> {
    @Query("select nominationTypeMaster from NominationTypeMaster nominationTypeMaster order by nominationTypeMaster.description")
    List<NominationTypeMaster> orderedAll();
}
