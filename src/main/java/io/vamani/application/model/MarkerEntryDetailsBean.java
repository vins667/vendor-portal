package io.vamani.application.model;

import java.time.Instant;

import io.vamani.application.domain.MarkerMasterEntry;

public class MarkerEntryDetailsBean {

    private Long id;

    private Integer sequence;

    private String sizeCode;

    private Integer sizeQty;

    private Double orderQty;

    private Double plannedQty;

    private Double actualPliesQty;

    private Double pliesQty;

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

    public String getSizeCode() {
        return sizeCode;
    }

    public void setSizeCode(String sizeCode) {
        this.sizeCode = sizeCode;
    }

    public Integer getSizeQty() {
        return sizeQty;
    }

    public void setSizeQty(Integer sizeQty) {
        this.sizeQty = sizeQty;
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

    public Double getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(Double orderQty) {
        this.orderQty = orderQty;
    }

    public Double getPlannedQty() {
        return plannedQty;
    }

    public void setPlannedQty(Double plannedQty) {
        this.plannedQty = plannedQty;
    }

    public Double getPliesQty() {
        return pliesQty;
    }

    public void setPliesQty(Double pliesQty) {
        this.pliesQty = pliesQty;
    }

    public Double getActualPliesQty() {
        return actualPliesQty;
    }

    public void setActualPliesQty(Double actualPliesQty) {
        this.actualPliesQty = actualPliesQty;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}
