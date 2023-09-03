package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.LeaveTypeMaster;
import io.vamani.application.repository.LeaveTypeMasterRepository;
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
 * REST controller for managing LeaveTypeMaster.
 */
@RestController
@RequestMapping("/api")
public class LeaveTypeMasterResource {

    private final Logger log = LoggerFactory.getLogger(LeaveTypeMasterResource.class);

    private static final String ENTITY_NAME = "leaveTypeMaster";

    private final LeaveTypeMasterRepository leaveTypeMasterRepository;

    public LeaveTypeMasterResource(LeaveTypeMasterRepository leaveTypeMasterRepository) {
        this.leaveTypeMasterRepository = leaveTypeMasterRepository;
    }

    /**
     * POST  /leave-type-masters : Create a new leaveTypeMaster.
     *
     * @param leaveTypeMaster the leaveTypeMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new leaveTypeMaster, or with status 400 (Bad Request) if the leaveTypeMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/leave-type-masters")
    @Timed
    public ResponseEntity<LeaveTypeMaster> createLeaveTypeMaster(@Valid @RequestBody LeaveTypeMaster leaveTypeMaster) throws URISyntaxException {
        log.debug("REST request to save LeaveTypeMaster : {}", leaveTypeMaster);
        if (leaveTypeMaster.getId() != null) {
            throw new BadRequestAlertException("A new leaveTypeMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LeaveTypeMaster result = leaveTypeMasterRepository.save(leaveTypeMaster);
        return ResponseEntity.created(new URI("/api/leave-type-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /leave-type-masters : Updates an existing leaveTypeMaster.
     *
     * @param leaveTypeMaster the leaveTypeMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated leaveTypeMaster,
     * or with status 400 (Bad Request) if the leaveTypeMaster is not valid,
     * or with status 500 (Internal Server Error) if the leaveTypeMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/leave-type-masters")
    @Timed
    public ResponseEntity<LeaveTypeMaster> updateLeaveTypeMaster(@Valid @RequestBody LeaveTypeMaster leaveTypeMaster) throws URISyntaxException {
        log.debug("REST request to update LeaveTypeMaster : {}", leaveTypeMaster);
        if (leaveTypeMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LeaveTypeMaster result = leaveTypeMasterRepository.save(leaveTypeMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, leaveTypeMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /leave-type-masters : get all the leaveTypeMasters.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of leaveTypeMasters in body
     */
    @GetMapping("/leave-type-masters")
    @Timed
    public List<LeaveTypeMaster> getAllLeaveTypeMasters() {
        log.debug("REST request to get all LeaveTypeMasters");
        return leaveTypeMasterRepository.findAll();
    }

    /**
     * GET  /leave-type-masters : get all the leaveTypeMasters.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of leaveTypeMasters in body
     */
    @GetMapping("/leave-type-masters-type/{leaveType}")
    @Timed
    public List<LeaveTypeMaster> getAllLeaveTypeMastersByType(@PathVariable String leaveType) {
        log.debug("REST request to get all LeaveTypeMasters");
        return leaveTypeMasterRepository.findAllByLeaveType(leaveType);
    }

    /**
     * GET  /leave-type-masters/:id : get the "id" leaveTypeMaster.
     *
     * @param id the id of the leaveTypeMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the leaveTypeMaster, or with status 404 (Not Found)
     */
    @GetMapping("/leave-type-masters/{id}")
    @Timed
    public ResponseEntity<LeaveTypeMaster> getLeaveTypeMaster(@PathVariable Long id) {
        log.debug("REST request to get LeaveTypeMaster : {}", id);
        Optional<LeaveTypeMaster> leaveTypeMaster = leaveTypeMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(leaveTypeMaster);
    }

    /**
     * DELETE  /leave-type-masters/:id : delete the "id" leaveTypeMaster.
     *
     * @param id the id of the leaveTypeMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/leave-type-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteLeaveTypeMaster(@PathVariable Long id) {
        log.debug("REST request to delete LeaveTypeMaster : {}", id);

        leaveTypeMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
