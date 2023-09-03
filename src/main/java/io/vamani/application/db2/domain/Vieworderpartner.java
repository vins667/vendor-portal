package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "vieworderpartner")
public class Vieworderpartner implements Serializable {
    private String customersuppliercompanycode;
    private String customersuppliertype;
    private String customersuppliercode;
    private Integer orderbusinesspartnernumberid;
    private String orderlogicalwarehousecode;
    private String representcompanycode;
    private String intercompanydivisioncode;
    private String companysuppliercode;
    private String marketcode;
    private String areacode;
    private String ordercategorycode;
    private String companyliableinitialscode;
    private Short orderallowed;
    private Short blockcontrolrequired;
    private String releasetype;
    private Integer releasepriority;
    private String lifecyclecode;
    private String workingcalendarcode;
    private String datecalculationcode;
    private BigDecimal minimumordervalue;
    private BigDecimal maximumordervalue;
    private BigDecimal minimumorderdeliveryvalue;
    private BigDecimal maximumorderdeliveryvalue;
    private BigDecimal minimumorderinvoicevalue;
    private BigDecimal maximumorderinvoicevalue;
    private String logicalwarehousecode;
    private Integer groupordersonshipping;
    private Integer groupshippingsoninvoice;
    private Long deliverypointuniqueid;
    private String deliverypointcode;
    private String termsofdeliverycode;
    private String termsofshippingcode;
    private String transportreasoncode;
    private String firstcarriertype;
    private String firstcarriercode;
    private String secondcarriertype;
    private String secondcarriercode;
    private String thirdcarriertype;
    private String thirdcarriercode;
    private BigDecimal creditlimit;
    private Date enddatecreditlimit;
    private BigDecimal insurancecreditlimit;
    private String insurancecmycsmsuppliertype;
    private String insurancecmycsmsuppliercode;
    private Date enddateinsurancecreditlimit;
    private String financialpartnertype;
    private String financialpartnercode;
    private Short taxstamprequired;
    private Short taxstamprequiredforcredit;
    private Short acknowledgementrequired;
    private String acknowledgementtype;
    private String paymentmethodcode;
    private String agtgrpstdordergrouptypecode;
    private String agentgrpcode;
    private String assortgrpstdordgrouptypecode;
    private String assortgrpcode;
    private String exsgrpstdordergrouptypecode;
    private String exclusivegrpcode;
    private String blockgrpstdordergrouptypecode;
    private String blockgrpcode;
    private String prcgrpstdordergrouptypecode;
    private String pricegrpcode;
    private String dscgrpstdordergrouptypecode;
    private String discountgrpcode;
    private String chargegrpstdordgrouptypecode;
    private String chargegrpcode;
    private String restricgrpstdordgrouptypecode;
    private String restricgrpcode;
    private String cmtgrpstdordergrouptypecode;
    private String commentgrpcode;
    private String taxgrpstdordergrouptypecode;
    private String taxgrpcode;
    private String mngaccgrpstdordgrouptypecode;
    private String managementaccountgrpcode;
    private String fncaccgrpstdordgrouptypecode;
    private String financialaccountgrpcode;
    private String firstusergrpusergengrptypecod;
    private String firstusergrpcode;
    private String sndusergrpusergengrptypecode;
    private String secondusergrpcode;
    private String thirdusergrpusergengrptypecod;
    private String thirdusergrpcode;
    private String fourthusergrpusergengrptypecod;
    private String fourthusergrpcode;
    private String fifthusergrpusergengrptypecod;
    private String fifthusergrpcode;
    private String risknumber;
    private String typeofinsurancesystemtablecode;
    private String typeofinsurancecode;
    private String riskcategorysystemtablecode;
    private String riskcategorycode;
    private String creditreportsystemtablecode;
    private String creditreportcode;
    private Short creditrequest;
    private Date creditrequestdate;
    private String glaccountcode;
    private String fintablenbraccountgroup;
    private String financeaccountgroupcode;
    private Date fininitialdate;
    private Date finfinaldate;
    private Short fininactive;
    private String noteforbooking;
    private String remindertemplatecode;
    private String reminderdelivery;
    private Short collectiondifferent;
    private Integer collectionaddressnumberid;
    private String remblockblocktype;
    private String remblockcode;
    private Date reminderblockdate;
    private Integer businessprnforremindernumberid;
    private String noteforreminder;
    private String baddebtssystemtablecode;
    private String baddebtscode;
    private String valueadjustmentsystemtablecode;
    private String valueadjustmentcode;
    private BigDecimal valueadjustmentrate;
    private String csmsupstatussystemtablecode;
    private String customersupplierstatuscode;
    private String paymenttypecode;
    private String paymentblockblocktype;
    private String paymentblockcode;
    private Date paymentholddate;
    private String noteforpayment;
    private Integer businessprnforpaymentnumberid;
    private String varforaccstatementstdtablecod;
    private String variantforaccountstatementcode;
    private String varforpaymentadvicestdtablecod;
    private String variantforpaymentadvicecode;
    private String varforblncnfstandardtablecode;
    private String varforbalanceconfirmationcode;
    private String vattaxcode;
    private Timestamp creationdatetime;
    private Timestamp creationdatetimeutc;
    private String creationuser;
    private Timestamp lastupdatedatetime;
    private Timestamp lastupdatedatetimeutc;
    private String lastupdateuser;
    private Long absuniqueid;
    private String companycode;
    private String type;
    private String code;
    private String warehousecode;
    private String currencycode;
    private Integer companybankidentifier;
    private String languagecode;
    private Integer numberid;
    private String origininformationtypecode;
    private Date enddate;
    private Integer substitutebpnumberid;
    private String legalname1;
    private String legalname2;
    private String shortname;
    private String searchname;
    private Integer groupbpnumberid;
    private Short sundry;
    private String fiscaltypecode;
    private String fiscalcode;
    private String taxregistrationnumber;
    private String countrycode;
    private String addressline1;
    private String addressline2;
    private String addressline3;
    private String addressline4;
    private String addressline5;
    private String postalcode;
    private String town;
    private String district;
    private String transportzonecode;
    private String addressphonenumber;
    private String addressfaxnumber;
    private String person;
    private String roleinthecompany;
    private String phonenumber;
    private String faxnumber;
    private String emailaddress;
    private String bookline01;
    private String bookline02;
    private String bookline03;
    private String bookline04;
    private String bookline05;
    private Timestamp creationdatetime2;
    private Timestamp creationdatetimeutc2;
    private String creationuser2;
    private Timestamp lastupdatedatetime2;
    private Timestamp lastupdatedatetimeutc2;
    private String lastupdateuser2;
    private Long bpabsuniqueid;
    private String documenttypeformail;

    @Basic
    @Column(name = "CUSTOMERSUPPLIERCOMPANYCODE", nullable = false, length = 3)
    public String getCustomersuppliercompanycode() {
        return customersuppliercompanycode;
    }

    public void setCustomersuppliercompanycode(String customersuppliercompanycode) {
        this.customersuppliercompanycode = customersuppliercompanycode;
    }

    @Basic
    @Column(name = "CUSTOMERSUPPLIERTYPE", nullable = false, length = 1)
    public String getCustomersuppliertype() {
        return customersuppliertype;
    }

    public void setCustomersuppliertype(String customersuppliertype) {
        this.customersuppliertype = customersuppliertype;
    }

    @Id
    @Column(name = "CUSTOMERSUPPLIERCODE", nullable = false, length = 8)
    public String getCustomersuppliercode() {
        return customersuppliercode;
    }

    public void setCustomersuppliercode(String customersuppliercode) {
        this.customersuppliercode = customersuppliercode;
    }

    @Basic
    @Column(name = "ORDERBUSINESSPARTNERNUMBERID", nullable = true, precision = 0)
    public Integer getOrderbusinesspartnernumberid() {
        return orderbusinesspartnernumberid;
    }

    public void setOrderbusinesspartnernumberid(Integer orderbusinesspartnernumberid) {
        this.orderbusinesspartnernumberid = orderbusinesspartnernumberid;
    }

    @Basic
    @Column(name = "ORDERLOGICALWAREHOUSECODE", nullable = true, length = 8)
    public String getOrderlogicalwarehousecode() {
        return orderlogicalwarehousecode;
    }

    public void setOrderlogicalwarehousecode(String orderlogicalwarehousecode) {
        this.orderlogicalwarehousecode = orderlogicalwarehousecode;
    }

    @Basic
    @Column(name = "REPRESENTCOMPANYCODE", nullable = true, length = 3)
    public String getRepresentcompanycode() {
        return representcompanycode;
    }

    public void setRepresentcompanycode(String representcompanycode) {
        this.representcompanycode = representcompanycode;
    }

    @Basic
    @Column(name = "INTERCOMPANYDIVISIONCODE", nullable = true, length = 3)
    public String getIntercompanydivisioncode() {
        return intercompanydivisioncode;
    }

    public void setIntercompanydivisioncode(String intercompanydivisioncode) {
        this.intercompanydivisioncode = intercompanydivisioncode;
    }

    @Basic
    @Column(name = "COMPANYSUPPLIERCODE", nullable = true, length = 10)
    public String getCompanysuppliercode() {
        return companysuppliercode;
    }

    public void setCompanysuppliercode(String companysuppliercode) {
        this.companysuppliercode = companysuppliercode;
    }

    @Basic
    @Column(name = "MARKETCODE", nullable = true, length = 10)
    public String getMarketcode() {
        return marketcode;
    }

    public void setMarketcode(String marketcode) {
        this.marketcode = marketcode;
    }

    @Basic
    @Column(name = "AREACODE", nullable = true, length = 3)
    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    @Basic
    @Column(name = "ORDERCATEGORYCODE", nullable = true, length = 3)
    public String getOrdercategorycode() {
        return ordercategorycode;
    }

    public void setOrdercategorycode(String ordercategorycode) {
        this.ordercategorycode = ordercategorycode;
    }

    @Basic
    @Column(name = "COMPANYLIABLEINITIALSCODE", nullable = true, length = 25)
    public String getCompanyliableinitialscode() {
        return companyliableinitialscode;
    }

