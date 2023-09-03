package io.vamani.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A TrailMockOperation.
 */
@Entity
@Table(name = "trail_mock_operation")
public class TrailMockOperation implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name="trailMockOperationSeq", sequenceName="trail_mock_operation_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="trailMockOperationSeq")
    private Long id;

    @Size(max = 1)
    @Column(name = "spl_machine_knowledge", length = 1)
    private String splMachineKnowledge;

    @Column(name = "achive_rating")
    private Double achiveRating;

    @Size(max = 1)
    @Column(name = "result", length = 1)
    private String result;

    @Size(max = 5)
    @Column(name = "grade", length = 5)
    private String grade;

    @Size(max = 500)
    @Column(name = "remarks", length = 500)
    private String remarks;

    @Size(max = 60)
    @Column(name = "created_by", length = 60)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Size(max = 60)
    @Column(name = "last_updated_by", length = 60)
    private String lastUpdatedBy;

    @Column(name = "last_updated_date")
    private Instant lastUpdatedDate;

    @Column(name = "snls")
    private Boolean snls;

    @Column(name = "dnls")
    private Boolean dnls;

    @Column(name = "ol")
    private Boolean ol;

    @Size(max = 200)
    @Column(name = "grade_description", length = 200)
    private String gradeDescription;

    @Size(max = 15)
    @Column(name = "time_taken", length = 15)
    private String timeTaken;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name="worker_recruitment_id")
    @JsonIgnoreProperties("trailMockOperations")
    private WorkerRecruitment workerRecruitment;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "trail_mock_operation_operation_master",
               joinColumns = @JoinColumn(name = "trail_mock_operation_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "operation_master_id", referencedColumnName = "id"))
    private Set<OperationMaster> operationMasters = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "trail_mock_operation_machine_master",
               joinColumns = @JoinColumn(name = "trail_mock_operation_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "machine_master_id", referencedColumnName = "id"))
    private Set<MachineMaster> machineMasters = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSplMachineKnowledge() {
        return splMachineKnowledge;
    }

    public TrailMockOperation splMachineKnowledge(String splMachineKnowledge) {
        this.splMachineKnowledge = splMachineKnowledge;
        return this;
    }

    public void setSplMachineKnowledge(String splMachineKnowledge) {
        this.splMachineKnowledge = splMachineKnowledge;
    }

    public Double getAchiveRating() {
        return achiveRating;
    }

    public TrailMockOperation achiveRating(Double achiveRating) {
        this.achiveRating = achiveRating;
        return this;
    }

    public void setAchiveRating(Double achiveRating) {
        this.achiveRating = achiveRating;
    }

    public String getResult() {
        return result;
    }

    public TrailMockOperation result(String result) {
        this.result = result;
        return this;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getGrade() {
        return grade;
    }

    public TrailMockOperation grade(String grade) {
        this.grade = grade;
        return this;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getRemarks() {
        return remarks;
    }

    public TrailMockOperation remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public TrailMockOperation createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public TrailMockOperation createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public TrailMockOperation lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public TrailMockOperation lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Boolean isSnls() {
        return snls;
    }

    public TrailMockOperation snls(Boolean snls) {
        this.snls = snls;
        return this;
    }

    public void setSnls(Boolean snls) {
        this.snls = snls;
    }

    public Boolean isDnls() {
        return dnls;
    }

    public TrailMockOperation dnls(Boolean dnls) {
        this.dnls = dnls;
        return this;
    }

    public void setDnls(Boolean dnls) {
        this.dnls = dnls;
    }

    public Boolean isOl() {
        return ol;
    }

    public TrailMockOperation ol(Boolean ol) {
        this.ol = ol;
        return this;
    }

    public void setOl(Boolean ol) {
        this.ol = ol;
    }

    public String getGradeDescription() {
        return gradeDescription;
    }

    public TrailMockOperation gradeDescription(String gradeDescription) {
        this.gradeDescription = gradeDescription;
        return this;
    }

    public void setGradeDescription(String gradeDescription) {
        this.gradeDescription = gradeDescription;
    }

    public String getTimeTaken() {
        return timeTaken;
    }

    public TrailMockOperation timeTaken(String timeTaken) {
        this.timeTaken = timeTaken;
        return this;
    }

    public void setTimeTaken(String timeTaken) {
        this.timeTaken = timeTaken;
    }

    public WorkerRecruitment getWorkerRecruitment() {
        return workerRecruitment;
    }

    public TrailMockOperation workerRecruitment(WorkerRecruitment workerRecruitment) {
        this.workerRecruitment = workerRecruitment;
        return this;
    }

    public void setWorkerRecruitment(WorkerRecruitment workerRecruitment) {
        this.workerRecruitment = workerRecruitment;
    }

    public Set<OperationMaster> getOperationMasters() {
        return operationMasters;
    }

    public TrailMockOperation operationMasters(Set<OperationMaster> operationMasters) {
        this.operationMasters = operationMasters;
        return this;
    }

    public TrailMockOperation addOperationMaster(OperationMaster operationMaster) {
        this.operationMasters.add(operationMaster);
        return this;
    }

    public TrailMockOperation removeOperationMaster(OperationMaster operationMaster) {
        this.operationMasters.remove(operationMaster);
        return this;
    }

    public void setOperationMasters(Set<OperationMaster> operationMasters) {
        this.operationMasters = operationMasters;
    }

    public Set<MachineMaster> getMachineMasters() {
        return machineMasters;
    }

    public TrailMockOperation machineMasters(Set<MachineMaster> machineMasters) {
        this.machineMasters = machineMasters;
        return this;
    }

    public TrailMockOperation addMachineMaster(MachineMaster machineMaster) {
        this.machineMasters.add(machineMaster);
        return this;
    }

    public TrailMockOperation removeMachineMaster(MachineMaster machineMaster) {
        this.machineMasters.remove(machineMaster);
        return this;
    }

    public void setMachineMasters(Set<MachineMaster> machineMasters) {
        this.machineMasters = machineMasters;
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
        TrailMockOperation trailMockOperation = (TrailMockOperation) o;
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
        return "TrailMockOperation{" +
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
