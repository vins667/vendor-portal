package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.domain.CompOffMaster;
import io.vamani.application.domain.LeaveMaster;
import io.vamani.application.domain.LeaveMasterRemarksDetails;
import io.vamani.application.domain.User;
import io.vamani.application.model.LeaveMasterRemarksDetailsBean;
import io.vamani.application.model.Master;
import io.vamani.application.mssql.domain.EmployeeView;
import io.vamani.application.mssql.repository.EmployeeViewRepository;
import io.vamani.application.repository.CompOffMasterRepository;
import io.vamani.application.repository.LeaveMasterRemarksDetailsRepository;
import io.vamani.application.repository.LeaveMasterRepository;
import io.vamani.application.repository.UserRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.service.MailService;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

/**
 * REST controller for managing LeaveMasterRemarksDetails.
 */
@RestController
@RequestMapping("/api")
public class LeaveMasterRemarksDetailsResource {

    private final Logger log = LoggerFactory.getLogger(LeaveMasterRemarksDetailsResource.class);

    private static final String ENTITY_NAME = "leaveMasterRemarksDetails";

    private final LeaveMasterRemarksDetailsRepository leaveMasterRemarksDetailsRepository;

    @Autowired
    private EmployeeViewRepository employeeViewRepository;

    @Autowired
    private LeaveMasterRepository leaveMasterRepository;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private MailService mailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private CompOffMasterRepository compOffMasterRepository;

    public LeaveMasterRemarksDetailsResource(LeaveMasterRemarksDetailsRepository leaveMasterRemarksDetailsRepository) {
        this.leaveMasterRemarksDetailsRepository = leaveMasterRemarksDetailsRepository;
    }

