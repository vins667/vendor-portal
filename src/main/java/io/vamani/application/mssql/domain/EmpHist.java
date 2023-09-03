package io.vamani.application.mssql.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "emp_hist_view")
public class EmpHist {

    @EmbeddedId
    private EmpHistId id;

    @Column(name = "month_no")
    private Short monthNo;

    @Column(name = "dep_code", nullable = true)
    private Integer depCode;

    @Column(name = "des_code", nullable = true)
    private Integer desCode;

    @Column(name = "cat_code", nullable = true)
    private Short catCode;

    @Column(name = "onoff", nullable = true)
    private Short onoff;

    @Column(name = "bank_code", nullable = true)
    private Short bankCode;

    @Column(name = "pay_mode", nullable = true)
    private Short payMode;

    @Column(name = "sub_code", nullable = true)
    private Short subCode;

    @Column(name = "wday", nullable = true, precision = 0)
    private Double wday;

    @Column(name = "el", nullable = true, precision = 0)
    private Double el;

    @Column(name = "cl", nullable = true, precision = 0)
    private Double cl;

    @Column(name = "sl", nullable = true, precision = 0)
    private Double sl;

    @Column(name = "sp", nullable = true, precision = 0)
    private Double sp;

    @Column(name = "ml", nullable = true, precision = 0)
    private Double ml;

    @Column(name = "pl", nullable = true, precision = 0)
    private Double pl;

    @Column(name = "coff", nullable = true, precision = 0)
    private Double coff;

    @Column(name = "pday", nullable = true, precision = 0)
    private Double pday;

    @Column(name = "ot", nullable = true)
    private Short ot;

    @Column(name = "bel", nullable = true, precision = 0)
    private Double bel;

    @Column(name = "bcl", nullable = true, precision = 0)
    private Double bcl;

    @Column(name = "bsl", nullable = true, precision = 0)
    private Double bsl;

    @Column(name = "bpl", nullable = true, precision = 0)
    private Double bpl;

    @Column(name = "bml", nullable = true, precision = 0)
    private Double bml;

    @Column(name = "bcoff", nullable = true, precision = 0)
    private Double bcoff;

    @Column(name = "bsp", nullable = true, precision = 0)
    private Double bsp;

    @Column(name = "wf", nullable = true, precision = 0)
    private Double wf;

    @Column(name = "hd", nullable = true, precision = 0)
    private Double hd;

    @Column(name = "c_wday", nullable = true, precision = 0)
    private Double cWday;

    @Column(name = "ab", nullable = true, precision = 0)
    private Double ab;

    @Column(name = "lof", nullable = true, precision = 0)
    private Double lof;

    @Column(name = "fst_15day", nullable = true, precision = 0)
    private Double fst15Day;

    @Column(name = "snd_15day", nullable = true, precision = 0)
    private Double snd15Day;

    @Column(name = "m_wd", nullable = true, precision = 0)
    private Double mWd;

    @Column(name = "m_wf", nullable = true, precision = 0)
    private Double mWf;

    @Column(name = "m_hd", nullable = true, precision = 0)
    private Double mHd;

    @Column(name = "m_el", nullable = true, precision = 0)
    private Double mEl;

    @Column(name = "m_cl", nullable = true, precision = 0)
    private Double mCl;

    @Column(name = "m_sl", nullable = true, precision = 0)
    private Double mSl;

    @Column(name = "m_co", nullable = true, precision = 0)
    private Double mCo;

    @Column(name = "m_od", nullable = true, precision = 0)
    private Double mOd;

    @Column(name = "m_pd", nullable = true, precision = 0)
    private Double mPd;

    @Column(name = "m_intive", nullable = true, precision = 0)
    private Double mIntive;

    @Column(name = "el_encash", nullable = true, precision = 0)
    private Double elEncash;

    @Column(name = "el_encash_amt", nullable = true, precision = 0)
    private Double elEncashAmt;

    @Column(name = "es", nullable = true, precision = 0)
    private Double es;

    @Column(name = "earn_co", nullable = true, precision = 0)
    private Double earnCo;

    @Column(name = "earn_el", nullable = true, precision = 0)
    private Double earnEl;

    @Column(name = "earn_cl", nullable = true, precision = 0)
    private Double earnCl;

