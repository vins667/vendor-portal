package io.vamani.application.db2.model;

import io.vamani.application.config.Constants;
import io.vamani.application.db2.domain.BalanceId;
import io.vamani.application.domain.CutPlanEntryDetails;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.Instant;

public class BalanceBean implements Serializable {
    private BalanceId id;
    private Long detailId;
    private String itemtypecompanycode;
    private String itemtypecode;
    private String logicalwarehousecompanycode;
    private String logicalwarehousecode;
    private String decosubcode01;
    private String decosubcode02;
    private String decosubcode03;
    private String decosubcode04;
    private String decosubcode05;
    private String decosubcode06;
    private String decosubcode07;
    private String decosubcode08;
    private String decosubcode09;
    private String decosubcode10;
    private String physicalwarehousecompanycode;
    private String physicalwarehousecode;
    private String whslocationwarehousezonecode;
    private String warehouselocationcode;
    private BigInteger qualitylevelcode;
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
    private String statisticalgroupcode;
    private String stocktypecode;
    private String detailtype;
    private String baseprimaryunitcode;
    private BigDecimal baseprimaryquantityunit;
    private String basesecondaryunitcode;
    private BigDecimal basesecondaryquantityunit;
    private String packagingcode;
    private BigDecimal packagingquantityunit;
    private Timestamp creationdatetime;
    private String creationuser;
    private Timestamp lastupdatedatetime;
    private String lastupdateuser;
    private Long absuniqueid;
    private Long noPlies;
    private Double endBits;
    private Long splitNoPlies;
    private Double splitEndBits;
    private String splitFlag;
    private Long splitTransactionnumber;
    private Double actualRollQty;
    private Double actualEndBits;
    private Long actualNoPlies;
    private Boolean allowPlies;
    private String issuedBy;
    private Instant issuedDate;

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public BalanceId getId() {
        return id;
    }

    public void setId(BalanceId id) {
        this.id = id;
    }

    public String getItemtypecompanycode() {
        return itemtypecompanycode;
    }

    public void setItemtypecompanycode(String itemtypecompanycode) {
        this.itemtypecompanycode = itemtypecompanycode;
    }

    public String getItemtypecode() {
        return itemtypecode;
    }

    public void setItemtypecode(String itemtypecode) {
        this.itemtypecode = itemtypecode;
    }

    public String getLogicalwarehousecompanycode() {
        return logicalwarehousecompanycode;
    }

    public void setLogicalwarehousecompanycode(String logicalwarehousecompanycode) {
        this.logicalwarehousecompanycode = logicalwarehousecompanycode;
    }

    public String getLogicalwarehousecode() {
        return logicalwarehousecode;
    }

    public void setLogicalwarehousecode(String logicalwarehousecode) {
        this.logicalwarehousecode = logicalwarehousecode;
    }

    public String getDecosubcode01() {
        return decosubcode01;
    }

    public void setDecosubcode01(String decosubcode01) {
        this.decosubcode01 = decosubcode01;
    }

    public String getDecosubcode02() {
        return decosubcode02;
    }

    public void setDecosubcode02(String decosubcode02) {
        this.decosubcode02 = decosubcode02;
    }

    public String getDecosubcode03() {
        return decosubcode03;
    }

    public void setDecosubcode03(String decosubcode03) {
        this.decosubcode03 = decosubcode03;
    }

    public String getDecosubcode04() {
        return decosubcode04;
    }

    public void setDecosubcode04(String decosubcode04) {
        this.decosubcode04 = decosubcode04;
    }

    public String getDecosubcode05() {
        return decosubcode05;
    }

    public void setDecosubcode05(String decosubcode05) {
        this.decosubcode05 = decosubcode05;
    }

    public String getDecosubcode06() {
        return decosubcode06;
    }

    public void setDecosubcode06(String decosubcode06) {
        this.decosubcode06 = decosubcode06;
    }

    public String getDecosubcode07() {
        return decosubcode07;
    }

    public void setDecosubcode07(String decosubcode07) {
        this.decosubcode07 = decosubcode07;
    }

    public String getDecosubcode08() {
        return decosubcode08;
    }

    public void setDecosubcode08(String decosubcode08) {
        this.decosubcode08 = decosubcode08;
    }

    public String getDecosubcode09() {
        return decosubcode09;
    }

    public void setDecosubcode09(String decosubcode09) {
        this.decosubcode09 = decosubcode09;
    }

    public String getDecosubcode10() {
        return decosubcode10;
    }

    public void setDecosubcode10(String decosubcode10) {
        this.decosubcode10 = decosubcode10;
    }

    public String getPhysicalwarehousecompanycode() {
        return physicalwarehousecompanycode;
    }

