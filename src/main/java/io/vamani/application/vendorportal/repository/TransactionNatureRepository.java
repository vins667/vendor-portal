package io.vamani.application.vendorportal.repository;

import io.vamani.application.vendorportal.domain.TransactionNature;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;


/**
 * Spring Data  repository for the TransactionNature entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("vendorportalTransactionManager")
@PersistenceContext(name = "vendorportalEntityManagerFactory")
public interface TransactionNatureRepository extends JpaRepository<TransactionNature, Long> {

}
