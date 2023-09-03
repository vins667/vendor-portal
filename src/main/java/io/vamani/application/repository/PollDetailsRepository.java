package io.vamani.application.repository;

import io.vamani.application.domain.PollDetails;
import io.vamani.application.domain.PollMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.Set;


/**
 * Spring Data  repository for the PollDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PollDetailsRepository extends JpaRepository<PollDetails, Long> {
    Set<PollDetails> findAllByPollMasterOrderByIdAsc(PollMaster pollMaster);

    @Transactional
    @Modifying
    void deleteAllByPollMasterId(long id);
}
