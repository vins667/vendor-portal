package io.vamani.application.mssql.domain;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class TimetId implements Serializable {
    private String empCode;
    private Short monthNo;
    private String monthYear;

    @Column(name = "emp_code")
    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    @Column(name = "month_no")
    public Short getMonthNo() {
        return monthNo;
    }

    public void setMonthNo(Short monthNo) {
        this.monthNo = monthNo;
    }

    @Column(name = "month_year", nullable = true)

    public String getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimetId)) return false;
        TimetId timetId = (TimetId) o;
        return Objects.equals(getEmpCode(), timetId.getEmpCode()) &&
            Objects.equals(getMonthNo(), timetId.getMonthNo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmpCode(), getMonthNo());
    }

    @Override
    public String toString() {
        return "TimetId{" +
            "empCode='" + empCode + '\'' +
            ", monthNo=" + monthNo +
            '}';
    }
}
