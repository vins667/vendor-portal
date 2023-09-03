package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.DiStocktransactionissue;
import io.vamani.application.db2.domain.DiStocktransactionissueId;
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
public interface DiStocktransactionissueRepository extends JpaRepository<DiStocktransactionissue, DiStocktransactionissueId> {
    @Query(value = "SELECT ST.TRANSACTIONNUMBER, ST.TRANSACTIONDETAILNUMBER, ST.TRANSACTIONDATE, ST.TRANSACTIONTIME, ST.ORDERCOUNTERCODE,"
        + " ST.ORDERCODE, ST.PRODUCTIONORDERCODE, ST.ITEMTYPECOMPANYCODE, ST.ITEMTYPECODE, ST.DECOSUBCODE01,"
        + " ST.DECOSUBCODE02, ST.DECOSUBCODE03, ST.DECOSUBCODE04, ST.DECOSUBCODE05, ST.DECOSUBCODE06,"
        + " ST.DECOSUBCODE07, ST.DECOSUBCODE08, ST.DECOSUBCODE09, ST.DECOSUBCODE10, ST.USERPRIMARYUOMCODE, ST.USERPRIMARYQUANTITY"
        + " FROM STOCKTRANSACTION ST"
        + " WHERE ST.TEMPLATECODE='M04' AND ST.TRANSACTIONDATE<'2021-07-15' ORDER BY ST.TRANSACTIONNUMBER"
        , nativeQuery = true)
    List<Object[]> fetchIssuesByTransactionDate();

    @Query("select distocktransactionissue from DiStocktransactionissue distocktransactionissue where distocktransactionissue.countercode = ?1 and distocktransactionissue.code = ?2 and distocktransactionissue.productionordercode = ?3")
    List<DiStocktransactionissue> findDiStocktransactionissueByDemandCode(String countercode, String code, String productionordercode);

    @Query("select distocktransactionissue from DiStocktransactionissue distocktransactionissue where distocktransactionissue.productionordercode = ?1 and distocktransactionissue.itemtypecode = ?2 and distocktransactionissue.decosubcode01 = ?3 and distocktransactionissue.decosubcode02 = ?4 and distocktransactionissue.decosubcode03 = ?5 and distocktransactionissue.decosubcode04 = ?6 and distocktransactionissue.decosubcode05 = ?7 and distocktransactionissue.decosubcode06 = ?8 and distocktransactionissue.decosubcode07 = ?9 and distocktransactionissue.decosubcode08 = ?10 and distocktransactionissue.decosubcode09 = ?11 and distocktransactionissue.decosubcode10 = ?12")
    List<DiStocktransactionissue> findDiStocktransactionissueByProductionOrderCode(String productionordercode, String itemtypecode, String subcode01, String subcode02, String subcode03, String subcode04, String subcode05, String subcode06, String subcode07, String subcode08, String subcode09, String subcode10);
}
