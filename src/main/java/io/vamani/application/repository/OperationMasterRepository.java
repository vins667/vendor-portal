package io.vamani.application.repository;

import io.vamani.application.domain.OperationMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OperationMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OperationMasterRepository extends JpaRepository<OperationMaster, Long> {

}
