package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.domain.CompOffMaster;
import io.vamani.application.domain.HolidayMaster;
import io.vamani.application.mobile.*;
import io.vamani.application.model.*;
import io.vamani.application.mssql.domain.DayStatus;
import io.vamani.application.mssql.domain.EmployeeView;
import io.vamani.application.mssql.repository.DayStatusRepository;
import io.vamani.application.mssql.repository.EmployeeViewRepository;
import io.vamani.application.repository.CompOffMasterRepository;
import io.vamani.application.repository.HolidayMasterRepository;
import io.vamani.application.repository.UserRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.service.MailService;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.YearMonth;
import java.util.*;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

/**
 * REST controller for managing CompOffMaster.
 */
@RestController
@RequestMapping("/api")
public class CompOffMasterResource {

    private final Logger log = LoggerFactory.getLogger(CompOffMasterResource.class);

    private static final String ENTITY_NAME = "compOffMaster";

    private final CompOffMasterRepository compOffMasterRepository;

    @Autowired
    private EmployeeViewRepository employeeViewRepository;

    @Autowired
    private HolidayMasterRepository holidayMasterRepository;

    @Autowired
    private DayStatusRepository dayStatusRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private MailService mailService;

    @Autowired
    private ApplicationProperties applicationProperties;

    public CompOffMasterResource(CompOffMasterRepository compOffMasterRepository) {
        this.compOffMasterRepository = compOffMasterRepository;
    }

