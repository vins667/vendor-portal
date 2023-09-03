package io.vamani.application.vendorportal.repository;

import io.vamani.application.vendorportal.domain.VendorTaxRegistrationTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;


/**
 * Spring Data  repository for the VendorTaxRegistrationTransaction entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("vendorportalTransactionManager")
@PersistenceContext(name = "vendorportalEntityManagerFactory")
public interface VendorTaxRegistrationTransactionRepository extends JpaRepository<VendorTaxRegistrationTransaction, Long> {

}
