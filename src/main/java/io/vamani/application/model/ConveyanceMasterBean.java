package io.vamani.application.model;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import io.vamani.application.domain.ConveyanceMasterDetails;

public class ConveyanceMasterBean {
    private Long id;
    private String empCode;
    private String name;
    private String conveyanceType;
    private Instant conveyanceDate;
    private String conveyanceFromDate;
    private String vehicleNo;
    private Double totalAmount;
    private Integer totalDistance;
    private Double rate;
    private String flag;
    private String factory;
    private Double miscAmount;
    private String approvedBy;
    private Instant approvedDate;
    private String hrApproved;
    private Instant hrApprovedDate;
    private String createdBy;
    private Instant createdDate;
    private String lastUpdatedBy;
    private String vehicleType;
    private Instant lastUpdatedDate;

    private Set<ConveyanceMasterDetails> conveyanceMasterDetails = new HashSet<>();
    private List<ConveyanceMasterDetailsBean> conveyanceMasterDetailsbean;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Set<ConveyanceMasterDetails> getConveyanceMasterDetails() {
        return conveyanceMasterDetails;
    }

    public void setConveyanceMasterDetails(Set<ConveyanceMasterDetails> conveyanceMasterDetails) {
        this.conveyanceMasterDetails = conveyanceMasterDetails;
    }

	public List<ConveyanceMasterDetailsBean> getConveyanceMasterDetailsbean() {
		return conveyanceMasterDetailsbean;
	}

	public void setConveyanceMasterDetailsbean(List<ConveyanceMasterDetailsBean> conveyanceMasterDetailsbean) {
		this.conveyanceMasterDetailsbean = conveyanceMasterDetailsbean;
	}

	public String getConveyanceFromDate() {
		return conveyanceFromDate;
	}

	public void setConveyanceFromDate(String conveyanceFromDate) {
		this.conveyanceFromDate = conveyanceFromDate;
	}

	public Double getMiscAmount() {
		return miscAmount;
	}

	public void setMiscAmount(Double miscAmount) {
		this.miscAmount = miscAmount;
	}
    
	
}
