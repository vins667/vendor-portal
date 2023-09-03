package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.BankReconciliationdetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;

@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface BankReconciliationDetailRepository extends JpaRepository<BankReconciliationdetail, Long> {
}
