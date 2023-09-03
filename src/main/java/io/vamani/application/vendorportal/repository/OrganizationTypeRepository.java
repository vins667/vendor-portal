package io.vamani.application.vendorportal.repository;

import io.vamani.application.vendorportal.domain.OrganizationType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;


/**
 * Spring Data  repository for the OrganizationType entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("vendorportalTransactionManager")
@PersistenceContext(name = "vendorportalEntityManagerFactory")
public interface OrganizationTypeRepository extends JpaRepository<OrganizationType, Long> {

}
