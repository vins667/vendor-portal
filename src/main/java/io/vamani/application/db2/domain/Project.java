package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "project")
public class Project {
    @EmbeddedId
    private ProjectId id;
    private String alloweddivisions;
    private String longdescription;
    private String shortdescription;
    private String searchdescription;
    private String plannerannotation;
    private Short planrunning;
    private Short canbeexploded;
    private Integer progressstatus;
    private Short lineschanged;
    private String errors;
    private String warnings;
    private Integer tracecreationid;
    private Integer traceline;
    private Long submittedjobjobnumber;
    private Timestamp creationdatetime;
    private String creationuser;
    private Timestamp lastupdatedatetime;
    private String lastupdateuser;
    private Long absuniqueid;
    private Short budgetmanaged;
    private Integer budgetprogressstatus;
    private String budgetstatus;
    private Date budgetapprovaldate;
    private String budgetapprovaluser;
    private Timestamp creationdatetimeutc;
    private Timestamp lastupdatedatetimeutc;

    public ProjectId getId() {
        return id;
    }

    public void setId(ProjectId id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ALLOWEDDIVISIONS", nullable = true, length = 90)
    public String getAlloweddivisions() {
        return alloweddivisions;
    }

    public void setAlloweddivisions(String alloweddivisions) {
        this.alloweddivisions = alloweddivisions;
    }

    @Basic
    @Column(name = "LONGDESCRIPTION", nullable = false, length = 200)
    public String getLongdescription() {
        return longdescription;
    }

    public void setLongdescription(String longdescription) {
        this.longdescription = longdescription;
    }

    @Basic
    @Column(name = "SHORTDESCRIPTION", nullable = true, length = 80)
    public String getShortdescription() {
        return shortdescription;
    }

    public void setShortdescription(String shortdescription) {
        this.shortdescription = shortdescription;
    }

    @Basic
    @Column(name = "SEARCHDESCRIPTION", nullable = true, length = 120)
    public String getSearchdescription() {
        return searchdescription;
    }

    public void setSearchdescription(String searchdescription) {
        this.searchdescription = searchdescription;
    }

    @Basic
    @Column(name = "PLANNERANNOTATION", nullable = true, length = 250)
    public String getPlannerannotation() {
        return plannerannotation;
    }

    public void setPlannerannotation(String plannerannotation) {
        this.plannerannotation = plannerannotation;
    }

    @Basic
    @Column(name = "PLANRUNNING", nullable = false)
    public Short getPlanrunning() {
        return planrunning;
    }

    public void setPlanrunning(Short planrunning) {
        this.planrunning = planrunning;
    }

    @Basic
    @Column(name = "CANBEEXPLODED", nullable = false)
    public Short getCanbeexploded() {
        return canbeexploded;
    }

    public void setCanbeexploded(Short canbeexploded) {
        this.canbeexploded = canbeexploded;
    }

    @Basic
    @Column(name = "PROGRESSSTATUS", nullable = false)
    public Integer getProgressstatus() {
        return progressstatus;
    }

    public void setProgressstatus(Integer progressstatus) {
        this.progressstatus = progressstatus;
    }

    @Basic
    @Column(name = "LINESCHANGED", nullable = false)
    public Short getLineschanged() {
        return lineschanged;
    }

    public void setLineschanged(Short lineschanged) {
        this.lineschanged = lineschanged;
    }

    @Basic
    @Column(name = "ERRORS", nullable = true, length = 960)
    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    @Basic
    @Column(name = "WARNINGS", nullable = true, length = 960)
    public String getWarnings() {
        return warnings;
    }

    public void setWarnings(String warnings) {
        this.warnings = warnings;
    }

    @Basic
    @Column(name = "TRACECREATIONID", nullable = true, precision = 0)
    public Integer getTracecreationid() {
        return tracecreationid;
    }

    public void setTracecreationid(Integer tracecreationid) {
        this.tracecreationid = tracecreationid;
    }

    @Basic
    @Column(name = "TRACELINE", nullable = true)
    public Integer getTraceline() {
        return traceline;
    }

    public void setTraceline(Integer traceline) {
        this.traceline = traceline;
    }

    @Basic
    @Column(name = "SUBMITTEDJOBJOBNUMBER", nullable = true)
    public Long getSubmittedjobjobnumber() {
        return submittedjobjobnumber;
    }

    public void setSubmittedjobjobnumber(Long submittedjobjobnumber) {
        this.submittedjobjobnumber = submittedjobjobnumber;
    }

    @Basic
    @Column(name = "CREATIONDATETIME", nullable = true)
    public Timestamp getCreationdatetime() {
        return creationdatetime;
    }

    public void setCreationdatetime(Timestamp creationdatetime) {
        this.creationdatetime = creationdatetime;
    }

    @Basic
    @Column(name = "CREATIONUSER", nullable = true, length = 25)
    public String getCreationuser() {
        return creationuser;
    }

    public void setCreationuser(String creationuser) {
        this.creationuser = creationuser;
    }

    @Basic
    @Column(name = "LASTUPDATEDATETIME", nullable = true)
    public Timestamp getLastupdatedatetime() {
        return lastupdatedatetime;
    }

    public void setLastupdatedatetime(Timestamp lastupdatedatetime) {
        this.lastupdatedatetime = lastupdatedatetime;
    }

    @Basic
    @Column(name = "LASTUPDATEUSER", nullable = true, length = 25)
    public String getLastupdateuser() {
        return lastupdateuser;
    }

    public void setLastupdateuser(String lastupdateuser) {
        this.lastupdateuser = lastupdateuser;
    }

    @Basic
    @Column(name = "ABSUNIQUEID", nullable = false)
    public Long getAbsuniqueid() {
        return absuniqueid;
    }

    public void setAbsuniqueid(Long absuniqueid) {
        this.absuniqueid = absuniqueid;
    }

    @Basic
    @Column(name = "BUDGETMANAGED", nullable = false)
    public Short getBudgetmanaged() {
        return budgetmanaged;
    }

    public void setBudgetmanaged(Short budgetmanaged) {
        this.budgetmanaged = budgetmanaged;
    }

    @Basic
    @Column(name = "BUDGETPROGRESSSTATUS", nullable = false)
    public Integer getBudgetprogressstatus() {
        return budgetprogressstatus;
    }

    public void setBudgetprogressstatus(Integer budgetprogressstatus) {
        this.budgetprogressstatus = budgetprogressstatus;
    }

    @Basic
    @Column(name = "BUDGETSTATUS", nullable = false, length = 1)
    public String getBudgetstatus() {
        return budgetstatus;
    }

    public void setBudgetstatus(String budgetstatus) {
        this.budgetstatus = budgetstatus;
    }

    @Basic
    @Column(name = "BUDGETAPPROVALDATE", nullable = true)
    public Date getBudgetapprovaldate() {
        return budgetapprovaldate;
    }

    public void setBudgetapprovaldate(Date budgetapprovaldate) {
        this.budgetapprovaldate = budgetapprovaldate;
    }

    @Basic
    @Column(name = "BUDGETAPPROVALUSER", nullable = true, length = 25)
    public String getBudgetapprovaluser() {
        return budgetapprovaluser;
    }

    public void setBudgetapprovaluser(String budgetapprovaluser) {
        this.budgetapprovaluser = budgetapprovaluser;
    }

    @Basic
    @Column(name = "CREATIONDATETIMEUTC", nullable = true)
    public Timestamp getCreationdatetimeutc() {
        return creationdatetimeutc;
    }

    public void setCreationdatetimeutc(Timestamp creationdatetimeutc) {
        this.creationdatetimeutc = creationdatetimeutc;
    }

    @Basic
    @Column(name = "LASTUPDATEDATETIMEUTC", nullable = true)
    public Timestamp getLastupdatedatetimeutc() {
        return lastupdatedatetimeutc;
    }

    public void setLastupdatedatetimeutc(Timestamp lastupdatedatetimeutc) {
        this.lastupdatedatetimeutc = lastupdatedatetimeutc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id) && Objects.equals(alloweddivisions, project.alloweddivisions) && Objects.equals(longdescription, project.longdescription) && Objects.equals(shortdescription, project.shortdescription) && Objects.equals(searchdescription, project.searchdescription) && Objects.equals(plannerannotation, project.plannerannotation) && Objects.equals(planrunning, project.planrunning) && Objects.equals(canbeexploded, project.canbeexploded) && Objects.equals(progressstatus, project.progressstatus) && Objects.equals(lineschanged, project.lineschanged) && Objects.equals(errors, project.errors) && Objects.equals(warnings, project.warnings) && Objects.equals(tracecreationid, project.tracecreationid) && Objects.equals(traceline, project.traceline) && Objects.equals(submittedjobjobnumber, project.submittedjobjobnumber) && Objects.equals(creationdatetime, project.creationdatetime) && Objects.equals(creationuser, project.creationuser) && Objects.equals(lastupdatedatetime, project.lastupdatedatetime) && Objects.equals(lastupdateuser, project.lastupdateuser) && Objects.equals(absuniqueid, project.absuniqueid) && Objects.equals(budgetmanaged, project.budgetmanaged) && Objects.equals(budgetprogressstatus, project.budgetprogressstatus) && Objects.equals(budgetstatus, project.budgetstatus) && Objects.equals(budgetapprovaldate, project.budgetapprovaldate) && Objects.equals(budgetapprovaluser, project.budgetapprovaluser) && Objects.equals(creationdatetimeutc, project.creationdatetimeutc) && Objects.equals(lastupdatedatetimeutc, project.lastupdatedatetimeutc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, alloweddivisions, longdescription, shortdescription, searchdescription, plannerannotation, planrunning, canbeexploded, progressstatus, lineschanged, errors, warnings, tracecreationid, traceline, submittedjobjobnumber, creationdatetime, creationuser, lastupdatedatetime, lastupdateuser, absuniqueid, budgetmanaged, budgetprogressstatus, budgetstatus, budgetapprovaldate, budgetapprovaluser, creationdatetimeutc, lastupdatedatetimeutc);
    }
}
