package io.vamani.application.model;
import io.vamani.application.db2.domain.Fullitemkeydecoder;
import io.vamani.application.db2.model.MarkerBean;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

public class CutPlanEntryBean implements Serializable {

    private Long id;

    private String porductionCounterCode;

    private String productionCode;

    private String plantCode;

    private String plantDescription;

    private String style;

    private String color;

    private String colorDesc;

    private String destination;

    private String destinationDesc;

    private Double orderQty;

    private Double tolerance;

    private Double netOrderQty;

    private String itemtypecode;

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

    private String summerizedDescription;

    private Double fabricRequired;

    private Long noPlies;

    private Long noMarkers;

    private Double markerLength;

    private String LotNo;

    private Long noRolls;

    private Double endBits;

    private String status;

    private String releaseBy;

    private Instant releaseDate;

    private Instant startDate;

    private Instant endDate;

    private String resourceCode;

    private String resourceDescription;

    private Long actualNoPlies;

    private Double totalHour;

    private Long noCutters;

    private String createdby;

    private Instant createddate;

    private String lastupdatedby;

    private Instant lastupdateddate;

    private Instant progressEntryDate;

    private String progressPostedBy;

    private Instant progressPostedDate;

    private Double plannedAvg;

    private Double actualAvg;

    private Fullitemkeydecoder itemcode;

    private MarkerMasterEntryBean markerMasterEntry;

    private MarkerBean markerBean;

    private List<CutPlanEntryDetailsBean> cutPlanEntryDetailsBeans;

    private List<CutPlanProgressEntryBean> cutPlanProgressEntryBeans;

    private CutPlanProgressEntryBean CutPlanProgressEntry;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPorductionCounterCode() {
        return porductionCounterCode;
    }

    public void setPorductionCounterCode(String porductionCounterCode) {
        this.porductionCounterCode = porductionCounterCode;
    }

    public String getProductionCode() {
        return productionCode;
    }

    public void setProductionCode(String productionCode) {
        this.productionCode = productionCode;
    }

    public String getPlantCode() {
        return plantCode;
    }

    public void setPlantCode(String plantCode) {
        this.plantCode = plantCode;
    }

    public String getPlantDescription() {
        return plantDescription;
    }

