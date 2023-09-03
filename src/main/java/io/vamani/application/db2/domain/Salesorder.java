package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "salesorder")
public class Salesorder {
    @EmbeddedId
    private SalesorderId id;
    private String divisioncode;
    private String alloweddivisions;
    private String templatecode;
    private String ordertype;
    private String documenttypetype;
    private Short intercompanyrequired;
    private String intercompanycode;
    private String countercompanycode;
    private Date orderdate;
    private String ordprncustomersuppliercode;
    private String orderpartnerbrandcode;
    private String lifecyclecode;
    private String deliverypointtype;
    private Long deliverypointuniqueid;
    private String deliverypointcode;
    private String description;
    private Date initialdate;
    private Date finaldate;
    private String externalreference;
    private Date externalreferencedate;
    private String internalreference;
    private Date internalreferencedate;
    private String statisticalgroupcompanycode;
    private String statisticalgroupcode;
    private String collectiongroupcompanycode;
    private String collectiongroupcode;
    private String projectcode;
    private String languagecode;
    private String marketcode;
    private Integer groupordersonshipping;
    private String termsofdeliverycompanycode;
    private String termsofdeliverycode;
    private String termsofshippingcompanycode;
    private String termsofshippingcode;
    private String transportreasoncompanycode;
    private String transportreasoncode;
    private String areacompanycode;
    private String areacode;
    private String firstcarriertype;
    private String firstcarriercode;
    private String secondcarriertype;
    private String secondcarriercode;
    private String thirdcarriertype;
    private String thirdcarriercode;
    private String warehousecompanycode;
    private String warehousecode;
    private Date requiredduedate;
    private Date confirmedduedate;
    private String releasetype;
    private Integer releasepriority;
    private String fncordprncustomersuppliercode;
    private String ordercategorycode;
    private String currencycode;
    private BigDecimal entryexchangerate;
    private Date conditionretrievingdate;
    private String priceanddiscountdocumenttype;
    private String paymentmethodcompanycode;
    private String paymentmethodcode;
    private String pricelistcode;
    private String discountcategorycode;
    private String taxcode;
    private BigDecimal onordertotalamount;
    private Long paymentcustomeruniqueid;
    private String paymentcustomercode;
    private Short minamountachievementinvoice;
    private String bankbankcountrycode;
    private String bankcode;
    private String bankbranchcode;
    private String bankexternalcode;
    private Integer orderpartnerbankidentifier;
    private String companybankbankcountrycode;
    private String companybankcode;
    private String companybankbranchcode;
    private String companybankexternalcode;
    private Integer companybankididentifier;
    private Short thirdpartybilling;
    private String commissiondocumenttype;
    private String agent1Code;
    private Integer commissionliquidationtype1;
    private String agentcreationtype1;
    private String agent2Code;
    private Integer commissionliquidationtype2;
    private String agentcreationtype2;
    private String agent3Code;
    private Integer commissionliquidationtype3;
    private String agentcreationtype3;
    private String agent4Code;
    private Integer commissionliquidationtype4;
    private String agentcreationtype4;
    private String agent5Code;
    private Integer commissionliquidationtype5;
    private String agentcreationtype5;
    private String currentstatus;
    private Short linesuspended;
    private String progressstatus;
    private String ordersource;
    private String previoustemplatecode;
    private String previouscountercompanycode;
    private String previouscountercode;
    private String previouscode;
    private Short printedconfirmation;
    private Short linesunmatchedwithbox;
    private String termsoflogcode;
    private String logreasoncode;
    private Timestamp creationdatetime;
    private String creationuser;
    private Timestamp lastupdatedatetime;
    private String lastupdateuser;
    private Long absuniqueid;
    private String purordprncustomersuppliertype;
    private String purordprncustomersuppliercode;
    private Short keepentryexchangerate;
    private Timestamp creationdatetimeutc;
    private Timestamp lastupdatedatetimeutc;

    public SalesorderId getId() {
        return id;
    }

    public void setId(SalesorderId id) {
        this.id = id;
    }

    @Basic
    @Column(name = "DIVISIONCODE", nullable = true, length = 3)
    public String getDivisioncode() {
        return divisioncode;
    }

    public void setDivisioncode(String divisioncode) {
        this.divisioncode = divisioncode;
    }

    @Basic
    @Column(name = "ALLOWEDDIVISIONS", nullable = true, length = 90)
    public String getAlloweddivisions() {
        return alloweddivisions;
    }

    public void setAlloweddivisions(String alloweddivisions) {
        this.alloweddivisions = alloweddivisions;
    }

    @Basic
    @Column(name = "TEMPLATECODE", nullable = true, length = 3)
    public String getTemplatecode() {
        return templatecode;
    }

    public void setTemplatecode(String templatecode) {
        this.templatecode = templatecode;
    }

    @Basic
    @Column(name = "ORDERTYPE", nullable = false, length = 1)
    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    @Basic
    @Column(name = "DOCUMENTTYPETYPE", nullable = true, length = 3)
    public String getDocumenttypetype() {
        return documenttypetype;
    }

