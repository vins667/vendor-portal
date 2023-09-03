package io.vamani.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A TravelLuggageDetails.
 */
@Entity
@Table(name = "travel_luggage_details")
public class TravelLuggageDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="travelLuggageDetailsSeq", sequenceName="travel_luggage_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="travelLuggageDetailsSeq")
    private Long id;

    @Column(name = "luggage_count")
    private Double luggageCount;

    @Size(max = 20)
    @Column(name = "luggage_type", length = 20)
    private String luggageType;

    @Column(name = "approx_weight")
    private Double approxWeight;

    @Column(name = "extra_luggage_req")
    private Boolean extraLuggageReq;

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

    public Double getLuggageCount() {
        return luggageCount;
    }

    public TravelLuggageDetails luggageCount(Double luggageCount) {
        this.luggageCount = luggageCount;
        return this;
    }

    public void setLuggageCount(Double luggageCount) {
        this.luggageCount = luggageCount;
    }

    public String getLuggageType() {
        return luggageType;
    }

    public TravelLuggageDetails luggageType(String luggageType) {
        this.luggageType = luggageType;
        return this;
    }

    public void setLuggageType(String luggageType) {
        this.luggageType = luggageType;
    }

    public Double getApproxWeight() {
        return approxWeight;
    }

    public TravelLuggageDetails approxWeight(Double approxWeight) {
        this.approxWeight = approxWeight;
        return this;
    }

    public void setApproxWeight(Double approxWeight) {
        this.approxWeight = approxWeight;
    }

    public Boolean isExtraLuggageReq() {
        return extraLuggageReq;
    }

    public TravelLuggageDetails extraLuggageReq(Boolean extraLuggageReq) {
        this.extraLuggageReq = extraLuggageReq;
        return this;
    }

    public void setExtraLuggageReq(Boolean extraLuggageReq) {
        this.extraLuggageReq = extraLuggageReq;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public TravelLuggageDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public TravelLuggageDetails createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public TravelLuggageDetails lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public TravelLuggageDetails lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public TravelApplicationMaster getTravelApplicationMaster() {
        return travelApplicationMaster;
    }

    public TravelLuggageDetails travelApplicationMaster(TravelApplicationMaster travelApplicationMaster) {
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
        if (!(o instanceof TravelLuggageDetails)) {
            return false;
        }
        return id != null && id.equals(((TravelLuggageDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TravelLuggageDetails{" +
            "id=" + getId() +
            ", luggageCount=" + getLuggageCount() +
            ", luggageType='" + getLuggageType() + "'" +
            ", approxWeight=" + getApproxWeight() +
            ", extraLuggageReq='" + isExtraLuggageReq() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            "}";
    }
}
