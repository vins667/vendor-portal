package io.vamani.application.db2.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.db2.domain.Termsofshipping;
import io.vamani.application.db2.repository.TermsofshippingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TermsofshippingResource {
    private final Logger log = LoggerFactory.getLogger(TermsofdeliveryResource.class);

    private static final String ENTITY_NAME = "termsofshipping";

    private final TermsofshippingRepository termsofshippingRepository;

    public TermsofshippingResource(TermsofshippingRepository termsofshippingRepository) {
        this.termsofshippingRepository = termsofshippingRepository;
    }

    @GetMapping("/termsofshippings")
    @Timed
    public List<Termsofshipping> getAllTermsofshipping() {
        log.debug("REST request to get a page of termsofshippings");
        return termsofshippingRepository.findAll();
    }
}
