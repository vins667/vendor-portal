package io.vamani.application.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import io.vamani.application.domain.StitchCostHeadMaster;

public class StitchCostHeadMasterBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String factory;
	private BigDecimal totalCtc;
	private List<StitchCostHeadMaster> stitchCostHeadMasters;

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

	public List<StitchCostHeadMaster> getStitchCostHeadMasters() {
		return stitchCostHeadMasters;
	}

	public void setStitchCostHeadMasters(List<StitchCostHeadMaster> stitchCostHeadMasters) {
		this.stitchCostHeadMasters = stitchCostHeadMasters;
	}

	@Override
	public String toString() {
		return "StitchCostHeadMasterBean [factory=" + factory + ", totalCtc=" + totalCtc + ", stitchCostHeadMasters="
				+ stitchCostHeadMasters + "]";
	}

}
