package io.vamani.application.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class VcutMainEntryMasterBean implements Serializable {
    private Long id;

    private String entryType;

    private String entryTime;

    private String entryBy;

    private String rectifiedBy;

    private String rectifiedDate;

    private Long planId;

    private Long operationId;

    private String operationDescription;

    private String operationDescriptionLocal;

    private String status;

    private String rectifyStatus;

    private String stickerNumber;

    private Long cutPlanBundleDetailsId;

    private Long cutPlanBundleId;

    private String finalOperation;

    private List<VcutMainEntryIssueDetails> issueDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntryType() {
        return entryType;
    }

    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getEntryBy() {
        return entryBy;
    }

    public void setEntryBy(String entryBy) {
        this.entryBy = entryBy;
    }

    public String getRectifiedBy() {
        return rectifiedBy;
    }

    public void setRectifiedBy(String rectifiedBy) {
        this.rectifiedBy = rectifiedBy;
    }

    public String getRectifiedDate() {
        return rectifiedDate;
    }

    public void setRectifiedDate(String rectifiedDate) {
        this.rectifiedDate = rectifiedDate;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public Long getOperationId() {
        return operationId;
    }

    public void setOperationId(Long operationId) {
        this.operationId = operationId;
    }

    public String getOperationDescription() {
        return operationDescription;
    }

    public void setOperationDescription(String operationDescription) {
        this.operationDescription = operationDescription;
    }

    public String getOperationDescriptionLocal() {
        return operationDescriptionLocal;
    }

    public void setOperationDescriptionLocal(String operationDescriptionLocal) {
        this.operationDescriptionLocal = operationDescriptionLocal;
    }

    public List<VcutMainEntryIssueDetails> getIssueDetails() {
        return issueDetails;
    }

    public void setIssueDetails(List<VcutMainEntryIssueDetails> issueDetails) {
        this.issueDetails = issueDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRectifyStatus() {
        return rectifyStatus;
    }

    public void setRectifyStatus(String rectifyStatus) {
        this.rectifyStatus = rectifyStatus;
    }

    public String getStickerNumber() {
        return stickerNumber;
    }

    public void setStickerNumber(String stickerNumber) {
        this.stickerNumber = stickerNumber;
    }

    public Long getCutPlanBundleDetailsId() {
        return cutPlanBundleDetailsId;
    }

    public void setCutPlanBundleDetailsId(Long cutPlanBundleDetailsId) {
        this.cutPlanBundleDetailsId = cutPlanBundleDetailsId;
    }

    public Long getCutPlanBundleId() {
        return cutPlanBundleId;
    }

    public String getFinalOperation() {
        return finalOperation;
    }

    public void setFinalOperation(String finalOperation) {
        this.finalOperation = finalOperation;
    }

    public void setCutPlanBundleId(Long cutPlanBundleId) {
        this.cutPlanBundleId = cutPlanBundleId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VcutMainEntryMasterBean that = (VcutMainEntryMasterBean) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(entryType, that.entryType) &&
            Objects.equals(entryTime, that.entryTime) &&
            Objects.equals(entryBy, that.entryBy) &&
            Objects.equals(rectifiedBy, that.rectifiedBy) &&
            Objects.equals(rectifiedDate, that.rectifiedDate) &&
            Objects.equals(planId, that.planId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, entryType, entryTime, entryBy, rectifiedBy, rectifiedDate, planId);
    }

    @Override
    public String toString() {
        return "VcutMainEntryMasterBean{" +
            "id=" + id +
            ", entryType='" + entryType + '\'' +
            ", entryTime='" + entryTime + '\'' +
            ", entryBy='" + entryBy + '\'' +
            ", rectifiedBy='" + rectifiedBy + '\'' +
            ", rectifiedDate='" + rectifiedDate + '\'' +
            ", planId=" + planId +
            '}';
    }
}
