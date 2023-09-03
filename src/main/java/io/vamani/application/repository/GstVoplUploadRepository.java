package io.vamani.application.repository;

import io.vamani.application.domain.GstVoplUpload;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for the GstVoplUpload entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GstVoplUploadRepository extends JpaRepository<GstVoplUpload, Long> {

	@Query("select gstVoplUpload from GstVoplUpload gstVoplUpload where gstVoplUpload.status='0' ")
	List<GstVoplUpload> getAllVoplListByStatus();

	@Query("select gstVoplUpload from GstVoplUpload gstVoplUpload where gstVoplUpload.vInvoiceno=?1 and gstVoplUpload.vGstin=?2 and gstVoplUpload.vSupplierName=?3 and gstVoplUpload.vInvamt=?4 and gstVoplUpload.vCgst=?5 and gstVoplUpload.vSgst=?6 and gstVoplUpload.vIgst=?7")
	GstVoplUpload getVoplrow(String invoiceNo, String gstin, String supplierName, Double invoiceAmount,
			Double cgstAmount, Double sgstAmount, Double igstAmount);

}
