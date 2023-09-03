package io.vamani.application.db2.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.db2.domain.Company;
import io.vamani.application.db2.repository.CompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing Company.
 */
@RestController
@RequestMapping("/api")
public class CompanyResource {
    private final Logger log = LoggerFactory.getLogger(CompanyResource.class);

    private static final String ENTITY_NAME = "company";

    private final CompanyRepository companyRepository;

    public CompanyResource(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @GetMapping("/db2-companies")
    @Timed
    public List<Company> getAllCompanies() {
        log.debug("REST request to get all Companies");
        return companyRepository.findAll();
    }
}
