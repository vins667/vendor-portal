package io.vamani.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A TravelForexDetails.
 */
@Entity
@Table(name = "travel_forex_details")
public class TravelForexDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="travelForexDetailsSeq", sequenceName="travel_forex_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="travelForexDetailsSeq")
    private Long id;

    @Size(max = 15)
    @Column(name = "forex_type", length = 15)
    private String forexType;

    @Column(name = "required_amount")
    private Double requiredAmount;

    @Column(name = "approved_amount")
    private Double approvedAmount;

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

    public String getForexType() {
        return forexType;
    }

    public TravelForexDetails forexType(String forexType) {
        this.forexType = forexType;
        return this;
    }

    public void setForexType(String forexType) {
        this.forexType = forexType;
    }

    public Double getRequiredAmount() {
        return requiredAmount;
    }

    public TravelForexDetails requiredAmount(Double requiredAmount) {
        this.requiredAmount = requiredAmount;
        return this;
    }

    public void setRequiredAmount(Double requiredAmount) {
        this.requiredAmount = requiredAmount;
    }

    public Double getApprovedAmount() {
        return approvedAmount;
    }

    public TravelForexDetails approvedAmount(Double approvedAmount) {
        this.approvedAmount = approvedAmount;
        return this;
    }

    public void setApprovedAmount(Double approvedAmount) {
        this.approvedAmount = approvedAmount;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public TravelForexDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public TravelForexDetails createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public TravelForexDetails lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public TravelForexDetails lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public TravelApplicationMaster getTravelApplicationMaster() {
        return travelApplicationMaster;
    }

    public TravelForexDetails travelApplicationMaster(TravelApplicationMaster travelApplicationMaster) {
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
        if (!(o instanceof TravelForexDetails)) {
            return false;
        }
        return id != null && id.equals(((TravelForexDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TravelForexDetails{" +
            "id=" + getId() +
            ", forexType='" + getForexType() + "'" +
            ", requiredAmount=" + getRequiredAmount() +
            ", approvedAmount=" + getApprovedAmount() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            "}";
    }
}
