package io.vamani.application.mssql.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.mssql.domain.Department;
import io.vamani.application.mssql.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing EmployeeView.
 */
@RestController
@RequestMapping("/api")
public class DepartmentResource {
    private final Logger log = LoggerFactory.getLogger(DepartmentResource.class);
    private static final String ENTITY_NAME = "department";

    private final DepartmentRepository departmentRepository;

    public DepartmentResource(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    /**
     * GET  /employee-views/:id : get the "id" employeeView.
     *
     * @param id the id of the shift to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the shift, or with status 404 (Not Found)
     */
    @GetMapping("/ms-departments")
    @Timed
    public ResponseEntity<List<Department>> getDepartment() {
        log.debug("REST request to get Department : {}");
        List<Department> departments = departmentRepository.findAll();
        return ResponseUtil.wrapOrNotFound(Optional.of(departments));
    }

}
