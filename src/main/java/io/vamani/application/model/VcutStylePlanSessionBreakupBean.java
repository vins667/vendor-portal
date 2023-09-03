package io.vamani.application.model;

import io.vamani.application.domain.VcutStylePlanSessionBreakup;
import io.vamani.application.domain.VcutStylePlanSessionBreakupId;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class VcutStylePlanSessionBreakupBean implements Serializable {
    private Long sessionId;

    private String startTime;

    private Long vcutStylePlanUploadId;

    private String endTime;

    private String type;

    private Integer duration;

    private Integer order;

    private Integer cumulativeMins;

    private Integer planQuantity;

    private String createdBy;

    private Instant createdDate;

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Long getVcutStylePlanUploadId() {
        return vcutStylePlanUploadId;
    }

    public void setVcutStylePlanUploadId(Long vcutStylePlanUploadId) {
        this.vcutStylePlanUploadId = vcutStylePlanUploadId;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getCumulativeMins() {
        return cumulativeMins;
    }

    public void setCumulativeMins(Integer cumulativeMins) {
        this.cumulativeMins = cumulativeMins;
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

    public Integer getPlanQuantity() {
        return planQuantity;
    }

    public void setPlanQuantity(Integer planQuantity) {
        this.planQuantity = planQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VcutStylePlanSessionBreakupBean that = (VcutStylePlanSessionBreakupBean) o;
        return Objects.equals(sessionId, that.sessionId) &&
            Objects.equals(startTime, that.startTime) &&
            Objects.equals(vcutStylePlanUploadId, that.vcutStylePlanUploadId) &&
            Objects.equals(endTime, that.endTime) &&
            Objects.equals(type, that.type) &&
            Objects.equals(duration, that.duration) &&
            Objects.equals(order, that.order) &&
            Objects.equals(cumulativeMins, that.cumulativeMins) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(createdDate, that.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, startTime, vcutStylePlanUploadId, endTime, type, duration, order, cumulativeMins, createdBy, createdDate);
    }

    @Override
    public String toString() {
        return "VcutStylePlanSessionBreakupBean{" +
            "sessionId=" + sessionId +
            ", startTime='" + startTime + '\'' +
            ", vcutStylePlanUploadId=" + vcutStylePlanUploadId +
            ", endTime='" + endTime + '\'' +
            ", type='" + type + '\'' +
            ", duration=" + duration +
            ", order=" + order +
            ", cumulativeMins=" + cumulativeMins +
            ", createdBy='" + createdBy + '\'' +
            ", createdDate=" + createdDate +
            '}';
    }
}
