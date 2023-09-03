package io.vamani.application.service.impl;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import io.vamani.application.config.StitchApiProperties;
import io.vamani.application.domain.StitchingDataEntity;
import io.vamani.application.model.Result;
import io.vamani.application.model.StitchingData;
import io.vamani.application.repository.StitchingDataRepository;
import io.vamani.application.service.StickDataService;

@Service
public class StickDataServiceImpl implements StickDataService {
	private final Logger log = LoggerFactory.getLogger(StickDataServiceImpl.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private StitchingDataRepository repo;
	
	@Autowired
	private StitchApiProperties stitchApiProperties;
	
	private String SUCESS_MSG;
	
	@Transactional
	@Override
	public String saveStitchingData() {
		try {
			LocalDateTime ldt = LocalDateTime.now();
			for(long i=0;i<31;i++) {
				LocalDateTime currentDate= ldt.minusDays(i);
				List<StitchingDataEntity> resultList=getConsumeStickDataApi(currentDate);
				if(resultList.size()>0) {
					repo.saveAll(resultList);
					SUCESS_MSG="Record saved successfully !!! with total " +resultList.size();
				}else {
					SUCESS_MSG="Record not saved !!! "+" "+resultList.size();
				}
			}
		}catch(Exception e) {
			log.error("Something went wrong with db "+ "  "+e.getMessage());
		}
		return SUCESS_MSG;
	}
	
	public List<StitchingDataEntity> getConsumeStickDataApi(LocalDateTime ldt) {
        String startDate = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(ldt);
		List<StitchingDataEntity> stickList=new ArrayList<>();
		HttpHeaders headers = new HttpHeaders();
		headers.set("STITCH-KEY", stitchApiProperties.getStickKey());
		headers.set("X-Hub-Signature",stitchApiProperties.getBase64EncodedSignature());
		headers.set("Content-Type", stitchApiProperties.getContentType());
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<StitchingData> responseEntity = restTemplate.exchange(stitchApiProperties.getApiUrl() + startDate, HttpMethod.GET, entity, StitchingData.class);
		if(null!=responseEntity && responseEntity.getBody().getData().getResults().size()>0) {
			log.info("Total Records " +"  "+responseEntity.getBody().getData().getCount());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			for(Result result:responseEntity.getBody().getData().getResults()) {
				StitchingDataEntity dataEntity= new StitchingDataEntity();
				BeanUtils.copyProperties(result, dataEntity);
				if(null!=result.getProductionDate()) {
					 dataEntity.setProductionDate(LocalDate.parse(result.getProductionDate(), formatter));
			    }
				dataEntity.setCreatedBy("StickDataSchedular");
				dataEntity.setCreatedDate(Instant.now());
				stickList.add(dataEntity);
			}
		}
		return stickList;
	}

	
}
