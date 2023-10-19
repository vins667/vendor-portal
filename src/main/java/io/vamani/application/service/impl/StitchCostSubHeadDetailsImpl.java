package io.vamani.application.service.impl;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import io.vamani.application.domain.StitchCostHeadMaster;
import io.vamani.application.domain.StitchCostSubHeadDetails;
import io.vamani.application.model.StitchCostHeadMasterBean;
import io.vamani.application.repository.StitchCostHeadMasterRepository;
import io.vamani.application.repository.StitchCostSubHeadDetailsRepository;
import io.vamani.application.repository.StitchingCtcMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.service.StitchCostSubHeadDetailsService;

@Service
public class StitchCostSubHeadDetailsImpl implements StitchCostSubHeadDetailsService {

	@Autowired
	private StitchCostHeadMasterRepository costHeadMasterRepository;

	@Autowired
	private StitchingCtcMasterRepository ctcMasterRepository;

	@Autowired
	private StitchCostSubHeadDetailsRepository detailsRepository;

	private final Logger log = LoggerFactory.getLogger(StitchCostSubHeadDetailsImpl.class);

	@Override
	public StitchCostHeadMasterBean getAllStitchCostHeadMaster(String factory) {
		List<StitchCostHeadMaster> result = null;
		StitchCostHeadMasterBean bean = new StitchCostHeadMasterBean();
		List<Long> masterId=detailsRepository.findMasterIdByFactoryCode(factory);
		BigDecimal toatlCtc = ctcMasterRepository.findTotalCTC(factory);
		
		if(null!=masterId && masterId.size()>0) {
			result= new ArrayList<>();
			for(Long id:masterId) {
				result.add(costHeadMasterRepository.findById(id).orElse(null));
			}
		}else {
			result = costHeadMasterRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
			result.forEach(header->{
				header.getStitchCostSubHeadMaster().forEach(details->{
					details.setStitchCostSubHeadDetails(null);
				});
			});
		}
		result.forEach(data -> {
			if (null != data.getHeadType() && data.getHeadType().equals("CTC")) {
				data.setTotalCtc(toatlCtc);
			}
		});
		bean.setFactory(factory);
		bean.setStitchCostHeadMasters(result);
		return bean;
	}

	@Override
	public StitchCostHeadMasterBean getUpdateStitchCostSubHeadDetails(StitchCostHeadMasterBean bean) {
		log.debug("Inside StitchCostHeadMasterImpl calling method getUpdate()");
		if (null != bean && bean.getStitchCostHeadMasters().size() > 0) {
			bean.getStitchCostHeadMasters().forEach(header -> {
				header.getStitchCostSubHeadMaster().forEach(details -> {
					details.getStitchCostSubHeadDetails().setFactory(bean.getFactory());
					details.getStitchCostSubHeadDetails().setStitchCostSubHeadMaster(details);
					details.getStitchCostSubHeadDetails().setUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
					details.getStitchCostSubHeadDetails().setUpdatedDate(Instant.now());
					StitchCostSubHeadDetails result=detailsRepository.save(details.getStitchCostSubHeadDetails());
					details.setStitchCostSubHeadDetails(result);
				});
			});
		}
		return bean;
	}

	@Override
	public StitchCostHeadMasterBean getSaveStitchCostSubHeadDetails(StitchCostHeadMasterBean bean) {
		log.debug("Inside StitchCostHeadMasterImpl calling method getSave()");
		if (null != bean && bean.getStitchCostHeadMasters().size() > 0) {
			bean.getStitchCostHeadMasters().forEach(header -> {
				header.getStitchCostSubHeadMaster().forEach(data -> {
					StitchCostSubHeadDetails deatils= new StitchCostSubHeadDetails();
					deatils.setStitchCostSubHeadMaster(data);
					deatils.setCompanyCost(data.getStitchCostSubHeadDetails().getCompanyCost());
					deatils.setFactory(bean.getFactory());
					deatils.setEnterBy(SecurityUtils.getCurrentUserLogin().orElse(null));
					deatils.setEnterdDate(Instant.now());
					StitchCostSubHeadDetails result=detailsRepository.save(deatils);
					data.setStitchCostSubHeadDetails(result);
				});
			});
		}
		return bean;
	}
}