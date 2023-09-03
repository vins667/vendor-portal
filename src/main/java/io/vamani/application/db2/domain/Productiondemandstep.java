package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "productiondemandstep")
public class Productiondemandstep implements Serializable {
    @EmbeddedId
    private ProductiondemandstepId id;
    private String itemtypeaficompanycode;
    private String itemtypeaficode;
    private Integer routingnumberid;
    private Integer stdroutingstepnumberid;
    private Integer stdroutingstepsequence;
    private Integer stdroutingstepsubsequence;
    private String plannedworkcentercode;
    private String plannedoperationcode;
    private String workcentercode;
    private String operationcode;
    private Short dyelothandled;
    private String workcenterandoperattributescod;
    private Short partialstep;
    private String progressstatus;
    private String steptype;
    private String prodreservationlinkgroupcode;
    private Integer maxnumberofresourcesallowed;
    private String warehousewipcompanycode;
    private String locwipissuewhszonephywhscmycod;
    private String locwipissuewhszonephywhscode;
    private String locwipissuewarehousezonecode;
    private String locationwipissuecode;
    private String locwipentrywhszonephywhscmycod;
    private String locwipentrywhszonephywhscode;
    private String locwipentrywarehousezonecode;
    private String locationwipentrycode;
    private String longdescription;
    private String shortdescription;
    private String searchdescription;
    private String productionordercode;
    private Integer stepnumberlink;
    private Timestamp initialplanneddatetime;
    private Timestamp finalplanneddatetime;
    private Timestamp initialplanscheddatetime;
    private Timestamp finalplanscheddatetime;
    private Timestamp initialscheduleddatetime;
    private Short scheduledstep;
    private Timestamp finalscheduleddatetime;
    private BigDecimal standardstepquantity;
    private String standardstepquantityuomcode;
    private String stepefficiencyapply;
    private BigDecimal stepefficiency;
    private Integer nrofmachine;
    private BigDecimal repetitionnumber;
    private BigDecimal bathvolume;
    private String currentstepprogress;
    private String previousstepprogress;
    private String opstepgroupcode;
    private Short generateautomaticqatest;
    private Short overlappingmanagement;
    private BigDecimal overlappingquantity;
    private String overlappinguomcategory;
    private String lossincreasetype1Code;
    private BigDecimal lossincrease1;
    private String lossincreaserefuom1Code;
    private String lossincreasetype2Code;
    private BigDecimal lossincrease2;
    private String lossincreaserefuom2Code;
    private String lossincreasetype3Code;
    private BigDecimal lossincrease3;
    private String lossincreaserefuom3Code;
    private String lossincreasetype4Code;
    private BigDecimal lossincrease4;
    private String lossincreaserefuom4Code;
    private String lossincreasetype5Code;
    private BigDecimal lossincrease5;
    private String lossincreaserefuom5Code;
    private String lossincreasetype6Code;
    private BigDecimal lossincrease6;
    private String lossincreaserefuom6Code;
    private String lossincreasetype7Code;
    private BigDecimal lossincrease7;
    private String lossincreaserefuom7Code;
    private String lossincreasetype8Code;
    private BigDecimal lossincrease8;
    private String lossincreaserefuom8Code;
    private String lossincreasetypepolicy;
    private Integer parallelpdnumber;
    private BigDecimal planningleadtime;
    private String timetype1Code;
    private BigDecimal time1;
    private String timeunit1;
    private BigDecimal timerefqty1;
    private String timerefuom1Code;
    private String timetype2Code;
    private BigDecimal time2;
    private String timeunit2;
    private BigDecimal timerefqty2;
    private String timerefuom2Code;
    private String timetype3Code;
    private BigDecimal time3;
    private String timeunit3;
    private BigDecimal timerefqty3;
    private String timerefuom3Code;
    private String timetype4Code;
    private BigDecimal time4;
    private String timeunit4;
    private BigDecimal timerefqty4;
    private String timerefuom4Code;
    private String timetype5Code;
    private BigDecimal time5;
    private String timeunit5;
    private BigDecimal timerefqty5;
    private String timerefuom5Code;
    private BigDecimal calculatedtime1;
    private BigDecimal calculatedtime2;
    private BigDecimal calculatedtime3;
    private BigDecimal calculatedtime4;
    private Date minbeginqueue;
    private Time minbeginqueuetime;
    private Integer weekminbeginqueue;
    private Integer yearminbeginqueue;
    private Date minbeginpresetup;
    private Time minbeginpresetuptime;
    private Integer weekminbeginpresetup;
    private Integer yearminbeginpresetup;
    private Date minbeginoperation;
    private Time minbeginoperationtime;
    private Integer weekminbeginoperation;
    private Integer yearminbeginoperation;
    private Date minbeginpostsetup;
    private Time minbeginpostsetuptime;
    private Integer weekminbeginpostsetup;
    private Integer yearminbeginpostsetup;
    private Date minendstep;
    private Time minendsteptime;
    private Integer weekminendstep;
    private Integer yearminendstep;
    private Date stdbeginqueue;
    private Time stdbeginqueuetime;
    private Integer weekstdbeginqueue;
    private Integer yearstdbeginqueue;
    private Date stdbeginpresetup;
    private Time stdbeginpresetuptime;
    private Integer weekstdbeginpresetup;
    private Integer yearstdbeginpresetup;
    private Date stdbeginoperation;
    private Time stdbeginoperationtime;
    private Integer weekstdbeginoperation;
    private Integer yearstdbeginoperation;
    private Date stdbeginpostsetup;
    private Time stdbeginpostsetuptime;
    private Integer weekstdbeginpostsetup;
    private Integer yearstdbeginpostsetup;
    private Date stdendstep;
    private Time stdendsteptime;
    private Integer weekstdendstep;
    private Integer yearstdendstep;
    private Date maxbeginqueue;
    private Time maxbeginqueuetime;
    private Integer weekmaxbeginqueue;
    private Integer yearmaxbeginqueue;
    private Date maxbeginpresetup;
    private Time maxbeginpresetuptime;
    private Integer weekmaxbeginpresetup;
    private Integer yearmaxbeginpresetup;
    private Date maxbeginoperation;
    private Time maxbeginoperationtime;
    private Integer weekmaxbeginoperation;
    private Integer yearmaxbeginoperation;
    private Date maxbeginpostsetup;
    private Time maxbeginpostsetuptime;
    private Integer weekmaxbeginpostsetup;
    private Integer yearmaxbeginpostsetup;
    private Date maxendstep;
    private Time maxendsteptime;
    private Integer weekmaxendstep;
    private Integer yearmaxendstep;
    private BigDecimal initialuserprimaryquantity;
    private BigDecimal finaluserprimaryquantity;
    private String userprimaryuomcode;
    private BigDecimal initialbaseprimaryquantity;
    private BigDecimal finalbaseprimaryquantity;
    private String baseprimaryuomcode;
    private BigDecimal initialusersecondaryquantity;
    private BigDecimal finalusersecondaryquantity;
    private String usersecondaryuomcode;
    private BigDecimal initialbasesecondaryquantity;
    private BigDecimal finalbasesecondaryquantity;
    private String basesecondaryuomcode;
    private BigDecimal initialuserpackagingquantity;
    private BigDecimal finaluserpackagingquantity;
    private String userpackaginguomcode;
    private Timestamp creationdatetime;
    private String creationuser;
    private Timestamp lastupdatedatetime;
    private String lastupdateuser;
    private Short manualstepfromdemand;
    private Short manualmodifiedstep;
    private Integer groupstepnumber;
    private Long absuniqueid;

    public ProductiondemandstepId getId() {
        return id;
    }

