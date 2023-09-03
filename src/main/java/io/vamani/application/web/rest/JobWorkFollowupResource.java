package io.vamani.application.web.rest;

import io.vamani.application.db2.model.DaysBean;
import io.vamani.application.db2.model.MonthlyBean;
import io.vamani.application.domain.*;
import io.vamani.application.model.FollowupBuyerBean;
import io.vamani.application.model.JobWorkFollowupBean;
import io.vamani.application.model.JobWorkFollowupScheduleBean;
import io.vamani.application.model.MasterSearch;
import io.vamani.application.mssql.repository.EmployeeViewRepository;
import io.vamani.application.repository.*;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.util.DateUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.net.URI;
import java.net.URISyntaxException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

/**
 * REST controller for managing {@link JobWorkFollowup}.
 */
@RestController
@RequestMapping("/api")
public class JobWorkFollowupResource {

    private final Logger log = LoggerFactory.getLogger(JobWorkFollowupResource.class);

    private static final String ENTITY_NAME = "jobWorkFollowup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final JobWorkFollowupRepository jobWorkFollowupRepository;

    private final FollowupBuyerRepository followupBuyerRepository;

    @Autowired
    private EmployeeViewRepository employeeViewRepository;

    @Autowired
    private JobWorkFollowupScheduleRepository jobWorkFollowupScheduleRepository;

    @Autowired
    private JobWorkFollowupDetailsRepository jobWorkFollowupDetailsRepository;

    @Autowired
    private JobWorkFollowupReminderRepository jobWorkFollowupReminderRepository;

    @Autowired
    private JobWorkFollowupAttachRepository jobWorkFollowupAttachRepository;

    public JobWorkFollowupResource(JobWorkFollowupRepository jobWorkFollowupRepository, FollowupBuyerRepository followupBuyerRepository) {
        this.jobWorkFollowupRepository = jobWorkFollowupRepository;
        this.followupBuyerRepository = followupBuyerRepository;
    }

    /**
     * {@code POST  /job-work-followups} : Create a new jobWorkFollowup.
     *
     * @param jobWorkFollowupBean the jobWorkFollowup to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new jobWorkFollowup, or with status {@code 400 (Bad Request)} if the jobWorkFollowup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/job-work-followups")
    public ResponseEntity<JobWorkFollowup> createJobWorkFollowup(@Valid @RequestBody JobWorkFollowupBean jobWorkFollowupBean) throws URISyntaxException {
        log.debug("REST request to save JobWorkFollowup : {}", jobWorkFollowupBean);

        if (jobWorkFollowupBean.getId() != null) {
            throw new BadRequestAlertException("A new jobWorkFollowup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JobWorkFollowup jobWorkFollowup = new JobWorkFollowup();
        org.springframework.beans.BeanUtils.copyProperties(jobWorkFollowupBean, jobWorkFollowup);
        JobWorkFollowup result = jobWorkFollowupRepository.save(jobWorkFollowup);
        return ResponseEntity.created(new URI("/api/job-work-followups/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString())).body(result);
    }

    /**
     * {@code PUT  /job-work-followups} : Updates an existing jobWorkFollowup.
     *
     * @param jobWorkFollowupBean the jobWorkFollowup to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jobWorkFollowup,
     * or with status {@code 400 (Bad Request)} if the jobWorkFollowup is not valid,
     * or with status {@code 500 (Internal Server Error)} if the jobWorkFollowup couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/job-work-followups")
    public ResponseEntity<JobWorkFollowup> updateJobWorkFollowup(@Valid @RequestBody JobWorkFollowupBean jobWorkFollowupBean) throws URISyntaxException {

        log.debug("REST request to update JobWorkFollowup : {}", jobWorkFollowupBean);
        if (jobWorkFollowupBean.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(jobWorkFollowupBean.getId(), jobWorkFollowupBean.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }
        if (!jobWorkFollowupRepository.existsById(jobWorkFollowupBean.getId())) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        JobWorkFollowup jobWorkFollowup = new JobWorkFollowup();
        //  jobWorkFollowup.setBuyercode(followupBuyer.getBuyercode());
        // jobWorkFollowup.setBuyername(followupBuyer.getBuyername());

        org.springframework.beans.BeanUtils.copyProperties(jobWorkFollowupBean, jobWorkFollowup);
        JobWorkFollowup result = jobWorkFollowupRepository.save(jobWorkFollowup);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, jobWorkFollowup.getId().toString())).body(result);
    }

    /**
     * {@code GET  /job-work-followups} : get all the jobWorkFollowups.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of jobWorkFollowups in body.
     */
    @GetMapping("/job-work-followups")
    public ResponseEntity<List<JobWorkFollowup>> getAllJobWorkFollowups(Pageable pageable) {
        log.debug("REST request to get a page of JobWorkFollowups");
        Page<JobWorkFollowup> page = jobWorkFollowupRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /job-work-followups} : get all the jobWorkFollowups.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of jobWorkFollowups in body.
     */
    @PostMapping("/job-work-followups-filter")
    public ResponseEntity<List<JobWorkFollowup>> getAllJobWorkFollowupsFilter(@RequestBody MasterSearch search) {
        log.debug("REST request to get a page of JobWorkFollowups");
        String jobworkcode = "%";
        if (search.getCode() != null && search.getCode()!=null) {
            jobworkcode = "%"+search.getCode().toUpperCase()+"%";
        }
        String jobworkname = "%";
        if (search.getDescription() != null && search.getDescription()!=null) {
            jobworkname = "%"+search.getDescription().toUpperCase()+"%";
        }
        search.setPage(
            PageRequest.of(
                search.getPageNo(),
                search.getSize(),
                search.getSortType() != null && search.getSortType().equalsIgnoreCase("desc")
                    ? Sort.by(search.getSort()).descending()
                    : Sort.by(search.getSort()).ascending()
            )
        );
        Page<JobWorkFollowup> page = jobWorkFollowupRepository.findAllByJobworkcodeAndJoAndJobworkname(jobworkcode, jobworkname, search.getPage());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /job-work-followups/:id} : get the "id" jobWorkFollowup.
     *
     * @param id the id of the jobWorkFollowup to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the jobWorkFollowup, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/job-work-followups/{id}")
    public ResponseEntity<JobWorkFollowup> getJobWorkFollowup(@PathVariable Long id) {
        log.debug("REST request to get JobWorkFollowup : {}", id);
        Optional<JobWorkFollowup> jobWorkFollowup = jobWorkFollowupRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(jobWorkFollowup);
    }

