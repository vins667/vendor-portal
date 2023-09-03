package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A WorkerJoinFlowMaster.
 */
@Entity
@Table(name = "worker_join_flow_master")
public class WorkerJoinFlowMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="workerJoinFlowMasterSeq", sequenceName="worker_join_flow_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="workerJoinFlowMasterSeq")
    private Long id;

    @Size(max = 50)
    @Column(name = "emp_code", length = 50)
    private String empCode;

    @Size(max = 100)
    @Column(name = "emp_name", length = 100)
    private String empName;

    @Size(max = 50)
    @Column(name = "forward_code", length = 50)
    private String forwardCode;

    @Size(max = 100)
    @Column(name = "forward_name", length = 100)
    private String forwardName;

    @Size(max = 1)
    @Column(name = "status", length = 1)
    private String status;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Size(max = 50)
    @Column(name = "last_updated_by", length = 50)
    private String lastUpdatedBy;

    @Column(name = "last_updated_date")
    private Instant lastUpdatedDate;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "worker_forward_type_master_id")
    @JsonIgnoreProperties("")
    private WorkerForwardTypeMaster workerForwardTypeMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpCode() {
        return empCode;
    }

    public WorkerJoinFlowMaster empCode(String empCode) {
        this.empCode = empCode;
        return this;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public WorkerJoinFlowMaster empName(String empName) {
        this.empName = empName;
        return this;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getForwardCode() {
        return forwardCode;
    }

    public WorkerJoinFlowMaster forwardCode(String forwardCode) {
        this.forwardCode = forwardCode;
        return this;
    }

    public void setForwardCode(String forwardCode) {
        this.forwardCode = forwardCode;
    }

    public String getForwardName() {
        return forwardName;
    }

    public WorkerJoinFlowMaster forwardName(String forwardName) {
        this.forwardName = forwardName;
        return this;
    }

    public void setForwardName(String forwardName) {
        this.forwardName = forwardName;
    }

    public String getStatus() {
        return status;
    }

    public WorkerJoinFlowMaster status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public WorkerJoinFlowMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public WorkerJoinFlowMaster createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public WorkerJoinFlowMaster lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public WorkerJoinFlowMaster lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public WorkerForwardTypeMaster getWorkerForwardTypeMaster() {
        return workerForwardTypeMaster;
    }

    public WorkerJoinFlowMaster workerForwardTypeMaster(WorkerForwardTypeMaster workerForwardTypeMaster) {
        this.workerForwardTypeMaster = workerForwardTypeMaster;
        return this;
    }

    public void setWorkerForwardTypeMaster(WorkerForwardTypeMaster workerForwardTypeMaster) {
        this.workerForwardTypeMaster = workerForwardTypeMaster;
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
        WorkerJoinFlowMaster workerJoinFlowMaster = (WorkerJoinFlowMaster) o;
        if (workerJoinFlowMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), workerJoinFlowMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WorkerJoinFlowMaster{" +
            "id=" + getId() +
            ", empCode='" + getEmpCode() + "'" +
            ", empName='" + getEmpName() + "'" +
            ", forwardCode='" + getForwardCode() + "'" +
            ", forwardName='" + getForwardName() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            "}";
    }
}
