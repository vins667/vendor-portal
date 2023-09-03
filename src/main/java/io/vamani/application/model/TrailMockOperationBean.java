package io.vamani.application.model;


import io.vamani.application.domain.MachineMaster;
import io.vamani.application.domain.OperationMaster;
import io.vamani.application.domain.WorkerRecruitment;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class TrailMockOperationBean {

    private Long id;

    private String splMachineKnowledge;

    private Double achiveRating;

    private String result;

    private String grade;

    private String remarks;

    private String createdBy;

    private Instant createdDate;

    private String lastUpdatedBy;

    private Instant lastUpdatedDate;

    private Boolean snls;

    private Boolean dnls;

    private Boolean ol;

    private String gradeDescription;

    private String timeTaken;

    private Boolean allowEntry;

    private WorkerRecruitment workerRecruitment;

    private Set<OperationMaster> operationMasters = new HashSet<>();

    private Set<MachineMaster> machineMasters = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSplMachineKnowledge() {
        return splMachineKnowledge;
    }

    public TrailMockOperationBean splMachineKnowledge(String splMachineKnowledge) {
        this.splMachineKnowledge = splMachineKnowledge;
        return this;
    }

    public void setSplMachineKnowledge(String splMachineKnowledge) {
        this.splMachineKnowledge = splMachineKnowledge;
    }

    public Double getAchiveRating() {
        return achiveRating;
    }

    public TrailMockOperationBean achiveRating(Double achiveRating) {
        this.achiveRating = achiveRating;
        return this;
    }

    public void setAchiveRating(Double achiveRating) {
        this.achiveRating = achiveRating;
    }

    public String getResult() {
        return result;
    }

    public TrailMockOperationBean result(String result) {
        this.result = result;
        return this;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getGrade() {
        return grade;
    }

    public TrailMockOperationBean grade(String grade) {
        this.grade = grade;
        return this;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getRemarks() {
        return remarks;
    }

    public TrailMockOperationBean remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public TrailMockOperationBean createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public TrailMockOperationBean createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public TrailMockOperationBean lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public TrailMockOperationBean lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Boolean isSnls() {
        return snls;
    }

    public TrailMockOperationBean snls(Boolean snls) {
        this.snls = snls;
        return this;
    }

    public void setSnls(Boolean snls) {
        this.snls = snls;
    }

    public Boolean isDnls() {
        return dnls;
    }

    public TrailMockOperationBean dnls(Boolean dnls) {
        this.dnls = dnls;
        return this;
    }

    public void setDnls(Boolean dnls) {
        this.dnls = dnls;
    }

    public Boolean isOl() {
        return ol;
    }

    public TrailMockOperationBean ol(Boolean ol) {
        this.ol = ol;
        return this;
    }

    public void setOl(Boolean ol) {
        this.ol = ol;
    }

    public String getGradeDescription() {
        return gradeDescription;
    }

    public TrailMockOperationBean gradeDescription(String gradeDescription) {
        this.gradeDescription = gradeDescription;
        return this;
    }

    public void setGradeDescription(String gradeDescription) {
        this.gradeDescription = gradeDescription;
    }

    public String getTimeTaken() {
        return timeTaken;
    }

    public TrailMockOperationBean timeTaken(String timeTaken) {
        this.timeTaken = timeTaken;
        return this;
    }

    public void setTimeTaken(String timeTaken) {
        this.timeTaken = timeTaken;
    }

    public WorkerRecruitment getWorkerRecruitment() {
        return workerRecruitment;
    }

    public TrailMockOperationBean workerRecruitment(WorkerRecruitment workerRecruitment) {
        this.workerRecruitment = workerRecruitment;
        return this;
    }

    public void setWorkerRecruitment(WorkerRecruitment workerRecruitment) {
        this.workerRecruitment = workerRecruitment;
    }

    public Set<OperationMaster> getOperationMasters() {
        return operationMasters;
    }

    public TrailMockOperationBean operationMasters(Set<OperationMaster> operationMasters) {
        this.operationMasters = operationMasters;
        return this;
    }

    public TrailMockOperationBean addOperationMaster(OperationMaster operationMaster) {
        this.operationMasters.add(operationMaster);
        return this;
    }

    public TrailMockOperationBean removeOperationMaster(OperationMaster operationMaster) {
        this.operationMasters.remove(operationMaster);
        return this;
    }

    public void setOperationMasters(Set<OperationMaster> operationMasters) {
        this.operationMasters = operationMasters;
    }

    public Set<MachineMaster> getMachineMasters() {
        return machineMasters;
    }

    public TrailMockOperationBean machineMasters(Set<MachineMaster> machineMasters) {
        this.machineMasters = machineMasters;
        return this;
    }

    public TrailMockOperationBean addMachineMaster(MachineMaster machineMaster) {
        this.machineMasters.add(machineMaster);
        return this;
    }

    public TrailMockOperationBean removeMachineMaster(MachineMaster machineMaster) {
        this.machineMasters.remove(machineMaster);
        return this;
    }

    public void setMachineMasters(Set<MachineMaster> machineMasters) {
        this.machineMasters = machineMasters;
    }

    public Boolean getAllowEntry() {
        return allowEntry;
    }

    public void setAllowEntry(Boolean allowEntry) {
        this.allowEntry = allowEntry;
    }
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TrailMockOperationBean trailMockOperation = (TrailMockOperationBean) o;
        if (trailMockOperation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), trailMockOperation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TrailMockOperationBean{" +
            "id=" + getId() +
            ", splMachineKnowledge='" + getSplMachineKnowledge() + "'" +
            ", achiveRating=" + getAchiveRating() +
            ", result='" + getResult() + "'" +
            ", grade='" + getGrade() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", snls='" + isSnls() + "'" +
            ", dnls='" + isDnls() + "'" +
            ", ol='" + isOl() + "'" +
            ", gradeDescription='" + getGradeDescription() + "'" +
            ", timeTaken='" + getTimeTaken() + "'" +
            "}";
    }
}
