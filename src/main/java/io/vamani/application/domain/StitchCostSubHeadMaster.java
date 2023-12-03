package io.vamani.application.domain;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "stitching_cost_sub_heads_master")
public class StitchCostSubHeadMaster implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	@Column(name = "sub_heads_name")
	private String subHeadName;
	
	@Size(max = 50)
    @Column(name = "update_by", length = 50)
    private String updateBy;

    @Column(name = "updated_date")
    private Instant updatedDate;
    
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "head_id",referencedColumnName = "id",insertable = false)
	private StitchCostHeadMaster stitchCostHeadMaster;
	
	
	@OneToOne(cascade = CascadeType.ALL,fetch =FetchType.LAZY,mappedBy = "stitchCostHeadMaster")
	private StitchCostSubHeadDetails stitchCostSubHeadDetails;
	 
	 public Long getId() {
		return id;
	 }

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubHeadName() {
		return subHeadName;
	}

	public void setSubHeadName(String subHeadName) {
		this.subHeadName = subHeadName;
	}


	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Instant getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Instant updatedDate) {
		this.updatedDate = updatedDate;
	}

	public StitchCostHeadMaster getStitchCostHeadMaster() {
		return stitchCostHeadMaster;
	}

	public void setStitchCostHeadMaster(StitchCostHeadMaster stitchCostHeadMaster) {
		this.stitchCostHeadMaster = stitchCostHeadMaster;
	}

	public StitchCostSubHeadDetails getStitchCostSubHeadDetails() {
		return stitchCostSubHeadDetails;
	}

	public void setStitchCostSubHeadDetails(StitchCostSubHeadDetails stitchCostSubHeadDetails) {
		this.stitchCostSubHeadDetails = stitchCostSubHeadDetails;
	}

	
}
