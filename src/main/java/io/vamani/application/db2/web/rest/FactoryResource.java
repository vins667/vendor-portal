package io.vamani.application.db2.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.PaginationUtil;
import io.vamani.application.db2.domain.Factory;
import io.vamani.application.db2.repository.FactoryRepository;
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
 * REST controller for managing {@link Factory}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class FactoryResource {
    private final Logger log = LoggerFactory.getLogger(FactoryResource.class);

    private static final String ENTITY_NAME = "factory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FactoryRepository factoryRepository;

    public FactoryResource(FactoryRepository factoryRepository) {
        this.factoryRepository = factoryRepository;
    }

    @GetMapping("/db2-factories-by-division/{divisioncode}")
    @Timed
    public List<Factory> getAllFactories(@PathVariable String divisioncode) {
        log.debug("REST request to get a page of factory");
        return factoryRepository.findAllByDivisioncode(divisioncode);
    }
}
