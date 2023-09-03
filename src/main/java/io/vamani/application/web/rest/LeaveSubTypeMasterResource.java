package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.LeaveSubTypeMaster;
import io.vamani.application.repository.LeaveSubTypeMasterRepository;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing LeaveSubTypeMaster.
 */
@RestController
@RequestMapping("/api")
public class LeaveSubTypeMasterResource {

    private final Logger log = LoggerFactory.getLogger(LeaveSubTypeMasterResource.class);

    private static final String ENTITY_NAME = "leaveSubTypeMaster";

    private final LeaveSubTypeMasterRepository leaveSubTypeMasterRepository;

    public LeaveSubTypeMasterResource(LeaveSubTypeMasterRepository leaveSubTypeMasterRepository) {
        this.leaveSubTypeMasterRepository = leaveSubTypeMasterRepository;
    }

    /**
     * POST  /leave-sub-type-masters : Create a new leaveSubTypeMaster.
     *
     * @param leaveSubTypeMaster the leaveSubTypeMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new leaveSubTypeMaster, or with status 400 (Bad Request) if the leaveSubTypeMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/leave-sub-type-masters")
    @Timed
    public ResponseEntity<LeaveSubTypeMaster> createLeaveSubTypeMaster(@Valid @RequestBody LeaveSubTypeMaster leaveSubTypeMaster) throws URISyntaxException {
        log.debug("REST request to save LeaveSubTypeMaster : {}", leaveSubTypeMaster);
        if (leaveSubTypeMaster.getId() != null) {
            throw new BadRequestAlertException("A new leaveSubTypeMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LeaveSubTypeMaster result = leaveSubTypeMasterRepository.save(leaveSubTypeMaster);
        return ResponseEntity.created(new URI("/api/leave-sub-type-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /leave-sub-type-masters : Updates an existing leaveSubTypeMaster.
     *
     * @param leaveSubTypeMaster the leaveSubTypeMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated leaveSubTypeMaster,
     * or with status 400 (Bad Request) if the leaveSubTypeMaster is not valid,
     * or with status 500 (Internal Server Error) if the leaveSubTypeMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/leave-sub-type-masters")
    @Timed
    public ResponseEntity<LeaveSubTypeMaster> updateLeaveSubTypeMaster(@Valid @RequestBody LeaveSubTypeMaster leaveSubTypeMaster) throws URISyntaxException {
        log.debug("REST request to update LeaveSubTypeMaster : {}", leaveSubTypeMaster);
        if (leaveSubTypeMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LeaveSubTypeMaster result = leaveSubTypeMasterRepository.save(leaveSubTypeMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, leaveSubTypeMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /leave-sub-type-masters : get all the leaveSubTypeMasters.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of leaveSubTypeMasters in body
     */
    @GetMapping("/leave-sub-type-masters")
    @Timed
    public List<LeaveSubTypeMaster> getAllLeaveSubTypeMasters() {
        log.debug("REST request to get all LeaveSubTypeMasters");
        return leaveSubTypeMasterRepository.findAll();
    }

    /**
     * GET  /leave-sub-type-masters : get all the leaveSubTypeMasters.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of leaveSubTypeMasters in body
     */
    @GetMapping("/leave-sub-type-masters-by-leave/{id}")
    @Timed
    public List<LeaveSubTypeMaster> getAllLeaveSubTypeMastersByLeaveMaster(@PathVariable Long id) {
        log.debug("REST request to get all LeaveSubTypeMasters");
        return leaveSubTypeMasterRepository.findByLeaveTypeMasterId(id);
    }

    /**
     * GET  /leave-sub-type-masters/:id : get the "id" leaveSubTypeMaster.
     *
     * @param id the id of the leaveSubTypeMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the leaveSubTypeMaster, or with status 404 (Not Found)
     */
    @GetMapping("/leave-sub-type-masters/{id}")
    @Timed
    public ResponseEntity<LeaveSubTypeMaster> getLeaveSubTypeMaster(@PathVariable Long id) {
        log.debug("REST request to get LeaveSubTypeMaster : {}", id);
        Optional<LeaveSubTypeMaster> leaveSubTypeMaster = leaveSubTypeMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(leaveSubTypeMaster);
    }

    /**
     * DELETE  /leave-sub-type-masters/:id : delete the "id" leaveSubTypeMaster.
     *
     * @param id the id of the leaveSubTypeMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/leave-sub-type-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteLeaveSubTypeMaster(@PathVariable Long id) {
        log.debug("REST request to delete LeaveSubTypeMaster : {}", id);

        leaveSubTypeMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
