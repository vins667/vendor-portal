package io.vamani.application.vendorportal.repository;

import io.vamani.application.vendorportal.domain.VendorUsersTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * Spring Data  repository for the VendorUsersTransaction entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("vendorportalTransactionManager")
@PersistenceContext(name = "vendorportalEntityManagerFactory")
public interface VendorUsersTransactionRepository extends JpaRepository<VendorUsersTransaction, Long> {

    @Query("select vendorUsersTransaction from VendorUsersTransaction vendorUsersTransaction where vendorUsersTransaction.vendorUsersId = ?1 and vendorUsersTransaction.approvedDate is null")
    VendorUsersTransaction findByVendorUserId(Long vendorUserId);

    @Query("select vendorUsersTransaction from VendorUsersTransaction vendorUsersTransaction where vendorUsersTransaction.vendorId=?1 and vendorUsersTransaction.approvedDate is  null")
    List<VendorUsersTransaction> findAllByVendorId(Long vendorId);

}
