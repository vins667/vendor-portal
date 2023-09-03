package io.vamani.application.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.config.Constants;
import io.vamani.application.db2.domain.*;
import io.vamani.application.db2.repository.*;
import io.vamani.application.domain.DirectBookingDetails;
import io.vamani.application.domain.DirectBookingEntry;
import io.vamani.application.domain.DirectBookingTdsDetails;
import io.vamani.application.mobile.DateBean;
import io.vamani.application.model.*;
import io.vamani.application.repository.DirectBookingDetailsRepository;
import io.vamani.application.repository.DirectBookingEntryRepository;
import io.vamani.application.repository.DirectBookingTdsDetailsRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.*;

/**
 * REST controller for managing {@link io.vamani.application.domain.DirectBookingEntry}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class DirectBookingEntryResource {

    private final Logger log = LoggerFactory.getLogger(DirectBookingEntryResource.class);

    private static final String ENTITY_NAME = "directBookingEntry";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DirectBookingEntryRepository directBookingEntryRepository;

    @Autowired
    private DirectBookingDetailsRepository directBookingDetailsRepository;

    @Autowired
    private DirectBookingTdsDetailsRepository directBookingTdsDetailsRepository;

    @Autowired
    private FindocumentbeanRepository findocumentbeanRepository;

    @Autowired
    private FindocumentlinebeanRepository findocumentlinebeanRepository;

    @Autowired
    private FincostcenterdetailRepository fincostcenterdetailRepository;

    @Autowired
    private FinancemonthRepository financemonthRepository;

    @Autowired
    private AdstoragebeanRepository adstoragebeanRepository;

    @Autowired
    private ViewDitaxglmappingRepository viewDitaxglmappingRepository;

    @Autowired
    private OrderpartnerieRepository orderpartnerieRepository;

    @Autowired
    private ItemvseventglmapRepository itemvseventglmapRepository;

    @Autowired
    private FindocumentRepository findocumentRepository;

    @Autowired
    private AbsuserdefRepository absuserdefRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private GlmasterRepository glmasterRepository;

    public DirectBookingEntryResource(DirectBookingEntryRepository directBookingEntryRepository) {
        this.directBookingEntryRepository = directBookingEntryRepository;
    }

    @GetMapping("/direct-current-date")
    public ResponseEntity<DateBean> fetchCurrentDate() throws URISyntaxException {
        DateBean dateBean = new DateBean();
        dateBean.setDate(new java.util.Date());
        return ResponseUtil.wrapOrNotFound(Optional.of(dateBean));
    }

    /**
     * {@code POST  /direct-booking-entries} : Create a new directBookingEntry.
     *
     * @param directBookingEntry the directBookingEntry to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new directBookingEntry, or with status {@code 400 (Bad Request)} if the directBookingEntry has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/direct-booking-entries")
    public ResponseEntity<DirectBookingEntry> createDirectBookingEntry(@Valid @RequestBody DirectBookingEntryBean directBookingEntryBean) throws URISyntaxException {
        log.debug("REST request to save DirectBookingEntry : {}", directBookingEntryBean);
        if (directBookingEntryBean.getId() != null) {
            throw new BadRequestAlertException("A new directBookingEntry cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DirectBookingEntry directBookingEntry = new DirectBookingEntry();
        BeanUtils.copyProperties(directBookingEntryBean, directBookingEntry);

        List<DirectBookingEntry> directBookingEntries = directBookingEntryRepository.findAllByBillNo(directBookingEntry.getSuppliercustomercode(), directBookingEntry.getBillno().trim().toUpperCase());
        if (directBookingEntries != null && directBookingEntries.size() > 0) {
            java.sql.Date date = new java.sql.Date(java.util.Date.from(directBookingEntry.getBookingdate()).getTime());
            List<Financemonth> financemonths = financemonthRepository.findAllByBusinessunitcodeAndDate(directBookingEntry.getCompany(), directBookingEntry.getBusinessunitcode(), date);
            Financemonth financemonth = null;
            if (financemonths != null && financemonths.size() > 0) {
                financemonth = financemonths.get(0);
            }

            for (DirectBookingEntry bookingEntry : directBookingEntries) {
                java.sql.Date dateCheck = new java.sql.Date(java.util.Date.from(bookingEntry.getBilldate()).getTime());
                List<Financemonth> financemonthsCheck = financemonthRepository.findAllByBusinessunitcodeAndDate(bookingEntry.getCompany(), bookingEntry.getBusinessunitcode(), dateCheck);
                Financemonth financemonthCheck = null;
                if (financemonthsCheck != null && financemonthsCheck.size() > 0) {
                    financemonthCheck = financemonthsCheck.get(0);
                }

                if (financemonth.getId().getFinancialyearcode().intValue() == financemonthCheck.getId().getFinancialyearcode().intValue()) {
                    if (bookingEntry.getFindocumentcode() != null && bookingEntry.getFindocumentcode().length() > 0) {
                        List<Findocument> findocuments = findocumentRepository.findAllByDocumentCode(bookingEntry.getFindocumentcode());
                        for (Findocument findocument : findocuments) {
                            if (financemonth.getId().getFinancialyearcode().intValue() == findocument.getId().getFinancialyearcode().intValue() && (findocument.getReffindoccode() == null || (findocument.getReffindoccode() != null && findocument.getReffindoccode().length() == 0))) {
                                return ResponseEntity.badRequest()
                                    .headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "Bill No already exist"))
                                    .build();
                            }
                        }
                    } else {
                        return ResponseEntity.badRequest()
                            .headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "Bill No already exist"))
                            .build();
                    }
                }
            }
        }

        directBookingEntry.setCreatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
        directBookingEntry.setCreateddate(Instant.now());
        directBookingEntry.setUpdateddate(Instant.now());
        DirectBookingEntry result = directBookingEntryRepository.save(directBookingEntry);
        if (result != null && directBookingEntryBean.getDirectBookingDetails() != null && directBookingEntryBean.getDirectBookingDetails().size() > 0) {
            for (DirectBookingDetailsBean directBookingDetailsBean : directBookingEntryBean.getDirectBookingDetails()){
                DirectBookingDetails directBookingDetails = new DirectBookingDetails();
                BeanUtils.copyProperties(directBookingDetailsBean, directBookingDetails);
                directBookingDetails.setDirectBookingEntry(directBookingEntry);
                directBookingDetailsRepository.save(directBookingDetails);
            }
        }

        if (result != null && directBookingEntryBean.getOrderpartnertdss() != null && directBookingEntryBean.getOrderpartnertdss().size() > 0) {
            for (OrderpartnertdsBean orderpartnertdsBean : directBookingEntryBean.getOrderpartnertdss()){
                DirectBookingTdsDetails directBookingTdsDetails = new DirectBookingTdsDetails(orderpartnertdsBean);
                directBookingTdsDetails.setDirectBookingEntry(directBookingEntry);
                directBookingTdsDetailsRepository.save(directBookingTdsDetails);
            }
        }
        return ResponseEntity.created(new URI("/api/direct-booking-entries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /direct-booking-entries} : Updates an existing directBookingEntry.
     *
     * @param directBookingEntry the directBookingEntry to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated directBookingEntry,
     * or with status {@code 400 (Bad Request)} if the directBookingEntry is not valid,
     * or with status {@code 500 (Internal Server Error)} if the directBookingEntry couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/direct-booking-entries")
    public ResponseEntity<DirectBookingEntry> updateDirectBookingEntry(@Valid @RequestBody DirectBookingEntryBean directBookingEntryBean) throws URISyntaxException {
        log.debug("REST request to update DirectBookingEntry : {}", directBookingEntryBean);
        if (directBookingEntryBean.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        DirectBookingEntry directBookingEntry = new DirectBookingEntry();
        BeanUtils.copyProperties(directBookingEntryBean, directBookingEntry);
        directBookingEntry.setUpdatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
        directBookingEntry.setUpdateddate(Instant.now());
        DirectBookingEntry result = directBookingEntryRepository.save(directBookingEntry);
        if (result != null && directBookingEntryBean.getDirectBookingDetails() != null && directBookingEntryBean.getDirectBookingDetails().size() > 0) {
            for (DirectBookingDetailsBean directBookingDetailsBean : directBookingEntryBean.getDirectBookingDetails()){
                DirectBookingDetails directBookingDetails = new DirectBookingDetails();
                BeanUtils.copyProperties(directBookingDetailsBean, directBookingDetails);
                directBookingDetails.setDirectBookingEntry(result);
                directBookingDetailsRepository.save(directBookingDetails);
            }
        }

        if (result != null && directBookingEntryBean.getOrderpartnertdss() != null && directBookingEntryBean.getOrderpartnertdss().size() > 0) {
            for (OrderpartnertdsBean orderpartnertdsBean : directBookingEntryBean.getOrderpartnertdss()){
                DirectBookingTdsDetails directBookingTdsDetails = new DirectBookingTdsDetails(orderpartnertdsBean);
                directBookingTdsDetails.setDirectBookingEntry(directBookingEntry);
                directBookingTdsDetailsRepository.save(directBookingTdsDetails);
            }
        }
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, directBookingEntry.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /direct-booking-entries} : get all the directBookingEntries.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of directBookingEntries in body.
     */
    @GetMapping("/direct-booking-entries")
    public ResponseEntity<List<DirectBookingEntry>> getAllDirectBookingEntries(Pageable pageable) {
        log.debug("REST request to get a page of DirectBookingEntries");
        Page<DirectBookingEntry> page = directBookingEntryRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /direct-booking-entries} : get all the directBookingEntries.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of directBookingEntries in body.
     */
    @PostMapping("/direct-booking-entries-filter")
    public ResponseEntity<List<DirectBookingEntry>> getAllDirectBookingEntriesFilter(@RequestBody DirectBookingSearch search) {
        log.debug("REST request to get a page of DirectBookingEntries");
        String billno = "%";
        if (search.getBillNo() != null) {
            billno = "%" + search.getBillNo().toUpperCase() + "%";
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
        String id = "%";
        if(search.getId() != null) {
            id = search.getId().toString();
        }

        String supplier = "%";
        if (search.getCustomersuppliercode() != null && search.getCustomersuppliercode().length()>0) {
            supplier = "%" + search.getCustomersuppliercode().toUpperCase() + "%";
        }

        String company = "%";
        if (search.getCompany() != null && !search.getCompany().equalsIgnoreCase("undefined")) {
            company = search.getCompany().toUpperCase();
        }

        String division = "%";
        if (search.getDivision() != null && !search.getDivision().equalsIgnoreCase("undefined")) {
            division = search.getDivision().toUpperCase();
        }

        String businsessunit = "%";
        if (search.getBusinessunit() != null && !search.getBusinessunit().equalsIgnoreCase("undefined")) {
            businsessunit = search.getBusinessunit().toUpperCase();
        }
        Page<DirectBookingEntry> page = null;
        if (search.getFindocumentcode() != null && search.getFindocumentcode().length()>0) {
            page = directBookingEntryRepository.findAllByFindocumentcode(search.getFlag(), search.getFindocumentcode(), search.getPage());
        } else if (search.getBookingDateFrom() != null && search.getBookingDateTo() != null) {
            if (search.getBillDate() != null) {
                page = directBookingEntryRepository.findAllByBillNoLikeAndBilldate(billno, search.getBookingDateFrom(), search.getBookingDateTo(), search.getFlag(), supplier, supplier, company, division, businsessunit, search.getBillDate(), id, search.getPage());
            } else {
                page = directBookingEntryRepository.findAllByBillNoLike(billno, search.getBookingDateFrom(), search.getBookingDateTo(), search.getFlag(), supplier, supplier, company, division, businsessunit, id, search.getPage());
            }
        } else {
            if (search.getBillDate() != null) {
                page = directBookingEntryRepository.findAllByBillNoLikeAndBilldate(billno, search.getFlag(), supplier, supplier, company, division, businsessunit, search.getBillDate(), id, search.getPage());
            } else {
                page = directBookingEntryRepository.findAllByBillNoLike(billno, search.getFlag(), supplier, supplier, company, division, businsessunit, id, search.getPage());
            }
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }


    /**
     * {@code GET  /direct-booking-entries} : get all the directBookingEntries.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of directBookingEntries in body.
     */
    @PostMapping("/direct-booking-entries-filter-report")
    public @ResponseBody void getAllDirectBookingEntriesFilterReport(@RequestBody DirectBookingSearch search, HttpServletResponse response) throws JRException, IOException {
        log.debug("REST request to get a page of DirectBookingEntries");
        String billno = "%";
        if (search.getBillNo() != null) {
            billno = "%" + search.getBillNo().toUpperCase() + "%";
        }
        search.setPage(
            PageRequest.of(
                0,
                100000,
                search.getSortType() != null && search.getSortType().equalsIgnoreCase("desc")
                    ? Sort.by(search.getSort()).descending()
                    : Sort.by(search.getSort()).ascending()
            )
        );
        String id = "%";
        if(search.getId() != null) {
            id = search.getId().toString();
        }

        String supplier = "%";
        if (search.getCustomersuppliercode() != null && search.getCustomersuppliercode().length()>0) {
            supplier = "%" + search.getCustomersuppliercode().toUpperCase() + "%";
        }

        String company = "%";
        if (search.getCompany() != null && !search.getCompany().equalsIgnoreCase("undefined")) {
            company = search.getCompany().toUpperCase();
        }

        String division = "%";
        if (search.getDivision() != null && !search.getDivision().equalsIgnoreCase("undefined")) {
            division = search.getDivision().toUpperCase();
        }

        String businsessunit = "%";
        if (search.getBusinessunit() != null && !search.getBusinessunit().equalsIgnoreCase("undefined")) {
            businsessunit = search.getBusinessunit().toUpperCase();
        }
        Page<DirectBookingEntry> page = null;
        if (search.getFindocumentcode() != null && search.getFindocumentcode().length()>0) {
            page = directBookingEntryRepository.findAllByFindocumentcode(search.getFlag(), search.getFindocumentcode(), search.getPage());
        } else if (search.getBookingDateFrom() != null && search.getBookingDateTo() != null) {
            if (search.getBillDate() != null) {
                page = directBookingEntryRepository.findAllByBillNoLikeAndBilldate(billno, search.getBookingDateFrom(), search.getBookingDateTo(), search.getFlag(), supplier, supplier, company, division, businsessunit, search.getBillDate(), id, search.getPage());
            } else {
                page = directBookingEntryRepository.findAllByBillNoLike(billno, search.getBookingDateFrom(), search.getBookingDateTo(), search.getFlag(), supplier, supplier, company, division, businsessunit, id, search.getPage());
            }
        } else {
            if (search.getBillDate() != null) {
                page = directBookingEntryRepository.findAllByBillNoLikeAndBilldate(billno, search.getFlag(), supplier, supplier, company, division, businsessunit, search.getBillDate(), id, search.getPage());
            } else {
                page = directBookingEntryRepository.findAllByBillNoLike(billno, search.getFlag(), supplier, supplier, company, division, businsessunit, id, search.getPage());
            }
        }
        List<DirectBookingEntryBean> directBookingEntryBeans = new ArrayList<>();
        for (DirectBookingEntry directBookingEntry : page.getContent()) {
            DirectBookingEntryBean directBookingEntryBean = new DirectBookingEntryBean();
            BeanUtils.copyProperties(directBookingEntry, directBookingEntryBean);
            directBookingEntryBeans.add(directBookingEntryBean);
        }
        String path = applicationProperties.getTemplatePath() + "jasper/";
        JasperDesign jasperDesign = JRXmlLoader.load(path + "/DirectBillBooking.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String, Object> parameters = new HashMap<>();
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(directBookingEntryBeans);

        parameters.put("datasource", jrDataSource);
        parameters.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
        parameters.put("SUBREPORT_DIR", path);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=TdsComputationReportSummary.xlsx");

        final OutputStream outputStream = response.getOutputStream();
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
        exporter.exportReport();
    }

    /**
     * {@code GET  /direct-booking-entries} : get all the directBookingEntries.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of directBookingEntries in body.
     */
    @PostMapping("/direct-booking-entries-approval-filter")
    public ResponseEntity<List<DirectBookingEntry>> getAllDirectBookingEntriesApprovalFilter(@RequestBody DirectBookingSearch search) {
        log.debug("REST request to get a page of DirectBookingEntries");
        String billno = "%";
        if (search.getBillNo() != null) {
            billno = "%" + search.getBillNo().toUpperCase() + "%";
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
        String id = "%";
        if(search.getId() != null) {
            id = search.getId().toString();
        }

        String supplier = "%";
        if (search.getCustomersuppliercode() != null && search.getCustomersuppliercode().length()>0) {
            supplier = "%" + search.getCustomersuppliercode().toUpperCase() + "%";
        }

        String company = "%";
        if (search.getCompany() != null && !search.getCompany().equalsIgnoreCase("undefined")) {
            company = search.getCompany().toUpperCase();
        }

        String division = "%";
        if (search.getDivision() != null && !search.getDivision().equalsIgnoreCase("undefined")) {
            division = search.getDivision().toUpperCase();
        }

        String businsessunit = "%";
        if (search.getBusinessunit() != null && !search.getBusinessunit().equalsIgnoreCase("undefined")) {
            businsessunit = search.getBusinessunit().toUpperCase();
        }
        Page<DirectBookingEntry> page = null;
        if (search.getFindocumentcode() != null && search.getFindocumentcode().length()>0) {
            page = directBookingEntryRepository.findAllByFindocumentcode(search.getFlag(), search.getFindocumentcode(), search.getPage());
        } else if (search.getBookingDateFrom() != null && search.getBookingDateTo() != null) {
            if (search.getBillDate() != null) {
                page = directBookingEntryRepository.findAllByBillNoLikeAndFlagAndBilldate(billno, search.getFlag(), search.getBookingDateFrom(), search.getBookingDateTo(), supplier, supplier, company, division, businsessunit, search.getBillDate(), id, search.getPage());
            } else {
                page = directBookingEntryRepository.findAllByBillNoLikeAndFlag(billno, search.getFlag(), search.getBookingDateFrom(), search.getBookingDateTo(), supplier, supplier, company, division, businsessunit, id, search.getPage());
            }
        } else {
            if (search.getBillDate() != null) {
                page = directBookingEntryRepository.findAllByBillNoLikeAndFlagAndBilldate(billno, search.getFlag(), supplier, supplier, company, division, businsessunit, search.getBillDate(), id, search.getPage());
            } else {
                page = directBookingEntryRepository.findAllByBillNoLikeAndFlagDt(billno, search.getFlag(), supplier, supplier, company, division, businsessunit, id, search.getPage());
            }
        }

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /direct-booking-entries} : get all the directBookingEntries.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of directBookingEntries in body.
     */
    @PostMapping("/direct-booking-entries-approval-filter-report")
    public @ResponseBody void getAllDirectBookingEntriesApprovalFilterReport(@RequestBody DirectBookingSearch search, HttpServletResponse response) throws JRException, IOException {
        log.debug("REST request to get a page of DirectBookingEntries");
        String billno = "%";
        if (search.getBillNo() != null) {
            billno = "%" + search.getBillNo().toUpperCase() + "%";
        }
        search.setPage(
            PageRequest.of(
                0,
                100000,
                search.getSortType() != null && search.getSortType().equalsIgnoreCase("desc")
                    ? Sort.by(search.getSort()).descending()
                    : Sort.by(search.getSort()).ascending()
            )
        );
        String id = "%";
        if(search.getId() != null) {
            id = search.getId().toString();
        }

        String supplier = "%";
        if (search.getCustomersuppliercode() != null && search.getCustomersuppliercode().length()>0) {
            supplier = "%" + search.getCustomersuppliercode().toUpperCase() + "%";
        }

        String company = "%";
        if (search.getCompany() != null && !search.getCompany().equalsIgnoreCase("undefined")) {
            company = search.getCompany().toUpperCase();
        }

        String division = "%";
        if (search.getDivision() != null && !search.getDivision().equalsIgnoreCase("undefined")) {
            division = search.getDivision().toUpperCase();
        }
        String businsessunit = "%";
        if (search.getBusinessunit() != null && !search.getBusinessunit().equalsIgnoreCase("undefined")) {
            businsessunit = search.getBusinessunit().toUpperCase();
        }
        Page<DirectBookingEntry> page = null;
        if (search.getFindocumentcode() != null && search.getFindocumentcode().length()>0) {
            page = directBookingEntryRepository.findAllByFindocumentcode(search.getFlag(), search.getFindocumentcode(), search.getPage());
        } else if (search.getBookingDateFrom() != null && search.getBookingDateTo() != null) {
            if (search.getBillDate() != null) {
                page = directBookingEntryRepository.findAllByBillNoLikeAndFlagAndBilldate(billno, search.getFlag(), search.getBookingDateFrom(), search.getBookingDateTo(), supplier, supplier, company, division, businsessunit, search.getBillDate(), id, search.getPage());
            } else {
                page = directBookingEntryRepository.findAllByBillNoLikeAndFlag(billno, search.getFlag(), search.getBookingDateFrom(), search.getBookingDateTo(), supplier, supplier, company, division, businsessunit, id, search.getPage());
            }
        } else {
            if (search.getBillDate() != null) {
                page = directBookingEntryRepository.findAllByBillNoLikeAndFlagAndBilldate(billno, search.getFlag(), supplier, supplier, company, division, businsessunit, search.getBillDate(), id, search.getPage());
            } else {
                page = directBookingEntryRepository.findAllByBillNoLikeAndFlagDt(billno, search.getFlag(), supplier, supplier, company, division, businsessunit, id, search.getPage());
            }
        }
        List<DirectBookingEntryBean> directBookingEntryBeans = new ArrayList<>();
        for (DirectBookingEntry directBookingEntry : page.getContent()) {
            DirectBookingEntryBean directBookingEntryBean = new DirectBookingEntryBean();
            BeanUtils.copyProperties(directBookingEntry, directBookingEntryBean);
            directBookingEntryBeans.add(directBookingEntryBean);
        }
        String path = applicationProperties.getTemplatePath() + "jasper/";
        JasperDesign jasperDesign = JRXmlLoader.load(path + "/DirectBillBooking.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String, Object> parameters = new HashMap<>();
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(directBookingEntryBeans);

        parameters.put("datasource", jrDataSource);
        parameters.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
        parameters.put("SUBREPORT_DIR", path);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=TdsComputationReportSummary.xlsx");

        final OutputStream outputStream = response.getOutputStream();
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
        exporter.exportReport();
    }

    /**
     * {@code GET  /direct-booking-entries/:id} : get the "id" directBookingEntry.
     *
     * @param id the id of the directBookingEntry to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the directBookingEntry, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/direct-booking-entries/{id}")
    public ResponseEntity<DirectBookingEntryBean> getDirectBookingEntry(@PathVariable Long id) {
        log.debug("REST request to get DirectBookingEntry : {}", id);
        DirectBookingEntryBean directBookingEntryBean = new DirectBookingEntryBean();
        DirectBookingEntry directBookingEntry = directBookingEntryRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(directBookingEntry, directBookingEntryBean);

        List<DirectBookingDetailsBean> directBookingDetailsBeans = new ArrayList<>();
        List<DirectBookingDetails> directBookingDetails = directBookingDetailsRepository.findAllByDirectBookingEntry(id);
        Map<String, String> glDetailMap = new HashMap<>();
        for (DirectBookingDetails directBookingDetail : directBookingDetails) {
            DirectBookingDetailsBean directBookingDetailsBean = new DirectBookingDetailsBean();
            BeanUtils.copyProperties(directBookingDetail, directBookingDetailsBean);
            if(glDetailMap.containsKey(directBookingDetail.getGlcode().trim())) {
                String desc = glDetailMap.get(directBookingDetail.getGlcode().trim());
                directBookingDetailsBean.setGldescription(desc);
            } else {
                Glmaster glmaster = glmasterRepository.findById(new GlmasterId(Constants.COMPANY_CODE, directBookingDetail.getGlcode().trim())).orElse(null);
                directBookingDetailsBean.setGldescription(glmaster.getLongdescription().trim());
                glDetailMap.put(directBookingDetail.getGlcode().trim(), glmaster.getLongdescription().trim());
            }
            directBookingDetailsBeans.add(directBookingDetailsBean);
        }
        directBookingEntryBean.setDirectBookingDetails(directBookingDetailsBeans);

        List<OrderpartnertdsBean> orderpartnertdsBeans = new ArrayList<>();
        List<DirectBookingTdsDetails> directBookingTdsDetails = directBookingTdsDetailsRepository.findAllByDirectBookingEntry(id);
        for (DirectBookingTdsDetails directBookingTdsDetail : directBookingTdsDetails) {
            OrderpartnertdsBean orderpartnertdsBean = new OrderpartnertdsBean(directBookingTdsDetail);
            orderpartnertdsBeans.add(orderpartnertdsBean);
        }
        directBookingEntryBean.setOrderpartnertdss(orderpartnertdsBeans);
        return ResponseUtil.wrapOrNotFound(Optional.of(directBookingEntryBean));
    }

    /**
     * {@code GET  /direct-booking-entries/:id} : get the "id" directBookingEntry.
     *
     * @param id the id of the directBookingEntry to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the directBookingEntry, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/direct-booking-entries-copy/{id}")
    public ResponseEntity<DirectBookingEntryBean> getDirectBookingEntryCopy(@PathVariable Long id) {
        log.debug("REST request to get DirectBookingEntry : {}", id);
        DirectBookingEntryBean directBookingEntryBean = new DirectBookingEntryBean();
        DirectBookingEntry directBookingEntry = directBookingEntryRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(directBookingEntry, directBookingEntryBean);
        directBookingEntryBean.setId(null);
        directBookingEntryBean.setFlag("E");
        directBookingEntryBean.setCreatedby(null);
        directBookingEntryBean.setCreateddate(null);
        directBookingEntryBean.setUpdatedby(null);
        directBookingEntryBean.setUpdateddate(null);
        directBookingEntryBean.setBillno(null);
        directBookingEntryBean.setBilldate(null);
        directBookingEntryBean.setBookingdate(null);
        directBookingEntryBean.setGatenumber(null);
        directBookingEntryBean.setCopyFlag("Y");


        List<DirectBookingDetailsBean> directBookingDetailsBeans = new ArrayList<>();
        List<DirectBookingDetails> directBookingDetails = directBookingDetailsRepository.findAllByDirectBookingEntry(id);
        for (DirectBookingDetails directBookingDetail : directBookingDetails) {
            DirectBookingDetailsBean directBookingDetailsBean = new DirectBookingDetailsBean();
            BeanUtils.copyProperties(directBookingDetail, directBookingDetailsBean);
            directBookingDetailsBean.setId(null);
            directBookingDetailsBean.setCopyFlag("Y");
            directBookingDetailsBeans.add(directBookingDetailsBean);
        }
        directBookingEntryBean.setDirectBookingDetails(directBookingDetailsBeans);

        List<OrderpartnertdsBean> orderpartnertdsBeans = new ArrayList<>();
        List<DirectBookingTdsDetails> directBookingTdsDetails = directBookingTdsDetailsRepository.findAllByDirectBookingEntry(id);
        for (DirectBookingTdsDetails directBookingTdsDetail : directBookingTdsDetails) {
            OrderpartnertdsBean orderpartnertdsBean = new OrderpartnertdsBean(directBookingTdsDetail);
            orderpartnertdsBean.setParentId(null);
            orderpartnertdsBeans.add(orderpartnertdsBean);
        }
        directBookingEntryBean.setOrderpartnertdss(orderpartnertdsBeans);
        return ResponseUtil.wrapOrNotFound(Optional.of(directBookingEntryBean));
    }

    /**
     * {@code GET  /direct-booking-entries/:id} : get the "id" directBookingEntry.
     *
     * @param id the id of the directBookingEntry to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the directBookingEntry, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/direct-booking-entries-forward/{id}")
    public ResponseEntity<DirectBookingEntryBean> getDirectBookingEntryForward(@PathVariable Long id) {
        log.debug("REST request to get DirectBookingEntry : {}", id);
        DirectBookingEntryBean directBookingEntryBean = new DirectBookingEntryBean();
        DirectBookingEntry directBookingEntry = directBookingEntryRepository.findById(id).orElse(null);
        directBookingEntry.setFlag("F");
        directBookingEntry = directBookingEntryRepository.save(directBookingEntry);
        BeanUtils.copyProperties(directBookingEntry, directBookingEntryBean);

        List<DirectBookingDetailsBean> directBookingDetailsBeans = new ArrayList<>();
        List<DirectBookingDetails> directBookingDetails = directBookingDetailsRepository.findAllByDirectBookingEntry(id);
        for (DirectBookingDetails directBookingDetail : directBookingDetails) {
            DirectBookingDetailsBean directBookingDetailsBean = new DirectBookingDetailsBean();
            BeanUtils.copyProperties(directBookingDetail, directBookingDetailsBean);
            directBookingDetailsBeans.add(directBookingDetailsBean);
        }
        directBookingEntryBean.setDirectBookingDetails(directBookingDetailsBeans);

        List<OrderpartnertdsBean> orderpartnertdsBeans = new ArrayList<>();
        List<DirectBookingTdsDetails> directBookingTdsDetails = directBookingTdsDetailsRepository.findAllByDirectBookingEntry(id);
        for (DirectBookingTdsDetails directBookingTdsDetail : directBookingTdsDetails) {
            OrderpartnertdsBean orderpartnertdsBean = new OrderpartnertdsBean(directBookingTdsDetail);
            orderpartnertdsBeans.add(orderpartnertdsBean);
        }
        directBookingEntryBean.setOrderpartnertdss(orderpartnertdsBeans);
        return ResponseUtil.wrapOrNotFound(Optional.of(directBookingEntryBean));
    }

    /**
     * {@code GET  /direct-booking-entries/:id} : get the "id" directBookingEntry.
     *
     * @param id the id of the directBookingEntry to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the directBookingEntry, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/direct-booking-entries-return/{id}")
    public ResponseEntity<DirectBookingEntryBean> getDirectBookingEntryReturn(@PathVariable Long id) {
        log.debug("REST request to get DirectBookingEntry : {}", id);
        DirectBookingEntryBean directBookingEntryBean = new DirectBookingEntryBean();
        DirectBookingEntry directBookingEntry = directBookingEntryRepository.findById(id).orElse(null);
        directBookingEntry.setFlag("E");
        directBookingEntry = directBookingEntryRepository.save(directBookingEntry);
        BeanUtils.copyProperties(directBookingEntry, directBookingEntryBean);

        List<DirectBookingDetailsBean> directBookingDetailsBeans = new ArrayList<>();
        List<DirectBookingDetails> directBookingDetails = directBookingDetailsRepository.findAllByDirectBookingEntry(id);
        for (DirectBookingDetails directBookingDetail : directBookingDetails) {
            DirectBookingDetailsBean directBookingDetailsBean = new DirectBookingDetailsBean();
            BeanUtils.copyProperties(directBookingDetail, directBookingDetailsBean);
            directBookingDetailsBeans.add(directBookingDetailsBean);
        }
        directBookingEntryBean.setDirectBookingDetails(directBookingDetailsBeans);

        List<OrderpartnertdsBean> orderpartnertdsBeans = new ArrayList<>();
        List<DirectBookingTdsDetails> directBookingTdsDetails = directBookingTdsDetailsRepository.findAllByDirectBookingEntry(id);
        for (DirectBookingTdsDetails directBookingTdsDetail : directBookingTdsDetails) {
            OrderpartnertdsBean orderpartnertdsBean = new OrderpartnertdsBean(directBookingTdsDetail);
            orderpartnertdsBeans.add(orderpartnertdsBean);
        }
        directBookingEntryBean.setOrderpartnertdss(orderpartnertdsBeans);
        return ResponseUtil.wrapOrNotFound(Optional.of(directBookingEntryBean));
    }

    /**
     * {@code GET  /direct-booking-entries/:id} : get the "id" directBookingEntry.
     *
     * @param id the id of the directBookingEntry to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the directBookingEntry, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/direct-booking-entries-post/{id}")
    public ResponseEntity<DirectBookingEntryBean> getDirectBookingEntryPost(@PathVariable Long id) {
        log.debug("REST request to get DirectBookingEntry : {}", id);
        DirectBookingEntryBean directBookingEntryBean = new DirectBookingEntryBean();
        DirectBookingEntry directBookingEntry = directBookingEntryRepository.findById(id).orElse(null);
        List<DirectBookingTdsDetails> directBookingTdsDetails = directBookingTdsDetailsRepository.findAllByDirectBookingEntry(id);
        List<DirectBookingDetails> directBookingDetails = directBookingDetailsRepository.findAllByDirectBookingEntry(id);
        String USER_DETAILS = "system";
        List<Absuserdef> absuserdefs = absuserdefRepository.findAllBySendersmtpid(SecurityUtils.getCurrentUserLogin().orElse(null));
        if (absuserdefs != null && absuserdefs.size() > 0) {
            USER_DETAILS = absuserdefs.get(0).getUserid();
        }
        String profitcentercode = fincostcenterdetailRepository.fetchProfitCenter(directBookingEntry.getBusinessunitcode(), directBookingEntry.getCostcentercode());

        Findocumentbean findocumentbean = this.getFindocumentbean(directBookingEntry.getId(), directBookingEntry, directBookingTdsDetails, profitcentercode, USER_DETAILS);
        Findocumentbean result = findocumentbeanRepository.save(findocumentbean);
        if (result != null) {
            findocumentlinebeanRepository.deleteAllByFindocumentId(directBookingEntry.getId());
            Long childSequence = directBookingEntryRepository.getNextFinlineseq();
            int ctr = 10;
            Orderpartnerie orderpartnerie = orderpartnerieRepository.findById(new OrderpartnerieId(directBookingEntry.getCompany(), directBookingEntry.getSuppliercustomertype(), directBookingEntry.getSuppliercustomercode())).orElse(null);
            Findocumentlinebean findocumentlineSupplier = this.getFindocumentlinebeanSupplier(result.getImportautocounter(), childSequence, ctr, profitcentercode, directBookingEntry, orderpartnerie.getGlcode(), USER_DETAILS);
            Findocumentlinebean resultSupplier = findocumentlinebeanRepository.save(findocumentlineSupplier);


            Double freight = 0.0;
            Double otherCharges = 0.0;
            if (directBookingDetails != null && directBookingDetails.size() > 0) {
                Map<String, Double> glMap = new HashMap<>();
                for (DirectBookingDetails directBookingDetail : directBookingDetails) {
                    if (glMap.containsKey(directBookingDetail.getGlcode())) {
                        Double value = glMap.get(directBookingDetail.getGlcode());
                        Double freightLine = 0.0;
                        if (directBookingDetail.getFreight() != null && directBookingDetail.getFreight().doubleValue() > 0) {
                            freightLine = directBookingDetail.getFreight().doubleValue();
                            freight += freightLine;
                        }

                        Double OtherChargesLine = 0.0;
                        if (directBookingDetail.getOthers() != null && directBookingDetail.getOthers().doubleValue() > 0) {
                            OtherChargesLine = directBookingDetail.getOthers().doubleValue();
                            otherCharges += OtherChargesLine;
                        }
                        value = (value.doubleValue() + directBookingDetail.getTaxablevalue().doubleValue());// - (freightLine + OtherChargesLine);
                        glMap.put(directBookingDetail.getGlcode(), value);
                    } else {
                        Double freightLine = 0.0;
                        if (directBookingDetail.getFreight() != null && directBookingDetail.getFreight().doubleValue() > 0) {
                            freightLine = directBookingDetail.getFreight().doubleValue();
                            freight += freightLine;
                        }

                        Double OtherChargesLine = 0.0;
                        if (directBookingDetail.getOthers() != null && directBookingDetail.getOthers().doubleValue() > 0) {
                            OtherChargesLine = directBookingDetail.getOthers().doubleValue();
                            otherCharges += OtherChargesLine;
                        }
                        Double value = (directBookingDetail.getTaxablevalue().doubleValue());// - (freightLine + OtherChargesLine);
                        glMap.put(directBookingDetail.getGlcode(), value);
                    }
                }

                for (String key : glMap.keySet()){
                    ctr = ctr + 10;
                    childSequence = directBookingEntryRepository.getNextFinlineseq();
                    Findocumentlinebean findocumentlineGOODS = this.getFindocumentlinebeanGOODS(result.getImportautocounter(), childSequence, ctr, profitcentercode, directBookingEntry, key, glMap.get(key), USER_DETAILS);
                    Findocumentlinebean resulGOODS = findocumentlinebeanRepository.save(findocumentlineGOODS);
                }

                if(directBookingEntry.getRoundOffValue() != null && directBookingEntry.getRoundOffValue().doubleValue() != 0) {
                    ctr = ctr + 10;
                    childSequence = directBookingEntryRepository.getNextFinlineseq();
                    if(directBookingEntry.getRoundOffValue().doubleValue()<0) {
                        Findocumentlinebean findocumentlineRound = this.getFindocumentlinebeanRoundingNEG(result.getImportautocounter(), childSequence, ctr, profitcentercode, directBookingEntry, applicationProperties.getDirectBillRoundGL(), USER_DETAILS);
                        Findocumentlinebean resultRound = findocumentlinebeanRepository.save(findocumentlineRound);
                    } else {
                        Findocumentlinebean findocumentlineRound = this.getFindocumentlinebeanRounding(result.getImportautocounter(), childSequence, ctr, profitcentercode, directBookingEntry, applicationProperties.getDirectBillRoundGL(), USER_DETAILS);
                        Findocumentlinebean resultRound = findocumentlinebeanRepository.save(findocumentlineRound);
                    }
                }

                if (freight != null && freight.doubleValue() > 0) {
                    Itemvseventglmap itemvseventglmap = null;
                    List<Itemvseventglmap> itemvseventglmaps = itemvseventglmapRepository.findDetailByEventAndItemtype("41", directBookingEntry.getDivision(), "FRE", "2", "1");
                    if (itemvseventglmaps != null && itemvseventglmaps.size() > 0) {
                        itemvseventglmap = itemvseventglmaps.get(0);
                    }
                    if (itemvseventglmap != null) {
                        ctr = ctr + 10;
                        childSequence = directBookingEntryRepository.getNextFinlineseq();
                        Findocumentlinebean findocumentlineFreight = this.getFindocumentlinebeanGOODS(result.getImportautocounter(), childSequence, ctr, profitcentercode, directBookingEntry, itemvseventglmap.getDebitglcode(), freight, USER_DETAILS);
                        Findocumentlinebean resulFreight = findocumentlinebeanRepository.save(findocumentlineFreight);
                    }
                }


                //vivek
                if (otherCharges != null && otherCharges.doubleValue() > 0) {
                    Itemvseventglmap itemvseventglmap = null;
                    List<Itemvseventglmap> itemvseventglmaps = itemvseventglmapRepository.findDetailByEventAndItemtype("41", directBookingEntry.getDivision(), "OTH", "2", "1");
                    if (itemvseventglmaps != null && itemvseventglmaps.size() > 0) {
                        itemvseventglmap = itemvseventglmaps.get(0);
                    }
                    if (itemvseventglmap != null) {
                        ctr = ctr + 10;
                        childSequence = directBookingEntryRepository.getNextFinlineseq();
                        Findocumentlinebean findocumentlineOthers = this.getFindocumentlinebeanGOODS(result.getImportautocounter(), childSequence, ctr, profitcentercode, directBookingEntry, itemvseventglmap.getDebitglcode(), otherCharges, USER_DETAILS);
                        Findocumentlinebean resulOthers = findocumentlinebeanRepository.save(findocumentlineOthers);
                    }
                }
            }

            if (directBookingEntry.getSuppliercustomerstate() != null && directBookingEntry.getFactorystate() != null && directBookingEntry.getSuppliercustomerstate().trim().equalsIgnoreCase(directBookingEntry.getFactorystate().trim())) {
                if (directBookingEntry.getCtaxvalue() != null && directBookingEntry.getCtaxvalue().doubleValue() > 0) {
                    String cgstglcode = null;
                    String sgstglcode = null;
                    for (DirectBookingDetails directBookingDetail : directBookingDetails) {
                        if (cgstglcode == null) {
                            if (directBookingDetail.getCgstglcode() != null) {
                                cgstglcode = directBookingDetail.getCgstglcode();
                            }
                        }

                        if (sgstglcode == null) {
                            if (directBookingDetail.getSgstglcode() != null) {
                                sgstglcode = directBookingDetail.getSgstglcode();
                            }
                        }
                    }
                    ctr = ctr + 10;
                    childSequence = directBookingEntryRepository.getNextFinlineseq();
                    Findocumentlinebean findocumentlineCGST = this.getFindocumentlinebeanCGST(result.getImportautocounter(), childSequence, ctr, profitcentercode, directBookingEntry, cgstglcode, USER_DETAILS);
                    Findocumentlinebean resultCGST = findocumentlinebeanRepository.save(findocumentlineCGST);

                    ctr = ctr + 10;
                    childSequence = directBookingEntryRepository.getNextFinlineseq();
                    Findocumentlinebean findocumentlineSGST = this.getFindocumentlinebeanSGST(result.getImportautocounter(), childSequence, ctr, profitcentercode, directBookingEntry, sgstglcode, USER_DETAILS);
                    Findocumentlinebean resultSGST = findocumentlinebeanRepository.save(findocumentlineSGST);
                }
            } else {
                if (directBookingEntry.getItaxvalue() != null && directBookingEntry.getItaxvalue().doubleValue() > 0) {
                    String igstglcode = null;
                    for (DirectBookingDetails directBookingDetail : directBookingDetails) {
                        if (igstglcode == null) {
                            if (directBookingDetail.getIgstglcode() != null) {
                                igstglcode = directBookingDetail.getIgstglcode();
                            }
                        }
                    }
                    ctr = ctr + 10;
                    childSequence = directBookingEntryRepository.getNextFinlineseq();
                    Findocumentlinebean findocumentlineIGST = this.getFindocumentlinebeanIGST(result.getImportautocounter(), childSequence, ctr, profitcentercode, directBookingEntry, igstglcode, USER_DETAILS);
                    Findocumentlinebean resultIGST = findocumentlinebeanRepository.save(findocumentlineIGST);
                }
            }

            if(directBookingEntry.getTcsApplicable() != null && directBookingEntry.getTcsApplicable().booleanValue() == true) {
                ctr = ctr + 10;
                childSequence = directBookingEntryRepository.getNextFinlineseq();
                ViewDitaxglmapping viewDitaxglmapping = viewDitaxglmappingRepository.findAllByTaxCode("41", "TCP");
                if(viewDitaxglmapping != null) {
                    Findocumentlinebean findocumentlineTCS = this.getFindocumentlinebeanTCS(result.getImportautocounter(), childSequence, ctr, profitcentercode, directBookingEntry, viewDitaxglmapping.getGlcode(), USER_DETAILS);
                    Findocumentlinebean resultTCS = findocumentlinebeanRepository.save(findocumentlineTCS);
                } else {
                    return ResponseEntity.badRequest().headers(io.vamani.application.web.rest.util.HeaderUtil.createFailureAlert(ENTITY_NAME, "", "GL Code not exist for TCS")).build();
                }
            }

            DirectBookingTdsDetails activeDirectBookingTdsDetails = null;
            for (DirectBookingTdsDetails directBookingTdsDetail : directBookingTdsDetails) {
                if (directBookingTdsDetail.getTdsApplicable() != null && directBookingTdsDetail.getTdsApplicable().booleanValue() == true) {
                    activeDirectBookingTdsDetails = directBookingTdsDetail;
                }
            }
            if(activeDirectBookingTdsDetails != null) {
                ctr = ctr + 10;
                childSequence = directBookingEntryRepository.getNextFinlineseq();
                Findocumentlinebean findocumentlineTAX = this.getFindocumentlinebeanTAX(result.getImportautocounter(), childSequence, ctr, profitcentercode, directBookingEntry, orderpartnerie.getGlcode(), USER_DETAILS);
                Findocumentlinebean resultTAX = findocumentlinebeanRepository.save(findocumentlineTAX);

                ctr = ctr + 10;
                childSequence = directBookingEntryRepository.getNextFinlineseq();
                ViewDitaxglmapping viewDitaxglmapping = viewDitaxglmappingRepository.findAllByTaxCode("41", activeDirectBookingTdsDetails.getTdsTaxCode());
                if(viewDitaxglmapping != null) {
                    Findocumentlinebean findocumentlineTAXNEG = this.getFindocumentlinebeanTAXNEG(result.getImportautocounter(), childSequence, ctr, profitcentercode, directBookingEntry, viewDitaxglmapping.getGlcode(), USER_DETAILS);
                    Findocumentlinebean resultTAXNEG = findocumentlinebeanRepository.save(findocumentlineTAXNEG);
                } else {
                    return ResponseEntity.badRequest().headers(io.vamani.application.web.rest.util.HeaderUtil.createFailureAlert(ENTITY_NAME, "", "GL Code not exist for TDS Code")).build();
                }
            }
        }
        directBookingEntry.setFlag("P");
        directBookingEntryRepository.save(directBookingEntry);
        result.setImportstatus(0);
        result = findocumentbeanRepository.save(result);
        return ResponseUtil.wrapOrNotFound(Optional.of(directBookingEntryBean));
    }

    /**
     * {@code DELETE  /direct-booking-entries/:id} : delete the "id" directBookingEntry.
     *
     * @param id the id of the directBookingEntry to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/direct-booking-entries/{id}")
    public ResponseEntity<Void> deleteDirectBookingEntry(@PathVariable Long id) {
        log.debug("REST request to delete DirectBookingEntry : {}", id);
        directBookingTdsDetailsRepository.deleteAllByDirectBookingEntry(id);
        directBookingDetailsRepository.deleteAllByDirectBookingEntry(id);
        directBookingEntryRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code DELETE  /direct-booking-details/:id} : delete the "id" directBookingDetails.
     *
     * @param id the id of the directBookingDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/direct-booking-details/{id}")
    public ResponseEntity<Void> deleteDirectBookingDetails(@PathVariable Long id) {
        log.debug("REST request to delete DirectBookingDetails : {}", id);
        directBookingDetailsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    private Findocumentbean getFindocumentbean(Long fatherId, DirectBookingEntry directBookingEntry, List<DirectBookingTdsDetails> directBookingTdsDetails, String profitcentercode, String USER_DETAILS) {
        DecimalFormat df = new DecimalFormat("#.##");
        Date currentDate = new Date(new java.util.Date().getTime());
        Findocumentbean findocumentbean = new Findocumentbean();
        java.sql.Date date = new java.sql.Date(java.util.Date.from(directBookingEntry.getBookingdate()).getTime());
        List<Financemonth> financemonths = financemonthRepository.findAllByBusinessunitcodeAndDate(directBookingEntry.getCompany(), directBookingEntry.getBusinessunitcode(), date);
        Financemonth financemonth = null;
        if (financemonths != null && financemonths.size() > 0) {
            financemonth = financemonths.get(0);
        }
        findocumentbean.setImportautocounter(fatherId);
        findocumentbean.setCompanycode(directBookingEntry.getCompany());
        findocumentbean.setBusinessunitcode(directBookingEntry.getBusinessunitcode().trim());
        findocumentbean.setDirectentry((short) 1);
        findocumentbean.setFinancialyearcode(financemonth.getId().getFinancialyearcode());
        if (directBookingEntry.getBookingtype() != null && directBookingEntry.getBookingtype().equalsIgnoreCase("S")) {
            findocumentbean.setDocumenttemplatecode("S03");
            findocumentbean.setDocumenttypecode("SD");
        } else {
            findocumentbean.setDocumenttemplatecode("P02");
            findocumentbean.setDocumenttypecode("PD");
        }
        findocumentbean.setFinancemonthcode(financemonth.getId().getCode());
        findocumentbean.setCode(null);
        findocumentbean.setRevaluationbusinessunitcode(null);
        findocumentbean.setRevaluationfinancialyearcode(0);
        findocumentbean.setRevaluationrevaluationdate(null);
        findocumentbean.setRevaluationprocesstype(null);
        findocumentbean.setCurrentstatus("0");
        findocumentbean.setProgressstatus("0");
        findocumentbean.setCustomertype("1");
        findocumentbean.setCustomercode(directBookingEntry.getSuppliercustomertype().equalsIgnoreCase("1") ? directBookingEntry.getSuppliercustomercode() : null);
        findocumentbean.setSuppliertype("2");
        findocumentbean.setSuppliercode(directBookingEntry.getSuppliercustomertype().equalsIgnoreCase("2") ? directBookingEntry.getSuppliercustomercode() : null);
        findocumentbean.setEmployeecode(null);
        findocumentbean.setOthercustomertype("1");
        findocumentbean.setOthercustomercode(null);
        findocumentbean.setOthervendortype("2");
        findocumentbean.setOthervendorcode(null);
        findocumentbean.setGlcode(null);
        findocumentbean.setNotdsapplicable((short) 0);
        findocumentbean.setOptdstdsteugengrouptypecode("TDS");
        if (directBookingTdsDetails != null && directBookingTdsDetails.size() > 0) {
            DirectBookingTdsDetails activeDirectBookingTdsDetails = null;
            for (DirectBookingTdsDetails directBookingTdsDetail : directBookingTdsDetails) {
                if (directBookingTdsDetail.getTdsApplicable() != null && directBookingTdsDetail.getTdsApplicable().booleanValue() == true) {
                    activeDirectBookingTdsDetails = directBookingTdsDetail;
                }
            }
            if (activeDirectBookingTdsDetails != null) {
                findocumentbean.setOptdstdstypecode(activeDirectBookingTdsDetails.getTdsTypeCode());
                findocumentbean.setOptdstdscode(activeDirectBookingTdsDetails.getTdsCode());
                findocumentbean.setOptdstdsitaxcode(activeDirectBookingTdsDetails.getTdsTaxCode());
                findocumentbean.setTdspercentage(new BigDecimal(activeDirectBookingTdsDetails.getTdsPerc()));
                findocumentbean.setTdsapplicableamount(new BigDecimal(directBookingEntry.getValue()));
                findocumentbean.setTdsamount(new BigDecimal(new Double(df.format(directBookingEntry.getTdsValue()))));
                ViewDitaxglmapping viewDitaxglmapping = viewDitaxglmappingRepository.findAllByTaxCode("41", activeDirectBookingTdsDetails.getTdsTaxCode());
                if(viewDitaxglmapping != null) {
                    findocumentbean.setTdsglcode(viewDitaxglmapping.getGlcode());
                }
            } else {
                findocumentbean.setOptdstdstypecode(null);
                findocumentbean.setOptdstdscode(null);
                findocumentbean.setOptdstdsitaxcode(null);
                findocumentbean.setTdspercentage(new BigDecimal(0.0));
                findocumentbean.setTdsapplicableamount(new BigDecimal(0.0));
                findocumentbean.setTdsamount(new BigDecimal(0.0));
                findocumentbean.setTdsglcode(null);
            }
        } else {
            findocumentbean.setOptdstdstypecode(null);
            findocumentbean.setOptdstdscode(null);
            findocumentbean.setOptdstdsitaxcode(null);
            findocumentbean.setTdspercentage(new BigDecimal(0.0));
            findocumentbean.setTdsapplicableamount(new BigDecimal(0.0));
            findocumentbean.setTdsamount(new BigDecimal(0.0));
            findocumentbean.setTdsglcode(null);
        }
        findocumentbean.setFinancedocumentdate(new Date(java.util.Date.from(directBookingEntry.getBookingdate()).getTime()));
        findocumentbean.setPostingdate(new Date(java.util.Date.from(directBookingEntry.getBookingdate()).getTime()));
        findocumentbean.setDuedate(null);
        findocumentbean.setTermsofpaymentcode(null);
        findocumentbean.setStatisticalgroupcode(null);
        findocumentbean.setProjectcode(null);
        findocumentbean.setCreditamount(new BigDecimal(0.0));
        findocumentbean.setDebitamount(new BigDecimal(0.0));
        Double documentAmount = new Double(df.format(directBookingEntry.getNetAmount() + (directBookingEntry.getTcsApplicable() != null && directBookingEntry.getTcsApplicable() == true ? directBookingEntry.getTcsValue() : 0) + (directBookingEntry.getRoundOffValue() != null ? directBookingEntry.getRoundOffValue() : 0)));
        findocumentbean.setDocumentamount(new BigDecimal(documentAmount));
        findocumentbean.setDynamicclearing((short) 0);
        findocumentbean.setDocumentcurrencycode("INR");
        findocumentbean.setExchangerate(new BigDecimal(1.0));
        findocumentbean.setDoccompanycurrencycode("INR");
        findocumentbean.setChequelotcode(null);
        findocumentbean.setChequenumber(null);
        findocumentbean.setChequedate(null);
        findocumentbean.setCustomerreference(null);
        findocumentbean.setCustomerreferencedate(null);
        findocumentbean.setVendorreference("100");
        findocumentbean.setVendorreferencedate(new Date(java.util.Date.from(directBookingEntry.getBookingdate()).getTime()));
        findocumentbean.setReffindocbusinessunitcode(null);
        findocumentbean.setReffindocfinancialyearcode(0);
        findocumentbean.setReffindocdocumenttemplatecode(null);
        findocumentbean.setReffindocstatisticalgroupcode(null);
        findocumentbean.setReffindoccode(null);
        findocumentbean.setReferencetext1(null);
        findocumentbean.setReferencetext2(directBookingEntry.getProjectcode());
        findocumentbean.setReferencetext3(directBookingEntry.getGatenumber());
        findocumentbean.setReferencetext4(null);
        findocumentbean.setReferencetext5("DIRECT#" + directBookingEntry.getId().toString());
        findocumentbean.setFirstugrpugengrouptypecode(null);
        findocumentbean.setFirstusergrpcode(null);
        findocumentbean.setSndugrpugenericgrouptypecode(null);
        findocumentbean.setSecondusergrpcode(null);
        findocumentbean.setThirdugrpugengrouptypecode(null);
        findocumentbean.setThirdusergrpcode(null);
        findocumentbean.setFrugrpugenericgrouptypecode(null);
        findocumentbean.setFourthusergrpcode(null);
        findocumentbean.setFifthugrpugengrouptypecode(null);
        findocumentbean.setFifthusergrpcode(null);
        findocumentbean.setReferenceamt1(new BigDecimal(0.0));
        findocumentbean.setReferenceamt2(new BigDecimal(0.0));
        findocumentbean.setReferenceamt3(new BigDecimal(0.0));
        findocumentbean.setReferenceamt4(new BigDecimal(0.0));
        findocumentbean.setReferenceamt5(new BigDecimal(0.0));
        findocumentbean.setRemark(directBookingEntry.getRemarks() != null ? directBookingEntry.getRemarks() : "");
        findocumentbean.setProfitcentercode(profitcentercode);
        findocumentbean.setCostcentercode(directBookingEntry.getCostcentercode());
        findocumentbean.setPurchaseinvoicedivisioncode(null);
        findocumentbean.setPurinvoiceordprncsmsuptype(null);
        findocumentbean.setPurinvoiceordprncsmsupcode(null);
        findocumentbean.setPurchaseinvoicecode(null);
        findocumentbean.setPurchaseinvoiceinvoicedate(null);
        findocumentbean.setExpenseinvoicedivisioncode(null);
        findocumentbean.setExpenseinvoiceordprncsmsupte(null);
        findocumentbean.setExpenseinvoiceordprncsmsupcod(null);
        findocumentbean.setExpenseinvoicecode(null);
        findocumentbean.setExpenseinvoiceinvoicedate(null);
        findocumentbean.setMrnrejmdmrnheaderdivisioncode(null);
        findocumentbean.setMrnrejmdmrnheadermrnprefixcode(null);
        findocumentbean.setMrnrejmdmrnheadercode(0);
        findocumentbean.setMrnrejmdlineid(0);
        findocumentbean.setMrnrejrejectionlineid(0);
        findocumentbean.setPoadvancepurordercountercode(null);
        findocumentbean.setPoadvancepurchaseordercode(null);
        findocumentbean.setPoadvancelineno(0);
        findocumentbean.setEmpladvancelrdivisioncode(null);
        findocumentbean.setEmpladvancelremployeeidcode(null);
        findocumentbean.setEmpladvancelrequestloantype(0);
        findocumentbean.setEmpladvancelrequestloancode(null);
        findocumentbean.setEmpladvancelrloanvoucherno(0);
        findocumentbean.setEmploanadvancecode(0L);
        findocumentbean.setPlantinvoicedivisioncode(null);
        findocumentbean.setPlantinvoicecode(null);
        findocumentbean.setCommercialinvoicedivisioncode(null);
        findocumentbean.setCommercialinvoicecode(null);
        findocumentbean.setSdcreditprovisionalcountercode(null);
        findocumentbean.setSdcreditprovisionalcode(null);
        findocumentbean.setDirectinvoicedivisioncode(directBookingEntry.getDivision());
        findocumentbean.setDirectinvoicecountercode(null);
        findocumentbean.setDirectinvoicecode(null);
        findocumentbean.setMrndivisioncode(null);
        findocumentbean.setMrnmrnprefixcode(null);
        findocumentbean.setMrncode(0);
        findocumentbean.setStocktrntransactionnumber(null);
        findocumentbean.setStocktrntrndetailnumber(0);
        findocumentbean.setConsumptiondivisioncode(null);
        findocumentbean.setConsumptionitemtypecode(null);
        findocumentbean.setConsumptionbusinessareacode(null);
        findocumentbean.setConsumptionstartdate(null);
        findocumentbean.setConsumptionenddate(null);
        findocumentbean.setConsumptionlglwarehousecode(null);
        findocumentbean.setPayrollpostingsno(0L);
        findocumentbean.setPayrollpostingpayrollcode(null);
        findocumentbean.setPayrollpostingprocessperiod(0);
        findocumentbean.setInternalordercountercode(null);
        findocumentbean.setInternalordercode(null);
        findocumentbean.setRg23Iiaexciseyearregno(null);
        findocumentbean.setRg23Iiaexciseyearcode(null);
        findocumentbean.setRg23Iiacode(null);
        findocumentbean.setRg23Iicexciseyearregno(null);
        findocumentbean.setRg23Iicexciseyearcode(null);
        findocumentbean.setRg23Iiccode(null);
        findocumentbean.setExportshippingbilldivisioncode(null);
        findocumentbean.setExportshippingbillcode(null);
        findocumentbean.setMrninvoiceno(null);
        findocumentbean.setMrninvoicedate(null);
        findocumentbean.setWsoperation(1);
        findocumentbean.setImpoperationuser(null);
        findocumentbean.setImportstatus(3);
        findocumentbean.setImpcreationdatetime(null);
        findocumentbean.setImpcreationuser(USER_DETAILS);
        findocumentbean.setImplastupdatedatetime(Timestamp.from(Instant.now()));
        findocumentbean.setImplastupdateuser(USER_DETAILS);
        findocumentbean.setImportdatetime(Timestamp.from(Instant.now()));
        findocumentbean.setRetrynr(0);
        findocumentbean.setNextretry(0L);
        findocumentbean.setImportid(fatherId);
        findocumentbean.setInvoiceno(directBookingEntry.getBillno());
        findocumentbean.setInvoicedate(new Date(java.util.Date.from(directBookingEntry.getBilldate()).getTime()));

        return findocumentbean;
    }

    private Findocumentlinebean getFindocumentlinebeanSupplier(Long fatherId, Long childId, Integer lineNo, String profitcentercode, DirectBookingEntry directBookingEntry, String supplierglcode, String USER_DETAILS) {
        Findocumentlinebean findocumentlinebean = new Findocumentlinebean();
        findocumentlinebean.setFatherid(fatherId);
        findocumentlinebean.setImportautocounter(childId);
        findocumentlinebean.setLinenumber(lineNo);
        if (directBookingEntry.getBookingtype() != null && directBookingEntry.getBookingtype().equalsIgnoreCase("S")) {
            findocumentlinebean.setLinetemplatecode("401"); //  Pending
        } else {
            findocumentlinebean.setLinetemplatecode("301"); //  Pending
        }
        findocumentlinebean.setCompanycode(directBookingEntry.getCompany());
        findocumentlinebean.setGlcode(supplierglcode);
        findocumentlinebean.setCreditline((short) 0);
        findocumentlinebean.setCurrentstatus("1");
        findocumentlinebean.setDirectentry((short) 1);
        findocumentlinebean.setSlcustomersuppliertype(directBookingEntry.getSuppliercustomertype());
        findocumentlinebean.setSlcustomersuppliercode(directBookingEntry.getSuppliercustomercode());
        findocumentlinebean.setAmountindc(new BigDecimal((directBookingEntry.getNetAmount() + (directBookingEntry.getTcsApplicable() != null && directBookingEntry.getTcsApplicable() == true ? directBookingEntry.getTcsValue() : 0) + (directBookingEntry.getRoundOffValue() != null ? directBookingEntry.getRoundOffValue() : 0))));
        findocumentlinebean.setDocumentcurrencycode("INR");
        findocumentlinebean.setExchangerate(new BigDecimal(1.0));
        findocumentlinebean.setAmountincc(new BigDecimal((directBookingEntry.getNetAmount() + (directBookingEntry.getTcsApplicable() != null && directBookingEntry.getTcsApplicable() == true ? directBookingEntry.getTcsValue() : 0)+ (directBookingEntry.getRoundOffValue() != null ? directBookingEntry.getRoundOffValue() : 0))));
        findocumentlinebean.setCompanycurrencycode("INR");
        findocumentlinebean.setStatisticalgroupcode(null);
        findocumentlinebean.setProjectcode(null);
        findocumentlinebean.setProfitcentercode(profitcentercode);
        findocumentlinebean.setCostcentercode(directBookingEntry.getCostcentercode());
        findocumentlinebean.setComments(null);
        findocumentlinebean.setDestinationcompanycode(null);
        findocumentlinebean.setIcfdlcompanycode(null);
        findocumentlinebean.setIcfdlbusinessunitcode(null);
        findocumentlinebean.setIcfdlfinancialyearcode(0);
        findocumentlinebean.setIcfdldocumenttemplatecode(null);
        findocumentlinebean.setIcfdlstatisticalgroupcode(null);
        findocumentlinebean.setIcfdlcode(null);
        findocumentlinebean.setReferencetext1(null);
        findocumentlinebean.setReferencetext2(null);
        findocumentlinebean.setReferencetext3(null);
        findocumentlinebean.setReferencetext4(null);
        findocumentlinebean.setReferencetext5(null);
        findocumentlinebean.setFirstugrpugengrouptypecode(null);
        findocumentlinebean.setFirstusergrpcode(null);
        findocumentlinebean.setSndugrpugenericgrouptypecode(null);
        findocumentlinebean.setSecondusergrpcode(null);
        findocumentlinebean.setThirdugrpugengrouptypecode(null);
        findocumentlinebean.setThirdusergrpcode(null);
        findocumentlinebean.setFrugrpugenericgrouptypecode(null);
        findocumentlinebean.setFourthusergrpcode(null);
        findocumentlinebean.setFifthugrpugengrouptypecode(null);
        findocumentlinebean.setFifthusergrpcode(null);
        findocumentlinebean.setSixthugrpugengrouptypecode(null);
        findocumentlinebean.setSixthusergrpcode(null);
        findocumentlinebean.setSeugrpugenericgrouptypecode(null);
        findocumentlinebean.setSeventhusergrpcode(null);
        findocumentlinebean.setReferenceamt1(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt2(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt3(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt4(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt5(new BigDecimal(0.0));
        findocumentlinebean.setReconciliationdate(null);
        findocumentlinebean.setReconciledby(null);
        findocumentlinebean.setReconciletranno(null);
        findocumentlinebean.setReconciledon(null);
        findocumentlinebean.setAssetnocountercode(null);
        findocumentlinebean.setAssetnocode(null);
        findocumentlinebean.setWsoperation(1);
        findocumentlinebean.setImpoperationuser(null);
        findocumentlinebean.setImportstatus(0);
        findocumentlinebean.setImpcreationdatetime(null);
        findocumentlinebean.setImpcreationuser(USER_DETAILS);
        findocumentlinebean.setImplastupdatedatetime(Timestamp.from(Instant.now()));
        findocumentlinebean.setImplastupdateuser(USER_DETAILS);
        findocumentlinebean.setImportdatetime(Timestamp.from(Instant.now()));
        findocumentlinebean.setRetrynr(0);
        findocumentlinebean.setNextretry(0L);
        findocumentlinebean.setImportid(0L);

        return findocumentlinebean;
    }

    private Findocumentlinebean getFindocumentlinebeanCGST(Long fatherId, Long childId, Integer lineNo, String profitcentercode, DirectBookingEntry directBookingEntry, String cgst, String USER_DETAILS) {
        Findocumentlinebean findocumentlinebean = new Findocumentlinebean();
        findocumentlinebean.setFatherid(fatherId);
        findocumentlinebean.setImportautocounter(childId);
        findocumentlinebean.setLinenumber(lineNo);
        if (directBookingEntry.getBookingtype() != null && directBookingEntry.getBookingtype().equalsIgnoreCase("S")) {
            findocumentlinebean.setLinetemplatecode("402"); //  Pending
        } else {
            findocumentlinebean.setLinetemplatecode("302"); //  Pending
        }
        findocumentlinebean.setCompanycode(directBookingEntry.getCompany());
        findocumentlinebean.setGlcode(cgst);
        findocumentlinebean.setCreditline((short) 0);
        findocumentlinebean.setCurrentstatus("1");
        findocumentlinebean.setDirectentry((short) 1);
        findocumentlinebean.setSlcustomersuppliertype(null);
        findocumentlinebean.setSlcustomersuppliercode(null);
        findocumentlinebean.setAmountindc(new BigDecimal(directBookingEntry.getCtaxvalue()));
        findocumentlinebean.setDocumentcurrencycode("INR");
        findocumentlinebean.setExchangerate(new BigDecimal(1.0));
        findocumentlinebean.setAmountincc(new BigDecimal(directBookingEntry.getCtaxvalue()));
        findocumentlinebean.setCompanycurrencycode("INR");
        findocumentlinebean.setStatisticalgroupcode(null);
        findocumentlinebean.setProjectcode(null);
        findocumentlinebean.setProfitcentercode(profitcentercode);
        findocumentlinebean.setCostcentercode(directBookingEntry.getCostcentercode());
        findocumentlinebean.setComments(null);
        findocumentlinebean.setDestinationcompanycode(null);
        findocumentlinebean.setIcfdlcompanycode(null);
        findocumentlinebean.setIcfdlbusinessunitcode(null);
        findocumentlinebean.setIcfdlfinancialyearcode(0);
        findocumentlinebean.setIcfdldocumenttemplatecode(null);
        findocumentlinebean.setIcfdlstatisticalgroupcode(null);
        findocumentlinebean.setIcfdlcode(null);
        findocumentlinebean.setReferencetext1(null);
        findocumentlinebean.setReferencetext2(null);
        findocumentlinebean.setReferencetext3(null);
        findocumentlinebean.setReferencetext4(null);
        findocumentlinebean.setReferencetext5(null);
        findocumentlinebean.setFirstugrpugengrouptypecode(null);
        findocumentlinebean.setFirstusergrpcode(null);
        findocumentlinebean.setSndugrpugenericgrouptypecode(null);
        findocumentlinebean.setSecondusergrpcode(null);
        findocumentlinebean.setThirdugrpugengrouptypecode(null);
        findocumentlinebean.setThirdusergrpcode(null);
        findocumentlinebean.setFrugrpugenericgrouptypecode(null);
        findocumentlinebean.setFourthusergrpcode(null);
        findocumentlinebean.setFifthugrpugengrouptypecode(null);
        findocumentlinebean.setFifthusergrpcode(null);
        findocumentlinebean.setSixthugrpugengrouptypecode(null);
        findocumentlinebean.setSixthusergrpcode(null);
        findocumentlinebean.setSeugrpugenericgrouptypecode(null);
        findocumentlinebean.setSeventhusergrpcode(null);
        findocumentlinebean.setReferenceamt1(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt2(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt3(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt4(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt5(new BigDecimal(0.0));
        findocumentlinebean.setReconciliationdate(null);
        findocumentlinebean.setReconciledby(null);
        findocumentlinebean.setReconciletranno(null);
        findocumentlinebean.setReconciledon(null);
        findocumentlinebean.setAssetnocountercode(null);
        findocumentlinebean.setAssetnocode(null);
        findocumentlinebean.setWsoperation(1);
        findocumentlinebean.setImpoperationuser(null);
        findocumentlinebean.setImportstatus(0);
        findocumentlinebean.setImpcreationdatetime(null);
        findocumentlinebean.setImpcreationuser(USER_DETAILS);
        findocumentlinebean.setImplastupdatedatetime(Timestamp.from(Instant.now()));
        findocumentlinebean.setImplastupdateuser(USER_DETAILS);
        findocumentlinebean.setImportdatetime(Timestamp.from(Instant.now()));
        findocumentlinebean.setRetrynr(0);
        findocumentlinebean.setNextretry(0L);
        findocumentlinebean.setImportid(0L);

        return findocumentlinebean;
    }

    private Findocumentlinebean getFindocumentlinebeanSGST(Long fatherId, Long childId, Integer lineNo, String profitcentercode, DirectBookingEntry directBookingEntry, String sgstglcode, String USER_DETAILS) {
        Findocumentlinebean findocumentlinebean = new Findocumentlinebean();
        findocumentlinebean.setFatherid(fatherId);
        findocumentlinebean.setImportautocounter(childId);
        findocumentlinebean.setLinenumber(lineNo);
        if (directBookingEntry.getBookingtype() != null && directBookingEntry.getBookingtype().equalsIgnoreCase("S")) {
            findocumentlinebean.setLinetemplatecode("402"); //  Pending
        } else {
            findocumentlinebean.setLinetemplatecode("302"); //  Pending
        }
        findocumentlinebean.setCompanycode(directBookingEntry.getCompany());
        findocumentlinebean.setGlcode(sgstglcode);
        findocumentlinebean.setCreditline((short) 0);
        findocumentlinebean.setCurrentstatus("1");
        findocumentlinebean.setDirectentry((short) 1);
        findocumentlinebean.setSlcustomersuppliertype(null);
        findocumentlinebean.setSlcustomersuppliercode(null);
        findocumentlinebean.setAmountindc(new BigDecimal(directBookingEntry.getStaxvalue()));
        findocumentlinebean.setDocumentcurrencycode("INR");
        findocumentlinebean.setExchangerate(new BigDecimal(1.0));
        findocumentlinebean.setAmountincc(new BigDecimal(directBookingEntry.getStaxvalue()));
        findocumentlinebean.setCompanycurrencycode("INR");
        findocumentlinebean.setStatisticalgroupcode(null);
        findocumentlinebean.setProjectcode(null);
        findocumentlinebean.setProfitcentercode(profitcentercode);//pending
        findocumentlinebean.setCostcentercode(directBookingEntry.getCostcentercode());
        findocumentlinebean.setComments(null);
        findocumentlinebean.setDestinationcompanycode(null);
        findocumentlinebean.setIcfdlcompanycode(null);
        findocumentlinebean.setIcfdlbusinessunitcode(null);
        findocumentlinebean.setIcfdlfinancialyearcode(0);
        findocumentlinebean.setIcfdldocumenttemplatecode(null);
        findocumentlinebean.setIcfdlstatisticalgroupcode(null);
        findocumentlinebean.setIcfdlcode(null);
        findocumentlinebean.setReferencetext1(null);
        findocumentlinebean.setReferencetext2(null);
        findocumentlinebean.setReferencetext3(null);
        findocumentlinebean.setReferencetext4(null);
        findocumentlinebean.setReferencetext5(null);
        findocumentlinebean.setFirstugrpugengrouptypecode(null);
        findocumentlinebean.setFirstusergrpcode(null);
        findocumentlinebean.setSndugrpugenericgrouptypecode(null);
        findocumentlinebean.setSecondusergrpcode(null);
        findocumentlinebean.setThirdugrpugengrouptypecode(null);
        findocumentlinebean.setThirdusergrpcode(null);
        findocumentlinebean.setFrugrpugenericgrouptypecode(null);
        findocumentlinebean.setFourthusergrpcode(null);
        findocumentlinebean.setFifthugrpugengrouptypecode(null);
        findocumentlinebean.setFifthusergrpcode(null);
        findocumentlinebean.setSixthugrpugengrouptypecode(null);
        findocumentlinebean.setSixthusergrpcode(null);
        findocumentlinebean.setSeugrpugenericgrouptypecode(null);
        findocumentlinebean.setSeventhusergrpcode(null);
        findocumentlinebean.setReferenceamt1(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt2(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt3(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt4(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt5(new BigDecimal(0.0));
        findocumentlinebean.setReconciliationdate(null);
        findocumentlinebean.setReconciledby(null);
        findocumentlinebean.setReconciletranno(null);
        findocumentlinebean.setReconciledon(null);
        findocumentlinebean.setAssetnocountercode(null);
        findocumentlinebean.setAssetnocode(null);
        findocumentlinebean.setWsoperation(1);
        findocumentlinebean.setImpoperationuser(null);
        findocumentlinebean.setImportstatus(0);
        findocumentlinebean.setImpcreationdatetime(null);
        findocumentlinebean.setImpcreationuser(USER_DETAILS);
        findocumentlinebean.setImplastupdatedatetime(Timestamp.from(Instant.now()));
        findocumentlinebean.setImplastupdateuser(USER_DETAILS);
        findocumentlinebean.setImportdatetime(Timestamp.from(Instant.now()));
        findocumentlinebean.setRetrynr(0);
        findocumentlinebean.setNextretry(0L);
        findocumentlinebean.setImportid(0L);

        return findocumentlinebean;
    }

    private Findocumentlinebean getFindocumentlinebeanIGST(Long fatherId, Long childId, Integer lineNo, String profitcentercode, DirectBookingEntry directBookingEntry, String igstglcode, String USER_DETAILS) {
        Findocumentlinebean findocumentlinebean = new Findocumentlinebean();
        findocumentlinebean.setFatherid(fatherId);
        findocumentlinebean.setImportautocounter(childId);
        findocumentlinebean.setLinenumber(lineNo);
        if (directBookingEntry.getBookingtype() != null && directBookingEntry.getBookingtype().equalsIgnoreCase("S")) {
            findocumentlinebean.setLinetemplatecode("402"); //  Pending
        } else {
            findocumentlinebean.setLinetemplatecode("302"); //  Pending
        }
        findocumentlinebean.setCompanycode(directBookingEntry.getCompany());
        findocumentlinebean.setGlcode(igstglcode);
        findocumentlinebean.setCreditline((short) 0);
        findocumentlinebean.setCurrentstatus("1");
        findocumentlinebean.setDirectentry((short) 1);
        findocumentlinebean.setSlcustomersuppliertype(null);
        findocumentlinebean.setSlcustomersuppliercode(null);
        findocumentlinebean.setAmountindc(new BigDecimal(directBookingEntry.getItaxvalue()));
        findocumentlinebean.setDocumentcurrencycode("INR");
        findocumentlinebean.setExchangerate(new BigDecimal(1.0));
        findocumentlinebean.setAmountincc(new BigDecimal(directBookingEntry.getItaxvalue()));
        findocumentlinebean.setCompanycurrencycode("INR");
        findocumentlinebean.setStatisticalgroupcode(null);
        findocumentlinebean.setProjectcode(null);
        findocumentlinebean.setProfitcentercode(profitcentercode);
        findocumentlinebean.setCostcentercode(directBookingEntry.getCostcentercode());
        findocumentlinebean.setComments(null);
        findocumentlinebean.setDestinationcompanycode(null);
        findocumentlinebean.setIcfdlcompanycode(null);
        findocumentlinebean.setIcfdlbusinessunitcode(null);
        findocumentlinebean.setIcfdlfinancialyearcode(0);
        findocumentlinebean.setIcfdldocumenttemplatecode(null);
        findocumentlinebean.setIcfdlstatisticalgroupcode(null);
        findocumentlinebean.setIcfdlcode(null);
        findocumentlinebean.setReferencetext1(null);
        findocumentlinebean.setReferencetext2(null);
        findocumentlinebean.setReferencetext3(null);
        findocumentlinebean.setReferencetext4(null);
        findocumentlinebean.setReferencetext5(null);
        findocumentlinebean.setFirstugrpugengrouptypecode(null);
        findocumentlinebean.setFirstusergrpcode(null);
        findocumentlinebean.setSndugrpugenericgrouptypecode(null);
        findocumentlinebean.setSecondusergrpcode(null);
        findocumentlinebean.setThirdugrpugengrouptypecode(null);
        findocumentlinebean.setThirdusergrpcode(null);
        findocumentlinebean.setFrugrpugenericgrouptypecode(null);
        findocumentlinebean.setFourthusergrpcode(null);
        findocumentlinebean.setFifthugrpugengrouptypecode(null);
        findocumentlinebean.setFifthusergrpcode(null);
        findocumentlinebean.setSixthugrpugengrouptypecode(null);
        findocumentlinebean.setSixthusergrpcode(null);
        findocumentlinebean.setSeugrpugenericgrouptypecode(null);
        findocumentlinebean.setSeventhusergrpcode(null);
        findocumentlinebean.setReferenceamt1(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt2(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt3(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt4(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt5(new BigDecimal(0.0));
        findocumentlinebean.setReconciliationdate(null);
        findocumentlinebean.setReconciledby(null);
        findocumentlinebean.setReconciletranno(null);
        findocumentlinebean.setReconciledon(null);
        findocumentlinebean.setAssetnocountercode(null);
        findocumentlinebean.setAssetnocode(null);
        findocumentlinebean.setWsoperation(1);
        findocumentlinebean.setImpoperationuser(null);
        findocumentlinebean.setImportstatus(0);
        findocumentlinebean.setImpcreationdatetime(null);
        findocumentlinebean.setImpcreationuser(USER_DETAILS);
        findocumentlinebean.setImplastupdatedatetime(Timestamp.from(Instant.now()));
        findocumentlinebean.setImplastupdateuser(USER_DETAILS);
        findocumentlinebean.setImportdatetime(Timestamp.from(Instant.now()));
        findocumentlinebean.setRetrynr(0);
        findocumentlinebean.setNextretry(0L);
        findocumentlinebean.setImportid(0L);

        return findocumentlinebean;
    }

    private Findocumentlinebean getFindocumentlinebeanGOODS(Long fatherId, Long childId, Integer lineNo, String profitcentercode, DirectBookingEntry directBookingEntry, String glcode, Double amount, String USER_DETAILS) {
        Findocumentlinebean findocumentlinebean = new Findocumentlinebean();
        findocumentlinebean.setFatherid(fatherId);
        findocumentlinebean.setImportautocounter(childId);
        findocumentlinebean.setLinenumber(lineNo);
        if (directBookingEntry.getBookingtype() != null && directBookingEntry.getBookingtype().equalsIgnoreCase("S")) {
            findocumentlinebean.setLinetemplatecode("402"); //  Pending
        } else {
            findocumentlinebean.setLinetemplatecode("302"); //  Pending
        }
        findocumentlinebean.setCompanycode(directBookingEntry.getCompany());
        findocumentlinebean.setGlcode(glcode);//pending
        findocumentlinebean.setCreditline((short) 0);
        findocumentlinebean.setCurrentstatus("1");
        findocumentlinebean.setDirectentry((short) 1);
        findocumentlinebean.setSlcustomersuppliertype(null);
        findocumentlinebean.setSlcustomersuppliercode(null);
        findocumentlinebean.setAmountindc(new BigDecimal(amount));
        findocumentlinebean.setDocumentcurrencycode("INR");
        findocumentlinebean.setExchangerate(new BigDecimal(1.0));
        findocumentlinebean.setAmountincc(new BigDecimal(amount));
        findocumentlinebean.setCompanycurrencycode("INR");
        findocumentlinebean.setStatisticalgroupcode(null);
        findocumentlinebean.setProjectcode(null);
        findocumentlinebean.setProfitcentercode(profitcentercode);
        findocumentlinebean.setCostcentercode(directBookingEntry.getCostcentercode());
        findocumentlinebean.setComments(null);
        findocumentlinebean.setDestinationcompanycode(null);
        findocumentlinebean.setIcfdlcompanycode(null);
        findocumentlinebean.setIcfdlbusinessunitcode(null);
        findocumentlinebean.setIcfdlfinancialyearcode(0);
        findocumentlinebean.setIcfdldocumenttemplatecode(null);
        findocumentlinebean.setIcfdlstatisticalgroupcode(null);
        findocumentlinebean.setIcfdlcode(null);
        findocumentlinebean.setReferencetext1(null);
        findocumentlinebean.setReferencetext2(null);
        findocumentlinebean.setReferencetext3(null);
        findocumentlinebean.setReferencetext4(null);
        findocumentlinebean.setReferencetext5(null);
        findocumentlinebean.setFirstugrpugengrouptypecode(null);
        findocumentlinebean.setFirstusergrpcode(null);
        findocumentlinebean.setSndugrpugenericgrouptypecode(null);
        findocumentlinebean.setSecondusergrpcode(null);
        findocumentlinebean.setThirdugrpugengrouptypecode(null);
        findocumentlinebean.setThirdusergrpcode(null);
        findocumentlinebean.setFrugrpugenericgrouptypecode(null);
        findocumentlinebean.setFourthusergrpcode(null);
        findocumentlinebean.setFifthugrpugengrouptypecode(null);
        findocumentlinebean.setFifthusergrpcode(null);
        findocumentlinebean.setSixthugrpugengrouptypecode(null);
        findocumentlinebean.setSixthusergrpcode(null);
        findocumentlinebean.setSeugrpugenericgrouptypecode(null);
        findocumentlinebean.setSeventhusergrpcode(null);
        findocumentlinebean.setReferenceamt1(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt2(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt3(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt4(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt5(new BigDecimal(0.0));
        findocumentlinebean.setReconciliationdate(null);
        findocumentlinebean.setReconciledby(null);
        findocumentlinebean.setReconciletranno(null);
        findocumentlinebean.setReconciledon(null);
        findocumentlinebean.setAssetnocountercode(null);
        findocumentlinebean.setAssetnocode(null);
        findocumentlinebean.setWsoperation(1);
        findocumentlinebean.setImpoperationuser(null);
        findocumentlinebean.setImportstatus(0);
        findocumentlinebean.setImpcreationdatetime(null);
        findocumentlinebean.setImpcreationuser(USER_DETAILS);
        findocumentlinebean.setImplastupdatedatetime(Timestamp.from(Instant.now()));
        findocumentlinebean.setImplastupdateuser(USER_DETAILS);
        findocumentlinebean.setImportdatetime(Timestamp.from(Instant.now()));
        findocumentlinebean.setRetrynr(0);
        findocumentlinebean.setNextretry(0L);
        findocumentlinebean.setImportid(0L);

        return findocumentlinebean;
    }

    private Findocumentlinebean getFindocumentlinebeanTCS(Long fatherId, Long childId, Integer lineNo, String profitcentercode, DirectBookingEntry directBookingEntry, String tcpglcode, String USER_DETAILS) {
        Findocumentlinebean findocumentlinebean = new Findocumentlinebean();
        findocumentlinebean.setFatherid(fatherId);
        findocumentlinebean.setImportautocounter(childId);
        findocumentlinebean.setLinenumber(lineNo);
        if (directBookingEntry.getBookingtype() != null && directBookingEntry.getBookingtype().equalsIgnoreCase("S")) {
            findocumentlinebean.setLinetemplatecode("402"); //  Pending
        } else {
            findocumentlinebean.setLinetemplatecode("302"); //  Pending
        }
        findocumentlinebean.setCompanycode(directBookingEntry.getCompany());
        findocumentlinebean.setGlcode(tcpglcode);
        findocumentlinebean.setCreditline((short) 0);
        findocumentlinebean.setCurrentstatus("1");
        findocumentlinebean.setDirectentry((short) 1);
        findocumentlinebean.setSlcustomersuppliertype(null);
        findocumentlinebean.setSlcustomersuppliercode(null);
        findocumentlinebean.setAmountindc(new BigDecimal(directBookingEntry.getTcsValue()));
        findocumentlinebean.setDocumentcurrencycode("INR");
        findocumentlinebean.setExchangerate(new BigDecimal(1.0));
        findocumentlinebean.setAmountincc(new BigDecimal(directBookingEntry.getTcsValue()));
        findocumentlinebean.setCompanycurrencycode("INR");
        findocumentlinebean.setStatisticalgroupcode(null);
        findocumentlinebean.setProjectcode(null);
        findocumentlinebean.setProfitcentercode(profitcentercode);
        findocumentlinebean.setCostcentercode(directBookingEntry.getCostcentercode());
        findocumentlinebean.setComments(null);
        findocumentlinebean.setDestinationcompanycode(null);
        findocumentlinebean.setIcfdlcompanycode(null);
        findocumentlinebean.setIcfdlbusinessunitcode(null);
        findocumentlinebean.setIcfdlfinancialyearcode(0);
        findocumentlinebean.setIcfdldocumenttemplatecode(null);
        findocumentlinebean.setIcfdlstatisticalgroupcode(null);
        findocumentlinebean.setIcfdlcode(null);
        findocumentlinebean.setReferencetext1(null);
        findocumentlinebean.setReferencetext2(null);
        findocumentlinebean.setReferencetext3(null);
        findocumentlinebean.setReferencetext4(null);
        findocumentlinebean.setReferencetext5(null);
        findocumentlinebean.setFirstugrpugengrouptypecode(null);
        findocumentlinebean.setFirstusergrpcode(null);
        findocumentlinebean.setSndugrpugenericgrouptypecode(null);
        findocumentlinebean.setSecondusergrpcode(null);
        findocumentlinebean.setThirdugrpugengrouptypecode(null);
        findocumentlinebean.setThirdusergrpcode(null);
        findocumentlinebean.setFrugrpugenericgrouptypecode(null);
        findocumentlinebean.setFourthusergrpcode(null);
        findocumentlinebean.setFifthugrpugengrouptypecode(null);
        findocumentlinebean.setFifthusergrpcode(null);
        findocumentlinebean.setSixthugrpugengrouptypecode(null);
        findocumentlinebean.setSixthusergrpcode(null);
        findocumentlinebean.setSeugrpugenericgrouptypecode(null);
        findocumentlinebean.setSeventhusergrpcode(null);
        findocumentlinebean.setReferenceamt1(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt2(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt3(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt4(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt5(new BigDecimal(0.0));
        findocumentlinebean.setReconciliationdate(null);
        findocumentlinebean.setReconciledby(null);
        findocumentlinebean.setReconciletranno(null);
        findocumentlinebean.setReconciledon(null);
        findocumentlinebean.setAssetnocountercode(null);
        findocumentlinebean.setAssetnocode(null);
        findocumentlinebean.setWsoperation(1);
        findocumentlinebean.setImpoperationuser(null);
        findocumentlinebean.setImportstatus(0);
        findocumentlinebean.setImpcreationdatetime(null);
        findocumentlinebean.setImpcreationuser(USER_DETAILS);
        findocumentlinebean.setImplastupdatedatetime(Timestamp.from(Instant.now()));
        findocumentlinebean.setImplastupdateuser(USER_DETAILS);
        findocumentlinebean.setImportdatetime(Timestamp.from(Instant.now()));
        findocumentlinebean.setRetrynr(0);
        findocumentlinebean.setNextretry(0L);
        findocumentlinebean.setImportid(0L);

        return findocumentlinebean;
    }

    private Findocumentlinebean getFindocumentlinebeanRounding(Long fatherId, Long childId, Integer lineNo, String profitcentercode, DirectBookingEntry directBookingEntry, String tcpglcode, String USER_DETAILS) {
        Findocumentlinebean findocumentlinebean = new Findocumentlinebean();
        findocumentlinebean.setFatherid(fatherId);
        findocumentlinebean.setImportautocounter(childId);
        findocumentlinebean.setLinenumber(lineNo);
        if (directBookingEntry.getBookingtype() != null && directBookingEntry.getBookingtype().equalsIgnoreCase("S")) {
            findocumentlinebean.setLinetemplatecode("402"); //  Pending
        } else {
            findocumentlinebean.setLinetemplatecode("302"); //  Pending
        }
        findocumentlinebean.setCompanycode(directBookingEntry.getCompany());
        findocumentlinebean.setGlcode(tcpglcode);
        findocumentlinebean.setCreditline((short) 0);
        findocumentlinebean.setCurrentstatus("1");
        findocumentlinebean.setDirectentry((short) 1);
        findocumentlinebean.setSlcustomersuppliertype(null);
        findocumentlinebean.setSlcustomersuppliercode(null);
        findocumentlinebean.setAmountindc(new BigDecimal(directBookingEntry.getRoundOffValue()));
        findocumentlinebean.setDocumentcurrencycode("INR");
        findocumentlinebean.setExchangerate(new BigDecimal(1.0));
        findocumentlinebean.setAmountincc(new BigDecimal(directBookingEntry.getRoundOffValue()));
        findocumentlinebean.setCompanycurrencycode("INR");
        findocumentlinebean.setStatisticalgroupcode(null);
        findocumentlinebean.setProjectcode(null);
        findocumentlinebean.setProfitcentercode(profitcentercode);
        findocumentlinebean.setCostcentercode(directBookingEntry.getCostcentercode());
        findocumentlinebean.setComments(null);
        findocumentlinebean.setDestinationcompanycode(null);
        findocumentlinebean.setIcfdlcompanycode(null);
        findocumentlinebean.setIcfdlbusinessunitcode(null);
        findocumentlinebean.setIcfdlfinancialyearcode(0);
        findocumentlinebean.setIcfdldocumenttemplatecode(null);
        findocumentlinebean.setIcfdlstatisticalgroupcode(null);
        findocumentlinebean.setIcfdlcode(null);
        findocumentlinebean.setReferencetext1(null);
        findocumentlinebean.setReferencetext2(null);
        findocumentlinebean.setReferencetext3(null);
        findocumentlinebean.setReferencetext4(null);
        findocumentlinebean.setReferencetext5(null);
        findocumentlinebean.setFirstugrpugengrouptypecode(null);
        findocumentlinebean.setFirstusergrpcode(null);
        findocumentlinebean.setSndugrpugenericgrouptypecode(null);
        findocumentlinebean.setSecondusergrpcode(null);
        findocumentlinebean.setThirdugrpugengrouptypecode(null);
        findocumentlinebean.setThirdusergrpcode(null);
        findocumentlinebean.setFrugrpugenericgrouptypecode(null);
        findocumentlinebean.setFourthusergrpcode(null);
        findocumentlinebean.setFifthugrpugengrouptypecode(null);
        findocumentlinebean.setFifthusergrpcode(null);
        findocumentlinebean.setSixthugrpugengrouptypecode(null);
        findocumentlinebean.setSixthusergrpcode(null);
        findocumentlinebean.setSeugrpugenericgrouptypecode(null);
        findocumentlinebean.setSeventhusergrpcode(null);
        findocumentlinebean.setReferenceamt1(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt2(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt3(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt4(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt5(new BigDecimal(0.0));
        findocumentlinebean.setReconciliationdate(null);
        findocumentlinebean.setReconciledby(null);
        findocumentlinebean.setReconciletranno(null);
        findocumentlinebean.setReconciledon(null);
        findocumentlinebean.setAssetnocountercode(null);
        findocumentlinebean.setAssetnocode(null);
        findocumentlinebean.setWsoperation(1);
        findocumentlinebean.setImpoperationuser(null);
        findocumentlinebean.setImportstatus(0);
        findocumentlinebean.setImpcreationdatetime(null);
        findocumentlinebean.setImpcreationuser(USER_DETAILS);
        findocumentlinebean.setImplastupdatedatetime(Timestamp.from(Instant.now()));
        findocumentlinebean.setImplastupdateuser(USER_DETAILS);
        findocumentlinebean.setImportdatetime(Timestamp.from(Instant.now()));
        findocumentlinebean.setRetrynr(0);
        findocumentlinebean.setNextretry(0L);
        findocumentlinebean.setImportid(0L);

        return findocumentlinebean;
    }

    private Findocumentlinebean getFindocumentlinebeanRoundingNEG(Long fatherId, Long childId, Integer lineNo, String profitcentercode, DirectBookingEntry directBookingEntry, String tcpglcode, String USER_DETAILS) {
        Findocumentlinebean findocumentlinebean = new Findocumentlinebean();
        findocumentlinebean.setFatherid(fatherId);
        findocumentlinebean.setImportautocounter(childId);
        findocumentlinebean.setLinenumber(lineNo);
        findocumentlinebean.setLinetemplatecode("998"); //  Pending
        findocumentlinebean.setCompanycode(directBookingEntry.getCompany());
        findocumentlinebean.setGlcode(tcpglcode);
        findocumentlinebean.setCreditline((short) 0);
        findocumentlinebean.setCurrentstatus("1");
        findocumentlinebean.setDirectentry((short) 1);
        findocumentlinebean.setSlcustomersuppliertype(null);
        findocumentlinebean.setSlcustomersuppliercode(null);
        findocumentlinebean.setAmountindc(new BigDecimal(directBookingEntry.getRoundOffValue()*-1));
        findocumentlinebean.setDocumentcurrencycode("INR");
        findocumentlinebean.setExchangerate(new BigDecimal(1.0));
        findocumentlinebean.setAmountincc(new BigDecimal(directBookingEntry.getRoundOffValue()*-1));
        findocumentlinebean.setCompanycurrencycode("INR");
        findocumentlinebean.setStatisticalgroupcode(null);
        findocumentlinebean.setProjectcode(null);
        findocumentlinebean.setProfitcentercode(profitcentercode);
        findocumentlinebean.setCostcentercode(directBookingEntry.getCostcentercode());
        findocumentlinebean.setComments(null);
        findocumentlinebean.setDestinationcompanycode(null);
        findocumentlinebean.setIcfdlcompanycode(null);
        findocumentlinebean.setIcfdlbusinessunitcode(null);
        findocumentlinebean.setIcfdlfinancialyearcode(0);
        findocumentlinebean.setIcfdldocumenttemplatecode(null);
        findocumentlinebean.setIcfdlstatisticalgroupcode(null);
        findocumentlinebean.setIcfdlcode(null);
        findocumentlinebean.setReferencetext1(null);
        findocumentlinebean.setReferencetext2(null);
        findocumentlinebean.setReferencetext3(null);
        findocumentlinebean.setReferencetext4(null);
        findocumentlinebean.setReferencetext5(null);
        findocumentlinebean.setFirstugrpugengrouptypecode(null);
        findocumentlinebean.setFirstusergrpcode(null);
        findocumentlinebean.setSndugrpugenericgrouptypecode(null);
        findocumentlinebean.setSecondusergrpcode(null);
        findocumentlinebean.setThirdugrpugengrouptypecode(null);
        findocumentlinebean.setThirdusergrpcode(null);
        findocumentlinebean.setFrugrpugenericgrouptypecode(null);
        findocumentlinebean.setFourthusergrpcode(null);
        findocumentlinebean.setFifthugrpugengrouptypecode(null);
        findocumentlinebean.setFifthusergrpcode(null);
        findocumentlinebean.setSixthugrpugengrouptypecode(null);
        findocumentlinebean.setSixthusergrpcode(null);
        findocumentlinebean.setSeugrpugenericgrouptypecode(null);
        findocumentlinebean.setSeventhusergrpcode(null);
        findocumentlinebean.setReferenceamt1(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt2(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt3(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt4(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt5(new BigDecimal(0.0));
        findocumentlinebean.setReconciliationdate(null);
        findocumentlinebean.setReconciledby(null);
        findocumentlinebean.setReconciletranno(null);
        findocumentlinebean.setReconciledon(null);
        findocumentlinebean.setAssetnocountercode(null);
        findocumentlinebean.setAssetnocode(null);
        findocumentlinebean.setWsoperation(1);
        findocumentlinebean.setImpoperationuser(null);
        findocumentlinebean.setImportstatus(0);
        findocumentlinebean.setImpcreationdatetime(null);
        findocumentlinebean.setImpcreationuser(USER_DETAILS);
        findocumentlinebean.setImplastupdatedatetime(Timestamp.from(Instant.now()));
        findocumentlinebean.setImplastupdateuser(USER_DETAILS);
        findocumentlinebean.setImportdatetime(Timestamp.from(Instant.now()));
        findocumentlinebean.setRetrynr(0);
        findocumentlinebean.setNextretry(0L);
        findocumentlinebean.setImportid(0L);

        return findocumentlinebean;
    }

    private Findocumentlinebean getFindocumentlinebeanTAX(Long fatherId, Long childId, Integer lineNo, String profitcentercode, DirectBookingEntry directBookingEntry, String supplierglcode, String USER_DETAILS) {
        Findocumentlinebean findocumentlinebean = new Findocumentlinebean();
        findocumentlinebean.setFatherid(fatherId);
        findocumentlinebean.setImportautocounter(childId);
        findocumentlinebean.setLinenumber(lineNo);
        if (directBookingEntry.getBookingtype() != null && directBookingEntry.getBookingtype().equalsIgnoreCase("S")) {
            findocumentlinebean.setLinetemplatecode("806"); //  Pending
        } else {
            findocumentlinebean.setLinetemplatecode("804"); //  Pending
        }
        findocumentlinebean.setCompanycode(directBookingEntry.getCompany());
        findocumentlinebean.setGlcode(supplierglcode);
        findocumentlinebean.setCreditline((short) 0);
        findocumentlinebean.setCurrentstatus("1");
        findocumentlinebean.setDirectentry((short) 1);
        findocumentlinebean.setSlcustomersuppliertype(directBookingEntry.getSuppliercustomertype());
        findocumentlinebean.setSlcustomersuppliercode(directBookingEntry.getSuppliercustomercode());
        findocumentlinebean.setAmountindc(new BigDecimal(directBookingEntry.getTdsValue()));
        findocumentlinebean.setDocumentcurrencycode("INR");
        findocumentlinebean.setExchangerate(new BigDecimal(1.0));
        findocumentlinebean.setAmountincc(new BigDecimal(directBookingEntry.getTdsValue()));
        findocumentlinebean.setCompanycurrencycode("INR");
        findocumentlinebean.setStatisticalgroupcode(null);
        findocumentlinebean.setProjectcode(null);
        findocumentlinebean.setProfitcentercode(profitcentercode);
        findocumentlinebean.setCostcentercode(directBookingEntry.getCostcentercode());
        findocumentlinebean.setComments(null);
        findocumentlinebean.setDestinationcompanycode(null);
        findocumentlinebean.setIcfdlcompanycode(null);
        findocumentlinebean.setIcfdlbusinessunitcode(null);
        findocumentlinebean.setIcfdlfinancialyearcode(0);
        findocumentlinebean.setIcfdldocumenttemplatecode(null);
        findocumentlinebean.setIcfdlstatisticalgroupcode(null);
        findocumentlinebean.setIcfdlcode(null);
        findocumentlinebean.setReferencetext1(null);
        findocumentlinebean.setReferencetext2(null);
        findocumentlinebean.setReferencetext3(null);
        findocumentlinebean.setReferencetext4(null);
        findocumentlinebean.setReferencetext5(null);
        findocumentlinebean.setFirstugrpugengrouptypecode(null);
        findocumentlinebean.setFirstusergrpcode(null);
        findocumentlinebean.setSndugrpugenericgrouptypecode(null);
        findocumentlinebean.setSecondusergrpcode(null);
        findocumentlinebean.setThirdugrpugengrouptypecode(null);
        findocumentlinebean.setThirdusergrpcode(null);
        findocumentlinebean.setFrugrpugenericgrouptypecode(null);
        findocumentlinebean.setFourthusergrpcode(null);
        findocumentlinebean.setFifthugrpugengrouptypecode(null);
        findocumentlinebean.setFifthusergrpcode(null);
        findocumentlinebean.setSixthugrpugengrouptypecode(null);
        findocumentlinebean.setSixthusergrpcode(null);
        findocumentlinebean.setSeugrpugenericgrouptypecode(null);
        findocumentlinebean.setSeventhusergrpcode(null);
        findocumentlinebean.setReferenceamt1(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt2(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt3(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt4(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt5(new BigDecimal(0.0));
        findocumentlinebean.setReconciliationdate(null);
        findocumentlinebean.setReconciledby(null);
        findocumentlinebean.setReconciletranno(null);
        findocumentlinebean.setReconciledon(null);
        findocumentlinebean.setAssetnocountercode(null);
        findocumentlinebean.setAssetnocode(null);
        findocumentlinebean.setWsoperation(1);
        findocumentlinebean.setImpoperationuser(null);
        findocumentlinebean.setImportstatus(0);
        findocumentlinebean.setImpcreationdatetime(null);
        findocumentlinebean.setImpcreationuser(USER_DETAILS);
        findocumentlinebean.setImplastupdatedatetime(Timestamp.from(Instant.now()));
        findocumentlinebean.setImplastupdateuser(USER_DETAILS);
        findocumentlinebean.setImportdatetime(Timestamp.from(Instant.now()));
        findocumentlinebean.setRetrynr(0);
        findocumentlinebean.setNextretry(0L);
        findocumentlinebean.setImportid(0L);

        return findocumentlinebean;
    }

    private Findocumentlinebean getFindocumentlinebeanTAXNEG(Long fatherId, Long childId, Integer lineNo, String profitcentercode, DirectBookingEntry directBookingEntry, String tdsglcode, String USER_DETAILS) {
        Findocumentlinebean findocumentlinebean = new Findocumentlinebean();
        findocumentlinebean.setFatherid(fatherId);
        findocumentlinebean.setImportautocounter(childId);
        findocumentlinebean.setLinenumber(lineNo);
        if (directBookingEntry.getBookingtype() != null && directBookingEntry.getBookingtype().equalsIgnoreCase("S")) {
            findocumentlinebean.setLinetemplatecode("805"); //  Pending
        } else {
            findocumentlinebean.setLinetemplatecode("801"); //  Pending
        }
        findocumentlinebean.setCompanycode(directBookingEntry.getCompany());
        findocumentlinebean.setGlcode(tdsglcode);
        findocumentlinebean.setCreditline((short) 0);
        findocumentlinebean.setCurrentstatus("1");
        findocumentlinebean.setDirectentry((short) 1);
        findocumentlinebean.setSlcustomersuppliertype(null);
        findocumentlinebean.setSlcustomersuppliercode(null);
        findocumentlinebean.setAmountindc(new BigDecimal(directBookingEntry.getTdsValue()));
        findocumentlinebean.setDocumentcurrencycode("INR");
        findocumentlinebean.setExchangerate(new BigDecimal(1.0));
        findocumentlinebean.setAmountincc(new BigDecimal(directBookingEntry.getTdsValue()));
        findocumentlinebean.setCompanycurrencycode("INR");
        findocumentlinebean.setStatisticalgroupcode(null);
        findocumentlinebean.setProjectcode(null);
        findocumentlinebean.setProfitcentercode(profitcentercode);
        findocumentlinebean.setCostcentercode(directBookingEntry.getCostcentercode());
        findocumentlinebean.setComments(null);
        findocumentlinebean.setDestinationcompanycode(null);
        findocumentlinebean.setIcfdlcompanycode(null);
        findocumentlinebean.setIcfdlbusinessunitcode(null);
        findocumentlinebean.setIcfdlfinancialyearcode(0);
        findocumentlinebean.setIcfdldocumenttemplatecode(null);
        findocumentlinebean.setIcfdlstatisticalgroupcode(null);
        findocumentlinebean.setIcfdlcode(null);
        findocumentlinebean.setReferencetext1(null);
        findocumentlinebean.setReferencetext2(null);
        findocumentlinebean.setReferencetext3(null);
        findocumentlinebean.setReferencetext4(null);
        findocumentlinebean.setReferencetext5(null);
        findocumentlinebean.setFirstugrpugengrouptypecode(null);
        findocumentlinebean.setFirstusergrpcode(null);
        findocumentlinebean.setSndugrpugenericgrouptypecode(null);
        findocumentlinebean.setSecondusergrpcode(null);
        findocumentlinebean.setThirdugrpugengrouptypecode(null);
        findocumentlinebean.setThirdusergrpcode(null);
        findocumentlinebean.setFrugrpugenericgrouptypecode(null);
        findocumentlinebean.setFourthusergrpcode(null);
        findocumentlinebean.setFifthugrpugengrouptypecode(null);
        findocumentlinebean.setFifthusergrpcode(null);
        findocumentlinebean.setSixthugrpugengrouptypecode(null);
        findocumentlinebean.setSixthusergrpcode(null);
        findocumentlinebean.setSeugrpugenericgrouptypecode(null);
        findocumentlinebean.setSeventhusergrpcode(null);
        findocumentlinebean.setReferenceamt1(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt2(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt3(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt4(new BigDecimal(0.0));
        findocumentlinebean.setReferenceamt5(new BigDecimal(0.0));
        findocumentlinebean.setReconciliationdate(null);
        findocumentlinebean.setReconciledby(null);
        findocumentlinebean.setReconciletranno(null);
        findocumentlinebean.setReconciledon(null);
        findocumentlinebean.setAssetnocountercode(null);
        findocumentlinebean.setAssetnocode(null);
        findocumentlinebean.setWsoperation(1);
        findocumentlinebean.setImpoperationuser(null);
        findocumentlinebean.setImportstatus(0);
        findocumentlinebean.setImpcreationdatetime(null);
        findocumentlinebean.setImpcreationuser(USER_DETAILS);
        findocumentlinebean.setImplastupdatedatetime(Timestamp.from(Instant.now()));
        findocumentlinebean.setImplastupdateuser(USER_DETAILS);
        findocumentlinebean.setImportdatetime(Timestamp.from(Instant.now()));
        findocumentlinebean.setRetrynr(0);
        findocumentlinebean.setNextretry(0L);
        findocumentlinebean.setImportid(0L);

        return findocumentlinebean;
    }
}
