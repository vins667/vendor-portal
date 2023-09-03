package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "logicalwarehouse")
public class Logicalwarehouse {
    @EmbeddedId
    private LogicalwarehouseId id;
    private String alloweddivisions;
    private String physicalwarehousecompanycode;
    private String physicalwarehousecode;
    private String longdescription;
    private String shortdescription;
    private String searchdescription;
    private Integer goodshandlingleadtime;
    private Integer pmmainorsubstore;
    private String plantcode;
    private String addressline1;
    private String addressline2;
    private String addressline3;
    private String addressline4;
    private String addressline5;
    private String postalcode;
    private String town;
    private String district;
    private String addressphonenumber;
    private String addressfaxnumber;
    private Date initialmovementdate;
    private Date finalmovementdate;
    private Short reordertransfer;
    private Short lotcontrolled;
    private Short containercontrolled;
    private Short containerelementcontrolled;
    private Short elementcontrolled;
    private Short toolelementcontrolled;
    private Short qualitycontrolled;
    private Short projectcontrolled;
    private Short statisticalgroupcontrolled;
    private Short customercontrolled;
    private Short suppliercontrolled;
    private Short secondaryqtynotcontrolled;
    private Short packagingqtynotcontrolled;
    private String warehouseaccountinggroupcode;
    private Timestamp creationdatetime;
    private String creationuser;
    private Timestamp lastupdatedatetime;
    private String lastupdateuser;
    private Long absuniqueid;

    public LogicalwarehouseId getId() {
        return id;
    }