    public void setPhysicalwarehousecompanycode(String physicalwarehousecompanycode) {
        this.physicalwarehousecompanycode = physicalwarehousecompanycode;
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

    public BigInteger getQualitylevelcode() {
        return qualitylevelcode;
    }

    public void setQualitylevelcode(BigInteger qualitylevelcode) {
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

    public String getStatisticalgroupcode() {
        return statisticalgroupcode;
    }

    public void setStatisticalgroupcode(String statisticalgroupcode) {
        this.statisticalgroupcode = statisticalgroupcode;
    }

    public String getStocktypecode() {
        return stocktypecode;
    }

    public void setStocktypecode(String stocktypecode) {
        this.stocktypecode = stocktypecode;
    }

    public String getDetailtype() {
        return detailtype;
    }

    public void setDetailtype(String detailtype) {
        this.detailtype = detailtype;
    }

    public String getBaseprimaryunitcode() {
        return baseprimaryunitcode;
    }

    public void setBaseprimaryunitcode(String baseprimaryunitcode) {
        this.baseprimaryunitcode = baseprimaryunitcode;
    }

    public BigDecimal getBaseprimaryquantityunit() {
        return baseprimaryquantityunit;
    }

    public void setBaseprimaryquantityunit(BigDecimal baseprimaryquantityunit) {
        this.baseprimaryquantityunit = baseprimaryquantityunit;
    }

    public String getBasesecondaryunitcode() {
        return basesecondaryunitcode;
    }

    public void setBasesecondaryunitcode(String basesecondaryunitcode) {
        this.basesecondaryunitcode = basesecondaryunitcode;
    }

    public BigDecimal getBasesecondaryquantityunit() {
        return basesecondaryquantityunit;
    }

    public void setBasesecondaryquantityunit(BigDecimal basesecondaryquantityunit) {
        this.basesecondaryquantityunit = basesecondaryquantityunit;
    }

    public String getPackagingcode() {
        return packagingcode;
    }

    public void setPackagingcode(String packagingcode) {
        this.packagingcode = packagingcode;
    }

    public BigDecimal getPackagingquantityunit() {
        return packagingquantityunit;
    }

    public void setPackagingquantityunit(BigDecimal packagingquantityunit) {
        this.packagingquantityunit = packagingquantityunit;
    }

    public Timestamp getCreationdatetime() {
        return creationdatetime;
    }

    public void setCreationdatetime(Timestamp creationdatetime) {
        this.creationdatetime = creationdatetime;
    }

    public String getCreationuser() {
        return creationuser;
    }

    public void setCreationuser(String creationuser) {
        this.creationuser = creationuser;
    }

    public Timestamp getLastupdatedatetime() {
        return lastupdatedatetime;
    }

    public void setLastupdatedatetime(Timestamp lastupdatedatetime) {
        this.lastupdatedatetime = lastupdatedatetime;
    }

    public String getLastupdateuser() {
        return lastupdateuser;
    }

    public void setLastupdateuser(String lastupdateuser) {
        this.lastupdateuser = lastupdateuser;
    }

    public Long getAbsuniqueid() {
        return absuniqueid;
    }

    public void setAbsuniqueid(Long absuniqueid) {
        this.absuniqueid = absuniqueid;
    }

    public Long getNoPlies() {
        return noPlies;
    }

    public void setNoPlies(Long noPlies) {
        this.noPlies = noPlies;
    }

    public Double getEndBits() {
        return endBits;
    }

    public void setEndBits(Double endBits) {
        this.endBits = endBits;
    }

    public Long getSplitNoPlies() {
        return splitNoPlies;
    }

    public void setSplitNoPlies(Long splitNoPlies) {
        this.splitNoPlies = splitNoPlies;
    }

    public Double getSplitEndBits() {
        return splitEndBits;
    }

    public void setSplitEndBits(Double splitEndBits) {
        this.splitEndBits = splitEndBits;
    }

    public String getSplitFlag() {
        return splitFlag;
    }

    public void setSplitFlag(String splitFlag) {
        this.splitFlag = splitFlag;
    }

    public Long getSplitTransactionnumber() {
        return splitTransactionnumber;
    }

    public void setSplitTransactionnumber(Long splitTransactionnumber) {
        this.splitTransactionnumber = splitTransactionnumber;
    }

    public Boolean getAllowPlies() {
        return allowPlies;
    }

    public void setAllowPlies(Boolean allowPlies) {
        this.allowPlies = allowPlies;
    }

    public Double getActualRollQty() {
        return actualRollQty;
    }

    public void setActualRollQty(Double actualRollQty) {
        this.actualRollQty = actualRollQty;
    }

    public Double getActualEndBits() {
        return actualEndBits;
    }

    public void setActualEndBits(Double actualEndBits) {
        this.actualEndBits = actualEndBits;
    }

    public Long getActualNoPlies() {
        return actualNoPlies;
    }

    public void setActualNoPlies(Long actualNoPlies) {
        this.actualNoPlies = actualNoPlies;
    }

    public String getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    public Instant getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Instant issuedDate) {
        this.issuedDate = issuedDate;
    }

    public BalanceBean() {
    }

    public BalanceBean(CutPlanEntryDetails cutPlanEntryDetail) {
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        this.id = id;
        this.detailId = cutPlanEntryDetail.getId();
        this.itemtypecompanycode = Constants.COMPANY_CODE;
        this.itemtypecode = cutPlanEntryDetail.getItemtypecode();
        this.logicalwarehousecompanycode = Constants.COMPANY_CODE;
        this.logicalwarehousecode = cutPlanEntryDetail.getLogicalwarehousecode();
        this.decosubcode01 = cutPlanEntryDetail.getDecosubcode01();
        this.decosubcode02 = cutPlanEntryDetail.getDecosubcode02();
        this.decosubcode03 = cutPlanEntryDetail.getDecosubcode03();
        this.decosubcode04 = cutPlanEntryDetail.getDecosubcode04();
        this.decosubcode05 = cutPlanEntryDetail.getDecosubcode05();
        this.decosubcode06 = cutPlanEntryDetail.getDecosubcode06();
        this.decosubcode07 = cutPlanEntryDetail.getDecosubcode07();
        this.decosubcode08 = cutPlanEntryDetail.getDecosubcode08();
        this.decosubcode09 = cutPlanEntryDetail.getDecosubcode09();
        this.decosubcode10 = cutPlanEntryDetail.getDecosubcode10();
        this.physicalwarehousecompanycode = Constants.COMPANY_CODE;
        this.physicalwarehousecode = cutPlanEntryDetail.getPhysicalwarehousecode();
        this.whslocationwarehousezonecode = cutPlanEntryDetail.getWhslocationwarehousezonecode();
        this.warehouselocationcode = cutPlanEntryDetail.getWarehouselocationcode();
        this.qualitylevelcode = BigInteger.valueOf(cutPlanEntryDetail.getQualitylevelcode().longValue());
        this.lotcode = cutPlanEntryDetail.getLotcode();
        this.containeritemtypecode = cutPlanEntryDetail.getContaineritemtypecode();
        this.containersubcode01 = cutPlanEntryDetail.getContainersubcode01();
        this.containerelementcode = cutPlanEntryDetail.getContainerelementcode();
        this.elementssubcodekey = cutPlanEntryDetail.getElementssubcodekey();
        this.elementscode = cutPlanEntryDetail.getElementscode();
        this.customertype = cutPlanEntryDetail.getCustomertype();
        this.customercode = cutPlanEntryDetail.getCustomercode();
        this.suppliertype = cutPlanEntryDetail.getSuppliertype();
        this.suppliercode = cutPlanEntryDetail.getSuppliercode();
        this.projectcode = cutPlanEntryDetail.getProjectcode();
        this.baseprimaryunitcode = cutPlanEntryDetail.getBaseprimaryunitcode();
        this.baseprimaryquantityunit = new BigDecimal(cutPlanEntryDetail.getBaseprimaryquantityunit());
        this.basesecondaryunitcode = cutPlanEntryDetail.getBasesecondaryunitcode();
        this.basesecondaryquantityunit = new BigDecimal(cutPlanEntryDetail.getBasesecondaryquantityunit());
        this.packagingcode = cutPlanEntryDetail.getPackagingcode();
        this.packagingquantityunit = new BigDecimal(cutPlanEntryDetail.getPackagingquantityunit());
        this.noPlies = cutPlanEntryDetail.getNoPlies();
        this.endBits = cutPlanEntryDetail.getEndBits();
        this.splitNoPlies = cutPlanEntryDetail.getSplitNoPlies();
        this.splitEndBits = cutPlanEntryDetail.getSplitEndBits();
        this.splitFlag = cutPlanEntryDetail.getSplitFlag();
        this.splitTransactionnumber = cutPlanEntryDetail.getSplitTransactionnumber();
        if (cutPlanEntryDetail.getSplitFlag() != null) {
            this.actualEndBits = cutPlanEntryDetail.getActualEndBits() != null ? new Double(decimalFormat.format(cutPlanEntryDetail.getActualEndBits())) : new Double(decimalFormat.format(cutPlanEntryDetail.getSplitEndBits()));
            this.actualNoPlies = cutPlanEntryDetail.getActualNoPlies() != null ? cutPlanEntryDetail.getActualNoPlies() : cutPlanEntryDetail.getSplitNoPlies();
            this.actualRollQty = cutPlanEntryDetail.getActualRollQty() != null ? new Double(decimalFormat.format(cutPlanEntryDetail.getActualRollQty())) : new Double(decimalFormat.format(cutPlanEntryDetail.getBaseprimaryquantityunit() - cutPlanEntryDetail.getSplitEndBits()));
        } else {
            this.actualEndBits = cutPlanEntryDetail.getActualEndBits() != null ? new Double(decimalFormat.format(cutPlanEntryDetail.getActualEndBits())) : new Double(decimalFormat.format(cutPlanEntryDetail.getEndBits()));
            this.actualNoPlies = cutPlanEntryDetail.getActualNoPlies() != null ? cutPlanEntryDetail.getActualNoPlies() : cutPlanEntryDetail.getNoPlies();
            this.actualRollQty = cutPlanEntryDetail.getActualRollQty() != null ? new Double(decimalFormat.format(cutPlanEntryDetail.getActualRollQty())) : new Double(decimalFormat.format(cutPlanEntryDetail.getBaseprimaryquantityunit()));
        }
        this.issuedBy = cutPlanEntryDetail.getIssuedBy();
        this.issuedDate = cutPlanEntryDetail.getIssuedDate();
    }
}
