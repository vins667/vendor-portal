package io.vamani.application.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A VcutSessionMaster.
 */
@Entity
@Table(name = "vcut_session_master")
public class VcutSessionMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="vcutSessionMasterSeq", sequenceName="vcut_session_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="vcutSessionMasterSeq")
    private Long id;

    @Size(max = 100)
    @Column(name = "plan_name", length = 100)
    private String planName;

    @Column(name = "day_start_time")
    private Instant dayStartTime;

    @Column(name = "hours")
    private Integer hours;

    @Column(name = "minutes")
    private Integer minutes;

    @Column(name = "total_mins_per_day")
    private Integer totalMinsPerDay;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlanName() {
        return planName;
    }

    public VcutSessionMaster planName(String planName) {
        this.planName = planName;
        return this;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public Instant getDayStartTime() {
        return dayStartTime;
    }

    public VcutSessionMaster dayStartTime(Instant dayStartTime) {
        this.dayStartTime = dayStartTime;
        return this;
    }

    public void setDayStartTime(Instant dayStartTime) {
        this.dayStartTime = dayStartTime;
    }

    public Integer getHours() {
        return hours;
    }

    public VcutSessionMaster hours(Integer hours) {
        this.hours = hours;
        return this;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public VcutSessionMaster minutes(Integer minutes) {
        this.minutes = minutes;
        return this;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public Integer getTotalMinsPerDay() {
        return totalMinsPerDay;
    }

    public VcutSessionMaster totalMinsPerDay(Integer totalMinsPerDay) {
        this.totalMinsPerDay = totalMinsPerDay;
        return this;
    }

    public void setTotalMinsPerDay(Integer totalMinsPerDay) {
        this.totalMinsPerDay = totalMinsPerDay;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public VcutSessionMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public VcutSessionMaster createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public VcutSessionMaster lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public VcutSessionMaster lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
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
        VcutSessionMaster vcutSessionMaster = (VcutSessionMaster) o;
        if (vcutSessionMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vcutSessionMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VcutSessionMaster{" +
            "id=" + getId() +
            ", planName='" + getPlanName() + "'" +
            ", dayStartTime='" + getDayStartTime() + "'" +
            ", hours=" + getHours() +
            ", minutes=" + getMinutes() +
            ", totalMinsPerDay=" + getTotalMinsPerDay() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            "}";
    }
}
