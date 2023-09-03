package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Purchaseorder;
import io.vamani.application.db2.domain.PurchaseorderId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;

@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface PurchaseorderRepository extends JpaRepository<Purchaseorder, PurchaseorderId> {
    @Query("select purchaseorder from Purchaseorder purchaseorder, Volpoapprovalhistory volpoapprovalhistory "
        + " where purchaseorder.id.companycode = volpoapprovalhistory.id.companycode and purchaseorder.id.code = volpoapprovalhistory.id.code and "
        + " volpoapprovalhistory.approverstatus = 'A' and purchaseorder.ordprncustomersuppliercode = ?1 and upper(purchaseorder.id.code) like ?2")
    Page<Purchaseorder> findAllByPurchaseorderIgnoreCaseLike(String ordprncustomersuppliercode, String purchaseordercode, Pageable pageable);
}
