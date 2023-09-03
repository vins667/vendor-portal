package io.vamani.application.model;

import java.time.Instant;

public class ConveyanceReportBean {
    private String controlNo;
    private String subSName;
    private String empCode;
    private String name;
    private String conveyanceDate;
    private Double totalAmount;
    private Integer totalDistance;
    private Double rate;
    private String factory;
    private Integer tripStart;
    private Integer tripEnd;
    private String fromLocation;
    private String toLocation;
    private Double miscAmount;
    private String reason;

    public String getControlNo() {
        return controlNo;
    }

    public void setControlNo(String controlNo) {
        this.controlNo = controlNo;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConveyanceDate() {
        return conveyanceDate;
    }

    public void setConveyanceDate(String conveyanceDate) {
        this.conveyanceDate = conveyanceDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(Integer totalDistance) {
        this.totalDistance = totalDistance;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public Integer getTripStart() {
        return tripStart;
    }

    public void setTripStart(Integer tripStart) {
        this.tripStart = tripStart;
    }

    public Integer getTripEnd() {
        return tripEnd;
    }

    public void setTripEnd(Integer tripEnd) {
        this.tripEnd = tripEnd;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Double getMiscAmount() {
        return miscAmount;
    }

    public void setMiscAmount(Double miscAmount) {
        this.miscAmount = miscAmount;
    }

    public String getSubSName() {
        return subSName;
    }

    public void setSubSName(String subSName) {
        this.subSName = subSName;
    }
}
