package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Balance;
import io.vamani.application.db2.domain.Productionreservation;
import io.vamani.application.db2.domain.ProductionreservationId;
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
public interface ProductionreservationRepository extends JpaRepository<Productionreservation, ProductionreservationId> {
    @Query("select productionreservation.itemtypeaficompanycode, productionreservation.itemtypeaficode, productionreservation.subcode01," +
        " productionreservation.subcode02, productionreservation.subcode03, productionreservation.subcode04, productionreservation.subcode05," +
        " productionreservation.subcode06, productionreservation.subcode07, productionreservation.subcode08, productionreservation.subcode09, productionreservation.subcode10, " +
        " productionreservation.userprimaryuomcode, productionreservation.qualitycode, productionreservation.warehousecode," +
        " fullitemkeydecoder.summarizeddescription, logicalwarehouse.longdescription, productionreservation.groupline, productionreservation.costcentercode, productionreservation.progressstatus," +
        " sum(productionreservation.userprimaryquantity), sum(productionreservation.usedbaseprimaryquantity)," +
        " sum(productionreservation.userprimaryquantity-productionreservation.usedbaseprimaryquantity)" +
        " from Productionreservation productionreservation inner join Fullitemkeydecoder fullitemkeydecoder on productionreservation.fullitemidentifier=fullitemkeydecoder.identifier" +
        " inner join Logicalwarehouse logicalwarehouse on productionreservation.id.companycode=logicalwarehouse.id.companycode and productionreservation.warehousecode=logicalwarehouse.id.code" +
        " where productionreservation.id.companycode=?1 and productionreservation.productionordercode=?2 group by " +
        " productionreservation.itemtypeaficompanycode, productionreservation.itemtypeaficode, productionreservation.subcode01," +
        " productionreservation.subcode02, productionreservation.subcode03, productionreservation.subcode04, productionreservation.subcode05," +
        " productionreservation.subcode06, productionreservation.subcode07, productionreservation.subcode08, productionreservation.subcode09, productionreservation.subcode10, " +
        " productionreservation.userprimaryuomcode, productionreservation.qualitycode, productionreservation.warehousecode," +
        " fullitemkeydecoder.summarizeddescription, logicalwarehouse.longdescription, productionreservation.groupline, productionreservation.costcentercode, productionreservation.progressstatus order by productionreservation.itemtypeaficompanycode, productionreservation.itemtypeaficode, productionreservation.subcode01, productionreservation.subcode02")
    List<Object[]> findByProductionOrderCode(String companycode, String productionordercode);


    @Query("select productionreservation.itemtypeaficompanycode, productionreservation.itemtypeaficode, productionreservation.subcode01," +
        " productionreservation.subcode02, productionreservation.subcode03, productionreservation.subcode04, productionreservation.subcode05," +
        " productionreservation.subcode06, productionreservation.subcode07, productionreservation.subcode08, productionreservation.subcode09, productionreservation.subcode10, " +
        " productionreservation.userprimaryuomcode, productionreservation.qualitycode, productionreservation.warehousecode," +
        " fullitemkeydecoder.summarizeddescription, logicalwarehouse.longdescription, productionreservation.groupline," +
        " sum(productionreservation.userprimaryquantity), sum(productionreservation.usedbaseprimaryquantity)," +
        " sum(productionreservation.userprimaryquantity-productionreservation.usedbaseprimaryquantity), productionreservation.costcentercode" +
        " from Productionreservation productionreservation inner join Fullitemkeydecoder fullitemkeydecoder on productionreservation.fullitemidentifier=fullitemkeydecoder.identifier" +
        " inner join Logicalwarehouse logicalwarehouse on productionreservation.id.companycode=logicalwarehouse.id.companycode and productionreservation.warehousecode=logicalwarehouse.id.code" +
        " where productionreservation.id.companycode=?1 and productionreservation.productionordercode=?2 group by " +
        " productionreservation.itemtypeaficompanycode, productionreservation.itemtypeaficode, productionreservation.subcode01," +
        " productionreservation.subcode02, productionreservation.subcode03, productionreservation.subcode04, productionreservation.subcode05," +
        " productionreservation.subcode06, productionreservation.subcode07, productionreservation.subcode08, productionreservation.subcode09, productionreservation.subcode10, " +
        " productionreservation.userprimaryuomcode, productionreservation.qualitycode, productionreservation.warehousecode," +
        " fullitemkeydecoder.summarizeddescription, logicalwarehouse.longdescription, productionreservation.groupline, productionreservation.costcentercode order by productionreservation.itemtypeaficompanycode, productionreservation.itemtypeaficode, productionreservation.subcode01, productionreservation.subcode02")
    List<Object[]> findByProductionOrderCodeReservation(String companycode, String productionordercode);

