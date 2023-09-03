package io.vamani.application.domain;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * A ConveyanceMaster.
 */
@Entity
@Table(name = "conveyance_master")
public class ConveyanceMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="conveyanceMasterSeq", sequenceName="conveyance_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="conveyanceMasterSeq")
    private Long id;

    @NotNull
    @Size(min = 1)
    @Column(name = "conveyance_type", nullable = false)
    private String conveyanceType;
    
    @Column(name = "conveyance_date")
    private Instant conveyanceDate;
    
    @Column(name = "vehicle_no")
    private String vehicleNo;
    
    @Column(name = "total_amount")
    private Double totalAmount;

    
    @Column(name = "total_distance")
    private Integer totalDistance;

    @Column(name = "rate")
    private Double rate;

    @Column(name = "flag",length = 1)
    private String flag;

    @Column(name = "factory")
    private String factory;

    @NotNull
    @Column(name = "emp_code", length = 50, nullable = false)
    private String empCode;
    
    @NotNull
    @Size(max = 50)
    @Column(name = "approved_by", length = 50, nullable = false)
    private String approvedBy;
    
    @Column(name = "approved_date")
    private Instant approvedDate;
    
    @Size(max = 50)
    @Column(name = "hr_approved", length = 50)
    private String hrApproved;
    
    @Column(name = "hr_approved_date")
    private Instant hrApprovedDate;
    
    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Size(max = 50)
    @Column(name = "last_updated_by", length = 50)
    private String lastUpdatedBy;

    @Column(name = "last_updated_date")
    private Instant lastUpdatedDate;

    @Column(name = "control_no", length = 50)
    private Long controlNo;

    @Size(max = 50)
    @Column(name = "control_no_by", length = 50)
    private String controlNoBy;

    @Column(name = "control_no_date")
    private Instant controlNoDate;

    @Column(name = "vehicle_type")
    private String vehicleType;
    
    @OneToMany(mappedBy = "conveyanceMaster")
    private Set<ConveyanceMasterDetails> conveyanceMasterDetails = new HashSet<>();
    
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConveyanceType() {
        return conveyanceType;
    }

    public ConveyanceMaster conveyanceType(String conveyanceType) {
        this.conveyanceType = conveyanceType;
        return this;
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
		this.vehicleNo = vehicleNo !=null ? vehicleNo.trim().toUpperCase() : "";
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

    public ConveyanceMaster totalDistance(Integer totalDistance) {
        this.totalDistance = totalDistance;
        return this;
    }

    public void setTotalDistance(Integer totalDistance) {
        this.totalDistance = totalDistance;
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
	

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public String getApprovedBy() {
        return approvedBy;
    }

    public ConveyanceMaster approvedBy(String approvedBy) {
        this.approvedBy = approvedBy;
        return this;
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

    public ConveyanceMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public ConveyanceMaster createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public ConveyanceMaster lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public ConveyanceMaster lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

   
    public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}
	
	
	public Set<ConveyanceMasterDetails> getConveyanceMasterDetails() {
        return conveyanceMasterDetails;
    }

    public ConveyanceMaster conveyanceMasterDetails(Set<ConveyanceMasterDetails> conveyanceMasterDetails) {
        this.conveyanceMasterDetails = conveyanceMasterDetails;
        return this;
    }

    public ConveyanceMaster addConveyanceAttach(ConveyanceMasterDetails conveyanceMasterDetails) {
        this.conveyanceMasterDetails.add(conveyanceMasterDetails);
        conveyanceMasterDetails.setConveyanceMaster(this);
        return this;
    }

    public ConveyanceMaster removeConveyanceAttach(ConveyanceMasterDetails conveyanceMasterDetails) {
        this.conveyanceMasterDetails.remove(conveyanceMasterDetails);
        conveyanceMasterDetails.setConveyanceMaster(null);
        return this;
    }

    public void setConveyanceAttaches(Set<ConveyanceMasterDetails> conveyanceMasterDetails) {
        this.conveyanceMasterDetails = conveyanceMasterDetails;
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
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ConveyanceMaster)) {
            return false;
        }
        return id != null && id.equals(((ConveyanceMaster) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ConveyanceMaster{" +
            "id=" + getId() +
            ", conveyanceType='" + getConveyanceType() + "'" +
            ", totalDistance=" + getTotalDistance() +
            ", approvedBy='" + getApprovedBy() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", rate='" + getRate() + "'" +
            "}";
    }
}