    public void setCompanyliableinitialscode(String companyliableinitialscode) {
        this.companyliableinitialscode = companyliableinitialscode;
    }

    @Basic
    @Column(name = "ORDERALLOWED", nullable = false)
    public Short getOrderallowed() {
        return orderallowed;
    }

    public void setOrderallowed(Short orderallowed) {
        this.orderallowed = orderallowed;
    }

    @Basic
    @Column(name = "BLOCKCONTROLREQUIRED", nullable = false)
    public Short getBlockcontrolrequired() {
        return blockcontrolrequired;
    }

    public void setBlockcontrolrequired(Short blockcontrolrequired) {
        this.blockcontrolrequired = blockcontrolrequired;
    }

    @Basic
    @Column(name = "RELEASETYPE", nullable = true, length = 2)
    public String getReleasetype() {
        return releasetype;
    }

    public void setReleasetype(String releasetype) {
        this.releasetype = releasetype;
    }

    @Basic
    @Column(name = "RELEASEPRIORITY", nullable = false)
    public Integer getReleasepriority() {
        return releasepriority;
    }

    public void setReleasepriority(Integer releasepriority) {
        this.releasepriority = releasepriority;
    }

    @Basic
    @Column(name = "LIFECYCLECODE", nullable = true, length = 3)
    public String getLifecyclecode() {
        return lifecyclecode;
    }

    public void setLifecyclecode(String lifecyclecode) {
        this.lifecyclecode = lifecyclecode;
    }

    @Basic
    @Column(name = "WORKINGCALENDARCODE", nullable = true, length = 3)
    public String getWorkingcalendarcode() {
        return workingcalendarcode;
    }

    public void setWorkingcalendarcode(String workingcalendarcode) {
        this.workingcalendarcode = workingcalendarcode;
    }

    @Basic
    @Column(name = "DATECALCULATIONCODE", nullable = true, length = 3)
    public String getDatecalculationcode() {
        return datecalculationcode;
    }

    public void setDatecalculationcode(String datecalculationcode) {
        this.datecalculationcode = datecalculationcode;
    }

    @Basic
    @Column(name = "MINIMUMORDERVALUE", nullable = true, precision = 5)
    public BigDecimal getMinimumordervalue() {
        return minimumordervalue;
    }

    public void setMinimumordervalue(BigDecimal minimumordervalue) {
        this.minimumordervalue = minimumordervalue;
    }

    @Basic
    @Column(name = "MAXIMUMORDERVALUE", nullable = true, precision = 5)
    public BigDecimal getMaximumordervalue() {
        return maximumordervalue;
    }

    public void setMaximumordervalue(BigDecimal maximumordervalue) {
        this.maximumordervalue = maximumordervalue;
    }

    @Basic
    @Column(name = "MINIMUMORDERDELIVERYVALUE", nullable = true, precision = 5)
    public BigDecimal getMinimumorderdeliveryvalue() {
        return minimumorderdeliveryvalue;
    }

    public void setMinimumorderdeliveryvalue(BigDecimal minimumorderdeliveryvalue) {
        this.minimumorderdeliveryvalue = minimumorderdeliveryvalue;
    }

    @Basic
    @Column(name = "MAXIMUMORDERDELIVERYVALUE", nullable = true, precision = 5)
    public BigDecimal getMaximumorderdeliveryvalue() {
        return maximumorderdeliveryvalue;
    }

    public void setMaximumorderdeliveryvalue(BigDecimal maximumorderdeliveryvalue) {
        this.maximumorderdeliveryvalue = maximumorderdeliveryvalue;
    }

    @Basic
    @Column(name = "MINIMUMORDERINVOICEVALUE", nullable = true, precision = 5)
    public BigDecimal getMinimumorderinvoicevalue() {
        return minimumorderinvoicevalue;
    }

    public void setMinimumorderinvoicevalue(BigDecimal minimumorderinvoicevalue) {
        this.minimumorderinvoicevalue = minimumorderinvoicevalue;
    }

    @Basic
    @Column(name = "MAXIMUMORDERINVOICEVALUE", nullable = true, precision = 5)
    public BigDecimal getMaximumorderinvoicevalue() {
        return maximumorderinvoicevalue;
    }

    public void setMaximumorderinvoicevalue(BigDecimal maximumorderinvoicevalue) {
        this.maximumorderinvoicevalue = maximumorderinvoicevalue;
    }

    @Basic
    @Column(name = "LOGICALWAREHOUSECODE", nullable = true, length = 8)
    public String getLogicalwarehousecode() {
        return logicalwarehousecode;
    }

    public void setLogicalwarehousecode(String logicalwarehousecode) {
        this.logicalwarehousecode = logicalwarehousecode;
    }

    @Basic
    @Column(name = "GROUPORDERSONSHIPPING", nullable = false)
    public Integer getGroupordersonshipping() {
        return groupordersonshipping;
    }

    public void setGroupordersonshipping(Integer groupordersonshipping) {
        this.groupordersonshipping = groupordersonshipping;
    }

    @Basic
    @Column(name = "GROUPSHIPPINGSONINVOICE", nullable = false)
    public Integer getGroupshippingsoninvoice() {
        return groupshippingsoninvoice;
    }

    public void setGroupshippingsoninvoice(Integer groupshippingsoninvoice) {
        this.groupshippingsoninvoice = groupshippingsoninvoice;
    }

    @Basic
    @Column(name = "DELIVERYPOINTUNIQUEID", nullable = false)
    public Long getDeliverypointuniqueid() {
        return deliverypointuniqueid;
    }

    public void setDeliverypointuniqueid(Long deliverypointuniqueid) {
        this.deliverypointuniqueid = deliverypointuniqueid;
    }

    @Basic
    @Column(name = "DELIVERYPOINTCODE", nullable = true, length = 8)
    public String getDeliverypointcode() {
        return deliverypointcode;
    }

    public void setDeliverypointcode(String deliverypointcode) {
        this.deliverypointcode = deliverypointcode;
    }

    @Basic
    @Column(name = "TERMSOFDELIVERYCODE", nullable = true, length = 3)
    public String getTermsofdeliverycode() {
        return termsofdeliverycode;
    }

    public void setTermsofdeliverycode(String termsofdeliverycode) {
        this.termsofdeliverycode = termsofdeliverycode;
    }

    @Basic
    @Column(name = "TERMSOFSHIPPINGCODE", nullable = true, length = 2)
    public String getTermsofshippingcode() {
        return termsofshippingcode;
    }

    public void setTermsofshippingcode(String termsofshippingcode) {
        this.termsofshippingcode = termsofshippingcode;
    }

    @Basic
    @Column(name = "TRANSPORTREASONCODE", nullable = true, length = 3)
    public String getTransportreasoncode() {
        return transportreasoncode;
    }

    public void setTransportreasoncode(String transportreasoncode) {
        this.transportreasoncode = transportreasoncode;
    }

    @Basic
    @Column(name = "FIRSTCARRIERTYPE", nullable = true, length = 1)
    public String getFirstcarriertype() {
        return firstcarriertype;
    }

    public void setFirstcarriertype(String firstcarriertype) {
        this.firstcarriertype = firstcarriertype;
    }

    @Basic
    @Column(name = "FIRSTCARRIERCODE", nullable = true, length = 8)
    public String getFirstcarriercode() {
        return firstcarriercode;
    }

    public void setFirstcarriercode(String firstcarriercode) {
        this.firstcarriercode = firstcarriercode;
    }

    @Basic
    @Column(name = "SECONDCARRIERTYPE", nullable = true, length = 1)
    public String getSecondcarriertype() {
        return secondcarriertype;
    }

    public void setSecondcarriertype(String secondcarriertype) {
        this.secondcarriertype = secondcarriertype;
    }

    @Basic
    @Column(name = "SECONDCARRIERCODE", nullable = true, length = 8)
    public String getSecondcarriercode() {
        return secondcarriercode;
    }

    public void setSecondcarriercode(String secondcarriercode) {
        this.secondcarriercode = secondcarriercode;
    }

    @Basic
    @Column(name = "THIRDCARRIERTYPE", nullable = true, length = 1)
    public String getThirdcarriertype() {
        return thirdcarriertype;
    }

    public void setThirdcarriertype(String thirdcarriertype) {
        this.thirdcarriertype = thirdcarriertype;
    }

    @Basic
    @Column(name = "THIRDCARRIERCODE", nullable = true, length = 8)
    public String getThirdcarriercode() {
        return thirdcarriercode;
    }

    public void setThirdcarriercode(String thirdcarriercode) {
        this.thirdcarriercode = thirdcarriercode;
    }

    @Basic
    @Column(name = "CREDITLIMIT", nullable = true, precision = 5)
    public BigDecimal getCreditlimit() {
        return creditlimit;
    }

    public void setCreditlimit(BigDecimal creditlimit) {
        this.creditlimit = creditlimit;
    }

    @Basic
    @Column(name = "ENDDATECREDITLIMIT", nullable = true)
    public Date getEnddatecreditlimit() {
        return enddatecreditlimit;
    }

    public void setEnddatecreditlimit(Date enddatecreditlimit) {
        this.enddatecreditlimit = enddatecreditlimit;
    }

    @Basic
    @Column(name = "INSURANCECREDITLIMIT", nullable = true, precision = 5)
    public BigDecimal getInsurancecreditlimit() {
        return insurancecreditlimit;
    }

    public void setInsurancecreditlimit(BigDecimal insurancecreditlimit) {
        this.insurancecreditlimit = insurancecreditlimit;
    }

    @Basic
    @Column(name = "INSURANCECMYCSMSUPPLIERTYPE", nullable = true, length = 1)
    public String getInsurancecmycsmsuppliertype() {
        return insurancecmycsmsuppliertype;
    }

    public void setInsurancecmycsmsuppliertype(String insurancecmycsmsuppliertype) {
        this.insurancecmycsmsuppliertype = insurancecmycsmsuppliertype;
    }

