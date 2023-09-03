package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Purchaseinvoice;
import io.vamani.application.db2.domain.Purchaseorderline;
import io.vamani.application.db2.domain.PurchaseorderlineId;
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
public interface PurchaseorderlineRepository extends JpaRepository<Purchaseorderline, PurchaseorderlineId> {
    @Query("select purchaseorder.id.companycode, purchaseorder.id.countercode, purchaseorder.id.code, purchaseorder.orderdate, purchaseorderline.projectcode, purchaseorderline.itemtypeaficode, "
        + " purchaseorderline.subcode01, purchaseorderline.subcode02, purchaseorderline.subcode03, purchaseorderline.subcode04, purchaseorderline.subcode05, purchaseorderline.subcode06, purchaseorderline.subcode07, purchaseorderline.subcode08, purchaseorderline.subcode09, purchaseorderline.subcode10,"
        + " fullitemkeydecoder.summarizeddescription, purchaseorderline.userprimaryuomcode, purchaseorderline.userprimaryquantity, purchaseorderline.price, purchaseorderline.userprimaryquantity * purchaseorderline.price as value"
        + " from Purchaseorder purchaseorder inner join Purchaseorderline purchaseorderline on"
        + " purchaseorder.id.companycode = purchaseorderline.id.purchaseordercompanycode and purchaseorder.id.countercode = purchaseorderline.id.purchaseordercountercode and purchaseorder.id.code = purchaseorderline.id.purchaseordercode"
        + " inner join Fullitemkeydecoder fullitemkeydecoder on fullitemkeydecoder.identifier = purchaseorderline.fullitemidentifier"
        + " where purchaseorder.ordprncustomersuppliercode = ?1 and purchaseorder.id.code like ?2")
    Page<Object[]> findAllBySupplierCodeAndPOLike(String suppliercode, String code, Pageable pageable);
}
