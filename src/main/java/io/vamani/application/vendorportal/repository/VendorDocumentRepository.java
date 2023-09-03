package io.vamani.application.vendorportal.repository;

import io.vamani.application.vendorportal.domain.VendorDocument;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;


/**
 * Spring Data  repository for the VendorDocument entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("vendorportalTransactionManager")
@PersistenceContext(name = "vendorportalEntityManagerFactory")
public interface VendorDocumentRepository extends JpaRepository<VendorDocument, Long> {
	@Query("select vendorDocument from VendorDocument vendorDocument where vendorDocument.vendorId =?1")
    List<VendorDocument> findByVendorId(Long vendorId);


    @Query("select vendorDocument from VendorDocument vendorDocument where vendorDocument.vendorId =?1 and vendorDocument.documentId = ?2")
    VendorDocument findByVendorIdAndDocumentId(Long vendorId, Long documentId);
}
