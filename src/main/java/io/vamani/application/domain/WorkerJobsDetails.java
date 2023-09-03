package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A WorkerJobsDetails.
 */
@Entity
@Table(name = "worker_jobs_details")
public class WorkerJobsDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="workerJobsDetailsSeq", sequenceName="worker_jobs_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="workerJobsDetailsSeq")
    private Long id;

    @Size(max = 100)
    @Column(name = "company_name", length = 100)
    private String companyName;

    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    @Column(name = "exp")
    private Double exp;

    @Column(name = "designation")
    private String designation;

    @Size(max = 500)
    @Column(name = "reason_leavig", length = 500)
    private String reasonLeavig;

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

    public String getCompanyName() {
        return companyName;
    }

    public WorkerJobsDetails companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public WorkerJobsDetails fromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
        return this;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public WorkerJobsDetails toDate(LocalDate toDate) {
        this.toDate = toDate;
        return this;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public Double getExp() {
        return exp;
    }

    public WorkerJobsDetails exp(Double exp) {
        this.exp = exp;
        return this;
    }

    public void setExp(Double exp) {
        this.exp = exp;
    }

    public String getDesignation() {
        return designation;
    }

    public WorkerJobsDetails designation(String designation) {
        this.designation = designation;
        return this;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getReasonLeavig() {
        return reasonLeavig;
    }

    public WorkerJobsDetails reasonLeavig(String reasonLeavig) {
        this.reasonLeavig = reasonLeavig;
        return this;
    }

    public void setReasonLeavig(String reasonLeavig) {
        this.reasonLeavig = reasonLeavig;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public WorkerJobsDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public WorkerJobsDetails createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public WorkerJoining getWorkerJoining() {
        return workerJoining;
    }

    public WorkerJobsDetails workerJoining(WorkerJoining workerJoining) {
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
        WorkerJobsDetails workerJobsDetails = (WorkerJobsDetails) o;
        if (workerJobsDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), workerJobsDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WorkerJobsDetails{" +
            "id=" + getId() +
            ", companyName='" + getCompanyName() + "'" +
            ", fromDate='" + getFromDate() + "'" +
            ", toDate='" + getToDate() + "'" +
            ", exp=" + getExp() +
            ", designation='" + getDesignation() + "'" +
            ", reasonLeavig='" + getReasonLeavig() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}
