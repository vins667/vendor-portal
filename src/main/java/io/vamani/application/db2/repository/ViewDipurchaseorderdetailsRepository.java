package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.ViewDipurchaseorderdetails;
import io.vamani.application.db2.domain.ViewDipurchaseorderdetailsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;

@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface ViewDipurchaseorderdetailsRepository extends JpaRepository<ViewDipurchaseorderdetails, ViewDipurchaseorderdetailsId> {
    @Query("select viewdipurchaseorderdetails from ViewDipurchaseorderdetails viewdipurchaseorderdetails where viewdipurchaseorderdetails.id.companycode = '100' and viewdipurchaseorderdetails.id.code = ?1")
    ViewDipurchaseorderdetails findByCode(String purchaseordercode);
}