    @Column(name = "earn_sl", nullable = true, precision = 0)
    private Double earnSl;

    @Column(name = "bank_no", nullable = true, length = 20)
    private String bankNo;

    @Column(name = "cost_code", nullable = true)
    private Integer costCode;

    @Column(name = "sec_code", nullable = true)
    private Integer secCode;

    @Column(name = "bel1", nullable = true, precision = 0)
    private Double bel1;

    @Column(name = "bcl1", nullable = true, precision = 0)
    private Double bcl1;

    @Column(name = "bsl1", nullable = true, precision = 0)
    private Double bsl1;

    @Column(name = "bml1", nullable = true, precision = 0)
    private Double bml1;

    @Column(name = "bpl1", nullable = true, precision = 0)
    private Double bpl1;

    @Column(name = "bcoff1", nullable = true, precision = 0)
    private Double bcoff1;

    @Column(name = "bsp1", nullable = true, precision = 0)
    private Double bsp1;

    @Column(name = "bus_p", nullable = true, precision = 0)
    private Double busP;

    public Short getMonthNo() {
        return monthNo;
    }

    public void setMonthNo(Short monthNo) {
        this.monthNo = monthNo;
    }

    public EmpHistId getId() { return id; }

    public void setId(EmpHistId id) { this.id = id; }

    public Integer getDepCode() {
        return depCode;
    }

    public void setDepCode(Integer depCode) {
        this.depCode = depCode;
    }

    public Integer getDesCode() {
        return desCode;
    }

    public void setDesCode(Integer desCode) {
        this.desCode = desCode;
    }

    public Short getCatCode() {
        return catCode;
    }

    public void setCatCode(Short catCode) {
        this.catCode = catCode;
    }

    public Short getOnoff() {
        return onoff;
    }

    public void setOnoff(Short onoff) {
        this.onoff = onoff;
    }

    public Short getBankCode() {
        return bankCode;
    }

    public void setBankCode(Short bankCode) {
        this.bankCode = bankCode;
    }

    public Short getPayMode() {
        return payMode;
    }

    public void setPayMode(Short payMode) {
        this.payMode = payMode;
    }

    public Short getSubCode() {
        return subCode;
    }

    public void setSubCode(Short subCode) {
        this.subCode = subCode;
    }

    public Double getWday() {
        return wday;
    }

