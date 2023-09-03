package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A HolidayMaster.
 */
@Entity
@Table(name = "holiday_master")
public class HolidayMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="holidayMasterSeq", sequenceName="holiday_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="holidayMasterSeq")
    private Long id;

    @NotNull
    @Column(name = "holiday_date", nullable = false)
    private LocalDate holidayDate;

    @NotNull
    @Size(max = 200)
    @Column(name = "holiday_name", length = 200, nullable = false)
    private String holidayName;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Size(max = 1)
    @Column(name = "flag", length = 1)
    private String flag;


    @Column(name = "created_date")
    private Instant createdDate;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    @JoinColumn(name = "factory_master_id")
    private FactoryMaster factoryMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getHolidayDate() {
        return holidayDate;
    }

    public HolidayMaster holidayDate(LocalDate holidayDate) {
        this.holidayDate = holidayDate;
        return this;
    }

    public void setHolidayDate(LocalDate holidayDate) {
        this.holidayDate = holidayDate;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public HolidayMaster holidayName(String holidayName) {
        this.holidayName = holidayName;
        return this;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public HolidayMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public HolidayMaster createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public FactoryMaster getFactoryMaster() {
        return factoryMaster;
    }

    public HolidayMaster factoryMaster(FactoryMaster factoryMaster) {
        this.factoryMaster = factoryMaster;
        return this;
    }

    public void setFactoryMaster(FactoryMaster factoryMaster) {
        this.factoryMaster = factoryMaster;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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
        HolidayMaster holidayMaster = (HolidayMaster) o;
        if (holidayMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), holidayMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HolidayMaster{" +
            "id=" + getId() +
            ", holidayDate='" + getHolidayDate() + "'" +
            ", holidayName='" + getHolidayName() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}
