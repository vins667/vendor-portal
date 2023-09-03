package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.config.FirebaseConfigration;
import io.vamani.application.domain.*;
import io.vamani.application.domain.User;
import io.vamani.application.firebase.FirebaseSystem;
import io.vamani.application.mobile.*;
import io.vamani.application.model.*;
import io.vamani.application.mssql.domain.DayStatus;
import io.vamani.application.mssql.domain.EmployeeView;
import io.vamani.application.mssql.repository.DayStatusRepository;
import io.vamani.application.mssql.repository.EmployeeViewRepository;
import io.vamani.application.repository.*;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.service.MailService;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Pattern;

/**
 * REST controller for managing LeaveMaster.
 */
@RestController
@RequestMapping("/api")
public class LeaveMasterResource {

    private final Logger log = LoggerFactory.getLogger(LeaveMasterResource.class);

    private static final String ENTITY_NAME = "leaveMaster";

    private final LeaveMasterRepository leaveMasterRepository;

    @Autowired
    private LeaveMasterRemarksDetailsRepository leaveMasterRemarksDetailsRepository;

    @Autowired
    private EmployeeViewRepository employeeViewRepository;

    @Autowired
    private MobileAttendanceRepository mobileAttendanceRepository;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private MailService mailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HolidayMasterRepository holidayMasterRepository;

    @Autowired
    private LeaveTypeMasterRepository leaveTypeMasterRepository;

    @Autowired
    private LeaveSubTypeMasterRepository leaveSubTypeMasterRepository;

    @Autowired
    private CompOffMasterRepository compOffMasterRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private FirebaseMessageRepository firebaseMessageRepository;

    @Autowired
    private FirebaseConfigration firebaseConfigration;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private final DayStatusRepository dayStatusRepository;

    public LeaveMasterResource(LeaveMasterRepository leaveMasterRepository,
                               DayStatusRepository dayStatusRepository) {
        this.leaveMasterRepository = leaveMasterRepository;
        this.dayStatusRepository = dayStatusRepository;
    }

