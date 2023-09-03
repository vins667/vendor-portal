package io.vamani.application.repository;

import io.vamani.application.domain.PollEmployeeDetails;
import io.vamani.application.domain.PollMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * Spring Data  repository for the PollEmployeeDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PollEmployeeDetailsRepository extends JpaRepository<PollEmployeeDetails, Long> {

    PollEmployeeDetails findAllByPollMasterAndCreatedBy(PollMaster pollMaster, String createdBy);
}
