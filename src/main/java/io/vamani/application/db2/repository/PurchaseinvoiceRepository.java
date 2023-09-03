package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Purchaseinvoice;
import io.vamani.application.db2.domain.PurchaseinvoiceId;
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
public interface PurchaseinvoiceRepository extends JpaRepository<Purchaseinvoice, PurchaseinvoiceId> {
    @Query("select purchaseinvoice from Purchaseinvoice purchaseinvoice where purchaseinvoice.id.ordprncustomersuppliertype = '2' and purchaseinvoice.id.ordprncustomersuppliercode = ?1 and purchaseinvoice.id.code like ?2")
    Page<Purchaseinvoice> findAllByPurchaseinvoiceAllIgnoreCaseLike(String suppliercode, String code, Pageable pageable);
}
