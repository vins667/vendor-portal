package io.vamani.application.model;

import java.util.Date;
import java.util.Objects;

public class CTCDetailBean {
    private String payCode;
    private String name;
    private String designation;
    private String dept;
    private String subDept;
    private String cadre;
    private Date doj;
    private String absent;
    private String paidDay;
    private Long basicRate;
    private Long grossRate;
    private Long earnBasic;
    private Long earnGross;
    private Long otHrs;
    private Long otAmt;
    private Long fooding;
    private String pf;
    private String esi;
    private Long pfAmt;
    private Long esiAmt;
    private Long ctc;

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getSubDept() {
        return subDept;
    }

    public void setSubDept(String subDept) {
        this.subDept = subDept;
    }

    public String getCadre() {
        return cadre;
    }

    public void setCadre(String cadre) {
        this.cadre = cadre;
    }

    public Date getDoj() {
        return doj;
    }

    public void setDoj(Date doj) {
        this.doj = doj;
    }

    public String getAbsent() {
        return absent;
    }

    public void setAbsent(String absent) {
        this.absent = absent;
    }

    public String getPaidDay() {
        return paidDay;
    }

    public void setPaidDay(String paidDay) {
        this.paidDay = paidDay;
    }

    public Long getBasicRate() {
        return basicRate;
    }

    public void setBasicRate(Long basicRate) {
        this.basicRate = basicRate;
    }

    public Long getGrossRate() {
        return grossRate;
    }

    public void setGrossRate(Long grossRate) {
        this.grossRate = grossRate;
    }

    public Long getEarnBasic() {
        return earnBasic;
    }

    public void setEarnBasic(Long earnBasic) {
        this.earnBasic = earnBasic;
    }

    public Long getEarnGross() {
        return earnGross;
    }

    public void setEarnGross(Long earnGross) {
        this.earnGross = earnGross;
    }

    public Long getOtHrs() {
        return otHrs;
    }

    public void setOtHrs(Long otHrs) {
        this.otHrs = otHrs;
    }

    public Long getOtAmt() {
        return otAmt;
    }

    public void setOtAmt(Long otAmt) {
        this.otAmt = otAmt;
    }

    public Long getFooding() {
        return fooding;
    }

    public void setFooding(Long fooding) {
        this.fooding = fooding;
    }

    public String getPf() {
        return pf;
    }

    public void setPf(String pf) {
        this.pf = pf;
    }

    public String getEsi() {
        return esi;
    }

    public void setEsi(String esi) {
        this.esi = esi;
    }

    public Long getPfAmt() {
        return pfAmt;
    }

    public void setPfAmt(Long pfAmt) {
        this.pfAmt = pfAmt;
    }

    public Long getEsiAmt() {
        return esiAmt;
    }

    public void setEsiAmt(Long esiAmt) {
        this.esiAmt = esiAmt;
    }

    public Long getCtc() {
        return ctc;
    }

    public void setCtc(Long ctc) {
        this.ctc = ctc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CTCDetailBean that = (CTCDetailBean) o;
        return Objects.equals(payCode, that.payCode) &&
            Objects.equals(name, that.name) &&
            Objects.equals(designation, that.designation) &&
            Objects.equals(dept, that.dept) &&
            Objects.equals(subDept, that.subDept) &&
            Objects.equals(cadre, that.cadre) &&
            Objects.equals(doj, that.doj) &&
            Objects.equals(absent, that.absent) &&
            Objects.equals(paidDay, that.paidDay) &&
            Objects.equals(basicRate, that.basicRate) &&
            Objects.equals(grossRate, that.grossRate) &&
            Objects.equals(earnBasic, that.earnBasic) &&
            Objects.equals(earnGross, that.earnGross) &&
            Objects.equals(otHrs, that.otHrs) &&
            Objects.equals(otAmt, that.otAmt) &&
            Objects.equals(fooding, that.fooding) &&
            Objects.equals(pf, that.pf) &&
            Objects.equals(esi, that.esi) &&
            Objects.equals(pfAmt, that.pfAmt) &&
            Objects.equals(esiAmt, that.esiAmt) &&
            Objects.equals(ctc, that.ctc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payCode, name, designation, dept, subDept, cadre, doj, absent, paidDay, basicRate, grossRate, earnBasic, earnGross, otHrs, otAmt, fooding, pf, esi, pfAmt, esiAmt, ctc);
    }

    @Override
    public String toString() {
        return "CTCDetailBean{" +
            "payCode='" + payCode + '\'' +
            ", name='" + name + '\'' +
            ", designation='" + designation + '\'' +
            ", dept='" + dept + '\'' +
            ", subDept='" + subDept + '\'' +
            ", cadre='" + cadre + '\'' +
            ", doj='" + doj + '\'' +
            ", absent='" + absent + '\'' +
            ", paidDay='" + paidDay + '\'' +
            ", basicRate=" + basicRate +
            ", grossRate=" + grossRate +
            ", earnBasic=" + earnBasic +
            ", earnGross=" + earnGross +
            ", otHrs=" + otHrs +
            ", otAmt=" + otAmt +
            ", fooding=" + fooding +
            ", pf='" + pf + '\'' +
            ", esi='" + esi + '\'' +
            ", pfAmt=" + pfAmt +
            ", esiAmt=" + esiAmt +
            ", ctc=" + ctc +
            '}';
    }
}
