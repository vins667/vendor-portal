package io.vamani.application.model;

import io.vamani.application.domain.HolidayMaster;
import io.vamani.application.domain.MonthlyNewsData;
import io.vamani.application.domain.PollMaster;
import io.vamani.application.domain.Quotes;
import io.vamani.application.mssql.domain.EmployeeView;

import java.io.Serializable;
import java.util.List;

public class Dashboard implements Serializable {
    private List<EmployeeView> birthdayList;
    private List<EmployeeView> anniversaryList;
    private List<HolidayMaster> holidayMastersList;
    private List<Attendance> attendanceList;
    private List<LeaveStatus> leaveStatusList;
    private PollMaster pollMaster;
    private Quotes quotes;
    private MonthlyNewsData monthlyNews;

    public List<EmployeeView> getBirthdayList() {
        return birthdayList;
    }

    public void setBirthdayList(List<EmployeeView> birthdayList) {
        this.birthdayList = birthdayList;
    }

    public List<EmployeeView> getAnniversaryList() {
        return anniversaryList;
    }

    public void setAnniversaryList(List<EmployeeView> anniversaryList) {
        this.anniversaryList = anniversaryList;
    }

    public List<HolidayMaster> getHolidayMastersList() {
        return holidayMastersList;
    }

    public void setHolidayMastersList(List<HolidayMaster> holidayMastersList) {
        this.holidayMastersList = holidayMastersList;
    }

    public List<Attendance> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(List<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }

    public PollMaster getPollMaster() {
        return pollMaster;
    }

    public void setPollMaster(PollMaster pollMaster) {
        this.pollMaster = pollMaster;
    }

    public List<LeaveStatus> getLeaveStatusList() {
        return leaveStatusList;
    }

    public void setLeaveStatusList(List<LeaveStatus> leaveStatusList) {
        this.leaveStatusList = leaveStatusList;
    }

    public Quotes getQuotes() {
        return quotes;
    }

    public void setQuotes(Quotes quotes) {
        this.quotes = quotes;
    }

    public MonthlyNewsData getMonthlyNews() {
        return monthlyNews;
    }

    public void setMonthlyNews(MonthlyNewsData monthlyNews) {
        this.monthlyNews = monthlyNews;
    }
}
