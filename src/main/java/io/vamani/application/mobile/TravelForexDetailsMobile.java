package io.vamani.application.mobile;

import io.vamani.application.domain.TravelForexDetails;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class TravelForexDetailsMobile implements Serializable {
    private Long id;

    private String forexType;

    private Double requiredAmount;

    private Double approvedAmount;

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

    public String getForexType() {
        return forexType;
    }

    public void setForexType(String forexType) {
        this.forexType = forexType;
    }

    public Double getRequiredAmount() {
        return requiredAmount;
    }

    public void setRequiredAmount(Double requiredAmount) {
        this.requiredAmount = requiredAmount;
    }

    public Double getApprovedAmount() {
        return approvedAmount;
    }

    public void setApprovedAmount(Double approvedAmount) {
        this.approvedAmount = approvedAmount;
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

    public TravelForexDetailsMobile() {
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

    public TravelForexDetailsMobile(TravelForexDetails travelForexDetails) {
        BeanUtils.copyProperties(travelForexDetails, this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TravelForexDetailsMobile that = (TravelForexDetailsMobile) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(forexType, that.forexType) &&
            Objects.equals(requiredAmount, that.requiredAmount) &&
            Objects.equals(approvedAmount, that.approvedAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, forexType, requiredAmount, approvedAmount);
    }
}
