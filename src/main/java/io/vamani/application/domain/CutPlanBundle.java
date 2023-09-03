package io.vamani.application.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.Instant;

/**
 * A CutPlanBundle.
 */
@Entity
@Table(name = "cut_plan_bundle")
public class CutPlanBundle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="cutPlanBundleSeq", sequenceName="cut_plan_bundle_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="cutPlanBundleSeq")
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "production_code", length = 20, nullable = false)
    private String productionCode;

    @Column(name = "groupstepnumber")
    private Long groupstepnumber;

    @Size(max = 10)
    @Column(name = "demandcountercode", length = 10)
    private String demandcountercode;

    @Size(max = 20)
    @Column(name = "demandcode", length = 20)
    private String demandcode;

    @Size(max = 20)
    @Column(name = "plant_code", length = 20)
    private String plantCode;

    @Size(max = 20)
    @Column(name = "destination", length = 20)
    private String destination;

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

    @Size(max = 20)
    @Column(name = "logicalwarehousecode", length = 20)
    private String logicalwarehousecode;

    @Column(name = "primaryquantity")
    private Double primaryquantity;

    @Size(max = 20)
    @Column(name = "primaryuomcode", length = 20)
    private String primaryuomcode;

    @Column(name = "secondaryquantity")
    private Double secondaryquantity;

    @Size(max = 20)
    @Column(name = "secondaryuomcode", length = 20)
    private String secondaryuomcode;

    @Size(max = 20)
    @Column(name = "physicalwarehousecode", length = 20)
    private String physicalwarehousecode;

    @Size(max = 20)
    @Column(name = "whslocationwarehousezonecode", length = 20)
    private String whslocationwarehousezonecode;

    @Size(max = 20)
    @Column(name = "warehouselocationcode", length = 20)
    private String warehouselocationcode;

    @Size(max = 20)
    @Column(name = "lotcode", length = 20)
    private String lotcode;

    @Size(max = 20)
    @Column(name = "projectcode", length = 20)
    private String projectcode;

    @Size(max = 20)
    @Column(name = "bundle_code", length = 20)
    private String bundleCode;

    @Size(max = 1)
    @Column(name = "print_flag", length = 1)
    private String printFlag;

    @Size(max = 1)
    @Column(name = "print_piece_flag", length = 1)
    private String printPieceFlag;

    @Column(name = "stocktransactionid")
    private Long stocktransactionid;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductionCode() {
        return productionCode;
    }

    public CutPlanBundle productionCode(String productionCode) {
        this.productionCode = productionCode != null ? productionCode.trim() : productionCode;
        return this;
    }

    public void setProductionCode(String productionCode) {
        this.productionCode = productionCode != null ? productionCode.trim() : productionCode;
    }

    public Long getGroupstepnumber() {
        return groupstepnumber;
    }

    public CutPlanBundle groupstepnumber(Long groupstepnumber) {
        this.groupstepnumber = groupstepnumber;
        return this;
    }

    public void setGroupstepnumber(Long groupstepnumber) {
        this.groupstepnumber = groupstepnumber;
    }

    public String getDemandcountercode() {
        return demandcountercode;
    }

    public CutPlanBundle demandcountercode(String demandcountercode) {
        this.demandcountercode = demandcountercode;
        return this;
    }

    public void setDemandcountercode(String demandcountercode) {
        this.demandcountercode = demandcountercode;
    }

    public String getDemandcode() {
        return demandcode;
    }

    public CutPlanBundle demandcode(String demandcode) {
        this.demandcode = demandcode;
        return this;
    }

    public void setDemandcode(String demandcode) {
        this.demandcode = demandcode;
    }

    public String getItemtypecode() {
        return itemtypecode;
    }

    public CutPlanBundle itemtypecode(String itemtypecode) {
        this.itemtypecode = itemtypecode;
        return this;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setItemtypecode(String itemtypecode) {
        this.itemtypecode = itemtypecode;
    }

    public String getSubcode01() {
        return subcode01;
    }

    public CutPlanBundle subcode01(String subcode01) {
        this.subcode01 = subcode01 != null ? subcode01.trim() : subcode01;
        return this;
    }

    public void setSubcode01(String subcode01) {
        this.subcode01 = subcode01 != null ? subcode01.trim() : subcode01;
    }

    public String getSubcode02() {
        return subcode02;
    }

    public CutPlanBundle subcode02(String subcode02) {
        this.subcode02 = subcode02 != null ? subcode02.trim() : subcode02;
        return this;
    }

    public void setSubcode02(String subcode02) {
        this.subcode02 = subcode02 != null ? subcode02.trim() : subcode02;
    }

    public String getSubcode03() {
        return subcode03;
    }

    public CutPlanBundle subcode03(String subcode03) {
        this.subcode03 = subcode03 != null ? subcode03.trim() : subcode03;
        return this;
    }

    public void setSubcode03(String subcode03) {
        this.subcode03 = subcode03 != null ? subcode03.trim() : subcode03;
    }

    public String getSubcode04() {
        return subcode04;
    }

    public CutPlanBundle subcode04(String subcode04) {
        this.subcode04 = subcode04 != null ? subcode04.trim() : subcode04;
        return this;
    }

    public void setSubcode04(String subcode04) {
        this.subcode04 = subcode04 != null ? subcode04.trim() : subcode04;
    }

    public String getSubcode05() {
        return subcode05;
    }

    public CutPlanBundle subcode05(String subcode05) {
        this.subcode05 = subcode05 != null ? subcode05.trim() : subcode05;
        return this;
    }

    public void setSubcode05(String subcode05) {
        this.subcode05 = subcode05 != null ? subcode05.trim() : subcode05;
    }

    public String getSubcode06() {
        return subcode06;
    }

    public CutPlanBundle subcode06(String subcode06) {
        this.subcode06 = subcode06 != null ? subcode06.trim() : subcode06;
        return this;
    }

    public void setSubcode06(String subcode06) {
        this.subcode06 = subcode06 != null ? subcode06.trim() : subcode06;
    }

    public String getSubcode07() {
        return subcode07;
    }

    public CutPlanBundle subcode07(String subcode07) {
        this.subcode07 = subcode07 != null ? subcode07.trim() : subcode07;
        return this;
    }

    public void setSubcode07(String subcode07) {
        this.subcode07 = subcode07 != null ? subcode07.trim() : subcode07;
    }

    public String getSubcode08() {
        return subcode08;
    }

    public CutPlanBundle subcode08(String subcode08) {
        this.subcode08 = subcode08 != null ? subcode08.trim() : subcode08;
        return this;
    }

    public void setSubcode08(String subcode08) {
        this.subcode08 = subcode08 != null ? subcode08.trim() : subcode08;
    }

    public String getSubcode09() {
        return subcode09;
    }

    public CutPlanBundle subcode09(String subcode09) {
        this.subcode09 = subcode09 != null ? subcode09.trim() : subcode09;
        return this;
    }

    public void setSubcode09(String subcode09) {
        this.subcode09 = subcode09 != null ? subcode09.trim() : subcode09;
    }

    public String getSubcode10() {
        return subcode10;
    }

    public CutPlanBundle subcode10(String subcode10) {
        this.subcode10 = subcode10 != null ? subcode10.trim() : subcode10;
        return this;
    }

    public void setSubcode10(String subcode10) {
        this.subcode10 = subcode10 != null ? subcode10.trim() : subcode10;
    }

    public String getLogicalwarehousecode() {
        return logicalwarehousecode;
    }

    public CutPlanBundle logicalwarehousecode(String logicalwarehousecode) {
        this.logicalwarehousecode = logicalwarehousecode;
        return this;
    }

    public void setLogicalwarehousecode(String logicalwarehousecode) {
        this.logicalwarehousecode = logicalwarehousecode;
    }

    public Double getPrimaryquantity() {
        return primaryquantity;
    }

    public CutPlanBundle primaryquantity(Double primaryquantity) {
        this.primaryquantity = primaryquantity;
        return this;
    }

    public void setPrimaryquantity(Double primaryquantity) {
        this.primaryquantity = primaryquantity;
    }

    public String getPrimaryuomcode() {
        return primaryuomcode;
    }

    public CutPlanBundle primaryuomcode(String primaryuomcode) {
        this.primaryuomcode = primaryuomcode;
        return this;
    }

    public void setPrimaryuomcode(String primaryuomcode) {
        this.primaryuomcode = primaryuomcode;
    }

    public Double getSecondaryquantity() {
        return secondaryquantity;
    }

    public CutPlanBundle secondaryquantity(Double secondaryquantity) {
        this.secondaryquantity = secondaryquantity;
        return this;
    }

    public void setSecondaryquantity(Double secondaryquantity) {
        this.secondaryquantity = secondaryquantity;
    }

    public String getSecondaryuomcode() {
        return secondaryuomcode;
    }

    public CutPlanBundle secondaryuomcode(String secondaryuomcode) {
        this.secondaryuomcode = secondaryuomcode;
        return this;
    }

    public void setSecondaryuomcode(String secondaryuomcode) {
        this.secondaryuomcode = secondaryuomcode;
    }

    public String getPhysicalwarehousecode() {
        return physicalwarehousecode;
    }

    public CutPlanBundle physicalwarehousecode(String physicalwarehousecode) {
        this.physicalwarehousecode = physicalwarehousecode;
        return this;
    }

    public void setPhysicalwarehousecode(String physicalwarehousecode) {
        this.physicalwarehousecode = physicalwarehousecode;
    }

    public String getWhslocationwarehousezonecode() {
        return whslocationwarehousezonecode;
    }

    public CutPlanBundle whslocationwarehousezonecode(String whslocationwarehousezonecode) {
        this.whslocationwarehousezonecode = whslocationwarehousezonecode;
        return this;
    }

    public void setWhslocationwarehousezonecode(String whslocationwarehousezonecode) {
        this.whslocationwarehousezonecode = whslocationwarehousezonecode;
    }

    public String getWarehouselocationcode() {
        return warehouselocationcode;
    }

    public CutPlanBundle warehouselocationcode(String warehouselocationcode) {
        this.warehouselocationcode = warehouselocationcode;
        return this;
    }

    public void setWarehouselocationcode(String warehouselocationcode) {
        this.warehouselocationcode = warehouselocationcode;
    }

    public String getLotcode() {
        return lotcode;
    }

    public CutPlanBundle lotcode(String lotcode) {
        this.lotcode = lotcode;
        return this;
    }

    public void setLotcode(String lotcode) {
        this.lotcode = lotcode;
    }

    public String getProjectcode() {
        return projectcode;
    }

    public CutPlanBundle projectcode(String projectcode) {
        this.projectcode = projectcode;
        return this;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    public String getBundleCode() {
        return bundleCode;
    }

    public CutPlanBundle bundleCode(String bundleCode) {
        this.bundleCode = bundleCode;
        return this;
    }

    public void setBundleCode(String bundleCode) {
        this.bundleCode = bundleCode;
    }

    public String getPrintFlag() {
        return printFlag;
    }

    public CutPlanBundle printFlag(String printFlag) {
        this.printFlag = printFlag;
        return this;
    }

    public void setPrintFlag(String printFlag) {
        this.printFlag = printFlag;
    }

    public String getPrintPieceFlag() {
        return printPieceFlag;
    }

    public CutPlanBundle printPieceFlag(String printPieceFlag) {
        this.printPieceFlag = printPieceFlag;
        return this;
    }

    public void setPrintPieceFlag(String printPieceFlag) {
        this.printPieceFlag = printPieceFlag;
    }

    public Long getStocktransactionid() {
        return stocktransactionid;
    }

    public CutPlanBundle stocktransactionid(Long stocktransactionid) {
        this.stocktransactionid = stocktransactionid;
        return this;
    }

    public void setStocktransactionid(Long stocktransactionid) {
        this.stocktransactionid = stocktransactionid;
    }

    public String getCreatedby() {
        return createdby;
    }

    public CutPlanBundle createdby(String createdby) {
        this.createdby = createdby;
        return this;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Instant getCreateddate() {
        return createddate;
    }

    public CutPlanBundle createddate(Instant createddate) {
        this.createddate = createddate;
        return this;
    }

    public void setCreateddate(Instant createddate) {
        this.createddate = createddate;
    }

    public String getLastupdatedby() {
        return lastupdatedby;
    }

    public CutPlanBundle lastupdatedby(String lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
        return this;
    }

    public void setLastupdatedby(String lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
    }

    public Instant getLastupdateddate() {
        return lastupdateddate;
    }

    public CutPlanBundle lastupdateddate(Instant lastupdateddate) {
        this.lastupdateddate = lastupdateddate;
        return this;
    }

    public void setLastupdateddate(Instant lastupdateddate) {
        this.lastupdateddate = lastupdateddate;
    }

    public String getPlantCode() {
        return plantCode;
    }

    public void setPlantCode(String plantCode) {
        this.plantCode = plantCode;
    }
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CutPlanBundle)) {
            return false;
        }
        return id != null && id.equals(((CutPlanBundle) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CutPlanBundle{" +
            "id=" + getId() +
            ", productionCode='" + getProductionCode() + "'" +
            ", groupstepnumber=" + getGroupstepnumber() +
            ", demandcountercode='" + getDemandcountercode() + "'" +
            ", demandcode='" + getDemandcode() + "'" +
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
            ", logicalwarehousecode='" + getLogicalwarehousecode() + "'" +
            ", primaryquantity=" + getPrimaryquantity() +
            ", primaryuomcode='" + getPrimaryuomcode() + "'" +
            ", secondaryquantity=" + getSecondaryquantity() +
            ", secondaryuomcode='" + getSecondaryuomcode() + "'" +
            ", physicalwarehousecode='" + getPhysicalwarehousecode() + "'" +
            ", whslocationwarehousezonecode='" + getWhslocationwarehousezonecode() + "'" +
            ", warehouselocationcode='" + getWarehouselocationcode() + "'" +
            ", lotcode='" + getLotcode() + "'" +
            ", projectcode='" + getProjectcode() + "'" +
            ", bundle_code='" + getBundleCode() + "'" +
            ", stocktransactionid=" + getStocktransactionid() +
            ", createdby='" + getCreatedby() + "'" +
            ", createddate='" + getCreateddate() + "'" +
            ", lastupdatedby='" + getLastupdatedby() + "'" +
            ", lastupdateddate='" + getLastupdateddate() + "'" +
            "}";
    }
}
