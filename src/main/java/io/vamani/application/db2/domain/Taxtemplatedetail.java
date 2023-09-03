package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "taxtemplatedetail")
public class Taxtemplatedetail {
    @EmbeddedId
    private TaxtemplatedetailId id;
    private BigInteger calculationsequence;
    private Integer roundofftype;
    private BigDecimal roundoffamount;
    private Integer taxcodetype;
    private Integer calculationtype;
    private BigDecimal value;
    private String currencycode;
    private Integer sign;
    private String calculationbasiscode;
    private Integer modvatsetoff;
    private Integer modvat;
    private BigDecimal modvatcypercentage;
    private BigDecimal modvatnypercentage;
    private Integer allocationbasis;
    private Integer sourceapplicable;
    private Timestamp creationdatetime;
    private String creationuser;
    private Timestamp lastupdatedatetime;
    private String lastupdateuser;
    private Long absuniqueid;
    private Timestamp creationdatetimeutc;
    private Timestamp lastupdatedatetimeutc;

    public TaxtemplatedetailId getId() {
        return id;
    }

    public void setId(TaxtemplatedetailId id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CALCULATIONSEQUENCE", nullable = false, precision = 0)
    public BigInteger getCalculationsequence() {
        return calculationsequence;
    }

    public void setCalculationsequence(BigInteger calculationsequence) {
        this.calculationsequence = calculationsequence;
    }

    @Basic
    @Column(name = "ROUNDOFFTYPE", nullable = false)
    public Integer getRoundofftype() {
        return roundofftype;
    }

    public void setRoundofftype(Integer roundofftype) {
        this.roundofftype = roundofftype;
    }

    @Basic
    @Column(name = "ROUNDOFFAMOUNT", nullable = false, precision = 2)
    public BigDecimal getRoundoffamount() {
        return roundoffamount;
    }

    public void setRoundoffamount(BigDecimal roundoffamount) {
        this.roundoffamount = roundoffamount;
    }

    @Basic
    @Column(name = "TAXCODETYPE", nullable = false)
    public Integer getTaxcodetype() {
        return taxcodetype;
    }

    public void setTaxcodetype(Integer taxcodetype) {
        this.taxcodetype = taxcodetype;
    }

    @Basic
    @Column(name = "CALCULATIONTYPE", nullable = false)
    public Integer getCalculationtype() {
        return calculationtype;
    }

    public void setCalculationtype(Integer calculationtype) {
        this.calculationtype = calculationtype;
    }

    @Basic
    @Column(name = "VALUE", nullable = false, precision = 5)
    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Basic
    @Column(name = "CURRENCYCODE", nullable = true, length = 4)
    public String getCurrencycode() {
        return currencycode;
    }

    public void setCurrencycode(String currencycode) {
        this.currencycode = currencycode;
    }

    @Basic
    @Column(name = "SIGN", nullable = false)
    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }

    @Basic
    @Column(name = "CALCULATIONBASISCODE", nullable = true, length = 3)
    public String getCalculationbasiscode() {
        return calculationbasiscode;
    }

    public void setCalculationbasiscode(String calculationbasiscode) {
        this.calculationbasiscode = calculationbasiscode;
    }

    @Basic
    @Column(name = "MODVATSETOFF", nullable = false)
    public Integer getModvatsetoff() {
        return modvatsetoff;
    }

    public void setModvatsetoff(Integer modvatsetoff) {
        this.modvatsetoff = modvatsetoff;
    }

    @Basic
    @Column(name = "MODVAT", nullable = false)
    public Integer getModvat() {
        return modvat;
    }

    public void setModvat(Integer modvat) {
        this.modvat = modvat;
    }

    @Basic
    @Column(name = "MODVATCYPERCENTAGE", nullable = true, precision = 5)
    public BigDecimal getModvatcypercentage() {
        return modvatcypercentage;
    }

    public void setModvatcypercentage(BigDecimal modvatcypercentage) {
        this.modvatcypercentage = modvatcypercentage;
    }

    @Basic
    @Column(name = "MODVATNYPERCENTAGE", nullable = true, precision = 5)
    public BigDecimal getModvatnypercentage() {
        return modvatnypercentage;
    }

    public void setModvatnypercentage(BigDecimal modvatnypercentage) {
        this.modvatnypercentage = modvatnypercentage;
    }

    @Basic
    @Column(name = "ALLOCATIONBASIS", nullable = false)
    public Integer getAllocationbasis() {
        return allocationbasis;
    }

    public void setAllocationbasis(Integer allocationbasis) {
        this.allocationbasis = allocationbasis;
    }

    @Basic
    @Column(name = "SOURCEAPPLICABLE", nullable = false)
    public Integer getSourceapplicable() {
        return sourceapplicable;
    }

    public void setSourceapplicable(Integer sourceapplicable) {
        this.sourceapplicable = sourceapplicable;
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
        Taxtemplatedetail that = (Taxtemplatedetail) o;
        return Objects.equals(id, that.id) && Objects.equals(calculationsequence, that.calculationsequence) && Objects.equals(roundofftype, that.roundofftype) && Objects.equals(roundoffamount, that.roundoffamount) && Objects.equals(taxcodetype, that.taxcodetype) && Objects.equals(calculationtype, that.calculationtype) && Objects.equals(value, that.value) && Objects.equals(currencycode, that.currencycode) && Objects.equals(sign, that.sign) && Objects.equals(calculationbasiscode, that.calculationbasiscode) && Objects.equals(modvatsetoff, that.modvatsetoff) && Objects.equals(modvat, that.modvat) && Objects.equals(modvatcypercentage, that.modvatcypercentage) && Objects.equals(modvatnypercentage, that.modvatnypercentage) && Objects.equals(allocationbasis, that.allocationbasis) && Objects.equals(sourceapplicable, that.sourceapplicable) && Objects.equals(creationdatetime, that.creationdatetime) && Objects.equals(creationuser, that.creationuser) && Objects.equals(lastupdatedatetime, that.lastupdatedatetime) && Objects.equals(lastupdateuser, that.lastupdateuser) && Objects.equals(absuniqueid, that.absuniqueid) && Objects.equals(creationdatetimeutc, that.creationdatetimeutc) && Objects.equals(lastupdatedatetimeutc, that.lastupdatedatetimeutc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, calculationsequence, roundofftype, roundoffamount, taxcodetype, calculationtype, value, currencycode, sign, calculationbasiscode, modvatsetoff, modvat, modvatcypercentage, modvatnypercentage, allocationbasis, sourceapplicable, creationdatetime, creationuser, lastupdatedatetime, lastupdateuser, absuniqueid, creationdatetimeutc, lastupdatedatetimeutc);
    }
}
