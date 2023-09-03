package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Adstoragebean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;

/**
 * Spring Data  repository for the Adstoragebean entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface AdstoragebeanRepository extends JpaRepository<Adstoragebean,  Long> {

    @Query(value = "select next value for di_adstoragebean_seq from SYSIBM.SYSDUMMY1", nativeQuery = true)
    Long getNextSeriesId();
}