    /**
     * {@code DELETE  /job-work-followups/:id} : delete the "id" jobWorkFollowup.
     *
     * @param id the id of the jobWorkFollowup to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/job-work-followups/{id}")
    public ResponseEntity<Void> deleteJobWorkFollowup(@PathVariable Long id) {
        log.debug("REST request to delete JobWorkFollowup : {}", id);
        jobWorkFollowupRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code POST  /job-work-followups} : Create a new jobWorkFollowup.
     *
     * @param jobWorkFollowupBean the jobWorkFollowup to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new jobWorkFollowup, or with status {@code 400 (Bad Request)} if the jobWorkFollowup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/job-work-followups-schedule")
    public ResponseEntity<JobWorkFollowupScheduleBean> createJobWorkFollowup(@Valid @RequestBody JobWorkFollowupScheduleBean jobWorkFollowupScheduleBean) throws URISyntaxException, ParseException {
        log.debug("REST request to save JobWorkFollowup : {}", jobWorkFollowupScheduleBean);
        JobWorkFollowup jobWorkFollowup = jobWorkFollowupRepository.findById(jobWorkFollowupScheduleBean.getJobWorkFollowupId()).orElse(null);
        JobWorkFollowupSchedule jobWorkFollowupSchedule = jobWorkFollowupScheduleRepository.findByJobWorkFollowupIdAndFinYear(jobWorkFollowupScheduleBean.getJobWorkFollowupId(), Long.parseLong(jobWorkFollowupScheduleBean.getFinYear()));
        if(jobWorkFollowupSchedule != null) {
            jobWorkFollowupSchedule.setFinYear(Long.parseLong(jobWorkFollowupScheduleBean.getFinYear()));
            jobWorkFollowupSchedule.setSchType(jobWorkFollowupScheduleBean.getSchType());
            jobWorkFollowupSchedule.setOnDate(jobWorkFollowupScheduleBean.getOnDate());
            jobWorkFollowupSchedule.setRespReminder(jobWorkFollowupScheduleBean.getRespReminder());
            jobWorkFollowupSchedule.setLevel1Reminder(jobWorkFollowupScheduleBean.getLevel1Reminder());
            jobWorkFollowupSchedule.setLevel2Reminder(jobWorkFollowupScheduleBean.getLevel2Reminder());
            jobWorkFollowupSchedule.setJobWorkFollowup(jobWorkFollowup);
        } else {
            jobWorkFollowupSchedule = new JobWorkFollowupSchedule();
            jobWorkFollowupSchedule.setFinYear(Long.parseLong(jobWorkFollowupScheduleBean.getFinYear()));
            jobWorkFollowupSchedule.setSchType(jobWorkFollowupScheduleBean.getSchType());
            jobWorkFollowupSchedule.setOnDate(jobWorkFollowupScheduleBean.getOnDate());
            jobWorkFollowupSchedule.setRespReminder(jobWorkFollowupScheduleBean.getRespReminder());
            jobWorkFollowupSchedule.setLevel1Reminder(jobWorkFollowupScheduleBean.getLevel1Reminder());
            jobWorkFollowupSchedule.setLevel2Reminder(jobWorkFollowupScheduleBean.getLevel2Reminder());
            jobWorkFollowupSchedule.setJobWorkFollowup(jobWorkFollowup);
        }
        JobWorkFollowupSchedule result = jobWorkFollowupScheduleRepository.save(jobWorkFollowupSchedule);
        if (result != null && jobWorkFollowupScheduleBean.getMonthlyBeans() != null && jobWorkFollowupScheduleBean.getMonthlyBeans().size() > 0) {
            jobWorkFollowupDetailsRepository.deleteAllByJobWorkFollowupIdAndFinYear(result.getJobWorkFollowup().getId(), result.getFinYear());
            jobWorkFollowupReminderRepository.deleteAllByJobWorkFollowupIdAndFinYear(result.getJobWorkFollowup().getId(), result.getFinYear());
            for (MonthlyBean monthlyBean : jobWorkFollowupScheduleBean.getMonthlyBeans()) {
                if (monthlyBean.getDaysBeans() != null && monthlyBean.getDaysBeans().size() > 0) {
                    for (DaysBean daysBean : monthlyBean.getDaysBeans()) {
                        if (daysBean.getSelectDay().booleanValue() == true) {
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
                            Date date = simpleDateFormat.parse(daysBean.getDate());
                            JobWorkFollowupDetails jobWorkFollowupDetails = new JobWorkFollowupDetails();
                            jobWorkFollowupDetails.setFinYear(result.getFinYear());
                            jobWorkFollowupDetails.setJobWorkDate(DateUtils.asLocalDate(date));
                            jobWorkFollowupDetails.setJobWorkFollowup(result.getJobWorkFollowup());
                            JobWorkFollowupDetails resultDetails = jobWorkFollowupDetailsRepository.save(jobWorkFollowupDetails);
                            if (resultDetails != null) {
                                if (result.getRespReminder() != null && result.getRespReminder().intValue() > 0) {
                                    JobWorkFollowupReminder jobWorkFollowupReminder = new JobWorkFollowupReminder();
                                    jobWorkFollowupReminder.setFinYear(result.getFinYear());
                                    jobWorkFollowupReminder.setReminderType("RESP_REMINDER");

                                    Calendar cal = Calendar.getInstance();
                                    cal.setTime(date);
                                    //decrementing days by
                                    cal.add(Calendar.DATE, result.getRespReminder().intValue() * -1);

                                    jobWorkFollowupReminder.setReminderDate(DateUtils.asLocalDate(cal.getTime()));
                                    jobWorkFollowupReminder.setJobWorkFollowup(result.getJobWorkFollowup());
                                    jobWorkFollowupReminderRepository.save(jobWorkFollowupReminder);
                                }

                                if (result.getLevel1Reminder() != null && result.getLevel1Reminder().intValue() > 0) {
                                    JobWorkFollowupReminder jobWorkFollowupReminder = new JobWorkFollowupReminder();
                                    jobWorkFollowupReminder.setFinYear(result.getFinYear());
                                    jobWorkFollowupReminder.setReminderType("LEVEL1_REMINDER");

                                    Calendar cal = Calendar.getInstance();
                                    cal.setTime(date);
                                    //decrementing days by
                                    cal.add(Calendar.DATE, result.getLevel1Reminder().intValue() * -1);

                                    jobWorkFollowupReminder.setReminderDate(DateUtils.asLocalDate(cal.getTime()));
                                    jobWorkFollowupReminder.setJobWorkFollowup(result.getJobWorkFollowup());
                                    jobWorkFollowupReminderRepository.save(jobWorkFollowupReminder);
                                }

                                if (result.getLevel2Reminder() != null && result.getLevel2Reminder().intValue() > 0) {
                                    JobWorkFollowupReminder jobWorkFollowupReminder = new JobWorkFollowupReminder();
                                    jobWorkFollowupReminder.setFinYear(result.getFinYear());
                                    jobWorkFollowupReminder.setReminderType("LEVEL2_REMINDER");

                                    Calendar cal = Calendar.getInstance();
                                    cal.setTime(date);
                                    //decrementing days by
                                    cal.add(Calendar.DATE, result.getLevel2Reminder().intValue() * -1);

                                    jobWorkFollowupReminder.setReminderDate(DateUtils.asLocalDate(cal.getTime()));
                                    jobWorkFollowupReminder.setJobWorkFollowup(result.getJobWorkFollowup());
                                    jobWorkFollowupReminderRepository.save(jobWorkFollowupReminder);
                                }
                            }
                        }
                    }
                }
            }
        }
        return ResponseEntity.created(new URI("/api/job-work-followups/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString())).body(jobWorkFollowupScheduleBean);
    }

    /**
     * {@code GET  /job-work-followups} : get all the jobWorkFollowups.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of jobWorkFollowups in body.
     */
    @PostMapping("/job-work-followups-details-filter")
    public ResponseEntity<List<JobWorkFollowupDetails>> getAllJobWorkFollowupsDetailsFiter(@RequestBody MasterSearch search) {
        log.debug("REST request to get a page of JobWorkFollowups");

        search.setPage(
            PageRequest.of(
                search.getPageNo(),
                search.getSize(),
                search.getSortType() != null && search.getSortType().equalsIgnoreCase("desc")
                    ? Sort.by("jobWorkDate").descending()
                    : Sort.by("jobWorkDate").ascending()
            )
        );
        Page<JobWorkFollowupDetails> page = null;
        String user = SecurityUtils.getCurrentUserLogin().orElse(null);
        if(search.getCode() != null && search.getCode().equals("P")) {
            page = jobWorkFollowupDetailsRepository.findAllByPendingWorks(user, user, user, search.getPage());
        } else {
            page = jobWorkFollowupDetailsRepository.findAllByClosedWorks(user, user, user, search.getPage());
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /job-work-followups} : get all the jobWorkFollowups.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of jobWorkFollowups in body.
     */
    @GetMapping("/job-work-followups-details/{id}")
    public ResponseEntity<JobWorkFollowupDetails> getAllJobWorkFollowupsDetailsFiter(@PathVariable Long id) {
        log.debug("REST request to get a page of JobWorkFollowups");
        return ResponseUtil.wrapOrNotFound(jobWorkFollowupDetailsRepository.findById(id));
    }

    /**
     * {@code PUT  /followup-buyers} : Updates an existing followupBuyer.
     *
     * @param followupBuyerBean the followupBuyer to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated followupBuyer,
     * or with status {@code 400 (Bad Request)} if the followupBuyer is not valid,
     * or with status {@code 500 (Internal Server Error)} if the followupBuyer couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/job-work-followups-details")
    public ResponseEntity<JobWorkFollowupDetails> updateFollowupBuyer(@Valid @RequestBody JobWorkFollowupDetails jobWorkFollowupDetails) throws URISyntaxException {
        log.debug("REST request to update FollowupBuyer : {}", jobWorkFollowupDetails);
        if (jobWorkFollowupDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(jobWorkFollowupDetails.getId(), jobWorkFollowupDetails.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }
        if (!jobWorkFollowupDetailsRepository.existsById(jobWorkFollowupDetails.getId())) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        JobWorkFollowupDetails workFollowupDetails = jobWorkFollowupDetailsRepository.findById(jobWorkFollowupDetails.getId()).orElse(null);
        workFollowupDetails.setSubmitBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        workFollowupDetails.setSubmitDate(jobWorkFollowupDetails.getSubmitDate());
        workFollowupDetails.setRemarks(jobWorkFollowupDetails.getRemarks());
        JobWorkFollowupDetails result = jobWorkFollowupDetailsRepository.save(workFollowupDetails);
        if (result != null && result.getSubmitDate() != null) {
            List<JobWorkFollowupReminder> jobWorkFollowupReminders = jobWorkFollowupReminderRepository.findAllByJobWorkFollowupIdAndFinYear(result.getJobWorkFollowup().getId(), result.getJobWorkDate());
            for (JobWorkFollowupReminder jobWorkFollowupReminder : jobWorkFollowupReminders) {
                jobWorkFollowupReminder.setReminderSent(Instant.now());
                jobWorkFollowupReminder.setReminderSentMails("AUTO CLOSED");
                jobWorkFollowupReminderRepository.save(jobWorkFollowupReminder);
            }
        }
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, jobWorkFollowupDetails.getId().toString()))
            .body(result);
    }
}
