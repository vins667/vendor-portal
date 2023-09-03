package io.vamani.application.mobile;

import io.vamani.application.domain.TravelAccommodationDetails;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Objects;

public class TravelAccommodationDetailsMobile implements Serializable {
    private Long id;

    private Instant accommodationDate;

    private String accommodationDateFormat;

    private String accommodationType;

    private String accommodationName;

    private Double accommodationTarif;

    private Double daysStay;

    private Boolean earlyCheckin;

    private String createdBy;

    private Instant createdDate;

    private String lastUpdatedBy;

    private Instant lastUpdatedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getAccommodationDate() {
        return accommodationDate;
    }

    public void setAccommodationDate(Instant accommodationDate) {
        this.accommodationDate = accommodationDate;
    }

    public String getAccommodationDateFormat() {
        accommodationDateFormat = null;
        if (accommodationDate != null) {
            accommodationDateFormat = new SimpleDateFormat("dd-MM-yyyy").format(Date.from(accommodationDate));
        }
        return accommodationDateFormat;
    }

    public void setAccommodationDateFormat(String accommodationDateFormat) {
        this.accommodationDateFormat = accommodationDateFormat;
    }

    public String getAccommodationType() {
        return accommodationType;
    }

    public void setAccommodationType(String accommodationType) {
        this.accommodationType = accommodationType;
    }

    public String getAccommodationName() {
        return accommodationName;
    }

    public void setAccommodationName(String accommodationName) {
        this.accommodationName = accommodationName;
    }

    public Double getAccommodationTarif() {
        return accommodationTarif;
    }

    public void setAccommodationTarif(Double accommodationTarif) {
        this.accommodationTarif = accommodationTarif;
    }

    public Double getDaysStay() {
        return daysStay;
    }

    public void setDaysStay(Double daysStay) {
        this.daysStay = daysStay;
    }

    public Boolean getEarlyCheckin() {
        return earlyCheckin;
    }

    public void setEarlyCheckin(Boolean earlyCheckin) {
        this.earlyCheckin = earlyCheckin;
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

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public TravelAccommodationDetailsMobile() {
    }

    public TravelAccommodationDetailsMobile(TravelAccommodationDetails travelAccommodationDetails) {
        BeanUtils.copyProperties(travelAccommodationDetails, this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TravelAccommodationDetailsMobile that = (TravelAccommodationDetailsMobile) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(accommodationDate, that.accommodationDate) &&
            Objects.equals(accommodationDateFormat, that.accommodationDateFormat) &&
            Objects.equals(accommodationType, that.accommodationType) &&
            Objects.equals(accommodationName, that.accommodationName) &&
            Objects.equals(accommodationTarif, that.accommodationTarif) &&
            Objects.equals(daysStay, that.daysStay) &&
            Objects.equals(earlyCheckin, that.earlyCheckin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accommodationDate, accommodationDateFormat, accommodationType, accommodationName, accommodationTarif, daysStay, earlyCheckin);
    }
}