    /**
     * POST  /comp-off-masters : Create a new compOffMaster.
     *
     * @param compOffMaster the compOffMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new compOffMaster, or with status 400 (Bad Request) if the compOffMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/comp-off-masters")
    @Timed
    public ResponseEntity<CompOffMaster> createCompOffMaster(@Valid @RequestBody CompOffMaster compOffMaster) throws URISyntaxException {
        log.debug("REST request to save CompOffMaster : {}", compOffMaster);
        if (compOffMaster.getId() != null) {
            throw new BadRequestAlertException("A new compOffMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        if (compOffMaster.getUserCode() != null && compOffMaster.getUserCode().getId() == null) {
            compOffMaster.setUserCode(userRepository.findOneByLogin(compOffMaster.getUserCode().getLogin()).orElse(null));
        }
        compOffMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        compOffMaster.setCreatedDate(Instant.now());
        compOffMaster.setFlag("E");
        CompOffMaster result = compOffMasterRepository.save(compOffMaster);
        if (result != null) {
            EmployeeView requester = employeeViewRepository.findById(result.getUserCode().getLogin().toLowerCase()).orElse(null);
            EmployeeView approver = employeeViewRepository.findById(result.getHodApprovedBy().toLowerCase()).orElse(null);
            if (requester != null && requester != null) {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
                Locale locale = Locale.forLanguageTag("en");
                Context context = new Context(locale);
                context.setVariable("empCode", requester.getCardNo());
                context.setVariable("name", requester.getName());
                context.setVariable("compOffDate", format.format(Date.from(result.getCompOffDate())));
                context.setVariable("flag", result.getFlag());
                context.setVariable("remarks", result.getRemarks());
                context.setVariable("url", applicationProperties.getUrl());
                String content = null;
                String subject = "Comp Off sanction request for " + format.format(Date.from(compOffMaster.getCompOffDate()));
                try {
                    content = templateEngine.process("mail/comp_mail", context);
                    if (approver.getEmail() != null && approver.getEmail().length() > 0 && LeaveMasterResource.VALID_EMAIL_ADDRESS_REGEX.matcher(approver.getEmail()).find() == true) {
                        mailService.sendEmail(approver.getEmail(), subject, content, false, true);
                    }
                } catch (Exception e) {
                }
            }
        }
        return ResponseEntity.created(new URI("/api/comp-off-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /comp-off-masters : Updates an existing compOffMaster.
     *
     * @param compOffMaster the compOffMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated compOffMaster,
     * or with status 400 (Bad Request) if the compOffMaster is not valid,
     * or with status 500 (Internal Server Error) if the compOffMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/comp-off-masters")
    @Timed
    public ResponseEntity<Message> updateCompOffMaster(@Valid @RequestBody CompOffMaster[] compOffMasters) throws URISyntaxException {
        log.debug("REST request to update CompOffMaster : {}", compOffMasters);
        int ctr = 0;
        String error = "";
        for (CompOffMaster compOffMaster : compOffMasters) {
            compOffMaster.setHodApprovedDate(Instant.now());
            CompOffMaster result = compOffMasterRepository.save(compOffMaster);
            if (result != null) {
                ++ctr;
                EmployeeView requester = employeeViewRepository.findById(result.getUserCode().getLogin().toLowerCase()).orElse(null);
                if (requester != null && requester != null) {
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
                    Locale locale = Locale.forLanguageTag("en");
                    Context context = new Context(locale);
                    context.setVariable("empCode", requester.getCardNo());
                    context.setVariable("name", requester.getName());
                    context.setVariable("compOffDate", format.format(Date.from(result.getCompOffDate())));
                    context.setVariable("flag", result.getFlag());
                    context.setVariable("remarks", result.getRemarks());
                    context.setVariable("url", applicationProperties.getUrl());
                    String content = null;
                    String subject = null;
                    if (result.getFlag().equalsIgnoreCase("A")) {
                        subject = "Comp Off Request Approved by HOD for " + format.format(Date.from(compOffMaster.getCompOffDate()));
                    } else if (result.getFlag().equalsIgnoreCase("R")) {
                        subject = "Comp Off Request Rejected by HOD for " + format.format(Date.from(compOffMaster.getCompOffDate()));
                    }
                    try {
                        content = templateEngine.process("mail/comp_mail", context);
                        if (requester.getEmail() != null && requester.getEmail().length() > 0 && LeaveMasterResource.VALID_EMAIL_ADDRESS_REGEX.matcher(requester.getEmail()).find() == true) {
                            mailService.sendEmail(requester.getEmail(), subject, content, false, true);
                        }
                    } catch (Exception e) {
                    }
                }
            } else {
                error += compOffMaster.getId().toString() + ", ";
            }
        }
        if (ctr == compOffMasters.length) {
            return ResponseEntity.ok()
                .body(new Message("success", "Save Successfully!"));
        } else {
            return ResponseEntity.ok()
                .body(new Message("error", "Issue saving in these id's " + error));
        }
    }

    /**
     * GET  /comp-off-masters : get all the compOffMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of compOffMasters in body
     */
    @GetMapping("/comp-off-masters-mobile/{monthYear}")
    @Timed
    public ResponseEntity<CompOffMobileBean> getAllCompOffMasters(@PathVariable String monthYear) {
        log.debug("REST request to get a page of CompOffMasters");
        String[] arr = monthYear.split("-");
        YearMonth yearMonth = YearMonth.of( Integer.parseInt(arr[1]), Integer.parseInt(arr[0]) );  // January of 2015.
        Instant firstOfMonth = yearMonth.atDay( 1 ).atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant lastOfMonth = yearMonth.atEndOfMonth().atStartOfDay(ZoneId.systemDefault()).toInstant();
        CompOffMobileBean compOffMobileBean = new CompOffMobileBean();

        EmployeeView employeeView = employeeViewRepository.findById(SecurityUtils.getCurrentUserLogin().orElse(null)).orElse(null);
        List<CompOffMaster> compOffMasters = compOffMasterRepository.findAllByEmpCodeAndMonth(SecurityUtils.getCurrentUserLogin().orElse(null), firstOfMonth, lastOfMonth);
        if(compOffMasters != null && compOffMasters.size()>0) {
            compOffMobileBean.setExist(true);
            List<CompOffMobile> compOffMobiles = new ArrayList<>();
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            for (CompOffMaster compOffMaster : compOffMasters) {
                CompOffMobile compOffMobile = new CompOffMobile();
                compOffMobile.setId(compOffMaster.getId());
                if(compOffMaster.getAvailDate() != null) {
                    compOffMobile.setAvailDate(format.format(Date.from(compOffMaster.getAvailDate())));
                }
                compOffMobile.setBalance(compOffMaster.getBalance());
                compOffMobile.setCompOffDate(format.format(Date.from(compOffMaster.getCompOffDate())));
                compOffMobile.setFlag(compOffMaster.getFlag());
                compOffMobile.setRemarks(compOffMaster.getRemarks());
                compOffMobile.setTimeFrom(compOffMaster.getTimeFrom());
                compOffMobile.setTimeTo(compOffMaster.getTimeTo());
                compOffMobiles.add(compOffMobile);
            }
            compOffMobileBean.setCompOffMasters(compOffMobiles);
        } else {
            compOffMobileBean.setExist(false);
            compOffMobileBean.setErrorMessage("No Data Found");
        }
        compOffMobileBean.setHolidayMasters(holidayMasterRepository.findAllByFactoryMaster(employeeView.getFactory().trim()));
        return ResponseEntity.ok().body(compOffMobileBean);
    }

