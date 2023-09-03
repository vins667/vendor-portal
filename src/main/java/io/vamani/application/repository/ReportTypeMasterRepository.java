package io.vamani.application.repository;

import io.vamani.application.domain.ReportTypeMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ReportTypeMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReportTypeMasterRepository extends JpaRepository<ReportTypeMaster, Long> {

}
