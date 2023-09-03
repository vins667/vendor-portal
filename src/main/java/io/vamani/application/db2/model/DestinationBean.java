package io.vamani.application.db2.model;

import io.vamani.application.model.MarkerEntryDetailsBean;

import java.io.Serializable;
import java.util.List;

public class DestinationBean implements Serializable {
    private String destination;
    private Double totalQty;
    private List<MarkerEntryDetailsBean> markerEntryDetailsBeans;

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Double getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(Double totalQty) {
        this.totalQty = totalQty;
    }

    public List<MarkerEntryDetailsBean> getMarkerEntryDetailsBeans() {
        return markerEntryDetailsBeans;
    }

    public void setMarkerEntryDetailsBeans(List<MarkerEntryDetailsBean> markerEntryDetailsBeans) {
        this.markerEntryDetailsBeans = markerEntryDetailsBeans;
    }
}
