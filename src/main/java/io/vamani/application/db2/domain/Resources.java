package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "resources")
public class Resources {
    @EmbeddedId
    private ResourcesId id;
    private String longdescription;
    private String shortdescription;
    private String searchdescription;
    private String type;
    private BigDecimal batchmin;
    private BigDecimal batchmax;
    private BigDecimal bathvolume;
    private Integer maxnbrofscreenscylinders;
    private Date purchasedate;
    private Integer numberofworkers;
    private Timestamp creationdatetime;
    private String creationuser;
    private Timestamp lastupdatedatetime;
    private String lastupdateuser;
    private Long absuniqueid;
    private Timestamp creationdatetimeutc;
    private Timestamp lastupdatedatetimeutc;

    public ResourcesId getId() {
        return id;
    }

    public void setId(ResourcesId id) {
        this.id = id;
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
    @Column(name = "TYPE", nullable = false, length = 1)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "BATCHMIN", nullable = true, precision = 5)
    public BigDecimal getBatchmin() {
        return batchmin;
    }

    public void setBatchmin(BigDecimal batchmin) {
        this.batchmin = batchmin;
    }

    @Basic
    @Column(name = "BATCHMAX", nullable = true, precision = 5)
    public BigDecimal getBatchmax() {
        return batchmax;
    }

    public void setBatchmax(BigDecimal batchmax) {
        this.batchmax = batchmax;
    }

    @Basic
    @Column(name = "BATHVOLUME", nullable = true, precision = 5)
    public BigDecimal getBathvolume() {
        return bathvolume;
    }

    public void setBathvolume(BigDecimal bathvolume) {
        this.bathvolume = bathvolume;
    }

    @Basic
    @Column(name = "MAXNBROFSCREENSCYLINDERS", nullable = true, precision = 0)
    public Integer getMaxnbrofscreenscylinders() {
        return maxnbrofscreenscylinders;
    }

    public void setMaxnbrofscreenscylinders(Integer maxnbrofscreenscylinders) {
        this.maxnbrofscreenscylinders = maxnbrofscreenscylinders;
    }

    @Basic
    @Column(name = "PURCHASEDATE", nullable = true)
    public Date getPurchasedate() {
        return purchasedate;
    }

    public void setPurchasedate(Date purchasedate) {
        this.purchasedate = purchasedate;
    }

    @Basic
    @Column(name = "NUMBEROFWORKERS", nullable = true, precision = 0)
    public Integer getNumberofworkers() {
        return numberofworkers;
    }

    public void setNumberofworkers(Integer numberofworkers) {
        this.numberofworkers = numberofworkers;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resources resources = (Resources) o;
        return Objects.equals(id, resources.id) && Objects.equals(longdescription, resources.longdescription) && Objects.equals(shortdescription, resources.shortdescription) && Objects.equals(searchdescription, resources.searchdescription) && Objects.equals(type, resources.type) && Objects.equals(batchmin, resources.batchmin) && Objects.equals(batchmax, resources.batchmax) && Objects.equals(bathvolume, resources.bathvolume) && Objects.equals(maxnbrofscreenscylinders, resources.maxnbrofscreenscylinders) && Objects.equals(purchasedate, resources.purchasedate) && Objects.equals(numberofworkers, resources.numberofworkers) && Objects.equals(creationdatetime, resources.creationdatetime) && Objects.equals(creationuser, resources.creationuser) && Objects.equals(lastupdatedatetime, resources.lastupdatedatetime) && Objects.equals(lastupdateuser, resources.lastupdateuser) && Objects.equals(absuniqueid, resources.absuniqueid) && Objects.equals(creationdatetimeutc, resources.creationdatetimeutc) && Objects.equals(lastupdatedatetimeutc, resources.lastupdatedatetimeutc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, longdescription, shortdescription, searchdescription, type, batchmin, batchmax, bathvolume, maxnbrofscreenscylinders, purchasedate, numberofworkers, creationdatetime, creationuser, lastupdatedatetime, lastupdateuser, absuniqueid, creationdatetimeutc, lastupdatedatetimeutc);
    }
}
