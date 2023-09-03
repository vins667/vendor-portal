package io.vamani.application.db2.model;

import java.math.BigDecimal;

public class PurchaseOrderJobwBean {

	private String factoryname;

	private String hsncode;

	private String factoryaddress;

	private String factorystatecode;

	private String ewaybillno;

	private String factorystatename;

	private String factorycstno;

	private String factorycstdate;

	private String factorygstno;

	private String factoryphoneno;

	private String factoryemailaddress;

	private String countercode;

	private String lineremarks;

	private String code;

	private String orderline;

	private String ordprncustomersuppliercode;

	private String billtopartyname;

	private String billtoaddress1;

	private String billtoaddress2;

	private String billtophoneno;

	private String billtoemail;

	private String billtogstinnumber;

	private String billtostatecode;

	private String billtostatename;

	private String provisionalgstinnumber;

	private String stepdemandcode;

	private String productionordercode;

	private String orderdate;

	private String itemtypeaficode;

	private String itemdescription;

	private BigDecimal userprimaryquantity;

	private String userprimaryuomcode;

	private BigDecimal useduserprimaryquantity;

	private String projectcode;

	private String costcentercode;

	private String issuewarehousecode;

	private String termsofdeliverycode;

	private String termsofshippingcode;

	private String requiredduedate;

	private String descriptions;

	private String currencycode;

	private BigDecimal price;

	private BigDecimal netvalue;

	private BigDecimal cgstaxpers;

	private BigDecimal cgstaxamount;

	private BigDecimal sgstaxpers;

	private BigDecimal sgstaxamount;

	private BigDecimal igstaxpers;

	private BigDecimal igstaxamount;

	private BigDecimal taxdiscount;

	private BigDecimal netvalueincludingtax;

	private String workcentercode;

	private String operationcode;

	private BigDecimal lossincrease1;

	private String creationuser;

	private String approvedby;

	private String buyername;

	private String operationname;

	private BigDecimal greigeqty;

	private String greigecode;

	private String approvedstatus;

	private String grossValueInword;

	private String salesorderCode;

	private String itemcodeShortDesc;

	private String buyercodes;

	public String getFactoryname() {
		return factoryname;
	}

	public void setFactoryname(String factoryname) {
		this.factoryname = factoryname;
	}

	public String getBuyercodes() {
		return buyercodes;
	}

	public void setBuyercodes(String buyercodes) {
		this.buyercodes = buyercodes;
	}

	public String getFactoryaddress() {
		return factoryaddress;
	}

	public String getLineremarks() {
		return lineremarks;
	}

	public void setLineremarks(String lineremarks) {
		this.lineremarks = lineremarks;
	}

	public String getApprovedstatus() {
		return approvedstatus;
	}

	public String getOrderline() {
		return orderline;
	}

	public void setOrderline(String orderline) {
		this.orderline = orderline;
	}

	public String getEwaybillno() {
		return ewaybillno;
	}

	public void setEwaybillno(String ewaybillno) {
		this.ewaybillno = ewaybillno;
	}

	public void setApprovedstatus(String approvedstatus) {
		this.approvedstatus = approvedstatus;
	}

	public String getSalesorderCode() {
		return salesorderCode;
	}

	public void setSalesorderCode(String salesorderCode) {
		this.salesorderCode = salesorderCode;
	}

	public String getItemcodeShortDesc() {
		return itemcodeShortDesc;
	}

	public void setItemcodeShortDesc(String itemcodeShortDesc) {
		this.itemcodeShortDesc = itemcodeShortDesc;
	}

	public String getHsncode() {
		return hsncode;
	}

