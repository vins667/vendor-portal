package io.vamani.application.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Suggestion.
 */
@Entity
@Table(name = "manpower_budget_entry")
public class ManpowerBudgetEntry implements Serializable {

    @EmbeddedId
    private ManpowerBudgetEntryId id;

    @NotNull
    @Size(max = 10)
    @Column(name = "now_fact_code", length = 10, nullable = false)
    private String nowFactCode;

    @Size(max = 100)
    @Column(name = "fact_desc", length = 100, nullable = false)
    private String factDesc;

    @Size(max = 100)
    @Column(name = "dept_desc", length = 100, nullable = false)
    private String deptDesc;

    @Size(max = 100)
    @Column(name = "desg_desc", length = 100, nullable = false)
    private String desgDesc;

    @Size(max = 100)
    @Column(name = "subdesg_desc", length = 100, nullable = false)
    private String subdesgDesc;

    @Size(max = 100)
    @Column(name = "skill_desc", length = 100, nullable = false)
    private String skillDesc;

    @Column(name = "present_count")
    private Long presentCount;

    @Column(name = "actual_present_count")
    private Long actualPresentCount;

    @Column(name = "balanace_count")
    private Long balanaceCount;

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

    public ManpowerBudgetEntryId getId() {
        return id;
    }

    public void setId(ManpowerBudgetEntryId id) {
        this.id = id;
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

    public String getDeptDesc() {
        return deptDesc;
    }

    public void setDeptDesc(String deptDesc) {
        this.deptDesc = deptDesc;
    }

    public String getDesgDesc() {
        return desgDesc;
    }

    public void setDesgDesc(String desgDesc) {
        this.desgDesc = desgDesc;
    }

    public String getSubdesgDesc() {
        return subdesgDesc;
    }

    public void setSubdesgDesc(String subdesgDesc) {
        this.subdesgDesc = subdesgDesc;
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
}
