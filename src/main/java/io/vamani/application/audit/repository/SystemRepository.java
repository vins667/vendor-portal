package io.vamani.application.audit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import io.vamani.application.audit.domain.System;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;

/**
 * Spring Data  repository for the Catgory entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("auditTransactionManager")
@PersistenceContext(name = "auditEntityManagerFactory")
public interface SystemRepository extends JpaRepository<System, Integer>, JpaSpecificationExecutor<System> {
    @Query("select system from System system where system.uuid=?1")
    System findByUuid(String uuid);
}
