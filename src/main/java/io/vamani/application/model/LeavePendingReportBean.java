package io.vamani.application.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class LeavePendingReportBean implements Serializable {

    private String hodCardNo;
    private String hodName;
    private String empCode;
    private String name;
    private String leaveFrom;
    private Date leaveFromDt;
    private String leaveTo;
    private String leaveType;
    private String leaveSubType;
    private String flag;
    private String status;
    private String reqDate;
    private String reqTime;

    public String getHodCardNo() {
        return hodCardNo;
    }

    public void setHodCardNo(String hodCardNo) {
        this.hodCardNo = hodCardNo;
    }

    public String getHodName() {
        return hodName;
    }

    public void setHodName(String hodName) {
        this.hodName = hodName;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getLeaveFrom() {
        return leaveFrom;
    }

    public void setLeaveFrom(String leaveFrom) {
        this.leaveFrom = leaveFrom;
    }

    public String getLeaveTo() {
        return leaveTo;
    }

    public void setLeaveTo(String leaveTo) {
        this.leaveTo = leaveTo;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getLeaveSubType() {
        return leaveSubType;
    }

    public void setLeaveSubType(String leaveSubType) {
        this.leaveSubType = leaveSubType;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReqDate() {
        return reqDate;
    }

    public void setReqDate(String reqDate) {
        this.reqDate = reqDate;
    }

    public String getReqTime() {
        return reqTime;
    }

    public void setReqTime(String reqTime) {
        this.reqTime = reqTime;
    }

    public Date getLeaveFromDt() {
        return leaveFromDt;
    }

    public void setLeaveFromDt(Date leaveFromDt) {
        this.leaveFromDt = leaveFromDt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeavePendingReportBean that = (LeavePendingReportBean) o;
        return Objects.equals(hodCardNo, that.hodCardNo) &&
            Objects.equals(hodName, that.hodName) &&
            Objects.equals(empCode, that.empCode) &&
            Objects.equals(name, that.name) &&
            Objects.equals(leaveFrom, that.leaveFrom) &&
            Objects.equals(leaveTo, that.leaveTo) &&
            Objects.equals(leaveType, that.leaveType) &&
            Objects.equals(leaveSubType, that.leaveSubType) &&
            Objects.equals(flag, that.flag) &&
            Objects.equals(status, that.status) &&
            Objects.equals(reqDate, that.reqDate) &&
            Objects.equals(reqTime, that.reqTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hodCardNo, hodName, empCode, name, leaveFrom, leaveTo, leaveType, leaveSubType, flag, status, reqDate, reqTime);
    }

    @Override
    public String toString() {
        return "LeavePendingReportBean{" +
            "hodCardNo='" + hodCardNo + '\'' +
            ", hodName='" + hodName + '\'' +
            ", empCode='" + empCode + '\'' +
            ", name='" + name + '\'' +
            ", leaveFrom='" + leaveFrom + '\'' +
            ", leaveTo='" + leaveTo + '\'' +
            ", leaveType='" + leaveType + '\'' +
            ", leaveSubType='" + leaveSubType + '\'' +
            ", flag='" + flag + '\'' +
            ", status='" + status + '\'' +
            ", reqDate='" + reqDate + '\'' +
            ", reqTime='" + reqTime + '\'' +
            '}';
    }
}
