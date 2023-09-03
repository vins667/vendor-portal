package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Viewfindocument;
import io.vamani.application.db2.domain.ViewfindocumentId;
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
public interface ViewfindocumentRepository extends JpaRepository<Viewfindocument, ViewfindocumentId> {

    @Query("select viewfindocument from Viewfindocument viewfindocument where viewfindocument.id.companycode = ?1 and viewfindocument.id.businessunitcode = ?2 and viewfindocument.id.financialyearcode = ?3 and viewfindocument.id.documenttemplatecode = ?4 and viewfindocument.id.findocumentcode = ?5 order by viewfindocument.id.linenumber")
    List<Viewfindocument> findAllByFindocument(String companycode, String businessunitcode, Integer financialyearcode, String documenttypecode, String code);

    @Query(value = "SELECT NVL(CASE WHEN STATECODE = SUPPLIERSTATECODE THEN 'CGST' ELSE 'IGST' END,'')TAXTYPE FROM (select DISTINCT FC.STATECODE, (SELECT MAX(StateCode) FROM orderpartnerie where CUSTOMERSUPPLIERCODE=FD.SUPPLIERCODE)SUPPLIERSTATECODE  from VIEWFINDOCUMENT VD, DI_FACTORYCOSTCENTERMAPPING DI, factory fc, FINDOCUMENT FD WHERE VD.PROFITCENTERCODE = DI.COSTCENTERTYPE AND DI.FACTORYCODE = FC.CODE AND vd.COMPANYCODE = fd.COMPANYCODE AND VD.FINDOCUMENTCODE = fd.CODE AND VD.DOCUMENTTEMPLATECODE='VD2' AND FD.COMPANYCODE=?1 AND FD.BUSINESSUNITCODE=?2 AND FD.FINANCIALYEARCODE=?3 AND FD.DOCUMENTTEMPLATECODE=?4 AND FD.CODE=?5)",nativeQuery = true)
    String getGstType(String companycode, String businessunit, String financialyear, String documenttemplate, String code);

    @Query(value = "SELECT NVL(TRIM(MD.TARIFFCODE), '') TARIFFCODE, MD.ITEMTYPEAFICODE, NVL(TRIM(MD.SUBCODE01), '') SUBCODE01, NVL(TRIM(MD.SUBCODE02), '') SUBCODE02, NVL(TRIM(MD.SUBCODE03), '') SUBCODE03,"
        + " NVL(TRIM(MD.SUBCODE04), '') SUBCODE04, NVL(TRIM(MD.SUBCODE05), '') SUBCODE05, NVL(TRIM(MD.SUBCODE06), '') SUBCODE06, NVL(TRIM(MD.SUBCODE07), '') SUBCODE07,"
        + " NVL(TRIM(MD.SUBCODE08), '') SUBCODE08, NVL(TRIM(MD.SUBCODE09), '') SUBCODE09, NVL(TRIM(MD.SUBCODE10), '') SUBCODE10, MD.PRIMARYUMCODE,"
        + " FID.SUMMARIZEDDESCRIPTION"
        + " FROM MRNHEADER MH, PURCHASEINVOICE PI, MRNDETAIL MD, FULLITEMKEYDECODER FID, FINDOCUMENT FD"
        + " WHERE MH.COMPANYCODE = PI.COMPANYCODE AND"
        + " MH.PURINVOICEORDPRNCSMSUPTYPE = PI.ORDPRNCUSTOMERSUPPLIERTYPE AND MH.ORDPRNCUSTOMERSUPPLIERCODE = PI.ORDPRNCUSTOMERSUPPLIERCODE AND"
        + " MH.PURCHASEINVOICECODE = PI.CODE AND MH.PURCHASEINVOICEINVOICEDATE = PI.INVOICEDATE AND"
        + " MH.COMPANYCODE = MD.MRNHEADERCOMPANYCODE AND MH.DIVISIONCODE = MD.MRNHEADERDIVISIONCODE AND"
        + " MH.MRNPREFIXCODE = MD.MRNHEADERMRNPREFIXCODE AND MH.CODE = MD.MRNHEADERCODE AND"
        + " MD.ITEMTYPEAFICODE = FID.ITEMTYPECODE AND NVL(TRIM(MD.SUBCODE01), '') = NVL(TRIM(FID.SUBCODE01), '') AND NVL(TRIM(MD.SUBCODE02), '') = NVL(TRIM(FID.SUBCODE02), '') AND"
        + " NVL(TRIM(MD.SUBCODE03), '') = NVL(TRIM(FID.SUBCODE03), '') AND NVL(TRIM(MD.SUBCODE04), '') = NVL(TRIM(FID.SUBCODE04), '') AND NVL(TRIM(MD.SUBCODE05), '') = NVL(TRIM(FID.SUBCODE05), '') AND"
        + " NVL(TRIM(MD.SUBCODE06), '') = NVL(TRIM(FID.SUBCODE06), '') AND NVL(TRIM(MD.SUBCODE07), '') = NVL(TRIM(FID.SUBCODE07), '') AND NVL(TRIM(MD.SUBCODE08), '') = NVL(TRIM(FID.SUBCODE08), '') AND"
        + " NVL(TRIM(MD.SUBCODE09), '') = NVL(TRIM(FID.SUBCODE09), '') AND NVL(TRIM(MD.SUBCODE10), '') = NVL(TRIM(FID.SUBCODE10), '') AND"
        + " PI.DIVISIONCODE = FD.PURCHASEINVOICEDIVISIONCODE AND"
        + " PI.ORDPRNCUSTOMERSUPPLIERTYPE = FD.PURINVOICEORDPRNCSMSUPTYPE AND PI.ORDPRNCUSTOMERSUPPLIERCODE = FD.PURINVOICEORDPRNCSMSUPCODE AND"
        + " PI.CODE = FD.PURCHASEINVOICECODE AND PI.INVOICEDATE = FD.PURCHASEINVOICEINVOICEDATE AND FD.COMPANYCODE=?1 AND"
        + " FD.BUSINESSUNITCODE = ?2 AND FD.FINANCIALYEARCODE = ?3 AND FD.DOCUMENTTEMPLATECODE = ?4 AND FD.CODE = ?5", nativeQuery = true)
    List<Object[]> fetchMrnDetailByFindoc(String companycode, String businessunitcode, String finyearcode, String documenttemplate, String findoccode);
}
