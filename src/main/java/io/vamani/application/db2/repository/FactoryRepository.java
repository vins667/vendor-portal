package io.vamani.application.db2.repository;

import java.util.List;
import io.vamani.application.db2.domain.Factory;
import javax.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface FactoryRepository extends JpaRepository<Factory, String>{
    @Override
    @Query("select factory from Factory factory where factory.factCode<>'ALL' order by factory.factCode")
    List<Factory> findAll();

    @Query("select factory from Factory factory where factory.factCode<>'ALL' and factory.factCode<>?1 order by factory.factCode")
    List<Factory> findAllAndNotPlant(String factory);

    @Query("select factory from Factory factory where factory.factCode in (select businessunitvscompany.id.factorycode from Businessunitvscompany businessunitvscompany where businessunitvscompany.id.businessunitcode = ?1) order by factory.factCode")
    List<Factory> findAllByDivisioncode(String divisioncode);

    @Query("select factory from Factory factory where factory.factCode like ?1")
   Page<Factory> findAllFactory(String factCode, Pageable page);

   @Query(value="SELECT F.CODE,F.LONGDESCRIPTION,ADDRESSLINE1,ADDRESSLINE2,ADDRESSLINE3,ADDRESSLINE4,ADDRESSLINE5,F.POSTALCODE,F.DISTRICT,F.STATECODE,GSTINNUMBER G"
   		    + " FROM FACTORY F,ADDRESSGST G WHERE F.ABSUNIQUEID=G.UNIQUEID  AND F.CODE= ?1",nativeQuery = true)
   List<Object[]> findByFactCode(String code);

   @Query(value="SELECT O.ABSUNIQUEID,O.CUSTOMERSUPPLIERCODE,B.LEGALNAME1 FROM ORDERPARTNER O,BUSINESSPARTNER B WHERE O.ORDERBUSINESSPARTNERNUMBERID=B.NUMBERID AND O.CUSTOMERSUPPLIERCODE LIKE ?1",
        countQuery = "SELECT COUNT(*) FROM ORDERPARTNER O,BUSINESSPARTNER B WHERE O.ORDERBUSINESSPARTNERNUMBERID=B.NUMBERID AND O.CUSTOMERSUPPLIERCODE LIKE ?1",nativeQuery = true)
   Page<Object[]> findByAllOrderPartner(String supplirecode,Pageable var1);

   @Query(value="SELECT O.CUSTOMERSUPPLIERCODE,NVL(TRIM(B.SHORTNAME),'')SHORTNAME,ADDRESSLINE1,ADDRESSLINE2,ADDRESSLINE3,ADDRESSLINE4,ADDRESSLINE5,POSTALCODE,DISTRICT,GSTINNUMBER G,G.STATECODE"
   		    + " FROM ORDERPARTNER O,BUSINESSPARTNER B,ADDRESSGST G WHERE O.ORDERBUSINESSPARTNERNUMBERID=B.NUMBERID AND B.ABSUNIQUEID=G.UNIQUEID AND O.ABSUNIQUEID= ?1",nativeQuery = true)
   List<Object[]> findOrderPartnerBySupplirecode(String absuniqueid);

   @Query(value="select distinct NVL(HX.VALUE*2,0) taxper from TAXTEMPLATEdetail HX, hsngstmapping HS where HX.ITAXCODE IN (SELECT templatetype FROM voplreporttemplate WHERE reporttype = 'GST_PURCHASE' AND templatecode = 'SGST_TAX')"
   		 + " AND HX.TAXTEMPLATEHEADERCODE = HS.SGSTTAXTEMPLATECODE AND HS.TARRIFCODE = ?1",nativeQuery = true)
   Double findByTaxCGST(String code);

   @Query(value="select distinct NVL(HX.VALUE,0)taxper from TAXTEMPLATEdetail HX, hsngstmapping HS where HX.ITAXCODE IN ( SELECT templatetype FROM voplreporttemplate WHERE reporttype = 'GST_PURCHASE' AND templatecode = 'IGST_TAX')"
	   		 + " AND HX.TAXTEMPLATEHEADERCODE = HS.IGSTTAXTEMPLATECODE AND HS.TARRIFCODE = ?1",nativeQuery = true)
   Double findByTaxIGST(String code);

}