    public void setId(ProductiondemandstepId id) {
        this.id = id;
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
    @Column(name = "ROUTINGNUMBERID", nullable = true, precision = 0)
    public Integer getRoutingnumberid() {
        return routingnumberid;
    }

    public void setRoutingnumberid(Integer routingnumberid) {
        this.routingnumberid = routingnumberid;
    }

    @Basic
    @Column(name = "STDROUTINGSTEPNUMBERID", nullable = true, precision = 0)
    public Integer getStdroutingstepnumberid() {
        return stdroutingstepnumberid;
    }

    public void setStdroutingstepnumberid(Integer stdroutingstepnumberid) {
        this.stdroutingstepnumberid = stdroutingstepnumberid;
    }

    @Basic
    @Column(name = "STDROUTINGSTEPSEQUENCE", nullable = true, precision = 0)
    public Integer getStdroutingstepsequence() {
        return stdroutingstepsequence;
    }

    public void setStdroutingstepsequence(Integer stdroutingstepsequence) {
        this.stdroutingstepsequence = stdroutingstepsequence;
    }

    @Basic
    @Column(name = "STDROUTINGSTEPSUBSEQUENCE", nullable = true, precision = 0)
    public Integer getStdroutingstepsubsequence() {
        return stdroutingstepsubsequence;
    }

    public void setStdroutingstepsubsequence(Integer stdroutingstepsubsequence) {
        this.stdroutingstepsubsequence = stdroutingstepsubsequence;
    }

    @Basic
    @Column(name = "PLANNEDWORKCENTERCODE", nullable = true, length = 8)
    public String getPlannedworkcentercode() {
        return plannedworkcentercode;
    }

    public void setPlannedworkcentercode(String plannedworkcentercode) {
        this.plannedworkcentercode = plannedworkcentercode;
    }

    @Basic
    @Column(name = "PLANNEDOPERATIONCODE", nullable = true, length = 8)
    public String getPlannedoperationcode() {
        return plannedoperationcode;
    }

    public void setPlannedoperationcode(String plannedoperationcode) {
        this.plannedoperationcode = plannedoperationcode;
    }

    @Basic
    @Column(name = "WORKCENTERCODE", nullable = true, length = 8)
    public String getWorkcentercode() {
        return workcentercode;
    }

    public void setWorkcentercode(String workcentercode) {
        this.workcentercode = workcentercode;
    }

    @Basic
    @Column(name = "OPERATIONCODE", nullable = true, length = 8)
    public String getOperationcode() {
        return operationcode;
    }

    public void setOperationcode(String operationcode) {
        this.operationcode = operationcode;
    }

    @Basic
    @Column(name = "DYELOTHANDLED", nullable = false)
    public Short getDyelothandled() {
        return dyelothandled;
    }

    public void setDyelothandled(Short dyelothandled) {
        this.dyelothandled = dyelothandled;
    }

    @Basic
    @Column(name = "WORKCENTERANDOPERATTRIBUTESCOD", nullable = true, length = 20)
    public String getWorkcenterandoperattributescod() {
        return workcenterandoperattributescod;
    }

    public void setWorkcenterandoperattributescod(String workcenterandoperattributescod) {
        this.workcenterandoperattributescod = workcenterandoperattributescod;
    }

    @Basic
    @Column(name = "PARTIALSTEP", nullable = false)
    public Short getPartialstep() {
        return partialstep;
    }

    public void setPartialstep(Short partialstep) {
        this.partialstep = partialstep;
    }

    @Basic
    @Column(name = "PROGRESSSTATUS", nullable = true, length = 2)
    public String getProgressstatus() {
        return progressstatus;
    }

    public void setProgressstatus(String progressstatus) {
        this.progressstatus = progressstatus;
    }

    @Basic
    @Column(name = "STEPTYPE", nullable = false, length = 2)
    public String getSteptype() {
        return steptype;
    }

    public void setSteptype(String steptype) {
        this.steptype = steptype;
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
    @Column(name = "MAXNUMBEROFRESOURCESALLOWED", nullable = false, precision = 0)
    public Integer getMaxnumberofresourcesallowed() {
        return maxnumberofresourcesallowed;
    }

    public void setMaxnumberofresourcesallowed(Integer maxnumberofresourcesallowed) {
        this.maxnumberofresourcesallowed = maxnumberofresourcesallowed;
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
    @Column(name = "LOCWIPISSUEWHSZONEPHYWHSCMYCOD", nullable = true, length = 3)
    public String getLocwipissuewhszonephywhscmycod() {
        return locwipissuewhszonephywhscmycod;
    }

    public void setLocwipissuewhszonephywhscmycod(String locwipissuewhszonephywhscmycod) {
        this.locwipissuewhszonephywhscmycod = locwipissuewhszonephywhscmycod;
    }

    @Basic
    @Column(name = "LOCWIPISSUEWHSZONEPHYWHSCODE", nullable = true, length = 8)
    public String getLocwipissuewhszonephywhscode() {
        return locwipissuewhszonephywhscode;
    }

    public void setLocwipissuewhszonephywhscode(String locwipissuewhszonephywhscode) {
        this.locwipissuewhszonephywhscode = locwipissuewhszonephywhscode;
    }

    @Basic
    @Column(name = "LOCWIPISSUEWAREHOUSEZONECODE", nullable = true, length = 3)
    public String getLocwipissuewarehousezonecode() {
        return locwipissuewarehousezonecode;
    }

    public void setLocwipissuewarehousezonecode(String locwipissuewarehousezonecode) {
        this.locwipissuewarehousezonecode = locwipissuewarehousezonecode;
    }

    @Basic
    @Column(name = "LOCATIONWIPISSUECODE", nullable = true, length = 10)
    public String getLocationwipissuecode() {
        return locationwipissuecode;
    }

    public void setLocationwipissuecode(String locationwipissuecode) {
        this.locationwipissuecode = locationwipissuecode;
    }

    @Basic
    @Column(name = "LOCWIPENTRYWHSZONEPHYWHSCMYCOD", nullable = true, length = 3)
    public String getLocwipentrywhszonephywhscmycod() {
        return locwipentrywhszonephywhscmycod;
    }

    public void setLocwipentrywhszonephywhscmycod(String locwipentrywhszonephywhscmycod) {
        this.locwipentrywhszonephywhscmycod = locwipentrywhszonephywhscmycod;
    }

    @Basic
    @Column(name = "LOCWIPENTRYWHSZONEPHYWHSCODE", nullable = true, length = 8)
    public String getLocwipentrywhszonephywhscode() {
        return locwipentrywhszonephywhscode;
    }

    public void setLocwipentrywhszonephywhscode(String locwipentrywhszonephywhscode) {
        this.locwipentrywhszonephywhscode = locwipentrywhszonephywhscode;
    }

    @Basic
    @Column(name = "LOCWIPENTRYWAREHOUSEZONECODE", nullable = true, length = 3)
    public String getLocwipentrywarehousezonecode() {
        return locwipentrywarehousezonecode;
    }

    public void setLocwipentrywarehousezonecode(String locwipentrywarehousezonecode) {
        this.locwipentrywarehousezonecode = locwipentrywarehousezonecode;
    }

    @Basic
    @Column(name = "LOCATIONWIPENTRYCODE", nullable = true, length = 10)
    public String getLocationwipentrycode() {
        return locationwipentrycode;
    }

    public void setLocationwipentrycode(String locationwipentrycode) {
        this.locationwipentrycode = locationwipentrycode;
    }

    @Basic
    @Column(name = "LONGDESCRIPTION", nullable = false, length = 100)
    public String getLongdescription() {
        return longdescription;
    }

    public void setLongdescription(String longdescription) {
        this.longdescription = longdescription;
    }

    @Basic
    @Column(name = "SHORTDESCRIPTION", nullable = true, length = 40)
    public String getShortdescription() {
        return shortdescription;
    }

    public void setShortdescription(String shortdescription) {
        this.shortdescription = shortdescription;
    }

    @Basic
    @Column(name = "SEARCHDESCRIPTION", nullable = true, length = 60)
    public String getSearchdescription() {
        return searchdescription;
    }

    public void setSearchdescription(String searchdescription) {
        this.searchdescription = searchdescription;
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
    @Column(name = "STEPNUMBERLINK", nullable = true, precision = 0)
    public Integer getStepnumberlink() {
        return stepnumberlink;
    }

    public void setStepnumberlink(Integer stepnumberlink) {
        this.stepnumberlink = stepnumberlink;
    }

    @Basic
    @Column(name = "INITIALPLANNEDDATETIME", nullable = true)
    public Timestamp getInitialplanneddatetime() {
        return initialplanneddatetime;
    }

    public void setInitialplanneddatetime(Timestamp initialplanneddatetime) {
        this.initialplanneddatetime = initialplanneddatetime;
    }

    @Basic
    @Column(name = "FINALPLANNEDDATETIME", nullable = true)
    public Timestamp getFinalplanneddatetime() {
        return finalplanneddatetime;
    }

    public void setFinalplanneddatetime(Timestamp finalplanneddatetime) {
        this.finalplanneddatetime = finalplanneddatetime;
    }

    @Basic
    @Column(name = "INITIALPLANSCHEDDATETIME", nullable = true)
    public Timestamp getInitialplanscheddatetime() {
        return initialplanscheddatetime;
    }

    public void setInitialplanscheddatetime(Timestamp initialplanscheddatetime) {
        this.initialplanscheddatetime = initialplanscheddatetime;
    }

    @Basic
    @Column(name = "FINALPLANSCHEDDATETIME", nullable = true)
    public Timestamp getFinalplanscheddatetime() {
        return finalplanscheddatetime;
    }

    public void setFinalplanscheddatetime(Timestamp finalplanscheddatetime) {
        this.finalplanscheddatetime = finalplanscheddatetime;
    }

    @Basic
    @Column(name = "INITIALSCHEDULEDDATETIME", nullable = true)
    public Timestamp getInitialscheduleddatetime() {
        return initialscheduleddatetime;
    }

    public void setInitialscheduleddatetime(Timestamp initialscheduleddatetime) {
        this.initialscheduleddatetime = initialscheduleddatetime;
    }

    @Basic
    @Column(name = "SCHEDULEDSTEP", nullable = false)
    public Short getScheduledstep() {
        return scheduledstep;
    }

    public void setScheduledstep(Short scheduledstep) {
        this.scheduledstep = scheduledstep;
    }

    @Basic
    @Column(name = "FINALSCHEDULEDDATETIME", nullable = true)
    public Timestamp getFinalscheduleddatetime() {
        return finalscheduleddatetime;
    }

    public void setFinalscheduleddatetime(Timestamp finalscheduleddatetime) {
        this.finalscheduleddatetime = finalscheduleddatetime;
    }

    @Basic
    @Column(name = "STANDARDSTEPQUANTITY", nullable = true, precision = 5)
    public BigDecimal getStandardstepquantity() {
        return standardstepquantity;
    }

    public void setStandardstepquantity(BigDecimal standardstepquantity) {
        this.standardstepquantity = standardstepquantity;
    }

    @Basic
    @Column(name = "STANDARDSTEPQUANTITYUOMCODE", nullable = true, length = 3)
    public String getStandardstepquantityuomcode() {
        return standardstepquantityuomcode;
    }

    public void setStandardstepquantityuomcode(String standardstepquantityuomcode) {
        this.standardstepquantityuomcode = standardstepquantityuomcode;
    }

    @Basic
    @Column(name = "STEPEFFICIENCYAPPLY", nullable = true, length = 1)
    public String getStepefficiencyapply() {
        return stepefficiencyapply;
    }

    public void setStepefficiencyapply(String stepefficiencyapply) {
        this.stepefficiencyapply = stepefficiencyapply;
    }

    @Basic
    @Column(name = "STEPEFFICIENCY", nullable = false, precision = 2)
    public BigDecimal getStepefficiency() {
        return stepefficiency;
    }

    public void setStepefficiency(BigDecimal stepefficiency) {
        this.stepefficiency = stepefficiency;
    }

    @Basic
    @Column(name = "NROFMACHINE", nullable = false)
    public Integer getNrofmachine() {
        return nrofmachine;
    }

    public void setNrofmachine(Integer nrofmachine) {
        this.nrofmachine = nrofmachine;
    }

    @Basic
    @Column(name = "REPETITIONNUMBER", nullable = false, precision = 6)
    public BigDecimal getRepetitionnumber() {
        return repetitionnumber;
    }

    public void setRepetitionnumber(BigDecimal repetitionnumber) {
        this.repetitionnumber = repetitionnumber;
    }

    @Basic
    @Column(name = "BATHVOLUME", nullable = true, precision = 6)
    public BigDecimal getBathvolume() {
        return bathvolume;
    }

    public void setBathvolume(BigDecimal bathvolume) {
        this.bathvolume = bathvolume;
    }

    @Basic
    @Column(name = "CURRENTSTEPPROGRESS", nullable = true, length = 1)
    public String getCurrentstepprogress() {
        return currentstepprogress;
    }

    public void setCurrentstepprogress(String currentstepprogress) {
        this.currentstepprogress = currentstepprogress;
    }

    @Basic
    @Column(name = "PREVIOUSSTEPPROGRESS", nullable = true, length = 1)
    public String getPreviousstepprogress() {
        return previousstepprogress;
    }

    public void setPreviousstepprogress(String previousstepprogress) {
        this.previousstepprogress = previousstepprogress;
    }

    @Basic
    @Column(name = "OPSTEPGROUPCODE", nullable = true, length = 8)
    public String getOpstepgroupcode() {
        return opstepgroupcode;
    }

    public void setOpstepgroupcode(String opstepgroupcode) {
        this.opstepgroupcode = opstepgroupcode;
    }

    @Basic
    @Column(name = "GENERATEAUTOMATICQATEST", nullable = false)
    public Short getGenerateautomaticqatest() {
        return generateautomaticqatest;
    }

    public void setGenerateautomaticqatest(Short generateautomaticqatest) {
        this.generateautomaticqatest = generateautomaticqatest;
    }

    @Basic
    @Column(name = "OVERLAPPINGMANAGEMENT", nullable = false)
    public Short getOverlappingmanagement() {
        return overlappingmanagement;
    }

    public void setOverlappingmanagement(Short overlappingmanagement) {
        this.overlappingmanagement = overlappingmanagement;
    }

    @Basic
    @Column(name = "OVERLAPPINGQUANTITY", nullable = true, precision = 5)
    public BigDecimal getOverlappingquantity() {
        return overlappingquantity;
    }

    public void setOverlappingquantity(BigDecimal overlappingquantity) {
        this.overlappingquantity = overlappingquantity;
    }

    @Basic
    @Column(name = "OVERLAPPINGUOMCATEGORY", nullable = true, length = 1)
    public String getOverlappinguomcategory() {
        return overlappinguomcategory;
    }

    public void setOverlappinguomcategory(String overlappinguomcategory) {
        this.overlappinguomcategory = overlappinguomcategory;
    }

    @Basic
    @Column(name = "LOSSINCREASETYPE1CODE", nullable = true, length = 3)
    public String getLossincreasetype1Code() {
        return lossincreasetype1Code;
    }

    public void setLossincreasetype1Code(String lossincreasetype1Code) {
        this.lossincreasetype1Code = lossincreasetype1Code;
    }

    @Basic
    @Column(name = "LOSSINCREASE1", nullable = true, precision = 5)
    public BigDecimal getLossincrease1() {
        return lossincrease1;
    }

    public void setLossincrease1(BigDecimal lossincrease1) {
        this.lossincrease1 = lossincrease1;
    }

    @Basic
    @Column(name = "LOSSINCREASEREFUOM1CODE", nullable = true, length = 3)
    public String getLossincreaserefuom1Code() {
        return lossincreaserefuom1Code;
    }

    public void setLossincreaserefuom1Code(String lossincreaserefuom1Code) {
        this.lossincreaserefuom1Code = lossincreaserefuom1Code;
    }

    @Basic
    @Column(name = "LOSSINCREASETYPE2CODE", nullable = true, length = 3)
    public String getLossincreasetype2Code() {
        return lossincreasetype2Code;
    }

    public void setLossincreasetype2Code(String lossincreasetype2Code) {
        this.lossincreasetype2Code = lossincreasetype2Code;
    }

    @Basic
    @Column(name = "LOSSINCREASE2", nullable = true, precision = 5)
    public BigDecimal getLossincrease2() {
        return lossincrease2;
    }

    public void setLossincrease2(BigDecimal lossincrease2) {
        this.lossincrease2 = lossincrease2;
    }

    @Basic
    @Column(name = "LOSSINCREASEREFUOM2CODE", nullable = true, length = 3)
    public String getLossincreaserefuom2Code() {
        return lossincreaserefuom2Code;
    }

    public void setLossincreaserefuom2Code(String lossincreaserefuom2Code) {
        this.lossincreaserefuom2Code = lossincreaserefuom2Code;
    }

    @Basic
    @Column(name = "LOSSINCREASETYPE3CODE", nullable = true, length = 3)
    public String getLossincreasetype3Code() {
        return lossincreasetype3Code;
    }

    public void setLossincreasetype3Code(String lossincreasetype3Code) {
        this.lossincreasetype3Code = lossincreasetype3Code;
    }

    @Basic
    @Column(name = "LOSSINCREASE3", nullable = true, precision = 5)
    public BigDecimal getLossincrease3() {
        return lossincrease3;
    }

    public void setLossincrease3(BigDecimal lossincrease3) {
        this.lossincrease3 = lossincrease3;
    }

    @Basic
    @Column(name = "LOSSINCREASEREFUOM3CODE", nullable = true, length = 3)
    public String getLossincreaserefuom3Code() {
        return lossincreaserefuom3Code;
    }

    public void setLossincreaserefuom3Code(String lossincreaserefuom3Code) {
        this.lossincreaserefuom3Code = lossincreaserefuom3Code;
    }

    @Basic
    @Column(name = "LOSSINCREASETYPE4CODE", nullable = true, length = 3)
    public String getLossincreasetype4Code() {
        return lossincreasetype4Code;
    }

    public void setLossincreasetype4Code(String lossincreasetype4Code) {
        this.lossincreasetype4Code = lossincreasetype4Code;
    }

    @Basic
    @Column(name = "LOSSINCREASE4", nullable = true, precision = 5)
    public BigDecimal getLossincrease4() {
        return lossincrease4;
    }

    public void setLossincrease4(BigDecimal lossincrease4) {
        this.lossincrease4 = lossincrease4;
    }

    @Basic
    @Column(name = "LOSSINCREASEREFUOM4CODE", nullable = true, length = 3)
    public String getLossincreaserefuom4Code() {
        return lossincreaserefuom4Code;
    }

    public void setLossincreaserefuom4Code(String lossincreaserefuom4Code) {
        this.lossincreaserefuom4Code = lossincreaserefuom4Code;
    }

    @Basic
    @Column(name = "LOSSINCREASETYPE5CODE", nullable = true, length = 3)
    public String getLossincreasetype5Code() {
        return lossincreasetype5Code;
    }

    public void setLossincreasetype5Code(String lossincreasetype5Code) {
        this.lossincreasetype5Code = lossincreasetype5Code;
    }

    @Basic
    @Column(name = "LOSSINCREASE5", nullable = true, precision = 5)
    public BigDecimal getLossincrease5() {
        return lossincrease5;
    }

    public void setLossincrease5(BigDecimal lossincrease5) {
        this.lossincrease5 = lossincrease5;
    }

    @Basic
    @Column(name = "LOSSINCREASEREFUOM5CODE", nullable = true, length = 3)
    public String getLossincreaserefuom5Code() {
        return lossincreaserefuom5Code;
    }

    public void setLossincreaserefuom5Code(String lossincreaserefuom5Code) {
        this.lossincreaserefuom5Code = lossincreaserefuom5Code;
    }

    @Basic
    @Column(name = "LOSSINCREASETYPE6CODE", nullable = true, length = 3)
    public String getLossincreasetype6Code() {
        return lossincreasetype6Code;
    }

    public void setLossincreasetype6Code(String lossincreasetype6Code) {
        this.lossincreasetype6Code = lossincreasetype6Code;
    }

    @Basic
    @Column(name = "LOSSINCREASE6", nullable = true, precision = 5)
    public BigDecimal getLossincrease6() {
        return lossincrease6;
    }

    public void setLossincrease6(BigDecimal lossincrease6) {
        this.lossincrease6 = lossincrease6;
    }

    @Basic
    @Column(name = "LOSSINCREASEREFUOM6CODE", nullable = true, length = 3)
    public String getLossincreaserefuom6Code() {
        return lossincreaserefuom6Code;
    }

    public void setLossincreaserefuom6Code(String lossincreaserefuom6Code) {
        this.lossincreaserefuom6Code = lossincreaserefuom6Code;
    }

    @Basic
    @Column(name = "LOSSINCREASETYPE7CODE", nullable = true, length = 3)
    public String getLossincreasetype7Code() {
        return lossincreasetype7Code;
    }

    public void setLossincreasetype7Code(String lossincreasetype7Code) {
        this.lossincreasetype7Code = lossincreasetype7Code;
    }

    @Basic
    @Column(name = "LOSSINCREASE7", nullable = true, precision = 5)
    public BigDecimal getLossincrease7() {
        return lossincrease7;
    }

    public void setLossincrease7(BigDecimal lossincrease7) {
        this.lossincrease7 = lossincrease7;
    }

    @Basic
    @Column(name = "LOSSINCREASEREFUOM7CODE", nullable = true, length = 3)
    public String getLossincreaserefuom7Code() {
        return lossincreaserefuom7Code;
    }

    public void setLossincreaserefuom7Code(String lossincreaserefuom7Code) {
        this.lossincreaserefuom7Code = lossincreaserefuom7Code;
    }

    @Basic
    @Column(name = "LOSSINCREASETYPE8CODE", nullable = true, length = 3)
    public String getLossincreasetype8Code() {
        return lossincreasetype8Code;
    }

    public void setLossincreasetype8Code(String lossincreasetype8Code) {
        this.lossincreasetype8Code = lossincreasetype8Code;
    }

    @Basic
    @Column(name = "LOSSINCREASE8", nullable = true, precision = 5)
    public BigDecimal getLossincrease8() {
        return lossincrease8;
    }

    public void setLossincrease8(BigDecimal lossincrease8) {
        this.lossincrease8 = lossincrease8;
    }

    @Basic
    @Column(name = "LOSSINCREASEREFUOM8CODE", nullable = true, length = 3)
    public String getLossincreaserefuom8Code() {
        return lossincreaserefuom8Code;
    }

    public void setLossincreaserefuom8Code(String lossincreaserefuom8Code) {
        this.lossincreaserefuom8Code = lossincreaserefuom8Code;
    }

    @Basic
    @Column(name = "LOSSINCREASETYPEPOLICY", nullable = true, length = 3)
    public String getLossincreasetypepolicy() {
        return lossincreasetypepolicy;
    }

    public void setLossincreasetypepolicy(String lossincreasetypepolicy) {
        this.lossincreasetypepolicy = lossincreasetypepolicy;
    }

    @Basic
    @Column(name = "PARALLELPDNUMBER", nullable = false)
    public Integer getParallelpdnumber() {
        return parallelpdnumber;
    }

    public void setParallelpdnumber(Integer parallelpdnumber) {
        this.parallelpdnumber = parallelpdnumber;
    }

    @Basic
    @Column(name = "PLANNINGLEADTIME", nullable = true, precision = 5)
    public BigDecimal getPlanningleadtime() {
        return planningleadtime;
    }

    public void setPlanningleadtime(BigDecimal planningleadtime) {
        this.planningleadtime = planningleadtime;
    }

    @Basic
    @Column(name = "TIMETYPE1CODE", nullable = true, length = 3)
    public String getTimetype1Code() {
        return timetype1Code;
    }

    public void setTimetype1Code(String timetype1Code) {
        this.timetype1Code = timetype1Code;
    }

    @Basic
    @Column(name = "TIME1", nullable = true, precision = 5)
    public BigDecimal getTime1() {
        return time1;
    }

    public void setTime1(BigDecimal time1) {
        this.time1 = time1;
    }

    @Basic
    @Column(name = "TIMEUNIT1", nullable = true, length = 2)
    public String getTimeunit1() {
        return timeunit1;
    }

    public void setTimeunit1(String timeunit1) {
        this.timeunit1 = timeunit1;
    }

    @Basic
    @Column(name = "TIMEREFQTY1", nullable = true, precision = 5)
    public BigDecimal getTimerefqty1() {
        return timerefqty1;
    }

    public void setTimerefqty1(BigDecimal timerefqty1) {
        this.timerefqty1 = timerefqty1;
    }

    @Basic
    @Column(name = "TIMEREFUOM1CODE", nullable = true, length = 3)
    public String getTimerefuom1Code() {
        return timerefuom1Code;
    }

    public void setTimerefuom1Code(String timerefuom1Code) {
        this.timerefuom1Code = timerefuom1Code;
    }

    @Basic
    @Column(name = "TIMETYPE2CODE", nullable = true, length = 3)
    public String getTimetype2Code() {
        return timetype2Code;
    }

    public void setTimetype2Code(String timetype2Code) {
        this.timetype2Code = timetype2Code;
    }

    @Basic
    @Column(name = "TIME2", nullable = true, precision = 5)
    public BigDecimal getTime2() {
        return time2;
    }

    public void setTime2(BigDecimal time2) {
        this.time2 = time2;
    }

    @Basic
    @Column(name = "TIMEUNIT2", nullable = true, length = 2)
    public String getTimeunit2() {
        return timeunit2;
    }

    public void setTimeunit2(String timeunit2) {
        this.timeunit2 = timeunit2;
    }

    @Basic
    @Column(name = "TIMEREFQTY2", nullable = true, precision = 5)
    public BigDecimal getTimerefqty2() {
        return timerefqty2;
    }

    public void setTimerefqty2(BigDecimal timerefqty2) {
        this.timerefqty2 = timerefqty2;
    }

    @Basic
    @Column(name = "TIMEREFUOM2CODE", nullable = true, length = 3)
    public String getTimerefuom2Code() {
        return timerefuom2Code;
    }

    public void setTimerefuom2Code(String timerefuom2Code) {
        this.timerefuom2Code = timerefuom2Code;
    }

    @Basic
    @Column(name = "TIMETYPE3CODE", nullable = true, length = 3)
    public String getTimetype3Code() {
        return timetype3Code;
    }

    public void setTimetype3Code(String timetype3Code) {
        this.timetype3Code = timetype3Code;
    }

    @Basic
    @Column(name = "TIME3", nullable = true, precision = 5)
    public BigDecimal getTime3() {
        return time3;
    }

    public void setTime3(BigDecimal time3) {
        this.time3 = time3;
    }

    @Basic
    @Column(name = "TIMEUNIT3", nullable = true, length = 2)
    public String getTimeunit3() {
        return timeunit3;
    }

    public void setTimeunit3(String timeunit3) {
        this.timeunit3 = timeunit3;
    }

    @Basic
    @Column(name = "TIMEREFQTY3", nullable = true, precision = 5)
    public BigDecimal getTimerefqty3() {
        return timerefqty3;
    }

    public void setTimerefqty3(BigDecimal timerefqty3) {
        this.timerefqty3 = timerefqty3;
    }

    @Basic
    @Column(name = "TIMEREFUOM3CODE", nullable = true, length = 3)
    public String getTimerefuom3Code() {
        return timerefuom3Code;
    }

    public void setTimerefuom3Code(String timerefuom3Code) {
        this.timerefuom3Code = timerefuom3Code;
    }

    @Basic
    @Column(name = "TIMETYPE4CODE", nullable = true, length = 3)
    public String getTimetype4Code() {
        return timetype4Code;
    }

    public void setTimetype4Code(String timetype4Code) {
        this.timetype4Code = timetype4Code;
    }

    @Basic
    @Column(name = "TIME4", nullable = true, precision = 5)
    public BigDecimal getTime4() {
        return time4;
    }

    public void setTime4(BigDecimal time4) {
        this.time4 = time4;
    }

    @Basic
    @Column(name = "TIMEUNIT4", nullable = true, length = 2)
    public String getTimeunit4() {
        return timeunit4;
    }

    public void setTimeunit4(String timeunit4) {
        this.timeunit4 = timeunit4;
    }

    @Basic
    @Column(name = "TIMEREFQTY4", nullable = true, precision = 5)
    public BigDecimal getTimerefqty4() {
        return timerefqty4;
    }

    public void setTimerefqty4(BigDecimal timerefqty4) {
        this.timerefqty4 = timerefqty4;
    }

    @Basic
    @Column(name = "TIMEREFUOM4CODE", nullable = true, length = 3)
    public String getTimerefuom4Code() {
        return timerefuom4Code;
    }

    public void setTimerefuom4Code(String timerefuom4Code) {
        this.timerefuom4Code = timerefuom4Code;
    }

    @Basic
    @Column(name = "TIMETYPE5CODE", nullable = true, length = 3)
    public String getTimetype5Code() {
        return timetype5Code;
    }

    public void setTimetype5Code(String timetype5Code) {
        this.timetype5Code = timetype5Code;
    }

    @Basic
    @Column(name = "TIME5", nullable = true, precision = 5)
    public BigDecimal getTime5() {
        return time5;
    }

    public void setTime5(BigDecimal time5) {
        this.time5 = time5;
    }

    @Basic
    @Column(name = "TIMEUNIT5", nullable = true, length = 2)
    public String getTimeunit5() {
        return timeunit5;
    }

    public void setTimeunit5(String timeunit5) {
        this.timeunit5 = timeunit5;
    }

    @Basic
    @Column(name = "TIMEREFQTY5", nullable = true, precision = 5)
    public BigDecimal getTimerefqty5() {
        return timerefqty5;
    }

    public void setTimerefqty5(BigDecimal timerefqty5) {
        this.timerefqty5 = timerefqty5;
    }

    @Basic
    @Column(name = "TIMEREFUOM5CODE", nullable = true, length = 3)
    public String getTimerefuom5Code() {
        return timerefuom5Code;
    }

    public void setTimerefuom5Code(String timerefuom5Code) {
        this.timerefuom5Code = timerefuom5Code;
    }

    @Basic
    @Column(name = "CALCULATEDTIME1", nullable = true, precision = 5)
    public BigDecimal getCalculatedtime1() {
        return calculatedtime1;
    }

    public void setCalculatedtime1(BigDecimal calculatedtime1) {
        this.calculatedtime1 = calculatedtime1;
    }

    @Basic
    @Column(name = "CALCULATEDTIME2", nullable = true, precision = 5)
    public BigDecimal getCalculatedtime2() {
        return calculatedtime2;
    }

    public void setCalculatedtime2(BigDecimal calculatedtime2) {
        this.calculatedtime2 = calculatedtime2;
    }

    @Basic
    @Column(name = "CALCULATEDTIME3", nullable = true, precision = 5)
    public BigDecimal getCalculatedtime3() {
        return calculatedtime3;
    }

    public void setCalculatedtime3(BigDecimal calculatedtime3) {
        this.calculatedtime3 = calculatedtime3;
    }

    @Basic
    @Column(name = "CALCULATEDTIME4", nullable = true, precision = 5)
    public BigDecimal getCalculatedtime4() {
        return calculatedtime4;
    }

    public void setCalculatedtime4(BigDecimal calculatedtime4) {
        this.calculatedtime4 = calculatedtime4;
    }

    @Basic
    @Column(name = "MINBEGINQUEUE", nullable = true)
    public Date getMinbeginqueue() {
        return minbeginqueue;
    }

    public void setMinbeginqueue(Date minbeginqueue) {
        this.minbeginqueue = minbeginqueue;
    }

    @Basic
    @Column(name = "MINBEGINQUEUETIME", nullable = true)
    public Time getMinbeginqueuetime() {
        return minbeginqueuetime;
    }

    public void setMinbeginqueuetime(Time minbeginqueuetime) {
        this.minbeginqueuetime = minbeginqueuetime;
    }

    @Basic
    @Column(name = "WEEKMINBEGINQUEUE", nullable = false)
    public Integer getWeekminbeginqueue() {
        return weekminbeginqueue;
    }

    public void setWeekminbeginqueue(Integer weekminbeginqueue) {
        this.weekminbeginqueue = weekminbeginqueue;
    }

    @Basic
    @Column(name = "YEARMINBEGINQUEUE", nullable = false)
    public Integer getYearminbeginqueue() {
        return yearminbeginqueue;
    }

    public void setYearminbeginqueue(Integer yearminbeginqueue) {
        this.yearminbeginqueue = yearminbeginqueue;
    }

    @Basic
    @Column(name = "MINBEGINPRESETUP", nullable = true)
    public Date getMinbeginpresetup() {
        return minbeginpresetup;
    }

    public void setMinbeginpresetup(Date minbeginpresetup) {
        this.minbeginpresetup = minbeginpresetup;
    }

    @Basic
    @Column(name = "MINBEGINPRESETUPTIME", nullable = true)
    public Time getMinbeginpresetuptime() {
        return minbeginpresetuptime;
    }

    public void setMinbeginpresetuptime(Time minbeginpresetuptime) {
        this.minbeginpresetuptime = minbeginpresetuptime;
    }

    @Basic
    @Column(name = "WEEKMINBEGINPRESETUP", nullable = false)
    public Integer getWeekminbeginpresetup() {
        return weekminbeginpresetup;
    }

    public void setWeekminbeginpresetup(Integer weekminbeginpresetup) {
        this.weekminbeginpresetup = weekminbeginpresetup;
    }

    @Basic
    @Column(name = "YEARMINBEGINPRESETUP", nullable = false)
    public Integer getYearminbeginpresetup() {
        return yearminbeginpresetup;
    }

    public void setYearminbeginpresetup(Integer yearminbeginpresetup) {
        this.yearminbeginpresetup = yearminbeginpresetup;
    }

    @Basic
    @Column(name = "MINBEGINOPERATION", nullable = true)
    public Date getMinbeginoperation() {
        return minbeginoperation;
    }

    public void setMinbeginoperation(Date minbeginoperation) {
        this.minbeginoperation = minbeginoperation;
    }

    @Basic
    @Column(name = "MINBEGINOPERATIONTIME", nullable = true)
    public Time getMinbeginoperationtime() {
        return minbeginoperationtime;
    }

    public void setMinbeginoperationtime(Time minbeginoperationtime) {
        this.minbeginoperationtime = minbeginoperationtime;
    }

    @Basic
    @Column(name = "WEEKMINBEGINOPERATION", nullable = false)
    public Integer getWeekminbeginoperation() {
        return weekminbeginoperation;
    }

    public void setWeekminbeginoperation(Integer weekminbeginoperation) {
        this.weekminbeginoperation = weekminbeginoperation;
    }

    @Basic
    @Column(name = "YEARMINBEGINOPERATION", nullable = false)
    public Integer getYearminbeginoperation() {
        return yearminbeginoperation;
    }

    public void setYearminbeginoperation(Integer yearminbeginoperation) {
        this.yearminbeginoperation = yearminbeginoperation;
    }

    @Basic
    @Column(name = "MINBEGINPOSTSETUP", nullable = true)
    public Date getMinbeginpostsetup() {
        return minbeginpostsetup;
    }

    public void setMinbeginpostsetup(Date minbeginpostsetup) {
        this.minbeginpostsetup = minbeginpostsetup;
    }

    @Basic
    @Column(name = "MINBEGINPOSTSETUPTIME", nullable = true)
    public Time getMinbeginpostsetuptime() {
        return minbeginpostsetuptime;
    }

    public void setMinbeginpostsetuptime(Time minbeginpostsetuptime) {
        this.minbeginpostsetuptime = minbeginpostsetuptime;
    }

    @Basic
    @Column(name = "WEEKMINBEGINPOSTSETUP", nullable = false)
    public Integer getWeekminbeginpostsetup() {
        return weekminbeginpostsetup;
    }

    public void setWeekminbeginpostsetup(Integer weekminbeginpostsetup) {
        this.weekminbeginpostsetup = weekminbeginpostsetup;
    }

    @Basic
    @Column(name = "YEARMINBEGINPOSTSETUP", nullable = false)
    public Integer getYearminbeginpostsetup() {
        return yearminbeginpostsetup;
    }

    public void setYearminbeginpostsetup(Integer yearminbeginpostsetup) {
        this.yearminbeginpostsetup = yearminbeginpostsetup;
    }

    @Basic
    @Column(name = "MINENDSTEP", nullable = true)
    public Date getMinendstep() {
        return minendstep;
    }

    public void setMinendstep(Date minendstep) {
        this.minendstep = minendstep;
    }

    @Basic
    @Column(name = "MINENDSTEPTIME", nullable = true)
    public Time getMinendsteptime() {
        return minendsteptime;
    }

    public void setMinendsteptime(Time minendsteptime) {
        this.minendsteptime = minendsteptime;
    }

    @Basic
    @Column(name = "WEEKMINENDSTEP", nullable = false)
    public Integer getWeekminendstep() {
        return weekminendstep;
    }

    public void setWeekminendstep(Integer weekminendstep) {
        this.weekminendstep = weekminendstep;
    }

    @Basic
    @Column(name = "YEARMINENDSTEP", nullable = false)
    public Integer getYearminendstep() {
        return yearminendstep;
    }

    public void setYearminendstep(Integer yearminendstep) {
        this.yearminendstep = yearminendstep;
    }

    @Basic
    @Column(name = "STDBEGINQUEUE", nullable = true)
    public Date getStdbeginqueue() {
        return stdbeginqueue;
    }

    public void setStdbeginqueue(Date stdbeginqueue) {
        this.stdbeginqueue = stdbeginqueue;
    }

    @Basic
    @Column(name = "STDBEGINQUEUETIME", nullable = true)
    public Time getStdbeginqueuetime() {
        return stdbeginqueuetime;
    }

    public void setStdbeginqueuetime(Time stdbeginqueuetime) {
        this.stdbeginqueuetime = stdbeginqueuetime;
    }

    @Basic
    @Column(name = "WEEKSTDBEGINQUEUE", nullable = false)
    public Integer getWeekstdbeginqueue() {
        return weekstdbeginqueue;
    }

    public void setWeekstdbeginqueue(Integer weekstdbeginqueue) {
        this.weekstdbeginqueue = weekstdbeginqueue;
    }

    @Basic
    @Column(name = "YEARSTDBEGINQUEUE", nullable = false)
    public Integer getYearstdbeginqueue() {
        return yearstdbeginqueue;
    }

    public void setYearstdbeginqueue(Integer yearstdbeginqueue) {
        this.yearstdbeginqueue = yearstdbeginqueue;
    }

    @Basic
    @Column(name = "STDBEGINPRESETUP", nullable = true)
    public Date getStdbeginpresetup() {
        return stdbeginpresetup;
    }

    public void setStdbeginpresetup(Date stdbeginpresetup) {
        this.stdbeginpresetup = stdbeginpresetup;
    }

    @Basic
    @Column(name = "STDBEGINPRESETUPTIME", nullable = true)
    public Time getStdbeginpresetuptime() {
        return stdbeginpresetuptime;
    }

    public void setStdbeginpresetuptime(Time stdbeginpresetuptime) {
        this.stdbeginpresetuptime = stdbeginpresetuptime;
    }

    @Basic
    @Column(name = "WEEKSTDBEGINPRESETUP", nullable = false)
    public Integer getWeekstdbeginpresetup() {
        return weekstdbeginpresetup;
    }

    public void setWeekstdbeginpresetup(Integer weekstdbeginpresetup) {
        this.weekstdbeginpresetup = weekstdbeginpresetup;
    }

    @Basic
    @Column(name = "YEARSTDBEGINPRESETUP", nullable = false)
    public Integer getYearstdbeginpresetup() {
        return yearstdbeginpresetup;
    }

    public void setYearstdbeginpresetup(Integer yearstdbeginpresetup) {
        this.yearstdbeginpresetup = yearstdbeginpresetup;
    }

    @Basic
    @Column(name = "STDBEGINOPERATION", nullable = true)
    public Date getStdbeginoperation() {
        return stdbeginoperation;
    }

    public void setStdbeginoperation(Date stdbeginoperation) {
        this.stdbeginoperation = stdbeginoperation;
    }

    @Basic
    @Column(name = "STDBEGINOPERATIONTIME", nullable = true)
    public Time getStdbeginoperationtime() {
        return stdbeginoperationtime;
    }

    public void setStdbeginoperationtime(Time stdbeginoperationtime) {
        this.stdbeginoperationtime = stdbeginoperationtime;
    }

    @Basic
    @Column(name = "WEEKSTDBEGINOPERATION", nullable = false)
    public Integer getWeekstdbeginoperation() {
        return weekstdbeginoperation;
    }

    public void setWeekstdbeginoperation(Integer weekstdbeginoperation) {
        this.weekstdbeginoperation = weekstdbeginoperation;
    }

    @Basic
    @Column(name = "YEARSTDBEGINOPERATION", nullable = false)
    public Integer getYearstdbeginoperation() {
        return yearstdbeginoperation;
    }

    public void setYearstdbeginoperation(Integer yearstdbeginoperation) {
        this.yearstdbeginoperation = yearstdbeginoperation;
    }

    @Basic
    @Column(name = "STDBEGINPOSTSETUP", nullable = true)
    public Date getStdbeginpostsetup() {
        return stdbeginpostsetup;
    }

    public void setStdbeginpostsetup(Date stdbeginpostsetup) {
        this.stdbeginpostsetup = stdbeginpostsetup;
    }

    @Basic
    @Column(name = "STDBEGINPOSTSETUPTIME", nullable = true)
    public Time getStdbeginpostsetuptime() {
        return stdbeginpostsetuptime;
    }

    public void setStdbeginpostsetuptime(Time stdbeginpostsetuptime) {
        this.stdbeginpostsetuptime = stdbeginpostsetuptime;
    }

    @Basic
    @Column(name = "WEEKSTDBEGINPOSTSETUP", nullable = false)
    public Integer getWeekstdbeginpostsetup() {
        return weekstdbeginpostsetup;
    }

    public void setWeekstdbeginpostsetup(Integer weekstdbeginpostsetup) {
        this.weekstdbeginpostsetup = weekstdbeginpostsetup;
    }

    @Basic
    @Column(name = "YEARSTDBEGINPOSTSETUP", nullable = false)
    public Integer getYearstdbeginpostsetup() {
        return yearstdbeginpostsetup;
    }

    public void setYearstdbeginpostsetup(Integer yearstdbeginpostsetup) {
        this.yearstdbeginpostsetup = yearstdbeginpostsetup;
    }

    @Basic
    @Column(name = "STDENDSTEP", nullable = true)
    public Date getStdendstep() {
        return stdendstep;
    }

    public void setStdendstep(Date stdendstep) {
        this.stdendstep = stdendstep;
    }

    @Basic
    @Column(name = "STDENDSTEPTIME", nullable = true)
    public Time getStdendsteptime() {
        return stdendsteptime;
    }

    public void setStdendsteptime(Time stdendsteptime) {
        this.stdendsteptime = stdendsteptime;
    }

    @Basic
    @Column(name = "WEEKSTDENDSTEP", nullable = false)
    public Integer getWeekstdendstep() {
        return weekstdendstep;
    }

    public void setWeekstdendstep(Integer weekstdendstep) {
        this.weekstdendstep = weekstdendstep;
    }

    @Basic
    @Column(name = "YEARSTDENDSTEP", nullable = false)
    public Integer getYearstdendstep() {
        return yearstdendstep;
    }

    public void setYearstdendstep(Integer yearstdendstep) {
        this.yearstdendstep = yearstdendstep;
    }

    @Basic
    @Column(name = "MAXBEGINQUEUE", nullable = true)
    public Date getMaxbeginqueue() {
        return maxbeginqueue;
    }

    public void setMaxbeginqueue(Date maxbeginqueue) {
        this.maxbeginqueue = maxbeginqueue;
    }

    @Basic
    @Column(name = "MAXBEGINQUEUETIME", nullable = true)
    public Time getMaxbeginqueuetime() {
        return maxbeginqueuetime;
    }

    public void setMaxbeginqueuetime(Time maxbeginqueuetime) {
        this.maxbeginqueuetime = maxbeginqueuetime;
    }

    @Basic
    @Column(name = "WEEKMAXBEGINQUEUE", nullable = false)
    public Integer getWeekmaxbeginqueue() {
        return weekmaxbeginqueue;
    }

    public void setWeekmaxbeginqueue(Integer weekmaxbeginqueue) {
        this.weekmaxbeginqueue = weekmaxbeginqueue;
    }

    @Basic
    @Column(name = "YEARMAXBEGINQUEUE", nullable = false)
    public Integer getYearmaxbeginqueue() {
        return yearmaxbeginqueue;
    }

    public void setYearmaxbeginqueue(Integer yearmaxbeginqueue) {
        this.yearmaxbeginqueue = yearmaxbeginqueue;
    }

    @Basic
    @Column(name = "MAXBEGINPRESETUP", nullable = true)
    public Date getMaxbeginpresetup() {
        return maxbeginpresetup;
    }

    public void setMaxbeginpresetup(Date maxbeginpresetup) {
        this.maxbeginpresetup = maxbeginpresetup;
    }

    @Basic
    @Column(name = "MAXBEGINPRESETUPTIME", nullable = true)
    public Time getMaxbeginpresetuptime() {
        return maxbeginpresetuptime;
    }

    public void setMaxbeginpresetuptime(Time maxbeginpresetuptime) {
        this.maxbeginpresetuptime = maxbeginpresetuptime;
    }

    @Basic
    @Column(name = "WEEKMAXBEGINPRESETUP", nullable = false)
    public Integer getWeekmaxbeginpresetup() {
        return weekmaxbeginpresetup;
    }

    public void setWeekmaxbeginpresetup(Integer weekmaxbeginpresetup) {
        this.weekmaxbeginpresetup = weekmaxbeginpresetup;
    }

    @Basic
    @Column(name = "YEARMAXBEGINPRESETUP", nullable = false)
    public Integer getYearmaxbeginpresetup() {
        return yearmaxbeginpresetup;
    }

    public void setYearmaxbeginpresetup(Integer yearmaxbeginpresetup) {
        this.yearmaxbeginpresetup = yearmaxbeginpresetup;
    }

    @Basic
    @Column(name = "MAXBEGINOPERATION", nullable = true)
    public Date getMaxbeginoperation() {
        return maxbeginoperation;
    }

    public void setMaxbeginoperation(Date maxbeginoperation) {
        this.maxbeginoperation = maxbeginoperation;
    }

    @Basic
    @Column(name = "MAXBEGINOPERATIONTIME", nullable = true)
    public Time getMaxbeginoperationtime() {
        return maxbeginoperationtime;
    }

    public void setMaxbeginoperationtime(Time maxbeginoperationtime) {
        this.maxbeginoperationtime = maxbeginoperationtime;
    }

    @Basic
    @Column(name = "WEEKMAXBEGINOPERATION", nullable = false)
    public Integer getWeekmaxbeginoperation() {
        return weekmaxbeginoperation;
    }

    public void setWeekmaxbeginoperation(Integer weekmaxbeginoperation) {
        this.weekmaxbeginoperation = weekmaxbeginoperation;
    }

    @Basic
    @Column(name = "YEARMAXBEGINOPERATION", nullable = false)
    public Integer getYearmaxbeginoperation() {
        return yearmaxbeginoperation;
    }

    public void setYearmaxbeginoperation(Integer yearmaxbeginoperation) {
        this.yearmaxbeginoperation = yearmaxbeginoperation;
    }

    @Basic
    @Column(name = "MAXBEGINPOSTSETUP", nullable = true)
    public Date getMaxbeginpostsetup() {
        return maxbeginpostsetup;
    }

    public void setMaxbeginpostsetup(Date maxbeginpostsetup) {
        this.maxbeginpostsetup = maxbeginpostsetup;
    }

    @Basic
    @Column(name = "MAXBEGINPOSTSETUPTIME", nullable = true)
    public Time getMaxbeginpostsetuptime() {
        return maxbeginpostsetuptime;
    }

    public void setMaxbeginpostsetuptime(Time maxbeginpostsetuptime) {
        this.maxbeginpostsetuptime = maxbeginpostsetuptime;
    }

    @Basic
    @Column(name = "WEEKMAXBEGINPOSTSETUP", nullable = false)
    public Integer getWeekmaxbeginpostsetup() {
        return weekmaxbeginpostsetup;
    }

    public void setWeekmaxbeginpostsetup(Integer weekmaxbeginpostsetup) {
        this.weekmaxbeginpostsetup = weekmaxbeginpostsetup;
    }

    @Basic
    @Column(name = "YEARMAXBEGINPOSTSETUP", nullable = false)
    public Integer getYearmaxbeginpostsetup() {
        return yearmaxbeginpostsetup;
    }

    public void setYearmaxbeginpostsetup(Integer yearmaxbeginpostsetup) {
        this.yearmaxbeginpostsetup = yearmaxbeginpostsetup;
    }

    @Basic
    @Column(name = "MAXENDSTEP", nullable = true)
    public Date getMaxendstep() {
        return maxendstep;
    }

    public void setMaxendstep(Date maxendstep) {
        this.maxendstep = maxendstep;
    }

    @Basic
    @Column(name = "MAXENDSTEPTIME", nullable = true)
    public Time getMaxendsteptime() {
        return maxendsteptime;
    }

    public void setMaxendsteptime(Time maxendsteptime) {
        this.maxendsteptime = maxendsteptime;
    }

    @Basic
    @Column(name = "WEEKMAXENDSTEP", nullable = false)
    public Integer getWeekmaxendstep() {
        return weekmaxendstep;
    }

    public void setWeekmaxendstep(Integer weekmaxendstep) {
        this.weekmaxendstep = weekmaxendstep;
    }

    @Basic
    @Column(name = "YEARMAXENDSTEP", nullable = false)
    public Integer getYearmaxendstep() {
        return yearmaxendstep;
    }

    public void setYearmaxendstep(Integer yearmaxendstep) {
        this.yearmaxendstep = yearmaxendstep;
    }

    @Basic
    @Column(name = "INITIALUSERPRIMARYQUANTITY", nullable = true, precision = 5)
    public BigDecimal getInitialuserprimaryquantity() {
        return initialuserprimaryquantity;
    }

    public void setInitialuserprimaryquantity(BigDecimal initialuserprimaryquantity) {
        this.initialuserprimaryquantity = initialuserprimaryquantity;
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
    @Column(name = "USERPRIMARYUOMCODE", nullable = true, length = 3)
    public String getUserprimaryuomcode() {
        return userprimaryuomcode;
    }

    public void setUserprimaryuomcode(String userprimaryuomcode) {
        this.userprimaryuomcode = userprimaryuomcode;
    }

    @Basic
    @Column(name = "INITIALBASEPRIMARYQUANTITY", nullable = true, precision = 5)
    public BigDecimal getInitialbaseprimaryquantity() {
        return initialbaseprimaryquantity;
    }

    public void setInitialbaseprimaryquantity(BigDecimal initialbaseprimaryquantity) {
        this.initialbaseprimaryquantity = initialbaseprimaryquantity;
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
    @Column(name = "BASEPRIMARYUOMCODE", nullable = true, length = 3)
    public String getBaseprimaryuomcode() {
        return baseprimaryuomcode;
    }

    public void setBaseprimaryuomcode(String baseprimaryuomcode) {
        this.baseprimaryuomcode = baseprimaryuomcode;
    }

    @Basic
    @Column(name = "INITIALUSERSECONDARYQUANTITY", nullable = true, precision = 5)
    public BigDecimal getInitialusersecondaryquantity() {
        return initialusersecondaryquantity;
    }

    public void setInitialusersecondaryquantity(BigDecimal initialusersecondaryquantity) {
        this.initialusersecondaryquantity = initialusersecondaryquantity;
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
    @Column(name = "USERSECONDARYUOMCODE", nullable = true, length = 3)
    public String getUsersecondaryuomcode() {
        return usersecondaryuomcode;
    }

    public void setUsersecondaryuomcode(String usersecondaryuomcode) {
        this.usersecondaryuomcode = usersecondaryuomcode;
    }

    @Basic
    @Column(name = "INITIALBASESECONDARYQUANTITY", nullable = true, precision = 5)
    public BigDecimal getInitialbasesecondaryquantity() {
        return initialbasesecondaryquantity;
    }

    public void setInitialbasesecondaryquantity(BigDecimal initialbasesecondaryquantity) {
        this.initialbasesecondaryquantity = initialbasesecondaryquantity;
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
    @Column(name = "BASESECONDARYUOMCODE", nullable = true, length = 3)
    public String getBasesecondaryuomcode() {
        return basesecondaryuomcode;
    }

    public void setBasesecondaryuomcode(String basesecondaryuomcode) {
        this.basesecondaryuomcode = basesecondaryuomcode;
    }

    @Basic
    @Column(name = "INITIALUSERPACKAGINGQUANTITY", nullable = true, precision = 5)
    public BigDecimal getInitialuserpackagingquantity() {
        return initialuserpackagingquantity;
    }

    public void setInitialuserpackagingquantity(BigDecimal initialuserpackagingquantity) {
        this.initialuserpackagingquantity = initialuserpackagingquantity;
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
    @Column(name = "USERPACKAGINGUOMCODE", nullable = true, length = 3)
    public String getUserpackaginguomcode() {
        return userpackaginguomcode;
    }

    public void setUserpackaginguomcode(String userpackaginguomcode) {
        this.userpackaginguomcode = userpackaginguomcode;
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
    @Column(name = "MANUALSTEPFROMDEMAND", nullable = false)
    public Short getManualstepfromdemand() {
        return manualstepfromdemand;
    }

    public void setManualstepfromdemand(Short manualstepfromdemand) {
        this.manualstepfromdemand = manualstepfromdemand;
    }

    @Basic
    @Column(name = "MANUALMODIFIEDSTEP", nullable = false)
    public Short getManualmodifiedstep() {
        return manualmodifiedstep;
    }

    public void setManualmodifiedstep(Short manualmodifiedstep) {
        this.manualmodifiedstep = manualmodifiedstep;
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
    @Column(name = "ABSUNIQUEID", nullable = false)
    public Long getAbsuniqueid() {
        return absuniqueid;
    }

    public void setAbsuniqueid(Long absuniqueid) {
        this.absuniqueid = absuniqueid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Productiondemandstep that = (Productiondemandstep) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(itemtypeaficompanycode, that.itemtypeaficompanycode) &&
            Objects.equals(itemtypeaficode, that.itemtypeaficode) &&
            Objects.equals(routingnumberid, that.routingnumberid) &&
            Objects.equals(stdroutingstepnumberid, that.stdroutingstepnumberid) &&
            Objects.equals(stdroutingstepsequence, that.stdroutingstepsequence) &&
            Objects.equals(stdroutingstepsubsequence, that.stdroutingstepsubsequence) &&
            Objects.equals(plannedworkcentercode, that.plannedworkcentercode) &&
            Objects.equals(plannedoperationcode, that.plannedoperationcode) &&
            Objects.equals(operationcode, that.operationcode) &&
            Objects.equals(dyelothandled, that.dyelothandled) &&
            Objects.equals(workcenterandoperattributescod, that.workcenterandoperattributescod) &&
            Objects.equals(partialstep, that.partialstep) &&
            Objects.equals(progressstatus, that.progressstatus) &&
            Objects.equals(steptype, that.steptype) &&
            Objects.equals(prodreservationlinkgroupcode, that.prodreservationlinkgroupcode) &&
            Objects.equals(maxnumberofresourcesallowed, that.maxnumberofresourcesallowed) &&
            Objects.equals(warehousewipcompanycode, that.warehousewipcompanycode) &&
            Objects.equals(locwipissuewhszonephywhscmycod, that.locwipissuewhszonephywhscmycod) &&
            Objects.equals(locwipissuewhszonephywhscode, that.locwipissuewhszonephywhscode) &&
            Objects.equals(locwipissuewarehousezonecode, that.locwipissuewarehousezonecode) &&
            Objects.equals(locationwipissuecode, that.locationwipissuecode) &&
            Objects.equals(locwipentrywhszonephywhscmycod, that.locwipentrywhszonephywhscmycod) &&
            Objects.equals(locwipentrywhszonephywhscode, that.locwipentrywhszonephywhscode) &&
            Objects.equals(locwipentrywarehousezonecode, that.locwipentrywarehousezonecode) &&
            Objects.equals(locationwipentrycode, that.locationwipentrycode) &&
            Objects.equals(longdescription, that.longdescription) &&
            Objects.equals(shortdescription, that.shortdescription) &&
            Objects.equals(searchdescription, that.searchdescription) &&
            Objects.equals(productionordercode, that.productionordercode) &&
            Objects.equals(stepnumberlink, that.stepnumberlink) &&
            Objects.equals(initialplanneddatetime, that.initialplanneddatetime) &&
            Objects.equals(finalplanneddatetime, that.finalplanneddatetime) &&
            Objects.equals(initialplanscheddatetime, that.initialplanscheddatetime) &&
            Objects.equals(finalplanscheddatetime, that.finalplanscheddatetime) &&
            Objects.equals(initialscheduleddatetime, that.initialscheduleddatetime) &&
            Objects.equals(scheduledstep, that.scheduledstep) &&
            Objects.equals(finalscheduleddatetime, that.finalscheduleddatetime) &&
            Objects.equals(standardstepquantity, that.standardstepquantity) &&
            Objects.equals(standardstepquantityuomcode, that.standardstepquantityuomcode) &&
            Objects.equals(stepefficiencyapply, that.stepefficiencyapply) &&
            Objects.equals(stepefficiency, that.stepefficiency) &&
            Objects.equals(nrofmachine, that.nrofmachine) &&
            Objects.equals(repetitionnumber, that.repetitionnumber) &&
            Objects.equals(bathvolume, that.bathvolume) &&
            Objects.equals(currentstepprogress, that.currentstepprogress) &&
            Objects.equals(previousstepprogress, that.previousstepprogress) &&
            Objects.equals(opstepgroupcode, that.opstepgroupcode) &&
            Objects.equals(generateautomaticqatest, that.generateautomaticqatest) &&
            Objects.equals(overlappingmanagement, that.overlappingmanagement) &&
            Objects.equals(overlappingquantity, that.overlappingquantity) &&
            Objects.equals(overlappinguomcategory, that.overlappinguomcategory) &&
            Objects.equals(lossincreasetype1Code, that.lossincreasetype1Code) &&
            Objects.equals(lossincrease1, that.lossincrease1) &&
            Objects.equals(lossincreaserefuom1Code, that.lossincreaserefuom1Code) &&
            Objects.equals(lossincreasetype2Code, that.lossincreasetype2Code) &&
            Objects.equals(lossincrease2, that.lossincrease2) &&
            Objects.equals(lossincreaserefuom2Code, that.lossincreaserefuom2Code) &&
            Objects.equals(lossincreasetype3Code, that.lossincreasetype3Code) &&
            Objects.equals(lossincrease3, that.lossincrease3) &&
            Objects.equals(lossincreaserefuom3Code, that.lossincreaserefuom3Code) &&
            Objects.equals(lossincreasetype4Code, that.lossincreasetype4Code) &&
            Objects.equals(lossincrease4, that.lossincrease4) &&
            Objects.equals(lossincreaserefuom4Code, that.lossincreaserefuom4Code) &&
            Objects.equals(lossincreasetype5Code, that.lossincreasetype5Code) &&
            Objects.equals(lossincrease5, that.lossincrease5) &&
            Objects.equals(lossincreaserefuom5Code, that.lossincreaserefuom5Code) &&
            Objects.equals(lossincreasetype6Code, that.lossincreasetype6Code) &&
            Objects.equals(lossincrease6, that.lossincrease6) &&
            Objects.equals(lossincreaserefuom6Code, that.lossincreaserefuom6Code) &&
            Objects.equals(lossincreasetype7Code, that.lossincreasetype7Code) &&
            Objects.equals(lossincrease7, that.lossincrease7) &&
            Objects.equals(lossincreaserefuom7Code, that.lossincreaserefuom7Code) &&
            Objects.equals(lossincreasetype8Code, that.lossincreasetype8Code) &&
            Objects.equals(lossincrease8, that.lossincrease8) &&
            Objects.equals(lossincreaserefuom8Code, that.lossincreaserefuom8Code) &&
            Objects.equals(lossincreasetypepolicy, that.lossincreasetypepolicy) &&
            Objects.equals(parallelpdnumber, that.parallelpdnumber) &&
            Objects.equals(planningleadtime, that.planningleadtime) &&
            Objects.equals(timetype1Code, that.timetype1Code) &&
            Objects.equals(time1, that.time1) &&
            Objects.equals(timeunit1, that.timeunit1) &&
            Objects.equals(timerefqty1, that.timerefqty1) &&
            Objects.equals(timerefuom1Code, that.timerefuom1Code) &&
            Objects.equals(timetype2Code, that.timetype2Code) &&
            Objects.equals(time2, that.time2) &&
            Objects.equals(timeunit2, that.timeunit2) &&
            Objects.equals(timerefqty2, that.timerefqty2) &&
            Objects.equals(timerefuom2Code, that.timerefuom2Code) &&
            Objects.equals(timetype3Code, that.timetype3Code) &&
            Objects.equals(time3, that.time3) &&
            Objects.equals(timeunit3, that.timeunit3) &&
            Objects.equals(timerefqty3, that.timerefqty3) &&
            Objects.equals(timerefuom3Code, that.timerefuom3Code) &&
            Objects.equals(timetype4Code, that.timetype4Code) &&
            Objects.equals(time4, that.time4) &&
            Objects.equals(timeunit4, that.timeunit4) &&
            Objects.equals(timerefqty4, that.timerefqty4) &&
            Objects.equals(timerefuom4Code, that.timerefuom4Code) &&
            Objects.equals(timetype5Code, that.timetype5Code) &&
            Objects.equals(time5, that.time5) &&
            Objects.equals(timeunit5, that.timeunit5) &&
            Objects.equals(timerefqty5, that.timerefqty5) &&
            Objects.equals(timerefuom5Code, that.timerefuom5Code) &&
            Objects.equals(calculatedtime1, that.calculatedtime1) &&
            Objects.equals(calculatedtime2, that.calculatedtime2) &&
            Objects.equals(calculatedtime3, that.calculatedtime3) &&
            Objects.equals(calculatedtime4, that.calculatedtime4) &&
            Objects.equals(minbeginqueue, that.minbeginqueue) &&
            Objects.equals(minbeginqueuetime, that.minbeginqueuetime) &&
            Objects.equals(weekminbeginqueue, that.weekminbeginqueue) &&
            Objects.equals(yearminbeginqueue, that.yearminbeginqueue) &&
            Objects.equals(minbeginpresetup, that.minbeginpresetup) &&
            Objects.equals(minbeginpresetuptime, that.minbeginpresetuptime) &&
            Objects.equals(weekminbeginpresetup, that.weekminbeginpresetup) &&
            Objects.equals(yearminbeginpresetup, that.yearminbeginpresetup) &&
            Objects.equals(minbeginoperation, that.minbeginoperation) &&
            Objects.equals(minbeginoperationtime, that.minbeginoperationtime) &&
            Objects.equals(weekminbeginoperation, that.weekminbeginoperation) &&
            Objects.equals(yearminbeginoperation, that.yearminbeginoperation) &&
            Objects.equals(minbeginpostsetup, that.minbeginpostsetup) &&
            Objects.equals(minbeginpostsetuptime, that.minbeginpostsetuptime) &&
            Objects.equals(weekminbeginpostsetup, that.weekminbeginpostsetup) &&
            Objects.equals(yearminbeginpostsetup, that.yearminbeginpostsetup) &&
            Objects.equals(minendstep, that.minendstep) &&
            Objects.equals(minendsteptime, that.minendsteptime) &&
            Objects.equals(weekminendstep, that.weekminendstep) &&
            Objects.equals(yearminendstep, that.yearminendstep) &&
            Objects.equals(stdbeginqueue, that.stdbeginqueue) &&
            Objects.equals(stdbeginqueuetime, that.stdbeginqueuetime) &&
            Objects.equals(weekstdbeginqueue, that.weekstdbeginqueue) &&
            Objects.equals(yearstdbeginqueue, that.yearstdbeginqueue) &&
            Objects.equals(stdbeginpresetup, that.stdbeginpresetup) &&
            Objects.equals(stdbeginpresetuptime, that.stdbeginpresetuptime) &&
            Objects.equals(weekstdbeginpresetup, that.weekstdbeginpresetup) &&
            Objects.equals(yearstdbeginpresetup, that.yearstdbeginpresetup) &&
            Objects.equals(stdbeginoperation, that.stdbeginoperation) &&
            Objects.equals(stdbeginoperationtime, that.stdbeginoperationtime) &&
            Objects.equals(weekstdbeginoperation, that.weekstdbeginoperation) &&
            Objects.equals(yearstdbeginoperation, that.yearstdbeginoperation) &&
            Objects.equals(stdbeginpostsetup, that.stdbeginpostsetup) &&
            Objects.equals(stdbeginpostsetuptime, that.stdbeginpostsetuptime) &&
            Objects.equals(weekstdbeginpostsetup, that.weekstdbeginpostsetup) &&
            Objects.equals(yearstdbeginpostsetup, that.yearstdbeginpostsetup) &&
            Objects.equals(stdendstep, that.stdendstep) &&
            Objects.equals(stdendsteptime, that.stdendsteptime) &&
            Objects.equals(weekstdendstep, that.weekstdendstep) &&
            Objects.equals(yearstdendstep, that.yearstdendstep) &&
            Objects.equals(maxbeginqueue, that.maxbeginqueue) &&
            Objects.equals(maxbeginqueuetime, that.maxbeginqueuetime) &&
            Objects.equals(weekmaxbeginqueue, that.weekmaxbeginqueue) &&
            Objects.equals(yearmaxbeginqueue, that.yearmaxbeginqueue) &&
            Objects.equals(maxbeginpresetup, that.maxbeginpresetup) &&
            Objects.equals(maxbeginpresetuptime, that.maxbeginpresetuptime) &&
            Objects.equals(weekmaxbeginpresetup, that.weekmaxbeginpresetup) &&
            Objects.equals(yearmaxbeginpresetup, that.yearmaxbeginpresetup) &&
            Objects.equals(maxbeginoperation, that.maxbeginoperation) &&
            Objects.equals(maxbeginoperationtime, that.maxbeginoperationtime) &&
            Objects.equals(weekmaxbeginoperation, that.weekmaxbeginoperation) &&
            Objects.equals(yearmaxbeginoperation, that.yearmaxbeginoperation) &&
            Objects.equals(maxbeginpostsetup, that.maxbeginpostsetup) &&
            Objects.equals(maxbeginpostsetuptime, that.maxbeginpostsetuptime) &&
            Objects.equals(weekmaxbeginpostsetup, that.weekmaxbeginpostsetup) &&
            Objects.equals(yearmaxbeginpostsetup, that.yearmaxbeginpostsetup) &&
            Objects.equals(maxendstep, that.maxendstep) &&
            Objects.equals(maxendsteptime, that.maxendsteptime) &&
            Objects.equals(weekmaxendstep, that.weekmaxendstep) &&
            Objects.equals(yearmaxendstep, that.yearmaxendstep) &&
            Objects.equals(initialuserprimaryquantity, that.initialuserprimaryquantity) &&
            Objects.equals(finaluserprimaryquantity, that.finaluserprimaryquantity) &&
            Objects.equals(userprimaryuomcode, that.userprimaryuomcode) &&
            Objects.equals(initialbaseprimaryquantity, that.initialbaseprimaryquantity) &&
            Objects.equals(finalbaseprimaryquantity, that.finalbaseprimaryquantity) &&
            Objects.equals(baseprimaryuomcode, that.baseprimaryuomcode) &&
            Objects.equals(initialusersecondaryquantity, that.initialusersecondaryquantity) &&
            Objects.equals(finalusersecondaryquantity, that.finalusersecondaryquantity) &&
            Objects.equals(usersecondaryuomcode, that.usersecondaryuomcode) &&
            Objects.equals(initialbasesecondaryquantity, that.initialbasesecondaryquantity) &&
            Objects.equals(finalbasesecondaryquantity, that.finalbasesecondaryquantity) &&
            Objects.equals(basesecondaryuomcode, that.basesecondaryuomcode) &&
            Objects.equals(initialuserpackagingquantity, that.initialuserpackagingquantity) &&
            Objects.equals(finaluserpackagingquantity, that.finaluserpackagingquantity) &&
            Objects.equals(userpackaginguomcode, that.userpackaginguomcode) &&
            Objects.equals(creationdatetime, that.creationdatetime) &&
            Objects.equals(creationuser, that.creationuser) &&
            Objects.equals(lastupdatedatetime, that.lastupdatedatetime) &&
            Objects.equals(lastupdateuser, that.lastupdateuser) &&
            Objects.equals(manualstepfromdemand, that.manualstepfromdemand) &&
            Objects.equals(manualmodifiedstep, that.manualmodifiedstep) &&
            Objects.equals(groupstepnumber, that.groupstepnumber) &&
            Objects.equals(absuniqueid, that.absuniqueid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemtypeaficompanycode, itemtypeaficode, routingnumberid, stdroutingstepnumberid, stdroutingstepsequence, stdroutingstepsubsequence, plannedworkcentercode, plannedoperationcode, operationcode, dyelothandled, workcenterandoperattributescod, partialstep, progressstatus, steptype, prodreservationlinkgroupcode, maxnumberofresourcesallowed, warehousewipcompanycode, locwipissuewhszonephywhscmycod, locwipissuewhszonephywhscode, locwipissuewarehousezonecode, locationwipissuecode, locwipentrywhszonephywhscmycod, locwipentrywhszonephywhscode, locwipentrywarehousezonecode, locationwipentrycode, longdescription, shortdescription, searchdescription, productionordercode, stepnumberlink, initialplanneddatetime, finalplanneddatetime, initialplanscheddatetime, finalplanscheddatetime, initialscheduleddatetime, scheduledstep, finalscheduleddatetime, standardstepquantity, standardstepquantityuomcode, stepefficiencyapply, stepefficiency, nrofmachine, repetitionnumber, bathvolume, currentstepprogress, previousstepprogress, opstepgroupcode, generateautomaticqatest, overlappingmanagement, overlappingquantity, overlappinguomcategory, lossincreasetype1Code, lossincrease1, lossincreaserefuom1Code, lossincreasetype2Code, lossincrease2, lossincreaserefuom2Code, lossincreasetype3Code, lossincrease3, lossincreaserefuom3Code, lossincreasetype4Code, lossincrease4, lossincreaserefuom4Code, lossincreasetype5Code, lossincrease5, lossincreaserefuom5Code, lossincreasetype6Code, lossincrease6, lossincreaserefuom6Code, lossincreasetype7Code, lossincrease7, lossincreaserefuom7Code, lossincreasetype8Code, lossincrease8, lossincreaserefuom8Code, lossincreasetypepolicy, parallelpdnumber, planningleadtime, timetype1Code, time1, timeunit1, timerefqty1, timerefuom1Code, timetype2Code, time2, timeunit2, timerefqty2, timerefuom2Code, timetype3Code, time3, timeunit3, timerefqty3, timerefuom3Code, timetype4Code, time4, timeunit4, timerefqty4, timerefuom4Code, timetype5Code, time5, timeunit5, timerefqty5, timerefuom5Code, calculatedtime1, calculatedtime2, calculatedtime3, calculatedtime4, minbeginqueue, minbeginqueuetime, weekminbeginqueue, yearminbeginqueue, minbeginpresetup, minbeginpresetuptime, weekminbeginpresetup, yearminbeginpresetup, minbeginoperation, minbeginoperationtime, weekminbeginoperation, yearminbeginoperation, minbeginpostsetup, minbeginpostsetuptime, weekminbeginpostsetup, yearminbeginpostsetup, minendstep, minendsteptime, weekminendstep, yearminendstep, stdbeginqueue, stdbeginqueuetime, weekstdbeginqueue, yearstdbeginqueue, stdbeginpresetup, stdbeginpresetuptime, weekstdbeginpresetup, yearstdbeginpresetup, stdbeginoperation, stdbeginoperationtime, weekstdbeginoperation, yearstdbeginoperation, stdbeginpostsetup, stdbeginpostsetuptime, weekstdbeginpostsetup, yearstdbeginpostsetup, stdendstep, stdendsteptime, weekstdendstep, yearstdendstep, maxbeginqueue, maxbeginqueuetime, weekmaxbeginqueue, yearmaxbeginqueue, maxbeginpresetup, maxbeginpresetuptime, weekmaxbeginpresetup, yearmaxbeginpresetup, maxbeginoperation, maxbeginoperationtime, weekmaxbeginoperation, yearmaxbeginoperation, maxbeginpostsetup, maxbeginpostsetuptime, weekmaxbeginpostsetup, yearmaxbeginpostsetup, maxendstep, maxendsteptime, weekmaxendstep, yearmaxendstep, initialuserprimaryquantity, finaluserprimaryquantity, userprimaryuomcode, initialbaseprimaryquantity, finalbaseprimaryquantity, baseprimaryuomcode, initialusersecondaryquantity, finalusersecondaryquantity, usersecondaryuomcode, initialbasesecondaryquantity, finalbasesecondaryquantity, basesecondaryuomcode, initialuserpackagingquantity, finaluserpackagingquantity, userpackaginguomcode, creationdatetime, creationuser, lastupdatedatetime, lastupdateuser, manualstepfromdemand, manualmodifiedstep, groupstepnumber, absuniqueid);
    }

    @Override
    public String toString() {
        return "Productiondemandstep{" +
            "id=" + id +
            ", itemtypeaficompanycode='" + itemtypeaficompanycode + '\'' +
            ", itemtypeaficode='" + itemtypeaficode + '\'' +
            ", routingnumberid=" + routingnumberid +
            ", stdroutingstepnumberid=" + stdroutingstepnumberid +
            ", stdroutingstepsequence=" + stdroutingstepsequence +
            ", stdroutingstepsubsequence=" + stdroutingstepsubsequence +
            ", plannedworkcentercode='" + plannedworkcentercode + '\'' +
            ", plannedoperationcode='" + plannedoperationcode + '\'' +
            ", operationcode='" + operationcode + '\'' +
            ", dyelothandled=" + dyelothandled +
            ", workcenterandoperattributescod='" + workcenterandoperattributescod + '\'' +
            ", partialstep=" + partialstep +
            ", progressstatus='" + progressstatus + '\'' +
            ", steptype='" + steptype + '\'' +
            ", prodreservationlinkgroupcode='" + prodreservationlinkgroupcode + '\'' +
            ", maxnumberofresourcesallowed=" + maxnumberofresourcesallowed +
            ", warehousewipcompanycode='" + warehousewipcompanycode + '\'' +
            ", locwipissuewhszonephywhscmycod='" + locwipissuewhszonephywhscmycod + '\'' +
            ", locwipissuewhszonephywhscode='" + locwipissuewhszonephywhscode + '\'' +
            ", locwipissuewarehousezonecode='" + locwipissuewarehousezonecode + '\'' +
            ", locationwipissuecode='" + locationwipissuecode + '\'' +
            ", locwipentrywhszonephywhscmycod='" + locwipentrywhszonephywhscmycod + '\'' +
            ", locwipentrywhszonephywhscode='" + locwipentrywhszonephywhscode + '\'' +
            ", locwipentrywarehousezonecode='" + locwipentrywarehousezonecode + '\'' +
            ", locationwipentrycode='" + locationwipentrycode + '\'' +
            ", longdescription='" + longdescription + '\'' +
            ", shortdescription='" + shortdescription + '\'' +
            ", searchdescription='" + searchdescription + '\'' +
            ", productionordercode='" + productionordercode + '\'' +
            ", stepnumberlink=" + stepnumberlink +
            ", initialplanneddatetime=" + initialplanneddatetime +
            ", finalplanneddatetime=" + finalplanneddatetime +
            ", initialplanscheddatetime=" + initialplanscheddatetime +
            ", finalplanscheddatetime=" + finalplanscheddatetime +
            ", initialscheduleddatetime=" + initialscheduleddatetime +
            ", scheduledstep=" + scheduledstep +
            ", finalscheduleddatetime=" + finalscheduleddatetime +
            ", standardstepquantity=" + standardstepquantity +
            ", standardstepquantityuomcode='" + standardstepquantityuomcode + '\'' +
            ", stepefficiencyapply='" + stepefficiencyapply + '\'' +
            ", stepefficiency=" + stepefficiency +
            ", nrofmachine=" + nrofmachine +
            ", repetitionnumber=" + repetitionnumber +
            ", bathvolume=" + bathvolume +
            ", currentstepprogress='" + currentstepprogress + '\'' +
            ", previousstepprogress='" + previousstepprogress + '\'' +
            ", opstepgroupcode='" + opstepgroupcode + '\'' +
            ", generateautomaticqatest=" + generateautomaticqatest +
            ", overlappingmanagement=" + overlappingmanagement +
            ", overlappingquantity=" + overlappingquantity +
            ", overlappinguomcategory='" + overlappinguomcategory + '\'' +
            ", lossincreasetype1Code='" + lossincreasetype1Code + '\'' +
            ", lossincrease1=" + lossincrease1 +
            ", lossincreaserefuom1Code='" + lossincreaserefuom1Code + '\'' +
            ", lossincreasetype2Code='" + lossincreasetype2Code + '\'' +
            ", lossincrease2=" + lossincrease2 +
            ", lossincreaserefuom2Code='" + lossincreaserefuom2Code + '\'' +
            ", lossincreasetype3Code='" + lossincreasetype3Code + '\'' +
            ", lossincrease3=" + lossincrease3 +
            ", lossincreaserefuom3Code='" + lossincreaserefuom3Code + '\'' +
            ", lossincreasetype4Code='" + lossincreasetype4Code + '\'' +
            ", lossincrease4=" + lossincrease4 +
            ", lossincreaserefuom4Code='" + lossincreaserefuom4Code + '\'' +
            ", lossincreasetype5Code='" + lossincreasetype5Code + '\'' +
            ", lossincrease5=" + lossincrease5 +
            ", lossincreaserefuom5Code='" + lossincreaserefuom5Code + '\'' +
            ", lossincreasetype6Code='" + lossincreasetype6Code + '\'' +
            ", lossincrease6=" + lossincrease6 +
            ", lossincreaserefuom6Code='" + lossincreaserefuom6Code + '\'' +
            ", lossincreasetype7Code='" + lossincreasetype7Code + '\'' +
            ", lossincrease7=" + lossincrease7 +
            ", lossincreaserefuom7Code='" + lossincreaserefuom7Code + '\'' +
            ", lossincreasetype8Code='" + lossincreasetype8Code + '\'' +
            ", lossincrease8=" + lossincrease8 +
            ", lossincreaserefuom8Code='" + lossincreaserefuom8Code + '\'' +
            ", lossincreasetypepolicy='" + lossincreasetypepolicy + '\'' +
            ", parallelpdnumber=" + parallelpdnumber +
            ", planningleadtime=" + planningleadtime +
            ", timetype1Code='" + timetype1Code + '\'' +
            ", time1=" + time1 +
            ", timeunit1='" + timeunit1 + '\'' +
            ", timerefqty1=" + timerefqty1 +
            ", timerefuom1Code='" + timerefuom1Code + '\'' +
            ", timetype2Code='" + timetype2Code + '\'' +
            ", time2=" + time2 +
            ", timeunit2='" + timeunit2 + '\'' +
            ", timerefqty2=" + timerefqty2 +
            ", timerefuom2Code='" + timerefuom2Code + '\'' +
            ", timetype3Code='" + timetype3Code + '\'' +
            ", time3=" + time3 +
            ", timeunit3='" + timeunit3 + '\'' +
            ", timerefqty3=" + timerefqty3 +
            ", timerefuom3Code='" + timerefuom3Code + '\'' +
            ", timetype4Code='" + timetype4Code + '\'' +
            ", time4=" + time4 +
            ", timeunit4='" + timeunit4 + '\'' +
            ", timerefqty4=" + timerefqty4 +
            ", timerefuom4Code='" + timerefuom4Code + '\'' +
            ", timetype5Code='" + timetype5Code + '\'' +
            ", time5=" + time5 +
            ", timeunit5='" + timeunit5 + '\'' +
            ", timerefqty5=" + timerefqty5 +
            ", timerefuom5Code='" + timerefuom5Code + '\'' +
            ", calculatedtime1=" + calculatedtime1 +
            ", calculatedtime2=" + calculatedtime2 +
            ", calculatedtime3=" + calculatedtime3 +
            ", calculatedtime4=" + calculatedtime4 +
            ", minbeginqueue=" + minbeginqueue +
            ", minbeginqueuetime=" + minbeginqueuetime +
            ", weekminbeginqueue=" + weekminbeginqueue +
            ", yearminbeginqueue=" + yearminbeginqueue +
            ", minbeginpresetup=" + minbeginpresetup +
            ", minbeginpresetuptime=" + minbeginpresetuptime +
            ", weekminbeginpresetup=" + weekminbeginpresetup +
            ", yearminbeginpresetup=" + yearminbeginpresetup +
            ", minbeginoperation=" + minbeginoperation +
            ", minbeginoperationtime=" + minbeginoperationtime +
            ", weekminbeginoperation=" + weekminbeginoperation +
            ", yearminbeginoperation=" + yearminbeginoperation +
            ", minbeginpostsetup=" + minbeginpostsetup +
            ", minbeginpostsetuptime=" + minbeginpostsetuptime +
            ", weekminbeginpostsetup=" + weekminbeginpostsetup +
            ", yearminbeginpostsetup=" + yearminbeginpostsetup +
            ", minendstep=" + minendstep +
            ", minendsteptime=" + minendsteptime +
            ", weekminendstep=" + weekminendstep +
            ", yearminendstep=" + yearminendstep +
            ", stdbeginqueue=" + stdbeginqueue +
            ", stdbeginqueuetime=" + stdbeginqueuetime +
            ", weekstdbeginqueue=" + weekstdbeginqueue +
            ", yearstdbeginqueue=" + yearstdbeginqueue +
            ", stdbeginpresetup=" + stdbeginpresetup +
            ", stdbeginpresetuptime=" + stdbeginpresetuptime +
            ", weekstdbeginpresetup=" + weekstdbeginpresetup +
            ", yearstdbeginpresetup=" + yearstdbeginpresetup +
            ", stdbeginoperation=" + stdbeginoperation +
            ", stdbeginoperationtime=" + stdbeginoperationtime +
            ", weekstdbeginoperation=" + weekstdbeginoperation +
            ", yearstdbeginoperation=" + yearstdbeginoperation +
            ", stdbeginpostsetup=" + stdbeginpostsetup +
            ", stdbeginpostsetuptime=" + stdbeginpostsetuptime +
            ", weekstdbeginpostsetup=" + weekstdbeginpostsetup +
            ", yearstdbeginpostsetup=" + yearstdbeginpostsetup +
            ", stdendstep=" + stdendstep +
            ", stdendsteptime=" + stdendsteptime +
            ", weekstdendstep=" + weekstdendstep +
            ", yearstdendstep=" + yearstdendstep +
            ", maxbeginqueue=" + maxbeginqueue +
            ", maxbeginqueuetime=" + maxbeginqueuetime +
            ", weekmaxbeginqueue=" + weekmaxbeginqueue +
            ", yearmaxbeginqueue=" + yearmaxbeginqueue +
            ", maxbeginpresetup=" + maxbeginpresetup +
            ", maxbeginpresetuptime=" + maxbeginpresetuptime +
            ", weekmaxbeginpresetup=" + weekmaxbeginpresetup +
            ", yearmaxbeginpresetup=" + yearmaxbeginpresetup +
            ", maxbeginoperation=" + maxbeginoperation +
            ", maxbeginoperationtime=" + maxbeginoperationtime +
            ", weekmaxbeginoperation=" + weekmaxbeginoperation +
            ", yearmaxbeginoperation=" + yearmaxbeginoperation +
            ", maxbeginpostsetup=" + maxbeginpostsetup +
            ", maxbeginpostsetuptime=" + maxbeginpostsetuptime +
            ", weekmaxbeginpostsetup=" + weekmaxbeginpostsetup +
            ", yearmaxbeginpostsetup=" + yearmaxbeginpostsetup +
            ", maxendstep=" + maxendstep +
            ", maxendsteptime=" + maxendsteptime +
            ", weekmaxendstep=" + weekmaxendstep +
            ", yearmaxendstep=" + yearmaxendstep +
            ", initialuserprimaryquantity=" + initialuserprimaryquantity +
            ", finaluserprimaryquantity=" + finaluserprimaryquantity +
            ", userprimaryuomcode='" + userprimaryuomcode + '\'' +
            ", initialbaseprimaryquantity=" + initialbaseprimaryquantity +
            ", finalbaseprimaryquantity=" + finalbaseprimaryquantity +
            ", baseprimaryuomcode='" + baseprimaryuomcode + '\'' +
            ", initialusersecondaryquantity=" + initialusersecondaryquantity +
            ", finalusersecondaryquantity=" + finalusersecondaryquantity +
            ", usersecondaryuomcode='" + usersecondaryuomcode + '\'' +
            ", initialbasesecondaryquantity=" + initialbasesecondaryquantity +
            ", finalbasesecondaryquantity=" + finalbasesecondaryquantity +
            ", basesecondaryuomcode='" + basesecondaryuomcode + '\'' +
            ", initialuserpackagingquantity=" + initialuserpackagingquantity +
            ", finaluserpackagingquantity=" + finaluserpackagingquantity +
            ", userpackaginguomcode='" + userpackaginguomcode + '\'' +
            ", creationdatetime=" + creationdatetime +
            ", creationuser='" + creationuser + '\'' +
            ", lastupdatedatetime=" + lastupdatedatetime +
            ", lastupdateuser='" + lastupdateuser + '\'' +
            ", manualstepfromdemand=" + manualstepfromdemand +
            ", manualmodifiedstep=" + manualmodifiedstep +
            ", groupstepnumber=" + groupstepnumber +
            ", absuniqueid=" + absuniqueid +
            '}';
    }
}
