package io.vamani.application.repository;

import io.vamani.application.domain.LanguageMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the LanguageMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LanguageMasterRepository extends JpaRepository<LanguageMaster, Long> {
    @Query("select languageMaster from LanguageMaster languageMaster order by languageMaster.description")
    List<LanguageMaster> orderedAll();
}
