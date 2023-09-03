package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Productionprogressimport;
import io.vamani.application.db2.domain.ProductionprogressimportId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;


@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface ProductionprogressimportRepository extends JpaRepository<Productionprogressimport, ProductionprogressimportId> {
    @Query(value = "select next value for di_productionprogresssimport_seq from SYSIBM.SYSDUMMY1", nativeQuery = true)
    Long getNextSequence();
}
