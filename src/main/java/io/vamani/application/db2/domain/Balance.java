package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "balance")
public class Balance {
    @EmbeddedId
    private BalanceId id;
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

    public BalanceId getId() {
        return id;
    }

    public void setId(BalanceId id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ITEMTYPECOMPANYCODE", nullable = true, length = 3)
    public String getItemtypecompanycode() {
        return itemtypecompanycode;
    }

    public void setItemtypecompanycode(String itemtypecompanycode) {
        this.itemtypecompanycode = itemtypecompanycode;
    }

    @Basic
    @Column(name = "ITEMTYPECODE", nullable = true, length = 3)
    public String getItemtypecode() {
        return itemtypecode;
    }

    public void setItemtypecode(String itemtypecode) {
        this.itemtypecode = itemtypecode;
    }

    @Basic
    @Column(name = "LOGICALWAREHOUSECOMPANYCODE", nullable = true, length = 3)
    public String getLogicalwarehousecompanycode() {
        return logicalwarehousecompanycode;
    }

    public void setLogicalwarehousecompanycode(String logicalwarehousecompanycode) {
        this.logicalwarehousecompanycode = logicalwarehousecompanycode;
    }

    @Basic
    @Column(name = "LOGICALWAREHOUSECODE", nullable = true, length = 8)
    public String getLogicalwarehousecode() {
        return logicalwarehousecode;
    }

    public void setLogicalwarehousecode(String logicalwarehousecode) {
        this.logicalwarehousecode = logicalwarehousecode;
    }

    @Basic
    @Column(name = "DECOSUBCODE01", nullable = true, length = 20)
    public String getDecosubcode01() {
        return decosubcode01;
    }

    public void setDecosubcode01(String decosubcode01) {
        this.decosubcode01 = decosubcode01;
    }

    @Basic
    @Column(name = "DECOSUBCODE02", nullable = true, length = 10)
    public String getDecosubcode02() {
        return decosubcode02;
    }

    public void setDecosubcode02(String decosubcode02) {
        this.decosubcode02 = decosubcode02;
    }

    @Basic
    @Column(name = "DECOSUBCODE03", nullable = true, length = 10)
    public String getDecosubcode03() {
        return decosubcode03;
    }

    public void setDecosubcode03(String decosubcode03) {
        this.decosubcode03 = decosubcode03;
    }

    @Basic
    @Column(name = "DECOSUBCODE04", nullable = true, length = 10)
    public String getDecosubcode04() {
        return decosubcode04;
    }

    public void setDecosubcode04(String decosubcode04) {
        this.decosubcode04 = decosubcode04;
    }

    @Basic
    @Column(name = "DECOSUBCODE05", nullable = true, length = 10)
    public String getDecosubcode05() {
        return decosubcode05;
    }

    public void setDecosubcode05(String decosubcode05) {
        this.decosubcode05 = decosubcode05;
    }

    @Basic
    @Column(name = "DECOSUBCODE06", nullable = true, length = 10)
    public String getDecosubcode06() {
        return decosubcode06;
    }

    public void setDecosubcode06(String decosubcode06) {
        this.decosubcode06 = decosubcode06;
    }

    @Basic
    @Column(name = "DECOSUBCODE07", nullable = true, length = 10)
    public String getDecosubcode07() {
        return decosubcode07;
    }

    public void setDecosubcode07(String decosubcode07) {
        this.decosubcode07 = decosubcode07;
    }

    @Basic
    @Column(name = "DECOSUBCODE08", nullable = true, length = 10)
    public String getDecosubcode08() {
        return decosubcode08;
    }

    public void setDecosubcode08(String decosubcode08) {
        this.decosubcode08 = decosubcode08;
    }

    @Basic
    @Column(name = "DECOSUBCODE09", nullable = true, length = 10)
    public String getDecosubcode09() {
        return decosubcode09;
    }

    public void setDecosubcode09(String decosubcode09) {
        this.decosubcode09 = decosubcode09;
    }

    @Basic
    @Column(name = "DECOSUBCODE10", nullable = true, length = 10)
    public String getDecosubcode10() {
        return decosubcode10;
    }

    public void setDecosubcode10(String decosubcode10) {
        this.decosubcode10 = decosubcode10;
    }

    @Basic
    @Column(name = "PHYSICALWAREHOUSECOMPANYCODE", nullable = true, length = 3)
    public String getPhysicalwarehousecompanycode() {
        return physicalwarehousecompanycode;
    }

    public void setPhysicalwarehousecompanycode(String physicalwarehousecompanycode) {
        this.physicalwarehousecompanycode = physicalwarehousecompanycode;
    }

    @Basic
    @Column(name = "PHYSICALWAREHOUSECODE", nullable = true, length = 8)
    public String getPhysicalwarehousecode() {
        return physicalwarehousecode;
    }

    public void setPhysicalwarehousecode(String physicalwarehousecode) {
        this.physicalwarehousecode = physicalwarehousecode;
    }

    @Basic
    @Column(name = "WHSLOCATIONWAREHOUSEZONECODE", nullable = true, length = 3)
    public String getWhslocationwarehousezonecode() {
        return whslocationwarehousezonecode;
    }

    public void setWhslocationwarehousezonecode(String whslocationwarehousezonecode) {
        this.whslocationwarehousezonecode = whslocationwarehousezonecode;
    }

    @Basic
    @Column(name = "WAREHOUSELOCATIONCODE", nullable = true, length = 10)
    public String getWarehouselocationcode() {
        return warehouselocationcode;
    }

    public void setWarehouselocationcode(String warehouselocationcode) {
        this.warehouselocationcode = warehouselocationcode;
    }

    @Basic
    @Column(name = "QUALITYLEVELCODE", nullable = true, precision = 0)
    public BigInteger getQualitylevelcode() {
        return qualitylevelcode;
    }

    public void setQualitylevelcode(BigInteger qualitylevelcode) {
        this.qualitylevelcode = qualitylevelcode;
    }

    @Basic
    @Column(name = "LOTCODE", nullable = true, length = 10)
    public String getLotcode() {
        return lotcode;
    }

    public void setLotcode(String lotcode) {
        this.lotcode = lotcode;
    }

    @Basic
    @Column(name = "CONTAINERITEMTYPECODE", nullable = true, length = 3)
    public String getContaineritemtypecode() {
        return containeritemtypecode;
    }

    public void setContaineritemtypecode(String containeritemtypecode) {
        this.containeritemtypecode = containeritemtypecode;
    }

    @Basic
    @Column(name = "CONTAINERSUBCODE01", nullable = true, length = 20)
    public String getContainersubcode01() {
        return containersubcode01;
    }

    public void setContainersubcode01(String containersubcode01) {
        this.containersubcode01 = containersubcode01;
    }

    @Basic
    @Column(name = "CONTAINERELEMENTCODE", nullable = true, length = 15)
    public String getContainerelementcode() {
        return containerelementcode;
    }

    public void setContainerelementcode(String containerelementcode) {
        this.containerelementcode = containerelementcode;
    }

    @Basic
    @Column(name = "ELEMENTSSUBCODEKEY", nullable = true, length = 20)
    public String getElementssubcodekey() {
        return elementssubcodekey;
    }

    public void setElementssubcodekey(String elementssubcodekey) {
        this.elementssubcodekey = elementssubcodekey;
    }

    @Basic
    @Column(name = "ELEMENTSCODE", nullable = true, length = 15)
    public String getElementscode() {
        return elementscode;
    }

    public void setElementscode(String elementscode) {
        this.elementscode = elementscode;
    }

    @Basic
    @Column(name = "CUSTOMERTYPE", nullable = true, length = 1)
    public String getCustomertype() {
        return customertype;
    }

    public void setCustomertype(String customertype) {
        this.customertype = customertype;
    }

    @Basic
    @Column(name = "CUSTOMERCODE", nullable = true, length = 8)
    public String getCustomercode() {
        return customercode;
    }

    public void setCustomercode(String customercode) {
        this.customercode = customercode;
    }

    @Basic
    @Column(name = "SUPPLIERTYPE", nullable = true, length = 1)
    public String getSuppliertype() {
        return suppliertype;
    }

    public void setSuppliertype(String suppliertype) {
        this.suppliertype = suppliertype;
    }

    @Basic
    @Column(name = "SUPPLIERCODE", nullable = true, length = 8)
    public String getSuppliercode() {
        return suppliercode;
    }

    public void setSuppliercode(String suppliercode) {
        this.suppliercode = suppliercode;
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
    @Column(name = "STATISTICALGROUPCODE", nullable = true, length = 6)
    public String getStatisticalgroupcode() {
        return statisticalgroupcode;
    }

    public void setStatisticalgroupcode(String statisticalgroupcode) {
        this.statisticalgroupcode = statisticalgroupcode;
    }

    @Basic
    @Column(name = "STOCKTYPECODE", nullable = true, length = 3)
    public String getStocktypecode() {
        return stocktypecode;
    }

    public void setStocktypecode(String stocktypecode) {
        this.stocktypecode = stocktypecode;
    }

    @Basic
    @Column(name = "DETAILTYPE", nullable = false, length = 2)
    public String getDetailtype() {
        return detailtype;
    }

    public void setDetailtype(String detailtype) {
        this.detailtype = detailtype;
    }

    @Basic
    @Column(name = "BASEPRIMARYUNITCODE", nullable = true, length = 3)
    public String getBaseprimaryunitcode() {
        return baseprimaryunitcode;
    }

    public void setBaseprimaryunitcode(String baseprimaryunitcode) {
        this.baseprimaryunitcode = baseprimaryunitcode;
    }

    @Basic
    @Column(name = "BASEPRIMARYQUANTITYUNIT", nullable = true, precision = 5)
    public BigDecimal getBaseprimaryquantityunit() {
        return baseprimaryquantityunit;
    }

    public void setBaseprimaryquantityunit(BigDecimal baseprimaryquantityunit) {
        this.baseprimaryquantityunit = baseprimaryquantityunit;
    }

    @Basic
    @Column(name = "BASESECONDARYUNITCODE", nullable = true, length = 3)
    public String getBasesecondaryunitcode() {
        return basesecondaryunitcode;
    }

    public void setBasesecondaryunitcode(String basesecondaryunitcode) {
        this.basesecondaryunitcode = basesecondaryunitcode;
    }

    @Basic
    @Column(name = "BASESECONDARYQUANTITYUNIT", nullable = true, precision = 5)
    public BigDecimal getBasesecondaryquantityunit() {
        return basesecondaryquantityunit;
    }

    public void setBasesecondaryquantityunit(BigDecimal basesecondaryquantityunit) {
        this.basesecondaryquantityunit = basesecondaryquantityunit;
    }

    @Basic
    @Column(name = "PACKAGINGCODE", nullable = true, length = 3)
    public String getPackagingcode() {
        return packagingcode;
    }

    public void setPackagingcode(String packagingcode) {
        this.packagingcode = packagingcode;
    }

    @Basic
    @Column(name = "PACKAGINGQUANTITYUNIT", nullable = true, precision = 5)
    public BigDecimal getPackagingquantityunit() {
        return packagingquantityunit;
    }

    public void setPackagingquantityunit(BigDecimal packagingquantityunit) {
        this.packagingquantityunit = packagingquantityunit;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Balance balance = (Balance) o;
        return Objects.equals(id, balance.id) &&
            Objects.equals(itemtypecompanycode, balance.itemtypecompanycode) &&
            Objects.equals(itemtypecode, balance.itemtypecode) &&
            Objects.equals(logicalwarehousecompanycode, balance.logicalwarehousecompanycode) &&
            Objects.equals(logicalwarehousecode, balance.logicalwarehousecode) &&
            Objects.equals(decosubcode01, balance.decosubcode01) &&
            Objects.equals(decosubcode02, balance.decosubcode02) &&
            Objects.equals(decosubcode03, balance.decosubcode03) &&
            Objects.equals(decosubcode04, balance.decosubcode04) &&
            Objects.equals(decosubcode05, balance.decosubcode05) &&
            Objects.equals(decosubcode06, balance.decosubcode06) &&
            Objects.equals(decosubcode07, balance.decosubcode07) &&
            Objects.equals(decosubcode08, balance.decosubcode08) &&
            Objects.equals(decosubcode09, balance.decosubcode09) &&
            Objects.equals(decosubcode10, balance.decosubcode10) &&
            Objects.equals(physicalwarehousecompanycode, balance.physicalwarehousecompanycode) &&
            Objects.equals(physicalwarehousecode, balance.physicalwarehousecode) &&
            Objects.equals(whslocationwarehousezonecode, balance.whslocationwarehousezonecode) &&
            Objects.equals(warehouselocationcode, balance.warehouselocationcode) &&
            Objects.equals(qualitylevelcode, balance.qualitylevelcode) &&
            Objects.equals(lotcode, balance.lotcode) &&
            Objects.equals(containeritemtypecode, balance.containeritemtypecode) &&
            Objects.equals(containersubcode01, balance.containersubcode01) &&
            Objects.equals(containerelementcode, balance.containerelementcode) &&
            Objects.equals(elementssubcodekey, balance.elementssubcodekey) &&
            Objects.equals(elementscode, balance.elementscode) &&
            Objects.equals(customertype, balance.customertype) &&
            Objects.equals(customercode, balance.customercode) &&
            Objects.equals(suppliertype, balance.suppliertype) &&
            Objects.equals(suppliercode, balance.suppliercode) &&
            Objects.equals(projectcode, balance.projectcode) &&
            Objects.equals(statisticalgroupcode, balance.statisticalgroupcode) &&
            Objects.equals(stocktypecode, balance.stocktypecode) &&
            Objects.equals(detailtype, balance.detailtype) &&
            Objects.equals(baseprimaryunitcode, balance.baseprimaryunitcode) &&
            Objects.equals(baseprimaryquantityunit, balance.baseprimaryquantityunit) &&
            Objects.equals(basesecondaryunitcode, balance.basesecondaryunitcode) &&
            Objects.equals(basesecondaryquantityunit, balance.basesecondaryquantityunit) &&
            Objects.equals(packagingcode, balance.packagingcode) &&
            Objects.equals(packagingquantityunit, balance.packagingquantityunit) &&
            Objects.equals(creationdatetime, balance.creationdatetime) &&
            Objects.equals(creationuser, balance.creationuser) &&
            Objects.equals(lastupdatedatetime, balance.lastupdatedatetime) &&
            Objects.equals(lastupdateuser, balance.lastupdateuser) &&
            Objects.equals(absuniqueid, balance.absuniqueid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemtypecompanycode, itemtypecode, logicalwarehousecompanycode, logicalwarehousecode, decosubcode01, decosubcode02, decosubcode03, decosubcode04, decosubcode05, decosubcode06, decosubcode07, decosubcode08, decosubcode09, decosubcode10, physicalwarehousecompanycode, physicalwarehousecode, whslocationwarehousezonecode, warehouselocationcode, qualitylevelcode, lotcode, containeritemtypecode, containersubcode01, containerelementcode, elementssubcodekey, elementscode, customertype, customercode, suppliertype, suppliercode, projectcode, statisticalgroupcode, stocktypecode, detailtype, baseprimaryunitcode, baseprimaryquantityunit, basesecondaryunitcode, basesecondaryquantityunit, packagingcode, packagingquantityunit, creationdatetime, creationuser, lastupdatedatetime, lastupdateuser, absuniqueid);
    }
}
