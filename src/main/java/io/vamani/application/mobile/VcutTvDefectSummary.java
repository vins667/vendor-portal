package io.vamani.application.mobile;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class VcutTvDefectSummary implements Serializable {
    private String currentDateTime;
    private String day;
    private String finishDays;
    private String finishHours;
    private String finishMinutes;
    private String buyerName;
    private String style;
    private String color;
    private String poNo;
    private Long defects;
    private Long totalHour;
    private List<VcutTvDefectBreakup> vcutTvDefectBreakups;

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

    public Long getDefects() {
        return defects;
    }

    public void setDefects(Long defects) {
        this.defects = defects;
    }

    public Long getTotalHour() {
        return totalHour;
    }

    public void setTotalHour(Long totalHour) {
        this.totalHour = totalHour;
    }

    public List<VcutTvDefectBreakup> getVcutTvDefectBreakups() {
        return vcutTvDefectBreakups;
    }

    public void setVcutTvDefectBreakups(List<VcutTvDefectBreakup> vcutTvDefectBreakups) {
        this.vcutTvDefectBreakups = vcutTvDefectBreakups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VcutTvDefectSummary that = (VcutTvDefectSummary) o;
        return Objects.equals(currentDateTime, that.currentDateTime) &&
            Objects.equals(day, that.day) &&
            Objects.equals(finishDays, that.finishDays) &&
            Objects.equals(finishHours, that.finishHours) &&
            Objects.equals(finishMinutes, that.finishMinutes) &&
            Objects.equals(buyerName, that.buyerName) &&
            Objects.equals(style, that.style) &&
            Objects.equals(color, that.color) &&
            Objects.equals(poNo, that.poNo) &&
            Objects.equals(defects, that.defects) &&
            Objects.equals(totalHour, that.totalHour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentDateTime, day, finishDays, finishHours, finishMinutes, buyerName, style, color, poNo, defects, totalHour);
    }

    @Override
    public String toString() {
        return "VcutTvDefectSummary{" +
            "currentDateTime='" + currentDateTime + '\'' +
            ", day='" + day + '\'' +
            ", finishDays='" + finishDays + '\'' +
            ", finishHours='" + finishHours + '\'' +
            ", finishMinutes='" + finishMinutes + '\'' +
            ", buyerName='" + buyerName + '\'' +
            ", style='" + style + '\'' +
            ", color='" + color + '\'' +
            ", poNo='" + poNo + '\'' +
            ", defects=" + defects +
            ", totalHour=" + totalHour +
            '}';
    }
}
