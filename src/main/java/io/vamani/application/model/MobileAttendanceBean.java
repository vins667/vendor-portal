package io.vamani.application.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class MobileAttendanceBean implements Serializable {
    private Long id;

    private String cardNo;

    private Instant attendanceDate;

    private String attendanceDateView;

    private String latitude;

    private String longitude;

    private String fileName;

    private String attendanceType;

    private String factoryCode;

    private String remarks;

    private Long leaveMasterId;

    private String createdBy;

    private Instant createdDate;

    private Boolean exist;

    private String errorMessage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Instant getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(Instant attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAttendanceType() {
        return attendanceType;
    }

    public void setAttendanceType(String attendanceType) {
        this.attendanceType = attendanceType;
    }

    public String getFactoryCode() {
        return factoryCode;
    }

    public void setFactoryCode(String factoryCode) {
        this.factoryCode = factoryCode;
    }

    public Long getLeaveMasterId() {
        return leaveMasterId;
    }

    public void setLeaveMasterId(Long leaveMasterId) {
        this.leaveMasterId = leaveMasterId;
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

    public Boolean getExist() {
        return exist;
    }

    public void setExist(Boolean exist) {
        this.exist = exist;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getAttendanceDateView() {
        return attendanceDateView;
    }

    public void setAttendanceDateView(String attendanceDateView) {
        this.attendanceDateView = attendanceDateView;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MobileAttendanceBean that = (MobileAttendanceBean) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(cardNo, that.cardNo) &&
            Objects.equals(attendanceDate, that.attendanceDate) &&
            Objects.equals(latitude, that.latitude) &&
            Objects.equals(longitude, that.longitude) &&
            Objects.equals(fileName, that.fileName) &&
            Objects.equals(attendanceType, that.attendanceType) &&
            Objects.equals(factoryCode, that.factoryCode) &&
            Objects.equals(leaveMasterId, that.leaveMasterId) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(createdDate, that.createdDate) &&
            Objects.equals(exist, that.exist) &&
            Objects.equals(errorMessage, that.errorMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardNo, attendanceDate, latitude, longitude, fileName, attendanceType, factoryCode, leaveMasterId, createdBy, createdDate, exist, errorMessage);
    }

    @Override
    public String toString() {
        return "MobileAttendanceBean{" +
            "id=" + id +
            ", cardNo='" + cardNo + '\'' +
            ", attendanceDate=" + attendanceDate +
            ", latitude='" + latitude + '\'' +
            ", longitude='" + longitude + '\'' +
            ", fileName='" + fileName + '\'' +
            ", attendanceType='" + attendanceType + '\'' +
            ", factoryCode='" + factoryCode + '\'' +
            ", leaveMasterId=" + leaveMasterId +
            ", createdBy='" + createdBy + '\'' +
            ", createdDate=" + createdDate +
            ", exist=" + exist +
            ", errorMessage='" + errorMessage + '\'' +
            '}';
    }
}
