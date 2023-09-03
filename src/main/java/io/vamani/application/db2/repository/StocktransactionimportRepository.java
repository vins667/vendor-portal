package io.vamani.application.db2.repository;


import io.vamani.application.db2.domain.Stocktransactionimport;
import io.vamani.application.db2.domain.StocktransactionimportId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.time.Instant;
import java.util.List;

@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface StocktransactionimportRepository extends JpaRepository<Stocktransactionimport, StocktransactionimportId> {
    @Query("select stocktransactionimport from Stocktransactionimport stocktransactionimport where stocktransactionimport.id.transactionnumber = ?1")
    List<Stocktransactionimport> findAllByTransactionnumber(String transactionnumber);

    @Query(value = "select next value for di_stocktransaction_seq from SYSIBM.SYSDUMMY1", nativeQuery = true)
    Long getNextSequence();


    @Modifying
    @Transactional
    @Query("update Stocktransactionimport stocktransactionimport set stocktransactionimport.importstatus=1 where stocktransactionimport.id.transactionnumber = ?1")
    void updateStockFlag(String transactionnumber);
}