	public void setHsncode(String hsncode) {
		this.hsncode = hsncode;
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

	public String getGreigecode() {
		return greigecode;
	}

	public void setGreigecode(String greigecode) {
		this.greigecode = greigecode;
	}

	public String getFactorystatename() {
		return factorystatename;
	}

	public String getGrossValueInword() {
		return grossValueInword;
	}

	public void setGrossValueInword(String grossValueInword) {
		this.grossValueInword = grossValueInword;
	}

	public void setFactorystatename(String factorystatename) {
		this.factorystatename = factorystatename;
	}

	public String getFactorycstno() {
		return factorycstno;
	}

	public void setFactorycstno(String factorycstno) {
		this.factorycstno = factorycstno;
	}

	public String getCreationuser() {
		return creationuser;
	}

	public void setCreationuser(String creationuser) {
		this.creationuser = creationuser;
	}

	public String getApprovedby() {
		return approvedby;
	}

	public void setApprovedby(String approvedby) {
		this.approvedby = approvedby;
	}

	public String getBuyername() {
		return buyername;
	}

	public void setBuyername(String buyername) {
		this.buyername = buyername;
	}

	public String getOperationname() {
		return operationname;
	}

	public void setOperationname(String operationname) {
		this.operationname = operationname;
	}

	public BigDecimal getGreigeqty() {
		return greigeqty;
	}

	public void setGreigeqty(BigDecimal greigeqty) {
		this.greigeqty = greigeqty;
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

	public String getCountercode() {
		return countercode;
	}

	public void setCountercode(String countercode) {
		this.countercode = countercode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOrdprncustomersuppliercode() {
		return ordprncustomersuppliercode;
	}

	public void setOrdprncustomersuppliercode(String ordprncustomersuppliercode) {
		this.ordprncustomersuppliercode = ordprncustomersuppliercode;
	}

	public String getBilltopartyname() {
		return billtopartyname;
	}

	public void setBilltopartyname(String billtopartyname) {
		this.billtopartyname = billtopartyname;
	}

	public String getBilltoaddress1() {
		return billtoaddress1;
	}

	public void setBilltoaddress1(String billtoaddress1) {
		this.billtoaddress1 = billtoaddress1;
	}

	public String getBilltoaddress2() {
		return billtoaddress2;
	}

	public void setBilltoaddress2(String billtoaddress2) {
		this.billtoaddress2 = billtoaddress2;
	}

	public String getBilltophoneno() {
		return billtophoneno;
	}

	public void setBilltophoneno(String billtophoneno) {
		this.billtophoneno = billtophoneno;
	}

	public String getBilltoemail() {
		return billtoemail;
	}

	public void setBilltoemail(String billtoemail) {
		this.billtoemail = billtoemail;
	}

	public String getBilltogstinnumber() {
		return billtogstinnumber;
	}

	public void setBilltogstinnumber(String billtogstinnumber) {
		this.billtogstinnumber = billtogstinnumber;
	}

	public String getBilltostatecode() {
		return billtostatecode;
	}

	public void setBilltostatecode(String billtostatecode) {
		this.billtostatecode = billtostatecode;
	}

	public String getBilltostatename() {
		return billtostatename;
	}

	public void setBilltostatename(String billtostatename) {
		this.billtostatename = billtostatename;
	}

	public String getProvisionalgstinnumber() {
		return provisionalgstinnumber;
	}

	public void setProvisionalgstinnumber(String provisionalgstinnumber) {
		this.provisionalgstinnumber = provisionalgstinnumber;
	}

	public String getStepdemandcode() {
		return stepdemandcode;
	}

	public void setStepdemandcode(String stepdemandcode) {
		this.stepdemandcode = stepdemandcode;
	}

	public String getProductionordercode() {
		return productionordercode;
	}

	public void setProductionordercode(String productionordercode) {
		this.productionordercode = productionordercode;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	public String getItemtypeaficode() {
		return itemtypeaficode;
	}

	public void setItemtypeaficode(String itemtypeaficode) {
		this.itemtypeaficode = itemtypeaficode;
	}

	public String getItemdescription() {
		return itemdescription;
	}

	public void setItemdescription(String itemdescription) {
		this.itemdescription = itemdescription;
	}

	public BigDecimal getUserprimaryquantity() {
		return userprimaryquantity;
	}

	public void setUserprimaryquantity(BigDecimal userprimaryquantity) {
		this.userprimaryquantity = userprimaryquantity;
	}

	public String getUserprimaryuomcode() {
		return userprimaryuomcode;
	}

	public void setUserprimaryuomcode(String userprimaryuomcode) {
		this.userprimaryuomcode = userprimaryuomcode;
	}

	public BigDecimal getUseduserprimaryquantity() {
		return useduserprimaryquantity;
	}

	public void setUseduserprimaryquantity(BigDecimal useduserprimaryquantity) {
		this.useduserprimaryquantity = useduserprimaryquantity;
	}

	public String getProjectcode() {
		return projectcode;
	}

	public void setProjectcode(String projectcode) {
		this.projectcode = projectcode;
	}

	public String getCostcentercode() {
		return costcentercode;
	}

	public void setCostcentercode(String costcentercode) {
		this.costcentercode = costcentercode;
	}

	public String getIssuewarehousecode() {
		return issuewarehousecode;
	}

	public void setIssuewarehousecode(String issuewarehousecode) {
		this.issuewarehousecode = issuewarehousecode;
	}

	public String getTermsofdeliverycode() {
		return termsofdeliverycode;
	}

	public void setTermsofdeliverycode(String termsofdeliverycode) {
		this.termsofdeliverycode = termsofdeliverycode;
	}

	public String getTermsofshippingcode() {
		return termsofshippingcode;
	}

	public void setTermsofshippingcode(String termsofshippingcode) {
		this.termsofshippingcode = termsofshippingcode;
	}

	public String getRequiredduedate() {
		return requiredduedate;
	}

	public void setRequiredduedate(String requiredduedate) {
		this.requiredduedate = requiredduedate;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public String getCurrencycode() {
		return currencycode;
	}

	public void setCurrencycode(String currencycode) {
		this.currencycode = currencycode;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getNetvalue() {
		return netvalue;
	}

	public void setNetvalue(BigDecimal netvalue) {
		this.netvalue = netvalue;
	}

	public BigDecimal getCgstaxpers() {
		return cgstaxpers;
	}

	public void setCgstaxpers(BigDecimal cgstaxpers) {
		this.cgstaxpers = cgstaxpers;
	}

	public BigDecimal getCgstaxamount() {
		return cgstaxamount;
	}

	public void setCgstaxamount(BigDecimal cgstaxamount) {
		this.cgstaxamount = cgstaxamount;
	}

	public BigDecimal getSgstaxpers() {
		return sgstaxpers;
	}

	public void setSgstaxpers(BigDecimal sgstaxpers) {
		this.sgstaxpers = sgstaxpers;
	}

	public BigDecimal getSgstaxamount() {
		return sgstaxamount;
	}

	public void setSgstaxamount(BigDecimal sgstaxamount) {
		this.sgstaxamount = sgstaxamount;
	}

	public BigDecimal getIgstaxpers() {
		return igstaxpers;
	}

	public void setIgstaxpers(BigDecimal igstaxpers) {
		this.igstaxpers = igstaxpers;
	}

	public BigDecimal getIgstaxamount() {
		return igstaxamount;
	}

	public void setIgstaxamount(BigDecimal igstaxamount) {
		this.igstaxamount = igstaxamount;
	}

	public BigDecimal getTaxdiscount() {
		return taxdiscount;
	}

	public void setTaxdiscount(BigDecimal taxdiscount) {
		this.taxdiscount = taxdiscount;
	}

	public BigDecimal getNetvalueincludingtax() {
		return netvalueincludingtax;
	}

	public void setNetvalueincludingtax(BigDecimal netvalueincludingtax) {
		this.netvalueincludingtax = netvalueincludingtax;
	}

	public String getWorkcentercode() {
		return workcentercode;
	}

	public void setWorkcentercode(String workcentercode) {
		this.workcentercode = workcentercode;
	}

	public String getOperationcode() {
		return operationcode;
	}

	public void setOperationcode(String operationcode) {
		this.operationcode = operationcode;
	}

	public BigDecimal getLossincrease1() {
		return lossincrease1;
	}

	public void setLossincrease1(BigDecimal lossincrease1) {
		this.lossincrease1 = lossincrease1;
	}
}
