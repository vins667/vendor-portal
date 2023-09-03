package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Financemonth;
import io.vamani.application.db2.domain.FinancemonthId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.util.List;

@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface FinancemonthRepository extends JpaRepository<Financemonth, FinancemonthId> {
    @Query("select financemonth from Financemonth financemonth where financemonth.id.companycode = ?1 and financemonth.id.businessunitcode = ?2 and ?3 between financemonth.monthstartdate and financemonth.monthenddate")
    List<Financemonth> findAllByBusinessunitcodeAndDate(String companycode, String businessunitcode, Date billDate);
}
