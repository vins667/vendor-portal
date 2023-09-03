package io.vamani.application.mobile;

import java.util.Date;
import java.util.List;

public class LeaveMobileApproval {
    private String leaveDateFrom;
    private Date date;
    private String flag;
    private List<LeaveMobileApprovalDetails> leaveMobileApprovalDetails;

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

    public List<LeaveMobileApprovalDetails> getLeaveMobileApprovalDetails() {
        return leaveMobileApprovalDetails;
    }

    public void setLeaveMobileApprovalDetails(List<LeaveMobileApprovalDetails> leaveMobileApprovalDetails) {
        this.leaveMobileApprovalDetails = leaveMobileApprovalDetails;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