    public void setPlantDescription(String plantDescription) {
        this.plantDescription = plantDescription;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColorDesc() {
        return colorDesc;
    }

    public void setColorDesc(String colorDesc) {
        this.colorDesc = colorDesc;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestinationDesc() {
        return destinationDesc;
    }

    public void setDestinationDesc(String destinationDesc) {
        this.destinationDesc = destinationDesc;
    }

    public Double getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(Double orderQty) {
        this.orderQty = orderQty;
    }

    public Double getTolerance() {
        return tolerance;
    }

    public void setTolerance(Double tolerance) {
        this.tolerance = tolerance;
    }

    public Double getNetOrderQty() {
        return netOrderQty;
    }

    public void setNetOrderQty(Double netOrderQty) {
        this.netOrderQty = netOrderQty;
    }

    public String getItemtypecode() {
        return itemtypecode;
    }

    public void setItemtypecode(String itemtypecode) {
        this.itemtypecode = itemtypecode;
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

    public String getSummerizedDescription() {
        return summerizedDescription;
    }

    public void setSummerizedDescription(String summerizedDescription) {
        this.summerizedDescription = summerizedDescription;
    }

    public Double getFabricRequired() {
        return fabricRequired;
    }

    public void setFabricRequired(Double fabricRequired) {
        this.fabricRequired = fabricRequired;
    }

    public Long getNoPlies() {
        return noPlies;
    }

    public void setNoPlies(Long noPlies) {
        this.noPlies = noPlies;
    }

    public Long getNoMarkers() {
        return noMarkers;
    }

    public void setNoMarkers(Long noMarkers) {
        this.noMarkers = noMarkers;
    }

    public Double getMarkerLength() {
        return markerLength;
    }

    public void setMarkerLength(Double markerLength) {
        this.markerLength = markerLength;
    }

    public String getLotNo() {
        return LotNo;
    }

    public void setLotNo(String lotNo) {
        LotNo = lotNo;
    }

    public Long getNoRolls() {
        return noRolls;
    }

    public void setNoRolls(Long noRolls) {
        this.noRolls = noRolls;
    }

    public Double getEndBits() {
        return endBits;
    }

    public void setEndBits(Double endBits) {
        this.endBits = endBits;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Instant getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Instant createddate) {
        this.createddate = createddate;
    }

    public String getLastupdatedby() {
        return lastupdatedby;
    }

    public void setLastupdatedby(String lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
    }

    public Instant getLastupdateddate() {
        return lastupdateddate;
    }

    public void setLastupdateddate(Instant lastupdateddate) {
        this.lastupdateddate = lastupdateddate;
    }

    public Instant getProgressEntryDate() {
        return progressEntryDate;
    }

    public void setProgressEntryDate(Instant progressEntryDate) {
        this.progressEntryDate = progressEntryDate;
    }

    public MarkerMasterEntryBean getMarkerMasterEntry() {
        return markerMasterEntry;
    }

    public void setMarkerMasterEntry(MarkerMasterEntryBean markerMasterEntry) {
        this.markerMasterEntry = markerMasterEntry;
    }

    public MarkerBean getMarkerBean() {
        return markerBean;
    }

    public void setMarkerBean(MarkerBean markerBean) {
        this.markerBean = markerBean;
    }

    public Fullitemkeydecoder getItemcode() {
        return itemcode;
    }

    public void setItemcode(Fullitemkeydecoder itemcode) {
        this.itemcode = itemcode;
    }

    public String getReleaseBy() {
        return releaseBy;
    }

    public void setReleaseBy(String releaseBy) {
        this.releaseBy = releaseBy;
    }

    public Instant getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Instant releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public List<CutPlanEntryDetailsBean> getCutPlanEntryDetailsBeans() {
        return cutPlanEntryDetailsBeans;
    }

    public void setCutPlanEntryDetailsBeans(List<CutPlanEntryDetailsBean> cutPlanEntryDetailsBeans) {
        this.cutPlanEntryDetailsBeans = cutPlanEntryDetailsBeans;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getResourceDescription() {
        return resourceDescription;
    }

    public void setResourceDescription(String resourceDescription) {
        this.resourceDescription = resourceDescription;
    }

    public Long getActualNoPlies() {
        return actualNoPlies;
    }

    public void setActualNoPlies(Long actualNoPlies) {
        this.actualNoPlies = actualNoPlies;
    }

    public Double getTotalHour() {
        return totalHour;
    }

    public void setTotalHour(Double totalHour) {
        this.totalHour = totalHour;
    }

    public Long getNoCutters() {
        return noCutters;
    }

    public void setNoCutters(Long noCutters) {
        this.noCutters = noCutters;
    }

    public String getProgressPostedBy() {
        return progressPostedBy;
    }

    public void setProgressPostedBy(String progressPostedBy) {
        this.progressPostedBy = progressPostedBy;
    }

    public Instant getProgressPostedDate() {
        return progressPostedDate;
    }

    public void setProgressPostedDate(Instant progressPostedDate) {
        this.progressPostedDate = progressPostedDate;
    }

    public Double getPlannedAvg() {
        return plannedAvg;
    }

    public void setPlannedAvg(Double plannedAvg) {
        this.plannedAvg = plannedAvg;
    }

    public Double getActualAvg() {
        return actualAvg;
    }

    public void setActualAvg(Double actualAvg) {
        this.actualAvg = actualAvg;
    }

    public List<CutPlanProgressEntryBean> getCutPlanProgressEntryBeans() {
        return cutPlanProgressEntryBeans;
    }

    public void setCutPlanProgressEntryBeans(List<CutPlanProgressEntryBean> cutPlanProgressEntryBeans) {
        this.cutPlanProgressEntryBeans = cutPlanProgressEntryBeans;
    }

    public CutPlanProgressEntryBean getCutPlanProgressEntry() {
        return CutPlanProgressEntry;
    }

    public void setCutPlanProgressEntry(CutPlanProgressEntryBean cutPlanProgressEntry) {
        CutPlanProgressEntry = cutPlanProgressEntry;
    }
}
