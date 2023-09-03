package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "STOCKTRANSACTION")
public class Stocktransaction {
    @EmbeddedId
    private StocktransactionId id;
    private String transactionstatus;
    private Date transactiondate;
    private Time transactiontime;
    private String definitivetransactionnumber;
    private String detailtype;
    private String templatecompanycode;
    private String templatecode;
    private String stocktransactiontype;
    private String itemtypecompanycode;
    private String itemtypecode;
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
    private String stocktypecode;
    private String userprimaryuomcode;
    private BigDecimal userprimaryquantity;
    private String baseprimaryuomcode;
    private BigDecimal baseprimaryquantity;
    private String usersecondaryuomcode;
    private BigDecimal usersecondaryquantity;
    private String basesecondaryuomcode;
    private BigDecimal basesecondaryquantity;
    private String userpackaginguomcode;
    private BigDecimal userpackagingquantity;
    private String weightunitofmeasurecode;
    private BigDecimal weightgross;
    private BigDecimal weightnet;
    private BigDecimal weightrealnet;
    private String derivationcode;
    private Integer derivationlinenumber;
    private Integer derivationcomponentlinenumber;
    private BigInteger qualitylevelcode;
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
    private String qualityreasoncompanycode;
    private String qualityreasoncode;
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
    private BigDecimal costinbasecurrency;
    private String basecurrencycode;
    private BigDecimal exchangerate;
    private String basecostunitcode;
    private BigDecimal basecostunitquantity;
    private BigDecimal provisionalbasecost;
    private String provisionalcoststatus;
    private BigDecimal closingbasecost;
    private BigDecimal additionalclosingcost;
    private String closingcoststatus;
    private BigInteger valuationpriority;
    private BigDecimal actualbasecost;
    private String actualbasecoststatus;
    private BigDecimal actualcstbeforeaddcst;
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
    private String ordercountercode;
    private String ordercode;
    private Integer orderline;
    private Integer ordersubline;
    private Integer ordercomponentline;
    private Integer orderdeliveryline;
    private String productionordercode;
    private String returncode;
    private Integer returnline;
    private Date invoicedate;
    private String invoicecode;
    private String accounttransactionnumber;
    private String entrydocumenttogenerate;
    private Short lotcostupdate;
    private Short returntransaction;
    private Short portfoliocontrol;
    private String onhandupdate;
    private Short lotreceivedquantityupdate;
    private String whsaccountinggroupcompanycode;
    private String warehouseaccountinggroupcode;
    private String tmpgrpstdgrouptypecompanycode;
    private String tmpgroupstandardgrouptypecode;
    private String templategroupcode;
    private Short postedtransaction;
    private Short dynamicaveragecostupdate;
    private Integer fullitemidentifier;
    private Short fromcutitem;
    private String cutorgtrtransactionnumber;
    private Timestamp creationdatetime;
    private String creationuser;
    private Timestamp lastupdatedatetime;
    private String lastupdateuser;
    private String tokencode;
    private Long absuniqueid;
    private Timestamp creationdatetimeutc;
    private Timestamp lastupdatedatetimeutc;

    public StocktransactionId getId() {
        return id;
    }

