package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "salesorderline")
public class Salesorderline {
    @EmbeddedId
    private SalesorderlineId id;
    private String divisioncode;
    private String alloweddivisions;
    private String ordertype;
    private String documenttypetype;
    private Integer assortmentnumberid;
    private String linetemplatecode;
    private String samplestype;
    private Short boxmanaged;
    private String externalreference;
    private Date externalreferencedate;
    private String internalreference;
    private Date internalreferencedate;
    private String itemtypeaficompanycode;
    private String itemtypeaficode;
    private String itemnature;
    private String subcode01;
    private String subcode02;
    private String subcode03;
    private String subcode04;
    private String subcode05;
    private String subcode06;
    private String subcode07;
    private String subcode08;
    private String subcode09;
    private String subcode10;
    private Integer fullitemidentifier;
    private Integer sellingitemidentifier;
    private String externalitem;
    private String itemdescription;
    private Integer obsoletediscardeditem;
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
    private BigDecimal cancelleduserprimaryquantity;
    private BigDecimal cancelledbaseprimaryquantity;
    private BigDecimal cancelledusersecondaryquantity;
    private BigDecimal cancelledbasesecondaryquantity;
    private BigDecimal cancelleduserpackagingquantity;
    private BigDecimal originaluserprimaryquantity;
    private BigDecimal originalusersecondaryquantity;
    private BigDecimal originaluserpackagingquantity;
    private BigInteger qualitycode;
    private String linestatus;
    private String progressstatus;
    private String statisticalgroupcompanycode;
    private String statisticalgroupcode;
    private String collectiongroupcompanycode;
    private String collectiongroupcode;
    private String projectcode;
    private String linegroup;
    private String warehousecompanycode;
    private String warehousecode;
    private Short updatewarehouseavailability;
    private BigDecimal cost;
    private String costcentercompanycode;
    private String costcentercode;
    private Short distributionwarehouserequired;
    private String releasetype;
    private Integer releasepriority;
    private Short leftoverloss;
    private Integer joinedorderline;
    private Integer joinedordersubline;
    private Integer joinedcomponentorderline;
    private String linesource;
    private String previouslinetemplatecode;
    private String previouscountercompanycode;
    private String previouscountercode;
    private String previousdocumenttypeordertype;
    private String previousdocumenttypetype;
    private String previouscode;
    private Integer previousorderline;
    private Integer previousordersubline;
    private Integer previouscomponentorderline;
    private String intercompanycode;
    private Integer previousdeliveryline;
    private BigDecimal entryexchangerate;
    private Date conditionretrievingdate;
    private String paymentmethodcompanycode;
    private String paymentmethodcode;
    private String pricelistcode;
    private String discountcategorycode;
    private String lineuservalue;
    private String priceunitofmeasurecode;
    private BigDecimal price;
    private String pricetype;
    private String pricesign;
    private Short priceincludingtax;
    private BigDecimal priceretrieved;
    private BigDecimal originalamount;
    private BigDecimal netvalue;
    private Short updatevalue;
    private String taxcode;
    private String freegifttaxdebit;
    private BigDecimal taxableincomevalue;
    private BigDecimal netvalueincludingtax;
    private Short onlybyamount;
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
    private Short manuallyinsertforbox;
    private String termsoflogcode;
    private String logreasoncode;
    private Timestamp creationdatetime;
    private String creationuser;
    private Timestamp lastupdatedatetime;
    private String lastupdateuser;
    private Long absuniqueid;
    private String purordprncustomersuppliertype;
    private String purordprncustomersuppliercode;
    private BigDecimal pouserprimaryquantity;
    private BigDecimal pousersecondaryquantity;
    private BigDecimal pouserpackagingquantity;
    private Short keepentryexchangerate;
    private Timestamp creationdatetimeutc;
    private Timestamp lastupdatedatetimeutc;

    public SalesorderlineId getId() {
        return id;
    }

