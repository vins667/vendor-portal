package io.vamani.application.service;
import io.vamani.application.model.StitchCostHeadMasterBean;

public interface StitchCostSubHeadDetailsService {
	public StitchCostHeadMasterBean getAllStitchCostHeadMaster(String factory);

	public StitchCostHeadMasterBean getUpdateStitchCostSubHeadDetails(StitchCostHeadMasterBean bean);
	
	public StitchCostHeadMasterBean getSaveStitchCostSubHeadDetails(StitchCostHeadMasterBean bean);
}
