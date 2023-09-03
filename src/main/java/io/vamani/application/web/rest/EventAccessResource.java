package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.EventAccess;
import io.vamani.application.model.Master;
import io.vamani.application.repository.EventAccessRepository;
import io.vamani.application.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing Event.
 */
@RestController
@RequestMapping("/api")
public class EventAccessResource {
    private final Logger log = LoggerFactory.getLogger(EventAccessResource.class);

    private static final String ENTITY_NAME = "eventAccess";

    private final EventAccessRepository eventAccessRepository;

    public EventAccessResource(EventAccessRepository eventAccessRepository) {
        this.eventAccessRepository = eventAccessRepository;
    }

    /**
     * GET  /events : get all the events.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of events in body
     */
    @GetMapping("/events-access")
    @Timed
    public List<EventAccess> getAllEventAccess() {
        log.debug("REST request to get all Event Access");
        return eventAccessRepository.findByCardNo(SecurityUtils.getCurrentUserLogin().orElse(null));
    }
}
