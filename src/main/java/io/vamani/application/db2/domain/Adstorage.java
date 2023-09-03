package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "ADSTORAGE")
public class Adstorage {
    @EmbeddedId
    private AdstorageId id;
    private Integer keysequence;
    private Short shared;
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

    public AdstorageId getId() {
        return id;
    }

    public void setId(AdstorageId id) {
        this.id = id;
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
    @Column(name = "SHARED", nullable = false)
    public Short getShared() {
        return shared;
    }

    public void setShared(Short shared) {
        this.shared = shared;
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
        Adstorage adstorage = (Adstorage) o;
        return Objects.equals(id, adstorage.id) && Objects.equals(keysequence, adstorage.keysequence) && Objects.equals(shared, adstorage.shared) && Objects.equals(datatype, adstorage.datatype) && Objects.equals(valuestring, adstorage.valuestring) && Objects.equals(valueint, adstorage.valueint) && Objects.equals(valueboolean, adstorage.valueboolean) && Objects.equals(valuedate, adstorage.valuedate) && Objects.equals(valuedecimal, adstorage.valuedecimal) && Objects.equals(valuelong, adstorage.valuelong) && Objects.equals(valuetime, adstorage.valuetime) && Objects.equals(valuetimestamp, adstorage.valuetimestamp) && Objects.equals(absuniqueid, adstorage.absuniqueid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, keysequence, shared, datatype, valuestring, valueint, valueboolean, valuedate, valuedecimal, valuelong, valuetime, valuetimestamp, absuniqueid);
    }
}
