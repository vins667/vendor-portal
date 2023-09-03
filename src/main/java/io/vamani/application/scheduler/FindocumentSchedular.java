package io.vamani.application.scheduler;

import io.vamani.application.db2.domain.Findocument;
import io.vamani.application.db2.domain.Findocumentbean;
import io.vamani.application.db2.repository.FindocumentRepository;
import io.vamani.application.db2.repository.FindocumentbeanRepository;
import io.vamani.application.db2.repository.FinopendocumentsRepository;
import io.vamani.application.domain.DirectBookingEntry;
import io.vamani.application.repository.DirectBookingEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
public class FindocumentSchedular {
    @Autowired
    private DirectBookingEntryRepository directBookingEntryRepository;

    @Autowired
    private FindocumentRepository findocumentRepository;

    @Autowired
    private FindocumentbeanRepository findocumentbeanRepository;

    @Autowired
    private FinopendocumentsRepository finopendocumentsRepository;

    /* @Bean
    @Scheduled(cron = "0 0/2 * * * ?")
    public void scheduleUpdate() {
        List<DirectBookingEntry> directBookingEntries = directBookingEntryRepository.findAllPostedButCodeNotUpdate();
        if (directBookingEntries != null && directBookingEntries.size() > 0) {
            for (DirectBookingEntry directBookingEntry : directBookingEntries) {
                List<Findocument> findocuments = findocumentRepository.findByRemark("DIRECT#" + directBookingEntry.getId().toString());
                if (findocuments != null && findocuments.size() > 0) {
                    directBookingEntry.setFindocumentcode(findocuments.get(0).getId().getCode());
                    directBookingEntryRepository.save(directBookingEntry);
                    Findocumentbean findocumentbean = findocumentbeanRepository.findById(directBookingEntry.getId()).orElse(null);
                    if (findocumentbean != null) {
                        findocumentRepository.updateCreationUser(findocumentbean.getImpcreationuser(), "DIRECT#" + directBookingEntry.getId().toString());
                    }
                    Findocument findocument = findocuments.get(0);
                    if (findocument.getTdsamount() != null && findocument.getTdsamount().doubleValue() > 0) {
                        finopendocumentsRepository.updateClearedamount(findocument.getTdsamount(),findocument.getId().getBusinessunitcode(), findocument.getId().getFinancialyearcode(), findocument.getId().getCode());
                    }
                }
            }
        }
    }*/
}
