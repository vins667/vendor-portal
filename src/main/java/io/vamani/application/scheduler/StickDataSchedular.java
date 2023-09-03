package io.vamani.application.scheduler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import io.vamani.application.service.StickDataService;

@Component
@EnableScheduling
public class StickDataSchedular {
	private final Logger log = LoggerFactory.getLogger(StickDataSchedular.class);
	
	@Autowired
	private StickDataService stickDataService;

	@Scheduled(cron = "${stitchapi.stitchCron}")
	public void getStickDetails() {
        String startDate = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH).format(LocalDateTime.now());
		log.info("Calling method getStickDetails() inside StickDataSchedular" +"  "+startDate);
		String message = stickDataService.saveStitchingData();
		log.info("Method call end " +"  "+startDate+"   "+message);
	}
}