    @Basic
    @Column(name = "INSURANCECMYCSMSUPPLIERCODE", nullable = true, length = 8)
    public String getInsurancecmycsmsuppliercode() {
        return insurancecmycsmsuppliercode;
    }

    public void setInsurancecmycsmsuppliercode(String insurancecmycsmsuppliercode) {
        this.insurancecmycsmsuppliercode = insurancecmycsmsuppliercode;
    }

    @Basic
    @Column(name = "ENDDATEINSURANCECREDITLIMIT", nullable = true)
    public Date getEnddateinsurancecreditlimit() {
        return enddateinsurancecreditlimit;
    }

    public void setEnddateinsurancecreditlimit(Date enddateinsurancecreditlimit) {
        this.enddateinsurancecreditlimit = enddateinsurancecreditlimit;
    }

    @Basic
    @Column(name = "FINANCIALPARTNERTYPE", nullable = false, length = 1)
    public String getFinancialpartnertype() {
        return financialpartnertype;
    }

    public void setFinancialpartnertype(String financialpartnertype) {
        this.financialpartnertype = financialpartnertype;
    }

    @Basic
    @Column(name = "FINANCIALPARTNERCODE", nullable = true, length = 8)
    public String getFinancialpartnercode() {
        return financialpartnercode;
    }

    public void setFinancialpartnercode(String financialpartnercode) {
        this.financialpartnercode = financialpartnercode;
    }

    @Basic
    @Column(name = "TAXSTAMPREQUIRED", nullable = false)
    public Short getTaxstamprequired() {
        return taxstamprequired;
    }

    public void setTaxstamprequired(Short taxstamprequired) {
        this.taxstamprequired = taxstamprequired;
    }

    @Basic
    @Column(name = "TAXSTAMPREQUIREDFORCREDIT", nullable = false)
    public Short getTaxstamprequiredforcredit() {
        return taxstamprequiredforcredit;
    }

    public void setTaxstamprequiredforcredit(Short taxstamprequiredforcredit) {
        this.taxstamprequiredforcredit = taxstamprequiredforcredit;
    }

    @Basic
    @Column(name = "ACKNOWLEDGEMENTREQUIRED", nullable = false)
    public Short getAcknowledgementrequired() {
        return acknowledgementrequired;
    }

    public void setAcknowledgementrequired(Short acknowledgementrequired) {
        this.acknowledgementrequired = acknowledgementrequired;
    }

    @Basic
    @Column(name = "ACKNOWLEDGEMENTTYPE", nullable = true, length = 2)
    public String getAcknowledgementtype() {
        return acknowledgementtype;
    }

    public void setAcknowledgementtype(String acknowledgementtype) {
        this.acknowledgementtype = acknowledgementtype;
    }

    @Basic
    @Column(name = "PAYMENTMETHODCODE", nullable = true, length = 3)
    public String getPaymentmethodcode() {
        return paymentmethodcode;
    }

    public void setPaymentmethodcode(String paymentmethodcode) {
        this.paymentmethodcode = paymentmethodcode;
    }

    @Basic
    @Column(name = "AGTGRPSTDORDERGROUPTYPECODE", nullable = true, length = 3)
    public String getAgtgrpstdordergrouptypecode() {
        return agtgrpstdordergrouptypecode;
    }

    public void setAgtgrpstdordergrouptypecode(String agtgrpstdordergrouptypecode) {
        this.agtgrpstdordergrouptypecode = agtgrpstdordergrouptypecode;
    }

    @Basic
    @Column(name = "AGENTGRPCODE", nullable = true, length = 3)
    public String getAgentgrpcode() {
        return agentgrpcode;
    }

    public void setAgentgrpcode(String agentgrpcode) {
        this.agentgrpcode = agentgrpcode;
    }

    @Basic
    @Column(name = "ASSORTGRPSTDORDGROUPTYPECODE", nullable = true, length = 3)
    public String getAssortgrpstdordgrouptypecode() {
        return assortgrpstdordgrouptypecode;
    }

    public void setAssortgrpstdordgrouptypecode(String assortgrpstdordgrouptypecode) {
        this.assortgrpstdordgrouptypecode = assortgrpstdordgrouptypecode;
    }

    @Basic
    @Column(name = "ASSORTGRPCODE", nullable = true, length = 3)
    public String getAssortgrpcode() {
        return assortgrpcode;
    }

    public void setAssortgrpcode(String assortgrpcode) {
        this.assortgrpcode = assortgrpcode;
    }

    @Basic
    @Column(name = "EXSGRPSTDORDERGROUPTYPECODE", nullable = true, length = 3)
    public String getExsgrpstdordergrouptypecode() {
        return exsgrpstdordergrouptypecode;
    }

    public void setExsgrpstdordergrouptypecode(String exsgrpstdordergrouptypecode) {
        this.exsgrpstdordergrouptypecode = exsgrpstdordergrouptypecode;
    }

    @Basic
    @Column(name = "EXCLUSIVEGRPCODE", nullable = true, length = 3)
    public String getExclusivegrpcode() {
        return exclusivegrpcode;
    }

    public void setExclusivegrpcode(String exclusivegrpcode) {
        this.exclusivegrpcode = exclusivegrpcode;
    }

    @Basic
    @Column(name = "BLOCKGRPSTDORDERGROUPTYPECODE", nullable = true, length = 3)
    public String getBlockgrpstdordergrouptypecode() {
        return blockgrpstdordergrouptypecode;
    }

    public void setBlockgrpstdordergrouptypecode(String blockgrpstdordergrouptypecode) {
        this.blockgrpstdordergrouptypecode = blockgrpstdordergrouptypecode;
    }

    @Basic
    @Column(name = "BLOCKGRPCODE", nullable = true, length = 3)
    public String getBlockgrpcode() {
        return blockgrpcode;
    }

    public void setBlockgrpcode(String blockgrpcode) {
        this.blockgrpcode = blockgrpcode;
    }

    @Basic
    @Column(name = "PRCGRPSTDORDERGROUPTYPECODE", nullable = true, length = 3)
    public String getPrcgrpstdordergrouptypecode() {
        return prcgrpstdordergrouptypecode;
    }

    public void setPrcgrpstdordergrouptypecode(String prcgrpstdordergrouptypecode) {
        this.prcgrpstdordergrouptypecode = prcgrpstdordergrouptypecode;
    }

    @Basic
    @Column(name = "PRICEGRPCODE", nullable = true, length = 3)
    public String getPricegrpcode() {
        return pricegrpcode;
    }

    public void setPricegrpcode(String pricegrpcode) {
        this.pricegrpcode = pricegrpcode;
    }

    @Basic
    @Column(name = "DSCGRPSTDORDERGROUPTYPECODE", nullable = true, length = 3)
    public String getDscgrpstdordergrouptypecode() {
        return dscgrpstdordergrouptypecode;
    }

    public void setDscgrpstdordergrouptypecode(String dscgrpstdordergrouptypecode) {
        this.dscgrpstdordergrouptypecode = dscgrpstdordergrouptypecode;
    }

    @Basic
    @Column(name = "DISCOUNTGRPCODE", nullable = true, length = 3)
    public String getDiscountgrpcode() {
        return discountgrpcode;
    }

    public void setDiscountgrpcode(String discountgrpcode) {
        this.discountgrpcode = discountgrpcode;
    }

    @Basic
    @Column(name = "CHARGEGRPSTDORDGROUPTYPECODE", nullable = true, length = 3)
    public String getChargegrpstdordgrouptypecode() {
        return chargegrpstdordgrouptypecode;
    }

    public void setChargegrpstdordgrouptypecode(String chargegrpstdordgrouptypecode) {
        this.chargegrpstdordgrouptypecode = chargegrpstdordgrouptypecode;
    }

    @Basic
    @Column(name = "CHARGEGRPCODE", nullable = true, length = 3)
    public String getChargegrpcode() {
        return chargegrpcode;
    }

    public void setChargegrpcode(String chargegrpcode) {
        this.chargegrpcode = chargegrpcode;
    }

    @Basic
    @Column(name = "RESTRICGRPSTDORDGROUPTYPECODE", nullable = true, length = 3)
    public String getRestricgrpstdordgrouptypecode() {
        return restricgrpstdordgrouptypecode;
    }

    public void setRestricgrpstdordgrouptypecode(String restricgrpstdordgrouptypecode) {
        this.restricgrpstdordgrouptypecode = restricgrpstdordgrouptypecode;
    }

    @Basic
    @Column(name = "RESTRICGRPCODE", nullable = true, length = 3)
    public String getRestricgrpcode() {
        return restricgrpcode;
    }

    public void setRestricgrpcode(String restricgrpcode) {
        this.restricgrpcode = restricgrpcode;
    }

    @Basic
    @Column(name = "CMTGRPSTDORDERGROUPTYPECODE", nullable = true, length = 3)
    public String getCmtgrpstdordergrouptypecode() {
        return cmtgrpstdordergrouptypecode;
    }

    public void setCmtgrpstdordergrouptypecode(String cmtgrpstdordergrouptypecode) {
        this.cmtgrpstdordergrouptypecode = cmtgrpstdordergrouptypecode;
    }

    @Basic
    @Column(name = "COMMENTGRPCODE", nullable = true, length = 3)
    public String getCommentgrpcode() {
        return commentgrpcode;
    }

    public void setCommentgrpcode(String commentgrpcode) {
        this.commentgrpcode = commentgrpcode;
    }

    @Basic
    @Column(name = "TAXGRPSTDORDERGROUPTYPECODE", nullable = true, length = 3)
    public String getTaxgrpstdordergrouptypecode() {
        return taxgrpstdordergrouptypecode;
    }

    public void setTaxgrpstdordergrouptypecode(String taxgrpstdordergrouptypecode) {
        this.taxgrpstdordergrouptypecode = taxgrpstdordergrouptypecode;
    }

    @Basic
    @Column(name = "TAXGRPCODE", nullable = true, length = 3)
    public String getTaxgrpcode() {
        return taxgrpcode;
    }

    public void setTaxgrpcode(String taxgrpcode) {
        this.taxgrpcode = taxgrpcode;
    }

