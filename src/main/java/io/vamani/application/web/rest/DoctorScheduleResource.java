package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.DoctorSchedule;
import io.vamani.application.mssql.domain.EmployeeView;
import io.vamani.application.mssql.repository.EmployeeViewRepository;
import io.vamani.application.repository.DoctorScheduleRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing DoctorSchedule.
 */
@RestController
@RequestMapping("/api")
public class DoctorScheduleResource {

    private final Logger log = LoggerFactory.getLogger(DoctorScheduleResource.class);

    private static final String ENTITY_NAME = "doctorSchedule";

    private final DoctorScheduleRepository doctorScheduleRepository;

    @Autowired
    private EmployeeViewRepository employeeViewRepository;

    public DoctorScheduleResource(DoctorScheduleRepository doctorScheduleRepository) {
        this.doctorScheduleRepository = doctorScheduleRepository;
    }

    /**
     * POST  /doctor-schedules : Create a new doctorSchedule.
     *
     * @param doctorSchedule the doctorSchedule to create
     * @return the ResponseEntity with status 201 (Created) and with body the new doctorSchedule, or with status 400 (Bad Request) if the doctorSchedule has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/doctor-schedules")
    @Timed
    public ResponseEntity<DoctorSchedule> createDoctorSchedule(@Valid @RequestBody DoctorSchedule doctorSchedule) throws URISyntaxException {
        log.debug("REST request to save DoctorSchedule : {}", doctorSchedule);
        if (doctorSchedule.getId() != null) {
            throw new BadRequestAlertException("A new doctorSchedule cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DoctorSchedule result = doctorScheduleRepository.save(doctorSchedule);
        return ResponseEntity.created(new URI("/api/doctor-schedules/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /doctor-schedules : Updates an existing doctorSchedule.
     *
     * @param doctorSchedule the doctorSchedule to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated doctorSchedule,
     * or with status 400 (Bad Request) if the doctorSchedule is not valid,
     * or with status 500 (Internal Server Error) if the doctorSchedule couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/doctor-schedules")
    @Timed
    public ResponseEntity<DoctorSchedule> updateDoctorSchedule(@Valid @RequestBody DoctorSchedule doctorSchedule) throws URISyntaxException {
        log.debug("REST request to update DoctorSchedule : {}", doctorSchedule);
        if (doctorSchedule.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DoctorSchedule result = doctorScheduleRepository.save(doctorSchedule);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, doctorSchedule.getId().toString()))
            .body(result);
    }

    /**
     * GET  /doctor-schedules : get all the doctorSchedules.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of doctorSchedules in body
     */
    @GetMapping("/doctor-schedules")
    @Timed
    public List<DoctorSchedule> getAllDoctorSchedules() {
        log.debug("REST request to get all DoctorSchedules");
        return doctorScheduleRepository.findAll();
    }

    /**
     * GET  /doctor-schedules/:id : get the "id" doctorSchedule.
     *
     * @param !id the id of the doctorSchedule to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the doctorSchedule, or with status 404 (Not Found)
     */
    @GetMapping("/doctor-schedules-unit")
    @Timed
    public ResponseEntity<DoctorSchedule> getDoctorScheduleByUnit() {
        log.debug("REST request to get DoctorSchedule : {}");
        EmployeeView employeeView = employeeViewRepository.findById(SecurityUtils.getCurrentUserLogin().orElse(null)).orElse(null);
        Optional<DoctorSchedule> doctorSchedule = doctorScheduleRepository.findByUnitId(Long.parseLong(employeeView.getFactory()));
        return ResponseUtil.wrapOrNotFound(doctorSchedule);
    }

    /**
     * GET  /doctor-schedules/:id : get the "id" doctorSchedule.
     *
     * @param id the id of the doctorSchedule to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the doctorSchedule, or with status 404 (Not Found)
     */
    @GetMapping("/doctor-schedules/{id}")
    @Timed
    public ResponseEntity<DoctorSchedule> getDoctorSchedule(@PathVariable Long id) {
        log.debug("REST request to get DoctorSchedule : {}", id);
        DoctorSchedule doctorSchedule = doctorScheduleRepository.findById(id).orElse(new DoctorSchedule());
        return ResponseUtil.wrapOrNotFound(Optional.of(doctorSchedule));
    }

    /**
     * DELETE  /doctor-schedules/:id : delete the "id" doctorSchedule.
     *
     * @param id the id of the doctorSchedule to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/doctor-schedules/{id}")
    @Timed
    public ResponseEntity<Void> deleteDoctorSchedule(@PathVariable Long id) {
        log.debug("REST request to delete DoctorSchedule : {}", id);

        doctorScheduleRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
