package io.vamani.application.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.vamani.application.domain.CutBundleLock;
import io.vamani.application.domain.CutBundleLockId;
import io.vamani.application.repository.CutBundleLockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

/**
 * REST controller for managing {@link com.iconnect.myapp.domain.CutBundleLock}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CutBundleLockResource {
    private final Logger log = LoggerFactory.getLogger(CutBundleEntryResource.class);

    private static final String ENTITY_NAME = "cutBundleLock";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CutBundleLockRepository cutBundleLockRepository;

    public CutBundleLockResource(CutBundleLockRepository cutBundleLockRepository) {
        this.cutBundleLockRepository = cutBundleLockRepository;
    }

    /**
     * {@code GET  /direct-booking-entries} : get all the directBookingEntries.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of directBookingEntries in body.
     */
    @GetMapping("/cut-bundle-locks")
    public ResponseEntity<List<CutBundleLock>> getAllCutBundleLocks(Pageable pageable) {
        log.debug("REST request to get a page of CutBundleLocks");
        Page<CutBundleLock> page = cutBundleLockRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code DELETE  /direct-booking-entries/:id} : delete the "id" directBookingEntry.
     *
     * @param id the id of the directBookingEntry to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/cut-bundle-locks-delete")
    public ResponseEntity<Void> deleteDirectBookingEntry(@RequestBody CutBundleLockId id) {
        log.debug("REST request to delete DirectBookingEntry : {}", id);
        cutBundleLockRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
