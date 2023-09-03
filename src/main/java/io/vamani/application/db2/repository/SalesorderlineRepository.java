package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Salesorderline;
import io.vamani.application.db2.domain.SalesorderlineId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.PersistenceContext;

@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface SalesorderlineRepository extends JpaRepository<Salesorderline, SalesorderlineId> {

	@Query("select distinct salesorderline.subcode05, usergenericgroup.longdescription from Salesorderline salesorderline inner join Usergenericgroup usergenericgroup on usergenericgroup.id.usergenericgrouptypecode = 'DST' and salesorderline.subcode05 = usergenericgroup.id.code where salesorderline.projectcode=?1 ")
	List<Object[]> getAllCountryById(String styleCode);

	@Query("select distinct case when salesorder.divisioncode = '101' then salesorderline.subcode06 else salesorderline.subcode05 end , color.longdescription from Salesorderline salesorderline inner join Salesorder salesorder on salesorderline.id.salesordercompanycode = salesorder.id.companycode and salesorderline.id.salesordercountercode = salesorder.id.countercode and salesorderline.id.salesordercode = salesorder.id.code  inner join Color color on color.id.colorfoldercode = 'COL' and "
        +" ((salesorder.divisioncode = 101 and salesorderline.subcode06 = color.id.code) or (salesorder.divisioncode = 102 and salesorderline.subcode05 = color.id.code)) where salesorderline.projectcode=?1 ")
	List<Object[]> getAllColorByCountry(String styleCode);

	@Query(value = "select distinct divisioncode from salesorder where projectcode=?1 ", nativeQuery = true)
	String getFactorycodebyProject(String styleCode);

	@Query(value = "SELECT SS.SEQUENCE, CASE WHEN SL.DIVISIONCODE='102' THEN 'NA' ELSE SOL.SUBCODE05 END AS DESTINATION,"
            + " CASE WHEN SL.DIVISIONCODE='102' THEN SOL.SUBCODE06 ELSE SOL.SUBCODE07 END AS SIZECODE,"
            + " CEILING(SUM(CASE WHEN AD.VALUEDECIMAL IS NOT NULL THEN SOL.USERPRIMARYQUANTITY + ((SOL.USERPRIMARYQUANTITY*AD.VALUEDECIMAL)/100) ELSE SOL.USERPRIMARYQUANTITY END))  USERPRIMARYQUANTITY"
            + " FROM SALESORDER SL, SALESORDERLINE SOL, PRODUCTSPECIALIZEDSIZE PSS, SIZES SS, PROJECT PR"
            + " LEFT OUTER JOIN ADSTORAGE AD ON AD.NAMEENTITYNAME='Project' AND AD.NAMENAME='ProductionTolerancePercentage' AND AD.FIELDNAME='ProductionTolerancePercentage' AND PR.ABSUNIQUEID = AD.UNIQUEID"
            + " WHERE SL.COMPANYCODE = SOL.SALESORDERCOMPANYCODE AND SL.COUNTERCODE = SOL.SALESORDERCOUNTERCODE AND SL.CODE = SOL.SALESORDERCODE AND"
            + " SOL.ITEMTYPEAFICODE = PSS.PRODUCTITEMTYPECODE AND SOL.SUBCODE01 = PSS.PRODUCTSUBCODE01 AND"
            + " SOL.SUBCODE02 = PSS.PRODUCTSUBCODE02 AND SOL.SUBCODE03 = PSS.PRODUCTSUBCODE03 AND SOL.SUBCODE04 = PSS.PRODUCTSUBCODE04 AND"
            + " PSS.SIZETYPECODE = SS.SIZESTYPECODE AND ((SL.DIVISIONCODE='102' AND SS.CODE = SOL.SUBCODE06) OR (SS.CODE = SOL.SUBCODE07)) AND"
            + " SOL.PROJECTCODE = PR.CODE AND SOL.PROJECTCODE=?1 AND"
            + " ((SL.DIVISIONCODE='102' AND SOL.SUBCODE05=?2) OR (SOL.SUBCODE06=?2))"
            + " group by ss.sequence, CASE WHEN SL.DIVISIONCODE='102' THEN 'NA' ELSE SOL.SUBCODE05 END,"
            + " CASE WHEN SL.DIVISIONCODE='102' THEN SOL.SUBCODE06 ELSE SOL.SUBCODE07 END"
            + " ORDER BY 1, 2", nativeQuery = true)
	List<Object[]> getAllSizesByStyle(String styleCode, String color);

    @Query(value = "select SS.SEQUENCE, CASE WHEN SL.DIVISIONCODE='102' THEN 'NA' ELSE SOL.SUBCODE05 END AS DESTINATION, CASE WHEN SL.DIVISIONCODE='102' THEN SOL.SUBCODE06 ELSE SOL.SUBCODE07 END AS SIZECODE,"
        + " CEILING(SUM(CASE WHEN AD.VALUEDECIMAL IS NOT NULL THEN SOL.USERPRIMARYQUANTITY + ((SOL.USERPRIMARYQUANTITY*AD.VALUEDECIMAL)/100) ELSE SOL.USERPRIMARYQUANTITY END))  USERPRIMARYQUANTITY"
        + " FROM SALESORDER SL, SALESORDERLINE SOL, PRODUCTSPECIALIZEDSIZE PSS, SIZES SS, PROJECT PR"
        + " LEFT OUTER JOIN ADSTORAGE AD ON AD.NAMEENTITYNAME='Project' AND AD.NAMENAME='ProductionTolerancePercentage' AND AD.FIELDNAME='ProductionTolerancePercentage' AND PR.ABSUNIQUEID = AD.UNIQUEID"
        + " WHERE  SL.COMPANYCODE = SOL.SALESORDERCOMPANYCODE AND SL.COUNTERCODE = SOL.SALESORDERCOUNTERCODE AND SL.CODE = SOL.SALESORDERCODE AND"
        + " SOL.ITEMTYPEAFICODE = PSS.PRODUCTITEMTYPECODE AND SOL.SUBCODE01 = PSS.PRODUCTSUBCODE01 AND"
        + " SOL.SUBCODE02 = PSS.PRODUCTSUBCODE02 AND SOL.SUBCODE03 = PSS.PRODUCTSUBCODE03 AND SOL.SUBCODE04 = PSS.PRODUCTSUBCODE04 AND"
        + " PSS.SIZETYPECODE = SS.SIZESTYPECODE AND ((SL.DIVISIONCODE='102' AND SS.CODE = SOL.SUBCODE06) OR (SS.CODE = SOL.SUBCODE07)) AND SOL.PROJECTCODE = PR.CODE AND SOL.PROJECTCODE=?1 AND  ((SL.DIVISIONCODE='102') OR (SOL.SUBCODE05 = ?2)) AND ((SL.DIVISIONCODE='102' AND SOL.SUBCODE05=?3) OR (SOL.SUBCODE06=?3))"
        + " GROUP BY SS.SEQUENCE, CASE WHEN SL.DIVISIONCODE='102' THEN 'NA' ELSE SOL.SUBCODE05 END, CASE WHEN SL.DIVISIONCODE='102' THEN SOL.SUBCODE06 ELSE SOL.SUBCODE07 END ORDER BY SS.SEQUENCE, CASE WHEN SL.DIVISIONCODE='102' THEN 'NA' ELSE SOL.SUBCODE05 END", nativeQuery = true)
    List<Object[]> getAllSizesByStyleAndDestination(String styleCode, String destination, String color);

    @Query("select coalesce(max(salesorderline.subcode01), '') from Salesorderline salesorderline where salesorderline.projectcode=?1 ")
    String getStyleByProjectcode(String projectcode);

    @Query("select distinct coalesce(salesorderline.subcode05, ''), usergenericgroup.longdescription from Salesorderline salesorderline, Usergenericgroup usergenericgroup where salesorderline.id.salesordercompanycode = usergenericgroup.id.usergengrouptypecompanycode and usergenericgroup.id.usergenericgrouptypecode = 'DST' and salesorderline.subcode05 = usergenericgroup.id.code and salesorderline.projectcode=?1 and salesorderline.subcode06 = ?2")
    List<Object[]> getDestinationsByStyle(String style, String color);

    @Query(value = "SELECT SO.ORDPRNCUSTOMERSUPPLIERCODE, OP.LEGALNAME1 FROM SALESORDER SO, VIEWORDERPARTNER OP"
    + " WHERE SO.COMPANYCODE = OP.CUSTOMERSUPPLIERCOMPANYCODE AND SO.ORDPRNCUSTOMERSUPPLIERCODE = OP.CUSTOMERSUPPLIERCODE AND"
    + " SO.PROJECTCODE=?1", nativeQuery = true)
    List<Object[]> fetchBuyerByProject(String projectcode);

    @Query(value = "select PR.CODE, SOL.SUBCODE01, OP.CUSTOMERSUPPLIERCODE, OP.LEGALNAME1, AD1.VALUEBOOLEAN, SUM(SOL.USERPRIMARYQUANTITY) ORDERQUANTITY, NVL(MAX(AD.VALUEDECIMAL), 0) TOLERANCE_PERCENTAGE,"
        + " SUM(SOL.USERPRIMARYQUANTITY + (SOL.USERPRIMARYQUANTITY*NVL(AD.VALUEDECIMAL, 0)/100)) TOTALORDERQUANTITY,"
        + " NVL(SUM(PL.PRIMARYQTY), 0) SHIPPEDQUANTITY,"
        + " CAST(NVL(CAST(SUM(PL.PRIMARYQTY) AS DECIMAL(15,5)) / CAST(SUM(SOL.USERPRIMARYQUANTITY) AS DECIMAL(15,5)),0) * 100 AS DECIMAL(15, 2)) SHIPPED_PERCENTAGE"
        + " FROM PROJECT  PR LEFT OUTER JOIN ADSTORAGE AD ON PR.ABSUNIQUEID=AD.UNIQUEID AND AD.NAMEENTITYNAME = 'Project' AND"
        + " AD.NAMENAME='ProductionTolerancePercentage' LEFT OUTER JOIN ADSTORAGE AD1 ON PR.ABSUNIQUEID=AD1.UNIQUEID AND AD1.NAMEENTITYNAME = 'Project' AND"
        + " AD1.NAMENAME='ProjectClosed' AND AD1.FIELDNAME='ProjectClosed', SALESORDERLINE SOL LEFT OUTER JOIN PLANTINVOICELINE PL ON"
        + " SOL.ABSUNIQUEID = PL.SOLINEABSUNIQUEID, SALESORDER SO, VIEWORDERPARTNER OP"
        + " WHERE PR.COMPANYCODE=SOL.SALESORDERCOMPANYCODE and PR.code = SOL.projectcode AND"
        + " SOL.SALESORDERCOMPANYCODE = SO.COMPANYCODE  AND SOL.SALESORDERCOUNTERCODE = SO.COUNTERCODE AND SOL.SALESORDERCODE = SO.CODE AND"
        + " OP.CUSTOMERSUPPLIERCOMPANYCODE = SO.COMPANYCODE AND OP.CUSTOMERSUPPLIERTYPE = '1' AND SO.ORDPRNCUSTOMERSUPPLIERCODE = OP.CUSTOMERSUPPLIERCODE AND PR.CODE LIKE ?1 AND NVL(AD1.VALUEBOOLEAN, 0) = ?2"
        + " GROUP BY PR.CODE, SOL.SUBCODE01, OP.CUSTOMERSUPPLIERCODE, OP.LEGALNAME1, AD1.VALUEBOOLEAN"
        + " HAVING CAST(NVL(CAST(SUM(PL.PRIMARYQTY) AS DECIMAL(15,5)) / CAST(SUM(SOL.USERPRIMARYQUANTITY) AS DECIMAL(15,5)),0) * 100 AS DECIMAL(15, 2)) > ?3 ",
        countQuery =  "select PR.CODE, SOL.SUBCODE01, OP.CUSTOMERSUPPLIERCODE, OP.LEGALNAME1, AD1.VALUEBOOLEAN, SUM(SOL.USERPRIMARYQUANTITY) ORDERQUANTITY, NVL(MAX(AD.VALUEDECIMAL), 0) TOLERANCE_PERCENTAGE,"
        + " SUM(SOL.USERPRIMARYQUANTITY + (SOL.USERPRIMARYQUANTITY*NVL(AD.VALUEDECIMAL, 0)/100)) TOTALORDERQUANTITY,"
        + " NVL(SUM(PL.PRIMARYQTY), 0) SHIPPEDQUANTITY,"
        + " CAST(NVL(CAST(SUM(PL.PRIMARYQTY) AS DECIMAL(15,5)) / CAST(SUM(SOL.USERPRIMARYQUANTITY) AS DECIMAL(15,5)),0) * 100 AS DECIMAL(15, 2)) SHIPPED_PERCENTAGE"
        + " FROM PROJECT  PR LEFT OUTER JOIN ADSTORAGE AD ON PR.ABSUNIQUEID=AD.UNIQUEID AND AD.NAMEENTITYNAME = 'Project' AND"
        + " AD.NAMENAME='ProductionTolerancePercentage' LEFT OUTER JOIN ADSTORAGE AD1 ON PR.ABSUNIQUEID=AD1.UNIQUEID AND AD1.NAMEENTITYNAME = 'Project' AND"
        + " AD1.NAMENAME='ProjectClosed' AND AD1.FIELDNAME='ProjectClosed', SALESORDERLINE SOL LEFT OUTER JOIN PLANTINVOICELINE PL ON"
        + " SOL.ABSUNIQUEID = PL.SOLINEABSUNIQUEID, SALESORDER SO, VIEWORDERPARTNER OP"
        + " WHERE PR.COMPANYCODE=SOL.SALESORDERCOMPANYCODE and PR.code = SOL.projectcode AND"
        + " SOL.SALESORDERCOMPANYCODE = SO.COMPANYCODE  AND SOL.SALESORDERCOUNTERCODE = SO.COUNTERCODE AND SOL.SALESORDERCODE = SO.CODE AND"
        + " OP.CUSTOMERSUPPLIERCOMPANYCODE = SO.COMPANYCODE AND OP.CUSTOMERSUPPLIERTYPE = '1' AND SO.ORDPRNCUSTOMERSUPPLIERCODE = OP.CUSTOMERSUPPLIERCODE AND PR.CODE LIKE ?1 AND NVL(AD1.VALUEBOOLEAN, 0) = ?2"
        + " GROUP BY PR.CODE, SOL.SUBCODE01, OP.CUSTOMERSUPPLIERCODE, OP.LEGALNAME1, AD1.VALUEBOOLEAN"
        + " HAVING CAST(NVL(CAST(SUM(PL.PRIMARYQTY) AS DECIMAL(15,5)) / CAST(SUM(SOL.USERPRIMARYQUANTITY) AS DECIMAL(15,5)),0) * 100 AS DECIMAL(15, 2)) >?3 ", nativeQuery = true)
    Page<Object[]> fetchSalesOrderByCodeAndShippedPercentage(String projectcode, String status, Double valueOf, Pageable pageable);

    @Query(value = "SELECT PR.CODE, SO.COMPANYCODE, SO.DIVISIONCODE, NVL(SO.CODE, '') SALESORDERCODE, SOL.ORDERLINE, SOL.ORDERSUBLINE, TO_CHAR(SO.ORDERDATE, 'DD-MM-YYYY') SALESORDERDATE,"
        + " OP.CUSTOMERSUPPLIERCODE, OP.LEGALNAME1, NVL(SOL.SUBCODE01, '') STYLE, CASE WHEN SOL.ITEMTYPEAFICODE = 'PKG' THEN SOL.SUBCODE06 ELSE SOL.SUBCODE05 END STYLE_COLOR,"
        + " CASE WHEN SOL.ITEMTYPEAFICODE = 'PKG' THEN SOL.SUBCODE07 ELSE SOL.SUBCODE06 END STYLE_SIZE, AD1.VALUEBOOLEAN,"
        + " NVL(MAX(SOL.USERPRIMARYQUANTITY), 0) ORDERQUANTITY, NVL(MAX(AD.VALUEDECIMAL), 0) TOLERANCE_PERCENTAGE,"
        + " NVL(MAX(SOL.USERPRIMARYQUANTITY + (SOL.USERPRIMARYQUANTITY*NVL(AD.VALUEDECIMAL, 0)/100)), 0) TOTALORDERQUANTITY,"
        + " NVL(SUM(PL.PRIMARYQTY), 0) SHIPPEDQUANTITY, CASE WHEN SO.CURRENTSTATUS = '2' THEN 'SUSPENDED' ELSE 'ACTIVE' END STATUS"
        + " FROM PROJECT  PR LEFT OUTER JOIN ADSTORAGE AD ON PR.ABSUNIQUEID=AD.UNIQUEID AND AD.NAMEENTITYNAME = 'Project' AND"
        + " AD.NAMENAME='ProductionTolerancePercentage' LEFT OUTER JOIN ADSTORAGE AD1 ON PR.ABSUNIQUEID=AD1.UNIQUEID AND AD1.NAMEENTITYNAME = 'Project' AND"
        + " AD1.NAMENAME='ProjectClosed' AND AD1.FIELDNAME='ProjectClosed', SALESORDERLINE SOL LEFT OUTER JOIN PLANTINVOICELINE PL ON"
        + " SOL.ABSUNIQUEID = PL.SOLINEABSUNIQUEID, SALESORDER SO, VIEWORDERPARTNER OP"
        + " WHERE PR.COMPANYCODE=SOL.SALESORDERCOMPANYCODE and PR.code = SOL.projectcode AND"
        + " SOL.SALESORDERCOMPANYCODE = SO.COMPANYCODE  AND SOL.SALESORDERCOUNTERCODE = SO.COUNTERCODE AND SOL.SALESORDERCODE = SO.CODE AND"
        + " OP.CUSTOMERSUPPLIERCOMPANYCODE = SO.COMPANYCODE AND OP.CUSTOMERSUPPLIERTYPE = '1' AND"
        + " SO.ORDPRNCUSTOMERSUPPLIERCODE = OP.CUSTOMERSUPPLIERCODE AND PR.CODE=?1"
        + " GROUP BY PR.CODE, SO.COMPANYCODE, SO.DIVISIONCODE, SO.CODE, SOL.ORDERLINE, SOL.ORDERSUBLINE, SO.ORDERDATE, OP.CUSTOMERSUPPLIERCODE, OP.LEGALNAME1,"
        + " SOL.SUBCODE01, CASE WHEN SOL.ITEMTYPEAFICODE = 'PKG' THEN SOL.SUBCODE06 ELSE SOL.SUBCODE05 END,"
        + " CASE WHEN SOL.ITEMTYPEAFICODE = 'PKG' THEN SOL.SUBCODE07 ELSE SOL.SUBCODE06 END, AD1.VALUEBOOLEAN, CASE WHEN SO.CURRENTSTATUS = '2' THEN 'SUSPENDED' ELSE 'ACTIVE' END"
        + " ORDER BY 1, 2, 3, 4, 5, 6", nativeQuery = true)
    List<Object[]> fetchByProject(String projectCode);
}