package io.vamani.application.model;

import java.time.Instant;
import java.util.Objects;

public class WorkerWorkFlowDetail {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long mockId;

    private String empCode;

    private String empName;

    private String forwardCode;

    private String forwardName;

    private String remarks;

    private String authType;

    private Instant authDate;

    private String userType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMockId() {
        return mockId;
    }

    public WorkerWorkFlowDetail mockId(Long mockId) {
        this.mockId = mockId;
        return this;
    }

    public void setMockId(Long mockId) {
        this.mockId = mockId;
    }

    public String getEmpCode() {
        return empCode;
    }

    public WorkerWorkFlowDetail empCode(String empCode) {
        this.empCode = empCode;
        return this;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public WorkerWorkFlowDetail empName(String empName) {
        this.empName = empName;
        return this;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getForwardCode() {
        return forwardCode;
    }

    public WorkerWorkFlowDetail forwardCode(String forwardCode) {
        this.forwardCode = forwardCode;
        return this;
    }

    public void setForwardCode(String forwardCode) {
        this.forwardCode = forwardCode;
    }

    public String getForwardName() {
        return forwardName;
    }

    public WorkerWorkFlowDetail forwardName(String forwardName) {
        this.forwardName = forwardName;
        return this;
    }

    public void setForwardName(String forwardName) {
        this.forwardName = forwardName;
    }

    public String getRemarks() {
        return remarks;
    }

    public WorkerWorkFlowDetail remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getAuthType() {
        return authType;
    }

    public WorkerWorkFlowDetail authType(String authType) {
        this.authType = authType;
        return this;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public Instant getAuthDate() {
        return authDate;
    }

    public WorkerWorkFlowDetail authDate(Instant authDate) {
        this.authDate = authDate;
        return this;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
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
        WorkerWorkFlowDetail workerWorkFlow = (WorkerWorkFlowDetail) o;
        if (workerWorkFlow.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), workerWorkFlow.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WorkerWorkFlowDetail{" +
            "id=" + getId() +
            ", mockId=" + getMockId() +
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
