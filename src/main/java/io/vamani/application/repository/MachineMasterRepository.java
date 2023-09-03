package io.vamani.application.repository;

import io.vamani.application.domain.MachineMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MachineMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MachineMasterRepository extends JpaRepository<MachineMaster, Long> {

}
