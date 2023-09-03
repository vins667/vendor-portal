package io.vamani.application.mobile;

import io.vamani.application.model.VcutFactoryLineBreakup;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class VcutTvLineSummary implements Serializable {
    private String currentDateTime;
    private String day;
    private String finishDays;
    private String finishHours;
    private String finishMinutes;
    private String buyerName;
    private String style;
    private String color;
    private String poNo;

    private List<VcutFactoryLineBreakup> vcutFactoryLineBreakups;

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

    public List<VcutFactoryLineBreakup> getVcutFactoryLineBreakups() {
        return vcutFactoryLineBreakups;
    }

    public void setVcutFactoryLineBreakups(List<VcutFactoryLineBreakup> vcutFactoryLineBreakups) {
        this.vcutFactoryLineBreakups = vcutFactoryLineBreakups;
    }
}
