package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.DiStocktransactionreciept;
import io.vamani.application.db2.domain.DiStocktransactionrecieptId;
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
public interface DiStocktransactionrecieptRepository extends JpaRepository<DiStocktransactionreciept, DiStocktransactionrecieptId> {
    @Query(value = "SELECT ST.TRANSACTIONNUMBER, ST.TRANSACTIONDETAILNUMBER, ST.TRANSACTIONDATE, ST.TRANSACTIONTIME, ST.ORDERCOUNTERCODE,"
        +" ST.ORDERCODE, ST.PRODUCTIONORDERCODE, PR.ITEMTYPEAFICOMPANYCODE, PR.ITEMTYPEAFICODE, PR.SUBCODE01, PR.SUBCODE02, PR.SUBCODE03,"
        +" PR.SUBCODE04, PR.SUBCODE05, PR.SUBCODE06, PR.SUBCODE07, PR.SUBCODE08, PR.SUBCODE09, PR.SUBCODE10,"
        +" PR.USERPRIMARYUOMCODE, SUM(PR.USERPRIMARYQUANTITY) RESERVATIONQUANTITY,"
        +" MAX(ST.USERPRIMARYQUANTITY) STOCKQUANTITY, SUM(PD.USERPRIMARYQUANTITY) DEMANDQUANTITY"
        +" FROM STOCKTRANSACTION ST, PRODUCTIONRESERVATION PR, PRODUCTIONDEMAND PD"
        +" WHERE ST.ORDERCOUNTERCODE = PR.ORDERCOUNTERCODE AND"
        +" ST.ORDERCODE = PR.ORDERCODE AND PR.ORDERCOUNTERCODE = PD.COUNTERCODE AND"
        +" PR.ORDERCODE = PD.CODE AND ST.TEMPLATECODE='M05' AND ST.TRANSACTIONDATE<'2021-07-15'"
        +" GROUP BY ST.TRANSACTIONNUMBER, ST.TRANSACTIONDETAILNUMBER, ST.TRANSACTIONDATE, ST.TRANSACTIONTIME, ST.ORDERCOUNTERCODE,"
        +" ST.ORDERCODE, ST.PRODUCTIONORDERCODE, PR.ITEMTYPEAFICOMPANYCODE, PR.ITEMTYPEAFICODE, PR.SUBCODE01, PR.SUBCODE02, PR.SUBCODE03,"
        +" PR.SUBCODE04, PR.SUBCODE05, PR.SUBCODE06, PR.SUBCODE07, PR.SUBCODE08, PR.SUBCODE09, PR.SUBCODE10, PR.USERPRIMARYUOMCODE"
        , nativeQuery = true)
    List<Object[]> fetchRecieptByTransactionDate();
}
