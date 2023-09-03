package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "Warehouseitemcost")
public class Warehouseitemcost {
    @EmbeddedId
    private WarehouseitemcostId id;
    private String whsaccountinggroupcompanycode;
    private BigInteger costidentifier;
    private String itemtypeaficompanycode;
    private String qualitylvlitemtypecompanycode;
    private String statisticalgroupcompanycode;
    private BigDecimal lastinboundcost;
    private Date lastinboundcostlastupdatedate;
    private BigDecimal standardcost;
    private Date standardcostlastupdatedate;
    private BigDecimal weightedaveragecost;
    private String basecostunitcode;
    private BigDecimal dynamicaveragecosttotalvalue;
    private BigDecimal dynamicaveragecosttotalqty;
    private BigDecimal dynamicaveragecostunitvalue;
    private BigDecimal hifocost;
    private Date hifocostlastupdatedate;
    private BigDecimal secondstandardcost;
    private Date sndstandardcostlastupdatedate;
    private Timestamp creationdatetime;
    private String creationuser;
    private Timestamp lastupdatedatetime;
    private String lastupdateuser;
    private Long absuniqueid;

    public WarehouseitemcostId getId() {
        return id;
    }

    public void setId(WarehouseitemcostId id) {
        this.id = id;
    }

    @Basic
    @Column(name = "WHSACCOUNTINGGROUPCOMPANYCODE", nullable = false, length = 3)
    public String getWhsaccountinggroupcompanycode() {
        return whsaccountinggroupcompanycode;
    }

    public void setWhsaccountinggroupcompanycode(String whsaccountinggroupcompanycode) {
        this.whsaccountinggroupcompanycode = whsaccountinggroupcompanycode;
    }

    @Basic
    @Column(name = "COSTIDENTIFIER", nullable = false, precision = 0)
    public BigInteger getCostidentifier() {
        return costidentifier;
    }

    public void setCostidentifier(BigInteger costidentifier) {
        this.costidentifier = costidentifier;
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
    @Column(name = "QUALITYLVLITEMTYPECOMPANYCODE", nullable = false, length = 3)
    public String getQualitylvlitemtypecompanycode() {
        return qualitylvlitemtypecompanycode;
    }

    public void setQualitylvlitemtypecompanycode(String qualitylvlitemtypecompanycode) {
        this.qualitylvlitemtypecompanycode = qualitylvlitemtypecompanycode;
    }

    @Basic
    @Column(name = "STATISTICALGROUPCOMPANYCODE", nullable = false, length = 3)
    public String getStatisticalgroupcompanycode() {
        return statisticalgroupcompanycode;
    }

    public void setStatisticalgroupcompanycode(String statisticalgroupcompanycode) {
        this.statisticalgroupcompanycode = statisticalgroupcompanycode;
    }

    @Basic
    @Column(name = "LASTINBOUNDCOST", nullable = true, precision = 5)
    public BigDecimal getLastinboundcost() {
        return lastinboundcost;
    }

    public void setLastinboundcost(BigDecimal lastinboundcost) {
        this.lastinboundcost = lastinboundcost;
    }

    @Basic
    @Column(name = "LASTINBOUNDCOSTLASTUPDATEDATE", nullable = true)
    public Date getLastinboundcostlastupdatedate() {
        return lastinboundcostlastupdatedate;
    }

    public void setLastinboundcostlastupdatedate(Date lastinboundcostlastupdatedate) {
        this.lastinboundcostlastupdatedate = lastinboundcostlastupdatedate;
    }

    @Basic
    @Column(name = "STANDARDCOST", nullable = true, precision = 5)
    public BigDecimal getStandardcost() {
        return standardcost;
    }

    public void setStandardcost(BigDecimal standardcost) {
        this.standardcost = standardcost;
    }

    @Basic
    @Column(name = "STANDARDCOSTLASTUPDATEDATE", nullable = true)
    public Date getStandardcostlastupdatedate() {
        return standardcostlastupdatedate;
    }

    public void setStandardcostlastupdatedate(Date standardcostlastupdatedate) {
        this.standardcostlastupdatedate = standardcostlastupdatedate;
    }

    @Basic
    @Column(name = "WEIGHTEDAVERAGECOST", nullable = true, precision = 5)
    public BigDecimal getWeightedaveragecost() {
        return weightedaveragecost;
    }

    public void setWeightedaveragecost(BigDecimal weightedaveragecost) {
        this.weightedaveragecost = weightedaveragecost;
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
    @Column(name = "DYNAMICAVERAGECOSTTOTALVALUE", nullable = true, precision = 5)
    public BigDecimal getDynamicaveragecosttotalvalue() {
        return dynamicaveragecosttotalvalue;
    }

    public void setDynamicaveragecosttotalvalue(BigDecimal dynamicaveragecosttotalvalue) {
        this.dynamicaveragecosttotalvalue = dynamicaveragecosttotalvalue;
    }

    @Basic
    @Column(name = "DYNAMICAVERAGECOSTTOTALQTY", nullable = true, precision = 5)
    public BigDecimal getDynamicaveragecosttotalqty() {
        return dynamicaveragecosttotalqty;
    }

    public void setDynamicaveragecosttotalqty(BigDecimal dynamicaveragecosttotalqty) {
        this.dynamicaveragecosttotalqty = dynamicaveragecosttotalqty;
    }

    @Basic
    @Column(name = "DYNAMICAVERAGECOSTUNITVALUE", nullable = true, precision = 5)
    public BigDecimal getDynamicaveragecostunitvalue() {
        return dynamicaveragecostunitvalue;
    }

    public void setDynamicaveragecostunitvalue(BigDecimal dynamicaveragecostunitvalue) {
        this.dynamicaveragecostunitvalue = dynamicaveragecostunitvalue;
    }

    @Basic
    @Column(name = "HIFOCOST", nullable = true, precision = 5)
    public BigDecimal getHifocost() {
        return hifocost;
    }

    public void setHifocost(BigDecimal hifocost) {
        this.hifocost = hifocost;
    }

    @Basic
    @Column(name = "HIFOCOSTLASTUPDATEDATE", nullable = true)
    public Date getHifocostlastupdatedate() {
        return hifocostlastupdatedate;
    }

    public void setHifocostlastupdatedate(Date hifocostlastupdatedate) {
        this.hifocostlastupdatedate = hifocostlastupdatedate;
    }

    @Basic
    @Column(name = "SECONDSTANDARDCOST", nullable = true, precision = 5)
    public BigDecimal getSecondstandardcost() {
        return secondstandardcost;
    }

    public void setSecondstandardcost(BigDecimal secondstandardcost) {
        this.secondstandardcost = secondstandardcost;
    }

    @Basic
    @Column(name = "SNDSTANDARDCOSTLASTUPDATEDATE", nullable = true)
    public Date getSndstandardcostlastupdatedate() {
        return sndstandardcostlastupdatedate;
    }

    public void setSndstandardcostlastupdatedate(Date sndstandardcostlastupdatedate) {
        this.sndstandardcostlastupdatedate = sndstandardcostlastupdatedate;
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


}
