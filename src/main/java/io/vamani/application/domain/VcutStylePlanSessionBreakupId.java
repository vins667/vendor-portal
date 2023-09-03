package io.vamani.application.domain;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class VcutStylePlanSessionBreakupId implements Serializable {
    @Column(name = "start_time")
    private String startTime;

    @Column(name = "vcut_style_plan_upload_id")
    private Long vcutStylePlanUploadId;

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

    @Override
    public String toString() {
        return "VcutStylePlanSessionBreakupId{" +
            "startTime='" + startTime + '\'' +
            ", vcutStylePlanUploadId=" + vcutStylePlanUploadId +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VcutStylePlanSessionBreakupId that = (VcutStylePlanSessionBreakupId) o;
        return Objects.equals(startTime, that.startTime) &&
            Objects.equals(vcutStylePlanUploadId, that.vcutStylePlanUploadId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, vcutStylePlanUploadId);
    }
}