    /**
     * POST  /leave-master-remarks-details : Create a new leaveMasterRemarksDetails.
     *
     * @param !leaveMasterRemarksDetails the leaveMasterRemarksDetails to create
     * @return the ResponseEntity with status 201 (Created) and with body the new leaveMasterRemarksDetails, or with status 400 (Bad Request) if the leaveMasterRemarksDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/leave-master-remarks-details")
    @Timed
    public ResponseEntity<LeaveMasterRemarksDetailsBean> createLeaveMasterRemarksDetails(@Valid @RequestBody LeaveMasterRemarksDetails leaveMasterRemarksDetailsNew) throws URISyntaxException {
        log.debug("REST request to save LeaveMasterRemarksDetails : {}", leaveMasterRemarksDetailsNew);
        LeaveMasterRemarksDetails result = null;
        LeaveMasterRemarksDetailsBean leaveMasterRemarksDetailsBean = new LeaveMasterRemarksDetailsBean();
        LeaveMaster leaveMasterTemp = leaveMasterRepository.findById(leaveMasterRemarksDetailsNew.getLeaveMaster().getId()).orElse(null);
        LeaveMaster leaveMasterResult = null;
        java.util.Date allowDate = allowDateApproval(java.util.Date.from(leaveMasterTemp.getLeaveDateFrom()));
        if (allowDate.before(new java.util.Date()) && (leaveMasterRemarksDetailsNew.getStatus() != null && leaveMasterRemarksDetailsNew.getStatus().equalsIgnoreCase("A"))) {

        } else {
            if (leaveMasterRemarksDetailsNew.getStatus() != null &&
                (leaveMasterRemarksDetailsNew.getStatus().equalsIgnoreCase("A") || leaveMasterRemarksDetailsNew.getStatus().equalsIgnoreCase("R"))) {
                leaveMasterRemarksDetailsNew.setCreatedDate(Instant.now());
                result = leaveMasterRemarksDetailsRepository.save(leaveMasterRemarksDetailsNew);
                leaveMasterTemp.setFlag(leaveMasterRemarksDetailsNew.getStatus());
                leaveMasterTemp.setHodApprovedDate(Instant.now());
                leaveMasterTemp.setHodRemarks(leaveMasterRemarksDetailsNew.getRemarks());
                leaveMasterResult = leaveMasterRepository.save(leaveMasterTemp);
            } else {
                leaveMasterRemarksDetailsNew.setCreatedDate(Instant.now());
                result = leaveMasterRemarksDetailsRepository.save(leaveMasterRemarksDetailsNew);
                LeaveMasterRemarksDetails remarksDetailsNew = new LeaveMasterRemarksDetails();
                remarksDetailsNew.setEmpCode(result.getForwardCode());
                remarksDetailsNew.setEmpName(result.getForwardName());
                remarksDetailsNew.setLeaveMaster(leaveMasterTemp);
                leaveMasterRemarksDetailsRepository.save(remarksDetailsNew);
                leaveMasterTemp.setFlag(leaveMasterRemarksDetailsNew.getStatus());
                leaveMasterResult = leaveMasterRepository.save(leaveMasterTemp);
            }
            if (leaveMasterResult != null && leaveMasterResult.getLeaveTypeMaster() != null && leaveMasterResult.getLeaveTypeMaster().getLeaveCode().equalsIgnoreCase("CO")
                && leaveMasterResult.getCompOffMasterId() != null) {
                CompOffMaster compOffMaster = compOffMasterRepository.findById(leaveMasterResult.getCompOffMasterId()).orElse(null);
                compOffMaster.setAvailDate(leaveMasterResult.getLeaveDateFrom());
                compOffMasterRepository.save(compOffMaster);
            }


            String currentUser = SecurityUtils.getCurrentUserLogin().orElse(null);
            leaveMasterRemarksDetailsBean.setLeaveMasterId(leaveMasterResult.getId());
            leaveMasterRemarksDetailsBean.setStatus(leaveMasterResult.getFlag());
            leaveMasterRemarksDetailsBean.setCreatedBy(leaveMasterResult.getUserCode().getLogin());
            leaveMasterRemarksDetailsBean.setCreatedName(leaveMasterResult.getUserCode().getFirstName() + " " + (leaveMasterResult.getUserCode().getLastName() != null ? leaveMasterResult.getUserCode().getLastName() : ""));
            leaveMasterRemarksDetailsBean.setHodApprovedBy(leaveMasterResult.getHodApprovedBy());
            User userHOD = userRepository.findOneByLogin(leaveMasterResult.getHodApprovedBy()).orElse(null);
            leaveMasterRemarksDetailsBean.setHodApprovedName(userHOD.getFirstName() + " " + (userHOD.getLastName() != null ? userHOD.getLastName() : ""));
            LeaveMasterRemarksDetails leaveMasterRemarksDetails = leaveMasterRemarksDetailsRepository.getEntryLeaveMasterRemarksDetailsByLeaveMasterId(result.getLeaveMaster().getId());
            List<LeaveMasterRemarksDetails> leaveMasterRemarksDetailsList = leaveMasterRemarksDetailsRepository.getLeaveMasterRemarksDetailsByLeaveMasterId(result.getLeaveMaster().getId());
            if (leaveMasterRemarksDetailsList != null && leaveMasterRemarksDetailsList.size() > 0) {
                leaveMasterRemarksDetailsBean.setLeaveMasterRemarksDetails(leaveMasterRemarksDetailsList);
                if (leaveMasterRemarksDetails != null && leaveMasterRemarksDetails.getId() != null && leaveMasterRemarksDetails.getId() > 0) {
                    leaveMasterRemarksDetailsBean.setId(leaveMasterRemarksDetails.getId());
                    if (currentUser != null && currentUser.equalsIgnoreCase(leaveMasterRemarksDetails.getEmpCode())) {
                        leaveMasterRemarksDetailsBean.setEmpCode(leaveMasterRemarksDetails.getEmpCode());
                        leaveMasterRemarksDetailsBean.setEmpName(leaveMasterRemarksDetails.getEmpName());
                        leaveMasterRemarksDetailsBean.setAllowEntry(true);
                    } else {
                        leaveMasterRemarksDetailsBean.setEmpCode(leaveMasterRemarksDetails.getEmpCode());
                        leaveMasterRemarksDetailsBean.setEmpName(leaveMasterRemarksDetails.getEmpName());
                        leaveMasterRemarksDetailsBean.setAllowEntry(false);
                    }
                } else {
                    EmployeeView employeeView = employeeViewRepository.findById(currentUser).orElse(null);
                    leaveMasterRemarksDetailsBean.setId(new Long(0));
                    leaveMasterRemarksDetailsBean.setEmpCode(currentUser);
                    leaveMasterRemarksDetailsBean.setEmpName(employeeView.getName());
                    leaveMasterRemarksDetailsBean.setAllowEntry(false);
                }
            } else {
                EmployeeView employeeView = employeeViewRepository.findById(leaveMasterResult.getHodApprovedBy()).orElse(null);
                if (currentUser != null && currentUser.equalsIgnoreCase(leaveMasterResult.getHodApprovedBy())) {
                    leaveMasterRemarksDetailsBean.setEmpCode(currentUser);
                    leaveMasterRemarksDetailsBean.setEmpName(employeeView.getName());
                    leaveMasterRemarksDetailsBean.setAllowEntry(true);
                } else {
                    leaveMasterRemarksDetailsBean.setEmpCode(employeeView.getLogin());
                    leaveMasterRemarksDetailsBean.setEmpName(employeeView.getName());
                    leaveMasterRemarksDetailsBean.setAllowEntry(false);
                }
            }
            if (leaveMasterResult.getFlag() != null && (leaveMasterResult.getFlag().equalsIgnoreCase("A") || leaveMasterResult.getFlag().equalsIgnoreCase("R") || leaveMasterResult.getFlag().equalsIgnoreCase("Q"))) {
                EmployeeView requester = employeeViewRepository.findById(leaveMasterResult.getUserCode().getLogin().toLowerCase()).orElse(null);
                if (requester != null && requester != null) {
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
                    Locale locale = Locale.forLanguageTag("en");
                    Context context = new Context(locale);
                    context.setVariable("leaveTypeCode", leaveMasterResult.getLeaveTypeMaster().getLeaveCode());
                    context.setVariable("leaveTypeName", leaveMasterResult.getLeaveTypeMaster().getLeaveName());
                    context.setVariable("empCode", requester.getCardNo());
                    context.setVariable("name", requester.getName());
                    context.setVariable("leaveDateFrom", format.format(Date.from(leaveMasterResult.getLeaveDateFrom())));
                    context.setVariable("leaveDateTo", format.format(Date.from(leaveMasterResult.getLeaveDateTo())));
                    context.setVariable("flag", leaveMasterResult.getFlag());
                    context.setVariable("reason", leaveMasterResult.getReason());
                    context.setVariable("remarksBy", "HOD Remarks");
                    context.setVariable("remarks", leaveMasterResult.getHodRemarks());
                    context.setVariable("url", applicationProperties.getUrl());
                    String content = null;
                    String subject = null;
                    if (leaveMasterResult.getFlag().equalsIgnoreCase("A")) {
                        subject = "Leave Approved by HOD for " + leaveMasterResult.getLeaveTypeMaster().getLeaveName();
                    } else if (leaveMasterResult.getFlag().equalsIgnoreCase("R")) {
                        subject = "Leave Rejected by HOD for " + leaveMasterResult.getLeaveTypeMaster().getLeaveName();
                    } else if (leaveMasterResult.getFlag().equalsIgnoreCase("Q")) {
                        subject = "Query Raised by HOD for " + leaveMasterResult.getLeaveTypeMaster().getLeaveName();
                    }
                    try {
                        content = templateEngine.process("mail/leave_mail", context);
                        mailService.sendEmail(requester.getEmail(), subject, content, false, true);
                        if (leaveMasterResult.getFlag().equalsIgnoreCase("A")) {
                            mailService.sendEmail("hrd169@vamanioverseas.com", subject, content, false, true);
                        }
                    } catch (Exception e) {
                    }
                }
            } else if (leaveMasterResult.getFlag() != null && leaveMasterResult.getFlag().equalsIgnoreCase("E")) {
                EmployeeView requester = employeeViewRepository.findById(leaveMasterResult.getUserCode().getLogin().toLowerCase()).orElse(null);
                EmployeeView approver = employeeViewRepository.findById(leaveMasterResult.getHodApprovedBy().toLowerCase()).orElse(null);
                if (requester != null && requester != null) {
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
                    Locale locale = Locale.forLanguageTag("en");
                    Context context = new Context(locale);
                    context.setVariable("leaveTypeCode", leaveMasterResult.getLeaveTypeMaster().getLeaveCode());
                    context.setVariable("leaveTypeName", leaveMasterResult.getLeaveTypeMaster().getLeaveName());
                    context.setVariable("empCode", requester.getCardNo());
                    context.setVariable("name", requester.getName());
                    context.setVariable("leaveDateFrom", format.format(Date.from(leaveMasterResult.getLeaveDateFrom())));
                    context.setVariable("leaveDateTo", format.format(Date.from(leaveMasterResult.getLeaveDateTo())));
                    context.setVariable("flag", leaveMasterResult.getFlag());
                    context.setVariable("reason", leaveMasterResult.getReason());
                    context.setVariable("url", applicationProperties.getUrl());
                    String content = null;
                    String subject = "Leave sanction request for " + leaveMasterResult.getLeaveTypeMaster().getLeaveName();
                    try {
                        content = templateEngine.process("mail/leave_mail", context);
                        mailService.sendEmail(approver.getEmail(), subject, content, false, true);
                    } catch (Exception e) {
                    }
                }
            }
            if (currentUser != null && currentUser.equalsIgnoreCase(leaveMasterResult.getHodApprovedBy())) {
                List<Master> masterList = new ArrayList<>();
                masterList.add(new Master("Q", "Query"));
                masterList.add(new Master("A", "Approve"));
                masterList.add(new Master("R", "Reject"));
                leaveMasterRemarksDetailsBean.setStatusList(masterList);
            } else {
                List<Master> masterList = new ArrayList<>();
                masterList.add(new Master("E", "Response"));
                leaveMasterRemarksDetailsBean.setStatusList(masterList);
            }
            leaveMasterRemarksDetailsBean.setLeaveMaster(leaveMasterResult);
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(leaveMasterRemarksDetailsBean));
    }

    /**
     * PUT  /leave-master-remarks-details : Updates an existing leaveMasterRemarksDetails.
     *
     * @param leaveMasterRemarksDetails the leaveMasterRemarksDetails to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated leaveMasterRemarksDetails,
     * or with status 400 (Bad Request) if the leaveMasterRemarksDetails is not valid,
     * or with status 500 (Internal Server Error) if the leaveMasterRemarksDetails couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/leave-master-remarks-details")
    @Timed
    public ResponseEntity<LeaveMasterRemarksDetails> updateLeaveMasterRemarksDetails(@Valid @RequestBody LeaveMasterRemarksDetails leaveMasterRemarksDetails) throws URISyntaxException {
        log.debug("REST request to update LeaveMasterRemarksDetails : {}", leaveMasterRemarksDetails);
        if (leaveMasterRemarksDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LeaveMasterRemarksDetails result = leaveMasterRemarksDetailsRepository.save(leaveMasterRemarksDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, leaveMasterRemarksDetails.getId().toString()))
            .body(result);
    }

    /**
     * GET  /leave-master-remarks-details : get all the leaveMasterRemarksDetails.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of leaveMasterRemarksDetails in body
     */
    @GetMapping("/leave-master-remarks-details")
    @Timed
    public ResponseEntity<List<LeaveMasterRemarksDetails>> getAllLeaveMasterRemarksDetails(Pageable pageable) {
        log.debug("REST request to get a page of LeaveMasterRemarksDetails");
        Page<LeaveMasterRemarksDetails> page = leaveMasterRemarksDetailsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/leave-master-remarks-details");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /leave-master-remarks-details/:id : get the "id" leaveMasterRemarksDetails.
     *
     * @param id the id of the leaveMasterRemarksDetails to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the leaveMasterRemarksDetails, or with status 404 (Not Found)
     */
    @GetMapping("/leave-master-remarks-details/{id}")
    @Timed
    public ResponseEntity<LeaveMasterRemarksDetails> getLeaveMasterRemarksDetails(@PathVariable Long id) {
        log.debug("REST request to get LeaveMasterRemarksDetails : {}", id);
        Optional<LeaveMasterRemarksDetails> leaveMasterRemarksDetails = leaveMasterRemarksDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(leaveMasterRemarksDetails);
    }

    /**
     * DELETE  /leave-master-remarks-details/:id : delete the "id" leaveMasterRemarksDetails.
     *
     * @param id the id of the leaveMasterRemarksDetails to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/leave-master-remarks-details/{id}")
    @Timed
    public ResponseEntity<Void> deleteLeaveMasterRemarksDetails(@PathVariable Long id) {
        log.debug("REST request to delete LeaveMasterRemarksDetails : {}", id);

        leaveMasterRemarksDetailsRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    private java.util.Date allowDateApproval(java.util.Date entryDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(entryDate);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR, 6);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.AM_PM, Calendar.PM);
        return calendar.getTime();
    }
}
