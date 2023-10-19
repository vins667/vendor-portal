package io.vamani.application.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "stitching_Cost_heads_master")
public class StitchCostHeadMaster implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	@Column(name = "head_name", insertable = false)
	private String headName;

	@Column(name = "head_type", insertable = false)
	private String headType;
	
	@Column(name = "fact_code")
	private String factory;
	
	@Transient
	@JsonProperty
	private BigDecimal totalCtc;

	@OrderBy("id")
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "stitchCostHeadMaster")
	private List<StitchCostSubHeadMaster> stitchCostSubHeadMaster;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHeadName() {
		return headName;
	}

	public void setHeadName(String headName) {
		this.headName = headName;
	}
	
	public String getHeadType() {
		return headType;
	}

	public void setHeadType(String headType) {
		this.headType = headType;
	}

	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public BigDecimal getTotalCtc() {
		return totalCtc;
	}

	public void setTotalCtc(BigDecimal totalCtc) {
		this.totalCtc = totalCtc;
	}

	public List<StitchCostSubHeadMaster> getStitchCostSubHeadMaster() {
		return stitchCostSubHeadMaster;
	}

	public void setStitchCostSubHeadMaster(List<StitchCostSubHeadMaster> stitchCostSubHeadMaster) {
		this.stitchCostSubHeadMaster = stitchCostSubHeadMaster;
	}

	@Override
	public String toString() {
		return "StitchCostHeadMaster [id=" + id + ", headName=" + headName + ", headType=" + headType + ", factory="
				+ factory + ", totalCtc=" + totalCtc + ", stitchCostSubHeadMaster=" + stitchCostSubHeadMaster + "]";
	}

}
