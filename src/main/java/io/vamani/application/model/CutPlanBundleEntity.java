package io.vamani.application.model;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

public class CutPlanBundleEntity implements Serializable {
    private Long id;

    private String productionCode;

    private Long groupstepnumber;

    private String demandcountercode;

    private String demandcode;

    private String plantCode;

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

    private String summerizeddescription;

    private String logicalwarehousecode;

    private Double primaryquantity;

    private String primaryuomcode;

    private Double secondaryquantity;

    private String secondaryuomcode;

    private String physicalwarehousecode;

    private String whslocationwarehousezonecode;

    private String warehouselocationcode;

    private String elementscode;

    private String lotcode;

    private String projectcode;

    private String bundleCode;

    private String printFlag;

    private String printPieceFlag;

    private Long stocktransactionid;

    private String startPiece;

    private String endPiece;

    private String createdby;

    private Instant createddate;

    private String lastupdatedby;

    private Instant lastupdateddate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductionCode() {
        return productionCode;
    }

    public void setProductionCode(String productionCode) {
        this.productionCode = productionCode;
    }

    public Long getGroupstepnumber() {
        return groupstepnumber;
    }

    public void setGroupstepnumber(Long groupstepnumber) {
        this.groupstepnumber = groupstepnumber;
    }

    public String getDemandcountercode() {
        return demandcountercode;
    }

    public void setDemandcountercode(String demandcountercode) {
        this.demandcountercode = demandcountercode;
    }

    public String getDemandcode() {
        return demandcode;
    }

    public void setDemandcode(String demandcode) {
        this.demandcode = demandcode;
    }

    public String getPlantCode() {
        return plantCode;
    }

    public void setPlantCode(String plantCode) {
        this.plantCode = plantCode;
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

    public String getSummerizeddescription() {
        return summerizeddescription;
    }

    public void setSummerizeddescription(String summerizeddescription) {
        this.summerizeddescription = summerizeddescription;
    }

    public String getLogicalwarehousecode() {
        return logicalwarehousecode;
    }

    public void setLogicalwarehousecode(String logicalwarehousecode) {
        this.logicalwarehousecode = logicalwarehousecode;
    }

    public Double getPrimaryquantity() {
        return primaryquantity;
    }

    public void setPrimaryquantity(Double primaryquantity) {
        this.primaryquantity = primaryquantity;
    }

    public String getPrimaryuomcode() {
        return primaryuomcode;
    }

    public void setPrimaryuomcode(String primaryuomcode) {
        this.primaryuomcode = primaryuomcode;
    }

    public Double getSecondaryquantity() {
        return secondaryquantity;
    }

    public void setSecondaryquantity(Double secondaryquantity) {
        this.secondaryquantity = secondaryquantity;
    }

    public String getSecondaryuomcode() {
        return secondaryuomcode;
    }

    public void setSecondaryuomcode(String secondaryuomcode) {
        this.secondaryuomcode = secondaryuomcode;
    }

    public String getPhysicalwarehousecode() {
        return physicalwarehousecode;
    }

    public void setPhysicalwarehousecode(String physicalwarehousecode) {
        this.physicalwarehousecode = physicalwarehousecode;
    }

    public String getWhslocationwarehousezonecode() {
        return whslocationwarehousezonecode;
    }

    public void setWhslocationwarehousezonecode(String whslocationwarehousezonecode) {
        this.whslocationwarehousezonecode = whslocationwarehousezonecode;
    }

    public String getWarehouselocationcode() {
        return warehouselocationcode;
    }

    public void setWarehouselocationcode(String warehouselocationcode) {
        this.warehouselocationcode = warehouselocationcode;
    }

    public String getLotcode() {
        return lotcode;
    }

    public void setLotcode(String lotcode) {
        this.lotcode = lotcode;
    }

    public String getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    public String getBundleCode() {
        return bundleCode;
    }

    public void setBundleCode(String bundleCode) {
        this.bundleCode = bundleCode;
    }

    public String getPrintFlag() {
        return printFlag;
    }

    public void setPrintFlag(String printFlag) {
        this.printFlag = printFlag;
    }

    public String getPrintPieceFlag() {
        return printPieceFlag;
    }

    public void setPrintPieceFlag(String printPieceFlag) {
        this.printPieceFlag = printPieceFlag;
    }

    public Long getStocktransactionid() {
        return stocktransactionid;
    }

    public void setStocktransactionid(Long stocktransactionid) {
        this.stocktransactionid = stocktransactionid;
    }

    public String getStartPiece() {
        return startPiece;
    }

    public void setStartPiece(String startPiece) {
        this.startPiece = startPiece;
    }

    public String getEndPiece() {
        return endPiece;
    }

    public void setEndPiece(String endPiece) {
        this.endPiece = endPiece;
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

    public String getElementscode() {
        return elementscode;
    }

    public void setElementscode(String elementscode) {
        this.elementscode = elementscode;
    }
}
