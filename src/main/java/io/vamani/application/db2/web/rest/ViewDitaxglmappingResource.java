package io.vamani.application.db2.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.PaginationUtil;
import io.vamani.application.db2.domain.ViewDitaxglmapping;
import io.vamani.application.db2.repository.ViewDitaxglmappingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

/**
 * REST controller for managing {@link ViewDitaxglmapping}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ViewDitaxglmappingResource {
    private final Logger log = LoggerFactory.getLogger(ViewDitaxglmappingResource.class);

    private static final String ENTITY_NAME = "viewDitaxglmapping";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ViewDitaxglmappingRepository viewDitaxglmappingRepository;

    public ViewDitaxglmappingResource(ViewDitaxglmappingRepository viewDitaxglmappingRepository) {
        this.viewDitaxglmappingRepository = viewDitaxglmappingRepository;
    }

    @GetMapping("/viewditaxglmappings/{taxcode}/{longdescription}")
    @Timed
    public ResponseEntity<List<ViewDitaxglmapping>> getAllEstprdemployeedetails(@PathVariable String taxcode, @PathVariable String longdescription) {
        log.debug("REST request to get a page of viewDitaxglmapping");
        Pageable pageable = PageRequest.of(0, 50, Sort.by("longdescription").ascending());
        Page<ViewDitaxglmapping> page = viewDitaxglmappingRepository.findAllByLongdescriptionIgnoreCaseLike(taxcode+'%', longdescription.toUpperCase() + "%", pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
