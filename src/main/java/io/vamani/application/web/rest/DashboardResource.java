package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.domain.HolidayMaster;
import io.vamani.application.domain.MonthlyNewsData;
import io.vamani.application.domain.PollMaster;
import io.vamani.application.model.*;
import io.vamani.application.mssql.domain.*;
import io.vamani.application.mssql.repository.*;
import io.vamani.application.repository.*;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.service.MailService;
import io.vamani.application.util.AttendanceUtil;
import io.vamani.application.util.MD5UrlEncryption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.validation.Valid;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * REST controller for managing Dashboard.
 */
@RestController
@RequestMapping("/api")
public class DashboardResource {
    private final Logger log = LoggerFactory.getLogger(DashboardResource.class);

    private static final String ENTITY_NAME = "companyMaster";

    @Autowired
    private EmployeeViewRepository employeeViewRepository;

    @Autowired
    private PollMasterRepository pollMasterRepository;

    @Autowired
    private PollDetailsRepository pollDetailsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HolidayMasterRepository holidayMasterRepository;

    @Autowired
    private DayStatusRepository dayStatusRepository;

    @Autowired
    private ShiftRepository shiftRepository;

    @Autowired
    private HourtRepository hourtRepository;

    @Autowired
    private EmpHistRepository empHistRepository;

    @Autowired
    private QuotesRepository quotesRepository;

    @Autowired
    private LeaveMasterRepository leaveMasterRepository;

    @Autowired
    private MonthlyNewsDataRepository monthlyNewsDataRepository;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private MailService mailService;

    @Autowired
    private ApplicationProperties applicationProperties;

    /**
     * GET  /company-masters : get all the companyMasters.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of companyMasters in body
     */
    @GetMapping("/dashboard")
    @Timed
    public ResponseEntity<Dashboard> getDashBoard() throws ParseException, NoSuchAlgorithmException {
        log.debug("REST request to get Dashboard");
        Dashboard dashboard = new Dashboard();

        SimpleDateFormat format = new SimpleDateFormat("MMM-yyyy");
        SimpleDateFormat format1 = new SimpleDateFormat("dd-MMM-yyyy");
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DATE);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        EmployeeView employeeView = employeeViewRepository.findById(SecurityUtils.getCurrentUserLogin().orElse(null)).orElse(null);
        dashboard.setBirthdayList(employeeViewRepository.getBirthdayList(day, month, employeeView.getSubSname().trim()));
        dashboard.setAnniversaryList(employeeViewRepository.getAnniversaryList(day, month, year));
        dashboard.setHolidayMastersList(holidayMasterRepository.findAllByFactoryMaster(employeeView.getFactory().trim()));
        MonthlyNewsData monthlyNewsData = monthlyNewsDataRepository.findAllClosedDate();
        if (monthlyNewsData != null) {
            monthlyNewsData.setFileName(MD5UrlEncryption.fakeUploadUrl("news-letter/"+monthlyNewsData.getFileName()));
        }
        dashboard.setMonthlyNews(monthlyNewsData);
        // List<PollMaster> pollMasters = pollMasterRepository.findAllByFactoryMasterscodeName(employeeView.getSubSname().trim());
        List<PollMaster> pollMasters = pollMasterRepository.findAllWithoutFactory();
        PollMaster pollMaster = null;
        if (pollMasters != null && pollMasters.size() > 0) {
            pollMaster = pollMasters.get(0);
            pollMaster.setPollDetails(pollDetailsRepository.findAllByPollMasterOrderByIdAsc(pollMaster));
        }
        dashboard.setPollMaster(pollMaster);
        short mm = 0;
        if (month >= 1 && month <= 3) {
            mm = (short) (9 + month);
        } else {
            mm = (short) (month - 3);
        }
        List<DayStatus> dayStatusList = dayStatusRepository.findAllByEmpCodeAndDate(employeeView.getEmpCode(), month, year);
        Hourt hourt = hourtRepository.findById(new HourtId(Integer.parseInt(employeeView.getEmpCode()), mm)).orElse(null);

