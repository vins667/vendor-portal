package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Adstorage;
import io.vamani.application.db2.domain.AdstorageId;
import org.springframework.data.jpa.repository.JpaRepository;
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
public interface AdstorageRepository extends JpaRepository<Adstorage, AdstorageId> {
}
