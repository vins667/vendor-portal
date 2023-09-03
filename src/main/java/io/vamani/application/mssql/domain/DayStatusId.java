package io.vamani.application.mssql.domain;

import javax.persistence.Column;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class DayStatusId implements Serializable {

    @Column(name = "emp_code", nullable = true)
    private String empCode;

    @Column(name = "dayno", nullable = true)
    private Timestamp dayno;

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public Timestamp getDayno() {
        return dayno;
    }

    public void setDayno(Timestamp dayno) {
        this.dayno = dayno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DayStatusId)) return false;
        DayStatusId that = (DayStatusId) o;
        return getEmpCode().equals(that.getEmpCode()) &&
            getDayno().equals(that.getDayno());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmpCode(), getDayno());
    }

    @Override
    public String toString() {
        return "DayStatusId{" +
            "empCode='" + empCode + '\'' +
            ", dayno=" + dayno +
            '}';
    }
}
