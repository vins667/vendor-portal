package io.vamani.application.vendorportal.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.vendorportal.domain.TransactionNature;
import io.vamani.application.vendorportal.repository.TransactionNatureRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing TransactionNature.
 */
@RestController
@RequestMapping("/api")
public class TransactionNatureResource {

    private final Logger log = LoggerFactory.getLogger(TransactionNatureResource.class);

    private static final String ENTITY_NAME = "transactionNature";

    private final TransactionNatureRepository transactionNatureRepository;

    public TransactionNatureResource(TransactionNatureRepository transactionNatureRepository) {
        this.transactionNatureRepository = transactionNatureRepository;
    }

    /**
     * POST  /transaction-natures : Create a new transactionNature.
     *
     * @param transactionNature the transactionNature to create
     * @return the ResponseEntity with status 201 (Created) and with body the new transactionNature, or with status 400 (Bad Request) if the transactionNature has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/transaction-natures")
    @Timed
    public ResponseEntity<TransactionNature> createTransactionNature(@Valid @RequestBody TransactionNature transactionNature) throws URISyntaxException {
        log.debug("REST request to save TransactionNature : {}", transactionNature);
        if (transactionNature.getId() != null) {
            throw new BadRequestAlertException("A new transactionNature cannot already have an ID", ENTITY_NAME, "idexists");
        }
        transactionNature.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        transactionNature.setCreatedDate(Instant.now());
        TransactionNature result = transactionNatureRepository.save(transactionNature);
        return ResponseEntity.created(new URI("/api/transaction-natures/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /transaction-natures : Updates an existing transactionNature.
     *
     * @param transactionNature the transactionNature to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated transactionNature,
     * or with status 400 (Bad Request) if the transactionNature is not valid,
     * or with status 500 (Internal Server Error) if the transactionNature couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/transaction-natures")
    @Timed
    public ResponseEntity<TransactionNature> updateTransactionNature(@Valid @RequestBody TransactionNature transactionNature) throws URISyntaxException {
        log.debug("REST request to update TransactionNature : {}", transactionNature);
        if (transactionNature.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        transactionNature.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        transactionNature.setLastUpdatedDate(Instant.now());
        TransactionNature result = transactionNatureRepository.save(transactionNature);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, transactionNature.getId().toString()))
            .body(result);
    }

    /**
     * GET  /transaction-natures : get all the transactionNatures.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of transactionNatures in body
     */
    @GetMapping("/transaction-natures")
    @Timed
    public ResponseEntity<List<TransactionNature>> getAllTransactionNatures(@PageableDefault(sort = { "description" }, value = Integer.MAX_VALUE) Pageable pageable) {
        log.debug("REST request to get a page of TransactionNatures");
        Page<TransactionNature> page = transactionNatureRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/transaction-natures");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /transaction-natures/:id : get the "id" transactionNature.
     *
     * @param id the id of the transactionNature to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the transactionNature, or with status 404 (Not Found)
     */
    @GetMapping("/transaction-natures/{id}")
    @Timed
    public ResponseEntity<TransactionNature> getTransactionNature(@PathVariable Long id) {
        log.debug("REST request to get TransactionNature : {}", id);
        Optional<TransactionNature> transactionNature = transactionNatureRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(transactionNature);
    }

    /**
     * DELETE  /transaction-natures/:id : delete the "id" transactionNature.
     *
     * @param id the id of the transactionNature to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/transaction-natures/{id}")
    @Timed
    public ResponseEntity<Void> deleteTransactionNature(@PathVariable Long id) {
        log.debug("REST request to delete TransactionNature : {}", id);

        transactionNatureRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
