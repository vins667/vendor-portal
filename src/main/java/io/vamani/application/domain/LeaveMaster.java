package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

/**
 * A LeaveMaster.
 */
@Entity
@Table(name = "leave_master")
public class LeaveMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="leaveMasterSeq", sequenceName="leave_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="leaveMasterSeq")
    private Long id;

    /*@NotNull
    @Size(max = 50)
    @Column(name = "emp_code", length = 50, nullable = false)*/
    @OneToOne
    @JoinColumn(name="emp_code", referencedColumnName="login")
    private User userCode;
    // private String empCode;

    @NotNull
    @Column(name = "leave_date_from", nullable = false)
    private Instant leaveDateFrom;

    @NotNull
    @Column(name = "leave_date_to", nullable = false)
    private Instant leaveDateTo;

    @NotNull
    @Size(max = 1)
    @Column(name = "flag", length = 1, nullable = false)
    private String flag;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Size(max = 50)
    @Column(name = "hod_approved_by", length = 50)
    private String hodApprovedBy;

    @Column(name = "hod_approved_date")
    private Instant hodApprovedDate;

    @Size(max = 50)
    @Column(name = "hr_approved_by", length = 50)
    private String hrApprovedBy;

    @Column(name = "hr_approved_date")
    private Instant hrApprovedDate;

    @Column(name = "reason", length = 500)
    private String reason;

    @Column(name = "no_days")
    private Float noDays;

    @Column(name = "leave_time_from")
    private Instant leaveTimeFrom;

    @Column(name = "leave_time_to")
    private Instant leaveTimeTo;

    @Column(name = "hod_remarks", length = 500)
    private String hodRemarks;

    @Column(name = "hr_remarks", length = 500)
    private String hrRemarks;

    @Column(name = "process_flag")
    private String processFlag;

    @Column(name = "miss_punch_type")
    private String missPunchType;

    @Column(name = "factory")
    private String factory;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    @JoinColumn(name = "leave_type_master_id")
    private LeaveTypeMaster leaveTypeMaster;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    @JoinColumn(name = "leave_sub_type_master_id")
    private LeaveSubTypeMaster leaveSubTypeMaster;

    @Column(name = "comp_off_master_id")
    private Long compOffMasterId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "leave_master_id", referencedColumnName = "id")
    private List<MobileAttendance> mobileAttendances;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserCode() {
        return userCode;
    }

    public LeaveMaster userCode(User userCode) {
        this.userCode = userCode;
        return this;
    }

    public void setUserCode(User userCode) {
        this.userCode = userCode;
    }

    public Instant getLeaveDateFrom() {
        return leaveDateFrom;
    }

    public LeaveMaster leaveDateFrom(Instant leaveDateFrom) {
        this.leaveDateFrom = leaveDateFrom;
        return this;
    }

    public void setLeaveDateFrom(Instant leaveDateFrom) {
        this.leaveDateFrom = leaveDateFrom;
    }

    public Instant getLeaveDateTo() {
        return leaveDateTo;
    }

    public LeaveMaster leaveDateTo(Instant leaveDateTo) {
        this.leaveDateTo = leaveDateTo;
        return this;
    }

    public void setLeaveDateTo(Instant leaveDateTo) {
        this.leaveDateTo = leaveDateTo;
    }

    public String getFlag() {
        return flag;
    }

    public LeaveMaster flag(String flag) {
        this.flag = flag;
        return this;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public LeaveMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public LeaveMaster createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getHodApprovedBy() {
        return hodApprovedBy;
    }

    public LeaveMaster hodApprovedBy(String hodApprovedBy) {
        this.hodApprovedBy = hodApprovedBy;
        return this;
    }

    public void setHodApprovedBy(String hodApprovedBy) {
        this.hodApprovedBy = hodApprovedBy;
    }

    public Instant getHodApprovedDate() {
        return hodApprovedDate;
    }

    public LeaveMaster hodApprovedDate(Instant hodApprovedDate) {
        this.hodApprovedDate = hodApprovedDate;
        return this;
    }

    public void setHodApprovedDate(Instant hodApprovedDate) {
        this.hodApprovedDate = hodApprovedDate;
    }

    public String getHrApprovedBy() {
        return hrApprovedBy;
    }

    public LeaveMaster hrApprovedBy(String hrApprovedBy) {
        this.hrApprovedBy = hrApprovedBy;
        return this;
    }

    public void setHrApprovedBy(String hrApprovedBy) {
        this.hrApprovedBy = hrApprovedBy;
    }

    public Instant getHrApprovedDate() {
        return hrApprovedDate;
    }

    public LeaveMaster hrApprovedDate(Instant hrApprovedDate) {
        this.hrApprovedDate = hrApprovedDate;
        return this;
    }

    public void setHrApprovedDate(Instant hrApprovedDate) {
        this.hrApprovedDate = hrApprovedDate;
    }

    public String getReason() {
        return reason;
    }

    public LeaveMaster reason(String reason) {
        this.reason = reason;
        return this;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Instant getLeaveTimeFrom() {
        return leaveTimeFrom;
    }

    public LeaveMaster leaveTimeFrom(Instant leaveTimeFrom) {
        this.leaveTimeFrom = leaveTimeFrom;
        return this;
    }

    public void setLeaveTimeFrom(Instant leaveTimeFrom) {
        this.leaveTimeFrom = leaveTimeFrom;
    }

    public Instant getLeaveTimeTo() {
        return leaveTimeTo;
    }

    public LeaveMaster leaveTimeTo(Instant leaveTimeTo) {
        this.leaveTimeTo = leaveTimeTo;
        return this;
    }

    public void setLeaveTimeTo(Instant leaveTimeTo) {
        this.leaveTimeTo = leaveTimeTo;
    }

    public LeaveTypeMaster getLeaveTypeMaster() {
        return leaveTypeMaster;
    }

    public LeaveMaster leaveTypeMaster(LeaveTypeMaster leaveTypeMaster) {
        this.leaveTypeMaster = leaveTypeMaster;
        return this;
    }

    public void setLeaveTypeMaster(LeaveTypeMaster leaveTypeMaster) {
        this.leaveTypeMaster = leaveTypeMaster;
    }

    public LeaveSubTypeMaster getLeaveSubTypeMaster() {
        return leaveSubTypeMaster;
    }

    public LeaveMaster leaveSubTypeMaster(LeaveSubTypeMaster leaveSubTypeMaster) {
        this.leaveSubTypeMaster = leaveSubTypeMaster;
        return this;
    }

    public void setLeaveSubTypeMaster(LeaveSubTypeMaster leaveSubTypeMaster) {
        this.leaveSubTypeMaster = leaveSubTypeMaster;
    }

    public Float getNoDays() { return noDays; }

    public void setNoDays(Float noDays) { this.noDays = noDays; }

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

    public String getProcessFlag() { return processFlag; }

    public void setProcessFlag(String processFlag) { this.processFlag = processFlag; }

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

    public Long getCompOffMasterId() {
        return compOffMasterId;
    }

    public void setCompOffMasterId(Long compOffMasterId) {
        this.compOffMasterId = compOffMasterId;
    }

    public List<MobileAttendance> getMobileAttendances() {
        return mobileAttendances;
    }

    public void setMobileAttendances(List<MobileAttendance> mobileAttendances) {
        this.mobileAttendances = mobileAttendances;
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
        LeaveMaster leaveMaster = (LeaveMaster) o;
        if (leaveMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), leaveMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LeaveMaster{" +
            "id=" + getId() +
            ", userCode='" + getUserCode() + "'" +
            ", leaveDateFrom='" + getLeaveDateFrom() + "'" +
            ", leaveDateTo='" + getLeaveDateTo() + "'" +
            ", flag='" + getFlag() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", hodApprovedBy='" + getHodApprovedBy() + "'" +
            ", hodApprovedDate='" + getHodApprovedDate() + "'" +
            ", hrApprovedBy='" + getHrApprovedBy() + "'" +
            ", hrApprovedDate='" + getHrApprovedDate() + "'" +
            ", reason='" + getReason() + "'" +
            ", leaveTimeFrom='" + getLeaveTimeFrom() + "'" +
            ", leaveTimeTo='" + getLeaveTimeTo() + "'" +
            "}";
    }
}
