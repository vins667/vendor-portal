package io.vamani.application.mobile;

import io.vamani.application.domain.TravelForexDetails;
import io.vamani.application.domain.TravelLuggageDetails;
import io.vamani.application.domain.TravelPassengerDetails;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

public class TravelApplicationMasterMobileBean implements Serializable {
    private Long id;

    private String empCode;

    private String travelDestination;

    private Double travelDays;

    private String travelPurpose;

    private String createdBy;

    private String hodCode;

    private String hodApprovedBy;

    private String hrApprovedBy;

    private Instant createdDate;

    private Instant travelFromdate;

    private Instant travelTodate;

    private String travelFromdateFormat;

    private String travelTodateFormat;

    private Instant hodApprovedDate;

    private Instant hrApprovedDate;

    private String lastUpdatedBy;

    private Instant lastUpdatedDate;

    private String status;

    private List<TravelAccommodationDetailsMobile> travelAccommodationDetails;
    private List<TravelFlightDetailsMobile> travelFlightDetails;
    private List<TravelForexDetailsMobile> travelForexDetails;
    private List<TravelLuggageDetailsMobile> travelLuggageDetails;
    private List<TravelPassengerDetailsMobile> travelPassengerDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getTravelDestination() {
        return travelDestination;
    }

    public void setTravelDestination(String travelDestination) {
        this.travelDestination = travelDestination;
    }

    public Double getTravelDays() {
        return travelDays;
    }

    public void setTravelDays(Double travelDays) {
        this.travelDays = travelDays;
    }

    public String getTravelPurpose() {
        return travelPurpose;
    }

    public void setTravelPurpose(String travelPurpose) {
        this.travelPurpose = travelPurpose;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getHodCode() {
        return hodCode;
    }

    public void setHodCode(String hodCode) {
        this.hodCode = hodCode;
    }

    public String getHodApprovedBy() {
        return hodApprovedBy;
    }

    public void setHodApprovedBy(String hodApprovedBy) {
        this.hodApprovedBy = hodApprovedBy;
    }

    public String getHrApprovedBy() {
        return hrApprovedBy;
    }

    public void setHrApprovedBy(String hrApprovedBy) {
        this.hrApprovedBy = hrApprovedBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getTravelFromdate() {
        return travelFromdate;
    }

    public void setTravelFromdate(Instant travelFromdate) {
        this.travelFromdate = travelFromdate;
    }

    public Instant getTravelTodate() {
        return travelTodate;
    }

    public void setTravelTodate(Instant travelTodate) {
        this.travelTodate = travelTodate;
    }

    public Instant getHodApprovedDate() {
        return hodApprovedDate;
    }

    public void setHodApprovedDate(Instant hodApprovedDate) {
        this.hodApprovedDate = hodApprovedDate;
    }

    public Instant getHrApprovedDate() {
        return hrApprovedDate;
    }

    public void setHrApprovedDate(Instant hrApprovedDate) {
        this.hrApprovedDate = hrApprovedDate;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTravelFromdateFormat() {
        return travelFromdateFormat;
    }

    public void setTravelFromdateFormat(String travelFromdateFormat) {
        this.travelFromdateFormat = travelFromdateFormat;
    }

    public String getTravelTodateFormat() {
        return travelTodateFormat;
    }

    public void setTravelTodateFormat(String travelTodateFormat) {
        this.travelTodateFormat = travelTodateFormat;
    }

    public List<TravelAccommodationDetailsMobile> getTravelAccommodationDetails() {
        return travelAccommodationDetails;
    }

    public void setTravelAccommodationDetails(List<TravelAccommodationDetailsMobile> travelAccommodationDetails) {
        this.travelAccommodationDetails = travelAccommodationDetails;
    }

    public List<TravelFlightDetailsMobile> getTravelFlightDetails() {
        return travelFlightDetails;
    }

    public void setTravelFlightDetails(List<TravelFlightDetailsMobile> travelFlightDetails) {
        this.travelFlightDetails = travelFlightDetails;
    }

    public List<TravelForexDetailsMobile> getTravelForexDetails() {
        return travelForexDetails;
    }

    public void setTravelForexDetails(List<TravelForexDetailsMobile> travelForexDetails) {
        this.travelForexDetails = travelForexDetails;
    }

    public List<TravelLuggageDetailsMobile> getTravelLuggageDetails() {
        return travelLuggageDetails;
    }

    public void setTravelLuggageDetails(List<TravelLuggageDetailsMobile> travelLuggageDetails) {
        this.travelLuggageDetails = travelLuggageDetails;
    }

    public List<TravelPassengerDetailsMobile> getTravelPassengerDetails() {
        return travelPassengerDetails;
    }

    public void setTravelPassengerDetails(List<TravelPassengerDetailsMobile> travelPassengerDetails) {
        this.travelPassengerDetails = travelPassengerDetails;
    }
}
