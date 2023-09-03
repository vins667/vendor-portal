package io.vamani.application.db2.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.db2.domain.Resources;
import io.vamani.application.db2.domain.Termsofdelivery;
import io.vamani.application.db2.repository.ResourcesRepository;
import io.vamani.application.db2.repository.TermsofdeliveryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TermsofdeliveryResource {
    private final Logger log = LoggerFactory.getLogger(TermsofdeliveryResource.class);

    private static final String ENTITY_NAME = "termsofdelivery";

    private final TermsofdeliveryRepository termsofdeliveryRepository;

    public TermsofdeliveryResource(TermsofdeliveryRepository termsofdeliveryRepository) {
        this.termsofdeliveryRepository = termsofdeliveryRepository;
    }

    @GetMapping("/termsofdeliveries")
    @Timed
    public List<Termsofdelivery> getAllTermsofdelivery() {
        log.debug("REST request to get a page of termsofdeliveries");
        return termsofdeliveryRepository.findAll();
    }
}
