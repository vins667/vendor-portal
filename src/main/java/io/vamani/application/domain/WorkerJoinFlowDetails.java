package io.vamani.application.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A WorkerJoinFlowDetails.
 */
@Entity
@Table(name = "worker_join_flow_details")
public class WorkerJoinFlowDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="workerJoinFlowDetailsSeq", sequenceName="worker_join_flow_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="workerJoinFlowDetailsSeq")
    private Long id;

    @NotNull
    @Column(name = "joining_id", nullable = false)
    private Long joiningId;

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

    @Size(max = 1000)
    @Column(name = "remarks", length = 1000)
    private String remarks;

    @Size(max = 1)
    @Column(name = "auth_type", length = 1)
    private String authType;

    @Column(name = "auth_date")
    private Instant authDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJoiningId() {
        return joiningId;
    }

    public WorkerJoinFlowDetails joiningId(Long joiningId) {
        this.joiningId = joiningId;
        return this;
    }

    public void setJoiningId(Long joiningId) {
        this.joiningId = joiningId;
    }

    public String getEmpCode() {
        return empCode;
    }

    public WorkerJoinFlowDetails empCode(String empCode) {
        this.empCode = empCode;
        return this;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public WorkerJoinFlowDetails empName(String empName) {
        this.empName = empName;
        return this;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getForwardCode() {
        return forwardCode;
    }

    public WorkerJoinFlowDetails forwardCode(String forwardCode) {
        this.forwardCode = forwardCode;
        return this;
    }

    public void setForwardCode(String forwardCode) {
        this.forwardCode = forwardCode;
    }

    public String getForwardName() {
        return forwardName;
    }

    public WorkerJoinFlowDetails forwardName(String forwardName) {
        this.forwardName = forwardName;
        return this;
    }

    public void setForwardName(String forwardName) {
        this.forwardName = forwardName;
    }

    public String getRemarks() {
        return remarks;
    }

    public WorkerJoinFlowDetails remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getAuthType() {
        return authType;
    }

    public WorkerJoinFlowDetails authType(String authType) {
        this.authType = authType;
        return this;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public Instant getAuthDate() {
        return authDate;
    }

    public WorkerJoinFlowDetails authDate(Instant authDate) {
        this.authDate = authDate;
        return this;
    }

    public void setAuthDate(Instant authDate) {
        this.authDate = authDate;
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
        WorkerJoinFlowDetails workerJoinFlowDetails = (WorkerJoinFlowDetails) o;
        if (workerJoinFlowDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), workerJoinFlowDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WorkerJoinFlowDetails{" +
            "id=" + getId() +
            ", joiningId=" + getJoiningId() +
            ", empCode='" + getEmpCode() + "'" +
            ", empName='" + getEmpName() + "'" +
            ", forwardCode='" + getForwardCode() + "'" +
            ", forwardName='" + getForwardName() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", authType='" + getAuthType() + "'" +
            ", authDate='" + getAuthDate() + "'" +
            "}";
    }
}
