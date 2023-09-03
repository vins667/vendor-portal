package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "productiondemand")
public class Productiondemand {
    @EmbeddedId
    private ProductiondemandId id;
    private Long absversionnumber;
    private String divisioncode;
    private String alloweddivisions;
    private String templatecode;
    private String type;
    private String countercompanycode;
    private Date orderdate;
    private String statisticalgroupcompanycode;
    private String statisticalgroupcode;
    private String collectiongroupcompanycode;
    private String collectiongroupcode;
    private String internalordergroupcode;
    private String description;
    private String plantcompanycode;
    private String plantcode;
    private String proresponsiblecompanycode;
    private String productionresponsiblecode;
    private Date referencedate;
    private String plannercompanycode;
    private String plannercode;
    private String mainresourcecode;
    private String customertype;
    private String customercode;
    private String projectcode;
    private String externalreference;
    private Date internalreferencedate;
    private Short updatewarehouseavailability;
    private String prddemandstocktypecode;
    private String entrywarehousecompanycode;
    private String entrywarehousecode;
    private String entrylocwhszonephywhscmycode;
    private String entrylocwhszonephywhscode;
    private String entrylocationwarehousezonecode;
    private String entrylocationcode;
    private String warehousewipcompanycode;
    private String warehousewipcode;
    private String wipcostcentercompanycode;
    private String wipcostcentercode;
    private String itemtypeaficompanycode;
    private String itemtypeaficode;
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
    private BigInteger qualitycode;
    private Integer bomnumberid;
    private Integer routingnumberid;
    private Short splittingmanagement;
    private String productiongroupcode;
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
    private BigDecimal finaluserprimaryquantity;
    private BigDecimal finalbaseprimaryquantity;
    private BigDecimal finalusersecondaryquantity;
    private BigDecimal finalbasesecondaryquantity;
    private BigDecimal finaluserpackagingquantity;
    private BigDecimal entereduserprimaryquantity;
    private BigDecimal enteredbaseprimaryquantity;
    private BigDecimal enteredusersecondaryquantity;
    private BigDecimal enteredbasesecondaryquantity;
    private BigDecimal entereduserpackagingquantity;
    private String uomtype;
    private BigDecimal stdproductionbatch;
    private String stdproductionbatchuomcode;
    private BigDecimal quantityperperiod;
    private Integer numberofperiods;
    private Date initialplanneddate;
    private Date finalplanneddate;
    private Date initialplannedscheduleddate;
    private Date finalplannedscheduleddate;
    private Date initialscheduleddate;
    private Date finalscheduleddate;
    private Date initialeffectivedate;
    private Date finaleffectivedate;
    private String destinationorder;
    private String dlvsalordlinesalordcntcode;
    private String dlvsalorderlinesalesordercode;
    private Integer dlvsalesorderlineorderline;
    private Integer dlvsalesorderlineordersubline;
    private Integer dlvsalordlinecmporderline;
    private Integer deliverydeliveryline;
    private String reservationordercountercode;
    private String reservationordercode;
    private Integer reservationreservationline;
    private String origdlvsalordlinesalordcntcod;
    private String origdlvsalordlinesalordercode;
    private Integer origdlvsalorderlineorderline;
    private Integer origdlvsalordlineordersubline;
    private Integer origdlvsalordlinecmporderline;
    private Integer origdeliverydeliveryline;
    private String intdlvintordlineintordcntcode;
    private String intdlvintordlineintordercode;
    private Integer intdlvintorderlineorderline;
    private Integer intdlvintordlineordersubline;
    private Integer intdeliverydeliveryline;
    private String intdocintdocprvcountercode;
    private String intdocintdocprovisionalcode;
    private Integer intdocumentorderline;
    private Integer intdocumentordersubline;
    private Date externalreferencedate;
    private String internalreference;
    private String currentstatus;
    private Integer manualclosurereason;
    private String progressstatus;
    private Integer fullitemidentifier;
    private Timestamp creationdatetime;
    private String creationuser;
    private Timestamp lastupdatedatetime;
    private String lastupdateuser;
    private Long absuniqueid;

    @Basic
    @Column(name = "ABSVERSIONNUMBER", nullable = false)
    public Long getAbsversionnumber() {
        return absversionnumber;
    }

