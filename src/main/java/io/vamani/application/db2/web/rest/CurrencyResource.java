package io.vamani.application.db2.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.db2.domain.Currency;
import io.vamani.application.db2.repository.CurrencyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing Currency.
 */
@RestController
@RequestMapping("/api")
public class CurrencyResource {
    private final Logger log = LoggerFactory.getLogger(CurrencyResource.class);

    private static final String ENTITY_NAME = "currency";

    private final CurrencyRepository currencyRepository;

    public CurrencyResource(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    /**
     * GET  /company-masters/:id : get the "id" companyMaster.
     *
     * @param id the id of the companyMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the companyMaster, or with status 404 (Not Found)
     */
    @GetMapping("/db2-currencies")
    @Timed
    public List<Currency> getCurrencies() {
        return currencyRepository.findAll();
    }
}
