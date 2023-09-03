package io.vamani.application.model;

import io.vamani.application.domain.WorkerWorkFlow;

import java.io.Serializable;
import java.util.List;

public class WorkerWorkFlowBean implements Serializable {
    private Long id;
    private Long mockId;
    private String empCode;
    private String empName;
    private Boolean allowEntry;
    private String recStatus;
    private List<WorkerWorkFlow> workerWorkFlows;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMockId() {
        return mockId;
    }

    public void setMockId(Long mockId) {
        this.mockId = mockId;
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

    public List<WorkerWorkFlow> getWorkerWorkFlows() {
        return workerWorkFlows;
    }

    public void setWorkerWorkFlows(List<WorkerWorkFlow> workerWorkFlows) {
        this.workerWorkFlows = workerWorkFlows;
    }

    public String getRecStatus() {
        return recStatus;
    }

    public void setRecStatus(String recStatus) {
        this.recStatus = recStatus;
    }
}
