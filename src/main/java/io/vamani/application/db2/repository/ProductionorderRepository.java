package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Productionorder;
import io.vamani.application.db2.domain.ProductionorderId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface ProductionorderRepository extends JpaRepository<Productionorder, ProductionorderId> {
    @Query("select productionorder from Productionorder productionorder where productionorder.id.companycode=?1 and productionorder.productionordercountercode in(?2) and productionorder.id.code like ?3 and productionorder.progressstatus<>'6'")
    Page<Productionorder> findByCode(String companycode, List<String> countercode,String code,Pageable pageable);

    @Query(value = "select distinct productionorder.id.companycode, productionorder.id.code, productionorder.productionordercountercode, productionorder.orderdate, productionorder.totalprimaryquantity, productionorder.primaryunitofmeasurecode from Productionorder productionorder inner join Productiondemandstep productiondemandstep on productionorder.id.code = productiondemandstep.productionordercode where productionorder.id.companycode=?1 and productionorder.productionordercountercode in ?2 and productionorder.id.code like ?3 and productiondemandstep.operationcode = ?4 and productionorder.progressstatus<>'6'",
        countQuery = "select count(distinct productionorder.id.code)     from Productionorder productionorder inner join Productiondemandstep productiondemandstep on productionorder.id.code = productiondemandstep.productionordercode where productionorder.id.companycode=?1 and productionorder.productionordercountercode in ?2 and productionorder.id.code like ?3 and productiondemandstep.operationcode = ?4 and productionorder.progressstatus<>'6'")
    Page<Object[]> findByCodeAndOperation(String companycode,List<String> countercode,String code, String operationcode,Pageable pageable);

    @Query("select distinct productionorder.id.companycode, productionorder.id.code, productionorder.productionordercountercode, productionorder.orderdate, productionorder.totalprimaryquantity, productionorder.primaryunitofmeasurecode from Productionorder productionorder inner join Productiondemandstep productiondemandstep on productionorder.id.code = productiondemandstep.productionordercode where productionorder.id.companycode=?1 and productionorder.productionordercountercode in ?2 and productionorder.id.code = ?3 and productiondemandstep.operationcode = ?4 and productionorder.progressstatus<>'6'")
    List<Object[]> findByCodeAndOperationById(String companycode,List<String> countercode,String code, String operationcode);

    @Query("select productionorder from Productionorder productionorder where productionorder.id.companycode=?1 and productionorder.id.code like ?2")
    Page<Productionorder> findByCode(String companycode, String code, Pageable pageable);

    @Query("select productionorder from Productionorder productionorder where productionorder.id.companycode=?1 and productionorder.id.code = ?2")
    Productionorder findByCode(String companycode, String code);

    @Query(value="SELECT DISTINCT PR.SUBCODE01, PR.SUBCODE02, PR.SUBCODE03, PR.SUBCODE04, PR.SUBCODE05, PR.SUBCODE06,PR.SUBCODE07, PR.SUBCODE08, PR.SUBCODE09, PR.SUBCODE10, (NVL(TRIM(PR.SUBCODE01),'')||' '||NVL(TRIM(PR.SUBCODE02),'')||' '||NVL(TRIM(PR.SUBCODE03),'')||' '||NVL(TRIM(PR.SUBCODE04),'')||' '||NVL(TRIM(PR.SUBCODE05),'')||' '|| NVL(TRIM(PR.SUBCODE06),'')||' '||NVL(TRIM(PR.SUBCODE07),'')||' '||NVL(TRIM(PR.SUBCODE08),'')||' '||NVL(TRIM(PR.SUBCODE09),'')||' '||NVL(TRIM(PR.SUBCODE10),''))ITEMCODE FROM PRODUCTIONRESERVATION PR WHERE PR.ITEMTYPEAFICODE='WPF' AND PR.ORDERCODE IN (select PD.CODE from PRODUCTIONORDER PO, XMLTABLE('$doc/d/i' PASSING XMLPARSE(DOCUMENT '<d><i>' || regexp_replace(PO.DEMANDLIST, ';', '</i><i>') || '</i></d>') as \"doc\"  COLUMNS DEMAND VARCHAR(200) PATH '.') DMS,  PRODUCTIONDEMAND PD where (TRIM(PD.COUNTERCOMPANYCODE) || '|' || TRIM(PD.COUNTERCODE) || '|' || TRIM(PD.CODE)) = DMS.DEMAND AND po.PRODUCTIONORDERCOUNTERCODE in('CPPO','HCPO') and PD.PROJECTCODE=?1 AND ((PD.DIVISIONCODE ='101' AND PD.SUBCODE07=?2) OR (PD.DIVISIONCODE ='102' AND PD.SUBCODE05=?2)) AND PO.CODE=?3) ", nativeQuery = true)
    List<Object[]> getAllReservationItemByPo(String style, String color, String PoNumber);

    @Query("select distinct productionreservation.itemtypeaficode, productionreservation.subcode01, productionreservation.subcode02, productionreservation.subcode03, productionreservation.subcode04, productionreservation.subcode05, productionreservation.subcode06, productionreservation.subcode07, productionreservation.subcode08, productionreservation.subcode09, productionreservation.subcode10 from Productionreservation productionreservation where productionreservation.productionordercode=?1 and productionreservation.id.ordercode in ?2 and productionreservation.itemtypeaficode='WPF'")
    List<Object[]> getItemsByProductionorderAndProductionDemands(String productionordercode, List<String> demandcode);

    @Query(value="SELECT DISTINCT PR.SUBCODE01, PR.SUBCODE02, PR.SUBCODE03, PR.SUBCODE04, PR.SUBCODE05, PR.SUBCODE06,PR.SUBCODE07, PR.SUBCODE08, PR.SUBCODE09, PR.SUBCODE10, (NVL(TRIM(PR.SUBCODE01),'')||' '||NVL(TRIM(PR.SUBCODE02),'')||' '||NVL(TRIM(PR.SUBCODE03),'')||' '||NVL(TRIM(PR.SUBCODE04),'')||' '||NVL(TRIM(PR.SUBCODE05),'')||' '|| NVL(TRIM(PR.SUBCODE06),'')||' '||NVL(TRIM(PR.SUBCODE07),'')||' '||NVL(TRIM(PR.SUBCODE08),'')||' '||NVL(TRIM(PR.SUBCODE09),'')||' '||NVL(TRIM(PR.SUBCODE10),''))ITEMCODE, PR.ITEMTYPEAFICODE FROM PRODUCTIONRESERVATION PR WHERE PR.ITEMTYPEAFICODE='WPF' AND PR.ORDERCODE IN (select PD.CODE from PRODUCTIONORDER PO, XMLTABLE('$doc/d/i' PASSING XMLPARSE(DOCUMENT '<d><i>' || regexp_replace(PO.DEMANDLIST, ';', '</i><i>') || '</i></d>') as \"doc\"  COLUMNS DEMAND VARCHAR(200) PATH '.') DMS,  PRODUCTIONDEMAND PD where (TRIM(PD.COUNTERCOMPANYCODE) || '|' || TRIM(PD.COUNTERCODE) || '|' || TRIM(PD.CODE)) = DMS.DEMAND AND po.PRODUCTIONORDERCOUNTERCODE IN('CPPO','HCPO') and PD.SUBCODE01=?1 AND ((PD.DIVISIONCODE ='101' AND PD.SUBCODE07=?2) OR (PD.DIVISIONCODE ='102' AND PD.SUBCODE05=?2))) AND PR.SUBCODE09 = ?2 ", nativeQuery = true)
    List<Object[]> getAllMarkerReservationItemByPo(String style, String color);

    @Query("select distinct productionreservation.subcode01, productionreservation.subcode02, productionreservation.subcode03, productionreservation.subcode04, productionreservation.subcode05,"
        + " productionreservation.subcode06,productionreservation.subcode07, productionreservation.subcode08, productionreservation.subcode09, productionreservation.subcode10,"
        + " (coalesce(trim(productionreservation.subcode01),'')||' '||coalesce(trim(productionreservation.subcode02),'')||' '||coalesce(trim(productionreservation.subcode03),'')||' '||coalesce(trim(productionreservation.subcode04),'')||' '||coalesce(trim(productionreservation.subcode05),'')||' '|| coalesce(trim(productionreservation.subcode06),'')||' '||coalesce(trim(productionreservation.subcode07),'')||' '||coalesce(trim(productionreservation.subcode08),'')||' '||coalesce(trim(productionreservation.subcode09),'')||' '||coalesce(trim(productionreservation.subcode10),'')), productionreservation.itemtypeaficode"
        + " from Productionreservation productionreservation inner join Productiondemand productiondemand on"
        + " productionreservation.id.companycode = productiondemand.id.companycode and productionreservation.id.ordercountercode = productiondemand.id.countercode and"
        + " productionreservation.id.ordercode = productiondemand.id.code where (productionreservation.productionordercode like 'CP%' OR productionreservation.productionordercode like 'HC%') and productiondemand.projectcode= ?1 and ((productiondemand.divisioncode = '102' and productiondemand.subcode05 = ?2) or(productiondemand.subcode07 = ?2))")
    List<Object[]> getAllReservationItemByDemand(String style, String color);
}
