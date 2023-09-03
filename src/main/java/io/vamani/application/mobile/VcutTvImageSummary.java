package io.vamani.application.mobile;

import java.util.List;
import java.util.Objects;

public class VcutTvImageSummary {
    private String currentDateTime;
    private String day;
    private String finishDays;
    private String finishHours;
    private String finishMinutes;
    private String buyerName;
    private String style;
    private String color;
    private String poNo;
    private String imageFront;
    private String imageBack;

    private List<VcutTvcCordinate> frontCordinates;
    private List<VcutTvcCordinate> backCordinates;

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

    public String getImageFront() {
        return imageFront;
    }

    public void setImageFront(String imageFront) {
        this.imageFront = imageFront;
    }

    public String getImageBack() {
        return imageBack;
    }

    public void setImageBack(String imageBack) {
        this.imageBack = imageBack;
    }

    public List<VcutTvcCordinate> getFrontCordinates() {
        return frontCordinates;
    }

    public void setFrontCordinates(List<VcutTvcCordinate> frontCordinates) {
        this.frontCordinates = frontCordinates;
    }

    public List<VcutTvcCordinate> getBackCordinates() {
        return backCordinates;
    }

    public void setBackCordinates(List<VcutTvcCordinate> backCordinates) {
        this.backCordinates = backCordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VcutTvImageSummary that = (VcutTvImageSummary) o;
        return Objects.equals(currentDateTime, that.currentDateTime) &&
            Objects.equals(day, that.day) &&
            Objects.equals(finishDays, that.finishDays) &&
            Objects.equals(finishHours, that.finishHours) &&
            Objects.equals(finishMinutes, that.finishMinutes) &&
            Objects.equals(buyerName, that.buyerName) &&
            Objects.equals(style, that.style) &&
            Objects.equals(color, that.color) &&
            Objects.equals(poNo, that.poNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentDateTime, day, finishDays, finishHours, finishMinutes, buyerName, style, color, poNo);
    }
}
