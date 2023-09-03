package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A WorkerNominationDetails.
 */
@Entity
@Table(name = "worker_nomination_details")
public class WorkerNominationDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="workerNominationDetailsSeq", sequenceName="worker_nomination_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="workerNominationDetailsSeq")
    private Long id;

    @NotNull
    @Column(name = "nomination_percentage", nullable = false)
    private Double nominationPercentage;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name="worker_family_details_id")
    @JsonIgnoreProperties("")
    private WorkerFamilyDetails workerFamilyDetails;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name="nomination_type_master_id")
    @JsonIgnoreProperties("")
    private NominationTypeMaster nominationTypeMaster;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name="worker_joining_id")
    @JsonIgnoreProperties("")
    private WorkerJoining workerJoining;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getNominationPercentage() {
        return nominationPercentage;
    }

    public WorkerNominationDetails nominationPercentage(Double nominationPercentage) {
        this.nominationPercentage = nominationPercentage;
        return this;
    }

    public void setNominationPercentage(Double nominationPercentage) {
        this.nominationPercentage = nominationPercentage;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public WorkerNominationDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public WorkerNominationDetails createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public WorkerFamilyDetails getWorkerFamilyDetails() {
        return workerFamilyDetails;
    }

    public WorkerNominationDetails workerFamilyDetails(WorkerFamilyDetails workerFamilyDetails) {
        this.workerFamilyDetails = workerFamilyDetails;
        return this;
    }

    public void setWorkerFamilyDetails(WorkerFamilyDetails workerFamilyDetails) {
        this.workerFamilyDetails = workerFamilyDetails;
    }

    public NominationTypeMaster getNominationTypeMaster() {
        return nominationTypeMaster;
    }

    public WorkerNominationDetails nominationTypeMaster(NominationTypeMaster nominationTypeMaster) {
        this.nominationTypeMaster = nominationTypeMaster;
        return this;
    }

    public void setNominationTypeMaster(NominationTypeMaster nominationTypeMaster) {
        this.nominationTypeMaster = nominationTypeMaster;
    }

    public WorkerJoining getWorkerJoining() {
        return workerJoining;
    }

    public WorkerNominationDetails workerJoining(WorkerJoining workerJoining) {
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
        WorkerNominationDetails workerNominationDetails = (WorkerNominationDetails) o;
        if (workerNominationDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), workerNominationDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WorkerNominationDetails{" +
            "id=" + getId() +
            ", nominationPercentage=" + getNominationPercentage() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}
