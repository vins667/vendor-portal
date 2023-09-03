package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Viewprodorderdemand;
import io.vamani.application.db2.domain.ViewprodorderdemandId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface ViewprodorderdemandRepository extends JpaRepository<Viewprodorderdemand, ViewprodorderdemandId> {
    @Query(value = "select SS.SEQUENCE, CASE WHEN PD.DIVISIONCODE='102' THEN 'NA' ELSE PD.SUBCODE06 END AS DESTINATION, CASE WHEN PD.DIVISIONCODE='102' THEN PD.SUBCODE06 ELSE PD.SUBCODE08 END AS SIZECODE,"
        + " CEILING(SUM(CASE WHEN AD.VALUEDECIMAL IS NOT NULL THEN PD.USERPRIMARYQUANTITY ELSE PD.USERPRIMARYQUANTITY END))  USERPRIMARYQUANTITY"
        + " FROM VIEWPRODORDERDEMAND PO, PRODUCTIONDEMAND PD, PRODUCTSPECIALIZEDSIZE PSS, SIZES SS, PROJECT PR"
        + " LEFT OUTER JOIN ADSTORAGE AD ON AD.NAMEENTITYNAME='Project' AND AD.NAMENAME='ProductionTolerancePercentage' AND AD.FIELDNAME='ProductionTolerancePercentage' AND PR.ABSUNIQUEID = AD.UNIQUEID"
        + " WHERE  PO.COMPANYCODE = PD.COMPANYCODE AND PO.PDCOUNTERCODE = PD.COUNTERCODE AND PO.PDCODE = PD.CODE AND"
        + " PD.ITEMTYPEAFICODE = PSS.PRODUCTITEMTYPECODE AND PD.SUBCODE01 = PSS.PRODUCTSUBCODE01 AND"
        + " PD.SUBCODE02 = PSS.PRODUCTSUBCODE02 AND PD.SUBCODE03 = PSS.PRODUCTSUBCODE03 AND PD.SUBCODE04 = PSS.PRODUCTSUBCODE04 AND"
        + " PSS.SIZETYPECODE = SS.SIZESTYPECODE AND ((PD.DIVISIONCODE='102' AND SS.CODE = PD.SUBCODE06) OR (SS.CODE = PD.SUBCODE08)) AND PD.PROJECTCODE = PR.CODE AND PO.COMPANYCODE = ?1 AND PO.CODE=?2 AND ((PD.DIVISIONCODE='102' AND PD.SUBCODE05=?3) OR (PD.SUBCODE07=?3))"
        + " GROUP BY SS.SEQUENCE, CASE WHEN PD.DIVISIONCODE='102' THEN 'NA' ELSE PD.SUBCODE06 END, CASE WHEN PD.DIVISIONCODE='102' THEN PD.SUBCODE06 ELSE PD.SUBCODE08 END ORDER BY SS.SEQUENCE, CASE WHEN PD.DIVISIONCODE='102' THEN 'NA' ELSE PD.SUBCODE06 END", nativeQuery = true)
    List<Object[]> getAllSizesByStyleAndDestination(String COMPANYCODE, String CODE, String COLOR);

    @Query("select viewprodorderdemand from Viewprodorderdemand viewprodorderdemand where viewprodorderdemand.id.companycode = ?1 and viewprodorderdemand.id.code = ?2 and viewprodorderdemand.subcode07 = ?3")
    List<Viewprodorderdemand> findProductionOrder(String companycode, String productionorder, String color);

    @Query("select viewprodorderdemand from Viewprodorderdemand viewprodorderdemand where viewprodorderdemand.id.companycode = ?1 and viewprodorderdemand.id.code = ?2 and viewprodorderdemand.subcode05 = ?3")
    List<Viewprodorderdemand> findProductionOrderHFD(String companycode, String productionorder, String color);
}