    @Basic
    @Column(name = "MNGACCGRPSTDORDGROUPTYPECODE", nullable = true, length = 3)
    public String getMngaccgrpstdordgrouptypecode() {
        return mngaccgrpstdordgrouptypecode;
    }

    public void setMngaccgrpstdordgrouptypecode(String mngaccgrpstdordgrouptypecode) {
        this.mngaccgrpstdordgrouptypecode = mngaccgrpstdordgrouptypecode;
    }

    @Basic
    @Column(name = "MANAGEMENTACCOUNTGRPCODE", nullable = true, length = 3)
    public String getManagementaccountgrpcode() {
        return managementaccountgrpcode;
    }

    public void setManagementaccountgrpcode(String managementaccountgrpcode) {
        this.managementaccountgrpcode = managementaccountgrpcode;
    }

    @Basic
    @Column(name = "FNCACCGRPSTDORDGROUPTYPECODE", nullable = true, length = 3)
    public String getFncaccgrpstdordgrouptypecode() {
        return fncaccgrpstdordgrouptypecode;
    }

    public void setFncaccgrpstdordgrouptypecode(String fncaccgrpstdordgrouptypecode) {
        this.fncaccgrpstdordgrouptypecode = fncaccgrpstdordgrouptypecode;
    }

    @Basic
    @Column(name = "FINANCIALACCOUNTGRPCODE", nullable = true, length = 3)
    public String getFinancialaccountgrpcode() {
        return financialaccountgrpcode;
    }

    public void setFinancialaccountgrpcode(String financialaccountgrpcode) {
        this.financialaccountgrpcode = financialaccountgrpcode;
    }

    @Basic
    @Column(name = "FIRSTUSERGRPUSERGENGRPTYPECOD", nullable = true, length = 3)
    public String getFirstusergrpusergengrptypecod() {
        return firstusergrpusergengrptypecod;
    }

    public void setFirstusergrpusergengrptypecod(String firstusergrpusergengrptypecod) {
        this.firstusergrpusergengrptypecod = firstusergrpusergengrptypecod;
    }

    @Basic
    @Column(name = "FIRSTUSERGRPCODE", nullable = true, length = 10)
    public String getFirstusergrpcode() {
        return firstusergrpcode;
    }

    public void setFirstusergrpcode(String firstusergrpcode) {
        this.firstusergrpcode = firstusergrpcode;
    }

    @Basic
    @Column(name = "SNDUSERGRPUSERGENGRPTYPECODE", nullable = true, length = 3)
    public String getSndusergrpusergengrptypecode() {
        return sndusergrpusergengrptypecode;
    }

    public void setSndusergrpusergengrptypecode(String sndusergrpusergengrptypecode) {
        this.sndusergrpusergengrptypecode = sndusergrpusergengrptypecode;
    }

    @Basic
    @Column(name = "SECONDUSERGRPCODE", nullable = true, length = 10)
    public String getSecondusergrpcode() {
        return secondusergrpcode;
    }

    public void setSecondusergrpcode(String secondusergrpcode) {
        this.secondusergrpcode = secondusergrpcode;
    }

    @Basic
    @Column(name = "THIRDUSERGRPUSERGENGRPTYPECOD", nullable = true, length = 3)
    public String getThirdusergrpusergengrptypecod() {
        return thirdusergrpusergengrptypecod;
    }

    public void setThirdusergrpusergengrptypecod(String thirdusergrpusergengrptypecod) {
        this.thirdusergrpusergengrptypecod = thirdusergrpusergengrptypecod;
    }

    @Basic
    @Column(name = "THIRDUSERGRPCODE", nullable = true, length = 10)
    public String getThirdusergrpcode() {
        return thirdusergrpcode;
    }

    public void setThirdusergrpcode(String thirdusergrpcode) {
        this.thirdusergrpcode = thirdusergrpcode;
    }

    @Basic
    @Column(name = "FOURTHUSERGRPUSERGENGRPTYPECOD", nullable = true, length = 3)
    public String getFourthusergrpusergengrptypecod() {
        return fourthusergrpusergengrptypecod;
    }

    public void setFourthusergrpusergengrptypecod(String fourthusergrpusergengrptypecod) {
        this.fourthusergrpusergengrptypecod = fourthusergrpusergengrptypecod;
    }

    @Basic
    @Column(name = "FOURTHUSERGRPCODE", nullable = true, length = 10)
    public String getFourthusergrpcode() {
        return fourthusergrpcode;
    }

    public void setFourthusergrpcode(String fourthusergrpcode) {
        this.fourthusergrpcode = fourthusergrpcode;
    }

    @Basic
    @Column(name = "FIFTHUSERGRPUSERGENGRPTYPECOD", nullable = true, length = 3)
    public String getFifthusergrpusergengrptypecod() {
        return fifthusergrpusergengrptypecod;
    }

    public void setFifthusergrpusergengrptypecod(String fifthusergrpusergengrptypecod) {
        this.fifthusergrpusergengrptypecod = fifthusergrpusergengrptypecod;
    }

    @Basic
    @Column(name = "FIFTHUSERGRPCODE", nullable = true, length = 10)
    public String getFifthusergrpcode() {
        return fifthusergrpcode;
    }

    public void setFifthusergrpcode(String fifthusergrpcode) {
        this.fifthusergrpcode = fifthusergrpcode;
    }

    @Basic
    @Column(name = "RISKNUMBER", nullable = true, length = 30)
    public String getRisknumber() {
        return risknumber;
    }

    public void setRisknumber(String risknumber) {
        this.risknumber = risknumber;
    }

    @Basic
    @Column(name = "TYPEOFINSURANCESYSTEMTABLECODE", nullable = true, length = 5)
    public String getTypeofinsurancesystemtablecode() {
        return typeofinsurancesystemtablecode;
    }

    public void setTypeofinsurancesystemtablecode(String typeofinsurancesystemtablecode) {
        this.typeofinsurancesystemtablecode = typeofinsurancesystemtablecode;
    }

    @Basic
    @Column(name = "TYPEOFINSURANCECODE", nullable = true, length = 10)
    public String getTypeofinsurancecode() {
        return typeofinsurancecode;
    }

    public void setTypeofinsurancecode(String typeofinsurancecode) {
        this.typeofinsurancecode = typeofinsurancecode;
    }

    @Basic
    @Column(name = "RISKCATEGORYSYSTEMTABLECODE", nullable = true, length = 5)
    public String getRiskcategorysystemtablecode() {
        return riskcategorysystemtablecode;
    }

    public void setRiskcategorysystemtablecode(String riskcategorysystemtablecode) {
        this.riskcategorysystemtablecode = riskcategorysystemtablecode;
    }

    @Basic
    @Column(name = "RISKCATEGORYCODE", nullable = true, length = 10)
    public String getRiskcategorycode() {
        return riskcategorycode;
    }

    public void setRiskcategorycode(String riskcategorycode) {
        this.riskcategorycode = riskcategorycode;
    }

    @Basic
    @Column(name = "CREDITREPORTSYSTEMTABLECODE", nullable = true, length = 5)
    public String getCreditreportsystemtablecode() {
        return creditreportsystemtablecode;
    }

    public void setCreditreportsystemtablecode(String creditreportsystemtablecode) {
        this.creditreportsystemtablecode = creditreportsystemtablecode;
    }

    @Basic
    @Column(name = "CREDITREPORTCODE", nullable = true, length = 10)
    public String getCreditreportcode() {
        return creditreportcode;
    }

    public void setCreditreportcode(String creditreportcode) {
        this.creditreportcode = creditreportcode;
    }

    @Basic
    @Column(name = "CREDITREQUEST", nullable = false)
    public Short getCreditrequest() {
        return creditrequest;
    }

    public void setCreditrequest(Short creditrequest) {
        this.creditrequest = creditrequest;
    }

    @Basic
    @Column(name = "CREDITREQUESTDATE", nullable = true)
    public Date getCreditrequestdate() {
        return creditrequestdate;
    }

    public void setCreditrequestdate(Date creditrequestdate) {
        this.creditrequestdate = creditrequestdate;
    }

    @Basic
    @Column(name = "GLACCOUNTCODE", nullable = true, length = 10)
    public String getGlaccountcode() {
        return glaccountcode;
    }

    public void setGlaccountcode(String glaccountcode) {
        this.glaccountcode = glaccountcode;
    }

    @Basic
    @Column(name = "FINTABLENBRACCOUNTGROUP", nullable = true, length = 5)
    public String getFintablenbraccountgroup() {
        return fintablenbraccountgroup;
    }

    public void setFintablenbraccountgroup(String fintablenbraccountgroup) {
        this.fintablenbraccountgroup = fintablenbraccountgroup;
    }

    @Basic
    @Column(name = "FINANCEACCOUNTGROUPCODE", nullable = true, length = 10)
    public String getFinanceaccountgroupcode() {
        return financeaccountgroupcode;
    }

    public void setFinanceaccountgroupcode(String financeaccountgroupcode) {
        this.financeaccountgroupcode = financeaccountgroupcode;
    }

    @Basic
    @Column(name = "FININITIALDATE", nullable = true)
    public Date getFininitialdate() {
        return fininitialdate;
    }

    public void setFininitialdate(Date fininitialdate) {
        this.fininitialdate = fininitialdate;
    }

    @Basic
    @Column(name = "FINFINALDATE", nullable = true)
    public Date getFinfinaldate() {
        return finfinaldate;
    }

    public void setFinfinaldate(Date finfinaldate) {
        this.finfinaldate = finfinaldate;
    }

    @Basic
    @Column(name = "FININACTIVE", nullable = false)
    public Short getFininactive() {
        return fininactive;
    }

    public void setFininactive(Short fininactive) {
        this.fininactive = fininactive;
    }

    @Basic
    @Column(name = "NOTEFORBOOKING", nullable = true, length = 100)
    public String getNoteforbooking() {
        return noteforbooking;
    }

    public void setNoteforbooking(String noteforbooking) {
        this.noteforbooking = noteforbooking;
    }

    @Basic
    @Column(name = "REMINDERTEMPLATECODE", nullable = true, length = 3)
    public String getRemindertemplatecode() {
        return remindertemplatecode;
    }

