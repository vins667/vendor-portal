package io.vamani.application.mssql.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "salary_rate_view")
public class SalaryRate {
    @EmbeddedId
    private SalaryRateId id;

    @Column(name = "tot_sal")
    private Double totSal;

    @Column(name = "tot_sal1")
    private Double totSal1;

    @Column(name = "amt1")
    private Double amt1;

    @Column(name = "amt2")
    private Double amt2;

    @Column(name = "a_code1")
    private Integer acode1;

    @Column(name = "a_code2")
    private Integer acode2;

    @Column(name = "a_code3")
    private Integer acode3;

    @Column(name = "a_code4")
    private Integer acode4;

    @Column(name = "a_code5")
    private Integer acode5;

    @Column(name = "a_code6")
    private Integer acode6;

    @Column(name = "a_code7")
    private Integer acode7;

    @Column(name = "a_code8")
    private Integer acode8;

    @Column(name = "a_code9")
    private Integer acode9;

    @Column(name = "a_code10")
    private Integer acode10;

    @Column(name = "a_code11")
    private Integer acode11;

    @Column(name = "a_code12")
    private Integer acode12;

    @Column(name = "a_code13")
    private Integer acode13;

    @Column(name = "a_code14")
    private Integer acode14;

    @Column(name = "a_code15")
    private Integer acode15;

    @Column(name = "a_code16")
    private Integer acode16;

    @Column(name = "amt3")
    private Double amt3;

    @Column(name = "amt4")
    private Double amt4;

    @Column(name = "amt5")
    private Double amt5;

    @Column(name = "amt6")
    private Double amt6;

    @Column(name = "amt7")
    private Double amt7;

    @Column(name = "amt8")
    private Double amt8;

    @Column(name = "amt9")
    private Double amt9;

    @Column(name = "amt10")
    private Double amt10;

    @Column(name = "amt11")
    private Double amt11;

    @Column(name = "amt12")
    private Double amt12;

    @Column(name = "amt13")
    private Double amt13;

    @Column(name = "amt14")
    private Double amt14;

    @Column(name = "amt15")
    private Double amt15;

    @Column(name = "amt16")
    private Double amt16;

    @Column(name = "tdat")
    private Timestamp tdat;

    public SalaryRateId getId() {
        return id;
    }

    public void setId(SalaryRateId id) {
        this.id = id;
    }

    public Double getTotSal() {
        return totSal;
    }

    public void setTotSal(Double totSal) {
        this.totSal = totSal;
    }

    public Double getTotSal1() {
        return totSal1;
    }

    public void setTotSal1(Double totSal1) {
        this.totSal1 = totSal1;
    }

    public Double getAmt1() {
        return amt1;
    }

    public void setAmt1(Double amt1) {
        this.amt1 = amt1;
    }

    public Double getAmt2() {
        return amt2;
    }

    public void setAmt2(Double amt2) {
        this.amt2 = amt2;
    }

    public Integer getAcode1() {
        return acode1;
    }

    public void setAcode1(Integer acode1) {
        this.acode1 = acode1;
    }

    public Integer getAcode2() {
        return acode2;
    }

    public void setAcode2(Integer acode2) {
        this.acode2 = acode2;
    }

    public Integer getAcode3() {
        return acode3;
    }

    public void setAcode3(Integer acode3) {
        this.acode3 = acode3;
    }

    public Integer getAcode4() {
        return acode4;
    }

    public void setAcode4(Integer acode4) {
        this.acode4 = acode4;
    }

    public Integer getAcode5() {
        return acode5;
    }

    public void setAcode5(Integer acode5) {
        this.acode5 = acode5;
    }

    public Integer getAcode6() {
        return acode6;
    }

    public void setAcode6(Integer acode6) {
        this.acode6 = acode6;
    }

    public Integer getAcode7() {
        return acode7;
    }

    public void setAcode7(Integer acode7) {
        this.acode7 = acode7;
    }

    public Integer getAcode8() {
        return acode8;
    }

    public void setAcode8(Integer acode8) {
        this.acode8 = acode8;
    }

    public Integer getAcode9() {
        return acode9;
    }

    public void setAcode9(Integer acode9) {
        this.acode9 = acode9;
    }

    public Integer getAcode10() {
        return acode10;
    }

    public void setAcode10(Integer acode10) {
        this.acode10 = acode10;
    }

    public Integer getAcode11() {
        return acode11;
    }

    public void setAcode11(Integer acode11) {
        this.acode11 = acode11;
    }

    public Integer getAcode12() {
        return acode12;
    }

    public void setAcode12(Integer acode12) {
        this.acode12 = acode12;
    }

    public Integer getAcode13() {
        return acode13;
    }

    public void setAcode13(Integer acode13) {
        this.acode13 = acode13;
    }

    public Integer getAcode14() {
        return acode14;
    }

    public void setAcode14(Integer acode14) {
        this.acode14 = acode14;
    }

    public Integer getAcode15() {
        return acode15;
    }

    public void setAcode15(Integer acode15) {
        this.acode15 = acode15;
    }

    public Integer getAcode16() {
        return acode16;
    }

    public void setAcode16(Integer acode16) {
        this.acode16 = acode16;
    }

    public Double getAmt3() {
        return amt3;
    }

    public void setAmt3(Double amt3) {
        this.amt3 = amt3;
    }

    public Double getAmt4() {
        return amt4;
    }

    public void setAmt4(Double amt4) {
        this.amt4 = amt4;
    }

    public Double getAmt5() {
        return amt5;
    }

    public void setAmt5(Double amt5) {
        this.amt5 = amt5;
    }

    public Double getAmt6() {
        return amt6;
    }

