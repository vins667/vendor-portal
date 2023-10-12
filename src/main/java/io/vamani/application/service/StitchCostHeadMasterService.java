package io.vamani.application.service;

import java.util.List;
import io.vamani.application.domain.StitchCostHeadMaster;
import io.vamani.application.domain.StitchCostSubHeadMaster;

public interface StitchCostHeadMasterService {
	public List<StitchCostHeadMaster> getAllStitchCostHeadMaster();

	public StitchCostSubHeadMaster getUpdate(List<StitchCostSubHeadMaster>subHeadMaster);
}