    public void setRemindertemplatecode(String remindertemplatecode) {
        this.remindertemplatecode = remindertemplatecode;
    }

    @Basic
    @Column(name = "REMINDERDELIVERY", nullable = true, length = 1)
    public String getReminderdelivery() {
        return reminderdelivery;
    }

    public void setReminderdelivery(String reminderdelivery) {
        this.reminderdelivery = reminderdelivery;
    }

    @Basic
    @Column(name = "COLLECTIONDIFFERENT", nullable = false)
    public Short getCollectiondifferent() {
        return collectiondifferent;
    }

    public void setCollectiondifferent(Short collectiondifferent) {
        this.collectiondifferent = collectiondifferent;
    }

    @Basic
    @Column(name = "COLLECTIONADDRESSNUMBERID", nullable = true, precision = 0)
    public Integer getCollectionaddressnumberid() {
        return collectionaddressnumberid;
    }

    public void setCollectionaddressnumberid(Integer collectionaddressnumberid) {
        this.collectionaddressnumberid = collectionaddressnumberid;
    }

    @Basic
    @Column(name = "REMBLOCKBLOCKTYPE", nullable = true, length = 1)
    public String getRemblockblocktype() {
        return remblockblocktype;
    }

    public void setRemblockblocktype(String remblockblocktype) {
        this.remblockblocktype = remblockblocktype;
    }

    @Basic
    @Column(name = "REMBLOCKCODE", nullable = true, length = 2)
    public String getRemblockcode() {
        return remblockcode;
    }

    public void setRemblockcode(String remblockcode) {
        this.remblockcode = remblockcode;
    }

    @Basic
    @Column(name = "REMINDERBLOCKDATE", nullable = true)
    public Date getReminderblockdate() {
        return reminderblockdate;
    }

    public void setReminderblockdate(Date reminderblockdate) {
        this.reminderblockdate = reminderblockdate;
    }

    @Basic
    @Column(name = "BUSINESSPRNFORREMINDERNUMBERID", nullable = true, precision = 0)
    public Integer getBusinessprnforremindernumberid() {
        return businessprnforremindernumberid;
    }

    public void setBusinessprnforremindernumberid(Integer businessprnforremindernumberid) {
        this.businessprnforremindernumberid = businessprnforremindernumberid;
    }

    @Basic
    @Column(name = "NOTEFORREMINDER", nullable = true, length = 100)
    public String getNoteforreminder() {
        return noteforreminder;
    }

    public void setNoteforreminder(String noteforreminder) {
        this.noteforreminder = noteforreminder;
    }

    @Basic
    @Column(name = "BADDEBTSSYSTEMTABLECODE", nullable = true, length = 5)
    public String getBaddebtssystemtablecode() {
        return baddebtssystemtablecode;
    }

    public void setBaddebtssystemtablecode(String baddebtssystemtablecode) {
        this.baddebtssystemtablecode = baddebtssystemtablecode;
    }

    @Basic
    @Column(name = "BADDEBTSCODE", nullable = true, length = 10)
    public String getBaddebtscode() {
        return baddebtscode;
    }

    public void setBaddebtscode(String baddebtscode) {
        this.baddebtscode = baddebtscode;
    }

    @Basic
    @Column(name = "VALUEADJUSTMENTSYSTEMTABLECODE", nullable = true, length = 5)
    public String getValueadjustmentsystemtablecode() {
        return valueadjustmentsystemtablecode;
    }

    public void setValueadjustmentsystemtablecode(String valueadjustmentsystemtablecode) {
        this.valueadjustmentsystemtablecode = valueadjustmentsystemtablecode;
    }

    @Basic
    @Column(name = "VALUEADJUSTMENTCODE", nullable = true, length = 10)
    public String getValueadjustmentcode() {
        return valueadjustmentcode;
    }

    public void setValueadjustmentcode(String valueadjustmentcode) {
        this.valueadjustmentcode = valueadjustmentcode;
    }

    @Basic
    @Column(name = "VALUEADJUSTMENTRATE", nullable = true, precision = 2)
    public BigDecimal getValueadjustmentrate() {
        return valueadjustmentrate;
    }

    public void setValueadjustmentrate(BigDecimal valueadjustmentrate) {
        this.valueadjustmentrate = valueadjustmentrate;
    }

    @Basic
    @Column(name = "CSMSUPSTATUSSYSTEMTABLECODE", nullable = true, length = 5)
    public String getCsmsupstatussystemtablecode() {
        return csmsupstatussystemtablecode;
    }

    public void setCsmsupstatussystemtablecode(String csmsupstatussystemtablecode) {
        this.csmsupstatussystemtablecode = csmsupstatussystemtablecode;
    }

    @Basic
    @Column(name = "CUSTOMERSUPPLIERSTATUSCODE", nullable = true, length = 10)
    public String getCustomersupplierstatuscode() {
        return customersupplierstatuscode;
    }

    public void setCustomersupplierstatuscode(String customersupplierstatuscode) {
        this.customersupplierstatuscode = customersupplierstatuscode;
    }

    @Basic
    @Column(name = "PAYMENTTYPECODE", nullable = true, length = 3)
    public String getPaymenttypecode() {
        return paymenttypecode;
    }

    public void setPaymenttypecode(String paymenttypecode) {
        this.paymenttypecode = paymenttypecode;
    }

    @Basic
    @Column(name = "PAYMENTBLOCKBLOCKTYPE", nullable = true, length = 1)
    public String getPaymentblockblocktype() {
        return paymentblockblocktype;
    }

    public void setPaymentblockblocktype(String paymentblockblocktype) {
        this.paymentblockblocktype = paymentblockblocktype;
    }

    @Basic
    @Column(name = "PAYMENTBLOCKCODE", nullable = true, length = 2)
    public String getPaymentblockcode() {
        return paymentblockcode;
    }

    public void setPaymentblockcode(String paymentblockcode) {
        this.paymentblockcode = paymentblockcode;
    }

    @Basic
    @Column(name = "PAYMENTHOLDDATE", nullable = true)
    public Date getPaymentholddate() {
        return paymentholddate;
    }

    public void setPaymentholddate(Date paymentholddate) {
        this.paymentholddate = paymentholddate;
    }

    @Basic
    @Column(name = "NOTEFORPAYMENT", nullable = true, length = 100)
    public String getNoteforpayment() {
        return noteforpayment;
    }

    public void setNoteforpayment(String noteforpayment) {
        this.noteforpayment = noteforpayment;
    }

    @Basic
    @Column(name = "BUSINESSPRNFORPAYMENTNUMBERID", nullable = true, precision = 0)
    public Integer getBusinessprnforpaymentnumberid() {
        return businessprnforpaymentnumberid;
    }

    public void setBusinessprnforpaymentnumberid(Integer businessprnforpaymentnumberid) {
        this.businessprnforpaymentnumberid = businessprnforpaymentnumberid;
    }

    @Basic
    @Column(name = "VARFORACCSTATEMENTSTDTABLECOD", nullable = true, length = 5)
    public String getVarforaccstatementstdtablecod() {
        return varforaccstatementstdtablecod;
    }

    public void setVarforaccstatementstdtablecod(String varforaccstatementstdtablecod) {
        this.varforaccstatementstdtablecod = varforaccstatementstdtablecod;
    }

    @Basic
    @Column(name = "VARIANTFORACCOUNTSTATEMENTCODE", nullable = true, length = 10)
    public String getVariantforaccountstatementcode() {
        return variantforaccountstatementcode;
    }

    public void setVariantforaccountstatementcode(String variantforaccountstatementcode) {
        this.variantforaccountstatementcode = variantforaccountstatementcode;
    }

    @Basic
    @Column(name = "VARFORPAYMENTADVICESTDTABLECOD", nullable = true, length = 5)
    public String getVarforpaymentadvicestdtablecod() {
        return varforpaymentadvicestdtablecod;
    }

    public void setVarforpaymentadvicestdtablecod(String varforpaymentadvicestdtablecod) {
        this.varforpaymentadvicestdtablecod = varforpaymentadvicestdtablecod;
    }

    @Basic
    @Column(name = "VARIANTFORPAYMENTADVICECODE", nullable = true, length = 10)
    public String getVariantforpaymentadvicecode() {
        return variantforpaymentadvicecode;
    }

    public void setVariantforpaymentadvicecode(String variantforpaymentadvicecode) {
        this.variantforpaymentadvicecode = variantforpaymentadvicecode;
    }

    @Basic
    @Column(name = "VARFORBLNCNFSTANDARDTABLECODE", nullable = true, length = 5)
    public String getVarforblncnfstandardtablecode() {
        return varforblncnfstandardtablecode;
    }

    public void setVarforblncnfstandardtablecode(String varforblncnfstandardtablecode) {
        this.varforblncnfstandardtablecode = varforblncnfstandardtablecode;
    }

    @Basic
    @Column(name = "VARFORBALANCECONFIRMATIONCODE", nullable = true, length = 10)
    public String getVarforbalanceconfirmationcode() {
        return varforbalanceconfirmationcode;
    }

    public void setVarforbalanceconfirmationcode(String varforbalanceconfirmationcode) {
        this.varforbalanceconfirmationcode = varforbalanceconfirmationcode;
    }

    @Basic
    @Column(name = "VATTAXCODE", nullable = true, length = 5)
    public String getVattaxcode() {
        return vattaxcode;
    }

    public void setVattaxcode(String vattaxcode) {
        this.vattaxcode = vattaxcode;
    }

    @Basic
    @Column(name = "CREATIONDATETIME", nullable = true)
    public Timestamp getCreationdatetime() {
        return creationdatetime;
    }

    public void setCreationdatetime(Timestamp creationdatetime) {
        this.creationdatetime = creationdatetime;
    }

    @Basic
    @Column(name = "CREATIONDATETIMEUTC", nullable = true)
    public Timestamp getCreationdatetimeutc() {
        return creationdatetimeutc;
    }

    public void setCreationdatetimeutc(Timestamp creationdatetimeutc) {
        this.creationdatetimeutc = creationdatetimeutc;
    }

