package io.vamani.application.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A TravelApplicationMaster.
 */
@Entity
@Table(name = "travel_application_master")
public class TravelApplicationMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="travelApplicationMasterSeq", sequenceName="travel_application_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="travelApplicationMasterSeq")
    private Long id;

    @Size(max = 20)
    @Column(name = "emp_code", length = 20)
    private String empCode;

    @NotNull
    @Size(max = 100)
    @Column(name = "travel_destination", length = 100, nullable = false)
    private String travelDestination;

    @Column(name = "travel_days")
    private Double travelDays;

    @Size(max = 200)
    @Column(name = "travel_purpose", length = 200)
    private String travelPurpose;

    @Size(max = 20)
    @Column(name = "created_by", length = 20)
    private String createdBy;
    
    @Size(max = 10)
    @Column(name = "hod_code", length = 10)
    private String hodCode;
    
    @Size(max = 10)
    @Column(name = "hod_approved_by", length = 10)
    private String hodApprovedBy;
    
    @Size(max = 10)
    @Column(name = "hr_approved_by", length = 10)
    private String hrApprovedBy;

    @Column(name = "created_date")
    private Instant createdDate;
    
    @Column(name = "travel_fromdate")
    private Instant travelFromdate;
    
    @Column(name = "travel_todate")
    private Instant travelTodate;
    
    @Column(name = "hod_approved_date")
    private Instant hodApprovedDate;
    
    @Column(name = "hr_approved_date")
    private Instant hrApprovedDate;

    @Size(max = 20)
    @Column(name = "last_updated_by", length = 20)
    private String lastUpdatedBy;

    @Column(name = "last_updated_date")
    private Instant lastUpdatedDate;

    @Column(name = "status")
    private String status;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpCode() {
        return empCode;
    }

    public TravelApplicationMaster empCode(String empCode) {
        this.empCode = empCode;
        return this;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }


	public String getTravelDestination() {
        return travelDestination;
    }

    public TravelApplicationMaster travelDestination(String travelDestination) {
        this.travelDestination = travelDestination != null ? travelDestination.trim().toUpperCase() : "";
        return this;
    }

    public void setTravelDestination(String travelDestination) {
        this.travelDestination = travelDestination != null ? travelDestination.trim().toUpperCase() : "";
    }

    public Double getTravelDays() {
        return travelDays;
    }

    public String getHodApprovedBy() {
		return hodApprovedBy;
	}

	public void setHodApprovedBy(String hodApprovedBy) {
		this.hodApprovedBy = hodApprovedBy;
	}

	public String getHrApprovedBy() {
		return hrApprovedBy;
	}

	public void setHrApprovedBy(String hrApprovedBy) {
		this.hrApprovedBy = hrApprovedBy;
	}

	public String getHodCode() {
		return hodCode;
	}

	public void setHodCode(String hodCode) {
		this.hodCode = hodCode;
	}

	public Instant getHodApprovedDate() {
		return hodApprovedDate;
	}

	public void setHodApprovedDate(Instant hodApprovedDate) {
		this.hodApprovedDate = hodApprovedDate;
	}

	public Instant getHrApprovedDate() {
		return hrApprovedDate;
	}

	public void setHrApprovedDate(Instant hrApprovedDate) {
		this.hrApprovedDate = hrApprovedDate;
	}

	public TravelApplicationMaster travelDays(Double travelDays) {
        this.travelDays = travelDays;
        return this;
    }

    public void setTravelDays(Double travelDays) {
        this.travelDays = travelDays;
    }

    public String getTravelPurpose() {
        return travelPurpose;
    }

    public TravelApplicationMaster travelPurpose(String travelPurpose) {
        this.travelPurpose = travelPurpose != null ? travelPurpose.trim().toUpperCase() : "";
        return this;
    }

    public void setTravelPurpose(String travelPurpose) {
        this.travelPurpose = travelPurpose != null ? travelPurpose.trim().toUpperCase() : "";
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public TravelApplicationMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public TravelApplicationMaster createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public TravelApplicationMaster lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public TravelApplicationMaster lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    public Instant getTravelFromdate() {
		return travelFromdate;
	}

	public void setTravelFromdate(Instant travelFromdate) {
		this.travelFromdate = travelFromdate;
	}

	public Instant getTravelTodate() {
		return travelTodate;
	}

	public void setTravelTodate(Instant travelTodate) {
		this.travelTodate = travelTodate;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TravelApplicationMaster)) {
            return false;
        }
        return id != null && id.equals(((TravelApplicationMaster) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

	@Override
	public String toString() {
		return "TravelApplicationMaster [id=" + id + ", empCode=" + empCode + ", travelDestination=" + travelDestination
				+ ", travelDays=" + travelDays + ", travelPurpose=" + travelPurpose + ", createdBy=" + createdBy
				+ ", hodCode=" + hodCode + ", hodApprovedBy=" + hodApprovedBy + ", hrApprovedBy=" + hrApprovedBy
				+ ", createdDate=" + createdDate + ", travelFromdate=" + travelFromdate + ", travelTodate="
				+ travelTodate + ", hodApprovedDate=" + hodApprovedDate + ", hrApprovedDate=" + hrApprovedDate
				+ ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdatedDate=" + lastUpdatedDate + ", status=" + status
				+ "]";
	}


}
