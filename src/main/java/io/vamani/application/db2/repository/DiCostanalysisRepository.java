package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.DiCostanalysis;
import io.vamani.application.db2.domain.DiCostanalysisId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;

@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface DiCostanalysisRepository extends JpaRepository<DiCostanalysis, DiCostanalysisId> {
}
