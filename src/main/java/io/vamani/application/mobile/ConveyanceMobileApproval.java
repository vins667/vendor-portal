package io.vamani.application.mobile;

import java.util.Date;
import java.util.List;

public class ConveyanceMobileApproval {
    private String leaveDateFrom;
    private Date date;
    private String flag;
    private List<ConveyanceMobileApprovalDetails> conveyanceMobileApprovalDetails;

    public String getLeaveDateFrom() {
        return leaveDateFrom;
    }

    public void setLeaveDateFrom(String leaveDateFrom) {
        this.leaveDateFrom = leaveDateFrom;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<ConveyanceMobileApprovalDetails> getConveyanceMobileApprovalDetails() {
        return conveyanceMobileApprovalDetails;
    }

    public void setConveyanceMobileApprovalDetails(List<ConveyanceMobileApprovalDetails> conveyanceMobileApprovalDetails) {
        this.conveyanceMobileApprovalDetails = conveyanceMobileApprovalDetails;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
