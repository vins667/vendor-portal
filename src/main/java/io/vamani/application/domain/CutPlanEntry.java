package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.Instant;

/**
 * A CutPlanEntry.
 */
@Entity
@Table(name = "cut_plan_entry")
public class CutPlanEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="cutPlanEntrySeq", sequenceName="cut_plan_entry_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="cutPlanEntrySeq")
    private Long id;

    @NotNull
    @Size(max = 10)
    @Column(name = "porduction_counter_code", length = 10, nullable = false)
    private String porductionCounterCode;

    @NotNull
    @Size(max = 20)
    @Column(name = "production_code", length = 20, nullable = false)
    private String productionCode;

    @Size(max = 10)
    @Column(name = "plant_code", length = 10)
    private String plantCode;

    @Size(max = 100)
    @Column(name = "plant_description", length = 100)
    private String plantDescription;

    @NotNull
    @Size(max = 20)
    @Column(name = "style", length = 20, nullable = false)
    private String style;

    @NotNull
    @Size(max = 20)
    @Column(name = "color", length = 20, nullable = false)
    private String color;

    @Size(max = 20)
    @Column(name = "destination", length = 20, nullable = false)
    private String destination;

    @Size(max = 100)
    @Column(name = "destination_desc", length = 100, nullable = false)
    private String destinationDesc;

    @Size(max = 100)
    @Column(name = "color_desc", length = 20)
    private String colorDesc;

    @Column(name = "order_qty")
    private Double orderQty;

    @Column(name = "tolerance")
    private Double tolerance;

    @Column(name = "net_order_qty")
    private Double netOrderQty;

    @Size(max = 3)
    @Column(name = "itemtypecode", length = 3)
    private String itemtypecode;

    @Size(max = 20)
    @Column(name = "subcode_01", length = 20)
    private String subcode01;

    @Size(max = 10)
    @Column(name = "subcode_02", length = 10)
    private String subcode02;

    @Size(max = 10)
    @Column(name = "subcode_03", length = 10)
    private String subcode03;

    @Size(max = 10)
    @Column(name = "subcode_04", length = 10)
    private String subcode04;

    @Size(max = 10)
    @Column(name = "subcode_05", length = 10)
    private String subcode05;

    @Size(max = 10)
    @Column(name = "subcode_06", length = 10)
    private String subcode06;

    @Size(max = 10)
    @Column(name = "subcode_07", length = 10)
    private String subcode07;

    @Size(max = 10)
    @Column(name = "subcode_08", length = 10)
    private String subcode08;

    @Size(max = 10)
    @Column(name = "subcode_09", length = 10)
    private String subcode09;

    @Size(max = 10)
    @Column(name = "subcode_10", length = 10)
    private String subcode10;

    @Size(max = 100)
    @Column(name = "summerized_description", length = 100)
    private String summerizedDescription;

    @Column(name = "fabric_required")
    private Double fabricRequired;

    @Column(name = "no_plies")
    private Long noPlies;

    @Column(name = "actual_no_plies")
    private Long actualNoPlies;

    @Column(name = "no_markers")
    private Long noMarkers;

    @Column(name = "marker_length")
    private Double markerLength;

    @Size(max = 20)
    @Column(name = "lot_no", length = 20)
    private String LotNo;

    @Column(name = "no_rolls")
    private Long noRolls;

    @Column(name = "end_bits")
    private Double endBits;

    @Size(max = 20)
    @Column(name = "status", length = 20)
    private String status;

    @Size(max = 20)
    @Column(name = "release_by", length = 20)
    private String releaseBy;

    @Column(name = "release_date")
    private Instant releaseDate;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "end_date")
    private Instant endDate;

    @Column(name = "no_cutters")
    private Long noCutters;

    @Column(name = "total_hour")
    private Double totalHour;

    @Column(name = "progress_entry_date")
    private Instant progressEntryDate;

    @Column(name = "planned_avg")
    private Double plannedAvg;

    @Column(name = "actual_avg")
    private Double actualAvg;

    @Size(max = 20)
    @Column(name = "createdby", length = 20)
    private String createdby;

    @Column(name = "createddate")
    private Instant createddate;

    @Size(max = 20)
    @Column(name = "lastupdatedby", length = 20)
    private String lastupdatedby;

    @Column(name = "lastupdateddate")
    private Instant lastupdateddate;

    @Size(max = 20)
    @Column(name = "progress_posted_by", length = 20)
    private String progressPostedBy;

    @Column(name = "progress_posted_date")
    private Instant progressPostedDate;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("cutPlanEntries")
    @JoinColumn(name = "marker_master_entry_id")
    private MarkerMasterEntry markerMasterEntry;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPorductionCounterCode() {
        return porductionCounterCode;
    }

    public CutPlanEntry porductionCounterCode(String porductionCounterCode) {
        this.porductionCounterCode = porductionCounterCode;
        return this;
    }

    public void setPorductionCounterCode(String porductionCounterCode) {
        this.porductionCounterCode = porductionCounterCode;
    }

    public String getProductionCode() {
        return productionCode;
    }

    public CutPlanEntry productionCode(String productionCode) {
        this.productionCode = productionCode != null ? productionCode.trim() : productionCode;
        return this;
    }

    public void setProductionCode(String productionCode) {
        this.productionCode = productionCode != null ? productionCode.trim() : productionCode;
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

    public CutPlanEntry style(String style) {
        this.style = style;
        return this;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getColor() {
        return color;
    }

    public CutPlanEntry color(String color) {
        this.color = color;
        return this;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDestination() {
        return destination;
    }

    public CutPlanEntry destination(String destination) {
        this.destination = destination;
        return this;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestinationDesc() {
        return destinationDesc;
    }

    public CutPlanEntry destinationDesc(String destinationDesc) {
        this.destinationDesc = destinationDesc;
        return this;
    }

    public void setDestinationDesc(String destinationDesc) {
        this.destinationDesc = destinationDesc;
    }

    public String getColorDesc() {
        return colorDesc;
    }

    public CutPlanEntry colorDesc(String colorDesc) {
        this.colorDesc = colorDesc;
        return this;
    }

    public void setColorDesc(String colorDesc) {
        this.colorDesc = colorDesc;
    }

    public Double getOrderQty() {
        return orderQty;
    }

    public CutPlanEntry orderQty(Double orderQty) {
        this.orderQty = orderQty;
        return this;
    }

    public void setOrderQty(Double orderQty) {
        this.orderQty = orderQty;
    }

    public Double getTolerance() {
        return tolerance;
    }

    public CutPlanEntry tolerance(Double tolerance) {
        this.tolerance = tolerance;
        return this;
    }

    public void setTolerance(Double tolerance) {
        this.tolerance = tolerance;
    }

    public Double getNetOrderQty() {
        return netOrderQty;
    }

    public CutPlanEntry netOrderQty(Double netOrderQty) {
        this.netOrderQty = netOrderQty;
        return this;
    }

    public void setNetOrderQty(Double netOrderQty) {
        this.netOrderQty = netOrderQty;
    }

    public String getItemtypecode() {
        return itemtypecode;
    }

    public CutPlanEntry itemtypecode(String itemtypecode) {
        this.itemtypecode = itemtypecode;
        return this;
    }

    public void setItemtypecode(String itemtypecode) {
        this.itemtypecode = itemtypecode;
    }

    public String getSubcode01() {
        return subcode01;
    }

    public CutPlanEntry subcode01(String subcode01) {
        this.subcode01 = subcode01 != null ? subcode01.trim() : subcode01;
        return this;
    }

    public void setSubcode01(String subcode01) {
        this.subcode01 = subcode01 != null ? subcode01.trim() : subcode01;
    }

    public String getSubcode02() {
        return subcode02;
    }

    public CutPlanEntry subcode02(String subcode02) {
        this.subcode02 = subcode02 != null ? subcode02.trim() : subcode02;
        return this;
    }

    public void setSubcode02(String subcode02) {
        this.subcode02 = subcode02 != null ? subcode02.trim() : subcode02;
    }

    public String getSubcode03() {
        return subcode03;
    }

    public CutPlanEntry subcode03(String subcode03) {
        this.subcode03 = subcode03 != null ? subcode03.trim() : subcode03;
        return this;
    }

    public void setSubcode03(String subcode03) {
        this.subcode03 = subcode03 != null ? subcode03.trim() : subcode03;
    }

    public String getSubcode04() {
        return subcode04;
    }

    public CutPlanEntry subcode04(String subcode04) {
        this.subcode04 = subcode04 != null ? subcode04.trim() : subcode04;
        return this;
    }

    public void setSubcode04(String subcode04) {
        this.subcode04 = subcode04 != null ? subcode04.trim() : subcode04;
    }

    public String getSubcode05() {
        return subcode05;
    }

    public CutPlanEntry subcode05(String subcode05) {
        this.subcode05 = subcode05 != null ? subcode05.trim() : subcode05;
        return this;
    }

    public void setSubcode05(String subcode05) {
        this.subcode05 = subcode05 != null ? subcode05.trim() : subcode05;
    }

    public String getSubcode06() {
        return subcode06;
    }

    public CutPlanEntry subcode06(String subcode06) {
        this.subcode06 = subcode06 != null ? subcode06.trim() : subcode06;
        return this;
    }

    public void setSubcode06(String subcode06) {
        this.subcode06 = subcode06 != null ? subcode06.trim() : subcode06;
    }

    public String getSubcode07() {
        return subcode07;
    }

    public CutPlanEntry subcode07(String subcode07) {
        this.subcode07 = subcode07 != null ? subcode07.trim() : subcode07;
        return this;
    }

    public void setSubcode07(String subcode07) {
        this.subcode07 = subcode07 != null ? subcode07.trim() : subcode07;
    }

    public String getSubcode08() {
        return subcode08;
    }

    public CutPlanEntry subcode08(String subcode08) {
        this.subcode08 = subcode08 != null ? subcode08.trim() : subcode08;
        return this;
    }

    public void setSubcode08(String subcode08) {
        this.subcode08 = subcode08 != null ? subcode08.trim() : subcode08;
    }

    public String getSubcode09() {
        return subcode09;
    }

    public CutPlanEntry subcode09(String subcode09) {
        this.subcode09 = subcode09 != null ? subcode09.trim() : subcode09;
        return this;
    }

    public void setSubcode09(String subcode09) {
        this.subcode09 = subcode09 != null ? subcode09.trim() : subcode09;
    }

    public String getSubcode10() {
        return subcode10;
    }

    public CutPlanEntry subcode10(String subcode10) {
        this.subcode10 = subcode10 != null ? subcode10.trim() : subcode10;
        return this;
    }

    public void setSubcode10(String subcode10) {
        this.subcode10 = subcode10 != null ? subcode10.trim() : subcode10;
    }

    public String getSummerizedDescription() {
        return summerizedDescription;
    }

    public CutPlanEntry summerizedDescription(String summerizedDescription) {
        this.summerizedDescription = summerizedDescription;
        return this;
    }

    public void setSummerizedDescription(String summerizedDescription) {
        this.summerizedDescription = summerizedDescription;
    }

    public Double getFabricRequired() {
        return fabricRequired;
    }

    public CutPlanEntry fabricRequired(Double fabricRequired) {
        this.fabricRequired = fabricRequired;
        return this;
    }

    public void setFabricRequired(Double fabricRequired) {
        this.fabricRequired = fabricRequired;
    }

    public Long getNoPlies() {
        return noPlies;
    }

    public CutPlanEntry noPlies(Long noPlies) {
        this.noPlies = noPlies;
        return this;
    }

    public void setNoPlies(Long noPlies) {
        this.noPlies = noPlies;
    }

    public Long getNoMarkers() {
        return noMarkers;
    }

    public CutPlanEntry noMarkers(Long noMarkers) {
        this.noMarkers = noMarkers;
        return this;
    }

    public void setNoMarkers(Long noMarkers) {
        this.noMarkers = noMarkers;
    }

    public Double getMarkerLength() {
        return markerLength;
    }

    public CutPlanEntry markerLength(Double markerLength) {
        this.markerLength = markerLength;
        return this;
    }

    public void setMarkerLength(Double markerLength) {
        this.markerLength = markerLength;
    }

    public String getLotNo() {
        return LotNo;
    }

    public CutPlanEntry LotNo(String LotNo) {
        this.LotNo = LotNo;
        return this;
    }

    public void setLotNo(String LotNo) {
        this.LotNo = LotNo;
    }

    public Long getNoRolls() {
        return noRolls;
    }

    public CutPlanEntry noRolls(Long noRolls) {
        this.noRolls = noRolls;
        return this;
    }

    public void setNoRolls(Long noRolls) {
        this.noRolls = noRolls;
    }

    public Double getEndBits() {
        return endBits;
    }

    public CutPlanEntry endBits(Double endBits) {
        this.endBits = endBits;
        return this;
    }

    public void setEndBits(Double endBits) {
        this.endBits = endBits;
    }

    public String getStatus() {
        return status;
    }

    public CutPlanEntry status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getCreatedby() {
        return createdby;
    }

    public CutPlanEntry createdby(String createdby) {
        this.createdby = createdby;
        return this;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Instant getCreateddate() {
        return createddate;
    }

    public CutPlanEntry createddate(Instant createddate) {
        this.createddate = createddate;
        return this;
    }

    public void setCreateddate(Instant createddate) {
        this.createddate = createddate;
    }

    public String getLastupdatedby() {
        return lastupdatedby;
    }

    public CutPlanEntry lastupdatedby(String lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
        return this;
    }

    public void setLastupdatedby(String lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
    }

    public Instant getLastupdateddate() {
        return lastupdateddate;
    }

    public CutPlanEntry lastupdateddate(Instant lastupdateddate) {
        this.lastupdateddate = lastupdateddate;
        return this;
    }

    public void setLastupdateddate(Instant lastupdateddate) {
        this.lastupdateddate = lastupdateddate;
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

    public MarkerMasterEntry getMarkerMasterEntry() {
        return markerMasterEntry;
    }

    public CutPlanEntry markerMasterEntry(MarkerMasterEntry markerMasterEntry) {
        this.markerMasterEntry = markerMasterEntry;
        return this;
    }

    public void setMarkerMasterEntry(MarkerMasterEntry markerMasterEntry) {
        this.markerMasterEntry = markerMasterEntry;
    }

    public Long getActualNoPlies() {
        return actualNoPlies;
    }

    public void setActualNoPlies(Long actualNoPlies) {
        this.actualNoPlies = actualNoPlies;
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

    public Long getNoCutters() {
        return noCutters;
    }

    public void setNoCutters(Long noCutters) {
        this.noCutters = noCutters;
    }

    public Double getTotalHour() {
        return totalHour;
    }

    public void setTotalHour(Double totalHour) {
        this.totalHour = totalHour;
    }

    public Instant getProgressEntryDate() {
        return progressEntryDate;
    }

    public void setProgressEntryDate(Instant progressEntryDate) {
        this.progressEntryDate = progressEntryDate;
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
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CutPlanEntry)) {
            return false;
        }
        return id != null && id.equals(((CutPlanEntry) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CutPlanEntry{" +
            "id=" + getId() +
            ", porductionCounterCode='" + getPorductionCounterCode() + "'" +
            ", productionCode='" + getProductionCode() + "'" +
            ", style='" + getStyle() + "'" +
            ", color='" + getColor() + "'" +
            ", orderQty=" + getOrderQty() +
            ", tolerance=" + getTolerance() +
            ", netOrderQty=" + getNetOrderQty() +
            ", itemtypecode='" + getItemtypecode() + "'" +
            ", subcode01='" + getSubcode01() + "'" +
            ", subcode02='" + getSubcode02() + "'" +
            ", subcode03='" + getSubcode03() + "'" +
            ", subcode04='" + getSubcode04() + "'" +
            ", subcode05='" + getSubcode05() + "'" +
            ", subcode06='" + getSubcode06() + "'" +
            ", subcode07='" + getSubcode07() + "'" +
            ", subcode08='" + getSubcode08() + "'" +
            ", subcode09='" + getSubcode09() + "'" +
            ", subcode10='" + getSubcode10() + "'" +
            ", summerizedDescription='" + getSummerizedDescription() + "'" +
            ", fabricRequired=" + getFabricRequired() +
            ", noPlies=" + getNoPlies() +
            ", noMarkers=" + getNoMarkers() +
            ", markerLength=" + getMarkerLength() +
            ", LotNo='" + getLotNo() + "'" +
            ", noRolls=" + getNoRolls() +
            ", endBits=" + getEndBits() +
            ", status='" + getStatus() + "'" +
            ", createdby='" + getCreatedby() + "'" +
            ", createddate='" + getCreateddate() + "'" +
            ", lastupdatedby='" + getLastupdatedby() + "'" +
            ", lastupdateddate='" + getLastupdateddate() + "'" +
            "}";
    }
}
