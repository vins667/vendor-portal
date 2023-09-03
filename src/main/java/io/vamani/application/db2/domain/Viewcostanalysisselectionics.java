package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "VIEWCOSTANALYSISSELECTIONICS")
public class Viewcostanalysisselectionics implements Serializable {
    @EmbeddedId
    private ViewcostanalysisselectionicsId id;
    private String companycode;
    private Integer productindex;
    private String projectcode;
    private String plantcode;
    private String costgroupcode;
    private String costscostgroupcode;
    private Integer routeindex;
    private Integer bomindex;
    private Integer stepnumber;
    private String itemnature;
    private String itemtypecode;
    private Date runningdate;
    private String itemsubcode01;
    private String itemsubcode02;
    private String itemsubcode03;
    private String itemsubcode04;
    private String itemsubcode05;
    private String itemsubcode06;
    private String itemsubcode07;
    private String itemsubcode08;
    private String itemsubcode09;
    private String itemsubcode10;
    private String costcategorycode;
    private String costlevelcode;
    private String componentplantcode;
    private String costcentercode;
    private String warehousecode;
    private String prodreservationlinkgroupcode;
    private String productionreservationgroupcode;
    private String componentprimaryuomcode;
    private BigDecimal costper;
    private String currencyper;
    private BigDecimal quantityper;
    private String quantityperuomcode;
    private BigDecimal quantityrequired;
    private BigDecimal costperunit;
    private String costcurrency;
    private BigDecimal quantityrequiredprimaryuom;
    private Integer calculationsequence;
    private Short produced;
    private String wastetype1;
    private BigDecimal waste1;
    private String wastetype2;
    private BigDecimal waste2;
    private String bomcalculateqtyname;
    private String bomcalculateqtycode;
    private String pricelistcode;
    private BigDecimal costinbasecurrency;
    private Timestamp endcalculationdatetime;
    private String wasteproduct;
    private String consumptioncalculationtype;
    private Long stepindex;
    private String workcentercode;
    private String operationcode;
    private String workcenterandoperattributescod;
    private BigDecimal efficiency;
    private String efficiencyapply;
    private BigDecimal stdquantity;
    private String stduomcode;
    private BigDecimal repetitionnumber;
    private BigDecimal initqtyprimeuom;
    private BigDecimal finalqtyprimeuom;
    private BigDecimal initqtyseconduom;
    private BigDecimal finalqtyseconduom;
    private BigDecimal wasteqtyinprimaryuom;
    private BigDecimal wasteqtyinsecondaryuom;
    private BigDecimal totaltime;
    private Long headuniqueid;
    private BigDecimal averagecostinbasecurrency;
    private Integer indlevel;
    private BigDecimal cost;
    private String itemtypecmp;
    private String plantcmp;
    private String productcmp;
    private String toolcmp;
    private String fikdcmp;
    private String costcentercmp;
    private String costcategorycmp;
    private String costlevelcmp;
    private String plantdescription;
    private String leveldescription;
    private String costgroupdescription;
    private String costscostgroupdescription;
    private String centerdescription;
    private String itemdescription;
    private Integer groupnumber;
    private Integer explosionlevel;
    private String fatherproductcode;
    private String fatheritemcode;
    private String itemcode;
    private String costrec1Code;
    private String costrec2Code;
    private String costrec3Code;
    private String costrec4Code;
    private String costrec5Code;
    private String costrec6Code;
    private String costrec7Code;
    private String costrec8Code;
    private String costrec9Code;
    private String costrec10Code;
    private String categorydescription;
    private String costleveldescription;
    private String costcenterdescription;
    private String workcenterdescription;
    private String workattributes;
    private BigDecimal expqtyreqd;
    private String uomcode;
    private BigDecimal multiplequantity;
    private Long viewcostuniqueid;
    private Integer lowerlevel;
    private Integer intermediate;

    public ViewcostanalysisselectionicsId getId() {
        return id;
    }

    public void setId(ViewcostanalysisselectionicsId id) {
        this.id = id;
    }

    @Basic
    @Column(name = "COMPANYCODE", nullable = false, length = 3)
    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    @Basic
    @Column(name = "PRODUCTINDEX", nullable = false, precision = 0)
    public Integer getProductindex() {
        return productindex;
    }

