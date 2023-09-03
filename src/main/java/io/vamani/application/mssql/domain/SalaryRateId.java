package io.vamani.application.mssql.domain;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class SalaryRateId implements Serializable {
    @Column(name = "emp_code", nullable = true)
    private String empCode;

    @Column(name = "month_no", nullable = true)
    private Short monthNo;

    @Column(name = "yr", nullable = true)
    private Short yr;

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

    public Short getYr() {
        return yr;
    }

    public void setYr(Short yr) {
        this.yr = yr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalaryRateId that = (SalaryRateId) o;
        return Objects.equals(empCode, that.empCode) &&
            Objects.equals(monthNo, that.monthNo) &&
            Objects.equals(yr, that.yr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empCode, monthNo, yr);
    }

    @Override
    public String toString() {
        return "SalaryRateId{" +
            "empCode='" + empCode + '\'' +
            ", monthNo=" + monthNo +
            ", yr=" + yr +
            '}';
    }
}
