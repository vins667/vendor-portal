package io.vamani.application.vendorportal.repository;

import io.vamani.application.vendorportal.domain.VendorDocumentTrancation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * Spring Data  repository for the VendorDocumentTrancation entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("vendorportalTransactionManager")
@PersistenceContext(name = "vendorportalEntityManagerFactory")
public interface VendorDocumentTrancationRepository extends JpaRepository<VendorDocumentTrancation, Long> {
    @Query("select vendorDocumentTrancation from VendorDocumentTrancation vendorDocumentTrancation where vendorDocumentTrancation.vendorId =?1 and vendorDocumentTrancation.documentId = ?2 and vendorDocumentTrancation.approvedDate is null")
    VendorDocumentTrancation findByVendorIdAndDocumentId(Long vendorId, Long documentId);

    @Query("select vendorDocumentTrancation from VendorDocumentTrancation vendorDocumentTrancation where vendorDocumentTrancation.vendorId =?1 and vendorDocumentTrancation.approvedDate is null")
    List<VendorDocumentTrancation> findByVendorId(Long vendorId);
}
