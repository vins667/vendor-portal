package io.vamani.application.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A EmployeeInformationUpdate.
 */
@Entity
@Table(name = "employee_information_update")
public class EmployeeInformationUpdate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name=" employeeInformationUpdateSeq", sequenceName="employee_information_update_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator=" employeeInformationUpdateSeq")
    private Long id;

    @OneToOne
    @JoinColumn(name="emp_code", referencedColumnName="login")
    private User userCode;

    @Size(max = 500)
    @Column(name = "old_correspondence_address", length = 500)
    private String oldCorrespondenceAddress;

    @Size(max = 15)
    @Column(name = "old_mobile_number", length = 15)
    private String oldMobileNumber;

    @Size(max = 500)
    @Column(name = "correspondence_address", length = 500)
    private String correspondenceAddress;

    @Size(max = 15)
    @Column(name = "mobile_number", length = 15)
    private String mobileNumber;

    @Size(max = 100)
    @Column(name = "image_path", length = 100)
    private String imagePath;

    @Size(max = 100)
    @Column(name = "old_image_path", length = 100)
    private String oldImagePath;

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
    @Column(name = "flag", length = 1)
    private String flag;

    @Size(max = 50)
    @Column(name = "hr_approved_by", length = 50)
    private String hrApprovedBy;

    @Column(name = "hr_approved_date")
    private Instant hrApprovedDate;

    @Column(name = "factory")
    private String factory;

    @Column(name = "process_flag")
    private String processFlag;

    @Column(name = "process_date")
    private Instant processDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCorrespondenceAddress() {
        return correspondenceAddress;
    }

    public EmployeeInformationUpdate correspondenceAddress(String correspondenceAddress) {
        this.correspondenceAddress = correspondenceAddress;
        return this;
    }

    public void setCorrespondenceAddress(String correspondenceAddress) {
        this.correspondenceAddress = correspondenceAddress != null ? correspondenceAddress.toUpperCase() : "";
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public EmployeeInformationUpdate mobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getImagePath() {
        return imagePath;
    }

    public EmployeeInformationUpdate imagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public EmployeeInformationUpdate createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public EmployeeInformationUpdate createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public EmployeeInformationUpdate lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public EmployeeInformationUpdate lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getFlag() {
        return flag;
    }

    public EmployeeInformationUpdate flag(String flag) {
        this.flag = flag;
        return this;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getHrApprovedBy() {
        return hrApprovedBy;
    }

    public EmployeeInformationUpdate hrApprovedBy(String hrApprovedBy) {
        this.hrApprovedBy = hrApprovedBy;
        return this;
    }

    public void setHrApprovedBy(String hrApprovedBy) {
        this.hrApprovedBy = hrApprovedBy;
    }

    public Instant getHrApprovedDate() {
        return hrApprovedDate;
    }

    public EmployeeInformationUpdate hrApprovedDate(Instant hrApprovedDate) {
        this.hrApprovedDate = hrApprovedDate;
        return this;
    }

    public void setHrApprovedDate(Instant hrApprovedDate) {
        this.hrApprovedDate = hrApprovedDate;
    }

    public User getUserCode() {
        return userCode;
    }

    public void setUserCode(User userCode) {
        this.userCode = userCode;
    }

    public String getOldCorrespondenceAddress() {
        return oldCorrespondenceAddress;
    }

    public void setOldCorrespondenceAddress(String oldCorrespondenceAddress) {
        this.oldCorrespondenceAddress = oldCorrespondenceAddress;
    }

    public String getOldMobileNumber() {
        return oldMobileNumber;
    }

    public void setOldMobileNumber(String oldMobileNumber) {
        this.oldMobileNumber = oldMobileNumber;
    }

    public String getOldImagePath() {
        return oldImagePath;
    }

    public void setOldImagePath(String oldImagePath) {
        this.oldImagePath = oldImagePath;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getProcessFlag() {
        return processFlag;
    }

    public void setProcessFlag(String processFlag) {
        this.processFlag = processFlag;
    }

    public Instant getProcessDate() {
        return processDate;
    }

    public void setProcessDate(Instant processDate) {
        this.processDate = processDate;
    }
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeInformationUpdate that = (EmployeeInformationUpdate) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(userCode, that.userCode) &&
            Objects.equals(oldCorrespondenceAddress, that.oldCorrespondenceAddress) &&
            Objects.equals(oldMobileNumber, that.oldMobileNumber) &&
            Objects.equals(correspondenceAddress, that.correspondenceAddress) &&
            Objects.equals(mobileNumber, that.mobileNumber) &&
            Objects.equals(imagePath, that.imagePath) &&
            Objects.equals(oldImagePath, that.oldImagePath) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(createdDate, that.createdDate) &&
            Objects.equals(lastUpdatedBy, that.lastUpdatedBy) &&
            Objects.equals(lastUpdatedDate, that.lastUpdatedDate) &&
            Objects.equals(flag, that.flag) &&
            Objects.equals(hrApprovedBy, that.hrApprovedBy) &&
            Objects.equals(hrApprovedDate, that.hrApprovedDate) &&
            Objects.equals(factory, that.factory) &&
            Objects.equals(processFlag, that.processFlag) &&
            Objects.equals(processDate, that.processDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userCode, oldCorrespondenceAddress, oldMobileNumber, correspondenceAddress, mobileNumber, imagePath, oldImagePath, createdBy, createdDate, lastUpdatedBy, lastUpdatedDate, flag, hrApprovedBy, hrApprovedDate, factory, processFlag, processDate);
    }

    @Override
    public String toString() {
        return "EmployeeInformationUpdate{" +
            "id=" + id +
            ", userCode=" + userCode +
            ", oldCorrespondenceAddress='" + oldCorrespondenceAddress + '\'' +
            ", oldMobileNumber='" + oldMobileNumber + '\'' +
            ", correspondenceAddress='" + correspondenceAddress + '\'' +
            ", mobileNumber='" + mobileNumber + '\'' +
            ", imagePath='" + imagePath + '\'' +
            ", oldImagePath='" + oldImagePath + '\'' +
            ", createdBy='" + createdBy + '\'' +
            ", createdDate=" + createdDate +
            ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
            ", lastUpdatedDate=" + lastUpdatedDate +
            ", flag='" + flag + '\'' +
            ", hrApprovedBy='" + hrApprovedBy + '\'' +
            ", hrApprovedDate=" + hrApprovedDate +
            ", factory='" + factory + '\'' +
            ", processFlag='" + processFlag + '\'' +
            ", processDate=" + processDate +
            '}';
    }
}
