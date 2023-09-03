package io.vamani.application.model;

import java.time.Instant;
import java.util.List;

import io.vamani.application.domain.TdsGroupMaster;

public class TdsGroupDetailsBean {
    private Long id;
    private String perkCode;
    private String perkDescription;
    private String perkLimit;
    private String perkType;
    private String perkMode;
    private String calType;
    private String forComp;
    private Integer printOrder;
    private String remarks;
    private String createdBy;
    private String lastUpdatedBy;
    private Instant lastUpdatedDate;
    private Instant createdDate;
    private String displayFlag;
    private double amount;
    private TdsGroupMaster tdsGroupMaster;
    private List<TdsDeclarationBreakupBean> tdsDeclarationBreakupBeans;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPerkCode() {
        return perkCode;
    }

    public void setPerkCode(String perkCode) {
        this.perkCode = perkCode;
    }

    public String getPerkDescription() {
        return perkDescription;
    }

    public void setPerkDescription(String perkDescription) {
        this.perkDescription = perkDescription;
    }

    public String getPerkLimit() {
        return perkLimit;
    }

    public void setPerkLimit(String perkLimit) {
        this.perkLimit = perkLimit;
    }

    public String getPerkType() {
        return perkType;
    }

    public void setPerkType(String perkType) {
        this.perkType = perkType;
    }

    public String getPerkMode() {
        return perkMode;
    }

    public void setPerkMode(String perkMode) {
        this.perkMode = perkMode;
    }

    public String getCalType() {
        return calType;
    }

    public void setCalType(String calType) {
        this.calType = calType;
    }

    public String getForComp() {
        return forComp;
    }

    public void setForComp(String forComp) {
        this.forComp = forComp;
    }

    public Integer getPrintOrder() {
        return printOrder;
    }

    public void setPrintOrder(Integer printOrder) {
        this.printOrder = printOrder;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TdsGroupMaster getTdsGroupMaster() {
        return tdsGroupMaster;
    }

    public void setTdsGroupMaster(TdsGroupMaster tdsGroupMaster) {
        this.tdsGroupMaster = tdsGroupMaster;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDisplayFlag() {
        return displayFlag;
    }

    public void setDisplayFlag(String displayFlag) {
        this.displayFlag = displayFlag;
    }

    public List<TdsDeclarationBreakupBean> getTdsDeclarationBreakupBeans() {
        return tdsDeclarationBreakupBeans;
    }

    public void setTdsDeclarationBreakupBeans(List<TdsDeclarationBreakupBean> tdsDeclarationBreakupBeans) {
        this.tdsDeclarationBreakupBeans = tdsDeclarationBreakupBeans;
    }
}
