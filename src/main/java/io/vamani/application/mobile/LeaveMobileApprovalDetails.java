package io.vamani.application.mobile;

import java.io.Serializable;
import java.util.Objects;

public class LeaveMobileApprovalDetails implements Serializable {
    private Long id;

    private String empCode;

    private String empName;

    private String leaveDateFromView;

    private String leaveDateToView;

    private String leaveTypeMasterDesc;

    private String leaveSubTypeMasterDesc;

    private String flag;

    private String reason;

    private Float noDays;

    private String hodRemarks;

    private String hrRemarks;

    private String processFlag;

    private String missPunchType;

    private String factory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLeaveDateFromView() {
        return leaveDateFromView;
    }

    public void setLeaveDateFromView(String leaveDateFromView) {
        this.leaveDateFromView = leaveDateFromView;
    }

    public String getLeaveDateToView() {
        return leaveDateToView;
    }

    public void setLeaveDateToView(String leaveDateToView) {
        this.leaveDateToView = leaveDateToView;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Float getNoDays() {
        return noDays;
    }

    public void setNoDays(Float noDays) {
        this.noDays = noDays;
    }

    public String getHodRemarks() {
        return hodRemarks;
    }

    public void setHodRemarks(String hodRemarks) {
        this.hodRemarks = hodRemarks;
    }

    public String getHrRemarks() {
        return hrRemarks;
    }

    public void setHrRemarks(String hrRemarks) {
        this.hrRemarks = hrRemarks;
    }

    public String getProcessFlag() {
        return processFlag;
    }

    public void setProcessFlag(String processFlag) {
        this.processFlag = processFlag;
    }

    public String getMissPunchType() {
        return missPunchType;
    }

    public void setMissPunchType(String missPunchType) {
        this.missPunchType = missPunchType;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getLeaveTypeMasterDesc() {
        return leaveTypeMasterDesc;
    }

    public void setLeaveTypeMasterDesc(String leaveTypeMasterDesc) {
        this.leaveTypeMasterDesc = leaveTypeMasterDesc;
    }

    public String getLeaveSubTypeMasterDesc() {
        return leaveSubTypeMasterDesc;
    }

    public void setLeaveSubTypeMasterDesc(String leaveSubTypeMasterDesc) {
        this.leaveSubTypeMasterDesc = leaveSubTypeMasterDesc;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeaveMobileApprovalDetails that = (LeaveMobileApprovalDetails) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(empCode, that.empCode) &&
            Objects.equals(empName, that.empName) &&
            Objects.equals(leaveDateFromView, that.leaveDateFromView) &&
            Objects.equals(leaveDateToView, that.leaveDateToView) &&
            Objects.equals(leaveTypeMasterDesc, that.leaveTypeMasterDesc) &&
            Objects.equals(leaveSubTypeMasterDesc, that.leaveSubTypeMasterDesc) &&
            Objects.equals(flag, that.flag) &&
            Objects.equals(reason, that.reason) &&
            Objects.equals(noDays, that.noDays) &&
            Objects.equals(hodRemarks, that.hodRemarks) &&
            Objects.equals(hrRemarks, that.hrRemarks) &&
            Objects.equals(processFlag, that.processFlag) &&
            Objects.equals(missPunchType, that.missPunchType) &&
            Objects.equals(factory, that.factory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, empCode, empName, leaveDateFromView, leaveDateToView, leaveTypeMasterDesc, leaveSubTypeMasterDesc, flag, reason, noDays, hodRemarks, hrRemarks, processFlag, missPunchType, factory);
    }

    @Override
    public String toString() {
        return "LeaveMobileApprovalDetails{" +
            "id=" + id +
            ", empCode='" + empCode + '\'' +
            ", empName='" + empName + '\'' +
            ", leaveDateFromView='" + leaveDateFromView + '\'' +
            ", leaveDateToView='" + leaveDateToView + '\'' +
            ", leaveTypeMasterDesc='" + leaveTypeMasterDesc + '\'' +
            ", leaveSubTypeMasterDesc='" + leaveSubTypeMasterDesc + '\'' +
            ", flag='" + flag + '\'' +
            ", reason='" + reason + '\'' +
            ", noDays=" + noDays +
            ", hodRemarks='" + hodRemarks + '\'' +
            ", hrRemarks='" + hrRemarks + '\'' +
            ", processFlag='" + processFlag + '\'' +
            ", missPunchType='" + missPunchType + '\'' +
            ", factory='" + factory + '\'' +
            '}';
    }
}
