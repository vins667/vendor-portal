package io.vamani.application.db2.model;

public class DaysBean {
    private  Integer day;
    private String date;

    private Boolean selectDay;

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getSelectDay() {
        return selectDay;
    }

    public void setSelectDay(Boolean selectDay) {
        this.selectDay = selectDay;
    }
}
