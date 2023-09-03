package io.vamani.application.repository;

import io.vamani.application.domain.GstGovtUpload;
import io.vamani.application.domain.GstVoplUpload;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for the GstGovtUpload entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GstGovtUploadRepository extends JpaRepository<GstGovtUpload, Long> {
	
	@Query("select gstGovtUpload from GstGovtUpload gstGovtUpload where gstGovtUpload.gStatus='0' ")
	List<GstGovtUpload> getAllGovtListByParm();
	
	@Query("select gstGovtUpload from GstGovtUpload gstGovtUpload where gstGovtUpload.gGstin=?1 and  gstGovtUpload.gInvno=?2 and gstGovtUpload.gCgst=?3 and gstGovtUpload.gSgst=?4 and gstGovtUpload.gIgst=?5 and gstGovtUpload.gGstin like ?6 and gstGovtUpload.gInvno like ?7 and gstGovtUpload.gSupplier like ?8 ")
	GstGovtUpload getAllGovtListByStatus(String gstin, String invNumber, Double cgstAmount, Double sgstAmount,
			Double igstAmount, String voplGstin, String voplInvoice, String voplSupplierName);

	@Query("select gstGovtUpload from GstGovtUpload gstGovtUpload where gstGovtUpload.gGstin=?1 and  gstGovtUpload.gInvno=?2 and (?3 - (gstGovtUpload.gCgst+ gstGovtUpload.gSgst+ gstGovtUpload.gIgst) <=?4 and  ?3 - (gstGovtUpload.gCgst+ gstGovtUpload.gSgst+ gstGovtUpload.gIgst) >= ?5) and gstGovtUpload.gGstin like ?6 and gstGovtUpload.gInvno like ?7 and gstGovtUpload.gSupplier like ?8 ")
	GstGovtUpload getAllGovtListByStatus2(String gstin, String invNumber, Double vpolTax, Double totalTaxPlus,
			Double totalTaxMinus, String voplGstin, String voplInvoice, String voplSupplierName);

	@Query("select gstGovtUpload from GstGovtUpload gstGovtUpload where gstGovtUpload.gGstin=?1 and  gstGovtUpload.gInvno=?2 and gstGovtUpload.gCgst=?3 and gstGovtUpload.gSgst=?4 and gstGovtUpload.gIgst=?5 and gstGovtUpload.gInvamt like ?6 and gstGovtUpload.gSupplier like ?7 ")
	GstGovtUpload getAllGovtList(String gstin, String invoiceNo, Double cgstAmount, Double sgstAmount,
			Double igstAmount, Double invoiceAmount, String supplierName);

}
