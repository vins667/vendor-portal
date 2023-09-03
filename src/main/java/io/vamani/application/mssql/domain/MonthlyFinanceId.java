package io.vamani.application.mssql.domain;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class MonthlyFinanceId implements Serializable {
    @Column(name = "emp_code", nullable = true)
    private String empCode;

    @Column(name = "month_no", nullable = true)
    private Short monthNo;

    @Column(name = "month_year", nullable = true)
    private String monthYear;

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

    public String getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MonthlyFinanceId)) return false;
        MonthlyFinanceId monthlyId = (MonthlyFinanceId) o;
        return getEmpCode().equals(monthlyId.getEmpCode()) &&
            getMonthNo().equals(monthlyId.getMonthNo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmpCode(), getMonthNo());
    }

    @Override
    public String toString() {
        return "MonthlyId{" +
            "empCode=" + empCode +
            ", monthNo=" + monthNo +
            '}';
    }
}