    public void setDocumenttypetype(String documenttypetype) {
        this.documenttypetype = documenttypetype;
    }

    @Basic
    @Column(name = "INTERCOMPANYREQUIRED", nullable = false)
    public Short getIntercompanyrequired() {
        return intercompanyrequired;
    }

    public void setIntercompanyrequired(Short intercompanyrequired) {
        this.intercompanyrequired = intercompanyrequired;
    }

    @Basic
    @Column(name = "INTERCOMPANYCODE", nullable = true, length = 3)
    public String getIntercompanycode() {
        return intercompanycode;
    }

    public void setIntercompanycode(String intercompanycode) {
        this.intercompanycode = intercompanycode;
    }

    @Basic
    @Column(name = "COUNTERCOMPANYCODE", nullable = false, length = 3)
    public String getCountercompanycode() {
        return countercompanycode;
    }

    public void setCountercompanycode(String countercompanycode) {
        this.countercompanycode = countercompanycode;
    }

    @Basic
    @Column(name = "ORDERDATE", nullable = false)
    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    @Basic
    @Column(name = "ORDPRNCUSTOMERSUPPLIERCODE", nullable = true, length = 8)
    public String getOrdprncustomersuppliercode() {
        return ordprncustomersuppliercode;
    }

    public void setOrdprncustomersuppliercode(String ordprncustomersuppliercode) {
        this.ordprncustomersuppliercode = ordprncustomersuppliercode;
    }

    @Basic
    @Column(name = "ORDERPARTNERBRANDCODE", nullable = true, length = 8)
    public String getOrderpartnerbrandcode() {
        return orderpartnerbrandcode;
    }