    @Basic
    @Column(name = "CREATIONUSER", nullable = true, length = 25)
    public String getCreationuser() {
        return creationuser;
    }

    public void setCreationuser(String creationuser) {
        this.creationuser = creationuser;
    }

    @Basic
    @Column(name = "LASTUPDATEDATETIME", nullable = true)
    public Timestamp getLastupdatedatetime() {
        return lastupdatedatetime;
    }

    public void setLastupdatedatetime(Timestamp lastupdatedatetime) {
        this.lastupdatedatetime = lastupdatedatetime;
    }

    @Basic
    @Column(name = "LASTUPDATEDATETIMEUTC", nullable = true)
    public Timestamp getLastupdatedatetimeutc() {
        return lastupdatedatetimeutc;
    }

    public void setLastupdatedatetimeutc(Timestamp lastupdatedatetimeutc) {
        this.lastupdatedatetimeutc = lastupdatedatetimeutc;
    }

    @Basic
    @Column(name = "LASTUPDATEUSER", nullable = true, length = 25)
    public String getLastupdateuser() {
        return lastupdateuser;
    }

    public void setLastupdateuser(String lastupdateuser) {
        this.lastupdateuser = lastupdateuser;
    }

    @Basic
    @Column(name = "ABSUNIQUEID", nullable = false)
    public Long getAbsuniqueid() {
        return absuniqueid;
    }

    public void setAbsuniqueid(Long absuniqueid) {
        this.absuniqueid = absuniqueid;
    }

