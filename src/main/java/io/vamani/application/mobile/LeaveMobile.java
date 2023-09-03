package io.vamani.application.mobile;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class LeaveMobile implements Serializable {
    private Long id;

    private String leaveDateFrom;

    private String leaveDateTo;

    private String flag;

    private String leaveType;

    private String leaveSubType;

    private String reason;

    private String missPunchType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLeaveDateFrom() {
        return leaveDateFrom;
    }

    public void setLeaveDateFrom(String leaveDateFrom) {
        this.leaveDateFrom = leaveDateFrom;
    }

    public String getLeaveDateTo() {
        return leaveDateTo;
    }

    public void setLeaveDateTo(String leaveDateTo) {
        this.leaveDateTo = leaveDateTo;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMissPunchType() {
        return missPunchType;
    }

    public void setMissPunchType(String missPunchType) {
        this.missPunchType = missPunchType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeaveMobile that = (LeaveMobile) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(leaveDateFrom, that.leaveDateFrom) &&
            Objects.equals(leaveDateTo, that.leaveDateTo) &&
            Objects.equals(flag, that.flag) &&
            Objects.equals(leaveType, that.leaveType) &&
            Objects.equals(leaveSubType, that.leaveSubType) &&
            Objects.equals(reason, that.reason) &&
            Objects.equals(missPunchType, that.missPunchType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, leaveDateFrom, leaveDateTo, flag, leaveType, leaveSubType, reason, missPunchType);
    }

    @Override
    public String toString() {
        return "LeaveMobile{" +
            "id=" + id +
            ", leaveDateFrom='" + leaveDateFrom + '\'' +
            ", leaveDateTo='" + leaveDateTo + '\'' +
            ", flag='" + flag + '\'' +
            ", leaveType='" + leaveType + '\'' +
            ", leaveSubType='" + leaveSubType + '\'' +
            ", reason='" + reason + '\'' +
            ", missPunchType='" + missPunchType + '\'' +
            '}';
    }
}
