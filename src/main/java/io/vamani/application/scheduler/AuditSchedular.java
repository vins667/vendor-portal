package io.vamani.application.scheduler;

import io.vamani.application.audit.domain.Software;
import io.vamani.application.audit.domain.SoftwareKey;
import io.vamani.application.audit.repository.DiskRepository;
import io.vamani.application.audit.repository.SoftwareKeyRepository;
import io.vamani.application.audit.repository.SoftwareRepository;
import io.vamani.application.audit.repository.SystemRepository;
import io.vamani.application.domain.*;
import io.vamani.application.repository.*;
import io.vamani.application.security.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@EnableScheduling
public class AuditSchedular {
    @Autowired
    private SystemRepository systemRepository;

    @Autowired
    private SoftwareRepository softwareRepository;

    @Autowired
    private SoftwareKeyRepository softwareKeyRepository;

    @Autowired
    private IgnoreSoftwareMasterRepository ignoreSoftwareMasterRepository;

    @Autowired
    private DiskRepository diskRepository;

    @Autowired
    private AssetAuditDetailsRepository assetAuditDetailsRepository;

    @Autowired
    private AssetAuditSoftwareDetailsRepository assetAuditSoftwareDetailsRepository;

    @Autowired
    private AssetAuditSoftwareKeyDetailsRepository assetAuditSoftwareKeyDetailsRepository;

    @Autowired
    private AssetMasterRepository assetMasterRepository;

    @Autowired
    private AssetAuditLastRunTimeRepository assetAuditLastRunTimeRepository;

    @Autowired
    private AssetAuditRunTimeRepository assetAuditRunTimeRepository;

    //@Scheduled(cron = "0 0 18 * * ?")
    public void pushAudit() {
        Instant nowTime = Instant.now();
        //Run Time
        assetAuditRunTimeRepository.save(new AssetAuditRunTime(nowTime));

        // Last Run Time
        assetAuditLastRunTimeRepository.truncateTable();
        assetAuditLastRunTimeRepository.save(new AssetAuditLastRunTime(nowTime));
        List<io.vamani.application.audit.domain.System> systems = systemRepository.findAll();
        long ctr = 0;
        for (io.vamani.application.audit.domain.System system : systems) {
            ++ctr;
            AssetAuditDetails assetAuditDetails = new AssetAuditDetails();
            BeanUtils.copyProperties(system, assetAuditDetails);
            assetAuditDetails.setSystemId(Long.parseLong(system.getId().toString()));
            if (system.getStorageCount() != null && system.getStorageCount().intValue()>0) {
                assetAuditDetails.setStorageCount(Long.parseLong(system.getStorageCount().toString()));
            } else {
                Long storageCount = diskRepository.sumSizeBySystemId(system.getId());
                assetAuditDetails.setStorageCount(storageCount);
            }

            if (system.getMemoryCount() != null) {
                assetAuditDetails.setMemoryCount(Long.parseLong(system.getMemoryCount().toString()));
            }
            if (system.getOsInstallationDate() != null) {
                assetAuditDetails.setOsInstallationDate(system.getOsInstallationDate().toInstant());
            }
            assetAuditDetails.setId(null);
            assetAuditDetails.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
            assetAuditDetails.setCreatedDate(nowTime);
            List<AssetMaster> assetMasters = assetMasterRepository.findByUUID(system.getUuid());
            if (assetMasters != null && assetMasters.size()>0) {
                assetAuditDetails.setAssetCode(assetMasters.get(0).getAssetCode());
            }
            assetAuditDetailsRepository.save(assetAuditDetails);
        }
        if(ctr == systems.size()) {
            System.out.println("Audit schedular run successfully!");
        }

        // Software
        List<Software> softwares = softwareRepository.findAll();

        List<IgnoreSoftwareMaster> ignoreSoftwareMasters = ignoreSoftwareMasterRepository.findAll();

        for (Software software : softwares) {
            boolean exist = false;
            for (IgnoreSoftwareMaster ignoreSoftwareMaster : ignoreSoftwareMasters) {
                if(ignoreSoftwareMaster.getSwName() != null && ignoreSoftwareMaster.getSwName().length()>0 &&
                    software.getName() != null && software.getName().length()>0 &&
                    software.getName().contains(ignoreSoftwareMaster.getSwName())) {
                    exist = true;
                }
                if(ignoreSoftwareMaster.getSwPublisher() != null && ignoreSoftwareMaster.getSwPublisher().length()>0 &&
                    software.getPublisher() != null && software.getPublisher().length()>0 &&
                    software.getPublisher().contains(ignoreSoftwareMaster.getSwPublisher())) {
                    exist = true;
                }
            }
            if(exist == true) {} else {
                AssetAuditSoftwareDetails assetAuditSoftwareDetails = new AssetAuditSoftwareDetails();
                assetAuditSoftwareDetails.setName(software.getName());
                assetAuditSoftwareDetails.setPublisher(software.getPublisher());
                assetAuditSoftwareDetails.setSystemId(Long.parseLong(software.getSystemId()+""));
                assetAuditSoftwareDetails.setInstalledOn(software.getInstalledOn().toInstant());
                assetAuditSoftwareDetails.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                assetAuditSoftwareDetails.setCreatedDate(nowTime);
                assetAuditSoftwareDetailsRepository.save(assetAuditSoftwareDetails);
            }
        }

        // Software Key
        List<SoftwareKey> softwareKeys = softwareKeyRepository.findAll();
        for (SoftwareKey softwareKey : softwareKeys) {
            AssetAuditSoftwareKeyDetails assetAuditSoftwareKeyDetails = new AssetAuditSoftwareKeyDetails();
            assetAuditSoftwareKeyDetails.setName(softwareKey.getName());
            assetAuditSoftwareKeyDetails.setKey(softwareKey.getString());
            assetAuditSoftwareKeyDetails.setSystemId(Long.parseLong(softwareKey.getSystemId().toString()));
            assetAuditSoftwareKeyDetails.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
            assetAuditSoftwareKeyDetails.setCreatedDate(nowTime);
            assetAuditSoftwareKeyDetailsRepository.save(assetAuditSoftwareKeyDetails);
        }
    }
}
