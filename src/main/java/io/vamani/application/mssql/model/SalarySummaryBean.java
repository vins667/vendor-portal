package io.vamani.application.mssql.model;

import java.sql.Timestamp;

public class SalarySummaryBean {
    private String month;
    private String cardNo;
    private String name;
    private Timestamp doj;
    private String panNo;
    private Double paidDays;
    private Double basic;
    private Double hra;
    private Double convAllow;
    private Double others;
    private Double splAllow;
    private Double medical;
    private Double arrear;
    private Double grossSalary;
    private Double pf;
    private Double vpf;
    private Double lwf;
    private Double tds;
    private Double netSalary;
    private String factoryDesc;
    private String subCodeDesc;
    private Timestamp rdate;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getDoj() {
        return doj;
    }

    public void setDoj(Timestamp doj) {
        this.doj = doj;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public Double getPaidDays() {
        return paidDays;
    }

    public void setPaidDays(Double paidDays) {
        this.paidDays = paidDays;
    }

    public Double getBasic() {
        return basic;
    }

    public void setBasic(Double basic) {
        this.basic = basic;
    }

    public Double getHra() {
        return hra;
    }

    public void setHra(Double hra) {
        this.hra = hra;
    }

    public Double getConvAllow() {
        return convAllow;
    }

    public void setConvAllow(Double convAllow) {
        this.convAllow = convAllow;
    }

    public Double getOthers() {
        return others;
    }

    public void setOthers(Double others) {
        this.others = others;
    }

    public Double getSplAllow() {
        return splAllow;
    }

    public void setSplAllow(Double splAllow) {
        this.splAllow = splAllow;
    }

    public Double getMedical() {
        return medical;
    }

    public void setMedical(Double medical) {
        this.medical = medical;
    }

    public Double getArrear() {
        return arrear;
    }

    public void setArrear(Double arrear) {
        this.arrear = arrear;
    }

    public Double getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(Double grossSalary) {
        this.grossSalary = grossSalary;
    }

    public Double getPf() {
        return pf;
    }

    public void setPf(Double pf) {
        this.pf = pf;
    }

    public Double getVpf() {
        return vpf;
    }

    public void setVpf(Double vpf) {
        this.vpf = vpf;
    }

    public Double getTds() {
        return tds;
    }

    public void setTds(Double tds) {
        this.tds = tds;
    }

    public Double getNetSalary() {
        return netSalary;
    }

    public Double getLwf() {
        return lwf;
    }

    public void setLwf(Double lwf) {
        this.lwf = lwf;
    }

    public void setNetSalary(Double netSalary) {
        this.netSalary = netSalary;
    }

    public String getFactoryDesc() {
        return factoryDesc;
    }

    public void setFactoryDesc(String factoryDesc) {
        this.factoryDesc = factoryDesc;
    }

    public String getSubCodeDesc() {
        return subCodeDesc;
    }

    public void setSubCodeDesc(String subCodeDesc) {
        this.subCodeDesc = subCodeDesc;
    }

    public Timestamp getRdate() {
        return rdate;
    }

    public void setRdate(Timestamp rdate) {
        this.rdate = rdate;
    }
}
