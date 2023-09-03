package io.vamani.application.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class WorkerJoinFlowBean implements Serializable {
    private Long id;
    private Long joiningId;
    private String empCode;
    private String empName;
    private Boolean allowEntry;
    private String recStatus;
    private List<WorkerJoinFlowDetails> workerJoinFlowDetails;

    @Override
    public String toString() {
        return "WorkerJoinFlowBean{" +
            "id=" + id +
            ", joiningId=" + joiningId +
            ", empCode='" + empCode + '\'' +
            ", empName='" + empName + '\'' +
            ", allowEntry=" + allowEntry +
            ", recStatus='" + recStatus + '\'' +
            ", workerWorkFlows=" + workerJoinFlowDetails +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkerJoinFlowBean that = (WorkerJoinFlowBean) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(joiningId, that.joiningId) &&
            Objects.equals(empCode, that.empCode) &&
            Objects.equals(empName, that.empName) &&
            Objects.equals(allowEntry, that.allowEntry) &&
            Objects.equals(recStatus, that.recStatus) &&
            Objects.equals(workerJoinFlowDetails, that.workerJoinFlowDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, joiningId, empCode, empName, allowEntry, recStatus, workerJoinFlowDetails);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJoiningId() {
        return joiningId;
    }

    public void setJoiningId(Long joiningId) {
        this.joiningId = joiningId;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Boolean getAllowEntry() {
        return allowEntry;
    }

    public void setAllowEntry(Boolean allowEntry) {
        this.allowEntry = allowEntry;
    }

    public String getRecStatus() {
        return recStatus;
    }

    public void setRecStatus(String recStatus) {
        this.recStatus = recStatus;
    }

    public List<WorkerJoinFlowDetails> getWorkerJoinFlowDetails() {
        return workerJoinFlowDetails;
    }

    public void setWorkerJoinFlowDetails(List<WorkerJoinFlowDetails> workerJoinFlowDetails) {
        this.workerJoinFlowDetails = workerJoinFlowDetails;
    }
}
