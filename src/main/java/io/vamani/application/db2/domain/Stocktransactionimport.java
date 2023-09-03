package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "stocktransactionimport")
public class Stocktransactionimport {
    @EmbeddedId
    private StocktransactionimportId id;
    private Integer importstatus;
    private String nowtrnnumbertransactionnumber;
    private String transactionstatus;
    private String itemtypecompanycode;
    private String itemtypecode;
    private Date transactiondate;
    private Time transactiontime;
    private String detailtype;
    private String templatecompanycode;
    private String templatecode;
    private String autoissuetemplatecompanycode;
    private String autoissuetemplatecode;
    private String stocktransactiontype;
    private String decocompanycode;
    private String decosubcode01;
    private String decosubcode02;
    private String decosubcode03;
    private String decosubcode04;
    private String decosubcode05;
    private String decosubcode06;
    private String decosubcode07;
    private String decosubcode08;
    private String decosubcode09;
    private String decosubcode10;
    private String itemdescription;
    private String logicalwarehousecompanycode;
    private String logicalwarehousecode;
    private String transferallocation;
    private Short issueqtyfrombalance;
    private String userprimaryuomcode;
    private BigDecimal userprimaryquantity;
    private String usersecondaryuomcode;
    private BigDecimal usersecondaryquantity;
    private String userpackaginguomcode;
    private BigDecimal userpackagingquantity;
    private String weightunitofmeasurecode;
    private BigDecimal weightgross;
    private BigDecimal weightnet;
    private BigDecimal weightrealnet;
    private String derivationcode;
    private Integer derivationlinenumber;
    private Integer derivationcomponentlinenumber;
    private String qualitylvlitemtypecompanycode;
    private BigInteger qualitylevelcode;
    private String qualityreasoncompanycode;
    private String qualityreasoncode;
    private Date firstqualitycontroldate;
    private String firstqualitycontrolcounter;
    private String firstqualitycontrolnumber;
    private String physicalwarehousecompanycode;
    private String physicalwarehousecode;
    private String whslocwhszonephywhscmycode;
    private String whslocationwarehousezonecode;
    private String warehouselocationcode;
    private String containercompanycode;
    private String containeritemtypecode;
    private String containersubcode01;
    private String containerelementcompanycode;
    private String containerelementcode;
    private String lotcompanycode;
    private String lotcode;
    private String itemelementcompanycode;
    private String itemelementsubcodekey;
    private String itemelementcode;
    private String projectcode;
    private String costcentercompanycode;
    private String costcentercode;
    private String statisticalgroupcompanycode;
    private String statisticalgroupcode;
    private BigDecimal cost;
    private String currencycode;
    private String customertype;
    private String customercode;
    private String suppliertype;
    private String suppliercode;
    private Date billdate;
    private Short billtype;
    private String billcounter;
    private String billcode;
    private Date internaldocumentdate;
    private Integer internaldocumentnumber;
    private String ordercountercompanycode;
    private String ordercountercode;
    private String ordercode;
    private Integer orderline;
    private Integer ordersubline;
    private Integer ordercomponentline;
    private Integer orderdeliveryline;
    private String productionordercode;
    private String returncode;
    private Integer returnline;
    private String tokencode;
    private String envcodeskipblcexp;
    private Long aduniqueid;
    private Long aduniqueidforautoissue;
    private Timestamp creationdatetime;
    private String creationuser;
    private Timestamp lastupdatedatetime;
    private String lastupdateuser;
    private Long absuniqueid;
    @Transient
    private boolean exist;

    public StocktransactionimportId getId() {
        return id;
    }

    public void setId(StocktransactionimportId id) {
        this.id = id;
    }

    @Basic
    @Column(name = "IMPORTSTATUS", nullable = false)
    public Integer getImportstatus() {
        return importstatus;
    }

    public void setImportstatus(Integer importstatus) {
        this.importstatus = importstatus;
    }

    @Basic
    @Column(name = "NOWTRNNUMBERTRANSACTIONNUMBER", nullable = true, length = 15)
    public String getNowtrnnumbertransactionnumber() {
        return nowtrnnumbertransactionnumber;
    }

    public void setNowtrnnumbertransactionnumber(String nowtrnnumbertransactionnumber) {
        this.nowtrnnumbertransactionnumber = nowtrnnumbertransactionnumber;
    }

    @Basic
    @Column(name = "TRANSACTIONSTATUS", nullable = true, length = 2)
    public String getTransactionstatus() {
        return transactionstatus;
    }

    public void setTransactionstatus(String transactionstatus) {
        this.transactionstatus = transactionstatus;
    }

    @Basic
    @Column(name = "ITEMTYPECOMPANYCODE", nullable = true, length = 3)
    public String getItemtypecompanycode() {
        return itemtypecompanycode;
    }

    public void setItemtypecompanycode(String itemtypecompanycode) {
        this.itemtypecompanycode = itemtypecompanycode;
    }

