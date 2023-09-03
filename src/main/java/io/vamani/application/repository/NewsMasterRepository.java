package io.vamani.application.repository;

import io.vamani.application.domain.NewsMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;


/**
 * Spring Data  repository for the NewsMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NewsMasterRepository extends JpaRepository<NewsMaster, Long> {

}
