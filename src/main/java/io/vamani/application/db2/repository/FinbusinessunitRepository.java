package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Finbusinessunit;
import io.vamani.application.db2.domain.FinbusinessunitId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;

@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface FinbusinessunitRepository extends JpaRepository<Finbusinessunit, FinbusinessunitId> {
    @Query("select finbusinessunit from Finbusinessunit finbusinessunit where finbusinessunit.id.companycode = ?1 and finbusinessunit.groupflag=0 order by finbusinessunit.id.code")
    List<Finbusinessunit> findAllByCompanycode(String companycode);
}
