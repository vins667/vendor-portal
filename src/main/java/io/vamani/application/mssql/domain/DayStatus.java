package io.vamani.application.mssql.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "day_status_view")
public class DayStatus implements Serializable {

    @EmbeddedId
    private DayStatusId id;

    @Column(name = "status", nullable = true, length = 3)
    private String status;

    @Column(name = "lv", nullable = true, length = 3)
    private String lv;

    @Column(name = "othr", nullable = true, precision = 0)
    private Double othr;

    @Column(name = "tothr", nullable = true, precision = 0)
    private Double tothr;

    @Column(name = "late", nullable = true, precision = 0)
    private Double late;

    @Column(name = "in_tm", nullable = true, length = 5)
    private String inTm;

    @Column(name = "out_tm", nullable = true, length = 5)
    private String outTm;

    @Column(name = "sal", nullable = true, precision = 0)
    private Double sal;

    @Column(name = "ot_amt", nullable = true, precision = 0)
    private Double otAmt;

    @Column(name = "shift", nullable = true, length = 5)
    private String shift;

    @Column(name = "wo_hd", nullable = true)
    private Integer woHd;

    @Column(name = "pday", nullable = true, precision = 0)
    private Double pday;

    @Column(name = "wday", nullable = true, precision = 0)
    private Double wday;

    @Column(name = "bef_in", nullable = true, precision = 0)
    private Double befIn;

    @Column(name = "el", nullable = true, precision = 0)
    private Double el;

    @Column(name = "cl", nullable = true, precision = 0)
    private Double cl;

    @Column(name = "sl", nullable = true, precision = 0)
    private Double sl;

    @Column(name = "co", nullable = true, precision = 0)
    private Double co;

    @Column(name = "ml", nullable = true, precision = 0)
    private Double ml;

    @Column(name = "sp", nullable = true, precision = 0)
    private Double sp;

    @Column(name = "out_late", nullable = true, precision = 0)
    private Double outLate;

    @Column(name = "food_amt", nullable = true, precision = 0)
    private Double foodAmt;

    @Column(name = "tea_amt", nullable = true, precision = 0)
    private Double teaAmt;

    @Column(name = "pl", nullable = true, precision = 0)
    private Double pl;

    @Column(name = "basic_sal", nullable = true, precision = 0)
    private Double basicSal;

    @Column(name = "es", nullable = true, precision = 0)
    private Double es;

    @Column(name = "in_dt", nullable = true, length = 16)
    private String inDt;

    @Column(name = "out_dt", nullable = true, length = 16)
    private String outDt;

    @Column(name = "ot_hr16", nullable = true, precision = 0)
    private Double otHr16;

    @Column(name = "machine_punch1", nullable = true, length = 5)
    private String machinePunch1;

    @Column(name = "machine_punch2", nullable = true, length = 5)
    private String machinePunch2;

    @Column(name = "man_punch1", nullable = true, length = 5)
    private String manPunch1;

    @Column(name = "man_punch2", nullable = true, length = 5)
    private String manPunch2;

    @Column(name = "bonus_sal", nullable = true, precision = 0)
    private Double bonusSal;

    @Column(name = "basic_rate", nullable = true, precision = 0)
    private Double basicRate;

    @Column(name = "conv", nullable = true, precision = 0)
    private Double conv;

    @Column(name = "m_no", nullable = true, precision = 0)
    private Double mNo;

    @Column(name = "coff", nullable = true, precision = 0)
    private Double coff;

    @Column(name = "hr_2", nullable = true, precision = 0)
    private Double hr2;

    @Column(name = "sft_mis", nullable = true)
    private Integer sftMis;

    @Column(name = "gross", nullable = true, precision = 0)
    private Double gross;

    @Column(name = "gross_rate", nullable = true, precision = 0)
    private Double grossRate;

    @Column(name = "basic_rate1", nullable = true, precision = 0)
    private Double basicRate1;

    @Column(name = "basic_sal1", nullable = true, precision = 0)
    private Double basicSal1;

    @Column(name = "m_txt", nullable = true, length = 50)
    private String mTxt;

    @Column(name = "othr_50", nullable = true, precision = 0)
    private Double othr50;

    @Column(name = "prv_othr", nullable = true, precision = 0)
    private Double prvOthr;

    public DayStatusId getId() { return id; }

    public void setId(DayStatusId id) { this.id = id; }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLv() {
        return lv;
    }

    public void setLv(String lv) {
        this.lv = lv;
    }

    public Double getOthr() {
        return othr;
    }

    public void setOthr(Double othr) {
        this.othr = othr;
    }

    public Double getTothr() {
        return tothr;
    }

    public void setTothr(Double tothr) {
        this.tothr = tothr;
    }

