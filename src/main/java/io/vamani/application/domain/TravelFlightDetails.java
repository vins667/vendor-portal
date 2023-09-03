package io.vamani.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A TravelFlightDetails.
 */
@Entity
@Table(name = "travel_flight_details")
public class TravelFlightDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="travelFlightDetailsSeq", sequenceName="travel_flight_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="travelFlightDetailsSeq")
    private Long id;

    @Column(name = "travel_date")
    private Instant travelDate;

    @Size(max = 20)
    @Column(name = "ticket_type", length = 20)
    private String ticketType;

    @Column(name = "ticket_no")
    private String ticketNo;

    @Column(name = "departure_date")
    private Instant departureDate;

    @Column(name = "arrival_date")
    private Instant arrivalDate;

    @Column(name = "fare_price")
    private Double farePrice;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Size(max = 20)
    @Column(name = "last_updated_by", length = 20)
    private String lastUpdatedBy;

    @Column(name = "last_updated_date")
    private Instant lastUpdatedDate;

    @ManyToOne
    @JsonIgnoreProperties("")
    @JoinColumn(name = "travel_application_master_id")
    private TravelApplicationMaster travelApplicationMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getTravelDate() {
        return travelDate;
    }

    public TravelFlightDetails travelDate(Instant travelDate) {
        this.travelDate = travelDate;
        return this;
    }

    public void setTravelDate(Instant travelDate) {
        this.travelDate = travelDate;
    }

    public String getTicketType() {
        return ticketType;
    }

    public TravelFlightDetails ticketType(String ticketType) {
        this.ticketType = ticketType;
        return this;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public TravelFlightDetails ticketNo(String ticketNo) {
        this.ticketNo = ticketNo != null ? ticketNo.trim().toUpperCase() : "";
        return this;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo != null ? ticketNo.trim().toUpperCase() : "";
    }

    public Instant getDepartureDate() {
        return departureDate;
    }

    public TravelFlightDetails departureDate(Instant departureDate) {
        this.departureDate = departureDate;
        return this;
    }

    public void setDepartureDate(Instant departureDate) {
        this.departureDate = departureDate;
    }

    public Instant getArrivalDate() {
        return arrivalDate;
    }

    public TravelFlightDetails arrivalDate(Instant arrivalDate) {
        this.arrivalDate = arrivalDate;
        return this;
    }

    public void setArrivalDate(Instant arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Double getFarePrice() {
        return farePrice;
    }

    public TravelFlightDetails farePrice(Double farePrice) {
        this.farePrice = farePrice;
        return this;
    }

    public void setFarePrice(Double farePrice) {
        this.farePrice = farePrice;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public TravelFlightDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public TravelFlightDetails createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public TravelFlightDetails lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public TravelFlightDetails lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public TravelApplicationMaster getTravelApplicationMaster() {
        return travelApplicationMaster;
    }

    public TravelFlightDetails travelApplicationMaster(TravelApplicationMaster travelApplicationMaster) {
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
        if (!(o instanceof TravelFlightDetails)) {
            return false;
        }
        return id != null && id.equals(((TravelFlightDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TravelFlightDetails{" +
            "id=" + getId() +
            ", travelDate='" + getTravelDate() + "'" +
            ", ticketType='" + getTicketType() + "'" +
            ", ticketNo='" + getTicketNo() + "'" +
            ", departureDate='" + getDepartureDate() + "'" +
            ", arrivalDate='" + getArrivalDate() + "'" +
            ", farePrice=" + getFarePrice() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            "}";
    }
}