    public void setAmt6(Double amt6) {
        this.amt6 = amt6;
    }

    public Double getAmt7() {
        return amt7;
    }

    public void setAmt7(Double amt7) {
        this.amt7 = amt7;
    }

    public Double getAmt8() {
        return amt8;
    }

    public void setAmt8(Double amt8) {
        this.amt8 = amt8;
    }

    public Double getAmt9() {
        return amt9;
    }

    public void setAmt9(Double amt9) {
        this.amt9 = amt9;
    }

    public Double getAmt10() {
        return amt10;
    }

    public void setAmt10(Double amt10) {
        this.amt10 = amt10;
    }

    public Double getAmt11() {
        return amt11;
    }

    public void setAmt11(Double amt11) {
        this.amt11 = amt11;
    }

    public Double getAmt12() {
        return amt12;
    }

    public void setAmt12(Double amt12) {
        this.amt12 = amt12;
    }

    public Double getAmt13() {
        return amt13;
    }

    public void setAmt13(Double amt13) {
        this.amt13 = amt13;
    }

    public Double getAmt14() {
        return amt14;
    }

    public void setAmt14(Double amt14) {
        this.amt14 = amt14;
    }

    public Double getAmt15() {
        return amt15;
    }

    public void setAmt15(Double amt15) {
        this.amt15 = amt15;
    }

    public Double getAmt16() {
        return amt16;
    }

    public void setAmt16(Double amt16) {
        this.amt16 = amt16;
    }

    public Timestamp getTdat() {
        return tdat;
    }

    public void setTdat(Timestamp tdat) {
        this.tdat = tdat;
    }

    public SalaryRate() {
    }

    public SalaryRate(SalaryRateId id, Double totSal, Double totSal1, Double amt1, Double amt2, Integer acode1, Integer acode2, Integer acode3, Integer acode4, Integer acode5, Integer acode6, Integer acode7, Integer acode8, Integer acode9, Integer acode10, Integer acode11, Integer acode12, Integer acode13, Integer acode14, Integer acode15, Integer acode16, Double amt3, Double amt4, Double amt5, Double amt6, Double amt7, Double amt8, Double amt9, Double amt10, Double amt11, Double amt12, Double amt13, Double amt14, Double amt15, Double amt16, Timestamp tdat) {
        this.id = id;
        this.totSal = totSal;
        this.totSal1 = totSal1;
        this.amt1 = amt1;
        this.amt2 = amt2;
        this.acode1 = acode1;
        this.acode2 = acode2;
        this.acode3 = acode3;
        this.acode4 = acode4;
        this.acode5 = acode5;
        this.acode6 = acode6;
        this.acode7 = acode7;
        this.acode8 = acode8;
        this.acode9 = acode9;
        this.acode10 = acode10;
        this.acode11 = acode11;
        this.acode12 = acode12;
        this.acode13 = acode13;
        this.acode14 = acode14;
        this.acode15 = acode15;
        this.acode16 = acode16;
        this.amt3 = amt3;
        this.amt4 = amt4;
        this.amt5 = amt5;
        this.amt6 = amt6;
        this.amt7 = amt7;
        this.amt8 = amt8;
        this.amt9 = amt9;
        this.amt10 = amt10;
        this.amt11 = amt11;
        this.amt12 = amt12;
        this.amt13 = amt13;
        this.amt14 = amt14;
        this.amt15 = amt15;
        this.amt16 = amt16;
        this.tdat = tdat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalaryRate that = (SalaryRate) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(totSal, that.totSal) &&
            Objects.equals(totSal1, that.totSal1) &&
            Objects.equals(amt1, that.amt1) &&
            Objects.equals(amt2, that.amt2) &&
            Objects.equals(acode1, that.acode1) &&
            Objects.equals(acode2, that.acode2) &&
            Objects.equals(acode3, that.acode3) &&
            Objects.equals(acode4, that.acode4) &&
            Objects.equals(acode5, that.acode5) &&
            Objects.equals(acode6, that.acode6) &&
            Objects.equals(acode7, that.acode7) &&
            Objects.equals(acode8, that.acode8) &&
            Objects.equals(acode9, that.acode9) &&
            Objects.equals(acode10, that.acode10) &&
            Objects.equals(acode11, that.acode11) &&
            Objects.equals(acode12, that.acode12) &&
            Objects.equals(acode13, that.acode13) &&
            Objects.equals(acode14, that.acode14) &&
            Objects.equals(acode15, that.acode15) &&
            Objects.equals(acode16, that.acode16) &&
            Objects.equals(amt3, that.amt3) &&
            Objects.equals(amt4, that.amt4) &&
            Objects.equals(amt5, that.amt5) &&
            Objects.equals(amt6, that.amt6) &&
            Objects.equals(amt7, that.amt7) &&
            Objects.equals(amt8, that.amt8) &&
            Objects.equals(amt9, that.amt9) &&
            Objects.equals(amt10, that.amt10) &&
            Objects.equals(amt11, that.amt11) &&
            Objects.equals(amt12, that.amt12) &&
            Objects.equals(amt13, that.amt13) &&
            Objects.equals(amt14, that.amt14) &&
            Objects.equals(amt15, that.amt15) &&
            Objects.equals(amt16, that.amt16) &&
            Objects.equals(tdat, that.tdat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totSal, totSal1, amt1, amt2, acode1, acode2, acode3, acode4, acode5, acode6, acode7, acode8, acode9, acode10, acode11, acode12, acode13, acode14, acode15, acode16, amt3, amt4, amt5, amt6, amt7, amt8, amt9, amt10, amt11, amt12, amt13, amt14, amt15, amt16, tdat);
    }
}
