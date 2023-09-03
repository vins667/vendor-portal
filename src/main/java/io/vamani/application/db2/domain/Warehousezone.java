package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "warehousezone")
public class Warehousezone {
    @EmbeddedId
    private WarehousezoneId id;
    private String longdescription;
    private String shortdescription;
    private String searchdescription;
    private Integer numberoflocationsubcode;
    private Short weightconstraint;
    private Short volumeconstraint;
    private Short lengthconstraint;
    private Short heightconstraint;
    private Short widthconstraint;
    private Short firstconstraint;
    private Short secondconstraint;
    private Short thirdconstraint;
    private Short fourthconstraint;
    private Short fifthconstraint;
    private String measurementconstraintcode;
    private Timestamp creationdatetime;
    private String creationuser;
    private Timestamp lastupdatedatetime;
    private String lastupdateuser;
    private Long absuniqueid;
    private Timestamp creationdatetimeutc;
    private Timestamp lastupdatedatetimeutc;

    public WarehousezoneId getId() {
        return id;
    }

    public void setId(WarehousezoneId id) {
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
    @Column(name = "NUMBEROFLOCATIONSUBCODE", nullable = false)
    public Integer getNumberoflocationsubcode() {
        return numberoflocationsubcode;
    }

    public void setNumberoflocationsubcode(Integer numberoflocationsubcode) {
        this.numberoflocationsubcode = numberoflocationsubcode;
    }

    @Basic
    @Column(name = "WEIGHTCONSTRAINT", nullable = false)
    public Short getWeightconstraint() {
        return weightconstraint;
    }

    public void setWeightconstraint(Short weightconstraint) {
        this.weightconstraint = weightconstraint;
    }

    @Basic
    @Column(name = "VOLUMECONSTRAINT", nullable = false)
    public Short getVolumeconstraint() {
        return volumeconstraint;
    }

    public void setVolumeconstraint(Short volumeconstraint) {
        this.volumeconstraint = volumeconstraint;
    }

    @Basic
    @Column(name = "LENGTHCONSTRAINT", nullable = false)
    public Short getLengthconstraint() {
        return lengthconstraint;
    }

    public void setLengthconstraint(Short lengthconstraint) {
        this.lengthconstraint = lengthconstraint;
    }

    @Basic
    @Column(name = "HEIGHTCONSTRAINT", nullable = false)
    public Short getHeightconstraint() {
        return heightconstraint;
    }

    public void setHeightconstraint(Short heightconstraint) {
        this.heightconstraint = heightconstraint;
    }

    @Basic
    @Column(name = "WIDTHCONSTRAINT", nullable = false)
    public Short getWidthconstraint() {
        return widthconstraint;
    }

    public void setWidthconstraint(Short widthconstraint) {
        this.widthconstraint = widthconstraint;
    }

    @Basic
    @Column(name = "FIRSTCONSTRAINT", nullable = false)
    public Short getFirstconstraint() {
        return firstconstraint;
    }

    public void setFirstconstraint(Short firstconstraint) {
        this.firstconstraint = firstconstraint;
    }

    @Basic
    @Column(name = "SECONDCONSTRAINT", nullable = false)
    public Short getSecondconstraint() {
        return secondconstraint;
    }

    public void setSecondconstraint(Short secondconstraint) {
        this.secondconstraint = secondconstraint;
    }

    @Basic
    @Column(name = "THIRDCONSTRAINT", nullable = false)
    public Short getThirdconstraint() {
        return thirdconstraint;
    }

    public void setThirdconstraint(Short thirdconstraint) {
        this.thirdconstraint = thirdconstraint;
    }

    @Basic
    @Column(name = "FOURTHCONSTRAINT", nullable = false)
    public Short getFourthconstraint() {
        return fourthconstraint;
    }

    public void setFourthconstraint(Short fourthconstraint) {
        this.fourthconstraint = fourthconstraint;
    }

    @Basic
    @Column(name = "FIFTHCONSTRAINT", nullable = false)
    public Short getFifthconstraint() {
        return fifthconstraint;
    }

    public void setFifthconstraint(Short fifthconstraint) {
        this.fifthconstraint = fifthconstraint;
    }

    @Basic
    @Column(name = "MEASUREMENTCONSTRAINTCODE", nullable = true, length = 20)
    public String getMeasurementconstraintcode() {
        return measurementconstraintcode;
    }

    public void setMeasurementconstraintcode(String measurementconstraintcode) {
        this.measurementconstraintcode = measurementconstraintcode;
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
