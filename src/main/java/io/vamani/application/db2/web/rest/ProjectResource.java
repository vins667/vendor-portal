package io.vamani.application.db2.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.PaginationUtil;
import io.vamani.application.db2.domain.Project;
import io.vamani.application.db2.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * REST controller for managing {@link Project}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ProjectResource {
    private final Logger log = LoggerFactory.getLogger(ProjectResource.class);

    private static final String ENTITY_NAME = "project";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProjectRepository projectRepository;

    public ProjectResource(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping("/projects/{projectcode}")
    @Timed
    public ResponseEntity<List<Project>> getAllEstprdemployeedetails(@PathVariable String projectcode) throws UnsupportedEncodingException {
        log.debug("REST request to get a page of project");
        Pageable pageable = PageRequest.of(0, 1000, Sort.by("id.code").ascending());
        projectcode = URLDecoder.decode(projectcode, "UTF-8");
        Page<Project> page = projectRepository.findAllByProjectIgnoreCaseLike("%" + projectcode.toUpperCase() + "%", pageable);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
