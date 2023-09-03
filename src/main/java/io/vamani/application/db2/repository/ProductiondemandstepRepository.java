package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Productiondemand;
import io.vamani.application.db2.domain.Productiondemandstep;
import io.vamani.application.db2.domain.ProductiondemandstepId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public interface ProductiondemandstepRepository extends JpaRepository<Productiondemandstep, ProductiondemandstepId> {
    @Query("SELECT SUM(productiondemandstep.finaluserprimaryquantity), productiondemand.subcode08, productiondemand.subcode09, productiondemand.subcode10, sizes.sequence" +
        " FROM Productiondemandstep productiondemandstep INNER JOIN Productiondemand productiondemand on" +
        " productiondemandstep.id.productiondemandcompanycode = productiondemand.id.companycode and" +
        " productiondemandstep.id.productiondemandcountercode = productiondemand.id.countercode and" +
        " productiondemandstep.id.productiondemandcode = productiondemand.id.code inner join Sizes sizes on" +
        " productiondemand.id.companycode=sizes.id.sizestypecompanycode and productiondemand.subcode10=sizes.id.code and" +
        " sizes.id.sizestypecode= ?1" +
        " where (productiondemandstep.id.productiondemandcompanycode, productiondemandstep.id.productiondemandcountercode, productiondemandstep.id.productiondemandcode, productiondemandstep.id.stepnumber) in(" +
        " select productiondemandstep.id.productiondemandcompanycode, productiondemandstep.id.productiondemandcountercode, productiondemandstep.id.productiondemandcode, min(productiondemandstep.id.stepnumber) " +
        " from Productiondemandstep productiondemandstep where productiondemandstep.id.productiondemandcompanycode=?2 and productiondemandstep.productionordercode=?3 " +
        " group by productiondemandstep.id.productiondemandcompanycode, productiondemandstep.id.productiondemandcountercode, productiondemandstep.id.productiondemandcode)" +
        " group by productiondemand.subcode08, productiondemand.subcode09, productiondemand.subcode10,sizes.sequence order by sizes.sequence")
    List<Object[]> getFinalPrimaryQuantity(String sizestypecode, String productiondemandcompanycode, String productionordercode);

    @Query("select sum(productiondemandstep.finaluserprimaryquantity), MAX(productiondemandstep.time3) from Productiondemand productiondemand inner join Productiondemandstep productiondemandstep on productiondemand.id.companycode=productiondemandstep.id.productiondemandcompanycode and"+
    " productiondemand.id.countercode=productiondemandstep.id.productiondemandcountercode and productiondemand.id.code = productiondemandstep.id.productiondemandcode"+
    " where productiondemandstep.id.productiondemandcompanycode = ?1 and productiondemandstep.productionordercode = ?2 and productiondemand.subcode08 = ?3 and"+
    " productiondemand.subcode09=?4 and productiondemand.subcode10=?5 and productiondemandstep.operationcode='STITCH'")
    List<Object[]> getQuantityAndSam(String companycode, String productionordercode, String subcode08, String subcode09, String subcode10);

    @Query("select sum(productiondemandstep.finaluserprimaryquantity), MAX(productiondemandstep.time3), sum(productiondemandstep.standardstepquantity) from Productiondemand productiondemand inner join Productiondemandstep productiondemandstep on productiondemand.id.companycode=productiondemandstep.id.productiondemandcompanycode and"+
        " productiondemand.id.countercode=productiondemandstep.id.productiondemandcountercode and productiondemand.id.code = productiondemandstep.id.productiondemandcode"+
        " where productiondemandstep.id.productiondemandcompanycode = ?1 and productiondemandstep.productionordercode = ?2 and productiondemand.subcode08 = ?3 and"+
        " productiondemand.subcode09=?4 and productiondemand.subcode10=?5 and productiondemandstep.operationcode='CUTTING'")
    List<Object[]> getQuantityAndSamCutting(String companycode, String productionordercode, String subcode08, String subcode09, String subcode10);

    @Query("select sum(productiondemandstep.finaluserprimaryquantity), MAX(productiondemandstep.time3), sum(productiondemandstep.standardstepquantity) from Productiondemand productiondemand inner join Productiondemandstep productiondemandstep on productiondemand.id.companycode=productiondemandstep.id.productiondemandcompanycode and"+
        " productiondemand.id.countercode=productiondemandstep.id.productiondemandcountercode and productiondemand.id.code = productiondemandstep.id.productiondemandcode"+
        " where productiondemandstep.id.productiondemandcompanycode = ?1 and productiondemandstep.productionordercode = ?2 and productiondemand.subcode08 = ?3 and"+
        " productiondemand.subcode09=?4 and productiondemandstep.operationcode='CUTTING'")
    List<Object[]> getQuantityAndSamCuttingWo(String companycode, String productionordercode, String subcode08, String subcode09);

    @Query("select max(productiondemandstep.id.stepnumber), sum(productiondemandstep.finalbaseprimaryquantity), max(productiondemandstep.groupstepnumber), sum(productiondemandstep.standardstepquantity) from Productiondemandstep productiondemandstep where productiondemandstep.id.productiondemandcompanycode =?1 and"+
        " productiondemandstep.id.productiondemandcountercode = ?2 and productiondemandstep.id.productiondemandcode = ?3 and productiondemandstep.operationcode= ?4")
    List<Object[]> fetchAllQuantityByDemandAndOperation(String productiondemandcompanycode, String productiondemandcountercode, String productiondemandcode, String operationcode);

    @Query("select distinct productiondemandstep.operationcode, operation.longdescription, productiondemandstep.id.stepnumber from Productiondemandstep productiondemandstep inner join Operation operation  on productiondemandstep.operationcode=operation.id.code where operation.externalreservationtype<>'1' and productiondemandstep.id.productiondemandcompanycode = ?1 and productiondemandstep.productionordercode = ?2 order by productiondemandstep.id.stepnumber")
    List<Object[]> findAllOperation(String companycode, String productionordercode);

    @Query("select distinct productiondemandstep.operationcode, operation.longdescription, productiondemandstep.id.stepnumber from Productiondemandstep productiondemandstep inner join Operation operation  on productiondemandstep.operationcode=operation.id.code where productiondemandstep.id.productiondemandcompanycode = ?1 and productiondemandstep.productionordercode = ?2 and productiondemandstep.operationcode in (?3) order by productiondemandstep.id.stepnumber")
    List<Object[]> findAllOperationAndDefaultOperation(String companycode, String productionordercode, List<String> defaultOperation);


    @Query("select productiondemandstep.operationcode, operation.longdescription from Productiondemandstep productiondemandstep inner join Operation operation  on productiondemandstep.operationcode=operation.id.code where productiondemandstep.id.productiondemandcompanycode = ?1 and productiondemandstep.id.productiondemandcountercode = ?2 and"
        +" productiondemandstep.id.productiondemandcode = ?3 and productiondemandstep.id.stepnumber < ?4")
    Page<Object[]> findPreviousOperation(String productiondemandcompanycode, String productiondemandcountercode, String productiondemandcode, Integer stepnumber, Pageable pageable);

    @Query("select productiondemandstep from Productiondemandstep productiondemandstep where productiondemandstep.id.productiondemandcompanycode = ?1 and productiondemandstep.id.productiondemandcountercode = ?2 and"
        +" productiondemandstep.id.productiondemandcode = ?3 and productiondemandstep.operationcode = ?4")
    List<Productiondemandstep> findAllProductiondemandstepbyOperation(String productiondemandcompanycode, String productiondemandcountercode, String productiondemandcode, String operationcode);

    @Query("select productiondemandstep from Productiondemandstep productiondemandstep where (productiondemandstep.id.productiondemandcompanycode, productiondemandstep.productionordercode, productiondemandstep.id.stepnumber) in ("
        +" select productiondemandstep.id.productiondemandcompanycode, productiondemandstep.productionordercode, max(productiondemandstep.id.stepnumber) from Productiondemandstep productiondemandstep where productiondemandstep.id.productiondemandcompanycode=?1 and productiondemandstep.productionordercode=?2 group by productiondemandstep.id.productiondemandcompanycode, productiondemandstep.productionordercode)")
    Productiondemandstep findMaxProductiondemandstep(String productiondemandcompanycode, String productionordercode);

    @Query("select productiondemandstep from Productiondemandstep productiondemandstep where productiondemandstep.id.productiondemandcompanycode = ?1 and productiondemandstep.productionordercode = ?2 and productiondemandstep.groupstepnumber = ?3")
    List<Productiondemandstep> findProductiondemandstepByGroupStepNumber(String productiondemandcompanycode, String productionordercode, Integer groupstepnumber);

    @Query("select productiondemandstep from Productiondemandstep productiondemandstep where productiondemandstep.id.productiondemandcompanycode = ?1 and productiondemandstep.productionordercode = ?2 and productiondemandstep.operationcode = ?3")
    List<Productiondemandstep> findByProductionOrderAndOperation(String productiondemandcompanycode, String productionordercode, String operationcode);

    @Query("select productiondemandstep from Productiondemandstep productiondemandstep where productiondemandstep.id.productiondemandcompanycode = ?1 and productiondemandstep.productionordercode = ?2")
    List<Productiondemandstep> findByProductionOrder(String productiondemandcompanycode, String productionordercode);

    @Query("select productiondemandstep from Productiondemandstep productiondemandstep where (productiondemandstep.id.productiondemandcompanycode, productiondemandstep.id.productiondemandcountercode, productiondemandstep.id.productiondemandcode, productiondemandstep.id.stepnumber) in (select productiondemandstep.id.productiondemandcompanycode, productiondemandstep.id.productiondemandcountercode, productiondemandstep.id.productiondemandcode, max(productiondemandstep.id.stepnumber) from Productiondemandstep productiondemandstep where productiondemandstep.id.productiondemandcompanycode = ?1 and productiondemandstep.id.productiondemandcountercode = ?2 and productiondemandstep.id.productiondemandcode = ?3 and productiondemandstep.id.stepnumber < ?4 group by productiondemandstep.id.productiondemandcompanycode, productiondemandstep.id.productiondemandcountercode, productiondemandstep.id.productiondemandcode) order by productiondemandstep.id.stepnumber desc")
    Productiondemandstep findByProductiondemandAndStep(String companycode, String countercode, String demandcode, Integer stepnumber);

    @Query("select productiondemandstep from Productiondemandstep productiondemandstep where productiondemandstep.id.productiondemandcompanycode = ?1 and productiondemandstep.id.productiondemandcountercode = ?2 and productiondemandstep.id.productiondemandcode = ?3 and productiondemandstep.id.stepnumber<(select max(productiondemandstep.id.stepnumber) from Productiondemandstep productiondemandstep where productiondemandstep.id.productiondemandcompanycode = ?1 and productiondemandstep.id.productiondemandcountercode = ?2 and productiondemandstep.id.productiondemandcode = ?3 and productiondemandstep.productionordercode = ?4 group by productiondemandstep.id.productiondemandcompanycode, productiondemandstep.id.productiondemandcountercode, productiondemandstep.id.productiondemandcode) order by productiondemandstep.id.stepnumber desc")
    List<Productiondemandstep> findByProductiondemandAndProductionOrder(String companycode, String countercode, String demandcode, String productionordercode);

    @Query("select productiondemandstep from Productiondemandstep productiondemandstep where productiondemandstep.id.productiondemandcompanycode = ?1 and productiondemandstep.id.productiondemandcountercode = ?2 and productiondemandstep.id.productiondemandcode = ?3 and productiondemandstep.id.stepnumber in(select max(productiondemandstep.id.stepnumber) from Productiondemandstep productiondemandstep where productiondemandstep.id.productiondemandcompanycode = ?1 and productiondemandstep.id.productiondemandcountercode = ?2 and productiondemandstep.id.productiondemandcode = ?3 group by productiondemandstep.id.productiondemandcompanycode, productiondemandstep.id.productiondemandcountercode, productiondemandstep.id.productiondemandcode) order by productiondemandstep.id.stepnumber desc")
    List<Productiondemandstep> findByProductiondemand(String companycode, String countercode, String demandcode);

    @Query("select productiondemandstep from Productiondemand productiondemand, Productiondemandstep productiondemandstep where productiondemand.id.companycode = productiondemandstep.id.productiondemandcompanycode and "
        + " productiondemand.id.countercode = productiondemandstep.id.productiondemandcountercode and productiondemand.id.code = productiondemandstep.id.productiondemandcode and productiondemandstep.id.productiondemandcompanycode = ?1 and productiondemandstep.productionordercode = ?2 and productiondemandstep.operationcode = ?3 and productiondemand.subcode06 = ?4 and productiondemand.subcode07 = ?5")
    List<Productiondemandstep> findByProductionorderAndOperationcodeAndColorSize(String companycode, String productionordercode, String operationcode, String color, String size);

    @Query(value = "SELECT MAX(WORKCENTERCODE, '') FROM WORKCENTERANDOPERATTRIBUTES WA, WORKCENTER WC"
        + " WHERE WA.COMPANYCODE = WC.COMPANYCODE AND WA.WORKCENTERCODE = WC.CODE AND WC.PLANTCODE=?1 AND WA.OPERATIONCODE=?2", nativeQuery = true)
    String fetchWorkcenterByPlantCodeAndOperation(String plantCode, String operationCode);

}
