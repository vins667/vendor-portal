package io.vamani.application.mobile;

import io.vamani.application.domain.TravelLuggageDetails;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.validation.constraints.Size;
import java.time.Instant;

public class TravelLuggageDetailsMobile {

    private Long id;

    private Double luggageCount;

    private String luggageType;

    private Double approxWeight;

    private Boolean extraLuggageReq;

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

    public Double getLuggageCount() {
        return luggageCount;
    }

    public void setLuggageCount(Double luggageCount) {
        this.luggageCount = luggageCount;
    }

    public String getLuggageType() {
        return luggageType;
    }

    public void setLuggageType(String luggageType) {
        this.luggageType = luggageType;
    }

    public Double getApproxWeight() {
        return approxWeight;
    }

    public void setApproxWeight(Double approxWeight) {
        this.approxWeight = approxWeight;
    }

    public Boolean getExtraLuggageReq() {
        return extraLuggageReq;
    }

    public void setExtraLuggageReq(Boolean extraLuggageReq) {
        this.extraLuggageReq = extraLuggageReq;
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

    public TravelLuggageDetailsMobile() {
    }

    public TravelLuggageDetailsMobile(TravelLuggageDetails travelLuggageDetails) {
        BeanUtils.copyProperties(travelLuggageDetails, this);
    }
}
