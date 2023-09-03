package io.vamani.application.mobile;

import io.vamani.application.domain.TravelFlightDetails;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Objects;

public class TravelFlightDetailsMobile implements Serializable {
    private Long id;

    private Instant travelDate;

    private String travelDateFormat;

    private String ticketType;

    private String ticketNo;

    private Instant departureDate;

    private Instant arrivalDate;

    private String departureDateFormat;

    private String arrivalDateFormat;

    private Double farePrice;

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

    public Instant getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(Instant travelDate) {
        this.travelDate = travelDate;
    }

    public String getTravelDateFormat() {
        travelDateFormat = null;
        if (travelDate != null) {
            travelDateFormat = new SimpleDateFormat("dd-MM-yyyy").format(Date.from(travelDate));
        }
        return travelDateFormat;
    }

    public void setTravelDateFormat(String travelDateFormat) {
        this.travelDateFormat = travelDateFormat;
    }

    public String getDepartureDateFormat() {
        departureDateFormat = null;
        if (departureDate != null) {
            departureDateFormat = new SimpleDateFormat("dd-MM-yyyy").format(Date.from(departureDate));
        }
        return departureDateFormat;
    }

    public void setDepartureDateFormat(String departureDateFormat) {
        this.departureDateFormat = departureDateFormat;
    }

    public String getArrivalDateFormat() {
        arrivalDateFormat = null;
        if (arrivalDate != null) {
            arrivalDateFormat = new SimpleDateFormat("dd-MM-yyyy").format(Date.from(arrivalDate));
        }
        return arrivalDateFormat;
    }

    public void setArrivalDateFormat(String arrivalDateFormat) {
        this.arrivalDateFormat = arrivalDateFormat;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public Instant getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Instant departureDate) {
        this.departureDate = departureDate;
    }

    public Instant getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Instant arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Double getFarePrice() {
        return farePrice;
    }

    public void setFarePrice(Double farePrice) {
        this.farePrice = farePrice;
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

    public TravelFlightDetailsMobile() {
    }

    public TravelFlightDetailsMobile(TravelFlightDetails flightDetails) {
        BeanUtils.copyProperties(flightDetails, this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TravelFlightDetailsMobile that = (TravelFlightDetailsMobile) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(travelDate, that.travelDate) &&
            Objects.equals(travelDateFormat, that.travelDateFormat) &&
            Objects.equals(ticketType, that.ticketType) &&
            Objects.equals(ticketNo, that.ticketNo) &&
            Objects.equals(departureDate, that.departureDate) &&
            Objects.equals(arrivalDate, that.arrivalDate) &&
            Objects.equals(farePrice, that.farePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, travelDate, travelDateFormat, ticketType, ticketNo, departureDate, arrivalDate, farePrice);
    }
}
