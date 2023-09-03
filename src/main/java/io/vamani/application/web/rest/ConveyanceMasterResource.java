package io.vamani.application.web.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import io.vamani.application.domain.*;
import io.vamani.application.domain.User;
import io.vamani.application.firebase.FirebaseSystem;
import io.vamani.application.mobile.*;
import io.vamani.application.mssql.domain.EmployeeView;
import io.vamani.application.repository.HolidayMasterRepository;
import io.vamani.application.service.MailService;
import io.vamani.application.util.CustomMultipartFile;
import io.vamani.application.util.DateUtils;
import net.sf.jasperreports.engine.*;
import org.apache.commons.collections4.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import com.codahale.metrics.annotation.Timed;
import com.ibm.icu.util.Calendar;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.model.ConveyanceMasterBean;
import io.vamani.application.model.ConveyanceMasterDetailsBean;
import io.vamani.application.model.ConveyanceReportBean;
import io.vamani.application.model.ConveyanceSearchMaster;
import io.vamani.application.model.Master;
import io.vamani.application.model.Message;
import io.vamani.application.mssql.domain.EmployeeMatView;
import io.vamani.application.mssql.repository.EmployeeMatViewRepository;
import io.vamani.application.repository.ConveyanceMasterDetailsRepository;
import io.vamani.application.repository.ConveyanceMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * REST controller for managing {@link io.vamani.application.domain.ConveyanceMaster}.
 */
@RestController
@RequestMapping("/api")
public class ConveyanceMasterResource {

    private final Logger log = LoggerFactory.getLogger(ConveyanceMasterResource.class);

    private static final String ENTITY_NAME = "conveyanceMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private ConveyanceMasterDetailsRepository conveyanceAttachRepository;

    @Autowired
    private EmployeeMatViewRepository employeeMatViewRepository;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private MailService mailService;

    @Autowired
    private HolidayMasterRepository holidayMasterRepository;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private final ConveyanceMasterRepository conveyanceMasterRepository;

    public ConveyanceMasterResource(ConveyanceMasterRepository conveyanceMasterRepository) {
        this.conveyanceMasterRepository = conveyanceMasterRepository;
    }

