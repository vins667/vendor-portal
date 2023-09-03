package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.MobileVersion;
import io.vamani.application.model.Message;
import io.vamani.application.repository.MobileVersionRepository;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing MobileVersion.
 */
@RestController
@RequestMapping("/api")
public class MobileVersionResource {

    private final Logger log = LoggerFactory.getLogger(MobileVersionResource.class);

    private static final String ENTITY_NAME = "mobileVersion";

    private final MobileVersionRepository mobileVersionRepository;

    public MobileVersionResource(MobileVersionRepository mobileVersionRepository) {
        this.mobileVersionRepository = mobileVersionRepository;
    }

    /**
     * POST  /mobile-versions : Create a new mobileVersion.
     *
     * @param mobileVersion the mobileVersion to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mobileVersion, or with status 400 (Bad Request) if the mobileVersion has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/mobile-versions")
    @Timed
    public ResponseEntity<MobileVersion> createMobileVersion(@Valid @RequestBody MobileVersion mobileVersion) throws URISyntaxException {
        log.debug("REST request to save MobileVersion : {}", mobileVersion);
        if (mobileVersion.getId() != null) {
            throw new BadRequestAlertException("A new mobileVersion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        if (mobileVersion.getMobileType().equalsIgnoreCase("A")) {
            MobileVersion mobileVersion1 = mobileVersionRepository.findLatestAndroidVersion();
            mobileVersion1.setClosedDate(Instant.now());
            mobileVersionRepository.save(mobileVersion1);
        } else {
            MobileVersion mobileVersion1 = mobileVersionRepository.findLatestIphoneVersion();
            mobileVersion1.setClosedDate(Instant.now());
            mobileVersionRepository.save(mobileVersion1);
        }
        mobileVersion.createdDate(Instant.now());
        MobileVersion result = mobileVersionRepository.save(mobileVersion);
        return ResponseEntity.created(new URI("/api/mobile-versions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /mobile-versions : Updates an existing mobileVersion.
     *
     * @param mobileVersion the mobileVersion to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated mobileVersion,
     * or with status 400 (Bad Request) if the mobileVersion is not valid,
     * or with status 500 (Internal Server Error) if the mobileVersion couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/mobile-versions")
    @Timed
    public ResponseEntity<MobileVersion> updateMobileVersion(@Valid @RequestBody MobileVersion mobileVersion) throws URISyntaxException {
        log.debug("REST request to update MobileVersion : {}", mobileVersion);
        if (mobileVersion.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MobileVersion result = mobileVersionRepository.save(mobileVersion);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, mobileVersion.getId().toString()))
            .body(result);
    }

    /**
     * GET  /mobile-versions : get all the mobileVersions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of mobileVersions in body
     */
    @GetMapping("/mobile-versions")
    @Timed
    public ResponseEntity<List<MobileVersion>> getAllMobileVersions(Pageable pageable) {
        log.debug("REST request to get a page of MobileVersions");
        Page<MobileVersion> page = mobileVersionRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/mobile-versions");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /mobile-versions/:id : get the "id" mobileVersion.
     *
     * @param id the id of the mobileVersion to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the mobileVersion, or with status 404 (Not Found)
     */
    @GetMapping("/mobile-versions/{id}")
    @Timed
    public ResponseEntity<MobileVersion> getMobileVersion(@PathVariable Long id) {
        log.debug("REST request to get MobileVersion : {}", id);
        Optional<MobileVersion> mobileVersion = mobileVersionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(mobileVersion);
    }

    /**
     * GET  /mobile-versions/:id : get the "id" mobileVersion.
     *
     * @param id the id of the mobileVersion to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the mobileVersion, or with status 404 (Not Found)
     */
    @GetMapping("/current-android-version")
    @Timed
    public ResponseEntity<Message> getMobileVersion() {
        MobileVersion mobileVersion = mobileVersionRepository.findLatestAndroidVersion();
        if(mobileVersion != null) {
            return ResponseUtil.wrapOrNotFound(Optional.of(new Message(mobileVersion.getVersion(), "Android", true, "")));
        } else {
            return ResponseUtil.wrapOrNotFound(Optional.of(new Message("No Version Exist", "Android", false, "No Version Exist")));
        }
    }

    /**
     * DELETE  /mobile-versions/:id : delete the "id" mobileVersion.
     *
     * @param id the id of the mobileVersion to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/mobile-versions/{id}")
    @Timed
    public ResponseEntity<Void> deleteMobileVersion(@PathVariable Long id) {
        log.debug("REST request to delete MobileVersion : {}", id);

        mobileVersionRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
