package io.vamani.application.vendorportal.repository;

import io.vamani.application.vendorportal.domain.TurnoverMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;


/**
 * Spring Data  repository for the TurnoverMaster entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("vendorportalTransactionManager")
@PersistenceContext(name = "vendorportalEntityManagerFactory")
public interface TurnoverMasterRepository extends JpaRepository<TurnoverMaster, Long> {

}