    public void setId(StocktransactionId id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TRANSACTIONSTATUS", nullable = false, length = 2)
    public String getTransactionstatus() {
        return transactionstatus;
    }

    public void setTransactionstatus(String transactionstatus) {
        this.transactionstatus = transactionstatus;
    }

    @Basic
    @Column(name = "TRANSACTIONDATE", nullable = false)
    public Date getTransactiondate() {
        return transactiondate;
    }

    public void setTransactiondate(Date transactiondate) {
        this.transactiondate = transactiondate;
    }

    @Basic
    @Column(name = "TRANSACTIONTIME", nullable = false)
    public Time getTransactiontime() {
        return transactiontime;
    }

    public void setTransactiontime(Time transactiontime) {
        this.transactiontime = transactiontime;
    }

    @Basic
    @Column(name = "DEFINITIVETRANSACTIONNUMBER", nullable = true, length = 15)
    public String getDefinitivetransactionnumber() {
        return definitivetransactionnumber;
    }

    public void setDefinitivetransactionnumber(String definitivetransactionnumber) {
        this.definitivetransactionnumber = definitivetransactionnumber;
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
    @Column(name = "STOCKTRANSACTIONTYPE", nullable = false, length = 2)
    public String getStocktransactiontype() {
        return stocktransactiontype;
    }

    public void setStocktransactiontype(String stocktransactiontype) {
        this.stocktransactiontype = stocktransactiontype;
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
    @Column(name = "ITEMDESCRIPTION", nullable = true, length = 200)
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
    @Column(name = "STOCKTYPECODE", nullable = true, length = 3)
    public String getStocktypecode() {
        return stocktypecode;
    }

    public void setStocktypecode(String stocktypecode) {
        this.stocktypecode = stocktypecode;
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
    @Column(name = "USERPRIMARYQUANTITY", nullable = false, precision = 5)
    public BigDecimal getUserprimaryquantity() {
        return userprimaryquantity;
    }

    public void setUserprimaryquantity(BigDecimal userprimaryquantity) {
        this.userprimaryquantity = userprimaryquantity;
    }

    @Basic
    @Column(name = "BASEPRIMARYUOMCODE", nullable = true, length = 3)
    public String getBaseprimaryuomcode() {
        return baseprimaryuomcode;
    }

    public void setBaseprimaryuomcode(String baseprimaryuomcode) {
        this.baseprimaryuomcode = baseprimaryuomcode;
    }

    @Basic
    @Column(name = "BASEPRIMARYQUANTITY", nullable = true, precision = 5)
    public BigDecimal getBaseprimaryquantity() {
        return baseprimaryquantity;
    }

    public void setBaseprimaryquantity(BigDecimal baseprimaryquantity) {
        this.baseprimaryquantity = baseprimaryquantity;
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
    @Column(name = "BASESECONDARYUOMCODE", nullable = true, length = 3)
    public String getBasesecondaryuomcode() {
        return basesecondaryuomcode;
    }

    public void setBasesecondaryuomcode(String basesecondaryuomcode) {
        this.basesecondaryuomcode = basesecondaryuomcode;
    }

    @Basic
    @Column(name = "BASESECONDARYQUANTITY", nullable = true, precision = 5)
    public BigDecimal getBasesecondaryquantity() {
        return basesecondaryquantity;
    }

    public void setBasesecondaryquantity(BigDecimal basesecondaryquantity) {
        this.basesecondaryquantity = basesecondaryquantity;
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
    @Column(name = "QUALITYLEVELCODE", nullable = true, precision = 0)
    public BigInteger getQualitylevelcode() {
        return qualitylevelcode;
    }

    public void setQualitylevelcode(BigInteger qualitylevelcode) {
        this.qualitylevelcode = qualitylevelcode;
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
    @Column(name = "LOTCODE", nullable = true, length = 35)
    public String getLotcode() {
        return lotcode;
    }

    public void setLotcode(String lotcode) {
        this.lotcode = lotcode;
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
    @Column(name = "COSTINBASECURRENCY", nullable = true, precision = 5)
    public BigDecimal getCostinbasecurrency() {
        return costinbasecurrency;
    }

    public void setCostinbasecurrency(BigDecimal costinbasecurrency) {
        this.costinbasecurrency = costinbasecurrency;
    }

    @Basic
    @Column(name = "BASECURRENCYCODE", nullable = true, length = 4)
    public String getBasecurrencycode() {
        return basecurrencycode;
    }

    public void setBasecurrencycode(String basecurrencycode) {
        this.basecurrencycode = basecurrencycode;
    }

    @Basic
    @Column(name = "EXCHANGERATE", nullable = true, precision = 15)
    public BigDecimal getExchangerate() {
        return exchangerate;
    }

    public void setExchangerate(BigDecimal exchangerate) {
        this.exchangerate = exchangerate;
    }

    @Basic
    @Column(name = "BASECOSTUNITCODE", nullable = true, length = 3)
    public String getBasecostunitcode() {
        return basecostunitcode;
    }

    public void setBasecostunitcode(String basecostunitcode) {
        this.basecostunitcode = basecostunitcode;
    }

    @Basic
    @Column(name = "BASECOSTUNITQUANTITY", nullable = true, precision = 5)
    public BigDecimal getBasecostunitquantity() {
        return basecostunitquantity;
    }

    public void setBasecostunitquantity(BigDecimal basecostunitquantity) {
        this.basecostunitquantity = basecostunitquantity;
    }

    @Basic
    @Column(name = "PROVISIONALBASECOST", nullable = true, precision = 5)
    public BigDecimal getProvisionalbasecost() {
        return provisionalbasecost;
    }

    public void setProvisionalbasecost(BigDecimal provisionalbasecost) {
        this.provisionalbasecost = provisionalbasecost;
    }

    @Basic
    @Column(name = "PROVISIONALCOSTSTATUS", nullable = true, length = 2)
    public String getProvisionalcoststatus() {
        return provisionalcoststatus;
    }

    public void setProvisionalcoststatus(String provisionalcoststatus) {
        this.provisionalcoststatus = provisionalcoststatus;
    }

    @Basic
    @Column(name = "CLOSINGBASECOST", nullable = true, precision = 5)
    public BigDecimal getClosingbasecost() {
        return closingbasecost;
    }

    public void setClosingbasecost(BigDecimal closingbasecost) {
        this.closingbasecost = closingbasecost;
    }

    @Basic
    @Column(name = "ADDITIONALCLOSINGCOST", nullable = true, precision = 5)
    public BigDecimal getAdditionalclosingcost() {
        return additionalclosingcost;
    }

    public void setAdditionalclosingcost(BigDecimal additionalclosingcost) {
        this.additionalclosingcost = additionalclosingcost;
    }

    @Basic
    @Column(name = "CLOSINGCOSTSTATUS", nullable = true, length = 2)
    public String getClosingcoststatus() {
        return closingcoststatus;
    }

    public void setClosingcoststatus(String closingcoststatus) {
        this.closingcoststatus = closingcoststatus;
    }

    @Basic
    @Column(name = "VALUATIONPRIORITY", nullable = true, precision = 0)
    public BigInteger getValuationpriority() {
        return valuationpriority;
    }

    public void setValuationpriority(BigInteger valuationpriority) {
        this.valuationpriority = valuationpriority;
    }

    @Basic
    @Column(name = "ACTUALBASECOST", nullable = true, precision = 5)
    public BigDecimal getActualbasecost() {
        return actualbasecost;
    }

    public void setActualbasecost(BigDecimal actualbasecost) {
        this.actualbasecost = actualbasecost;
    }

    @Basic
    @Column(name = "ACTUALBASECOSTSTATUS", nullable = true, length = 1)
    public String getActualbasecoststatus() {
        return actualbasecoststatus;
    }

    public void setActualbasecoststatus(String actualbasecoststatus) {
        this.actualbasecoststatus = actualbasecoststatus;
    }

    @Basic
    @Column(name = "ACTUALCSTBEFOREADDCST", nullable = true, precision = 5)
    public BigDecimal getActualcstbeforeaddcst() {
        return actualcstbeforeaddcst;
    }

    public void setActualcstbeforeaddcst(BigDecimal actualcstbeforeaddcst) {
        this.actualcstbeforeaddcst = actualcstbeforeaddcst;
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
    @Column(name = "INVOICEDATE", nullable = true)
    public Date getInvoicedate() {
        return invoicedate;
    }

    public void setInvoicedate(Date invoicedate) {
        this.invoicedate = invoicedate;
    }

    @Basic
    @Column(name = "INVOICECODE", nullable = true, length = 50)
    public String getInvoicecode() {
        return invoicecode;
    }

    public void setInvoicecode(String invoicecode) {
        this.invoicecode = invoicecode;
    }

    @Basic
    @Column(name = "ACCOUNTTRANSACTIONNUMBER", nullable = true, length = 15)
    public String getAccounttransactionnumber() {
        return accounttransactionnumber;
    }

    public void setAccounttransactionnumber(String accounttransactionnumber) {
        this.accounttransactionnumber = accounttransactionnumber;
    }

    @Basic
    @Column(name = "ENTRYDOCUMENTTOGENERATE", nullable = false, length = 2)
    public String getEntrydocumenttogenerate() {
        return entrydocumenttogenerate;
    }

    public void setEntrydocumenttogenerate(String entrydocumenttogenerate) {
        this.entrydocumenttogenerate = entrydocumenttogenerate;
    }

    @Basic
    @Column(name = "LOTCOSTUPDATE", nullable = false)
    public Short getLotcostupdate() {
        return lotcostupdate;
    }

    public void setLotcostupdate(Short lotcostupdate) {
        this.lotcostupdate = lotcostupdate;
    }

    @Basic
    @Column(name = "RETURNTRANSACTION", nullable = false)
    public Short getReturntransaction() {
        return returntransaction;
    }

    public void setReturntransaction(Short returntransaction) {
        this.returntransaction = returntransaction;
    }

    @Basic
    @Column(name = "PORTFOLIOCONTROL", nullable = false)
    public Short getPortfoliocontrol() {
        return portfoliocontrol;
    }

    public void setPortfoliocontrol(Short portfoliocontrol) {
        this.portfoliocontrol = portfoliocontrol;
    }

    @Basic
    @Column(name = "ONHANDUPDATE", nullable = false, length = 2)
    public String getOnhandupdate() {
        return onhandupdate;
    }

    public void setOnhandupdate(String onhandupdate) {
        this.onhandupdate = onhandupdate;
    }

    @Basic
    @Column(name = "LOTRECEIVEDQUANTITYUPDATE", nullable = false)
    public Short getLotreceivedquantityupdate() {
        return lotreceivedquantityupdate;
    }

    public void setLotreceivedquantityupdate(Short lotreceivedquantityupdate) {
        this.lotreceivedquantityupdate = lotreceivedquantityupdate;
    }

    @Basic
    @Column(name = "WHSACCOUNTINGGROUPCOMPANYCODE", nullable = true, length = 3)
    public String getWhsaccountinggroupcompanycode() {
        return whsaccountinggroupcompanycode;
    }

    public void setWhsaccountinggroupcompanycode(String whsaccountinggroupcompanycode) {
        this.whsaccountinggroupcompanycode = whsaccountinggroupcompanycode;
    }

    @Basic
    @Column(name = "WAREHOUSEACCOUNTINGGROUPCODE", nullable = true, length = 3)
    public String getWarehouseaccountinggroupcode() {
        return warehouseaccountinggroupcode;
    }

    public void setWarehouseaccountinggroupcode(String warehouseaccountinggroupcode) {
        this.warehouseaccountinggroupcode = warehouseaccountinggroupcode;
    }

    @Basic
    @Column(name = "TMPGRPSTDGROUPTYPECOMPANYCODE", nullable = true, length = 3)
    public String getTmpgrpstdgrouptypecompanycode() {
        return tmpgrpstdgrouptypecompanycode;
    }

    public void setTmpgrpstdgrouptypecompanycode(String tmpgrpstdgrouptypecompanycode) {
        this.tmpgrpstdgrouptypecompanycode = tmpgrpstdgrouptypecompanycode;
    }

    @Basic
    @Column(name = "TMPGROUPSTANDARDGROUPTYPECODE", nullable = true, length = 3)
    public String getTmpgroupstandardgrouptypecode() {
        return tmpgroupstandardgrouptypecode;
    }

    public void setTmpgroupstandardgrouptypecode(String tmpgroupstandardgrouptypecode) {
        this.tmpgroupstandardgrouptypecode = tmpgroupstandardgrouptypecode;
    }

    @Basic
    @Column(name = "TEMPLATEGROUPCODE", nullable = true, length = 3)
    public String getTemplategroupcode() {
        return templategroupcode;
    }

    public void setTemplategroupcode(String templategroupcode) {
        this.templategroupcode = templategroupcode;
    }

    @Basic
    @Column(name = "POSTEDTRANSACTION", nullable = false)
    public Short getPostedtransaction() {
        return postedtransaction;
    }

    public void setPostedtransaction(Short postedtransaction) {
        this.postedtransaction = postedtransaction;
    }

    @Basic
    @Column(name = "DYNAMICAVERAGECOSTUPDATE", nullable = false)
    public Short getDynamicaveragecostupdate() {
        return dynamicaveragecostupdate;
    }

    public void setDynamicaveragecostupdate(Short dynamicaveragecostupdate) {
        this.dynamicaveragecostupdate = dynamicaveragecostupdate;
    }

    @Basic
    @Column(name = "FULLITEMIDENTIFIER", nullable = true, precision = 0)
    public Integer getFullitemidentifier() {
        return fullitemidentifier;
    }

    public void setFullitemidentifier(Integer fullitemidentifier) {
        this.fullitemidentifier = fullitemidentifier;
    }

    @Basic
    @Column(name = "FROMCUTITEM", nullable = false)
    public Short getFromcutitem() {
        return fromcutitem;
    }

    public void setFromcutitem(Short fromcutitem) {
        this.fromcutitem = fromcutitem;
    }

    @Basic
    @Column(name = "CUTORGTRTRANSACTIONNUMBER", nullable = true, length = 15)
    public String getCutorgtrtransactionnumber() {
        return cutorgtrtransactionnumber;
    }

    public void setCutorgtrtransactionnumber(String cutorgtrtransactionnumber) {
        this.cutorgtrtransactionnumber = cutorgtrtransactionnumber;
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
    @Column(name = "TOKENCODE", nullable = true, length = 20)
    public String getTokencode() {
        return tokencode;
    }

    public void setTokencode(String tokencode) {
        this.tokencode = tokencode;
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
}
