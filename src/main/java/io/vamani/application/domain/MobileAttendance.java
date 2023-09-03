package io.vamani.application.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A MobileAttendance.
 */
@Entity
@Table(name = "mobile_attendance")
public class MobileAttendance implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="mobileAttendanceSeq", sequenceName="mobile_attendance_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="mobileAttendanceSeq")
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "card_no", length = 50, nullable = false)
    private String cardNo;

    @Column(name = "attendance_date")
    private Instant attendanceDate;

    @Size(max = 50)
    @Column(name = "latitude", length = 50)
    private String latitude;

    @Size(max = 50)
    @Column(name = "longitude", length = 50)
    private String longitude;

    @Size(max = 100)
    @Column(name = "file_name", length = 100)
    private String fileName;

    @Size(max = 1)
    @Column(name = "attendance_type", length = 1)
    private String attendanceType;

    @Size(max = 15)
    @Column(name = "factory_code", length = 15)
    private String factoryCode;

    @Size(max = 500)
    @Column(name = "remarks", length = 500)
    private String remarks;

    @Column(name = "leave_master_id")
    private Long leaveMasterId;

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

    public String getCardNo() {
        return cardNo;
    }

    public MobileAttendance cardNo(String cardNo) {
        this.cardNo = cardNo;
        return this;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Instant getAttendanceDate() {
        return attendanceDate;
    }

    public MobileAttendance attendanceDate(Instant attendanceDate) {
        this.attendanceDate = attendanceDate;
        return this;
    }

    public void setAttendanceDate(Instant attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getLatitude() {
        return latitude;
    }

    public MobileAttendance latitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public MobileAttendance longitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getFileName() {
        return fileName;
    }

    public MobileAttendance fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAttendanceType() {
        return attendanceType;
    }

    public MobileAttendance attendanceType(String attendanceType) {
        this.attendanceType = attendanceType;
        return this;
    }

    public void setAttendanceType(String attendanceType) {
        this.attendanceType = attendanceType;
    }

    public String getFactoryCode() {
        return factoryCode;
    }

    public MobileAttendance factoryCode(String factoryCode) {
        this.factoryCode = factoryCode;
        return this;
    }

    public void setFactoryCode(String factoryCode) {
        this.factoryCode = factoryCode;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public MobileAttendance createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public MobileAttendance createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLeaveMasterId() {
        return leaveMasterId;
    }

    public void setLeaveMasterId(Long leaveMasterId) {
        this.leaveMasterId = leaveMasterId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
        MobileAttendance mobileAttendance = (MobileAttendance) o;
        if (mobileAttendance.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mobileAttendance.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MobileAttendance{" +
            "id=" + getId() +
            ", cardNo='" + getCardNo() + "'" +
            ", attendanceDate='" + getAttendanceDate() + "'" +
            ", latitude='" + getLatitude() + "'" +
            ", longitude='" + getLongitude() + "'" +
            ", fileName='" + getFileName() + "'" +
            ", attendanceType='" + getAttendanceType() + "'" +
            ", factoryCode='" + getFactoryCode() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}
