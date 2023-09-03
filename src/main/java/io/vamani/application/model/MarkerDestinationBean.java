package io.vamani.application.model;

import io.vamani.application.db2.model.DestinationBean;
import io.vamani.application.db2.model.SizesBean;

import java.io.Serializable;
import java.util.List;

public class MarkerDestinationBean implements Serializable {
    private List<SizesBean> sizeCodes;
    private List<DestinationBean> destinationBeans;

    public List<SizesBean> getSizeCodes() {
        return sizeCodes;
    }

    public void setSizeCodes(List<SizesBean> sizeCodes) {
        this.sizeCodes = sizeCodes;
    }

    public List<DestinationBean> getDestinationBeans() {
        return destinationBeans;
    }

    public void setDestinationBeans(List<DestinationBean> destinationBeans) {
        this.destinationBeans = destinationBeans;
    }
}