    @Query("select productionreservation.itemtypeaficompanycode, productionreservation.itemtypeaficode, productionreservation.subcode01," +
            " productionreservation.subcode02, productionreservation.subcode03, productionreservation.subcode04, productionreservation.subcode05," +
            " productionreservation.subcode06, productionreservation.subcode07, productionreservation.subcode08, productionreservation.subcode09, productionreservation.subcode10, " +
            " productionreservation.userprimaryuomcode, productionreservation.qualitycode, productionreservation.warehousecode," +
            " fullitemkeydecoder.summarizeddescription, logicalwarehouse.longdescription, productionreservation.id.reservationline," +
            " sum(productionreservation.userprimaryquantity), sum(productionreservation.usedbaseprimaryquantity)," +
            " sum(productionreservation.userprimaryquantity-productionreservation.usedbaseprimaryquantity), productionreservation.costcentercode " +
            " from Productionreservation productionreservation inner join Fullitemkeydecoder fullitemkeydecoder on productionreservation.fullitemidentifier=fullitemkeydecoder.identifier" +
            " inner join Logicalwarehouse logicalwarehouse on productionreservation.id.companycode=logicalwarehouse.id.companycode and productionreservation.warehousecode=logicalwarehouse.id.code" +
            " where productionreservation.id.companycode=?1 and productionreservation.productionordercode=?2 and productionreservation.itemtypeaficode in(?3) group by " +
            " productionreservation.itemtypeaficompanycode, productionreservation.itemtypeaficode, productionreservation.subcode01," +
            " productionreservation.subcode02, productionreservation.subcode03, productionreservation.subcode04, productionreservation.subcode05," +
            " productionreservation.subcode06, productionreservation.subcode07, productionreservation.subcode08, productionreservation.subcode09, productionreservation.subcode10, " +
            " productionreservation.userprimaryuomcode, productionreservation.qualitycode, productionreservation.warehousecode," +
            " fullitemkeydecoder.summarizeddescription, logicalwarehouse.longdescription, productionreservation.id.reservationline, productionreservation.costcentercode order by productionreservation.itemtypeaficompanycode, productionreservation.itemtypeaficode, productionreservation.subcode01, productionreservation.subcode02")
        List<Object[]> findByProductionOrderCodeByItemtype(String companycode, String productionordercode, List<String> itemtypes);

