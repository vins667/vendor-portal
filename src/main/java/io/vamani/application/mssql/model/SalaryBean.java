package io.vamani.application.mssql.model;

import java.io.Serializable;

public class SalaryBean implements Serializable {
    private String empCode;
    private Short monthNo;
    private String monthYear;
    private String displayMonthYear;
    private Double dayNo;
    private Double totSal;
    private Double totDed;
    private Double netSal;
    private Double arrAmt;

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public Short getMonthNo() {
        return monthNo;
    }

    public void setMonthNo(Short monthNo) {
        this.monthNo = monthNo;
    }

    public Double getDayNo() {
        return dayNo;
    }

    public void setDayNo(Double dayNo) {
        this.dayNo = dayNo;
    }

    public Double getTotSal() {
        return totSal;
    }

    public void setTotSal(Double totSal) {
        this.totSal = totSal;
    }

    public Double getTotDed() {
        return totDed;
    }

    public void setTotDed(Double totDed) {
        this.totDed = totDed;
    }

    public Double getNetSal() {
        return netSal;
    }

    public void setNetSal(Double netSal) {
        this.netSal = netSal;
    }

    public String getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }

    public String getDisplayMonthYear() {
        return displayMonthYear;
    }

    public void setDisplayMonthYear(String displayMonthYear) {
        this.displayMonthYear = displayMonthYear;
    }

    public Double getArrAmt() { return arrAmt; }

    public void setArrAmt(Double arrAmt) { this.arrAmt = arrAmt; }
}
