package io.vamani.application.db2.model;

import io.vamani.application.model.PurchaseOrderLineBean;

import java.math.BigDecimal;
import java.util.List;

public class PurchaseOrderImportBean {

	private String countercode;

	private String code;

	private String currencycode;

	private String creationuser;

	private String inhousedate;

	private BigDecimal advancepoamount;

	private String description;

	private String currentstatus;

	private String divname;

	private String divaddress;

	private String addressphonenumber;

	private String emailaddress;

	private String tinno;

	private String midno;

	private String vnname;

	private String vnaddress;

	private String vnphonenumber;

	private String vnemailaddress;

	private String fax;

	private String orderdate;

	private String ordprncustomersuppliercode;

	private String alternativeaddresscode;

	private String deliverypointcode;

	private String externalreference;

	private String internalreference;

	private String warehousecode;

	private String plantcode;

	private String potype;

	private String deliveryterm;

	private String shipmentterm;

	private String paymentcode;

	private String paymentdesc;

	private String noteremarks;

	private String firstcarriertype;

	private String warehousename;

	private String whaddress1;

	private String whaddress2;

	private String whphonenumber;

	private String whemailaddress;

	private String gstinnumber;

	private String statename;

	private String statecode;

	private String factorycode;

	private String factoryname;

	private String factoryaddress;

	private String factorystatecode;

	private String factorycstno;

	private String factorycstdate;

	private String factorygstno;

	private String factoryphoneno;

	private String factoryemailaddress;

	private String vngstnumber;

	private String vnstatecode;

	private String vnstatename;

	public String netValueInword;

	public String specialInstruction;

	public String approvedby;

	private List<PurchaseOrderLineBean> purchaseOrderLineBean;



	public PurchaseOrderImportBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCountercode() {
		return countercode;
	}

	public void setCountercode(String countercode) {
		this.countercode = countercode;
	}

    public String getApprovedby() {
        return approvedby;
    }

    public void setApprovedby(String approvedby) {
        this.approvedby = approvedby;
    }

    public String getNetValueInword() {
		return netValueInword;
	}

	public void setNetValueInword(String netValueInword) {
		this.netValueInword = netValueInword;
	}

	public String getCode() {
		return code;
	}

	public String getSpecialInstruction() {
		return specialInstruction;
	}

