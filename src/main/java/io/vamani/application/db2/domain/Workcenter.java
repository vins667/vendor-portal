package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name ="workcenter")
public class Workcenter {
    @EmbeddedId
    private WorkcenterId id;
    private String alloweddivisions;
    private String longdescription;
    private String shortdescription;
    private String searchdescription;
    private String type;
    private Integer numberofmainresources;
    private BigDecimal queuetimeonentrance;
    private String queuetimeunit;
    private BigDecimal standardefficiency;
    private String standardefficiencyapply;
    private BigDecimal bathvolume;
    private BigDecimal temperaturelossovertime;
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
    private String plantcode;
    private Timestamp creationdatetime;
    private String creationuser;
    private Timestamp lastupdatedatetime;
    private String lastupdateuser;
    private Long absuniqueid;
    private Timestamp creationdatetimeutc;
    private Timestamp lastupdatedatetimeutc;
    private Short reclcsteptimefrompartprg;
    private String costcentercode;

    public WorkcenterId getId() {
        return id;
    }

    public void setId(WorkcenterId id) {
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
    @Column(name = "LONGDESCRIPTION", nullable = false, length = 200)
    public String getLongdescription() {
        return longdescription;
    }

    public void setLongdescription(String longdescription) {
        this.longdescription = longdescription;
    }

    @Basic
    @Column(name = "SHORTDESCRIPTION", nullable = true, length = 80)
    public String getShortdescription() {
        return shortdescription;
    }

    public void setShortdescription(String shortdescription) {
        this.shortdescription = shortdescription;
    }

    @Basic
    @Column(name = "SEARCHDESCRIPTION", nullable = true, length = 120)
    public String getSearchdescription() {
        return searchdescription;
    }

    public void setSearchdescription(String searchdescription) {
        this.searchdescription = searchdescription;
    }

    @Basic
    @Column(name = "TYPE", nullable = true, length = 2)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "NUMBEROFMAINRESOURCES", nullable = true, precision = 0)
    public Integer getNumberofmainresources() {
        return numberofmainresources;
    }

    public void setNumberofmainresources(Integer numberofmainresources) {
        this.numberofmainresources = numberofmainresources;
    }

    @Basic
    @Column(name = "QUEUETIMEONENTRANCE", nullable = true, precision = 5)
    public BigDecimal getQueuetimeonentrance() {
        return queuetimeonentrance;
    }

    public void setQueuetimeonentrance(BigDecimal queuetimeonentrance) {
        this.queuetimeonentrance = queuetimeonentrance;
    }

    @Basic
    @Column(name = "QUEUETIMEUNIT", nullable = true, length = 2)
    public String getQueuetimeunit() {
        return queuetimeunit;
    }

    public void setQueuetimeunit(String queuetimeunit) {
        this.queuetimeunit = queuetimeunit;
    }

    @Basic
    @Column(name = "STANDARDEFFICIENCY", nullable = true, precision = 2)
    public BigDecimal getStandardefficiency() {
        return standardefficiency;
    }

    public void setStandardefficiency(BigDecimal standardefficiency) {
        this.standardefficiency = standardefficiency;
    }

    @Basic
    @Column(name = "STANDARDEFFICIENCYAPPLY", nullable = true, length = 1)
    public String getStandardefficiencyapply() {
        return standardefficiencyapply;
    }

    public void setStandardefficiencyapply(String standardefficiencyapply) {
        this.standardefficiencyapply = standardefficiencyapply;
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
    @Column(name = "TEMPERATURELOSSOVERTIME", nullable = true, precision = 6)
    public BigDecimal getTemperaturelossovertime() {
        return temperaturelossovertime;
    }

    public void setTemperaturelossovertime(BigDecimal temperaturelossovertime) {
        this.temperaturelossovertime = temperaturelossovertime;
    }

    @Basic
    @Column(name = "ADDRESSLINE1", nullable = true, length = 150)
    public String getAddressline1() {
        return addressline1;
    }

    public void setAddressline1(String addressline1) {
        this.addressline1 = addressline1;
    }

    @Basic
    @Column(name = "ADDRESSLINE2", nullable = true, length = 150)
    public String getAddressline2() {
        return addressline2;
    }

    public void setAddressline2(String addressline2) {
        this.addressline2 = addressline2;
    }

    @Basic
    @Column(name = "ADDRESSLINE3", nullable = true, length = 150)
    public String getAddressline3() {
        return addressline3;
    }

    public void setAddressline3(String addressline3) {
        this.addressline3 = addressline3;
    }

    @Basic
    @Column(name = "ADDRESSLINE4", nullable = true, length = 150)
    public String getAddressline4() {
        return addressline4;
    }

    public void setAddressline4(String addressline4) {
        this.addressline4 = addressline4;
    }

    @Basic
    @Column(name = "ADDRESSLINE5", nullable = true, length = 150)
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
    @Column(name = "TOWN", nullable = true, length = 200)
    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @Basic
    @Column(name = "DISTRICT", nullable = true, length = 200)
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Basic
    @Column(name = "ADDRESSPHONENUMBER", nullable = true, length = 80)
    public String getAddressphonenumber() {
        return addressphonenumber;
    }

    public void setAddressphonenumber(String addressphonenumber) {
        this.addressphonenumber = addressphonenumber;
    }

    @Basic
    @Column(name = "ADDRESSFAXNUMBER", nullable = true, length = 80)
    public String getAddressfaxnumber() {
        return addressfaxnumber;
    }

    public void setAddressfaxnumber(String addressfaxnumber) {
        this.addressfaxnumber = addressfaxnumber;
    }

    @Basic
    @Column(name = "PLANTCODE")
    public String getPlantcode() {
        return plantcode;
    }

    public void setPlantcode(String plantcode) {
        this.plantcode = plantcode;
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

    @Basic
    @Column(name = "CREATIONDATETIMEUTC", nullable = true)
    public Timestamp getCreationdatetimeutc() {
        return creationdatetimeutc;
    }

    public void setCreationdatetimeutc(Timestamp creationdatetimeutc) {
        this.creationdatetimeutc = creationdatetimeutc;
    }

    @Basic
    @Column(name = "LASTUPDATEDATETIMEUTC", nullable = true)
    public Timestamp getLastupdatedatetimeutc() {
        return lastupdatedatetimeutc;
    }

    public void setLastupdatedatetimeutc(Timestamp lastupdatedatetimeutc) {
        this.lastupdatedatetimeutc = lastupdatedatetimeutc;
    }

    @Basic
    @Column(name = "RECLCSTEPTIMEFROMPARTPRG", nullable = false)
    public Short getReclcsteptimefrompartprg() {
        return reclcsteptimefrompartprg;
    }

    public void setReclcsteptimefrompartprg(Short reclcsteptimefrompartprg) {
        this.reclcsteptimefrompartprg = reclcsteptimefrompartprg;
    }

    @Basic
    @Column(name = "COSTCENTERCODE")
    public String getCostcentercode() {
        return costcentercode;
    }

    public void setCostcentercode(String costcentercode) {
        this.costcentercode = costcentercode;
    }
}
