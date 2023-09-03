package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.BankReconciliation;
import io.vamani.application.db2.domain.BankReconciliationdetail;
import io.vamani.application.db2.model.BankReconciliationdetailBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface BankReconciliationRepository extends JpaRepository<BankReconciliation, Long> {

    //@Query(value = "select ROW_NUMBER() OVER(ORDER BY CODE) rowcount, COMPANYCODE, BUSINESSUNITCODE, CODE, GLCODE, GLNAME, nvl(CHEQUENO,'')CHEQUENO, to_char(CHEQUEDATE,'yyyy-MM-dd')CHEQUEDATE, to_char(CHEQUECLEARINGDATE,'yyyy-MM-dd')CHEQUECLEARINGDATE, nvl(CHEQUEAMOUNT,0)CHEQUEAMOUNT, nvl(DEBITAMIT,0)DEBITAMIT, nvl(CREDITAMT,0)CREDITAMT, DOCUMENTYYPE, PROFITCENTERCODE, nvl(case when CHEQUECLEARINGDATE is null OR  CHEQUECLEARINGDATE > ?2 THEN DEBITAMIT ELSE 0 END,0)CHECKDEPOSIRNOTCLEAR, nvl(case when CHEQUECLEARINGDATE is null OR  CHEQUECLEARINGDATE > ?2 THEN CREDITAMT ELSE 0 END,0)CHECKISSUENOTCLEAR, narration, to_char(RECONCILIATIONDATE,'yyyy-MM-dd')RECONCILIATIONDATE  from ( SELECT fd.ABSUNIQUEID, fd.companycode, fd.businessunitcode, fd.code, fdl.glcode, nvl(gm.longdescription, '') glname, nvl(ck.chequeno, '') chequeno, fd.chequedate, ck.chequeclearingdate, nvl(ck.chequeamount, 0) chequeamount, nvl(SUM(CASE WHEN v.amountincc > 0 AND v.documenttypecode <> 'OB' THEN v.amountincc ELSE 0 END), 0) debitamit, nvl(SUM(CASE WHEN v.amountincc < 0 AND v.documenttypecode <> 'OB' THEN v.amountincc * - 1 ELSE 0 END), 0) creditamt, nvl(f.code, '')  documentyype, nvl(fdl.profitcentercode,'')profitcentercode, NVL((SELECT VALUESTRING FROM ADSTORAGE WHERE NAMEENTITYNAME='FINDocument' AND NAMENAME='Remarks2' AND FIELDNAME='Remarks2' AND UNIQUEID=FD.ABSUNIQUEID), '')narration, fdl.RECONCILIATIONDATE FROM findocument fd LEFT OUTER JOIN finchequeline ck ON fd.companycode = ck.finchequecompanycode AND fd.businessunitcode = ck.fdbusinessunitcode AND fd.financialyearcode = ck.fdfinancialyearcode AND fd.documenttemplatecode = ck.fddocumenttemplatecode AND fd.code = ck.fdcode, viewfindocument v, findocumentline fdl, glmaster gm, findocumenttemplate  f WHERE fd.companycode = fdl.findocumentcompanycode AND fd.businessunitcode = fdl.findocumentbusinessunitcode AND fd.financialyearcode = fdl.findocumentfinancialyearcode AND fd.documenttemplatecode = fdl.findocdocumenttemplatecode AND fd.code = fdl.findocumentcode AND fdl.glcode = gm.code AND fd.code = v.findocumentcode AND fd.businessunitcode = v.businessunitcode AND fd.financialyearcode = v.financialyearcode AND v.documenttemplatecode = fd.documenttemplatecode AND v.documenttypecode = f.documenttypecode AND v.documenttemplatecode = f.code AND v.documenttemplatecode <> 'OB' AND fd.documenttemplatecode NOT IN ('M01') AND fd.documenttypecode NOT IN ('AB') AND fd.reffindoccode IS NULL AND fdl.glcode =?1 AND fd.postingdate <= ?2 GROUP BY  fd.companycode, fd.businessunitcode, fd.code, fdl.glcode, nvl(gm.longdescription, ''), nvl(ck.chequeno, ''), fd.chequedate, ck.chequeclearingdate, nvl(ck.chequeamount, 0), nvl(f.code, ''), fdl.profitcentercode, fd.ABSUNIQUEID, fdl.RECONCILIATIONDATE ) ", nativeQuery = true)
    @Query(value = "SELECT ROW_NUMBER() OVER(ORDER BY FD.CODE) ROWCOUNT,"
        + " (SELECT MAX(DIVISIONCODE) FROM BUSINESSUNITVSCOMPANY WHERE BUSINESSUNITCOMPANYCODE=FD.BUSINESSUNITCOMPANYCODE AND BUSINESSUNITCODE=FD.BUSINESSUNITCODE) DIVISION,"
        + " FD.BUSINESSUNITCODE, FD.CODE,"
        + " (SELECT FDL9.GLCODE FROM FINDOCUMENTLINE FDL9 WHERE FDL9.FINDOCUMENTCOMPANYCODE=FD.COMPANYCODE AND FDL9.FINDOCUMENTBUSINESSUNITCODE=FD.BUSINESSUNITCODE AND FDL9.FINDOCUMENTFINANCIALYEARCODE=FD.FINANCIALYEARCODE AND FDL9.FINDOCUMENTCODE=FD.CODE ORDER BY AMOUNTINCC DESC FETCH FIRST ROW ONLY) GLCODE,"
        + " (SELECT GM9.LONGDESCRIPTION FROM FINDOCUMENTLINE FDL9, GLMASTER GM9 WHERE FDL9.FINDOCUMENTCOMPANYCODE=FD.COMPANYCODE AND FDL9.FINDOCUMENTBUSINESSUNITCODE=FD.BUSINESSUNITCODE AND FDL9.FINDOCUMENTFINANCIALYEARCODE=FD.FINANCIALYEARCODE AND FDL9.FINDOCUMENTCODE=FD.CODE AND FDL9.FINDOCUMENTCOMPANYCODE = GM9.COMPANYCODE AND FDL9.GLCODE = GM9.CODE ORDER BY AMOUNTINCC DESC FETCH FIRST ROW ONLY) GLNAME,"
        + " (SELECT TRIM(FDL9.SLCUSTOMERSUPPLIERCODE) FROM FINDOCUMENTLINE FDL9 WHERE FDL9.FINDOCUMENTCOMPANYCODE=FD.COMPANYCODE AND FDL9.FINDOCUMENTBUSINESSUNITCODE=FD.BUSINESSUNITCODE AND FDL9.FINDOCUMENTFINANCIALYEARCODE=FD.FINANCIALYEARCODE AND FDL9.FINDOCUMENTCODE=FD.CODE ORDER BY AMOUNTINCC DESC FETCH FIRST ROW ONLY) SUPPLIERCODE,"
        + " (SELECT V9.LEGALNAME1 FROM FINDOCUMENTLINE FDL9, VIEWORDERPARTNER V9 WHERE FDL9.FINDOCUMENTCOMPANYCODE=FD.COMPANYCODE AND FDL9.FINDOCUMENTBUSINESSUNITCODE=FD.BUSINESSUNITCODE AND FDL9.FINDOCUMENTFINANCIALYEARCODE=FD.FINANCIALYEARCODE AND FDL9.FINDOCUMENTCODE=FD.CODE AND FDL9.FINDOCUMENTCOMPANYCODE = V9.CUSTOMERSUPPLIERCOMPANYCODE AND FDL9.SLCUSTOMERSUPPLIERTYPE=V9.CUSTOMERSUPPLIERTYPE AND FDL9.SLCUSTOMERSUPPLIERCODE=V9.CUSTOMERSUPPLIERCODE  ORDER BY AMOUNTINCC DESC FETCH FIRST ROW ONLY) SUPPLINERNAME,"
        + " (SELECT COUNT(SLCUSTOMERSUPPLIERCODE) FROM FINDOCUMENTLINE FDL9 WHERE FDL9.FINDOCUMENTCOMPANYCODE=FD.COMPANYCODE AND FDL9.FINDOCUMENTBUSINESSUNITCODE=FD.BUSINESSUNITCODE AND FDL9.FINDOCUMENTFINANCIALYEARCODE=FD.FINANCIALYEARCODE AND FDL9.FINDOCUMENTCODE=FD.CODE  AND LENGTH(TRIM(SLCUSTOMERSUPPLIERCODE))>0) ICOUNT,"
        + " NVL(NVL(FD.CHEQUENUMBER, FD.PROPOSALREFNO), '') CHEQUENO, NVL(FD.CHEQUEDATE, FD.POSTINGDATE) CHEQUEDATE,"
        + " (CASE WHEN FDL.AMOUNTINCC>0 THEN FDL.AMOUNTINCC ELSE 0 END) DEBITAMOUNT,"
        + " (CASE WHEN FDL.AMOUNTINCC<0 THEN FDL.AMOUNTINCC * -1 ELSE 0 END) CREDITAMOUNT, FDL.RECONCILIATIONDATE, FDL.PROFITCENTERCODE,"
        + " NVL((SELECT VALUESTRING FROM ADSTORAGE WHERE NAMEENTITYNAME='FINDocument' AND NAMENAME='Remarks2' AND FIELDNAME='Remarks2'  AND UNIQUEID=FD.ABSUNIQUEID), '') NARRATION,"
        + " FDL.LINENUMBER, FD.FINANCIALYEARCODE, FD.DOCUMENTTEMPLATECODE, FD.POSTINGDATE"
        + " FROM FINDOCUMENT FD, FINDOCUMENTLINE FDL, GLMASTER GM"
        + " WHERE FD.COMPANYCODE = FDL.FINDOCUMENTCOMPANYCODE AND FD.BUSINESSUNITCODE = FDL.FINDOCUMENTBUSINESSUNITCODE AND"
        + " FD.FINANCIALYEARCODE = FDL.FINDOCUMENTFINANCIALYEARCODE AND FD.CODE = FDL.FINDOCUMENTCODE AND"
        + " FDL.FINDOCUMENTCOMPANYCODE = GM.COMPANYCODE AND FDL.GLCODE = GM.CODE AND FD.DOCUMENTTYPECODE NOT IN ('AB') AND"
        + " FD.REFFINDOCCODE IS NULL AND FDL.GLCODE =?1 AND FD.POSTINGDATE BETWEEN '2021-07-01' AND ?2"
        + " ORDER BY 4", nativeQuery = true)
    List<Object[]> findAllBankReconcilations4(String glcode, String ondate);

    @Query(value = "SELECT ROW_NUMBER() OVER(ORDER BY FD.CODE) ROWCOUNT,"
        + " (SELECT MAX(DIVISIONCODE) FROM BUSINESSUNITVSCOMPANY WHERE BUSINESSUNITCOMPANYCODE=FD.BUSINESSUNITCOMPANYCODE AND BUSINESSUNITCODE=FD.BUSINESSUNITCODE) DIVISION,"
        + " FD.BUSINESSUNITCODE, FD.CODE,"
        + " (SELECT FDL9.GLCODE FROM FINDOCUMENTLINE FDL9 WHERE FDL9.FINDOCUMENTCOMPANYCODE=FD.COMPANYCODE AND FDL9.FINDOCUMENTBUSINESSUNITCODE=FD.BUSINESSUNITCODE AND FDL9.FINDOCUMENTFINANCIALYEARCODE=FD.FINANCIALYEARCODE AND FDL9.FINDOCUMENTCODE=FD.CODE ORDER BY AMOUNTINCC DESC FETCH FIRST ROW ONLY) GLCODE,"
        + " (SELECT GM9.LONGDESCRIPTION FROM FINDOCUMENTLINE FDL9, GLMASTER GM9 WHERE FDL9.FINDOCUMENTCOMPANYCODE=FD.COMPANYCODE AND FDL9.FINDOCUMENTBUSINESSUNITCODE=FD.BUSINESSUNITCODE AND FDL9.FINDOCUMENTFINANCIALYEARCODE=FD.FINANCIALYEARCODE AND FDL9.FINDOCUMENTCODE=FD.CODE AND FDL9.FINDOCUMENTCOMPANYCODE = GM9.COMPANYCODE AND FDL9.GLCODE = GM9.CODE ORDER BY AMOUNTINCC DESC FETCH FIRST ROW ONLY) GLNAME,"
        + " (SELECT TRIM(FDL9.SLCUSTOMERSUPPLIERCODE) FROM FINDOCUMENTLINE FDL9 WHERE FDL9.FINDOCUMENTCOMPANYCODE=FD.COMPANYCODE AND FDL9.FINDOCUMENTBUSINESSUNITCODE=FD.BUSINESSUNITCODE AND FDL9.FINDOCUMENTFINANCIALYEARCODE=FD.FINANCIALYEARCODE AND FDL9.FINDOCUMENTCODE=FD.CODE ORDER BY AMOUNTINCC DESC FETCH FIRST ROW ONLY) SUPPLIERCODE,"
        + " (SELECT V9.LEGALNAME1 FROM FINDOCUMENTLINE FDL9, VIEWORDERPARTNER V9 WHERE FDL9.FINDOCUMENTCOMPANYCODE=FD.COMPANYCODE AND FDL9.FINDOCUMENTBUSINESSUNITCODE=FD.BUSINESSUNITCODE AND FDL9.FINDOCUMENTFINANCIALYEARCODE=FD.FINANCIALYEARCODE AND FDL9.FINDOCUMENTCODE=FD.CODE AND FDL9.FINDOCUMENTCOMPANYCODE = V9.CUSTOMERSUPPLIERCOMPANYCODE AND FDL9.SLCUSTOMERSUPPLIERTYPE=V9.CUSTOMERSUPPLIERTYPE AND FDL9.SLCUSTOMERSUPPLIERCODE=V9.CUSTOMERSUPPLIERCODE  ORDER BY AMOUNTINCC DESC FETCH FIRST ROW ONLY) SUPPLINERNAME,"
        + " (SELECT COUNT(SLCUSTOMERSUPPLIERCODE) FROM FINDOCUMENTLINE FDL9 WHERE FDL9.FINDOCUMENTCOMPANYCODE=FD.COMPANYCODE AND FDL9.FINDOCUMENTBUSINESSUNITCODE=FD.BUSINESSUNITCODE AND FDL9.FINDOCUMENTFINANCIALYEARCODE=FD.FINANCIALYEARCODE AND FDL9.FINDOCUMENTCODE=FD.CODE  AND LENGTH(TRIM(SLCUSTOMERSUPPLIERCODE))>0) ICOUNT,"
        + " NVL(NVL(FD.CHEQUENUMBER, FD.PROPOSALREFNO), '') CHEQUENO, NVL(FD.CHEQUEDATE, FD.POSTINGDATE) CHEQUEDATE,"
        + " (CASE WHEN FDL.AMOUNTINCC>0 THEN FDL.AMOUNTINCC ELSE 0 END) DEBITAMOUNT,"
        + " (CASE WHEN FDL.AMOUNTINCC<0 THEN FDL.AMOUNTINCC * -1 ELSE 0 END) CREDITAMOUNT, FDL.RECONCILIATIONDATE, FDL.PROFITCENTERCODE,"
        + " NVL((SELECT VALUESTRING FROM ADSTORAGE WHERE NAMEENTITYNAME='FINDocument' AND NAMENAME='Remarks2' AND FIELDNAME='Remarks2'  AND UNIQUEID=FD.ABSUNIQUEID), '') NARRATION,"
        + " FDL.LINENUMBER, FD.FINANCIALYEARCODE, FD.DOCUMENTTEMPLATECODE, FD.POSTINGDATE"
        + " FROM FINDOCUMENT FD, FINDOCUMENTLINE FDL, GLMASTER GM"
        + " WHERE FD.COMPANYCODE = FDL.FINDOCUMENTCOMPANYCODE AND FD.BUSINESSUNITCODE = FDL.FINDOCUMENTBUSINESSUNITCODE AND"
        + " FD.FINANCIALYEARCODE = FDL.FINDOCUMENTFINANCIALYEARCODE AND FD.CODE = FDL.FINDOCUMENTCODE AND"
        + " FDL.FINDOCUMENTCOMPANYCODE = GM.COMPANYCODE AND FDL.GLCODE = GM.CODE AND FD.DOCUMENTTYPECODE NOT IN ('AB') AND"
        + " FD.REFFINDOCCODE IS NULL AND FDL.GLCODE =?1 AND FD.POSTINGDATE BETWEEN '2021-07-01' AND ?2"
        + " ORDER BY 5", nativeQuery = true)
    List<Object[]> findAllBankReconcilations5(String glcode, String ondate);

    @Query(value = "SELECT ROW_NUMBER() OVER(ORDER BY FD.CODE) ROWCOUNT,"
        + " (SELECT MAX(DIVISIONCODE) FROM BUSINESSUNITVSCOMPANY WHERE BUSINESSUNITCOMPANYCODE=FD.BUSINESSUNITCOMPANYCODE AND BUSINESSUNITCODE=FD.BUSINESSUNITCODE) DIVISION,"
        + " FD.BUSINESSUNITCODE, FD.CODE,"
        + " (SELECT FDL9.GLCODE FROM FINDOCUMENTLINE FDL9 WHERE FDL9.FINDOCUMENTCOMPANYCODE=FD.COMPANYCODE AND FDL9.FINDOCUMENTBUSINESSUNITCODE=FD.BUSINESSUNITCODE AND FDL9.FINDOCUMENTFINANCIALYEARCODE=FD.FINANCIALYEARCODE AND FDL9.FINDOCUMENTCODE=FD.CODE ORDER BY AMOUNTINCC DESC FETCH FIRST ROW ONLY) GLCODE,"
        + " (SELECT GM9.LONGDESCRIPTION FROM FINDOCUMENTLINE FDL9, GLMASTER GM9 WHERE FDL9.FINDOCUMENTCOMPANYCODE=FD.COMPANYCODE AND FDL9.FINDOCUMENTBUSINESSUNITCODE=FD.BUSINESSUNITCODE AND FDL9.FINDOCUMENTFINANCIALYEARCODE=FD.FINANCIALYEARCODE AND FDL9.FINDOCUMENTCODE=FD.CODE AND FDL9.FINDOCUMENTCOMPANYCODE = GM9.COMPANYCODE AND FDL9.GLCODE = GM9.CODE ORDER BY AMOUNTINCC DESC FETCH FIRST ROW ONLY) GLNAME,"
        + " (SELECT TRIM(FDL9.SLCUSTOMERSUPPLIERCODE) FROM FINDOCUMENTLINE FDL9 WHERE FDL9.FINDOCUMENTCOMPANYCODE=FD.COMPANYCODE AND FDL9.FINDOCUMENTBUSINESSUNITCODE=FD.BUSINESSUNITCODE AND FDL9.FINDOCUMENTFINANCIALYEARCODE=FD.FINANCIALYEARCODE AND FDL9.FINDOCUMENTCODE=FD.CODE ORDER BY AMOUNTINCC DESC FETCH FIRST ROW ONLY) SUPPLIERCODE,"
        + " (SELECT V9.LEGALNAME1 FROM FINDOCUMENTLINE FDL9, VIEWORDERPARTNER V9 WHERE FDL9.FINDOCUMENTCOMPANYCODE=FD.COMPANYCODE AND FDL9.FINDOCUMENTBUSINESSUNITCODE=FD.BUSINESSUNITCODE AND FDL9.FINDOCUMENTFINANCIALYEARCODE=FD.FINANCIALYEARCODE AND FDL9.FINDOCUMENTCODE=FD.CODE AND FDL9.FINDOCUMENTCOMPANYCODE = V9.CUSTOMERSUPPLIERCOMPANYCODE AND FDL9.SLCUSTOMERSUPPLIERTYPE=V9.CUSTOMERSUPPLIERTYPE AND FDL9.SLCUSTOMERSUPPLIERCODE=V9.CUSTOMERSUPPLIERCODE  ORDER BY AMOUNTINCC DESC FETCH FIRST ROW ONLY) SUPPLINERNAME,"
        + " (SELECT COUNT(SLCUSTOMERSUPPLIERCODE) FROM FINDOCUMENTLINE FDL9 WHERE FDL9.FINDOCUMENTCOMPANYCODE=FD.COMPANYCODE AND FDL9.FINDOCUMENTBUSINESSUNITCODE=FD.BUSINESSUNITCODE AND FDL9.FINDOCUMENTFINANCIALYEARCODE=FD.FINANCIALYEARCODE AND FDL9.FINDOCUMENTCODE=FD.CODE  AND LENGTH(TRIM(SLCUSTOMERSUPPLIERCODE))>0) ICOUNT,"
        + " NVL(NVL(FD.CHEQUENUMBER, FD.PROPOSALREFNO), '') CHEQUENO, NVL(FD.CHEQUEDATE, FD.POSTINGDATE) CHEQUEDATE,"
        + " (CASE WHEN FDL.AMOUNTINCC>0 THEN FDL.AMOUNTINCC ELSE 0 END) DEBITAMOUNT,"
        + " (CASE WHEN FDL.AMOUNTINCC<0 THEN FDL.AMOUNTINCC * -1 ELSE 0 END) CREDITAMOUNT, FDL.RECONCILIATIONDATE, FDL.PROFITCENTERCODE,"
        + " NVL((SELECT VALUESTRING FROM ADSTORAGE WHERE NAMEENTITYNAME='FINDocument' AND NAMENAME='Remarks2' AND FIELDNAME='Remarks2'  AND UNIQUEID=FD.ABSUNIQUEID), '') NARRATION,"
        + " FDL.LINENUMBER, FD.FINANCIALYEARCODE, FD.DOCUMENTTEMPLATECODE, FD.POSTINGDATE"
        + " FROM FINDOCUMENT FD, FINDOCUMENTLINE FDL, GLMASTER GM"
        + " WHERE FD.COMPANYCODE = FDL.FINDOCUMENTCOMPANYCODE AND FD.BUSINESSUNITCODE = FDL.FINDOCUMENTBUSINESSUNITCODE AND"
        + " FD.FINANCIALYEARCODE = FDL.FINDOCUMENTFINANCIALYEARCODE AND FD.CODE = FDL.FINDOCUMENTCODE AND"
        + " FDL.FINDOCUMENTCOMPANYCODE = GM.COMPANYCODE AND FDL.GLCODE = GM.CODE AND FD.DOCUMENTTYPECODE NOT IN ('AB') AND"
        + " FD.REFFINDOCCODE IS NULL AND FDL.GLCODE =?1 AND FD.POSTINGDATE BETWEEN '2021-07-01' AND ?2"
        + " ORDER BY 6", nativeQuery = true)
    List<Object[]> findAllBankReconcilations6(String glcode, String ondate);

    @Query(value = "SELECT ROW_NUMBER() OVER(ORDER BY FD.CODE) ROWCOUNT,"
        + " (SELECT MAX(DIVISIONCODE) FROM BUSINESSUNITVSCOMPANY WHERE BUSINESSUNITCOMPANYCODE=FD.BUSINESSUNITCOMPANYCODE AND BUSINESSUNITCODE=FD.BUSINESSUNITCODE) DIVISION,"
        + " FD.BUSINESSUNITCODE, FD.CODE,"
        + " (SELECT FDL9.GLCODE FROM FINDOCUMENTLINE FDL9 WHERE FDL9.FINDOCUMENTCOMPANYCODE=FD.COMPANYCODE AND FDL9.FINDOCUMENTBUSINESSUNITCODE=FD.BUSINESSUNITCODE AND FDL9.FINDOCUMENTFINANCIALYEARCODE=FD.FINANCIALYEARCODE AND FDL9.FINDOCUMENTCODE=FD.CODE ORDER BY AMOUNTINCC DESC FETCH FIRST ROW ONLY) GLCODE,"
        + " (SELECT GM9.LONGDESCRIPTION FROM FINDOCUMENTLINE FDL9, GLMASTER GM9 WHERE FDL9.FINDOCUMENTCOMPANYCODE=FD.COMPANYCODE AND FDL9.FINDOCUMENTBUSINESSUNITCODE=FD.BUSINESSUNITCODE AND FDL9.FINDOCUMENTFINANCIALYEARCODE=FD.FINANCIALYEARCODE AND FDL9.FINDOCUMENTCODE=FD.CODE AND FDL9.FINDOCUMENTCOMPANYCODE = GM9.COMPANYCODE AND FDL9.GLCODE = GM9.CODE ORDER BY AMOUNTINCC DESC FETCH FIRST ROW ONLY) GLNAME,"
        + " (SELECT TRIM(FDL9.SLCUSTOMERSUPPLIERCODE) FROM FINDOCUMENTLINE FDL9 WHERE FDL9.FINDOCUMENTCOMPANYCODE=FD.COMPANYCODE AND FDL9.FINDOCUMENTBUSINESSUNITCODE=FD.BUSINESSUNITCODE AND FDL9.FINDOCUMENTFINANCIALYEARCODE=FD.FINANCIALYEARCODE AND FDL9.FINDOCUMENTCODE=FD.CODE ORDER BY AMOUNTINCC DESC FETCH FIRST ROW ONLY) SUPPLIERCODE,"
        + " (SELECT V9.LEGALNAME1 FROM FINDOCUMENTLINE FDL9, VIEWORDERPARTNER V9 WHERE FDL9.FINDOCUMENTCOMPANYCODE=FD.COMPANYCODE AND FDL9.FINDOCUMENTBUSINESSUNITCODE=FD.BUSINESSUNITCODE AND FDL9.FINDOCUMENTFINANCIALYEARCODE=FD.FINANCIALYEARCODE AND FDL9.FINDOCUMENTCODE=FD.CODE AND FDL9.FINDOCUMENTCOMPANYCODE = V9.CUSTOMERSUPPLIERCOMPANYCODE AND FDL9.SLCUSTOMERSUPPLIERTYPE=V9.CUSTOMERSUPPLIERTYPE AND FDL9.SLCUSTOMERSUPPLIERCODE=V9.CUSTOMERSUPPLIERCODE  ORDER BY AMOUNTINCC DESC FETCH FIRST ROW ONLY) SUPPLINERNAME,"
        + " (SELECT COUNT(SLCUSTOMERSUPPLIERCODE) FROM FINDOCUMENTLINE FDL9 WHERE FDL9.FINDOCUMENTCOMPANYCODE=FD.COMPANYCODE AND FDL9.FINDOCUMENTBUSINESSUNITCODE=FD.BUSINESSUNITCODE AND FDL9.FINDOCUMENTFINANCIALYEARCODE=FD.FINANCIALYEARCODE AND FDL9.FINDOCUMENTCODE=FD.CODE  AND LENGTH(TRIM(SLCUSTOMERSUPPLIERCODE))>0) ICOUNT,"
        + " NVL(NVL(FD.CHEQUENUMBER, FD.PROPOSALREFNO), '') CHEQUENO, NVL(FD.CHEQUEDATE, FD.POSTINGDATE) CHEQUEDATE,"
        + " (CASE WHEN FDL.AMOUNTINCC>0 THEN FDL.AMOUNTINCC ELSE 0 END) DEBITAMOUNT,"
        + " (CASE WHEN FDL.AMOUNTINCC<0 THEN FDL.AMOUNTINCC * -1 ELSE 0 END) CREDITAMOUNT, FDL.RECONCILIATIONDATE, FDL.PROFITCENTERCODE,"
        + " NVL((SELECT VALUESTRING FROM ADSTORAGE WHERE NAMEENTITYNAME='FINDocument' AND NAMENAME='Remarks2' AND FIELDNAME='Remarks2'  AND UNIQUEID=FD.ABSUNIQUEID), '') NARRATION,"
        + " FDL.LINENUMBER, FD.FINANCIALYEARCODE, FD.DOCUMENTTEMPLATECODE, FD.POSTINGDATE"
        + " FROM FINDOCUMENT FD, FINDOCUMENTLINE FDL, GLMASTER GM"
        + " WHERE FD.COMPANYCODE = FDL.FINDOCUMENTCOMPANYCODE AND FD.BUSINESSUNITCODE = FDL.FINDOCUMENTBUSINESSUNITCODE AND"
        + " FD.FINANCIALYEARCODE = FDL.FINDOCUMENTFINANCIALYEARCODE AND FD.CODE = FDL.FINDOCUMENTCODE AND"
        + " FDL.FINDOCUMENTCOMPANYCODE = GM.COMPANYCODE AND FDL.GLCODE = GM.CODE AND FD.DOCUMENTTYPECODE NOT IN ('AB') AND"
        + " FD.REFFINDOCCODE IS NULL AND FDL.GLCODE =?1 AND FD.POSTINGDATE BETWEEN '2021-07-01' AND ?2"
        + " ORDER BY 7", nativeQuery = true)
    List<Object[]> findAllBankReconcilations7(String glcode, String ondate);

    @Query(value = "SELECT ROW_NUMBER() OVER(ORDER BY FD.CODE) ROWCOUNT,"
        + " (SELECT MAX(DIVISIONCODE) FROM BUSINESSUNITVSCOMPANY WHERE BUSINESSUNITCOMPANYCODE=FD.BUSINESSUNITCOMPANYCODE AND BUSINESSUNITCODE=FD.BUSINESSUNITCODE) DIVISION,"
        + " FD.BUSINESSUNITCODE, FD.CODE,"
        + " (SELECT FDL9.GLCODE FROM FINDOCUMENTLINE FDL9 WHERE FDL9.FINDOCUMENTCOMPANYCODE=FD.COMPANYCODE AND FDL9.FINDOCUMENTBUSINESSUNITCODE=FD.BUSINESSUNITCODE AND FDL9.FINDOCUMENTFINANCIALYEARCODE=FD.FINANCIALYEARCODE AND FDL9.FINDOCUMENTCODE=FD.CODE ORDER BY AMOUNTINCC DESC FETCH FIRST ROW ONLY) GLCODE,"
        + " (SELECT GM9.LONGDESCRIPTION FROM FINDOCUMENTLINE FDL9, GLMASTER GM9 WHERE FDL9.FINDOCUMENTCOMPANYCODE=FD.COMPANYCODE AND FDL9.FINDOCUMENTBUSINESSUNITCODE=FD.BUSINESSUNITCODE AND FDL9.FINDOCUMENTFINANCIALYEARCODE=FD.FINANCIALYEARCODE AND FDL9.FINDOCUMENTCODE=FD.CODE AND FDL9.FINDOCUMENTCOMPANYCODE = GM9.COMPANYCODE AND FDL9.GLCODE = GM9.CODE ORDER BY AMOUNTINCC DESC FETCH FIRST ROW ONLY) GLNAME,"
        + " (SELECT TRIM(FDL9.SLCUSTOMERSUPPLIERCODE) FROM FINDOCUMENTLINE FDL9 WHERE FDL9.FINDOCUMENTCOMPANYCODE=FD.COMPANYCODE AND FDL9.FINDOCUMENTBUSINESSUNITCODE=FD.BUSINESSUNITCODE AND FDL9.FINDOCUMENTFINANCIALYEARCODE=FD.FINANCIALYEARCODE AND FDL9.FINDOCUMENTCODE=FD.CODE ORDER BY AMOUNTINCC DESC FETCH FIRST ROW ONLY) SUPPLIERCODE,"
        + " (SELECT V9.LEGALNAME1 FROM FINDOCUMENTLINE FDL9, VIEWORDERPARTNER V9 WHERE FDL9.FINDOCUMENTCOMPANYCODE=FD.COMPANYCODE AND FDL9.FINDOCUMENTBUSINESSUNITCODE=FD.BUSINESSUNITCODE AND FDL9.FINDOCUMENTFINANCIALYEARCODE=FD.FINANCIALYEARCODE AND FDL9.FINDOCUMENTCODE=FD.CODE AND FDL9.FINDOCUMENTCOMPANYCODE = V9.CUSTOMERSUPPLIERCOMPANYCODE AND FDL9.SLCUSTOMERSUPPLIERTYPE=V9.CUSTOMERSUPPLIERTYPE AND FDL9.SLCUSTOMERSUPPLIERCODE=V9.CUSTOMERSUPPLIERCODE  ORDER BY AMOUNTINCC DESC FETCH FIRST ROW ONLY) SUPPLINERNAME,"
        + " (SELECT COUNT(SLCUSTOMERSUPPLIERCODE) FROM FINDOCUMENTLINE FDL9 WHERE FDL9.FINDOCUMENTCOMPANYCODE=FD.COMPANYCODE AND FDL9.FINDOCUMENTBUSINESSUNITCODE=FD.BUSINESSUNITCODE AND FDL9.FINDOCUMENTFINANCIALYEARCODE=FD.FINANCIALYEARCODE AND FDL9.FINDOCUMENTCODE=FD.CODE  AND LENGTH(TRIM(SLCUSTOMERSUPPLIERCODE))>0) ICOUNT,"
        + " NVL(NVL(FD.CHEQUENUMBER, FD.PROPOSALREFNO), '') CHEQUENO, NVL(FD.CHEQUEDATE, FD.POSTINGDATE) CHEQUEDATE,"
        + " (CASE WHEN FDL.AMOUNTINCC>0 THEN FDL.AMOUNTINCC ELSE 0 END) DEBITAMOUNT,"
        + " (CASE WHEN FDL.AMOUNTINCC<0 THEN FDL.AMOUNTINCC * -1 ELSE 0 END) CREDITAMOUNT, FDL.RECONCILIATIONDATE, FDL.PROFITCENTERCODE,"
        + " NVL((SELECT VALUESTRING FROM ADSTORAGE WHERE NAMEENTITYNAME='FINDocument' AND NAMENAME='Remarks2' AND FIELDNAME='Remarks2'  AND UNIQUEID=FD.ABSUNIQUEID), '') NARRATION,"
        + " FDL.LINENUMBER, FD.FINANCIALYEARCODE, FD.DOCUMENTTEMPLATECODE, FD.POSTINGDATE"
        + " FROM FINDOCUMENT FD, FINDOCUMENTLINE FDL, GLMASTER GM"
        + " WHERE FD.COMPANYCODE = FDL.FINDOCUMENTCOMPANYCODE AND FD.BUSINESSUNITCODE = FDL.FINDOCUMENTBUSINESSUNITCODE AND"
        + " FD.FINANCIALYEARCODE = FDL.FINDOCUMENTFINANCIALYEARCODE AND FD.CODE = FDL.FINDOCUMENTCODE AND"
        + " FDL.FINDOCUMENTCOMPANYCODE = GM.COMPANYCODE AND FDL.GLCODE = GM.CODE AND FD.DOCUMENTTYPECODE NOT IN ('AB') AND"
        + " FD.REFFINDOCCODE IS NULL AND FDL.GLCODE =?1 AND FD.POSTINGDATE BETWEEN '2021-07-01' AND ?2"
        + " ORDER BY 8", nativeQuery = true)
    List<Object[]> findAllBankReconcilations8(String glcode, String ondate);

    //@Query(value = "select ROW_NUMBER() OVER(ORDER BY CODE) rowcount, COMPANYCODE, BUSINESSUNITCODE, CODE, GLCODE, GLNAME, nvl(CHEQUENO,'')CHEQUENO, to_char(CHEQUEDATE,'yyyy-MM-dd')CHEQUEDATE, to_char(CHEQUECLEARINGDATE,'yyyy-MM-dd')CHEQUECLEARINGDATE, nvl(CHEQUEAMOUNT,0)CHEQUEAMOUNT, nvl(DEBITAMIT,0)DEBITAMIT, nvl(CREDITAMT,0)CREDITAMT, DOCUMENTYYPE, PROFITCENTERCODE, nvl(case when CHEQUECLEARINGDATE is null OR  CHEQUECLEARINGDATE > ?2 THEN DEBITAMIT ELSE 0 END,0)CHECKDEPOSIRNOTCLEAR, nvl(case when CHEQUECLEARINGDATE is null OR  CHEQUECLEARINGDATE > ?2 THEN CREDITAMT ELSE 0 END,0)CHECKISSUENOTCLEAR, narration, to_char(RECONCILIATIONDATE,'yyyy-MM-dd')RECONCILIATIONDATE  from ( SELECT fd.ABSUNIQUEID, fd.companycode, fd.businessunitcode, fd.code, fdl.glcode, nvl(gm.longdescription, '') glname, nvl(ck.chequeno, '') chequeno, fd.chequedate, ck.chequeclearingdate, nvl(ck.chequeamount, 0) chequeamount, nvl(SUM(CASE WHEN v.amountincc > 0 AND v.documenttypecode <> 'OB' THEN v.amountincc ELSE 0 END), 0) debitamit, nvl(SUM(CASE WHEN v.amountincc < 0 AND v.documenttypecode <> 'OB' THEN v.amountincc * - 1 ELSE 0 END), 0) creditamt, nvl(f.code, '')  documentyype, nvl(fdl.profitcentercode,'')profitcentercode, NVL((SELECT VALUESTRING FROM ADSTORAGE WHERE NAMEENTITYNAME='FINDocument' AND NAMENAME='Remarks2' AND FIELDNAME='Remarks2' AND UNIQUEID=FD.ABSUNIQUEID), '')narration, fdl.RECONCILIATIONDATE FROM findocument fd LEFT OUTER JOIN finchequeline ck ON fd.companycode = ck.finchequecompanycode AND fd.businessunitcode = ck.fdbusinessunitcode AND fd.financialyearcode = ck.fdfinancialyearcode AND fd.documenttemplatecode = ck.fddocumenttemplatecode AND fd.code = ck.fdcode, viewfindocument v, findocumentline fdl, glmaster gm, findocumenttemplate  f WHERE fd.companycode = fdl.findocumentcompanycode AND fd.businessunitcode = fdl.findocumentbusinessunitcode AND fd.financialyearcode = fdl.findocumentfinancialyearcode AND fd.documenttemplatecode = fdl.findocdocumenttemplatecode AND fd.code = fdl.findocumentcode AND fdl.glcode = gm.code AND fd.code = v.findocumentcode AND fd.businessunitcode = v.businessunitcode AND fd.financialyearcode = v.financialyearcode AND v.documenttemplatecode = fd.documenttemplatecode AND v.documenttypecode = f.documenttypecode AND v.documenttemplatecode = f.code AND v.documenttemplatecode <> 'OB' AND fd.documenttemplatecode NOT IN ('M01') AND fd.documenttypecode NOT IN ('AB') AND fd.reffindoccode IS NULL AND fdl.glcode =?1 AND fd.postingdate <= ?2  AND fd.businessunitcode LIKE ?3 AND fd.code LIKE ?4 AND fdl.glcode LIKE ?5 AND gm.longdescription  LIKE ?6 AND ck.chequeno LIKE ?7 AND fd.chequedate LIKE ?8 AND fdl.profitcentercode LIKE ?9  GROUP BY  fd.companycode, fd.businessunitcode, fd.code, fdl.glcode, nvl(gm.longdescription, ''), nvl(ck.chequeno, ''), fd.chequedate, ck.chequeclearingdate, nvl(ck.chequeamount, 0), nvl(f.code, ''), fdl.profitcentercode, fd.ABSUNIQUEID, fdl.RECONCILIATIONDATE ) ", nativeQuery = true)
    @Query(value = "select ROW_NUMBER() OVER(ORDER BY CODE) rowcount, COMPANYCODE,FINANCIALYEARCODE,DOCUMENTTEMPLATECODE, BUSINESSUNITCODE, CODE,  (select fdl9.glcode from findocumentline fdl9 where fdl9.FINDOCUMENTCOMPANYCODE=SOA.COMPANYCODE and fdl9.FINDOCUMENTBUSINESSUNITCODE=SOA.BUSINESSUNITCODE and fdl9.FINDOCUMENTFINANCIALYEARCODE=SOA.FINANCIALYEARCODE and fdl9.FINDOCDOCUMENTTEMPLATECODE=SOA.DOCUMENTTEMPLATECODE   and fdl9.FINDOCUMENTCODE=SOA.CODE ORDER BY AMOUNTINCC DESC FETCH FIRST ROW ONLY)GLCODE, (select gm9.LONGDESCRIPTION from findocumentline fdl9, GLMASTER GM9 where fdl9.FINDOCUMENTCOMPANYCODE=SOA.COMPANYCODE and fdl9.FINDOCUMENTBUSINESSUNITCODE=SOA.BUSINESSUNITCODE and fdl9.FINDOCUMENTFINANCIALYEARCODE=SOA.FINANCIALYEARCODE and fdl9.FINDOCDOCUMENTTEMPLATECODE=SOA.DOCUMENTTEMPLATECODE   and fdl9.FINDOCUMENTCODE=SOA.CODE AND FDL9.GLCODE = GM9.CODE ORDER BY AMOUNTINCC DESC FETCH FIRST ROW ONLY)GLNAME, (select trim(fdl9.SLCUSTOMERSUPPLIERCODE) from findocumentline fdl9 where fdl9.FINDOCUMENTCOMPANYCODE=SOA.COMPANYCODE and fdl9.FINDOCUMENTBUSINESSUNITCODE=SOA.BUSINESSUNITCODE and fdl9.FINDOCUMENTFINANCIALYEARCODE=SOA.FINANCIALYEARCODE and fdl9.FINDOCDOCUMENTTEMPLATECODE=SOA.DOCUMENTTEMPLATECODE   and fdl9.FINDOCUMENTCODE=SOA.CODE ORDER BY AMOUNTINCC DESC FETCH FIRST ROW ONLY)SUPPLIERCODE, (select v9.LEGALNAME1 from findocumentline fdl9, vieworderpartner v9 where fdl9.FINDOCUMENTCOMPANYCODE=SOA.COMPANYCODE and fdl9.FINDOCUMENTBUSINESSUNITCODE=SOA.BUSINESSUNITCODE and fdl9.FINDOCUMENTFINANCIALYEARCODE=SOA.FINANCIALYEARCODE and fdl9.FINDOCDOCUMENTTEMPLATECODE=SOA.DOCUMENTTEMPLATECODE   and fdl9.FINDOCUMENTCODE=SOA.CODE and fdl9.SLCUSTOMERSUPPLIERTYPE=v9.CUSTOMERSUPPLIERTYPE and fdl9.SLCUSTOMERSUPPLIERCODE=v9.CUSTOMERSUPPLIERCODE  ORDER BY AMOUNTINCC DESC FETCH FIRST ROW ONLY)SUPPLINERNAME, (SELECT COUNT(SLCUSTOMERSUPPLIERCODE) FROM findocumentline fdl9 where fdl9.FINDOCUMENTCOMPANYCODE=SOA.COMPANYCODE and fdl9.FINDOCUMENTBUSINESSUNITCODE=SOA.BUSINESSUNITCODE and fdl9.FINDOCUMENTFINANCIALYEARCODE=SOA.FINANCIALYEARCODE and fdl9.FINDOCDOCUMENTTEMPLATECODE=SOA.DOCUMENTTEMPLATECODE   and fdl9.FINDOCUMENTCODE=SOA.CODE  AND LENGTH(TRIM(SLCUSTOMERSUPPLIERCODE))>0)ICOUNT, nvl(CHEQUENO,'')CHEQUENO, to_char(CHEQUEDATE,'yyyy-MM-dd')CHEQUEDATE,  to_char(CHEQUECLEARINGDATE,'yyyy-MM-dd')CHEQUECLEARINGDATE, nvl(CHEQUEAMOUNT,0)CHEQUEAMOUNT, nvl(DEBITAMIT,0)DEBITAMIT, nvl(CREDITAMT,0)CREDITAMT, DOCUMENTYYPE, PROFITCENTERCODE,  nvl(case when CHEQUECLEARINGDATE is null OR  CHEQUECLEARINGDATE > ?2 THEN DEBITAMIT ELSE 0 END,0)CHECKDEPOSIRNOTCLEAR,  nvl(case when CHEQUECLEARINGDATE is null OR  CHEQUECLEARINGDATE > ?2 THEN CREDITAMT ELSE 0 END,0)CHECKISSUENOTCLEAR, narration,  to_char(RECONCILIATIONDATE,'yyyy-MM-dd')RECONCILIATIONDATE  from ( SELECT fd.ABSUNIQUEID, fd.companycode, fd.FINANCIALYEARCODE, fd.DOCUMENTTEMPLATECODE, fd.businessunitcode, fd.code,  fdl.glcode, nvl(gm.longdescription, '') glname, nvl(ck.chequeno, '') chequeno, fd.chequedate, ck.chequeclearingdate, nvl(ck.chequeamount, 0) chequeamount,  nvl(SUM(CASE WHEN v.amountincc > 0 AND v.documenttypecode <> 'OB' THEN v.amountincc ELSE 0 END), 0) debitamit,  nvl(SUM(CASE WHEN v.amountincc < 0 AND v.documenttypecode <> 'OB' THEN v.amountincc * - 1 ELSE 0 END), 0) creditamt, nvl(f.code, '')  documentyype,  nvl(fdl.profitcentercode,'')profitcentercode, NVL((SELECT VALUESTRING FROM ADSTORAGE WHERE NAMEENTITYNAME='FINDocument' AND NAMENAME='Remarks2' AND FIELDNAME='Remarks2'  AND UNIQUEID=FD.ABSUNIQUEID), '')narration, fdl.RECONCILIATIONDATE FROM findocument fd LEFT OUTER JOIN finchequeline ck ON fd.companycode = ck.finchequecompanycode  AND fd.businessunitcode = ck.fdbusinessunitcode AND fd.financialyearcode = ck.fdfinancialyearcode AND fd.documenttemplatecode = ck.fddocumenttemplatecode  AND fd.code = ck.fdcode, viewfindocument v, findocumentline fdl, glmaster gm, findocumenttemplate  f WHERE fd.companycode = fdl.findocumentcompanycode  AND fd.businessunitcode = fdl.findocumentbusinessunitcode AND fd.financialyearcode = fdl.findocumentfinancialyearcode AND fd.documenttemplatecode = fdl.findocdocumenttemplatecode  AND fd.code = fdl.findocumentcode AND fdl.glcode = gm.code AND fd.code = v.findocumentcode AND fd.businessunitcode = v.businessunitcode  AND fd.financialyearcode = v.financialyearcode AND v.documenttemplatecode = fd.documenttemplatecode AND v.documenttypecode = f.documenttypecode AND v.documenttemplatecode = f.code  AND v.documenttemplatecode <> 'OB' AND fd.documenttemplatecode NOT IN ('M01') AND fd.documenttypecode NOT IN ('AB') AND fd.reffindoccode IS NULL  AND fdl.glcode =?1 AND fd.postingdate <= ?2 AND fd.businessunitcode LIKE ?3 AND fd.code LIKE ?4 AND fdl.glcode LIKE ?5 AND gm.longdescription  LIKE ?6 AND ck.chequeno LIKE ?7 AND fd.chequedate LIKE ?8 AND fdl.profitcentercode LIKE ?9 GROUP BY  fd.companycode,fd.FINANCIALYEARCODE, fd.DOCUMENTTEMPLATECODE, fd.businessunitcode, fd.code, fdl.glcode, nvl(gm.longdescription, ''), nvl(ck.chequeno, ''), fd.chequedate, ck.chequeclearingdate,  nvl(ck.chequeamount, 0), nvl(f.code, ''), fdl.profitcentercode, fd.ABSUNIQUEID, fdl.RECONCILIATIONDATE ) soa ", nativeQuery = true)
    List<Object[]> findAllBankReconcilationsearch(String bankcode, String fromDate, String unit, String findocument, String glcode, String glname, String chekno, String chkdate, String profitcenter);
}

