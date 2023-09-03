package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Fincostcenterdetail;
import io.vamani.application.db2.domain.FincostcenterdetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;

@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface FincostcenterdetailRepository extends JpaRepository<Fincostcenterdetail, FincostcenterdetailId> {
    @Query("select fincostcenterdetail.id.finbvsprofitprofitcentercode from Fincostcenterdetail fincostcenterdetail where fincostcenterdetail.id.finbvsprofitbusinessunitcode = ?1 and fincostcenterdetail.id.costcentercode = ?2")
    String fetchProfitCenter(String businessunitcode, String costcentercode);
}