    public void setAbsversionnumber(Long absversionnumber) {
        this.absversionnumber = absversionnumber;
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
    @Column(name = "TYPE", nullable = false, length = 2)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
    @Column(name = "INTERNALORDERGROUPCODE", nullable = true, length = 15)
    public String getInternalordergroupcode() {
        return internalordergroupcode;
    }

    public void setInternalordergroupcode(String internalordergroupcode) {
        this.internalordergroupcode = internalordergroupcode;
    }

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, length = 100)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "PLANTCOMPANYCODE", nullable = true, length = 3)
    public String getPlantcompanycode() {
        return plantcompanycode;
    }

    public void setPlantcompanycode(String plantcompanycode) {
        this.plantcompanycode = plantcompanycode;
    }

    @Basic
    @Column(name = "PLANTCODE", nullable = true, length = 8)
    public String getPlantcode() {
        return plantcode;
    }

    public void setPlantcode(String plantcode) {
        this.plantcode = plantcode;
    }

    @Basic
    @Column(name = "PRORESPONSIBLECOMPANYCODE", nullable = true, length = 3)
    public String getProresponsiblecompanycode() {
        return proresponsiblecompanycode;
    }

    public void setProresponsiblecompanycode(String proresponsiblecompanycode) {
        this.proresponsiblecompanycode = proresponsiblecompanycode;
    }

    @Basic
    @Column(name = "PRODUCTIONRESPONSIBLECODE", nullable = true, length = 25)
    public String getProductionresponsiblecode() {
        return productionresponsiblecode;
    }

    public void setProductionresponsiblecode(String productionresponsiblecode) {
        this.productionresponsiblecode = productionresponsiblecode;
    }

    @Basic
    @Column(name = "REFERENCEDATE", nullable = true)
    public Date getReferencedate() {
        return referencedate;
    }

    public void setReferencedate(Date referencedate) {
        this.referencedate = referencedate;
    }

    @Basic
    @Column(name = "PLANNERCOMPANYCODE", nullable = true, length = 3)
    public String getPlannercompanycode() {
        return plannercompanycode;
    }

    public void setPlannercompanycode(String plannercompanycode) {
        this.plannercompanycode = plannercompanycode;
    }

    @Basic
    @Column(name = "PLANNERCODE", nullable = true, length = 25)
    public String getPlannercode() {
        return plannercode;
    }

    public void setPlannercode(String plannercode) {
        this.plannercode = plannercode;
    }

    @Basic
    @Column(name = "MAINRESOURCECODE", nullable = true, length = 8)
    public String getMainresourcecode() {
        return mainresourcecode;
    }

    public void setMainresourcecode(String mainresourcecode) {
        this.mainresourcecode = mainresourcecode;
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
    @Column(name = "PROJECTCODE", nullable = true, length = 20)
    public String getProjectcode() {
        return projectcode != null ? projectcode.trim() : projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    @Basic
    @Column(name = "EXTERNALREFERENCE", nullable = true, length = 100)
    public String getExternalreference() {
        return externalreference;
    }

    public void setExternalreference(String externalreference) {
        this.externalreference = externalreference;
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
    @Column(name = "UPDATEWAREHOUSEAVAILABILITY", nullable = false)
    public Short getUpdatewarehouseavailability() {
        return updatewarehouseavailability;
    }

    public void setUpdatewarehouseavailability(Short updatewarehouseavailability) {
        this.updatewarehouseavailability = updatewarehouseavailability;
    }

    @Basic
    @Column(name = "PRDDEMANDSTOCKTYPECODE", nullable = true, length = 3)
    public String getPrddemandstocktypecode() {
        return prddemandstocktypecode;
    }

    public void setPrddemandstocktypecode(String prddemandstocktypecode) {
        this.prddemandstocktypecode = prddemandstocktypecode;
    }

    @Basic
    @Column(name = "ENTRYWAREHOUSECOMPANYCODE", nullable = true, length = 3)
    public String getEntrywarehousecompanycode() {
        return entrywarehousecompanycode;
    }

    public void setEntrywarehousecompanycode(String entrywarehousecompanycode) {
        this.entrywarehousecompanycode = entrywarehousecompanycode;
    }

    @Basic
    @Column(name = "ENTRYWAREHOUSECODE", nullable = true, length = 8)
    public String getEntrywarehousecode() {
        return entrywarehousecode;
    }

    public void setEntrywarehousecode(String entrywarehousecode) {
        this.entrywarehousecode = entrywarehousecode;
    }

    @Basic
    @Column(name = "ENTRYLOCWHSZONEPHYWHSCMYCODE", nullable = true, length = 3)
    public String getEntrylocwhszonephywhscmycode() {
        return entrylocwhszonephywhscmycode;
    }

    public void setEntrylocwhszonephywhscmycode(String entrylocwhszonephywhscmycode) {
        this.entrylocwhszonephywhscmycode = entrylocwhszonephywhscmycode;
    }

    @Basic
    @Column(name = "ENTRYLOCWHSZONEPHYWHSCODE", nullable = true, length = 8)
    public String getEntrylocwhszonephywhscode() {
        return entrylocwhszonephywhscode;
    }

    public void setEntrylocwhszonephywhscode(String entrylocwhszonephywhscode) {
        this.entrylocwhszonephywhscode = entrylocwhszonephywhscode;
    }

    @Basic
    @Column(name = "ENTRYLOCATIONWAREHOUSEZONECODE", nullable = true, length = 3)
    public String getEntrylocationwarehousezonecode() {
        return entrylocationwarehousezonecode;
    }

    public void setEntrylocationwarehousezonecode(String entrylocationwarehousezonecode) {
        this.entrylocationwarehousezonecode = entrylocationwarehousezonecode;
    }

    @Basic
    @Column(name = "ENTRYLOCATIONCODE", nullable = true, length = 10)
    public String getEntrylocationcode() {
        return entrylocationcode;
    }

    public void setEntrylocationcode(String entrylocationcode) {
        this.entrylocationcode = entrylocationcode;
    }

    @Basic
    @Column(name = "WAREHOUSEWIPCOMPANYCODE", nullable = true, length = 3)
    public String getWarehousewipcompanycode() {
        return warehousewipcompanycode;
    }

    public void setWarehousewipcompanycode(String warehousewipcompanycode) {
        this.warehousewipcompanycode = warehousewipcompanycode;
    }

    @Basic
    @Column(name = "WAREHOUSEWIPCODE", nullable = true, length = 8)
    public String getWarehousewipcode() {
        return warehousewipcode;
    }

    public void setWarehousewipcode(String warehousewipcode) {
        this.warehousewipcode = warehousewipcode;
    }

    @Basic
    @Column(name = "WIPCOSTCENTERCOMPANYCODE", nullable = true, length = 3)
    public String getWipcostcentercompanycode() {
        return wipcostcentercompanycode;
    }

    public void setWipcostcentercompanycode(String wipcostcentercompanycode) {
        this.wipcostcentercompanycode = wipcostcentercompanycode;
    }

    @Basic
    @Column(name = "WIPCOSTCENTERCODE", nullable = true, length = 20)
    public String getWipcostcentercode() {
        return wipcostcentercode;
    }

    public void setWipcostcentercode(String wipcostcentercode) {
        this.wipcostcentercode = wipcostcentercode;
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
        return subcode08 != null ? subcode08.trim() : subcode08;
    }

    public void setSubcode08(String subcode08) {
        this.subcode08 = subcode08;
    }

    @Basic
    @Column(name = "SUBCODE09", nullable = true, length = 10)
    public String getSubcode09() {
        return subcode09 != null ? subcode09.trim() : subcode09;
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
    @Column(name = "QUALITYCODE", nullable = true, precision = 0)
    public BigInteger getQualitycode() {
        return qualitycode;
    }

    public void setQualitycode(BigInteger qualitycode) {
        this.qualitycode = qualitycode;
    }

    @Basic
    @Column(name = "BOMNUMBERID", nullable = true, precision = 0)
    public Integer getBomnumberid() {
        return bomnumberid;
    }

    public void setBomnumberid(Integer bomnumberid) {
        this.bomnumberid = bomnumberid;
    }

    @Basic
    @Column(name = "ROUTINGNUMBERID", nullable = true, precision = 0)
    public Integer getRoutingnumberid() {
        return routingnumberid;
    }

    public void setRoutingnumberid(Integer routingnumberid) {
        this.routingnumberid = routingnumberid;
    }

    @Basic
    @Column(name = "SPLITTINGMANAGEMENT", nullable = false)
    public Short getSplittingmanagement() {
        return splittingmanagement;
    }

    public void setSplittingmanagement(Short splittingmanagement) {
        this.splittingmanagement = splittingmanagement;
    }

    @Basic
    @Column(name = "PRODUCTIONGROUPCODE", nullable = true, length = 3)
    public String getProductiongroupcode() {
        return productiongroupcode;
    }

    public void setProductiongroupcode(String productiongroupcode) {
        this.productiongroupcode = productiongroupcode;
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
    @Column(name = "FINALUSERPRIMARYQUANTITY", nullable = true, precision = 5)
    public BigDecimal getFinaluserprimaryquantity() {
        return finaluserprimaryquantity;
    }

    public void setFinaluserprimaryquantity(BigDecimal finaluserprimaryquantity) {
        this.finaluserprimaryquantity = finaluserprimaryquantity;
    }

    @Basic
    @Column(name = "FINALBASEPRIMARYQUANTITY", nullable = true, precision = 5)
    public BigDecimal getFinalbaseprimaryquantity() {
        return finalbaseprimaryquantity;
    }

    public void setFinalbaseprimaryquantity(BigDecimal finalbaseprimaryquantity) {
        this.finalbaseprimaryquantity = finalbaseprimaryquantity;
    }

    @Basic
    @Column(name = "FINALUSERSECONDARYQUANTITY", nullable = true, precision = 5)
    public BigDecimal getFinalusersecondaryquantity() {
        return finalusersecondaryquantity;
    }

    public void setFinalusersecondaryquantity(BigDecimal finalusersecondaryquantity) {
        this.finalusersecondaryquantity = finalusersecondaryquantity;
    }

    @Basic
    @Column(name = "FINALBASESECONDARYQUANTITY", nullable = true, precision = 5)
    public BigDecimal getFinalbasesecondaryquantity() {
        return finalbasesecondaryquantity;
    }

    public void setFinalbasesecondaryquantity(BigDecimal finalbasesecondaryquantity) {
        this.finalbasesecondaryquantity = finalbasesecondaryquantity;
    }

    @Basic
    @Column(name = "FINALUSERPACKAGINGQUANTITY", nullable = true, precision = 5)
    public BigDecimal getFinaluserpackagingquantity() {
        return finaluserpackagingquantity;
    }

    public void setFinaluserpackagingquantity(BigDecimal finaluserpackagingquantity) {
        this.finaluserpackagingquantity = finaluserpackagingquantity;
    }

    @Basic
    @Column(name = "ENTEREDUSERPRIMARYQUANTITY", nullable = false, precision = 5)
    public BigDecimal getEntereduserprimaryquantity() {
        return entereduserprimaryquantity;
    }

    public void setEntereduserprimaryquantity(BigDecimal entereduserprimaryquantity) {
        this.entereduserprimaryquantity = entereduserprimaryquantity;
    }

    @Basic
    @Column(name = "ENTEREDBASEPRIMARYQUANTITY", nullable = false, precision = 5)
    public BigDecimal getEnteredbaseprimaryquantity() {
        return enteredbaseprimaryquantity;
    }

    public void setEnteredbaseprimaryquantity(BigDecimal enteredbaseprimaryquantity) {
        this.enteredbaseprimaryquantity = enteredbaseprimaryquantity;
    }

    @Basic
    @Column(name = "ENTEREDUSERSECONDARYQUANTITY", nullable = false, precision = 5)
    public BigDecimal getEnteredusersecondaryquantity() {
        return enteredusersecondaryquantity;
    }

    public void setEnteredusersecondaryquantity(BigDecimal enteredusersecondaryquantity) {
        this.enteredusersecondaryquantity = enteredusersecondaryquantity;
    }

    @Basic
    @Column(name = "ENTEREDBASESECONDARYQUANTITY", nullable = false, precision = 5)
    public BigDecimal getEnteredbasesecondaryquantity() {
        return enteredbasesecondaryquantity;
    }

    public void setEnteredbasesecondaryquantity(BigDecimal enteredbasesecondaryquantity) {
        this.enteredbasesecondaryquantity = enteredbasesecondaryquantity;
    }

    @Basic
    @Column(name = "ENTEREDUSERPACKAGINGQUANTITY", nullable = false, precision = 5)
    public BigDecimal getEntereduserpackagingquantity() {
        return entereduserpackagingquantity;
    }

    public void setEntereduserpackagingquantity(BigDecimal entereduserpackagingquantity) {
        this.entereduserpackagingquantity = entereduserpackagingquantity;
    }

    @Basic
    @Column(name = "UOMTYPE", nullable = false, length = 2)
    public String getUomtype() {
        return uomtype;
    }

    public void setUomtype(String uomtype) {
        this.uomtype = uomtype;
    }

    @Basic
    @Column(name = "STDPRODUCTIONBATCH", nullable = true, precision = 5)
    public BigDecimal getStdproductionbatch() {
        return stdproductionbatch;
    }

    public void setStdproductionbatch(BigDecimal stdproductionbatch) {
        this.stdproductionbatch = stdproductionbatch;
    }

    @Basic
    @Column(name = "STDPRODUCTIONBATCHUOMCODE", nullable = true, length = 3)
    public String getStdproductionbatchuomcode() {
        return stdproductionbatchuomcode;
    }

    public void setStdproductionbatchuomcode(String stdproductionbatchuomcode) {
        this.stdproductionbatchuomcode = stdproductionbatchuomcode;
    }

    @Basic
    @Column(name = "QUANTITYPERPERIOD", nullable = true, precision = 5)
    public BigDecimal getQuantityperperiod() {
        return quantityperperiod;
    }

    public void setQuantityperperiod(BigDecimal quantityperperiod) {
        this.quantityperperiod = quantityperperiod;
    }

    @Basic
    @Column(name = "NUMBEROFPERIODS", nullable = false)
    public Integer getNumberofperiods() {
        return numberofperiods;
    }

    public void setNumberofperiods(Integer numberofperiods) {
        this.numberofperiods = numberofperiods;
    }

    @Basic
    @Column(name = "INITIALPLANNEDDATE", nullable = true)
    public Date getInitialplanneddate() {
        return initialplanneddate;
    }

    public void setInitialplanneddate(Date initialplanneddate) {
        this.initialplanneddate = initialplanneddate;
    }

    @Basic
    @Column(name = "FINALPLANNEDDATE", nullable = true)
    public Date getFinalplanneddate() {
        return finalplanneddate;
    }

    public void setFinalplanneddate(Date finalplanneddate) {
        this.finalplanneddate = finalplanneddate;
    }

    @Basic
    @Column(name = "INITIALPLANNEDSCHEDULEDDATE", nullable = true)
    public Date getInitialplannedscheduleddate() {
        return initialplannedscheduleddate;
    }

    public void setInitialplannedscheduleddate(Date initialplannedscheduleddate) {
        this.initialplannedscheduleddate = initialplannedscheduleddate;
    }

    @Basic
    @Column(name = "FINALPLANNEDSCHEDULEDDATE", nullable = true)
    public Date getFinalplannedscheduleddate() {
        return finalplannedscheduleddate;
    }

    public void setFinalplannedscheduleddate(Date finalplannedscheduleddate) {
        this.finalplannedscheduleddate = finalplannedscheduleddate;
    }

    @Basic
    @Column(name = "INITIALSCHEDULEDDATE", nullable = true)
    public Date getInitialscheduleddate() {
        return initialscheduleddate;
    }

    public void setInitialscheduleddate(Date initialscheduleddate) {
        this.initialscheduleddate = initialscheduleddate;
    }

    @Basic
    @Column(name = "FINALSCHEDULEDDATE", nullable = true)
    public Date getFinalscheduleddate() {
        return finalscheduleddate;
    }

    public void setFinalscheduleddate(Date finalscheduleddate) {
        this.finalscheduleddate = finalscheduleddate;
    }

    @Basic
    @Column(name = "INITIALEFFECTIVEDATE", nullable = true)
    public Date getInitialeffectivedate() {
        return initialeffectivedate;
    }

    public void setInitialeffectivedate(Date initialeffectivedate) {
        this.initialeffectivedate = initialeffectivedate;
    }

    @Basic
    @Column(name = "FINALEFFECTIVEDATE", nullable = true)
    public Date getFinaleffectivedate() {
        return finaleffectivedate;
    }

    public void setFinaleffectivedate(Date finaleffectivedate) {
        this.finaleffectivedate = finaleffectivedate;
    }

    @Basic
    @Column(name = "DESTINATIONORDER", nullable = false, length = 2)
    public String getDestinationorder() {
        return destinationorder;
    }

    public void setDestinationorder(String destinationorder) {
        this.destinationorder = destinationorder;
    }

    @Basic
    @Column(name = "DLVSALORDLINESALORDCNTCODE", nullable = true, length = 8)
    public String getDlvsalordlinesalordcntcode() {
        return dlvsalordlinesalordcntcode;
    }

    public void setDlvsalordlinesalordcntcode(String dlvsalordlinesalordcntcode) {
        this.dlvsalordlinesalordcntcode = dlvsalordlinesalordcntcode;
    }

    @Basic
    @Column(name = "DLVSALORDERLINESALESORDERCODE", nullable = true, length = 15)
    public String getDlvsalorderlinesalesordercode() {
        return dlvsalorderlinesalesordercode;
    }

    public void setDlvsalorderlinesalesordercode(String dlvsalorderlinesalesordercode) {
        this.dlvsalorderlinesalesordercode = dlvsalorderlinesalesordercode;
    }

    @Basic
    @Column(name = "DLVSALESORDERLINEORDERLINE", nullable = true, precision = 0)
    public Integer getDlvsalesorderlineorderline() {
        return dlvsalesorderlineorderline;
    }

    public void setDlvsalesorderlineorderline(Integer dlvsalesorderlineorderline) {
        this.dlvsalesorderlineorderline = dlvsalesorderlineorderline;
    }

    @Basic
    @Column(name = "DLVSALESORDERLINEORDERSUBLINE", nullable = true, precision = 0)
    public Integer getDlvsalesorderlineordersubline() {
        return dlvsalesorderlineordersubline;
    }

    public void setDlvsalesorderlineordersubline(Integer dlvsalesorderlineordersubline) {
        this.dlvsalesorderlineordersubline = dlvsalesorderlineordersubline;
    }

    @Basic
    @Column(name = "DLVSALORDLINECMPORDERLINE", nullable = true, precision = 0)
    public Integer getDlvsalordlinecmporderline() {
        return dlvsalordlinecmporderline;
    }

    public void setDlvsalordlinecmporderline(Integer dlvsalordlinecmporderline) {
        this.dlvsalordlinecmporderline = dlvsalordlinecmporderline;
    }

    @Basic
    @Column(name = "DELIVERYDELIVERYLINE", nullable = true, precision = 0)
    public Integer getDeliverydeliveryline() {
        return deliverydeliveryline;
    }

    public void setDeliverydeliveryline(Integer deliverydeliveryline) {
        this.deliverydeliveryline = deliverydeliveryline;
    }

    @Basic
    @Column(name = "RESERVATIONORDERCOUNTERCODE", nullable = true, length = 8)
    public String getReservationordercountercode() {
        return reservationordercountercode;
    }

    public void setReservationordercountercode(String reservationordercountercode) {
        this.reservationordercountercode = reservationordercountercode;
    }

    @Basic
    @Column(name = "RESERVATIONORDERCODE", nullable = true, length = 15)
    public String getReservationordercode() {
        return reservationordercode;
    }

    public void setReservationordercode(String reservationordercode) {
        this.reservationordercode = reservationordercode;
    }

    @Basic
    @Column(name = "RESERVATIONRESERVATIONLINE", nullable = true, precision = 0)
    public Integer getReservationreservationline() {
        return reservationreservationline;
    }

    public void setReservationreservationline(Integer reservationreservationline) {
        this.reservationreservationline = reservationreservationline;
    }

    @Basic
    @Column(name = "ORIGDLVSALORDLINESALORDCNTCOD", nullable = true, length = 8)
    public String getOrigdlvsalordlinesalordcntcod() {
        return origdlvsalordlinesalordcntcod;
    }

    public void setOrigdlvsalordlinesalordcntcod(String origdlvsalordlinesalordcntcod) {
        this.origdlvsalordlinesalordcntcod = origdlvsalordlinesalordcntcod;
    }

    @Basic
    @Column(name = "ORIGDLVSALORDLINESALORDERCODE", nullable = true, length = 15)
    public String getOrigdlvsalordlinesalordercode() {
        return origdlvsalordlinesalordercode;
    }

    public void setOrigdlvsalordlinesalordercode(String origdlvsalordlinesalordercode) {
        this.origdlvsalordlinesalordercode = origdlvsalordlinesalordercode;
    }

    @Basic
    @Column(name = "ORIGDLVSALORDERLINEORDERLINE", nullable = true, precision = 0)
    public Integer getOrigdlvsalorderlineorderline() {
        return origdlvsalorderlineorderline;
    }

    public void setOrigdlvsalorderlineorderline(Integer origdlvsalorderlineorderline) {
        this.origdlvsalorderlineorderline = origdlvsalorderlineorderline;
    }

    @Basic
    @Column(name = "ORIGDLVSALORDLINEORDERSUBLINE", nullable = true, precision = 0)
    public Integer getOrigdlvsalordlineordersubline() {
        return origdlvsalordlineordersubline;
    }

    public void setOrigdlvsalordlineordersubline(Integer origdlvsalordlineordersubline) {
        this.origdlvsalordlineordersubline = origdlvsalordlineordersubline;
    }

    @Basic
    @Column(name = "ORIGDLVSALORDLINECMPORDERLINE", nullable = true, precision = 0)
    public Integer getOrigdlvsalordlinecmporderline() {
        return origdlvsalordlinecmporderline;
    }

    public void setOrigdlvsalordlinecmporderline(Integer origdlvsalordlinecmporderline) {
        this.origdlvsalordlinecmporderline = origdlvsalordlinecmporderline;
    }

    @Basic
    @Column(name = "ORIGDELIVERYDELIVERYLINE", nullable = true, precision = 0)
    public Integer getOrigdeliverydeliveryline() {
        return origdeliverydeliveryline;
    }

    public void setOrigdeliverydeliveryline(Integer origdeliverydeliveryline) {
        this.origdeliverydeliveryline = origdeliverydeliveryline;
    }

    @Basic
    @Column(name = "INTDLVINTORDLINEINTORDCNTCODE", nullable = true, length = 8)
    public String getIntdlvintordlineintordcntcode() {
        return intdlvintordlineintordcntcode;
    }

    public void setIntdlvintordlineintordcntcode(String intdlvintordlineintordcntcode) {
        this.intdlvintordlineintordcntcode = intdlvintordlineintordcntcode;
    }

    @Basic
    @Column(name = "INTDLVINTORDLINEINTORDERCODE", nullable = true, length = 15)
    public String getIntdlvintordlineintordercode() {
        return intdlvintordlineintordercode;
    }

    public void setIntdlvintordlineintordercode(String intdlvintordlineintordercode) {
        this.intdlvintordlineintordercode = intdlvintordlineintordercode;
    }

    @Basic
    @Column(name = "INTDLVINTORDERLINEORDERLINE", nullable = true, precision = 0)
    public Integer getIntdlvintorderlineorderline() {
        return intdlvintorderlineorderline;
    }

    public void setIntdlvintorderlineorderline(Integer intdlvintorderlineorderline) {
        this.intdlvintorderlineorderline = intdlvintorderlineorderline;
    }

    @Basic
    @Column(name = "INTDLVINTORDLINEORDERSUBLINE", nullable = true, precision = 0)
    public Integer getIntdlvintordlineordersubline() {
        return intdlvintordlineordersubline;
    }

    public void setIntdlvintordlineordersubline(Integer intdlvintordlineordersubline) {
        this.intdlvintordlineordersubline = intdlvintordlineordersubline;
    }

    @Basic
    @Column(name = "INTDELIVERYDELIVERYLINE", nullable = true, precision = 0)
    public Integer getIntdeliverydeliveryline() {
        return intdeliverydeliveryline;
    }

    public void setIntdeliverydeliveryline(Integer intdeliverydeliveryline) {
        this.intdeliverydeliveryline = intdeliverydeliveryline;
    }

    @Basic
    @Column(name = "INTDOCINTDOCPRVCOUNTERCODE", nullable = true, length = 8)
    public String getIntdocintdocprvcountercode() {
        return intdocintdocprvcountercode;
    }

    public void setIntdocintdocprvcountercode(String intdocintdocprvcountercode) {
        this.intdocintdocprvcountercode = intdocintdocprvcountercode;
    }

    @Basic
    @Column(name = "INTDOCINTDOCPROVISIONALCODE", nullable = true, length = 15)
    public String getIntdocintdocprovisionalcode() {
        return intdocintdocprovisionalcode;
    }

    public void setIntdocintdocprovisionalcode(String intdocintdocprovisionalcode) {
        this.intdocintdocprovisionalcode = intdocintdocprovisionalcode;
    }

    @Basic
    @Column(name = "INTDOCUMENTORDERLINE", nullable = true, precision = 0)
    public Integer getIntdocumentorderline() {
        return intdocumentorderline;
    }

    public void setIntdocumentorderline(Integer intdocumentorderline) {
        this.intdocumentorderline = intdocumentorderline;
    }

    @Basic
    @Column(name = "INTDOCUMENTORDERSUBLINE", nullable = true, precision = 0)
    public Integer getIntdocumentordersubline() {
        return intdocumentordersubline;
    }

    public void setIntdocumentordersubline(Integer intdocumentordersubline) {
        this.intdocumentordersubline = intdocumentordersubline;
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
    @Column(name = "INTERNALREFERENCE", nullable = true, length = 100)
    public String getInternalreference() {
        return internalreference;
    }

    public void setInternalreference(String internalreference) {
        this.internalreference = internalreference;
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
    @Column(name = "MANUALCLOSUREREASON", nullable = false)
    public Integer getManualclosurereason() {
        return manualclosurereason;
    }

    public void setManualclosurereason(Integer manualclosurereason) {
        this.manualclosurereason = manualclosurereason;
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
    @Column(name = "FULLITEMIDENTIFIER", nullable = true, precision = 0)
    public Integer getFullitemidentifier() {
        return fullitemidentifier;
    }

    public void setFullitemidentifier(Integer fullitemidentifier) {
        this.fullitemidentifier = fullitemidentifier;
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

    public ProductiondemandId getId() {
        return id;
    }

    public void setId(ProductiondemandId id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Productiondemand that = (Productiondemand) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(absversionnumber, that.absversionnumber) &&
            Objects.equals(divisioncode, that.divisioncode) &&
            Objects.equals(alloweddivisions, that.alloweddivisions) &&
            Objects.equals(templatecode, that.templatecode) &&
            Objects.equals(type, that.type) &&
            Objects.equals(countercompanycode, that.countercompanycode) &&
            Objects.equals(orderdate, that.orderdate) &&
            Objects.equals(statisticalgroupcompanycode, that.statisticalgroupcompanycode) &&
            Objects.equals(statisticalgroupcode, that.statisticalgroupcode) &&
            Objects.equals(collectiongroupcompanycode, that.collectiongroupcompanycode) &&
            Objects.equals(collectiongroupcode, that.collectiongroupcode) &&
            Objects.equals(internalordergroupcode, that.internalordergroupcode) &&
            Objects.equals(description, that.description) &&
            Objects.equals(plantcompanycode, that.plantcompanycode) &&
            Objects.equals(plantcode, that.plantcode) &&
            Objects.equals(proresponsiblecompanycode, that.proresponsiblecompanycode) &&
            Objects.equals(productionresponsiblecode, that.productionresponsiblecode) &&
            Objects.equals(referencedate, that.referencedate) &&
            Objects.equals(plannercompanycode, that.plannercompanycode) &&
            Objects.equals(plannercode, that.plannercode) &&
            Objects.equals(mainresourcecode, that.mainresourcecode) &&
            Objects.equals(customertype, that.customertype) &&
            Objects.equals(customercode, that.customercode) &&
            Objects.equals(projectcode, that.projectcode) &&
            Objects.equals(externalreference, that.externalreference) &&
            Objects.equals(internalreferencedate, that.internalreferencedate) &&
            Objects.equals(updatewarehouseavailability, that.updatewarehouseavailability) &&
            Objects.equals(prddemandstocktypecode, that.prddemandstocktypecode) &&
            Objects.equals(entrywarehousecompanycode, that.entrywarehousecompanycode) &&
            Objects.equals(entrywarehousecode, that.entrywarehousecode) &&
            Objects.equals(entrylocwhszonephywhscmycode, that.entrylocwhszonephywhscmycode) &&
            Objects.equals(entrylocwhszonephywhscode, that.entrylocwhszonephywhscode) &&
            Objects.equals(entrylocationwarehousezonecode, that.entrylocationwarehousezonecode) &&
            Objects.equals(entrylocationcode, that.entrylocationcode) &&
            Objects.equals(warehousewipcompanycode, that.warehousewipcompanycode) &&
            Objects.equals(warehousewipcode, that.warehousewipcode) &&
            Objects.equals(wipcostcentercompanycode, that.wipcostcentercompanycode) &&
            Objects.equals(wipcostcentercode, that.wipcostcentercode) &&
            Objects.equals(itemtypeaficompanycode, that.itemtypeaficompanycode) &&
            Objects.equals(itemtypeaficode, that.itemtypeaficode) &&
            Objects.equals(subcode01, that.subcode01) &&
            Objects.equals(subcode02, that.subcode02) &&
            Objects.equals(subcode03, that.subcode03) &&
            Objects.equals(subcode04, that.subcode04) &&
            Objects.equals(subcode05, that.subcode05) &&
            Objects.equals(subcode06, that.subcode06) &&
            Objects.equals(subcode07, that.subcode07) &&
            Objects.equals(subcode08, that.subcode08) &&
            Objects.equals(subcode09, that.subcode09) &&
            Objects.equals(subcode10, that.subcode10) &&
            Objects.equals(qualitycode, that.qualitycode) &&
            Objects.equals(bomnumberid, that.bomnumberid) &&
            Objects.equals(routingnumberid, that.routingnumberid) &&
            Objects.equals(splittingmanagement, that.splittingmanagement) &&
            Objects.equals(productiongroupcode, that.productiongroupcode) &&
            Objects.equals(userprimaryuomcode, that.userprimaryuomcode) &&
            Objects.equals(userprimaryquantity, that.userprimaryquantity) &&
            Objects.equals(baseprimaryuomcode, that.baseprimaryuomcode) &&
            Objects.equals(baseprimaryquantity, that.baseprimaryquantity) &&
            Objects.equals(usersecondaryuomcode, that.usersecondaryuomcode) &&
            Objects.equals(usersecondaryquantity, that.usersecondaryquantity) &&
            Objects.equals(basesecondaryuomcode, that.basesecondaryuomcode) &&
            Objects.equals(basesecondaryquantity, that.basesecondaryquantity) &&
            Objects.equals(userpackaginguomcode, that.userpackaginguomcode) &&
            Objects.equals(userpackagingquantity, that.userpackagingquantity) &&
            Objects.equals(finaluserprimaryquantity, that.finaluserprimaryquantity) &&
            Objects.equals(finalbaseprimaryquantity, that.finalbaseprimaryquantity) &&
            Objects.equals(finalusersecondaryquantity, that.finalusersecondaryquantity) &&
            Objects.equals(finalbasesecondaryquantity, that.finalbasesecondaryquantity) &&
            Objects.equals(finaluserpackagingquantity, that.finaluserpackagingquantity) &&
            Objects.equals(entereduserprimaryquantity, that.entereduserprimaryquantity) &&
            Objects.equals(enteredbaseprimaryquantity, that.enteredbaseprimaryquantity) &&
            Objects.equals(enteredusersecondaryquantity, that.enteredusersecondaryquantity) &&
            Objects.equals(enteredbasesecondaryquantity, that.enteredbasesecondaryquantity) &&
            Objects.equals(entereduserpackagingquantity, that.entereduserpackagingquantity) &&
            Objects.equals(uomtype, that.uomtype) &&
            Objects.equals(stdproductionbatch, that.stdproductionbatch) &&
            Objects.equals(stdproductionbatchuomcode, that.stdproductionbatchuomcode) &&
            Objects.equals(quantityperperiod, that.quantityperperiod) &&
            Objects.equals(numberofperiods, that.numberofperiods) &&
            Objects.equals(initialplanneddate, that.initialplanneddate) &&
            Objects.equals(finalplanneddate, that.finalplanneddate) &&
            Objects.equals(initialplannedscheduleddate, that.initialplannedscheduleddate) &&
            Objects.equals(finalplannedscheduleddate, that.finalplannedscheduleddate) &&
            Objects.equals(initialscheduleddate, that.initialscheduleddate) &&
            Objects.equals(finalscheduleddate, that.finalscheduleddate) &&
            Objects.equals(initialeffectivedate, that.initialeffectivedate) &&
            Objects.equals(finaleffectivedate, that.finaleffectivedate) &&
            Objects.equals(destinationorder, that.destinationorder) &&
            Objects.equals(dlvsalordlinesalordcntcode, that.dlvsalordlinesalordcntcode) &&
            Objects.equals(dlvsalorderlinesalesordercode, that.dlvsalorderlinesalesordercode) &&
            Objects.equals(dlvsalesorderlineorderline, that.dlvsalesorderlineorderline) &&
            Objects.equals(dlvsalesorderlineordersubline, that.dlvsalesorderlineordersubline) &&
            Objects.equals(dlvsalordlinecmporderline, that.dlvsalordlinecmporderline) &&
            Objects.equals(deliverydeliveryline, that.deliverydeliveryline) &&
            Objects.equals(reservationordercountercode, that.reservationordercountercode) &&
            Objects.equals(reservationordercode, that.reservationordercode) &&
            Objects.equals(reservationreservationline, that.reservationreservationline) &&
            Objects.equals(origdlvsalordlinesalordcntcod, that.origdlvsalordlinesalordcntcod) &&
            Objects.equals(origdlvsalordlinesalordercode, that.origdlvsalordlinesalordercode) &&
            Objects.equals(origdlvsalorderlineorderline, that.origdlvsalorderlineorderline) &&
            Objects.equals(origdlvsalordlineordersubline, that.origdlvsalordlineordersubline) &&
            Objects.equals(origdlvsalordlinecmporderline, that.origdlvsalordlinecmporderline) &&
            Objects.equals(origdeliverydeliveryline, that.origdeliverydeliveryline) &&
            Objects.equals(intdlvintordlineintordcntcode, that.intdlvintordlineintordcntcode) &&
            Objects.equals(intdlvintordlineintordercode, that.intdlvintordlineintordercode) &&
            Objects.equals(intdlvintorderlineorderline, that.intdlvintorderlineorderline) &&
            Objects.equals(intdlvintordlineordersubline, that.intdlvintordlineordersubline) &&
            Objects.equals(intdeliverydeliveryline, that.intdeliverydeliveryline) &&
            Objects.equals(intdocintdocprvcountercode, that.intdocintdocprvcountercode) &&
            Objects.equals(intdocintdocprovisionalcode, that.intdocintdocprovisionalcode) &&
            Objects.equals(intdocumentorderline, that.intdocumentorderline) &&
            Objects.equals(intdocumentordersubline, that.intdocumentordersubline) &&
            Objects.equals(externalreferencedate, that.externalreferencedate) &&
            Objects.equals(internalreference, that.internalreference) &&
            Objects.equals(currentstatus, that.currentstatus) &&
            Objects.equals(manualclosurereason, that.manualclosurereason) &&
            Objects.equals(progressstatus, that.progressstatus) &&
            Objects.equals(fullitemidentifier, that.fullitemidentifier) &&
            Objects.equals(creationdatetime, that.creationdatetime) &&
            Objects.equals(creationuser, that.creationuser) &&
            Objects.equals(lastupdatedatetime, that.lastupdatedatetime) &&
            Objects.equals(lastupdateuser, that.lastupdateuser) &&
            Objects.equals(absuniqueid, that.absuniqueid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, absversionnumber, divisioncode, alloweddivisions, templatecode, type, countercompanycode, orderdate, statisticalgroupcompanycode, statisticalgroupcode, collectiongroupcompanycode, collectiongroupcode, internalordergroupcode, description, plantcompanycode, plantcode, proresponsiblecompanycode, productionresponsiblecode, referencedate, plannercompanycode, plannercode, mainresourcecode, customertype, customercode, projectcode, externalreference, internalreferencedate, updatewarehouseavailability, prddemandstocktypecode, entrywarehousecompanycode, entrywarehousecode, entrylocwhszonephywhscmycode, entrylocwhszonephywhscode, entrylocationwarehousezonecode, entrylocationcode, warehousewipcompanycode, warehousewipcode, wipcostcentercompanycode, wipcostcentercode, itemtypeaficompanycode, itemtypeaficode, subcode01, subcode02, subcode03, subcode04, subcode05, subcode06, subcode07, subcode08, subcode09, subcode10, qualitycode, bomnumberid, routingnumberid, splittingmanagement, productiongroupcode, userprimaryuomcode, userprimaryquantity, baseprimaryuomcode, baseprimaryquantity, usersecondaryuomcode, usersecondaryquantity, basesecondaryuomcode, basesecondaryquantity, userpackaginguomcode, userpackagingquantity, finaluserprimaryquantity, finalbaseprimaryquantity, finalusersecondaryquantity, finalbasesecondaryquantity, finaluserpackagingquantity, entereduserprimaryquantity, enteredbaseprimaryquantity, enteredusersecondaryquantity, enteredbasesecondaryquantity, entereduserpackagingquantity, uomtype, stdproductionbatch, stdproductionbatchuomcode, quantityperperiod, numberofperiods, initialplanneddate, finalplanneddate, initialplannedscheduleddate, finalplannedscheduleddate, initialscheduleddate, finalscheduleddate, initialeffectivedate, finaleffectivedate, destinationorder, dlvsalordlinesalordcntcode, dlvsalorderlinesalesordercode, dlvsalesorderlineorderline, dlvsalesorderlineordersubline, dlvsalordlinecmporderline, deliverydeliveryline, reservationordercountercode, reservationordercode, reservationreservationline, origdlvsalordlinesalordcntcod, origdlvsalordlinesalordercode, origdlvsalorderlineorderline, origdlvsalordlineordersubline, origdlvsalordlinecmporderline, origdeliverydeliveryline, intdlvintordlineintordcntcode, intdlvintordlineintordercode, intdlvintorderlineorderline, intdlvintordlineordersubline, intdeliverydeliveryline, intdocintdocprvcountercode, intdocintdocprovisionalcode, intdocumentorderline, intdocumentordersubline, externalreferencedate, internalreference, currentstatus, manualclosurereason, progressstatus, fullitemidentifier, creationdatetime, creationuser, lastupdatedatetime, lastupdateuser, absuniqueid);
    }

    @Override
    public String toString() {
        return "Productiondemand{" +
            "id=" + id +
            ", absversionnumber=" + absversionnumber +
            ", divisioncode='" + divisioncode + '\'' +
            ", alloweddivisions='" + alloweddivisions + '\'' +
            ", templatecode='" + templatecode + '\'' +
            ", type='" + type + '\'' +
            ", countercompanycode='" + countercompanycode + '\'' +
            ", orderdate=" + orderdate +
            ", statisticalgroupcompanycode='" + statisticalgroupcompanycode + '\'' +
            ", statisticalgroupcode='" + statisticalgroupcode + '\'' +
            ", collectiongroupcompanycode='" + collectiongroupcompanycode + '\'' +
            ", collectiongroupcode='" + collectiongroupcode + '\'' +
            ", internalordergroupcode='" + internalordergroupcode + '\'' +
            ", description='" + description + '\'' +
            ", plantcompanycode='" + plantcompanycode + '\'' +
            ", plantcode='" + plantcode + '\'' +
            ", proresponsiblecompanycode='" + proresponsiblecompanycode + '\'' +
            ", productionresponsiblecode='" + productionresponsiblecode + '\'' +
            ", referencedate=" + referencedate +
            ", plannercompanycode='" + plannercompanycode + '\'' +
            ", plannercode='" + plannercode + '\'' +
            ", mainresourcecode='" + mainresourcecode + '\'' +
            ", customertype='" + customertype + '\'' +
            ", customercode='" + customercode + '\'' +
            ", projectcode='" + projectcode + '\'' +
            ", externalreference='" + externalreference + '\'' +
            ", internalreferencedate=" + internalreferencedate +
            ", updatewarehouseavailability=" + updatewarehouseavailability +
            ", prddemandstocktypecode='" + prddemandstocktypecode + '\'' +
            ", entrywarehousecompanycode='" + entrywarehousecompanycode + '\'' +
            ", entrywarehousecode='" + entrywarehousecode + '\'' +
            ", entrylocwhszonephywhscmycode='" + entrylocwhszonephywhscmycode + '\'' +
            ", entrylocwhszonephywhscode='" + entrylocwhszonephywhscode + '\'' +
            ", entrylocationwarehousezonecode='" + entrylocationwarehousezonecode + '\'' +
            ", entrylocationcode='" + entrylocationcode + '\'' +
            ", warehousewipcompanycode='" + warehousewipcompanycode + '\'' +
            ", warehousewipcode='" + warehousewipcode + '\'' +
            ", wipcostcentercompanycode='" + wipcostcentercompanycode + '\'' +
            ", wipcostcentercode='" + wipcostcentercode + '\'' +
            ", itemtypeaficompanycode='" + itemtypeaficompanycode + '\'' +
            ", itemtypeaficode='" + itemtypeaficode + '\'' +
            ", subcode01='" + subcode01 + '\'' +
            ", subcode02='" + subcode02 + '\'' +
            ", subcode03='" + subcode03 + '\'' +
            ", subcode04='" + subcode04 + '\'' +
            ", subcode05='" + subcode05 + '\'' +
            ", subcode06='" + subcode06 + '\'' +
            ", subcode07='" + subcode07 + '\'' +
            ", subcode08='" + subcode08 + '\'' +
            ", subcode09='" + subcode09 + '\'' +
            ", subcode10='" + subcode10 + '\'' +
            ", qualitycode=" + qualitycode +
            ", bomnumberid=" + bomnumberid +
            ", routingnumberid=" + routingnumberid +
            ", splittingmanagement=" + splittingmanagement +
            ", productiongroupcode='" + productiongroupcode + '\'' +
            ", userprimaryuomcode='" + userprimaryuomcode + '\'' +
            ", userprimaryquantity=" + userprimaryquantity +
            ", baseprimaryuomcode='" + baseprimaryuomcode + '\'' +
            ", baseprimaryquantity=" + baseprimaryquantity +
            ", usersecondaryuomcode='" + usersecondaryuomcode + '\'' +
            ", usersecondaryquantity=" + usersecondaryquantity +
            ", basesecondaryuomcode='" + basesecondaryuomcode + '\'' +
            ", basesecondaryquantity=" + basesecondaryquantity +
            ", userpackaginguomcode='" + userpackaginguomcode + '\'' +
            ", userpackagingquantity=" + userpackagingquantity +
            ", finaluserprimaryquantity=" + finaluserprimaryquantity +
            ", finalbaseprimaryquantity=" + finalbaseprimaryquantity +
            ", finalusersecondaryquantity=" + finalusersecondaryquantity +
            ", finalbasesecondaryquantity=" + finalbasesecondaryquantity +
            ", finaluserpackagingquantity=" + finaluserpackagingquantity +
            ", entereduserprimaryquantity=" + entereduserprimaryquantity +
            ", enteredbaseprimaryquantity=" + enteredbaseprimaryquantity +
            ", enteredusersecondaryquantity=" + enteredusersecondaryquantity +
            ", enteredbasesecondaryquantity=" + enteredbasesecondaryquantity +
            ", entereduserpackagingquantity=" + entereduserpackagingquantity +
            ", uomtype='" + uomtype + '\'' +
            ", stdproductionbatch=" + stdproductionbatch +
            ", stdproductionbatchuomcode='" + stdproductionbatchuomcode + '\'' +
            ", quantityperperiod=" + quantityperperiod +
            ", numberofperiods=" + numberofperiods +
            ", initialplanneddate=" + initialplanneddate +
            ", finalplanneddate=" + finalplanneddate +
            ", initialplannedscheduleddate=" + initialplannedscheduleddate +
            ", finalplannedscheduleddate=" + finalplannedscheduleddate +
            ", initialscheduleddate=" + initialscheduleddate +
            ", finalscheduleddate=" + finalscheduleddate +
            ", initialeffectivedate=" + initialeffectivedate +
            ", finaleffectivedate=" + finaleffectivedate +
            ", destinationorder='" + destinationorder + '\'' +
            ", dlvsalordlinesalordcntcode='" + dlvsalordlinesalordcntcode + '\'' +
            ", dlvsalorderlinesalesordercode='" + dlvsalorderlinesalesordercode + '\'' +
            ", dlvsalesorderlineorderline=" + dlvsalesorderlineorderline +
            ", dlvsalesorderlineordersubline=" + dlvsalesorderlineordersubline +
            ", dlvsalordlinecmporderline=" + dlvsalordlinecmporderline +
            ", deliverydeliveryline=" + deliverydeliveryline +
            ", reservationordercountercode='" + reservationordercountercode + '\'' +
            ", reservationordercode='" + reservationordercode + '\'' +
            ", reservationreservationline=" + reservationreservationline +
            ", origdlvsalordlinesalordcntcod='" + origdlvsalordlinesalordcntcod + '\'' +
            ", origdlvsalordlinesalordercode='" + origdlvsalordlinesalordercode + '\'' +
            ", origdlvsalorderlineorderline=" + origdlvsalorderlineorderline +
            ", origdlvsalordlineordersubline=" + origdlvsalordlineordersubline +
            ", origdlvsalordlinecmporderline=" + origdlvsalordlinecmporderline +
            ", origdeliverydeliveryline=" + origdeliverydeliveryline +
            ", intdlvintordlineintordcntcode='" + intdlvintordlineintordcntcode + '\'' +
            ", intdlvintordlineintordercode='" + intdlvintordlineintordercode + '\'' +
            ", intdlvintorderlineorderline=" + intdlvintorderlineorderline +
            ", intdlvintordlineordersubline=" + intdlvintordlineordersubline +
            ", intdeliverydeliveryline=" + intdeliverydeliveryline +
            ", intdocintdocprvcountercode='" + intdocintdocprvcountercode + '\'' +
            ", intdocintdocprovisionalcode='" + intdocintdocprovisionalcode + '\'' +
            ", intdocumentorderline=" + intdocumentorderline +
            ", intdocumentordersubline=" + intdocumentordersubline +
            ", externalreferencedate=" + externalreferencedate +
            ", internalreference='" + internalreference + '\'' +
            ", currentstatus='" + currentstatus + '\'' +
            ", manualclosurereason=" + manualclosurereason +
            ", progressstatus='" + progressstatus + '\'' +
            ", fullitemidentifier=" + fullitemidentifier +
            ", creationdatetime=" + creationdatetime +
            ", creationuser='" + creationuser + '\'' +
            ", lastupdatedatetime=" + lastupdatedatetime +
            ", lastupdateuser='" + lastupdateuser + '\'' +
            ", absuniqueid=" + absuniqueid +
            '}';
    }
}
