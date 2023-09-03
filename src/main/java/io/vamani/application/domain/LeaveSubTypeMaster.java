package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A LeaveSubTypeMaster.
 */
@Entity
@Table(name = "leave_sub_type_master")
public class LeaveSubTypeMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="leaveSubTypeMasterSeq", sequenceName="leave_sub_type_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="leaveSubTypeMasterSeq")
    private Long id;

    @NotNull
    @Size(max = 5)
    @Column(name = "sub_type_code", length = 5, nullable = false)
    private String subTypeCode;

    @NotNull
    @Size(max = 50)
    @Column(name = "sub_type_name", length = 50, nullable = false)
    private String subTypeName;

    @NotNull
    @Size(max = 1)
    @Column(name = "flag", length = 1, nullable = false)
    private String flag;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    @JoinColumn(name = "leave_type_master_id")
    private LeaveTypeMaster leaveTypeMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubTypeCode() {
        return subTypeCode;
    }

    public LeaveSubTypeMaster subTypeCode(String subTypeCode) {
        this.subTypeCode = subTypeCode;
        return this;
    }

    public void setSubTypeCode(String subTypeCode) {
        this.subTypeCode = subTypeCode;
    }

    public String getSubTypeName() {
        return subTypeName;
    }

    public LeaveSubTypeMaster subTypeName(String subTypeName) {
        this.subTypeName = subTypeName;
        return this;
    }

    public void setSubTypeName(String subTypeName) {
        this.subTypeName = subTypeName;
    }

    public String getFlag() {
        return flag;
    }

    public LeaveSubTypeMaster flag(String flag) {
        this.flag = flag;
        return this;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public LeaveSubTypeMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public LeaveSubTypeMaster createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public LeaveTypeMaster getLeaveTypeMaster() {
        return leaveTypeMaster;
    }

    public LeaveSubTypeMaster leaveTypeMaster(LeaveTypeMaster leaveTypeMaster) {
        this.leaveTypeMaster = leaveTypeMaster;
        return this;
    }

    public void setLeaveTypeMaster(LeaveTypeMaster leaveTypeMaster) {
        this.leaveTypeMaster = leaveTypeMaster;
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
        LeaveSubTypeMaster leaveSubTypeMaster = (LeaveSubTypeMaster) o;
        if (leaveSubTypeMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), leaveSubTypeMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LeaveSubTypeMaster{" +
            "id=" + getId() +
            ", subTypeCode='" + getSubTypeCode() + "'" +
            ", subTypeName='" + getSubTypeName() + "'" +
            ", flag='" + getFlag() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}