	public void setSpecialInstruction(String specialInstruction) {
		this.specialInstruction = specialInstruction;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCurrencycode() {
		return currencycode;
	}

	public void setCurrencycode(String currencycode) {
		this.currencycode = currencycode;
	}

	public String getCreationuser() {
		return creationuser;
	}

	public void setCreationuser(String creationuser) {
		this.creationuser = creationuser;
	}

	public String getInhousedate() {
		return inhousedate;
	}

	public void setInhousedate(String inhousedate) {
		this.inhousedate = inhousedate;
	}

	public BigDecimal getAdvancepoamount() {
		return advancepoamount;
	}

	public void setAdvancepoamount(BigDecimal advancepoamount) {
		this.advancepoamount = advancepoamount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCurrentstatus() {
		return currentstatus;
	}

	public void setCurrentstatus(String currentstatus) {
		this.currentstatus = currentstatus;
	}

	public String getDivname() {
		return divname;
	}

	public void setDivname(String divname) {
		this.divname = divname;
	}

	public String getDivaddress() {
		return divaddress;
	}

	public void setDivaddress(String divaddress) {
		this.divaddress = divaddress;
	}

	public String getAddressphonenumber() {
		return addressphonenumber;
	}

	public void setAddressphonenumber(String addressphonenumber) {
		this.addressphonenumber = addressphonenumber;
	}

	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public String getTinno() {
		return tinno;
	}

	public void setTinno(String tinno) {
		this.tinno = tinno;
	}

	public String getMidno() {
		return midno;
	}

	public void setMidno(String midno) {
		this.midno = midno;
	}

	public String getVnname() {
		return vnname;
	}

	public void setVnname(String vnname) {
		this.vnname = vnname;
	}

	public String getVnaddress() {
		return vnaddress;
	}

	public void setVnaddress(String vnaddress) {
		this.vnaddress = vnaddress;
	}

	public String getVnphonenumber() {
		return vnphonenumber;
	}

	public void setVnphonenumber(String vnphonenumber) {
		this.vnphonenumber = vnphonenumber;
	}

	public String getVnemailaddress() {
		return vnemailaddress;
	}

	public void setVnemailaddress(String vnemailaddress) {
		this.vnemailaddress = vnemailaddress;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	public String getOrdprncustomersuppliercode() {
		return ordprncustomersuppliercode;
	}

	public void setOrdprncustomersuppliercode(String ordprncustomersuppliercode) {
		this.ordprncustomersuppliercode = ordprncustomersuppliercode;
	}

	public String getAlternativeaddresscode() {
		return alternativeaddresscode;
	}

	public void setAlternativeaddresscode(String alternativeaddresscode) {
		this.alternativeaddresscode = alternativeaddresscode;
	}

	public String getDeliverypointcode() {
		return deliverypointcode;
	}

	public void setDeliverypointcode(String deliverypointcode) {
		this.deliverypointcode = deliverypointcode;
	}

	public String getExternalreference() {
		return externalreference;
	}

	public void setExternalreference(String externalreference) {
		this.externalreference = externalreference;
	}

	public String getInternalreference() {
		return internalreference;
	}

	public void setInternalreference(String internalreference) {
		this.internalreference = internalreference;
	}

	public String getWarehousecode() {
		return warehousecode;
	}

	public void setWarehousecode(String warehousecode) {
		this.warehousecode = warehousecode;
	}

	public String getPlantcode() {
		return plantcode;
	}

	public void setPlantcode(String plantcode) {
		this.plantcode = plantcode;
	}

	public String getPotype() {
		return potype;
	}

	public void setPotype(String potype) {
		this.potype = potype;
	}

	public String getDeliveryterm() {
		return deliveryterm;
	}

	public void setDeliveryterm(String deliveryterm) {
		this.deliveryterm = deliveryterm;
	}

	public String getShipmentterm() {
		return shipmentterm;
	}

	public void setShipmentterm(String shipmentterm) {
		this.shipmentterm = shipmentterm;
	}

	public String getPaymentcode() {
		return paymentcode;
	}

	public void setPaymentcode(String paymentcode) {
		this.paymentcode = paymentcode;
	}

	public String getPaymentdesc() {
		return paymentdesc;
	}

	public void setPaymentdesc(String paymentdesc) {
		this.paymentdesc = paymentdesc;
	}

	public String getNoteremarks() {
		return noteremarks;
	}

	public void setNoteremarks(String noteremarks) {
		this.noteremarks = noteremarks;
	}

	public String getFirstcarriertype() {
		return firstcarriertype;
	}

	public void setFirstcarriertype(String firstcarriertype) {
		this.firstcarriertype = firstcarriertype;
	}

	public String getWarehousename() {
		return warehousename;
	}

	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
	}

	public String getWhaddress1() {
		return whaddress1;
	}

	public void setWhaddress1(String whaddress1) {
		this.whaddress1 = whaddress1;
	}

	public String getWhaddress2() {
		return whaddress2;
	}

	public void setWhaddress2(String whaddress2) {
		this.whaddress2 = whaddress2;
	}

	public String getWhphonenumber() {
		return whphonenumber;
	}

	public void setWhphonenumber(String whphonenumber) {
		this.whphonenumber = whphonenumber;
	}

	public String getWhemailaddress() {
		return whemailaddress;
	}

	public void setWhemailaddress(String whemailaddress) {
		this.whemailaddress = whemailaddress;
	}

	public String getGstinnumber() {
		return gstinnumber;
	}

	public void setGstinnumber(String gstinnumber) {
		this.gstinnumber = gstinnumber;
	}

	public String getStatename() {
		return statename;
	}

	public void setStatename(String statename) {
		this.statename = statename;
	}

	public String getStatecode() {
		return statecode;
	}

	public void setStatecode(String statecode) {
		this.statecode = statecode;
	}

	public String getFactorycode() {
		return factorycode;
	}

	public void setFactorycode(String factorycode) {
		this.factorycode = factorycode;
	}

	public String getFactoryname() {
		return factoryname;
	}

	public void setFactoryname(String factoryname) {
		this.factoryname = factoryname;
	}

	public String getFactoryaddress() {
		return factoryaddress;
	}

	public void setFactoryaddress(String factoryaddress) {
		this.factoryaddress = factoryaddress;
	}

	public String getFactorystatecode() {
		return factorystatecode;
	}

	public void setFactorystatecode(String factorystatecode) {
		this.factorystatecode = factorystatecode;
	}

	public String getFactorycstno() {
		return factorycstno;
	}

	public void setFactorycstno(String factorycstno) {
		this.factorycstno = factorycstno;
	}

	public String getFactorycstdate() {
		return factorycstdate;
	}

	public void setFactorycstdate(String factorycstdate) {
		this.factorycstdate = factorycstdate;
	}

	public String getFactorygstno() {
		return factorygstno;
	}

	public void setFactorygstno(String factorygstno) {
		this.factorygstno = factorygstno;
	}

	public String getFactoryphoneno() {
		return factoryphoneno;
	}

	public void setFactoryphoneno(String factoryphoneno) {
		this.factoryphoneno = factoryphoneno;
	}

	public String getFactoryemailaddress() {
		return factoryemailaddress;
	}

	public void setFactoryemailaddress(String factoryemailaddress) {
		this.factoryemailaddress = factoryemailaddress;
	}

	public String getVngstnumber() {
		return vngstnumber;
	}

	public void setVngstnumber(String vngstnumber) {
		this.vngstnumber = vngstnumber;
	}

	public String getVnstatecode() {
		return vnstatecode;
	}

	public void setVnstatecode(String vnstatecode) {
		this.vnstatecode = vnstatecode;
	}

	public String getVnstatename() {
		return vnstatename;
	}

	public void setVnstatename(String vnstatename) {
		this.vnstatename = vnstatename;
	}

	public List<PurchaseOrderLineBean> getPurchaseOrderLineBean() {
		return purchaseOrderLineBean;
	}

	public void setPurchaseOrderLineBean(List<PurchaseOrderLineBean> purchaseOrderLineBean) {
		this.purchaseOrderLineBean = purchaseOrderLineBean;
	}

	@Override
	public String toString() {
		return "PurchaseOrderImportBean [countercode=" + countercode + ", code=" + code + ", currencycode="
				+ currencycode + ", creationuser=" + creationuser + ", inhousedate=" + inhousedate
				+ ", advancepoamount=" + advancepoamount + ", description=" + description + ", currentstatus="
				+ currentstatus + ", divname=" + divname + ", divaddress=" + divaddress + ", addressphonenumber="
				+ addressphonenumber + ", emailaddress=" + emailaddress + ", tinno=" + tinno + ", midno=" + midno
				+ ", vnname=" + vnname + ", vnaddress=" + vnaddress + ", vnphonenumber=" + vnphonenumber
				+ ", vnemailaddress=" + vnemailaddress + ", fax=" + fax + ", orderdate=" + orderdate
				+ ", ordprncustomersuppliercode=" + ordprncustomersuppliercode + ", alternativeaddresscode="
				+ alternativeaddresscode + ", deliverypointcode=" + deliverypointcode + ", externalreference="
				+ externalreference + ", internalreference=" + internalreference + ", warehousecode=" + warehousecode
				+ ", plantcode=" + plantcode + ", potype=" + potype + ", deliveryterm=" + deliveryterm
				+ ", shipmentterm=" + shipmentterm + ", paymentcode=" + paymentcode + ", paymentdesc=" + paymentdesc
				+ ", noteremarks=" + noteremarks + ", firstcarriertype=" + firstcarriertype + ", warehousename="
				+ warehousename + ", whaddress1=" + whaddress1 + ", whaddress2=" + whaddress2 + ", whphonenumber="
				+ whphonenumber + ", whemailaddress=" + whemailaddress + ", gstinnumber=" + gstinnumber + ", statename="
				+ statename + ", statecode=" + statecode + ", factorycode=" + factorycode + ", factoryname="
				+ factoryname + ", factoryaddress=" + factoryaddress + ", factorystatecode=" + factorystatecode
				+ ", factorycstno=" + factorycstno + ", factorycstdate=" + factorycstdate + ", factorygstno="
				+ factorygstno + ", factoryphoneno=" + factoryphoneno + ", factoryemailaddress=" + factoryemailaddress
				+ ", vngstnumber=" + vngstnumber + ", vnstatecode=" + vnstatecode + ", vnstatename=" + vnstatename
				+ ", purchaseOrderLineBean=" + purchaseOrderLineBean + "]";
	}


}
