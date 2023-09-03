package io.vamani.application.model;

import java.io.Serializable;
import java.time.Instant;

public class StitchLineIssueDetailsBean implements Serializable {
    private Long id;
    private String productionCode;
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
    private String logicalwarehousecode;
    private String physicalwarehousecode;
    private String whslocationwarehousezonecode;
    private String warehouselocationcode;
    private Long qualitylevelcode;
    private String lotcode;
    private String containeritemtypecode;
    private String containersubcode01;
    private String containerelementcode;
    private String elementssubcodekey;
    private String elementscode;
    private String customertype;
    private String customercode;
    private String suppliertype;
    private String suppliercode;
    private String projectcode;
    private String baseprimaryunitcode;
    private Double baseprimaryquantityunit;
    private String basesecondaryunitcode;
    private Double basesecondaryquantityunit;
    private String packagingcode;
    private Double packagingquantityunit;
    private String bundleCode;
    private String startPiece;
    private String endPiece;
    private Long stocktransactionid;
    private String createdby;
    private Instant createddate;
    private String lastupdatedby;
    private Instant lastupdateddate;
    private Long cutPlanBundleId;
    private Double primaryquantity;
    private String primaryuomcode;
    private Double secondaryquantity;
    private String secondaryuomcode;
    private Long detailId;
    private String line;
    private String lineDesc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getProductionCode() {
        return productionCode;
    }
    public void setProductionCode(String productionCode) {
        this.productionCode = productionCode;
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

    public String getLogicalwarehousecode() {
        return logicalwarehousecode;
    }

    public void setLogicalwarehousecode(String logicalwarehousecode) {
        this.logicalwarehousecode = logicalwarehousecode;
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

    public Long getQualitylevelcode() {
        return qualitylevelcode;
    }

    public void setQualitylevelcode(Long qualitylevelcode) {
        this.qualitylevelcode = qualitylevelcode;
    }

    public String getLotcode() {
        return lotcode;
    }

    public void setLotcode(String lotcode) {
        this.lotcode = lotcode;
    }

    public String getContaineritemtypecode() {
        return containeritemtypecode;
    }

    public void setContaineritemtypecode(String containeritemtypecode) {
        this.containeritemtypecode = containeritemtypecode;
    }

    public String getContainersubcode01() {
        return containersubcode01;
    }

    public void setContainersubcode01(String containersubcode01) {
        this.containersubcode01 = containersubcode01;
    }

    public String getContainerelementcode() {
        return containerelementcode;
    }

    public void setContainerelementcode(String containerelementcode) {
        this.containerelementcode = containerelementcode;
    }

    public String getElementssubcodekey() {
        return elementssubcodekey;
    }

    public void setElementssubcodekey(String elementssubcodekey) {
        this.elementssubcodekey = elementssubcodekey;
    }

    public String getElementscode() {
        return elementscode;
    }

    public void setElementscode(String elementscode) {
        this.elementscode = elementscode;
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

    public String getSuppliertype() {
        return suppliertype;
    }

    public void setSuppliertype(String suppliertype) {
        this.suppliertype = suppliertype;
    }

    public String getSuppliercode() {
        return suppliercode;
    }

    public void setSuppliercode(String suppliercode) {
        this.suppliercode = suppliercode;
    }

    public String getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    public String getBaseprimaryunitcode() {
        return baseprimaryunitcode;
    }

    public void setBaseprimaryunitcode(String baseprimaryunitcode) {
        this.baseprimaryunitcode = baseprimaryunitcode;
    }

    public Double getBaseprimaryquantityunit() {
        return baseprimaryquantityunit;
    }

    public void setBaseprimaryquantityunit(Double baseprimaryquantityunit) {
        this.baseprimaryquantityunit = baseprimaryquantityunit;
    }

    public String getBasesecondaryunitcode() {
        return basesecondaryunitcode;
    }

    public void setBasesecondaryunitcode(String basesecondaryunitcode) {
        this.basesecondaryunitcode = basesecondaryunitcode;
    }

    public Double getBasesecondaryquantityunit() {
        return basesecondaryquantityunit;
    }

    public void setBasesecondaryquantityunit(Double basesecondaryquantityunit) {
        this.basesecondaryquantityunit = basesecondaryquantityunit;
    }

    public String getPackagingcode() {
        return packagingcode;
    }

    public void setPackagingcode(String packagingcode) {
        this.packagingcode = packagingcode;
    }

    public Double getPackagingquantityunit() {
        return packagingquantityunit;
    }

    public void setPackagingquantityunit(Double packagingquantityunit) {
        this.packagingquantityunit = packagingquantityunit;
    }

    public String getBundleCode() {
        return bundleCode;
    }

    public void setBundleCode(String bundleCode) {
        this.bundleCode = bundleCode;
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

    public Long getStocktransactionid() {
        return stocktransactionid;
    }

    public void setStocktransactionid(Long stocktransactionid) {
        this.stocktransactionid = stocktransactionid;
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

    public Long getCutPlanBundleId() {
        return cutPlanBundleId;
    }

    public void setCutPlanBundleId(Long cutPlanBundleId) {
        this.cutPlanBundleId = cutPlanBundleId;
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

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getLineDesc() {
        return lineDesc;
    }

    public void setLineDesc(String lineDesc) {
        this.lineDesc = lineDesc;
    }
}
