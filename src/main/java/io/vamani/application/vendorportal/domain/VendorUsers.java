package io.vamani.application.vendorportal.domain;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A VendorUsers.
 */
@Entity
@Table(name = "vendor_users")
public class VendorUsers implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="vendorUsersSeq", sequenceName="vendor_users_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="vendorUsersSeq")
    private Long id;

    @Size(max = 10)
    @Column(name = "user_type", length = 10)
    private String userType;

    @Size(max = 100)
    @Column(name = "user_name", length = 100)
    private String userName;

    @Size(max = 100)
    @Column(name = "designation", length = 100)
    private String designation;

    @Size(max = 45)
    @Column(name = "email_id", length = 45)
    private String emailId;

    @Size(max = 45)
    @Column(name = "mobile_number", length = 45)
    private String mobileNumber;

    @Column(name = "vendor_id")
    private Long vendorId;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Size(max = 50)
    @Column(name = "last_updated_by", length = 50)
    private String lastUpdatedBy;

    @Column(name = "last_updated_date")
    private Instant lastUpdatedDate;

    @Size(max = 1)
    @Column(name = "data_flag", length = 1)
    private String dataFlag;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserType() {
        return userType;
    }

    public VendorUsers userType(String userType) {
        this.userType = userType;
        return this;
    }

    public void setUserType(String userType) {
        this.userType = userType != null ? userType.toUpperCase() : "";
    }

    public String getUserName() {
        return userName;
    }

    public VendorUsers userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName != null ? userName.toUpperCase() : "";
    }

    public String getDesignation() {
        return designation;
    }

    public VendorUsers designation(String designation) {
        this.designation = designation;
        return this;
    }

    public void setDesignation(String designation) {
        this.designation = designation != null ? designation.toUpperCase() : "";
    }

    public String getEmailId() {
        return emailId;
    }

    public VendorUsers emailId(String emailId) {
        this.emailId = emailId;
        return this;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId != null ? emailId.toLowerCase() : "";
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public VendorUsers mobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber != null ? mobileNumber.toUpperCase() : "";
    }

    public Long getVendorId() {
        return vendorId;
    }

    public VendorUsers vendorId(Long vendorId) {
        this.vendorId = vendorId;
        return this;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public VendorUsers createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public VendorUsers createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public VendorUsers lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public VendorUsers lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getDataFlag() {
        return dataFlag;
    }

    public void setDataFlag(String dataFlag) {
        this.dataFlag = dataFlag;
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
        VendorUsers vendorUsers = (VendorUsers) o;
        if (vendorUsers.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vendorUsers.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VendorUsers{" +
            "id=" + getId() +
            ", userType='" + getUserType() + "'" +
            ", userName='" + getUserName() + "'" +
            ", designation='" + getDesignation() + "'" +
            ", emailId='" + getEmailId() + "'" +
            ", mobileNumber='" + getMobileNumber() + "'" +
            ", vendorId=" + getVendorId() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            "}";
    }
}
