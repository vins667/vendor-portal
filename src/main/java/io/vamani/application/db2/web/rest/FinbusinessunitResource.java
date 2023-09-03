package io.vamani.application.db2.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.db2.domain.Finbusinessunit;
import io.vamani.application.db2.repository.FinbusinessunitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * REST controller for managing Finbusinessunit.
 */
@RestController
@RequestMapping("/api")
public class FinbusinessunitResource {

    private final Logger log = LoggerFactory.getLogger(FinbusinessunitResource.class);

    private static final String ENTITY_NAME = "finbusinessunit";

    private final FinbusinessunitRepository finbusinessunitRepository;

    public FinbusinessunitResource(FinbusinessunitRepository finbusinessunitRepository) {
        this.finbusinessunitRepository = finbusinessunitRepository;
    }

    /**
     * GET  /company-masters/:id : get the "id" companyMaster.
     *
     * @param id the id of the companyMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the companyMaster, or with status 404 (Not Found)
     */
    @GetMapping("/db2-finbusinessunits/{companycode}")
    @Timed
    public List<Finbusinessunit> getFinbusinessunits(@PathVariable String companycode) {
        log.debug("REST request to get CompanyMaster : {}", companycode);
        return finbusinessunitRepository.findAllByCompanycode(companycode);
    }
}