    public void setWday(Double wday) {
        this.wday = wday;
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

    public Double getSp() {
        return sp;
    }

    public void setSp(Double sp) {
        this.sp = sp;
    }

    public Double getMl() {
        return ml;
    }

    public void setMl(Double ml) {
        this.ml = ml;
    }

    public Double getPl() {
        return pl;
    }

    public void setPl(Double pl) {
        this.pl = pl;
    }

    public Double getCoff() {
        return coff;
    }

    public void setCoff(Double coff) {
        this.coff = coff;
    }

    public Double getPday() {
        return pday;
    }

    public void setPday(Double pday) {
        this.pday = pday;
    }

    public Short getOt() {
        return ot;
    }

    public void setOt(Short ot) {
        this.ot = ot;
    }

    public Double getBel() {
        return bel;
    }

    public void setBel(Double bel) {
        this.bel = bel;
    }

    public Double getBcl() {
        return bcl;
    }

    public void setBcl(Double bcl) {
        this.bcl = bcl;
    }

    public Double getBsl() {
        return bsl;
    }

    public void setBsl(Double bsl) {
        this.bsl = bsl;
    }

    public Double getBpl() {
        return bpl;
    }

    public void setBpl(Double bpl) {
        this.bpl = bpl;
    }

    public Double getBml() {
        return bml;
    }

    public void setBml(Double bml) {
        this.bml = bml;
    }

    public Double getBcoff() {
        return bcoff;
    }

    public void setBcoff(Double bcoff) {
        this.bcoff = bcoff;
    }

    public Double getBsp() {
        return bsp;
    }

    public void setBsp(Double bsp) {
        this.bsp = bsp;
    }

    public Double getWf() {
        return wf;
    }

    public void setWf(Double wf) {
        this.wf = wf;
    }

    public Double getHd() {
        return hd;
    }

    public void setHd(Double hd) {
        this.hd = hd;
    }

    public Double getcWday() {
        return cWday;
    }

    public void setcWday(Double cWday) {
        this.cWday = cWday;
    }

    public Double getAb() {
        return ab;
    }

    public void setAb(Double ab) {
        this.ab = ab;
    }

    public Double getLof() {
        return lof;
    }

    public void setLof(Double lof) {
        this.lof = lof;
    }

    public Double getFst15Day() {
        return fst15Day;
    }

    public void setFst15Day(Double fst15Day) {
        this.fst15Day = fst15Day;
    }

    public Double getSnd15Day() {
        return snd15Day;
    }

    public void setSnd15Day(Double snd15Day) {
        this.snd15Day = snd15Day;
    }

    public Double getmWd() {
        return mWd;
    }

    public void setmWd(Double mWd) {
        this.mWd = mWd;
    }

    public Double getmWf() {
        return mWf;
    }

    public void setmWf(Double mWf) {
        this.mWf = mWf;
    }

    public Double getmHd() {
        return mHd;
    }

    public void setmHd(Double mHd) {
        this.mHd = mHd;
    }

    public Double getmEl() {
        return mEl;
    }

    public void setmEl(Double mEl) {
        this.mEl = mEl;
    }

    public Double getmCl() {
        return mCl;
    }

    public void setmCl(Double mCl) {
        this.mCl = mCl;
    }

    public Double getmSl() {
        return mSl;
    }

    public void setmSl(Double mSl) {
        this.mSl = mSl;
    }

    public Double getmCo() {
        return mCo;
    }

    public void setmCo(Double mCo) {
        this.mCo = mCo;
    }

    public Double getmOd() {
        return mOd;
    }

    public void setmOd(Double mOd) {
        this.mOd = mOd;
    }

    public Double getmPd() {
        return mPd;
    }

    public void setmPd(Double mPd) {
        this.mPd = mPd;
    }

    public Double getmIntive() {
        return mIntive;
    }

    public void setmIntive(Double mIntive) {
        this.mIntive = mIntive;
    }

    public Double getElEncash() {
        return elEncash;
    }

    public void setElEncash(Double elEncash) {
        this.elEncash = elEncash;
    }

    public Double getElEncashAmt() {
        return elEncashAmt;
    }

    public void setElEncashAmt(Double elEncashAmt) {
        this.elEncashAmt = elEncashAmt;
    }

    public Double getEs() {
        return es;
    }

    public void setEs(Double es) {
        this.es = es;
    }

    public Double getEarnCo() {
        return earnCo;
    }

    public void setEarnCo(Double earnCo) {
        this.earnCo = earnCo;
    }

    public Double getEarnEl() {
        return earnEl;
    }

    public void setEarnEl(Double earnEl) {
        this.earnEl = earnEl;
    }

    public Double getEarnCl() {
        return earnCl;
    }

    public void setEarnCl(Double earnCl) {
        this.earnCl = earnCl;
    }

    public Double getEarnSl() {
        return earnSl;
    }

    public void setEarnSl(Double earnSl) {
        this.earnSl = earnSl;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public Integer getCostCode() {
        return costCode;
    }

    public void setCostCode(Integer costCode) {
        this.costCode = costCode;
    }

    public Integer getSecCode() {
        return secCode;
    }

    public void setSecCode(Integer secCode) {
        this.secCode = secCode;
    }

    public Double getBel1() {
        return bel1;
    }

    public void setBel1(Double bel1) {
        this.bel1 = bel1;
    }

    public Double getBcl1() {
        return bcl1;
    }

    public void setBcl1(Double bcl1) {
        this.bcl1 = bcl1;
    }

    public Double getBsl1() {
        return bsl1;
    }

    public void setBsl1(Double bsl1) {
        this.bsl1 = bsl1;
    }

    public Double getBml1() {
        return bml1;
    }

    public void setBml1(Double bml1) {
        this.bml1 = bml1;
    }

    public Double getBpl1() {
        return bpl1;
    }

    public void setBpl1(Double bpl1) {
        this.bpl1 = bpl1;
    }

    public Double getBcoff1() {
        return bcoff1;
    }

    public void setBcoff1(Double bcoff1) {
        this.bcoff1 = bcoff1;
    }

    public Double getBsp1() {
        return bsp1;
    }

    public void setBsp1(Double bsp1) {
        this.bsp1 = bsp1;
    }

    public Double getBusP() {
        return busP;
    }

    public void setBusP(Double busP) {
        this.busP = busP;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpHist empHist = (EmpHist) o;
        return Objects.equals(depCode, empHist.depCode) &&
                Objects.equals(desCode, empHist.desCode) &&
                Objects.equals(catCode, empHist.catCode) &&
                Objects.equals(onoff, empHist.onoff) &&
                Objects.equals(bankCode, empHist.bankCode) &&
                Objects.equals(payMode, empHist.payMode) &&
                Objects.equals(subCode, empHist.subCode) &&
                Objects.equals(wday, empHist.wday) &&
                Objects.equals(el, empHist.el) &&
                Objects.equals(cl, empHist.cl) &&
                Objects.equals(sl, empHist.sl) &&
                Objects.equals(sp, empHist.sp) &&
                Objects.equals(ml, empHist.ml) &&
                Objects.equals(pl, empHist.pl) &&
                Objects.equals(coff, empHist.coff) &&
                Objects.equals(pday, empHist.pday) &&
                Objects.equals(ot, empHist.ot) &&
                Objects.equals(bel, empHist.bel) &&
                Objects.equals(bcl, empHist.bcl) &&
                Objects.equals(bsl, empHist.bsl) &&
                Objects.equals(bpl, empHist.bpl) &&
                Objects.equals(bml, empHist.bml) &&
                Objects.equals(bcoff, empHist.bcoff) &&
                Objects.equals(bsp, empHist.bsp) &&
                Objects.equals(wf, empHist.wf) &&
                Objects.equals(hd, empHist.hd) &&
                Objects.equals(cWday, empHist.cWday) &&
                Objects.equals(ab, empHist.ab) &&
                Objects.equals(lof, empHist.lof) &&
                Objects.equals(fst15Day, empHist.fst15Day) &&
                Objects.equals(snd15Day, empHist.snd15Day) &&
                Objects.equals(mWd, empHist.mWd) &&
                Objects.equals(mWf, empHist.mWf) &&
                Objects.equals(mHd, empHist.mHd) &&
                Objects.equals(mEl, empHist.mEl) &&
                Objects.equals(mCl, empHist.mCl) &&
                Objects.equals(mSl, empHist.mSl) &&
                Objects.equals(mCo, empHist.mCo) &&
                Objects.equals(mOd, empHist.mOd) &&
                Objects.equals(mPd, empHist.mPd) &&
                Objects.equals(mIntive, empHist.mIntive) &&
                Objects.equals(elEncash, empHist.elEncash) &&
                Objects.equals(elEncashAmt, empHist.elEncashAmt) &&
                Objects.equals(es, empHist.es) &&
                Objects.equals(earnCo, empHist.earnCo) &&
                Objects.equals(earnEl, empHist.earnEl) &&
                Objects.equals(earnCl, empHist.earnCl) &&
                Objects.equals(earnSl, empHist.earnSl) &&
                Objects.equals(bankNo, empHist.bankNo) &&
                Objects.equals(costCode, empHist.costCode) &&
                Objects.equals(secCode, empHist.secCode) &&
                Objects.equals(bel1, empHist.bel1) &&
                Objects.equals(bcl1, empHist.bcl1) &&
                Objects.equals(bsl1, empHist.bsl1) &&
                Objects.equals(bml1, empHist.bml1) &&
                Objects.equals(bpl1, empHist.bpl1) &&
                Objects.equals(bcoff1, empHist.bcoff1) &&
                Objects.equals(bsp1, empHist.bsp1) &&
                Objects.equals(busP, empHist.busP);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id.getEmpCode(), id.getMonthYear(), depCode, desCode, catCode, onoff, bankCode, payMode, subCode, wday, el, cl, sl, sp, ml, pl, coff, pday, ot, bel, bcl, bsl, bpl, bml, bcoff, bsp, wf, hd, cWday, ab, lof, fst15Day, snd15Day, mWd, mWf, mHd, mEl, mCl, mSl, mCo, mOd, mPd, mIntive, elEncash, elEncashAmt, es, earnCo, earnEl, earnCl, earnSl, bankNo, costCode, secCode, bel1, bcl1, bsl1, bml1, bpl1, bcoff1, bsp1, busP);
    }
}
