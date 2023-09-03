package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.domain.JobProfile;
import io.vamani.application.domain.JobProfileAccess;
import io.vamani.application.model.JobProfileBean;
import io.vamani.application.model.JobProfileSearch;
import io.vamani.application.model.Master;
import io.vamani.application.model.Message;
import io.vamani.application.mssql.domain.EmployeeView;
import io.vamani.application.mssql.repository.EmployeeViewRepository;
import io.vamani.application.repository.JobProfileAccessRepository;
import io.vamani.application.repository.JobProfileRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.util.MD5UrlEncryption;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
 * REST controller for managing JobProfile.
 */
@RestController
@RequestMapping("/api")
public class JobProfileResource {

    private final Logger log = LoggerFactory.getLogger(JobProfileResource.class);

    private static final String ENTITY_NAME = "jobProfile";

    private final JobProfileRepository jobProfileRepository;

    @Autowired
    private JobProfileAccessRepository jobProfileAccessRepository;

    @Autowired
    private EmployeeViewRepository employeeViewRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

    public JobProfileResource(JobProfileRepository jobProfileRepository) {
        this.jobProfileRepository = jobProfileRepository;
    }

    /**
     * POST  /job-profiles : Create a new jobProfile.
     *
     * @param jobProfile the jobProfile to create
     * @return the ResponseEntity with status 201 (Created) and with body the new jobProfile, or with status 400 (Bad Request) if the jobProfile has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/job-profiles")
    @Timed
    public ResponseEntity<JobProfile> createJobProfile(@Valid @RequestBody JobProfile jobProfile) throws URISyntaxException {
        log.debug("REST request to save JobProfile : {}", jobProfile);
        if (jobProfile.getId() != null) {
            throw new BadRequestAlertException("A new jobProfile cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JobProfile result = jobProfileRepository.save(jobProfile);
        return ResponseEntity.created(new URI("/api/job-profiles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /job-profiles : Updates an existing jobProfile.
     *
     * @param jobProfile the jobProfile to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated jobProfile,
     * or with status 400 (Bad Request) if the jobProfile is not valid,
     * or with status 500 (Internal Server Error) if the jobProfile couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/job-profiles")
    @Timed
    public ResponseEntity<JobProfile> updateJobProfile(@Valid @RequestBody JobProfile jobProfile) throws URISyntaxException {
        log.debug("REST request to update JobProfile : {}", jobProfile);
        if (jobProfile.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        JobProfile result = jobProfileRepository.save(jobProfile);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, jobProfile.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /job-profiles : Updates an existing jobProfile.
     *
     * @param jobProfile the jobProfile to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated jobProfile,
     * or with status 400 (Bad Request) if the jobProfile is not valid,
     * or with status 500 (Internal Server Error) if the jobProfile couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/job-profiles-multiple")
    @Timed
    public ResponseEntity<Message> updateMultpleJobProfile(@Valid @RequestBody JobProfile[] jobProfiles) throws URISyntaxException {
        log.debug("REST request to update JobProfile : {}", jobProfiles);
        for (JobProfile jobProfile : jobProfiles) {
            JobProfile result = jobProfileRepository.save(jobProfile);
        }
        return ResponseEntity.ok().body(new Message("success", "Save Successfully!!!"));
    }

    /**
     * GET  /job-profiles : get all the jobProfiles.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of jobProfiles in body
     */
    @PostMapping("/job-profiles-custom")
    @Timed
    public ResponseEntity<List<JobProfile>> getAllJobProfiles(@Valid @RequestBody JobProfileSearch search, Pageable pageable) {
        log.debug("REST request to get a page of JobProfiles");
        EmployeeView employeeView = employeeViewRepository.findById(SecurityUtils.getCurrentUserLogin().orElse(null)).orElse(null);
        String department = "%";
        if (search.getDepartment() != null) {
            department = search.getDepartment() + "%";
        }
        String designation = "%";
        if (search.getDesignation() != null) {
            designation = search.getDesignation() + "%";
        }
        Page<JobProfile> page = null;
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("id").ascending()));
        if(search.getStatus() != null && search.getStatus().equalsIgnoreCase("P")) {
            page = jobProfileRepository.findAllPending(employeeView.getFactory(), department, designation, search.getPage());
        } else {
            page = jobProfileRepository.findAllClosed(employeeView.getFactory(), department, designation, search.getPage());
        }
        List<JobProfile> jobProfiles = new ArrayList<>();
        for (JobProfile profile : jobProfiles) {
            try {
                profile.setFileName(MD5UrlEncryption.fakeUploadUrl("profile/" + profile.getFileName()));
            } catch (Exception e) {
            }
            jobProfiles.add(profile);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/job-profiles");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /job-profiles/:id : get the "id" jobProfile.
     *
     * @param id the id of the jobProfile to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the jobProfile, or with status 404 (Not Found)
     */
    @GetMapping("/job-profiles-department")
    @Timed
    public List<Master> getJobProfileDepartment() {
        log.debug("REST request to get JobProfile : {}");
        List<Object[]> jobProfiles = jobProfileRepository.getDistinctByDepartmentDesc();
        List<Master> masters = new ArrayList<>();
        for(Object[] objects : jobProfiles) {
            masters.add(new Master(objects[0].toString(), objects[1].toString()));
        }
        return masters;
        // return ResponseUtil.wrapOrNotFound(jobProfile);
    }

    /**
     * GET  /job-profiles/:id : get the "id" jobProfile.
     *
     * @param id the id of the jobProfile to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the jobProfile, or with status 404 (Not Found)
     */
    @GetMapping("/job-profiles/{id}")
    @Timed
    public ResponseEntity<List<JobProfile>> getJobProfile(@PathVariable String id) {
        log.debug("REST request to get JobProfile : {}", id);
        EmployeeView employeeView = employeeViewRepository.findById(SecurityUtils.getCurrentUserLogin().orElse(null)).orElse(null);
        List<JobProfile> jobProfile = jobProfileRepository.findByDepartment(employeeView.getFactory(), id + "");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccessControlAllowCredentials(true);
        headers.setAccessControlAllowOrigin("*");
        headers.setAccessControlMaxAge(1800);
        List<JobProfile> jobProfiles = new ArrayList<>();
        for(JobProfile profile : jobProfile) {
            try {
                profile.setFileName(MD5UrlEncryption.fakeUploadUrl("profile/" + profile.getFileName()));
            } catch (Exception e) {
            }
            jobProfiles.add(profile);
        }
        return ResponseEntity.ok().headers(headers).body(jobProfiles);
        // return ResponseUtil.wrapOrNotFound(jobProfile);
    }

    /**
     * GET  /job-profiles/:id : get the "id" jobProfile.
     *
     * @param id the id of the jobProfile to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the jobProfile, or with status 404 (Not Found)
     */
    @GetMapping("/job-profiles-designation/{id}")
    @Timed
    public ResponseEntity<List<JobProfileBean>> getJobProfileDesignation(@PathVariable String id) {
        log.debug("REST request to get JobProfile : {}", id);
        EmployeeView employeeView = employeeViewRepository.findById(SecurityUtils.getCurrentUserLogin().orElse(null)).orElse(null);
        JobProfileAccess jobProfileAccess = jobProfileAccessRepository.findByLogin(SecurityUtils.getCurrentUserLogin().orElse(null));
        List<JobProfile> jobProfiles = null;
        List<JobProfileBean> jobProfilesBeans = new ArrayList<>();
        if (id != null && id.equalsIgnoreCase("deptCode")) {
            id = employeeView.getDepCode();
        }
        if(jobProfileAccess != null ){
            jobProfiles = jobProfileRepository.findAllByCustom(employeeView.getFactory());
        } else {
            jobProfiles = jobProfileRepository.findByDepartment(employeeView.getFactory(), id + "");
        }
        for( JobProfile jobProfile : jobProfiles ) {
            JobProfileBean jobProfileBean = new JobProfileBean();
            BeanUtils.copyProperties(jobProfile, jobProfileBean);
            if(employeeView.getDepCode().equalsIgnoreCase(jobProfile.getDepartment()) && employeeView.getDesCode().equalsIgnoreCase(jobProfile.getDesignation())) {
                jobProfileBean.setActiveProfile(true);
            } else {
                jobProfileBean.setActiveProfile(false);
            }
            try {
                jobProfileBean.setFileName(MD5UrlEncryption.fakeUploadUrl("profile/" + jobProfileBean.getFileName()));
            } catch (Exception e) {
            }
            jobProfilesBeans.add(jobProfileBean);
        }
        return ResponseEntity.ok().body(jobProfilesBeans);
    }

    /**
     * DELETE  /job-profiles/:id : delete the "id" jobProfile.
     *
     * @param id the id of the jobProfile to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/job-profiles/{id}")
    @Timed
    public ResponseEntity<Void> deleteJobProfile(@PathVariable Long id) {
        log.debug("REST request to delete JobProfile : {}", id);

        jobProfileRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /feedbacks/:id : get the "id" feedback.
     *
     * @param id the id of the feedback to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the feedback, or with status 404 (Not Found)
     */
    @GetMapping("/job-profiles-download/{id}")
    @Timed
    public ResponseEntity<Object> getJobProfileDownload(@PathVariable Long id) throws FileNotFoundException, IOException {
        log.debug("REST request to get Feedback : {}", id);
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        JobProfile jobProfile = jobProfileRepository.findById(id).orElse(null);;
        File file = new File(UPLOADED_FOLDER + "profile/"+jobProfile.getFileName());
        Path path = Paths.get(UPLOADED_FOLDER + "profile/" + jobProfile.getFileName());
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

    /**
     * POST  /news-details-attaches : Create a new newsDetailsAttach.
     *
     * @param @newsDetailsAttach the newsDetailsAttach to create
     * @return the ResponseEntity with status 201 (Created) and with body the new newsDetailsAttach, or with status 400 (Bad Request) if the newsDetailsAttach has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping(value = "/job-profiles-attach", consumes = {"multipart/form-data"})
    @Timed
    public ResponseEntity<JobProfile> uploadJobProfile(@RequestParam(required = false) MultipartFile file, String id) throws URISyntaxException, IOException {
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        JobProfile jobProfile = jobProfileRepository.findById(new Long(id)).orElse(null);
        String extn = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
        String fileName = "JD-" + jobProfile.getDesignation() + "-" + jobProfile.getDepartment() + extn;
        jobProfile.setFileName(fileName);
        jobProfile.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        jobProfile.setCreatedDate(Instant.now());
        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOADED_FOLDER + "profile/" + fileName);
        Files.write(path, bytes);
        JobProfile result = jobProfileRepository.save(jobProfile);
        return ResponseEntity.created(new URI("/api/job-profile/" + id))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }


}
