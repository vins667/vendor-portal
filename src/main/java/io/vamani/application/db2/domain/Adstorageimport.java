package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "adstorageimport")
public class Adstorageimport {
    @EmbeddedId
    private AdstorageimportId id;
    private Integer importoperation;
    private Integer importstatus;
    private Integer keysequence;
    private Integer datatype;
    private String valuestring;
    private Integer valueint;
    private Short valueboolean;
    private Date valuedate;
    private BigDecimal valuedecimal;
    private Long valuelong;
    private Time valuetime;
    private Timestamp valuetimestamp;
    private Long absuniqueid;

    public AdstorageimportId getId() {
        return id;
    }

    public void setId(AdstorageimportId id) {
        this.id = id;
    }

    @Basic
    @Column(name = "IMPORTOPERATION", nullable = false)
    public Integer getImportoperation() {
        return importoperation;
    }

    public void setImportoperation(Integer importoperation) {
        this.importoperation = importoperation;
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
    @Column(name = "KEYSEQUENCE", nullable = false)
    public Integer getKeysequence() {
        return keysequence;
    }

    public void setKeysequence(Integer keysequence) {
        this.keysequence = keysequence;
    }

    @Basic
    @Column(name = "DATATYPE", nullable = false)
    public Integer getDatatype() {
        return datatype;
    }

    public void setDatatype(Integer datatype) {
        this.datatype = datatype;
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
        Adstorageimport that = (Adstorageimport) o;
        return Objects.equals(importoperation, that.importoperation) && Objects.equals(importstatus, that.importstatus) && Objects.equals(keysequence, that.keysequence) && Objects.equals(datatype, that.datatype) && Objects.equals(valuestring, that.valuestring) && Objects.equals(valueint, that.valueint) && Objects.equals(valueboolean, that.valueboolean) && Objects.equals(valuedate, that.valuedate) && Objects.equals(valuedecimal, that.valuedecimal) && Objects.equals(valuelong, that.valuelong) && Objects.equals(valuetime, that.valuetime) && Objects.equals(valuetimestamp, that.valuetimestamp) && Objects.equals(absuniqueid, that.absuniqueid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(importoperation, importstatus, keysequence, datatype, valuestring, valueint, valueboolean, valuedate, valuedecimal, valuelong, valuetime, valuetimestamp, absuniqueid);
    }

    public Adstorageimport() {
    }

    public Adstorageimport(AdstorageimportId id, Integer importoperation, Integer importstatus, Integer keysequence, Integer datatype, String valuestring, Integer valueint, Short valueboolean, Date valuedate, BigDecimal valuedecimal, Long valuelong, Time valuetime, Timestamp valuetimestamp, Long absuniqueid) {
        this.id = id;
        this.importoperation = importoperation;
        this.importstatus = importstatus;
        this.keysequence = keysequence;
        this.datatype = datatype;
        this.valuestring = valuestring;
        this.valueint = valueint;
        this.valueboolean = valueboolean;
        this.valuedate = valuedate;
        this.valuedecimal = valuedecimal;
        this.valuelong = valuelong;
        this.valuetime = valuetime;
        this.valuetimestamp = valuetimestamp;
        this.absuniqueid = absuniqueid;
    }
}
