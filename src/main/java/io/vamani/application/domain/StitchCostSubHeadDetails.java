package io.vamani.application.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "STITCHING_COST_SUB_HEADS_DETAILS")
public class StitchCostSubHeadDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "factory_code")
	private String factory;

	@Column(name = "company_cost")
	private Double companyCost;

	@Column(name = "entry_by", length = 50)
	private String enterBy;

	@Column(name = "entry_date")
	private Instant enterdDate;

	@Size(max = 50)
	@Column(name = "updated_by", length = 50)
	private String updatedBy;

	@Column(name = "updated_date")
	private Instant updatedDate;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "sub_head_id", referencedColumnName = "id")
	private StitchCostSubHeadMaster stitchCostSubHeadMaster;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public Double getCompanyCost() {
		return companyCost;
	}

	public void setCompanyCost(Double companyCost) {
		this.companyCost = companyCost;
	}

	public String getEnterBy() {
		return enterBy;
	}

	public void setEnterBy(String enterBy) {
		this.enterBy = enterBy;
	}

	public Instant getEnterdDate() {
		return enterdDate;
	}

	public void setEnterdDate(Instant enterdDate) {
		this.enterdDate = enterdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updateBy) {
		this.updatedBy = updateBy;
	}

	public Instant getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Instant updatedDate) {
		this.updatedDate = updatedDate;
	}

	public StitchCostSubHeadMaster getStitchCostSubHeadMaster() {
		return stitchCostSubHeadMaster;
	}

	public void setStitchCostSubHeadMaster(StitchCostSubHeadMaster stitchCostSubHeadMaster) {
		this.stitchCostSubHeadMaster = stitchCostSubHeadMaster;
	}

}
