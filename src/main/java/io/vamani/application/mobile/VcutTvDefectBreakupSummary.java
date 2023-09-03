package io.vamani.application.mobile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class VcutTvDefectBreakupSummary implements Serializable {
    private Long id;
    private String startTime;
    private String endTime;
    private String description;
    private String descriptionLocal;
    private String hours;
    private Long countDefect;
    private Long countDefectCum;
    private String style;
    private String activeHour;

    @JsonIgnore
    private Date startDate;

    @JsonIgnore
    private Long srNo;

    @JsonIgnore
    private String sessionSpl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionLocal() {
        return descriptionLocal;
    }

    public void setDescriptionLocal(String descriptionLocal) {
        this.descriptionLocal = descriptionLocal;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public Long getCountDefect() {
        return countDefect;
    }

    public void setCountDefect(Long countDefect) {
        this.countDefect = countDefect;
    }

    public Long getCountDefectCum() {
        return countDefectCum;
    }

    public void setCountDefectCum(Long countDefectCum) {
        this.countDefectCum = countDefectCum;
    }

    public String getStyle() {
        return style;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Long getSrNo() {
        return srNo;
    }

    public void setSrNo(Long srNo) {
        this.srNo = srNo;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getSessionSpl() {
        return sessionSpl;
    }

    public void setSessionSpl(String sessionSpl) {
        this.sessionSpl = sessionSpl;
    }

    public String getActiveHour() {
        return activeHour;
    }

    public void setActiveHour(String activeHour) {
        this.activeHour = activeHour;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VcutTvDefectBreakupSummary that = (VcutTvDefectBreakupSummary) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(description, that.description) &&
            Objects.equals(descriptionLocal, that.descriptionLocal) &&
            Objects.equals(hours, that.hours) &&
            Objects.equals(countDefect, that.countDefect) &&
            Objects.equals(countDefectCum, that.countDefectCum) &&
            Objects.equals(style, that.style);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, descriptionLocal, hours, countDefect, countDefectCum, style);
    }

    @Override
    public String toString() {
        return "VcutTvDefectBreakupSummary{" +
            "id=" + id +
            ", description='" + description + '\'' +
            ", descriptionLocal='" + descriptionLocal + '\'' +
            ", hours='" + hours + '\'' +
            ", countDefect=" + countDefect +
            ", countDefectCum=" + countDefectCum +
            ", style='" + style + '\'' +
            '}';
    }
}