    /**
     * {@code POST  /conveyance-masters} : Create a new conveyanceMaster.
     *
     * @param conveyanceMaster the conveyanceMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new conveyanceMaster, or with status {@code 400 (Bad Request)} if the conveyanceMaster has already an ID.
     * @throws URISyntaxException       if the Location URI syntax is incorrect.
     * @throws IOException
     * @throws java.text.ParseException
     * @throws ParseException
     */
    @PostMapping(value = "/conveyance-masters", consumes = {"multipart/form-data"})
    @Timed
    public ResponseEntity<ConveyanceMaster> createConveyanceMaster(@RequestParam(required = false) MultipartFile[] file, String[] tripStart, String[] tripEnd, String[] miscAmount, String[] fromLocation, String[] toLocation, String[] reason, String conveyanceDate, String conveyanceType, String vehicleNo, String totalDistance, String rate, String totalAmount, String approvedBy, String vehicleType) throws URISyntaxException, IOException, ParseException {
        log.debug("REST request to save ConveyanceMaster : {}");
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(conveyanceDate);
        Instant instant = date.toInstant();
        String empCode = SecurityUtils.getCurrentUserLogin().orElse(null);
        ConveyanceMaster conveyanceMasterEdit = conveyanceMasterRepository.findByEmpCodeAndConveyanceDate(empCode, instant);
        ConveyanceMaster result = null;
        if (conveyanceMasterEdit != null) {
            return ResponseEntity.badRequest()
                .headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "Entry already exist in same date")).build();
        } else {
            java.util.Date allowDate = allowDateEmployee(java.util.Date.from(instant));
            /*if(allowDate.before(new java.util.Date())) {
                return ResponseEntity.badRequest().headers(io.vamani.application.web.rest.util.HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Conveyance Entry is  not allowed. Previous Month salary locked.")).body(null);
            }*/

            String UPLOADED_FOLDER = applicationProperties.getUploadPath();
            EmployeeMatView employeeMatView = employeeMatViewRepository.findByLogins(empCode);
            ConveyanceMaster conveyanceMaster = new ConveyanceMaster();
            conveyanceMaster.setConveyanceType(conveyanceType);
            conveyanceMaster.setTotalDistance(Integer.parseInt(totalDistance));
            conveyanceMaster.setVehicleNo(vehicleNo);
            conveyanceMaster.setRate(Double.parseDouble(rate));
            conveyanceMaster.setConveyanceDate(instant);
            conveyanceMaster.setTotalAmount(Double.parseDouble(totalAmount));
            conveyanceMaster.setApprovedBy(approvedBy);
            conveyanceMaster.setVehicleType(vehicleType);
            conveyanceMaster.setFlag("E");
            conveyanceMaster.setFactory(employeeMatView.getFactory());
            conveyanceMaster.setEmpCode(employeeMatView.getLogin());
            conveyanceMaster.setCreatedBy(empCode);
            conveyanceMaster.setCreatedDate(Instant.now());
            result = conveyanceMasterRepository.save(conveyanceMaster);
            if (fromLocation != null && fromLocation.length > 0) {
                for (int i = 0; i < fromLocation.length; i++) {
                    if (!fromLocation[i].equals("undefined")) {
                        ConveyanceMasterDetails conveyanceAttach = new ConveyanceMasterDetails();
                        conveyanceAttach.setConveyanceMaster(result);
                        conveyanceAttach.setFromLocation(fromLocation[i]);
                        conveyanceAttach.setToLocation(toLocation[i]);
                        conveyanceAttach.setTripStart(Integer.parseInt(tripStart[i]));
                        conveyanceAttach.setTripEnd(Integer.parseInt(tripEnd[i]));
                        if (miscAmount[i] != null && miscAmount[i].length() > 0) {
                            conveyanceAttach.setMiscAmount(Double.parseDouble(miscAmount[i]));
                        }
                        conveyanceAttach.setReason(reason[i]);
                        conveyanceAttach.setCreatedDate(Instant.now());
                        conveyanceAttach.setCreatedBy(empCode);
                        MultipartFile multipartFile = file[i];
                        if (!multipartFile.getOriginalFilename().equals("fake.pdf")) {
                            conveyanceAttach = conveyanceAttachRepository.save(conveyanceAttach);
                            String extn = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."), multipartFile.getOriginalFilename().length());
                            conveyanceAttach.setAttachFile(conveyanceAttach.getId() + extn);
                            conveyanceAttach.setAttachDisplayFile(multipartFile.getOriginalFilename());
                            byte[] bytes = multipartFile.getBytes();
                            Path path = Paths.get(UPLOADED_FOLDER + "conveyance/" + conveyanceAttach.getId() + extn);
                            Files.write(path, bytes);
                        }
                        conveyanceAttachRepository.save(conveyanceAttach);
                    }
                }
            }
            return ResponseEntity.created(new URI("/api/conveyance-masters/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
        }
    }


    /**
     * {@code POST  /conveyance-masters} : Create a new conveyanceMaster.
     *
     * @param conveyanceMaster the conveyanceMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new conveyanceMaster, or with status {@code 400 (Bad Request)} if the conveyanceMaster has already an ID.
     * @throws URISyntaxException       if the Location URI syntax is incorrect.
     * @throws IOException
     * @throws java.text.ParseException
     * @throws ParseException
     */
    @PostMapping("/conveyance-masters-mobile")
    public ResponseEntity<ConveyanceMaster> createConveyanceMaster(@Valid @RequestBody ConveyanceMasterBean conveyanceMasterBean) throws URISyntaxException, ParseException, IOException {
        ConveyanceMaster conveyanceMaster = new ConveyanceMaster();
        log.debug("REST request to save ConveyanceMaster : {}", conveyanceMaster);
        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(conveyanceMasterBean.getConveyanceFromDate());
        Instant instant = date.toInstant();
        String empCode = SecurityUtils.getCurrentUserLogin().orElse(null);
        ConveyanceMaster conveyanceMasterEdit = conveyanceMasterRepository.findByEmpCodeAndConveyanceDate(empCode, instant);
        ConveyanceMaster result = null;
        if (conveyanceMasterEdit != null) {
            return ResponseEntity.badRequest()
                .headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "Entry already exist in same date")).build();
        } else {
            String UPLOADED_FOLDER = applicationProperties.getUploadPath();
            EmployeeMatView employeeMatView = employeeMatViewRepository.findByLogins(empCode);
            conveyanceMaster.setConveyanceType(conveyanceMasterBean.getConveyanceType());
            conveyanceMaster.setTotalDistance(conveyanceMasterBean.getTotalDistance());
            conveyanceMaster.setVehicleNo(conveyanceMasterBean.getVehicleNo());
            conveyanceMaster.setRate(conveyanceMasterBean.getRate());
            conveyanceMaster.setConveyanceDate(instant);
            conveyanceMaster.setTotalAmount(conveyanceMasterBean.getTotalAmount());
            conveyanceMaster.setApprovedBy(conveyanceMasterBean.getApprovedBy());
            conveyanceMaster.setVehicleType(conveyanceMasterBean.getVehicleType());
            conveyanceMaster.setFlag("E");
            conveyanceMaster.setFactory(employeeMatView.getFactory());
            conveyanceMaster.setEmpCode(employeeMatView.getLogin());
            conveyanceMaster.setCreatedBy(empCode);
            conveyanceMaster.setCreatedDate(Instant.now());
            result = conveyanceMasterRepository.save(conveyanceMaster);
            if (conveyanceMasterBean.getConveyanceMasterDetailsbean() != null && conveyanceMasterBean.getConveyanceMasterDetailsbean().size() > 0) {
                for (ConveyanceMasterDetailsBean bean : conveyanceMasterBean.getConveyanceMasterDetailsbean()) {
                    if (bean.getFromLocation() != null && bean.getToLocation() != null) {
                        ConveyanceMasterDetails conveyanceAttach = new ConveyanceMasterDetails();
                        BeanUtils.copyProperties(bean, conveyanceAttach);
                        conveyanceAttach.setConveyanceMaster(result);
                        conveyanceAttach.setCreatedDate(Instant.now());
                        conveyanceAttach.setCreatedBy(empCode);
                        if (bean.getMultipartFile() != null) {
                            conveyanceAttach = conveyanceAttachRepository.save(conveyanceAttach);
                            String extn = bean.getMultipartFile().getOriginalFilename().substring(bean.getMultipartFile().getOriginalFilename().lastIndexOf("."), bean.getMultipartFile().getOriginalFilename().length());
                            conveyanceAttach.setAttachFile(conveyanceAttach.getId() + extn);
                            conveyanceAttach.setAttachDisplayFile(bean.getMultipartFile().getOriginalFilename());
                            byte[] bytes = bean.getMultipartFile().getBytes();
                            Path path = Paths.get(UPLOADED_FOLDER + "conveyance/" + conveyanceAttach.getId() + extn);
                            Files.write(path, bytes);
                        }
                        conveyanceAttachRepository.save(conveyanceAttach);
                    }
                }
            }
        }
        return ResponseEntity.created(new URI("/api/conveyance-masters-mobile/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PostMapping("/conveyance-masters-update-mobile")
    public ResponseEntity<ConveyanceMaster> updateConveyanceMasterUpdate(@Valid @RequestBody ConveyanceMasterBean conveyanceMasterBean) throws URISyntaxException, ParseException, IOException {
        ConveyanceMaster conveyanceMaster = conveyanceMasterRepository.findAllById(conveyanceMasterBean.getId());
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        EmployeeMatView employeeMatView = employeeMatViewRepository.findByLogins(SecurityUtils.getCurrentUserLogin().orElse(null));
        conveyanceMaster.setConveyanceType(conveyanceMasterBean.getConveyanceType());
        conveyanceMaster.setTotalDistance(conveyanceMasterBean.getTotalDistance());
        conveyanceMaster.setVehicleNo(conveyanceMasterBean.getVehicleNo());
        conveyanceMaster.setRate(conveyanceMasterBean.getRate());
        Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(conveyanceMasterBean.getConveyanceFromDate());
        conveyanceMaster.setConveyanceDate(date1.toInstant());
        conveyanceMaster.setTotalAmount(conveyanceMasterBean.getTotalAmount());
        conveyanceMaster.setApprovedBy(conveyanceMasterBean.getApprovedBy());
        conveyanceMaster.setFlag("E");
        conveyanceMaster.setFactory(employeeMatView.getFactory());
        conveyanceMaster.setEmpCode(employeeMatView.getLogin());
        conveyanceMaster.setVehicleType(conveyanceMasterBean.getVehicleType());
        conveyanceMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        conveyanceMaster.setLastUpdatedDate(Instant.now());
        ConveyanceMaster result = conveyanceMasterRepository.save(conveyanceMaster);
        if (conveyanceMasterBean.getConveyanceMasterDetailsbean() != null && conveyanceMasterBean.getConveyanceMasterDetailsbean().size() > 0) {
            for (ConveyanceMasterDetailsBean bean : conveyanceMasterBean.getConveyanceMasterDetailsbean()) {
                if (bean.getFromLocation() != null && bean.getToLocation() != null) {
                    ConveyanceMasterDetails conveyanceAttach = null;
                    if (bean.getId() != null) {
                        conveyanceAttach = conveyanceAttachRepository.findById(bean.getId()).orElse(null);
                        conveyanceAttach.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                        conveyanceAttach.setLastUpdatedDate(Instant.now());
                    } else {
                        conveyanceAttach = new ConveyanceMasterDetails();
                        conveyanceAttach.setId(null);
                        conveyanceAttach.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                        conveyanceAttach.setCreatedDate(Instant.now());
                    }
                    conveyanceAttach.setConveyanceMaster(result);
                    conveyanceAttach.setFromLocation(bean.getFromLocation());
                    conveyanceAttach.setToLocation(bean.getToLocation());
                    conveyanceAttach.setTripStart(bean.getTripStart());
                    conveyanceAttach.setTripEnd(bean.getTripEnd());
                    conveyanceAttach.setMiscAmount(bean.getMiscAmount());
                    conveyanceAttach.setReason(bean.getReason());
                    if (bean.getMultipartFile() != null) {
                        conveyanceAttach = conveyanceAttachRepository.save(conveyanceAttach);
                        String extn = bean.getMultipartFile().getOriginalFilename().substring(bean.getMultipartFile().getOriginalFilename().lastIndexOf("."), bean.getMultipartFile().getOriginalFilename().length());
                        conveyanceAttach.setAttachFile(conveyanceAttach.getId() + extn);
                        conveyanceAttach.setAttachDisplayFile(bean.getMultipartFile().getOriginalFilename());
                        byte[] bytes = bean.getMultipartFile().getBytes();
                        Path path = Paths.get(UPLOADED_FOLDER + "conveyance/" + conveyanceAttach.getId() + extn);
                        Files.write(path, bytes);
                    }
                    conveyanceAttachRepository.save(conveyanceAttach);
                }
            }
        }
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, conveyanceMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code Pos  /conveyance-masters} : Updates an existing conveyanceMaster.
     *
     * @param conveyanceMaster the conveyanceMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated conveyanceMaster,
     * or with status {@code 400 (Bad Request)} if the conveyanceMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the conveyanceMaster couldn't be updated.
     * @throws URISyntaxException       if the Location URI syntax is incorrect.
     * @throws IOException
     * @throws java.text.ParseException
     * @throws ParseException
     */
    @PostMapping(value = "/conveyance-masters-update", consumes = {"multipart/form-data"})
    public ResponseEntity<ConveyanceMaster> updateConveyanceMaster(@RequestParam(required = false) MultipartFile[] file, String id, String[] tripStart, String[] tripEnd, String[] miscAmount, String[] fromLocation, String[] toLocation, String[] reason, String conveyanceDate, String conveyanceType, String vehicleNo, String totalDistance, String rate, String totalAmount, String approvedBy, String[] detailId, String vehicleType) throws URISyntaxException, IOException, ParseException {
        log.debug("REST request to update ConveyanceMaster : {}");
        if (id == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ConveyanceMaster conveyanceMaster = conveyanceMasterRepository.findAllById(Long.parseLong(id));
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        EmployeeMatView employeeMatView = employeeMatViewRepository.findByLogins(SecurityUtils.getCurrentUserLogin().orElse(null));
        conveyanceMaster.setConveyanceType(conveyanceType);
        conveyanceMaster.setTotalDistance(Integer.parseInt(totalDistance));
        conveyanceMaster.setVehicleNo(vehicleNo);
        conveyanceMaster.setRate(Double.parseDouble(rate));
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(conveyanceDate);
        conveyanceMaster.setConveyanceDate(date1.toInstant());
        conveyanceMaster.setTotalAmount(Double.parseDouble(totalAmount));
        conveyanceMaster.setApprovedBy(approvedBy);
        conveyanceMaster.setFlag("E");
        conveyanceMaster.setFactory(employeeMatView.getFactory());
        conveyanceMaster.setEmpCode(employeeMatView.getLogin());
        conveyanceMaster.setVehicleType(vehicleType);
        conveyanceMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        conveyanceMaster.setLastUpdatedDate(Instant.now());
        ConveyanceMaster result = conveyanceMasterRepository.save(conveyanceMaster);
        if (fromLocation != null && fromLocation.length > 0) {
            for (int i = 0; i < fromLocation.length; i++) {
                if (!fromLocation[i].equals("undefined")) {
                    ConveyanceMasterDetails conveyanceAttach = null;
                    if (detailId[i] != null && detailId[i].length() > 0) {
                        conveyanceAttach = conveyanceAttachRepository.findById(Long.parseLong(detailId[i])).orElse(null);
                        conveyanceAttach.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                        conveyanceAttach.setLastUpdatedDate(Instant.now());
                    } else {
                        conveyanceAttach = new ConveyanceMasterDetails();
                        conveyanceAttach.setId(null);
                        conveyanceAttach.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                        conveyanceAttach.setCreatedDate(Instant.now());
                    }
                    conveyanceAttach.setConveyanceMaster(result);
                    conveyanceAttach.setFromLocation(fromLocation[i]);
                    conveyanceAttach.setToLocation(toLocation[i]);
                    conveyanceAttach.setTripStart(Integer.parseInt(tripStart[i]));
                    conveyanceAttach.setTripEnd(Integer.parseInt(tripEnd[i]));
                    if (miscAmount[i] != null && miscAmount[i].length() > 0) {
                        conveyanceAttach.setMiscAmount(Double.parseDouble(miscAmount[i]));
                    }
                    conveyanceAttach.setReason(reason[i]);
                    MultipartFile multipartFile = file[i];
                    if (!multipartFile.getOriginalFilename().equals("fake.pdf")) {
                        conveyanceAttach = conveyanceAttachRepository.save(conveyanceAttach);
                        String extn = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."), multipartFile.getOriginalFilename().length());
                        conveyanceAttach.setAttachFile(conveyanceAttach.getId() + extn);
                        conveyanceAttach.setAttachDisplayFile(multipartFile.getOriginalFilename());
                        byte[] bytes = multipartFile.getBytes();
                        Path path = Paths.get(UPLOADED_FOLDER + "conveyance/" + conveyanceAttach.getId() + extn);
                        Files.write(path, bytes);
                    }
                    conveyanceAttachRepository.save(conveyanceAttach);
                }
            }
        }
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, conveyanceMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /conveyance-masters} : get all the conveyanceMasters.
     *
     * @param pageable    the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder  a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of conveyanceMasters in body.
     */
    @PostMapping("/conveyance-masters-qry")
    @Timed
    public ResponseEntity<List<ConveyanceMaster>> getAllConveyanceMasters(@Valid @RequestBody ConveyanceSearchMaster search) throws ParseException {
        log.debug("REST request to get a page of ConveyanceMasters");
        Date date = Date.from(search.getConveyanceDate());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Instant fromDate = format.parse(format.format(calendar.getTime())).toInstant();

        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 59);

        Instant toDate = format.parse(format.format(calendar.getTime())).toInstant();


        Page<ConveyanceMaster> page = null;
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("conveyanceDate").descending()));
        page = conveyanceMasterRepository.findByMonthYear(fromDate, toDate, SecurityUtils.getCurrentUserLogin().orElse(null), search.getPage());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /conveyance-masters} : get all the conveyanceMasters.
     *
     * @param pageable    the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder  a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of conveyanceMasters in body.
     */
    @PostMapping("/conveyance-masters-mobile-qry")
    @Timed
    public ResponseEntity<List<ConveyanceMaster>> getAllConveyanceMasters(@Valid @RequestBody Master search) throws ParseException {
        log.debug("REST request to get a page of ConveyanceMasters");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yyyy");
        Date date = simpleDateFormat.parse(search.getDesc());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Instant fromDate = format.parse(format.format(calendar.getTime())).toInstant();

        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 59);

        Instant toDate = format.parse(format.format(calendar.getTime())).toInstant();


        Page<ConveyanceMaster> page = null;
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("conveyanceDate").descending()));
        page = conveyanceMasterRepository.findByMonthYear(fromDate, toDate, SecurityUtils.getCurrentUserLogin().orElse(null), search.getPage());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    //=================================This is for Hod Approval===========================================
    @PutMapping("/conveyance-masters-approval")
    public ResponseEntity<Message> updateConveyanceMaster(@Valid @RequestBody ConveyanceMaster conveyanceMaster) throws URISyntaxException {
        log.debug("REST request to update ConveyanceMaster : {}", conveyanceMaster);
        if (conveyanceMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        java.util.Date allowDate = allowDateApproval(java.util.Date.from(conveyanceMaster.getConveyanceDate()));
        if(allowDate.before(new java.util.Date())) {
            return ResponseEntity.badRequest().headers(io.vamani.application.web.rest.util.HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Conveyance Approval is  not allowed. Previous Month salary locked.")).body(null);
        }
        conveyanceMaster.setFlag(conveyanceMaster.getFlag());
        conveyanceMaster.setApprovedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        conveyanceMaster.setApprovedDate(Instant.now());
        ConveyanceMaster result = conveyanceMasterRepository.save(conveyanceMaster);
        if(result != null) {
            EmployeeMatView requester = employeeMatViewRepository.findById(result.getEmpCode().toLowerCase()).orElse(null);
            EmployeeMatView approver = employeeMatViewRepository.findById(result.getApprovedBy().toLowerCase()).orElse(null);
            if (conveyanceMaster.getFlag() != null && conveyanceMaster.getFlag().equalsIgnoreCase("A")) {
                if (requester != null && requester != null) {
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
                    Locale locale = Locale.forLanguageTag("en");
                    Context context = new Context(locale);
                    context.setVariable("empCode", requester.getCardNo());
                    context.setVariable("name", requester.getName());
                    context.setVariable("conveyanceDate", format.format(Date.from(result.getConveyanceDate())));
                    context.setVariable("flag", result.getFlag());
                    String content = null;
                    String subject = "Conveyance request approved by HOD for Date " + format.format(Date.from(result.getConveyanceDate()));
                    try {
                        content = templateEngine.process("mail/conveyance_mail", context);
                        if (requester.getEmail() != null && requester.getEmail().length() > 0 && VALID_EMAIL_ADDRESS_REGEX.matcher(approver.getEmail()).find() == true) {
                            mailService.sendEmail(requester.getEmail(), subject, content, false, true);
                        }
                    } catch (Exception e) {
                    }
                    if (requester.getLogin() != null && requester.getLogin().startsWith("102")) {
                        try {
                            mailService.sendEmail("hrd169@vamanioverseas.com", subject, content, false, true);
                        } catch (Exception e) {
                        }
                    } else if (requester.getLogin() != null && requester.getLogin().startsWith("101")) {
                        try {
                            mailService.sendEmail("hrd@vamanioverseas.com", subject, content, false, true);
                        } catch (Exception e) {
                        }
                    } else if (requester.getLogin() != null && requester.getLogin().startsWith("190") || requester.getLogin().startsWith("191") || requester.getLogin().startsWith("201")) {
                        try {
                            mailService.sendEmail("samayyadav@vamanioverseas.com", subject, content, false, true);
                        } catch (Exception e) {
                        }
                    } else if (requester.getLogin() != null && requester.getLogin().startsWith("103")) {
                        try {
                            mailService.sendEmail("amittomar@vamanioverseas.com", subject, content, false, true);
                        } catch (Exception e) {
                        }
                    } else if (requester.getLogin() != null && requester.getLogin().startsWith("104")) {
                        try {
                            mailService.sendEmail("ramavatar@vamanioverseas.com", subject, content, false, true);
                        } catch (Exception e) {
                        }
                    } else if (requester.getLogin() != null && requester.getLogin().startsWith("105")) {
                        try {
                            mailService.sendEmail("hrdb4-noida@vamanioverseas.com", subject, content, false, true);
                        } catch (Exception e) {
                        }
                    } else if (requester.getLogin() != null && requester.getLogin().startsWith("106")) {
                        try {
                            mailService.sendEmail("bhushankumar@vamanioverseas.com", subject, content, false, true);
                        } catch (Exception e) {
                        }
                    }
                }
            } else if (conveyanceMaster.getFlag() != null && conveyanceMaster.getFlag().equalsIgnoreCase("R")) {
                if (requester != null && requester != null) {
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
                    Locale locale = Locale.forLanguageTag("en");
                    Context context = new Context(locale);
                    context.setVariable("empCode", requester.getCardNo());
                    context.setVariable("name", requester.getName());
                    context.setVariable("conveyanceDate", format.format(Date.from(result.getConveyanceDate())));
                    context.setVariable("flag", result.getFlag());
                    String content = null;
                    String subject = "Conveyance request rejected by HOD for Date " + format.format(Date.from(result.getConveyanceDate()));
                    try {
                        content = templateEngine.process("mail/conveyance_mail", context);
                        if (requester.getEmail() != null && requester.getEmail().length() > 0 && VALID_EMAIL_ADDRESS_REGEX.matcher(approver.getEmail()).find() == true) {
                            mailService.sendEmail(requester.getEmail(), subject, content, false, true);
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
        return ResponseEntity.ok()
            .body(new Message("success", "Save Successfully!"));
    }

    @PostMapping("/conveyance-masters-hod")
    @Timed
    public ResponseEntity<List<ConveyanceMasterBean>> getAllConveyanceApprovedMasters(@Valid @RequestBody ConveyanceSearchMaster search) {
        log.debug("REST request to get a page of ConveyanceMasters");
        Map<String, List<ConveyanceMaster>> map = new HashedMap<>();
        List<ConveyanceMasterBean> conveyanceMasterBean = new ArrayList<ConveyanceMasterBean>();
        Page<ConveyanceMaster> page = null;
        if (search.getStatus() != null && search.getStatus().equalsIgnoreCase("P")) {
            search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("conveyanceDate").ascending()));
            page = conveyanceMasterRepository.findAllByHodApprovedByPending(SecurityUtils.getCurrentUserLogin().orElse(null), search.getPage());
            for (ConveyanceMaster conveyanceMaster : page.getContent()) {
                if (map.containsKey(conveyanceMaster.getEmpCode())) {
                    List<ConveyanceMaster> list = map.get(conveyanceMaster.getEmpCode());
                    list.add(conveyanceMaster);
                    map.put(conveyanceMaster.getEmpCode(), list);
                } else {
                    List<ConveyanceMaster> list = new ArrayList<>();
                    list.add(conveyanceMaster);
                    map.put(conveyanceMaster.getEmpCode(), list);
                }
            }
            if (page.getContent() != null && page.getContent().size() > 0) {
                List<EmployeeMatView> employeeMatView = employeeMatViewRepository.findAllByLogins(new ArrayList(map.keySet()));
                for (EmployeeMatView bean : employeeMatView) {
                    List<ConveyanceMaster> conveyanceMasters = map.get(bean.getLogin());
                    for (ConveyanceMaster conveyanceMaster : conveyanceMasters) {
                        ConveyanceMasterBean qryBean = new ConveyanceMasterBean();
                        BeanUtils.copyProperties(conveyanceMaster, qryBean);
                        qryBean.setName(bean.getName());
                        qryBean.setMiscAmount(conveyanceAttachRepository.findMisAmtByConveyanceMasterId(conveyanceMaster.getId()));
                        conveyanceMasterBean.add(qryBean);
                    }
                }
            }
        } else if (search.getStatus() != null && search.getStatus().equalsIgnoreCase("A")) {
            search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("conveyanceDate").ascending()));
            page = conveyanceMasterRepository.findAllByHodApprovedByApproved(SecurityUtils.getCurrentUserLogin().orElse(null), search.getConveyanceDate(), search.getConveyanceDateTo(), search.getPage());
            for (ConveyanceMaster conveyanceMaster : page.getContent()) {
                if (map.containsKey(conveyanceMaster.getEmpCode())) {
                    List<ConveyanceMaster> list = map.get(conveyanceMaster.getEmpCode());
                    list.add(conveyanceMaster);
                    map.put(conveyanceMaster.getEmpCode(), list);
                } else {
                    List<ConveyanceMaster> list = new ArrayList<>();
                    list.add(conveyanceMaster);
                    map.put(conveyanceMaster.getEmpCode(), list);
                }
            }
            if (page.getContent() != null && page.getContent().size() > 0) {
                List<EmployeeMatView> employeeMatView = employeeMatViewRepository.findAllByLogins(new ArrayList(map.keySet()));
                for (EmployeeMatView bean : employeeMatView) {
                    List<ConveyanceMaster> conveyanceMasters = map.get(bean.getLogin());
                    for (ConveyanceMaster conveyanceMaster : conveyanceMasters) {
                        ConveyanceMasterBean qryBean = new ConveyanceMasterBean();
                        BeanUtils.copyProperties(conveyanceMaster, qryBean);
                        qryBean.setName(bean.getName());
                        qryBean.setMiscAmount(conveyanceAttachRepository.findMisAmtByConveyanceMasterId(conveyanceMaster.getId()));
                        conveyanceMasterBean.add(qryBean);
                    }
                }
            }
        } else {
            search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("conveyanceDate").ascending()));
            page = conveyanceMasterRepository.findAllByHodApprovedByRejected(SecurityUtils.getCurrentUserLogin().orElse(null), search.getConveyanceDate(), search.getConveyanceDateTo(), search.getStatus(), search.getPage());
            for (ConveyanceMaster conveyanceMaster : page.getContent()) {
                if (map.containsKey(conveyanceMaster.getEmpCode())) {
                    List<ConveyanceMaster> list = map.get(conveyanceMaster.getEmpCode());
                    list.add(conveyanceMaster);
                    map.put(conveyanceMaster.getEmpCode(), list);
                } else {
                    List<ConveyanceMaster> list = new ArrayList<>();
                    list.add(conveyanceMaster);
                    map.put(conveyanceMaster.getEmpCode(), list);
                }
            }
            if (page.getContent() != null && page.getContent().size() > 0) {
                List<EmployeeMatView> employeeMatView = employeeMatViewRepository.findAllByLogins(new ArrayList(map.keySet()));
                for (EmployeeMatView bean : employeeMatView) {
                    List<ConveyanceMaster> conveyanceMasters = map.get(bean.getLogin());
                    for (ConveyanceMaster conveyanceMaster : conveyanceMasters) {
                        ConveyanceMasterBean qryBean = new ConveyanceMasterBean();
                        BeanUtils.copyProperties(conveyanceMaster, qryBean);
                        qryBean.setName(bean.getName());
                        qryBean.setMiscAmount(conveyanceAttachRepository.findMisAmtByConveyanceMasterId(conveyanceMaster.getId()));
                        conveyanceMasterBean.add(qryBean);
                    }
                }
            }
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(conveyanceMasterBean);
    }





    /**
     * GET  /leave-masters : get all the leaveMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of leaveMasters in body
     */
    @GetMapping("/conveyance-masters-hod-mobile/{monthYear}")
    @Timed
    public ResponseEntity<ConveyanceMobileApprovalBean> getAllConveyanceApprovedMastersMobile(@PathVariable String monthYear) throws ParseException {
        log.debug("REST request to get a page of ConveyanceMasters");
        String[] arr = monthYear.split("-");
        ConveyanceMobileApprovalBean conveyanceMobileApprovalBean = new ConveyanceMobileApprovalBean();

        EmployeeMatView employeeView = employeeMatViewRepository.findById(SecurityUtils.getCurrentUserLogin().orElse(null)).orElse(null);

        YearMonth yearMonth = YearMonth.of( Integer.parseInt(arr[1]), Integer.parseInt(arr[0]) );  // January of 2015.
        Instant firstOfMonth = yearMonth.atDay( 1 ).atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant lastOfMonth = yearMonth.atEndOfMonth().atStartOfDay(ZoneId.systemDefault()).toInstant();

        List<ConveyanceMaster> conveyanceMasters = conveyanceMasterRepository.findAllByEmpCodeAndMonthApproval(SecurityUtils.getCurrentUserLogin().orElse(null), firstOfMonth, lastOfMonth);
        if(conveyanceMasters != null && conveyanceMasters.size()>0) {
            conveyanceMobileApprovalBean.setExist(true);
            List<ConveyanceMobileApproval> conveyanceMobileApprovals = new ArrayList<>();
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Map<String, Set<String>> listMap = new HashMap<>();
            Map<String, List<ConveyanceMobileApprovalDetails>> leaveMobileMap = new HashMap<>();
            Map<String, EmployeeMatView> empMatMap = new HashedMap<>();
            for (ConveyanceMaster conveyanceMaster : conveyanceMasters) {
                String date = format.format(java.sql.Date.from(conveyanceMaster.getConveyanceDate()));
                if (listMap.containsKey(date)) {
                    List<ConveyanceMobileApprovalDetails> masters = leaveMobileMap.get(date);
                    ConveyanceMobileApprovalDetails conveyanceMobileApprovalDetails = new ConveyanceMobileApprovalDetails();
                    BeanUtils.copyProperties(conveyanceMaster, conveyanceMobileApprovalDetails);
                    conveyanceMobileApprovalDetails.setConveyanceDateString(format.format(java.sql.Date.from(conveyanceMaster.getConveyanceDate())));
                    if(empMatMap.containsKey(conveyanceMobileApprovalDetails.getEmpCode())) {
                        conveyanceMobileApprovalDetails.setEmpName(empMatMap.get(conveyanceMobileApprovalDetails.getEmpCode()).getName());
                    } else {
                        EmployeeMatView employeeMatView = employeeMatViewRepository.findByCardNo(conveyanceMobileApprovalDetails.getEmpCode()).orElse(null);
                        empMatMap.put(conveyanceMobileApprovalDetails.getEmpCode(), employeeMatView);
                        conveyanceMobileApprovalDetails.setEmpName(empMatMap.get(conveyanceMobileApprovalDetails.getEmpCode()).getName());
                    }
                    masters.add(conveyanceMobileApprovalDetails);
                    leaveMobileMap.put(date, masters);

                    Set<String> strings = listMap.get(date);
                    strings.add(conveyanceMaster.getFlag());
                    listMap.put(date, strings);
                } else {
                    List<ConveyanceMobileApprovalDetails> masters = new ArrayList<>();
                    ConveyanceMobileApprovalDetails conveyanceMobileApprovalDetails = new ConveyanceMobileApprovalDetails();
                    BeanUtils.copyProperties(conveyanceMaster, conveyanceMobileApprovalDetails);
                    conveyanceMobileApprovalDetails.setConveyanceDateString(format.format(java.sql.Date.from(conveyanceMaster.getConveyanceDate())));
                    if(empMatMap.containsKey(conveyanceMobileApprovalDetails.getEmpCode())) {
                        conveyanceMobileApprovalDetails.setEmpName(empMatMap.get(conveyanceMobileApprovalDetails.getEmpCode()).getName());
                    } else {
                        EmployeeMatView employeeMatView = employeeMatViewRepository.findByCardNo(conveyanceMobileApprovalDetails.getEmpCode()).orElse(null);
                        empMatMap.put(conveyanceMobileApprovalDetails.getEmpCode(), employeeMatView);
                        conveyanceMobileApprovalDetails.setEmpName(empMatMap.get(conveyanceMobileApprovalDetails.getEmpCode()).getName());
                    }
                    masters.add(conveyanceMobileApprovalDetails);
                    leaveMobileMap.put(date, masters);

                    Set<String> strings = new HashSet<>();
                    strings.add(conveyanceMaster.getFlag());
                    listMap.put(date, strings);
                }
            }
            for (String key : listMap.keySet()) {
                ConveyanceMobileApproval conveyanceMobileApproval = new ConveyanceMobileApproval();
                conveyanceMobileApproval.setLeaveDateFrom(key);
                conveyanceMobileApproval.setDate(format.parse(key));
                if (listMap.get(key).contains("E")) {
                    conveyanceMobileApproval.setFlag("Y");
                } else if (listMap.get(key).contains("A")) {
                    conveyanceMobileApproval.setFlag("O");
                } else if (listMap.get(key).contains("R")) {
                    conveyanceMobileApproval.setFlag("R");
                }
                conveyanceMobileApproval.setConveyanceMobileApprovalDetails(leaveMobileMap.get(key));
                conveyanceMobileApprovals.add(conveyanceMobileApproval);

                if(conveyanceMobileApprovals != null && conveyanceMobileApprovals.size()>0){
                    Collections.sort(conveyanceMobileApprovals, Comparator.comparing(ConveyanceMobileApproval::getDate));
                }
            }
            conveyanceMobileApprovalBean.setConveyanceMobileApprovals(conveyanceMobileApprovals);
            conveyanceMobileApprovalBean.setHolidayMasters(holidayMasterRepository.findAllByFactoryMaster(employeeView.getFactory().trim()));
        } else {
            conveyanceMobileApprovalBean.setExist(false);
            conveyanceMobileApprovalBean.setErrorMessage("No Data Found");
        }
        return ResponseEntity.ok().body(conveyanceMobileApprovalBean);
    }

    //======================This is for HR Approval=======================================================================================
    @PutMapping("/conveyance-masters-update-hr")
    public ResponseEntity<Message> updateConveyanceHrMaster(@Valid @RequestBody ConveyanceMaster conveyanceMaster) throws URISyntaxException {
        log.debug("REST request to update ConveyanceMaster : {}", conveyanceMaster);
        if (conveyanceMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        conveyanceMaster.setFlag(conveyanceMaster.getFlag());
        conveyanceMaster.setHrApproved(SecurityUtils.getCurrentUserLogin().orElse(null));
        conveyanceMaster.setHrApprovedDate(Instant.now());
        ConveyanceMaster result = conveyanceMasterRepository.save(conveyanceMaster);
        if(result != null) {
            EmployeeMatView requester = employeeMatViewRepository.findById(result.getEmpCode().toLowerCase()).orElse(null);
            EmployeeMatView approver = employeeMatViewRepository.findById(result.getApprovedBy().toLowerCase()).orElse(null);
            if (conveyanceMaster.getFlag() != null && conveyanceMaster.getFlag().equalsIgnoreCase("C")) {
                if (requester != null && requester != null) {
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
                    Locale locale = Locale.forLanguageTag("en");
                    Context context = new Context(locale);
                    context.setVariable("empCode", requester.getCardNo());
                    context.setVariable("name", requester.getName());
                    context.setVariable("conveyanceDate", format.format(Date.from(result.getConveyanceDate())));
                    context.setVariable("flag", result.getFlag());
                    String content = null;
                    String subject = "Conveyance request approved by HR for Date " + format.format(Date.from(result.getConveyanceDate()));
                    try {
                        content = templateEngine.process("mail/conveyance_mail", context);
                        if (requester.getEmail() != null && requester.getEmail().length() > 0 && VALID_EMAIL_ADDRESS_REGEX.matcher(approver.getEmail()).find() == true) {
                            mailService.sendEmail(requester.getEmail(), subject, content, false, true);
                        }
                    } catch (Exception e) {
                    }
                }
            } else if (conveyanceMaster.getFlag() != null && conveyanceMaster.getFlag().equalsIgnoreCase("R")) {
                if (requester != null && requester != null) {
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
                    Locale locale = Locale.forLanguageTag("en");
                    Context context = new Context(locale);
                    context.setVariable("empCode", requester.getCardNo());
                    context.setVariable("name", requester.getName());
                    context.setVariable("conveyanceDate", format.format(Date.from(result.getConveyanceDate())));
                    context.setVariable("flag", result.getFlag());
                    String content = null;
                    String subject = "Conveyance request rejected by HR for Date " + format.format(Date.from(result.getConveyanceDate()));
                    try {
                        content = templateEngine.process("mail/conveyance_mail", context);
                        if (requester.getEmail() != null && requester.getEmail().length() > 0 && VALID_EMAIL_ADDRESS_REGEX.matcher(approver.getEmail()).find() == true) {
                            mailService.sendEmail(requester.getEmail(), subject, content, false, true);
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
        return ResponseEntity.ok()
            .body(new Message("success", "Save Successfully!"));
    }

    /**
     * DELETE  /comp-off-masters/:id : delete the "id" compOffMaster.
     *
     * @param id the id of the compOffMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @GetMapping("/conveyance-masters-mobile-approval/{id}/{flag}")
    @Timed
    public ResponseEntity<Message> approvalConveyance(@PathVariable Long id, @PathVariable String flag) {
        log.debug("REST request to Approval Leave Master : {}", id);
        ConveyanceMaster conveyanceMaster = conveyanceMasterRepository.findById(id).orElse(null);
        if (conveyanceMaster != null) {
            conveyanceMaster.setFlag(flag);
            conveyanceMaster.setApprovedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
            conveyanceMaster.setApprovedDate(Instant.now());
            ConveyanceMaster result = conveyanceMasterRepository.save(conveyanceMaster);
            if (result != null) {
                EmployeeMatView requester = employeeMatViewRepository.findById(result.getEmpCode().toLowerCase()).orElse(null);
                EmployeeMatView approver = employeeMatViewRepository.findById(result.getApprovedBy().toLowerCase()).orElse(null);
                if (conveyanceMaster.getFlag() != null && conveyanceMaster.getFlag().equalsIgnoreCase("A")) {
                    if (requester != null && requester != null) {
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
                        Locale locale = Locale.forLanguageTag("en");
                        Context context = new Context(locale);
                        context.setVariable("empCode", requester.getCardNo());
                        context.setVariable("name", requester.getName());
                        context.setVariable("conveyanceDate", format.format(Date.from(result.getConveyanceDate())));
                        context.setVariable("flag", result.getFlag());
                        String content = null;
                        String subject = "Conveyance request approved by HOD for Date " + format.format(Date.from(result.getConveyanceDate()));
                        try {
                            content = templateEngine.process("mail/conveyance_mail", context);
                            if (requester.getEmail() != null && requester.getEmail().length() > 0 && VALID_EMAIL_ADDRESS_REGEX.matcher(approver.getEmail()).find() == true) {
                                mailService.sendEmail(requester.getEmail(), subject, content, false, true);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (requester.getLogin() != null && requester.getLogin().startsWith("102")) {
                            try {
                                mailService.sendEmail("hrd169@vamanioverseas.com", subject, content, false, true);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else if (requester.getLogin() != null && requester.getLogin().startsWith("101")) {
                            try {
                                mailService.sendEmail("hrd@vamanioverseas.com", subject, content, false, true);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }  else if (requester.getLogin() != null && requester.getLogin().startsWith("103")) {
                            try {
                                mailService.sendEmail("amittomar@vamanioverseas.com", subject, content, false, true);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else if (requester.getLogin() != null && requester.getLogin().startsWith("190") || requester.getLogin().startsWith("191")) {
                            try {
                                mailService.sendEmail("samayyadav@vamanioverseas.com", subject, content, false, true);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else if (requester.getLogin() != null && requester.getLogin().startsWith("106")) {
                            try {
                                mailService.sendEmail("bhushankumar@vamanioverseas.com", subject, content, false, true);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else if (conveyanceMaster.getFlag() != null && conveyanceMaster.getFlag().equalsIgnoreCase("R")) {
                    if (requester != null && requester != null) {
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
                        Locale locale = Locale.forLanguageTag("en");
                        Context context = new Context(locale);
                        context.setVariable("empCode", requester.getCardNo());
                        context.setVariable("name", requester.getName());
                        context.setVariable("conveyanceDate", format.format(Date.from(result.getConveyanceDate())));
                        context.setVariable("flag", result.getFlag());
                        String content = null;
                        String subject = "Conveyance request rejected by HOD for Date " + format.format(Date.from(result.getConveyanceDate()));
                        try {
                            content = templateEngine.process("mail/conveyance_mail", context);
                            if (requester.getEmail() != null && requester.getEmail().length() > 0 && VALID_EMAIL_ADDRESS_REGEX.matcher(approver.getEmail()).find() == true) {
                                mailService.sendEmail(requester.getEmail(), subject, content, false, true);
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            }
            return ResponseEntity.ok().body(new Message("Save successfully!", "success", true, ""));
        } else {
            return ResponseEntity.badRequest().headers(io.vamani.application.web.rest.util.HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Leave Request already " + (flag.equalsIgnoreCase("A") ? "approved" : flag.equalsIgnoreCase("C") ? "approved by HR" : "rejected") + ".")).body(new Message("Leave Request already " + (flag.equalsIgnoreCase("A") ? "approved" : flag.equalsIgnoreCase("C") ? "approved by HR" : "rejected") + ".", "error", false, "Leave Request already " + (flag.equalsIgnoreCase("A") ? "approved" : flag.equalsIgnoreCase("C") ? "approved by HR" : "rejected") + "."));
        }
    }

    @PostMapping("/conveyance-masters-hr")
    @Timed
    public ResponseEntity<List<ConveyanceMasterBean>> getAllConveyanceHrQry(@Valid @RequestBody ConveyanceSearchMaster search) {
        log.debug("REST request to get a page of ConveyanceMasters");
        EmployeeMatView employeeMat = employeeMatViewRepository.findByLogins(SecurityUtils.getCurrentUserLogin().orElse(null));
        Map<String, List<ConveyanceMaster>> map = new HashedMap<>();
        List<ConveyanceMasterBean> conveyanceMasterBean = new ArrayList<ConveyanceMasterBean>();
        Page<ConveyanceMaster> page = null;
        if (search.getStatus() != null && search.getStatus().equalsIgnoreCase("P")) {
            search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by(Sort.Order.asc("empCode"), Sort.Order.asc("conveyanceDate"))));
            page = conveyanceMasterRepository.findAllByHrApprovedByPending(employeeMat.getFactory(), search.getPage());
            for (ConveyanceMaster conveyanceMaster : page.getContent()) {
                if (map.containsKey(conveyanceMaster.getEmpCode())) {
                    List<ConveyanceMaster> list = map.get(conveyanceMaster.getEmpCode());
                    list.add(conveyanceMaster);
                    map.put(conveyanceMaster.getEmpCode(), list);
                } else {
                    List<ConveyanceMaster> list = new ArrayList<>();
                    list.add(conveyanceMaster);
                    map.put(conveyanceMaster.getEmpCode(), list);
                }
            }
            if (page.getContent() != null && page.getContent().size() > 0) {
                List<EmployeeMatView> employeeMatView = employeeMatViewRepository.findAllByLogins(new ArrayList(map.keySet()));
                for (EmployeeMatView bean : employeeMatView) {
                    List<ConveyanceMaster> conveyanceMasters = map.get(bean.getLogin());
                    for (ConveyanceMaster conveyanceMaster : conveyanceMasters) {
                        ConveyanceMasterBean qryBean = new ConveyanceMasterBean();
                        BeanUtils.copyProperties(conveyanceMaster, qryBean);
                        qryBean.setName(bean.getName());
                        qryBean.setMiscAmount(conveyanceAttachRepository.findMisAmtByConveyanceMasterId(conveyanceMaster.getId()));
                        conveyanceMasterBean.add(qryBean);
                    }
                }
            }
        } else if (search.getStatus() != null && search.getStatus().equalsIgnoreCase("A")) {
            search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by(Sort.Order.asc("empCode"), Sort.Order.asc("conveyanceDate"))));
            page = conveyanceMasterRepository.findAllByHrApproved(search.getConveyanceDate(), search.getConveyanceDateTo(), employeeMat.getFactory(), search.getPage());
            for (ConveyanceMaster conveyanceMaster : page.getContent()) {
                if (map.containsKey(conveyanceMaster.getEmpCode())) {
                    List<ConveyanceMaster> list = map.get(conveyanceMaster.getEmpCode());
                    list.add(conveyanceMaster);
                    map.put(conveyanceMaster.getEmpCode(), list);
                } else {
                    List<ConveyanceMaster> list = new ArrayList<>();
                    list.add(conveyanceMaster);
                    map.put(conveyanceMaster.getEmpCode(), list);
                }
            }
            if (page.getContent() != null && page.getContent().size() > 0) {
                List<EmployeeMatView> employeeMatView = employeeMatViewRepository.findAllByLogins(new ArrayList(map.keySet()));
                for (EmployeeMatView bean : employeeMatView) {
                    List<ConveyanceMaster> conveyanceMasters = map.get(bean.getLogin());
                    for (ConveyanceMaster conveyanceMaster : conveyanceMasters) {
                        ConveyanceMasterBean qryBean = new ConveyanceMasterBean();
                        BeanUtils.copyProperties(conveyanceMaster, qryBean);
                        qryBean.setName(bean.getName());
                        qryBean.setMiscAmount(conveyanceAttachRepository.findMisAmtByConveyanceMasterId(conveyanceMaster.getId()));
                        conveyanceMasterBean.add(qryBean);
                    }
                }
            }
        } else {
            search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by(Sort.Order.asc("empCode"), Sort.Order.asc("conveyanceDate"))));
            page = conveyanceMasterRepository.findAllByHrApprovedByRejected(search.getConveyanceDate(), search.getConveyanceDateTo(), SecurityUtils.getCurrentUserLogin().orElse(null), employeeMat.getFactory(), search.getPage());
            for (ConveyanceMaster conveyanceMaster : page.getContent()) {
                if (map.containsKey(conveyanceMaster.getEmpCode())) {
                    List<ConveyanceMaster> list = map.get(conveyanceMaster.getEmpCode());
                    list.add(conveyanceMaster);
                    map.put(conveyanceMaster.getEmpCode(), list);
                } else {
                    List<ConveyanceMaster> list = new ArrayList<>();
                    list.add(conveyanceMaster);
                    map.put(conveyanceMaster.getEmpCode(), list);
                }
            }
            if (page.getContent() != null && page.getContent().size() > 0) {
                List<EmployeeMatView> employeeMatView = employeeMatViewRepository.findAllByLogins(new ArrayList(map.keySet()));
                for (EmployeeMatView bean : employeeMatView) {
                    List<ConveyanceMaster> conveyanceMasters = map.get(bean.getLogin());
                    for (ConveyanceMaster conveyanceMaster : conveyanceMasters) {
                        ConveyanceMasterBean qryBean = new ConveyanceMasterBean();
                        BeanUtils.copyProperties(conveyanceMaster, qryBean);
                        qryBean.setName(bean.getName());
                        qryBean.setMiscAmount(conveyanceAttachRepository.findMisAmtByConveyanceMasterId(conveyanceMaster.getId()));
                        conveyanceMasterBean.add(qryBean);
                    }
                }
            }
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(conveyanceMasterBean);
    }

    @PostMapping("/conveyance-masters-process")
    @Timed
    public ResponseEntity<List<ConveyanceMasterBean>> getAllConveyanceProcessQry(@Valid @RequestBody ConveyanceSearchMaster search) {
        log.debug("REST request to get a page of ConveyanceMasters");
        EmployeeMatView employeeMat = employeeMatViewRepository.findByLogins(SecurityUtils.getCurrentUserLogin().orElse(null));
        Map<String, List<ConveyanceMaster>> map = new HashedMap<>();
        List<ConveyanceMasterBean> conveyanceMasterBean = new ArrayList<ConveyanceMasterBean>();
        Page<ConveyanceMaster> page = null;
        if (search.getStatus() != null && search.getStatus().equalsIgnoreCase("P")) {
            search.setPage(PageRequest.of(search.getPageNo(), Integer.MAX_VALUE, Sort.by(Sort.Order.asc("empCode"), Sort.Order.asc("conveyanceDate"))));
            page = conveyanceMasterRepository.findAllByProcessApprovedByPending(employeeMat.getFactory(), search.getPage());
            for (ConveyanceMaster conveyanceMaster : page.getContent()) {
                if (map.containsKey(conveyanceMaster.getEmpCode())) {
                    List<ConveyanceMaster> list = map.get(conveyanceMaster.getEmpCode());
                    list.add(conveyanceMaster);
                    map.put(conveyanceMaster.getEmpCode(), list);
                } else {
                    List<ConveyanceMaster> list = new ArrayList<>();
                    list.add(conveyanceMaster);
                    map.put(conveyanceMaster.getEmpCode(), list);
                }
            }
            if (page.getContent() != null && page.getContent().size() > 0) {
                List<EmployeeMatView> employeeMatView = employeeMatViewRepository.findAllByLogins(new ArrayList(map.keySet()));
                for (EmployeeMatView bean : employeeMatView) {
                    List<ConveyanceMaster> conveyanceMasters = map.get(bean.getLogin());
                    for (ConveyanceMaster conveyanceMaster : conveyanceMasters) {
                        ConveyanceMasterBean qryBean = new ConveyanceMasterBean();
                        BeanUtils.copyProperties(conveyanceMaster, qryBean);
                        qryBean.setName(bean.getName());
                        qryBean.setMiscAmount(conveyanceAttachRepository.findMisAmtByConveyanceMasterId(conveyanceMaster.getId()));
                        conveyanceMasterBean.add(qryBean);
                    }
                }
            }
        } else if (search.getStatus() != null && search.getStatus().equalsIgnoreCase("A")) {
            search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by(Sort.Order.asc("empCode"), Sort.Order.asc("conveyanceDate"))));
            page = conveyanceMasterRepository.findAllByHrApproved(search.getConveyanceDate(), search.getConveyanceDateTo(), employeeMat.getFactory(), search.getPage());
            for (ConveyanceMaster conveyanceMaster : page.getContent()) {
                if (map.containsKey(conveyanceMaster.getEmpCode())) {
                    List<ConveyanceMaster> list = map.get(conveyanceMaster.getEmpCode());
                    list.add(conveyanceMaster);
                    map.put(conveyanceMaster.getEmpCode(), list);
                } else {
                    List<ConveyanceMaster> list = new ArrayList<>();
                    list.add(conveyanceMaster);
                    map.put(conveyanceMaster.getEmpCode(), list);
                }
            }
            if (page.getContent() != null && page.getContent().size() > 0) {
                List<EmployeeMatView> employeeMatView = employeeMatViewRepository.findAllByLogins(new ArrayList(map.keySet()));
                for (EmployeeMatView bean : employeeMatView) {
                    List<ConveyanceMaster> conveyanceMasters = map.get(bean.getLogin());
                    for (ConveyanceMaster conveyanceMaster : conveyanceMasters) {
                        ConveyanceMasterBean qryBean = new ConveyanceMasterBean();
                        BeanUtils.copyProperties(conveyanceMaster, qryBean);
                        qryBean.setName(bean.getName());
                        qryBean.setMiscAmount(conveyanceAttachRepository.findMisAmtByConveyanceMasterId(conveyanceMaster.getId()));
                        conveyanceMasterBean.add(qryBean);
                    }
                }
            }
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(conveyanceMasterBean);
    }

    /**
     * {@code GET  /conveyance-masters/:id} : get the "id" conveyanceMaster.
     *
     * @param id the id of the conveyanceMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the conveyanceMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/conveyance-masters-aprv/{id}")
    public ResponseEntity<ConveyanceMasterBean> getConveyanceApprovedMaster(@PathVariable Long id) {
        log.debug("REST request to get ConveyanceMaster : {}", id);
        ConveyanceMasterBean conveyanceMasterBean = new ConveyanceMasterBean();
        ConveyanceMaster conveyanceMaster = conveyanceMasterRepository.findConveyanceMasterId(id).orElse(null);
        conveyanceMaster.setConveyanceAttaches(conveyanceAttachRepository.findAllByConveyanceMaster(conveyanceMaster));
        BeanUtils.copyProperties(conveyanceMaster, conveyanceMasterBean);
        EmployeeMatView employeeMatView = employeeMatViewRepository.findByLogins(conveyanceMaster.getEmpCode());
        conveyanceMasterBean.setName(employeeMatView.getName());
        return ResponseUtil.wrapOrNotFound(Optional.of(conveyanceMasterBean));
    }

    /**
     * {@code GET  /conveyance-masters/:id} : get the "id" conveyanceMaster.
     *
     * @param id the id of the conveyanceMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the conveyanceMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/conveyance-masters/{id}")
    public ResponseEntity<ConveyanceMaster> getConveyanceMaster(@PathVariable Long id) {
        log.debug("REST request to get ConveyanceMaster : {}", id);
        ConveyanceMaster conveyanceMaster = conveyanceMasterRepository.findConveyanceMasterId(id).orElse(null);
        conveyanceMaster.setConveyanceAttaches(conveyanceAttachRepository.findAllByConveyanceMaster(conveyanceMaster));
        return ResponseUtil.wrapOrNotFound(Optional.of(conveyanceMaster));
    }

    /**
     * {@code GET  /conveyance-masters/:id} : get the "id" conveyanceMaster.
     *
     * @param id the id of the conveyanceMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the conveyanceMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/conveyance-masters-mobile/{id}")
    public ResponseEntity<ConveyanceMasterBean> getConveyanceMasterMobile(@PathVariable Long id) {
        log.debug("REST request to get ConveyanceMaster : {}", id);
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        ConveyanceMasterBean conveyanceMasterBean = new ConveyanceMasterBean();
        List<ConveyanceMasterDetailsBean> conveyanceMasterDetailsBeanList =  new ArrayList<>();
        ConveyanceMaster conveyanceMaster = conveyanceMasterRepository.findConveyanceMasterId(id).orElse(null);
        List<ConveyanceMasterDetails>  conveyanceMasterDetailsList=  conveyanceAttachRepository.findAllByConveyanceMasterId(conveyanceMaster.getId());
        BeanUtils.copyProperties(conveyanceMaster, conveyanceMasterBean);
        conveyanceMasterBean.setConveyanceFromDate(format.format(Date.from(conveyanceMaster.getConveyanceDate())));
        for(ConveyanceMasterDetails bean:conveyanceMasterDetailsList) {
            ConveyanceMasterDetailsBean conveyanceMasterDetailsBean = new ConveyanceMasterDetailsBean();
            BeanUtils.copyProperties(bean, conveyanceMasterDetailsBean);
            conveyanceMasterDetailsBeanList.add(conveyanceMasterDetailsBean);
        }
        conveyanceMasterBean.setConveyanceMasterDetailsbean(conveyanceMasterDetailsBeanList);
        return ResponseUtil.wrapOrNotFound(Optional.of(conveyanceMasterBean));
    }

    @GetMapping("/conveyance-masters-download/{id}")
    @Timed
    public ResponseEntity<Object> getConveyanceMasterAttachesDownload(@PathVariable Long id) throws FileNotFoundException, IOException {
        log.debug("REST request to get Feedback : {}", id);
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        ConveyanceMasterDetails conveyanceAttaches = conveyanceAttachRepository.findById(id).orElse(null);
        ;
        File file = new File(UPLOADED_FOLDER + "conveyance/" + conveyanceAttaches.getAttachFile());
        Path path = Paths.get(UPLOADED_FOLDER + "conveyance/" + conveyanceAttaches.getAttachFile());
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

    @GetMapping("/conveyance-masters-controls")
    @Timed
    public ResponseEntity<List<Master>> getConveyanceMasterControls() throws FileNotFoundException, IOException {
        EmployeeMatView employeeView = employeeMatViewRepository.findByCardNo(SecurityUtils.getCurrentUserLogin().orElse(null)).orElse(null);
        List<Long> controlNos = conveyanceMasterRepository.getControlNosByFactory(employeeView.getFactory());
        List<Master> masters = new ArrayList<>();
        for (Long controlNo : controlNos) {
            Master master = new Master();
            master.setId(controlNo.toString());
            master.setDesc(controlNo.toString());
            masters.add(master);
        }
        return ResponseEntity.ok().body(masters);
    }

    @GetMapping("/conveyance-masters-fetch-controls/{id}")
    @Timed
    public ResponseEntity<List<ConveyanceMasterBean>> getConveyanceMasterByControl(@PathVariable Long id) throws FileNotFoundException, IOException {
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, Sort.by(Sort.Order.asc("empCode"), Sort.Order.asc("conveyanceDate")));
        Map<String, List<ConveyanceMaster>> map = new HashedMap<>();
        List<ConveyanceMasterBean> conveyanceMasterBeans = new ArrayList<>();
        Page<ConveyanceMaster> page = conveyanceMasterRepository.findAllByControlNo(id, pageable);
        for (ConveyanceMaster conveyanceMaster : page.getContent()) {
            if (map.containsKey(conveyanceMaster.getEmpCode())) {
                List<ConveyanceMaster> list = map.get(conveyanceMaster.getEmpCode());
                list.add(conveyanceMaster);
                map.put(conveyanceMaster.getEmpCode(), list);
            } else {
                List<ConveyanceMaster> list = new ArrayList<>();
                list.add(conveyanceMaster);
                map.put(conveyanceMaster.getEmpCode(), list);
            }
        }
        if (page.getContent() != null && page.getContent().size() > 0) {
            List<EmployeeMatView> employeeMatView = employeeMatViewRepository.findAllByLogins(new ArrayList(map.keySet()));
            for (EmployeeMatView bean : employeeMatView) {
                List<ConveyanceMaster> conveyanceMasters = map.get(bean.getLogin());
                for (ConveyanceMaster conveyanceMaster : conveyanceMasters) {
                    ConveyanceMasterBean qryBean = new ConveyanceMasterBean();
                    BeanUtils.copyProperties(conveyanceMaster, qryBean);
                    qryBean.setName(bean.getName());
                    qryBean.setMiscAmount(conveyanceAttachRepository.findMisAmtByConveyanceMasterId(conveyanceMaster.getId()));
                    conveyanceMasterBeans.add(qryBean);
                }
            }
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(conveyanceMasterBeans);
    }

    /**
     * {@code DELETE  /conveyance-masters/:id} : delete the "id" conveyanceMaster.
     *
     * @param id the id of the conveyanceMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/conveyance-masters/{id}")
    public ResponseEntity<Void> deleteConveyanceMaster(@PathVariable Long id) {
        log.debug("REST request to delete ConveyanceMaster : {}", id);
        conveyanceAttachRepository.deleteAllDetailById(id);
        conveyanceMasterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    @PostMapping("/conveyance-masters-process-save")
    @Timed
    public ResponseEntity<Master> saveAllConveyanceProcessQry(@Valid @RequestBody List<Long> processIds) {
        List<ConveyanceMaster> conveyanceMasters = conveyanceMasterRepository.findAllByIds(processIds);
        Long controlNo = conveyanceMasterRepository.getNextControlNo();
        String controlNoBy = SecurityUtils.getCurrentUserLogin().orElse(null);
        Instant controlNoDate = Instant.now();
        int ctr = 0;
        for (ConveyanceMaster conveyanceMaster : conveyanceMasters) {
            conveyanceMaster.setControlNo(controlNo);
            conveyanceMaster.setControlNoBy(controlNoBy);
            conveyanceMaster.setControlNoDate(controlNoDate);
            conveyanceMaster.setFlag("P");
            ConveyanceMaster result = conveyanceMasterRepository.save(conveyanceMaster);
            if (result != null) {
                ++ctr;
            }
        }
        if(ctr==processIds.size()){
            Master master = new Master();
            master.setId(controlNo.toString());
            return ResponseEntity.ok().body(master);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/conveyance-masters-report/{controlNo}")
    @Timed
    public @ResponseBody void getConveyanceMasterReport(@Valid @PathVariable Long controlNo, HttpServletResponse response) throws JRException, IOException, ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        List<ConveyanceReportBean> conveyanceReportBeans = new ArrayList<>();
        List<Object[]> listObject = conveyanceMasterRepository.findAllByControlNo(controlNo);
        Set<String> empList = new HashSet<>();
        for (Object object : listObject) {
            ConveyanceReportBean bean = new ConveyanceReportBean();
            Object[] objects = (Object[]) object;
            System.out.println("Emp Code : "+objects[1].toString());
            bean.setControlNo(objects[0].toString());
            bean.setEmpCode(objects[1].toString());
            bean.setConveyanceDate(simpleDateFormat.format(Date.from((Instant) objects[2])));
            bean.setRate(Double.parseDouble(objects[3].toString()));
            bean.setTripStart(Integer.parseInt(objects[4].toString()));
            bean.setTripEnd(Integer.parseInt(objects[5].toString()));
            bean.setFromLocation(objects[6].toString());
            bean.setToLocation(objects[7].toString());
            bean.setReason(objects[8].toString());
            bean.setMiscAmount((Double) objects[9]);
            bean.setTotalDistance((Integer) objects[10]);
            conveyanceReportBeans.add(bean);
            empList.add(objects[1].toString());
        }
        Map<String, String> map = new HashMap<>();
        Map<String, String> mapSub = new HashMap<>();
        if (empList != null && empList.size() > 0) {
            List<EmployeeMatView> employeeMatViews = employeeMatViewRepository.findAllByLogins(new ArrayList<>(empList));

            for (EmployeeMatView employee : employeeMatViews) {
                map.put(employee.getLogin(), employee.getName());
                mapSub.put(employee.getLogin(), employee.getSubSname());
            }
        }
        List<ConveyanceReportBean> conveyanceReports = new ArrayList<>();
        for (ConveyanceReportBean conveyanceReportBean : conveyanceReportBeans) {
            if(map.containsKey(conveyanceReportBean.getEmpCode())) {
                conveyanceReportBean.setName(map.get(conveyanceReportBean.getEmpCode()));
                conveyanceReportBean.setSubSName(mapSub.get(conveyanceReportBean.getEmpCode()));
            } else {
                conveyanceReportBean.setName("");
                conveyanceReportBean.setSubSName("");
            }
            conveyanceReports.add(conveyanceReportBean);
        }
        Collections.sort(conveyanceReports, Comparator.comparing(ConveyanceReportBean:: getSubSName).thenComparing(Comparator.comparing(ConveyanceReportBean:: getEmpCode).thenComparing(Comparator.comparing(ConveyanceReportBean:: getConveyanceDate))));

        String path = applicationProperties.getTemplatePath() + "jasper/";
        JasperDesign jasperDesign = JRXmlLoader.load(path + "/ConveyanceReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String, Object> parameters = new HashMap<String, Object>();
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(conveyanceReports);

        parameters.put("datasource", jrDataSource);
        parameters.put("SUBREPORT_DIR", path);
        parameters.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=ConveyanceReport.xlsx");

        final OutputStream outputStream = response.getOutputStream();
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
        exporter.exportReport();
    }

    private java.util.Date allowDateEmployee( java.util.Date entryDate) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(entryDate);
        calendar.add(java.util.Calendar.MONTH, 1);
        calendar.set(java.util.Calendar.DATE, 5);
        calendar.set(java.util.Calendar.HOUR, 6);
        calendar.set(java.util.Calendar.MINUTE, 0);
        calendar.set(java.util.Calendar.AM_PM, java.util.Calendar.PM);
        return calendar.getTime();
    }

    private java.util.Date allowDateApproval( java.util.Date entryDate) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(entryDate);
        calendar.add(java.util.Calendar.MONTH, 1);
        calendar.set(java.util.Calendar.DATE, 5);
        calendar.set(java.util.Calendar.HOUR, 6);
        calendar.set(java.util.Calendar.MINUTE, 0);
        calendar.set(java.util.Calendar.AM_PM, java.util.Calendar.PM);
        return calendar.getTime();
    }
}
