package io.vamani.application.model;

import java.util.Date;

public class LeaveDiffReportBean {
    private String empCode;
    private String empName;
    private String leaveDate;
    private Date leaveDateDt;
    private String leaveTypeCode;
    private String leaveSubTypeCode;
    private String status;

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

    public String getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getLeaveTypeCode() {
        return leaveTypeCode;
    }

    public void setLeaveTypeCode(String leaveTypeCode) {
        this.leaveTypeCode = leaveTypeCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLeaveSubTypeCode() {
        return leaveSubTypeCode;
    }

    public void setLeaveSubTypeCode(String leaveSubTypeCode) {
        this.leaveSubTypeCode = leaveSubTypeCode;
    }

    public Date getLeaveDateDt() {
        return leaveDateDt;
    }

    public void setLeaveDateDt(Date leaveDateDt) {
        this.leaveDateDt = leaveDateDt;
    }
}