    /**
     * GET  /comp-off-masters : get all the compOffMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of compOffMasters in body
     */
    @GetMapping("/comp-off-masters")
    @Timed
    public ResponseEntity<List<CompOffMaster>> getAllCompOffMasters(Pageable pageable) {
        log.debug("REST request to get a page of CompOffMasters");
        Page<CompOffMaster> page = compOffMasterRepository.findAllByEmpCode(SecurityUtils.getCurrentUserLogin().orElse(null), pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/comp-off-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }



    /**
     * GET  /comp-off-masters : get all the compOffMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of compOffMasters in body
     */
    @GetMapping("/comp-off-masters-leave")
    @Timed
    public ResponseEntity<List<CompOffLeave>> getAllCompOffMastersLeave() {
        log.debug("REST request to get a page of CompOffMasters");
        List<CompOffMaster> compOffMasters = compOffMasterRepository.findAllByEmpCode(SecurityUtils.getCurrentUserLogin().orElse(null));
        List<CompOffLeave> compOffLeaves  = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        for(CompOffMaster compOffMaster : compOffMasters) {
            CompOffLeave compOffLeave = new CompOffLeave();
            compOffLeave.setId(compOffMaster.getId());
            compOffLeave.setCompOffDate(compOffMaster.getCompOffDate());
            compOffLeave.setCompOffDateView(simpleDateFormat.format(Date.from(compOffMaster.getCompOffDate())));
            compOffLeave.setBalance(compOffMaster.getBalance());
            compOffLeaves.add(compOffLeave);
        }
        return ResponseEntity.ok().body(compOffLeaves);
    }

    /**
     * GET  /comp-off-masters/:id : get the "id" compOffMaster.
     *
     * @param id the id of the compOffMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the compOffMaster, or with status 404 (Not Found)
     */
    @GetMapping("/comp-off-masters/{id}")
    @Timed
    public ResponseEntity<CompOffMaster> getCompOffMaster(@PathVariable Long id) {
        log.debug("REST request to get CompOffMaster : {}", id);
        Optional<CompOffMaster> compOffMaster = compOffMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(compOffMaster);
    }

    /**
     * GET  /comp-off-masters/:id : get the "id" compOffMaster.
     *
     * @param id the id of the compOffMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the compOffMaster, or with status 404 (Not Found)
     */
    @PostMapping("/comp-off-masters-fetch")
    @Timed
    public ResponseEntity<CompOffMaster> getCompOffMasterFetch(@RequestBody DateSearch eventSearch) throws ParseException {
        log.debug("REST request to get CompOffMaster : {}", eventSearch);
        String empCode = SecurityUtils.getCurrentUserLogin().orElse(null);
        EmployeeView employeeView = employeeViewRepository.findByCardNo(empCode).orElse(null);
        List<HolidayMaster> holidayMasters = holidayMasterRepository.findAllByFactoryMaster(employeeView.getFactory().trim());
        CompOffMaster compOffMaster = compOffMasterRepository.findByEmpCodeAndDate(empCode, eventSearch.getDate());
        if (compOffMaster != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Entry Already Exist")).build();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Date.from(eventSearch.getDate()));

        boolean validateDate = false;
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            validateDate = true;
        } else if (holidayMasters.size() > 0) {
            for (HolidayMaster holidayMaster : holidayMasters) {
                if (DateUtils.isSameDay(Date.from(holidayMaster.getHolidayDate().atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(eventSearch.getDate())))
                {
                    validateDate = true;
                }
            }
        }
        if (validateDate == true) {
            compOffMaster = new CompOffMaster();
            compOffMaster.setCompOffDate(eventSearch.getDate());

            Integer date = calendar.get(Calendar.DATE);
            Integer month = calendar.get(Calendar.MONTH) + 1;
            Integer year = calendar.get(Calendar.YEAR);
            List<Object[]> dayStatus = dayStatusRepository.findByIdAndDayNo(employeeView.getEmpCode(), date, month, year);
            if (dayStatus != null && dayStatus.size() > 0) {
                String inDate = "";
                String outDate = "";
                if (dayStatus.get(0)[0] != null) {
                    String inTime = dayStatus.get(0)[0].toString();
                    inTime = inTime.replaceAll("\\s", inTime);
                    compOffMaster.setTimeFrom(inTime.trim());
                }
                if (dayStatus.get(0)[1] != null) {
                    String outTime = dayStatus.get(0)[1].toString();
                    outTime = outTime.replaceAll("\\s", outTime);
                    compOffMaster.setTimeTo(outTime.trim());
                }
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);

                if (dayStatus.get(0)[3] != null) {
                    inDate = dayStatus.get(0)[3].toString();
                    inDate = inDate.replaceAll("\\s", "");
                }
                if (dayStatus.get(0)[4] != null) {
                    outDate = dayStatus.get(0)[4].toString();
                    outDate = outDate.replaceAll("\\s", "");
                }

                if (compOffMaster.getTimeTo() != null && compOffMaster.getTimeTo().length() > 0 && compOffMaster.getTimeFrom() != null && compOffMaster.getTimeFrom().length() > 0) {
                    Date dt = calendar.getTime();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
                    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
                    Date dateFrom = simpleDateFormat2.parse(inDate + " " + compOffMaster.getTimeFrom());
                    Date dateTo = simpleDateFormat2.parse(outDate + " " + compOffMaster.getTimeTo());

                    long diff = dateTo.getTime() - dateFrom.getTime();
                    long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);

                    if ((minutes / 60) >= 6 && minutes % 60 >= 0) {
                        compOffMaster.setBalance(1.0);
                    } else if ((minutes / 60) >= 3 && minutes % 60 >= 0) {
                        compOffMaster.setBalance(0.5);
                    } else {
                        return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Working hours less than 3 hours. Comp Off Request not applicable")).build();
                    }
                } else {
                    return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Please choose valid date")).build();
                }
            }

        } else {
            compOffMaster = new CompOffMaster();
            compOffMaster.setCompOffDate(eventSearch.getDate());

            Integer date = calendar.get(Calendar.DATE);
            Integer month = calendar.get(Calendar.MONTH) + 1;
            Integer year = calendar.get(Calendar.YEAR);
            List<Object[]> dayStatus = dayStatusRepository.findByIdAndDayNo(employeeView.getEmpCode(), date, month, year);
            if (dayStatus != null && dayStatus.size() > 0 && dayStatus.get(0)[2].toString() != null && dayStatus.get(0)[2].toString().startsWith("WO")) {
                String inDate = "";
                String outDate = "";
                if (dayStatus.get(0)[0] != null) {
                    String inTime = dayStatus.get(0)[0].toString();
                    inTime = inTime.replaceAll("\\s", inTime);
                    compOffMaster.setTimeFrom(inTime.trim());
                }
                if (dayStatus.get(0)[1] != null) {
                    String outTime = dayStatus.get(0)[1].toString();
                    outTime = outTime.replaceAll("\\s", outTime);
                    compOffMaster.setTimeTo(outTime.trim());
                }
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);

                if (dayStatus.get(0)[3] != null) {
                    inDate = dayStatus.get(0)[3].toString();
                    inDate = inDate.replaceAll("\\s", "");
                }
                if (dayStatus.get(0)[4] != null) {
                    outDate = dayStatus.get(0)[4].toString();
                    outDate = outDate.replaceAll("\\s", "");
                }

                if (compOffMaster.getTimeTo() != null && compOffMaster.getTimeTo().length() > 0 && compOffMaster.getTimeFrom() != null && compOffMaster.getTimeFrom().length() > 0) {
                    Date dt = calendar.getTime();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
                    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
                    Date dateFrom = simpleDateFormat2.parse(inDate + " " + compOffMaster.getTimeFrom());
                    Date dateTo = simpleDateFormat2.parse(outDate + " " + compOffMaster.getTimeTo());

                    long diff = dateTo.getTime() - dateFrom.getTime();
                    long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);

                    if ((minutes / 60) >= 6 && minutes % 60 >= 0) {
                        compOffMaster.setBalance(1.0);
                    } else if ((minutes / 60) >= 3 && minutes % 60 >= 0) {
                        compOffMaster.setBalance(0.5);
                    } else {
                        return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Working hours less than 3 hours. Comp Off Request not applicable")).build();
                    }
                } else {
                    return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Please choose valid date")).build();
                }
            } else {
                return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Please choose valid date")).build();
            }
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(compOffMaster));
    }

    /**
     * DELETE  /comp-off-masters/:id : delete the "id" compOffMaster.
     *
     * @param id the id of the compOffMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/comp-off-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteCompOffMaster(@PathVariable Long id) {
        log.debug("REST request to delete CompOffMaster : {}", id);
        CompOffMaster compOffMaster = compOffMasterRepository.findById(id).orElse(null);
        if (compOffMaster.getFlag() != null && compOffMaster.getFlag().equalsIgnoreCase("E")) {
            compOffMasterRepository.deleteById(id);
            return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
        } else {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Comp Off already " + (compOffMaster.getFlag().equalsIgnoreCase("A") ? "approved" : "rejected") + ". You can't delete.")).build();
        }
    }

    /**
     * GET  /leave-masters : get all the leaveMasters.
     *
     * @param !pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of leaveMasters in body
     */
    @PostMapping("/comp-off-masters-hod")
    @Timed
    public ResponseEntity<List<CompOffMaster>> getAllCompOffMasterByHod(@Valid @RequestBody LeaveSearch search) {
        log.debug("REST request to get a page of LeaveMasters");
        String empCode = "%";
        if (search.getEmpCode() != null) {
            empCode = search.getEmpCode() + "%";
        }
        Page<CompOffMaster> page = null;
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("id").descending()));
        if(search.getLeaveStatus() != null && search.getLeaveStatus().equalsIgnoreCase("P")) {
            page = compOffMasterRepository.findAllByHodApprovedByPending(SecurityUtils.getCurrentUserLogin().orElse(null), empCode, search.getPage());
        } else if(search.getLeaveStatus() != null && search.getLeaveStatus().equalsIgnoreCase("A")) {
            page = compOffMasterRepository.findAllByHodApprovedByApproved(SecurityUtils.getCurrentUserLogin().orElse(null), search.getLeaveDateFrom(), search.getLeaveDateTo(), empCode, search.getPage());
        } else {
            page = compOffMasterRepository.findAllByHodApprovedByRejected(SecurityUtils.getCurrentUserLogin().orElse(null), search.getLeaveDateFrom(), search.getLeaveDateTo(), empCode, search.getLeaveStatus(), search.getPage());
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/comp-off-masters-hod");

        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }


    /**
     * GET  /comp-off-masters : get all the compOffMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of compOffMasters in body
     */
    @GetMapping("/comp-off-masters-approval-mobile/{monthYear}")
    @Timed
    public ResponseEntity<CompOffMobileApprovalBean> getAllCompOffMastersApproval(@PathVariable String monthYear) throws ParseException{
        log.debug("REST request to get a page of CompOffMasters");
        String[] arr = monthYear.split("-");

        YearMonth yearMonth = YearMonth.of( Integer.parseInt(arr[1]), Integer.parseInt(arr[0]) );  // January of 2015.
        Instant firstOfMonth = yearMonth.atDay( 1 ).atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant lastOfMonth = yearMonth.atEndOfMonth().atStartOfDay(ZoneId.systemDefault()).toInstant();
        CompOffMobileApprovalBean compOffMobileApprovalBean = new CompOffMobileApprovalBean();

        EmployeeView employeeView = employeeViewRepository.findById(SecurityUtils.getCurrentUserLogin().orElse(null)).orElse(null);

        List<CompOffMaster> compOffMasters = compOffMasterRepository.findAllByEmpCodeAndMonthApproval(SecurityUtils.getCurrentUserLogin().orElse(null), firstOfMonth, lastOfMonth);
        if(compOffMasters != null && compOffMasters.size()>0) {
            compOffMobileApprovalBean.setExist(true);
            List<CompOffApprovalMaster> compOffApprovalMasters = new ArrayList<>();
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Map<String, Set<String>> listMap = new HashMap<>();
            Map<String, List<CompOffMasterMobile>> compOffMasterMap = new HashMap<>();
            for (CompOffMaster compOffMaster : compOffMasters) {
                String date = format.format(Date.from(compOffMaster.getCompOffDate()));
                if (listMap.containsKey(date)) {
                    List<CompOffMasterMobile> masters = compOffMasterMap.get(date);
                    CompOffMasterMobile compOffMasterMobile = new CompOffMasterMobile();
                    BeanUtils.copyProperties(compOffMaster, compOffMasterMobile);
                    compOffMasterMobile.setCompOffDateView(format.format(Date.from(compOffMaster.getCompOffDate())));
                    compOffMasterMobile.setHodApprovedDateView(compOffMaster.getHodApprovedDate() != null ? format.format(Date.from(compOffMaster.getHodApprovedDate())) : null);
                    compOffMasterMobile.setAvailDateView(compOffMaster.getAvailDate() != null ? format.format(Date.from(compOffMaster.getAvailDate())) : null);
                    compOffMasterMobile.setEmpCode(compOffMaster.getUserCode().getLogin());
                    compOffMasterMobile.setEmpName(compOffMaster.getUserCode().getFirstName() + (compOffMaster.getUserCode().getLastName() != null ? " " + compOffMaster.getUserCode().getLastName() : ""));
                    masters.add(compOffMasterMobile);
                    compOffMasterMap.put(date, masters);

                    Set<String> strings = listMap.get(date);
                    strings.add(compOffMaster.getFlag());
                    listMap.put(date, strings);
                } else {
                    List<CompOffMasterMobile> masters = new ArrayList<>();
                    CompOffMasterMobile compOffMasterMobile = new CompOffMasterMobile();
                    BeanUtils.copyProperties(compOffMaster, compOffMasterMobile);
                    compOffMasterMobile.setCompOffDateView(format.format(Date.from(compOffMaster.getCompOffDate())));
                    compOffMasterMobile.setHodApprovedDateView(compOffMaster.getHodApprovedDate() != null ? format.format(Date.from(compOffMaster.getHodApprovedDate())) : null);
                    compOffMasterMobile.setAvailDateView(compOffMaster.getAvailDate() != null ? format.format(Date.from(compOffMaster.getAvailDate())) : null);
                    compOffMasterMobile.setEmpCode(compOffMaster.getUserCode().getLogin());
                    compOffMasterMobile.setEmpName(compOffMaster.getUserCode().getFirstName() + (compOffMaster.getUserCode().getLastName() != null ? " " + compOffMaster.getUserCode().getLastName() : ""));
                    masters.add(compOffMasterMobile);
                    compOffMasterMap.put(date, masters);

                    Set<String> strings = new HashSet<>();
                    strings.add(compOffMaster.getFlag());
                    listMap.put(date, strings);
                }
            }
            for (String key : listMap.keySet()) {
                CompOffApprovalMaster compOffApprovalMaster = new CompOffApprovalMaster();
                compOffApprovalMaster.setCompOffDate(key);
                compOffApprovalMaster.setDate(format.parse(key));
                if (listMap.get(key).contains("E")) {
                    compOffApprovalMaster.setFlag("Y");
                } else if (listMap.get(key).contains("A")) {
                    compOffApprovalMaster.setFlag("O");
                } else if (listMap.get(key).contains("R")) {
                    compOffApprovalMaster.setFlag("R");
                }
                compOffApprovalMaster.setCompOffMasters(compOffMasterMap.get(key));
                compOffApprovalMasters.add(compOffApprovalMaster);

                if(compOffApprovalMasters != null && compOffApprovalMasters.size()>0){
                    Collections.sort(compOffApprovalMasters, Comparator.comparing(CompOffApprovalMaster::getDate));
                }
            }
            compOffMobileApprovalBean.setCompOffApprovalMasters(compOffApprovalMasters);
        } else {
            compOffMobileApprovalBean.setExist(false);
            compOffMobileApprovalBean.setErrorMessage("No Data Found");
        }
        compOffMobileApprovalBean.setHolidayMasters(holidayMasterRepository.findAllByFactoryMaster(employeeView.getFactory().trim()));
        return ResponseEntity.ok().body(compOffMobileApprovalBean);
    }

    /**
     * DELETE  /comp-off-masters/:id : delete the "id" compOffMaster.
     *
     * @param id the id of the compOffMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @GetMapping("/comp-off-masters-mobile-approval/{id}/{flag}")
    @Timed
    public ResponseEntity<Message> approvalCompOffMaster(@PathVariable Long id, @PathVariable String flag) {
        log.debug("REST request to Approval CompOffMaster : {}", id);
        CompOffMaster compOffMaster = compOffMasterRepository.findById(id).orElse(null);
        if (compOffMaster.getFlag() != null && compOffMaster.getFlag().equalsIgnoreCase("E")) {
            compOffMaster.setFlag(flag);
            compOffMaster.setHodApprovedDate(Instant.now());
            CompOffMaster result = compOffMasterRepository.save(compOffMaster);
            if (result != null) {
                EmployeeView requester = employeeViewRepository.findById(result.getUserCode().getLogin().toLowerCase()).orElse(null);
                if (requester != null && requester != null) {
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
                    Locale locale = Locale.forLanguageTag("en");
                    Context context = new Context(locale);
                    context.setVariable("empCode", requester.getCardNo());
                    context.setVariable("name", requester.getName());
                    context.setVariable("compOffDate", format.format(Date.from(result.getCompOffDate())));
                    context.setVariable("flag", result.getFlag());
                    context.setVariable("remarks", result.getRemarks());
                    context.setVariable("url", applicationProperties.getUrl());
                    String content = null;
                    String subject = null;
                    if (result.getFlag().equalsIgnoreCase("A")) {
                        subject = "Comp Off Request Approved by HOD for " + format.format(Date.from(compOffMaster.getCompOffDate()));
                    } else if (result.getFlag().equalsIgnoreCase("R")) {
                        subject = "Comp Off Request Rejected by HOD for " + format.format(Date.from(compOffMaster.getCompOffDate()));
                    }
                    try {
                        content = templateEngine.process("mail/comp_mail", context);
                        if (requester.getEmail() != null && requester.getEmail().length() > 0 && LeaveMasterResource.VALID_EMAIL_ADDRESS_REGEX.matcher(requester.getEmail()).find() == true) {
                            mailService.sendEmail(requester.getEmail(), subject, content, false, true);
                        }
                    } catch (Exception e) {
                    }
                }
            }
            return ResponseEntity.ok().body(new Message("Save successfully!", "success", true, ""));
        } else {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Comp Off already " + (compOffMaster.getFlag().equalsIgnoreCase("A") ? "approved" : "rejected") + ".")).body(new Message("Comp Off already " + (compOffMaster.getFlag().equalsIgnoreCase("A") ? "approved" : "rejected") + ".", "error", false, "Comp Off already " + (compOffMaster.getFlag().equalsIgnoreCase("A") ? "approved" : "rejected") + "."));
        }
    }

}
