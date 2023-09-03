package io.vamani.application.model;

import io.vamani.application.domain.VcutStylePlanSessionBreakupId;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class VcutStylePlanSessionBreakupBeanNew implements Serializable {

    private VcutStylePlanSessionBreakupId id;

    private Date startDate;

    private String endTime;

    private String type;

    private Integer duration;

    private Integer order;

    private Integer cumulativeMins;

    private Integer planQuantity;

    public VcutStylePlanSessionBreakupId getId() {
        return id;
    }

    public void setId(VcutStylePlanSessionBreakupId id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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

    public Integer getPlanQuantity() {
        return planQuantity;
    }

    public void setPlanQuantity(Integer planQuantity) {
        this.planQuantity = planQuantity;
    }

    @Override
    public String toString() {
        return "VcutStylePlanSessionBreakupBeanNew{" +
            "id=" + id +
            ", startDate=" + startDate +
            ", endTime='" + endTime + '\'' +
            ", type='" + type + '\'' +
            ", duration=" + duration +
            ", order=" + order +
            ", cumulativeMins=" + cumulativeMins +
            ", planQuantity=" + planQuantity +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VcutStylePlanSessionBreakupBeanNew that = (VcutStylePlanSessionBreakupBeanNew) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(startDate, that.startDate) &&
            Objects.equals(endTime, that.endTime) &&
            Objects.equals(type, that.type) &&
            Objects.equals(duration, that.duration) &&
            Objects.equals(order, that.order) &&
            Objects.equals(cumulativeMins, that.cumulativeMins) &&
            Objects.equals(planQuantity, that.planQuantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endTime, type, duration, order, cumulativeMins, planQuantity);
    }
}
