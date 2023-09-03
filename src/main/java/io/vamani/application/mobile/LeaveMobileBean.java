package io.vamani.application.mobile;

import io.vamani.application.domain.HolidayMaster;

import java.util.List;

public class LeaveMobileBean {

    private Boolean exist;

    private String errorMessage;

    private List<HolidayMaster> holidayMasters;

    private List<LeaveMobile> leaveMobiles;

    public Boolean getExist() {
        return exist;
    }

    public void setExist(Boolean exist) {
        this.exist = exist;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<HolidayMaster> getHolidayMasters() {
        return holidayMasters;
    }

    public void setHolidayMasters(List<HolidayMaster> holidayMasters) {
        this.holidayMasters = holidayMasters;
    }

    public List<LeaveMobile> getLeaveMobiles() {
        return leaveMobiles;
    }

    public void setLeaveMobiles(List<LeaveMobile> leaveMobiles) {
        this.leaveMobiles = leaveMobiles;
    }
}
