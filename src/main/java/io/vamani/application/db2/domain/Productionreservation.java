package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "productionreservation")
public class Productionreservation {
    @EmbeddedId
    private ProductionreservationId id;
    private String alloweddivisions;
    private String origintype;
    private Integer orderline;
    private Integer ordersubline;
    private String reservationgroupcode;
    private Integer stepnumber;
    private String productionordercode;
    private String prodreservationlinkgroupcode;
    private String costcentercompanycode;
    private String costcentercode;
    private String warehousecode;
    private String subcontractorsupplytype;
    private Short updatewarehouseavailability;
    private Short updatesbcentrywhsavailability;
    private Short updatesbcissuewhsavailability;
    private BigInteger qualitycode;
    private Date issuedate;
    private Date planscheduledissuedate;
    private Date scheduledissuedate;
    private String itemnature;
    private String reservationnature;
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
    private String suffixcode;
    private String variantcode;
    private Integer fullitemidentifier;
    private String referenceitem;
    private String rcpgrouppatreference;
    private Integer rcpgroupnopatlevel;
    private Integer obsoletediscardeditem;
    private Short splittingmanagement;
    private BigDecimal pickupquantity;
    private String userprimaryuomcode;
    private String usersecondaryuomcode;
    private BigDecimal userprimaryquantity;
    private BigDecimal baseprimaryquantity;
    private BigDecimal usersecondaryquantity;
    private BigDecimal basesecondaryquantity;
    private BigDecimal userpackagingquantity;
    private BigDecimal useduserprimaryquantity;
    private BigDecimal usedbaseprimaryquantity;
    private BigDecimal usedusersecondaryquantity;
    private BigDecimal usedbasesecondaryquantity;
    private BigDecimal useduserpackagingquantity;
    private BigDecimal sbcuseduserprimaryquantity;
    private BigDecimal sbcusedbaseprimaryquantity;
    private BigDecimal sbcusedusersecondaryquantity;
    private BigDecimal sbcusedbasesecondaryquantity;
    private BigDecimal sbcuseduserpackagingquantity;
    private BigDecimal dyelotweight;
    private Short packagingqtyfromstep;
    private String currentstatus;
    private Short runmanualreopen;
    private String progressstatus;
    private BigDecimal quantityper;
    private String calculateqtycode;
    private String wastetype1;
    private BigDecimal waste1;
    private String wastetype2;
    private BigDecimal waste2;
    private BigDecimal assemblyfixleadtimeadj;
    private Integer bomcompbillofmaterialnumberid;
    private Integer bomcompsequence;
    private Integer bomcompsubsequence;
    private String relatedcmpbillofmatitemtypecod;
    private String relatedcmpbillofmatsubcode01;
    private String relatedcmpbillofmatsubcode02;
    private String relatedcmpbillofmatsubcode03;
    private String relatedcmpbillofmatsubcode04;
    private String relatedcmpbillofmatsubcode05;
    private String relatedcmpbillofmatsubcode06;
    private String relatedcmpbillofmatsubcode07;
    private String relatedcmpbillofmatsubcode08;
    private String relatedcmpbillofmatsubcode09;
    private String relatedcmpbillofmatsubcode10;
    private String relatedcmpbillofmatsuffixcode;
    private Integer relatedcomponentsequence;
    private Integer relatedgroupline;
    private Integer relatedreservationline;
    private String policychangenature;
    private BigDecimal referencequantity;
    private String rounded;
    private Integer calculationsequence;
    private String bomuomtype;
    private BigDecimal bomquantity;
    private BigDecimal packagingquantity;
    private Timestamp creationdatetime;
    private String creationuser;
    private Timestamp lastupdatedatetime;
    private String lastupdateuser;
    private Short manualreservation;
    private Short manualmodifiedquantity;
    private Short manualmodifiedreservation;
    private Short noteditablereservation;
    private Integer groupline;
    private Integer groupstepnumber;
    private Short reservationingrouporder;
    private String headerlinelink;
    private Short subrecipereservation;
    private Long absuniqueid;
    private String projectcode;

    public ProductionreservationId getId() {
        return id;
    }