    @Query("select productionreservation.itemtypeaficompanycode, productionreservation.itemtypeaficode, productionreservation.subcode01," +
        " productionreservation.subcode02, productionreservation.subcode03, productionreservation.subcode04, productionreservation.subcode05," +
        " productionreservation.subcode06, productionreservation.subcode07, productionreservation.subcode08, productionreservation.subcode09, productionreservation.subcode10, " +
        " productionreservation.userprimaryuomcode, productionreservation.qualitycode, productionreservation.warehousecode," +
        " fullitemkeydecoder.summarizeddescription, logicalwarehouse.longdescription, productionreservation.id.reservationline," +
        " productionreservation.costcentercode, costcenter.longdescription, "+
        " sum(productionreservation.userprimaryquantity), sum(productionreservation.usedbaseprimaryquantity)," +
        " sum(productionreservation.userprimaryquantity-productionreservation.usedbaseprimaryquantity)" +
        " from Productionreservation productionreservation inner join Fullitemkeydecoder fullitemkeydecoder on productionreservation.fullitemidentifier=fullitemkeydecoder.identifier" +
        " inner join Logicalwarehouse logicalwarehouse on productionreservation.id.companycode = logicalwarehouse.id.companycode and productionreservation.warehousecode=logicalwarehouse.id.code" +
        " inner join Costcenter costcenter on productionreservation.id.companycode = costcenter.id.companycode and productionreservation.costcentercode = costcenter.id.code" +
        " where productionreservation.id.companycode=?1 and productionreservation.productionordercode=?2 and productionreservation.reservationnature = '1' and productionreservation.prodreservationlinkgroupcode<>'BATCHING' group by " +
        " productionreservation.itemtypeaficompanycode, productionreservation.itemtypeaficode, productionreservation.subcode01," +
        " productionreservation.subcode02, productionreservation.subcode03, productionreservation.subcode04, productionreservation.subcode05," +
        " productionreservation.subcode06, productionreservation.subcode07, productionreservation.subcode08, productionreservation.subcode09, productionreservation.subcode10, " +
        " productionreservation.userprimaryuomcode, productionreservation.qualitycode, productionreservation.warehousecode," +
        " fullitemkeydecoder.summarizeddescription, logicalwarehouse.longdescription, productionreservation.id.reservationline, productionreservation.costcentercode, costcenter.longdescription order by productionreservation.itemtypeaficompanycode, productionreservation.itemtypeaficode, productionreservation.subcode01, productionreservation.subcode02")
    List<Object[]> findByProductionOrderCodeReservationDNC(String companycode, String productionordercode);

    @Query("select max(productionreservation.id.ordercode) from Productionreservation productionreservation where productionreservation.id.companycode = ?1 and"+
        " productionreservation.productionordercode=?2 and productionreservation.itemtypeaficode = ?3 and productionreservation.subcode01 = ?4 and productionreservation.subcode02 = ?5 and productionreservation.subcode03 = ?6 and productionreservation.subcode04 = ?7 and"+
        " productionreservation.subcode05 = ?8 and productionreservation.subcode06 = ?9 and productionreservation.subcode07 = ?10 and"+
        " productionreservation.subcode08 = ?11 and productionreservation.subcode09 = ?12 and productionreservation.subcode10 = ?13")
    String findProductionDemandCode(String companycode, String productionordercode, String itemtypecode01, String subcode01, String subcode02, String subcode03, String subcode04, String subcode05, String subcode06, String subcode07, String subcode08, String subcode09, String subcode10);

