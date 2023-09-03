package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "adstoragebean")
public class Adstoragebean {
    private Long fatherid;
    private Long importautocounter;
    private String nameentityname;
    private String namename;
    private String fieldname;
    private String valuestring;
    private Integer valueint;
    private Short valueboolean;
    private Date valuedate;
    private BigDecimal valuedecimal;
    private Long valuelong;
    private Time valuetime;
    private Timestamp valuetimestamp;
    private Integer wsoperation;
    private String impoperationuser;
    private Integer importstatus;
    private Timestamp impcreationdatetime;
    private String impcreationuser;
    private Timestamp implastupdatedatetime;
    private String implastupdateuser;
    private Timestamp importdatetime;
    private Integer retrynr;
    private Long nextretry;
    private Long importid;

    public Adstoragebean() {
    }

    public Adstoragebean(Long fatherid, Long importautocounter, String nameentityname, String namename, String fieldname, String valuestring, Integer valueint, Short valueboolean, Date valuedate, BigDecimal valuedecimal, Long valuelong, Time valuetime, Timestamp valuetimestamp, Integer wsoperation) {
        this.fatherid = fatherid;
        this.importautocounter = importautocounter;
        this.nameentityname = nameentityname;
        this.namename = namename;
        this.fieldname = fieldname;
        this.valuestring = valuestring;
        this.valueint = valueint != null ? valueint.intValue() : 0;
        this.valueboolean = valueboolean != null ? valueboolean : (short) 0;
        this.valuedate = valuedate;
        this.valuedecimal = valuedecimal;
        this.valuelong = valuelong != null ? valuelong.longValue() : 0L;
        this.valuetime = valuetime;
        this.valuetimestamp = valuetimestamp;
        this.wsoperation = wsoperation;
        this.impoperationuser = "";
        this.importstatus = 0;
        this.impcreationdatetime = Timestamp.from(Instant.now());
        this.impcreationuser = "system";
        this.implastupdatedatetime = null;
        this.implastupdateuser = null;
        this.importdatetime = null;
        this.retrynr = 0;
        this.nextretry = 0L;
        this.importid = 0L;
    }

    @Basic
    @Column(name = "FATHERID", nullable = true)
    public Long getFatherid() {
        return fatherid;
    }

    public void setFatherid(Long fatherid) {
        this.fatherid = fatherid;
    }

    @Id
    @Column(name = "IMPORTAUTOCOUNTER", nullable = false)
    public Long getImportautocounter() {
        return importautocounter;
    }

    public void setImportautocounter(Long importautocounter) {
        this.importautocounter = importautocounter;
    }

    @Basic
    @Column(name = "NAMEENTITYNAME", nullable = true, length = 50)
    public String getNameentityname() {
        return nameentityname;
    }

    public void setNameentityname(String nameentityname) {
        this.nameentityname = nameentityname;
    }

    @Basic
    @Column(name = "NAMENAME", nullable = true, length = 50)
    public String getNamename() {
        return namename;
    }

    public void setNamename(String namename) {
        this.namename = namename;
    }

