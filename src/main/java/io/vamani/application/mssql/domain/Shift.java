package io.vamani.application.mssql.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "shift_view")
public class Shift implements Serializable {

    @Id
    @Column(name = "sft_code")
    private Long sftCode;

    @Column(name = "sft_ucode")
    private String sftUcode;

    @Column(name = "intime")
    private String intime;

    @Column(name = "outtime")
    private String outtime;

    @Column(name = "grace")
    private Short grace;

    @Column(name = "br1")
    private String br1;

    @Column(name = "end1")
    private String end1;

    @Column(name = "br2")
    private String br2;

    @Column(name = "end2")
    private String end2;

    @Column(name = "br3")
    private String br3;

    @Column(name = "end3")
    private String end3;

    @Column(name = "ostart")
    private Integer ostart;

    @Column(name = "bstart")
    private Integer bstart;

    @Column(name = "hfday")
    private Integer hfday;

    @Column(name = "fulday")
    private Integer fulday;

    @Column(name = "hf_after_hrs")
    private Double hfAfterHrs;

    @Column(name = "ot_round_min")
    private Double otRoundMin;

    @Column(name = "nt_after_ded")
    private Double ntAfterDed;

    @Column(name = "min_deduct")
    private Double minDeduct;

    @Column(name = "lunch")
    private Double lunch;

    @Column(name = "food_after_min")
    private Integer foodAfterMin;

    @Column(name = "sft_mis_bef_min")
    private Integer sftMisBefMin;

    @Column(name = "sft_mis_aft_min")
    private Integer sftMisAftMin;

    @Column(name = "food_allow")
    private Integer foodAllow;

    public Long getSftCode() {
        return sftCode;
    }

    public void setSftCode(Long sftCode) {
        this.sftCode = sftCode;
    }

    public String getSftUcode() {
        return sftUcode;
    }

    public void setSftUcode(String sftUcode) {
        this.sftUcode = sftUcode;
    }

    public String getIntime() {
        return intime;
    }

    public void setIntime(String intime) {
        this.intime = intime;
    }

    public String getOuttime() {
        return outtime;
    }

    public void setOuttime(String outtime) {
        this.outtime = outtime;
    }

    public Short getGrace() {
        return grace;
    }

    public void setGrace(Short grace) {
        this.grace = grace;
    }

    public String getBr1() {
        return br1;
    }

    public void setBr1(String br1) {
        this.br1 = br1;
    }

    public String getEnd1() {
        return end1;
    }

    public void setEnd1(String end1) {
        this.end1 = end1;
    }

    public String getBr2() {
        return br2;
    }

    public void setBr2(String br2) {
        this.br2 = br2;
    }

    public String getEnd2() {
        return end2;
    }

    public void setEnd2(String end2) {
        this.end2 = end2;
    }

    public String getBr3() {
        return br3;
    }

    public void setBr3(String br3) {
        this.br3 = br3;
    }

    public String getEnd3() {
        return end3;
    }

    public void setEnd3(String end3) {
        this.end3 = end3;
    }

    public Integer getOstart() {
        return ostart;
    }

    public void setOstart(Integer ostart) {
        this.ostart = ostart;
    }

    public Integer getBstart() {
        return bstart;
    }

    public void setBstart(Integer bstart) {
        this.bstart = bstart;
    }

    public Integer getHfday() {
        return hfday;
    }

    public void setHfday(Integer hfday) {
        this.hfday = hfday;
    }

    public Integer getFulday() {
        return fulday;
    }

    public void setFulday(Integer fulday) {
        this.fulday = fulday;
    }

    public Double getHfAfterHrs() {
        return hfAfterHrs;
    }

    public void setHfAfterHrs(Double hfAfterHrs) {
        this.hfAfterHrs = hfAfterHrs;
    }

    public Double getOtRoundMin() {
        return otRoundMin;
    }

    public void setOtRoundMin(Double otRoundMin) {
        this.otRoundMin = otRoundMin;
    }

    public Double getNtAfterDed() {
        return ntAfterDed;
    }

    public void setNtAfterDed(Double ntAfterDed) {
        this.ntAfterDed = ntAfterDed;
    }

    public Double getMinDeduct() {
        return minDeduct;
    }

    public void setMinDeduct(Double minDeduct) {
        this.minDeduct = minDeduct;
    }

    public Double getLunch() {
        return lunch;
    }

    public void setLunch(Double lunch) {
        this.lunch = lunch;
    }

    public Integer getFoodAfterMin() {
        return foodAfterMin;
    }

    public void setFoodAfterMin(Integer foodAfterMin) {
        this.foodAfterMin = foodAfterMin;
    }

    public Integer getSftMisBefMin() {
        return sftMisBefMin;
    }

    public void setSftMisBefMin(Integer sftMisBefMin) {
        this.sftMisBefMin = sftMisBefMin;
    }

    public Integer getSftMisAftMin() {
        return sftMisAftMin;
    }

    public void setSftMisAftMin(Integer sftMisAftMin) {
        this.sftMisAftMin = sftMisAftMin;
    }

    public Integer getFoodAllow() {
        return foodAllow;
    }

    public void setFoodAllow(Integer foodAllow) {
        this.foodAllow = foodAllow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shift shift = (Shift) o;
        return Objects.equals(sftUcode, shift.sftUcode) &&
                Objects.equals(intime, shift.intime) &&
                Objects.equals(outtime, shift.outtime) &&
                Objects.equals(grace, shift.grace) &&
                Objects.equals(br1, shift.br1) &&
                Objects.equals(end1, shift.end1) &&
                Objects.equals(br2, shift.br2) &&
                Objects.equals(end2, shift.end2) &&
                Objects.equals(br3, shift.br3) &&
                Objects.equals(end3, shift.end3) &&
                Objects.equals(ostart, shift.ostart) &&
                Objects.equals(bstart, shift.bstart) &&
                Objects.equals(hfday, shift.hfday) &&
                Objects.equals(fulday, shift.fulday) &&
                Objects.equals(hfAfterHrs, shift.hfAfterHrs) &&
                Objects.equals(otRoundMin, shift.otRoundMin) &&
                Objects.equals(ntAfterDed, shift.ntAfterDed) &&
                Objects.equals(minDeduct, shift.minDeduct) &&
                Objects.equals(lunch, shift.lunch) &&
                Objects.equals(foodAfterMin, shift.foodAfterMin) &&
                Objects.equals(sftMisBefMin, shift.sftMisBefMin) &&
                Objects.equals(sftMisAftMin, shift.sftMisAftMin) &&
                Objects.equals(foodAllow, shift.foodAllow);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sftUcode, intime, outtime, grace, br1, end1, br2, end2, br3, end3, ostart, bstart, hfday, fulday, hfAfterHrs, otRoundMin, ntAfterDed, minDeduct, lunch, foodAfterMin, sftMisBefMin, sftMisAftMin, foodAllow);
    }
}