    @Query("select productionreservation.itemtypeaficompanycode, productionreservation.itemtypeaficode, productionreservation.subcode01," +
        " productionreservation.subcode02, productionreservation.subcode03, productionreservation.subcode04, productionreservation.subcode05," +
        " productionreservation.subcode06, productionreservation.subcode07, productionreservation.subcode08, productionreservation.subcode09, productionreservation.subcode10, " +
        " productionreservation.userprimaryuomcode, productionreservation.qualitycode, productionreservation.warehousecode," +
        " fullitemkeydecoder.summarizeddescription, logicalwarehouse.longdescription, productionreservation.groupline," +
        " sum(productionreservation.userprimaryquantity), sum(productionreservation.usedbaseprimaryquantity)," +
        " sum(productionreservation.userprimaryquantity-productionreservation.usedbaseprimaryquantity), productionreservation.costcentercode, productionreservation.reservationgroupcode, productionreservation.groupstepnumber, productionreservation.stepnumber, productionreservation.id.reservationline," +
        " productionreservation.usersecondaryuomcode, sum(productionreservation.usersecondaryquantity), sum(productionreservation.usedbasesecondaryquantity)," +
        " sum(productionreservation.usersecondaryquantity-productionreservation.usedbasesecondaryquantity) "+
        " from Productionreservation productionreservation inner join Fullitemkeydecoder fullitemkeydecoder on productionreservation.fullitemidentifier=fullitemkeydecoder.identifier" +
        " inner join Logicalwarehouse logicalwarehouse on productionreservation.id.companycode=logicalwarehouse.id.companycode and productionreservation.warehousecode=logicalwarehouse.id.code" +
        " where productionreservation.reservationnature = '1' and productionreservation.id.companycode=?1 and productionreservation.id.ordercountercode=?2 and productionreservation.id.ordercode=?3 and productionreservation.productionordercode = ?4 group by " +
        " productionreservation.itemtypeaficompanycode, productionreservation.itemtypeaficode, productionreservation.subcode01," +
        " productionreservation.subcode02, productionreservation.subcode03, productionreservation.subcode04, productionreservation.subcode05," +
        " productionreservation.subcode06, productionreservation.subcode07, productionreservation.subcode08, productionreservation.subcode09, productionreservation.subcode10, " +
        " productionreservation.userprimaryuomcode, productionreservation.qualitycode, productionreservation.warehousecode," +
        " fullitemkeydecoder.summarizeddescription, logicalwarehouse.longdescription, productionreservation.groupline, productionreservation.costcentercode, productionreservation.reservationgroupcode, productionreservation.groupstepnumber, productionreservation.stepnumber, productionreservation.id.reservationline, productionreservation.usersecondaryuomcode order by productionreservation.itemtypeaficompanycode, productionreservation.itemtypeaficode, productionreservation.subcode01, productionreservation.subcode02")
    List<Object[]> findByProductionDemandCodeReservation(String ordercountercompanycode, String ordercountercode, String ordercode, String productionordercode);

    @Query("select productionreservation.itemtypeaficompanycode, productionreservation.itemtypeaficode, productionreservation.subcode01," +
        " productionreservation.subcode02, productionreservation.subcode03, productionreservation.subcode04, productionreservation.subcode05," +
        " productionreservation.subcode06, productionreservation.subcode07, productionreservation.subcode08, productionreservation.subcode09, productionreservation.subcode10, " +
        " productionreservation.userprimaryuomcode, productionreservation.qualitycode, productionreservation.warehousecode," +
        " fullitemkeydecoder.summarizeddescription, logicalwarehouse.longdescription, productionreservation.groupline," +
        " sum(productionreservation.userprimaryquantity), sum(productionreservation.usedbaseprimaryquantity)," +
        " sum(productionreservation.userprimaryquantity-productionreservation.usedbaseprimaryquantity), productionreservation.costcentercode, productionreservation.reservationgroupcode, productionreservation.groupstepnumber, productionreservation.stepnumber, productionreservation.id.reservationline, " +
        " productionreservation.usersecondaryuomcode, sum(productionreservation.usersecondaryquantity), sum(productionreservation.usedbasesecondaryquantity)," +
        " sum(productionreservation.usersecondaryquantity-productionreservation.usedbasesecondaryquantity) "+
        " from Productionreservation productionreservation inner join Fullitemkeydecoder fullitemkeydecoder on productionreservation.fullitemidentifier=fullitemkeydecoder.identifier" +
        " inner join Logicalwarehouse logicalwarehouse on productionreservation.id.companycode=logicalwarehouse.id.companycode and productionreservation.warehousecode=logicalwarehouse.id.code" +
        " where productionreservation.reservationnature = '1' and productionreservation.id.companycode=?1 and productionreservation.id.ordercountercode=?2 and productionreservation.id.ordercode=?3 and productionreservation.productionordercode = ?4 and productionreservation.itemtypeaficode in(?5) group by " +
        " productionreservation.itemtypeaficompanycode, productionreservation.itemtypeaficode, productionreservation.subcode01," +
        " productionreservation.subcode02, productionreservation.subcode03, productionreservation.subcode04, productionreservation.subcode05," +
        " productionreservation.subcode06, productionreservation.subcode07, productionreservation.subcode08, productionreservation.subcode09, productionreservation.subcode10, " +
        " productionreservation.userprimaryuomcode, productionreservation.qualitycode, productionreservation.warehousecode," +
        " fullitemkeydecoder.summarizeddescription, logicalwarehouse.longdescription, productionreservation.groupline, productionreservation.costcentercode, productionreservation.reservationgroupcode, productionreservation.groupstepnumber, productionreservation.stepnumber, productionreservation.id.reservationline, productionreservation.usersecondaryuomcode order by productionreservation.itemtypeaficompanycode, productionreservation.itemtypeaficode, productionreservation.subcode01, productionreservation.subcode02")
    List<Object[]> findByProductiondemandCodeByItemtype(String ordercountercompanycode, String ordercountercode, String ordercode, String productionordercode, List<String> itemtypes);

