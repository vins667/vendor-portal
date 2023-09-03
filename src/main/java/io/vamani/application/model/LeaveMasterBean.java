package io.vamani.application.model;

import io.vamani.application.domain.LeaveSubTypeMaster;
import io.vamani.application.domain.LeaveTypeMaster;
import io.vamani.application.domain.MobileAttendance;
import io.vamani.application.domain.User;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

public class LeaveMasterBean implements Serializable {

    private Long id;

    private User userCode;

    private Instant leaveDateFrom;

    private Instant leaveDateTo;

    private String flag;

    private String createdBy;

    private Instant createdDate;

    private String hodApprovedBy;

    private Instant hodApprovedDate;

    private String hrApprovedBy;

    private Instant hrApprovedDate;

    private String reason;

    private Float noDays;

    private Instant leaveTimeFrom;

    private Instant leaveTimeTo;

    private String hodRemarks;

    private String hrRemarks;

    private String processFlag;

    private String missPunchType;

    private String factory;

    private LeaveTypeMaster leaveTypeMaster;

    private LeaveSubTypeMaster leaveSubTypeMaster;

    private Long compOffMasterId;

    private List<MobileAttendance> mobileAttendances;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserCode() {
        return userCode;
    }

    public void setUserCode(User userCode) {
        this.userCode = userCode;
    }

    public Instant getLeaveDateFrom() {
        return leaveDateFrom;
    }

    public void setLeaveDateFrom(Instant leaveDateFrom) {
        this.leaveDateFrom = leaveDateFrom;
    }

    public Instant getLeaveDateTo() {
        return leaveDateTo;
    }

    public void setLeaveDateTo(Instant leaveDateTo) {
        this.leaveDateTo = leaveDateTo;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getHodApprovedBy() {
        return hodApprovedBy;
    }

    public void setHodApprovedBy(String hodApprovedBy) {
        this.hodApprovedBy = hodApprovedBy;
    }

    public Instant getHodApprovedDate() {
        return hodApprovedDate;
    }

    public void setHodApprovedDate(Instant hodApprovedDate) {
        this.hodApprovedDate = hodApprovedDate;
    }

    public String getHrApprovedBy() {
        return hrApprovedBy;
    }

    public void setHrApprovedBy(String hrApprovedBy) {
        this.hrApprovedBy = hrApprovedBy;
    }

    public Instant getHrApprovedDate() {
        return hrApprovedDate;
    }

    public void setHrApprovedDate(Instant hrApprovedDate) {
        this.hrApprovedDate = hrApprovedDate;
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

    public Instant getLeaveTimeFrom() {
        return leaveTimeFrom;
    }

    public void setLeaveTimeFrom(Instant leaveTimeFrom) {
        this.leaveTimeFrom = leaveTimeFrom;
    }

    public Instant getLeaveTimeTo() {
        return leaveTimeTo;
    }

    public void setLeaveTimeTo(Instant leaveTimeTo) {
        this.leaveTimeTo = leaveTimeTo;
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

    public LeaveTypeMaster getLeaveTypeMaster() {
        return leaveTypeMaster;
    }

    public void setLeaveTypeMaster(LeaveTypeMaster leaveTypeMaster) {
        this.leaveTypeMaster = leaveTypeMaster;
    }

    public LeaveSubTypeMaster getLeaveSubTypeMaster() {
        return leaveSubTypeMaster;
    }

    public void setLeaveSubTypeMaster(LeaveSubTypeMaster leaveSubTypeMaster) {
        this.leaveSubTypeMaster = leaveSubTypeMaster;
    }

    public List<MobileAttendance> getMobileAttendances() {
        return mobileAttendances;
    }

    public void setMobileAttendances(List<MobileAttendance> mobileAttendances) {
        this.mobileAttendances = mobileAttendances;
    }

    public Long getCompOffMasterId() {
        return compOffMasterId;
    }

    public void setCompOffMasterId(Long compOffMasterId) {
        this.compOffMasterId = compOffMasterId;
    }
}
