package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A WorkerFamilyDetails.
 */
@Entity
@Table(name = "worker_family_details")
public class WorkerFamilyDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="workerFamilyDetailsSeq", sequenceName="worker_family_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="workerFamilyDetailsSeq")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @NotNull
    @Column(name = "age", nullable = false)
    private Integer age;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "occupation_master_id")
    private Long occupationMasterId;

    @Size(max = 1)
    @Column(name = "dependency", length = 1)
    private String dependency;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "relation_master_id")
    @JsonIgnoreProperties("")
    private RelationMaster relationMaster;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "worker_joining_id")
    @JsonIgnoreProperties("")
    private WorkerJoining workerJoining;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public WorkerFamilyDetails name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public WorkerFamilyDetails age(Integer age) {
        this.age = age;
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public WorkerFamilyDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public WorkerFamilyDetails createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getDob() {
        return dob;
    }

    public WorkerFamilyDetails dob(LocalDate dob) {
        this.dob = dob;
        return this;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Long getOccupationMasterId() {
        return occupationMasterId;
    }

    public WorkerFamilyDetails occupationMasterId(Long occupationMasterId) {
        this.occupationMasterId = occupationMasterId;
        return this;
    }

    public void setOccupationMasterId(Long occupationMasterId) {
        this.occupationMasterId = occupationMasterId;
    }

    public String getDependency() {
        return dependency;
    }

    public WorkerFamilyDetails dependency(String dependency) {
        this.dependency = dependency;
        return this;
    }

    public void setDependency(String dependency) {
        this.dependency = dependency;
    }

    public RelationMaster getRelationMaster() {
        return relationMaster;
    }

    public WorkerFamilyDetails relationMaster(RelationMaster relationMaster) {
        this.relationMaster = relationMaster;
        return this;
    }

    public void setRelationMaster(RelationMaster relationMaster) {
        this.relationMaster = relationMaster;
    }

    public WorkerJoining getWorkerJoining() {
        return workerJoining;
    }

    public WorkerFamilyDetails workerJoining(WorkerJoining workerJoining) {
        this.workerJoining = workerJoining;
        return this;
    }

    public void setWorkerJoining(WorkerJoining workerJoining) {
        this.workerJoining = workerJoining;
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
        WorkerFamilyDetails workerFamilyDetails = (WorkerFamilyDetails) o;
        if (workerFamilyDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), workerFamilyDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WorkerFamilyDetails{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", age=" + getAge() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", dob='" + getDob() + "'" +
            ", occupationMasterId=" + getOccupationMasterId() +
            ", dependency='" + getDependency() + "'" +
            "}";
    }
}