    @Query("select productionreservation from Productionreservation productionreservation where productionreservation.id.companycode=?1 and productionreservation.id.ordercountercode=?2 and productionreservation.id.ordercode=?3 and productionreservation.productionordercode = ?4 and productionreservation.itemtypeaficode in(?5)")
    List<Productionreservation> findAllProductionResverationByProductiondemandCodeByItemtype(String ordercountercompanycode, String ordercountercode, String ordercode, String productionordercode, List<String> itemtypes);

    @Query("select productionreservation from Productionreservation productionreservation where productionreservation.id.companycode=?1 and productionreservation.id.ordercountercode=?2 and productionreservation.id.ordercode=?3 and productionreservation.productionordercode = ?4 and productionreservation.itemtypeaficode IN ('CPT', 'HFC')")
    List<Productionreservation> findAllProductionResverationByProductiondemandCodeAndCpt(String ordercountercompanycode, String ordercountercode, String ordercode, String productionordercode);

    @Query("select productionreservation from Productionreservation productionreservation where productionreservation.id.companycode=?1 and productionreservation.id.ordercountercode=?2 and productionreservation.id.ordercode=?3 and productionreservation.productionordercode = ?4 and productionreservation.itemtypeaficode IN ('SEG', 'HFS')")
    List<Productionreservation> findAllProductionResverationByProductiondemandCodeAndSEG(String ordercountercompanycode, String ordercountercode, String ordercode, String productionordercode);

    @Query("select distinct balance from Productiondemand productiondemand inner join Productionreservation productionreservation on productiondemand.id.companycode = productionreservation.id.companycode and productiondemand.id.countercode = productionreservation.id.ordercountercode and productiondemand.id.code = productionreservation.id.ordercode "
        +" inner join Balance balance on productionreservation.itemtypeaficode = balance.itemtypecode and productionreservation.subcode01 = balance.decosubcode01 and productionreservation.subcode02 = balance.decosubcode02 and productionreservation.subcode03 = balance.decosubcode03 and productionreservation.subcode04 = balance.decosubcode04 and productionreservation.subcode05 = balance.decosubcode05 and productionreservation.subcode06 = balance.decosubcode06 and productionreservation.subcode07 = balance.decosubcode07 and "
        +" productionreservation.subcode08 = balance.decosubcode08 and productionreservation.subcode09 = balance.decosubcode09 and productionreservation.subcode10 = balance.decosubcode10 and productionreservation.projectcode = balance.projectcode and balance.stocktypecode='001'"
        +" inner join Logicalwarehouse logicalwarehouse on balance.logicalwarehousecode = logicalwarehouse.id.code "
        +" where productiondemand.subcode07 = ?1 and concat(Trim(productionreservation.id.companycode), '|', Trim(productionreservation.id.ordercountercode), '|', Trim(productionreservation.id.ordercode)) IN(?2) and "
        +" productionreservation.subcode01 = ?3 and productionreservation.subcode02 = ?4 and productionreservation.subcode03 = ?5 and productionreservation.subcode04 = ?6 and productionreservation.subcode05 = ?7 and "
        +" productionreservation.subcode06 = ?8 and productionreservation.subcode07 = ?9 and productionreservation.subcode08 = ?10 and productionreservation.subcode09 = ?11 and productionreservation.subcode10 = ?12 and logicalwarehouse.plantcode = ?13 and logicalwarehouse.id.code like ?14")
    List<Balance> findAllByProductionDemand(String color, List<String> ids, String subcode01, String subcode02, String subcode03, String subcode04, String subcode05, String subcode06, String subcode07, String subcode08, String subcode09, String subcode10, String plantCode, String logicalwarehousecode);

