package io.vamani.application.domain;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class ManpowerBudgetEntryId implements Serializable {
    @NotNull
    @Size(max = 10)
    @Column(name = "fact_code", length = 10, nullable = false)
    private String factCode;

    @NotNull
    @Size(max = 10)
    @Column(name = "dept_code", length = 10, nullable = false)
    private String deptCode;

    @Column(name = "attendance_date", nullable = false)
    private Instant attendanceDate;

    @Size(max = 20)
    @Column(name = "attendance_type", length = 20, nullable = false)
    private String attendanceType;

    @NotNull
    @Size(max = 10)
    @Column(name = "desg_code", length = 10, nullable = false)
    private String desgCode;

    @NotNull
    @Size(max = 10)
    @Column(name = "subdesg_code", length = 10, nullable = false)
    private String subdesgCode;

    @NotNull
    @Size(max = 10)
    @Column(name = "skill_code", length = 10, nullable = false)
    private String skillCode;

    @NotNull
    @Size(max = 10)
    @Column(name = "line_no", length = 10, nullable = false)
    private String lineNo;

    public String getFactCode() {
        return factCode;
    }

    public void setFactCode(String factCode) {
        this.factCode = factCode;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public Instant getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(Instant attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getAttendanceType() {
        return attendanceType;
    }

    public void setAttendanceType(String attendanceType) {
        this.attendanceType = attendanceType;
    }

    public String getDesgCode() {
        return desgCode;
    }

    public void setDesgCode(String desgCode) {
        this.desgCode = desgCode;
    }

    public String getSubdesgCode() {
        return subdesgCode;
    }

    public void setSubdesgCode(String subdesgCode) {
        this.subdesgCode = subdesgCode;
    }

    public String getSkillCode() {
        return skillCode;
    }

    public void setSkillCode(String skillCode) {
        this.skillCode = skillCode;
    }

    public String getLineNo() {
        return lineNo;
    }

    public void setLineNo(String lineNo) {
        this.lineNo = lineNo;
    }

    public ManpowerBudgetEntryId() {
    }

    public ManpowerBudgetEntryId(@NotNull @Size(max = 10) String factCode, @NotNull @Size(max = 10) String deptCode, Instant attendanceDate, @Size(max = 20) String attendanceType, @NotNull @Size(max = 10) String desgCode, @NotNull @Size(max = 10) String subdesgCode, @NotNull @Size(max = 10) String skillCode, @NotNull @Size(max = 10) String lineNo) {
        this.factCode = factCode;
        this.deptCode = deptCode;
        this.attendanceDate = attendanceDate;
        this.attendanceType = attendanceType;
        this.desgCode = desgCode;
        this.subdesgCode = subdesgCode;
        this.skillCode = skillCode;
        this.lineNo = lineNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManpowerBudgetEntryId that = (ManpowerBudgetEntryId) o;
        return Objects.equals(factCode, that.factCode) && Objects.equals(deptCode, that.deptCode) && Objects.equals(attendanceDate, that.attendanceDate) && Objects.equals(attendanceType, that.attendanceType) && Objects.equals(desgCode, that.desgCode) && Objects.equals(subdesgCode, that.subdesgCode) && Objects.equals(skillCode, that.skillCode) && Objects.equals(lineNo, that.lineNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(factCode, deptCode, attendanceDate, attendanceType, desgCode, subdesgCode, skillCode, lineNo);
    }
}
