package io.vamani.application.repository;

import io.vamani.application.domain.InstituteMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the InstituteMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InstituteMasterRepository extends JpaRepository<InstituteMaster, Long> {
    @Query("select instituteMaster from InstituteMaster instituteMaster order by instituteMaster.description")
    List<InstituteMaster> orderedAll();
}
