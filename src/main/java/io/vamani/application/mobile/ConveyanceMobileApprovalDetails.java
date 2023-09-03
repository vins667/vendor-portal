package io.vamani.application.mobile;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class ConveyanceMobileApprovalDetails implements Serializable {

    private Long id;

    @Column(name = "conveyance_type", nullable = false)
    private String conveyanceType;

    private Instant conveyanceDate;

    private String conveyanceDateString;

    private String vehicleNo;

    private Double totalAmount;

    private Integer totalDistance;

    private Double rate;

    private String flag;

    private String factory;

    private String empCode;

    private String empName;

    private String approvedBy;

    private Instant approvedDate;

    private String hrApproved;

    private Instant hrApprovedDate;

    private Long controlNo;

    private String controlNoBy;

    private Instant controlNoDate;

    private String vehicleType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConveyanceType() {
        return conveyanceType;
    }

    public void setConveyanceType(String conveyanceType) {
        this.conveyanceType = conveyanceType;
    }

    public Instant getConveyanceDate() {
        return conveyanceDate;
    }

    public void setConveyanceDate(Instant conveyanceDate) {
        this.conveyanceDate = conveyanceDate;
    }

    public String getConveyanceDateString() {
        return conveyanceDateString;
    }

    public void setConveyanceDateString(String conveyanceDateString) {
        this.conveyanceDateString = conveyanceDateString;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Instant getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Instant approvedDate) {
        this.approvedDate = approvedDate;
    }

    public String getHrApproved() {
        return hrApproved;
    }

    public void setHrApproved(String hrApproved) {
        this.hrApproved = hrApproved;
    }

    public Instant getHrApprovedDate() {
        return hrApprovedDate;
    }

    public void setHrApprovedDate(Instant hrApprovedDate) {
        this.hrApprovedDate = hrApprovedDate;
    }

    public Long getControlNo() {
        return controlNo;
    }

    public void setControlNo(Long controlNo) {
        this.controlNo = controlNo;
    }

    public String getControlNoBy() {
        return controlNoBy;
    }

    public void setControlNoBy(String controlNoBy) {
        this.controlNoBy = controlNoBy;
    }

    public Instant getControlNoDate() {
        return controlNoDate;
    }

    public void setControlNoDate(Instant controlNoDate) {
        this.controlNoDate = controlNoDate;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConveyanceMobileApprovalDetails that = (ConveyanceMobileApprovalDetails) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(conveyanceType, that.conveyanceType) &&
            Objects.equals(conveyanceDate, that.conveyanceDate) &&
            Objects.equals(conveyanceDateString, that.conveyanceDateString) &&
            Objects.equals(vehicleNo, that.vehicleNo) &&
            Objects.equals(totalAmount, that.totalAmount) &&
            Objects.equals(totalDistance, that.totalDistance) &&
            Objects.equals(rate, that.rate) &&
            Objects.equals(flag, that.flag) &&
            Objects.equals(factory, that.factory) &&
            Objects.equals(empCode, that.empCode) &&
            Objects.equals(approvedBy, that.approvedBy) &&
            Objects.equals(approvedDate, that.approvedDate) &&
            Objects.equals(hrApproved, that.hrApproved) &&
            Objects.equals(hrApprovedDate, that.hrApprovedDate) &&
            Objects.equals(controlNo, that.controlNo) &&
            Objects.equals(controlNoBy, that.controlNoBy) &&
            Objects.equals(controlNoDate, that.controlNoDate) &&
            Objects.equals(vehicleType, that.vehicleType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, conveyanceType, conveyanceDate, conveyanceDateString, vehicleNo, totalAmount, totalDistance, rate, flag, factory, empCode, approvedBy, approvedDate, hrApproved, hrApprovedDate, controlNo, controlNoBy, controlNoDate, vehicleType);
    }

    @Override
    public String toString() {
        return "ConveyanceMobileApprovalDetails{" +
            "id=" + id +
            ", conveyanceType='" + conveyanceType + '\'' +
            ", conveyanceDate=" + conveyanceDate +
            ", conveyanceDateString='" + conveyanceDateString + '\'' +
            ", vehicleNo='" + vehicleNo + '\'' +
            ", totalAmount=" + totalAmount +
            ", totalDistance=" + totalDistance +
            ", rate=" + rate +
            ", flag='" + flag + '\'' +
            ", factory='" + factory + '\'' +
            ", empCode='" + empCode + '\'' +
            ", approvedBy='" + approvedBy + '\'' +
            ", approvedDate=" + approvedDate +
            ", hrApproved='" + hrApproved + '\'' +
            ", hrApprovedDate=" + hrApprovedDate +
            ", controlNo=" + controlNo +
            ", controlNoBy='" + controlNoBy + '\'' +
            ", controlNoDate=" + controlNoDate +
            ", vehicleType='" + vehicleType + '\'' +
            '}';
    }
}
