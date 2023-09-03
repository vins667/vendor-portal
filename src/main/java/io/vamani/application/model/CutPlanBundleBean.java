package io.vamani.application.model;

import java.io.Serializable;
import java.util.List;

public class CutPlanBundleBean implements Serializable {
    private Boolean saveFlag;
    private List<CutPlanBundleSizesBean> cutPlanBundleSizesBeans;
    private List<CutPlanBundleDetailsBean> cutPlanBundleDetailsBeans;
    private List<CutPlanBundleMatrixBean> cutPlanBundleMatrixBeans;

    public Boolean getSaveFlag() {
        return saveFlag;
    }

    public void setSaveFlag(Boolean saveFlag) {
        this.saveFlag = saveFlag;
    }

    public List<CutPlanBundleSizesBean> getCutPlanBundleSizesBeans() {
        return cutPlanBundleSizesBeans;
    }

    public void setCutPlanBundleSizesBeans(List<CutPlanBundleSizesBean> cutPlanBundleSizesBeans) {
        this.cutPlanBundleSizesBeans = cutPlanBundleSizesBeans;
    }

    public List<CutPlanBundleDetailsBean> getCutPlanBundleDetailsBeans() {
        return cutPlanBundleDetailsBeans;
    }

    public void setCutPlanBundleDetailsBeans(List<CutPlanBundleDetailsBean> cutPlanBundleDetailsBeans) {
        this.cutPlanBundleDetailsBeans = cutPlanBundleDetailsBeans;
    }

    public List<CutPlanBundleMatrixBean> getCutPlanBundleMatrixBeans() {
        return cutPlanBundleMatrixBeans;
    }

    public void setCutPlanBundleMatrixBeans(List<CutPlanBundleMatrixBean> cutPlanBundleMatrixBeans) {
        this.cutPlanBundleMatrixBeans = cutPlanBundleMatrixBeans;
    }
}
