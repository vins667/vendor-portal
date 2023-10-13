package io.vamani.application.service.impl;

import java.time.Instant;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.vamani.application.domain.StitchCostHeadMaster;
import io.vamani.application.model.StitchCostHeadMasterBean;
import io.vamani.application.repository.StitchCostHeadMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.service.StitchCostHeadMasterService;

@Service
public class StitchCostHeadMasterImpl implements StitchCostHeadMasterService{
	
	@Autowired
	private StitchCostHeadMasterRepository costHeadMasterRepository;

	private final Logger log = LoggerFactory.getLogger(StitchCostHeadMasterImpl.class);

	@Override
	public StitchCostHeadMasterBean getAllStitchCostHeadMaster(String factory) {
		StitchCostHeadMasterBean bean = new StitchCostHeadMasterBean();
		List<StitchCostHeadMaster> result=costHeadMasterRepository.findByfactoryIgnoreCaseContaining(factory);
		if(null==result) {
		    result=costHeadMasterRepository.findAll();
		}
		bean.setFactory(factory);
		bean.setStitchCostHeadMasters(result);
		return bean;
	}

	@Override
	public StitchCostHeadMasterBean getUpdate(StitchCostHeadMasterBean bean) {
		 log.debug("Inside StitchCostHeadMasterImpl calling method getUpdate()");
		 if(null!=bean && bean.getStitchCostHeadMasters().size()>0) {
			 bean.getStitchCostHeadMasters().forEach(header->{
				 header.setFactory(bean.getFactory());
				 header.getStitchCostSubHeadMaster().forEach(details->{
					 details.setStitchCostHeadMastes(header);
					 details.setUpdateBy(SecurityUtils.getCurrentUserLogin().orElse(null));
					 details.setUpdatedDate(Instant.now());
				 });
			 });
			List<StitchCostHeadMaster> result=costHeadMasterRepository.saveAll(bean.getStitchCostHeadMasters());
		    bean.setFactory(bean.getFactory());
		    bean.setStitchCostHeadMasters(result);
		 }
		return bean;
	}
	
}
