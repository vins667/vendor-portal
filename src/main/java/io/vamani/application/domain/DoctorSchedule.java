package io.vamani.application.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DoctorSchedule.
 */
@Entity
@Table(name = "doctor_schedule")
public class DoctorSchedule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="doctorScheduleSeq", sequenceName="doctor_schedule_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="doctorScheduleSeq")
    private Long id;

    @Column(name = "unit_id")
    private Long unitId;

    @Size(max = 50)
    @Column(name = "days", length = 50)
    private String days;

    @Size(max = 50)
    @Column(name = "timing", length = 50)
    private String timing;

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

    public Long getUnitId() {
        return unitId;
    }

    public DoctorSchedule unitId(Long unitId) {
        this.unitId = unitId;
        return this;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public String getDays() {
        return days;
    }

    public DoctorSchedule days(String days) {
        this.days = days;
        return this;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getTiming() {
        return timing;
    }

    public DoctorSchedule timing(String timing) {
        this.timing = timing;
        return this;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public DoctorSchedule createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public DoctorSchedule createdDate(Instant createdDate) {
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
        DoctorSchedule doctorSchedule = (DoctorSchedule) o;
        if (doctorSchedule.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), doctorSchedule.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DoctorSchedule{" +
            "id=" + getId() +
            ", unitId=" + getUnitId() +
            ", days='" + getDays() + "'" +
            ", timing='" + getTiming() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}
