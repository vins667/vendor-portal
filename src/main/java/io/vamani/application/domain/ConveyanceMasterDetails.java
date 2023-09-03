package io.vamani.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A ConveyanceAttach.
 */
@Entity
@Table(name = "conveyance_master_details")
public class ConveyanceMasterDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="conveyanceMasterDetailsSeq", sequenceName="conveyance_master_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="conveyanceMasterDetailsSeq")
    private Long id;

    @Size(max = 100)
    @Column(name = "attach_file", length = 100)
    private String attachFile;


    @Size(max = 100)
    @Column(name = "attach_display_file", length = 100)
    private String attachDisplayFile;

    @Column(name = "trip_start")
    private Integer tripStart;

    @Column(name = "trip_end")
    private Integer tripEnd;

    @Column(name = "misc_amount")
    private Double miscAmount;

    @NotNull
    @Size(max = 500)
    @Column(name = "from_location", length = 500)
    private String fromLocation;

    @NotNull
    @Size(max = 500)
    @Column(name = "to_location", length = 500)
    private String toLocation;

    @Size(max = 1000)
    @Column(name = "reason", length = 1000)
    private String reason;

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

    @ManyToOne(optional = false)
    @JoinColumn(name="conveyance_master_id")
    @JsonIgnoreProperties("")
    private ConveyanceMaster conveyanceMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttachFile() {
        return attachFile;
    }

    public ConveyanceMasterDetails attachFile(String attachFile) {
        this.attachFile = attachFile;
        return this;
    }

    public void setAttachFile(String attachFile) {
        this.attachFile = attachFile;
    }

    public String getAttachDisplayFile() {
        return attachDisplayFile;
    }

    public ConveyanceMasterDetails attachDisplayFile(String attachDisplayFile) {
        this.attachDisplayFile = attachDisplayFile;
        return this;
    }

    public void setAttachDisplayFile(String attachDisplayFile) {
        this.attachDisplayFile = attachDisplayFile;
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
		this.fromLocation = fromLocation != null ? fromLocation.trim().toUpperCase() : "";
	}

	public String getToLocation() {
		return toLocation;
	}

	public void setToLocation(String toLocation) {
		this.toLocation = toLocation !=null ? toLocation.trim().toUpperCase() : "";
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason !=null ? reason.trim().toUpperCase(): "";
	}

	public String getCreatedBy() {
        return createdBy;
    }

    public ConveyanceMasterDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public ConveyanceMasterDetails createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public ConveyanceMasterDetails lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public ConveyanceMasterDetails lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public ConveyanceMaster getConveyanceMaster() {
        return conveyanceMaster;
    }

    public ConveyanceMasterDetails conveyanceMaster(ConveyanceMaster conveyanceMaster) {
        this.conveyanceMaster = conveyanceMaster;
        return this;
    }

    public void setConveyanceMaster(ConveyanceMaster conveyanceMaster) {
        this.conveyanceMaster = conveyanceMaster;
    }

    public Double getMiscAmount() {
        return miscAmount;
    }

    public void setMiscAmount(Double miscAmount) {
        this.miscAmount = miscAmount;
    }
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ConveyanceMasterDetails)) {
            return false;
        }
        return id != null && id.equals(((ConveyanceMasterDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

	@Override
	public String toString() {
		return "ConveyanceMasterDetails [id=" + id + ", attachFile=" + attachFile + ", attachDisplayFile="
				+ attachDisplayFile + ", tripStart=" + tripStart + ", tripEnd=" + tripEnd + ", totalDistance="
				+  ", fromLocation=" + fromLocation + ", toLocation=" + toLocation + ", reason=" + reason
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", lastUpdatedBy=" + lastUpdatedBy
				+ ", lastUpdatedDate=" + lastUpdatedDate + ", conveyanceMaster=" + conveyanceMaster + "]";
	}

}
