package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A LeaveMasterRemarksDetails.
 */
@Entity
@Table(name = "leave_master_remarks_details")
public class LeaveMasterRemarksDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="leaveMasterRemarksDetailsSeq", sequenceName="leave_master_remarks_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="leaveMasterRemarksDetailsSeq")
    private Long id;

    @Size(max = 50)
    @Column(name = "emp_code", length = 50)
    private String empCode;

    @Size(max = 200)
    @Column(name = "emp_name", length = 200)
    private String empName;

    @Size(max = 50)
    @Column(name = "forward_code", length = 50)
    private String forwardCode;

    @Size(max = 200)
    @Column(name = "forward_name", length = 200)
    private String forwardName;

    @Size(max = 500)
    @Column(name = "remarks", length = 500)
    private String remarks;

    @Size(max = 1)
    @Column(name = "status", length = 1)
    private String status;

    @Column(name = "created_date")
    private Instant createdDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "leave_master_id")
    @NotNull
    @JsonIgnoreProperties("")
    private LeaveMaster leaveMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpCode() {
        return empCode;
    }

    public LeaveMasterRemarksDetails empCode(String empCode) {
        this.empCode = empCode;
        return this;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public LeaveMasterRemarksDetails empName(String empName) {
        this.empName = empName;
        return this;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getForwardCode() {
        return forwardCode;
    }

    public LeaveMasterRemarksDetails forwardCode(String forwardCode) {
        this.forwardCode = forwardCode;
        return this;
    }

    public void setForwardCode(String forwardCode) {
        this.forwardCode = forwardCode;
    }

    public String getForwardName() {
        return forwardName;
    }

    public LeaveMasterRemarksDetails forwardName(String forwardName) {
        this.forwardName = forwardName;
        return this;
    }

    public void setForwardName(String forwardName) {
        this.forwardName = forwardName;
    }

    public String getRemarks() {
        return remarks;
    }

    public LeaveMasterRemarksDetails remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStatus() {
        return status;
    }

    public LeaveMasterRemarksDetails status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public LeaveMasterRemarksDetails createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public LeaveMaster getLeaveMaster() {
        return leaveMaster;
    }

    public LeaveMasterRemarksDetails leaveMaster(LeaveMaster leaveMaster) {
        this.leaveMaster = leaveMaster;
        return this;
    }

    public void setLeaveMaster(LeaveMaster leaveMaster) {
        this.leaveMaster = leaveMaster;
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
        LeaveMasterRemarksDetails leaveMasterRemarksDetails = (LeaveMasterRemarksDetails) o;
        if (leaveMasterRemarksDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), leaveMasterRemarksDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LeaveMasterRemarksDetails{" +
            "id=" + getId() +
            ", empCode='" + getEmpCode() + "'" +
            ", empName='" + getEmpName() + "'" +
            ", forwardCode='" + getForwardCode() + "'" +
            ", forwardName='" + getForwardName() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}
