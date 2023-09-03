package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "workcenterdetail")
public class Workcenterdetail {
    @EmbeddedId
    private WorkcenterdetailId id;
    private Integer numberofmainresources;
    private Date validityenddate;
    private String exceptionreason;
    private Integer exceptionnrofmainresources;
    private BigDecimal theoreticspeedvalue;
    private Timestamp creationdatetime;
    private String creationuser;
    private Timestamp lastupdatedatetime;
    private String lastupdateuser;
    private Long absuniqueid;
    private Timestamp creationdatetimeutc;
    private Timestamp lastupdatedatetimeutc;

    public WorkcenterdetailId getId() {
        return id;
    }

    public void setId(WorkcenterdetailId id) {
        this.id = id;
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
    @Column(name = "VALIDITYENDDATE", nullable = true)
    public Date getValidityenddate() {
        return validityenddate;
    }

    public void setValidityenddate(Date validityenddate) {
        this.validityenddate = validityenddate;
    }

    @Basic
    @Column(name = "EXCEPTIONREASON", nullable = true, length = 30)
    public String getExceptionreason() {
        return exceptionreason;
    }

    public void setExceptionreason(String exceptionreason) {
        this.exceptionreason = exceptionreason;
    }

    @Basic
    @Column(name = "EXCEPTIONNROFMAINRESOURCES", nullable = true, precision = 0)
    public Integer getExceptionnrofmainresources() {
        return exceptionnrofmainresources;
    }

    public void setExceptionnrofmainresources(Integer exceptionnrofmainresources) {
        this.exceptionnrofmainresources = exceptionnrofmainresources;
    }

    @Basic
    @Column(name = "THEORETICSPEEDVALUE", nullable = true, precision = 5)
    public BigDecimal getTheoreticspeedvalue() {
        return theoreticspeedvalue;
    }

    public void setTheoreticspeedvalue(BigDecimal theoreticspeedvalue) {
        this.theoreticspeedvalue = theoreticspeedvalue;
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
        Workcenterdetail that = (Workcenterdetail) o;
        return Objects.equals(id, that.id) && Objects.equals(numberofmainresources, that.numberofmainresources) && Objects.equals(validityenddate, that.validityenddate) && Objects.equals(exceptionreason, that.exceptionreason) && Objects.equals(exceptionnrofmainresources, that.exceptionnrofmainresources) && Objects.equals(theoreticspeedvalue, that.theoreticspeedvalue) && Objects.equals(creationdatetime, that.creationdatetime) && Objects.equals(creationuser, that.creationuser) && Objects.equals(lastupdatedatetime, that.lastupdatedatetime) && Objects.equals(lastupdateuser, that.lastupdateuser) && Objects.equals(absuniqueid, that.absuniqueid) && Objects.equals(creationdatetimeutc, that.creationdatetimeutc) && Objects.equals(lastupdatedatetimeutc, that.lastupdatedatetimeutc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberofmainresources, validityenddate, exceptionreason, exceptionnrofmainresources, theoreticspeedvalue, creationdatetime, creationuser, lastupdatedatetime, lastupdateuser, absuniqueid, creationdatetimeutc, lastupdatedatetimeutc);
    }
}
