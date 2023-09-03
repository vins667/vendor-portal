package io.vamani.application.model;

import java.time.Instant;
import java.util.List;

import io.vamani.application.domain.TdsGroupDetails;

public class TdsGroupMasterBean {
    private Long id;
    private Integer year;
    private String groupCode;
    private String groupDescription;
    private Double groupLimit;
    private Integer groupOrder;
    private String createdBy;
    private Instant createdDate;
    private String lastUpdatedBy;
    private Instant lastUpdatedDate;
    private Double exemptAmount;
    private Double totalValue;
    List<PreviousEmploymentDetailBean> previousEmploymentDetailBeans;
    private List<TdsGroupDetailsBean> tdsGroupDetailsBean;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public Double getGroupLimit() {
        return groupLimit;
    }

    public void setGroupLimit(Double groupLimit) {
        this.groupLimit = groupLimit;
    }

    public Integer getGroupOrder() {
        return groupOrder;
    }

    public void setGroupOrder(Integer groupOrder) {
        this.groupOrder = groupOrder;
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

    public Double getExemptAmount() {
        return exemptAmount;
    }

    public void setExemptAmount(Double exemptAmount) {
        this.exemptAmount = exemptAmount;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public List<TdsGroupDetailsBean> getTdsGroupDetailsBean() {
        return tdsGroupDetailsBean;
    }

    public void setTdsGroupDetailsBean(List<TdsGroupDetailsBean> tdsGroupDetailsBean) {
        this.tdsGroupDetailsBean = tdsGroupDetailsBean;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public List<PreviousEmploymentDetailBean> getPreviousEmploymentDetailBeans() {
        return previousEmploymentDetailBeans;
    }

    public void setPreviousEmploymentDetailBeans(List<PreviousEmploymentDetailBean> previousEmploymentDetailBeans) {
        this.previousEmploymentDetailBeans = previousEmploymentDetailBeans;
    }
}
