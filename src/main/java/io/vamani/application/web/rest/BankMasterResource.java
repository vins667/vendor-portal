package io.vamani.application.web.rest;
import io.vamani.application.domain.BankMaster;
import io.vamani.application.repository.BankMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
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
 * REST controller for managing BankMaster.
 */
@RestController
@RequestMapping("/api")
public class BankMasterResource {

    private final Logger log = LoggerFactory.getLogger(BankMasterResource.class);

    private static final String ENTITY_NAME = "bankMaster";

    private final BankMasterRepository bankMasterRepository;

    public BankMasterResource(BankMasterRepository bankMasterRepository) {
        this.bankMasterRepository = bankMasterRepository;
    }

    /**
     * POST  /bank-masters : Create a new bankMaster.
     *
     * @param bankMaster the bankMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bankMaster, or with status 400 (Bad Request) if the bankMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/bank-masters")
    public ResponseEntity<BankMaster> createBankMaster(@Valid @RequestBody BankMaster bankMaster) throws URISyntaxException {
        log.debug("REST request to save BankMaster : {}", bankMaster);
        if (bankMaster.getId() != null) {
            throw new BadRequestAlertException("A new bankMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        bankMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        bankMaster.setCreatedDate(Instant.now());
        BankMaster result = bankMasterRepository.save(bankMaster);
        return ResponseEntity.created(new URI("/api/bank-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /bank-masters : Updates an existing bankMaster.
     *
     * @param bankMaster the bankMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bankMaster,
     * or with status 400 (Bad Request) if the bankMaster is not valid,
     * or with status 500 (Internal Server Error) if the bankMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/bank-masters")
    public ResponseEntity<BankMaster> updateBankMaster(@Valid @RequestBody BankMaster bankMaster) throws URISyntaxException {
        log.debug("REST request to update BankMaster : {}", bankMaster);
        if (bankMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        bankMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        bankMaster.setLastUpdatedDate(Instant.now());
        BankMaster result = bankMasterRepository.save(bankMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, bankMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /bank-masters : get all the bankMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of bankMasters in body
     */
    @GetMapping("/bank-masters")
    public ResponseEntity<List<BankMaster>> getAllBankMasters(@PageableDefault(value = Integer.MAX_VALUE)  @SortDefault(sort = "bankName", direction = Sort.Direction.ASC) Pageable pageable) {
        log.debug("REST request to get a page of BankMasters");
        Page<BankMaster> page = bankMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/bank-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /bank-masters/:id : get the "id" bankMaster.
     *
     * @param id the id of the bankMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bankMaster, or with status 404 (Not Found)
     */
    @GetMapping("/bank-masters/{id}")
    public ResponseEntity<BankMaster> getBankMaster(@PathVariable Long id) {
        log.debug("REST request to get BankMaster : {}", id);
        Optional<BankMaster> bankMaster = bankMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(bankMaster);
    }

    /**
     * DELETE  /bank-masters/:id : delete the "id" bankMaster.
     *
     * @param id the id of the bankMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/bank-masters/{id}")
    public ResponseEntity<Void> deleteBankMaster(@PathVariable Long id) {
        log.debug("REST request to delete BankMaster : {}", id);
        bankMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
