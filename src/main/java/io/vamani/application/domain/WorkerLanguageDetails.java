package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A WorkerLanguageDetails.
 */
@Entity
@Table(name = "worker_language_details")
public class WorkerLanguageDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="workerLanguageDetailsSeq", sequenceName="worker_language_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="workerLanguageDetailsSeq")
    private Long id;

    @NotNull
    @Column(name = "language_master_id", nullable = false)
    private Long languageMasterId;

    @NotNull
    @Size(max = 1)
    @Column(name = "mother_tongue", length = 1, nullable = false)
    private String motherTongue;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

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

    public Long getLanguageMasterId() {
        return languageMasterId;
    }

    public WorkerLanguageDetails languageMasterId(Long languageMasterId) {
        this.languageMasterId = languageMasterId;
        return this;
    }

    public void setLanguageMasterId(Long languageMasterId) {
        this.languageMasterId = languageMasterId;
    }

    public String getMotherTongue() {
        return motherTongue;
    }

    public WorkerLanguageDetails motherTongue(String motherTongue) {
        this.motherTongue = motherTongue;
        return this;
    }

    public void setMotherTongue(String motherTongue) {
        this.motherTongue = motherTongue;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public WorkerLanguageDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public WorkerLanguageDetails createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public WorkerJoining getWorkerJoining() {
        return workerJoining;
    }

    public WorkerLanguageDetails workerJoining(WorkerJoining workerJoining) {
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
        WorkerLanguageDetails workerLanguageDetails = (WorkerLanguageDetails) o;
        if (workerLanguageDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), workerLanguageDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WorkerLanguageDetails{" +
            "id=" + getId() +
            ", languageMasterId=" + getLanguageMasterId() +
            ", motherTongue='" + getMotherTongue() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}
