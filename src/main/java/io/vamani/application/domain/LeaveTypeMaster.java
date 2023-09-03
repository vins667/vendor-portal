package io.vamani.application.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A LeaveTypeMaster.
 */
@Entity
@Table(name = "leave_type_master")
public class LeaveTypeMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="leaveTypeMasterSeq", sequenceName="leave_type_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="leaveTypeMasterSeq")
    private Long id;

    @NotNull
    @Size(max = 5)
    @Column(name = "leave_code", length = 5, nullable = false)
    private String leaveCode;

    @NotNull
    @Size(max = 50)
    @Column(name = "leave_name", length = 50, nullable = false)
    private String leaveName;

    @NotNull
    @Size(max = 1)
    @Column(name = "flag", length = 1, nullable = false)
    private String flag;

    @NotNull
    @Size(max = 1)
    @Column(name = "leave_type", length = 1, nullable = false)
    private String leaveType;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLeaveCode() {
        return leaveCode;
    }

    public LeaveTypeMaster leaveCode(String leaveCode) {
        this.leaveCode = leaveCode;
        return this;
    }

    public void setLeaveCode(String leaveCode) {
        this.leaveCode = leaveCode;
    }

    public String getLeaveName() {
        return leaveName;
    }

    public LeaveTypeMaster leaveName(String leaveName) {
        this.leaveName = leaveName;
        return this;
    }

    public void setLeaveName(String leaveName) {
        this.leaveName = leaveName;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public LeaveTypeMaster leaveType(String leaveType) {
        this.leaveType = leaveType;
        return this;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getFlag() {
        return flag;
    }

    public LeaveTypeMaster flag(String flag) {
        this.flag = flag;
        return this;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public LeaveTypeMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public LeaveTypeMaster createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
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
        LeaveTypeMaster leaveTypeMaster = (LeaveTypeMaster) o;
        if (leaveTypeMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), leaveTypeMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LeaveTypeMaster{" +
            "id=" + getId() +
            ", leaveCode='" + getLeaveCode() + "'" +
            ", leaveName='" + getLeaveName() + "'" +
            ", leaveType='" + getLeaveType() + "'" +
            ", flag='" + getFlag() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}
