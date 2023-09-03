package io.vamani.application.repository;

import io.vamani.application.domain.GstReconciliation;
import io.vamani.application.model.GstReconciliationBean;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for the GstReconciliation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GstReconciliationRepository extends JpaRepository<GstReconciliation, Long> {

	//@Query("select 'VOPL' as invoiceType, gstVoplUpload.vGstin as gstin, gstVoplUpload.vInvoiceno as invoiceNo,gstVoplUpload.vSupplierName as supplierName,gstVoplUpload.vInvamt as invoiceAmount, gstVoplUpload.vCgst as cgstAmount,gstVoplUpload.vSgst as sgstAmount,gstVoplUpload.vIgst as igstAmount from GstVoplUpload gstVoplUpload where gstVoplUpload.status='0'  and (gstVoplUpload.vGstin,gstVoplUpload.vInvoiceno) not in (select gstGovtUpload.gGstin, gstGovtUpload.gInvno from GstGovtUpload gstGovtUpload where gstGovtUpload.gStatus='0') union select 'GOVT' as invoiceType, gstGovtUpload.gGstin as gstin, gstGovtUpload.gInvno as invoiceNo,    gstGovtUpload.gSupplier as supplierName ,   gstGovtUpload.gInvamt as invoiceAmount, gstGovtUpload.gCgst as cgstAmount,gstGovtUpload.gSgst as sgstAmount,gstGovtUpload.gIgst as igstAmount from GstGovtUpload gstGovtUpload where gstGovtUpload.gStatus='0' and (gstGovtUpload.gGstin,gstGovtUpload.gInvno) not in (select gstVoplUpload.vGstin,gstVoplUpload.vInvoiceno from GstVoplUpload gstVoplUpload where gstVoplUpload.Status='0') ")
	@Query(value = "SELECT 'VOPL' AS invoiceType, v_gstin gstin,     v_supplier_name suppliername, v_invoiceno invoiceno, v_invoicedate  invoicedate, v_invamt invoiceamount, v_cgst   cgstamount, v_sgst sgstamount, v_igst igstamount FROM gst_vopl_upload WHERE status = '0' AND ( v_gstin, v_invoiceno ) NOT IN ( SELECT g_gstin, g_invno FROM gst_govt_upload WHERE g_status = '0' ) and v_gstin  like ?1 and v_invoiceno  like ?2 and v_supplier_name  like ?3 UNION "
			+ "     SELECT 'GOVT' AS invoiceType, g_gstin AS  gstin, g_supplier AS  suppliername,  g_invno AS  invoiceno, g_invdate  AS  invoicedate, g_invamt AS  invoiceamount, g_cgst AS   cgstamount, g_sgst AS  sgstamount, g_igst AS  igstamoun FROM gst_govt_upload WHERE g_status = '0' AND ( g_gstin,g_invno ) NOT IN (SELECT v_gstin, v_invoiceno FROM gst_vopl_upload WHERE status = '0') and g_gstin like ?1 and g_invno like ?2 and g_supplier like ?3  order by 2, 4 ", nativeQuery = true)
	List<Object[]> getMiscList(String Gstin, String invoice, String supplier);
}
