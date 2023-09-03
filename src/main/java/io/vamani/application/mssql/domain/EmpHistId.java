package io.vamani.application.mssql.domain;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class EmpHistId implements Serializable {

    @Column(name = "emp_code")
    private String empCode;

    @Column(name = "month_year", nullable = true)
    private String monthYear;

    public String getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpHistId empHistId = (EmpHistId) o;
        return Objects.equals(empCode, empHistId.empCode) &&
            Objects.equals(monthYear, empHistId.monthYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empCode, monthYear);
    }

    @Override
    public String toString() {
        return "EmpHistId{" +
            "empCode='" + empCode + '\'' +
            ", monthYear='" + monthYear + '\'' +
            '}';
    }
}