    @Query("select distinct balance from Productiondemand productiondemand inner join Productionreservation productionreservation on productiondemand.id.companycode = productionreservation.id.companycode and productiondemand.id.countercode = productionreservation.id.ordercountercode and productiondemand.id.code = productionreservation.id.ordercode "
        +" inner join Balance balance on productionreservation.itemtypeaficode = balance.itemtypecode and productionreservation.subcode01 = balance.decosubcode01 and productionreservation.subcode02 = balance.decosubcode02 and productionreservation.subcode03 = balance.decosubcode03 and productionreservation.subcode04 = balance.decosubcode04 and productionreservation.subcode05 = balance.decosubcode05 and productionreservation.subcode06 = balance.decosubcode06 and productionreservation.subcode07 = balance.decosubcode07 and "
        +" productionreservation.subcode08 = balance.decosubcode08 and productionreservation.subcode09 = balance.decosubcode09 and productionreservation.subcode10 = balance.decosubcode10 and productionreservation.projectcode = balance.projectcode and balance.stocktypecode='001'"
        +" inner join Logicalwarehouse logicalwarehouse on balance.logicalwarehousecode = logicalwarehouse.id.code "
        +" where productiondemand.subcode05 = ?1 and concat(Trim(productionreservation.id.companycode), '|', Trim(productionreservation.id.ordercountercode), '|', Trim(productionreservation.id.ordercode)) IN(?2) and "
        +" productionreservation.subcode01 = ?3 and productionreservation.subcode02 = ?4 and productionreservation.subcode03 = ?5 and productionreservation.subcode04 = ?6 and productionreservation.subcode05 = ?7 and "
        +" productionreservation.subcode06 = ?8 and productionreservation.subcode07 = ?9 and productionreservation.subcode08 = ?10 and productionreservation.subcode09 = ?11 and productionreservation.subcode10 = ?12 and logicalwarehouse.plantcode = ?13 and logicalwarehouse.id.code like ?14")
    List<Balance> findAllHFDByProductionDemand(String color, List<String> ids, String subcode01, String subcode02, String subcode03, String subcode04, String subcode05, String subcode06, String subcode07, String subcode08, String subcode09, String subcode10, String plantCode, String logicalwarehousecode);

    @Query("select distinct balance from Productiondemand productiondemand inner join Productionreservation productionreservation on productiondemand.id.companycode = productionreservation.id.companycode and productiondemand.id.countercode = productionreservation.id.ordercountercode and productiondemand.id.code = productionreservation.id.ordercode"
        +" inner join Balance balance on productionreservation.itemtypeaficode = balance.itemtypecode and productionreservation.subcode01 = balance.decosubcode01 and productionreservation.subcode02 = balance.decosubcode02 and productionreservation.subcode03 = balance.decosubcode03 and productionreservation.subcode04 = balance.decosubcode04 and productionreservation.subcode05 = balance.decosubcode05 and productionreservation.subcode06 = balance.decosubcode06 and productionreservation.subcode07 = balance.decosubcode07 and"
        +" productionreservation.subcode08 = balance.decosubcode08 and productionreservation.subcode09 = balance.decosubcode09 and productionreservation.subcode10 = balance.decosubcode10 and productionreservation.projectcode = balance.projectcode and balance.stocktypecode='001'"
        +" inner join Logicalwarehouse logicalwarehouse on balance.logicalwarehousecode = logicalwarehouse.id.code "
        +" where productiondemand.subcode07 = ?1 and concat(Trim(productionreservation.id.companycode), '|', Trim(productionreservation.id.ordercountercode), '|', Trim(productionreservation.id.ordercode)) IN(?2) and "
        +" productionreservation.subcode01 = ?3 and productionreservation.subcode02 = ?4 and productionreservation.subcode03 = ?5 and productionreservation.subcode04 = ?6 and productionreservation.subcode05 = ?7 and "
        +" productionreservation.subcode06 = ?8 and productionreservation.subcode07 = ?9 and productionreservation.subcode08 = ?10 and productionreservation.subcode09 = ?11 and productionreservation.subcode10 = ?12 and "
        +" trim(balance.elementscode) not in(?13) and logicalwarehouse.plantcode = ?14 and logicalwarehouse.id.code like ?15")
    List<Balance> findAllByProductionDemandAndRolls(String color, List<String> ids, String subcode01, String subcode02, String subcode03, String subcode04, String subcode05, String subcode06, String subcode07, String subcode08, String subcode09, String subcode10, List<String> rolls, String plantCode, String logicalwarehousecode);

