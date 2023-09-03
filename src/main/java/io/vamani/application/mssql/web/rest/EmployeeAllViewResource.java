package io.vamani.application.mssql.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.model.EmployeeSearch;
import io.vamani.application.mssql.domain.EmployeeAllView;
import io.vamani.application.mssql.repository.EmployeeAllViewRepository;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing EmployeeAllView.
 */
@RestController
@RequestMapping("/api")
public class EmployeeAllViewResource {

    private final Logger log = LoggerFactory.getLogger(EmployeeAllViewResource.class);

    private static final String ENTITY_NAME = "employeeAllView";

    private final EmployeeAllViewRepository employeeAllViewRepository;

    public EmployeeAllViewResource(EmployeeAllViewRepository employeeAllViewRepository) {
        this.employeeAllViewRepository = employeeAllViewRepository;
    }

    /**
     * POST  /employee-all-views : Create a new employeeAllView.
     *
     * @param employeeAllView the employeeAllView to create
     * @return the ResponseEntity with status 201 (Created) and with body the new employeeAllView, or with status 400 (Bad Request) if the employeeAllView has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/employee-all-views")
    @Timed
    public ResponseEntity<EmployeeAllView> createEmployeeAllView(@Valid @RequestBody EmployeeAllView employeeAllView) throws URISyntaxException {
        log.debug("REST request to save EmployeeAllView : {}", employeeAllView);
        if (employeeAllView.getLogin() != null) {
            throw new BadRequestAlertException("A new employeeAllView cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EmployeeAllView result = employeeAllViewRepository.save(employeeAllView);
        return ResponseEntity.created(new URI("/api/employee-all-views/" + result.getLogin()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getLogin().toString()))
            .body(result);
    }

    /**
     * PUT  /employee-all-views : Updates an existing employeeAllView.
     *
     * @param employeeAllView the employeeAllView to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated employeeAllView,
     * or with status 400 (Bad Request) if the employeeAllView is not valid,
     * or with status 500 (Internal Server Error) if the employeeAllView couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/employee-all-views")
    @Timed
    public ResponseEntity<EmployeeAllView> updateEmployeeAllView(@Valid @RequestBody EmployeeAllView employeeAllView) throws URISyntaxException {
        log.debug("REST request to update EmployeeAllView : {}", employeeAllView);
        if (employeeAllView.getLogin() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EmployeeAllView result = employeeAllViewRepository.save(employeeAllView);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, employeeAllView.getLogin().toString()))
            .body(result);
    }

    /**
     * GET  /employee-all-views : get all the employeeViews.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of employeeViews in body
     */
    @GetMapping("/employee-all-views")
    @Timed
    public ResponseEntity<List<EmployeeAllView>> getAllEmployeeAllViews(Pageable pageable) {
        log.debug("REST request to get a page of EmployeeAllViews");
        Page<EmployeeAllView> page = employeeAllViewRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/employee-all-views");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /employee-all-views/:id : get the "id" employeeAllView.
     *
     * @param id the id of the employeeAllView to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the employeeAllView, or with status 404 (Not Found)
     */
    @GetMapping("/employee-all-views/{id}")
    @Timed
    public ResponseEntity<EmployeeAllView> getEmployeeAllView(@PathVariable String id) {
        log.debug("REST request to get EmployeeAllView : {}", id);
        Optional<EmployeeAllView> employeeAllView = employeeAllViewRepository.findById(id.toUpperCase());
        return ResponseUtil.wrapOrNotFound(employeeAllView);
    }

    /**
     * DELETE  /employee-all-views/:id : delete the "id" employeeAllView.
     *
     * @param id the id of the employeeAllView to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/employee-all-views/{id}")
    @Timed
    public ResponseEntity<Void> deleteEmployeeAllView(@PathVariable String id) {
        log.debug("REST request to delete EmployeeAllView : {}", id);

        employeeAllViewRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    /**
     * POST  /employee-all-views : Create a new employeeAllView.
     *
     * @param employeeAllView the employeeAllView to create
     * @return the ResponseEntity with status 201 (Created) and with body the new employeeAllView, or with status 400 (Bad Request) if the employeeAllView has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/employee-all-views-custom")
    @Timed
    public ResponseEntity<List<EmployeeAllView>> getAllEmployeeAllView(@Valid @RequestBody EmployeeSearch search) {
        log.debug("REST request to get a page of EmployeeViews");
        String empCode = "%";
        String name = "%";
        String aadhar = "%";
        String panNo = "%";
        String mobileNo = "%";
        if (search.getEmpCode() != null) {
            empCode = search.getEmpCode() + "%";
        }
        if (search.getName() != null) {
            name = "%" + search.getName().toUpperCase() + "%";
        }
        if (search.getAadharNo() != null) {
        	aadhar = "%" + search.getAadharNo() + "%";
        }
        if (search.getPanNo() != null) {
        	panNo = "%" + search.getPanNo() + "%";
        }
        if (search.getMobileNo() != null) {
        	mobileNo = "%" + search.getMobileNo() + "%";
        }
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("name").ascending()));
        Page<EmployeeAllView> page;
        if(search.getStatus().equalsIgnoreCase("A")) {
        	page = employeeAllViewRepository.findAllByActive(empCode, name, aadhar, panNo,mobileNo,search.getPage());
        }else {
        	page = employeeAllViewRepository.findAllByResigned(empCode, name, aadhar, panNo,mobileNo,search.getPage());
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/employee-all-views-custom");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
