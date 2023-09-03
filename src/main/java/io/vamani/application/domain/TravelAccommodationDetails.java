package io.vamani.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A TravelAccommodationDetails.
 */
@Entity
@Table(name = "travel_accommodation_details")
public class TravelAccommodationDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="travelAccommodationDetailsSeq", sequenceName="travel_accommodation_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="travelAccommodationDetailsSeq")
    
    private Long id;

    @Column(name = "accommodation_date")
    private Instant accommodationDate;

    @Column(name = "accommodation_type")
    private String accommodationType;

    @Size(max = 100)
    @Column(name = "accommodation_name", length = 100)
    private String accommodationName;

    @Column(name = "accommodation_tarif")
    private Double accommodationTarif;

    @Column(name = "days_stay")
    private Double daysStay;

    @Column(name = "early_checkin")
    private Boolean earlyCheckin;

    @Size(max = 20)
    @Column(name = "created_by", length = 20)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Size(max = 20)
    @Column(name = "last_updated_by", length = 20)
    private String lastUpdatedBy;

    @Column(name = "last_updated_date")
    private Instant lastUpdatedDate;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnore
    @JoinColumn(name = "travel_application_master_id")
    private TravelApplicationMaster travelApplicationMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getAccommodationDate() {
        return accommodationDate;
    }

    public TravelAccommodationDetails accommodationDate(Instant accommodationDate) {
        this.accommodationDate = accommodationDate;
        return this;
    }

    public void setAccommodationDate(Instant accommodationDate) {
        this.accommodationDate = accommodationDate;
    }

    public String getAccommodationType() {
        return accommodationType;
    }

    public TravelAccommodationDetails accommodationType(String accommodationType) {
        this.accommodationType = accommodationType;
        return this;
    }

    public void setAccommodationType(String accommodationType) {
        this.accommodationType = accommodationType;
    }

    public String getAccommodationName() {
        return accommodationName;
    }

    public TravelAccommodationDetails accommodationName(String accommodationName) {
        this.accommodationName = accommodationName != null ? accommodationName.trim().toUpperCase() : "";
        return this;
    }

    public void setAccommodationName(String accommodationName) {
        this.accommodationName = accommodationName != null ? accommodationName.trim().toUpperCase() : "";
    }

    public Double getAccommodationTarif() {
        return accommodationTarif;
    }

    public TravelAccommodationDetails accommodationTarif(Double accommodationTarif) {
        this.accommodationTarif = accommodationTarif;
        return this;
    }

    public void setAccommodationTarif(Double accommodationTarif) {
        this.accommodationTarif = accommodationTarif;
    }

    public Double getDaysStay() {
        return daysStay;
    }

    public TravelAccommodationDetails daysStay(Double daysStay) {
        this.daysStay = daysStay;
        return this;
    }

    public void setDaysStay(Double daysStay) {
        this.daysStay = daysStay;
    }

    public Boolean isEarlyCheckin() {
        return earlyCheckin;
    }

    public TravelAccommodationDetails earlyCheckin(Boolean earlyCheckin) {
        this.earlyCheckin = earlyCheckin;
        return this;
    }

    public void setEarlyCheckin(Boolean earlyCheckin) {
        this.earlyCheckin = earlyCheckin;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public TravelAccommodationDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public TravelAccommodationDetails createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public TravelAccommodationDetails lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public TravelAccommodationDetails lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public TravelApplicationMaster getTravelApplicationMaster() {
        return travelApplicationMaster;
    }

    public TravelAccommodationDetails travelApplicationMaster(TravelApplicationMaster travelApplicationMaster) {
        this.travelApplicationMaster = travelApplicationMaster;
        return this;
    }

    public void setTravelApplicationMaster(TravelApplicationMaster travelApplicationMaster) {
        this.travelApplicationMaster = travelApplicationMaster;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TravelAccommodationDetails)) {
            return false;
        }
        return id != null && id.equals(((TravelAccommodationDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TravelAccommodationDetails{" +
            "id=" + getId() +
            ", accommodationDate='" + getAccommodationDate() + "'" +
            ", accommodationType='" + getAccommodationType() + "'" +
            ", accommodationName='" + getAccommodationName() + "'" +
            ", accommodationTarif=" + getAccommodationTarif() +
            ", daysStay=" + getDaysStay() +
            ", earlyCheckin='" + isEarlyCheckin() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            "}";
    }
}
