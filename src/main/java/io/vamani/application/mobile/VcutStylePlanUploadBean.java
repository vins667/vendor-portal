package io.vamani.application.mobile;

import io.vamani.application.domain.VcutSessionMaster;
import io.vamani.application.domain.VcutStylePlanUpload;
import io.vamani.application.model.VcutSessionMasterBean;

import java.io.Serializable;
import java.util.List;

public class VcutStylePlanUploadBean implements Serializable {
    private Long minuteUpdate;
    private List<VcutStylePlanUploadMaster> vcutStylePlanUploads;
    private List<VcutOperationIssueMasterBean> operationIssueMasters;
    private List<VcutSessionMaster> vcutSessionMasters;

    public List<VcutStylePlanUploadMaster> getVcutStylePlanUploads() {
        return vcutStylePlanUploads;
    }

    public void setVcutStylePlanUploads(List<VcutStylePlanUploadMaster> vcutStylePlanUploads) {
        this.vcutStylePlanUploads = vcutStylePlanUploads;
    }

    public List<VcutOperationIssueMasterBean> getOperationIssueMasters() {
        return operationIssueMasters;
    }

    public void setOperationIssueMasters(List<VcutOperationIssueMasterBean> operationIssueMasters) {
        this.operationIssueMasters = operationIssueMasters;
    }

    public List<VcutSessionMaster> getVcutSessionMasters() {
        return vcutSessionMasters;
    }

    public void setVcutSessionMasters(List<VcutSessionMaster> vcutSessionMasters) {
        this.vcutSessionMasters = vcutSessionMasters;
    }

    public Long getMinuteUpdate() {
        return minuteUpdate;
    }

    public void setMinuteUpdate(Long minuteUpdate) {
        this.minuteUpdate = minuteUpdate;
    }
}
