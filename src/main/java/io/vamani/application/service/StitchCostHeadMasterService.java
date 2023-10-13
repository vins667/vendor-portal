package io.vamani.application.service;
import io.vamani.application.model.StitchCostHeadMasterBean;

public interface StitchCostHeadMasterService {
	public StitchCostHeadMasterBean getAllStitchCostHeadMaster(String factory);

	public StitchCostHeadMasterBean getUpdate(StitchCostHeadMasterBean bean);
}
