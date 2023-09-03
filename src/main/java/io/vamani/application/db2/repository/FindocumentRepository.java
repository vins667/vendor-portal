package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Findocument;
import io.vamani.application.db2.domain.FindocumentId;
import io.vamani.application.db2.domain.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface FindocumentRepository extends JpaRepository<Findocument, FindocumentId> {

    @Query("select findocument from Findocument findocument where findocument.referencetext5 = ?1")
    List<Findocument> findByRemark(String remark);

    @Query("select findocument from Findocument findocument where findocument.id.documenttemplatecode='VD2' and upper(findocument.id.code) like ?1")
    Page<Findocument> findAllByFindocumentIgnoreCaseLike(String documentcode, Pageable pageable);

    @Query("select findocument from Findocument findocument where findocument.id.code like ?1")
    Page<Findocument> findAllByFindocumentAllIgnoreCaseLike(String documentcode, Pageable pageable);

    @Query("select findocument from Findocument findocument where findocument.id.financialyearcode>2122 and findocument.id.code like ?1")
    Page<Findocument> findAllByFindocumentGLChangeIgnoreCaseLike(String documentcode, Pageable pageable);

    @Query("select findocument from Findocument findocument where upper(findocument.id.code)=?1")
    Optional<Findocument> findByCode(String documentcode);

    @Query("select findocument.id.code from Findocument findocument where upper(findocument.id.code)=?1")
    String findByDocumentCode(String documentcode);

    @Query("select findocument from Findocument findocument where upper(findocument.id.code)=?1")
    List<Findocument> findAllByDocumentCode(String documentcode);

    @Query("select findocument from Findocument findocument where findocument.id.companycode = ?1 and findocument.id.businessunitcode = ?2 and findocument.id.financialyearcode = ?3 and findocument.id.code = ?4")
    Findocument findByCode(String companycode, String businessunitcode, Integer financialyearcode, String documentcode);

    @Modifying
    @Transactional
    @Query("update Findocument findocument set findocument.creationuser=?1 where findocument.referencetext5 = ?2")
    void updateCreationUser(String creationUser, String referencetext5);

    @Modifying
    @Transactional
    @Query(value = "update Findocumentline findocumentline set findocumentline.reconciliationdate=?1, findocumentline.reconciledby = ?2, findocumentline.reconciledon = ?3 where findocumentline.findocumentcompanycode = ?4 and findocumentline.findocumentbusinessunitcode = ?5 and findocumentline.findocumentfinancialyearcode = ?6 and findocumentline.findocumentcode = ?7 and findocumentline.linenumber = ?8", nativeQuery = true)
    void updateReconcile(String reconcileDate, String reconcileBy, String reconsileDon, String companycode, String businessunitcode, String finyearcode, String findoccode, Integer linenumber);

    @Modifying
    @Transactional
    @Query(value = "update Findocumentline findocumentline set findocumentline.reconciliationdate=?1, findocumentline.reconciledby = ?2, findocumentline.reconciledon = ?3 where findocumentline.findocumentcompanycode = ?4 and findocumentline.findocumentbusinessunitcode = ?5 and findocumentline.findocumentfinancialyearcode = ?6 and findocumentline.findocumentcode = ?7 and findocumentline.glcode = ?8", nativeQuery = true)
    void updateReconcileByGLCode(String reconcileDate, String reconcileBy, String reconsileDon, String companycode, String businessunitcode, String finyearcode, String findoccode, String glCode);

    @Modifying
    @Transactional
    @Query(value = "update FINDOCUMENTLINE findocumentline set findocumentline.GLCODE=?1 where findocumentline.FINDOCUMENTCOMPANYCODE = ?2 and findocumentline.FINDOCUMENTBUSINESSUNITCODE = ?3 and findocumentline.FINDOCUMENTFINANCIALYEARCODE = ?4 and findocumentline.FINDOCUMENTCODE = ?5 and findocumentline.LINENUMBER = ?6", nativeQuery = true)
    void updateGL(String glcode, String companycode, String businessunitcode, Integer financialyearcode, String code, Integer linenumber);

    @Modifying
    @Transactional
    @Query(value = "update FINOPENDOCUMENTS finopendocument set finopendocument.GLCODE=?1 where finopendocument.COMPANYCODE = ?2 and finopendocument.BUSINESSUNITCODE = ?3 and finopendocument.FINANCIALYEARCODE = ?4 and finopendocument.CODE = ?5 and finopendocument.LINENUMBER = ?6", nativeQuery = true)
    void updateOpenDocumentGL(String glcode, String companycode, String businessunitcode, Integer financialyearcode, String code, Integer linenumber);

    @Query(value = "SELECT NVL(SUM(FDL.AMOUNTINCC),0) TOTALOUTSTANDINGAMOUNT"
        + " FROM FINDOCUMENT FD, FINDOCUMENTLINE FDL"
        + " WHERE FD.COMPANYCODE = FDL.FINDOCUMENTCOMPANYCODE AND FD.BUSINESSUNITCODE = FDL.FINDOCUMENTBUSINESSUNITCODE AND FD.FINANCIALYEARCODE = FDL.FINDOCUMENTFINANCIALYEARCODE AND FD.CODE = FDL.FINDOCUMENTCODE AND"
        + " FD.COMPANYCODE='100' AND FD.POSTINGDATE BETWEEN ? AND ? AND FD.DOCUMENTTYPECODE NOT IN ('AB') AND FD.REFFINDOCCODE IS NULL AND FDL.SLCUSTOMERSUPPLIERCODE=?", nativeQuery = true)
    BigDecimal fetchOutstandingAmount(String dateFrom, String dateTo, String supplierCode);

    @Query(value = "select BUSINESSUNITCODE, FINANCIALYEARCODE, CODE from findocument where NVL(PROPOSALREFNO, CHEQUENUMBER) = ?1 FETCH FIRST ROW ONLY", nativeQuery = true)
    List<Object[]> findByChequeNumber(String chequeNumber);
}
