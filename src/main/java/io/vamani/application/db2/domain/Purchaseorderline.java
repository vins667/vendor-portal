package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "PURCHASEORDERLINE")
public class Purchaseorderline {
    @EmbeddedId
    private PurchaseorderlineId id;
    @Basic
    @Column(name = "ABSVERSIONNUMBER")
    private Long absversionnumber;
    @Basic
    @Column(name = "DIVISIONCODE")
    private String divisioncode;
    @Basic
    @Column(name = "ALLOWEDDIVISIONS")
    private String alloweddivisions;
    @Basic
    @Column(name = "ORDERTYPE")
    private String ordertype;
    @Basic
    @Column(name = "DOCUMENTTYPETYPE")
    private String documenttypetype;
    @Basic
    @Column(name = "ASSORTMENTNUMBERID")
    private Integer assortmentnumberid;
    @Basic
    @Column(name = "LINETEMPLATECODE")
    private String linetemplatecode;
    @Basic
    @Column(name = "OPERATIONCODE")
    private String operationcode;
    @Basic
    @Column(name = "EXTERNALOPERATIONREQUIRED")
    private Boolean externaloperationrequired;
    @Basic
    @Column(name = "BOXMANAGED")
    private Boolean boxmanaged;
    @Basic
    @Column(name = "ITEMTYPEAFICOMPANYCODE")
    private String itemtypeaficompanycode;
    @Basic
    @Column(name = "ITEMTYPEAFICODE")
    private String itemtypeaficode;
    @Basic
    @Column(name = "ITEMNATURE")
    private String itemnature;
    @Basic
    @Column(name = "SUBCODE01")
    private String subcode01;
    @Basic
    @Column(name = "SUBCODE02")
    private String subcode02;
    @Basic
    @Column(name = "SUBCODE03")
    private String subcode03;
    @Basic
    @Column(name = "SUBCODE04")
    private String subcode04;
    @Basic
    @Column(name = "SUBCODE05")
    private String subcode05;
    @Basic
    @Column(name = "SUBCODE06")
    private String subcode06;
    @Basic
    @Column(name = "SUBCODE07")
    private String subcode07;
    @Basic
    @Column(name = "SUBCODE08")
    private String subcode08;
    @Basic
    @Column(name = "SUBCODE09")
    private String subcode09;
    @Basic
    @Column(name = "SUBCODE10")
    private String subcode10;
    @Basic
    @Column(name = "FULLITEMIDENTIFIER")
    private Integer fullitemidentifier;
    @Basic
    @Column(name = "EXTERNALITEM")
    private String externalitem;
    @Basic
    @Column(name = "ITEMDESCRIPTION")
    private String itemdescription;
    @Basic
    @Column(name = "BOMNUMBERID")
    private Integer bomnumberid;
    @Basic
    @Column(name = "OBSOLETEDISCARDEDITEM")
    private Integer obsoletediscardeditem;
    @Basic
    @Column(name = "USERPRIMARYUOMCODE")
    private String userprimaryuomcode;
    @Basic
    @Column(name = "USERPRIMARYQUANTITY")
    private BigDecimal userprimaryquantity;
    @Basic
    @Column(name = "BASEPRIMARYUOMCODE")
    private String baseprimaryuomcode;
    @Basic
    @Column(name = "BASEPRIMARYQUANTITY")
    private BigDecimal baseprimaryquantity;
    @Basic
    @Column(name = "USERSECONDARYUOMCODE")
    private String usersecondaryuomcode;
    @Basic
    @Column(name = "USERSECONDARYQUANTITY")
    private BigDecimal usersecondaryquantity;
    @Basic
    @Column(name = "BASESECONDARYUOMCODE")
    private String basesecondaryuomcode;
    @Basic
    @Column(name = "BASESECONDARYQUANTITY")
    private BigDecimal basesecondaryquantity;
    @Basic
    @Column(name = "USERPACKAGINGUOMCODE")
    private String userpackaginguomcode;
    @Basic
    @Column(name = "USERPACKAGINGQUANTITY")
    private BigDecimal userpackagingquantity;
    @Basic
    @Column(name = "CANCELLEDUSERPRIMARYQUANTITY")
    private BigDecimal cancelleduserprimaryquantity;
    @Basic
    @Column(name = "CANCELLEDBASEPRIMARYQUANTITY")
    private BigDecimal cancelledbaseprimaryquantity;
    @Basic
    @Column(name = "CANCELLEDUSERSECONDARYQUANTITY")
    private BigDecimal cancelledusersecondaryquantity;
    @Basic
    @Column(name = "CANCELLEDBASESECONDARYQUANTITY")
    private BigDecimal cancelledbasesecondaryquantity;
    @Basic
    @Column(name = "CANCELLEDUSERPACKAGINGQUANTITY")
    private BigDecimal cancelleduserpackagingquantity;
    @Basic
    @Column(name = "ORIGINALUSERPRIMARYQUANTITY")
    private BigDecimal originaluserprimaryquantity;
    @Basic
    @Column(name = "ORIGINALUSERSECONDARYQUANTITY")
    private BigDecimal originalusersecondaryquantity;
    @Basic
    @Column(name = "ORIGINALUSERPACKAGINGQUANTITY")
    private BigDecimal originaluserpackagingquantity;
    @Basic
    @Column(name = "BUYERCOMPANYCODE")
    private String buyercompanycode;
    @Basic
    @Column(name = "BUYERCODE")
    private String buyercode;
    @Basic
    @Column(name = "QUALITYCODE")
    private BigInteger qualitycode;
    @Basic
    @Column(name = "LINESTATUS")
    private String linestatus;
    @Basic
    @Column(name = "PROGRESSSTATUS")
    private String progressstatus;
    @Basic
    @Column(name = "STATISTICALGROUPCOMPANYCODE")
    private String statisticalgroupcompanycode;
    @Basic
    @Column(name = "STATISTICALGROUPCODE")
    private String statisticalgroupcode;
    @Basic
    @Column(name = "COLLECTIONGROUPCOMPANYCODE")
    private String collectiongroupcompanycode;
    @Basic
    @Column(name = "COLLECTIONGROUPCODE")
    private String collectiongroupcode;
    @Basic
    @Column(name = "PROJECTCODE")
    private String projectcode;
    @Basic
    @Column(name = "CUSTOMERTYPE")
    private String customertype;
    @Basic
    @Column(name = "CUSTOMERCODE")
    private String customercode;
    @Basic
    @Column(name = "LINEGROUP")
    private String linegroup;
    @Basic
    @Column(name = "WAREHOUSECOMPANYCODE")
    private String warehousecompanycode;
    @Basic
    @Column(name = "WAREHOUSECODE")
    private String warehousecode;
    @Basic
    @Column(name = "ISSUEWAREHOUSECOMPANYCODE")
    private String issuewarehousecompanycode;
    @Basic
    @Column(name = "ISSUEWAREHOUSECODE")
    private String issuewarehousecode;
    @Basic
    @Column(name = "SBCWAREHOUSECOMPANYCODE")
    private String sbcwarehousecompanycode;
    @Basic
    @Column(name = "SUBCONTRACTORWAREHOUSECODE")
    private String subcontractorwarehousecode;
    @Basic
    @Column(name = "UPDATEWAREHOUSEAVAILABILITY")
    private Boolean updatewarehouseavailability;
    @Basic
    @Column(name = "COST")
    private BigDecimal cost;
    @Basic
    @Column(name = "COSTCENTERCOMPANYCODE")
    private String costcentercompanycode;
    @Basic
    @Column(name = "COSTCENTERCODE")
    private String costcentercode;
    @Basic
    @Column(name = "DELIVERYDESCRIPTION")
    private String deliverydescription;
    @Basic
    @Column(name = "SHIPPINGDESCRIPTION")
    private String shippingdescription;
    @Basic
    @Column(name = "TRANSPORTREASONDESCRIPTION")
    private String transportreasondescription;
    @Basic
    @Column(name = "RELEASETYPE")
    private String releasetype;
    @Basic
    @Column(name = "LEFTOVERLOSS")
    private Boolean leftoverloss;
    @Basic
    @Column(name = "JOINEDORDERLINE")
    private Integer joinedorderline;
    @Basic
    @Column(name = "JOINEDORDERSUBLINE")
    private Integer joinedordersubline;
    @Basic
    @Column(name = "LINESOURCE")
    private String linesource;
    @Basic
    @Column(name = "PREVIOUSLINETEMPLATECODE")
    private String previouslinetemplatecode;
    @Basic
    @Column(name = "PREVIOUSCOUNTERCOMPANYCODE")
    private String previouscountercompanycode;
    @Basic
    @Column(name = "PREVIOUSCOUNTERCODE")
    private String previouscountercode;
    @Basic
    @Column(name = "PREVIOUSDOCUMENTTYPEORDERTYPE")
    private String previousdocumenttypeordertype;
    @Basic
    @Column(name = "PREVIOUSDOCUMENTTYPETYPE")
    private String previousdocumenttypetype;
    @Basic
    @Column(name = "PREVIOUSCODE")
    private String previouscode;
    @Basic
    @Column(name = "PREVIOUSORDERLINE")
    private Integer previousorderline;
    @Basic
    @Column(name = "PREVIOUSORDERSUBLINE")
    private Integer previousordersubline;
    @Basic
    @Column(name = "PREVIOUSCOMPONENTORDERLINE")
    private Integer previouscomponentorderline;
    @Basic
    @Column(name = "PREVIOUSDELIVERYLINE")
    private Integer previousdeliveryline;
    @Basic
    @Column(name = "SENDTOSUPPLIER")
    private Boolean sendtosupplier;
    @Basic
    @Column(name = "ENTRYEXCHANGERATE")
    private BigDecimal entryexchangerate;
    @Basic
    @Column(name = "CONDITIONRETRIEVINGDATE")
    private Date conditionretrievingdate;
    @Basic
    @Column(name = "PAYMENTMETHODCOMPANYCODE")
    private String paymentmethodcompanycode;
    @Basic
    @Column(name = "PAYMENTMETHODCODE")
    private String paymentmethodcode;
    @Basic
    @Column(name = "PRICELISTCODE")
    private String pricelistcode;
    @Basic
    @Column(name = "LINEUSERVALUE")
    private String lineuservalue;
    @Basic
    @Column(name = "PRICEUNITOFMEASURECODE")
    private String priceunitofmeasurecode;
    @Basic
    @Column(name = "PRICE")
    private BigDecimal price;
    @Basic
    @Column(name = "PRICETYPE")
    private String pricetype;
    @Basic
    @Column(name = "PRICESIGN")
    private String pricesign;
    @Basic
    @Column(name = "PRICEINCLUDINGTAX")
    private Boolean priceincludingtax;
    @Basic
    @Column(name = "PRICERETRIEVED")
    private BigDecimal priceretrieved;
    @Basic
    @Column(name = "ORIGINALAMOUNT")
    private BigDecimal originalamount;
    @Basic
    @Column(name = "NETVALUE")
    private BigDecimal netvalue;
    @Basic
    @Column(name = "UPDATEVALUE")
    private Boolean updatevalue;
    @Basic
    @Column(name = "TAXCODE")
    private String taxcode;
    @Basic
    @Column(name = "FREEGIFTTAXDEBIT")
    private String freegifttaxdebit;
    @Basic
    @Column(name = "TAXABLEINCOMEVALUE")
    private BigDecimal taxableincomevalue;
    @Basic
    @Column(name = "NETVALUEINCLUDINGTAX")
    private BigDecimal netvalueincludingtax;
    @Basic
    @Column(name = "ONLYBYAMOUNT")
    private Boolean onlybyamount;
    @Basic
    @Column(name = "MANUALLYINSERTFORBOX")
    private Boolean manuallyinsertforbox;
    @Basic
    @Column(name = "TERMSOFLOGCODE")
    private String termsoflogcode;
    @Basic
    @Column(name = "LOGREASONCODE")
    private String logreasoncode;
    @Basic
    @Column(name = "CREATIONDATETIME")
    private Timestamp creationdatetime;
    @Basic
    @Column(name = "CREATIONUSER")
    private String creationuser;
    @Basic
    @Column(name = "LASTUPDATEDATETIME")
    private Timestamp lastupdatedatetime;
    @Basic
    @Column(name = "LASTUPDATEUSER")
    private String lastupdateuser;
    @Basic
    @Column(name = "ABSUNIQUEID")
    private Long absuniqueid;
    @Basic
    @Column(name = "CREATIONDATETIMEUTC")
    private Timestamp creationdatetimeutc;
    @Basic
    @Column(name = "LASTUPDATEDATETIMEUTC")
    private Timestamp lastupdatedatetimeutc;