    public void setOrderpartnerbrandcode(String orderpartnerbrandcode) {
        this.orderpartnerbrandcode = orderpartnerbrandcode;
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
    @Column(name = "DELIVERYPOINTTYPE", nullable = true, length = 2)
    public String getDeliverypointtype() {
        return deliverypointtype;
    }

    public void setDeliverypointtype(String deliverypointtype) {
        this.deliverypointtype = deliverypointtype;
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
    @Column(name = "DESCRIPTION", nullable = true, length = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "INITIALDATE", nullable = true)
    public Date getInitialdate() {
        return initialdate;
    }

    public void setInitialdate(Date initialdate) {
        this.initialdate = initialdate;
    }

    @Basic
    @Column(name = "FINALDATE", nullable = true)
    public Date getFinaldate() {
        return finaldate;
    }

    public void setFinaldate(Date finaldate) {
        this.finaldate = finaldate;
    }

    @Basic
    @Column(name = "EXTERNALREFERENCE", nullable = true, length = 200)
    public String getExternalreference() {
        return externalreference;
    }

    public void setExternalreference(String externalreference) {
        this.externalreference = externalreference;
    }

    @Basic
    @Column(name = "EXTERNALREFERENCEDATE", nullable = true)
    public Date getExternalreferencedate() {
        return externalreferencedate;
    }

    public void setExternalreferencedate(Date externalreferencedate) {
        this.externalreferencedate = externalreferencedate;
    }

    @Basic
    @Column(name = "INTERNALREFERENCE", nullable = true, length = 200)
    public String getInternalreference() {
        return internalreference;
    }

    public void setInternalreference(String internalreference) {
        this.internalreference = internalreference;
    }

    @Basic
    @Column(name = "INTERNALREFERENCEDATE", nullable = true)
    public Date getInternalreferencedate() {
        return internalreferencedate;
    }

    public void setInternalreferencedate(Date internalreferencedate) {
        this.internalreferencedate = internalreferencedate;
    }

    @Basic
    @Column(name = "STATISTICALGROUPCOMPANYCODE", nullable = true, length = 3)
    public String getStatisticalgroupcompanycode() {
        return statisticalgroupcompanycode;
    }

    public void setStatisticalgroupcompanycode(String statisticalgroupcompanycode) {
        this.statisticalgroupcompanycode = statisticalgroupcompanycode;
    }

    @Basic
    @Column(name = "STATISTICALGROUPCODE", nullable = true, length = 6)
    public String getStatisticalgroupcode() {
        return statisticalgroupcode;
    }

    public void setStatisticalgroupcode(String statisticalgroupcode) {
        this.statisticalgroupcode = statisticalgroupcode;
    }

    @Basic
    @Column(name = "COLLECTIONGROUPCOMPANYCODE", nullable = true, length = 3)
    public String getCollectiongroupcompanycode() {
        return collectiongroupcompanycode;
    }

    public void setCollectiongroupcompanycode(String collectiongroupcompanycode) {
        this.collectiongroupcompanycode = collectiongroupcompanycode;
    }

    @Basic
    @Column(name = "COLLECTIONGROUPCODE", nullable = true, length = 6)
    public String getCollectiongroupcode() {
        return collectiongroupcode;
    }

    public void setCollectiongroupcode(String collectiongroupcode) {
        this.collectiongroupcode = collectiongroupcode;
    }

    @Basic
    @Column(name = "PROJECTCODE", nullable = true, length = 20)
    public String getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
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
    @Column(name = "MARKETCODE", nullable = true, length = 10)
    public String getMarketcode() {
        return marketcode;
    }

    public void setMarketcode(String marketcode) {
        this.marketcode = marketcode;
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
    @Column(name = "TERMSOFDELIVERYCOMPANYCODE", nullable = true, length = 3)
    public String getTermsofdeliverycompanycode() {
        return termsofdeliverycompanycode;
    }

    public void setTermsofdeliverycompanycode(String termsofdeliverycompanycode) {
        this.termsofdeliverycompanycode = termsofdeliverycompanycode;
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
    @Column(name = "TERMSOFSHIPPINGCOMPANYCODE", nullable = true, length = 3)
    public String getTermsofshippingcompanycode() {
        return termsofshippingcompanycode;
    }

    public void setTermsofshippingcompanycode(String termsofshippingcompanycode) {
        this.termsofshippingcompanycode = termsofshippingcompanycode;
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
    @Column(name = "TRANSPORTREASONCOMPANYCODE", nullable = true, length = 3)
    public String getTransportreasoncompanycode() {
        return transportreasoncompanycode;
    }

    public void setTransportreasoncompanycode(String transportreasoncompanycode) {
        this.transportreasoncompanycode = transportreasoncompanycode;
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
    @Column(name = "AREACOMPANYCODE", nullable = true, length = 3)
    public String getAreacompanycode() {
        return areacompanycode;
    }

    public void setAreacompanycode(String areacompanycode) {
        this.areacompanycode = areacompanycode;
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
    @Column(name = "WAREHOUSECOMPANYCODE", nullable = true, length = 3)
    public String getWarehousecompanycode() {
        return warehousecompanycode;
    }

    public void setWarehousecompanycode(String warehousecompanycode) {
        this.warehousecompanycode = warehousecompanycode;
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
    @Column(name = "REQUIREDDUEDATE", nullable = true)
    public Date getRequiredduedate() {
        return requiredduedate;
    }

    public void setRequiredduedate(Date requiredduedate) {
        this.requiredduedate = requiredduedate;
    }

    @Basic
    @Column(name = "CONFIRMEDDUEDATE", nullable = true)
    public Date getConfirmedduedate() {
        return confirmedduedate;
    }

    public void setConfirmedduedate(Date confirmedduedate) {
        this.confirmedduedate = confirmedduedate;
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
    @Column(name = "FNCORDPRNCUSTOMERSUPPLIERCODE", nullable = true, length = 8)
    public String getFncordprncustomersuppliercode() {
        return fncordprncustomersuppliercode;
    }

    public void setFncordprncustomersuppliercode(String fncordprncustomersuppliercode) {
        this.fncordprncustomersuppliercode = fncordprncustomersuppliercode;
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
    @Column(name = "CURRENCYCODE", nullable = true, length = 4)
    public String getCurrencycode() {
        return currencycode;
    }

    public void setCurrencycode(String currencycode) {
        this.currencycode = currencycode;
    }

    @Basic
    @Column(name = "ENTRYEXCHANGERATE", nullable = true, precision = 15)
    public BigDecimal getEntryexchangerate() {
        return entryexchangerate;
    }

    public void setEntryexchangerate(BigDecimal entryexchangerate) {
        this.entryexchangerate = entryexchangerate;
    }

    @Basic
    @Column(name = "CONDITIONRETRIEVINGDATE", nullable = true)
    public Date getConditionretrievingdate() {
        return conditionretrievingdate;
    }

    public void setConditionretrievingdate(Date conditionretrievingdate) {
        this.conditionretrievingdate = conditionretrievingdate;
    }

    @Basic
    @Column(name = "PRICEANDDISCOUNTDOCUMENTTYPE", nullable = true, length = 3)
    public String getPriceanddiscountdocumenttype() {
        return priceanddiscountdocumenttype;
    }

    public void setPriceanddiscountdocumenttype(String priceanddiscountdocumenttype) {
        this.priceanddiscountdocumenttype = priceanddiscountdocumenttype;
    }

    @Basic
    @Column(name = "PAYMENTMETHODCOMPANYCODE", nullable = true, length = 3)
    public String getPaymentmethodcompanycode() {
        return paymentmethodcompanycode;
    }

    public void setPaymentmethodcompanycode(String paymentmethodcompanycode) {
        this.paymentmethodcompanycode = paymentmethodcompanycode;
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
    @Column(name = "PRICELISTCODE", nullable = true, length = 8)
    public String getPricelistcode() {
        return pricelistcode;
    }

    public void setPricelistcode(String pricelistcode) {
        this.pricelistcode = pricelistcode;
    }

    @Basic
    @Column(name = "DISCOUNTCATEGORYCODE", nullable = true, length = 3)
    public String getDiscountcategorycode() {
        return discountcategorycode;
    }

    public void setDiscountcategorycode(String discountcategorycode) {
        this.discountcategorycode = discountcategorycode;
    }

    @Basic
    @Column(name = "TAXCODE", nullable = true, length = 3)
    public String getTaxcode() {
        return taxcode;
    }

    public void setTaxcode(String taxcode) {
        this.taxcode = taxcode;
    }

    @Basic
    @Column(name = "ONORDERTOTALAMOUNT", nullable = true, precision = 5)
    public BigDecimal getOnordertotalamount() {
        return onordertotalamount;
    }

    public void setOnordertotalamount(BigDecimal onordertotalamount) {
        this.onordertotalamount = onordertotalamount;
    }

    @Basic
    @Column(name = "PAYMENTCUSTOMERUNIQUEID", nullable = true)
    public Long getPaymentcustomeruniqueid() {
        return paymentcustomeruniqueid;
    }

    public void setPaymentcustomeruniqueid(Long paymentcustomeruniqueid) {
        this.paymentcustomeruniqueid = paymentcustomeruniqueid;
    }

    @Basic
    @Column(name = "PAYMENTCUSTOMERCODE", nullable = true, length = 8)
    public String getPaymentcustomercode() {
        return paymentcustomercode;
    }

    public void setPaymentcustomercode(String paymentcustomercode) {
        this.paymentcustomercode = paymentcustomercode;
    }

    @Basic
    @Column(name = "MINAMOUNTACHIEVEMENTINVOICE", nullable = false)
    public Short getMinamountachievementinvoice() {
        return minamountachievementinvoice;
    }

    public void setMinamountachievementinvoice(Short minamountachievementinvoice) {
        this.minamountachievementinvoice = minamountachievementinvoice;
    }

    @Basic
    @Column(name = "BANKBANKCOUNTRYCODE", nullable = true, length = 3)
    public String getBankbankcountrycode() {
        return bankbankcountrycode;
    }

    public void setBankbankcountrycode(String bankbankcountrycode) {
        this.bankbankcountrycode = bankbankcountrycode;
    }

    @Basic
    @Column(name = "BANKCODE", nullable = true, length = 15)
    public String getBankcode() {
        return bankcode;
    }

    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }

    @Basic
    @Column(name = "BANKBRANCHCODE", nullable = true, length = 6)
    public String getBankbranchcode() {
        return bankbranchcode;
    }

    public void setBankbranchcode(String bankbranchcode) {
        this.bankbranchcode = bankbranchcode;
    }

    @Basic
    @Column(name = "BANKEXTERNALCODE", nullable = true, length = 15)
    public String getBankexternalcode() {
        return bankexternalcode;
    }

    public void setBankexternalcode(String bankexternalcode) {
        this.bankexternalcode = bankexternalcode;
    }

    @Basic
    @Column(name = "ORDERPARTNERBANKIDENTIFIER", nullable = true, precision = 0)
    public Integer getOrderpartnerbankidentifier() {
        return orderpartnerbankidentifier;
    }

    public void setOrderpartnerbankidentifier(Integer orderpartnerbankidentifier) {
        this.orderpartnerbankidentifier = orderpartnerbankidentifier;
    }

    @Basic
    @Column(name = "COMPANYBANKBANKCOUNTRYCODE", nullable = true, length = 3)
    public String getCompanybankbankcountrycode() {
        return companybankbankcountrycode;
    }

    public void setCompanybankbankcountrycode(String companybankbankcountrycode) {
        this.companybankbankcountrycode = companybankbankcountrycode;
    }

    @Basic
    @Column(name = "COMPANYBANKCODE", nullable = true, length = 15)
    public String getCompanybankcode() {
        return companybankcode;
    }

    public void setCompanybankcode(String companybankcode) {
        this.companybankcode = companybankcode;
    }

    @Basic
    @Column(name = "COMPANYBANKBRANCHCODE", nullable = true, length = 6)
    public String getCompanybankbranchcode() {
        return companybankbranchcode;
    }

    public void setCompanybankbranchcode(String companybankbranchcode) {
        this.companybankbranchcode = companybankbranchcode;
    }

    @Basic
    @Column(name = "COMPANYBANKEXTERNALCODE", nullable = true, length = 15)
    public String getCompanybankexternalcode() {
        return companybankexternalcode;
    }

    public void setCompanybankexternalcode(String companybankexternalcode) {
        this.companybankexternalcode = companybankexternalcode;
    }

    @Basic
    @Column(name = "COMPANYBANKIDIDENTIFIER", nullable = true, precision = 0)
    public Integer getCompanybankididentifier() {
        return companybankididentifier;
    }

    public void setCompanybankididentifier(Integer companybankididentifier) {
        this.companybankididentifier = companybankididentifier;
    }

    @Basic
    @Column(name = "THIRDPARTYBILLING", nullable = false)
    public Short getThirdpartybilling() {
        return thirdpartybilling;
    }

    public void setThirdpartybilling(Short thirdpartybilling) {
        this.thirdpartybilling = thirdpartybilling;
    }

    @Basic
    @Column(name = "COMMISSIONDOCUMENTTYPE", nullable = true, length = 3)
    public String getCommissiondocumenttype() {
        return commissiondocumenttype;
    }

    public void setCommissiondocumenttype(String commissiondocumenttype) {
        this.commissiondocumenttype = commissiondocumenttype;
    }

    @Basic
    @Column(name = "AGENT1CODE", nullable = true, length = 3)
    public String getAgent1Code() {
        return agent1Code;
    }

    public void setAgent1Code(String agent1Code) {
        this.agent1Code = agent1Code;
    }

    @Basic
    @Column(name = "COMMISSIONLIQUIDATIONTYPE1", nullable = false)
    public Integer getCommissionliquidationtype1() {
        return commissionliquidationtype1;
    }

    public void setCommissionliquidationtype1(Integer commissionliquidationtype1) {
        this.commissionliquidationtype1 = commissionliquidationtype1;
    }

    @Basic
    @Column(name = "AGENTCREATIONTYPE1", nullable = false, length = 1)
    public String getAgentcreationtype1() {
        return agentcreationtype1;
    }

    public void setAgentcreationtype1(String agentcreationtype1) {
        this.agentcreationtype1 = agentcreationtype1;
    }

    @Basic
    @Column(name = "AGENT2CODE", nullable = true, length = 3)
    public String getAgent2Code() {
        return agent2Code;
    }

    public void setAgent2Code(String agent2Code) {
        this.agent2Code = agent2Code;
    }

    @Basic
    @Column(name = "COMMISSIONLIQUIDATIONTYPE2", nullable = false)
    public Integer getCommissionliquidationtype2() {
        return commissionliquidationtype2;
    }

    public void setCommissionliquidationtype2(Integer commissionliquidationtype2) {
        this.commissionliquidationtype2 = commissionliquidationtype2;
    }

    @Basic
    @Column(name = "AGENTCREATIONTYPE2", nullable = false, length = 1)
    public String getAgentcreationtype2() {
        return agentcreationtype2;
    }

    public void setAgentcreationtype2(String agentcreationtype2) {
        this.agentcreationtype2 = agentcreationtype2;
    }

    @Basic
    @Column(name = "AGENT3CODE", nullable = true, length = 3)
    public String getAgent3Code() {
        return agent3Code;
    }

    public void setAgent3Code(String agent3Code) {
        this.agent3Code = agent3Code;
    }

    @Basic
    @Column(name = "COMMISSIONLIQUIDATIONTYPE3", nullable = false)
    public Integer getCommissionliquidationtype3() {
        return commissionliquidationtype3;
    }

    public void setCommissionliquidationtype3(Integer commissionliquidationtype3) {
        this.commissionliquidationtype3 = commissionliquidationtype3;
    }

    @Basic
    @Column(name = "AGENTCREATIONTYPE3", nullable = false, length = 1)
    public String getAgentcreationtype3() {
        return agentcreationtype3;
    }

    public void setAgentcreationtype3(String agentcreationtype3) {
        this.agentcreationtype3 = agentcreationtype3;
    }

    @Basic
    @Column(name = "AGENT4CODE", nullable = true, length = 3)
    public String getAgent4Code() {
        return agent4Code;
    }

    public void setAgent4Code(String agent4Code) {
        this.agent4Code = agent4Code;
    }

    @Basic
    @Column(name = "COMMISSIONLIQUIDATIONTYPE4", nullable = false)
    public Integer getCommissionliquidationtype4() {
        return commissionliquidationtype4;
    }

    public void setCommissionliquidationtype4(Integer commissionliquidationtype4) {
        this.commissionliquidationtype4 = commissionliquidationtype4;
    }

    @Basic
    @Column(name = "AGENTCREATIONTYPE4", nullable = false, length = 1)
    public String getAgentcreationtype4() {
        return agentcreationtype4;
    }

    public void setAgentcreationtype4(String agentcreationtype4) {
        this.agentcreationtype4 = agentcreationtype4;
    }

    @Basic
    @Column(name = "AGENT5CODE", nullable = true, length = 3)
    public String getAgent5Code() {
        return agent5Code;
    }

    public void setAgent5Code(String agent5Code) {
        this.agent5Code = agent5Code;
    }

    @Basic
    @Column(name = "COMMISSIONLIQUIDATIONTYPE5", nullable = false)
    public Integer getCommissionliquidationtype5() {
        return commissionliquidationtype5;
    }

    public void setCommissionliquidationtype5(Integer commissionliquidationtype5) {
        this.commissionliquidationtype5 = commissionliquidationtype5;
    }

    @Basic
    @Column(name = "AGENTCREATIONTYPE5", nullable = false, length = 1)
    public String getAgentcreationtype5() {
        return agentcreationtype5;
    }

    public void setAgentcreationtype5(String agentcreationtype5) {
        this.agentcreationtype5 = agentcreationtype5;
    }

    @Basic
    @Column(name = "CURRENTSTATUS", nullable = false, length = 2)
    public String getCurrentstatus() {
        return currentstatus;
    }

    public void setCurrentstatus(String currentstatus) {
        this.currentstatus = currentstatus;
    }

    @Basic
    @Column(name = "LINESUSPENDED", nullable = false)
    public Short getLinesuspended() {
        return linesuspended;
    }

    public void setLinesuspended(Short linesuspended) {
        this.linesuspended = linesuspended;
    }

    @Basic
    @Column(name = "PROGRESSSTATUS", nullable = false, length = 2)
    public String getProgressstatus() {
        return progressstatus;
    }

    public void setProgressstatus(String progressstatus) {
        this.progressstatus = progressstatus;
    }

    @Basic
    @Column(name = "ORDERSOURCE", nullable = false, length = 2)
    public String getOrdersource() {
        return ordersource;
    }

    public void setOrdersource(String ordersource) {
        this.ordersource = ordersource;
    }

    @Basic
    @Column(name = "PREVIOUSTEMPLATECODE", nullable = true, length = 3)
    public String getPrevioustemplatecode() {
        return previoustemplatecode;
    }

    public void setPrevioustemplatecode(String previoustemplatecode) {
        this.previoustemplatecode = previoustemplatecode;
    }

    @Basic
    @Column(name = "PREVIOUSCOUNTERCOMPANYCODE", nullable = true, length = 3)
    public String getPreviouscountercompanycode() {
        return previouscountercompanycode;
    }

    public void setPreviouscountercompanycode(String previouscountercompanycode) {
        this.previouscountercompanycode = previouscountercompanycode;
    }

    @Basic
    @Column(name = "PREVIOUSCOUNTERCODE", nullable = true, length = 8)
    public String getPreviouscountercode() {
        return previouscountercode;
    }

    public void setPreviouscountercode(String previouscountercode) {
        this.previouscountercode = previouscountercode;
    }

    @Basic
    @Column(name = "PREVIOUSCODE", nullable = true, length = 15)
    public String getPreviouscode() {
        return previouscode;
    }

    public void setPreviouscode(String previouscode) {
        this.previouscode = previouscode;
    }

    @Basic
    @Column(name = "PRINTEDCONFIRMATION", nullable = false)
    public Short getPrintedconfirmation() {
        return printedconfirmation;
    }

    public void setPrintedconfirmation(Short printedconfirmation) {
        this.printedconfirmation = printedconfirmation;
    }

    @Basic
    @Column(name = "LINESUNMATCHEDWITHBOX", nullable = false)
    public Short getLinesunmatchedwithbox() {
        return linesunmatchedwithbox;
    }

    public void setLinesunmatchedwithbox(Short linesunmatchedwithbox) {
        this.linesunmatchedwithbox = linesunmatchedwithbox;
    }

    @Basic
    @Column(name = "TERMSOFLOGCODE", nullable = true, length = 2)
    public String getTermsoflogcode() {
        return termsoflogcode;
    }

    public void setTermsoflogcode(String termsoflogcode) {
        this.termsoflogcode = termsoflogcode;
    }

    @Basic
    @Column(name = "LOGREASONCODE", nullable = true, length = 2)
    public String getLogreasoncode() {
        return logreasoncode;
    }

    public void setLogreasoncode(String logreasoncode) {
        this.logreasoncode = logreasoncode;
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
    @Column(name = "PURORDPRNCUSTOMERSUPPLIERTYPE", nullable = true, length = 1)
    public String getPurordprncustomersuppliertype() {
        return purordprncustomersuppliertype;
    }

    public void setPurordprncustomersuppliertype(String purordprncustomersuppliertype) {
        this.purordprncustomersuppliertype = purordprncustomersuppliertype;
    }

    @Basic
    @Column(name = "PURORDPRNCUSTOMERSUPPLIERCODE", nullable = true, length = 8)
    public String getPurordprncustomersuppliercode() {
        return purordprncustomersuppliercode;
    }

    public void setPurordprncustomersuppliercode(String purordprncustomersuppliercode) {
        this.purordprncustomersuppliercode = purordprncustomersuppliercode;
    }

    @Basic
    @Column(name = "KEEPENTRYEXCHANGERATE", nullable = false)
    public Short getKeepentryexchangerate() {
        return keepentryexchangerate;
    }

    public void setKeepentryexchangerate(Short keepentryexchangerate) {
        this.keepentryexchangerate = keepentryexchangerate;
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
    @Column(name = "LASTUPDATEDATETIMEUTC", nullable = true)
    public Timestamp getLastupdatedatetimeutc() {
        return lastupdatedatetimeutc;
    }

    public void setLastupdatedatetimeutc(Timestamp lastupdatedatetimeutc) {
        this.lastupdatedatetimeutc = lastupdatedatetimeutc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salesorder that = (Salesorder) o;
        return Objects.equals(id, that.id) && Objects.equals(divisioncode, that.divisioncode) && Objects.equals(alloweddivisions, that.alloweddivisions) && Objects.equals(templatecode, that.templatecode) && Objects.equals(ordertype, that.ordertype) && Objects.equals(documenttypetype, that.documenttypetype) && Objects.equals(intercompanyrequired, that.intercompanyrequired) && Objects.equals(intercompanycode, that.intercompanycode) && Objects.equals(countercompanycode, that.countercompanycode) && Objects.equals(orderdate, that.orderdate) && Objects.equals(ordprncustomersuppliercode, that.ordprncustomersuppliercode) && Objects.equals(orderpartnerbrandcode, that.orderpartnerbrandcode) && Objects.equals(lifecyclecode, that.lifecyclecode) && Objects.equals(deliverypointtype, that.deliverypointtype) && Objects.equals(deliverypointuniqueid, that.deliverypointuniqueid) && Objects.equals(deliverypointcode, that.deliverypointcode) && Objects.equals(description, that.description) && Objects.equals(initialdate, that.initialdate) && Objects.equals(finaldate, that.finaldate) && Objects.equals(externalreference, that.externalreference) && Objects.equals(externalreferencedate, that.externalreferencedate) && Objects.equals(internalreference, that.internalreference) && Objects.equals(internalreferencedate, that.internalreferencedate) && Objects.equals(statisticalgroupcompanycode, that.statisticalgroupcompanycode) && Objects.equals(statisticalgroupcode, that.statisticalgroupcode) && Objects.equals(collectiongroupcompanycode, that.collectiongroupcompanycode) && Objects.equals(collectiongroupcode, that.collectiongroupcode) && Objects.equals(projectcode, that.projectcode) && Objects.equals(languagecode, that.languagecode) && Objects.equals(marketcode, that.marketcode) && Objects.equals(groupordersonshipping, that.groupordersonshipping) && Objects.equals(termsofdeliverycompanycode, that.termsofdeliverycompanycode) && Objects.equals(termsofdeliverycode, that.termsofdeliverycode) && Objects.equals(termsofshippingcompanycode, that.termsofshippingcompanycode) && Objects.equals(termsofshippingcode, that.termsofshippingcode) && Objects.equals(transportreasoncompanycode, that.transportreasoncompanycode) && Objects.equals(transportreasoncode, that.transportreasoncode) && Objects.equals(areacompanycode, that.areacompanycode) && Objects.equals(areacode, that.areacode) && Objects.equals(firstcarriertype, that.firstcarriertype) && Objects.equals(firstcarriercode, that.firstcarriercode) && Objects.equals(secondcarriertype, that.secondcarriertype) && Objects.equals(secondcarriercode, that.secondcarriercode) && Objects.equals(thirdcarriertype, that.thirdcarriertype) && Objects.equals(thirdcarriercode, that.thirdcarriercode) && Objects.equals(warehousecompanycode, that.warehousecompanycode) && Objects.equals(warehousecode, that.warehousecode) && Objects.equals(requiredduedate, that.requiredduedate) && Objects.equals(confirmedduedate, that.confirmedduedate) && Objects.equals(releasetype, that.releasetype) && Objects.equals(releasepriority, that.releasepriority) && Objects.equals(fncordprncustomersuppliercode, that.fncordprncustomersuppliercode) && Objects.equals(ordercategorycode, that.ordercategorycode) && Objects.equals(currencycode, that.currencycode) && Objects.equals(entryexchangerate, that.entryexchangerate) && Objects.equals(conditionretrievingdate, that.conditionretrievingdate) && Objects.equals(priceanddiscountdocumenttype, that.priceanddiscountdocumenttype) && Objects.equals(paymentmethodcompanycode, that.paymentmethodcompanycode) && Objects.equals(paymentmethodcode, that.paymentmethodcode) && Objects.equals(pricelistcode, that.pricelistcode) && Objects.equals(discountcategorycode, that.discountcategorycode) && Objects.equals(taxcode, that.taxcode) && Objects.equals(onordertotalamount, that.onordertotalamount) && Objects.equals(paymentcustomeruniqueid, that.paymentcustomeruniqueid) && Objects.equals(paymentcustomercode, that.paymentcustomercode) && Objects.equals(minamountachievementinvoice, that.minamountachievementinvoice) && Objects.equals(bankbankcountrycode, that.bankbankcountrycode) && Objects.equals(bankcode, that.bankcode) && Objects.equals(bankbranchcode, that.bankbranchcode) && Objects.equals(bankexternalcode, that.bankexternalcode) && Objects.equals(orderpartnerbankidentifier, that.orderpartnerbankidentifier) && Objects.equals(companybankbankcountrycode, that.companybankbankcountrycode) && Objects.equals(companybankcode, that.companybankcode) && Objects.equals(companybankbranchcode, that.companybankbranchcode) && Objects.equals(companybankexternalcode, that.companybankexternalcode) && Objects.equals(companybankididentifier, that.companybankididentifier) && Objects.equals(thirdpartybilling, that.thirdpartybilling) && Objects.equals(commissiondocumenttype, that.commissiondocumenttype) && Objects.equals(agent1Code, that.agent1Code) && Objects.equals(commissionliquidationtype1, that.commissionliquidationtype1) && Objects.equals(agentcreationtype1, that.agentcreationtype1) && Objects.equals(agent2Code, that.agent2Code) && Objects.equals(commissionliquidationtype2, that.commissionliquidationtype2) && Objects.equals(agentcreationtype2, that.agentcreationtype2) && Objects.equals(agent3Code, that.agent3Code) && Objects.equals(commissionliquidationtype3, that.commissionliquidationtype3) && Objects.equals(agentcreationtype3, that.agentcreationtype3) && Objects.equals(agent4Code, that.agent4Code) && Objects.equals(commissionliquidationtype4, that.commissionliquidationtype4) && Objects.equals(agentcreationtype4, that.agentcreationtype4) && Objects.equals(agent5Code, that.agent5Code) && Objects.equals(commissionliquidationtype5, that.commissionliquidationtype5) && Objects.equals(agentcreationtype5, that.agentcreationtype5) && Objects.equals(currentstatus, that.currentstatus) && Objects.equals(linesuspended, that.linesuspended) && Objects.equals(progressstatus, that.progressstatus) && Objects.equals(ordersource, that.ordersource) && Objects.equals(previoustemplatecode, that.previoustemplatecode) && Objects.equals(previouscountercompanycode, that.previouscountercompanycode) && Objects.equals(previouscountercode, that.previouscountercode) && Objects.equals(previouscode, that.previouscode) && Objects.equals(printedconfirmation, that.printedconfirmation) && Objects.equals(linesunmatchedwithbox, that.linesunmatchedwithbox) && Objects.equals(termsoflogcode, that.termsoflogcode) && Objects.equals(logreasoncode, that.logreasoncode) && Objects.equals(creationdatetime, that.creationdatetime) && Objects.equals(creationuser, that.creationuser) && Objects.equals(lastupdatedatetime, that.lastupdatedatetime) && Objects.equals(lastupdateuser, that.lastupdateuser) && Objects.equals(absuniqueid, that.absuniqueid) && Objects.equals(purordprncustomersuppliertype, that.purordprncustomersuppliertype) && Objects.equals(purordprncustomersuppliercode, that.purordprncustomersuppliercode) && Objects.equals(keepentryexchangerate, that.keepentryexchangerate) && Objects.equals(creationdatetimeutc, that.creationdatetimeutc) && Objects.equals(lastupdatedatetimeutc, that.lastupdatedatetimeutc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, divisioncode, alloweddivisions, templatecode, ordertype, documenttypetype, intercompanyrequired, intercompanycode, countercompanycode, orderdate, ordprncustomersuppliercode, orderpartnerbrandcode, lifecyclecode, deliverypointtype, deliverypointuniqueid, deliverypointcode, description, initialdate, finaldate, externalreference, externalreferencedate, internalreference, internalreferencedate, statisticalgroupcompanycode, statisticalgroupcode, collectiongroupcompanycode, collectiongroupcode, projectcode, languagecode, marketcode, groupordersonshipping, termsofdeliverycompanycode, termsofdeliverycode, termsofshippingcompanycode, termsofshippingcode, transportreasoncompanycode, transportreasoncode, areacompanycode, areacode, firstcarriertype, firstcarriercode, secondcarriertype, secondcarriercode, thirdcarriertype, thirdcarriercode, warehousecompanycode, warehousecode, requiredduedate, confirmedduedate, releasetype, releasepriority, fncordprncustomersuppliercode, ordercategorycode, currencycode, entryexchangerate, conditionretrievingdate, priceanddiscountdocumenttype, paymentmethodcompanycode, paymentmethodcode, pricelistcode, discountcategorycode, taxcode, onordertotalamount, paymentcustomeruniqueid, paymentcustomercode, minamountachievementinvoice, bankbankcountrycode, bankcode, bankbranchcode, bankexternalcode, orderpartnerbankidentifier, companybankbankcountrycode, companybankcode, companybankbranchcode, companybankexternalcode, companybankididentifier, thirdpartybilling, commissiondocumenttype, agent1Code, commissionliquidationtype1, agentcreationtype1, agent2Code, commissionliquidationtype2, agentcreationtype2, agent3Code, commissionliquidationtype3, agentcreationtype3, agent4Code, commissionliquidationtype4, agentcreationtype4, agent5Code, commissionliquidationtype5, agentcreationtype5, currentstatus, linesuspended, progressstatus, ordersource, previoustemplatecode, previouscountercompanycode, previouscountercode, previouscode, printedconfirmation, linesunmatchedwithbox, termsoflogcode, logreasoncode, creationdatetime, creationuser, lastupdatedatetime, lastupdateuser, absuniqueid, purordprncustomersuppliertype, purordprncustomersuppliercode, keepentryexchangerate, creationdatetimeutc, lastupdatedatetimeutc);
    }
}
