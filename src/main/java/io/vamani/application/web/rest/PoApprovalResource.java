package io.vamani.application.web.rest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.activation.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.db2.jcc.am.ResultSet;

import ch.qos.logback.classic.Logger;
import io.vamani.application.model.poApprovalHeaderBean;
import io.vamani.application.repository.poApprovalRepository;
import liquibase.logging.LoggerFactory;

public class PoApprovalResource implements poApprovalRepository {

	@Autowired
	private DataSource dataSource;
	//private final Logger log = LoggerFactory.getLogger(poApprovalResource.class);

	/*public List<poApprovalHeaderBean> getAllPoList(String[] potype, String[] ponumber, String[] partyCode,
			String status) {
		List<poApprovalHeaderBean> poApprovalHeader = new ArrayList<poApprovalHeaderBean>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String qyery = "SELECT  DECODE(a.currentstatus, '1', 'APPROVED', '2', 'UNAPPROVED') currentstatus, b.legalname1 AS vnname, nvl(CASE WHEN a.alternativeaddresscode "
				+ " IS NULL THEN(TRIM((nvl(b.addressline1, '') || ' ' || nvl(b.addressline2, '') || ' ' || nvl(b.addressline3, '') || ' ' || nvl(b.addressline4, '') || ' ' "
				+ "|| nvl(b.district, '') || '( ' || nvl(TRIM(b.postalcode), '') || ' ) Ph.No :' || nvl(b.phonenumber, '')))) ELSE(SELECT TRIM((nvl(gg.addressline1, '')|| ' ' "
				+ "|| nvl(gg.addressline2, '')|| ' '|| nvl(gg.addressline3, '')|| ' '|| nvl(gg.addressline4, '')|| ' '|| nvl(gg.district, '') "
				+ "|| '( '|| nvl(TRIM(gg.postalcode), '')|| ' ) Ph.No :' || nvl(gg.addressphonenumber, ''))) FROM address gg WHERE gg.uniqueid = a.alternativeaddressuniqueid   "
				+ " AND gg.code = a.alternativeaddresscode ) END, '') vnaddress, nvl(CASE WHEN a.alternativeaddresscode IS NULL "
				+ " THEN( SELECT gstinnumber FROM addressgst WHERE uniqueid = b.absuniqueid) ELSE( SELECT gstinnumber FROM address gg, addressgst gs "
				+ " WHERE gg.absuniqueid = gs.uniqueid AND gg.uniqueid = a.alternativeaddressuniqueid  AND gg.code = a.alternativeaddresscode) END, '') vngstnumber, "
				+ " nvl(CASE WHEN a.alternativeaddresscode IS NULL THEN( SELECT TRIM(longdescription) statename FROM addressgst ag, state st WHERE ag.statecode = st.code "
				+ " AND uniqueid = b.absuniqueid) ELSE( SELECT tt.longdescription FROM address gg, addressgst gs, state tt WHERE gg.absuniqueid = gs.uniqueid "
				+ " AND gs.statecode = tt.code  AND gg.uniqueid = a.alternativeaddressuniqueid AND gg.code = a.alternativeaddresscode ) END, '') vnstatename,  a.TEMPLATECODE, "
				+ " a.PROJECTCODE, a.countercode, a.code code,pl.orderline, TO_CHAR(a.orderdate, 'dd/MM/yyyy') orderdate, a.ordprncustomersuppliercode, a.warehousecode, "
				+ " a.currencycode, ( nvl(TRIM(pl.subcode01), '') || ' ' || nvl(TRIM(pl.subcode02), '') || ' ' || nvl(TRIM(pl.subcode03), '') || ' ' || "
				+ " nvl(TRIM(pl.subcode04), '') || ' ' || nvl(TRIM(pl.subcode05), '') || ' ' || nvl(TRIM(pl.subcode06), '') || ' ' || nvl(TRIM(pl.subcode07), '') || ' ' || "
				+ " nvl(TRIM(pl.subcode08), '') || ' ' || nvl(TRIM(pl.subcode09), '') || ' '|| nvl(TRIM(pl.subcode10), '') ) itemcode, ie.tariffcode hsncode, "
				+ " ie.grossvaluewoheader, pl.itemdescription AS itemdesc, pl.userprimaryuomcode AS uom, nvl(pl.userprimaryquantity, 0) qty, nvl(pl.price, 0) price, nvl(pl.netvalue, 0) netvalue, "
				+ " nvl((SELECT SUM(value) FROM indtaxdetail WHERE itaxcode IN( 'IG1', 'IG2', 'IG3', 'IG4' ) AND absuniqueid = pl.absuniqueid ), 0) igsttaxpers, "
				+ " nvl((SELECT SUM(calculatedvaluertc) FROM indtaxdetail WHERE itaxcode IN( 'IG1', 'IG2', 'IG3', 'IG4' ) AND absuniqueid = pl.absuniqueid ), 0) igstvalue, "
				+ " nvl((SELECT SUM(value) taxpers FROM indtaxdetail WHERE itaxcode IN( 'SG1', 'SG2', 'SG3', 'SG4' ) AND absuniqueid = pl.absuniqueid ), 0) sgsttaxpers, "
				+ " nvl((SELECT SUM(calculatedvaluertc) FROM indtaxdetail WHERE itaxcode IN('SG1', 'SG2', 'SG3', 'SG4' ) AND absuniqueid = pl.absuniqueid ), 0) sgstvalue, "
				+ " nvl((SELECT SUM(value) taxpers FROM indtaxdetail WHERE itaxcode IN('CG1', 'CG2', 'CG3', 'CG4') AND absuniqueid = pl.absuniqueid), 0) cgstpers, "
				+ " nvl((SELECT SUM(calculatedvaluertc) FROM indtaxdetail WHERE itaxcode IN('CG1', 'CG2', 'CG3', 'CG4') AND absuniqueid = pl.absuniqueid), 0) cgstvalue, "
				+ " (SELECT SUM(calculatedvaluertc) FROM indtaxdetail WHERE  absuniqueid = pl.absuniqueid AND itaxcode = 'F01') freight, "
				+ " (SELECT SUM(calculatedvaluertc) FROM indtaxdetail WHERE absuniqueid = pl.absuniqueid AND itaxcode IN ('DST','DIS')) discount FROM purchaseorder a "
				+ " LEFT OUTER JOIN purchaseorderie pi ON a.companycode = pi.companycode AND a.code = pi.code, purchaseorderline pl, purchaseorderlineie ie, orderpartner o, businesspartner b, "
				+ " logicalwarehouse lg, factory fc, addressgst ag, state st WHERE a.companycode = pl.purchaseordercompanycode AND a.countercode = pl.purchaseordercountercode "
				+ " AND a.code = pl.purchaseordercode AND pl.purchaseordercompanycode = ie.purchaseordercompanycode AND pl.purchaseordercountercode = ie.purchaseordercountercode "
				+ " AND pl.purchaseordercode = ie.purchaseordercode AND pl.orderline = ie.orderline AND pl.warehousecode = lg.code AND lg.plantcode = fc.code "
				+ " AND fc.absuniqueid = ag.uniqueid AND fc.statecode = st.code AND a.companycode = o.customersuppliercompanycode AND a.ordertype = o.customersuppliertype "
				+ " AND a.ordprncustomersuppliercode = o.customersuppliercode AND o.orderbusinesspartnernumberid = b.numberid AND a.code = 'WGD19000000156' ";

		String parameter = null;

		for (String purchaseType : potype) {
			if (parameter != null && parameter.length() > 0) {
				parameter += ",'" + purchaseType + "'";
			} else {
				parameter = " and a.code in ('" + purchaseType.toUpperCase() + "'";
			}
		}
		parameter += ")";

		for (String pono : ponumber) {
			if (parameter != null && parameter.length() > 0) {
				parameter += ",'" + pono + "'";
			} else {
				parameter = " and a.code in ('" + pono.toUpperCase() + "'";
			}
		}
		parameter += ")";

		for (String party : partyCode) {
			if (parameter != null && parameter.length() > 0) {
				parameter += ",'" + party + "'";
			} else {
				parameter = " and a.code in ('" + party.toUpperCase() + "'";
			}
		parameter += ")";
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(qyery);
			result = stmt.executeQuery();
			while (result.next()) {
				poApprovalHeaderBean bean = new poApprovalHeaderBean();

				poApprovalHeader.add(bean);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		qyery += parameter;
		System.out.println("qyery: " + qyery);


		return poApprovalHeader;
	}*/

}
