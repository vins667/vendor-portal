package io.vamani.application.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "stitching_Cost_heads_master")
public class StitchCostHeadMaster implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
    @Column(name = "head_name",insertable = false)
	private String headName;
    
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "stitchCostHeadMaster")
	private List<StitchCostSubHeadMaster>stitchCostSubHeadMaster;
    
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
	public List<StitchCostSubHeadMaster> getStitchCostSubHeadMaster() {
		return stitchCostSubHeadMaster;
	}
	public void setStitchCostSubHeadMaster(List<StitchCostSubHeadMaster> stitchCostSubHeadMaster) {
		this.stitchCostSubHeadMaster = stitchCostSubHeadMaster;
	}
	@Override
	public String toString() {
		return "StitchCostHeadMaster [id=" + id + ", headName=" + headName + ", stitchCostSubHeadMaster="
				+ stitchCostSubHeadMaster + "]";
	}

}
