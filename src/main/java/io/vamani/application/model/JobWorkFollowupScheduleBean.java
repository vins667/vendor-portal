package io.vamani.application.model;

import io.vamani.application.db2.model.MonthlyBean;

import java.util.List;

public class JobWorkFollowupScheduleBean {
    private String finYear;
    private String schType;
    private String onDate;
    private String onDateSecond;
    private Long respReminder;
    private Long level1Reminder;
    private Long level2Reminder;

    private Long jobWorkFollowupId;
    private List<MonthlyBean> monthlyBeans;

    public String getFinYear() {
        return finYear;
    }

    public void setFinYear(String finYear) {
        this.finYear = finYear;
    }

    public String getSchType() {
        return schType;
    }

    public void setSchType(String schType) {
        this.schType = schType;
    }

    public String getOnDate() {
        return onDate;
    }

    public void setOnDate(String onDate) {
        this.onDate = onDate;
    }

    public String getOnDateSecond() {
        return onDateSecond;
    }

    public void setOnDateSecond(String onDateSecond) {
        this.onDateSecond = onDateSecond;
    }

    public Long getRespReminder() {
        return respReminder;
    }

    public void setRespReminder(Long respReminder) {
        this.respReminder = respReminder;
    }

    public Long getLevel1Reminder() {
        return level1Reminder;
    }

    public void setLevel1Reminder(Long level1Reminder) {
        this.level1Reminder = level1Reminder;
    }

    public Long getLevel2Reminder() {
        return level2Reminder;
    }

    public void setLevel2Reminder(Long level2Reminder) {
        this.level2Reminder = level2Reminder;
    }

    public Long getJobWorkFollowupId() {
        return jobWorkFollowupId;
    }

    public void setJobWorkFollowupId(Long jobWorkFollowupId) {
        this.jobWorkFollowupId = jobWorkFollowupId;
    }

    public List<MonthlyBean> getMonthlyBeans() {
        return monthlyBeans;
    }

    public void setMonthlyBeans(List<MonthlyBean> monthlyBeans) {
        this.monthlyBeans = monthlyBeans;
    }
}
