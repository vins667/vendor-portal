package io.vamani.application.mssql.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.mssql.domain.Subcomp;
import io.vamani.application.mssql.repository.SubcompRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing EmployeeView.
 */
@RestController
@RequestMapping("/api")
public class SubcompResource {
    private final Logger log = LoggerFactory.getLogger(SubcompResource.class);
    private static final String ENTITY_NAME = "subcomp";

    private final SubcompRepository subcompRepository;

    public SubcompResource(SubcompRepository subcompRepository) {
        this.subcompRepository = subcompRepository;
    }

    /**
     * GET  /employee-views/:id : get the "id" employeeView.
     *
     * @param id the id of the shift to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the shift, or with status 404 (Not Found)
     */
    @GetMapping("/subcomp")
    @Timed
    public ResponseEntity<List<Subcomp>> getSubcomp(@PathVariable String companyCode) {
        log.debug("REST request to get Subcomp : {}", companyCode);
        List<Subcomp> subcomps = subcompRepository.findAll();
        return ResponseUtil.wrapOrNotFound(Optional.of(subcomps));
    }

}
