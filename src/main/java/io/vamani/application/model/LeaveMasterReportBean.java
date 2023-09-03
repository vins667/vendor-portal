package io.vamani.application.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class LeaveMasterReportBean implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String cardNo;
    private String leaveType;
    private String leaveTypeDesc;
    private String subTypeCode;
    private String subTypeDesc;
    private Timestamp leaveDateFrom;
    private Timestamp leaveTimeFrom;
    private Timestamp leaveDateTo;
    private Timestamp leaveTimeTo;
    private Timestamp hodApprovedDate;
    private String flag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getLeaveTypeDesc() {
        return leaveTypeDesc;
    }

    public void setLeaveTypeDesc(String leaveTypeDesc) {
        this.leaveTypeDesc = leaveTypeDesc;
    }

    public String getSubTypeCode() {
        return subTypeCode;
    }

    public void setSubTypeCode(String subTypeCode) {
        this.subTypeCode = subTypeCode;
    }

    public String getSubTypeDesc() {
        return subTypeDesc;
    }

    public void setSubTypeDesc(String subTypeDesc) {
        this.subTypeDesc = subTypeDesc;
    }

    public Timestamp getLeaveDateFrom() {
        return leaveDateFrom;
    }

    public void setLeaveDateFrom(Timestamp leaveDateFrom) {
        this.leaveDateFrom = leaveDateFrom;
    }

    public Timestamp getLeaveTimeFrom() {
        return leaveTimeFrom;
    }

    public void setLeaveTimeFrom(Timestamp leaveTimeFrom) {
        this.leaveTimeFrom = leaveTimeFrom;
    }

    public Timestamp getLeaveDateTo() {
        return leaveDateTo;
    }

    public void setLeaveDateTo(Timestamp leaveDateTo) {
        this.leaveDateTo = leaveDateTo;
    }

    public Timestamp getLeaveTimeTo() {
        return leaveTimeTo;
    }

    public void setLeaveTimeTo(Timestamp leaveTimeTo) {
        this.leaveTimeTo = leaveTimeTo;
    }

    public Timestamp getHodApprovedDate() {
        return hodApprovedDate;
    }

    public void setHodApprovedDate(Timestamp hodApprovedDate) {
        this.hodApprovedDate = hodApprovedDate;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LeaveMasterReportBean leaveMasterReportBean = (LeaveMasterReportBean) o;
        if (leaveMasterReportBean.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), leaveMasterReportBean.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LeaveMasterReportBean{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", cardNo='" + cardNo + '\'' +
            ", leaveType='" + leaveType + '\'' +
            ", leaveTypeDesc='" + leaveTypeDesc + '\'' +
            ", subTypeCode='" + subTypeCode + '\'' +
            ", subTypeDesc='" + subTypeDesc + '\'' +
            ", leaveDateFrom=" + leaveDateFrom +
            ", leaveTimeFrom=" + leaveTimeFrom +
            ", leaveDateTo=" + leaveDateTo +
            ", leaveTimeTo=" + leaveTimeTo +
            ", flag='" + flag + '\'' +
            '}';
    }
}
