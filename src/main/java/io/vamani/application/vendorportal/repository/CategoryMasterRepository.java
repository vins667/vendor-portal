package io.vamani.application.vendorportal.repository;

import io.vamani.application.vendorportal.domain.CategoryMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CategoryMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategoryMasterRepository extends JpaRepository<CategoryMaster, Long> {

}
