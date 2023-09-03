package io.vamani.application.model;

import io.vamani.application.domain.LeaveMaster;
import io.vamani.application.domain.LeaveMasterRemarksDetails;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class LeaveMasterRemarksDetailsBean implements Serializable {
    private Long id;
    private String empCode;
    private String empName;
    private String forwardCode;
    private String forwardName;
    private String createdBy;
    private String createdName;
    private String hodApprovedBy;
    private String hodApprovedName;
    private Long leaveMasterId;
    private String remarks;
    private Boolean allowEntry;
    private String status;
    private LeaveMaster leaveMaster;
    private List<LeaveMasterRemarksDetails> leaveMasterRemarksDetails;
    private List<Master> statusList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getForwardCode() {
        return forwardCode;
    }

    public void setForwardCode(String forwardCode) {
        this.forwardCode = forwardCode;
    }

    public String getForwardName() {
        return forwardName;
    }

    public void setForwardName(String forwardName) {
        this.forwardName = forwardName;
    }

    public Long getLeaveMasterId() {
        return leaveMasterId;
    }

    public void setLeaveMasterId(Long leaveMasterId) {
        this.leaveMasterId = leaveMasterId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Boolean getAllowEntry() {
        return allowEntry;
    }

    public void setAllowEntry(Boolean allowEntry) {
        this.allowEntry = allowEntry;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedName() {
        return createdName;
    }

    public void setCreatedName(String createdName) {
        this.createdName = createdName;
    }

    public LeaveMaster getLeaveMaster() {
        return leaveMaster;
    }

    public void setLeaveMaster(LeaveMaster leaveMaster) {
        this.leaveMaster = leaveMaster;
    }

    public String getHodApprovedBy() {
        return hodApprovedBy;
    }

    public void setHodApprovedBy(String hodApprovedBy) {
        this.hodApprovedBy = hodApprovedBy;
    }

    public String getHodApprovedName() {
        return hodApprovedName;
    }

    public void setHodApprovedName(String hodApprovedName) {
        this.hodApprovedName = hodApprovedName;
    }

    public List<LeaveMasterRemarksDetails> getLeaveMasterRemarksDetails() {
        return leaveMasterRemarksDetails;
    }

    public void setLeaveMasterRemarksDetails(List<LeaveMasterRemarksDetails> leaveMasterRemarksDetails) {
        this.leaveMasterRemarksDetails = leaveMasterRemarksDetails;
    }

    public List<Master> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Master> statusList) {
        this.statusList = statusList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeaveMasterRemarksDetailsBean that = (LeaveMasterRemarksDetailsBean) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(empCode, that.empCode) &&
            Objects.equals(empName, that.empName) &&
            Objects.equals(forwardCode, that.forwardCode) &&
            Objects.equals(forwardName, that.forwardName) &&
            Objects.equals(leaveMasterId, that.leaveMasterId) &&
            Objects.equals(remarks, that.remarks) &&
            Objects.equals(allowEntry, that.allowEntry) &&
            Objects.equals(status, that.status) &&
            Objects.equals(leaveMasterRemarksDetails, that.leaveMasterRemarksDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, empCode, empName, forwardCode, forwardName, leaveMasterId, remarks, allowEntry, status, leaveMasterRemarksDetails);
    }

    @Override
    public String toString() {
        return "LeaveMasterRemarksDetailsBean{" +
            "id=" + id +
            ", empCode='" + empCode + '\'' +
            ", empName='" + empName + '\'' +
            ", forwardCode='" + forwardCode + '\'' +
            ", forwardName='" + forwardName + '\'' +
            ", leaveMasterId='" + leaveMasterId + '\'' +
            ", remarks='" + remarks + '\'' +
            ", allowEntry='" + allowEntry + '\'' +
            ", status='" + status + '\'' +
            ", leaveMasterRemarksDetails=" + leaveMasterRemarksDetails +
            '}';
    }
}
