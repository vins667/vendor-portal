package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Finfinancialyear;
import io.vamani.application.db2.domain.FinfinancialyearId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;



@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface FinfinancialyearRepository extends JpaRepository<Finfinancialyear, FinfinancialyearId> {
    @Override
    @Query("select finfinancialyear from Finfinancialyear finfinancialyear order by finfinancialyear.id.code desc")
    List<Finfinancialyear> findAll();

    @Query("select financialyear from Finfinancialyear financialyear where financialyear.id.code=?1")
    Finfinancialyear findMonth(Long code);

    @Query("select financialyear from Finfinancialyear financialyear where ?1 between financialyear.fromdate and financialyear.todate")
    Finfinancialyear findByDate(Date date);
}
