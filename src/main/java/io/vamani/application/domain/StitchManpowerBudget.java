package io.vamani.application.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A Suggestion.
 */
@Entity
@Table(name = "stitch_manpower_budget")
public class StitchManpowerBudget implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="stitchManpowerBudgetSeq", sequenceName="stitch_manpower_budget_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="stitchManpowerBudgetSeq")
    private Long id;

    @NotNull
    @Size(max = 10)
    @Column(name = "fact_code", length = 10, nullable = false)
    private String factCode;

    @NotNull
    @Size(max = 10)
    @Column(name = "now_fact_code", length = 10, nullable = false)
    private String nowFactCode;

    @Size(max = 100)
    @Column(name = "fact_desc", length = 100, nullable = false)
    private String factDesc;

    @NotNull
    @Size(max = 10)
    @Column(name = "dept_code", length = 10, nullable = false)
    private String deptCode;

    @Size(max = 100)
    @Column(name = "dept_desc", length = 100, nullable = false)
    private String deptDesc;

    @Column(name = "attendance_date", nullable = false)
    private Instant attendanceDate;

    @Size(max = 20)
    @Column(name = "attendance_type", length = 20, nullable = false)
    private String attendanceType;

    @NotNull
    @Size(max = 10)
    @Column(name = "desg_code", length = 10, nullable = false)
    private String desgCode;

    @Size(max = 100)
    @Column(name = "desg_desc", length = 100, nullable = false)
    private String desgDesc;

    @NotNull
    @Size(max = 10)
    @Column(name = "subdesg_code", length = 10, nullable = false)
    private String subdesgCode;

    @Size(max = 100)
    @Column(name = "subdesg_desc", length = 100, nullable = false)
    private String subdesgDesc;

    @NotNull
    @Size(max = 10)
    @Column(name = "skill_code", length = 10, nullable = false)
    private String skillCode;

    @Size(max = 100)
    @Column(name = "skill_desc", length = 100, nullable = false)
    private String skillDesc;

    @Column(name = "present_count")
    private Long presentCount;

    @Column(name = "actual_present_count")
    private Long actualPresentCount;

    @Column(name = "balanace_count")
    private Long balanaceCount;

    @NotNull
    @Size(max = 10)
    @Column(name = "line_no", length = 10, nullable = false)
    private String lineNo;

    @Size(max = 100)
    @Column(name = "line_desc", length = 100, nullable = false)
    private String lineDesc;

    @Column(name = "line_count")
    private Long lineCount;

    @Size(max = 10)
    @Column(name = "flag", length = 1)
    private String flag;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Size(max = 50)
    @Column(name = "approved_by", length = 50)
    private String approvedBy;

    @Column(name = "approved_date")
    private Instant approvedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFactCode() {
        return factCode;
    }

    public void setFactCode(String factCode) {
        this.factCode = factCode;
    }

    public String getNowFactCode() {
        return nowFactCode;
    }

    public void setNowFactCode(String nowFactCode) {
        this.nowFactCode = nowFactCode;
    }

    public String getFactDesc() {
        return factDesc;
    }

    public void setFactDesc(String factDesc) {
        this.factDesc = factDesc;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptDesc() {
        return deptDesc;
    }

    public void setDeptDesc(String deptDesc) {
        this.deptDesc = deptDesc;
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

    public String getDesgDesc() {
        return desgDesc;
    }

    public void setDesgDesc(String desgDesc) {
        this.desgDesc = desgDesc;
    }

    public String getSubdesgCode() {
        return subdesgCode;
    }

    public void setSubdesgCode(String subdesgCode) {
        this.subdesgCode = subdesgCode;
    }

    public String getSubdesgDesc() {
        return subdesgDesc;
    }

    public void setSubdesgDesc(String subdesgDesc) {
        this.subdesgDesc = subdesgDesc;
    }

    public String getSkillCode() {
        return skillCode;
    }

    public void setSkillCode(String skillCode) {
        this.skillCode = skillCode;
    }

    public String getSkillDesc() {
        return skillDesc;
    }

    public void setSkillDesc(String skillDesc) {
        this.skillDesc = skillDesc;
    }

    public Long getPresentCount() {
        return presentCount;
    }

    public void setPresentCount(Long presentCount) {
        this.presentCount = presentCount;
    }

    public Long getActualPresentCount() {
        return actualPresentCount;
    }

    public void setActualPresentCount(Long actualPresentCount) {
        this.actualPresentCount = actualPresentCount;
    }

    public Long getBalanaceCount() {
        return balanaceCount;
    }

    public void setBalanaceCount(Long balanaceCount) {
        this.balanaceCount = balanaceCount;
    }

    public String getLineNo() {
        return lineNo;
    }

    public void setLineNo(String lineNo) {
        this.lineNo = lineNo;
    }

    public String getLineDesc() {
        return lineDesc;
    }

    public void setLineDesc(String lineDesc) {
        this.lineDesc = lineDesc;
    }

    public Long getLineCount() {
        return lineCount;
    }

    public void setLineCount(Long lineCount) {
        this.lineCount = lineCount;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Instant getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Instant approvedDate) {
        this.approvedDate = approvedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StitchManpowerBudget that = (StitchManpowerBudget) o;
        return Objects.equals(id, that.id) && Objects.equals(factCode, that.factCode) && Objects.equals(nowFactCode, that.nowFactCode) && Objects.equals(factDesc, that.factDesc) && Objects.equals(deptCode, that.deptCode) && Objects.equals(deptDesc, that.deptDesc) && Objects.equals(attendanceDate, that.attendanceDate) && Objects.equals(attendanceType, that.attendanceType) && Objects.equals(desgCode, that.desgCode) && Objects.equals(desgDesc, that.desgDesc) && Objects.equals(subdesgCode, that.subdesgCode) && Objects.equals(subdesgDesc, that.subdesgDesc) && Objects.equals(skillCode, that.skillCode) && Objects.equals(skillDesc, that.skillDesc) && Objects.equals(presentCount, that.presentCount) && Objects.equals(actualPresentCount, that.actualPresentCount) && Objects.equals(balanaceCount, that.balanaceCount) && Objects.equals(lineNo, that.lineNo) && Objects.equals(lineDesc, that.lineDesc) && Objects.equals(lineCount, that.lineCount) && Objects.equals(flag, that.flag) && Objects.equals(createdBy, that.createdBy) && Objects.equals(createdDate, that.createdDate) && Objects.equals(approvedBy, that.approvedBy) && Objects.equals(approvedDate, that.approvedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, factCode, nowFactCode, factDesc, deptCode, deptDesc, attendanceDate, attendanceType, desgCode, desgDesc, subdesgCode, subdesgDesc, skillCode, skillDesc, presentCount, actualPresentCount, balanaceCount, lineNo, lineDesc, lineCount, flag, createdBy, createdDate, approvedBy, approvedDate);
    }
}