    @Basic
    @Column(name = "ITEMTYPECODE", nullable = true, length = 3)
    public String getItemtypecode() {
        return itemtypecode;
    }

    public void setItemtypecode(String itemtypecode) {
        this.itemtypecode = itemtypecode;
    }

    @Basic
    @Column(name = "TRANSACTIONDATE", nullable = true)
    public Date getTransactiondate() {
        return transactiondate;
    }

    public void setTransactiondate(Date transactiondate) {
        this.transactiondate = transactiondate;
    }

    @Basic
    @Column(name = "TRANSACTIONTIME", nullable = true)
    public Time getTransactiontime() {
        return transactiontime;
    }

    public void setTransactiontime(Time transactiontime) {
        this.transactiontime = transactiontime;
    }

    @Basic
    @Column(name = "DETAILTYPE", nullable = true, length = 2)
    public String getDetailtype() {
        return detailtype;
    }

    public void setDetailtype(String detailtype) {
        this.detailtype = detailtype;
    }

    @Basic
    @Column(name = "TEMPLATECOMPANYCODE", nullable = true, length = 3)
    public String getTemplatecompanycode() {
        return templatecompanycode;
    }

    public void setTemplatecompanycode(String templatecompanycode) {
        this.templatecompanycode = templatecompanycode;
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
    @Column(name = "AUTOISSUETEMPLATECOMPANYCODE", nullable = true, length = 3)
    public String getAutoissuetemplatecompanycode() {
        return autoissuetemplatecompanycode;
    }

    public void setAutoissuetemplatecompanycode(String autoissuetemplatecompanycode) {
        this.autoissuetemplatecompanycode = autoissuetemplatecompanycode;
    }

    @Basic
    @Column(name = "AUTOISSUETEMPLATECODE", nullable = true, length = 3)
    public String getAutoissuetemplatecode() {
        return autoissuetemplatecode;
    }

    public void setAutoissuetemplatecode(String autoissuetemplatecode) {
        this.autoissuetemplatecode = autoissuetemplatecode;
    }

    @Basic
    @Column(name = "STOCKTRANSACTIONTYPE", nullable = false, length = 2)
    public String getStocktransactiontype() {
        return stocktransactiontype;
    }

    public void setStocktransactiontype(String stocktransactiontype) {
        this.stocktransactiontype = stocktransactiontype;
    }

    @Basic
    @Column(name = "DECOCOMPANYCODE", nullable = true, length = 3)
    public String getDecocompanycode() {
        return decocompanycode;
    }

    public void setDecocompanycode(String decocompanycode) {
        this.decocompanycode = decocompanycode;
    }

    @Basic
    @Column(name = "DECOSUBCODE01", nullable = true, length = 20)
    public String getDecosubcode01() {
        return decosubcode01;
    }

    public void setDecosubcode01(String decosubcode01) {
        this.decosubcode01 = decosubcode01;
    }

    @Basic
    @Column(name = "DECOSUBCODE02", nullable = true, length = 10)
    public String getDecosubcode02() {
        return decosubcode02;
    }

    public void setDecosubcode02(String decosubcode02) {
        this.decosubcode02 = decosubcode02;
    }

    @Basic
    @Column(name = "DECOSUBCODE03", nullable = true, length = 10)
    public String getDecosubcode03() {
        return decosubcode03;
    }

    public void setDecosubcode03(String decosubcode03) {
        this.decosubcode03 = decosubcode03;
    }

    @Basic
    @Column(name = "DECOSUBCODE04", nullable = true, length = 10)
    public String getDecosubcode04() {
        return decosubcode04;
    }

    public void setDecosubcode04(String decosubcode04) {
        this.decosubcode04 = decosubcode04;
    }

    @Basic
    @Column(name = "DECOSUBCODE05", nullable = true, length = 10)
    public String getDecosubcode05() {
        return decosubcode05;
    }

    public void setDecosubcode05(String decosubcode05) {
        this.decosubcode05 = decosubcode05;
    }

    @Basic
    @Column(name = "DECOSUBCODE06", nullable = true, length = 10)
    public String getDecosubcode06() {
        return decosubcode06;
    }

    public void setDecosubcode06(String decosubcode06) {
        this.decosubcode06 = decosubcode06;
    }

    @Basic
    @Column(name = "DECOSUBCODE07", nullable = true, length = 10)
    public String getDecosubcode07() {
        return decosubcode07;
    }

    public void setDecosubcode07(String decosubcode07) {
        this.decosubcode07 = decosubcode07;
    }

    @Basic
    @Column(name = "DECOSUBCODE08", nullable = true, length = 10)
    public String getDecosubcode08() {
        return decosubcode08;
    }

    public void setDecosubcode08(String decosubcode08) {
        this.decosubcode08 = decosubcode08;
    }

    @Basic
    @Column(name = "DECOSUBCODE09", nullable = true, length = 10)
    public String getDecosubcode09() {
        return decosubcode09;
    }

    public void setDecosubcode09(String decosubcode09) {
        this.decosubcode09 = decosubcode09;
    }

    @Basic
    @Column(name = "DECOSUBCODE10", nullable = true, length = 10)
    public String getDecosubcode10() {
        return decosubcode10;
    }

    public void setDecosubcode10(String decosubcode10) {
        this.decosubcode10 = decosubcode10;
    }

    @Basic
    @Column(name = "ITEMDESCRIPTION", nullable = true, length = 100)
    public String getItemdescription() {
        return itemdescription;
    }

    public void setItemdescription(String itemdescription) {
        this.itemdescription = itemdescription;
    }

    @Basic
    @Column(name = "LOGICALWAREHOUSECOMPANYCODE", nullable = true, length = 3)
    public String getLogicalwarehousecompanycode() {
        return logicalwarehousecompanycode;
    }

    public void setLogicalwarehousecompanycode(String logicalwarehousecompanycode) {
        this.logicalwarehousecompanycode = logicalwarehousecompanycode;
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
    @Column(name = "TRANSFERALLOCATION", nullable = true, length = 2)
    public String getTransferallocation() {
        return transferallocation;
    }

    public void setTransferallocation(String transferallocation) {
        this.transferallocation = transferallocation;
    }

    @Basic
    @Column(name = "ISSUEQTYFROMBALANCE", nullable = false)
    public Short getIssueqtyfrombalance() {
        return issueqtyfrombalance;
    }

    public void setIssueqtyfrombalance(Short issueqtyfrombalance) {
        this.issueqtyfrombalance = issueqtyfrombalance;
    }

    @Basic
    @Column(name = "USERPRIMARYUOMCODE", nullable = true, length = 3)
    public String getUserprimaryuomcode() {
        return userprimaryuomcode;
    }

    public void setUserprimaryuomcode(String userprimaryuomcode) {
        this.userprimaryuomcode = userprimaryuomcode;
    }

    @Basic
    @Column(name = "USERPRIMARYQUANTITY", nullable = true, precision = 5)
    public BigDecimal getUserprimaryquantity() {
        return userprimaryquantity;
    }

    public void setUserprimaryquantity(BigDecimal userprimaryquantity) {
        this.userprimaryquantity = userprimaryquantity;
    }

    @Basic
    @Column(name = "USERSECONDARYUOMCODE", nullable = true, length = 3)
    public String getUsersecondaryuomcode() {
        return usersecondaryuomcode;
    }

    public void setUsersecondaryuomcode(String usersecondaryuomcode) {
        this.usersecondaryuomcode = usersecondaryuomcode;
    }

    @Basic
    @Column(name = "USERSECONDARYQUANTITY", nullable = true, precision = 5)
    public BigDecimal getUsersecondaryquantity() {
        return usersecondaryquantity;
    }

    public void setUsersecondaryquantity(BigDecimal usersecondaryquantity) {
        this.usersecondaryquantity = usersecondaryquantity;
    }

    @Basic
    @Column(name = "USERPACKAGINGUOMCODE", nullable = true, length = 3)
    public String getUserpackaginguomcode() {
        return userpackaginguomcode;
    }

    public void setUserpackaginguomcode(String userpackaginguomcode) {
        this.userpackaginguomcode = userpackaginguomcode;
    }

    @Basic
    @Column(name = "USERPACKAGINGQUANTITY", nullable = true, precision = 5)
    public BigDecimal getUserpackagingquantity() {
        return userpackagingquantity;
    }

    public void setUserpackagingquantity(BigDecimal userpackagingquantity) {
        this.userpackagingquantity = userpackagingquantity;
    }

    @Basic
    @Column(name = "WEIGHTUNITOFMEASURECODE", nullable = true, length = 3)
    public String getWeightunitofmeasurecode() {
        return weightunitofmeasurecode;
    }

    public void setWeightunitofmeasurecode(String weightunitofmeasurecode) {
        this.weightunitofmeasurecode = weightunitofmeasurecode;
    }

    @Basic
    @Column(name = "WEIGHTGROSS", nullable = true, precision = 5)
    public BigDecimal getWeightgross() {
        return weightgross;
    }

    public void setWeightgross(BigDecimal weightgross) {
        this.weightgross = weightgross;
    }

    @Basic
    @Column(name = "WEIGHTNET", nullable = true, precision = 5)
    public BigDecimal getWeightnet() {
        return weightnet;
    }

    public void setWeightnet(BigDecimal weightnet) {
        this.weightnet = weightnet;
    }

    @Basic
    @Column(name = "WEIGHTREALNET", nullable = true, precision = 5)
    public BigDecimal getWeightrealnet() {
        return weightrealnet;
    }

    public void setWeightrealnet(BigDecimal weightrealnet) {
        this.weightrealnet = weightrealnet;
    }

    @Basic
    @Column(name = "DERIVATIONCODE", nullable = true, length = 15)
    public String getDerivationcode() {
        return derivationcode;
    }

    public void setDerivationcode(String derivationcode) {
        this.derivationcode = derivationcode;
    }

    @Basic
    @Column(name = "DERIVATIONLINENUMBER", nullable = true, precision = 0)
    public Integer getDerivationlinenumber() {
        return derivationlinenumber;
    }

    public void setDerivationlinenumber(Integer derivationlinenumber) {
        this.derivationlinenumber = derivationlinenumber;
    }

    @Basic
    @Column(name = "DERIVATIONCOMPONENTLINENUMBER", nullable = true, precision = 0)
    public Integer getDerivationcomponentlinenumber() {
        return derivationcomponentlinenumber;
    }

    public void setDerivationcomponentlinenumber(Integer derivationcomponentlinenumber) {
        this.derivationcomponentlinenumber = derivationcomponentlinenumber;
    }

    @Basic
    @Column(name = "QUALITYLVLITEMTYPECOMPANYCODE", nullable = true, length = 3)
    public String getQualitylvlitemtypecompanycode() {
        return qualitylvlitemtypecompanycode;
    }

    public void setQualitylvlitemtypecompanycode(String qualitylvlitemtypecompanycode) {
        this.qualitylvlitemtypecompanycode = qualitylvlitemtypecompanycode;
    }

    @Basic
    @Column(name = "QUALITYLEVELCODE", nullable = true, precision = 0)
    public BigInteger getQualitylevelcode() {
        return qualitylevelcode;
    }

    public void setQualitylevelcode(BigInteger qualitylevelcode) {
        this.qualitylevelcode = qualitylevelcode;
    }

    @Basic
    @Column(name = "QUALITYREASONCOMPANYCODE", nullable = true, length = 3)
    public String getQualityreasoncompanycode() {
        return qualityreasoncompanycode;
    }

    public void setQualityreasoncompanycode(String qualityreasoncompanycode) {
        this.qualityreasoncompanycode = qualityreasoncompanycode;
    }

    @Basic
    @Column(name = "QUALITYREASONCODE", nullable = true, length = 3)
    public String getQualityreasoncode() {
        return qualityreasoncode;
    }

    public void setQualityreasoncode(String qualityreasoncode) {
        this.qualityreasoncode = qualityreasoncode;
    }

    @Basic
    @Column(name = "FIRSTQUALITYCONTROLDATE", nullable = true)
    public Date getFirstqualitycontroldate() {
        return firstqualitycontroldate;
    }

    public void setFirstqualitycontroldate(Date firstqualitycontroldate) {
        this.firstqualitycontroldate = firstqualitycontroldate;
    }

    @Basic
    @Column(name = "FIRSTQUALITYCONTROLCOUNTER", nullable = true, length = 8)
    public String getFirstqualitycontrolcounter() {
        return firstqualitycontrolcounter;
    }

    public void setFirstqualitycontrolcounter(String firstqualitycontrolcounter) {
        this.firstqualitycontrolcounter = firstqualitycontrolcounter;
    }

    @Basic
    @Column(name = "FIRSTQUALITYCONTROLNUMBER", nullable = true, length = 15)
    public String getFirstqualitycontrolnumber() {
        return firstqualitycontrolnumber;
    }

    public void setFirstqualitycontrolnumber(String firstqualitycontrolnumber) {
        this.firstqualitycontrolnumber = firstqualitycontrolnumber;
    }

    @Basic
    @Column(name = "PHYSICALWAREHOUSECOMPANYCODE", nullable = true, length = 3)
    public String getPhysicalwarehousecompanycode() {
        return physicalwarehousecompanycode;
    }

    public void setPhysicalwarehousecompanycode(String physicalwarehousecompanycode) {
        this.physicalwarehousecompanycode = physicalwarehousecompanycode;
    }

    @Basic
    @Column(name = "PHYSICALWAREHOUSECODE", nullable = true, length = 8)
    public String getPhysicalwarehousecode() {
        return physicalwarehousecode;
    }

    public void setPhysicalwarehousecode(String physicalwarehousecode) {
        this.physicalwarehousecode = physicalwarehousecode;
    }

    @Basic
    @Column(name = "WHSLOCWHSZONEPHYWHSCMYCODE", nullable = true, length = 3)
    public String getWhslocwhszonephywhscmycode() {
        return whslocwhszonephywhscmycode;
    }

    public void setWhslocwhszonephywhscmycode(String whslocwhszonephywhscmycode) {
        this.whslocwhszonephywhscmycode = whslocwhszonephywhscmycode;
    }

    @Basic
    @Column(name = "WHSLOCATIONWAREHOUSEZONECODE", nullable = true, length = 3)
    public String getWhslocationwarehousezonecode() {
        return whslocationwarehousezonecode;
    }

    public void setWhslocationwarehousezonecode(String whslocationwarehousezonecode) {
        this.whslocationwarehousezonecode = whslocationwarehousezonecode;
    }

    @Basic
    @Column(name = "WAREHOUSELOCATIONCODE", nullable = true, length = 10)
    public String getWarehouselocationcode() {
        return warehouselocationcode;
    }

    public void setWarehouselocationcode(String warehouselocationcode) {
        this.warehouselocationcode = warehouselocationcode;
    }

    @Basic
    @Column(name = "CONTAINERCOMPANYCODE", nullable = true, length = 3)
    public String getContainercompanycode() {
        return containercompanycode;
    }

    public void setContainercompanycode(String containercompanycode) {
        this.containercompanycode = containercompanycode;
    }

    @Basic
    @Column(name = "CONTAINERITEMTYPECODE", nullable = true, length = 3)
    public String getContaineritemtypecode() {
        return containeritemtypecode;
    }

    public void setContaineritemtypecode(String containeritemtypecode) {
        this.containeritemtypecode = containeritemtypecode;
    }

    @Basic
    @Column(name = "CONTAINERSUBCODE01", nullable = true, length = 20)
    public String getContainersubcode01() {
        return containersubcode01;
    }

    public void setContainersubcode01(String containersubcode01) {
        this.containersubcode01 = containersubcode01;
    }

    @Basic
    @Column(name = "CONTAINERELEMENTCOMPANYCODE", nullable = true, length = 3)
    public String getContainerelementcompanycode() {
        return containerelementcompanycode;
    }

    public void setContainerelementcompanycode(String containerelementcompanycode) {
        this.containerelementcompanycode = containerelementcompanycode;
    }

    @Basic
    @Column(name = "CONTAINERELEMENTCODE", nullable = true, length = 15)
    public String getContainerelementcode() {
        return containerelementcode;
    }

    public void setContainerelementcode(String containerelementcode) {
        this.containerelementcode = containerelementcode;
    }

    @Basic
    @Column(name = "LOTCOMPANYCODE", nullable = true, length = 3)
    public String getLotcompanycode() {
        return lotcompanycode;
    }

    public void setLotcompanycode(String lotcompanycode) {
        this.lotcompanycode = lotcompanycode;
    }

    @Basic
    @Column(name = "LOTCODE", nullable = true, length = 10)
    public String getLotcode() {
        return lotcode;
    }

    public void setLotcode(String lotcode) {
        this.lotcode = lotcode;
    }

    @Basic
    @Column(name = "ITEMELEMENTCOMPANYCODE", nullable = true, length = 3)
    public String getItemelementcompanycode() {
        return itemelementcompanycode;
    }

    public void setItemelementcompanycode(String itemelementcompanycode) {
        this.itemelementcompanycode = itemelementcompanycode;
    }

    @Basic
    @Column(name = "ITEMELEMENTSUBCODEKEY", nullable = true, length = 20)
    public String getItemelementsubcodekey() {
        return itemelementsubcodekey;
    }

    public void setItemelementsubcodekey(String itemelementsubcodekey) {
        this.itemelementsubcodekey = itemelementsubcodekey;
    }

    @Basic
    @Column(name = "ITEMELEMENTCODE", nullable = true, length = 15)
    public String getItemelementcode() {
        return itemelementcode;
    }

    public void setItemelementcode(String itemelementcode) {
        this.itemelementcode = itemelementcode;
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
    @Column(name = "COSTCENTERCOMPANYCODE", nullable = true, length = 3)
    public String getCostcentercompanycode() {
        return costcentercompanycode;
    }

    public void setCostcentercompanycode(String costcentercompanycode) {
        this.costcentercompanycode = costcentercompanycode;
    }

    @Basic
    @Column(name = "COSTCENTERCODE", nullable = true, length = 20)
    public String getCostcentercode() {
        return costcentercode;
    }

    public void setCostcentercode(String costcentercode) {
        this.costcentercode = costcentercode;
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
    @Column(name = "COST", nullable = true, precision = 5)
    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
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
    @Column(name = "CUSTOMERTYPE", nullable = true, length = 1)
    public String getCustomertype() {
        return customertype;
    }

    public void setCustomertype(String customertype) {
        this.customertype = customertype;
    }

    @Basic
    @Column(name = "CUSTOMERCODE", nullable = true, length = 8)
    public String getCustomercode() {
        return customercode;
    }

    public void setCustomercode(String customercode) {
        this.customercode = customercode;
    }

    @Basic
    @Column(name = "SUPPLIERTYPE", nullable = true, length = 1)
    public String getSuppliertype() {
        return suppliertype;
    }

    public void setSuppliertype(String suppliertype) {
        this.suppliertype = suppliertype;
    }

    @Basic
    @Column(name = "SUPPLIERCODE", nullable = true, length = 8)
    public String getSuppliercode() {
        return suppliercode;
    }

    public void setSuppliercode(String suppliercode) {
        this.suppliercode = suppliercode;
    }

    @Basic
    @Column(name = "BILLDATE", nullable = true)
    public Date getBilldate() {
        return billdate;
    }

    public void setBilldate(Date billdate) {
        this.billdate = billdate;
    }

    @Basic
    @Column(name = "BILLTYPE", nullable = false)
    public Short getBilltype() {
        return billtype;
    }

    public void setBilltype(Short billtype) {
        this.billtype = billtype;
    }

    @Basic
    @Column(name = "BILLCOUNTER", nullable = true, length = 8)
    public String getBillcounter() {
        return billcounter;
    }

    public void setBillcounter(String billcounter) {
        this.billcounter = billcounter;
    }

    @Basic
    @Column(name = "BILLCODE", nullable = true, length = 50)
    public String getBillcode() {
        return billcode;
    }

    public void setBillcode(String billcode) {
        this.billcode = billcode;
    }

    @Basic
    @Column(name = "INTERNALDOCUMENTDATE", nullable = true)
    public Date getInternaldocumentdate() {
        return internaldocumentdate;
    }

    public void setInternaldocumentdate(Date internaldocumentdate) {
        this.internaldocumentdate = internaldocumentdate;
    }

    @Basic
    @Column(name = "INTERNALDOCUMENTNUMBER", nullable = false)
    public Integer getInternaldocumentnumber() {
        return internaldocumentnumber;
    }

    public void setInternaldocumentnumber(Integer internaldocumentnumber) {
        this.internaldocumentnumber = internaldocumentnumber;
    }

    @Basic
    @Column(name = "ORDERCOUNTERCOMPANYCODE", nullable = true, length = 3)
    public String getOrdercountercompanycode() {
        return ordercountercompanycode;
    }

    public void setOrdercountercompanycode(String ordercountercompanycode) {
        this.ordercountercompanycode = ordercountercompanycode;
    }

    @Basic
    @Column(name = "ORDERCOUNTERCODE", nullable = true, length = 8)
    public String getOrdercountercode() {
        return ordercountercode;
    }

    public void setOrdercountercode(String ordercountercode) {
        this.ordercountercode = ordercountercode;
    }

    @Basic
    @Column(name = "ORDERCODE", nullable = true, length = 15)
    public String getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode;
    }

    @Basic
    @Column(name = "ORDERLINE", nullable = true, precision = 0)
    public Integer getOrderline() {
        return orderline;
    }

    public void setOrderline(Integer orderline) {
        this.orderline = orderline;
    }

    @Basic
    @Column(name = "ORDERSUBLINE", nullable = true, precision = 0)
    public Integer getOrdersubline() {
        return ordersubline;
    }

    public void setOrdersubline(Integer ordersubline) {
        this.ordersubline = ordersubline;
    }

    @Basic
    @Column(name = "ORDERCOMPONENTLINE", nullable = true, precision = 0)
    public Integer getOrdercomponentline() {
        return ordercomponentline;
    }

    public void setOrdercomponentline(Integer ordercomponentline) {
        this.ordercomponentline = ordercomponentline;
    }

    @Basic
    @Column(name = "ORDERDELIVERYLINE", nullable = true, precision = 0)
    public Integer getOrderdeliveryline() {
        return orderdeliveryline;
    }

    public void setOrderdeliveryline(Integer orderdeliveryline) {
        this.orderdeliveryline = orderdeliveryline;
    }

    @Basic
    @Column(name = "PRODUCTIONORDERCODE", nullable = true, length = 15)
    public String getProductionordercode() {
        return productionordercode;
    }

    public void setProductionordercode(String productionordercode) {
        this.productionordercode = productionordercode;
    }

    @Basic
    @Column(name = "RETURNCODE", nullable = true, length = 15)
    public String getReturncode() {
        return returncode;
    }

    public void setReturncode(String returncode) {
        this.returncode = returncode;
    }

    @Basic
    @Column(name = "RETURNLINE", nullable = true, precision = 0)
    public Integer getReturnline() {
        return returnline;
    }

    public void setReturnline(Integer returnline) {
        this.returnline = returnline;
    }

    @Basic
    @Column(name = "TOKENCODE", nullable = true, length = 20)
    public String getTokencode() {
        return tokencode;
    }

    public void setTokencode(String tokencode) {
        this.tokencode = tokencode;
    }

    @Basic
    @Column(name = "ENVCODESKIPBLCEXP", nullable = true, length = 30)
    public String getEnvcodeskipblcexp() {
        return envcodeskipblcexp;
    }

    public void setEnvcodeskipblcexp(String envcodeskipblcexp) {
        this.envcodeskipblcexp = envcodeskipblcexp;
    }

    @Basic
    @Column(name = "ADUNIQUEID", nullable = false)
    public Long getAduniqueid() {
        return aduniqueid;
    }

    public void setAduniqueid(Long aduniqueid) {
        this.aduniqueid = aduniqueid;
    }

    @Basic
    @Column(name = "ADUNIQUEIDFORAUTOISSUE", nullable = false)
    public Long getAduniqueidforautoissue() {
        return aduniqueidforautoissue;
    }

    public void setAduniqueidforautoissue(Long aduniqueidforautoissue) {
        this.aduniqueidforautoissue = aduniqueidforautoissue;
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

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stocktransactionimport that = (Stocktransactionimport) o;
        return Objects.equals(importstatus, that.importstatus) &&
            Objects.equals(nowtrnnumbertransactionnumber, that.nowtrnnumbertransactionnumber) &&
            Objects.equals(transactionstatus, that.transactionstatus) &&
            Objects.equals(itemtypecompanycode, that.itemtypecompanycode) &&
            Objects.equals(itemtypecode, that.itemtypecode) &&
            Objects.equals(transactiondate, that.transactiondate) &&
            Objects.equals(transactiontime, that.transactiontime) &&
            Objects.equals(detailtype, that.detailtype) &&
            Objects.equals(templatecompanycode, that.templatecompanycode) &&
            Objects.equals(templatecode, that.templatecode) &&
            Objects.equals(autoissuetemplatecompanycode, that.autoissuetemplatecompanycode) &&
            Objects.equals(autoissuetemplatecode, that.autoissuetemplatecode) &&
            Objects.equals(stocktransactiontype, that.stocktransactiontype) &&
            Objects.equals(decocompanycode, that.decocompanycode) &&
            Objects.equals(decosubcode01, that.decosubcode01) &&
            Objects.equals(decosubcode02, that.decosubcode02) &&
            Objects.equals(decosubcode03, that.decosubcode03) &&
            Objects.equals(decosubcode04, that.decosubcode04) &&
            Objects.equals(decosubcode05, that.decosubcode05) &&
            Objects.equals(decosubcode06, that.decosubcode06) &&
            Objects.equals(decosubcode07, that.decosubcode07) &&
            Objects.equals(decosubcode08, that.decosubcode08) &&
            Objects.equals(decosubcode09, that.decosubcode09) &&
            Objects.equals(decosubcode10, that.decosubcode10) &&
            Objects.equals(itemdescription, that.itemdescription) &&
            Objects.equals(logicalwarehousecompanycode, that.logicalwarehousecompanycode) &&
            Objects.equals(logicalwarehousecode, that.logicalwarehousecode) &&
            Objects.equals(transferallocation, that.transferallocation) &&
            Objects.equals(issueqtyfrombalance, that.issueqtyfrombalance) &&
            Objects.equals(userprimaryuomcode, that.userprimaryuomcode) &&
            Objects.equals(userprimaryquantity, that.userprimaryquantity) &&
            Objects.equals(usersecondaryuomcode, that.usersecondaryuomcode) &&
            Objects.equals(usersecondaryquantity, that.usersecondaryquantity) &&
            Objects.equals(userpackaginguomcode, that.userpackaginguomcode) &&
            Objects.equals(userpackagingquantity, that.userpackagingquantity) &&
            Objects.equals(weightunitofmeasurecode, that.weightunitofmeasurecode) &&
            Objects.equals(weightgross, that.weightgross) &&
            Objects.equals(weightnet, that.weightnet) &&
            Objects.equals(weightrealnet, that.weightrealnet) &&
            Objects.equals(derivationcode, that.derivationcode) &&
            Objects.equals(derivationlinenumber, that.derivationlinenumber) &&
            Objects.equals(derivationcomponentlinenumber, that.derivationcomponentlinenumber) &&
            Objects.equals(qualitylvlitemtypecompanycode, that.qualitylvlitemtypecompanycode) &&
            Objects.equals(qualitylevelcode, that.qualitylevelcode) &&
            Objects.equals(qualityreasoncompanycode, that.qualityreasoncompanycode) &&
            Objects.equals(qualityreasoncode, that.qualityreasoncode) &&
            Objects.equals(firstqualitycontroldate, that.firstqualitycontroldate) &&
            Objects.equals(firstqualitycontrolcounter, that.firstqualitycontrolcounter) &&
            Objects.equals(firstqualitycontrolnumber, that.firstqualitycontrolnumber) &&
            Objects.equals(physicalwarehousecompanycode, that.physicalwarehousecompanycode) &&
            Objects.equals(physicalwarehousecode, that.physicalwarehousecode) &&
            Objects.equals(whslocwhszonephywhscmycode, that.whslocwhszonephywhscmycode) &&
            Objects.equals(whslocationwarehousezonecode, that.whslocationwarehousezonecode) &&
            Objects.equals(warehouselocationcode, that.warehouselocationcode) &&
            Objects.equals(containercompanycode, that.containercompanycode) &&
            Objects.equals(containeritemtypecode, that.containeritemtypecode) &&
            Objects.equals(containersubcode01, that.containersubcode01) &&
            Objects.equals(containerelementcompanycode, that.containerelementcompanycode) &&
            Objects.equals(containerelementcode, that.containerelementcode) &&
            Objects.equals(lotcompanycode, that.lotcompanycode) &&
            Objects.equals(lotcode, that.lotcode) &&
            Objects.equals(itemelementcompanycode, that.itemelementcompanycode) &&
            Objects.equals(itemelementsubcodekey, that.itemelementsubcodekey) &&
            Objects.equals(itemelementcode, that.itemelementcode) &&
            Objects.equals(projectcode, that.projectcode) &&
            Objects.equals(costcentercompanycode, that.costcentercompanycode) &&
            Objects.equals(costcentercode, that.costcentercode) &&
            Objects.equals(statisticalgroupcompanycode, that.statisticalgroupcompanycode) &&
            Objects.equals(statisticalgroupcode, that.statisticalgroupcode) &&
            Objects.equals(cost, that.cost) &&
            Objects.equals(currencycode, that.currencycode) &&
            Objects.equals(customertype, that.customertype) &&
            Objects.equals(customercode, that.customercode) &&
            Objects.equals(suppliertype, that.suppliertype) &&
            Objects.equals(suppliercode, that.suppliercode) &&
            Objects.equals(billdate, that.billdate) &&
            Objects.equals(billtype, that.billtype) &&
            Objects.equals(billcounter, that.billcounter) &&
            Objects.equals(billcode, that.billcode) &&
            Objects.equals(internaldocumentdate, that.internaldocumentdate) &&
            Objects.equals(internaldocumentnumber, that.internaldocumentnumber) &&
            Objects.equals(ordercountercompanycode, that.ordercountercompanycode) &&
            Objects.equals(ordercountercode, that.ordercountercode) &&
            Objects.equals(ordercode, that.ordercode) &&
            Objects.equals(orderline, that.orderline) &&
            Objects.equals(ordersubline, that.ordersubline) &&
            Objects.equals(ordercomponentline, that.ordercomponentline) &&
            Objects.equals(orderdeliveryline, that.orderdeliveryline) &&
            Objects.equals(productionordercode, that.productionordercode) &&
            Objects.equals(returncode, that.returncode) &&
            Objects.equals(returnline, that.returnline) &&
            Objects.equals(tokencode, that.tokencode) &&
            Objects.equals(envcodeskipblcexp, that.envcodeskipblcexp) &&
            Objects.equals(aduniqueid, that.aduniqueid) &&
            Objects.equals(aduniqueidforautoissue, that.aduniqueidforautoissue) &&
            Objects.equals(creationdatetime, that.creationdatetime) &&
            Objects.equals(creationuser, that.creationuser) &&
            Objects.equals(lastupdatedatetime, that.lastupdatedatetime) &&
            Objects.equals(lastupdateuser, that.lastupdateuser) &&
            Objects.equals(absuniqueid, that.absuniqueid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(importstatus, nowtrnnumbertransactionnumber, transactionstatus, itemtypecompanycode, itemtypecode, transactiondate, transactiontime, detailtype, templatecompanycode, templatecode, autoissuetemplatecompanycode, autoissuetemplatecode, stocktransactiontype, decocompanycode, decosubcode01, decosubcode02, decosubcode03, decosubcode04, decosubcode05, decosubcode06, decosubcode07, decosubcode08, decosubcode09, decosubcode10, itemdescription, logicalwarehousecompanycode, logicalwarehousecode, transferallocation, issueqtyfrombalance, userprimaryuomcode, userprimaryquantity, usersecondaryuomcode, usersecondaryquantity, userpackaginguomcode, userpackagingquantity, weightunitofmeasurecode, weightgross, weightnet, weightrealnet, derivationcode, derivationlinenumber, derivationcomponentlinenumber, qualitylvlitemtypecompanycode, qualitylevelcode, qualityreasoncompanycode, qualityreasoncode, firstqualitycontroldate, firstqualitycontrolcounter, firstqualitycontrolnumber, physicalwarehousecompanycode, physicalwarehousecode, whslocwhszonephywhscmycode, whslocationwarehousezonecode, warehouselocationcode, containercompanycode, containeritemtypecode, containersubcode01, containerelementcompanycode, containerelementcode, lotcompanycode, lotcode, itemelementcompanycode, itemelementsubcodekey, itemelementcode, projectcode, costcentercompanycode, costcentercode, statisticalgroupcompanycode, statisticalgroupcode, cost, currencycode, customertype, customercode, suppliertype, suppliercode, billdate, billtype, billcounter, billcode, internaldocumentdate, internaldocumentnumber, ordercountercompanycode, ordercountercode, ordercode, orderline, ordersubline, ordercomponentline, orderdeliveryline, productionordercode, returncode, returnline, tokencode, envcodeskipblcexp, aduniqueid, aduniqueidforautoissue, creationdatetime, creationuser, lastupdatedatetime, lastupdateuser, absuniqueid);
    }
}
