package io.vamani.application.mobile;

import io.vamani.application.domain.HolidayMaster;

import java.util.List;

public class LeaveMobileApprovalBean {

    private Boolean exist;

    private String errorMessage;

    private List<HolidayMaster> holidayMasters;

    private List<LeaveMobileApproval> leaveMobiles;

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

    public List<LeaveMobileApproval> getLeaveMobiles() {
        return leaveMobiles;
    }

    public void setLeaveMobiles(List<LeaveMobileApproval> leaveMobiles) {
        this.leaveMobiles = leaveMobiles;
    }
}
