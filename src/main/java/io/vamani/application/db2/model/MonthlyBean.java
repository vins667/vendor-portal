package io.vamani.application.db2.model;

import java.util.List;

public class MonthlyBean {
    private String month;

    private List<DaysBean>daysBeans;

    public List<DaysBean> getDaysBeans() {
        return daysBeans;
    }

    public void setDaysBeans(List<DaysBean> daysBeans) {
        this.daysBeans = daysBeans;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
