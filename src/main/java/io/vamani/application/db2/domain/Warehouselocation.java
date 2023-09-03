package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "warehouselocation")
public class Warehouselocation {
    @EmbeddedId
    private WarehouselocationId id;
    private Integer locationnature;
    private Short manualmanagement;
    private String status;
    private Integer entrypriority;
    private Integer issuepriority;
    private Short allowdifferentproductsinloc;
    private Short allowdifferentcontainersinloc;
    private Short allowdifferentlotsinlocation;
    private Short possibilitypartialentry;
    private Short possibilitypartialissue;
    private Short entryonlyifempty;
    private String locgroupstandardgrouptypecode;
    private String locationgroupcode;
    private String locpositionstdgrouptypecode;
    private String locationpositioncode;
    private Date currentstocktakedate;
    private Date laststocktakedate;
    private String understocktake;
    private Timestamp creationdatetime;
    private String creationuser;
    private Timestamp lastupdatedatetime;
    private String lastupdateuser;
    private Long absuniqueid;
    private Timestamp creationdatetimeutc;
    private Timestamp lastupdatedatetimeutc;

    public WarehouselocationId getId() {
        return id;
    }

    public void setId(WarehouselocationId id) {
        this.id = id;
    }

    @Basic
    @Column(name = "LOCATIONNATURE", nullable = false)
    public Integer getLocationnature() {
        return locationnature;
    }

    public void setLocationnature(Integer locationnature) {
        this.locationnature = locationnature;
    }

    @Basic
    @Column(name = "MANUALMANAGEMENT", nullable = false)
    public Short getManualmanagement() {
        return manualmanagement;
    }

    public void setManualmanagement(Short manualmanagement) {
        this.manualmanagement = manualmanagement;
    }

    @Basic
    @Column(name = "STATUS", nullable = false, length = 2)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "ENTRYPRIORITY", nullable = false)
    public Integer getEntrypriority() {
        return entrypriority;
    }

    public void setEntrypriority(Integer entrypriority) {
        this.entrypriority = entrypriority;
    }

    @Basic
    @Column(name = "ISSUEPRIORITY", nullable = false)
    public Integer getIssuepriority() {
        return issuepriority;
    }

    public void setIssuepriority(Integer issuepriority) {
        this.issuepriority = issuepriority;
    }

    @Basic
    @Column(name = "ALLOWDIFFERENTPRODUCTSINLOC", nullable = false)
    public Short getAllowdifferentproductsinloc() {
        return allowdifferentproductsinloc;
    }

    public void setAllowdifferentproductsinloc(Short allowdifferentproductsinloc) {
        this.allowdifferentproductsinloc = allowdifferentproductsinloc;
    }

    @Basic
    @Column(name = "ALLOWDIFFERENTCONTAINERSINLOC", nullable = false)
    public Short getAllowdifferentcontainersinloc() {
        return allowdifferentcontainersinloc;
    }

    public void setAllowdifferentcontainersinloc(Short allowdifferentcontainersinloc) {
        this.allowdifferentcontainersinloc = allowdifferentcontainersinloc;
    }

    @Basic
    @Column(name = "ALLOWDIFFERENTLOTSINLOCATION", nullable = false)
    public Short getAllowdifferentlotsinlocation() {
        return allowdifferentlotsinlocation;
    }

    public void setAllowdifferentlotsinlocation(Short allowdifferentlotsinlocation) {
        this.allowdifferentlotsinlocation = allowdifferentlotsinlocation;
    }

    @Basic
    @Column(name = "POSSIBILITYPARTIALENTRY", nullable = false)
    public Short getPossibilitypartialentry() {
        return possibilitypartialentry;
    }

    public void setPossibilitypartialentry(Short possibilitypartialentry) {
        this.possibilitypartialentry = possibilitypartialentry;
    }

    @Basic
    @Column(name = "POSSIBILITYPARTIALISSUE", nullable = false)
    public Short getPossibilitypartialissue() {
        return possibilitypartialissue;
    }

    public void setPossibilitypartialissue(Short possibilitypartialissue) {
        this.possibilitypartialissue = possibilitypartialissue;
    }

    @Basic
    @Column(name = "ENTRYONLYIFEMPTY", nullable = false)
    public Short getEntryonlyifempty() {
        return entryonlyifempty;
    }

    public void setEntryonlyifempty(Short entryonlyifempty) {
        this.entryonlyifempty = entryonlyifempty;
    }

    @Basic
    @Column(name = "LOCGROUPSTANDARDGROUPTYPECODE", nullable = true, length = 3)
    public String getLocgroupstandardgrouptypecode() {
        return locgroupstandardgrouptypecode;
    }

    public void setLocgroupstandardgrouptypecode(String locgroupstandardgrouptypecode) {
        this.locgroupstandardgrouptypecode = locgroupstandardgrouptypecode;
    }

    @Basic
    @Column(name = "LOCATIONGROUPCODE", nullable = true, length = 3)
    public String getLocationgroupcode() {
        return locationgroupcode;
    }

    public void setLocationgroupcode(String locationgroupcode) {
        this.locationgroupcode = locationgroupcode;
    }

    @Basic
    @Column(name = "LOCPOSITIONSTDGROUPTYPECODE", nullable = true, length = 3)
    public String getLocpositionstdgrouptypecode() {
        return locpositionstdgrouptypecode;
    }

    public void setLocpositionstdgrouptypecode(String locpositionstdgrouptypecode) {
        this.locpositionstdgrouptypecode = locpositionstdgrouptypecode;
    }

    @Basic
    @Column(name = "LOCATIONPOSITIONCODE", nullable = true, length = 3)
    public String getLocationpositioncode() {
        return locationpositioncode;
    }

    public void setLocationpositioncode(String locationpositioncode) {
        this.locationpositioncode = locationpositioncode;
    }

    @Basic
    @Column(name = "CURRENTSTOCKTAKEDATE", nullable = true)
    public Date getCurrentstocktakedate() {
        return currentstocktakedate;
    }

    public void setCurrentstocktakedate(Date currentstocktakedate) {
        this.currentstocktakedate = currentstocktakedate;
    }

    @Basic
    @Column(name = "LASTSTOCKTAKEDATE", nullable = true)
    public Date getLaststocktakedate() {
        return laststocktakedate;
    }

    public void setLaststocktakedate(Date laststocktakedate) {
        this.laststocktakedate = laststocktakedate;
    }

    @Basic
    @Column(name = "UNDERSTOCKTAKE", nullable = true, length = 2)
    public String getUnderstocktake() {
        return understocktake;
    }

    public void setUnderstocktake(String understocktake) {
        this.understocktake = understocktake;
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
}