    public void setId(SalesorderlineId id) {
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
    @Column(name = "ASSORTMENTNUMBERID", nullable = true, precision = 0)
    public Integer getAssortmentnumberid() {
        return assortmentnumberid;
    }

    public void setAssortmentnumberid(Integer assortmentnumberid) {
        this.assortmentnumberid = assortmentnumberid;
    }

    @Basic
    @Column(name = "LINETEMPLATECODE", nullable = true, length = 3)
    public String getLinetemplatecode() {
        return linetemplatecode;
    }

    public void setLinetemplatecode(String linetemplatecode) {
        this.linetemplatecode = linetemplatecode;
    }

    @Basic
    @Column(name = "SAMPLESTYPE", nullable = false, length = 2)
    public String getSamplestype() {
        return samplestype;
    }

    public void setSamplestype(String samplestype) {
        this.samplestype = samplestype;
    }

    @Basic
    @Column(name = "BOXMANAGED", nullable = false)
    public Short getBoxmanaged() {
        return boxmanaged;
    }

    public void setBoxmanaged(Short boxmanaged) {
        this.boxmanaged = boxmanaged;
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
    @Column(name = "ITEMTYPEAFICOMPANYCODE", nullable = true, length = 3)
    public String getItemtypeaficompanycode() {
        return itemtypeaficompanycode;
    }

    public void setItemtypeaficompanycode(String itemtypeaficompanycode) {
        this.itemtypeaficompanycode = itemtypeaficompanycode;
    }

    @Basic
    @Column(name = "ITEMTYPEAFICODE", nullable = true, length = 3)
    public String getItemtypeaficode() {
        return itemtypeaficode;
    }

    public void setItemtypeaficode(String itemtypeaficode) {
        this.itemtypeaficode = itemtypeaficode;
    }

    @Basic
    @Column(name = "ITEMNATURE", nullable = false, length = 1)
    public String getItemnature() {
        return itemnature;
    }

    public void setItemnature(String itemnature) {
        this.itemnature = itemnature;
    }

    @Basic
    @Column(name = "SUBCODE01", nullable = true, length = 20)
    public String getSubcode01() {
        return subcode01;
    }

    public void setSubcode01(String subcode01) {
        this.subcode01 = subcode01;
    }

    @Basic
    @Column(name = "SUBCODE02", nullable = true, length = 10)
    public String getSubcode02() {
        return subcode02;
    }

    public void setSubcode02(String subcode02) {
        this.subcode02 = subcode02;
    }

    @Basic
    @Column(name = "SUBCODE03", nullable = true, length = 10)
    public String getSubcode03() {
        return subcode03;
    }

    public void setSubcode03(String subcode03) {
        this.subcode03 = subcode03;
    }

    @Basic
    @Column(name = "SUBCODE04", nullable = true, length = 10)
    public String getSubcode04() {
        return subcode04;
    }

    public void setSubcode04(String subcode04) {
        this.subcode04 = subcode04;
    }

    @Basic
    @Column(name = "SUBCODE05", nullable = true, length = 10)
    public String getSubcode05() {
        return subcode05;
    }

    public void setSubcode05(String subcode05) {
        this.subcode05 = subcode05;
    }

    @Basic
    @Column(name = "SUBCODE06", nullable = true, length = 10)
    public String getSubcode06() {
        return subcode06;
    }

    public void setSubcode06(String subcode06) {
        this.subcode06 = subcode06;
    }

    @Basic
    @Column(name = "SUBCODE07", nullable = true, length = 10)
    public String getSubcode07() {
        return subcode07;
    }

    public void setSubcode07(String subcode07) {
        this.subcode07 = subcode07;
    }

    @Basic
    @Column(name = "SUBCODE08", nullable = true, length = 10)
    public String getSubcode08() {
        return subcode08;
    }

    public void setSubcode08(String subcode08) {
        this.subcode08 = subcode08;
    }

    @Basic
    @Column(name = "SUBCODE09", nullable = true, length = 10)
    public String getSubcode09() {
        return subcode09;
    }

    public void setSubcode09(String subcode09) {
        this.subcode09 = subcode09;
    }

    @Basic
    @Column(name = "SUBCODE10", nullable = true, length = 10)
    public String getSubcode10() {
        return subcode10;
    }

    public void setSubcode10(String subcode10) {
        this.subcode10 = subcode10;
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
    @Column(name = "SELLINGITEMIDENTIFIER", nullable = true, precision = 0)
    public Integer getSellingitemidentifier() {
        return sellingitemidentifier;
    }

    public void setSellingitemidentifier(Integer sellingitemidentifier) {
        this.sellingitemidentifier = sellingitemidentifier;
    }

    @Basic
    @Column(name = "EXTERNALITEM", nullable = true, length = 50)
    public String getExternalitem() {
        return externalitem;
    }

    public void setExternalitem(String externalitem) {
        this.externalitem = externalitem;
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
    @Column(name = "OBSOLETEDISCARDEDITEM", nullable = false)
    public Integer getObsoletediscardeditem() {
        return obsoletediscardeditem;
    }

    public void setObsoletediscardeditem(Integer obsoletediscardeditem) {
        this.obsoletediscardeditem = obsoletediscardeditem;
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
    @Column(name = "CANCELLEDUSERPRIMARYQUANTITY", nullable = true, precision = 5)
    public BigDecimal getCancelleduserprimaryquantity() {
        return cancelleduserprimaryquantity;
    }

    public void setCancelleduserprimaryquantity(BigDecimal cancelleduserprimaryquantity) {
        this.cancelleduserprimaryquantity = cancelleduserprimaryquantity;
    }

    @Basic
    @Column(name = "CANCELLEDBASEPRIMARYQUANTITY", nullable = true, precision = 5)
    public BigDecimal getCancelledbaseprimaryquantity() {
        return cancelledbaseprimaryquantity;
    }

    public void setCancelledbaseprimaryquantity(BigDecimal cancelledbaseprimaryquantity) {
        this.cancelledbaseprimaryquantity = cancelledbaseprimaryquantity;
    }

    @Basic
    @Column(name = "CANCELLEDUSERSECONDARYQUANTITY", nullable = true, precision = 5)
    public BigDecimal getCancelledusersecondaryquantity() {
        return cancelledusersecondaryquantity;
    }

    public void setCancelledusersecondaryquantity(BigDecimal cancelledusersecondaryquantity) {
        this.cancelledusersecondaryquantity = cancelledusersecondaryquantity;
    }

    @Basic
    @Column(name = "CANCELLEDBASESECONDARYQUANTITY", nullable = true, precision = 5)
    public BigDecimal getCancelledbasesecondaryquantity() {
        return cancelledbasesecondaryquantity;
    }

    public void setCancelledbasesecondaryquantity(BigDecimal cancelledbasesecondaryquantity) {
        this.cancelledbasesecondaryquantity = cancelledbasesecondaryquantity;
    }

    @Basic
    @Column(name = "CANCELLEDUSERPACKAGINGQUANTITY", nullable = true, precision = 5)
    public BigDecimal getCancelleduserpackagingquantity() {
        return cancelleduserpackagingquantity;
    }

    public void setCancelleduserpackagingquantity(BigDecimal cancelleduserpackagingquantity) {
        this.cancelleduserpackagingquantity = cancelleduserpackagingquantity;
    }

    @Basic
    @Column(name = "ORIGINALUSERPRIMARYQUANTITY", nullable = true, precision = 5)
    public BigDecimal getOriginaluserprimaryquantity() {
        return originaluserprimaryquantity;
    }

    public void setOriginaluserprimaryquantity(BigDecimal originaluserprimaryquantity) {
        this.originaluserprimaryquantity = originaluserprimaryquantity;
    }

    @Basic
    @Column(name = "ORIGINALUSERSECONDARYQUANTITY", nullable = true, precision = 5)
    public BigDecimal getOriginalusersecondaryquantity() {
        return originalusersecondaryquantity;
    }

    public void setOriginalusersecondaryquantity(BigDecimal originalusersecondaryquantity) {
        this.originalusersecondaryquantity = originalusersecondaryquantity;
    }

    @Basic
    @Column(name = "ORIGINALUSERPACKAGINGQUANTITY", nullable = true, precision = 5)
    public BigDecimal getOriginaluserpackagingquantity() {
        return originaluserpackagingquantity;
    }

    public void setOriginaluserpackagingquantity(BigDecimal originaluserpackagingquantity) {
        this.originaluserpackagingquantity = originaluserpackagingquantity;
    }

    @Basic
    @Column(name = "QUALITYCODE", nullable = true, precision = 0)
    public BigInteger getQualitycode() {
        return qualitycode;
    }

    public void setQualitycode(BigInteger qualitycode) {
        this.qualitycode = qualitycode;
    }

    @Basic
    @Column(name = "LINESTATUS", nullable = false, length = 2)
    public String getLinestatus() {
        return linestatus;
    }

    public void setLinestatus(String linestatus) {
        this.linestatus = linestatus;
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
    @Column(name = "LINEGROUP", nullable = true, length = 3)
    public String getLinegroup() {
        return linegroup;
    }

    public void setLinegroup(String linegroup) {
        this.linegroup = linegroup;
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
    @Column(name = "UPDATEWAREHOUSEAVAILABILITY", nullable = false)
    public Short getUpdatewarehouseavailability() {
        return updatewarehouseavailability;
    }

    public void setUpdatewarehouseavailability(Short updatewarehouseavailability) {
        this.updatewarehouseavailability = updatewarehouseavailability;
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
    @Column(name = "DISTRIBUTIONWAREHOUSEREQUIRED", nullable = false)
    public Short getDistributionwarehouserequired() {
        return distributionwarehouserequired;
    }

    public void setDistributionwarehouserequired(Short distributionwarehouserequired) {
        this.distributionwarehouserequired = distributionwarehouserequired;
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
    @Column(name = "LEFTOVERLOSS", nullable = false)
    public Short getLeftoverloss() {
        return leftoverloss;
    }

    public void setLeftoverloss(Short leftoverloss) {
        this.leftoverloss = leftoverloss;
    }

    @Basic
    @Column(name = "JOINEDORDERLINE", nullable = true, precision = 0)
    public Integer getJoinedorderline() {
        return joinedorderline;
    }

    public void setJoinedorderline(Integer joinedorderline) {
        this.joinedorderline = joinedorderline;
    }

    @Basic
    @Column(name = "JOINEDORDERSUBLINE", nullable = true, precision = 0)
    public Integer getJoinedordersubline() {
        return joinedordersubline;
    }

    public void setJoinedordersubline(Integer joinedordersubline) {
        this.joinedordersubline = joinedordersubline;
    }

    @Basic
    @Column(name = "JOINEDCOMPONENTORDERLINE", nullable = true, precision = 0)
    public Integer getJoinedcomponentorderline() {
        return joinedcomponentorderline;
    }

    public void setJoinedcomponentorderline(Integer joinedcomponentorderline) {
        this.joinedcomponentorderline = joinedcomponentorderline;
    }

    @Basic
    @Column(name = "LINESOURCE", nullable = false, length = 2)
    public String getLinesource() {
        return linesource;
    }

    public void setLinesource(String linesource) {
        this.linesource = linesource;
    }

    @Basic
    @Column(name = "PREVIOUSLINETEMPLATECODE", nullable = true, length = 3)
    public String getPreviouslinetemplatecode() {
        return previouslinetemplatecode;
    }

    public void setPreviouslinetemplatecode(String previouslinetemplatecode) {
        this.previouslinetemplatecode = previouslinetemplatecode;
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
    @Column(name = "PREVIOUSDOCUMENTTYPEORDERTYPE", nullable = true, length = 1)
    public String getPreviousdocumenttypeordertype() {
        return previousdocumenttypeordertype;
    }

    public void setPreviousdocumenttypeordertype(String previousdocumenttypeordertype) {
        this.previousdocumenttypeordertype = previousdocumenttypeordertype;
    }

    @Basic
    @Column(name = "PREVIOUSDOCUMENTTYPETYPE", nullable = true, length = 3)
    public String getPreviousdocumenttypetype() {
        return previousdocumenttypetype;
    }

    public void setPreviousdocumenttypetype(String previousdocumenttypetype) {
        this.previousdocumenttypetype = previousdocumenttypetype;
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
    @Column(name = "PREVIOUSORDERLINE", nullable = true, precision = 0)
    public Integer getPreviousorderline() {
        return previousorderline;
    }

    public void setPreviousorderline(Integer previousorderline) {
        this.previousorderline = previousorderline;
    }

    @Basic
    @Column(name = "PREVIOUSORDERSUBLINE", nullable = true, precision = 0)
    public Integer getPreviousordersubline() {
        return previousordersubline;
    }

    public void setPreviousordersubline(Integer previousordersubline) {
        this.previousordersubline = previousordersubline;
    }

    @Basic
    @Column(name = "PREVIOUSCOMPONENTORDERLINE", nullable = true, precision = 0)
    public Integer getPreviouscomponentorderline() {
        return previouscomponentorderline;
    }

    public void setPreviouscomponentorderline(Integer previouscomponentorderline) {
        this.previouscomponentorderline = previouscomponentorderline;
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
    @Column(name = "PREVIOUSDELIVERYLINE", nullable = true, precision = 0)
    public Integer getPreviousdeliveryline() {
        return previousdeliveryline;
    }

    public void setPreviousdeliveryline(Integer previousdeliveryline) {
        this.previousdeliveryline = previousdeliveryline;
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
    @Column(name = "CONDITIONRETRIEVINGDATE", nullable = false)
    public Date getConditionretrievingdate() {
        return conditionretrievingdate;
    }

    public void setConditionretrievingdate(Date conditionretrievingdate) {
        this.conditionretrievingdate = conditionretrievingdate;
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
    @Column(name = "LINEUSERVALUE", nullable = true, length = 20)
    public String getLineuservalue() {
        return lineuservalue;
    }

    public void setLineuservalue(String lineuservalue) {
        this.lineuservalue = lineuservalue;
    }

    @Basic
    @Column(name = "PRICEUNITOFMEASURECODE", nullable = true, length = 3)
    public String getPriceunitofmeasurecode() {
        return priceunitofmeasurecode;
    }

    public void setPriceunitofmeasurecode(String priceunitofmeasurecode) {
        this.priceunitofmeasurecode = priceunitofmeasurecode;
    }

    @Basic
    @Column(name = "PRICE", nullable = true, precision = 5)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "PRICETYPE", nullable = true, length = 2)
    public String getPricetype() {
        return pricetype;
    }

    public void setPricetype(String pricetype) {
        this.pricetype = pricetype;
    }

    @Basic
    @Column(name = "PRICESIGN", nullable = false, length = 2)
    public String getPricesign() {
        return pricesign;
    }

    public void setPricesign(String pricesign) {
        this.pricesign = pricesign;
    }

    @Basic
    @Column(name = "PRICEINCLUDINGTAX", nullable = false)
    public Short getPriceincludingtax() {
        return priceincludingtax;
    }

    public void setPriceincludingtax(Short priceincludingtax) {
        this.priceincludingtax = priceincludingtax;
    }

    @Basic
    @Column(name = "PRICERETRIEVED", nullable = true, precision = 5)
    public BigDecimal getPriceretrieved() {
        return priceretrieved;
    }

    public void setPriceretrieved(BigDecimal priceretrieved) {
        this.priceretrieved = priceretrieved;
    }

    @Basic
    @Column(name = "ORIGINALAMOUNT", nullable = true, precision = 5)
    public BigDecimal getOriginalamount() {
        return originalamount;
    }

    public void setOriginalamount(BigDecimal originalamount) {
        this.originalamount = originalamount;
    }

    @Basic
    @Column(name = "NETVALUE", nullable = true, precision = 5)
    public BigDecimal getNetvalue() {
        return netvalue;
    }

    public void setNetvalue(BigDecimal netvalue) {
        this.netvalue = netvalue;
    }

    @Basic
    @Column(name = "UPDATEVALUE", nullable = false)
    public Short getUpdatevalue() {
        return updatevalue;
    }

    public void setUpdatevalue(Short updatevalue) {
        this.updatevalue = updatevalue;
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
    @Column(name = "FREEGIFTTAXDEBIT", nullable = true, length = 1)
    public String getFreegifttaxdebit() {
        return freegifttaxdebit;
    }

    public void setFreegifttaxdebit(String freegifttaxdebit) {
        this.freegifttaxdebit = freegifttaxdebit;
    }

    @Basic
    @Column(name = "TAXABLEINCOMEVALUE", nullable = true, precision = 5)
    public BigDecimal getTaxableincomevalue() {
        return taxableincomevalue;
    }

    public void setTaxableincomevalue(BigDecimal taxableincomevalue) {
        this.taxableincomevalue = taxableincomevalue;
    }

    @Basic
    @Column(name = "NETVALUEINCLUDINGTAX", nullable = true, precision = 5)
    public BigDecimal getNetvalueincludingtax() {
        return netvalueincludingtax;
    }

    public void setNetvalueincludingtax(BigDecimal netvalueincludingtax) {
        this.netvalueincludingtax = netvalueincludingtax;
    }

    @Basic
    @Column(name = "ONLYBYAMOUNT", nullable = false)
    public Short getOnlybyamount() {
        return onlybyamount;
    }

    public void setOnlybyamount(Short onlybyamount) {
        this.onlybyamount = onlybyamount;
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
    @Column(name = "MANUALLYINSERTFORBOX", nullable = false)
    public Short getManuallyinsertforbox() {
        return manuallyinsertforbox;
    }

    public void setManuallyinsertforbox(Short manuallyinsertforbox) {
        this.manuallyinsertforbox = manuallyinsertforbox;
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
    @Column(name = "POUSERPRIMARYQUANTITY", nullable = true, precision = 5)
    public BigDecimal getPouserprimaryquantity() {
        return pouserprimaryquantity;
    }

    public void setPouserprimaryquantity(BigDecimal pouserprimaryquantity) {
        this.pouserprimaryquantity = pouserprimaryquantity;
    }

    @Basic
    @Column(name = "POUSERSECONDARYQUANTITY", nullable = true, precision = 5)
    public BigDecimal getPousersecondaryquantity() {
        return pousersecondaryquantity;
    }

    public void setPousersecondaryquantity(BigDecimal pousersecondaryquantity) {
        this.pousersecondaryquantity = pousersecondaryquantity;
    }

    @Basic
    @Column(name = "POUSERPACKAGINGQUANTITY", nullable = true, precision = 5)
    public BigDecimal getPouserpackagingquantity() {
        return pouserpackagingquantity;
    }

    public void setPouserpackagingquantity(BigDecimal pouserpackagingquantity) {
        this.pouserpackagingquantity = pouserpackagingquantity;
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
        Salesorderline that = (Salesorderline) o;
        return Objects.equals(id, that.id) && Objects.equals(divisioncode, that.divisioncode) && Objects.equals(alloweddivisions, that.alloweddivisions) && Objects.equals(ordertype, that.ordertype) && Objects.equals(documenttypetype, that.documenttypetype) && Objects.equals(assortmentnumberid, that.assortmentnumberid) && Objects.equals(linetemplatecode, that.linetemplatecode) && Objects.equals(samplestype, that.samplestype) && Objects.equals(boxmanaged, that.boxmanaged) && Objects.equals(externalreference, that.externalreference) && Objects.equals(externalreferencedate, that.externalreferencedate) && Objects.equals(internalreference, that.internalreference) && Objects.equals(internalreferencedate, that.internalreferencedate) && Objects.equals(itemtypeaficompanycode, that.itemtypeaficompanycode) && Objects.equals(itemtypeaficode, that.itemtypeaficode) && Objects.equals(itemnature, that.itemnature) && Objects.equals(subcode01, that.subcode01) && Objects.equals(subcode02, that.subcode02) && Objects.equals(subcode03, that.subcode03) && Objects.equals(subcode04, that.subcode04) && Objects.equals(subcode05, that.subcode05) && Objects.equals(subcode06, that.subcode06) && Objects.equals(subcode07, that.subcode07) && Objects.equals(subcode08, that.subcode08) && Objects.equals(subcode09, that.subcode09) && Objects.equals(subcode10, that.subcode10) && Objects.equals(fullitemidentifier, that.fullitemidentifier) && Objects.equals(sellingitemidentifier, that.sellingitemidentifier) && Objects.equals(externalitem, that.externalitem) && Objects.equals(itemdescription, that.itemdescription) && Objects.equals(obsoletediscardeditem, that.obsoletediscardeditem) && Objects.equals(userprimaryuomcode, that.userprimaryuomcode) && Objects.equals(userprimaryquantity, that.userprimaryquantity) && Objects.equals(baseprimaryuomcode, that.baseprimaryuomcode) && Objects.equals(baseprimaryquantity, that.baseprimaryquantity) && Objects.equals(usersecondaryuomcode, that.usersecondaryuomcode) && Objects.equals(usersecondaryquantity, that.usersecondaryquantity) && Objects.equals(basesecondaryuomcode, that.basesecondaryuomcode) && Objects.equals(basesecondaryquantity, that.basesecondaryquantity) && Objects.equals(userpackaginguomcode, that.userpackaginguomcode) && Objects.equals(userpackagingquantity, that.userpackagingquantity) && Objects.equals(cancelleduserprimaryquantity, that.cancelleduserprimaryquantity) && Objects.equals(cancelledbaseprimaryquantity, that.cancelledbaseprimaryquantity) && Objects.equals(cancelledusersecondaryquantity, that.cancelledusersecondaryquantity) && Objects.equals(cancelledbasesecondaryquantity, that.cancelledbasesecondaryquantity) && Objects.equals(cancelleduserpackagingquantity, that.cancelleduserpackagingquantity) && Objects.equals(originaluserprimaryquantity, that.originaluserprimaryquantity) && Objects.equals(originalusersecondaryquantity, that.originalusersecondaryquantity) && Objects.equals(originaluserpackagingquantity, that.originaluserpackagingquantity) && Objects.equals(qualitycode, that.qualitycode) && Objects.equals(linestatus, that.linestatus) && Objects.equals(progressstatus, that.progressstatus) && Objects.equals(statisticalgroupcompanycode, that.statisticalgroupcompanycode) && Objects.equals(statisticalgroupcode, that.statisticalgroupcode) && Objects.equals(collectiongroupcompanycode, that.collectiongroupcompanycode) && Objects.equals(collectiongroupcode, that.collectiongroupcode) && Objects.equals(projectcode, that.projectcode) && Objects.equals(linegroup, that.linegroup) && Objects.equals(warehousecompanycode, that.warehousecompanycode) && Objects.equals(warehousecode, that.warehousecode) && Objects.equals(updatewarehouseavailability, that.updatewarehouseavailability) && Objects.equals(cost, that.cost) && Objects.equals(costcentercompanycode, that.costcentercompanycode) && Objects.equals(costcentercode, that.costcentercode) && Objects.equals(distributionwarehouserequired, that.distributionwarehouserequired) && Objects.equals(releasetype, that.releasetype) && Objects.equals(releasepriority, that.releasepriority) && Objects.equals(leftoverloss, that.leftoverloss) && Objects.equals(joinedorderline, that.joinedorderline) && Objects.equals(joinedordersubline, that.joinedordersubline) && Objects.equals(joinedcomponentorderline, that.joinedcomponentorderline) && Objects.equals(linesource, that.linesource) && Objects.equals(previouslinetemplatecode, that.previouslinetemplatecode) && Objects.equals(previouscountercompanycode, that.previouscountercompanycode) && Objects.equals(previouscountercode, that.previouscountercode) && Objects.equals(previousdocumenttypeordertype, that.previousdocumenttypeordertype) && Objects.equals(previousdocumenttypetype, that.previousdocumenttypetype) && Objects.equals(previouscode, that.previouscode) && Objects.equals(previousorderline, that.previousorderline) && Objects.equals(previousordersubline, that.previousordersubline) && Objects.equals(previouscomponentorderline, that.previouscomponentorderline) && Objects.equals(intercompanycode, that.intercompanycode) && Objects.equals(previousdeliveryline, that.previousdeliveryline) && Objects.equals(entryexchangerate, that.entryexchangerate) && Objects.equals(conditionretrievingdate, that.conditionretrievingdate) && Objects.equals(paymentmethodcompanycode, that.paymentmethodcompanycode) && Objects.equals(paymentmethodcode, that.paymentmethodcode) && Objects.equals(pricelistcode, that.pricelistcode) && Objects.equals(discountcategorycode, that.discountcategorycode) && Objects.equals(lineuservalue, that.lineuservalue) && Objects.equals(priceunitofmeasurecode, that.priceunitofmeasurecode) && Objects.equals(price, that.price) && Objects.equals(pricetype, that.pricetype) && Objects.equals(pricesign, that.pricesign) && Objects.equals(priceincludingtax, that.priceincludingtax) && Objects.equals(priceretrieved, that.priceretrieved) && Objects.equals(originalamount, that.originalamount) && Objects.equals(netvalue, that.netvalue) && Objects.equals(updatevalue, that.updatevalue) && Objects.equals(taxcode, that.taxcode) && Objects.equals(freegifttaxdebit, that.freegifttaxdebit) && Objects.equals(taxableincomevalue, that.taxableincomevalue) && Objects.equals(netvalueincludingtax, that.netvalueincludingtax) && Objects.equals(onlybyamount, that.onlybyamount) && Objects.equals(agent1Code, that.agent1Code) && Objects.equals(commissionliquidationtype1, that.commissionliquidationtype1) && Objects.equals(agentcreationtype1, that.agentcreationtype1) && Objects.equals(agent2Code, that.agent2Code) && Objects.equals(commissionliquidationtype2, that.commissionliquidationtype2) && Objects.equals(agentcreationtype2, that.agentcreationtype2) && Objects.equals(agent3Code, that.agent3Code) && Objects.equals(commissionliquidationtype3, that.commissionliquidationtype3) && Objects.equals(agentcreationtype3, that.agentcreationtype3) && Objects.equals(agent4Code, that.agent4Code) && Objects.equals(commissionliquidationtype4, that.commissionliquidationtype4) && Objects.equals(agentcreationtype4, that.agentcreationtype4) && Objects.equals(agent5Code, that.agent5Code) && Objects.equals(commissionliquidationtype5, that.commissionliquidationtype5) && Objects.equals(agentcreationtype5, that.agentcreationtype5) && Objects.equals(manuallyinsertforbox, that.manuallyinsertforbox) && Objects.equals(termsoflogcode, that.termsoflogcode) && Objects.equals(logreasoncode, that.logreasoncode) && Objects.equals(creationdatetime, that.creationdatetime) && Objects.equals(creationuser, that.creationuser) && Objects.equals(lastupdatedatetime, that.lastupdatedatetime) && Objects.equals(lastupdateuser, that.lastupdateuser) && Objects.equals(absuniqueid, that.absuniqueid) && Objects.equals(purordprncustomersuppliertype, that.purordprncustomersuppliertype) && Objects.equals(purordprncustomersuppliercode, that.purordprncustomersuppliercode) && Objects.equals(pouserprimaryquantity, that.pouserprimaryquantity) && Objects.equals(pousersecondaryquantity, that.pousersecondaryquantity) && Objects.equals(pouserpackagingquantity, that.pouserpackagingquantity) && Objects.equals(keepentryexchangerate, that.keepentryexchangerate) && Objects.equals(creationdatetimeutc, that.creationdatetimeutc) && Objects.equals(lastupdatedatetimeutc, that.lastupdatedatetimeutc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, divisioncode, alloweddivisions, ordertype, documenttypetype, assortmentnumberid, linetemplatecode, samplestype, boxmanaged, externalreference, externalreferencedate, internalreference, internalreferencedate, itemtypeaficompanycode, itemtypeaficode, itemnature, subcode01, subcode02, subcode03, subcode04, subcode05, subcode06, subcode07, subcode08, subcode09, subcode10, fullitemidentifier, sellingitemidentifier, externalitem, itemdescription, obsoletediscardeditem, userprimaryuomcode, userprimaryquantity, baseprimaryuomcode, baseprimaryquantity, usersecondaryuomcode, usersecondaryquantity, basesecondaryuomcode, basesecondaryquantity, userpackaginguomcode, userpackagingquantity, cancelleduserprimaryquantity, cancelledbaseprimaryquantity, cancelledusersecondaryquantity, cancelledbasesecondaryquantity, cancelleduserpackagingquantity, originaluserprimaryquantity, originalusersecondaryquantity, originaluserpackagingquantity, qualitycode, linestatus, progressstatus, statisticalgroupcompanycode, statisticalgroupcode, collectiongroupcompanycode, collectiongroupcode, projectcode, linegroup, warehousecompanycode, warehousecode, updatewarehouseavailability, cost, costcentercompanycode, costcentercode, distributionwarehouserequired, releasetype, releasepriority, leftoverloss, joinedorderline, joinedordersubline, joinedcomponentorderline, linesource, previouslinetemplatecode, previouscountercompanycode, previouscountercode, previousdocumenttypeordertype, previousdocumenttypetype, previouscode, previousorderline, previousordersubline, previouscomponentorderline, intercompanycode, previousdeliveryline, entryexchangerate, conditionretrievingdate, paymentmethodcompanycode, paymentmethodcode, pricelistcode, discountcategorycode, lineuservalue, priceunitofmeasurecode, price, pricetype, pricesign, priceincludingtax, priceretrieved, originalamount, netvalue, updatevalue, taxcode, freegifttaxdebit, taxableincomevalue, netvalueincludingtax, onlybyamount, agent1Code, commissionliquidationtype1, agentcreationtype1, agent2Code, commissionliquidationtype2, agentcreationtype2, agent3Code, commissionliquidationtype3, agentcreationtype3, agent4Code, commissionliquidationtype4, agentcreationtype4, agent5Code, commissionliquidationtype5, agentcreationtype5, manuallyinsertforbox, termsoflogcode, logreasoncode, creationdatetime, creationuser, lastupdatedatetime, lastupdateuser, absuniqueid, purordprncustomersuppliertype, purordprncustomersuppliercode, pouserprimaryquantity, pousersecondaryquantity, pouserpackagingquantity, keepentryexchangerate, creationdatetimeutc, lastupdatedatetimeutc);
    }
}

