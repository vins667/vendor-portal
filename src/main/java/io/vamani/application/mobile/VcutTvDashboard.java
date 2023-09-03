package io.vamani.application.mobile;

import java.io.Serializable;
import java.util.Objects;

public class VcutTvDashboard implements Serializable {
    private String currentDateTime;
    private String day;
    private String finishDays;
    private String finishHours;
    private String finishMinutes;
    private String buyerName;
    private String style;
    private String color;
    private String poNo;
    private Long dayTarget;
    private Long operators;
    private Long helpers;
    private Long cumulativeTarget;
    private Long actualTarget;
    private Long varianceTarget;
    private Double ftpPercentage;
    private Long rejected;
    private Double plannedEfficiency;
    private Double actualEfficiency;
    private Double rectifiedPercentage;

    public String getCurrentDateTime() {
        return currentDateTime;
    }

    public void setCurrentDateTime(String currentDateTime) {
        this.currentDateTime = currentDateTime;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getFinishDays() {
        return finishDays;
    }

    public void setFinishDays(String finishDays) {
        this.finishDays = finishDays;
    }

    public String getFinishHours() {
        return finishHours;
    }

    public void setFinishHours(String finishHours) {
        this.finishHours = finishHours;
    }

    public String getFinishMinutes() {
        return finishMinutes;
    }

    public void setFinishMinutes(String finishMinutes) {
        this.finishMinutes = finishMinutes;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPoNo() {
        return poNo;
    }

    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }

    public Long getDayTarget() {
        return dayTarget;
    }

    public void setDayTarget(Long dayTarget) {
        this.dayTarget = dayTarget;
    }

    public Long getOperators() {
        return operators;
    }

    public void setOperators(Long operators) {
        this.operators = operators;
    }

    public Long getHelpers() {
        return helpers;
    }

    public void setHelpers(Long helpers) {
        this.helpers = helpers;
    }

    public Long getCumulativeTarget() {
        return cumulativeTarget;
    }

    public void setCumulativeTarget(Long cumulativeTarget) {
        this.cumulativeTarget = cumulativeTarget;
    }

    public Long getActualTarget() {
        return actualTarget;
    }

    public void setActualTarget(Long actualTarget) {
        this.actualTarget = actualTarget;
    }

    public Long getVarianceTarget() {
        return varianceTarget;
    }

    public void setVarianceTarget(Long varianceTarget) {
        this.varianceTarget = varianceTarget;
    }

    public Double getFtpPercentage() {
        return ftpPercentage;
    }

    public void setFtpPercentage(Double ftpPercentage) {
        this.ftpPercentage = ftpPercentage;
    }

    public Long getRejected() {
        return rejected;
    }

    public void setRejected(Long rejected) {
        this.rejected = rejected;
    }

    public Double getPlannedEfficiency() {
        return plannedEfficiency;
    }

    public void setPlannedEfficiency(Double plannedEfficiency) {
        this.plannedEfficiency = plannedEfficiency;
    }

    public Double getActualEfficiency() {
        return actualEfficiency;
    }

    public void setActualEfficiency(Double actualEfficiency) {
        this.actualEfficiency = actualEfficiency;
    }

    public Double getRectifiedPercentage() {
        return rectifiedPercentage;
    }

    public void setRectifiedPercentage(Double rectifiedPercentage) {
        this.rectifiedPercentage = rectifiedPercentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VcutTvDashboard that = (VcutTvDashboard) o;
        return Objects.equals(currentDateTime, that.currentDateTime) &&
            Objects.equals(day, that.day) &&
            Objects.equals(finishHours, that.finishHours) &&
            Objects.equals(finishMinutes, that.finishMinutes) &&
            Objects.equals(buyerName, that.buyerName) &&
            Objects.equals(style, that.style) &&
            Objects.equals(color, that.color) &&
            Objects.equals(poNo, that.poNo) &&
            Objects.equals(dayTarget, that.dayTarget) &&
            Objects.equals(operators, that.operators) &&
            Objects.equals(helpers, that.helpers) &&
            Objects.equals(cumulativeTarget, that.cumulativeTarget) &&
            Objects.equals(actualTarget, that.actualTarget) &&
            Objects.equals(varianceTarget, that.varianceTarget) &&
            Objects.equals(ftpPercentage, that.ftpPercentage) &&
            Objects.equals(rejected, that.rejected) &&
            Objects.equals(plannedEfficiency, that.plannedEfficiency) &&
            Objects.equals(actualEfficiency, that.actualEfficiency) &&
            Objects.equals(rectifiedPercentage, that.rectifiedPercentage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentDateTime, day, finishHours, finishMinutes, buyerName, style, color, poNo, dayTarget, operators, helpers, cumulativeTarget, actualTarget, varianceTarget, ftpPercentage, rejected, plannedEfficiency, actualEfficiency, rectifiedPercentage);
    }

    @Override
    public String toString() {
        return "VcutTvDashboard{" +
            "currentDateTime='" + currentDateTime + '\'' +
            ", day='" + day + '\'' +
            ", finishHours='" + finishHours + '\'' +
            ", finishMinutes='" + finishMinutes + '\'' +
            ", buyerName='" + buyerName + '\'' +
            ", style='" + style + '\'' +
            ", color='" + color + '\'' +
            ", poNo='" + poNo + '\'' +
            ", dayTarget=" + dayTarget +
            ", operators=" + operators +
            ", helpers=" + helpers +
            ", cumulativeTarget=" + cumulativeTarget +
            ", actualTarget=" + actualTarget +
            ", varianceTarget=" + varianceTarget +
            ", ftpPercentage=" + ftpPercentage +
            ", rejected=" + rejected +
            ", plannedEfficiency=" + plannedEfficiency +
            ", actualEfficiency=" + actualEfficiency +
            ", rectifiedPercentage=" + rectifiedPercentage +
            '}';
    }
}
