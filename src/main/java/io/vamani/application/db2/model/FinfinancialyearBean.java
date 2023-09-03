package io.vamani.application.db2.model;

import io.vamani.application.db2.domain.FinfinancialyearId;

import java.sql.Date;
import java.sql.Timestamp;

public class FinfinancialyearBean {

    private FinfinancialyearId id;
    private String longdescription;
    private String shortdescription;
    private String searchdescription;
    private Date fromdate;
    private Date todate;
    private boolean yearclosed;
    private Timestamp creationdatetime;
    private String creationuser;
    private Timestamp lastupdatedatetime;
    private String lastupdateuser;
    private String owningcompanycode;
    private Timestamp creationdatetimeutc;
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
}