    public void setProductindex(Integer productindex) {
        this.productindex = productindex;
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
    @Column(name = "PLANTCODE", nullable = false, length = 8)
    public String getPlantcode() {
        return plantcode;
    }

    public void setPlantcode(String plantcode) {
        this.plantcode = plantcode;
    }

    @Basic
    @Column(name = "COSTGROUPCODE", nullable = false, length = 3)
    public String getCostgroupcode() {
        return costgroupcode;
    }

    public void setCostgroupcode(String costgroupcode) {
        this.costgroupcode = costgroupcode;
    }

    @Basic
    @Column(name = "COSTSCOSTGROUPCODE", nullable = false, length = 3)
    public String getCostscostgroupcode() {
        return costscostgroupcode;
    }

    public void setCostscostgroupcode(String costscostgroupcode) {
        this.costscostgroupcode = costscostgroupcode;
    }

    @Basic
    @Column(name = "ROUTEINDEX", nullable = false, precision = 0)
    public Integer getRouteindex() {
        return routeindex;
    }

    public void setRouteindex(Integer routeindex) {
        this.routeindex = routeindex;
    }

    @Basic
    @Column(name = "BOMINDEX", nullable = false, precision = 0)
    public Integer getBomindex() {
        return bomindex;
    }

    public void setBomindex(Integer bomindex) {
        this.bomindex = bomindex;
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
    @Column(name = "ITEMNATURE", nullable = true, length = 1)
    public String getItemnature() {
        return itemnature;
    }

    public void setItemnature(String itemnature) {
        this.itemnature = itemnature;
    }

    @Basic
    @Column(name = "ITEMTYPECODE", nullable = false, length = 3)
    public String getItemtypecode() {
        return itemtypecode;
    }

    public void setItemtypecode(String itemtypecode) {
        this.itemtypecode = itemtypecode;
    }

    @Basic
    @Column(name = "RUNNINGDATE", nullable = true)
    public Date getRunningdate() {
        return runningdate;
    }

    public void setRunningdate(Date runningdate) {
        this.runningdate = runningdate;
    }

    @Basic
    @Column(name = "ITEMSUBCODE01", nullable = false, length = 20)
    public String getItemsubcode01() {
        return itemsubcode01;
    }

    public void setItemsubcode01(String itemsubcode01) {
        this.itemsubcode01 = itemsubcode01;
    }

    @Basic
    @Column(name = "ITEMSUBCODE02", nullable = true, length = 10)
    public String getItemsubcode02() {
        return itemsubcode02;
    }

    public void setItemsubcode02(String itemsubcode02) {
        this.itemsubcode02 = itemsubcode02;
    }

    @Basic
    @Column(name = "ITEMSUBCODE03", nullable = true, length = 10)
    public String getItemsubcode03() {
        return itemsubcode03;
    }

    public void setItemsubcode03(String itemsubcode03) {
        this.itemsubcode03 = itemsubcode03;
    }

    @Basic
    @Column(name = "ITEMSUBCODE04", nullable = true, length = 10)
    public String getItemsubcode04() {
        return itemsubcode04;
    }

    public void setItemsubcode04(String itemsubcode04) {
        this.itemsubcode04 = itemsubcode04;
    }

    @Basic
    @Column(name = "ITEMSUBCODE05", nullable = true, length = 10)
    public String getItemsubcode05() {
        return itemsubcode05;
    }

    public void setItemsubcode05(String itemsubcode05) {
        this.itemsubcode05 = itemsubcode05;
    }

    @Basic
    @Column(name = "ITEMSUBCODE06", nullable = true, length = 10)
    public String getItemsubcode06() {
        return itemsubcode06;
    }

    public void setItemsubcode06(String itemsubcode06) {
        this.itemsubcode06 = itemsubcode06;
    }

    @Basic
    @Column(name = "ITEMSUBCODE07", nullable = true, length = 10)
    public String getItemsubcode07() {
        return itemsubcode07;
    }

    public void setItemsubcode07(String itemsubcode07) {
        this.itemsubcode07 = itemsubcode07;
    }

    @Basic
    @Column(name = "ITEMSUBCODE08", nullable = true, length = 10)
    public String getItemsubcode08() {
        return itemsubcode08;
    }

    public void setItemsubcode08(String itemsubcode08) {
        this.itemsubcode08 = itemsubcode08;
    }

    @Basic
    @Column(name = "ITEMSUBCODE09", nullable = true, length = 10)
    public String getItemsubcode09() {
        return itemsubcode09;
    }

    public void setItemsubcode09(String itemsubcode09) {
        this.itemsubcode09 = itemsubcode09;
    }

    @Basic
    @Column(name = "ITEMSUBCODE10", nullable = true, length = 10)
    public String getItemsubcode10() {
        return itemsubcode10;
    }

    public void setItemsubcode10(String itemsubcode10) {
        this.itemsubcode10 = itemsubcode10;
    }

    @Basic
    @Column(name = "COSTCATEGORYCODE", nullable = true, length = 20)
    public String getCostcategorycode() {
        return costcategorycode;
    }

    public void setCostcategorycode(String costcategorycode) {
        this.costcategorycode = costcategorycode;
    }

    @Basic
    @Column(name = "COSTLEVELCODE", nullable = true, length = 3)
    public String getCostlevelcode() {
        return costlevelcode;
    }

    public void setCostlevelcode(String costlevelcode) {
        this.costlevelcode = costlevelcode;
    }

    @Basic
    @Column(name = "COMPONENTPLANTCODE", nullable = true, length = 8)
    public String getComponentplantcode() {
        return componentplantcode;
    }

    public void setComponentplantcode(String componentplantcode) {
        this.componentplantcode = componentplantcode;
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
    @Column(name = "WAREHOUSECODE", nullable = true, length = 8)
    public String getWarehousecode() {
        return warehousecode;
    }

    public void setWarehousecode(String warehousecode) {
        this.warehousecode = warehousecode;
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
    @Column(name = "PRODUCTIONRESERVATIONGROUPCODE", nullable = true, length = 3)
    public String getProductionreservationgroupcode() {
        return productionreservationgroupcode;
    }

    public void setProductionreservationgroupcode(String productionreservationgroupcode) {
        this.productionreservationgroupcode = productionreservationgroupcode;
    }

    @Basic
    @Column(name = "COMPONENTPRIMARYUOMCODE", nullable = true, length = 3)
    public String getComponentprimaryuomcode() {
        return componentprimaryuomcode;
    }

    public void setComponentprimaryuomcode(String componentprimaryuomcode) {
        this.componentprimaryuomcode = componentprimaryuomcode;
    }

    @Basic
    @Column(name = "COSTPER", nullable = true, precision = 5)
    public BigDecimal getCostper() {
        return costper;
    }

    public void setCostper(BigDecimal costper) {
        this.costper = costper;
    }

    @Basic
    @Column(name = "CURRENCYPER", nullable = true, length = 4)
    public String getCurrencyper() {
        return currencyper;
    }

    public void setCurrencyper(String currencyper) {
        this.currencyper = currencyper;
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
    @Column(name = "QUANTITYPERUOMCODE", nullable = true, length = 3)
    public String getQuantityperuomcode() {
        return quantityperuomcode;
    }

    public void setQuantityperuomcode(String quantityperuomcode) {
        this.quantityperuomcode = quantityperuomcode;
    }

    @Basic
    @Column(name = "QUANTITYREQUIRED", nullable = true, precision = 10)
    public BigDecimal getQuantityrequired() {
        return quantityrequired;
    }

    public void setQuantityrequired(BigDecimal quantityrequired) {
        this.quantityrequired = quantityrequired;
    }

    @Basic
    @Column(name = "COSTPERUNIT", nullable = true, precision = 5)
    public BigDecimal getCostperunit() {
        return costperunit;
    }

    public void setCostperunit(BigDecimal costperunit) {
        this.costperunit = costperunit;
    }

    @Basic
    @Column(name = "COSTCURRENCY", nullable = true, length = 4)
    public String getCostcurrency() {
        return costcurrency;
    }

    public void setCostcurrency(String costcurrency) {
        this.costcurrency = costcurrency;
    }

    @Basic
    @Column(name = "QUANTITYREQUIREDPRIMARYUOM", nullable = true, precision = 10)
    public BigDecimal getQuantityrequiredprimaryuom() {
        return quantityrequiredprimaryuom;
    }

    public void setQuantityrequiredprimaryuom(BigDecimal quantityrequiredprimaryuom) {
        this.quantityrequiredprimaryuom = quantityrequiredprimaryuom;
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
    @Column(name = "PRODUCED", nullable = false)
    public Short getProduced() {
        return produced;
    }

    public void setProduced(Short produced) {
        this.produced = produced;
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
    @Column(name = "BOMCALCULATEQTYNAME", nullable = true, length = 100)
    public String getBomcalculateqtyname() {
        return bomcalculateqtyname;
    }

    public void setBomcalculateqtyname(String bomcalculateqtyname) {
        this.bomcalculateqtyname = bomcalculateqtyname;
    }

    @Basic
    @Column(name = "BOMCALCULATEQTYCODE", nullable = true, length = 20)
    public String getBomcalculateqtycode() {
        return bomcalculateqtycode;
    }

    public void setBomcalculateqtycode(String bomcalculateqtycode) {
        this.bomcalculateqtycode = bomcalculateqtycode;
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
    @Column(name = "COSTINBASECURRENCY", nullable = true, precision = 10)
    public BigDecimal getCostinbasecurrency() {
        return costinbasecurrency;
    }

    public void setCostinbasecurrency(BigDecimal costinbasecurrency) {
        this.costinbasecurrency = costinbasecurrency;
    }

    @Basic
    @Column(name = "ENDCALCULATIONDATETIME", nullable = true)
    public Timestamp getEndcalculationdatetime() {
        return endcalculationdatetime;
    }

    public void setEndcalculationdatetime(Timestamp endcalculationdatetime) {
        this.endcalculationdatetime = endcalculationdatetime;
    }

    @Basic
    @Column(name = "WASTEPRODUCT", nullable = true, length = 2)
    public String getWasteproduct() {
        return wasteproduct;
    }

    public void setWasteproduct(String wasteproduct) {
        this.wasteproduct = wasteproduct;
    }

    @Basic
    @Column(name = "CONSUMPTIONCALCULATIONTYPE", nullable = true, length = 1)
    public String getConsumptioncalculationtype() {
        return consumptioncalculationtype;
    }

    public void setConsumptioncalculationtype(String consumptioncalculationtype) {
        this.consumptioncalculationtype = consumptioncalculationtype;
    }

    @Basic
    @Column(name = "STEPINDEX", nullable = true)
    public Long getStepindex() {
        return stepindex;
    }

    public void setStepindex(Long stepindex) {
        this.stepindex = stepindex;
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
    @Column(name = "WORKCENTERANDOPERATTRIBUTESCOD", nullable = true, length = 20)
    public String getWorkcenterandoperattributescod() {
        return workcenterandoperattributescod;
    }

    public void setWorkcenterandoperattributescod(String workcenterandoperattributescod) {
        this.workcenterandoperattributescod = workcenterandoperattributescod;
    }

    @Basic
    @Column(name = "EFFICIENCY", nullable = true, precision = 2)
    public BigDecimal getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(BigDecimal efficiency) {
        this.efficiency = efficiency;
    }

    @Basic
    @Column(name = "EFFICIENCYAPPLY", nullable = true, length = 1)
    public String getEfficiencyapply() {
        return efficiencyapply;
    }

    public void setEfficiencyapply(String efficiencyapply) {
        this.efficiencyapply = efficiencyapply;
    }

    @Basic
    @Column(name = "STDQUANTITY", nullable = true, precision = 5)
    public BigDecimal getStdquantity() {
        return stdquantity;
    }

    public void setStdquantity(BigDecimal stdquantity) {
        this.stdquantity = stdquantity;
    }

    @Basic
    @Column(name = "STDUOMCODE", nullable = true, length = 3)
    public String getStduomcode() {
        return stduomcode;
    }

    public void setStduomcode(String stduomcode) {
        this.stduomcode = stduomcode;
    }

    @Basic
    @Column(name = "REPETITIONNUMBER", nullable = true, precision = 5)
    public BigDecimal getRepetitionnumber() {
        return repetitionnumber;
    }

    public void setRepetitionnumber(BigDecimal repetitionnumber) {
        this.repetitionnumber = repetitionnumber;
    }

    @Basic
    @Column(name = "INITQTYPRIMEUOM", nullable = true, precision = 10)
    public BigDecimal getInitqtyprimeuom() {
        return initqtyprimeuom;
    }

    public void setInitqtyprimeuom(BigDecimal initqtyprimeuom) {
        this.initqtyprimeuom = initqtyprimeuom;
    }

    @Basic
    @Column(name = "FINALQTYPRIMEUOM", nullable = true, precision = 10)
    public BigDecimal getFinalqtyprimeuom() {
        return finalqtyprimeuom;
    }

    public void setFinalqtyprimeuom(BigDecimal finalqtyprimeuom) {
        this.finalqtyprimeuom = finalqtyprimeuom;
    }

    @Basic
    @Column(name = "INITQTYSECONDUOM", nullable = true, precision = 10)
    public BigDecimal getInitqtyseconduom() {
        return initqtyseconduom;
    }

    public void setInitqtyseconduom(BigDecimal initqtyseconduom) {
        this.initqtyseconduom = initqtyseconduom;
    }

    @Basic
    @Column(name = "FINALQTYSECONDUOM", nullable = true, precision = 10)
    public BigDecimal getFinalqtyseconduom() {
        return finalqtyseconduom;
    }

    public void setFinalqtyseconduom(BigDecimal finalqtyseconduom) {
        this.finalqtyseconduom = finalqtyseconduom;
    }

    @Basic
    @Column(name = "WASTEQTYINPRIMARYUOM", nullable = true, precision = 10)
    public BigDecimal getWasteqtyinprimaryuom() {
        return wasteqtyinprimaryuom;
    }

    public void setWasteqtyinprimaryuom(BigDecimal wasteqtyinprimaryuom) {
        this.wasteqtyinprimaryuom = wasteqtyinprimaryuom;
    }

    @Basic
    @Column(name = "WASTEQTYINSECONDARYUOM", nullable = true, precision = 10)
    public BigDecimal getWasteqtyinsecondaryuom() {
        return wasteqtyinsecondaryuom;
    }

    public void setWasteqtyinsecondaryuom(BigDecimal wasteqtyinsecondaryuom) {
        this.wasteqtyinsecondaryuom = wasteqtyinsecondaryuom;
    }

    @Basic
    @Column(name = "TOTALTIME", nullable = true, precision = 10)
    public BigDecimal getTotaltime() {
        return totaltime;
    }

    public void setTotaltime(BigDecimal totaltime) {
        this.totaltime = totaltime;
    }

    @Basic
    @Column(name = "HEADUNIQUEID", nullable = true)
    public Long getHeaduniqueid() {
        return headuniqueid;
    }

    public void setHeaduniqueid(Long headuniqueid) {
        this.headuniqueid = headuniqueid;
    }

    @Basic
    @Column(name = "AVERAGECOSTINBASECURRENCY", nullable = true, precision = 5)
    public BigDecimal getAveragecostinbasecurrency() {
        return averagecostinbasecurrency;
    }

    public void setAveragecostinbasecurrency(BigDecimal averagecostinbasecurrency) {
        this.averagecostinbasecurrency = averagecostinbasecurrency;
    }

    @Basic
    @Column(name = "INDLEVEL", nullable = true)
    public Integer getIndlevel() {
        return indlevel;
    }

    public void setIndlevel(Integer indlevel) {
        this.indlevel = indlevel;
    }

    @Basic
    @Column(name = "COST", nullable = false, precision = 10)
    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Basic
    @Column(name = "ITEMTYPECMP", nullable = true, length = 3)
    public String getItemtypecmp() {
        return itemtypecmp;
    }

    public void setItemtypecmp(String itemtypecmp) {
        this.itemtypecmp = itemtypecmp;
    }

    @Basic
    @Column(name = "PLANTCMP", nullable = true, length = 3)
    public String getPlantcmp() {
        return plantcmp;
    }

    public void setPlantcmp(String plantcmp) {
        this.plantcmp = plantcmp;
    }

    @Basic
    @Column(name = "PRODUCTCMP", nullable = true, length = 3)
    public String getProductcmp() {
        return productcmp;
    }

    public void setProductcmp(String productcmp) {
        this.productcmp = productcmp;
    }

    @Basic
    @Column(name = "TOOLCMP", nullable = true, length = 3)
    public String getToolcmp() {
        return toolcmp;
    }

    public void setToolcmp(String toolcmp) {
        this.toolcmp = toolcmp;
    }

    @Basic
    @Column(name = "FIKDCMP", nullable = true, length = 3)
    public String getFikdcmp() {
        return fikdcmp;
    }

    public void setFikdcmp(String fikdcmp) {
        this.fikdcmp = fikdcmp;
    }

    @Basic
    @Column(name = "COSTCENTERCMP", nullable = true, length = 3)
    public String getCostcentercmp() {
        return costcentercmp;
    }

    public void setCostcentercmp(String costcentercmp) {
        this.costcentercmp = costcentercmp;
    }

    @Basic
    @Column(name = "COSTCATEGORYCMP", nullable = true, length = 3)
    public String getCostcategorycmp() {
        return costcategorycmp;
    }

    public void setCostcategorycmp(String costcategorycmp) {
        this.costcategorycmp = costcategorycmp;
    }

    @Basic
    @Column(name = "COSTLEVELCMP", nullable = true, length = 3)
    public String getCostlevelcmp() {
        return costlevelcmp;
    }

    public void setCostlevelcmp(String costlevelcmp) {
        this.costlevelcmp = costlevelcmp;
    }

    @Basic
    @Column(name = "PLANTDESCRIPTION", nullable = true, length = 80)
    public String getPlantdescription() {
        return plantdescription;
    }

    public void setPlantdescription(String plantdescription) {
        this.plantdescription = plantdescription;
    }

    @Basic
    @Column(name = "LEVELDESCRIPTION", nullable = true, length = 80)
    public String getLeveldescription() {
        return leveldescription;
    }

    public void setLeveldescription(String leveldescription) {
        this.leveldescription = leveldescription;
    }

    @Basic
    @Column(name = "COSTGROUPDESCRIPTION", nullable = true, length = 80)
    public String getCostgroupdescription() {
        return costgroupdescription;
    }

    public void setCostgroupdescription(String costgroupdescription) {
        this.costgroupdescription = costgroupdescription;
    }

    @Basic
    @Column(name = "COSTSCOSTGROUPDESCRIPTION", nullable = true, length = 80)
    public String getCostscostgroupdescription() {
        return costscostgroupdescription;
    }

    public void setCostscostgroupdescription(String costscostgroupdescription) {
        this.costscostgroupdescription = costscostgroupdescription;
    }

    @Basic
    @Column(name = "CENTERDESCRIPTION", nullable = true, length = 80)
    public String getCenterdescription() {
        return centerdescription;
    }

    public void setCenterdescription(String centerdescription) {
        this.centerdescription = centerdescription;
    }

    @Basic
    @Column(name = "ITEMDESCRIPTION", nullable = true, length = 80)
    public String getItemdescription() {
        return itemdescription;
    }

    public void setItemdescription(String itemdescription) {
        this.itemdescription = itemdescription;
    }

    @Basic
    @Column(name = "GROUPNUMBER", nullable = true, precision = 0)
    public Integer getGroupnumber() {
        return groupnumber;
    }

    public void setGroupnumber(Integer groupnumber) {
        this.groupnumber = groupnumber;
    }

    @Basic
    @Column(name = "EXPLOSIONLEVEL", nullable = false)
    public Integer getExplosionlevel() {
        return explosionlevel;
    }

    public void setExplosionlevel(Integer explosionlevel) {
        this.explosionlevel = explosionlevel;
    }

    @Basic
    @Column(name = "FATHERPRODUCTCODE", nullable = true, length = 3)
    public String getFatherproductcode() {
        return fatherproductcode;
    }

    public void setFatherproductcode(String fatherproductcode) {
        this.fatherproductcode = fatherproductcode;
    }

    @Basic
    @Column(name = "FATHERITEMCODE", nullable = true, length = 120)
    public String getFatheritemcode() {
        return fatheritemcode;
    }

    public void setFatheritemcode(String fatheritemcode) {
        this.fatheritemcode = fatheritemcode;
    }

    @Basic
    @Column(name = "ITEMCODE", nullable = true, length = 120)
    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    @Basic
    @Column(name = "COSTREC1CODE", nullable = false, length = 10)
    public String getCostrec1Code() {
        return costrec1Code;
    }

    public void setCostrec1Code(String costrec1Code) {
        this.costrec1Code = costrec1Code;
    }

    @Basic
    @Column(name = "COSTREC2CODE", nullable = false, length = 10)
    public String getCostrec2Code() {
        return costrec2Code;
    }

    public void setCostrec2Code(String costrec2Code) {
        this.costrec2Code = costrec2Code;
    }

    @Basic
    @Column(name = "COSTREC3CODE", nullable = false, length = 10)
    public String getCostrec3Code() {
        return costrec3Code;
    }

    public void setCostrec3Code(String costrec3Code) {
        this.costrec3Code = costrec3Code;
    }

    @Basic
    @Column(name = "COSTREC4CODE", nullable = false, length = 10)
    public String getCostrec4Code() {
        return costrec4Code;
    }

    public void setCostrec4Code(String costrec4Code) {
        this.costrec4Code = costrec4Code;
    }

    @Basic
    @Column(name = "COSTREC5CODE", nullable = false, length = 10)
    public String getCostrec5Code() {
        return costrec5Code;
    }

    public void setCostrec5Code(String costrec5Code) {
        this.costrec5Code = costrec5Code;
    }

    @Basic
    @Column(name = "COSTREC6CODE", nullable = false, length = 10)
    public String getCostrec6Code() {
        return costrec6Code;
    }

    public void setCostrec6Code(String costrec6Code) {
        this.costrec6Code = costrec6Code;
    }

    @Basic
    @Column(name = "COSTREC7CODE", nullable = false, length = 10)
    public String getCostrec7Code() {
        return costrec7Code;
    }

    public void setCostrec7Code(String costrec7Code) {
        this.costrec7Code = costrec7Code;
    }

    @Basic
    @Column(name = "COSTREC8CODE", nullable = false, length = 10)
    public String getCostrec8Code() {
        return costrec8Code;
    }

    public void setCostrec8Code(String costrec8Code) {
        this.costrec8Code = costrec8Code;
    }

    @Basic
    @Column(name = "COSTREC9CODE", nullable = false, length = 10)
    public String getCostrec9Code() {
        return costrec9Code;
    }

    public void setCostrec9Code(String costrec9Code) {
        this.costrec9Code = costrec9Code;
    }

    @Basic
    @Column(name = "COSTREC10CODE", nullable = false, length = 10)
    public String getCostrec10Code() {
        return costrec10Code;
    }

    public void setCostrec10Code(String costrec10Code) {
        this.costrec10Code = costrec10Code;
    }

    @Basic
    @Column(name = "CATEGORYDESCRIPTION", nullable = true, length = 200)
    public String getCategorydescription() {
        return categorydescription;
    }

    public void setCategorydescription(String categorydescription) {
        this.categorydescription = categorydescription;
    }

    @Basic
    @Column(name = "COSTLEVELDESCRIPTION", nullable = true, length = 200)
    public String getCostleveldescription() {
        return costleveldescription;
    }

    public void setCostleveldescription(String costleveldescription) {
        this.costleveldescription = costleveldescription;
    }

    @Basic
    @Column(name = "COSTCENTERDESCRIPTION", nullable = true, length = 200)
    public String getCostcenterdescription() {
        return costcenterdescription;
    }

    public void setCostcenterdescription(String costcenterdescription) {
        this.costcenterdescription = costcenterdescription;
    }

    @Basic
    @Column(name = "WORKCENTERDESCRIPTION", nullable = true, length = 200)
    public String getWorkcenterdescription() {
        return workcenterdescription;
    }

    public void setWorkcenterdescription(String workcenterdescription) {
        this.workcenterdescription = workcenterdescription;
    }

    @Basic
    @Column(name = "WORKATTRIBUTES", nullable = true, length = 80)
    public String getWorkattributes() {
        return workattributes;
    }

    public void setWorkattributes(String workattributes) {
        this.workattributes = workattributes;
    }

    @Basic
    @Column(name = "EXPQTYREQD", nullable = true, precision = 10)
    public BigDecimal getExpqtyreqd() {
        return expqtyreqd;
    }

    public void setExpqtyreqd(BigDecimal expqtyreqd) {
        this.expqtyreqd = expqtyreqd;
    }

    @Basic
    @Column(name = "UOMCODE", nullable = true, length = 3)
    public String getUomcode() {
        return uomcode;
    }

    public void setUomcode(String uomcode) {
        this.uomcode = uomcode;
    }

    @Basic
    @Column(name = "MULTIPLEQUANTITY", nullable = true, precision = 10)
    public BigDecimal getMultiplequantity() {
        return multiplequantity;
    }

    public void setMultiplequantity(BigDecimal multiplequantity) {
        this.multiplequantity = multiplequantity;
    }

    @Basic
    @Column(name = "VIEWCOSTUNIQUEID", nullable = true)
    public Long getViewcostuniqueid() {
        return viewcostuniqueid;
    }

    public void setViewcostuniqueid(Long viewcostuniqueid) {
        this.viewcostuniqueid = viewcostuniqueid;
    }

    @Basic
    @Column(name = "LOWERLEVEL", nullable = false)
    public Integer getLowerlevel() {
        return lowerlevel;
    }

    public void setLowerlevel(Integer lowerlevel) {
        this.lowerlevel = lowerlevel;
    }

    @Basic
    @Column(name = "INTERMEDIATE", nullable = false)
    public Integer getIntermediate() {
        return intermediate;
    }

    public void setIntermediate(Integer intermediate) {
        this.intermediate = intermediate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Viewcostanalysisselectionics that = (Viewcostanalysisselectionics) o;
        return Objects.equals(companycode, that.companycode) && Objects.equals(productindex, that.productindex) && Objects.equals(projectcode, that.projectcode) && Objects.equals(plantcode, that.plantcode) && Objects.equals(costgroupcode, that.costgroupcode) && Objects.equals(costscostgroupcode, that.costscostgroupcode) && Objects.equals(routeindex, that.routeindex) && Objects.equals(bomindex, that.bomindex) && Objects.equals(stepnumber, that.stepnumber) && Objects.equals(itemnature, that.itemnature) && Objects.equals(itemtypecode, that.itemtypecode) && Objects.equals(runningdate, that.runningdate) && Objects.equals(itemsubcode01, that.itemsubcode01) && Objects.equals(itemsubcode02, that.itemsubcode02) && Objects.equals(itemsubcode03, that.itemsubcode03) && Objects.equals(itemsubcode04, that.itemsubcode04) && Objects.equals(itemsubcode05, that.itemsubcode05) && Objects.equals(itemsubcode06, that.itemsubcode06) && Objects.equals(itemsubcode07, that.itemsubcode07) && Objects.equals(itemsubcode08, that.itemsubcode08) && Objects.equals(itemsubcode09, that.itemsubcode09) && Objects.equals(itemsubcode10, that.itemsubcode10) && Objects.equals(costcategorycode, that.costcategorycode) && Objects.equals(costlevelcode, that.costlevelcode) && Objects.equals(componentplantcode, that.componentplantcode) && Objects.equals(costcentercode, that.costcentercode) && Objects.equals(warehousecode, that.warehousecode) && Objects.equals(prodreservationlinkgroupcode, that.prodreservationlinkgroupcode) && Objects.equals(productionreservationgroupcode, that.productionreservationgroupcode) && Objects.equals(componentprimaryuomcode, that.componentprimaryuomcode) && Objects.equals(costper, that.costper) && Objects.equals(currencyper, that.currencyper) && Objects.equals(quantityper, that.quantityper) && Objects.equals(quantityperuomcode, that.quantityperuomcode) && Objects.equals(quantityrequired, that.quantityrequired) && Objects.equals(costperunit, that.costperunit) && Objects.equals(costcurrency, that.costcurrency) && Objects.equals(quantityrequiredprimaryuom, that.quantityrequiredprimaryuom) && Objects.equals(calculationsequence, that.calculationsequence) && Objects.equals(produced, that.produced) && Objects.equals(wastetype1, that.wastetype1) && Objects.equals(waste1, that.waste1) && Objects.equals(wastetype2, that.wastetype2) && Objects.equals(waste2, that.waste2) && Objects.equals(bomcalculateqtyname, that.bomcalculateqtyname) && Objects.equals(bomcalculateqtycode, that.bomcalculateqtycode) && Objects.equals(pricelistcode, that.pricelistcode) && Objects.equals(costinbasecurrency, that.costinbasecurrency) && Objects.equals(endcalculationdatetime, that.endcalculationdatetime) && Objects.equals(wasteproduct, that.wasteproduct) && Objects.equals(consumptioncalculationtype, that.consumptioncalculationtype) && Objects.equals(stepindex, that.stepindex) && Objects.equals(workcentercode, that.workcentercode) && Objects.equals(operationcode, that.operationcode) && Objects.equals(workcenterandoperattributescod, that.workcenterandoperattributescod) && Objects.equals(efficiency, that.efficiency) && Objects.equals(efficiencyapply, that.efficiencyapply) && Objects.equals(stdquantity, that.stdquantity) && Objects.equals(stduomcode, that.stduomcode) && Objects.equals(repetitionnumber, that.repetitionnumber) && Objects.equals(initqtyprimeuom, that.initqtyprimeuom) && Objects.equals(finalqtyprimeuom, that.finalqtyprimeuom) && Objects.equals(initqtyseconduom, that.initqtyseconduom) && Objects.equals(finalqtyseconduom, that.finalqtyseconduom) && Objects.equals(wasteqtyinprimaryuom, that.wasteqtyinprimaryuom) && Objects.equals(wasteqtyinsecondaryuom, that.wasteqtyinsecondaryuom) && Objects.equals(totaltime, that.totaltime) && Objects.equals(headuniqueid, that.headuniqueid) && Objects.equals(averagecostinbasecurrency, that.averagecostinbasecurrency) && Objects.equals(indlevel, that.indlevel) && Objects.equals(cost, that.cost) && Objects.equals(itemtypecmp, that.itemtypecmp) && Objects.equals(plantcmp, that.plantcmp) && Objects.equals(productcmp, that.productcmp) && Objects.equals(toolcmp, that.toolcmp) && Objects.equals(fikdcmp, that.fikdcmp) && Objects.equals(costcentercmp, that.costcentercmp) && Objects.equals(costcategorycmp, that.costcategorycmp) && Objects.equals(costlevelcmp, that.costlevelcmp) && Objects.equals(plantdescription, that.plantdescription) && Objects.equals(leveldescription, that.leveldescription) && Objects.equals(costgroupdescription, that.costgroupdescription) && Objects.equals(costscostgroupdescription, that.costscostgroupdescription) && Objects.equals(centerdescription, that.centerdescription) && Objects.equals(itemdescription, that.itemdescription) && Objects.equals(groupnumber, that.groupnumber) && Objects.equals(explosionlevel, that.explosionlevel) && Objects.equals(fatherproductcode, that.fatherproductcode) && Objects.equals(fatheritemcode, that.fatheritemcode) && Objects.equals(itemcode, that.itemcode) && Objects.equals(costrec1Code, that.costrec1Code) && Objects.equals(costrec2Code, that.costrec2Code) && Objects.equals(costrec3Code, that.costrec3Code) && Objects.equals(costrec4Code, that.costrec4Code) && Objects.equals(costrec5Code, that.costrec5Code) && Objects.equals(costrec6Code, that.costrec6Code) && Objects.equals(costrec7Code, that.costrec7Code) && Objects.equals(costrec8Code, that.costrec8Code) && Objects.equals(costrec9Code, that.costrec9Code) && Objects.equals(costrec10Code, that.costrec10Code) && Objects.equals(categorydescription, that.categorydescription) && Objects.equals(costleveldescription, that.costleveldescription) && Objects.equals(costcenterdescription, that.costcenterdescription) && Objects.equals(workcenterdescription, that.workcenterdescription) && Objects.equals(workattributes, that.workattributes) && Objects.equals(expqtyreqd, that.expqtyreqd) && Objects.equals(uomcode, that.uomcode) && Objects.equals(multiplequantity, that.multiplequantity) && Objects.equals(viewcostuniqueid, that.viewcostuniqueid) && Objects.equals(lowerlevel, that.lowerlevel) && Objects.equals(intermediate, that.intermediate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companycode, productindex, projectcode, plantcode, costgroupcode, costscostgroupcode, routeindex, bomindex, stepnumber, itemnature, itemtypecode, runningdate, itemsubcode01, itemsubcode02, itemsubcode03, itemsubcode04, itemsubcode05, itemsubcode06, itemsubcode07, itemsubcode08, itemsubcode09, itemsubcode10, costcategorycode, costlevelcode, componentplantcode, costcentercode, warehousecode, prodreservationlinkgroupcode, productionreservationgroupcode, componentprimaryuomcode, costper, currencyper, quantityper, quantityperuomcode, quantityrequired, costperunit, costcurrency, quantityrequiredprimaryuom, calculationsequence, produced, wastetype1, waste1, wastetype2, waste2, bomcalculateqtyname, bomcalculateqtycode, pricelistcode, costinbasecurrency, endcalculationdatetime, wasteproduct, consumptioncalculationtype, stepindex, workcentercode, operationcode, workcenterandoperattributescod, efficiency, efficiencyapply, stdquantity, stduomcode, repetitionnumber, initqtyprimeuom, finalqtyprimeuom, initqtyseconduom, finalqtyseconduom, wasteqtyinprimaryuom, wasteqtyinsecondaryuom, totaltime, headuniqueid, averagecostinbasecurrency, indlevel, cost, itemtypecmp, plantcmp, productcmp, toolcmp, fikdcmp, costcentercmp, costcategorycmp, costlevelcmp, plantdescription, leveldescription, costgroupdescription, costscostgroupdescription, centerdescription, itemdescription, groupnumber, explosionlevel, fatherproductcode, fatheritemcode, itemcode, costrec1Code, costrec2Code, costrec3Code, costrec4Code, costrec5Code, costrec6Code, costrec7Code, costrec8Code, costrec9Code, costrec10Code, categorydescription, costleveldescription, costcenterdescription, workcenterdescription, workattributes, expqtyreqd, uomcode, multiplequantity, viewcostuniqueid, lowerlevel, intermediate);
    }
}