    @Basic
    @Column(name = "COMPANYCODE", nullable = true, length = 3)
    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    @Basic
    @Column(name = "TYPE", nullable = true, length = 1)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "CODE", nullable = true, length = 8)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "WAREHOUSECODE", nullable = true, length = 8)
    public String getWarehousecode() {
        return warehousecode;
    }

    public void setWarehousecode(String warehousecode) {
        this.warehousecode = warehousecode;
    }

    @Basic
    @Column(name = "CURRENCYCODE", nullable = true, length = 4)
    public String getCurrencycode() {
        return currencycode;
    }

    public void setCurrencycode(String currencycode) {
        this.currencycode = currencycode;
    }

    @Basic
    @Column(name = "COMPANYBANKIDENTIFIER", nullable = true, precision = 0)
    public Integer getCompanybankidentifier() {
        return companybankidentifier;
    }

    public void setCompanybankidentifier(Integer companybankidentifier) {
        this.companybankidentifier = companybankidentifier;
    }

    @Basic
    @Column(name = "LANGUAGECODE", nullable = true, length = 2)
    public String getLanguagecode() {
        return languagecode;
    }

    public void setLanguagecode(String languagecode) {
        this.languagecode = languagecode;
    }

    @Basic
    @Column(name = "NUMBERID", nullable = true, precision = 0)
    public Integer getNumberid() {
        return numberid;
    }

    public void setNumberid(Integer numberid) {
        this.numberid = numberid;
    }

    @Basic
    @Column(name = "ORIGININFORMATIONTYPECODE", nullable = true, length = 3)
    public String getOrigininformationtypecode() {
        return origininformationtypecode;
    }

    public void setOrigininformationtypecode(String origininformationtypecode) {
        this.origininformationtypecode = origininformationtypecode;
    }

    @Basic
    @Column(name = "ENDDATE", nullable = true)
    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    @Basic
    @Column(name = "SUBSTITUTEBPNUMBERID", nullable = true, precision = 0)
    public Integer getSubstitutebpnumberid() {
        return substitutebpnumberid;
    }

    public void setSubstitutebpnumberid(Integer substitutebpnumberid) {
        this.substitutebpnumberid = substitutebpnumberid;
    }

    @Basic
    @Column(name = "LEGALNAME1", nullable = true, length = 270)
    public String getLegalname1() {
        return legalname1;
    }

    public void setLegalname1(String legalname1) {
        this.legalname1 = legalname1;
    }

    @Basic
    @Column(name = "LEGALNAME2", nullable = true, length = 200)
    public String getLegalname2() {
        return legalname2;
    }

    public void setLegalname2(String legalname2) {
        this.legalname2 = legalname2;
    }

    @Basic
    @Column(name = "SHORTNAME", nullable = true, length = 80)
    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    @Basic
    @Column(name = "SEARCHNAME", nullable = true, length = 120)
    public String getSearchname() {
        return searchname;
    }

    public void setSearchname(String searchname) {
        this.searchname = searchname;
    }

    @Basic
    @Column(name = "GROUPBPNUMBERID", nullable = true, precision = 0)
    public Integer getGroupbpnumberid() {
        return groupbpnumberid;
    }

    public void setGroupbpnumberid(Integer groupbpnumberid) {
        this.groupbpnumberid = groupbpnumberid;
    }

    @Basic
    @Column(name = "SUNDRY", nullable = true)
    public Short getSundry() {
        return sundry;
    }

    public void setSundry(Short sundry) {
        this.sundry = sundry;
    }

    @Basic
    @Column(name = "FISCALTYPECODE", nullable = true, length = 2)
    public String getFiscaltypecode() {
        return fiscaltypecode;
    }

    public void setFiscaltypecode(String fiscaltypecode) {
        this.fiscaltypecode = fiscaltypecode;
    }

    @Basic
    @Column(name = "FISCALCODE", nullable = true, length = 16)
    public String getFiscalcode() {
        return fiscalcode;
    }

    public void setFiscalcode(String fiscalcode) {
        this.fiscalcode = fiscalcode;
    }

    @Basic
    @Column(name = "TAXREGISTRATIONNUMBER", nullable = true, length = 15)
    public String getTaxregistrationnumber() {
        return taxregistrationnumber;
    }

    public void setTaxregistrationnumber(String taxregistrationnumber) {
        this.taxregistrationnumber = taxregistrationnumber;
    }

    @Basic
    @Column(name = "COUNTRYCODE", nullable = true, length = 3)
    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    @Basic
    @Column(name = "ADDRESSLINE1", nullable = true, length = 150)
    public String getAddressline1() {
        return addressline1;
    }

    public void setAddressline1(String addressline1) {
        this.addressline1 = addressline1;
    }

    @Basic
    @Column(name = "ADDRESSLINE2", nullable = true, length = 150)
    public String getAddressline2() {
        return addressline2;
    }

    public void setAddressline2(String addressline2) {
        this.addressline2 = addressline2;
    }

    @Basic
    @Column(name = "ADDRESSLINE3", nullable = true, length = 150)
    public String getAddressline3() {
        return addressline3;
    }

    public void setAddressline3(String addressline3) {
        this.addressline3 = addressline3;
    }

    @Basic
    @Column(name = "ADDRESSLINE4", nullable = true, length = 150)
    public String getAddressline4() {
        return addressline4;
    }

    public void setAddressline4(String addressline4) {
        this.addressline4 = addressline4;
    }

    @Basic
    @Column(name = "ADDRESSLINE5", nullable = true, length = 150)
    public String getAddressline5() {
        return addressline5;
    }

    public void setAddressline5(String addressline5) {
        this.addressline5 = addressline5;
    }

    @Basic
    @Column(name = "POSTALCODE", nullable = true, length = 20)
    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    @Basic
    @Column(name = "TOWN", nullable = true, length = 200)
    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @Basic
    @Column(name = "DISTRICT", nullable = true, length = 200)
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Basic
    @Column(name = "TRANSPORTZONECODE", nullable = true, length = 3)
    public String getTransportzonecode() {
        return transportzonecode;
    }

    public void setTransportzonecode(String transportzonecode) {
        this.transportzonecode = transportzonecode;
    }

    @Basic
    @Column(name = "ADDRESSPHONENUMBER", nullable = true, length = 80)
    public String getAddressphonenumber() {
        return addressphonenumber;
    }

    public void setAddressphonenumber(String addressphonenumber) {
        this.addressphonenumber = addressphonenumber;
    }

    @Basic
    @Column(name = "ADDRESSFAXNUMBER", nullable = true, length = 80)
    public String getAddressfaxnumber() {
        return addressfaxnumber;
    }

    public void setAddressfaxnumber(String addressfaxnumber) {
        this.addressfaxnumber = addressfaxnumber;
    }

    @Basic
    @Column(name = "PERSON", nullable = true, length = 200)
    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    @Basic
    @Column(name = "ROLEINTHECOMPANY", nullable = true, length = 200)
    public String getRoleinthecompany() {
        return roleinthecompany;
    }

    public void setRoleinthecompany(String roleinthecompany) {
        this.roleinthecompany = roleinthecompany;
    }

    @Basic
    @Column(name = "PHONENUMBER", nullable = true, length = 80)
    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Basic
    @Column(name = "FAXNUMBER", nullable = true, length = 80)
    public String getFaxnumber() {
        return faxnumber;
    }

    public void setFaxnumber(String faxnumber) {
        this.faxnumber = faxnumber;
    }

    @Basic
    @Column(name = "EMAILADDRESS", nullable = true, length = 200)
    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    @Basic
    @Column(name = "BOOKLINE01", nullable = true, length = 200)
    public String getBookline01() {
        return bookline01;
    }

    public void setBookline01(String bookline01) {
        this.bookline01 = bookline01;
    }

    @Basic
    @Column(name = "BOOKLINE02", nullable = true, length = 200)
    public String getBookline02() {
        return bookline02;
    }

    public void setBookline02(String bookline02) {
        this.bookline02 = bookline02;
    }

    @Basic
    @Column(name = "BOOKLINE03", nullable = true, length = 200)
    public String getBookline03() {
        return bookline03;
    }

    public void setBookline03(String bookline03) {
        this.bookline03 = bookline03;
    }

    @Basic
    @Column(name = "BOOKLINE04", nullable = true, length = 200)
    public String getBookline04() {
        return bookline04;
    }

    public void setBookline04(String bookline04) {
        this.bookline04 = bookline04;
    }

    @Basic
    @Column(name = "BOOKLINE05", nullable = true, length = 200)
    public String getBookline05() {
        return bookline05;
    }

    public void setBookline05(String bookline05) {
        this.bookline05 = bookline05;
    }

    @Basic
    @Column(name = "CREATIONDATETIME2", nullable = true)
    public Timestamp getCreationdatetime2() {
        return creationdatetime2;
    }

    public void setCreationdatetime2(Timestamp creationdatetime2) {
        this.creationdatetime2 = creationdatetime2;
    }

    @Basic
    @Column(name = "CREATIONDATETIMEUTC2", nullable = true)
    public Timestamp getCreationdatetimeutc2() {
        return creationdatetimeutc2;
    }

    public void setCreationdatetimeutc2(Timestamp creationdatetimeutc2) {
        this.creationdatetimeutc2 = creationdatetimeutc2;
    }

    @Basic
    @Column(name = "CREATIONUSER2", nullable = true, length = 25)
    public String getCreationuser2() {
        return creationuser2;
    }

    public void setCreationuser2(String creationuser2) {
        this.creationuser2 = creationuser2;
    }

    @Basic
    @Column(name = "LASTUPDATEDATETIME2", nullable = true)
    public Timestamp getLastupdatedatetime2() {
        return lastupdatedatetime2;
    }

    public void setLastupdatedatetime2(Timestamp lastupdatedatetime2) {
        this.lastupdatedatetime2 = lastupdatedatetime2;
    }

    @Basic
    @Column(name = "LASTUPDATEDATETIMEUTC2", nullable = true)
    public Timestamp getLastupdatedatetimeutc2() {
        return lastupdatedatetimeutc2;
    }

    public void setLastupdatedatetimeutc2(Timestamp lastupdatedatetimeutc2) {
        this.lastupdatedatetimeutc2 = lastupdatedatetimeutc2;
    }

    @Basic
    @Column(name = "LASTUPDATEUSER2", nullable = true, length = 25)
    public String getLastupdateuser2() {
        return lastupdateuser2;
    }

    public void setLastupdateuser2(String lastupdateuser2) {
        this.lastupdateuser2 = lastupdateuser2;
    }

    @Basic
    @Column(name = "BPABSUNIQUEID", nullable = true)
    public Long getBpabsuniqueid() {
        return bpabsuniqueid;
    }

    public void setBpabsuniqueid(Long bpabsuniqueid) {
        this.bpabsuniqueid = bpabsuniqueid;
    }

    @Basic
    @Column(name = "DOCUMENTTYPEFORMAIL", nullable = true, length = 90)
    public String getDocumenttypeformail() {
        return documenttypeformail;
    }

    public void setDocumenttypeformail(String documenttypeformail) {
        this.documenttypeformail = documenttypeformail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vieworderpartner that = (Vieworderpartner) o;
        return Objects.equals(customersuppliercompanycode, that.customersuppliercompanycode) && Objects.equals(customersuppliertype, that.customersuppliertype) && Objects.equals(customersuppliercode, that.customersuppliercode) && Objects.equals(orderbusinesspartnernumberid, that.orderbusinesspartnernumberid) && Objects.equals(orderlogicalwarehousecode, that.orderlogicalwarehousecode) && Objects.equals(representcompanycode, that.representcompanycode) && Objects.equals(intercompanydivisioncode, that.intercompanydivisioncode) && Objects.equals(companysuppliercode, that.companysuppliercode) && Objects.equals(marketcode, that.marketcode) && Objects.equals(areacode, that.areacode) && Objects.equals(ordercategorycode, that.ordercategorycode) && Objects.equals(companyliableinitialscode, that.companyliableinitialscode) && Objects.equals(orderallowed, that.orderallowed) && Objects.equals(blockcontrolrequired, that.blockcontrolrequired) && Objects.equals(releasetype, that.releasetype) && Objects.equals(releasepriority, that.releasepriority) && Objects.equals(lifecyclecode, that.lifecyclecode) && Objects.equals(workingcalendarcode, that.workingcalendarcode) && Objects.equals(datecalculationcode, that.datecalculationcode) && Objects.equals(minimumordervalue, that.minimumordervalue) && Objects.equals(maximumordervalue, that.maximumordervalue) && Objects.equals(minimumorderdeliveryvalue, that.minimumorderdeliveryvalue) && Objects.equals(maximumorderdeliveryvalue, that.maximumorderdeliveryvalue) && Objects.equals(minimumorderinvoicevalue, that.minimumorderinvoicevalue) && Objects.equals(maximumorderinvoicevalue, that.maximumorderinvoicevalue) && Objects.equals(logicalwarehousecode, that.logicalwarehousecode) && Objects.equals(groupordersonshipping, that.groupordersonshipping) && Objects.equals(groupshippingsoninvoice, that.groupshippingsoninvoice) && Objects.equals(deliverypointuniqueid, that.deliverypointuniqueid) && Objects.equals(deliverypointcode, that.deliverypointcode) && Objects.equals(termsofdeliverycode, that.termsofdeliverycode) && Objects.equals(termsofshippingcode, that.termsofshippingcode) && Objects.equals(transportreasoncode, that.transportreasoncode) && Objects.equals(firstcarriertype, that.firstcarriertype) && Objects.equals(firstcarriercode, that.firstcarriercode) && Objects.equals(secondcarriertype, that.secondcarriertype) && Objects.equals(secondcarriercode, that.secondcarriercode) && Objects.equals(thirdcarriertype, that.thirdcarriertype) && Objects.equals(thirdcarriercode, that.thirdcarriercode) && Objects.equals(creditlimit, that.creditlimit) && Objects.equals(enddatecreditlimit, that.enddatecreditlimit) && Objects.equals(insurancecreditlimit, that.insurancecreditlimit) && Objects.equals(insurancecmycsmsuppliertype, that.insurancecmycsmsuppliertype) && Objects.equals(insurancecmycsmsuppliercode, that.insurancecmycsmsuppliercode) && Objects.equals(enddateinsurancecreditlimit, that.enddateinsurancecreditlimit) && Objects.equals(financialpartnertype, that.financialpartnertype) && Objects.equals(financialpartnercode, that.financialpartnercode) && Objects.equals(taxstamprequired, that.taxstamprequired) && Objects.equals(taxstamprequiredforcredit, that.taxstamprequiredforcredit) && Objects.equals(acknowledgementrequired, that.acknowledgementrequired) && Objects.equals(acknowledgementtype, that.acknowledgementtype) && Objects.equals(paymentmethodcode, that.paymentmethodcode) && Objects.equals(agtgrpstdordergrouptypecode, that.agtgrpstdordergrouptypecode) && Objects.equals(agentgrpcode, that.agentgrpcode) && Objects.equals(assortgrpstdordgrouptypecode, that.assortgrpstdordgrouptypecode) && Objects.equals(assortgrpcode, that.assortgrpcode) && Objects.equals(exsgrpstdordergrouptypecode, that.exsgrpstdordergrouptypecode) && Objects.equals(exclusivegrpcode, that.exclusivegrpcode) && Objects.equals(blockgrpstdordergrouptypecode, that.blockgrpstdordergrouptypecode) && Objects.equals(blockgrpcode, that.blockgrpcode) && Objects.equals(prcgrpstdordergrouptypecode, that.prcgrpstdordergrouptypecode) && Objects.equals(pricegrpcode, that.pricegrpcode) && Objects.equals(dscgrpstdordergrouptypecode, that.dscgrpstdordergrouptypecode) && Objects.equals(discountgrpcode, that.discountgrpcode) && Objects.equals(chargegrpstdordgrouptypecode, that.chargegrpstdordgrouptypecode) && Objects.equals(chargegrpcode, that.chargegrpcode) && Objects.equals(restricgrpstdordgrouptypecode, that.restricgrpstdordgrouptypecode) && Objects.equals(restricgrpcode, that.restricgrpcode) && Objects.equals(cmtgrpstdordergrouptypecode, that.cmtgrpstdordergrouptypecode) && Objects.equals(commentgrpcode, that.commentgrpcode) && Objects.equals(taxgrpstdordergrouptypecode, that.taxgrpstdordergrouptypecode) && Objects.equals(taxgrpcode, that.taxgrpcode) && Objects.equals(mngaccgrpstdordgrouptypecode, that.mngaccgrpstdordgrouptypecode) && Objects.equals(managementaccountgrpcode, that.managementaccountgrpcode) && Objects.equals(fncaccgrpstdordgrouptypecode, that.fncaccgrpstdordgrouptypecode) && Objects.equals(financialaccountgrpcode, that.financialaccountgrpcode) && Objects.equals(firstusergrpusergengrptypecod, that.firstusergrpusergengrptypecod) && Objects.equals(firstusergrpcode, that.firstusergrpcode) && Objects.equals(sndusergrpusergengrptypecode, that.sndusergrpusergengrptypecode) && Objects.equals(secondusergrpcode, that.secondusergrpcode) && Objects.equals(thirdusergrpusergengrptypecod, that.thirdusergrpusergengrptypecod) && Objects.equals(thirdusergrpcode, that.thirdusergrpcode) && Objects.equals(fourthusergrpusergengrptypecod, that.fourthusergrpusergengrptypecod) && Objects.equals(fourthusergrpcode, that.fourthusergrpcode) && Objects.equals(fifthusergrpusergengrptypecod, that.fifthusergrpusergengrptypecod) && Objects.equals(fifthusergrpcode, that.fifthusergrpcode) && Objects.equals(risknumber, that.risknumber) && Objects.equals(typeofinsurancesystemtablecode, that.typeofinsurancesystemtablecode) && Objects.equals(typeofinsurancecode, that.typeofinsurancecode) && Objects.equals(riskcategorysystemtablecode, that.riskcategorysystemtablecode) && Objects.equals(riskcategorycode, that.riskcategorycode) && Objects.equals(creditreportsystemtablecode, that.creditreportsystemtablecode) && Objects.equals(creditreportcode, that.creditreportcode) && Objects.equals(creditrequest, that.creditrequest) && Objects.equals(creditrequestdate, that.creditrequestdate) && Objects.equals(glaccountcode, that.glaccountcode) && Objects.equals(fintablenbraccountgroup, that.fintablenbraccountgroup) && Objects.equals(financeaccountgroupcode, that.financeaccountgroupcode) && Objects.equals(fininitialdate, that.fininitialdate) && Objects.equals(finfinaldate, that.finfinaldate) && Objects.equals(fininactive, that.fininactive) && Objects.equals(noteforbooking, that.noteforbooking) && Objects.equals(remindertemplatecode, that.remindertemplatecode) && Objects.equals(reminderdelivery, that.reminderdelivery) && Objects.equals(collectiondifferent, that.collectiondifferent) && Objects.equals(collectionaddressnumberid, that.collectionaddressnumberid) && Objects.equals(remblockblocktype, that.remblockblocktype) && Objects.equals(remblockcode, that.remblockcode) && Objects.equals(reminderblockdate, that.reminderblockdate) && Objects.equals(businessprnforremindernumberid, that.businessprnforremindernumberid) && Objects.equals(noteforreminder, that.noteforreminder) && Objects.equals(baddebtssystemtablecode, that.baddebtssystemtablecode) && Objects.equals(baddebtscode, that.baddebtscode) && Objects.equals(valueadjustmentsystemtablecode, that.valueadjustmentsystemtablecode) && Objects.equals(valueadjustmentcode, that.valueadjustmentcode) && Objects.equals(valueadjustmentrate, that.valueadjustmentrate) && Objects.equals(csmsupstatussystemtablecode, that.csmsupstatussystemtablecode) && Objects.equals(customersupplierstatuscode, that.customersupplierstatuscode) && Objects.equals(paymenttypecode, that.paymenttypecode) && Objects.equals(paymentblockblocktype, that.paymentblockblocktype) && Objects.equals(paymentblockcode, that.paymentblockcode) && Objects.equals(paymentholddate, that.paymentholddate) && Objects.equals(noteforpayment, that.noteforpayment) && Objects.equals(businessprnforpaymentnumberid, that.businessprnforpaymentnumberid) && Objects.equals(varforaccstatementstdtablecod, that.varforaccstatementstdtablecod) && Objects.equals(variantforaccountstatementcode, that.variantforaccountstatementcode) && Objects.equals(varforpaymentadvicestdtablecod, that.varforpaymentadvicestdtablecod) && Objects.equals(variantforpaymentadvicecode, that.variantforpaymentadvicecode) && Objects.equals(varforblncnfstandardtablecode, that.varforblncnfstandardtablecode) && Objects.equals(varforbalanceconfirmationcode, that.varforbalanceconfirmationcode) && Objects.equals(vattaxcode, that.vattaxcode) && Objects.equals(creationdatetime, that.creationdatetime) && Objects.equals(creationdatetimeutc, that.creationdatetimeutc) && Objects.equals(creationuser, that.creationuser) && Objects.equals(lastupdatedatetime, that.lastupdatedatetime) && Objects.equals(lastupdatedatetimeutc, that.lastupdatedatetimeutc) && Objects.equals(lastupdateuser, that.lastupdateuser) && Objects.equals(absuniqueid, that.absuniqueid) && Objects.equals(companycode, that.companycode) && Objects.equals(type, that.type) && Objects.equals(code, that.code) && Objects.equals(warehousecode, that.warehousecode) && Objects.equals(currencycode, that.currencycode) && Objects.equals(companybankidentifier, that.companybankidentifier) && Objects.equals(languagecode, that.languagecode) && Objects.equals(numberid, that.numberid) && Objects.equals(origininformationtypecode, that.origininformationtypecode) && Objects.equals(enddate, that.enddate) && Objects.equals(substitutebpnumberid, that.substitutebpnumberid) && Objects.equals(legalname1, that.legalname1) && Objects.equals(legalname2, that.legalname2) && Objects.equals(shortname, that.shortname) && Objects.equals(searchname, that.searchname) && Objects.equals(groupbpnumberid, that.groupbpnumberid) && Objects.equals(sundry, that.sundry) && Objects.equals(fiscaltypecode, that.fiscaltypecode) && Objects.equals(fiscalcode, that.fiscalcode) && Objects.equals(taxregistrationnumber, that.taxregistrationnumber) && Objects.equals(countrycode, that.countrycode) && Objects.equals(addressline1, that.addressline1) && Objects.equals(addressline2, that.addressline2) && Objects.equals(addressline3, that.addressline3) && Objects.equals(addressline4, that.addressline4) && Objects.equals(addressline5, that.addressline5) && Objects.equals(postalcode, that.postalcode) && Objects.equals(town, that.town) && Objects.equals(district, that.district) && Objects.equals(transportzonecode, that.transportzonecode) && Objects.equals(addressphonenumber, that.addressphonenumber) && Objects.equals(addressfaxnumber, that.addressfaxnumber) && Objects.equals(person, that.person) && Objects.equals(roleinthecompany, that.roleinthecompany) && Objects.equals(phonenumber, that.phonenumber) && Objects.equals(faxnumber, that.faxnumber) && Objects.equals(emailaddress, that.emailaddress) && Objects.equals(bookline01, that.bookline01) && Objects.equals(bookline02, that.bookline02) && Objects.equals(bookline03, that.bookline03) && Objects.equals(bookline04, that.bookline04) && Objects.equals(bookline05, that.bookline05) && Objects.equals(creationdatetime2, that.creationdatetime2) && Objects.equals(creationdatetimeutc2, that.creationdatetimeutc2) && Objects.equals(creationuser2, that.creationuser2) && Objects.equals(lastupdatedatetime2, that.lastupdatedatetime2) && Objects.equals(lastupdatedatetimeutc2, that.lastupdatedatetimeutc2) && Objects.equals(lastupdateuser2, that.lastupdateuser2) && Objects.equals(bpabsuniqueid, that.bpabsuniqueid) && Objects.equals(documenttypeformail, that.documenttypeformail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customersuppliercompanycode, customersuppliertype, customersuppliercode, orderbusinesspartnernumberid, orderlogicalwarehousecode, representcompanycode, intercompanydivisioncode, companysuppliercode, marketcode, areacode, ordercategorycode, companyliableinitialscode, orderallowed, blockcontrolrequired, releasetype, releasepriority, lifecyclecode, workingcalendarcode, datecalculationcode, minimumordervalue, maximumordervalue, minimumorderdeliveryvalue, maximumorderdeliveryvalue, minimumorderinvoicevalue, maximumorderinvoicevalue, logicalwarehousecode, groupordersonshipping, groupshippingsoninvoice, deliverypointuniqueid, deliverypointcode, termsofdeliverycode, termsofshippingcode, transportreasoncode, firstcarriertype, firstcarriercode, secondcarriertype, secondcarriercode, thirdcarriertype, thirdcarriercode, creditlimit, enddatecreditlimit, insurancecreditlimit, insurancecmycsmsuppliertype, insurancecmycsmsuppliercode, enddateinsurancecreditlimit, financialpartnertype, financialpartnercode, taxstamprequired, taxstamprequiredforcredit, acknowledgementrequired, acknowledgementtype, paymentmethodcode, agtgrpstdordergrouptypecode, agentgrpcode, assortgrpstdordgrouptypecode, assortgrpcode, exsgrpstdordergrouptypecode, exclusivegrpcode, blockgrpstdordergrouptypecode, blockgrpcode, prcgrpstdordergrouptypecode, pricegrpcode, dscgrpstdordergrouptypecode, discountgrpcode, chargegrpstdordgrouptypecode, chargegrpcode, restricgrpstdordgrouptypecode, restricgrpcode, cmtgrpstdordergrouptypecode, commentgrpcode, taxgrpstdordergrouptypecode, taxgrpcode, mngaccgrpstdordgrouptypecode, managementaccountgrpcode, fncaccgrpstdordgrouptypecode, financialaccountgrpcode, firstusergrpusergengrptypecod, firstusergrpcode, sndusergrpusergengrptypecode, secondusergrpcode, thirdusergrpusergengrptypecod, thirdusergrpcode, fourthusergrpusergengrptypecod, fourthusergrpcode, fifthusergrpusergengrptypecod, fifthusergrpcode, risknumber, typeofinsurancesystemtablecode, typeofinsurancecode, riskcategorysystemtablecode, riskcategorycode, creditreportsystemtablecode, creditreportcode, creditrequest, creditrequestdate, glaccountcode, fintablenbraccountgroup, financeaccountgroupcode, fininitialdate, finfinaldate, fininactive, noteforbooking, remindertemplatecode, reminderdelivery, collectiondifferent, collectionaddressnumberid, remblockblocktype, remblockcode, reminderblockdate, businessprnforremindernumberid, noteforreminder, baddebtssystemtablecode, baddebtscode, valueadjustmentsystemtablecode, valueadjustmentcode, valueadjustmentrate, csmsupstatussystemtablecode, customersupplierstatuscode, paymenttypecode, paymentblockblocktype, paymentblockcode, paymentholddate, noteforpayment, businessprnforpaymentnumberid, varforaccstatementstdtablecod, variantforaccountstatementcode, varforpaymentadvicestdtablecod, variantforpaymentadvicecode, varforblncnfstandardtablecode, varforbalanceconfirmationcode, vattaxcode, creationdatetime, creationdatetimeutc, creationuser, lastupdatedatetime, lastupdatedatetimeutc, lastupdateuser, absuniqueid, companycode, type, code, warehousecode, currencycode, companybankidentifier, languagecode, numberid, origininformationtypecode, enddate, substitutebpnumberid, legalname1, legalname2, shortname, searchname, groupbpnumberid, sundry, fiscaltypecode, fiscalcode, taxregistrationnumber, countrycode, addressline1, addressline2, addressline3, addressline4, addressline5, postalcode, town, district, transportzonecode, addressphonenumber, addressfaxnumber, person, roleinthecompany, phonenumber, faxnumber, emailaddress, bookline01, bookline02, bookline03, bookline04, bookline05, creationdatetime2, creationdatetimeutc2, creationuser2, lastupdatedatetime2, lastupdatedatetimeutc2, lastupdateuser2, bpabsuniqueid, documenttypeformail);
    }
}
