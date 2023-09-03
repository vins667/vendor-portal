package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Productiondemand;
import io.vamani.application.db2.domain.ProductiondemandId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface ProductiondemandRepository extends JpaRepository<Productiondemand, ProductiondemandId> {

	@Query("select productiondemand from Productiondemand productiondemand where concat(Trim(productiondemand.id.companycode), '|', Trim(productiondemand.id.countercode), '|', Trim(productiondemand.id.code)) IN(?1) order by productiondemand.itemtypeaficode, productiondemand.subcode01, productiondemand.subcode02")
    List<Productiondemand> findByIds(List<String> ids);

    @Query("select productiondemand from Productiondemand productiondemand where concat(Trim(productiondemand.id.companycode), '|', Trim(productiondemand.id.countercode), '|', Trim(productiondemand.id.code)) IN(?1) and productiondemand.subcode07 = ?2 order by productiondemand.id.code")
    List<Productiondemand> findByIdsAndColor(List<String> ids, String color);

    @Query("select productiondemand from Productiondemand productiondemand where concat(Trim(productiondemand.id.companycode), '|', Trim(productiondemand.id.countercode), '|', Trim(productiondemand.id.code)) IN(?1) and productiondemand.subcode05 = ?2 order by productiondemand.id.code")
    List<Productiondemand> findByIdsAndColorForHFD(List<String> ids, String color);

    @Query("select productiondemand from Productiondemand productiondemand where productiondemand.id.companycode = ?1 and productiondemand.id.code = ?2")
    Productiondemand findByDemandCode(String companycode, String demandcode);

    @Query("select productiondemandstep.id.productiondemandcompanycode, productiondemandstep.id.productiondemandcountercode, min(productiondemandstep.id.productiondemandcode), productiondemandstep.groupstepnumber, productiondemand.itemtypeaficode, productiondemandstep.operationcode,"+
         " productiondemand.userprimaryuomcode, productiondemand.usersecondaryuomcode, productiondemand.userprimaryquantity, productiondemand.usersecondaryquantity, productiondemandstep.workcentercode"+
         " from Productiondemand productiondemand inner join Productiondemandstep productiondemandstep on productiondemand.id.companycode=productiondemandstep.id.productiondemandcompanycode and"+
         " productiondemand.id.countercode=productiondemandstep.id.productiondemandcountercode and productiondemand.id.code = productiondemandstep.id.productiondemandcode"+
         " where productiondemandstep.id.productiondemandcompanycode = ?1 and productiondemandstep.productionordercode = ?2 and productiondemand.projectcode = ?3 and"+
         " productiondemand.subcode07=?4 and productiondemand.subcode08=?5 and productiondemandstep.operationcode=?6 group by productiondemandstep.id.productiondemandcompanycode, productiondemandstep.id.productiondemandcountercode, productiondemandstep.groupstepnumber, productiondemand.itemtypeaficode, productiondemandstep.operationcode,"+
         " productiondemand.userprimaryuomcode, productiondemand.usersecondaryuomcode, productiondemand.userprimaryquantity, productiondemand.usersecondaryquantity, productiondemandstep.workcentercode")
    List<Object[]> getDemandDetails(String companycode, String productionordercode, String style, String color, String size, String operationCode);

    @Query("select productiondemandstep.id.productiondemandcompanycode, productiondemandstep.id.productiondemandcountercode, min(productiondemandstep.id.productiondemandcode), productiondemandstep.groupstepnumber, productiondemand.itemtypeaficode, productiondemandstep.operationcode,"+
        " productiondemand.userprimaryuomcode, productiondemand.usersecondaryuomcode, productiondemand.userprimaryquantity, productiondemand.usersecondaryquantity, productiondemandstep.workcentercode"+
        " from Productiondemand productiondemand inner join Productiondemandstep productiondemandstep on productiondemand.id.companycode=productiondemandstep.id.productiondemandcompanycode and"+
        " productiondemand.id.countercode=productiondemandstep.id.productiondemandcountercode and productiondemand.id.code = productiondemandstep.id.productiondemandcode"+
        " where productiondemandstep.id.productiondemandcompanycode = ?1 and productiondemandstep.productionordercode = ?2 and productiondemand.projectcode = ?3 and"+
        " productiondemand.subcode05=?4 and productiondemand.subcode06=?5 and productiondemandstep.operationcode=?6 group by productiondemandstep.id.productiondemandcompanycode, productiondemandstep.id.productiondemandcountercode, productiondemandstep.groupstepnumber, productiondemand.itemtypeaficode, productiondemandstep.operationcode,"+
        " productiondemand.userprimaryuomcode, productiondemand.usersecondaryuomcode, productiondemand.userprimaryquantity, productiondemand.usersecondaryquantity, productiondemandstep.workcentercode")
    List<Object[]> getHCDemandDetails(String companycode, String productionordercode, String style, String color, String size, String operationCode);

    @Query("select productiondemandstep.id.productiondemandcompanycode, productiondemandstep.id.productiondemandcountercode, min(productiondemandstep.id.productiondemandcode), productiondemandstep.groupstepnumber, productiondemand.itemtypeaficode, productiondemandstep.operationcode,"+
        " productiondemand.userprimaryuomcode, productiondemand.usersecondaryuomcode"+
        " from Productiondemand productiondemand inner join Productiondemandstep productiondemandstep on productiondemand.id.companycode=productiondemandstep.id.productiondemandcompanycode and"+
        " productiondemand.id.countercode=productiondemandstep.id.productiondemandcountercode and productiondemand.id.code = productiondemandstep.id.productiondemandcode"+
        " where productiondemandstep.id.productiondemandcompanycode = ?1 and productiondemandstep.productionordercode = ?2 and productiondemand.subcode08 = ?3 and"+
        " productiondemand.subcode09=?4 and productiondemand.subcode10=?5 and productiondemandstep.operationcode='STITCH' group by productiondemandstep.id.productiondemandcompanycode, productiondemandstep.id.productiondemandcountercode, productiondemandstep.groupstepnumber, productiondemand.itemtypeaficode, productiondemandstep.operationcode,"+
        " productiondemand.userprimaryuomcode, productiondemand.usersecondaryuomcode")
    List<Object[]> getDemandDetailsStitch(String companycode, String productionordercode, String subcode08, String subcode09, String subcode10);

    @Query("select productiondemandstep.id.productiondemandcompanycode, productiondemandstep.id.productiondemandcountercode, min(productiondemandstep.id.productiondemandcode), productiondemandstep.groupstepnumber, productiondemand.itemtypeaficode, productiondemandstep.operationcode,"+
        " productiondemand.userprimaryuomcode, productiondemand.usersecondaryuomcode"+
        " from Productiondemand productiondemand inner join Productiondemandstep productiondemandstep on productiondemand.id.companycode=productiondemandstep.id.productiondemandcompanycode and"+
        " productiondemand.id.countercode=productiondemandstep.id.productiondemandcountercode and productiondemand.id.code = productiondemandstep.id.productiondemandcode"+
        " where productiondemandstep.id.productiondemandcompanycode = ?1 and productiondemandstep.productionordercode = ?2 and productiondemand.subcode08 = ?3 and"+
        " productiondemand.subcode09=?4 and productiondemand.subcode10=?5 and productiondemandstep.operationcode in ('STITCH', 'CUTTING') group by productiondemandstep.id.productiondemandcompanycode, productiondemandstep.id.productiondemandcountercode, productiondemandstep.groupstepnumber, productiondemand.itemtypeaficode, productiondemandstep.operationcode,"+
        " productiondemand.userprimaryuomcode, productiondemand.usersecondaryuomcode")
    List<Object[]> getDemandDetailsStitchAndCutting(String companycode, String productionordercode, String subcode08, String subcode09, String subcode10);

    @Query("select productiondemandstep.id.productiondemandcompanycode, productiondemandstep.id.productiondemandcountercode, min(productiondemandstep.id.productiondemandcode), productiondemandstep.groupstepnumber, productiondemand.itemtypeaficode, productiondemandstep.operationcode,"+
        " productiondemand.userprimaryuomcode, productiondemand.usersecondaryuomcode"+
        " from Productiondemand productiondemand inner join Productiondemandstep productiondemandstep on productiondemand.id.companycode=productiondemandstep.id.productiondemandcompanycode and"+
        " productiondemand.id.countercode=productiondemandstep.id.productiondemandcountercode and productiondemand.id.code = productiondemandstep.id.productiondemandcode"+
        " where productiondemandstep.id.productiondemandcompanycode = ?1 and productiondemandstep.productionordercode = ?2 and productiondemand.subcode08 = ?3 and"+
        " productiondemand.subcode09=?4 and productiondemand.subcode10=?5 and productiondemandstep.operationcode='PACKING' group by productiondemandstep.id.productiondemandcompanycode, productiondemandstep.id.productiondemandcountercode, productiondemandstep.groupstepnumber, productiondemand.itemtypeaficode, productiondemandstep.operationcode,"+
        " productiondemand.userprimaryuomcode, productiondemand.usersecondaryuomcode")
    List<Object[]> getDemandDetailsPacking(String companycode, String productionordercode, String subcode08, String subcode09, String subcode10);

    @Query("select productiondemand from Productiondemand productiondemand inner join Productiondemandstep productiondemandstep on productiondemand.id.companycode=productiondemandstep.id.productiondemandcompanycode and"+
        " productiondemand.id.countercode=productiondemandstep.id.productiondemandcountercode and productiondemand.id.code = productiondemandstep.id.productiondemandcode"+
        " where productiondemandstep.id.productiondemandcompanycode = ?1 and productiondemandstep.productionordercode = ?2 and productiondemand.projectcode = ?3 and"+
        " productiondemand.subcode07=?4 and productiondemand.subcode08=?5 and productiondemandstep.operationcode='CUTTING'")
    List<Productiondemand> getDemandDetailsByCode(String companycode, String productionordercode, String subcode08, String subcode09, String subcode10);

    @Query("select productiondemand from Productiondemand productiondemand inner join Productiondemandstep productiondemandstep on productiondemand.id.companycode=productiondemandstep.id.productiondemandcompanycode and"+
        " productiondemand.id.countercode=productiondemandstep.id.productiondemandcountercode and productiondemand.id.code = productiondemandstep.id.productiondemandcode"+
        " where productiondemandstep.id.productiondemandcompanycode = ?1 and productiondemandstep.productionordercode = ?2 and productiondemand.projectcode = ?3 and"+
        " productiondemand.subcode05=?4 and productiondemand.subcode06=?5 and productiondemandstep.operationcode='CUTTING'")
    List<Productiondemand> getHCDemandDetailsByCode(String companycode, String productionordercode, String subcode08, String subcode09, String subcode10);

    @Query("select productiondemand from Productiondemand productiondemand inner join Productiondemandstep productiondemandstep on productiondemand.id.companycode=productiondemandstep.id.productiondemandcompanycode and"+
        " productiondemand.id.countercode=productiondemandstep.id.productiondemandcountercode and productiondemand.id.code = productiondemandstep.id.productiondemandcode"+
        " where productiondemandstep.id.productiondemandcompanycode = ?1 and productiondemandstep.productionordercode = ?2 and productiondemand.subcode08 = ?3 and"+
        " productiondemand.subcode09=?4 and productiondemand.subcode10=?5 and productiondemandstep.operationcode='STITCH'")
    List<Productiondemand> getDemandDetailsByCodeStitch(String companycode, String productionordercode, String subcode08, String subcode09, String subcode10);

    @Query("select productiondemand from Productiondemand productiondemand inner join Productiondemandstep productiondemandstep on productiondemand.id.companycode=productiondemandstep.id.productiondemandcompanycode and"+
        " productiondemand.id.countercode=productiondemandstep.id.productiondemandcountercode and productiondemand.id.code = productiondemandstep.id.productiondemandcode"+
        " where productiondemandstep.id.productiondemandcompanycode = ?1 and productiondemandstep.productionordercode = ?2 and productiondemand.subcode08 = ?3 and"+
        " productiondemand.subcode09=?4 and productiondemand.subcode10=?5 and productiondemandstep.operationcode='PACKING'")
    List<Productiondemand> getDemandDetailsByCodePacking(String companycode, String productionordercode, String subcode08, String subcode09, String subcode10);

    @Query("select productiondemand from Productiondemand productiondemand inner join Productiondemandstep productiondemandstep on productiondemand.id.companycode=productiondemandstep.id.productiondemandcompanycode and"+
        " productiondemand.id.countercode=productiondemandstep.id.productiondemandcountercode and productiondemand.id.code = productiondemandstep.id.productiondemandcode"+
        " where productiondemandstep.id.productiondemandcompanycode = ?1 and productiondemandstep.productionordercode = ?2 and productiondemand.subcode01 = ?3 and productiondemand.subcode02 = ?4 and productiondemand.subcode03 = ?5 and productiondemand.subcode04 = ?6 and productiondemand.subcode05 = ?7 and productiondemand.subcode06 = ?8  and productiondemandstep.operationcode='FABDYE'")
    List<Productiondemand> getDemandDetailsByCodeFabDye(String companycode, String productionordercode, String subcode01, String subcode02, String subcode03, String subcode04, String subcode05, String subcode06);

    @Query("select productiondemand from Productiondemand productiondemand inner join Productiondemandstep productiondemandstep on productiondemand.id.companycode=productiondemandstep.id.productiondemandcompanycode and"+
        " productiondemand.id.countercode=productiondemandstep.id.productiondemandcountercode and productiondemand.id.code = productiondemandstep.id.productiondemandcode"+
        " where productiondemandstep.id.productiondemandcompanycode = ?1 and productiondemandstep.productionordercode = ?2  and productiondemandstep.operationcode=?3")
    List<Productiondemand> getDemandDetailsByOrdercodeAndOperation(String companycode, String productionordercode, String operationcode);

    @Query("select productiondemand from Productiondemand productiondemand inner join Productiondemandstep productiondemandstep on productiondemand.id.companycode=productiondemandstep.id.productiondemandcompanycode and"+
            " productiondemand.id.countercode=productiondemandstep.id.productiondemandcountercode and productiondemand.id.code = productiondemandstep.id.productiondemandcode"+
            " where productiondemandstep.id.productiondemandcompanycode = ?1 and productiondemandstep.productionordercode = ?2 and productiondemandstep.operationcode='YARNDYE'")
        List<Productiondemand> getDemandDetailsByCodeYarnDyeing(String companycode, String productionordercode);

    @Query("select productiondemand from Productiondemand productiondemand inner join Productiondemandstep productiondemandstep on productiondemand.id.companycode=productiondemandstep.id.productiondemandcompanycode and"+
            " productiondemand.id.countercode=productiondemandstep.id.productiondemandcountercode and productiondemand.id.code = productiondemandstep.id.productiondemandcode"+
            " where productiondemandstep.id.productiondemandcompanycode = ?1 and productiondemandstep.productionordercode = ?2 and productiondemandstep.operationcode in('SPINNING', 'MIXING')")
    List<Productiondemand> getDemandDetailsBySpinning(String companycode, String productionordercode);

    @Query("select distinct productiondemand.subcode06, usergenericgroup.longdescription from Productiondemand productiondemand inner join Usergenericgroup usergenericgroup on usergenericgroup.id.usergenericgrouptypecode = 'DST' and productiondemand.subcode06 = usergenericgroup.id.code where productiondemand.subcode01 = ?1 and concat(Trim(productiondemand.id.companycode), '|', Trim(productiondemand.id.countercode), '|', Trim(productiondemand.id.code)) IN(?2)")
	List<Object[]> getAllCountryByStyle(String style, List<String> ids);

    @Query("select distinct case when productiondemand.divisioncode='101' then productiondemand.subcode06 else productiondemand.subcode05 end, color.longdescription " +
        " from Productiondemand productiondemand inner join Color color on color.id.colorfoldercode = 'COL' and " +
        " ((productiondemand.divisioncode='101' and productiondemand.subcode06 = color.id.code) or (productiondemand.divisioncode='102' and productiondemand.subcode05 = color.id.code)) " +
        " where productiondemand.projectcode = ?1 and concat(Trim(productiondemand.id.companycode), '|', Trim(productiondemand.id.countercode), '|', Trim(productiondemand.id.code)) IN(?2) ")
	List<Object[]> getAllSewColorByStyle(String style, List<String> ids);

    @Query("select distinct case when productiondemand.divisioncode='101' then productiondemand.subcode07 else productiondemand.subcode05 end, color.longdescription from Productiondemand productiondemand inner join Color color on color.id.colorfoldercode = 'COL' " +
        " and ((productiondemand.divisioncode='101' and productiondemand.subcode07 = color.id.code) or (productiondemand.divisioncode='102' and productiondemand.subcode05 = color.id.code)) where productiondemand.projectcode = ?1 " +
        " and concat(Trim(productiondemand.id.companycode), '|', Trim(productiondemand.id.countercode), '|', Trim(productiondemand.id.code)) IN(?2) ")
    List<Object[]> getAllColorByStyle(String style, List<String> ids);

    @Query("select productiondemand.subcode07, coalesce(sum(productiondemand.userprimaryquantity), 0)  from Productiondemand productiondemand where productiondemand.projectcode = ?1 and productiondemand.subcode06 = ?2 and concat(Trim(productiondemand.id.companycode), '|', Trim(productiondemand.id.countercode), '|', Trim(productiondemand.id.code)) IN(?3) group by productiondemand.subcode07")
    List<Object[]> getAllSewQuantityByColorAndCountry(String style, String color, List<String> ids);

    @Query("select productiondemand.subcode08, coalesce(sum(productiondemand.userprimaryquantity), 0)  from Productiondemand productiondemand where productiondemand.projectcode = ?1 and productiondemand.subcode07 = ?2 and concat(Trim(productiondemand.id.companycode), '|', Trim(productiondemand.id.countercode), '|', Trim(productiondemand.id.code)) IN(?3) group by productiondemand.subcode08")
    List<Object[]> getAllQuantityByColorAndCountry(String style, String color, List<String> ids);

    @Query("select productiondemand.subcode08, coalesce(sum(productiondemand.userprimaryquantity), 0)  from Productiondemand productiondemand where productiondemand.projectcode = ?1 and productiondemand.subcode05 = ?2 and concat(Trim(productiondemand.id.companycode), '|', Trim(productiondemand.id.countercode), '|', Trim(productiondemand.id.code)) IN(?3) group by productiondemand.subcode08")
    List<Object[]> getAllHFQuantityByColorAndCountry(String style, String color, List<String> ids);

    @Query(value = "select coalesce(valuedecimal,0) from project pr inner join adstorage ad on pr.absuniqueid = ad.uniqueid and"
        + " ad.nameentityname='Project' and ad.namename='ProductionTolerancePercentage' and ad.fieldname='ProductionTolerancePercentage'"
        + " where pr.code=?1", nativeQuery = true)
    BigDecimal getToleranceQuantity(String projectcode);


}
