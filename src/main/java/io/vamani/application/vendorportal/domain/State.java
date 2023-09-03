package io.vamani.application.vendorportal.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A State.
 */
@Entity
@Table(name = "state")
public class State implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="stateSeq", sequenceName="state_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="stateSeq")
    private Long id;

    @NotNull
    @Size(max = 10)
    @Column(name = "state_code", length = 10, nullable = false)
    private String stateCode;

    @NotNull
    @Size(max = 45)
    @Column(name = "state_name", length = 45, nullable = false)
    private String stateName;

    @Column(name = "geo_code")
    private Long geoCode;

    @Size(max = 2)
    @Column(name = "gst_state_code", length = 2)
    private String gstStateCode;

    @Size(max = 1)
    @Column(name = "status", length = 1)
    private String status;

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

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    private Country country;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStateCode() {
        return stateCode;
    }

    public State stateCode(String stateCode) {
        this.stateCode = stateCode;
        return this;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateName() {
        return stateName;
    }

    public State stateName(String stateName) {
        this.stateName = stateName;
        return this;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Long getGeoCode() {
        return geoCode;
    }

    public State geoCode(Long geoCode) {
        this.geoCode = geoCode;
        return this;
    }

    public void setGeoCode(Long geoCode) {
        this.geoCode = geoCode;
    }

    public String getGstStateCode() {
        return gstStateCode;
    }

    public State gstStateCode(String gstStateCode) {
        this.gstStateCode = gstStateCode;
        return this;
    }

    public void setGstStateCode(String gstStateCode) {
        this.gstStateCode = gstStateCode;
    }

    public String getStatus() {
        return status;
    }

    public State status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public State createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public State createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public State lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public State lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Country getCountry() {
        return country;
    }

    public State country(Country country) {
        this.country = country;
        return this;
    }

    public void setCountry(Country country) {
        this.country = country;
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
        State state = (State) o;
        if (state.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), state.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "State{" +
            "id=" + getId() +
            ", stateCode='" + getStateCode() + "'" +
            ", stateName='" + getStateName() + "'" +
            ", geoCode=" + getGeoCode() +
            ", gstStateCode='" + getGstStateCode() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            "}";
    }
}