    public PurchaseorderlineId getId() {
        return id;
    }

    public void setId(PurchaseorderlineId id) {
        this.id = id;
    }

    public Long getAbsversionnumber() {
        return absversionnumber;
    }

    public void setAbsversionnumber(Long absversionnumber) {
        this.absversionnumber = absversionnumber;
    }

    public String getDivisioncode() {
        return divisioncode;
    }

    public void setDivisioncode(String divisioncode) {
        this.divisioncode = divisioncode;
    }

    public String getAlloweddivisions() {
        return alloweddivisions;
    }

    public void setAlloweddivisions(String alloweddivisions) {
        this.alloweddivisions = alloweddivisions;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    public String getDocumenttypetype() {
        return documenttypetype;
    }

    public void setDocumenttypetype(String documenttypetype) {
        this.documenttypetype = documenttypetype;
    }

    public Integer getAssortmentnumberid() {
        return assortmentnumberid;
    }

    public void setAssortmentnumberid(Integer assortmentnumberid) {
        this.assortmentnumberid = assortmentnumberid;
    }

    public String getLinetemplatecode() {
        return linetemplatecode;
    }

    public void setLinetemplatecode(String linetemplatecode) {
        this.linetemplatecode = linetemplatecode;
    }

    public String getOperationcode() {
        return operationcode;
    }

    public void setOperationcode(String operationcode) {
        this.operationcode = operationcode;
    }

    public Boolean getExternaloperationrequired() {
        return externaloperationrequired;
    }

    public void setExternaloperationrequired(Boolean externaloperationrequired) {
        this.externaloperationrequired = externaloperationrequired;
    }

    public Boolean getBoxmanaged() {
        return boxmanaged;
    }

    public void setBoxmanaged(Boolean boxmanaged) {
        this.boxmanaged = boxmanaged;
    }

    public String getItemtypeaficompanycode() {
        return itemtypeaficompanycode;
    }

    public void setItemtypeaficompanycode(String itemtypeaficompanycode) {
        this.itemtypeaficompanycode = itemtypeaficompanycode;
    }

    public String getItemtypeaficode() {
        return itemtypeaficode;
    }

    public void setItemtypeaficode(String itemtypeaficode) {
        this.itemtypeaficode = itemtypeaficode;
    }

    public String getItemnature() {
        return itemnature;
    }

    public void setItemnature(String itemnature) {
        this.itemnature = itemnature;
    }

    public String getSubcode01() {
        return subcode01;
    }

    public void setSubcode01(String subcode01) {
        this.subcode01 = subcode01;
    }

    public String getSubcode02() {
        return subcode02;
    }

    public void setSubcode02(String subcode02) {
        this.subcode02 = subcode02;
    }

    public String getSubcode03() {
        return subcode03;
    }

    public void setSubcode03(String subcode03) {
        this.subcode03 = subcode03;
    }

    public String getSubcode04() {
        return subcode04;
    }

    public void setSubcode04(String subcode04) {
        this.subcode04 = subcode04;
    }

    public String getSubcode05() {
        return subcode05;
    }

    public void setSubcode05(String subcode05) {
        this.subcode05 = subcode05;
    }

    public String getSubcode06() {
        return subcode06;
    }

    public void setSubcode06(String subcode06) {
        this.subcode06 = subcode06;
    }

    public String getSubcode07() {
        return subcode07;
    }

    public void setSubcode07(String subcode07) {
        this.subcode07 = subcode07;
    }

    public String getSubcode08() {
        return subcode08;
    }

    public void setSubcode08(String subcode08) {
        this.subcode08 = subcode08;
    }

    public String getSubcode09() {
        return subcode09;
    }

    public void setSubcode09(String subcode09) {
        this.subcode09 = subcode09;
    }

    public String getSubcode10() {
        return subcode10;
    }

    public void setSubcode10(String subcode10) {
        this.subcode10 = subcode10;
    }

    public Integer getFullitemidentifier() {
        return fullitemidentifier;
    }

    public void setFullitemidentifier(Integer fullitemidentifier) {
        this.fullitemidentifier = fullitemidentifier;
    }

    public String getExternalitem() {
        return externalitem;
    }

    public void setExternalitem(String externalitem) {
        this.externalitem = externalitem;
    }

    public String getItemdescription() {
        return itemdescription;
    }

    public void setItemdescription(String itemdescription) {
        this.itemdescription = itemdescription;
    }

    public Integer getBomnumberid() {
        return bomnumberid;
    }

    public void setBomnumberid(Integer bomnumberid) {
        this.bomnumberid = bomnumberid;
    }

    public Integer getObsoletediscardeditem() {
        return obsoletediscardeditem;
    }

    public void setObsoletediscardeditem(Integer obsoletediscardeditem) {
        this.obsoletediscardeditem = obsoletediscardeditem;
    }

    public String getUserprimaryuomcode() {
        return userprimaryuomcode;
    }

    public void setUserprimaryuomcode(String userprimaryuomcode) {
        this.userprimaryuomcode = userprimaryuomcode;
    }

    public BigDecimal getUserprimaryquantity() {
        return userprimaryquantity;
    }

    public void setUserprimaryquantity(BigDecimal userprimaryquantity) {
        this.userprimaryquantity = userprimaryquantity;
    }

    public String getBaseprimaryuomcode() {
        return baseprimaryuomcode;
    }

    public void setBaseprimaryuomcode(String baseprimaryuomcode) {
        this.baseprimaryuomcode = baseprimaryuomcode;
    }

    public BigDecimal getBaseprimaryquantity() {
        return baseprimaryquantity;
    }

    public void setBaseprimaryquantity(BigDecimal baseprimaryquantity) {
        this.baseprimaryquantity = baseprimaryquantity;
    }

    public String getUsersecondaryuomcode() {
        return usersecondaryuomcode;
    }

    public void setUsersecondaryuomcode(String usersecondaryuomcode) {
        this.usersecondaryuomcode = usersecondaryuomcode;
    }

    public BigDecimal getUsersecondaryquantity() {
        return usersecondaryquantity;
    }

    public void setUsersecondaryquantity(BigDecimal usersecondaryquantity) {
        this.usersecondaryquantity = usersecondaryquantity;
    }

    public String getBasesecondaryuomcode() {
        return basesecondaryuomcode;
    }

    public void setBasesecondaryuomcode(String basesecondaryuomcode) {
        this.basesecondaryuomcode = basesecondaryuomcode;
    }

    public BigDecimal getBasesecondaryquantity() {
        return basesecondaryquantity;
    }

    public void setBasesecondaryquantity(BigDecimal basesecondaryquantity) {
        this.basesecondaryquantity = basesecondaryquantity;
    }

    public String getUserpackaginguomcode() {
        return userpackaginguomcode;
    }

    public void setUserpackaginguomcode(String userpackaginguomcode) {
        this.userpackaginguomcode = userpackaginguomcode;
    }

    public BigDecimal getUserpackagingquantity() {
        return userpackagingquantity;
    }

    public void setUserpackagingquantity(BigDecimal userpackagingquantity) {
        this.userpackagingquantity = userpackagingquantity;
    }

    public BigDecimal getCancelleduserprimaryquantity() {
        return cancelleduserprimaryquantity;
    }

    public void setCancelleduserprimaryquantity(BigDecimal cancelleduserprimaryquantity) {
        this.cancelleduserprimaryquantity = cancelleduserprimaryquantity;
    }

    public BigDecimal getCancelledbaseprimaryquantity() {
        return cancelledbaseprimaryquantity;
    }

    public void setCancelledbaseprimaryquantity(BigDecimal cancelledbaseprimaryquantity) {
        this.cancelledbaseprimaryquantity = cancelledbaseprimaryquantity;
    }

    public BigDecimal getCancelledusersecondaryquantity() {
        return cancelledusersecondaryquantity;
    }

    public void setCancelledusersecondaryquantity(BigDecimal cancelledusersecondaryquantity) {
        this.cancelledusersecondaryquantity = cancelledusersecondaryquantity;
    }

    public BigDecimal getCancelledbasesecondaryquantity() {
        return cancelledbasesecondaryquantity;
    }

    public void setCancelledbasesecondaryquantity(BigDecimal cancelledbasesecondaryquantity) {
        this.cancelledbasesecondaryquantity = cancelledbasesecondaryquantity;
    }

    public BigDecimal getCancelleduserpackagingquantity() {
        return cancelleduserpackagingquantity;
    }

    public void setCancelleduserpackagingquantity(BigDecimal cancelleduserpackagingquantity) {
        this.cancelleduserpackagingquantity = cancelleduserpackagingquantity;
    }

    public BigDecimal getOriginaluserprimaryquantity() {
        return originaluserprimaryquantity;
    }

    public void setOriginaluserprimaryquantity(BigDecimal originaluserprimaryquantity) {
        this.originaluserprimaryquantity = originaluserprimaryquantity;
    }

    public BigDecimal getOriginalusersecondaryquantity() {
        return originalusersecondaryquantity;
    }

    public void setOriginalusersecondaryquantity(BigDecimal originalusersecondaryquantity) {
        this.originalusersecondaryquantity = originalusersecondaryquantity;
    }

    public BigDecimal getOriginaluserpackagingquantity() {
        return originaluserpackagingquantity;
    }

    public void setOriginaluserpackagingquantity(BigDecimal originaluserpackagingquantity) {
        this.originaluserpackagingquantity = originaluserpackagingquantity;
    }

    public String getBuyercompanycode() {
        return buyercompanycode;
    }

    public void setBuyercompanycode(String buyercompanycode) {
        this.buyercompanycode = buyercompanycode;
    }

    public String getBuyercode() {
        return buyercode;
    }

    public void setBuyercode(String buyercode) {
        this.buyercode = buyercode;
    }

    public BigInteger getQualitycode() {
        return qualitycode;
    }

    public void setQualitycode(BigInteger qualitycode) {
        this.qualitycode = qualitycode;
    }

    public String getLinestatus() {
        return linestatus;
    }

    public void setLinestatus(String linestatus) {
        this.linestatus = linestatus;
    }

    public String getProgressstatus() {
        return progressstatus;
    }

    public void setProgressstatus(String progressstatus) {
        this.progressstatus = progressstatus;
    }

    public String getStatisticalgroupcompanycode() {
        return statisticalgroupcompanycode;
    }

    public void setStatisticalgroupcompanycode(String statisticalgroupcompanycode) {
        this.statisticalgroupcompanycode = statisticalgroupcompanycode;
    }

    public String getStatisticalgroupcode() {
        return statisticalgroupcode;
    }

    public void setStatisticalgroupcode(String statisticalgroupcode) {
        this.statisticalgroupcode = statisticalgroupcode;
    }

    public String getCollectiongroupcompanycode() {
        return collectiongroupcompanycode;
    }

    public void setCollectiongroupcompanycode(String collectiongroupcompanycode) {
        this.collectiongroupcompanycode = collectiongroupcompanycode;
    }

    public String getCollectiongroupcode() {
        return collectiongroupcode;
    }

    public void setCollectiongroupcode(String collectiongroupcode) {
        this.collectiongroupcode = collectiongroupcode;
    }

    public String getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    public String getCustomertype() {
        return customertype;
    }

    public void setCustomertype(String customertype) {
        this.customertype = customertype;
    }

    public String getCustomercode() {
        return customercode;
    }

    public void setCustomercode(String customercode) {
        this.customercode = customercode;
    }

    public String getLinegroup() {
        return linegroup;
    }

    public void setLinegroup(String linegroup) {
        this.linegroup = linegroup;
    }

    public String getWarehousecompanycode() {
        return warehousecompanycode;
    }

    public void setWarehousecompanycode(String warehousecompanycode) {
        this.warehousecompanycode = warehousecompanycode;
    }

    public String getWarehousecode() {
        return warehousecode;
    }

    public void setWarehousecode(String warehousecode) {
        this.warehousecode = warehousecode;
    }

    public String getIssuewarehousecompanycode() {
        return issuewarehousecompanycode;
    }

    public void setIssuewarehousecompanycode(String issuewarehousecompanycode) {
        this.issuewarehousecompanycode = issuewarehousecompanycode;
    }

    public String getIssuewarehousecode() {
        return issuewarehousecode;
    }

    public void setIssuewarehousecode(String issuewarehousecode) {
        this.issuewarehousecode = issuewarehousecode;
    }

    public String getSbcwarehousecompanycode() {
        return sbcwarehousecompanycode;
    }

    public void setSbcwarehousecompanycode(String sbcwarehousecompanycode) {
        this.sbcwarehousecompanycode = sbcwarehousecompanycode;
    }

    public String getSubcontractorwarehousecode() {
        return subcontractorwarehousecode;
    }

    public void setSubcontractorwarehousecode(String subcontractorwarehousecode) {
        this.subcontractorwarehousecode = subcontractorwarehousecode;
    }

    public Boolean getUpdatewarehouseavailability() {
        return updatewarehouseavailability;
    }

    public void setUpdatewarehouseavailability(Boolean updatewarehouseavailability) {
        this.updatewarehouseavailability = updatewarehouseavailability;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getCostcentercompanycode() {
        return costcentercompanycode;
    }

    public void setCostcentercompanycode(String costcentercompanycode) {
        this.costcentercompanycode = costcentercompanycode;
    }

    public String getCostcentercode() {
        return costcentercode;
    }

    public void setCostcentercode(String costcentercode) {
        this.costcentercode = costcentercode;
    }

    public String getDeliverydescription() {
        return deliverydescription;
    }

    public void setDeliverydescription(String deliverydescription) {
        this.deliverydescription = deliverydescription;
    }

    public String getShippingdescription() {
        return shippingdescription;
    }

    public void setShippingdescription(String shippingdescription) {
        this.shippingdescription = shippingdescription;
    }

    public String getTransportreasondescription() {
        return transportreasondescription;
    }

    public void setTransportreasondescription(String transportreasondescription) {
        this.transportreasondescription = transportreasondescription;
    }

    public String getReleasetype() {
        return releasetype;
    }

    public void setReleasetype(String releasetype) {
        this.releasetype = releasetype;
    }

    public Boolean getLeftoverloss() {
        return leftoverloss;
    }

    public void setLeftoverloss(Boolean leftoverloss) {
        this.leftoverloss = leftoverloss;
    }

    public Integer getJoinedorderline() {
        return joinedorderline;
    }

    public void setJoinedorderline(Integer joinedorderline) {
        this.joinedorderline = joinedorderline;
    }

    public Integer getJoinedordersubline() {
        return joinedordersubline;
    }

    public void setJoinedordersubline(Integer joinedordersubline) {
        this.joinedordersubline = joinedordersubline;
    }

    public String getLinesource() {
        return linesource;
    }

    public void setLinesource(String linesource) {
        this.linesource = linesource;
    }

    public String getPreviouslinetemplatecode() {
        return previouslinetemplatecode;
    }

    public void setPreviouslinetemplatecode(String previouslinetemplatecode) {
        this.previouslinetemplatecode = previouslinetemplatecode;
    }

    public String getPreviouscountercompanycode() {
        return previouscountercompanycode;
    }

    public void setPreviouscountercompanycode(String previouscountercompanycode) {
        this.previouscountercompanycode = previouscountercompanycode;
    }

    public String getPreviouscountercode() {
        return previouscountercode;
    }

    public void setPreviouscountercode(String previouscountercode) {
        this.previouscountercode = previouscountercode;
    }

    public String getPreviousdocumenttypeordertype() {
        return previousdocumenttypeordertype;
    }

    public void setPreviousdocumenttypeordertype(String previousdocumenttypeordertype) {
        this.previousdocumenttypeordertype = previousdocumenttypeordertype;
    }

    public String getPreviousdocumenttypetype() {
        return previousdocumenttypetype;
    }

    public void setPreviousdocumenttypetype(String previousdocumenttypetype) {
        this.previousdocumenttypetype = previousdocumenttypetype;
    }

    public String getPreviouscode() {
        return previouscode;
    }

    public void setPreviouscode(String previouscode) {
        this.previouscode = previouscode;
    }

    public Integer getPreviousorderline() {
        return previousorderline;
    }

    public void setPreviousorderline(Integer previousorderline) {
        this.previousorderline = previousorderline;
    }

    public Integer getPreviousordersubline() {
        return previousordersubline;
    }

    public void setPreviousordersubline(Integer previousordersubline) {
        this.previousordersubline = previousordersubline;
    }

    public Integer getPreviouscomponentorderline() {
        return previouscomponentorderline;
    }

    public void setPreviouscomponentorderline(Integer previouscomponentorderline) {
        this.previouscomponentorderline = previouscomponentorderline;
    }

    public Integer getPreviousdeliveryline() {
        return previousdeliveryline;
    }

    public void setPreviousdeliveryline(Integer previousdeliveryline) {
        this.previousdeliveryline = previousdeliveryline;
    }

    public Boolean getSendtosupplier() {
        return sendtosupplier;
    }

    public void setSendtosupplier(Boolean sendtosupplier) {
        this.sendtosupplier = sendtosupplier;
    }

    public BigDecimal getEntryexchangerate() {
        return entryexchangerate;
    }

    public void setEntryexchangerate(BigDecimal entryexchangerate) {
        this.entryexchangerate = entryexchangerate;
    }

    public Date getConditionretrievingdate() {
        return conditionretrievingdate;
    }

    public void setConditionretrievingdate(Date conditionretrievingdate) {
        this.conditionretrievingdate = conditionretrievingdate;
    }

    public String getPaymentmethodcompanycode() {
        return paymentmethodcompanycode;
    }

    public void setPaymentmethodcompanycode(String paymentmethodcompanycode) {
        this.paymentmethodcompanycode = paymentmethodcompanycode;
    }

    public String getPaymentmethodcode() {
        return paymentmethodcode;
    }

    public void setPaymentmethodcode(String paymentmethodcode) {
        this.paymentmethodcode = paymentmethodcode;
    }

    public String getPricelistcode() {
        return pricelistcode;
    }

    public void setPricelistcode(String pricelistcode) {
        this.pricelistcode = pricelistcode;
    }

    public String getLineuservalue() {
        return lineuservalue;
    }

    public void setLineuservalue(String lineuservalue) {
        this.lineuservalue = lineuservalue;
    }

    public String getPriceunitofmeasurecode() {
        return priceunitofmeasurecode;
    }

    public void setPriceunitofmeasurecode(String priceunitofmeasurecode) {
        this.priceunitofmeasurecode = priceunitofmeasurecode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPricetype() {
        return pricetype;
    }

    public void setPricetype(String pricetype) {
        this.pricetype = pricetype;
    }

    public String getPricesign() {
        return pricesign;
    }

    public void setPricesign(String pricesign) {
        this.pricesign = pricesign;
    }

    public Boolean getPriceincludingtax() {
        return priceincludingtax;
    }

    public void setPriceincludingtax(Boolean priceincludingtax) {
        this.priceincludingtax = priceincludingtax;
    }

    public BigDecimal getPriceretrieved() {
        return priceretrieved;
    }

    public void setPriceretrieved(BigDecimal priceretrieved) {
        this.priceretrieved = priceretrieved;
    }

    public BigDecimal getOriginalamount() {
        return originalamount;
    }

    public void setOriginalamount(BigDecimal originalamount) {
        this.originalamount = originalamount;
    }

    public BigDecimal getNetvalue() {
        return netvalue;
    }

    public void setNetvalue(BigDecimal netvalue) {
        this.netvalue = netvalue;
    }

    public Boolean getUpdatevalue() {
        return updatevalue;
    }

    public void setUpdatevalue(Boolean updatevalue) {
        this.updatevalue = updatevalue;
    }

    public String getTaxcode() {
        return taxcode;
    }

    public void setTaxcode(String taxcode) {
        this.taxcode = taxcode;
    }

    public String getFreegifttaxdebit() {
        return freegifttaxdebit;
    }

    public void setFreegifttaxdebit(String freegifttaxdebit) {
        this.freegifttaxdebit = freegifttaxdebit;
    }

    public BigDecimal getTaxableincomevalue() {
        return taxableincomevalue;
    }

    public void setTaxableincomevalue(BigDecimal taxableincomevalue) {
        this.taxableincomevalue = taxableincomevalue;
    }

    public BigDecimal getNetvalueincludingtax() {
        return netvalueincludingtax;
    }

    public void setNetvalueincludingtax(BigDecimal netvalueincludingtax) {
        this.netvalueincludingtax = netvalueincludingtax;
    }

    public Boolean getOnlybyamount() {
        return onlybyamount;
    }

    public void setOnlybyamount(Boolean onlybyamount) {
        this.onlybyamount = onlybyamount;
    }

    public Boolean getManuallyinsertforbox() {
        return manuallyinsertforbox;
    }

    public void setManuallyinsertforbox(Boolean manuallyinsertforbox) {
        this.manuallyinsertforbox = manuallyinsertforbox;
    }

    public String getTermsoflogcode() {
        return termsoflogcode;
    }

    public void setTermsoflogcode(String termsoflogcode) {
        this.termsoflogcode = termsoflogcode;
    }

    public String getLogreasoncode() {
        return logreasoncode;
    }

    public void setLogreasoncode(String logreasoncode) {
        this.logreasoncode = logreasoncode;
    }

    public Timestamp getCreationdatetime() {
        return creationdatetime;
    }

    public void setCreationdatetime(Timestamp creationdatetime) {
        this.creationdatetime = creationdatetime;
    }

    public String getCreationuser() {
        return creationuser;
    }

    public void setCreationuser(String creationuser) {
        this.creationuser = creationuser;
    }

    public Timestamp getLastupdatedatetime() {
        return lastupdatedatetime;
    }

    public void setLastupdatedatetime(Timestamp lastupdatedatetime) {
        this.lastupdatedatetime = lastupdatedatetime;
    }

    public String getLastupdateuser() {
        return lastupdateuser;
    }

    public void setLastupdateuser(String lastupdateuser) {
        this.lastupdateuser = lastupdateuser;
    }

    public Long getAbsuniqueid() {
        return absuniqueid;
    }

    public void setAbsuniqueid(Long absuniqueid) {
        this.absuniqueid = absuniqueid;
    }

    public Timestamp getCreationdatetimeutc() {
        return creationdatetimeutc;
    }

    public void setCreationdatetimeutc(Timestamp creationdatetimeutc) {
        this.creationdatetimeutc = creationdatetimeutc;
    }

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
        Purchaseorderline that = (Purchaseorderline) o;
        return Objects.equals(id, that.id) && Objects.equals(absversionnumber, that.absversionnumber) && Objects.equals(divisioncode, that.divisioncode) && Objects.equals(alloweddivisions, that.alloweddivisions) && Objects.equals(ordertype, that.ordertype) && Objects.equals(documenttypetype, that.documenttypetype) && Objects.equals(assortmentnumberid, that.assortmentnumberid) && Objects.equals(linetemplatecode, that.linetemplatecode) && Objects.equals(operationcode, that.operationcode) && Objects.equals(externaloperationrequired, that.externaloperationrequired) && Objects.equals(boxmanaged, that.boxmanaged) && Objects.equals(itemtypeaficompanycode, that.itemtypeaficompanycode) && Objects.equals(itemtypeaficode, that.itemtypeaficode) && Objects.equals(itemnature, that.itemnature) && Objects.equals(subcode01, that.subcode01) && Objects.equals(subcode02, that.subcode02) && Objects.equals(subcode03, that.subcode03) && Objects.equals(subcode04, that.subcode04) && Objects.equals(subcode05, that.subcode05) && Objects.equals(subcode06, that.subcode06) && Objects.equals(subcode07, that.subcode07) && Objects.equals(subcode08, that.subcode08) && Objects.equals(subcode09, that.subcode09) && Objects.equals(subcode10, that.subcode10) && Objects.equals(fullitemidentifier, that.fullitemidentifier) && Objects.equals(externalitem, that.externalitem) && Objects.equals(itemdescription, that.itemdescription) && Objects.equals(bomnumberid, that.bomnumberid) && Objects.equals(obsoletediscardeditem, that.obsoletediscardeditem) && Objects.equals(userprimaryuomcode, that.userprimaryuomcode) && Objects.equals(userprimaryquantity, that.userprimaryquantity) && Objects.equals(baseprimaryuomcode, that.baseprimaryuomcode) && Objects.equals(baseprimaryquantity, that.baseprimaryquantity) && Objects.equals(usersecondaryuomcode, that.usersecondaryuomcode) && Objects.equals(usersecondaryquantity, that.usersecondaryquantity) && Objects.equals(basesecondaryuomcode, that.basesecondaryuomcode) && Objects.equals(basesecondaryquantity, that.basesecondaryquantity) && Objects.equals(userpackaginguomcode, that.userpackaginguomcode) && Objects.equals(userpackagingquantity, that.userpackagingquantity) && Objects.equals(cancelleduserprimaryquantity, that.cancelleduserprimaryquantity) && Objects.equals(cancelledbaseprimaryquantity, that.cancelledbaseprimaryquantity) && Objects.equals(cancelledusersecondaryquantity, that.cancelledusersecondaryquantity) && Objects.equals(cancelledbasesecondaryquantity, that.cancelledbasesecondaryquantity) && Objects.equals(cancelleduserpackagingquantity, that.cancelleduserpackagingquantity) && Objects.equals(originaluserprimaryquantity, that.originaluserprimaryquantity) && Objects.equals(originalusersecondaryquantity, that.originalusersecondaryquantity) && Objects.equals(originaluserpackagingquantity, that.originaluserpackagingquantity) && Objects.equals(buyercompanycode, that.buyercompanycode) && Objects.equals(buyercode, that.buyercode) && Objects.equals(qualitycode, that.qualitycode) && Objects.equals(linestatus, that.linestatus) && Objects.equals(progressstatus, that.progressstatus) && Objects.equals(statisticalgroupcompanycode, that.statisticalgroupcompanycode) && Objects.equals(statisticalgroupcode, that.statisticalgroupcode) && Objects.equals(collectiongroupcompanycode, that.collectiongroupcompanycode) && Objects.equals(collectiongroupcode, that.collectiongroupcode) && Objects.equals(projectcode, that.projectcode) && Objects.equals(customertype, that.customertype) && Objects.equals(customercode, that.customercode) && Objects.equals(linegroup, that.linegroup) && Objects.equals(warehousecompanycode, that.warehousecompanycode) && Objects.equals(warehousecode, that.warehousecode) && Objects.equals(issuewarehousecompanycode, that.issuewarehousecompanycode) && Objects.equals(issuewarehousecode, that.issuewarehousecode) && Objects.equals(sbcwarehousecompanycode, that.sbcwarehousecompanycode) && Objects.equals(subcontractorwarehousecode, that.subcontractorwarehousecode) && Objects.equals(updatewarehouseavailability, that.updatewarehouseavailability) && Objects.equals(cost, that.cost) && Objects.equals(costcentercompanycode, that.costcentercompanycode) && Objects.equals(costcentercode, that.costcentercode) && Objects.equals(deliverydescription, that.deliverydescription) && Objects.equals(shippingdescription, that.shippingdescription) && Objects.equals(transportreasondescription, that.transportreasondescription) && Objects.equals(releasetype, that.releasetype) && Objects.equals(leftoverloss, that.leftoverloss) && Objects.equals(joinedorderline, that.joinedorderline) && Objects.equals(joinedordersubline, that.joinedordersubline) && Objects.equals(linesource, that.linesource) && Objects.equals(previouslinetemplatecode, that.previouslinetemplatecode) && Objects.equals(previouscountercompanycode, that.previouscountercompanycode) && Objects.equals(previouscountercode, that.previouscountercode) && Objects.equals(previousdocumenttypeordertype, that.previousdocumenttypeordertype) && Objects.equals(previousdocumenttypetype, that.previousdocumenttypetype) && Objects.equals(previouscode, that.previouscode) && Objects.equals(previousorderline, that.previousorderline) && Objects.equals(previousordersubline, that.previousordersubline) && Objects.equals(previouscomponentorderline, that.previouscomponentorderline) && Objects.equals(previousdeliveryline, that.previousdeliveryline) && Objects.equals(sendtosupplier, that.sendtosupplier) && Objects.equals(entryexchangerate, that.entryexchangerate) && Objects.equals(conditionretrievingdate, that.conditionretrievingdate) && Objects.equals(paymentmethodcompanycode, that.paymentmethodcompanycode) && Objects.equals(paymentmethodcode, that.paymentmethodcode) && Objects.equals(pricelistcode, that.pricelistcode) && Objects.equals(lineuservalue, that.lineuservalue) && Objects.equals(priceunitofmeasurecode, that.priceunitofmeasurecode) && Objects.equals(price, that.price) && Objects.equals(pricetype, that.pricetype) && Objects.equals(pricesign, that.pricesign) && Objects.equals(priceincludingtax, that.priceincludingtax) && Objects.equals(priceretrieved, that.priceretrieved) && Objects.equals(originalamount, that.originalamount) && Objects.equals(netvalue, that.netvalue) && Objects.equals(updatevalue, that.updatevalue) && Objects.equals(taxcode, that.taxcode) && Objects.equals(freegifttaxdebit, that.freegifttaxdebit) && Objects.equals(taxableincomevalue, that.taxableincomevalue) && Objects.equals(netvalueincludingtax, that.netvalueincludingtax) && Objects.equals(onlybyamount, that.onlybyamount) && Objects.equals(manuallyinsertforbox, that.manuallyinsertforbox) && Objects.equals(termsoflogcode, that.termsoflogcode) && Objects.equals(logreasoncode, that.logreasoncode) && Objects.equals(creationdatetime, that.creationdatetime) && Objects.equals(creationuser, that.creationuser) && Objects.equals(lastupdatedatetime, that.lastupdatedatetime) && Objects.equals(lastupdateuser, that.lastupdateuser) && Objects.equals(absuniqueid, that.absuniqueid) && Objects.equals(creationdatetimeutc, that.creationdatetimeutc) && Objects.equals(lastupdatedatetimeutc, that.lastupdatedatetimeutc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, absversionnumber, divisioncode, alloweddivisions, ordertype, documenttypetype, assortmentnumberid, linetemplatecode, operationcode, externaloperationrequired, boxmanaged, itemtypeaficompanycode, itemtypeaficode, itemnature, subcode01, subcode02, subcode03, subcode04, subcode05, subcode06, subcode07, subcode08, subcode09, subcode10, fullitemidentifier, externalitem, itemdescription, bomnumberid, obsoletediscardeditem, userprimaryuomcode, userprimaryquantity, baseprimaryuomcode, baseprimaryquantity, usersecondaryuomcode, usersecondaryquantity, basesecondaryuomcode, basesecondaryquantity, userpackaginguomcode, userpackagingquantity, cancelleduserprimaryquantity, cancelledbaseprimaryquantity, cancelledusersecondaryquantity, cancelledbasesecondaryquantity, cancelleduserpackagingquantity, originaluserprimaryquantity, originalusersecondaryquantity, originaluserpackagingquantity, buyercompanycode, buyercode, qualitycode, linestatus, progressstatus, statisticalgroupcompanycode, statisticalgroupcode, collectiongroupcompanycode, collectiongroupcode, projectcode, customertype, customercode, linegroup, warehousecompanycode, warehousecode, issuewarehousecompanycode, issuewarehousecode, sbcwarehousecompanycode, subcontractorwarehousecode, updatewarehouseavailability, cost, costcentercompanycode, costcentercode, deliverydescription, shippingdescription, transportreasondescription, releasetype, leftoverloss, joinedorderline, joinedordersubline, linesource, previouslinetemplatecode, previouscountercompanycode, previouscountercode, previousdocumenttypeordertype, previousdocumenttypetype, previouscode, previousorderline, previousordersubline, previouscomponentorderline, previousdeliveryline, sendtosupplier, entryexchangerate, conditionretrievingdate, paymentmethodcompanycode, paymentmethodcode, pricelistcode, lineuservalue, priceunitofmeasurecode, price, pricetype, pricesign, priceincludingtax, priceretrieved, originalamount, netvalue, updatevalue, taxcode, freegifttaxdebit, taxableincomevalue, netvalueincludingtax, onlybyamount, manuallyinsertforbox, termsoflogcode, logreasoncode, creationdatetime, creationuser, lastupdatedatetime, lastupdateuser, absuniqueid, creationdatetimeutc, lastupdatedatetimeutc);
    }
}
