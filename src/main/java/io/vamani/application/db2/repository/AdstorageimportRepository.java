package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Adstorageimport;
import io.vamani.application.db2.domain.AdstorageimportId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;

/**
 * Spring Data  repository for the Adstorage entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface AdstorageimportRepository extends JpaRepository<Adstorageimport, AdstorageimportId> {
    @Query(value = "select next value for di_additionalimport_seq from SYSIBM.SYSDUMMY1", nativeQuery = true)
    Long getNextSequence();
}
