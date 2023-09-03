package io.vamani.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A TravelPassengerDetails.
 */
@Entity
@Table(name = "travel_passenger_details")
public class TravelPassengerDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="travelPassengerDetailsSeq", sequenceName="travel_passenger_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="travelPassengerDetailsSeq")
    private Long id;

    @Size(max = 50)
    @Column(name = "passenger_name", length = 50)
    private String passengerName;

    @Size(max = 50)
    @Column(name = "phone_no", length = 50)
    private String phoneNo;

    @Size(max = 50)
    @Column(name = "email_id", length = 50)
    private String emailId;

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

    public String getPassengerName() {
        return passengerName;
    }

    public TravelPassengerDetails passengerName(String passengerName) {
        this.passengerName = passengerName != null ? passengerName.trim().toUpperCase() : "";
        return this;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName != null ? passengerName.trim().toUpperCase() : "";
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public TravelPassengerDetails phoneNo(String phoneNo) {
        this.phoneNo = phoneNo != null ? phoneNo.trim().toUpperCase() : "";
        return this;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo != null ? phoneNo.trim().toUpperCase() : "";
    }

    public String getEmailId() {
        return emailId;
    }

    public TravelPassengerDetails emailId(String emailId) {
        this.emailId = emailId != null ? emailId.trim().toLowerCase() : "";
        return this;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId != null ? emailId.trim().toLowerCase() : "";
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public TravelPassengerDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public TravelPassengerDetails createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public TravelPassengerDetails lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public TravelPassengerDetails lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public TravelApplicationMaster getTravelApplicationMaster() {
        return travelApplicationMaster;
    }

    public TravelPassengerDetails travelApplicationMaster(TravelApplicationMaster travelApplicationMaster) {
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
        if (!(o instanceof TravelPassengerDetails)) {
            return false;
        }
        return id != null && id.equals(((TravelPassengerDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TravelPassengerDetails{" +
            "id=" + getId() +
            ", passengerName='" + getPassengerName() + "'" +
            ", phoneNo=" + getPhoneNo() +
            ", emailId='" + getEmailId() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            "}";
    }
}
