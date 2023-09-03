package io.vamani.application.repository;

import io.vamani.application.domain.CompanyMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;


/**
 * Spring Data  repository for the CompanyMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CompanyMasterRepository extends JpaRepository<CompanyMaster, Long> {

}
