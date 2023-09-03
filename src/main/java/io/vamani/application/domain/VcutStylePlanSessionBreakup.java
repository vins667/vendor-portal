package io.vamani.application.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "vcut_style_plan_session_breakup")
public class VcutStylePlanSessionBreakup implements Serializable {
    @EmbeddedId
    private VcutStylePlanSessionBreakupId id;

    @Size(max = 10)
    @Column(name = "end_time", length = 10)
    private String endTime;

    @Size(max = 20)
    @Column(name = "jhi_type", length = 20)
    private String type;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "jhi_order")
    private Integer order;

    @Column(name = "cumulative_mins")
    private Integer cumulativeMins;

    @Column(name = "plan_quantity")
    private Integer planQuantity;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    public VcutStylePlanSessionBreakupId getId() {
        return id;
    }

    public void setId(VcutStylePlanSessionBreakupId id) {
        this.id = id;
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
    public String toString() {
        return "VcutStylePlanSessionBreakup{" +
            "id=" + id +
            ", endTime='" + endTime + '\'' +
            ", type='" + type + '\'' +
            ", duration=" + duration +
            ", order=" + order +
            ", cumulativeMins=" + cumulativeMins +
            ", createdBy='" + createdBy + '\'' +
            ", createdDate=" + createdDate +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VcutStylePlanSessionBreakup that = (VcutStylePlanSessionBreakup) o;
        return Objects.equals(id, that.id) &&
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
        return Objects.hash(id, endTime, type, duration, order, cumulativeMins, createdBy, createdDate);
    }
}
