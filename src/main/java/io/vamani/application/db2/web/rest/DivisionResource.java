package io.vamani.application.db2.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.db2.domain.Division;
import io.vamani.application.db2.repository.DivisionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing Division.
 */
@RestController
@RequestMapping("/api")
public class DivisionResource {
    private final Logger log = LoggerFactory.getLogger(DivisionResource.class);

    private static final String ENTITY_NAME = "division";

    private final DivisionRepository divisionRepository;

    public DivisionResource(DivisionRepository divisionRepository) {
        this.divisionRepository = divisionRepository;
    }

    /**
     * GET  /company-masters/:id : get the "id" companyMaster.
     *
     * @param id the id of the companyMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the companyMaster, or with status 404 (Not Found)
     */
    @GetMapping("/db2-divisions/{companycode}")
    @Timed
    public List<Division> getDivisions(@PathVariable String companycode) {
        log.debug("REST request to get CompanyMaster : {}", companycode);
        return divisionRepository.findAllByCompanycode(companycode);
    }
}
