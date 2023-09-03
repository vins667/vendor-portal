package io.vamani.application.vendorportal.repository;

import io.vamani.application.vendorportal.domain.VendorBranchDetailsTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * Spring Data  repository for the VendorBranchDetailsTransaction entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("vendorportalTransactionManager")
@PersistenceContext(name = "vendorportalEntityManagerFactory")
public interface VendorBranchDetailsTransactionRepository extends JpaRepository<VendorBranchDetailsTransaction, Long> {
    @Query("select vendorBranchDetailsTransaction from VendorBranchDetailsTransaction vendorBranchDetailsTransaction where vendorBranchDetailsTransaction.branchId = ?1 and vendorBranchDetailsTransaction.approvedDate is null")
    VendorBranchDetailsTransaction findByBranchId(Long branchId);

    @Query("select vendorBranchDetailsTransaction from VendorBranchDetailsTransaction vendorBranchDetailsTransaction where vendorBranchDetailsTransaction.vendorId = ?1 and vendorBranchDetailsTransaction.approvedDate is null")
    List<VendorBranchDetailsTransaction> findByVendorId(Long vendorId);

    @Query("select count(vendorBranchDetailsTransaction) from VendorBranchDetailsTransaction vendorBranchDetailsTransaction where vendorBranchDetailsTransaction.vendorId=?1 and vendorBranchDetailsTransaction.approvedDate is null")
    Long countAllByVendorId(Long vendorId);
}
