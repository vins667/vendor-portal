package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;

import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.domain.EmployeeInformationUpdate;
import io.vamani.application.model.EmployeeInformationUpdateBean;
import io.vamani.application.model.LeaveSearch;
import io.vamani.application.model.Message;
import io.vamani.application.mssql.domain.EmployeeView;
import io.vamani.application.mssql.repository.EmployeeViewRepository;
import io.vamani.application.repository.EmployeeInformationUpdateRepository;
import io.vamani.application.repository.UserRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.util.MD5UrlEncryption;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

/**
 * REST controller for managing EmployeeInformationUpdate.
 */
@RestController
@RequestMapping("/api")
public class EmployeeInformationUpdateResource {

    private final Logger log = LoggerFactory.getLogger(EmployeeInformationUpdateResource.class);

    private static final String ENTITY_NAME = "employeeInformationUpdate";

    private final EmployeeInformationUpdateRepository employeeInformationUpdateRepository;
    
    @Autowired
    private  EmployeeViewRepository employeeViewRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ApplicationProperties applicationProperties;

    public EmployeeInformationUpdateResource(EmployeeInformationUpdateRepository employeeInformationUpdateRepository) {
        this.employeeInformationUpdateRepository = employeeInformationUpdateRepository;
    }

    /**
     * POST  /employee-information-updates : Create a new employeeInformationUpdate.
     *
     * @param employeeInformationUpdate the employeeInformationUpdate to create
     * @return the ResponseEntity with status 201 (Created) and with body the new employeeInformationUpdate, or with status 400 (Bad Request) if the employeeInformationUpdate has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws IOException 
     */
    @PostMapping(value = "/employee-information-updates", consumes = {"multipart/form-data"})
    @Timed
    public ResponseEntity<EmployeeInformationUpdate> createEmployeeInformationUpdate(@RequestParam(required = false) MultipartFile file, String id, String correspondenceAddress, String mobileNumber) throws URISyntaxException, IOException {
        String empCode = SecurityUtils.getCurrentUserLogin().orElse(null);
        EmployeeView employeeView = employeeViewRepository.findById(empCode).orElse(null);
        List<EmployeeInformationUpdate> employeeInformationUpdateOld = employeeInformationUpdateRepository.findAllByEmpCode(empCode);
        if (employeeInformationUpdateOld != null && employeeInformationUpdateOld.size() > 0) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Already Pending request exist!")).build();
        }
    	String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        EmployeeInformationUpdate employeeInformationUpdate = new EmployeeInformationUpdate();
        employeeInformationUpdate.setUserCode(userRepository.findOneByLogin(empCode).orElse(null));
        employeeInformationUpdate.setCorrespondenceAddress(correspondenceAddress);
        employeeInformationUpdate.setMobileNumber(mobileNumber);
        employeeInformationUpdate.setOldCorrespondenceAddress(employeeView.getAdd1());
        employeeInformationUpdate.setOldMobileNumber(employeeView.getPhone());
        employeeInformationUpdate.setOldImagePath(employeeView.getImagePath());
        employeeInformationUpdate.setFactory(employeeView.getFactory());
        employeeInformationUpdate.setFlag("E");
        employeeInformationUpdate.setCreatedBy(empCode);
        employeeInformationUpdate.setCreatedDate(Instant.now());
        EmployeeInformationUpdate result = employeeInformationUpdateRepository.save(employeeInformationUpdate);
        if(file!=null && !file.isEmpty()) {
            String extn = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
            String fileName = result.getId() + extn;
            result.setImagePath(fileName);
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + "personal/" + fileName);
            Files.write(path, bytes);
            result = employeeInformationUpdateRepository.save(result);
        }
        return ResponseEntity.created(new URI("/api/employee-information-updates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /employee-information-updates : Updates an existing employeeInformationUpdate.
     *
     * @param employeeInformationUpdate the employeeInformationUpdate to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated employeeInformationUpdate,
     * or with status 400 (Bad Request) if the employeeInformationUpdate is not valid,
     * or with status 500 (Internal Server Error) if the employeeInformationUpdate couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws IOException 
     */
    @PostMapping(value = "/employee-information-updates-update", consumes = {"multipart/form-data"})
    @Timed
    public ResponseEntity<EmployeeInformationUpdate> updateEmployeeInformationUpdate(@RequestParam(required = false) MultipartFile file, String id, String correspondenceAddress, String mobileNumber) throws URISyntaxException, IOException {
    	String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        EmployeeInformationUpdate employeeInformationUpdate = employeeInformationUpdateRepository.findById(Long.parseLong(id)).orElse(null);
        employeeInformationUpdate.setCorrespondenceAddress(correspondenceAddress);
        employeeInformationUpdate.setMobileNumber(mobileNumber);
        employeeInformationUpdate.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        employeeInformationUpdate.setLastUpdatedDate(Instant.now());
        if (employeeInformationUpdate.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EmployeeInformationUpdate result = employeeInformationUpdateRepository.save(employeeInformationUpdate);
        if(file!=null && !file.isEmpty()) {
            String extn = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
            String fileName = result.getId() + extn;
            result.setImagePath(fileName);
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + "personal/" + fileName);
            Files.write(path, bytes);
            result = employeeInformationUpdateRepository.save(result);
        }
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, employeeInformationUpdate.getId().toString()))
            .body(result);
    }

    /**
     * GET  /employee-information-updates : get all the employeeInformationUpdates.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of employeeInformationUpdates in body
     */
    @GetMapping("/employee-information-updates")
    @Timed
    public ResponseEntity<List<EmployeeInformationUpdate>> getAllEmployeeInformationUpdates(Pageable pageable) {
        log.debug("REST request to get a page of EmployeeInformationUpdates");
        Page<EmployeeInformationUpdate> page = employeeInformationUpdateRepository.findAll(SecurityUtils.getCurrentUserLogin().orElse(null), pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/employee-information-updates");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /leave-masters : get all the employeeInformationUpdates.
     *
     * @param !pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of leaveMasters in body
     */
    @PostMapping("/employee-information-updates-hr")
    @Timed
    public ResponseEntity<List<EmployeeInformationUpdate>> getAllEmployeeInformationUpdateByHr(@Valid @RequestBody LeaveSearch search) {
        log.debug("REST request to get a page of employeeInformationUpdates");
        String empCode = "%";
        if (search.getEmpCode() != null) {
            empCode = search.getEmpCode() + "%";
        }
        EmployeeView employeeView = employeeViewRepository.findByCardNo(SecurityUtils.getCurrentUserLogin().orElse(null)).orElse(null);
        Page<EmployeeInformationUpdate> page = null;
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("id").descending()));
        if(search.getLeaveStatus() != null && search.getLeaveStatus().equalsIgnoreCase("P")) {
            page = employeeInformationUpdateRepository.findAllByHrApprovedByPending(empCode, employeeView.getFactory(), search.getPage());
        } else if(search.getLeaveStatus() != null && search.getLeaveStatus().equalsIgnoreCase("A")) {
            page = employeeInformationUpdateRepository.findAllByHrApprovedByApproved(search.getLeaveDateFrom(), search.getLeaveDateTo(), empCode, employeeView.getFactory(), search.getPage());
        } else {
            page = employeeInformationUpdateRepository.findAllByHrApprovedByRejected(search.getLeaveDateFrom(), search.getLeaveDateTo(), empCode, search.getLeaveStatus(), employeeView.getFactory(), search.getPage());
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/employee-information-updates-hr");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    
    
    /**
     * PUT  /employee-information-updates : Updates an existing employeeInformationUpdate.
     *
     * @param employeeInformationUpdate the employeeInformationUpdate to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated employeeInformationUpdate,
     * or with status 400 (Bad Request) if the employeeInformationUpdate is not valid,
     * or with status 500 (Internal Server Error) if the employeeInformationUpdate couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/employee-information-updates-aprv")
    @Timed
    public ResponseEntity<EmployeeInformationUpdate> updateEmployeeInformationUpdate(@Valid @RequestBody EmployeeInformationUpdate employeeInformationUpdate) throws URISyntaxException {
        log.debug("REST request to update EmployeeInformationUpdate : {}", employeeInformationUpdate);
        if (employeeInformationUpdate.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        employeeInformationUpdate.setFlag("C");
        employeeInformationUpdate.setHrApprovedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        employeeInformationUpdate.setHrApprovedDate(Instant.now());
        EmployeeInformationUpdate result = employeeInformationUpdateRepository.save(employeeInformationUpdate);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, employeeInformationUpdate.getId().toString()))
            .body(result);
    }

    /**
     * GET  /employee-information-updates/:id : get the "id" employeeInformationUpdate.
     *
     * @param id the id of the employeeInformationUpdate to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the employeeInformationUpdate, or with status 404 (Not Found)
     */
    @GetMapping("/employee-information-updates/{id}")
    @Timed
    public ResponseEntity<EmployeeInformationUpdateBean> getEmployeeInformationUpdate(@PathVariable Long id) {
        log.debug("REST request to get EmployeeInformationUpdate : {}", id);
        EmployeeInformationUpdateBean employeeInformationUpdateBean= new EmployeeInformationUpdateBean();
        EmployeeInformationUpdate employeeInformationUpdate = employeeInformationUpdateRepository.findById(id).orElse(null);
        employeeInformationUpdateBean.setOldCorrespondenceAddress(employeeInformationUpdate.getOldCorrespondenceAddress());
        employeeInformationUpdateBean.setOldMobileNumber(employeeInformationUpdate.getOldMobileNumber());
        employeeInformationUpdateBean.setUserCode(employeeInformationUpdate.getUserCode());
        if(employeeInformationUpdate.getFlag() != null && employeeInformationUpdate.getFlag().equalsIgnoreCase("C") && employeeInformationUpdate.getProcessFlag() != null) {
            employeeInformationUpdateBean.setOldImagePath(applicationProperties.getImagePath() + "personal/old/" + employeeInformationUpdate.getOldImagePath());
        } else {
            employeeInformationUpdateBean.setOldImagePath(employeeInformationUpdate.getOldImagePath());
        }
        employeeInformationUpdateBean.setFlag(employeeInformationUpdate.getFlag());
        
        employeeInformationUpdateBean.setId(employeeInformationUpdate.getId());
        employeeInformationUpdateBean.setCorrespondenceAddress(employeeInformationUpdate.getCorrespondenceAddress());
        employeeInformationUpdateBean.setMobileNumber(employeeInformationUpdate.getMobileNumber());
        if(employeeInformationUpdate.getImagePath() != null && employeeInformationUpdate.getImagePath().length()>0) {
            try {
                employeeInformationUpdateBean.setImagePath(MD5UrlEncryption.fakeUploadUrl("personal/" + employeeInformationUpdate.getImagePath()));
            } catch (Exception e) {
            }
        } else {
            employeeInformationUpdateBean.setImagePath(employeeInformationUpdate.getOldImagePath());
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(employeeInformationUpdateBean));
    }
    
    
    /**
     * GET  /employee-information-updates/:id : get the "id" employeeInformationUpdate.
     *
     * @param id the id of the employeeInformationUpdate to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the employeeInformationUpdate, or with status 404 (Not Found)
     */
    @GetMapping("/employee-information-updates-custom")
    @Timed
    public ResponseEntity<EmployeeInformationUpdateBean> getEmployeeInformation() {
    	EmployeeView employeeView= employeeViewRepository.findById(SecurityUtils.getCurrentUserLogin().orElse(null)).orElse(null);
    	EmployeeInformationUpdateBean bean = new EmployeeInformationUpdateBean();
    	bean.setOldCorrespondenceAddress(employeeView.getAdd1());
    	bean.setOldMobileNumber(employeeView.getPhone());
    	bean.setOldImagePath(employeeView.getImagePath());
    	
    	bean.setCorrespondenceAddress(employeeView.getAdd1());
    	bean.setMobileNumber(employeeView.getPhone());
    	bean.setImagePath(employeeView.getImagePath());
        return ResponseUtil.wrapOrNotFound(Optional.of(bean));
    }

    /**
     * GET  /employee-information-updates/:id : get the "id" employeeInformationUpdate.
     *
     * @param id the id of the employeeInformationUpdate to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the employeeInformationUpdate, or with status 404 (Not Found)
     */
    @GetMapping("/employee-information-updates-mobile")
    @Timed
    public ResponseEntity<EmployeeInformationUpdateBean> getEmployeeInformationEditOrNew() {
        List<EmployeeInformationUpdate> employeeInformationUpdateOld = employeeInformationUpdateRepository.findAllByEmpCode(SecurityUtils.getCurrentUserLogin().orElse(null));
        EmployeeInformationUpdateBean bean = new EmployeeInformationUpdateBean();
        if (employeeInformationUpdateOld != null && employeeInformationUpdateOld.size() > 0) {
            EmployeeInformationUpdate employeeInformationUpdate = employeeInformationUpdateOld.get(0);
            bean.setOldCorrespondenceAddress(employeeInformationUpdate.getOldCorrespondenceAddress());
            bean.setOldMobileNumber(employeeInformationUpdate.getOldMobileNumber());
            bean.setOldImagePath(employeeInformationUpdate.getOldImagePath());

            bean.setCorrespondenceAddress(employeeInformationUpdate.getCorrespondenceAddress());
            bean.setMobileNumber(employeeInformationUpdate.getMobileNumber());
            bean.setImagePath(applicationProperties.getImagePath() + "personal/" + employeeInformationUpdate.getImagePath());
            bean.setId(employeeInformationUpdate.getId());
        } else {
            EmployeeView employeeView = employeeViewRepository.findById(SecurityUtils.getCurrentUserLogin().orElse(null)).orElse(null);
            bean.setOldCorrespondenceAddress(employeeView.getAdd1());
            bean.setOldMobileNumber(employeeView.getPhone());
            bean.setOldImagePath(employeeView.getImagePath());

            bean.setCorrespondenceAddress(employeeView.getAdd1());
            bean.setMobileNumber(employeeView.getPhone());
            bean.setImagePath(employeeView.getImagePath());
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(bean));
    }

    /**
     * DELETE  /employee-information-updates/:id : delete the "id" employeeInformationUpdate.
     *
     * @param id the id of the employeeInformationUpdate to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/employee-information-updates/{id}")
    @Timed
    public ResponseEntity<Void> deleteEmployeeInformationUpdate(@PathVariable Long id) {
        log.debug("REST request to delete EmployeeInformationUpdate : {}", id);

        employeeInformationUpdateRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * DELETE  /comp-off-masters/:id : delete the "id" compOffMaster.
     *
     * @param id the id of the compOffMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @GetMapping("/employee-information-updates-approval/{id}/{flag}")
    @Timed
    public ResponseEntity<Message> approvalEmployeeInformationUpdate(@PathVariable Long id, @PathVariable String flag) {
        log.debug("REST request to Approval Employee Information Update : {}", id);
        EmployeeInformationUpdate employeeInformationUpdate = employeeInformationUpdateRepository.findById(id).orElse(null);
        if (employeeInformationUpdate.getFlag() != null && employeeInformationUpdate.getFlag().equalsIgnoreCase("E")) {
            employeeInformationUpdate.setFlag(flag);
            employeeInformationUpdate.setHrApprovedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
            employeeInformationUpdate.setHrApprovedDate(Instant.now());
            EmployeeInformationUpdate result = employeeInformationUpdateRepository.save(employeeInformationUpdate);
            return ResponseEntity.ok().body(new Message("Save successfully!", "success", true, ""));
        } else {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Profile Request already " + (employeeInformationUpdate.getFlag().equalsIgnoreCase("C") ? "approved" : "rejected") + ".")).body(new Message("Profile Request already " + (employeeInformationUpdate.getFlag().equalsIgnoreCase("C") ? "approved" : "rejected") + ".", "error", false, "Profile Request already " + (employeeInformationUpdate.getFlag().equalsIgnoreCase("C") ? "approved" : "rejected") + "."));
        }
    }
}
