package io.vamani.application.model;

import io.vamani.application.domain.User;

import java.util.Objects;

public class EmployeeInformationUpdateBean {
    private Long id;
    private String correspondenceAddress;
    private String mobileNumber;
    private String imagePath;
    private String flag;
    private String oldCorrespondenceAddress;
    private String oldMobileNumber;
    private String oldImagePath;
    private User userCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCorrespondenceAddress() {
        return correspondenceAddress;
    }

    public void setCorrespondenceAddress(String correspondenceAddress) {
        this.correspondenceAddress = correspondenceAddress;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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

    public User getUserCode() {
        return userCode;
    }

    public void setUserCode(User userCode) {
        this.userCode = userCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeInformationUpdateBean that = (EmployeeInformationUpdateBean) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(correspondenceAddress, that.correspondenceAddress) &&
            Objects.equals(mobileNumber, that.mobileNumber) &&
            Objects.equals(imagePath, that.imagePath) &&
            Objects.equals(flag, that.flag) &&
            Objects.equals(oldCorrespondenceAddress, that.oldCorrespondenceAddress) &&
            Objects.equals(oldMobileNumber, that.oldMobileNumber) &&
            Objects.equals(oldImagePath, that.oldImagePath) &&
            Objects.equals(userCode, that.userCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, correspondenceAddress, mobileNumber, imagePath, flag, oldCorrespondenceAddress, oldMobileNumber, oldImagePath, userCode);
    }

    @Override
    public String toString() {
        return "EmployeeInformationUpdateBean{" +
            "id=" + id +
            ", correspondenceAddress='" + correspondenceAddress + '\'' +
            ", mobileNumber='" + mobileNumber + '\'' +
            ", imagePath='" + imagePath + '\'' +
            ", flag='" + flag + '\'' +
            ", oldCorrespondenceAddress='" + oldCorrespondenceAddress + '\'' +
            ", oldMobileNumber='" + oldMobileNumber + '\'' +
            ", oldImagePath='" + oldImagePath + '\'' +
            ", userCode=" + userCode +
            '}';
    }
}
