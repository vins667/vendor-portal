package io.vamani.application.mssql.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class HourtId implements Serializable {
    @Column(name = "emp_code", nullable = false)
    private Integer empCode;

    @Column(name = "month_no", nullable = false)
    private Short monthNo;

    public Integer getEmpCode() {
        return empCode;
    }

    public void setEmpCode(Integer empCode) {
        this.empCode = empCode;
    }

    public Short getMonthNo() {
        return monthNo;
    }

    public void setMonthNo(Short monthNo) {
        this.monthNo = monthNo;
    }

    public HourtId() {
    }

    public HourtId(Integer empCode, Short monthNo) {
        this.empCode = empCode;
        this.monthNo = monthNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HourtId hourtId = (HourtId) o;
        return Objects.equals(empCode, hourtId.empCode) &&
            Objects.equals(monthNo, hourtId.monthNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empCode, monthNo);
    }

    @Override
    public String toString() {
        return "HourtId{" +
            "empCode=" + empCode +
            ", monthNo=" + monthNo +
            '}';
    }
}
