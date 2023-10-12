package io.vamani.application.service.impl;

import java.time.Instant;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.vamani.application.domain.StitchCostHeadMaster;
import io.vamani.application.domain.StitchCostSubHeadMaster;
import io.vamani.application.repository.StitchCostHeadMasterRepository;
import io.vamani.application.repository.StitchCostSubHeadMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.service.StitchCostHeadMasterService;

@Service
public class StitchCostHeadMasterImpl implements StitchCostHeadMasterService{
	
	@Autowired
	private StitchCostHeadMasterRepository costHeadMasterRepository;
	
	@Autowired
	private StitchCostSubHeadMasterRepository subHeadMasterRepository;

	private final Logger log = LoggerFactory.getLogger(StitchCostHeadMasterImpl.class);
	@Override
	public List<StitchCostHeadMaster> getAllStitchCostHeadMaster() {
		log.debug("Inside StitchCostHeadMasterImpl Calling Method getAllStitchCostHeadMaster");
		return costHeadMasterRepository.findAll();
	}
	@Override
	public StitchCostSubHeadMaster getUpdate(List<StitchCostSubHeadMaster>subHeadMaster) {
		log.debug("Inside StitchCostHeadMasterImpl Calling Method updateStitchCostSubHeadMaster");
		StitchCostSubHeadMaster response=null;
		subHeadMaster.forEach(result->{
			result.setUpdateBy(SecurityUtils.getCurrentUserLogin().orElse(null));
			result.setUpdatedDate(Instant.now());
		});
		response=subHeadMasterRepository.saveAll(subHeadMaster).get(0);
		return response;
	}

}
