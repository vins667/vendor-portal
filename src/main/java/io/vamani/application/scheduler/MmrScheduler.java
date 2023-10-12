package io.vamani.application.scheduler;

import io.vamani.application.domain.JobProfile;
import io.vamani.application.domain.MMRMaster;
import io.vamani.application.repository.JobProfileRepository;
import io.vamani.application.repository.MMRMasterRepository;
import io.vamani.application.scheduler.MmrScheduler;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class MmrScheduler {

	@Autowired
	private MMRMasterRepository mmrMasterRepository;

    @Autowired
    private JobProfileRepository jobProfileRepository;

   // @Scheduled(cron = "* 0 11 * * ?")  by vinay 11-10-23
    public void pushMmrDataByDate() {
        List<JobProfile> jobProfiles = this.jobProfileRepository.findAll();
        Map<String, Map<String, MMRMaster>> factoryMap = new HashMap<String, Map<String, MMRMaster>>();
        List<MMRMaster> mmrMasters = this.mmrMasterRepository.GetFactoryGroupByDate();
        for (MMRMaster mmrMaster : mmrMasters) {
            if (factoryMap.containsKey(mmrMaster.getFactory())) {
                Map<String, MMRMaster> mmrMasterMap = (Map)factoryMap.get(mmrMaster.getFactory());
                mmrMasterMap.put(mmrMaster.getDepartment() + mmrMaster.getDesignation(), mmrMaster);
                factoryMap.put(mmrMaster.getFactory(), mmrMasterMap); continue;
            }
            Map<String, MMRMaster> mmrMasterMap = new HashMap<String, MMRMaster>();
            mmrMasterMap.put(mmrMaster.getDepartment() + mmrMaster.getDesignation(), mmrMaster);
            factoryMap.put(mmrMaster.getFactory(), mmrMasterMap);
        }


        Map<String, Date> factoryDateMap = new HashMap<String, Date>();
        for (MMRMaster mmrMaster : mmrMasters) {
            Date nextDate = Date.from(mmrMaster.getMonthYear());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(nextDate);
            calendar.add(5, 1);
            nextDate = calendar.getTime();
            factoryDateMap.put(mmrMaster.getFactory(), nextDate);
        }

        for (String key : factoryMap.keySet()) {
            Map<String, MMRMaster> mmrMasterMap = (Map)factoryMap.get(key);
            for (JobProfile jobProfile : jobProfiles) {
                if (jobProfile.getFactory() != null && jobProfile.getFactory().equalsIgnoreCase(key)) {
                    if (mmrMasterMap.containsKey(jobProfile.getDepartment() + jobProfile.getDesignation())) {
                        MMRMaster mmrMaster = (MMRMaster)mmrMasterMap.get(jobProfile.getDepartment() + jobProfile.getDesignation());
                        mmrMaster.setCreatedDate(((Date)factoryDateMap.get(key)).toInstant());
                        mmrMaster.setMonthYear(((Date)factoryDateMap.get(key)).toInstant());
                        mmrMaster.setId(null);
                        this.mmrMasterRepository.save(mmrMaster); continue;
                    }
                    MMRMaster mmrMaster = new MMRMaster();
                    mmrMaster.setMonthYear(((Date)factoryDateMap.get(key)).toInstant());
                    mmrMaster.setCreatedDate(((Date)factoryDateMap.get(key)).toInstant());
                    mmrMaster.setFactory(key);
                    mmrMaster.setCreatedBy("admin");
                    mmrMaster.setDepartment(jobProfile.getDepartment());
                    mmrMaster.setDesignation(jobProfile.getDesignation());
                    mmrMaster.setPcsRate(Double.valueOf(0.0D));
                    mmrMaster.setSalary(Double.valueOf(0.0D));
                    mmrMaster.setSwCode(jobProfile.getSwCode());
                    this.mmrMasterRepository.save(mmrMaster);
                }
            }
        }
    }
}
