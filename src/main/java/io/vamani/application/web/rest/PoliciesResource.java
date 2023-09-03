package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.domain.Policies;
import io.vamani.application.domain.PoliciesGroup;
import io.vamani.application.model.Message;
import io.vamani.application.repository.PoliciesRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.util.MD5UrlEncryption;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Policies.
 */
@RestController
@RequestMapping("/api")
public class PoliciesResource {

    private final Logger log = LoggerFactory.getLogger(PoliciesResource.class);

    private static final String ENTITY_NAME = "policies";

    private final PoliciesRepository policiesRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

    public PoliciesResource(PoliciesRepository policiesRepository) {
        this.policiesRepository = policiesRepository;
    }

    /**
     * POST  /policies : Create a new policies.
     *
     * @param policies the policies to create
     * @return the ResponseEntity with status 201 (Created) and with body the new policies, or with status 400 (Bad Request) if the policies has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping(value = "/policies", consumes = {"multipart/form-data"})
    @Timed
    public ResponseEntity<Policies> createPolicies(@RequestParam(required = false) MultipartFile file, Long id, String policyName, Long policyGroupId) throws URISyntaxException, IOException {
        log.debug("REST request to save Policies : {}", id);
        if (id != null) {
            throw new BadRequestAlertException("A new policies cannot already have an ID", ENTITY_NAME, "idexists");
        }
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        Policies policies = new Policies();
        policies.setPolicyName(policyName.toUpperCase());
        policies.setPolicyFile("DEMO.pdf");
        policies.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        policies.setCreatedDate(Instant.now());
        PoliciesGroup policiesGroup = new PoliciesGroup();
        policiesGroup.setId(policyGroupId);
        policies.setPoliciesGroup(policiesGroup);
        Policies result = policiesRepository.save(policies);

        String extn = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
        String fileName = result.getId() + extn;
        result.setPolicyFile(fileName);
        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOADED_FOLDER + "policies/" + fileName);
        Files.write(path, bytes);
        result = policiesRepository.save(result);
        return ResponseEntity.created(new URI("/api/policies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /policies : Updates an existing policies.
     *
     * @param policies the policies to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated policies,
     * or with status 400 (Bad Request) if the policies is not valid,
     * or with status 500 (Internal Server Error) if the policies couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping(value = "/policies-update", consumes = {"multipart/form-data"})
    @Timed
    public ResponseEntity<Policies> updatePolicies(@RequestParam(required = false) MultipartFile file, Long id, String policyName, Long policyGroupId) throws URISyntaxException, IOException {
        log.debug("REST request to update Policies : {}", id);
        if (id == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        Policies policies = policiesRepository.findById(new Long(id)).orElse(null);
        String extn = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
        String fileName = policies.getId() + extn;
        policies.setPolicyFile(fileName);
        policies.setPolicyName(policyName.toUpperCase());
        PoliciesGroup policiesGroup = new PoliciesGroup();
        policiesGroup.setId(policyGroupId);
        policies.setPoliciesGroup(policiesGroup);
        policies.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        policies.setCreatedDate(Instant.now());
        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOADED_FOLDER + "policies/" + fileName);
        Files.write(path, bytes);
        Policies result = policiesRepository.save(policies);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, policies.getId().toString()))
            .body(result);
    }

    /**
     * GET  /policies : get all the policies.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of policies in body
     */
    @GetMapping("/policies-ordered")
    @Timed
    public ResponseEntity<List<Policies>> getAllPoliciesOrder() {
        log.debug("REST request to get a page of Policies");
        List<Policies> page = policiesRepository.findAllBySort();
        List<Policies> policies = new ArrayList<>();
        for (Policies policy : page) {
            try {
                policy.setPolicyFile(MD5UrlEncryption.fakeUploadUrl("policies/" + policy.getPolicyFile()));
            } catch (Exception e) {

            }
            policies.add(policy);
        }
        return ResponseEntity.ok().body(policies);
    }

    /**
     * GET  /policies : get all the policies.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of policies in body
     */
    @GetMapping("/policies")
    @Timed
    public ResponseEntity<List<Policies>> getAllPolicies(@PageableDefault(value = Integer.MAX_VALUE) Pageable pageable) {
        log.debug("REST request to get a page of Policies");
        Page<Policies> page = policiesRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/policies");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /policies/:id : get the "id" policies.
     *
     * @param id the id of the policies to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the policies, or with status 404 (Not Found)
     */
    @GetMapping("/policies/{id}")
    @Timed
    public ResponseEntity<Policies> getPolicies(@PathVariable Long id) {
        log.debug("REST request to get Policies : {}", id);
        Optional<Policies> policies = policiesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(policies);
    }

    /**
     * PUT  /policies : Updates an existing policies.
     *
     * @param policies the policies to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated policies,
     * or with status 400 (Bad Request) if the policies is not valid,
     * or with status 500 (Internal Server Error) if the policies couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/policies-multiple")
    @Timed
    public ResponseEntity<Message> updateMultplePolicies(@Valid @RequestBody Policies[] policies) throws URISyntaxException {
        log.debug("REST request to update JobProfile : {}", policies);
        for (Policies policy : policies) {
            Policies result = policiesRepository.save(policy);
        }
        return ResponseEntity.ok().body(new Message("success", "Save Successfully!!!"));
    }
    /**
     * GET  /policies/:id : get the "id" policies.
     *
     * @param id the id of the policies to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the policies, or with status 404 (Not Found)
     */
    @GetMapping("/policies-by-group/{id}")
    @Timed
    public List<Policies> getPoliciesByGroup(@PathVariable Long id) {
        log.debug("REST request to get Policies : {}", id);
        List<Policies> policies = policiesRepository.findAllByPolicyGroup(id);
        return policies;
    }

    /**
     * DELETE  /policies/:id : delete the "id" policies.
     *
     * @param id the id of the policies to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/policies/{id}")
    @Timed
    public ResponseEntity<Void> deletePolicies(@PathVariable Long id) {
        log.debug("REST request to delete Policies : {}", id);

        policiesRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /feedbacks/:id : get the "id" feedback.
     *
     * @param id the id of the feedback to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the feedback, or with status 404 (Not Found)
     */
    @GetMapping("/policies-download/{id}")
    @Timed
    public ResponseEntity<Object> getPoliciesDownload(@PathVariable Long id) throws FileNotFoundException, IOException {
        log.debug("REST request to get Policies : {}", id);
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        Policies policies = policiesRepository.findById(id).orElse(null);;
        File file = new File(UPLOADED_FOLDER + "policies/"+policies.getPolicyFile());
        Path path = Paths.get(UPLOADED_FOLDER + "policies/" + policies.getPolicyFile());
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        String mimeType = Files.probeContentType(path);
        ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType(mimeType)).body(resource);
        return responseEntity;
    }
}
