package io.vamani.application.mobile;

import io.vamani.application.domain.HolidayMaster;

import java.util.List;

public class ConveyanceMobileApprovalBean {

    private Boolean exist;

    private String errorMessage;

    private List<HolidayMaster> holidayMasters;

    private List<ConveyanceMobileApproval> conveyanceMobileApprovals;

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

    public List<ConveyanceMobileApproval> getConveyanceMobileApprovals() {
        return conveyanceMobileApprovals;
    }

    public void setConveyanceMobileApprovals(List<ConveyanceMobileApproval> conveyanceMobileApprovals) {
        this.conveyanceMobileApprovals = conveyanceMobileApprovals;
    }
}