    public void setId(LogicalwarehouseId id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ALLOWEDDIVISIONS", nullable = true, length = 90)
    public String getAlloweddivisions() {
        return alloweddivisions;
    }

    public void setAlloweddivisions(String alloweddivisions) {
        this.alloweddivisions = alloweddivisions;
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
    @Column(name = "GOODSHANDLINGLEADTIME", nullable = false)
    public Integer getGoodshandlingleadtime() {
        return goodshandlingleadtime;
    }

    public void setGoodshandlingleadtime(Integer goodshandlingleadtime) {
        this.goodshandlingleadtime = goodshandlingleadtime;
    }

    @Basic
    @Column(name = "PMMAINORSUBSTORE", nullable = false)
    public Integer getPmmainorsubstore() {
        return pmmainorsubstore;
    }

    public void setPmmainorsubstore(Integer pmmainorsubstore) {
        this.pmmainorsubstore = pmmainorsubstore;
    }

    @Basic
    @Column(name = "PLANTCODE", nullable = true, length = 8)
    public String getPlantcode() {
        return plantcode;
    }

    public void setPlantcode(String plantcode) {
        this.plantcode = plantcode;
    }

    @Basic
    @Column(name = "ADDRESSLINE1", nullable = true, length = 100)
    public String getAddressline1() {
        return addressline1;
    }

    public void setAddressline1(String addressline1) {
        this.addressline1 = addressline1;
    }

    @Basic
    @Column(name = "ADDRESSLINE2", nullable = true, length = 100)
    public String getAddressline2() {
        return addressline2;
    }

    public void setAddressline2(String addressline2) {
        this.addressline2 = addressline2;
    }

    @Basic
    @Column(name = "ADDRESSLINE3", nullable = true, length = 100)
    public String getAddressline3() {
        return addressline3;
    }

    public void setAddressline3(String addressline3) {
        this.addressline3 = addressline3;
    }

    @Basic
    @Column(name = "ADDRESSLINE4", nullable = true, length = 100)
    public String getAddressline4() {
        return addressline4;
    }

    public void setAddressline4(String addressline4) {
        this.addressline4 = addressline4;
    }

    @Basic
    @Column(name = "ADDRESSLINE5", nullable = true, length = 100)
    public String getAddressline5() {
        return addressline5;
    }

    public void setAddressline5(String addressline5) {
        this.addressline5 = addressline5;
    }

    @Basic
    @Column(name = "POSTALCODE", nullable = true, length = 20)
    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    @Basic
    @Column(name = "TOWN", nullable = true, length = 100)
    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @Basic
    @Column(name = "DISTRICT", nullable = true, length = 100)
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Basic
    @Column(name = "ADDRESSPHONENUMBER", nullable = true, length = 40)
    public String getAddressphonenumber() {
        return addressphonenumber;
    }

    public void setAddressphonenumber(String addressphonenumber) {
        this.addressphonenumber = addressphonenumber;
    }

    @Basic
    @Column(name = "ADDRESSFAXNUMBER", nullable = true, length = 40)
    public String getAddressfaxnumber() {
        return addressfaxnumber;
    }

    public void setAddressfaxnumber(String addressfaxnumber) {
        this.addressfaxnumber = addressfaxnumber;
    }

    @Basic
    @Column(name = "INITIALMOVEMENTDATE", nullable = false)
    public Date getInitialmovementdate() {
        return initialmovementdate;
    }

    public void setInitialmovementdate(Date initialmovementdate) {
        this.initialmovementdate = initialmovementdate;
    }

    @Basic
    @Column(name = "FINALMOVEMENTDATE", nullable = false)
    public Date getFinalmovementdate() {
        return finalmovementdate;
    }

    public void setFinalmovementdate(Date finalmovementdate) {
        this.finalmovementdate = finalmovementdate;
    }

    @Basic
    @Column(name = "REORDERTRANSFER", nullable = false)
    public Short getReordertransfer() {
        return reordertransfer;
    }

    public void setReordertransfer(Short reordertransfer) {
        this.reordertransfer = reordertransfer;
    }

    @Basic
    @Column(name = "LOTCONTROLLED", nullable = false)
    public Short getLotcontrolled() {
        return lotcontrolled;
    }

    public void setLotcontrolled(Short lotcontrolled) {
        this.lotcontrolled = lotcontrolled;
    }

    @Basic
    @Column(name = "CONTAINERCONTROLLED", nullable = false)
    public Short getContainercontrolled() {
        return containercontrolled;
    }

    public void setContainercontrolled(Short containercontrolled) {
        this.containercontrolled = containercontrolled;
    }

    @Basic
    @Column(name = "CONTAINERELEMENTCONTROLLED", nullable = false)
    public Short getContainerelementcontrolled() {
        return containerelementcontrolled;
    }

    public void setContainerelementcontrolled(Short containerelementcontrolled) {
        this.containerelementcontrolled = containerelementcontrolled;
    }

    @Basic
    @Column(name = "ELEMENTCONTROLLED", nullable = false)
    public Short getElementcontrolled() {
        return elementcontrolled;
    }

    public void setElementcontrolled(Short elementcontrolled) {
        this.elementcontrolled = elementcontrolled;
    }

    @Basic
    @Column(name = "TOOLELEMENTCONTROLLED", nullable = false)
    public Short getToolelementcontrolled() {
        return toolelementcontrolled;
    }

    public void setToolelementcontrolled(Short toolelementcontrolled) {
        this.toolelementcontrolled = toolelementcontrolled;
    }

    @Basic
    @Column(name = "QUALITYCONTROLLED", nullable = false)
    public Short getQualitycontrolled() {
        return qualitycontrolled;
    }

    public void setQualitycontrolled(Short qualitycontrolled) {
        this.qualitycontrolled = qualitycontrolled;
    }

    @Basic
    @Column(name = "PROJECTCONTROLLED", nullable = false)
    public Short getProjectcontrolled() {
        return projectcontrolled;
    }

    public void setProjectcontrolled(Short projectcontrolled) {
        this.projectcontrolled = projectcontrolled;
    }

    @Basic
    @Column(name = "STATISTICALGROUPCONTROLLED", nullable = false)
    public Short getStatisticalgroupcontrolled() {
        return statisticalgroupcontrolled;
    }

    public void setStatisticalgroupcontrolled(Short statisticalgroupcontrolled) {
        this.statisticalgroupcontrolled = statisticalgroupcontrolled;
    }

    @Basic
    @Column(name = "CUSTOMERCONTROLLED", nullable = false)
    public Short getCustomercontrolled() {
        return customercontrolled;
    }

    public void setCustomercontrolled(Short customercontrolled) {
        this.customercontrolled = customercontrolled;
    }

    @Basic
    @Column(name = "SUPPLIERCONTROLLED", nullable = false)
    public Short getSuppliercontrolled() {
        return suppliercontrolled;
    }

    public void setSuppliercontrolled(Short suppliercontrolled) {
        this.suppliercontrolled = suppliercontrolled;
    }

    @Basic
    @Column(name = "SECONDARYQTYNOTCONTROLLED", nullable = false)
    public Short getSecondaryqtynotcontrolled() {
        return secondaryqtynotcontrolled;
    }

    public void setSecondaryqtynotcontrolled(Short secondaryqtynotcontrolled) {
        this.secondaryqtynotcontrolled = secondaryqtynotcontrolled;
    }

    @Basic
    @Column(name = "PACKAGINGQTYNOTCONTROLLED", nullable = false)
    public Short getPackagingqtynotcontrolled() {
        return packagingqtynotcontrolled;
    }

    public void setPackagingqtynotcontrolled(Short packagingqtynotcontrolled) {
        this.packagingqtynotcontrolled = packagingqtynotcontrolled;
    }

    @Basic
    @Column(name = "WAREHOUSEACCOUNTINGGROUPCODE")
    public String getWarehouseaccountinggroupcode() {
        return warehouseaccountinggroupcode;
    }

    public void setWarehouseaccountinggroupcode(String warehouseaccountinggroupcode) {
        this.warehouseaccountinggroupcode = warehouseaccountinggroupcode;
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
        Logicalwarehouse that = (Logicalwarehouse) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(alloweddivisions, that.alloweddivisions) &&
            Objects.equals(physicalwarehousecompanycode, that.physicalwarehousecompanycode) &&
            Objects.equals(physicalwarehousecode, that.physicalwarehousecode) &&
            Objects.equals(longdescription, that.longdescription) &&
            Objects.equals(shortdescription, that.shortdescription) &&
            Objects.equals(searchdescription, that.searchdescription) &&
            Objects.equals(goodshandlingleadtime, that.goodshandlingleadtime) &&
            Objects.equals(pmmainorsubstore, that.pmmainorsubstore) &&
            Objects.equals(addressline1, that.addressline1) &&
            Objects.equals(addressline2, that.addressline2) &&
            Objects.equals(addressline3, that.addressline3) &&
            Objects.equals(addressline4, that.addressline4) &&
            Objects.equals(addressline5, that.addressline5) &&
            Objects.equals(postalcode, that.postalcode) &&
            Objects.equals(town, that.town) &&
            Objects.equals(district, that.district) &&
            Objects.equals(addressphonenumber, that.addressphonenumber) &&
            Objects.equals(addressfaxnumber, that.addressfaxnumber) &&
            Objects.equals(initialmovementdate, that.initialmovementdate) &&
            Objects.equals(finalmovementdate, that.finalmovementdate) &&
            Objects.equals(reordertransfer, that.reordertransfer) &&
            Objects.equals(lotcontrolled, that.lotcontrolled) &&
            Objects.equals(containercontrolled, that.containercontrolled) &&
            Objects.equals(containerelementcontrolled, that.containerelementcontrolled) &&
            Objects.equals(elementcontrolled, that.elementcontrolled) &&
            Objects.equals(toolelementcontrolled, that.toolelementcontrolled) &&
            Objects.equals(qualitycontrolled, that.qualitycontrolled) &&
            Objects.equals(projectcontrolled, that.projectcontrolled) &&
            Objects.equals(statisticalgroupcontrolled, that.statisticalgroupcontrolled) &&
            Objects.equals(customercontrolled, that.customercontrolled) &&
            Objects.equals(suppliercontrolled, that.suppliercontrolled) &&
            Objects.equals(secondaryqtynotcontrolled, that.secondaryqtynotcontrolled) &&
            Objects.equals(packagingqtynotcontrolled, that.packagingqtynotcontrolled) &&
            Objects.equals(creationdatetime, that.creationdatetime) &&
            Objects.equals(creationuser, that.creationuser) &&
            Objects.equals(lastupdatedatetime, that.lastupdatedatetime) &&
            Objects.equals(lastupdateuser, that.lastupdateuser) &&
            Objects.equals(absuniqueid, that.absuniqueid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, alloweddivisions, physicalwarehousecompanycode, physicalwarehousecode, longdescription, shortdescription, searchdescription, goodshandlingleadtime, pmmainorsubstore, addressline1, addressline2, addressline3, addressline4, addressline5, postalcode, town, district, addressphonenumber, addressfaxnumber, initialmovementdate, finalmovementdate, reordertransfer, lotcontrolled, containercontrolled, containerelementcontrolled, elementcontrolled, toolelementcontrolled, qualitycontrolled, projectcontrolled, statisticalgroupcontrolled, customercontrolled, suppliercontrolled, secondaryqtynotcontrolled, packagingqtynotcontrolled, creationdatetime, creationuser, lastupdatedatetime, lastupdateuser, absuniqueid);
    }
}