    /**
     * POST  /leave-masters : Create a new leaveMaster.
     *
     * @param leaveMaster the leaveMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new leaveMaster, or with status 400 (Bad Request) if the leaveMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/leave-masters")
    @Timed
    public ResponseEntity<LeaveMaster> createLeaveMaster(@Valid @RequestBody LeaveMasterBean leaveMasterBean) throws URISyntaxException {
        log.debug("REST request to save LeaveMaster : {}", leaveMasterBean);
        if (leaveMasterBean.getId() != null) {
            throw new BadRequestAlertException("A new leaveMasterBean cannot already have an ID", ENTITY_NAME, "idexists");
        }

        if (leaveMasterBean.getUserCode() != null && leaveMasterBean.getUserCode().getId() == null) {
            leaveMasterBean.setUserCode(userRepository.findOneByLogin(leaveMasterBean.getUserCode().getLogin()).orElse(null));
        }

        if (leaveMasterBean.getLeaveTypeMaster() != null && leaveMasterBean.getLeaveTypeMaster().getLeaveCode() == null) {
            leaveMasterBean.setLeaveTypeMaster(leaveTypeMasterRepository.findById(leaveMasterBean.getLeaveTypeMaster().getId()).orElse(null));
        }

        if (leaveMasterBean.getLeaveSubTypeMaster() != null && leaveMasterBean.getLeaveSubTypeMaster().getSubTypeCode() == null) {
            leaveMasterBean.setLeaveSubTypeMaster(leaveSubTypeMasterRepository.findById(leaveMasterBean.getLeaveSubTypeMaster().getId()).orElse(null));
        }

        leaveMasterBean.setLeaveDateFrom(removeTime(leaveMasterBean.getLeaveDateFrom()));
        leaveMasterBean.setLeaveDateTo(removeTime(leaveMasterBean.getLeaveDateTo()));

        Instant from = leaveMasterBean.getLeaveDateFrom();
        java.util.Date allowDate = allowDateEmployee(java.util.Date.from(leaveMasterBean.getLeaveDateFrom()));
        if(allowDate.before(new java.util.Date())) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Leave Entry is  not allowed. Previous Month salary locked.")).body(null);
        }

        if(leaveMasterBean.getLeaveTypeMaster() != null && leaveMasterBean.getLeaveTypeMaster().getLeaveCode().equalsIgnoreCase("ML")) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Leave Entry is  not allowed Medical Leave Type.")).body(null);
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        EmployeeView employeeView = employeeViewRepository.findByCardNo(leaveMasterBean.getUserCode().getLogin()).orElse(null);
        List<HolidayMaster> holidayMasters = holidayMasterRepository.findAllByFactoryMaster(employeeView.getFactory().trim());
        List<LeaveMaster> leaveMasters = leaveMasterRepository.findByEmpCodeAndLeaveDate(leaveMasterBean.getUserCode().getLogin(), leaveMasterBean.getLeaveDateFrom());
        if (leaveMasters != null && leaveMasters.size() > 0) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Leave Date Range already Exist")).body(null);
        }
        leaveMasters = leaveMasterRepository.findByEmpCodeAndLeaveDate(leaveMasterBean.getUserCode().getLogin(), leaveMasterBean.getLeaveDateTo());
        if (leaveMasters != null && leaveMasters.size() > 0) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Leave Date Range already Exist")).body(null);
        }
        leaveMasters = leaveMasterRepository.findByEmpCodeAndLeaveDateFrom(leaveMasterBean.getUserCode().getLogin(), leaveMasterBean.getLeaveDateFrom(), leaveMasterBean.getLeaveDateTo());
        if (leaveMasters != null && leaveMasters.size() > 0) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Leave Date Range already Exist")).body(null);
        }
        leaveMasters = leaveMasterRepository.findByEmpCodeAndLeaveDateTo(leaveMasterBean.getUserCode().getLogin(), leaveMasterBean.getLeaveDateFrom(), leaveMasterBean.getLeaveDateTo());
        if (leaveMasters != null && leaveMasters.size() > 0) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Leave Date Range already Exist")).body(null);
        }
        if ((leaveMasterBean.getLeaveTypeMaster().getLeaveCode() != null && (leaveMasterBean.getLeaveTypeMaster().getLeaveCode().equalsIgnoreCase("CL") || leaveMasterBean.getLeaveTypeMaster().getLeaveCode().equalsIgnoreCase("SL")))
            && (leaveMasterBean.getLeaveSubTypeMaster().getSubTypeCode() != null && (leaveMasterBean.getLeaveSubTypeMaster().getSubTypeCode().equalsIgnoreCase("CL") || leaveMasterBean.getLeaveSubTypeMaster().getSubTypeCode().equalsIgnoreCase("SL")))) {
            Instant prevDate = prevNextDays(leaveMasterBean.getLeaveDateFrom(), holidayMasters, -1L);
            if (leaveMasterBean.getLeaveTypeMaster().getLeaveCode().equalsIgnoreCase("CL")) {
                leaveMasters = leaveMasterRepository.findByEmpCodeAndLeaveDateAndLeaveType(leaveMasterBean.getUserCode().getLogin(), prevDate, "SL", "sl2");
                if (leaveMasters != null && leaveMasters.size() > 0) {
                    return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "You have taken Sick Leave on " + simpleDateFormat.format(Date.from(prevDate)) + ". You can't apply Casual Leave.")).body(null);
                }
                leaveMasters = leaveMasterRepository.findByEmpCodeAndLeaveDateAndLeaveType(leaveMasterBean.getUserCode().getLogin(), prevDate, "SL", "SL");
                if (leaveMasters != null && leaveMasters.size() > 0) {
                    return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "You have taken Sick Leave on " + simpleDateFormat.format(Date.from(prevDate)) + ". You can't apply Casual Leave.")).body(null);
                }

                leaveMasters = leaveMasterRepository.findByEmpCodeAndLeaveDateAndLeaveType(leaveMasterBean.getUserCode().getLogin(), prevDate, "CL");
                if (leaveMasters != null && leaveMasters.size() > 0) {
                } else {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(Date.from(prevDate));
                    if (calendar.getTime().getMonth() == Date.from(leaveMasterBean.getLeaveDateFrom()).getMonth()) {
                        DayStatus dayStatus = dayStatusRepository.findByEmpCodeAndDate(employeeView.getEmpCode(), calendar.get(Calendar.DATE), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));
                        if (dayStatus != null && dayStatus.getStatus() != null && dayStatus.getStatus().trim().equalsIgnoreCase("A")) {
                            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Apply previous Day " + simpleDateFormat.format(Date.from(prevDate)) + " leave.")).body(null);
                        }
                    }
                }
            } else {
                leaveMasters = leaveMasterRepository.findByEmpCodeAndLeaveDateAndLeaveType(leaveMasterBean.getUserCode().getLogin(), prevDate, "CL", "cl2");
                if (leaveMasters != null && leaveMasters.size() > 0) {
                    return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "You have taken Casual Leave on " + simpleDateFormat.format(Date.from(prevDate)) + ". You can't apply Sick Leave.")).body(null);
                }
                leaveMasters = leaveMasterRepository.findByEmpCodeAndLeaveDateAndLeaveType(leaveMasterBean.getUserCode().getLogin(), prevDate, "CL", "CL");
                if (leaveMasters != null && leaveMasters.size() > 0) {
                    return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "You have taken Casual Leave on " + simpleDateFormat.format(Date.from(prevDate)) + ". You can't apply Sick Leave.")).body(null);
                }
                leaveMasters = leaveMasterRepository.findByEmpCodeAndLeaveDateAndLeaveType(leaveMasterBean.getUserCode().getLogin(), prevDate, "SL");
                if (leaveMasters != null && leaveMasters.size() > 0) {
                } else {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(Date.from(prevDate));
                    if (calendar.getTime().getMonth() == Date.from(leaveMasterBean.getLeaveDateFrom()).getMonth()) {
                        DayStatus dayStatus = dayStatusRepository.findByEmpCodeAndDate(employeeView.getEmpCode(), calendar.get(Calendar.DATE), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));
                        if (dayStatus != null && dayStatus.getStatus() != null && dayStatus.getStatus().trim().equalsIgnoreCase("A")) {
                            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Apply previous Day " + simpleDateFormat.format(Date.from(prevDate)) + " leave.")).body(null);
                        }
                    }
                }
            }

            Instant NextDate = prevNextDays(leaveMasterBean.getLeaveDateTo(), holidayMasters, 1L);
            if (leaveMasterBean.getLeaveTypeMaster().getLeaveCode().equalsIgnoreCase("CL")) {
                leaveMasters = leaveMasterRepository.findByEmpCodeAndLeaveDateAndLeaveType(leaveMasterBean.getUserCode().getLogin(), NextDate, "SL", "sl1");
                if (leaveMasters != null && leaveMasters.size() > 0) {
                    return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "You have taken Sick Leave on " + simpleDateFormat.format(Date.from(NextDate)) + ". You can't apply Casual Leave.")).body(null);
                }
                leaveMasters = leaveMasterRepository.findByEmpCodeAndLeaveDateAndLeaveType(leaveMasterBean.getUserCode().getLogin(), NextDate, "SL", "SL");
                if (leaveMasters != null && leaveMasters.size() > 0) {
                    return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "You have taken Sick Leave on " + simpleDateFormat.format(Date.from(NextDate)) + ". You can't apply Casual Leave.")).body(null);
                }
            } else {
                leaveMasters = leaveMasterRepository.findByEmpCodeAndLeaveDateAndLeaveType(leaveMasterBean.getUserCode().getLogin(), NextDate, "CL", "cl1");
                if (leaveMasters != null && leaveMasters.size() > 0) {
                    return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "You have taken Casual Leave on " + simpleDateFormat.format(Date.from(NextDate)) + ". You can't apply Sick Leave.")).body(null);
                }
                leaveMasters = leaveMasterRepository.findByEmpCodeAndLeaveDateAndLeaveType(leaveMasterBean.getUserCode().getLogin(), NextDate, "CL", "CL");
                if (leaveMasters != null && leaveMasters.size() > 0) {
                    return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "You have taken Casual Leave on " + simpleDateFormat.format(Date.from(NextDate)) + ". You can't apply Sick Leave.")).body(null);
                }
            }
        } else if ((leaveMasterBean.getLeaveTypeMaster().getLeaveCode() != null && (leaveMasterBean.getLeaveTypeMaster().getLeaveCode().equalsIgnoreCase("CL") || leaveMasterBean.getLeaveTypeMaster().getLeaveCode().equalsIgnoreCase("SL")))
            && (leaveMasterBean.getLeaveSubTypeMaster().getSubTypeCode() != null && (leaveMasterBean.getLeaveSubTypeMaster().getSubTypeCode().equalsIgnoreCase("cl1") || leaveMasterBean.getLeaveSubTypeMaster().getSubTypeCode().equalsIgnoreCase("sl1")))) {
            Instant prevDate = prevNextDays(leaveMasterBean.getLeaveDateFrom(), holidayMasters, -1L);
            if (leaveMasterBean.getLeaveTypeMaster().getLeaveCode().equalsIgnoreCase("CL")) {
                leaveMasters = leaveMasterRepository.findByEmpCodeAndLeaveDateAndLeaveType(leaveMasterBean.getUserCode().getLogin(), prevDate, "SL", "sl2");
                if (leaveMasters != null && leaveMasters.size() > 0) {
                    return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "You have taken Sick Leave on " + simpleDateFormat.format(Date.from(prevDate)) + ". You can't apply Casual Leave.")).body(null);
                }
                leaveMasters = leaveMasterRepository.findByEmpCodeAndLeaveDateAndLeaveType(leaveMasterBean.getUserCode().getLogin(), prevDate, "SL", "SL");
                if (leaveMasters != null && leaveMasters.size() > 0) {
                    return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "You have taken Sick Leave on " + simpleDateFormat.format(Date.from(prevDate)) + ". You can't apply Casual Leave.")).body(null);
                }
            } else {
                leaveMasters = leaveMasterRepository.findByEmpCodeAndLeaveDateAndLeaveType(leaveMasterBean.getUserCode().getLogin(), prevDate, "CL", "cl2");
                if (leaveMasters != null && leaveMasters.size() > 0) {
                    return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "You have taken Casual Leave on " + simpleDateFormat.format(Date.from(prevDate)) + ". You can't apply Sick Leave.")).body(null);
                }
                leaveMasters = leaveMasterRepository.findByEmpCodeAndLeaveDateAndLeaveType(leaveMasterBean.getUserCode().getLogin(), prevDate, "CL", "CL");
                if (leaveMasters != null && leaveMasters.size() > 0) {
                    return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "You have taken Casual Leave on " + simpleDateFormat.format(Date.from(prevDate)) + ". You can't apply Sick Leave.")).body(null);
                }
            }
        } else if ((leaveMasterBean.getLeaveTypeMaster().getLeaveCode() != null && (leaveMasterBean.getLeaveTypeMaster().getLeaveCode().equalsIgnoreCase("CL") || leaveMasterBean.getLeaveTypeMaster().getLeaveCode().equalsIgnoreCase("SL")))
            && (leaveMasterBean.getLeaveSubTypeMaster().getSubTypeCode() != null && (leaveMasterBean.getLeaveSubTypeMaster().getSubTypeCode().equalsIgnoreCase("cl2") || leaveMasterBean.getLeaveSubTypeMaster().getSubTypeCode().equalsIgnoreCase("sl2")))) {
            Instant NextDate = prevNextDays(leaveMasterBean.getLeaveDateTo(), holidayMasters, 1L);
            if (leaveMasterBean.getLeaveTypeMaster().getLeaveCode().equalsIgnoreCase("CL")) {
                leaveMasters = leaveMasterRepository.findByEmpCodeAndLeaveDateAndLeaveType(leaveMasterBean.getUserCode().getLogin(), NextDate, "SL", "sl1");
                if (leaveMasters != null && leaveMasters.size() > 0) {
                    return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "You have taken Sick Leave on " + simpleDateFormat.format(Date.from(NextDate)) + ". You can't apply Casual Leave.")).body(null);
                }
                leaveMasters = leaveMasterRepository.findByEmpCodeAndLeaveDateAndLeaveType(leaveMasterBean.getUserCode().getLogin(), NextDate, "SL", "SL");
                if (leaveMasters != null && leaveMasters.size() > 0) {
                    return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "You have taken Sick Leave on " + simpleDateFormat.format(Date.from(NextDate)) + ". You can't apply Casual Leave.")).body(null);
                }
            } else {
                leaveMasters = leaveMasterRepository.findByEmpCodeAndLeaveDateAndLeaveType(leaveMasterBean.getUserCode().getLogin(), NextDate, "CL", "cl1");
                if (leaveMasters != null && leaveMasters.size() > 0) {
                    return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "You have taken Casual Leave on " + simpleDateFormat.format(Date.from(NextDate)) + ". You can't apply Sick Leave.")).body(null);
                }
                leaveMasters = leaveMasterRepository.findByEmpCodeAndLeaveDateAndLeaveType(leaveMasterBean.getUserCode().getLogin(), NextDate, "CL", "CL");
                if (leaveMasters != null && leaveMasters.size() > 0) {
                    return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "You have taken Casual Leave on " + simpleDateFormat.format(Date.from(NextDate)) + ". You can't apply Sick Leave.")).body(null);
                }
            }
        }
        LeaveMaster leaveMaster = new LeaveMaster();
        BeanUtils.copyProperties(leaveMasterBean, leaveMaster);
        leaveMaster.createdBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        leaveMaster.createdDate(Instant.now());
        leaveMaster.setFactory(employeeView.getFactory());
        LeaveMaster result = leaveMasterRepository.save(leaveMaster);
        EmployeeView requester = employeeViewRepository.findById(leaveMaster.getUserCode().getLogin().toLowerCase()).orElse(null);
        EmployeeView approver = employeeViewRepository.findById(leaveMaster.getHodApprovedBy().toLowerCase()).orElse(null);
        if (requester != null && requester != null) {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
            Locale locale = Locale.forLanguageTag("en");
            Context context = new Context(locale);
            context.setVariable("leaveTypeCode", leaveMaster.getLeaveTypeMaster().getLeaveCode());
            context.setVariable("leaveTypeName", leaveMaster.getLeaveTypeMaster().getLeaveName());
            context.setVariable("empCode", requester.getCardNo());
            context.setVariable("name", requester.getName());
            context.setVariable("leaveDateFrom", format.format(Date.from(leaveMaster.getLeaveDateFrom())));
            context.setVariable("leaveDateTo", format.format(Date.from(leaveMaster.getLeaveDateTo())));
            context.setVariable("flag", leaveMaster.getFlag());
            context.setVariable("reason", leaveMaster.getReason());
            context.setVariable("url", applicationProperties.getUrl());
            String content = null;
            String subject = "Leave sanction request for " + leaveMaster.getLeaveTypeMaster().getLeaveName();
            try {
                content = templateEngine.process("mail/leave_mail", context);
                if (approver.getEmail() != null && approver.getEmail().length() > 0 && VALID_EMAIL_ADDRESS_REGEX.matcher(approver.getEmail()).find() == true) {
                    mailService.sendEmail(approver.getEmail(), subject, content, false, true);
                }
            } catch (Exception e) {
            }
        }
        User user = userRepository.findOneByLogin(leaveMaster.getHodApprovedBy().toLowerCase()).orElse(null);
        if (user != null && user.getDeviceId() != null && user.getDeviceId().length() > 0) {
            String body = "Leave sanction request for " + leaveMaster.getLeaveTypeMaster().getLeaveName() + " by " + requester.getName() + ".";
            FirebaseMessage firebaseMessage = new FirebaseMessage();
            firebaseMessage.setBody(body);
            firebaseMessage.setCreatedBy(leaveMasterBean.getUserCode().getLogin());
            firebaseMessage.setCreatedDate(Instant.now());
            firebaseMessage.setStatus("Pending");
            firebaseMessage.setType("Leave Approval");
            FirebaseMessage message = firebaseMessageRepository.save(firebaseMessage);
            if(message != null) {
                try {
                    int status = firebaseConfigration.postFirebase(new FirebaseSystem(user.getDeviceId(), message.getType(), message.getStatus(), message.getBody()));
                    if (status == 200) {
                        message.setSuccessStatus("Y");
                        message = firebaseMessageRepository.save(message);
                    }
                } catch(Exception e) {

                }
            }
        }
        if (leaveMasterBean.getLeaveTypeMaster() != null && leaveMasterBean.getLeaveTypeMaster().getLeaveCode().equalsIgnoreCase("OD")
            && leaveMasterBean.getMobileAttendances() != null && leaveMasterBean.getMobileAttendances().size() > 0) {
            List<MobileAttendance> mobileAttendances = leaveMasterBean.getMobileAttendances();
            for (MobileAttendance mobileAttendance : mobileAttendances) {
                mobileAttendance.setLeaveMasterId(result.getId());
                mobileAttendanceRepository.save(mobileAttendance);
            }
        }

        if (leaveMasterBean.getLeaveTypeMaster() != null && leaveMasterBean.getLeaveTypeMaster().getLeaveCode().equalsIgnoreCase("CO")
            && leaveMasterBean.getCompOffMasterId() != null) {
            CompOffMaster compOffMaster = compOffMasterRepository.findById(leaveMasterBean.getCompOffMasterId()).orElse(null);
            compOffMaster.setAvailDate(result.getLeaveDateFrom());
            compOffMasterRepository.save(compOffMaster);
        }
        return ResponseEntity.created(new URI("/api/leave-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * POST  /leave-masters : Create a new leaveMaster.
     *
     * @param leaveMaster the leaveMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new leaveMaster, or with status 400 (Bad Request) if the leaveMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/leave-masters-custom")
    @Timed
    public ResponseEntity<LeaveMaster> createHrLeaveMaster(@Valid @RequestBody LeaveMaster leaveMaster) throws URISyntaxException {
        log.debug("REST request to save LeaveMaster : {}", leaveMaster);
        if (leaveMaster.getId() != null) {
            throw new BadRequestAlertException("A new leaveMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EmployeeView employeeView = employeeViewRepository.findByCardNo(leaveMaster.getUserCode().getLogin()).orElse(null);
        HttpHeaders headers = HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Leave Date Range already Exist");
        List<LeaveMaster> leaveMasters = leaveMasterRepository.findByEmpCodeAndLeaveDate(leaveMaster.getUserCode().getLogin(), leaveMaster.getLeaveDateFrom());
        if (leaveMasters != null && leaveMasters.size() > 0) {
            return ResponseEntity.badRequest().headers(headers).body(null);
        }
        leaveMasters = leaveMasterRepository.findByEmpCodeAndLeaveDate(leaveMaster.getUserCode().getLogin(), leaveMaster.getLeaveDateTo());
        if (leaveMasters != null && leaveMasters.size() > 0) {
            return ResponseEntity.badRequest().headers(headers).body(null);
        }
        leaveMasters = leaveMasterRepository.findByEmpCodeAndLeaveDateFrom(leaveMaster.getUserCode().getLogin(), leaveMaster.getLeaveDateFrom(), leaveMaster.getLeaveDateTo());
        if (leaveMasters != null && leaveMasters.size() > 0) {
            return ResponseEntity.badRequest().headers(headers).body(null);
        }
        leaveMasters = leaveMasterRepository.findByEmpCodeAndLeaveDateTo(leaveMaster.getUserCode().getLogin(), leaveMaster.getLeaveDateFrom(), leaveMaster.getLeaveDateTo());
        if (leaveMasters != null && leaveMasters.size() > 0) {
            return ResponseEntity.badRequest().headers(headers).body(null);
        }
        EmployeeView requester = employeeViewRepository.findById(leaveMaster.getUserCode().getLogin().toLowerCase()).orElse(null);

        leaveMaster.hodApprovedBy(requester.getSupervisor().substring(0, requester.getSupervisor().indexOf("(")));
        leaveMaster.hodApprovedDate(Instant.now());
        leaveMaster.hrApprovedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        leaveMaster.hrApprovedDate(Instant.now());
        leaveMaster.flag("C");
        leaveMaster.setProcessFlag("Y");
        leaveMaster.setHodRemarks("Direct Entry By Hr");
        leaveMaster.setHrRemarks("Direct Entry By Hr");
        leaveMaster.createdBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        leaveMaster.createdDate(Instant.now());
        leaveMaster.setFactory(employeeView.getFactory());
        LeaveMaster result = leaveMasterRepository.save(leaveMaster);
        EmployeeView approver = employeeViewRepository.findById(leaveMaster.getHodApprovedBy().toLowerCase()).orElse(null);
        if (requester != null && requester != null) {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
            Locale locale = Locale.forLanguageTag("en");
            Context context = new Context(locale);
            context.setVariable("leaveTypeCode", leaveMaster.getLeaveTypeMaster().getLeaveCode());
            context.setVariable("leaveTypeName", leaveMaster.getLeaveTypeMaster().getLeaveName());
            context.setVariable("empCode", requester.getCardNo());
            context.setVariable("name", requester.getName());
            context.setVariable("leaveDateFrom", format.format(Date.from(leaveMaster.getLeaveDateFrom())));
            context.setVariable("leaveDateTo", format.format(Date.from(leaveMaster.getLeaveDateTo())));
            context.setVariable("flag", leaveMaster.getFlag());
            context.setVariable("reason", leaveMaster.getReason());
            context.setVariable("url", applicationProperties.getUrl());
            String content = null;
            String subject = "Leave sanction request for " + leaveMaster.getLeaveTypeMaster().getLeaveName();
            try {
                content = templateEngine.process("mail/leave_mail", context);
                if (approver.getEmail() != null && approver.getEmail().length() > 0 && VALID_EMAIL_ADDRESS_REGEX.matcher(approver.getEmail()).find() == true) {
                    mailService.sendEmail(approver.getEmail(), subject, content, false, true);
                }
            } catch (Exception e) {
            }
        }
        return ResponseEntity.created(new URI("/api/leave-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /leave-masters : Updates an existing leaveMaster.
     *
     * @param @leaveMaster[] the leaveMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated leaveMaster,
     * or with status 400 (Bad Request) if the leaveMaster is not valid,
     * or with status 500 (Internal Server Error) if the leaveMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/leave-masters")
    @Timed
    public ResponseEntity<Message> updateLeaveMaster(@Valid @RequestBody LeaveMaster[] leaveMasters) throws URISyntaxException {
        log.debug("REST request to update LeaveMaster : {}", leaveMasters);
        int ctr = 0;
        String error = "";
        for (LeaveMaster leaveMaster : leaveMasters) {
            java.util.Date allowDate = allowDateApproval(java.util.Date.from(leaveMaster.getLeaveDateFrom()));
            if (allowDate.before(new java.util.Date()) && (leaveMaster.getFlag() != null && !leaveMaster.getFlag().equalsIgnoreCase("R"))) {
                error += leaveMaster.getId().toString() + ", ";
            } else {
                leaveMaster.setHodApprovedDate(Instant.now());
                LeaveMaster result = leaveMasterRepository.save(leaveMaster);
                if (result != null) {
                    ++ctr;
                    if (result.getLeaveTypeMaster() != null && result.getLeaveTypeMaster().getLeaveCode().equalsIgnoreCase("CO")
                        && result.getCompOffMasterId() != null && result.getFlag().equalsIgnoreCase("R")) {
                        CompOffMaster compOffMaster = compOffMasterRepository.findById(result.getCompOffMasterId()).orElse(null);
                        compOffMaster.setAvailDate(null);
                        compOffMasterRepository.save(compOffMaster);
                    }
                    EmployeeView requester = employeeViewRepository.findById(leaveMaster.getUserCode().getLogin().toLowerCase()).orElse(null);
                    if (requester != null && requester != null) {
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
                        Locale locale = Locale.forLanguageTag("en");
                        Context context = new Context(locale);
                        context.setVariable("leaveTypeCode", leaveMaster.getLeaveTypeMaster().getLeaveCode());
                        context.setVariable("leaveTypeName", leaveMaster.getLeaveTypeMaster().getLeaveName());
                        context.setVariable("empCode", requester.getCardNo());
                        context.setVariable("name", requester.getName());
                        context.setVariable("leaveDateFrom", format.format(Date.from(leaveMaster.getLeaveDateFrom())));
                        context.setVariable("leaveDateTo", format.format(Date.from(leaveMaster.getLeaveDateTo())));
                        context.setVariable("flag", leaveMaster.getFlag());
                        context.setVariable("reason", leaveMaster.getReason());
                        context.setVariable("remarksBy", "HOD Remarks");
                        context.setVariable("remarks", leaveMaster.getHodRemarks());
                        context.setVariable("url", applicationProperties.getUrl());
                        String content = null;
                        String subject = null;
                        if (leaveMaster.getFlag().equalsIgnoreCase("A")) {
                            subject = "Leave Approved by HOD for " + leaveMaster.getLeaveTypeMaster().getLeaveName();
                        } else if (leaveMaster.getFlag().equalsIgnoreCase("R")) {
                            subject = "Leave Rejected by HOD for " + leaveMaster.getLeaveTypeMaster().getLeaveName();
                        }
                        try {
                            content = templateEngine.process("mail/leave_mail", context);
                            if (requester.getEmail() != null && requester.getEmail().length() > 0 && VALID_EMAIL_ADDRESS_REGEX.matcher(requester.getEmail()).find() == true) {
                                mailService.sendEmail(requester.getEmail(), subject, content, false, true);
                            }
                            if (leaveMaster.getFlag().equalsIgnoreCase("A")) {
                                if (requester.getLogin() != null && requester.getLogin().startsWith("102")) {
                                    mailService.sendEmail("hrd169@vamanioverseas.com", subject, content, false, true);
                                } else if (requester.getLogin() != null && requester.getLogin().startsWith("101")) {
                                    mailService.sendEmail("hrd@vamanioverseas.com", subject, content, false, true);
                                } else if (requester.getLogin() != null && requester.getLogin().startsWith("190") || requester.getLogin().startsWith("191")  || requester.getLogin().startsWith("192") || requester.getLogin().startsWith("201")) {
                                    mailService.sendEmail("samayyadav@vamanioverseas.com", subject, content, false, true);
                                } else if (requester.getLogin() != null && requester.getLogin().startsWith("103")) {
                                    mailService.sendEmail("hrd6f@vamanioverseas.com", subject, content, false, true);
                                } else if (requester.getLogin() != null && requester.getLogin().startsWith("104")) {
                                    mailService.sendEmail("ramavatar@vamanioverseas.com", subject, content, false, true);
                                } else if (requester.getLogin() != null && requester.getLogin().startsWith("105")) {
                                    mailService.sendEmail("hrdb4-noida@vamanioverseas.com", subject, content, false, true);
                                } else if (requester.getLogin() != null && requester.getLogin().startsWith("106")) {
                                    mailService.sendEmail("bhushankumar@vamanioverseas.com", subject, content, false, true);
                                } else if (requester.getLogin() != null && requester.getLogin().startsWith("107")) {
                                    mailService.sendEmail("ramavatar@vamanioverseas.com", subject, content, false, true);
                                }
                            }
                        } catch (Exception e) {
                        }
                    }

                    User user = userRepository.findOneByLogin(leaveMaster.getUserCode().getLogin().toLowerCase()).orElse(null);
                    if (user != null && user.getDeviceId() != null && user.getDeviceId().length() > 0) {
                        String body = null;
                        if (leaveMaster.getFlag().equalsIgnoreCase("A")) {
                            body = "Leave Approved by HOD for " + leaveMaster.getLeaveTypeMaster().getLeaveName();
                        } else if (leaveMaster.getFlag().equalsIgnoreCase("R")) {
                            body = "Leave Rejected by HOD for " + leaveMaster.getLeaveTypeMaster().getLeaveName();
                        }
                        FirebaseMessage firebaseMessage = new FirebaseMessage();
                        firebaseMessage.setBody(body);
                        firebaseMessage.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                        firebaseMessage.setCreatedDate(Instant.now());
                        firebaseMessage.setStatus(leaveMaster.getFlag().equalsIgnoreCase("A") ? "Approved By HOD" : "Rejected By HOD");
                        firebaseMessage.setType("Leave Status");
                        FirebaseMessage message = firebaseMessageRepository.save(firebaseMessage);
                        if (message != null) {
                            try {
                                int status = firebaseConfigration.postFirebase(new FirebaseSystem(user.getDeviceId(), message.getType(), message.getStatus(), message.getBody()));
                                if (status == 200) {
                                    message.setSuccessStatus("Y");
                                    message = firebaseMessageRepository.save(message);
                                }
                            } catch (Exception e) {

                            }
                        }
                    }
                } else {
                    error += leaveMaster.getId().toString() + ", ";
                }
            }
        }
        if (ctr == leaveMasters.length) {
            return ResponseEntity.ok()
                .body(new Message("success", "Save Successfully!"));
        } else {
            return ResponseEntity.ok()
                .body(new Message("error", "Issue saving in these id's " + error));
        }
    }

    /**
     * PUT  /leave-masters : Updates an existing leaveMaster.
     *
     * @param @leaveMaster[] the leaveMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated leaveMaster,
     * or with status 400 (Bad Request) if the leaveMaster is not valid,
     * or with status 500 (Internal Server Error) if the leaveMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/leave-masters-hr-update")
    @Timed
    public ResponseEntity<Message> updateLeaveMasterByHr(@Valid @RequestBody LeaveMaster[] leaveMasters) throws URISyntaxException {
        log.debug("REST request to update LeaveMaster : {}", leaveMasters);
        int ctr = 0;
        String error = "";
        for (LeaveMaster leaveMaster : leaveMasters) {
            leaveMaster.setHrApprovedDate(Instant.now());
            leaveMaster.setHrApprovedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
            LeaveMaster result = leaveMasterRepository.save(leaveMaster);
            if (result != null) {
                ++ctr;
                if (result.getLeaveTypeMaster() != null && result.getLeaveTypeMaster().getLeaveCode().equalsIgnoreCase("CO")
                    && result.getCompOffMasterId() != null && result.getFlag().equalsIgnoreCase("R")) {
                    CompOffMaster compOffMaster = compOffMasterRepository.findById(result.getCompOffMasterId()).orElse(null);
                    compOffMaster.setAvailDate(null);
                    compOffMasterRepository.save(compOffMaster);
                }
                /*EmployeeView requester = employeeViewRepository.findById(leaveMaster.getUserCode().getLogin().toLowerCase()).orElse(null);
                if (requester != null && requester != null) {
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
                    Locale locale = Locale.forLanguageTag("en");
                    Context context = new Context(locale);
                    context.setVariable("leaveTypeCode", leaveMaster.getLeaveTypeMaster().getLeaveCode());
                    context.setVariable("leaveTypeName", leaveMaster.getLeaveTypeMaster().getLeaveName());
                    context.setVariable("empCode", requester.getCardNo());
                    context.setVariable("name", requester.getName());
                    context.setVariable("leaveDateFrom", format.format(Date.from(leaveMaster.getLeaveDateFrom())));
                    context.setVariable("leaveDateTo", format.format(Date.from(leaveMaster.getLeaveDateTo())));
                    context.setVariable("flag", leaveMaster.getFlag());
                    context.setVariable("reason", leaveMaster.getReason());
                    context.setVariable("remarksBy", "HR Remarks");
                    context.setVariable("remarks", leaveMaster.getHrRemarks());
                    context.setVariable("url", applicationProperties.getUrl());
                    String content = null;
                    String subject = null;
                    if (leaveMaster.getFlag().equalsIgnoreCase("C")) {
                        subject = "Leave Approved by HR for " + leaveMaster.getLeaveTypeMaster().getLeaveName();
                    } else if (leaveMaster.getFlag().equalsIgnoreCase("R")) {
                        subject = "Leave Rejected by HR for " + leaveMaster.getLeaveTypeMaster().getLeaveName();
                    }
                    try {
                        content = templateEngine.process("mail/leave_mail", context);
                        if (requester.getEmail() != null && requester.getEmail().length() > 0 && VALID_EMAIL_ADDRESS_REGEX.matcher(requester.getEmail()).find() == true) {
                            mailService.sendEmail(requester.getEmail(), subject, content, false, true);
                        }
                    } catch (Exception e) {
                    }
                }*/
            } else {
                error += leaveMaster.getId().toString() + ", ";
            }
        }
        if (ctr == leaveMasters.length) {
            return ResponseEntity.ok()
                .body(new Message("success", "Save Successfully!"));
        } else {
            return ResponseEntity.ok()
                .body(new Message("error", "Issue saving in these id's " + error));
        }
    }

    /**
     * GET  /leave-masters : get all the leaveMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of leaveMasters in body
     */
    @GetMapping("/leave-masters")
    @Timed
    public ResponseEntity<List<LeaveMaster>> getAllLeaveMasters(Pageable pageable) {
        log.debug("REST request to get a page of LeaveMasters");
        Page<LeaveMaster> page = leaveMasterRepository.findAllByEmpCode(SecurityUtils.getCurrentUserLogin().orElse(null), pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/leave-masters");
        List<LeaveMaster> leaveMasters = new ArrayList<>();
        for (LeaveMaster leaveMaster : page.getContent()) {
            leaveMaster.setMobileAttendances(mobileAttendanceRepository.findAllByLeaveMasterId(leaveMaster.getId()));
            leaveMasters.add(leaveMaster);
        }
        return ResponseEntity.ok().headers(headers).body(leaveMasters);
    }



    /**
     * GET  /leave-masters : get all the leaveMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of leaveMasters in body
     */
    @GetMapping("/leave-masters-mobile/{monthYear}")
    @Timed
    public ResponseEntity<LeaveMobileBean> getAllLeaveMastersMobile(@PathVariable String monthYear) {
        log.debug("REST request to get a page of LeaveMasters");
        String[] arr = monthYear.split("-");
        YearMonth yearMonth = YearMonth.of( Integer.parseInt(arr[1]), Integer.parseInt(arr[0]) );  // January of 2015.
        Instant firstOfMonth = yearMonth.atDay( 1 ).atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant lastOfMonth = yearMonth.atEndOfMonth().atStartOfDay(ZoneId.systemDefault()).toInstant();
        LeaveMobileBean leaveMobileBean = new LeaveMobileBean();
        EmployeeView employeeView = employeeViewRepository.findById(SecurityUtils.getCurrentUserLogin().orElse(null)).orElse(null);
        List<LeaveMaster> leaveMasters = leaveMasterRepository.findAllByEmpCodeAndMonth(SecurityUtils.getCurrentUserLogin().orElse(null), firstOfMonth, lastOfMonth);
        if(leaveMasters != null && leaveMasters.size()>0) {
            leaveMobileBean.setExist(true);
            List<LeaveMobile> leaveMobiles = new ArrayList<>();
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
            for (LeaveMaster leaveMaster : leaveMasters) {
                LeaveMobile leaveMobile = new LeaveMobile();
                leaveMobile.setId(leaveMaster.getId());
                leaveMobile.setLeaveType(leaveMaster.getLeaveTypeMaster().getLeaveName());
                leaveMobile.setLeaveSubType(leaveMaster.getLeaveSubTypeMaster().getSubTypeName());
                leaveMobile.setReason(leaveMaster.getReason());
                leaveMobile.setFlag(leaveMaster.getFlag());
                leaveMobile.setMissPunchType(leaveMaster.getMissPunchType());
                leaveMobile.setLeaveDateFrom(format.format(Date.from(leaveMaster.getLeaveDateFrom())) + " " + formatTime.format(Date.from(leaveMaster.getLeaveTimeFrom())));
                leaveMobile.setLeaveDateTo(format.format(Date.from(leaveMaster.getLeaveDateTo())) + " " + " " + leaveMaster.getLeaveTimeTo() != null ? formatTime.format(Date.from(leaveMaster.getLeaveTimeTo())) : "");
                leaveMobiles.add(leaveMobile);
            }
            leaveMobileBean.setLeaveMobiles(leaveMobiles);
        } else {
            leaveMobileBean.setExist(false);
            leaveMobileBean.setErrorMessage("No Data Found");
        }
        leaveMobileBean.setHolidayMasters(holidayMasterRepository.findAllByFactoryMaster(employeeView.getFactory().trim()));
        return ResponseEntity.ok().body(leaveMobileBean);
    }

    /**
     * GET  /leave-masters : get all the leaveMasters.
     *
     * @param !pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of leaveMasters in body
     */
    @PostMapping("/leave-masters-hod")
    @Timed
    public ResponseEntity<List<LeaveMaster>> getAllLeaveMastersByHod(@Valid @RequestBody LeaveSearch search) {
        log.debug("REST request to get a page of LeaveMasters");
        String empCode = "%";
        if (search.getEmpCode() != null) {
            empCode = search.getEmpCode() + "%";
        }
        Page<LeaveMaster> page = null;
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("id").descending()));
        if(search.getLeaveStatus() != null && search.getLeaveStatus().equalsIgnoreCase("P")) {
            page = leaveMasterRepository.findAllByHodApprovedByPending(SecurityUtils.getCurrentUserLogin().orElse(null), empCode, search.getPage());
        } else if(search.getLeaveStatus() != null && search.getLeaveStatus().equalsIgnoreCase("A")) {
            page = leaveMasterRepository.findAllByHodApprovedByApproved(SecurityUtils.getCurrentUserLogin().orElse(null), search.getLeaveDateFrom(), search.getLeaveDateTo(), empCode, search.getPage());
        } else {
            page = leaveMasterRepository.findAllByHodApprovedByRejected(SecurityUtils.getCurrentUserLogin().orElse(null), search.getLeaveDateFrom(), search.getLeaveDateTo(), empCode, search.getLeaveStatus(), search.getPage());
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/leave-masters");
        List<LeaveMaster> leaveMasters = new ArrayList<>();
        for (LeaveMaster leaveMaster : page.getContent()) {
            leaveMaster.setMobileAttendances(mobileAttendanceRepository.findAllByLeaveMasterId(leaveMaster.getId()));
            leaveMasters.add(leaveMaster);
        }
        return ResponseEntity.ok().headers(headers).body(leaveMasters);
    }

    /**
     * GET  /comp-off-masters : get all the compOffMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of compOffMasters in body
     */
    @GetMapping("/leave-masters-hod-mobile/{monthYear}")
    @Timed
    public ResponseEntity<LeaveMobileApprovalBean> getAllCompOffMastersApproval(@PathVariable String monthYear) throws ParseException {
        log.debug("REST request to get a page of CompOffMasters");
        String[] arr = monthYear.split("-");
        LeaveMobileApprovalBean leaveMobileApprovalBean = new LeaveMobileApprovalBean();

        EmployeeView employeeView = employeeViewRepository.findById(SecurityUtils.getCurrentUserLogin().orElse(null)).orElse(null);

        YearMonth yearMonth = YearMonth.of( Integer.parseInt(arr[1]), Integer.parseInt(arr[0]) );  // January of 2015.
        Instant firstOfMonth = yearMonth.atDay( 1 ).atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant lastOfMonth = yearMonth.atEndOfMonth().atStartOfDay(ZoneId.systemDefault()).toInstant();

        List<LeaveMaster> leaveMasters = leaveMasterRepository.findAllByEmpCodeAndMonthApproval(SecurityUtils.getCurrentUserLogin().orElse(null), firstOfMonth, lastOfMonth);
        if(leaveMasters != null && leaveMasters.size()>0) {
            leaveMobileApprovalBean.setExist(true);
            List<LeaveMobileApproval> leaveMobileApprovals = new ArrayList<>();
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
            Map<String, Set<String>> listMap = new HashMap<>();
            Map<String, List<LeaveMobileApprovalDetails>> leaveMobileMap = new HashMap<>();
            for (LeaveMaster leaveMaster : leaveMasters) {
                String date = format.format(Date.from(leaveMaster.getLeaveDateFrom()));
                if (listMap.containsKey(date)) {
                    List<LeaveMobileApprovalDetails> masters = leaveMobileMap.get(date);
                    LeaveMobileApprovalDetails leaveMobileApprovalDetails = new LeaveMobileApprovalDetails();
                    BeanUtils.copyProperties(leaveMaster, leaveMobileApprovalDetails);
                    leaveMobileApprovalDetails.setLeaveDateFromView(format.format(Date.from(leaveMaster.getLeaveDateFrom()))+ " "+format2.format(Date.from(leaveMaster.getLeaveTimeFrom())));
                    leaveMobileApprovalDetails.setLeaveDateToView(format.format(Date.from(leaveMaster.getLeaveDateTo()))+ " "+format2.format(Date.from(leaveMaster.getLeaveTimeTo())));
                    leaveMobileApprovalDetails.setLeaveTypeMasterDesc(leaveMaster.getLeaveTypeMaster().getLeaveName());
                    leaveMobileApprovalDetails.setLeaveSubTypeMasterDesc(leaveMaster.getLeaveSubTypeMaster().getSubTypeName());
                    leaveMobileApprovalDetails.setEmpCode(leaveMaster.getUserCode().getLogin());
                    leaveMobileApprovalDetails.setEmpName(leaveMaster.getUserCode().getFirstName() + (leaveMaster.getUserCode().getLastName() != null ? " " + leaveMaster.getUserCode().getLastName() : ""));
                    masters.add(leaveMobileApprovalDetails);
                    leaveMobileMap.put(date, masters);

                    Set<String> strings = listMap.get(date);
                    strings.add(leaveMaster.getFlag());
                    listMap.put(date, strings);
                } else {
                    List<LeaveMobileApprovalDetails> masters = new ArrayList<>();
                    LeaveMobileApprovalDetails leaveMobileApprovalDetails = new LeaveMobileApprovalDetails();
                    BeanUtils.copyProperties(leaveMaster, leaveMobileApprovalDetails);
                    leaveMobileApprovalDetails.setLeaveDateFromView(format.format(Date.from(leaveMaster.getLeaveDateFrom()))+ " "+format2.format(Date.from(leaveMaster.getLeaveTimeFrom())));
                    leaveMobileApprovalDetails.setLeaveDateToView(format.format(Date.from(leaveMaster.getLeaveDateTo()))+ " "+format2.format(Date.from(leaveMaster.getLeaveTimeTo())));
                    leaveMobileApprovalDetails.setLeaveTypeMasterDesc(leaveMaster.getLeaveTypeMaster().getLeaveName());
                    leaveMobileApprovalDetails.setLeaveSubTypeMasterDesc(leaveMaster.getLeaveSubTypeMaster().getSubTypeName());
                    leaveMobileApprovalDetails.setEmpCode(leaveMaster.getUserCode().getLogin());
                    leaveMobileApprovalDetails.setEmpName(leaveMaster.getUserCode().getFirstName() + (leaveMaster.getUserCode().getLastName() != null ? " " + leaveMaster.getUserCode().getLastName() : ""));
                    masters.add(leaveMobileApprovalDetails);
                    leaveMobileMap.put(date, masters);

                    Set<String> strings = new HashSet<>();
                    strings.add(leaveMaster.getFlag());
                    listMap.put(date, strings);
                }
            }
            for (String key : listMap.keySet()) {
                LeaveMobileApproval leaveMobileApproval = new LeaveMobileApproval();
                leaveMobileApproval.setLeaveDateFrom(key);
                leaveMobileApproval.setDate(format.parse(key));
                if (listMap.get(key).contains("E")) {
                    leaveMobileApproval.setFlag("Y");
                } else if (listMap.get(key).contains("A")) {
                    leaveMobileApproval.setFlag("O");
                } else if (listMap.get(key).contains("R")) {
                    leaveMobileApproval.setFlag("R");
                }
                leaveMobileApproval.setLeaveMobileApprovalDetails(leaveMobileMap.get(key));
                leaveMobileApprovals.add(leaveMobileApproval);

                if(leaveMobileApprovals != null && leaveMobileApprovals.size()>0){
                    Collections.sort(leaveMobileApprovals, Comparator.comparing(LeaveMobileApproval::getDate));
                }
            }
            leaveMobileApprovalBean.setLeaveMobiles(leaveMobileApprovals);
            leaveMobileApprovalBean.setHolidayMasters(holidayMasterRepository.findAllByFactoryMaster(employeeView.getFactory().trim()));
        } else {
            leaveMobileApprovalBean.setExist(false);
            leaveMobileApprovalBean.setErrorMessage("No Data Found");
        }
        return ResponseEntity.ok().body(leaveMobileApprovalBean);
    }

    /**
     * GET  /leave-masters : get all the leaveMasters.
     *
     * @param !pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of leaveMasters in body
     */
    @PostMapping("/leave-masters-hr")
    @Timed
    public ResponseEntity<List<LeaveMaster>> getAllLeaveMastersByHr(@Valid @RequestBody LeaveSearch search) {
        log.debug("REST request to get a page of LeaveMasters");
        EmployeeView employeeView = employeeViewRepository.findByCardNo(SecurityUtils.getCurrentUserLogin().orElse(null)).orElse(null);
        String empCode = "%";
        if (search.getEmpCode() != null) {
            empCode = search.getEmpCode() + "%";
        }

        String hodCode = "%";
        if (search.getHodCode() != null) {
            hodCode = search.getHodCode() + "%";
        }
        Page<LeaveMaster> page = null;
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("id").descending()));
        if(search.getLeaveStatus() != null && search.getLeaveStatus().equalsIgnoreCase("P")) {
            search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by(
                new Sort.Order(Sort.Direction.ASC, "userCode.login"),
                new Sort.Order(Sort.Direction.ASC, "leaveDateFrom"))));
            if(search.getDateType() != null && search.getDateType().equalsIgnoreCase("E")) {
                page = leaveMasterRepository.findAllByHrApprovedByAndLeaveDatePending(empCode, empCode, hodCode, hodCode, employeeView.getFactory(), search.getLeaveDateFrom(), search.getLeaveDateTo(), search.getPage());
            } else if(search.getDateType() != null && search.getDateType().equalsIgnoreCase("O")) {
                page = leaveMasterRepository.findAllByHrApprovedByAndApprovedDatePending(empCode, empCode, hodCode, hodCode, employeeView.getFactory(), search.getLeaveDateFrom(), search.getLeaveDateTo(), search.getPage());
            }
        } else if(search.getLeaveStatus() != null && search.getLeaveStatus().equalsIgnoreCase("R")) {
            search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by(
                new Sort.Order(Sort.Direction.ASC, "userCode.login"),
                new Sort.Order(Sort.Direction.ASC, "leaveDateFrom"))));
            if(search.getDateType() != null && search.getDateType().equalsIgnoreCase("E")) {
                page = leaveMasterRepository.findAllByHrApprovedByAndLeaveDateRejected(empCode, empCode, hodCode, hodCode, employeeView.getFactory(), search.getLeaveDateFrom(), search.getLeaveDateTo(), search.getPage());
            } else if(search.getDateType() != null && search.getDateType().equalsIgnoreCase("O")) {
                page = leaveMasterRepository.findAllByHrApprovedByAndApprovedDateRejected(empCode, empCode, hodCode, hodCode, employeeView.getFactory(), search.getLeaveDateFrom(), search.getLeaveDateTo(), search.getPage());
            } else if(search.getDateType() != null && search.getDateType().equalsIgnoreCase("H")) {
                page = leaveMasterRepository.findAllByHrApprovedByAndHRRejectedDateRejected(empCode, empCode, hodCode, hodCode, employeeView.getFactory(), search.getLeaveDateFrom(), search.getLeaveDateTo(), search.getPage());
            }
        } else {
            search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by(
                new Sort.Order(Sort.Direction.ASC, "userCode.login"),
                new Sort.Order(Sort.Direction.ASC, "leaveDateFrom"))));
            if(search.getDateType() != null && search.getDateType().equalsIgnoreCase("E")) {
                page = leaveMasterRepository.findAllByHrApprovedByAndLeaveDateApproved(empCode, empCode, hodCode, hodCode, employeeView.getFactory(), search.getLeaveDateFrom(), search.getLeaveDateTo(), search.getPage());
            } else if(search.getDateType() != null && search.getDateType().equalsIgnoreCase("O")) {
                page = leaveMasterRepository.findAllByHrApprovedByAndApprovedDateApproved(empCode, empCode, hodCode, hodCode, employeeView.getFactory(), search.getLeaveDateFrom(), search.getLeaveDateTo(), search.getPage());
            } else if(search.getDateType() != null && search.getDateType().equalsIgnoreCase("H")) {
                page = leaveMasterRepository.findAllByHrApprovedByAndHRApprovedDateApproved(empCode, empCode, hodCode, hodCode, employeeView.getFactory(), search.getLeaveDateFrom(), search.getLeaveDateTo(), search.getPage());
            }
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/leave-masters");
        List<LeaveMaster> leaveMasters = new ArrayList<>();
        for (LeaveMaster leaveMaster : page.getContent()) {
            leaveMaster.setMobileAttendances(mobileAttendanceRepository.findAllByLeaveMasterId(leaveMaster.getId()));
            leaveMasters.add(leaveMaster);
        }
        return ResponseEntity.ok().headers(headers).body(leaveMasters);
    }

    /**
     * GET  /leave-masters : get all the leaveMasters.
     *
     * @param !pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of leaveMasters in body
     */
    @PostMapping("/leave-masters-hr-entry")
    @Timed
    public ResponseEntity<List<LeaveMaster>> getAllLeaveMastersByHrEntry(@Valid @RequestBody LeaveSearch search) {
        log.debug("REST request to get a page of LeaveMasters");
        EmployeeView employeeView = employeeViewRepository.findByCardNo(SecurityUtils.getCurrentUserLogin().orElse(null)).orElse(null);
        String empCode = "%";
        if (search.getEmpCode() != null) {
            empCode = search.getEmpCode() + "%";
        }
        Page<LeaveMaster> page = null;
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("id").descending()));
        page = leaveMasterRepository.findAllByHrApprovedByApprovedDirect(search.getLeaveDateFrom(), search.getLeaveDateTo(), empCode, employeeView.getFactory(), search.getPage());

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/leave-entry-hr");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /leave-masters : get all the leaveMasters.
     *
     * @param !pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of leaveMasters in body
     */
    @PostMapping("/leave-masters-hr-report")
    @Timed
    public @ResponseBody void getAllLeaveMastersByHrReport(@PageableDefault(value = Integer.MAX_VALUE)  @SortDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable, @Valid @RequestBody LeaveSearch search, HttpServletResponse response) throws JRException, IOException {
        log.debug("REST request to get a page of LeaveMasters");
        EmployeeView employeeView = employeeViewRepository.findByCardNo(SecurityUtils.getCurrentUserLogin().orElse(null)).orElse(null);
        String empCode = "%";
        if (search.getEmpCode() != null) {
            empCode = search.getEmpCode() + "%";
        }
        String hodCode = "%";
        if (search.getHodCode() != null) {
            hodCode = search.getHodCode() + "%";
        }
        Page<LeaveMaster> page = null;
        //search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("id").descending()));
        if(search.getLeaveStatus() != null && search.getLeaveStatus().equalsIgnoreCase("P")) {
            search.setPage(PageRequest.of(0, Integer.MAX_VALUE, Sort.by(
                new Sort.Order(Sort.Direction.ASC, "userCode.login"),
                new Sort.Order(Sort.Direction.ASC, "leaveDateFrom"))));
            if(search.getDateType() != null && search.getDateType().equalsIgnoreCase("E")) {
                page = leaveMasterRepository.findAllByHrApprovedByAndLeaveDatePending(empCode, empCode, hodCode, hodCode, employeeView.getFactory(), search.getLeaveDateFrom(), search.getLeaveDateTo(), search.getPage());
            } else if(search.getDateType() != null && search.getDateType().equalsIgnoreCase("O")) {
                page = leaveMasterRepository.findAllByHrApprovedByAndApprovedDatePending(empCode, empCode, hodCode, hodCode, employeeView.getFactory(), search.getLeaveDateFrom(), search.getLeaveDateTo(), search.getPage());
            }
        } else if(search.getLeaveStatus() != null && search.getLeaveStatus().equalsIgnoreCase("R")) {
            search.setPage(PageRequest.of(0, Integer.MAX_VALUE, Sort.by(
                new Sort.Order(Sort.Direction.ASC, "userCode.login"),
                new Sort.Order(Sort.Direction.ASC, "leaveDateFrom"))));
            if(search.getDateType() != null && search.getDateType().equalsIgnoreCase("E")) {
                page = leaveMasterRepository.findAllByHrApprovedByAndLeaveDateRejected(empCode, empCode, hodCode, hodCode, employeeView.getFactory(), search.getLeaveDateFrom(), search.getLeaveDateTo(), search.getPage());
            } else if(search.getDateType() != null && search.getDateType().equalsIgnoreCase("O")) {
                page = leaveMasterRepository.findAllByHrApprovedByAndApprovedDateRejected(empCode, empCode, hodCode, hodCode, employeeView.getFactory(), search.getLeaveDateFrom(), search.getLeaveDateTo(), search.getPage());
            } else if(search.getDateType() != null && search.getDateType().equalsIgnoreCase("H")) {
                page = leaveMasterRepository.findAllByHrApprovedByAndHRRejectedDateRejected(empCode, empCode, hodCode, hodCode, employeeView.getFactory(), search.getLeaveDateFrom(), search.getLeaveDateTo(), search.getPage());
            }
        } else {
            search.setPage(PageRequest.of(0, Integer.MAX_VALUE, Sort.by(
                new Sort.Order(Sort.Direction.ASC, "userCode.login"),
                new Sort.Order(Sort.Direction.ASC, "leaveDateFrom"))));
            if(search.getDateType() != null && search.getDateType().equalsIgnoreCase("E")) {
                page = leaveMasterRepository.findAllByHrApprovedByAndLeaveDateApproved(empCode, empCode, hodCode, hodCode, employeeView.getFactory(), search.getLeaveDateFrom(), search.getLeaveDateTo(), search.getPage());
            } else if(search.getDateType() != null && search.getDateType().equalsIgnoreCase("O")) {
                page = leaveMasterRepository.findAllByHrApprovedByAndApprovedDateApproved(empCode, empCode, hodCode, hodCode, employeeView.getFactory(), search.getLeaveDateFrom(), search.getLeaveDateTo(), search.getPage());
            } else if(search.getDateType() != null && search.getDateType().equalsIgnoreCase("H")) {
                page = leaveMasterRepository.findAllByHrApprovedByAndHRApprovedDateApproved(empCode, empCode, hodCode, hodCode, employeeView.getFactory(), search.getLeaveDateFrom(), search.getLeaveDateTo(), search.getPage());
            }
        }
        List<LeaveMaster> leaveMasters = page.getContent();
        List<LeaveMasterReportBean> leaveMasterReportBeans = new ArrayList<>();
        for (LeaveMaster leaveMaster : leaveMasters) {
            LeaveMasterReportBean leaveMasterReportBean = new LeaveMasterReportBean();
            leaveMasterReportBean.setId(leaveMaster.getId());
            leaveMasterReportBean.setCardNo(leaveMaster.getUserCode().getLogin());
            leaveMasterReportBean.setFirstName(leaveMaster.getUserCode().getFirstName());
            leaveMasterReportBean.setLastName(leaveMaster.getUserCode().getLastName());
            leaveMasterReportBean.setLeaveType(leaveMaster.getLeaveTypeMaster().getLeaveCode());
            leaveMasterReportBean.setLeaveTypeDesc(leaveMaster.getLeaveTypeMaster().getLeaveName());
            leaveMasterReportBean.setSubTypeCode(leaveMaster.getLeaveSubTypeMaster().getSubTypeCode());
            leaveMasterReportBean.setSubTypeDesc(leaveMaster.getLeaveSubTypeMaster().getSubTypeName());
            leaveMasterReportBean.setLeaveDateFrom(Timestamp.from(leaveMaster.getLeaveDateFrom()));
            leaveMasterReportBean.setLeaveTimeFrom(Timestamp.from(leaveMaster.getLeaveTimeFrom()));
            leaveMasterReportBean.setLeaveDateTo(Timestamp.from(leaveMaster.getLeaveDateTo()));
            leaveMasterReportBean.setLeaveTimeTo(Timestamp.from(leaveMaster.getLeaveTimeTo()));
            leaveMasterReportBean.setHodApprovedDate(Timestamp.from(leaveMaster.getHodApprovedDate()));
            if(leaveMaster.getFlag() != null && leaveMaster.getFlag().equalsIgnoreCase("A")) {
                leaveMasterReportBean.setFlag("Pending");
            } else {
                leaveMasterReportBean.setFlag("Closed");
            }
            leaveMasterReportBeans.add(leaveMasterReportBean);
        }
        String path = applicationProperties.getTemplatePath()+"jasper/";
        JasperDesign jasperDesign = JRXmlLoader.load(path+"/LeaveApprovalReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String,Object> parameters = new HashMap<String, Object>();
        parameters.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(leaveMasterReportBeans);


        parameters.put("datasource", jrDataSource);
        parameters.put("SUBREPORT_DIR",path);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, jrDataSource);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition","attachment; filename=LeaveReport.xlsx");

        final OutputStream outputStream = response.getOutputStream();

        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,outputStream);
        exporter.exportReport();
    }

    /**
     * GET  /leave-masters/:id : get the "id" leaveMaster.
     *
     * @param id the id of the leaveMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the leaveMaster, or with status 404 (Not Found)
     */
    @GetMapping("/leave-masters/{id}")
    @Timed
    public ResponseEntity<LeaveMaster> getLeaveMaster(@PathVariable Long id) {
        log.debug("REST request to get LeaveMaster : {}", id);
        Optional<LeaveMaster> leaveMaster = leaveMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(leaveMaster);
    }

    /**
     * DELETE  /leave-masters/:id : delete the "id" leaveMaster.
     *
     * @param id the id of the leaveMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/leave-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteLeaveMaster(@PathVariable Long id) {
        log.debug("REST request to delete LeaveMaster : {}", id);

        leaveMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /leave-masters/:id : get the "id" leaveMaster.
     *
     * @param id the id of the leaveMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the leaveMaster, or with status 404 (Not Found)
     */
    @GetMapping("/leave-masters-remarks-details/{id}")
    @Timed
    public ResponseEntity<LeaveMasterRemarksDetailsBean> getLeaveMasterRemarks(@PathVariable Long id) {
        log.debug("REST request to get LeaveMaster : {}", id);
        String currentUser = SecurityUtils.getCurrentUserLogin().orElse(null);
        LeaveMasterRemarksDetailsBean leaveMasterRemarksDetailsBean = new LeaveMasterRemarksDetailsBean();
        LeaveMaster leaveMaster = leaveMasterRepository.findById(id).orElse(null);
        leaveMasterRemarksDetailsBean.setLeaveMasterId(leaveMaster.getId());
        leaveMasterRemarksDetailsBean.setStatus(leaveMaster.getFlag());
        leaveMasterRemarksDetailsBean.setCreatedBy(leaveMaster.getUserCode().getLogin());
        leaveMasterRemarksDetailsBean.setCreatedName(leaveMaster.getUserCode().getFirstName() + " " + (leaveMaster.getUserCode().getLastName() != null ? leaveMaster.getUserCode().getLastName() : ""));
        leaveMasterRemarksDetailsBean.setHodApprovedBy(leaveMaster.getHodApprovedBy());
        User userHOD = userRepository.findOneByLogin(leaveMaster.getHodApprovedBy()).orElse(null);
        leaveMasterRemarksDetailsBean.setHodApprovedName(userHOD.getFirstName() + " " + (userHOD.getLastName() != null ? userHOD.getLastName() : ""));
        LeaveMasterRemarksDetails leaveMasterRemarksDetails = leaveMasterRemarksDetailsRepository.getEntryLeaveMasterRemarksDetailsByLeaveMasterId(id);
        List<LeaveMasterRemarksDetails> leaveMasterRemarksDetailsList = leaveMasterRemarksDetailsRepository.getLeaveMasterRemarksDetailsByLeaveMasterId(id);
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
            EmployeeView employeeView = employeeViewRepository.findById(leaveMaster.getHodApprovedBy()).orElse(null);
            if (currentUser != null && currentUser.equalsIgnoreCase(leaveMaster.getHodApprovedBy())) {
                leaveMasterRemarksDetailsBean.setEmpCode(currentUser);
                leaveMasterRemarksDetailsBean.setEmpName(employeeView.getName());
                leaveMasterRemarksDetailsBean.setAllowEntry(true);
            } else {
                leaveMasterRemarksDetailsBean.setEmpCode(employeeView.getLogin());
                leaveMasterRemarksDetailsBean.setEmpName(employeeView.getName());
                leaveMasterRemarksDetailsBean.setAllowEntry(false);
            }
        }
        if(currentUser != null && currentUser.equalsIgnoreCase(leaveMaster.getHodApprovedBy())) {
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
        leaveMasterRemarksDetailsBean.setLeaveMaster(leaveMaster);
        return ResponseUtil.wrapOrNotFound(Optional.of(leaveMasterRemarksDetailsBean));
    }

    private Instant prevNextDays(Instant date, List<HolidayMaster> holidayMasters, Long days) {
        Instant prevDay = date.plus(days, ChronoUnit.DAYS);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Date.from(prevDay));
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return prevNextDays(prevDay, holidayMasters, days);
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            for (HolidayMaster holidayMaster : holidayMasters) {
                String prevDayFormat = simpleDateFormat.format(Date.from(prevDay));
                String holidayFormat = simpleDateFormat.format(Date.from(holidayMaster.getHolidayDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                if (prevDayFormat.equalsIgnoreCase(holidayFormat)) {
                    return prevNextDays(prevDay, holidayMasters, days);
                }
            }
        }
        return prevDay;
    }

    /**
     * DELETE  /comp-off-masters/:id : delete the "id" compOffMaster.
     *
     * @param id the id of the compOffMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @GetMapping("/leave-masters-mobile-approval/{id}/{flag}")
    @Timed
    public ResponseEntity<Message> approvalLeaveMaster(@PathVariable Long id, @PathVariable String flag) {
        log.debug("REST request to Approval Leave Master : {}", id);
        LeaveMaster leaveMaster = leaveMasterRepository.findById(id).orElse(null);
        if (leaveMaster.getFlag() != null && leaveMaster.getFlag().equalsIgnoreCase("E")) {
            leaveMaster.setFlag(flag);
            leaveMaster.setHodApprovedDate(Instant.now());
            LeaveMaster result = leaveMasterRepository.save(leaveMaster);
            if (result != null) {
                EmployeeView requester = employeeViewRepository.findById(result.getUserCode().getLogin().toLowerCase()).orElse(null);
                if (requester != null && requester != null) {
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
                    Locale locale = Locale.forLanguageTag("en");
                    Context context = new Context(locale);
                    context.setVariable("leaveTypeCode", leaveMaster.getLeaveTypeMaster().getLeaveCode());
                    context.setVariable("leaveTypeName", leaveMaster.getLeaveTypeMaster().getLeaveName());
                    context.setVariable("empCode", requester.getCardNo());
                    context.setVariable("name", requester.getName());
                    context.setVariable("leaveDateFrom", format.format(Date.from(leaveMaster.getLeaveDateFrom())));
                    context.setVariable("leaveDateTo", format.format(Date.from(leaveMaster.getLeaveDateTo())));
                    context.setVariable("flag", leaveMaster.getFlag());
                    context.setVariable("reason", leaveMaster.getReason());
                    context.setVariable("remarksBy", "HOD Remarks");
                    context.setVariable("remarks", leaveMaster.getHodRemarks());
                    context.setVariable("url", applicationProperties.getUrl());
                    String content = null;
                    String subject = null;
                    if (leaveMaster.getFlag().equalsIgnoreCase("A")) {
                        subject = "Leave Approved by HOD for " + leaveMaster.getLeaveTypeMaster().getLeaveName();
                    } else if (leaveMaster.getFlag().equalsIgnoreCase("R")) {
                        subject = "Leave Rejected by HOD for " + leaveMaster.getLeaveTypeMaster().getLeaveName();
                    }
                    try {
                        content = templateEngine.process("mail/leave_mail", context);
                        if (requester.getEmail() != null && requester.getEmail().length() > 0 && VALID_EMAIL_ADDRESS_REGEX.matcher(requester.getEmail()).find() == true) {
                            mailService.sendEmail(requester.getEmail(), subject, content, false, true);
                        }
                        if (leaveMaster.getFlag().equalsIgnoreCase("A")) {
                            if(requester.getLogin() != null && requester.getLogin().startsWith("102")) {
                                mailService.sendEmail("hrd169@vamanioverseas.com", subject, content, false, true);
                            } else if(requester.getLogin() != null && requester.getLogin().startsWith("101")) {
                                mailService.sendEmail("hrd@vamanioverseas.com", subject, content, false, true);
                            } else if (requester.getLogin() != null && requester.getLogin().startsWith("190") || requester.getLogin().startsWith("191")  || requester.getLogin().startsWith("192") || requester.getLogin().startsWith("201")) {
                                mailService.sendEmail("samayyadav@vamanioverseas.com", subject, content, false, true);
                            } else if (requester.getLogin() != null && requester.getLogin().startsWith("103")) {
                                mailService.sendEmail("hrd6f@vamanioverseas.com", subject, content, false, true);
                            } else if (requester.getLogin() != null && requester.getLogin().startsWith("104")) {
                                mailService.sendEmail("ramavatar@vamanioverseas.com", subject, content, false, true);
                            } else if (requester.getLogin() != null && requester.getLogin().startsWith("105")) {
                                mailService.sendEmail("hrdb4-noida@vamanioverseas.com", subject, content, false, true);
                            } else if (requester.getLogin() != null && requester.getLogin().startsWith("106")) {
                                mailService.sendEmail("bhushankumar@vamanioverseas.com", subject, content, false, true);
                            } else if (requester.getLogin() != null && requester.getLogin().startsWith("107")) {
                                mailService.sendEmail("ramavatar@vamanioverseas.com", subject, content, false, true);
                            }
                        }
                    } catch (Exception e) {
                    }
                }
            }


            User user = userRepository.findOneByLogin(leaveMaster.getUserCode().getLogin().toLowerCase()).orElse(null);
            if (user != null && user.getDeviceId() != null && user.getDeviceId().length() > 0) {
                String body = null;
                if (leaveMaster.getFlag().equalsIgnoreCase("A")) {
                    body = "Leave Approved by HOD for " + leaveMaster.getLeaveTypeMaster().getLeaveName();
                } else if (leaveMaster.getFlag().equalsIgnoreCase("R")) {
                    body = "Leave Rejected by HOD for " + leaveMaster.getLeaveTypeMaster().getLeaveName();
                }
                FirebaseMessage firebaseMessage = new FirebaseMessage();
                firebaseMessage.setBody(body);
                firebaseMessage.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                firebaseMessage.setCreatedDate(Instant.now());
                firebaseMessage.setStatus(leaveMaster.getFlag().equalsIgnoreCase("A") ? "Approved By HOD" : "Rejected By HOD");
                firebaseMessage.setType("Leave Status");
                FirebaseMessage message = firebaseMessageRepository.save(firebaseMessage);
                if (message != null) {
                    try {
                        int status = firebaseConfigration.postFirebase(new FirebaseSystem(user.getDeviceId(), message.getType(), message.getStatus(), message.getBody()));
                        if (status == 200) {
                            message.setSuccessStatus("Y");
                            message = firebaseMessageRepository.save(message);
                        }
                    } catch (Exception e) {

                    }
                }
            }
            return ResponseEntity.ok().body(new Message("Save successfully!", "success", true, ""));
        } else {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Leave Request already " + (leaveMaster.getFlag().equalsIgnoreCase("A") ? "approved" : leaveMaster.getFlag().equalsIgnoreCase("C")? "approved by HR" : "rejected") + ".")).body(new Message("Leave Request already " + (leaveMaster.getFlag().equalsIgnoreCase("A") ? "approved" : leaveMaster.getFlag().equalsIgnoreCase("C") ? "approved by HR" : "rejected") + ".", "error", false, "Leave Request already " + (leaveMaster.getFlag().equalsIgnoreCase("A") ? "approved" : leaveMaster.getFlag().equalsIgnoreCase("C") ? "approved by HR" : "rejected") + "."));
        }
    }

    private Instant removeTime(Instant time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Date.from(time));
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime().toInstant();
    }

    @PostMapping("/leave-masters-report")
    @Timed
    public @ResponseBody
    void getLeaveDiffReport(@Valid @RequestBody(required = false) LeaveSearch search, HttpServletResponse response) throws JRException, IOException, ParseException {
        List<LeaveMaster> listObject = leaveMasterRepository.findAllByLeaveDate(search.getLeaveDateFrom(), search.getLeaveDateTo(), search.getLeaveStatus());
        List<LeaveDiffReportBean> leaveDiffReportBean = new ArrayList<>();
        if (listObject != null && listObject.size() > 0) {
            Map<String, List<LeaveMaster>> map = new HashMap<>();
            for (LeaveMaster leaveMaster : listObject) {
                if (map.containsKey(leaveMaster.getUserCode().getLogin())) {
                    List<LeaveMaster> leaveMasters = map.get(leaveMaster.getUserCode().getLogin());
                    leaveMasters.add(leaveMaster);
                    map.put(leaveMaster.getUserCode().getLogin(), leaveMasters);
                } else {
                    List<LeaveMaster> leaveMasters = new ArrayList<>();
                    leaveMasters.add(leaveMaster);
                    map.put(leaveMaster.getUserCode().getLogin(), leaveMasters);
                }
            }
            //String logins = map.keySet().toString();
            //logins = logins.substring(1, logins.length() - 1);
            List<Object[]> employeeViews = employeeViewRepository.findAllByCardNoAndDate(Timestamp.from(search.getLeaveDateFrom()), Timestamp.from(search.getLeaveDateTo()), Timestamp.from(Instant.now()));
            for (Object object : employeeViews) {
                Object[] objects = (Object[]) object;
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                if (map.containsKey(objects[0].toString())) {
                    List<LeaveMaster> leaveMasters = map.get(objects[0].toString());
                    java.util.Date dayStatusDate = Date.from(((Timestamp) objects[3]).toInstant());
                    for (LeaveMaster leaveMaster : leaveMasters) {
                        if ((dayStatusDate.before(Date.from(leaveMaster.getLeaveDateTo())) && dayStatusDate.after(Date.from(leaveMaster.getLeaveDateFrom()))) || format.format(dayStatusDate).equalsIgnoreCase(format.format(Date.from(leaveMaster.getLeaveDateTo()))) || format.format(dayStatusDate).equals(format.format(Date.from(leaveMaster.getLeaveDateFrom())))) {
                            LeaveDiffReportBean bean = new LeaveDiffReportBean();
                            bean.setEmpCode(objects[0].toString());
                            bean.setEmpName(objects[1].toString());
                            bean.setStatus(objects[2].toString());
                            bean.setLeaveDate(format.format(dayStatusDate));
                            bean.setLeaveDateDt(dayStatusDate);
                            bean.setLeaveTypeCode(leaveMaster.getLeaveTypeMaster().getLeaveName());
                            bean.setLeaveSubTypeCode(leaveMaster.getLeaveSubTypeMaster().getSubTypeName());
                            leaveDiffReportBean.add(bean);
                        }
                    }
                }
            }
        }
        if (leaveDiffReportBean != null && leaveDiffReportBean.size() > 0) {
            Collections.sort(leaveDiffReportBean, Comparator.comparing(LeaveDiffReportBean :: getEmpCode)
                .thenComparing(LeaveDiffReportBean::getLeaveDateDt));
        }
        String path = applicationProperties.getTemplatePath() + "jasper/";
        JasperDesign jasperDesign = JRXmlLoader.load(path + "/LeaveDifference.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String, Object> parameters = new HashMap<String, Object>();
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(leaveDiffReportBean);

        parameters.put("datasource", jrDataSource);
        parameters.put("SUBREPORT_DIR", path);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=LeaveDifference.xlsx");

        final OutputStream outputStream = response.getOutputStream();
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
        exporter.exportReport();
    }

    @PostMapping("/leave-masters-pending-report")
    @Timed
    public @ResponseBody void getLeavePendingReport(@Valid @RequestBody(required = false) LeaveSearch search, HttpServletResponse response) throws JRException, IOException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
        List<LeavePendingReportBean> leavePendingReportBean = new ArrayList<>();

        List<LeaveMaster> leaveMasters = null;
        if(search.getLeaveStatus() != null && search.getLeaveStatus().equalsIgnoreCase("C")) {
            leaveMasters = leaveMasterRepository.findAllByLeaveDate(search.getLeaveDateFrom(), search.getLeaveDateTo(), search.getFactory());
        } else {
            leaveMasters = leaveMasterRepository.findAllByLeaveStatus(search.getLeaveStatus(), search.getFactory());
        }
        Map<String, EmployeeView> hodMap = new HashMap<>();
        if (leaveMasters.size() > 0) {
            for (LeaveMaster leaveMaster : leaveMasters) {
                LeavePendingReportBean bean = new LeavePendingReportBean();
                bean.setHodCardNo(leaveMaster.getHodApprovedBy());
                if (hodMap.containsKey(leaveMaster.getHodApprovedBy())) {
                    EmployeeView user = hodMap.get(leaveMaster.getHodApprovedBy());
                    bean.setHodName(user.getName());
                } else {
                    EmployeeView user = employeeViewRepository.findByCardNo(leaveMaster.getHodApprovedBy()).orElse(null);
                    if (user != null) {
                        bean.setHodName(user.getName());
                        hodMap.put(leaveMaster.getHodApprovedBy(), user);
                    }
                }
                bean.setEmpCode(leaveMaster.getUserCode().getLogin());
                bean.setName(leaveMaster.getUserCode().getFirstName());
                bean.setLeaveFrom(format.format(Date.from(leaveMaster.getLeaveDateFrom())) + " " + formatTime.format(Date.from(leaveMaster.getLeaveTimeFrom())));
                bean.setLeaveTo(format.format(Date.from(leaveMaster.getLeaveDateTo())) + " " + (leaveMaster.getLeaveTimeTo() != null ? formatTime.format(Date.from(leaveMaster.getLeaveTimeTo())) : ""));
                bean.setLeaveType(leaveMaster.getLeaveTypeMaster().getLeaveName());
                bean.setLeaveSubType(leaveMaster.getLeaveSubTypeMaster().getSubTypeName());
                bean.setFlag(leaveMaster.getFlag() != null && leaveMaster.getFlag().equalsIgnoreCase("A") ? "Pending with HR" : leaveMaster.getFlag() != null && leaveMaster.getFlag().equalsIgnoreCase("C") ? "Approved By HR": "Pending with HOD");
                bean.setReqDate(format.format(Date.from(leaveMaster.getCreatedDate())));
                bean.setReqTime(formatTime.format(Date.from(leaveMaster.getCreatedDate())));
                leavePendingReportBean.add(bean);
            }
        }
        Collections.sort(leavePendingReportBean, Comparator.comparing(LeavePendingReportBean::getHodCardNo).thenComparing(LeavePendingReportBean::getEmpCode));
        String path = applicationProperties.getTemplatePath() + "jasper/";
        JasperDesign jasperDesign = JRXmlLoader.load(path + "/LeavePendingReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String, Object> parameters = new HashMap<String, Object>();
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(leavePendingReportBean);

        parameters.put("datasource", jrDataSource);
        parameters.put("SUBREPORT_DIR", path);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=LeavePendingReport.xlsx");

        final OutputStream outputStream = response.getOutputStream();
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
        exporter.exportReport();
    }

    private java.util.Date allowDateEmployee( java.util.Date entryDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(entryDate);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, 3);
        calendar.set(Calendar.HOUR, 6);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.AM_PM, Calendar.PM);
        return calendar.getTime();
    }

    private java.util.Date allowDateApproval( java.util.Date entryDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(entryDate);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, 4);
        calendar.set(Calendar.HOUR, 11);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.AM_PM, Calendar.AM);
        return calendar.getTime();
    }
}
