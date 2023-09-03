package io.vamani.application.vendorportal.domain;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A ProfileWorkFlow.
 */
@Entity
@Table(name = "profile_work_flow")
public class ProfileWorkFlow implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name="profileWorkFlowSeq", sequenceName="profile_work_flow_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="profileWorkFlowSeq")
    private Long id;

    @Size(max = 50)
    @Column(name = "user_code", length = 50)
    private String userCode;

    @Size(max = 200)
    @Column(name = "user_name", length = 200)
    private String userName;

    @Size(max = 50)
    @Column(name = "forward_code", length = 50)
    private String forwardCode;

    @Size(max = 200)
    @Column(name = "forward_name", length = 200)
    private String forwardName;

    @Size(max = 1)
    @Column(name = "forward_flag", length = 1)
    private String forwardFlag;

    @Size(max = 1)
    @Column(name = "user_type", length = 1)
    private String userType;

    @Size(max = 500)
    @Column(name = "remarks", length = 500)
    private String remarks;

    @Column(name = "created_date")
    private Instant createdDate;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "vendor_id")
    private Long vendorId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public ProfileWorkFlow userCode(String userCode) {
        this.userCode = userCode;
        return this;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public ProfileWorkFlow userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getForwardCode() {
        return forwardCode;
    }

    public ProfileWorkFlow forwardCode(String forwardCode) {
        this.forwardCode = forwardCode;
        return this;
    }

    public void setForwardCode(String forwardCode) {
        this.forwardCode = forwardCode;
    }

    public String getForwardName() {
        return forwardName;
    }

    public ProfileWorkFlow forwardName(String forwardName) {
        this.forwardName = forwardName;
        return this;
    }

    public void setForwardName(String forwardName) {
        this.forwardName = forwardName;
    }

    public String getForwardFlag() {
        return forwardFlag;
    }

    public ProfileWorkFlow forwardFlag(String forwardFlag) {
        this.forwardFlag = forwardFlag;
        return this;
    }

    public void setForwardFlag(String forwardFlag) {
        this.forwardFlag = forwardFlag;
    }

    public String getUserType() {
        return userType;
    }

    public ProfileWorkFlow userType(String userType) {
        this.userType = userType;
        return this;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getRemarks() {
        return remarks;
    }

    public ProfileWorkFlow remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public ProfileWorkFlow createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public ProfileWorkFlow createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public ProfileWorkFlow vendorId(Long vendorId) {
        this.vendorId = vendorId;
        return this;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
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
        ProfileWorkFlow profileWorkFlow = (ProfileWorkFlow) o;
        if (profileWorkFlow.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), profileWorkFlow.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProfileWorkFlow{" +
            "id=" + getId() +
            ", userCode='" + getUserCode() + "'" +
            ", userName='" + getUserName() + "'" +
            ", forwardCode='" + getForwardCode() + "'" +
            ", forwardName='" + getForwardName() + "'" +
            ", forwardFlag='" + getForwardFlag() + "'" +
            ", userType='" + getUserType() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", vendorId=" + getVendorId() +
            "}";
    }
}