    public Double getLate() {
        return late;
    }

    public void setLate(Double late) {
        this.late = late;
    }

    public String getInTm() {
        return inTm;
    }

    public void setInTm(String inTm) {
        this.inTm = inTm;
    }

    public String getOutTm() {
        return outTm;
    }

    public void setOutTm(String outTm) {
        this.outTm = outTm;
    }

    public Double getSal() {
        return sal;
    }

    public void setSal(Double sal) {
        this.sal = sal;
    }

    public Double getOtAmt() {
        return otAmt;
    }

    public void setOtAmt(Double otAmt) {
        this.otAmt = otAmt;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public Integer getWoHd() {
        return woHd;
    }

    public void setWoHd(Integer woHd) {
        this.woHd = woHd;
    }

    public Double getPday() {
        return pday;
    }

    public void setPday(Double pday) {
        this.pday = pday;
    }

    public Double getWday() {
        return wday;
    }

    public void setWday(Double wday) {
        this.wday = wday;
    }


    public Double getBefIn() {
        return befIn;
    }

    public void setBefIn(Double befIn) {
        this.befIn = befIn;
    }

    public Double getEl() {
        return el;
    }

    public void setEl(Double el) {
        this.el = el;
    }

    public Double getCl() {
        return cl;
    }

    public void setCl(Double cl) {
        this.cl = cl;
    }

    public Double getSl() {
        return sl;
    }

    public void setSl(Double sl) {
        this.sl = sl;
    }

    public Double getCo() {
        return co;
    }

    public void setCo(Double co) {
        this.co = co;
    }

    public Double getMl() {
        return ml;
    }

    public void setMl(Double ml) {
        this.ml = ml;
    }

    public Double getSp() {
        return sp;
    }

    public void setSp(Double sp) {
        this.sp = sp;
    }

    public Double getOutLate() {
        return outLate;
    }

    public void setOutLate(Double outLate) {
        this.outLate = outLate;
    }

    public Double getFoodAmt() {
        return foodAmt;
    }

    public void setFoodAmt(Double foodAmt) {
        this.foodAmt = foodAmt;
    }

    public Double getTeaAmt() {
        return teaAmt;
    }

    public void setTeaAmt(Double teaAmt) {
        this.teaAmt = teaAmt;
    }

    public Double getPl() {
        return pl;
    }

    public void setPl(Double pl) {
        this.pl = pl;
    }

    public Double getBasicSal() {
        return basicSal;
    }

    public void setBasicSal(Double basicSal) {
        this.basicSal = basicSal;
    }

    public Double getEs() {
        return es;
    }

    public void setEs(Double es) {
        this.es = es;
    }

    public String getInDt() {
        return inDt;
    }

    public void setInDt(String inDt) {
        this.inDt = inDt;
    }

    public String getOutDt() {
        return outDt;
    }

    public void setOutDt(String outDt) {
        this.outDt = outDt;
    }
    
    public Double getOtHr16() {
        return otHr16;
    }

    public void setOtHr16(Double otHr16) {
        this.otHr16 = otHr16;
    }

    public String getMachinePunch1() {
        return machinePunch1;
    }

    public void setMachinePunch1(String machinePunch1) {
        this.machinePunch1 = machinePunch1;
    }

    public String getMachinePunch2() {
        return machinePunch2;
    }

    public void setMachinePunch2(String machinePunch2) {
        this.machinePunch2 = machinePunch2;
    }

    public String getManPunch1() {
        return manPunch1;
    }

    public void setManPunch1(String manPunch1) {
        this.manPunch1 = manPunch1;
    }

    public String getManPunch2() {
        return manPunch2;
    }

    public void setManPunch2(String manPunch2) {
        this.manPunch2 = manPunch2;
    }

    public Double getBonusSal() {
        return bonusSal;
    }

    public void setBonusSal(Double bonusSal) {
        this.bonusSal = bonusSal;
    }

    public Double getBasicRate() {
        return basicRate;
    }

    public void setBasicRate(Double basicRate) {
        this.basicRate = basicRate;
    }

    public Double getConv() {
        return conv;
    }

    public void setConv(Double conv) {
        this.conv = conv;
    }

    public Double getmNo() {
        return mNo;
    }

    public void setmNo(Double mNo) {
        this.mNo = mNo;
    }

    public Double getCoff() {
        return coff;
    }

    public void setCoff(Double coff) {
        this.coff = coff;
    }

    public Double getHr2() {
        return hr2;
    }

    public void setHr2(Double hr2) {
        this.hr2 = hr2;
    }

    public Integer getSftMis() {
        return sftMis;
    }

    public void setSftMis(Integer sftMis) {
        this.sftMis = sftMis;
    }

    public Double getGross() {
        return gross;
    }

    public void setGross(Double gross) {
        this.gross = gross;
    }

    public Double getGrossRate() {
        return grossRate;
    }

    public void setGrossRate(Double grossRate) {
        this.grossRate = grossRate;
    }

    public Double getBasicRate1() {
        return basicRate1;
    }

    public void setBasicRate1(Double basicRate1) {
        this.basicRate1 = basicRate1;
    }

    public Double getBasicSal1() {
        return basicSal1;
    }

    public void setBasicSal1(Double basicSal1) {
        this.basicSal1 = basicSal1;
    }

    public String getmTxt() {
        return mTxt;
    }

    public void setmTxt(String mTxt) {
        this.mTxt = mTxt;
    }

    public Double getOthr50() {
        return othr50;
    }

    public void setOthr50(Double othr50) {
        this.othr50 = othr50;
    }

    public Double getPrvOthr() {
        return prvOthr;
    }

    public void setPrvOthr(Double prvOthr) {
        this.prvOthr = prvOthr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DayStatus dayStatus = (DayStatus) o;
        return Objects.equals(id.getEmpCode(), dayStatus.id.getEmpCode()) &&
                Objects.equals(id.getDayno(), dayStatus.id.getDayno()) &&
                Objects.equals(status, dayStatus.status) &&
                Objects.equals(lv, dayStatus.lv) &&
                Objects.equals(othr, dayStatus.othr) &&
                Objects.equals(tothr, dayStatus.tothr) &&
                Objects.equals(late, dayStatus.late) &&
                Objects.equals(inTm, dayStatus.inTm) &&
                Objects.equals(outTm, dayStatus.outTm) &&
                Objects.equals(sal, dayStatus.sal) &&
                Objects.equals(otAmt, dayStatus.otAmt) &&
                Objects.equals(shift, dayStatus.shift) &&
                Objects.equals(woHd, dayStatus.woHd) &&
                Objects.equals(pday, dayStatus.pday) &&
                Objects.equals(wday, dayStatus.wday) &&
                Objects.equals(befIn, dayStatus.befIn) &&
                Objects.equals(el, dayStatus.el) &&
                Objects.equals(cl, dayStatus.cl) &&
                Objects.equals(sl, dayStatus.sl) &&
                Objects.equals(co, dayStatus.co) &&
                Objects.equals(ml, dayStatus.ml) &&
                Objects.equals(sp, dayStatus.sp) &&
                Objects.equals(outLate, dayStatus.outLate) &&
                Objects.equals(foodAmt, dayStatus.foodAmt) &&
                Objects.equals(teaAmt, dayStatus.teaAmt) &&
                Objects.equals(pl, dayStatus.pl) &&
                Objects.equals(basicSal, dayStatus.basicSal) &&
                Objects.equals(es, dayStatus.es) &&
                Objects.equals(inDt, dayStatus.inDt) &&
                Objects.equals(outDt, dayStatus.outDt) &&
                Objects.equals(otHr16, dayStatus.otHr16) &&
                Objects.equals(machinePunch1, dayStatus.machinePunch1) &&
                Objects.equals(machinePunch2, dayStatus.machinePunch2) &&
                Objects.equals(manPunch1, dayStatus.manPunch1) &&
                Objects.equals(manPunch2, dayStatus.manPunch2) &&
                Objects.equals(bonusSal, dayStatus.bonusSal) &&
                Objects.equals(basicRate, dayStatus.basicRate) &&
                Objects.equals(conv, dayStatus.conv) &&
                Objects.equals(mNo, dayStatus.mNo) &&
                Objects.equals(coff, dayStatus.coff) &&
                Objects.equals(hr2, dayStatus.hr2) &&
                Objects.equals(sftMis, dayStatus.sftMis) &&
                Objects.equals(gross, dayStatus.gross) &&
                Objects.equals(grossRate, dayStatus.grossRate) &&
                Objects.equals(basicRate1, dayStatus.basicRate1) &&
                Objects.equals(basicSal1, dayStatus.basicSal1) &&
                Objects.equals(mTxt, dayStatus.mTxt) &&
                Objects.equals(othr50, dayStatus.othr50) &&
                Objects.equals(prvOthr, dayStatus.prvOthr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id.getEmpCode(), id.getDayno(), status, lv, othr, tothr, late, inTm, outTm, sal, otAmt, shift, woHd, pday, wday, befIn, el, cl, sl, co, ml, sp, outLate, foodAmt, teaAmt, pl, basicSal, es, inDt, outDt, otHr16, machinePunch1, machinePunch2, manPunch1, manPunch2, bonusSal, basicRate, conv, mNo, coff, hr2, sftMis, gross, grossRate, basicRate1, basicSal1, mTxt, othr50, prvOthr);
    }
}