    @Query("select distinct balance from Productiondemand productiondemand inner join Productionreservation productionreservation on productiondemand.id.companycode = productionreservation.id.companycode and productiondemand.id.countercode = productionreservation.id.ordercountercode and productiondemand.id.code = productionreservation.id.ordercode"
        +" inner join Balance balance on productionreservation.itemtypeaficode = balance.itemtypecode and productionreservation.subcode01 = balance.decosubcode01 and productionreservation.subcode02 = balance.decosubcode02 and productionreservation.subcode03 = balance.decosubcode03 and productionreservation.subcode04 = balance.decosubcode04 and productionreservation.subcode05 = balance.decosubcode05 and productionreservation.subcode06 = balance.decosubcode06 and productionreservation.subcode07 = balance.decosubcode07 and"
        +" productionreservation.subcode08 = balance.decosubcode08 and productionreservation.subcode09 = balance.decosubcode09 and productionreservation.subcode10 = balance.decosubcode10 and productionreservation.projectcode = balance.projectcode and balance.stocktypecode='001'"
        +" inner join Logicalwarehouse logicalwarehouse on balance.logicalwarehousecode = logicalwarehouse.id.code "
        +" where productiondemand.subcode05 = ?1 and concat(Trim(productionreservation.id.companycode), '|', Trim(productionreservation.id.ordercountercode), '|', Trim(productionreservation.id.ordercode)) IN(?2) and "
        +" productionreservation.subcode01 = ?3 and productionreservation.subcode02 = ?4 and productionreservation.subcode03 = ?5 and productionreservation.subcode04 = ?6 and productionreservation.subcode05 = ?7 and "
        +" productionreservation.subcode06 = ?8 and productionreservation.subcode07 = ?9 and productionreservation.subcode08 = ?10 and productionreservation.subcode09 = ?11 and productionreservation.subcode10 = ?12 and "
        +" trim(balance.elementscode) not in(?13) and logicalwarehouse.plantcode = ?14 and logicalwarehouse.id.code like ?15")
    List<Balance> findAllHFDByProductionDemandAndRolls(String color, List<String> ids, String subcode01, String subcode02, String subcode03, String subcode04, String subcode05, String subcode06, String subcode07, String subcode08, String subcode09, String subcode10, List<String> rolls, String plantCode, String logicalwarehousecode);


    @Query("select coalesce(max(productionreservation.groupline), 1) from Productionreservation productionreservation where productionreservation.id.companycode = ?1 and productionreservation.productionordercode = ?2 and productionreservation.itemtypeaficode = ?3 and productionreservation.subcode01 = ?4 and productionreservation.subcode02 = ?5 and productionreservation.subcode03 = ?6 and productionreservation.subcode04 = ?7 and productionreservation.subcode05 = ?8 and productionreservation.subcode06 = ?9 and productionreservation.subcode07 = ?10 and productionreservation.subcode08 = ?11 and productionreservation.subcode09 = ?12 and productionreservation.subcode10 = ?13")
    Long maxGroupLine(String companycode, String productionordercode, String itemtypecode, String subcode01, String subcode02, String subcode03, String subcode04, String subcode05, String subcode06, String subcode07, String subcode08, String subcode09, String subcode10);
}
