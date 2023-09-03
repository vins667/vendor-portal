package io.vamani.application.mobile;

import io.vamani.application.domain.CompOffMaster;

import java.util.Date;
import java.util.List;

public class CompOffApprovalMaster {
    private Date date;
    private String compOffDate;
    private String flag;
    private List<CompOffMasterMobile> compOffMasters;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCompOffDate() {
        return compOffDate;
    }

    public void setCompOffDate(String compOffDate) {
        this.compOffDate = compOffDate;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<CompOffMasterMobile> getCompOffMasters() {
        return compOffMasters;
    }

    public void setCompOffMasters(List<CompOffMasterMobile> compOffMasters) {
        this.compOffMasters = compOffMasters;
    }
}