    public void setId(ProductionreservationId id) {
        this.id = id;
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
    @Column(name = "ORIGINTYPE", nullable = false, length = 2)
    public String getOrigintype() {
        return origintype;
    }

    public void setOrigintype(String origintype) {
        this.origintype = origintype;
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
    @Column(name = "RESERVATIONGROUPCODE", nullable = true, length = 3)
    public String getReservationgroupcode() {
        return reservationgroupcode;
    }

    public void setReservationgroupcode(String reservationgroupcode) {
        this.reservationgroupcode = reservationgroupcode;
    }

    @Basic
    @Column(name = "STEPNUMBER", nullable = true, precision = 0)
    public Integer getStepnumber() {
        return stepnumber;
    }

    public void setStepnumber(Integer stepnumber) {
        this.stepnumber = stepnumber;
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
    @Column(name = "COSTCENTERCOMPANYCODE", nullable = true, length = 3)
    public String getCostcentercompanycode() {
        return costcentercompanycode;
    }

    public void setCostcentercompanycode(String costcentercompanycode) {
        this.costcentercompanycode = costcentercompanycode;
    }

    @Basic
    @Column(name = "PRODRESERVATIONLINKGROUPCODE", nullable = true, length = 20)
    public String getProdreservationlinkgroupcode() {
        return prodreservationlinkgroupcode;
    }

    public void setProdreservationlinkgroupcode(String prodreservationlinkgroupcode) {
        this.prodreservationlinkgroupcode = prodreservationlinkgroupcode;
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
    @Column(name = "warehousecode", nullable = true, length = 8)
    public String getWarehousecode() {
        return warehousecode;
    }

    public void setWarehousecode(String warehousecode) {
        this.warehousecode = warehousecode;
    }


    @Basic
    @Column(name = "SUBCONTRACTORSUPPLYTYPE", nullable = true, length = 2)
    public String getSubcontractorsupplytype() {
        return subcontractorsupplytype;
    }

    public void setSubcontractorsupplytype(String subcontractorsupplytype) {
        this.subcontractorsupplytype = subcontractorsupplytype;
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
    @Column(name = "UPDATESBCENTRYWHSAVAILABILITY", nullable = false)
    public Short getUpdatesbcentrywhsavailability() {
        return updatesbcentrywhsavailability;
    }

    public void setUpdatesbcentrywhsavailability(Short updatesbcentrywhsavailability) {
        this.updatesbcentrywhsavailability = updatesbcentrywhsavailability;
    }

    @Basic
    @Column(name = "UPDATESBCISSUEWHSAVAILABILITY", nullable = false)
    public Short getUpdatesbcissuewhsavailability() {
        return updatesbcissuewhsavailability;
    }

    public void setUpdatesbcissuewhsavailability(Short updatesbcissuewhsavailability) {
        this.updatesbcissuewhsavailability = updatesbcissuewhsavailability;
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
    @Column(name = "ISSUEDATE", nullable = false)
    public Date getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(Date issuedate) {
        this.issuedate = issuedate;
    }

    @Basic
    @Column(name = "PLANSCHEDULEDISSUEDATE", nullable = true)
    public Date getPlanscheduledissuedate() {
        return planscheduledissuedate;
    }

    public void setPlanscheduledissuedate(Date planscheduledissuedate) {
        this.planscheduledissuedate = planscheduledissuedate;
    }

    @Basic
    @Column(name = "SCHEDULEDISSUEDATE", nullable = true)
    public Date getScheduledissuedate() {
        return scheduledissuedate;
    }

    public void setScheduledissuedate(Date scheduledissuedate) {
        this.scheduledissuedate = scheduledissuedate;
    }

    @Basic
    @Column(name = "ITEMNATURE", nullable = true, length = 1)
    public String getItemnature() {
        return itemnature;
    }

    public void setItemnature(String itemnature) {
        this.itemnature = itemnature;
    }

    @Basic
    @Column(name = "RESERVATIONNATURE", nullable = true, length = 1)
    public String getReservationnature() {
        return reservationnature;
    }

    public void setReservationnature(String reservationnature) {
        this.reservationnature = reservationnature;
    }

    @Basic
    @Column(name = "ITEMTYPEAFICOMPANYCODE", nullable = false, length = 3)
    public String getItemtypeaficompanycode() {
        return itemtypeaficompanycode;
    }

    public void setItemtypeaficompanycode(String itemtypeaficompanycode) {
        this.itemtypeaficompanycode = itemtypeaficompanycode;
    }

    @Basic
    @Column(name = "ITEMTYPEAFICODE", nullable = false, length = 3)
    public String getItemtypeaficode() {
        return itemtypeaficode;
    }

    public void setItemtypeaficode(String itemtypeaficode) {
        this.itemtypeaficode = itemtypeaficode;
    }

    @Basic
    @Column(name = "SUBCODE01", nullable = false, length = 20)
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
    @Column(name = "SUFFIXCODE", nullable = true, length = 20)
    public String getSuffixcode() {
        return suffixcode;
    }

    public void setSuffixcode(String suffixcode) {
        this.suffixcode = suffixcode;
    }

    @Basic
    @Column(name = "VARIANTCODE", nullable = true, length = 20)
    public String getVariantcode() {
        return variantcode;
    }

    public void setVariantcode(String variantcode) {
        this.variantcode = variantcode;
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
    @Column(name = "REFERENCEITEM", nullable = true, length = 2)
    public String getReferenceitem() {
        return referenceitem;
    }

    public void setReferenceitem(String referenceitem) {
        this.referenceitem = referenceitem;
    }

    @Basic
    @Column(name = "RCPGROUPPATREFERENCE", nullable = true, length = 3)
    public String getRcpgrouppatreference() {
        return rcpgrouppatreference;
    }

    public void setRcpgrouppatreference(String rcpgrouppatreference) {
        this.rcpgrouppatreference = rcpgrouppatreference;
    }

    @Basic
    @Column(name = "RCPGROUPNOPATLEVEL", nullable = true, precision = 0)
    public Integer getRcpgroupnopatlevel() {
        return rcpgroupnopatlevel;
    }

    public void setRcpgroupnopatlevel(Integer rcpgroupnopatlevel) {
        this.rcpgroupnopatlevel = rcpgroupnopatlevel;
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
    @Column(name = "SPLITTINGMANAGEMENT", nullable = false)
    public Short getSplittingmanagement() {
        return splittingmanagement;
    }

    public void setSplittingmanagement(Short splittingmanagement) {
        this.splittingmanagement = splittingmanagement;
    }

    @Basic
    @Column(name = "PICKUPQUANTITY", nullable = true, precision = 5)
    public BigDecimal getPickupquantity() {
        return pickupquantity;
    }

    public void setPickupquantity(BigDecimal pickupquantity) {
        this.pickupquantity = pickupquantity;
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
    @Column(name = "USERSECONDARYUOMCODE", nullable = true, length = 3)
    public String getUsersecondaryuomcode() {
        return usersecondaryuomcode;
    }

    public void setUsersecondaryuomcode(String usersecondaryuomcode) {
        this.usersecondaryuomcode = usersecondaryuomcode;
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
    @Column(name = "BASEPRIMARYQUANTITY", nullable = true, precision = 5)
    public BigDecimal getBaseprimaryquantity() {
        return baseprimaryquantity;
    }

    public void setBaseprimaryquantity(BigDecimal baseprimaryquantity) {
        this.baseprimaryquantity = baseprimaryquantity;
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
    @Column(name = "BASESECONDARYQUANTITY", nullable = true, precision = 5)
    public BigDecimal getBasesecondaryquantity() {
        return basesecondaryquantity;
    }

    public void setBasesecondaryquantity(BigDecimal basesecondaryquantity) {
        this.basesecondaryquantity = basesecondaryquantity;
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
    @Column(name = "USEDUSERPRIMARYQUANTITY", nullable = false, precision = 5)
    public BigDecimal getUseduserprimaryquantity() {
        return useduserprimaryquantity;
    }

    public void setUseduserprimaryquantity(BigDecimal useduserprimaryquantity) {
        this.useduserprimaryquantity = useduserprimaryquantity;
    }

    @Basic
    @Column(name = "USEDBASEPRIMARYQUANTITY", nullable = false, precision = 5)
    public BigDecimal getUsedbaseprimaryquantity() {
        return usedbaseprimaryquantity;
    }

    public void setUsedbaseprimaryquantity(BigDecimal usedbaseprimaryquantity) {
        this.usedbaseprimaryquantity = usedbaseprimaryquantity;
    }

    @Basic
    @Column(name = "USEDUSERSECONDARYQUANTITY", nullable = false, precision = 5)
    public BigDecimal getUsedusersecondaryquantity() {
        return usedusersecondaryquantity;
    }

    public void setUsedusersecondaryquantity(BigDecimal usedusersecondaryquantity) {
        this.usedusersecondaryquantity = usedusersecondaryquantity;
    }

    @Basic
    @Column(name = "USEDBASESECONDARYQUANTITY", nullable = false, precision = 5)
    public BigDecimal getUsedbasesecondaryquantity() {
        return usedbasesecondaryquantity;
    }

    public void setUsedbasesecondaryquantity(BigDecimal usedbasesecondaryquantity) {
        this.usedbasesecondaryquantity = usedbasesecondaryquantity;
    }

    @Basic
    @Column(name = "USEDUSERPACKAGINGQUANTITY", nullable = false, precision = 5)
    public BigDecimal getUseduserpackagingquantity() {
        return useduserpackagingquantity;
    }

    public void setUseduserpackagingquantity(BigDecimal useduserpackagingquantity) {
        this.useduserpackagingquantity = useduserpackagingquantity;
    }

    @Basic
    @Column(name = "SBCUSEDUSERPRIMARYQUANTITY", nullable = false, precision = 5)
    public BigDecimal getSbcuseduserprimaryquantity() {
        return sbcuseduserprimaryquantity;
    }

    public void setSbcuseduserprimaryquantity(BigDecimal sbcuseduserprimaryquantity) {
        this.sbcuseduserprimaryquantity = sbcuseduserprimaryquantity;
    }

    @Basic
    @Column(name = "SBCUSEDBASEPRIMARYQUANTITY", nullable = false, precision = 5)
    public BigDecimal getSbcusedbaseprimaryquantity() {
        return sbcusedbaseprimaryquantity;
    }

    public void setSbcusedbaseprimaryquantity(BigDecimal sbcusedbaseprimaryquantity) {
        this.sbcusedbaseprimaryquantity = sbcusedbaseprimaryquantity;
    }

    @Basic
    @Column(name = "SBCUSEDUSERSECONDARYQUANTITY", nullable = false, precision = 5)
    public BigDecimal getSbcusedusersecondaryquantity() {
        return sbcusedusersecondaryquantity;
    }

    public void setSbcusedusersecondaryquantity(BigDecimal sbcusedusersecondaryquantity) {
        this.sbcusedusersecondaryquantity = sbcusedusersecondaryquantity;
    }

    @Basic
    @Column(name = "SBCUSEDBASESECONDARYQUANTITY", nullable = false, precision = 5)
    public BigDecimal getSbcusedbasesecondaryquantity() {
        return sbcusedbasesecondaryquantity;
    }

    public void setSbcusedbasesecondaryquantity(BigDecimal sbcusedbasesecondaryquantity) {
        this.sbcusedbasesecondaryquantity = sbcusedbasesecondaryquantity;
    }

    @Basic
    @Column(name = "SBCUSEDUSERPACKAGINGQUANTITY", nullable = false, precision = 5)
    public BigDecimal getSbcuseduserpackagingquantity() {
        return sbcuseduserpackagingquantity;
    }

    public void setSbcuseduserpackagingquantity(BigDecimal sbcuseduserpackagingquantity) {
        this.sbcuseduserpackagingquantity = sbcuseduserpackagingquantity;
    }

    @Basic
    @Column(name = "DYELOTWEIGHT", nullable = true, precision = 5)
    public BigDecimal getDyelotweight() {
        return dyelotweight;
    }

    public void setDyelotweight(BigDecimal dyelotweight) {
        this.dyelotweight = dyelotweight;
    }

    @Basic
    @Column(name = "PACKAGINGQTYFROMSTEP", nullable = false)
    public Short getPackagingqtyfromstep() {
        return packagingqtyfromstep;
    }

    public void setPackagingqtyfromstep(Short packagingqtyfromstep) {
        this.packagingqtyfromstep = packagingqtyfromstep;
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
    @Column(name = "RUNMANUALREOPEN", nullable = false)
    public Short getRunmanualreopen() {
        return runmanualreopen;
    }

    public void setRunmanualreopen(Short runmanualreopen) {
        this.runmanualreopen = runmanualreopen;
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
    @Column(name = "QUANTITYPER", nullable = true, precision = 5)
    public BigDecimal getQuantityper() {
        return quantityper;
    }

    public void setQuantityper(BigDecimal quantityper) {
        this.quantityper = quantityper;
    }

    @Basic
    @Column(name = "CALCULATEQTYCODE", nullable = true, length = 20)
    public String getCalculateqtycode() {
        return calculateqtycode;
    }

    public void setCalculateqtycode(String calculateqtycode) {
        this.calculateqtycode = calculateqtycode;
    }

    @Basic
    @Column(name = "WASTETYPE1", nullable = true, length = 2)
    public String getWastetype1() {
        return wastetype1;
    }

    public void setWastetype1(String wastetype1) {
        this.wastetype1 = wastetype1;
    }

    @Basic
    @Column(name = "WASTE1", nullable = true, precision = 2)
    public BigDecimal getWaste1() {
        return waste1;
    }

    public void setWaste1(BigDecimal waste1) {
        this.waste1 = waste1;
    }

    @Basic
    @Column(name = "WASTETYPE2", nullable = true, length = 2)
    public String getWastetype2() {
        return wastetype2;
    }

    public void setWastetype2(String wastetype2) {
        this.wastetype2 = wastetype2;
    }

    @Basic
    @Column(name = "WASTE2", nullable = true, precision = 2)
    public BigDecimal getWaste2() {
        return waste2;
    }

    public void setWaste2(BigDecimal waste2) {
        this.waste2 = waste2;
    }

    @Basic
    @Column(name = "ASSEMBLYFIXLEADTIMEADJ", nullable = true, precision = 5)
    public BigDecimal getAssemblyfixleadtimeadj() {
        return assemblyfixleadtimeadj;
    }

    public void setAssemblyfixleadtimeadj(BigDecimal assemblyfixleadtimeadj) {
        this.assemblyfixleadtimeadj = assemblyfixleadtimeadj;
    }

    @Basic
    @Column(name = "BOMCOMPBILLOFMATERIALNUMBERID", nullable = true, precision = 0)
    public Integer getBomcompbillofmaterialnumberid() {
        return bomcompbillofmaterialnumberid;
    }

    public void setBomcompbillofmaterialnumberid(Integer bomcompbillofmaterialnumberid) {
        this.bomcompbillofmaterialnumberid = bomcompbillofmaterialnumberid;
    }

    @Basic
    @Column(name = "BOMCOMPSEQUENCE", nullable = true, precision = 0)
    public Integer getBomcompsequence() {
        return bomcompsequence;
    }

    public void setBomcompsequence(Integer bomcompsequence) {
        this.bomcompsequence = bomcompsequence;
    }

    @Basic
    @Column(name = "BOMCOMPSUBSEQUENCE", nullable = true, precision = 0)
    public Integer getBomcompsubsequence() {
        return bomcompsubsequence;
    }

    public void setBomcompsubsequence(Integer bomcompsubsequence) {
        this.bomcompsubsequence = bomcompsubsequence;
    }

    @Basic
    @Column(name = "RELATEDCMPBILLOFMATITEMTYPECOD", nullable = true, length = 3)
    public String getRelatedcmpbillofmatitemtypecod() {
        return relatedcmpbillofmatitemtypecod;
    }

    public void setRelatedcmpbillofmatitemtypecod(String relatedcmpbillofmatitemtypecod) {
        this.relatedcmpbillofmatitemtypecod = relatedcmpbillofmatitemtypecod;
    }

    @Basic
    @Column(name = "RELATEDCMPBILLOFMATSUBCODE01", nullable = true, length = 20)
    public String getRelatedcmpbillofmatsubcode01() {
        return relatedcmpbillofmatsubcode01;
    }

    public void setRelatedcmpbillofmatsubcode01(String relatedcmpbillofmatsubcode01) {
        this.relatedcmpbillofmatsubcode01 = relatedcmpbillofmatsubcode01;
    }

    @Basic
    @Column(name = "RELATEDCMPBILLOFMATSUBCODE02", nullable = true, length = 10)
    public String getRelatedcmpbillofmatsubcode02() {
        return relatedcmpbillofmatsubcode02;
    }

    public void setRelatedcmpbillofmatsubcode02(String relatedcmpbillofmatsubcode02) {
        this.relatedcmpbillofmatsubcode02 = relatedcmpbillofmatsubcode02;
    }

    @Basic
    @Column(name = "RELATEDCMPBILLOFMATSUBCODE03", nullable = true, length = 10)
    public String getRelatedcmpbillofmatsubcode03() {
        return relatedcmpbillofmatsubcode03;
    }

    public void setRelatedcmpbillofmatsubcode03(String relatedcmpbillofmatsubcode03) {
        this.relatedcmpbillofmatsubcode03 = relatedcmpbillofmatsubcode03;
    }

    @Basic
    @Column(name = "RELATEDCMPBILLOFMATSUBCODE04", nullable = true, length = 10)
    public String getRelatedcmpbillofmatsubcode04() {
        return relatedcmpbillofmatsubcode04;
    }

    public void setRelatedcmpbillofmatsubcode04(String relatedcmpbillofmatsubcode04) {
        this.relatedcmpbillofmatsubcode04 = relatedcmpbillofmatsubcode04;
    }

    @Basic
    @Column(name = "RELATEDCMPBILLOFMATSUBCODE05", nullable = true, length = 10)
    public String getRelatedcmpbillofmatsubcode05() {
        return relatedcmpbillofmatsubcode05;
    }

    public void setRelatedcmpbillofmatsubcode05(String relatedcmpbillofmatsubcode05) {
        this.relatedcmpbillofmatsubcode05 = relatedcmpbillofmatsubcode05;
    }

    @Basic
    @Column(name = "RELATEDCMPBILLOFMATSUBCODE06", nullable = true, length = 10)
    public String getRelatedcmpbillofmatsubcode06() {
        return relatedcmpbillofmatsubcode06;
    }

    public void setRelatedcmpbillofmatsubcode06(String relatedcmpbillofmatsubcode06) {
        this.relatedcmpbillofmatsubcode06 = relatedcmpbillofmatsubcode06;
    }

    @Basic
    @Column(name = "RELATEDCMPBILLOFMATSUBCODE07", nullable = true, length = 10)
    public String getRelatedcmpbillofmatsubcode07() {
        return relatedcmpbillofmatsubcode07;
    }

    public void setRelatedcmpbillofmatsubcode07(String relatedcmpbillofmatsubcode07) {
        this.relatedcmpbillofmatsubcode07 = relatedcmpbillofmatsubcode07;
    }

    @Basic
    @Column(name = "RELATEDCMPBILLOFMATSUBCODE08", nullable = true, length = 10)
    public String getRelatedcmpbillofmatsubcode08() {
        return relatedcmpbillofmatsubcode08;
    }

    public void setRelatedcmpbillofmatsubcode08(String relatedcmpbillofmatsubcode08) {
        this.relatedcmpbillofmatsubcode08 = relatedcmpbillofmatsubcode08;
    }

    @Basic
    @Column(name = "RELATEDCMPBILLOFMATSUBCODE09", nullable = true, length = 10)
    public String getRelatedcmpbillofmatsubcode09() {
        return relatedcmpbillofmatsubcode09;
    }

    public void setRelatedcmpbillofmatsubcode09(String relatedcmpbillofmatsubcode09) {
        this.relatedcmpbillofmatsubcode09 = relatedcmpbillofmatsubcode09;
    }

    @Basic
    @Column(name = "RELATEDCMPBILLOFMATSUBCODE10", nullable = true, length = 10)
    public String getRelatedcmpbillofmatsubcode10() {
        return relatedcmpbillofmatsubcode10;
    }

    public void setRelatedcmpbillofmatsubcode10(String relatedcmpbillofmatsubcode10) {
        this.relatedcmpbillofmatsubcode10 = relatedcmpbillofmatsubcode10;
    }

    @Basic
    @Column(name = "RELATEDCMPBILLOFMATSUFFIXCODE", nullable = true, length = 20)
    public String getRelatedcmpbillofmatsuffixcode() {
        return relatedcmpbillofmatsuffixcode;
    }

    public void setRelatedcmpbillofmatsuffixcode(String relatedcmpbillofmatsuffixcode) {
        this.relatedcmpbillofmatsuffixcode = relatedcmpbillofmatsuffixcode;
    }

    @Basic
    @Column(name = "RELATEDCOMPONENTSEQUENCE", nullable = true, precision = 0)
    public Integer getRelatedcomponentsequence() {
        return relatedcomponentsequence;
    }

    public void setRelatedcomponentsequence(Integer relatedcomponentsequence) {
        this.relatedcomponentsequence = relatedcomponentsequence;
    }

    @Basic
    @Column(name = "RELATEDGROUPLINE", nullable = false)
    public Integer getRelatedgroupline() {
        return relatedgroupline;
    }

    public void setRelatedgroupline(Integer relatedgroupline) {
        this.relatedgroupline = relatedgroupline;
    }

    @Basic
    @Column(name = "RELATEDRESERVATIONLINE", nullable = true, precision = 0)
    public Integer getRelatedreservationline() {
        return relatedreservationline;
    }

    public void setRelatedreservationline(Integer relatedreservationline) {
        this.relatedreservationline = relatedreservationline;
    }

    @Basic
    @Column(name = "POLICYCHANGENATURE", nullable = true, length = 2)
    public String getPolicychangenature() {
        return policychangenature;
    }

    public void setPolicychangenature(String policychangenature) {
        this.policychangenature = policychangenature;
    }

    @Basic
    @Column(name = "REFERENCEQUANTITY", nullable = true, precision = 5)
    public BigDecimal getReferencequantity() {
        return referencequantity;
    }

    public void setReferencequantity(BigDecimal referencequantity) {
        this.referencequantity = referencequantity;
    }

    @Basic
    @Column(name = "ROUNDED", nullable = true, length = 1)
    public String getRounded() {
        return rounded;
    }

    public void setRounded(String rounded) {
        this.rounded = rounded;
    }

    @Basic
    @Column(name = "CALCULATIONSEQUENCE", nullable = false)
    public Integer getCalculationsequence() {
        return calculationsequence;
    }

    public void setCalculationsequence(Integer calculationsequence) {
        this.calculationsequence = calculationsequence;
    }

    @Basic
    @Column(name = "BOMUOMTYPE", nullable = true, length = 2)
    public String getBomuomtype() {
        return bomuomtype;
    }

    public void setBomuomtype(String bomuomtype) {
        this.bomuomtype = bomuomtype;
    }

    @Basic
    @Column(name = "BOMQUANTITY", nullable = true, precision = 5)
    public BigDecimal getBomquantity() {
        return bomquantity;
    }

    public void setBomquantity(BigDecimal bomquantity) {
        this.bomquantity = bomquantity;
    }

    @Basic
    @Column(name = "PACKAGINGQUANTITY", nullable = true, precision = 5)
    public BigDecimal getPackagingquantity() {
        return packagingquantity;
    }

    public void setPackagingquantity(BigDecimal packagingquantity) {
        this.packagingquantity = packagingquantity;
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
    @Column(name = "MANUALRESERVATION", nullable = false)
    public Short getManualreservation() {
        return manualreservation;
    }

    public void setManualreservation(Short manualreservation) {
        this.manualreservation = manualreservation;
    }

    @Basic
    @Column(name = "MANUALMODIFIEDQUANTITY", nullable = false)
    public Short getManualmodifiedquantity() {
        return manualmodifiedquantity;
    }

    public void setManualmodifiedquantity(Short manualmodifiedquantity) {
        this.manualmodifiedquantity = manualmodifiedquantity;
    }

    @Basic
    @Column(name = "MANUALMODIFIEDRESERVATION", nullable = false)
    public Short getManualmodifiedreservation() {
        return manualmodifiedreservation;
    }

    public void setManualmodifiedreservation(Short manualmodifiedreservation) {
        this.manualmodifiedreservation = manualmodifiedreservation;
    }

    @Basic
    @Column(name = "NOTEDITABLERESERVATION", nullable = false)
    public Short getNoteditablereservation() {
        return noteditablereservation;
    }

    public void setNoteditablereservation(Short noteditablereservation) {
        this.noteditablereservation = noteditablereservation;
    }

    @Basic
    @Column(name = "GROUPLINE", nullable = false)
    public Integer getGroupline() {
        return groupline;
    }

    public void setGroupline(Integer groupline) {
        this.groupline = groupline;
    }

    @Basic
    @Column(name = "GROUPSTEPNUMBER", nullable = false)
    public Integer getGroupstepnumber() {
        return groupstepnumber;
    }

    public void setGroupstepnumber(Integer groupstepnumber) {
        this.groupstepnumber = groupstepnumber;
    }

    @Basic
    @Column(name = "RESERVATIONINGROUPORDER", nullable = false)
    public Short getReservationingrouporder() {
        return reservationingrouporder;
    }

    public void setReservationingrouporder(Short reservationingrouporder) {
        this.reservationingrouporder = reservationingrouporder;
    }

    @Basic
    @Column(name = "HEADERLINELINK", nullable = true, length = 140)
    public String getHeaderlinelink() {
        return headerlinelink;
    }

    public void setHeaderlinelink(String headerlinelink) {
        this.headerlinelink = headerlinelink;
    }

    @Basic
    @Column(name = "SUBRECIPERESERVATION", nullable = false)
    public Short getSubrecipereservation() {
        return subrecipereservation;
    }

    public void setSubrecipereservation(Short subrecipereservation) {
        this.subrecipereservation = subrecipereservation;
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
    @Column(name = "PROJECTCODE")
    public String getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Productionreservation that = (Productionreservation) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(alloweddivisions, that.alloweddivisions) &&
            Objects.equals(origintype, that.origintype) &&
            Objects.equals(orderline, that.orderline) &&
            Objects.equals(ordersubline, that.ordersubline) &&
            Objects.equals(stepnumber, that.stepnumber) &&
            Objects.equals(productionordercode, that.productionordercode) &&
            Objects.equals(subcontractorsupplytype, that.subcontractorsupplytype) &&
            Objects.equals(updatewarehouseavailability, that.updatewarehouseavailability) &&
            Objects.equals(updatesbcentrywhsavailability, that.updatesbcentrywhsavailability) &&
            Objects.equals(updatesbcissuewhsavailability, that.updatesbcissuewhsavailability) &&
            Objects.equals(qualitycode, that.qualitycode) &&
            Objects.equals(issuedate, that.issuedate) &&
            Objects.equals(planscheduledissuedate, that.planscheduledissuedate) &&
            Objects.equals(scheduledissuedate, that.scheduledissuedate) &&
            Objects.equals(itemnature, that.itemnature) &&
            Objects.equals(reservationnature, that.reservationnature) &&
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
            Objects.equals(suffixcode, that.suffixcode) &&
            Objects.equals(variantcode, that.variantcode) &&
            Objects.equals(fullitemidentifier, that.fullitemidentifier) &&
            Objects.equals(referenceitem, that.referenceitem) &&
            Objects.equals(rcpgrouppatreference, that.rcpgrouppatreference) &&
            Objects.equals(rcpgroupnopatlevel, that.rcpgroupnopatlevel) &&
            Objects.equals(obsoletediscardeditem, that.obsoletediscardeditem) &&
            Objects.equals(splittingmanagement, that.splittingmanagement) &&
            Objects.equals(pickupquantity, that.pickupquantity) &&
            Objects.equals(userprimaryuomcode, that.userprimaryuomcode) &&
            Objects.equals(userprimaryquantity, that.userprimaryquantity) &&
            Objects.equals(baseprimaryquantity, that.baseprimaryquantity) &&
            Objects.equals(usersecondaryquantity, that.usersecondaryquantity) &&
            Objects.equals(basesecondaryquantity, that.basesecondaryquantity) &&
            Objects.equals(userpackagingquantity, that.userpackagingquantity) &&
            Objects.equals(useduserprimaryquantity, that.useduserprimaryquantity) &&
            Objects.equals(usedbaseprimaryquantity, that.usedbaseprimaryquantity) &&
            Objects.equals(usedusersecondaryquantity, that.usedusersecondaryquantity) &&
            Objects.equals(usedbasesecondaryquantity, that.usedbasesecondaryquantity) &&
            Objects.equals(useduserpackagingquantity, that.useduserpackagingquantity) &&
            Objects.equals(sbcuseduserprimaryquantity, that.sbcuseduserprimaryquantity) &&
            Objects.equals(sbcusedbaseprimaryquantity, that.sbcusedbaseprimaryquantity) &&
            Objects.equals(sbcusedusersecondaryquantity, that.sbcusedusersecondaryquantity) &&
            Objects.equals(sbcusedbasesecondaryquantity, that.sbcusedbasesecondaryquantity) &&
            Objects.equals(sbcuseduserpackagingquantity, that.sbcuseduserpackagingquantity) &&
            Objects.equals(dyelotweight, that.dyelotweight) &&
            Objects.equals(packagingqtyfromstep, that.packagingqtyfromstep) &&
            Objects.equals(currentstatus, that.currentstatus) &&
            Objects.equals(runmanualreopen, that.runmanualreopen) &&
            Objects.equals(progressstatus, that.progressstatus) &&
            Objects.equals(quantityper, that.quantityper) &&
            Objects.equals(calculateqtycode, that.calculateqtycode) &&
            Objects.equals(wastetype1, that.wastetype1) &&
            Objects.equals(waste1, that.waste1) &&
            Objects.equals(wastetype2, that.wastetype2) &&
            Objects.equals(waste2, that.waste2) &&
            Objects.equals(assemblyfixleadtimeadj, that.assemblyfixleadtimeadj) &&
            Objects.equals(bomcompbillofmaterialnumberid, that.bomcompbillofmaterialnumberid) &&
            Objects.equals(bomcompsequence, that.bomcompsequence) &&
            Objects.equals(bomcompsubsequence, that.bomcompsubsequence) &&
            Objects.equals(relatedcmpbillofmatitemtypecod, that.relatedcmpbillofmatitemtypecod) &&
            Objects.equals(relatedcmpbillofmatsubcode01, that.relatedcmpbillofmatsubcode01) &&
            Objects.equals(relatedcmpbillofmatsubcode02, that.relatedcmpbillofmatsubcode02) &&
            Objects.equals(relatedcmpbillofmatsubcode03, that.relatedcmpbillofmatsubcode03) &&
            Objects.equals(relatedcmpbillofmatsubcode04, that.relatedcmpbillofmatsubcode04) &&
            Objects.equals(relatedcmpbillofmatsubcode05, that.relatedcmpbillofmatsubcode05) &&
            Objects.equals(relatedcmpbillofmatsubcode06, that.relatedcmpbillofmatsubcode06) &&
            Objects.equals(relatedcmpbillofmatsubcode07, that.relatedcmpbillofmatsubcode07) &&
            Objects.equals(relatedcmpbillofmatsubcode08, that.relatedcmpbillofmatsubcode08) &&
            Objects.equals(relatedcmpbillofmatsubcode09, that.relatedcmpbillofmatsubcode09) &&
            Objects.equals(relatedcmpbillofmatsubcode10, that.relatedcmpbillofmatsubcode10) &&
            Objects.equals(relatedcmpbillofmatsuffixcode, that.relatedcmpbillofmatsuffixcode) &&
            Objects.equals(relatedcomponentsequence, that.relatedcomponentsequence) &&
            Objects.equals(relatedgroupline, that.relatedgroupline) &&
            Objects.equals(relatedreservationline, that.relatedreservationline) &&
            Objects.equals(policychangenature, that.policychangenature) &&
            Objects.equals(referencequantity, that.referencequantity) &&
            Objects.equals(rounded, that.rounded) &&
            Objects.equals(calculationsequence, that.calculationsequence) &&
            Objects.equals(bomuomtype, that.bomuomtype) &&
            Objects.equals(bomquantity, that.bomquantity) &&
            Objects.equals(packagingquantity, that.packagingquantity) &&
            Objects.equals(creationdatetime, that.creationdatetime) &&
            Objects.equals(creationuser, that.creationuser) &&
            Objects.equals(lastupdatedatetime, that.lastupdatedatetime) &&
            Objects.equals(lastupdateuser, that.lastupdateuser) &&
            Objects.equals(manualreservation, that.manualreservation) &&
            Objects.equals(manualmodifiedquantity, that.manualmodifiedquantity) &&
            Objects.equals(manualmodifiedreservation, that.manualmodifiedreservation) &&
            Objects.equals(noteditablereservation, that.noteditablereservation) &&
            Objects.equals(groupline, that.groupline) &&
            Objects.equals(groupstepnumber, that.groupstepnumber) &&
            Objects.equals(reservationingrouporder, that.reservationingrouporder) &&
            Objects.equals(headerlinelink, that.headerlinelink) &&
            Objects.equals(subrecipereservation, that.subrecipereservation) &&
            Objects.equals(absuniqueid, that.absuniqueid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, alloweddivisions, origintype, orderline, ordersubline, stepnumber, productionordercode, subcontractorsupplytype, updatewarehouseavailability, updatesbcentrywhsavailability, updatesbcissuewhsavailability, qualitycode, issuedate, planscheduledissuedate, scheduledissuedate, itemnature, reservationnature, subcode01, subcode02, subcode03, subcode04, subcode05, subcode06, subcode07, subcode08, subcode09, subcode10, suffixcode, variantcode, fullitemidentifier, referenceitem, rcpgrouppatreference, rcpgroupnopatlevel, obsoletediscardeditem, splittingmanagement, pickupquantity, userprimaryuomcode, userprimaryquantity, baseprimaryquantity, usersecondaryquantity, basesecondaryquantity, userpackagingquantity, useduserprimaryquantity, usedbaseprimaryquantity, usedusersecondaryquantity, usedbasesecondaryquantity, useduserpackagingquantity, sbcuseduserprimaryquantity, sbcusedbaseprimaryquantity, sbcusedusersecondaryquantity, sbcusedbasesecondaryquantity, sbcuseduserpackagingquantity, dyelotweight, packagingqtyfromstep, currentstatus, runmanualreopen, progressstatus, quantityper, calculateqtycode, wastetype1, waste1, wastetype2, waste2, assemblyfixleadtimeadj, bomcompbillofmaterialnumberid, bomcompsequence, bomcompsubsequence, relatedcmpbillofmatitemtypecod, relatedcmpbillofmatsubcode01, relatedcmpbillofmatsubcode02, relatedcmpbillofmatsubcode03, relatedcmpbillofmatsubcode04, relatedcmpbillofmatsubcode05, relatedcmpbillofmatsubcode06, relatedcmpbillofmatsubcode07, relatedcmpbillofmatsubcode08, relatedcmpbillofmatsubcode09, relatedcmpbillofmatsubcode10, relatedcmpbillofmatsuffixcode, relatedcomponentsequence, relatedgroupline, relatedreservationline, policychangenature, referencequantity, rounded, calculationsequence, bomuomtype, bomquantity, packagingquantity, creationdatetime, creationuser, lastupdatedatetime, lastupdateuser, manualreservation, manualmodifiedquantity, manualmodifiedreservation, noteditablereservation, groupline, groupstepnumber, reservationingrouporder, headerlinelink, subrecipereservation, absuniqueid);
    }
}