    @Basic
    @Column(name = "FIELDNAME", nullable = true, length = 120)
    public String getFieldname() {
        return fieldname;
    }

    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }

    @Basic
    @Column(name = "VALUESTRING", nullable = true, length = 250)
    public String getValuestring() {
        return valuestring;
    }

    public void setValuestring(String valuestring) {
        this.valuestring = valuestring;
    }

    @Basic
    @Column(name = "VALUEINT", nullable = false)
    public Integer getValueint() {
        return valueint;
    }

    public void setValueint(Integer valueint) {
        this.valueint = valueint;
    }

    @Basic
    @Column(name = "VALUEBOOLEAN", nullable = false)
    public Short getValueboolean() {
        return valueboolean;
    }

    public void setValueboolean(Short valueboolean) {
        this.valueboolean = valueboolean;
    }

    @Basic
    @Column(name = "VALUEDATE", nullable = true)
    public Date getValuedate() {
        return valuedate;
    }

    public void setValuedate(Date valuedate) {
        this.valuedate = valuedate;
    }

    @Basic
    @Column(name = "VALUEDECIMAL", nullable = true, precision = 5)
    public BigDecimal getValuedecimal() {
        return valuedecimal;
    }

    public void setValuedecimal(BigDecimal valuedecimal) {
        this.valuedecimal = valuedecimal;
    }

    @Basic
    @Column(name = "VALUELONG", nullable = false)
    public Long getValuelong() {
        return valuelong;
    }

    public void setValuelong(Long valuelong) {
        this.valuelong = valuelong;
    }

    @Basic
    @Column(name = "VALUETIME", nullable = true)
    public Time getValuetime() {
        return valuetime;
    }

    public void setValuetime(Time valuetime) {
        this.valuetime = valuetime;
    }

    @Basic
    @Column(name = "VALUETIMESTAMP", nullable = true)
    public Timestamp getValuetimestamp() {
        return valuetimestamp;
    }

    public void setValuetimestamp(Timestamp valuetimestamp) {
        this.valuetimestamp = valuetimestamp;
    }

    @Basic
    @Column(name = "WSOPERATION", nullable = false)
    public Integer getWsoperation() {
        return wsoperation;
    }

    public void setWsoperation(Integer wsoperation) {
        this.wsoperation = wsoperation;
    }

    @Basic
    @Column(name = "IMPOPERATIONUSER", nullable = true, length = 25)
    public String getImpoperationuser() {
        return impoperationuser;
    }

    public void setImpoperationuser(String impoperationuser) {
        this.impoperationuser = impoperationuser;
    }

    @Basic
    @Column(name = "IMPORTSTATUS", nullable = false)
    public Integer getImportstatus() {
        return importstatus;
    }

    public void setImportstatus(Integer importstatus) {
        this.importstatus = importstatus;
    }

    @Basic
    @Column(name = "IMPCREATIONDATETIME", nullable = true)
    public Timestamp getImpcreationdatetime() {
        return impcreationdatetime;
    }

    public void setImpcreationdatetime(Timestamp impcreationdatetime) {
        this.impcreationdatetime = impcreationdatetime;
    }

    @Basic
    @Column(name = "IMPCREATIONUSER", nullable = true, length = 25)
    public String getImpcreationuser() {
        return impcreationuser;
    }

    public void setImpcreationuser(String impcreationuser) {
        this.impcreationuser = impcreationuser;
    }

    @Basic
    @Column(name = "IMPLASTUPDATEDATETIME", nullable = true)
    public Timestamp getImplastupdatedatetime() {
        return implastupdatedatetime;
    }

    public void setImplastupdatedatetime(Timestamp implastupdatedatetime) {
        this.implastupdatedatetime = implastupdatedatetime;
    }

    @Basic
    @Column(name = "IMPLASTUPDATEUSER", nullable = true, length = 25)
    public String getImplastupdateuser() {
        return implastupdateuser;
    }

    public void setImplastupdateuser(String implastupdateuser) {
        this.implastupdateuser = implastupdateuser;
    }

    @Basic
    @Column(name = "IMPORTDATETIME", nullable = true)
    public Timestamp getImportdatetime() {
        return importdatetime;
    }

    public void setImportdatetime(Timestamp importdatetime) {
        this.importdatetime = importdatetime;
    }

    @Basic
    @Column(name = "RETRYNR", nullable = false)
    public Integer getRetrynr() {
        return retrynr;
    }

    public void setRetrynr(Integer retrynr) {
        this.retrynr = retrynr;
    }

    @Basic
    @Column(name = "NEXTRETRY", nullable = false)
    public Long getNextretry() {
        return nextretry;
    }

    public void setNextretry(Long nextretry) {
        this.nextretry = nextretry;
    }

    @Basic
    @Column(name = "IMPORTID", nullable = false)
    public Long getImportid() {
        return importid;
    }

    public void setImportid(Long importid) {
        this.importid = importid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adstoragebean that = (Adstoragebean) o;
        return Objects.equals(fatherid, that.fatherid) &&
            Objects.equals(importautocounter, that.importautocounter) &&
            Objects.equals(nameentityname, that.nameentityname) &&
            Objects.equals(namename, that.namename) &&
            Objects.equals(fieldname, that.fieldname) &&
            Objects.equals(valuestring, that.valuestring) &&
            Objects.equals(valueint, that.valueint) &&
            Objects.equals(valueboolean, that.valueboolean) &&
            Objects.equals(valuedate, that.valuedate) &&
            Objects.equals(valuedecimal, that.valuedecimal) &&
            Objects.equals(valuelong, that.valuelong) &&
            Objects.equals(valuetime, that.valuetime) &&
            Objects.equals(valuetimestamp, that.valuetimestamp) &&
            Objects.equals(wsoperation, that.wsoperation) &&
            Objects.equals(impoperationuser, that.impoperationuser) &&
            Objects.equals(importstatus, that.importstatus) &&
            Objects.equals(impcreationdatetime, that.impcreationdatetime) &&
            Objects.equals(impcreationuser, that.impcreationuser) &&
            Objects.equals(implastupdatedatetime, that.implastupdatedatetime) &&
            Objects.equals(implastupdateuser, that.implastupdateuser) &&
            Objects.equals(importdatetime, that.importdatetime) &&
            Objects.equals(retrynr, that.retrynr) &&
            Objects.equals(nextretry, that.nextretry) &&
            Objects.equals(importid, that.importid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fatherid, importautocounter, nameentityname, namename, fieldname, valuestring, valueint, valueboolean, valuedate, valuedecimal, valuelong, valuetime, valuetimestamp, wsoperation, impoperationuser, importstatus, impcreationdatetime, impcreationuser, implastupdatedatetime, implastupdateuser, importdatetime, retrynr, nextretry, importid);
    }
}
