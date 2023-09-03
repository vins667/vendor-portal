package io.vamani.application.audit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import io.vamani.application.audit.domain.SoftwareKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Spring Data  repository for the Catgory entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("auditTransactionManager")
@PersistenceContext(name = "auditEntityManagerFactory")
public interface SoftwareKeyRepository extends JpaRepository<SoftwareKey, Integer>, JpaSpecificationExecutor<SoftwareKey> {
    @Query("select softwareKey from SoftwareKey softwareKey where softwareKey.current = 'y'")
    List<SoftwareKey> findAll();
}
