package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name="FINFINANCIALYEAR")
public class Finfinancialyear {
   @EmbeddedId
   private FinfinancialyearId id;

    @Basic
    @Column(name = "LONGDESCRIPTION")
    private String longdescription;
    @Basic
    @Column(name = "SHORTDESCRIPTION")
    private String shortdescription;
    @Basic
    @Column(name = "SEARCHDESCRIPTION")
    private String searchdescription;
    @Basic
    @Column(name = "FROMDATE")
    private Date fromdate;
    @Basic
    @Column(name = "TODATE")
    private Date todate;
    @Basic
    @Column(name = "YEARCLOSED")
    private boolean yearclosed;
    @Basic
    @Column(name = "CREATIONDATETIME")
    private Timestamp creationdatetime;
    @Basic
    @Column(name = "CREATIONUSER")
    private String creationuser;
    @Basic
    @Column(name = "LASTUPDATEDATETIME")
    private Timestamp lastupdatedatetime;
    @Basic
    @Column(name = "LASTUPDATEUSER")
    private String lastupdateuser;
    @Basic
    @Column(name = "OWNINGCOMPANYCODE")
    private String owningcompanycode;
    @Basic
    @Column(name = "CREATIONDATETIMEUTC")
    private Timestamp creationdatetimeutc;
    @Basic
    @Column(name = "LASTUPDATEDATETIMEUTC")
    private Timestamp lastupdatedatetimeutc;

    public FinfinancialyearId getId() {
        return id;
    }

    public void setId(FinfinancialyearId id) {
        this.id = id;
    }

    public String getLongdescription() {
        return longdescription;
    }

    public void setLongdescription(String longdescription) {
        this.longdescription = longdescription;
    }

    public String getShortdescription() {
        return shortdescription;
    }

    public void setShortdescription(String shortdescription) {
        this.shortdescription = shortdescription;
    }

    public String getSearchdescription() {
        return searchdescription;
    }

    public void setSearchdescription(String searchdescription) {
        this.searchdescription = searchdescription;
    }

    public Date getFromdate() {
        return fromdate;
    }

    public void setFromdate(Date fromdate) {
        this.fromdate = fromdate;
    }

    public Date getTodate() {
        return todate;
    }

    public void setTodate(Date todate) {
        this.todate = todate;
    }

    public boolean isYearclosed() {
        return yearclosed;
    }

    public void setYearclosed(boolean yearclosed) {
        this.yearclosed = yearclosed;
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

    public String getOwningcompanycode() {
        return owningcompanycode;
    }

    public void setOwningcompanycode(String owningcompanycode) {
        this.owningcompanycode = owningcompanycode;
    }

    public Timestamp getCreationdatetimeutc() {
        return creationdatetimeutc;
    }

    public void setCreationdatetimeutc(Timestamp creationdatetimeutc) {
        this.creationdatetimeutc = creationdatetimeutc;
    }

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
        Finfinancialyear that = (Finfinancialyear) o;
        return yearclosed == that.yearclosed && Objects.equals(id, that.id) && Objects.equals(longdescription, that.longdescription) && Objects.equals(shortdescription, that.shortdescription) && Objects.equals(searchdescription, that.searchdescription) && Objects.equals(fromdate, that.fromdate) && Objects.equals(todate, that.todate) && Objects.equals(creationdatetime, that.creationdatetime) && Objects.equals(creationuser, that.creationuser) && Objects.equals(lastupdatedatetime, that.lastupdatedatetime) && Objects.equals(lastupdateuser, that.lastupdateuser) && Objects.equals(owningcompanycode, that.owningcompanycode) && Objects.equals(creationdatetimeutc, that.creationdatetimeutc) && Objects.equals(lastupdatedatetimeutc, that.lastupdatedatetimeutc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, longdescription, shortdescription, searchdescription, fromdate, todate, yearclosed, creationdatetime, creationuser, lastupdatedatetime, lastupdateuser, owningcompanycode, creationdatetimeutc, lastupdatedatetimeutc);
    }
}