        if (dayStatusList != null && hourt != null) {
            dashboard.setAttendanceList(AttendanceUtil.getAllendance(dayStatusList, hourt, shiftRepository, dashboard.getHolidayMastersList()));
        }
        EmpHistId empHistId = new EmpHistId();
        empHistId.setEmpCode(employeeView.getEmpCode());
        empHistId.setMonthYear(new SimpleDateFormat("MM-yyyy").format(new Date()));
        EmpHist empHist = empHistRepository.findById(empHistId).orElse(null);
        if (empHist != null) {
            List<LeaveStatus> leaveStatusList = new ArrayList<>();
            ZonedDateTime doj = employeeView.getDoj();
            Date dt = Date.from(doj.toInstant());
            dt = format1.parse("01-" + format.format(dt));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dt);
            calendar.add(Calendar.MONTH, 9);
            Date dobCurrent = calendar.getTime();
            Date currentDate = format1.parse(format1.format(date));
            if (dobCurrent.before(currentDate)) {
                leaveStatusList.add(new LeaveStatus("EL", empHist.getBel()));
            } else if (dobCurrent.equals(currentDate)) {
                leaveStatusList.add(new LeaveStatus("EL", empHist.getBel()));
            } else {
                leaveStatusList.add(new LeaveStatus("EL", 0.0));
            }
            leaveStatusList.add(new LeaveStatus("CL", empHist.getBcl()));
            leaveStatusList.add(new LeaveStatus("SL", empHist.getBsl()));
            dashboard.setLeaveStatusList(leaveStatusList);
        }
        // Random Quote Generate
        long low = 1;
        long high = quotesRepository.getCount();
        long id = (long) (Math.random() * (high - low)) + low;
        if (id >= low && id <= high) {
            dashboard.setQuotes(quotesRepository.findById(id).orElse(null));
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(dashboard));
    }

    @GetMapping("/dashboard-calendar/{monthYear}")
    @Timed
    public ResponseEntity<Dashboard> getDashBoardCalendar(@PathVariable String monthYear) throws ParseException {
        Dashboard dashboard = new Dashboard();
        SimpleDateFormat format = new SimpleDateFormat("MMM-yyyy");
        Date date = new SimpleDateFormat("dd-MM-yyyy").parse("01-" + monthYear);
        String []arr = monthYear.split("-");

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH) + 1;
        short mm = 0;
        if (month >= 1 && month <= 3) {
            mm = (short) (9 + month);
        } else {
            mm = (short) (month - 3);
        }

        EmployeeView employeeView = employeeViewRepository.findById(SecurityUtils.getCurrentUserLogin().orElse(null)).orElse(null);
        List<DayStatus> dayStatusList = dayStatusRepository.findAllByEmpCodeAndDate(employeeView.getEmpCode(), Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
        Hourt hourt = hourtRepository.findById(new HourtId(Integer.parseInt(employeeView.getEmpCode()), mm)).orElse(null);
        List<HolidayMaster> holidayMasters = holidayMasterRepository.findAllByFactoryMaster(employeeView.getFactory().trim());
        if (dayStatusList != null && hourt != null) {
            dashboard.setAttendanceList(AttendanceUtil.getAllendance(dayStatusList, hourt, shiftRepository, holidayMasters));
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(dashboard));
    }

    @GetMapping("/leave-balance/{leaveType}")
    @Timed
    public ResponseEntity<LeaveStatus> getLeaveBalance(@PathVariable String leaveType) throws ParseException {
        EmployeeView employeeView = employeeViewRepository.findById(SecurityUtils.getCurrentUserLogin().orElse(null)).orElse(null);
        LeaveStatus leaveStatus = null;

        Float noDays = leaveMasterRepository.getNoOfDays(SecurityUtils.getCurrentUserLogin().orElse(null), leaveType);
        if(noDays != null){} else {
            noDays = 0.0F;
        }

        EmpHistId empHistId = new EmpHistId();
        empHistId.setEmpCode(employeeView.getEmpCode());
        empHistId.setMonthYear(new SimpleDateFormat("MM-yyyy").format(new Date()));
        // empHistId.setMonthYear("03-2021");
        EmpHist empHist = empHistRepository.findById(empHistId).orElse(null);
        if (empHist != null) {
            if (leaveType != null && leaveType.equalsIgnoreCase("CL")) {
                leaveStatus = new LeaveStatus("CL", empHist.getBcl()-noDays);
            } else if (leaveType != null && leaveType.equalsIgnoreCase("SL")) {
                leaveStatus = new LeaveStatus("SL", empHist.getBsl()-noDays);
            } else if (leaveType != null && leaveType.equalsIgnoreCase("EL")) {
                SimpleDateFormat format = new SimpleDateFormat("MMM-yyyy");
                SimpleDateFormat format1 = new SimpleDateFormat("dd-MMM-yyyy");
                ZonedDateTime doj = employeeView.getDoj();
                Date dt = Date.from(doj.toInstant());
                dt = format1.parse("01-" + format.format(dt));
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dt);
                calendar.add(Calendar.MONTH, 9);
                Date dobCurrent = calendar.getTime();
                Date currentDate = format1.parse(format1.format(new Date()));
                if (dobCurrent.before(currentDate)) {
                    leaveStatus = new LeaveStatus("EL", empHist.getBel()-noDays);
                } else if (dobCurrent.equals(currentDate)) {
                    leaveStatus = new LeaveStatus("EL", empHist.getBel()-noDays);
                } else {
                    leaveStatus = new LeaveStatus("EL", 0.0);
                }
            }
        } else if (leaveType != null && (leaveType.equalsIgnoreCase("CL") || leaveType.equalsIgnoreCase("SL") || leaveType.equalsIgnoreCase("EL"))) {
            if (leaveType != null && leaveType.equalsIgnoreCase("CL")) {
                leaveStatus = new LeaveStatus("CL", 0.0);
            } else if (leaveType != null && leaveType.equalsIgnoreCase("SL")) {
                leaveStatus = new LeaveStatus("SL", 0.0);
            } else if (leaveType != null && leaveType.equalsIgnoreCase("EL")) {
                leaveStatus = new LeaveStatus("EL", 0.0);
            }
        }

        if (leaveType != null && leaveType.equalsIgnoreCase("CO")) {
            leaveStatus = new LeaveStatus("CO", 0.0);
        } else if (leaveType != null && leaveType.equalsIgnoreCase("LW")) {
            leaveStatus = new LeaveStatus("LW", 365.0);
        } else if (leaveType != null && leaveType.equalsIgnoreCase("OD")) {
            leaveStatus = new LeaveStatus("OD", 0.0);
        } else if (leaveType != null && leaveType.equalsIgnoreCase("SP")) {
            leaveStatus = new LeaveStatus("SP", 0.0);
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(leaveStatus));
    }

    @PostMapping("/leave-balance")
    @Timed
    public ResponseEntity<LeaveStatus> getLeaveBalance(@RequestBody Master master) throws ParseException {
        EmployeeView employeeView = employeeViewRepository.findById(master.getId()).orElse(null);
        LeaveStatus leaveStatus = null;

        Float noDays = leaveMasterRepository.getNoOfDays(master.getId(), master.getDesc());
        if(noDays != null){} else {
            noDays = 0.0F;
        }

        EmpHistId empHistId = new EmpHistId();
        empHistId.setEmpCode(employeeView.getEmpCode());
        empHistId.setMonthYear(new SimpleDateFormat("MM-yyyy").format(new Date()));
        EmpHist empHist = empHistRepository.findById(empHistId).orElse(null);
        if (empHist != null) {
            if (master.getDesc() != null && master.getDesc().equalsIgnoreCase("CL")) {
                leaveStatus = new LeaveStatus("CL", empHist.getBcl()-noDays);
            } else if (master.getDesc() != null && master.getDesc().equalsIgnoreCase("SL")) {
                leaveStatus = new LeaveStatus("SL", empHist.getBsl()-noDays);
            } else if (master.getDesc() != null && master.getDesc().equalsIgnoreCase("EL")) {
                SimpleDateFormat format = new SimpleDateFormat("MMM-yyyy");
                SimpleDateFormat format1 = new SimpleDateFormat("dd-MMM-yyyy");
                ZonedDateTime doj = employeeView.getDoj();
                Date dt = Date.from(doj.toInstant());
                dt = format1.parse("01-" + format.format(dt));
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dt);
                calendar.add(Calendar.MONTH, 9);
                Date dobCurrent = calendar.getTime();
                Date currentDate = format1.parse(format1.format(new Date()));
                if (dobCurrent.before(currentDate)) {
                    leaveStatus = new LeaveStatus("EL", empHist.getBel()-noDays);
                } else if (dobCurrent.equals(currentDate)) {
                    leaveStatus = new LeaveStatus("EL", empHist.getBel()-noDays);
                } else {
                    leaveStatus = new LeaveStatus("EL", 0.0);
                }
            }
        } else if (master.getDesc() != null && (master.getDesc().equalsIgnoreCase("CL") || master.getDesc().equalsIgnoreCase("SL") || master.getDesc().equalsIgnoreCase("EL"))) {
            if (master.getDesc() != null && master.getDesc().equalsIgnoreCase("CL")) {
                leaveStatus = new LeaveStatus("CL", 0.0);
            } else if (master.getDesc() != null && master.getDesc().equalsIgnoreCase("SL")) {
                leaveStatus = new LeaveStatus("SL", 0.0);
            } else if (master.getDesc() != null && master.getDesc().equalsIgnoreCase("EL")) {
                leaveStatus = new LeaveStatus("EL", 0.0);
            }
        }

        if (master.getDesc() != null && master.getDesc().equalsIgnoreCase("CO")) {
            leaveStatus = new LeaveStatus("CO", 0.0);
        } else if (master.getDesc() != null && master.getDesc().equalsIgnoreCase("LW")) {
            leaveStatus = new LeaveStatus("LW", 365.0);
        } else if (master.getDesc() != null && master.getDesc().equalsIgnoreCase("OD")) {
            leaveStatus = new LeaveStatus("OD", 0.0);
        } else if (master.getDesc() != null && master.getDesc().equalsIgnoreCase("SP")) {
            leaveStatus = new LeaveStatus("SP", 0.0);
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(leaveStatus));
    }

    @PostMapping("/post-wishes")
    @Timed
    public ResponseEntity<Message> postWishes(@Valid @RequestBody WishBean wishBean) {
        boolean postmail = false;
        log.debug("REST request to get Dashboard");
        if (wishBean != null && wishBean.getCelebrationMessageText() != null && wishBean.getEmployeeView() != null) {
            if (wishBean.getEmployeeView() != null && wishBean.getEmployeeView().getEmail() != null && wishBean.getEmployeeView().getEmail().length()>0) {
                EmployeeView user = employeeViewRepository.findById(SecurityUtils.getCurrentUserLogin().orElse(null).toUpperCase()).orElse(null);
                if (user != null) {
                    Locale locale = Locale.forLanguageTag("en");
                    Context context = new Context(locale);
                    context.setVariable("fullName", wishBean.getEmployeeView().getName());
                    context.setVariable("celebrationMessageText", wishBean.getCelebrationMessageText());
                    context.setVariable("fromName", user.getName());
                    context.setVariable("fromEmail", user.getEmail());
                    String content = null;
                    String subject = null;
                    if (wishBean.getMessageType() != null && wishBean.getMessageType().equalsIgnoreCase("JOB")) {
                        content = templateEngine.process("mail/working_anniversary_mail", context);
                        subject = "Job Anniversary Wishes!!!";
                    } else {
                        content = templateEngine.process("mail/birthday_mail", context);
                        subject = "Happy Birthday Wishes!!!";
                    }
                    try {
                        mailService.sendEmail(wishBean.getEmployeeView().getEmail(), subject, content, false, true);
                        postmail = true;
                    } catch (Exception e) {
                    }
                }
            }
        }
        if (postmail == true) {
            return ResponseUtil.wrapOrNotFound(Optional.of(new Message("success", "Wishes sent successfully!!!", true, null)));
        } else {
            return ResponseUtil.wrapOrNotFound(Optional.of(new Message("error", "Wishes not sent!!!", false, "Wishes not sent!!!")));
        }
    }
}
