package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.db2.domain.Finfinancialyear;
import io.vamani.application.db2.model.PurchaseOrderImportBean;
import io.vamani.application.db2.model.PurchaseOrderJobwBean;
import io.vamani.application.db2.repository.FindocumentRepository;
import io.vamani.application.db2.repository.FinfinancialyearRepository;
import io.vamani.application.domain.PaymentRequestForm;
import io.vamani.application.domain.PaymentRequestFormDetails;
import io.vamani.application.domain.PaymentRequestInvoice;
import io.vamani.application.domain.PaymentRequestInvoiceId;
import io.vamani.application.model.MasterSearch;
import io.vamani.application.model.PaymentRequestFormBean;
import io.vamani.application.model.PaymentRequestFormSearch;
import io.vamani.application.model.PaymentRequestInvoiceBean;
import io.vamani.application.mssql.domain.EmployeeView;
import io.vamani.application.mssql.model.SalarySearch;

import io.vamani.application.model.*;
import io.vamani.application.mssql.repository.EmployeeViewRepository;
import io.vamani.application.repository.PaymentRequestFormDetailsRepository;
import io.vamani.application.repository.PaymentRequestFormRepository;
import io.vamani.application.repository.PaymentRequestForwardRepository;
import io.vamani.application.repository.PaymentRequestInvoiceRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import numWordConv.EnglishNumberToWords;

import javax.sql.DataSource;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

/**
 * REST controller for managing {@link PaymentRequestForm}.
 */
@RestController
@RequestMapping("/api")
public class PaymentRequestFormResource {

    private final Logger log = LoggerFactory.getLogger(PaymentRequestFormResource.class);

    private static final String ENTITY_NAME = "paymentRequestForm";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    private ApplicationProperties applicationProperties;

    private final PaymentRequestFormRepository paymentRequestFormRepository;

    @Autowired
    private PaymentRequestFormDetailsRepository paymentRequestFormDetailsRepository;

    @Autowired
    private PaymentRequestForwardRepository paymentRequestForwardRepository;

    @Autowired
    private PaymentRequestInvoiceRepository paymentRequestInvoiceRepository;

    @Autowired
    private FinfinancialyearRepository finfinancialyearRepository;

    @Autowired
    private FindocumentRepository findocumentRepository;

    @Autowired
    private Environment env;
    private final EmployeeViewRepository employeeViewRepository;

    public PaymentRequestFormResource(PaymentRequestFormRepository paymentRequestFormRepository,
                                      EmployeeViewRepository employeeViewRepository) {
        this.paymentRequestFormRepository = paymentRequestFormRepository;
        this.employeeViewRepository = employeeViewRepository;
    }

    /**
     * {@code POST  /payment-request-forms} : Create a new paymentRequestForm.
     *
     * @param paymentRequestForm the paymentRequestForm to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paymentRequestForm, or with status {@code 400 (Bad Request)} if the paymentRequestForm has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/payment-request-forms")
    @Timed
    public ResponseEntity<PaymentRequestForm> createPaymentRequestForm(@Valid @RequestBody PaymentRequestFormBean paymentRequestFormBean) throws URISyntaxException {
        log.debug("REST request to save PaymentRequestForm : {}", paymentRequestFormBean);
        if (paymentRequestFormBean.getId() != null) {
            throw new BadRequestAlertException("A new paymentRequestForm cannot already have an ID", ENTITY_NAME, "idexists");
        }
        if (paymentRequestFormBean.getPaymentType() != null && paymentRequestFormBean.getPaymentType().equalsIgnoreCase("PO")) {
            List<PaymentRequestForm> paymentRequestForms = paymentRequestFormRepository.findAllByPONo(paymentRequestFormBean.getSupplierCode(), paymentRequestFormBean.getPoNo());
            Long totalPaymentRelease = 0L;
            for (PaymentRequestForm requestForm : paymentRequestForms) {
                totalPaymentRelease += requestForm.getPaymentRelease();
            }
            if (totalPaymentRelease + paymentRequestFormBean.getPaymentRelease() > 100) {
                Long balanceRelease = 100L - totalPaymentRelease;
                return ResponseEntity.badRequest()
                    .headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "Release amount must be less than or equals " + balanceRelease)).build();
            }
        } else if (paymentRequestFormBean.getPaymentType() != null && paymentRequestFormBean.getPaymentType().equalsIgnoreCase("PI")) {
            List<PaymentRequestForm> paymentRequestForms = paymentRequestFormRepository.findAllByPINo(paymentRequestFormBean.getSupplierCode(), paymentRequestFormBean.getPiNo().toUpperCase());
            Long totalPaymentRelease = 0L;
            for (PaymentRequestForm requestForm : paymentRequestForms) {
                totalPaymentRelease += requestForm.getPaymentRelease();
            }
            if (totalPaymentRelease + paymentRequestFormBean.getPaymentRelease() > 100) {
                Long balanceRelease = 100L - totalPaymentRelease;
                return ResponseEntity.badRequest()
                    .headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "Release amount must be less than or equals " + balanceRelease)).build();
            }
        }
        PaymentRequestForm paymentRequestForm = new PaymentRequestForm();
        BeanUtils.copyProperties(paymentRequestFormBean, paymentRequestForm);
        paymentRequestForm.setStatus("E");
        paymentRequestForm.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        paymentRequestForm.setCreatedDate(Instant.now());
        PaymentRequestForm result = paymentRequestFormRepository.save(paymentRequestForm);
        if (result != null && paymentRequestFormBean.getInvoices() != null) {
            paymentRequestInvoiceRepository.deleteByPaymentRequestFormId(result.getId());
            for (PaymentRequestInvoiceBean paymentRequestInvoiceBean : paymentRequestFormBean.getInvoices()) {
                if (paymentRequestInvoiceBean.getInvoiceNo() != null && paymentRequestInvoiceBean.getInvoiceNo().length() > 0) {
                    PaymentRequestInvoice paymentRequestInvoice = new PaymentRequestInvoice();
                    paymentRequestInvoice.setInvoiceDate(paymentRequestInvoiceBean.getInvoiceDate());
                    paymentRequestInvoice.setId(new PaymentRequestInvoiceId(paymentRequestInvoiceBean.getInvoiceNo(), result.getId()));
                    paymentRequestInvoiceRepository.save(paymentRequestInvoice);
                }
            }
        }
        return ResponseEntity.created(new URI("/api/payment-request-forms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /payment-request-forms} : Updates an existing paymentRequestForm.
     *
     * @param paymentRequestForm the paymentRequestForm to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paymentRequestForm,
     * or with status {@code 400 (Bad Request)} if the paymentRequestForm is not valid,
     * or with status {@code 500 (Internal Server Error)} if the paymentRequestForm couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/payment-request-forms")
    @Timed
    public ResponseEntity<PaymentRequestForm> updatePaymentRequestForm(@Valid @RequestBody PaymentRequestFormBean paymentRequestFormBean) throws URISyntaxException {
        log.debug("REST request to update PaymentRequestForm : {}", paymentRequestFormBean);
        if (paymentRequestFormBean.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        if (paymentRequestFormBean.getPaymentType() != null && paymentRequestFormBean.getPaymentType().equalsIgnoreCase("PO")) {
            List<PaymentRequestForm> paymentRequestForms = paymentRequestFormRepository.findAllByPONoAndId(paymentRequestFormBean.getSupplierCode(), paymentRequestFormBean.getPoNo(), paymentRequestFormBean.getId());
            Long totalPaymentRelease = 0L;
            for (PaymentRequestForm requestForm : paymentRequestForms) {
                totalPaymentRelease += requestForm.getPaymentRelease();
            }
            if (totalPaymentRelease + paymentRequestFormBean.getPaymentRelease() > 100) {
                Long balanceRelease = 100L - totalPaymentRelease;
                return ResponseEntity.badRequest()
                    .headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "Release amount must be less than or equals " + balanceRelease)).build();
            }
        } else if (paymentRequestFormBean.getPaymentType() != null && paymentRequestFormBean.getPaymentType().equalsIgnoreCase("PI")) {
            List<PaymentRequestForm> paymentRequestForms = paymentRequestFormRepository.findAllByPINoAndId(paymentRequestFormBean.getSupplierCode(), paymentRequestFormBean.getPiNo(), paymentRequestFormBean.getId());
            Long totalPaymentRelease = 0L;
            for (PaymentRequestForm requestForm : paymentRequestForms) {
                totalPaymentRelease += requestForm.getPaymentRelease();
            }
            if (totalPaymentRelease + paymentRequestFormBean.getPaymentRelease() > 100) {
                Long balanceRelease = 100L - totalPaymentRelease;
                return ResponseEntity.badRequest()
                    .headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "Release amount must be less than or equals " + balanceRelease)).build();
            }
        }

        PaymentRequestForm paymentRequestForm = new PaymentRequestForm();
        BeanUtils.copyProperties(paymentRequestFormBean, paymentRequestForm);
        PaymentRequestForm result = paymentRequestFormRepository.save(paymentRequestForm);
        if(result != null && paymentRequestFormBean.getInvoices() != null) {
            paymentRequestInvoiceRepository.deleteByPaymentRequestFormId(result.getId());
            for(PaymentRequestInvoiceBean paymentRequestInvoiceBean : paymentRequestFormBean.getInvoices()) {
                if (paymentRequestInvoiceBean.getInvoiceNo() != null && paymentRequestInvoiceBean.getInvoiceNo().length() > 0) {
                    PaymentRequestInvoice paymentRequestInvoice = new PaymentRequestInvoice();
                    paymentRequestInvoice.setInvoiceDate(paymentRequestInvoiceBean.getInvoiceDate());
                    paymentRequestInvoice.setId(new PaymentRequestInvoiceId(paymentRequestInvoiceBean.getInvoiceNo().toUpperCase(), result.getId()));
                    paymentRequestInvoiceRepository.save(paymentRequestInvoice);
                }
            }
        }
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, paymentRequestForm.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /payment-request-forms} : get all the paymentRequestForms.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paymentRequestForms in body.
     */
    @GetMapping("/payment-request-forms")
    public ResponseEntity<List<PaymentRequestForm>> getAllPaymentRequestForms(Pageable pageable) {
        log.debug("REST request to get a page of PaymentRequestForms");
        Page<PaymentRequestForm> page = paymentRequestFormRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /payment-request-forms/:id} : get the "id" paymentRequestForm.
     *
     * @param id the id of the paymentRequestForm to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paymentRequestForm, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/payment-request-forms/{id}")
    public ResponseEntity<PaymentRequestFormBean> getPaymentRequestForm(@PathVariable Long id) {
        log.debug("REST request to get PaymentRequestForm : {}", id);
        PaymentRequestForm paymentRequestForm = paymentRequestFormRepository.findById(id).orElse(null);
        PaymentRequestFormBean paymentRequestFormBean = new PaymentRequestFormBean();
        BeanUtils.copyProperties(paymentRequestForm, paymentRequestFormBean);
        List<PaymentRequestFormDetails> paymentRequestFormDetails = paymentRequestFormDetailsRepository.findAllWSByPaymentRequestFormId(id);
        paymentRequestFormBean.setPaymentRequestFormDetails(paymentRequestFormDetails);
        List<PaymentRequestInvoice> paymentRequestInvoices = paymentRequestInvoiceRepository.findByPaymentRequestFormId(id);
        if(paymentRequestInvoices != null && paymentRequestInvoices.size()>0){
            List<PaymentRequestInvoiceBean> paymentRequestInvoiceBeans = new ArrayList<>();
            for(PaymentRequestInvoice paymentRequestInvoice : paymentRequestInvoices) {
                PaymentRequestInvoiceBean paymentRequestInvoiceBean = new PaymentRequestInvoiceBean();
                paymentRequestInvoiceBean.setInvoiceNo(paymentRequestInvoice.getId().getInvoiceNo());
                paymentRequestInvoiceBean.setInvoiceDate(paymentRequestInvoice.getInvoiceDate());
                paymentRequestInvoiceBeans.add(paymentRequestInvoiceBean);
            }
            paymentRequestFormBean.setInvoices(paymentRequestInvoiceBeans);
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(paymentRequestFormBean));
    }

    /**
     * {@code GET  /payment-request-forms/:id} : get the "id" paymentRequestForm.
     *
     * @param id the id of the paymentRequestForm to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paymentRequestForm, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/payment-request-approval/{id}")
    public ResponseEntity<PaymentRequestFormBean> getPaymentRequestApproval(@PathVariable Long id) throws ParseException {
        log.debug("REST request to get PaymentRequestForm : {}", id);
        PaymentRequestForm paymentRequestForm = paymentRequestFormRepository.findById(id).orElse(null);
        PaymentRequestFormBean paymentRequestFormBean = new PaymentRequestFormBean();
        BeanUtils.copyProperties(paymentRequestForm, paymentRequestFormBean);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        Finfinancialyear finfinancialyear = finfinancialyearRepository.findByDate(date);
        BigDecimal amount = findocumentRepository.fetchOutstandingAmount(simpleDateFormat2.format(finfinancialyear.getFromdate()), simpleDateFormat2.format(date), paymentRequestFormBean.getSupplierCode());
        paymentRequestFormBean.setOutstandingAmount(amount);
        List<PaymentRequestFormDetails> paymentRequestFormDetails = paymentRequestFormDetailsRepository.findAllByPaymentRequestFormId(id);
        paymentRequestFormBean.setPaymentRequestFormDetails(paymentRequestFormDetails);
        PaymentRequestFormDetails paymentRequestFormDetail = paymentRequestFormDetailsRepository.findByPaymentRequestFormId(id);
        paymentRequestFormBean.setPaymentRequestFormDetail(paymentRequestFormDetail);
        List<Object[]> objects = paymentRequestForwardRepository.findAllByForward(SecurityUtils.getCurrentUserLogin().orElse(null));
        List<MasterSearch> forwards = new ArrayList<>();
        for (Object[] object : objects) {
            MasterSearch search = new MasterSearch();
            search.setCode(object[0].toString());
            search.setDescription(object[1].toString());
            forwards.add(search);
        }
        paymentRequestFormBean.setForwards(forwards);
        List<PaymentRequestInvoice> paymentRequestInvoices = paymentRequestInvoiceRepository.findByPaymentRequestFormId(id);
        if(paymentRequestInvoices != null && paymentRequestInvoices.size()>0){
            List<PaymentRequestInvoiceBean> paymentRequestInvoiceBeans = new ArrayList<>();
            for(PaymentRequestInvoice paymentRequestInvoice : paymentRequestInvoices) {
                PaymentRequestInvoiceBean paymentRequestInvoiceBean = new PaymentRequestInvoiceBean();
                paymentRequestInvoiceBean.setInvoiceNo(paymentRequestInvoice.getId().getInvoiceNo());
                paymentRequestInvoiceBean.setInvoiceDate(paymentRequestInvoice.getInvoiceDate());
                paymentRequestInvoiceBeans.add(paymentRequestInvoiceBean);
            }
            paymentRequestFormBean.setInvoices(paymentRequestInvoiceBeans);
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(paymentRequestFormBean));
    }

    /**
     * {@code GET  /payment-request-forms/:id} : get the "id" paymentRequestForm.
     *
     * @param id the id of the paymentRequestForm to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paymentRequestForm, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/payment-request-finance/{id}")
    public ResponseEntity<PaymentRequestFormBean> getPaymentRequestFinance(@PathVariable Long id) throws ParseException {
        log.debug("REST request to get PaymentRequestForm : {}", id);
        PaymentRequestForm paymentRequestForm = paymentRequestFormRepository.findById(id).orElse(null);
        PaymentRequestFormBean paymentRequestFormBean = new PaymentRequestFormBean();
        BeanUtils.copyProperties(paymentRequestForm, paymentRequestFormBean);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        Finfinancialyear finfinancialyear = finfinancialyearRepository.findByDate(date);
        BigDecimal amount = findocumentRepository.fetchOutstandingAmount(simpleDateFormat2.format(finfinancialyear.getFromdate()), simpleDateFormat2.format(date), paymentRequestFormBean.getSupplierCode());
        paymentRequestFormBean.setOutstandingAmount(amount);
        List<PaymentRequestFormDetails> paymentRequestFormDetails = paymentRequestFormDetailsRepository.findAllByPaymentRequestFormId(id);
        paymentRequestFormBean.setPaymentRequestFormDetails(paymentRequestFormDetails);
        PaymentRequestFormDetails paymentRequestFormDetail = paymentRequestFormDetailsRepository.findByPaymentRequestFormId(id);
        paymentRequestFormBean.setPaymentRequestFormDetail(paymentRequestFormDetail);
        List<Object[]> objects = paymentRequestForwardRepository.findAllByForward(SecurityUtils.getCurrentUserLogin().orElse(null));
        List<MasterSearch> forwards = new ArrayList<>();
        for (Object[] object : objects) {
            MasterSearch search = new MasterSearch();
            search.setCode(object[0].toString());
            search.setDescription(object[1].toString());
            forwards.add(search);
        }
        paymentRequestFormBean.setForwards(forwards);
        List<PaymentRequestInvoice> paymentRequestInvoices = paymentRequestInvoiceRepository.findByPaymentRequestFormId(id);
        if(paymentRequestInvoices != null && paymentRequestInvoices.size()>0){
            List<PaymentRequestInvoiceBean> paymentRequestInvoiceBeans = new ArrayList<>();
            for(PaymentRequestInvoice paymentRequestInvoice : paymentRequestInvoices) {
                PaymentRequestInvoiceBean paymentRequestInvoiceBean = new PaymentRequestInvoiceBean();
                paymentRequestInvoiceBean.setInvoiceNo(paymentRequestInvoice.getId().getInvoiceNo());
                paymentRequestInvoiceBean.setInvoiceDate(paymentRequestInvoice.getInvoiceDate());
                paymentRequestInvoiceBeans.add(paymentRequestInvoiceBean);
            }
            paymentRequestFormBean.setInvoices(paymentRequestInvoiceBeans);
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(paymentRequestFormBean));
    }

    /**
     * {@code DELETE  /payment-request-forms/:id} : delete the "id" paymentRequestForm.
     *
     * @param id the id of the paymentRequestForm to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/payment-request-forms/{id}")
    public ResponseEntity<Void> deletePaymentRequestForm(@PathVariable Long id) {
        log.debug("REST request to delete PaymentRequestForm : {}", id);
        paymentRequestFormRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
    @PostMapping("/payment-request-forms-filter")
    public ResponseEntity<List<PaymentRequestForm>>getAllPaymentRequestFormFilter(@RequestBody PaymentRequestFormSearch search){
        log.debug("REST request to filter PaymentRequestForm");
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
        Page<PaymentRequestForm> page = null;
        if (search.getBookingDateFrom() != null && search.getBookingDateTo() != null) {
            page = paymentRequestFormRepository.findAllByRequestNoLike(search.getBookingDateFrom(), search.getBookingDateTo(), search.getFlag(), supplier, supplier, company, division, businsessunit, id, search.getPage());
        } else {
            page = paymentRequestFormRepository.findAllByRequestNoLike(search.getFlag(), supplier, supplier, company, division, businsessunit, id, search.getPage());
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @PostMapping("/payment-request-approval-filter")
    public ResponseEntity<List<PaymentRequestForm>>getAllPaymentRequestApprovalFilter(@RequestBody PaymentRequestFormSearch search){
        log.debug("REST request to filter PaymentRequestForm");
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
        Page<PaymentRequestForm> page = null;
        if (search.getBookingDateFrom() != null && search.getBookingDateTo() != null) {
            page = paymentRequestFormRepository.findAllByRequestNoLike(search.getBookingDateFrom(), search.getBookingDateTo(), search.getFlag(), supplier, supplier, company, division, businsessunit, id, SecurityUtils.getCurrentUserLogin().orElse(null), search.getPage());
        } else {
            page = paymentRequestFormRepository.findAllByRequestNoLike(search.getFlag(), supplier, supplier, company, division, businsessunit, id, SecurityUtils.getCurrentUserLogin().orElse(null), search.getPage());
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @PostMapping("/payment-request-finance-filter")
    public ResponseEntity<List<PaymentRequestForm>>getAllPaymentRequestFinanceFilter(@RequestBody PaymentRequestFormSearch search){
        log.debug("REST request to filter PaymentRequestForm");
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
        Page<PaymentRequestForm> page = null;
        if (search.getBookingDateFrom() != null && search.getBookingDateTo() != null) {
            page = paymentRequestFormRepository.findAllByRequestNoLike(search.getBookingDateFrom(), search.getBookingDateTo(), search.getFlag(), supplier, supplier, company, division, businsessunit, id, search.getPage());
        } else {
            page = paymentRequestFormRepository.findAllByRequestNoLike(search.getFlag(), supplier, supplier, company, division, businsessunit, id, search.getPage());
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @PostMapping("/payment-request-report")
    @Timed
    public @ResponseBody void getPaymentRequestReportDetails(@Valid @RequestBody DirectBookingSearch search, HttpServletResponse response) throws JRException, IOException, ParseException {
        search.setPage(PageRequest.of(0, Integer.MAX_VALUE, Sort.by("id").ascending()));
        String supplier = "%";

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

        Page<PaymentRequestForm> page = paymentRequestFormRepository.findAllByRequestNoLike(search.getBookingDateFrom(), search.getBookingDateTo(), supplier, supplier, company, division, businsessunit, search.getPage());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        List<PaymentRequestFormReportBean> paymentRequestFormBeans = new ArrayList<>();
        Map<String, String> employeeMap = new HashMap<>();
        for (PaymentRequestForm paymentRequestForm : page.getContent()) {
            PaymentRequestFormReportBean bean = new PaymentRequestFormReportBean();
            bean.setId(paymentRequestForm.getId());
            bean.setRequestDate(simpleDateFormat.format(Date.from(paymentRequestForm.getRequestDate())));
            bean.setSupplierName(paymentRequestForm.getSupplierName());
            bean.setPaymentType(paymentRequestForm.getPaymentType() != null && paymentRequestForm.getPaymentType().equalsIgnoreCase("PO") ? "Payment Request For PO" : "Payment Request For PI");
            bean.setPiNo(paymentRequestForm.getPiNo());
            bean.setRequestAmount(paymentRequestForm.getRequestAmount());
            bean.setTotalReleaseAmount(paymentRequestForm.getTotalReleaseAmount()); // 2 times method used
            bean.setTdsValue(paymentRequestForm.getTdsValue());
            bean.setRemarks(paymentRequestForm.getRemarks());
            if(paymentRequestForm.getCreatedBy() != null) {
                if (employeeMap.containsKey(paymentRequestForm.getCreatedBy())) {
                    bean.setCreatedBy(employeeMap.get(paymentRequestForm.getCreatedBy()));
                } else {
                    EmployeeView employeeView = employeeViewRepository.findByLogin(paymentRequestForm.getCreatedBy());
                    if (employeeView != null) {
                        bean.setCreatedBy(employeeView.getName());
                        employeeMap.put(paymentRequestForm.getCreatedBy(), employeeView.getName());
                    } else {
                        employeeMap.put(paymentRequestForm.getCreatedBy(), "");
                    }
                }
            }
            bean.setCreatedDate(simpleDateFormat.format(Date.from(paymentRequestForm.getCreatedDate())));
            if(paymentRequestForm.getApprovedBy() != null) {
                if (employeeMap.containsKey(paymentRequestForm.getApprovedBy())) {
                    bean.setApprovedBy(employeeMap.get(paymentRequestForm.getApprovedBy()));
                } else {
                    EmployeeView employeeView = employeeViewRepository.findByLogin(paymentRequestForm.getApprovedBy());
                    if (employeeView != null) {
                        bean.setApprovedBy(employeeView.getName());
                        employeeMap.put(paymentRequestForm.getApprovedBy(), employeeView.getName());
                    } else {
                        employeeMap.put(paymentRequestForm.getApprovedBy(), "");
                    }
                }
            }
            bean.setApprovedDate(paymentRequestForm.getApprovedDate() != null ? simpleDateFormat.format(Date.from(paymentRequestForm.getApprovedDate())) : null);
            bean.setPoNo(paymentRequestForm.getPoNo());
            bean.setStatus(paymentRequestForm.getStatus().equalsIgnoreCase("E") ? "Pending" : paymentRequestForm.getStatus().equalsIgnoreCase("A") ? "Approved" : paymentRequestForm.getStatus().equalsIgnoreCase("R") ? "Rejected" : "Forward");

            bean.setChequeNo(paymentRequestForm.getChequeNo());
            bean.setUtrNo(paymentRequestForm.getUtrNo());
            bean.setUtrDate(paymentRequestForm.getUtrDate() != null ? simpleDateFormat.format(Date.from(paymentRequestForm.getUtrDate())) : null);
            paymentRequestFormBeans.add(bean);
        }
        String path = applicationProperties.getTemplatePath() + "jasper/";
        JasperDesign jasperDesign = JRXmlLoader.load(path + "/paymentRequestReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String, Object> parameters = new HashMap<String, Object>();
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(paymentRequestFormBeans);
        parameters.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
        parameters.put("datasource", jrDataSource);
        parameters.put("SUBREPORT_DIR", path);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=paymentRequestReport.xlsx");

        final OutputStream outputStream = response.getOutputStream();

        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
        exporter.exportReport();
    }

    @PostMapping("/db2-purchase-order")
    public @ResponseBody
    void fetchPurchaseOrder(@RequestBody MasterSearch search, HttpServletResponse response)
        throws Exception {
        String[] temp = new String[search.getParameters1().size()];
        for (int i = 0; i < search.getParameters1().size(); i++) {
            temp[i] = search.getParameters1().get(i);
        }
        Connection conn = null;
        try {
            conn = db2DataSource().getConnection();
            if (search.getTemplateType() != null && search.getTemplateType().equalsIgnoreCase("PO_GENERAL")) {
                List<PurchaseOrderBean> purchaseOrderBean = fetchAllPoById(temp, search.getTemplateType().trim().toUpperCase(), conn);
                String path = applicationProperties.getTemplatePath() + "jasper/";
                JasperDesign jasperDesign = JRXmlLoader.load(path + "/PurchaseOrderGeneral.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                Map<String, Object> parameters = new HashMap<>();
                JRDataSource jrDataSource = new JRBeanCollectionDataSource(purchaseOrderBean);
                parameters.put("datasource", jrDataSource);
                parameters.put("SUBREPORT_DIR", path);
                parameters.put("SUBREPORT_CONN", conn);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
                if (search.getReportType() != null && search.getReportType().equalsIgnoreCase("PDF")) {
                    response.setContentType("application/x-pdf");
                    response.setHeader("Content-Disposition", "attachment; filename=PurchaseOrderGeneral.pdf");
                    final OutputStream outputStream = response.getOutputStream();
                    JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
                } else {
                    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                    response.setHeader("Content-Disposition", "attachment; filename=PurchaseOrderGeneral.xlsx");
                    final OutputStream outputStream = response.getOutputStream();
                    JRXlsxExporter exporter = new JRXlsxExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
                    exporter.exportReport();
                }
            } else if (search.getTemplateType() != null && search.getTemplateType().equalsIgnoreCase("PO_FABRIC_ACC")) {
                List<PurchaseOrderBean> purchaseOrderBean = fetchAllPoById(temp, search.getTemplateType().trim().toUpperCase(), conn);
                String path = applicationProperties.getTemplatePath() + "jasper/";
                JasperDesign jasperDesign = JRXmlLoader.load(path + "/PurchaseOrder.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                Map<String, Object> parameters = new HashMap<>();
                JRDataSource jrDataSource = new JRBeanCollectionDataSource(purchaseOrderBean);
                parameters.put("datasource", jrDataSource);
                parameters.put("SUBREPORT_DIR", path);
                parameters.put("SUBREPORT_CONN", conn);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
                if (search.getReportType() != null && search.getReportType().equalsIgnoreCase("PDF")) {
                    response.setContentType("application/x-pdf");
                    response.setHeader("Content-Disposition", "attachment; filename=PurchaseOrder.pdf");
                    final OutputStream outputStream = response.getOutputStream();
                    JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
                } else {
                    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                    response.setHeader("Content-Disposition", "attachment; filename=PurchaseOrder.xlsx");
                    final OutputStream outputStream = response.getOutputStream();
                    JRXlsxExporter exporter = new JRXlsxExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
                    exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
                    exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
                    exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
                    exporter.exportReport();
                }
            } else if (search.getTemplateType() != null && search.getTemplateType().equalsIgnoreCase("PO_IMPORT")) {
                List<PurchaseOrderImportBean> purchaseOrderImportBean = fetchAllPoImpById(temp, conn);
                String path = applicationProperties.getTemplatePath() + "jasper/";
                JasperDesign jasperDesign = JRXmlLoader.load(path + "/PurchaseOrderImport.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                Map<String, Object> parameters = new HashMap<>();
                JRDataSource jrDataSource = new JRBeanCollectionDataSource(purchaseOrderImportBean);
                parameters.put("datasource", jrDataSource);
                parameters.put("SUBREPORT_DIR", path);
                parameters.put("SUBREPORT_CONN", conn);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
                if (search.getReportType() != null && search.getReportType().equalsIgnoreCase("PDF")) {
                    response.setContentType("application/x-pdf");
                    response.setHeader("Content-Disposition", "attachment; filename=PurchaseOrderImport.pdf");
                    final OutputStream outputStream = response.getOutputStream();
                    JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
                } else {
                    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                    response.setHeader("Content-Disposition", "attachment; filename=PurchaseOrderGeneral.xlsx");
                    final OutputStream outputStream = response.getOutputStream();
                    JRXlsxExporter exporter = new JRXlsxExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
                    exporter.exportReport();
                }
            } else if (search.getTemplateType() != null && search.getTemplateType().equalsIgnoreCase("PO_JOBWORK")) {
                List<PurchaseOrderJobwBean> purchaseOrderJobwBean = getAllPoJwById(temp, conn);
                String path = applicationProperties.getTemplatePath() + "jasper/";
                JasperDesign jasperDesign = JRXmlLoader.load(path + "/jobworkOrder.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                Map<String, Object> parameters = new HashMap<>();
                JRDataSource jrDataSource = new JRBeanCollectionDataSource(purchaseOrderJobwBean);
                parameters.put("datasource", jrDataSource);
                parameters.put("SUBREPORT_DIR", path);
                parameters.put("SUBREPORT_CONN", conn);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
                if (search.getReportType() != null && search.getReportType().equalsIgnoreCase("PDF")) {
                    response.setContentType("application/x-pdf");
                    response.setHeader("Content-Disposition", "attachment; filename=PurchaseOrderImport.pdf");
                    final OutputStream outputStream = response.getOutputStream();
                    JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
                } else {
                    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                    response.setHeader("Content-Disposition", "attachment; filename=PurchaseOrderGeneral.xlsx");
                    final OutputStream outputStream = response.getOutputStream();
                    JRXlsxExporter exporter = new JRXlsxExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
                    exporter.exportReport();
                }
            }
        } catch (Exception e) {
            System.out.println("PurchaseOrderResource fetchPurchaseOrder()" + e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
            conn = null;
        }
    }

    public static List<PurchaseOrderBean> fetchAllPoById(String[] orderno, String templateType, Connection conn) throws Exception {
        List<PurchaseOrderBean> purchaseOrderBean = new ArrayList<>();
        PreparedStatement stmt1 = null;
        ResultSet result1 = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        String NumbertoWord = "";
        String qyery = "select (select NVL(TRIM(UD.FULLNAME),'') from VOLPOAPPROVALHISTORY AH, ABSUserDef UD WHERE AH.FIRSTAPPROVERIDUSERID = UD.USERID and ah.COMPANYCODE=a.companycode and ah.COUNTERCODE=a.countercode and ah.CODE=a.code)approvedby, " +
            " (select NVL(TRIM(VALUESTRING),'') from adstorage AD where AD.NAMEENTITYNAME='PurchaseOrder' "
            + " and AD.NAMENAME='SpecialInstruction' AND AD.UNIQUEID=a.absuniqueid)specialInstruction, "
            + " (SELECT sum(GROSSVALUEWOHEADER) FROM purchaseorderlineie WHERE purchaseordercode=a.code)grossValueInword, a.companycode, a.countercode, a.code, "
            + " a.currencycode,(select distinct nvl(trim(FULLNAME),'') from ABSUserDef where userid=a.creationuser)creationuser, "
            + " (nvl((select to_char(DELIVERYDATE,'dd/mm/yyyy')  from purchaseorderdelivery where PURORDERLINEPURCHASEORDERCODE=a.code order by DELIVERYDATE desc fetch first row only),''))inhousedate ,  "
            + " (nvl((select PERCENTAGE from advance where PURCHASEORDERCODE=a.code fetch first row only),0))advancepoamount, a.DESCRIPTION,  "
            + " decode(a.currentstatus,'1','APPROVED','2','UNAPPROVED')currentstatus, d.longdescription AS DIVNAME,   "
            + " ( f.addressline1|| ' '|| f.addressline2|| ' '|| f.town|| '-'|| NVL(f.postalcode,'')) divaddress, f.addressphonenumber,f.emailaddress, "
            + " f.tinno,f.midno, fc.code factorycode, fc.longdescription AS factoryname, "
            + " (TRIM(nvl(fc.addressline1, ''))|| ''|| TRIM(nvl(fc.addressline2, ''))|| ''|| TRIM(nvl(fc.addressline3, ''))|| ' '||nvl(fc.district,'')|| ' '|| "
            + " nvl(st.longdescription,'')||'-'||nvl(TRIM(fc.postalcode), '') ) factoryaddress, fc.statecode  factorystatecode,  fc.cstno   factorycstno, "
            + " TO_CHAR(fc.cstdate, 'dd/mm/yyyy') factorycstdate, ag.gstinnumber  factorygstno,  fc.addressphonenumber   factoryphoneno, fc.emailaddress factoryemailaddress,  "
            + " b.legalname1  AS vnname, o.customersuppliertype, "
            + " nvl(case when a.ALTERNATIVEADDRESSCODE is null then (TRIM((nvl(b.addressline1, '')|| ' '|| nvl(b.addressline2, '')|| ' '|| "
            + " nvl(b.addressline3, '')|| ' '|| nvl(b.addressline4, '')|| ' '|| nvl(b.district, '')|| '-'|| nvl(TRIM(b.postalcode), '')|| ' Ph.No :'|| nvl(b.phonenumber, ''))))  "
            + " else (select TRIM((nvl(gg.addressline1, '')|| ' '|| nvl(gg.addressline2, '')|| ' '|| nvl(gg.addressline3, '')|| ' '||"
            + " nvl(gg.addressline4, '')|| ' '||  nvl(gg.district, '')|| '-'|| nvl(TRIM(gg.postalcode), '')|| ' Ph.No :'|| nvl(gg.ADDRESSPHONENUMBER, ''))) "
            + " from address gg where gg.uniqueid=a.ALTERNATIVEADDRESSUNIQUEID  "
            + " and gg.code=a.ALTERNATIVEADDRESSCODE) end,'')vnaddress, "
            + "nvl(case when a.ALTERNATIVEADDRESSCODE is null then (SELECT gstinnumber FROM addressgst WHERE uniqueid = b.absuniqueid)  else "
            + "(select GSTINNUMBER from address gg, addressgst gs where gg.absuniqueid=gs.uniqueid and gg.uniqueid=a.ALTERNATIVEADDRESSUNIQUEID "
            + " and gg.code=a.ALTERNATIVEADDRESSCODE) end,'') vngstnumber,  "
            + " ts.longdescription termsofship, a.creationuser, pm.longdescription paymentdesc,  "
            + " (SELECT nvl(SUBSTR(xmlserialize(xmlagg(xmltext(CONCAT( ', ',NOTE))) as VARCHAR(1024)), 3),'') AS remarks FROM note where fatherid=a.absuniqueid)NOTEREMARKS,  "
            + " o.absuniqueid AS orderabsid, a.firstcarriertype,a.firstcarriercode, nvl(case when a.ALTERNATIVEADDRESSCODE is null then  "
            + " (SELECT statecode FROM addressgst WHERE uniqueid = b.absuniqueid) else (select gs.STATECODE from address gg, addressgst gs where gg.absuniqueid=gs.uniqueid  "
            + " and gg.uniqueid=a.ALTERNATIVEADDRESSUNIQUEID and gg.code=a.ALTERNATIVEADDRESSCODE) end,'') vnstatecode, nvl(case when a.ALTERNATIVEADDRESSCODE is null then  "
            + " (SELECT TRIM(longdescription) statename FROM addressgst ag, state st WHERE ag.statecode = st.code AND uniqueid = b.absuniqueid) else  "
            + " (select tt.LONGDESCRIPTION from address gg, addressgst gs, state tt where gg.absuniqueid=gs.uniqueid and gs.statecode=tt.code and gg.uniqueid=a.ALTERNATIVEADDRESSUNIQUEID  "
            + " and gg.code=a.ALTERNATIVEADDRESSCODE) end,'') vnstatename, nvl(case when a.ALTERNATIVEADDRESSCODE is null then rtrim(b.phonenumber)  "
            + " else (select gg.ADDRESSPHONENUMBER from address gg where gg.uniqueid=a.ALTERNATIVEADDRESSUNIQUEID and gg.code=a.ALTERNATIVEADDRESSCODE) end,'')vnphonenumber,  "
            + " nvl(case when a.ALTERNATIVEADDRESSCODE is null then nvl(rtrim(b.emailaddress), '') else (select gg.EMAILADDRESS from address gg where gg.uniqueid=a.ALTERNATIVEADDRESSUNIQUEID  "
            + " and gg.code=a.ALTERNATIVEADDRESSCODE) end,'') vnemailaddress,  b.absuniqueid   bp_absuniqueid,  (SELECT gstinnumber FROM addressgst WHERE uniqueid = b.absuniqueid) vngstnumber,   "
            + " (SELECT statecode FROM addressgst WHERE uniqueid = b.absuniqueid) vnstatecode,   (SELECT TRIM(longdescription) statename FROM addressgst ag, state st WHERE ag.statecode = st.code "
            + " AND uniqueid = b.absuniqueid) vnstatename,  b.faxnumber   AS fax,  TO_CHAR(a.orderdate, 'dd/MM/yyyy') orderdate, a.ordprncustomersuppliercode, a.alternativeaddresscode,"
            + "  a.deliverypointcode, a.externalreference,   a.internalreference, a.warehousecode,  a.currencycode, l.plantcode, (NVL(TRIM(pt.CODE),'')||' - '||NVL(TRIM(pt.LONGDESCRIPTION),''))POTYPE,"
            + "  a.absuniqueid,   a.alternativeaddressuniqueid, t.longdescription  deliveryterm, t.longdescription  AS delvterm, tm.longdescription AS shipmentterm, pm.code    AS paymentcode, "
            + " (select PH.LONGDESCRIPTION WAREHOUSENAME from physicalwarehouse PH where  PH.code=a.warehousecode),  "
            + " (select (NVL(TRIM(PH.ADDRESSLINE1),'')||' '||NVL(TRIM(PH.ADDRESSLINE2),'')||' '||NVL(TRIM(PH.ADDRESSLINE3),'')) WHADDRESS1 "
            + " from physicalwarehouse PH  WHERE PH.code=a.warehousecode),   (select (TRIM(PH.DISTRICT)||'-'||PH.POSTALCODE)WHADDRESS2 from physicalwarehouse PH where PH.code=a.warehousecode),  "
            + " (select  PH.PHONENUMBER WHPHONENUMBER from physicalwarehouse PH where PH.code=a.warehousecode),   "
            + " (select EMAILADDRESS WHEMAILADDRESS from physicalwarehouse PH where PH.code=a.warehousecode),   "
            + " (SELECT A.GSTINNUMBER FROM FACTORY F, ADDRESSGST A WHERE F.ABSUNIQUEID = A.UNIQUEID AND F.CODE=lg.PLANTCODE)gstinnumber,  "
            + " (SELECT S.LONGDESCRIPTION FROM FACTORY F, ADDRESSGST A, STATE S WHERE F.ABSUNIQUEID = A.UNIQUEID AND A.STATECODE=S.CODE AND F.CODE=lg.PLANTCODE)statename,  "
            + " (SELECT A.STATECODE FROM FACTORY F, ADDRESSGST A WHERE F.ABSUNIQUEID = A.UNIQUEID AND F.CODE=lg.PLANTCODE)statecode  "
            + " from  purchaseorder a   "
            + " LEFT OUTER JOIN termsofshipping ts ON a.companycode = ts.companycode AND a.termsofshippingcode = ts.code  "
            + " LEFT OUTER JOIN termsofdelivery t  ON a.companycode = t.companycode AND a.termsofdeliverycode = t.code  "
            + " LEFT OUTER JOIN purchaseorderie pi ON a.companycode = pi.companycode AND a.code = pi.code   "
            + " LEFT OUTER JOIN logicalwarehouse l ON a.companycode = l.companycode AND a.divisioncode = l.divisioncode AND a.warehousecode = l.code   "
            + " LEFT OUTER JOIN termsofshipping tm ON a.companycode = tm.companycode AND a.termsofshippingcode = tm.code   "
            + " LEFT OUTER JOIN paymentmethod pm ON a.companycode = pm.companycode  AND a.paymentmethodcode = pm.code   "
            + " LEFT OUTER JOIN purchaseordertemplate pt ON a.companycode = pt.companycode AND a.templatecode = pt.code, "
            + "  company c, firm f, division d, factory fc, addressgst ag, state st, logicalwarehouse lg, orderpartner o, businesspartner b where a.companycode = c.code  "
            + " AND a.companycode = f.companycode AND a.divisioncode = f.code AND a.companycode = d.companycode AND a.divisioncode = d.code AND a.warehousecode = lg.code "
            + " AND lg.plantcode = fc.code  AND fc.absuniqueid = ag.uniqueid AND fc.statecode = st.code AND a.companycode = o.customersuppliercompanycode AND a.ordertype = o.customersuppliertype   "
            + " AND a.ordprncustomersuppliercode = o.customersuppliercode AND o.orderbusinesspartnernumberid = b.numberid "
            + " AND A.COUNTERCODE IN (SELECT distinct TEMPLATETYPE FROM voplreporttemplate WHERE REPORTTYPE='"
            + templateType + "')  ";

        String parameter = null;

        for (String headercode : orderno) {
            if (parameter != null && parameter.length() > 0) {
                parameter += ",'" + headercode + "'";
            } else {
                parameter = " and a.code in ('" + headercode.toUpperCase() + "' ";
            }
        }
        parameter += ")";
        qyery += parameter;
        try {
            stmt = conn.prepareStatement(qyery);
            result = stmt.executeQuery();
            while (result.next()) {
                PurchaseOrderBean bean = new PurchaseOrderBean();
                if (result.getString("grossValueInword") != null && result.getString("grossValueInword").length() > 0) {
                    NumbertoWord = EnglishNumberToWords.callNumToWord(result.getString("grossValueInword"), 0);
                    bean.setGrossValueInword(NumbertoWord);
                }
                bean.setCountercode(result.getString("countercode"));
                bean.setCode(result.getString("code"));
                bean.setCurrencycode(result.getString("currencycode"));
                bean.setCreationuser(result.getString("creationuser"));
                bean.setInhousedate(result.getString("inhousedate"));
                bean.setApprovedby(result.getString("approvedby"));
                bean.setAdvancepoamount(result.getBigDecimal("advancepoamount"));
                bean.setDescription(result.getString("DESCRIPTION"));
                bean.setCurrentstatus(result.getString("currentstatus"));
                bean.setDivname(result.getString("DIVNAME"));
                bean.setDivaddress(result.getString("divaddress"));
                bean.setAddressphonenumber(result.getString("addressphonenumber"));
                bean.setEmailaddress(result.getString("emailaddress"));
                bean.setTinno(result.getString("tinno"));
                bean.setMidno(result.getString("midno"));
                bean.setSpecialInstruction(result.getString("specialInstruction"));
                bean.setFactorycode(result.getString("factorycode"));
                bean.setFactoryname(result.getString("factoryname"));
                bean.setFactoryaddress(result.getString("factoryaddress"));
                bean.setFactorystatecode(result.getString("factorystatecode"));
                bean.setFactorycstno(result.getString("factorycstno"));
                bean.setFactorycstdate(result.getString("factorycstdate"));
                bean.setFactorygstno(result.getString("factorygstno"));
                bean.setFactoryemailaddress(result.getString("factoryemailaddress"));
                bean.setFactoryphoneno(result.getString("factoryphoneno"));
                bean.setVnname(result.getString("vnname"));
                bean.setVnaddress(result.getString("vnaddress"));
                bean.setVngstnumber(result.getString("vngstnumber"));
                bean.setVnstatecode(result.getString("vnstatecode"));
                bean.setVnstatename(result.getString("vnstatename"));
                bean.setVnphonenumber(result.getString("vnphonenumber"));
                bean.setVnemailaddress(result.getString("vnemailaddress"));
                bean.setFax(result.getString("fax"));
                bean.setOrderdate(result.getString("orderdate"));
                bean.setOrdprncustomersuppliercode(result.getString("ordprncustomersuppliercode"));
                bean.setAlternativeaddresscode(result.getString("alternativeaddresscode"));
                bean.setDeliverypointcode(result.getString("deliverypointcode"));
                bean.setExternalreference(result.getString("externalreference"));
                bean.setInternalreference(result.getString("internalreference"));
                bean.setWarehousecode(result.getString("warehousecode"));
                bean.setPotype(result.getString("POTYPE"));
                bean.setDeliveryterm(result.getString("deliveryterm"));
                bean.setShipmentterm(result.getString("shipmentterm"));
                bean.setPaymentcode(result.getString("paymentcode"));
                bean.setPaymentdesc(result.getString("paymentdesc"));
                bean.setNoteremarks(result.getString("NOTEREMARKS"));
                bean.setWarehousename(result.getString("WAREHOUSENAME"));
                bean.setWhaddress1(result.getString("WHADDRESS1"));
                bean.setWhaddress2(result.getString("WHADDRESS2"));
                bean.setWhphonenumber(result.getString("WHPHONENUMBER"));
                bean.setWhemailaddress(result.getString("WHEMAILADDRESS"));
                bean.setGstinnumber(result.getString("gstinnumber"));
                bean.setStatecode(result.getString("statecode"));
                bean.setStatename(result.getString("statename"));

                List<PurchaseOrderLineBean> purchaseOrderLineBean = new ArrayList<PurchaseOrderLineBean>();
                stmt1 = conn.prepareStatement(
                    "SELECT pl.PURCHASEORDERCOMPANYCODE,pl.PURCHASEORDERCOUNTERCODE,pl.PURCHASEORDERCODE, pl.projectcode, "
                        + " (nvl((select distinct SALESORDERCODE from salesorderline where PROJECTCODE = pl.projectcode fetch first row only),''))salesordercode, "
                        + " pl.ORDERLINE,    (REPLACE(NVL(TRIM(pl.subcode01),''),'NA','')|| ' '|| REPLACE(NVL(TRIM(pl.subcode02),''),'NA','')|| ' '|| REPLACE(NVL(TRIM(pl.subcode03),''),'NA','')|| ' '|| REPLACE(NVL(TRIM(pl.subcode04),''),'NA','')|| ' '|| "
                        + "  REPLACE(NVL(TRIM(pl.subcode05),''),'NA','')|| ' '|| REPLACE(NVL(TRIM(pl.subcode06),''),'NA','')|| ' '|| REPLACE(NVL(TRIM(pl.subcode07),''),'NA','')|| ' '|| REPLACE(NVL(TRIM(pl.subcode08),''),'NA','')|| ' '|| "
                        + "  REPLACE(NVL(TRIM(pl.subcode09),''),'NA','')|| ' '|| REPLACE(NVL(TRIM(pl.subcode10),''),'NA','')) ITEMCODE,nvl(pl.itemdescription,'') ITEMDESC, ie.tariffcode HSNCODE, "
                        + " ie.GROSSVALUEWOHEADER,   pl.USERPRIMARYUOMCODE  UOM, nvl(pl.userprimaryquantity, 0) QTY, nvl(pl.price, 0) PRICE, nvl(pl.netvalue, 0) NETVALUE,  "
                        + " pl.absuniqueid, "
                        + " nvl((SELECT sum(value) FROM indtaxdetail WHERE itaxcode IN('IG1','IG2','IG3','IG4') AND absuniqueid = pl.absuniqueid), 0) IGSTTAXPERS,  "
                        + " nvl((SELECT sum(calculatedvaluertc) FROM indtaxdetail    WHERE itaxcode IN('IG1','IG2','IG3','IG4') AND absuniqueid = pl.absuniqueid), 0)IGSTVALUE,   "
                        + " nvl((SELECT sum(value) taxpers FROM indtaxdetail         WHERE itaxcode IN('SG1','SG2','SG3','SG4') AND absuniqueid = pl.absuniqueid), 0) SGSTTAXPERS,   "
                        + " nvl((SELECT sum(calculatedvaluertc) FROM indtaxdetail    WHERE itaxcode IN('SG1','SG2','SG3','SG4') AND absuniqueid = pl.absuniqueid), 0) SGSTVALUE,   "
                        + " nvl((SELECT sum(value) taxpers FROM indtaxdetail         WHERE itaxcode IN('CG1','CG2','CG3','CG4') AND absuniqueid = pl.absuniqueid), 0) CGSTPERS,   "
                        + " nvl((SELECT sum(calculatedvaluertc) FROM indtaxdetail    WHERE itaxcode IN('CG1','CG2','CG3','CG4') AND absuniqueid = pl.absuniqueid), 0) CGSTVALUE,   "
                        + " (select sum(CALCULATEDVALUERTC) from indtaxdetail where absuniqueid=pl.absuniqueid and ITAXCODE='F01')FREIGHT,   "
                        + " (select sum(CALCULATEDVALUERTC) from indtaxdetail where absuniqueid=pl.absuniqueid and ITAXCODE in('DST','DIS'))DISCOUNT "
                        + " FROM   purchaseorderline pl, purchaseorderlineie ie WHERE pl.purchaseordercompanycode = ie.purchaseordercompanycode "
                        + " AND pl.purchaseordercountercode = ie.purchaseordercountercode   AND pl.purchaseordercode = ie.purchaseordercode AND pl.orderline = ie.orderline "
                        + " AND pl.PURCHASEORDERCOUNTERCODE = ? AND pl.PURCHASEORDERCODE = ? ");
                stmt1.setString(1, result.getString("countercode"));
                stmt1.setString(2, result.getString("code"));
                result1 = stmt1.executeQuery();
                while (result1.next()) {
                    PurchaseOrderLineBean lineBean = new PurchaseOrderLineBean();
                    lineBean.setPurchaseordercompanycode(result1.getString("PURCHASEORDERCOMPANYCODE"));
                    lineBean.setPurchaseordercountercode(result1.getString("PURCHASEORDERCOUNTERCODE"));
                    lineBean.setPurchaseordercode(result1.getString("PURCHASEORDERCODE"));
                    lineBean.setSalesordercode(result1.getString("salesordercode"));
                    lineBean.setProjectcode(result1.getString("projectcode"));
                    lineBean.setItemcode(result1.getString("ITEMCODE"));
                    lineBean.setItemdesc(result1.getString("ITEMDESC"));
                    lineBean.setTariffcode(result1.getDouble("HSNCODE"));
                    lineBean.setGrossvaluewoheader(result1.getBigDecimal("GROSSVALUEWOHEADER"));
                    lineBean.setUom(result1.getString("UOM"));
                    lineBean.setQty(result1.getBigDecimal("QTY"));
                    lineBean.setPrice(result1.getBigDecimal("PRICE"));
                    lineBean.setNetvalue(result1.getBigDecimal("NETVALUE"));
                    lineBean.setIgsttaxpers(result1.getBigDecimal("IGSTTAXPERS"));
                    lineBean.setIgstvalue(result1.getBigDecimal("IGSTVALUE"));
                    lineBean.setSgsttaxpers(result1.getBigDecimal("SGSTTAXPERS"));
                    lineBean.setSgstvalue(result1.getBigDecimal("SGSTVALUE"));
                    lineBean.setCgstpers(result1.getBigDecimal("CGSTPERS"));
                    lineBean.setCgstvalue(result1.getBigDecimal("CGSTVALUE"));
                    lineBean.setFreight(result1.getBigDecimal("FREIGHT"));
                    lineBean.setDiscount(result1.getBigDecimal("DISCOUNT"));
                    purchaseOrderLineBean.add(lineBean);
                }
                if (result1 != null) {
                    result1.close();
                }
                result1 = null;

                if (stmt1 != null) {
                    stmt1.close();
                }
                stmt1 = null;
                bean.setPurchaseOrderLineBean(purchaseOrderLineBean);
                purchaseOrderBean.add(bean);

            }
        } catch (Exception e) {
            System.out.println("PurchaseOrdeDaoImpl.class (M: fetchPurchaseOrder; Exception: " + e.getMessage() + ")");
        } finally {
            if (result != null) {
                result.close();
            }
            result = null;

            if (stmt != null) {
                stmt.close();
            }
            stmt = null;
        }
        return purchaseOrderBean;
    }

    public static List<PurchaseOrderImportBean> fetchAllPoImpById(String[] orderno, Connection conn) throws SQLException {
        List<PurchaseOrderImportBean> purchaseOrderImportBean = new ArrayList<>();
        PreparedStatement stmt1 = null;
        ResultSet result1 = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        String qyery = "select (select NVL(TRIM(UD.FULLNAME),'') from VOLPOAPPROVALHISTORY AH, ABSUserDef UD WHERE AH.FIRSTAPPROVERIDUSERID = UD.USERID and ah.COMPANYCODE=a.companycode and ah.COUNTERCODE=a.countercode and ah.CODE=a.code)approvedby, (select NVL(TRIM(VALUESTRING),'') from adstorage AD where AD.NAMEENTITYNAME='PurchaseOrder' "
            + " and AD.NAMENAME='SpecialInstruction' AND AD.UNIQUEID=a.absuniqueid)specialInstruction, (SELECT sum(NETVALUE) FROM PURCHASEORDERline WHERE purchaseordercode=a.code)netValueInword, a.companycode, a.countercode, a.code, "
            + " a.currencycode, (select distinct nvl(trim(FULLNAME),'') from ABSUserDef where userid=a.creationuser)creationuser, "
            + " (nvl((select to_char(DELIVERYDATE,'dd/mm/yyyy')  from purchaseorderdelivery where PURORDERLINEPURCHASEORDERCODE=a.code order by DELIVERYDATE desc fetch first row only),''))inhousedate ,  "
            + " (nvl((select PERCENTAGE from advance where PURCHASEORDERCODE=a.code fetch first row only),0))advancepoamount, a.DESCRIPTION,  "
            + " decode(a.currentstatus,'1','APPROVED','2','UNAPPROVED')currentstatus, d.longdescription AS DIVNAME,   "
            + " ( f.addressline1|| ' '|| f.addressline2|| ' '|| f.town|| '-'|| NVL(f.postalcode,'')) divaddress, f.addressphonenumber,f.emailaddress, "
            + " f.tinno,f.midno, fc.code factorycode, fc.longdescription AS factoryname, "
            + " (TRIM(nvl(fc.addressline1, ''))|| ''|| TRIM(nvl(fc.addressline2, ''))|| ''|| TRIM(nvl(fc.addressline3, ''))|| ' '|| fc.district|| ' '|| "
            + " st.longdescription||'-'||nvl(TRIM(fc.postalcode), '') ) factoryaddress, fc.statecode  factorystatecode,  fc.cstno   factorycstno, "
            + " TO_CHAR(fc.cstdate, 'dd/mm/yyyy') factorycstdate, ag.gstinnumber  factorygstno,  fc.addressphonenumber   factoryphoneno, fc.emailaddress factoryemailaddress,  "
            + " b.legalname1  AS vnname, o.customersuppliertype, nvl(case when a.ALTERNATIVEADDRESSCODE is null then (TRIM((nvl(b.addressline1, '')|| ' '|| nvl(b.addressline2, '')|| ' '|| "
            + " nvl(b.addressline3, '')|| ' '|| nvl(b.addressline4, '')|| ' '|| nvl(b.district, '')|| '-'|| nvl(TRIM(b.postalcode), '')|| ' Ph.No :'|| nvl(b.phonenumber, ''))))  "
            + " else (select TRIM((nvl(gg.addressline1, '')|| ' '|| nvl(gg.addressline2, '')|| ' '|| nvl(gg.addressline3, '')|| ' '||"
            + " nvl(gg.addressline4, '')|| ' '||  nvl(gg.district, '')|| '-'|| nvl(TRIM(gg.postalcode), '')|| ' Ph.No :'|| nvl(gg.ADDRESSPHONENUMBER, ''))) "
            + " from address gg where gg.uniqueid=a.ALTERNATIVEADDRESSUNIQUEID  and gg.code=a.ALTERNATIVEADDRESSCODE) end,'')vnaddress, "
            + "nvl(case when a.ALTERNATIVEADDRESSCODE is null then (SELECT gstinnumber FROM addressgst WHERE uniqueid = b.absuniqueid)  else "
            + "(select GSTINNUMBER from address gg, addressgst gs where gg.absuniqueid=gs.uniqueid and gg.uniqueid=a.ALTERNATIVEADDRESSUNIQUEID "
            + " and gg.code=a.ALTERNATIVEADDRESSCODE) end,'') vngstnumber,  "
            + " ts.longdescription termsofship, a.creationuser, pm.longdescription paymentdesc,  "
            + " (SELECT nvl(SUBSTR(xmlserialize(xmlagg(xmltext(CONCAT( ', ',NOTE))) as VARCHAR(1024)), 3),'') AS remarks FROM note where fatherid=a.absuniqueid)NOTEREMARKS,  "
            + " o.absuniqueid AS orderabsid, a.firstcarriertype,a.firstcarriercode, nvl(case when a.ALTERNATIVEADDRESSCODE is null then  "
            + " (SELECT statecode FROM addressgst WHERE uniqueid = b.absuniqueid) else (select gs.STATECODE from address gg, addressgst gs where gg.absuniqueid=gs.uniqueid  "
            + " and gg.uniqueid=a.ALTERNATIVEADDRESSUNIQUEID and gg.code=a.ALTERNATIVEADDRESSCODE) end,'') vnstatecode, nvl(case when a.ALTERNATIVEADDRESSCODE is null then  "
            + " (SELECT TRIM(longdescription) statename FROM addressgst ag, state st WHERE ag.statecode = st.code AND uniqueid = b.absuniqueid) else  "
            + " (select tt.LONGDESCRIPTION from address gg, addressgst gs, state tt where gg.absuniqueid=gs.uniqueid and gs.statecode=tt.code and gg.uniqueid=a.ALTERNATIVEADDRESSUNIQUEID  "
            + " and gg.code=a.ALTERNATIVEADDRESSCODE) end,'') vnstatename, nvl(case when a.ALTERNATIVEADDRESSCODE is null then rtrim(b.phonenumber)  "
            + " else (select gg.ADDRESSPHONENUMBER from address gg where gg.uniqueid=a.ALTERNATIVEADDRESSUNIQUEID and gg.code=a.ALTERNATIVEADDRESSCODE) end,'')vnphonenumber,  "
            + " nvl(case when a.ALTERNATIVEADDRESSCODE is null then nvl(rtrim(b.emailaddress), '') else (select gg.EMAILADDRESS from address gg where gg.uniqueid=a.ALTERNATIVEADDRESSUNIQUEID  "
            + " and gg.code=a.ALTERNATIVEADDRESSCODE) end,'') vnemailaddress,  b.absuniqueid   bp_absuniqueid,  (SELECT gstinnumber FROM addressgst WHERE uniqueid = b.absuniqueid) vngstnumber,   "
            + " (SELECT statecode FROM addressgst WHERE uniqueid = b.absuniqueid) vnstatecode,   (SELECT TRIM(longdescription) statename FROM addressgst ag, state st WHERE ag.statecode = st.code "
            + " AND uniqueid = b.absuniqueid) vnstatename,  b.faxnumber   AS fax,  TO_CHAR(a.orderdate, 'dd/MM/yyyy') orderdate, a.ordprncustomersuppliercode, a.alternativeaddresscode,"
            + "  a.deliverypointcode, a.externalreference,   a.internalreference, a.warehousecode,  a.currencycode, l.plantcode, (NVL(TRIM(pt.CODE),'')||' - '||NVL(TRIM(pt.LONGDESCRIPTION),''))POTYPE,"
            + "  a.absuniqueid,   a.alternativeaddressuniqueid, t.longdescription  deliveryterm, t.longdescription  AS delvterm, tm.longdescription AS shipmentterm, pm.code    AS paymentcode, "
            + " (select PH.LONGDESCRIPTION WAREHOUSENAME from physicalwarehouse PH where  PH.code=a.warehousecode),  "
            + " (select (NVL(TRIM(PH.ADDRESSLINE1),'')||' '||NVL(TRIM(PH.ADDRESSLINE2),'')||' '||NVL(TRIM(PH.ADDRESSLINE3),'')) WHADDRESS1 "
            + " from physicalwarehouse PH  WHERE PH.code=a.warehousecode), (select (TRIM(PH.DISTRICT)||'-'||PH.POSTALCODE)WHADDRESS2 from physicalwarehouse PH where PH.code=a.warehousecode),  "
            + " (select  PH.PHONENUMBER WHPHONENUMBER from physicalwarehouse PH where PH.code=a.warehousecode),   "
            + " (select EMAILADDRESS WHEMAILADDRESS from physicalwarehouse PH where PH.code=a.warehousecode),   "
            + " (SELECT A.GSTINNUMBER FROM FACTORY F, ADDRESSGST A WHERE F.ABSUNIQUEID = A.UNIQUEID AND F.CODE=lg.PLANTCODE)gstinnumber,  "
            + " (SELECT S.LONGDESCRIPTION FROM FACTORY F, ADDRESSGST A, STATE S WHERE F.ABSUNIQUEID = A.UNIQUEID AND A.STATECODE=S.CODE AND F.CODE=lg.PLANTCODE)statename,  "
            + " (SELECT A.STATECODE FROM FACTORY F, ADDRESSGST A WHERE F.ABSUNIQUEID = A.UNIQUEID AND F.CODE=lg.PLANTCODE)statecode  "
            + " from  purchaseorder a   "
            + " LEFT OUTER JOIN termsofshipping ts ON a.companycode = ts.companycode AND a.termsofshippingcode = ts.code  "
            + " LEFT OUTER JOIN termsofdelivery t  ON a.companycode = t.companycode AND a.termsofdeliverycode = t.code  LEFT OUTER JOIN purchaseorderie pi ON a.companycode = pi.companycode AND a.code = pi.code   "
            + " LEFT OUTER JOIN logicalwarehouse l ON a.companycode = l.companycode AND a.divisioncode = l.divisioncode AND a.warehousecode = l.code   "
            + " LEFT OUTER JOIN termsofshipping tm ON a.companycode = tm.companycode AND a.termsofshippingcode = tm.code   "
            + " LEFT OUTER JOIN paymentmethod pm ON a.companycode = pm.companycode  AND a.paymentmethodcode = pm.code   "
            + " LEFT OUTER JOIN purchaseordertemplate pt ON a.companycode = pt.companycode AND a.templatecode = pt.code, "
            + "  company c, firm f, division d, factory fc, addressgst ag, state st, logicalwarehouse lg, orderpartner o, businesspartner b where a.companycode = c.code  "
            + " AND a.companycode = f.companycode AND a.divisioncode = f.code AND a.companycode = d.companycode AND a.divisioncode = d.code AND a.warehousecode = lg.code "
            + " AND lg.plantcode = fc.code  AND fc.absuniqueid = ag.uniqueid AND fc.statecode = st.code AND a.companycode = o.customersuppliercompanycode AND a.ordertype = o.customersuppliertype   "
            + " AND a.ordprncustomersuppliercode = o.customersuppliercode AND o.orderbusinesspartnernumberid = b.numberid and b.COUNTRYCODE NOT IN ('IN','IND')  ";

        String parameter = null;

        for (String headercode : orderno) {
            if (parameter != null && parameter.length() > 0) {
                parameter += ",'" + headercode + "'";
            } else {
                parameter = " and a.code in ('" + headercode.toUpperCase() + "'";
            }
        }
        parameter += ")";
        qyery += parameter;
        try {
            stmt = conn.prepareStatement(qyery);
            result = stmt.executeQuery();
            while (result.next()) {
                PurchaseOrderImportBean bean = new PurchaseOrderImportBean();
                if (result.getString("netValueInword") != null && result.getString("netValueInword").length() > 0) {
                    String NumbertoWord = EnglishNumberToWords.callNumToWord(result.getString("netValueInword"), 0);
                    bean.setNetValueInword(NumbertoWord);
                }
                bean.setCountercode(result.getString("countercode"));
                bean.setCode(result.getString("code"));
                bean.setCurrencycode(result.getString("currencycode"));
                bean.setCreationuser(result.getString("creationuser"));
                bean.setInhousedate(result.getString("inhousedate"));
                bean.setAdvancepoamount(result.getBigDecimal("advancepoamount"));
                bean.setApprovedby(result.getString("approvedby"));
                bean.setDescription(result.getString("DESCRIPTION"));
                bean.setCurrentstatus(result.getString("currentstatus"));
                bean.setDivname(result.getString("DIVNAME"));
                bean.setDivaddress(result.getString("divaddress"));
                bean.setAddressphonenumber(result.getString("addressphonenumber"));
                bean.setEmailaddress(result.getString("emailaddress"));
                bean.setTinno(result.getString("tinno"));
                bean.setMidno(result.getString("midno"));
                bean.setSpecialInstruction(result.getString("specialInstruction"));
                bean.setFactorycode(result.getString("factorycode"));
                bean.setFactoryname(result.getString("factoryname"));
                bean.setFactoryaddress(result.getString("factoryaddress"));
                bean.setFactorystatecode(result.getString("factorystatecode"));
                bean.setFactorycstno(result.getString("factorycstno"));
                bean.setFactorycstdate(result.getString("factorycstdate"));
                bean.setFactorygstno(result.getString("factorygstno"));
                bean.setFactoryemailaddress(result.getString("factoryemailaddress"));
                bean.setFactoryphoneno(result.getString("factoryphoneno"));

                bean.setVnname(result.getString("vnname"));
                bean.setVnaddress(result.getString("vnaddress"));
                bean.setVngstnumber(result.getString("vngstnumber"));
                bean.setVnstatecode(result.getString("vnstatecode"));
                bean.setVnstatename(result.getString("vnstatename"));
                bean.setVnphonenumber(result.getString("vnphonenumber"));
                bean.setVnemailaddress(result.getString("vnemailaddress"));
                bean.setFax(result.getString("fax"));
                bean.setOrderdate(result.getString("orderdate"));
                bean.setOrdprncustomersuppliercode(result.getString("ordprncustomersuppliercode"));
                bean.setAlternativeaddresscode(result.getString("alternativeaddresscode"));
                bean.setDeliverypointcode(result.getString("deliverypointcode"));
                bean.setExternalreference(result.getString("externalreference"));
                bean.setInternalreference(result.getString("internalreference"));
                bean.setWarehousecode(result.getString("warehousecode"));
                bean.setPotype(result.getString("POTYPE"));
                bean.setDeliveryterm(result.getString("deliveryterm"));
                bean.setShipmentterm(result.getString("shipmentterm"));
                bean.setPaymentcode(result.getString("paymentcode"));

                bean.setPaymentdesc(result.getString("paymentdesc"));
                bean.setNoteremarks(result.getString("NOTEREMARKS"));
                bean.setWarehousename(result.getString("WAREHOUSENAME"));
                bean.setWhaddress1(result.getString("WHADDRESS1"));
                bean.setWhaddress2(result.getString("WHADDRESS2"));
                bean.setWhphonenumber(result.getString("WHPHONENUMBER"));
                bean.setWhemailaddress(result.getString("WHEMAILADDRESS"));
                bean.setGstinnumber(result.getString("gstinnumber"));
                bean.setStatecode(result.getString("statecode"));
                bean.setStatename(result.getString("statename"));

                List<PurchaseOrderLineBean> purchaseOrderLineBean = new ArrayList<PurchaseOrderLineBean>();
                stmt1 = conn.prepareStatement(
                    "SELECT (1/pl.ENTRYEXCHANGERATE)exchangerate, pl.PURCHASEORDERCOMPANYCODE,pl.PURCHASEORDERCOUNTERCODE,pl.PURCHASEORDERCODE, pl.projectcode, "
                        + " (nvl((select distinct SALESORDERCODE from salesorderline where PROJECTCODE = pl.projectcode fetch first row only),''))salesordercode, "
                        + " pl.ORDERLINE,    (NVL(TRIM(pl.subcode01),'')|| ' '|| NVL(TRIM(pl.subcode02),'')|| ' '|| NVL(TRIM(pl.subcode03),'')|| ' '|| NVL(TRIM(pl.subcode04),'')|| ' '||"
                        + " NVL(TRIM(pl.subcode05),'')|| ' '|| NVL(TRIM(pl.subcode06),'')|| ' '|| NVL(TRIM(pl.subcode07),'')|| ' '|| NVL(TRIM(pl.subcode08),'')|| ' '||"
                        + " NVL(TRIM(pl.subcode09),'')|| ' '|| NVL(TRIM(pl.subcode10),'')) ITEMCODE,pl.itemdescription AS ITEMDESC, ie.tariffcode HSNCODE, "
                        + " ie.GROSSVALUEWOHEADER,   pl.USERPRIMARYUOMCODE  UOM, nvl(pl.userprimaryquantity, 0) QTY, nvl(pl.price, 0) PRICE, nvl(pl.netvalue, 0) NETVALUE,  "
                        + " pl.absuniqueid, nvl((SELECT sum(value) FROM indtaxdetail WHERE itaxcode IN('IG1','IG2','IG3','IG4') AND absuniqueid = pl.absuniqueid), 0) IGSTTAXPERS,  "
                        + " nvl((SELECT sum(calculatedvaluertc) FROM indtaxdetail    WHERE itaxcode IN('IG1','IG2','IG3','IG4') AND absuniqueid = pl.absuniqueid), 0)IGSTVALUE,   "
                        + " nvl((SELECT sum(value) taxpers FROM indtaxdetail         WHERE itaxcode IN('SG1','SG2','SG3','SG4') AND absuniqueid = pl.absuniqueid), 0) SGSTTAXPERS,   "
                        + " nvl((SELECT sum(calculatedvaluertc) FROM indtaxdetail    WHERE itaxcode IN('SG1','SG2','SG3','SG4') AND absuniqueid = pl.absuniqueid), 0) SGSTVALUE,   "
                        + " nvl((SELECT sum(value) taxpers FROM indtaxdetail         WHERE itaxcode IN('CG1','CG2','CG3','CG4') AND absuniqueid = pl.absuniqueid), 0) CGSTPERS,   "
                        + " nvl((SELECT sum(calculatedvaluertc) FROM indtaxdetail    WHERE itaxcode IN('CG1','CG2','CG3','CG4') AND absuniqueid = pl.absuniqueid), 0) CGSTVALUE,   "
                        + " (select sum(CALCULATEDVALUERTC) from indtaxdetail where absuniqueid=pl.absuniqueid and ITAXCODE='F01')FREIGHT,   "
                        + " (select sum(CALCULATEDVALUERTC) from indtaxdetail where absuniqueid=pl.absuniqueid and ITAXCODE in('DST','DIS'))DISCOUNT "
                        + " FROM   purchaseorderline pl, purchaseorderlineie ie WHERE pl.purchaseordercompanycode = ie.purchaseordercompanycode "
                        + " AND pl.purchaseordercountercode = ie.purchaseordercountercode   AND pl.purchaseordercode = ie.purchaseordercode AND pl.orderline = ie.orderline "
                        + " AND pl.PURCHASEORDERCOUNTERCODE = ? AND pl.PURCHASEORDERCODE = ? ");
                stmt1.setString(1, result.getString("countercode"));
                stmt1.setString(2, result.getString("code"));
                result1 = stmt1.executeQuery();
                while (result1.next()) {
                    PurchaseOrderLineBean lineBean = new PurchaseOrderLineBean();
                    lineBean.setPurchaseordercompanycode(result1.getString("PURCHASEORDERCOMPANYCODE"));
                    lineBean.setPurchaseordercountercode(result1.getString("PURCHASEORDERCOUNTERCODE"));
                    lineBean.setPurchaseordercode(result1.getString("PURCHASEORDERCODE"));
                    lineBean.setExchangerate(result1.getBigDecimal("exchangerate"));
                    lineBean.setSalesordercode(result1.getString("salesordercode"));
                    lineBean.setProjectcode(result1.getString("projectcode"));
                    lineBean.setItemcode(result1.getString("ITEMCODE"));
                    lineBean.setItemdesc(result1.getString("ITEMDESC"));
                    lineBean.setTariffcode(result1.getDouble("HSNCODE"));
                    lineBean.setGrossvaluewoheader(result1.getBigDecimal("GROSSVALUEWOHEADER"));
                    lineBean.setUom(result1.getString("UOM"));
                    lineBean.setQty(result1.getBigDecimal("QTY"));
                    lineBean.setPrice(result1.getBigDecimal("PRICE"));
                    lineBean.setNetvalue(result1.getBigDecimal("NETVALUE"));
                    lineBean.setIgsttaxpers(result1.getBigDecimal("IGSTTAXPERS"));
                    lineBean.setIgstvalue(result1.getBigDecimal("IGSTVALUE"));
                    lineBean.setSgsttaxpers(result1.getBigDecimal("SGSTTAXPERS"));
                    lineBean.setSgstvalue(result1.getBigDecimal("SGSTVALUE"));
                    lineBean.setCgstpers(result1.getBigDecimal("CGSTPERS"));
                    lineBean.setCgstvalue(result1.getBigDecimal("CGSTVALUE"));
                    lineBean.setFreight(result1.getBigDecimal("FREIGHT"));
                    lineBean.setDiscount(result1.getBigDecimal("DISCOUNT"));
                    purchaseOrderLineBean.add(lineBean);
                }
                if (result1 != null) {
                    result1.close();
                }
                result1 = null;

                if (stmt1 != null) {
                    stmt1.close();
                }
                stmt1 = null;
                bean.setPurchaseOrderLineBean(purchaseOrderLineBean);
                purchaseOrderImportBean.add(bean);
            }
        } catch (Exception e) {
            System.out.println("PurchaseOrdeDaoImpl.class (M: fetchPurchaseOrder; Exception: " + e.getMessage() + ")");
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            result = null;

            if (stmt != null) {
                stmt.close();
            }
            stmt = null;
        }
        return purchaseOrderImportBean;
    }

    public static List<PurchaseOrderJobwBean> getAllPoJwById(String[] orderno, Connection conn) throws Exception {
        List<PurchaseOrderJobwBean> purchaseOrderJobwBean = new ArrayList<PurchaseOrderJobwBean>();
        PreparedStatement stmt = null;
        ResultSet result = null;
        String NumbertoWord = "";
        //nvl((select nvl(USERPRIMARYQUANTITY,0) from extoplinereservation where EXTOPLINECODE=ex.code AND extoplineorderline=ex.orderline ),0)greigeqty, (select (nvl(trim(SUBCODE01),'')||' '||nvl(trim(SUBCODE02),'')||' '||nvl(trim(SUBCODE03),'')||' '|| nvl(trim(SUBCODE04),'')||' '|| nvl(trim(SUBCODE05),'')||' '||nvl(trim(SUBCODE06),'')||' '||nvl(trim(SUBCODE07),'')||' '||nvl(trim(SUBCODE08),''))  from extoplinereservation where EXTOPLINECODE=ex.code AND extoplineorderline=ex.orderline )greigecode,

        String qyery = "select lineremarks, orderline, buyercodes, STEPPRODUCTIONDEMANDCODE,ENTRYWAREHOUSECODE,ISSUEWAREHOUSECODE, factoryname, FULLITEMDESC, ITEMDESCNEW, PAYMENTTERM, SALESORDERCODE, OPERATIONNAME,  CREATIONUSER, APPROVEDSTATUS, APPROVEDBY, ITEMCODE, ITEMCODESHORTDESC, HSNCODE, "
            + " factoryaddress, FACTORYSTATECODE, FACTORYSTATENAME, FACTORYCSTNO, FACTORYCSTDATE, FACTORYGSTNO, FACTORYPHONENO, FACTORYEMAILADDRESS, COUNTERCODE, CODE, ORDPRNCUSTOMERSUPPLIERCODE, "
            + " BILLTOPARTYNAME, BILLTOADDRESS1, BILLTOADDRESS2, billtophoneno, BILLTOEMAIL,  billtogstinnumber, billtostatecode, BILLTOSTATENAME, PROVISIONALGSTINNUMBER, stepno,  "
            + " PRODUCTIONORDERCODE, ORDERDATE, DLVORDPRNCUSTOMERSUPPLIERCODE, ITEMTYPEAFICODE, BOMNUMBERID, ITEMDESCRIPTION, nvl(sum(USERPRIMARYQUANTITY),0)USERPRIMARYQUANTITY, "
            + "USERPRIMARYUOMCODE,  nvl(sum(USEDUSERPRIMARYQUANTITY),0)USEDUSERPRIMARYQUANTITY,  nvl(sum(NETVALUE),0)NETVALUE, nvl(CGSTAXPERS,0)CGSTAXPERS, nvl(sum(CGSTAXAMOUNT),0)CGSTAXAMOUNT, "
            + "nvl(SGSTAXPERS,0)SGSTAXPERS, nvl(sum(SGSTAXAMOUNT),0)SGSTAXAMOUNT,  nvl(IGSTAXPERS,0)IGSTAXPERS, nvl(sum(IGSTAXAMOUNT),0)IGSTAXAMOUNT, "
            + "nvl(sum(BASICVALUEIE),0)BASICVALUEIE, nvl(sum(GROSSVALUEWOHEADERIE),0)GROSSVALUEWOHEADERIE, nvl(sum(GROSSVALUEEXTIE),0)GROSSVALUEEXTIE, PROJECTCODE, "
            + "COSTCENTERCODE, QUALITYCODE, TERMSOFDELIVERYCODE, TERMSOFSHIPPINGCODE, REQUIREDDUEDATE, DESCRIPTIONS, CURRENCYCODE, nvl(PRICE,0)PRICE,  "
            + "TAXDISCOUNT, NETVALUEINCLUDINGTAX, WORKCENTERCODE, OPERATIONCODE,  LOSSINCREASE1,NETVALUEINWORD from "
            + " (SELECT (SELECT MAX(VALUESTRING) FROM ADSTORAGE WHERE NAMEENTITYNAME='ExtOpLine' AND NAMENAME='Remarks' AND UNIQUEID IN (SELECT ABSUNIQUEID FROM EXTOPLINE WHERE CODE=EX.CODE))lineremarks,"
            + " (select vi.LEGALNAME1 from salesorder s, vieworderpartner vi where s.ORDPRNCUSTOMERSUPPLIERCODE = vi.CUSTOMERSUPPLIERCODE and s.projectcode=ex.projectcode "
            + " fetch first row only)buyercodes, "
            + " ex.STEPPRODUCTIONDEMANDCODE, pd9.ENTRYWAREHOUSECODE, nvl(TRIM(kd.SUMMARIZEDDESCRIPTION),'')fullitemdesc,  "
            + "nvl(trim(kd.summarizeddescription),'')itemdescnew,  nvl(pm.LONGDESCRIPTION,'')paymentterm,    (select (nvl(trim(CODE),'')) "
            + "from salesorder where projectcode=ex.projectcode fetch first row only)salesorderCode,  (select op.LONGDESCRIPTION from extopline ex1, "
            + "productiondemandstep pd, operation op where ex1.STEPPRODEMANDCOUNTERCODE = pd.PRODUCTIONDEMANDCOUNTERCODE and ex1.STEPPRODUCTIONDEMANDCODE = pd.PRODUCTIONDEMANDCODE "
            + "and ex1.STEPSTEPNUMBER = pd.STEPNUMBER and pd.OPERATIONCODE = op.code  and ex1.code=ex.code fetch first row only)operationname,  NVL((select NVL(sum(GROSSVALUEWOHEADER),0) "
            + "from EXTOPLINEIE where code=ex.code),0)netValueInword, (select distinct nvl(trim(FULLNAME),'') from ABSUserDef where userid=ex.CREATIONUSER)creationuser,  "
            + " nvl((select decode(APPROVERSTATUS,'A','APPROVED','N','NOT-APPROVED') from volextopapprovalhistory where code=ex.code),'NOT-APPROVED')approvedstatus,  "
            + " (select nvl(FIRSTAPPROVERIDUSERID,'') from volextopapprovalhistory where code=ex.code)approvedby,  ((nvl(trim(ENTRYSUBCODE01),'')||' '||nvl(trim(ENTRYSUBCODE02),'')||' '||"
            + "nvl(trim(ENTRYSUBCODE03),'')||' '||nvl(trim(ENTRYSUBCODE04),'')||' '||nvl(trim(ENTRYSUBCODE05),'')||' '||nvl(trim(ENTRYSUBCODE06),'')||' '||nvl(trim(ENTRYSUBCODE07),'')||'-'||"
            + "nvl(trim(ENTRYSUBCODE08),'')))itemCode, ((nvl(trim(ENTRYSUBCODE03),'')||' '|| nvl(trim(ENTRYSUBCODE04),'')||' '||nvl(trim(ENTRYSUBCODE05),'')||' '||nvl(trim(ENTRYSUBCODE06),'')||' '||"
            + "nvl(trim(ENTRYSUBCODE07),'')||'-'||nvl(trim(ENTRYSUBCODE08),'')))itemcodeShortDesc,  nvl((select TARIFFCODE from extoplineie where CODE=ex.code and ORDERLINE=ex.orderline),'')hsncode,  "
            + " NVL(fc.longdescription,'') factoryname, nvl((TRIM(nvl(fc.addressline1, ''))||' '|| TRIM(nvl(fc.addressline2, ''))|| ''||TRIM(nvl(fc.addressline3, ''))|| ' '|| nvl(fc.district,'')|| ' '|| "
            + "nvl(st.longdescription,'')|| ' - '|| nvl(TRIM(fc.postalcode),'') ),'') factoryaddress, nvl(fc.statecode,'') factorystatecode, "
            + " (nvl((select LONGDESCRIPTION from state where code=fc.statecode),'')||'-'||fc.statecode)factorystatename,  nvl(fc.cstno,'') factorycstno,  "
            + " TO_CHAR(fc.cstdate, 'dd/mm/yyyy') factorycstdate,  nvl(ag2.gstinnumber,'') factorygstno,  nvl(fc.addressphonenumber,'') factoryphoneno,  nvl(fc.emailaddress,'') factoryemailaddress,  "
            + " ex.countercode, ex.code, ex.orderline, ex.ordprncustomersuppliercode,  nvl(TRIM(b.legalname1), '') billtopartyname,   (nvl(TRIM(b.addressline1), '')|| ' '||"
            + " nvl(TRIM(b.addressline2), '')|| ' ' || nvl(TRIM(b.addressline3), '') ) billtoaddress1, (nvl(TRIM(b.district), '')|| '-'|| nvl(TRIM(b.postalcode), '') ) billtoaddress2, "
            + " nvl(b.addressphonenumber,'')  billtophoneno,  TRIM(b.emailaddress) billtoemail,   nvl(ag.gstinnumber,'') billtogstinnumber,  nvl(ag.statecode,'') billtostatecode,  "
            + "(nvl((select LONGDESCRIPTION from state where code=ag.statecode),'')||'-'||ag.statecode)billtostatename, ag.provisionalgstinnumber, nvl(ex.stepproductiondemandcode,'')stepdemandcode, "
            + " nvl(ex.stepstepnumber,'') stepno, ex.productionordercode,   TO_CHAR(ex.orderdate, 'dd/mm/yyyy') orderdate, ex.dlvordprncustomersuppliercode, ex.itemtypeaficode, ex.bomnumberid, "
            + " ex.itemdescription, ex.userprimaryquantity, ex.userprimaryuomcode, ex.useduserprimaryquantity, ex.projectcode, ex.costcentercode, ex.issuewarehousecode, ex.qualitycode, "
            + " ex.termsofdeliverycode, ex.termsofshippingcode, to_char(ex.requiredduedate,'dd/mm/yyyy')requiredduedate, nvl(TRIM(fk.summarizeddescription),'') descriptions, ex.currencycode, "
            + "ex.price, ex.netvalue,   (SELECT SUM(VALUE) FROM INDTAXDETAIL WHERE ITAXCODE IN('CG1','CG2','CG3','CG4') AND ABSUNIQUEID=EX.ABSUNIQUEID)CGSTAXPERS,   "
            + "(SELECT SUM(CALCULATEDVALUERTC) FROM INDTAXDETAIL WHERE ITAXCODE IN('CG1','CG2','CG3','CG4') AND ABSUNIQUEID=EX.ABSUNIQUEID)CGSTAXAMOUNT,   "
            + "(SELECT SUM(VALUE) FROM INDTAXDETAIL WHERE ITAXCODE IN('SG1','SG2','SG3','SG4') AND ABSUNIQUEID=EX.ABSUNIQUEID)SGSTAXPERS,  "
            + " (SELECT SUM(CALCULATEDVALUERTC) FROM INDTAXDETAIL WHERE ITAXCODE IN('SG1','SG2','SG3','SG4') AND ABSUNIQUEID=EX.ABSUNIQUEID)SGSTAXAMOUNT,   "
            + "(SELECT SUM(VALUE) FROM INDTAXDETAIL WHERE ITAXCODE IN('IG1','IG2','IG3','IG4') AND ABSUNIQUEID=EX.ABSUNIQUEID)IGSTAXPERS,  "
            + " (SELECT SUM(CALCULATEDVALUERTC) FROM INDTAXDETAIL WHERE ITAXCODE IN('IG1','IG2','IG3','IG4') AND ABSUNIQUEID=EX.ABSUNIQUEID)IGSTAXAMOUNT,   "
            + "(SELECT SUM(CALCULATEDVALUERTC) FROM INDTAXDETAIL WHERE ITAXCODE IN('DIS','DST') AND ABSUNIQUEID=EX.ABSUNIQUEID)TAXDISCOUNT, ex.netvalueincludingtax,   "
            + " ex.absuniqueid, ds.workcentercode, ds.operationcode, ds.lossincrease1 , nvl(EI.BASICVALUE,0) BASICVALUEIE,   nvl(EI.GROSSVALUEWOHEADER,'') GROSSVALUEWOHEADERIE, "
            + "nvl(EI.GROSSVALUEEXT,'') GROSSVALUEEXTIE   FROM extopline ex left outer join productiondemand pd9 on ex.STEPPRODUCTIONDEMANDCODE = pd9.code "
            + " LEFT OUTER JOIN paymentmethod pm ON ex.companycode = pm.companycode  AND ex.PAYMENTMETHODCODE = pm.code "
            + " LEFT OUTER JOIN fullitemkeydecoder kd on  nvl(trim(ex.ENTRYITEMTYPEAFICODE),'')= nvl(trim(kd.ITEMTYPECODE),'') and nvl(trim(ex.ENTRYSUBCODE01),'') = nvl(trim(kd.SUBCODE01),'') and nvl(trim(ex.ENTRYSUBCODE02),'') = nvl(trim(kd.SUBCODE02),'')   "
            + " and nvl(trim(ex.ENTRYSUBCODE03),'') = nvl(trim(kd.SUBCODE03),'') and nvl(trim(ex.ENTRYSUBCODE04),'') = nvl(trim(kd.SUBCODE04),'') "
            + " and nvl(trim(ex.ENTRYSUBCODE05),'') = nvl(trim(kd.SUBCODE05),'') and nvl(trim(ex.ENTRYSUBCODE06),'') = nvl(trim(kd.SUBCODE06),'')  "
            + "and nvl(trim(ex.ENTRYSUBCODE07),'') = nvl(trim(kd.SUBCODE07),'') and nvl(trim(ex.ENTRYSUBCODE08),'') = nvl(trim(kd.SUBCODE08),'')  "
            + "and nvl(trim(ex.ENTRYSUBCODE09),'') = nvl(trim(kd.SUBCODE09),'') and nvl(trim(ex.ENTRYSUBCODE10),'') = nvl(trim(kd.SUBCODE10),''),  EXTOPLINEIE ei, "
            + "orderpartner o, businesspartner b    LEFT OUTER JOIN addressgst ag ON b.absuniqueid = ag.uniqueid, logicalwarehouse lw, factory fc, addressgst ag2, "
            + "state st, fullitemkeydecoder fk,   productiondemandstep ds  WHERE ex.COMPANYCODE=ei.COMPANYCODE and ex.COUNTERCODE=ei.COUNTERCODE and ex.CODE=ei.CODE   "
            + "and ex.ORDERLINE = ei.ORDERLINE AND ex.ordprncustomersuppliercode = o.customersuppliercode AND o.orderbusinesspartnernumberid = b.numberid   AND pd9.entrywarehousecode = lw.code  "
            + "AND lw.plantcode = fc.code AND fc.absuniqueid = ag2.uniqueid AND fc.statecode = st.code   AND ex.fullitemidentifier = fk.identifier  "
            + "AND ex.stepproductiondemandcode = ds.productiondemandcode AND EX.STEPSTEPNUMBER = DS.STEPNUMBER";

        String parameter = null;

        for (String headercode : orderno) {
            if (parameter != null && parameter.length() > 0) {
                parameter += ",'" + headercode + "'";
            } else {
                parameter = " and ex.code in ('" + headercode.toUpperCase() + "'";
            }
        }
        parameter += ") ";

        qyery += parameter + " ) group by lineremarks, orderline, buyercodes, factoryname, FULLITEMDESC, ITEMDESCNEW, PAYMENTTERM, SALESORDERCODE, OPERATIONNAME,  CREATIONUSER, APPROVEDSTATUS,  APPROVEDBY, "
            + " ITEMCODE, ITEMCODESHORTDESC, HSNCODE, factoryaddress, FACTORYSTATECODE, FACTORYSTATENAME, FACTORYCSTNO,  FACTORYCSTDATE, FACTORYGSTNO, "
            + " FACTORYPHONENO, FACTORYEMAILADDRESS, COUNTERCODE, CODE, ORDPRNCUSTOMERSUPPLIERCODE,  BILLTOPARTYNAME, BILLTOADDRESS1, BILLTOADDRESS2, "
            + " billtophoneno, BILLTOEMAIL,  billtogstinnumber, billtostatecode, BILLTOSTATENAME, PROVISIONALGSTINNUMBER, stepno,  PRODUCTIONORDERCODE, "
            + " ORDERDATE, DLVORDPRNCUSTOMERSUPPLIERCODE, ITEMTYPEAFICODE, BOMNUMBERID, ITEMDESCRIPTION, USERPRIMARYUOMCODE,  PROJECTCODE, COSTCENTERCODE, "
            + " ISSUEWAREHOUSECODE, QUALITYCODE, TERMSOFDELIVERYCODE, TERMSOFSHIPPINGCODE, REQUIREDDUEDATE, DESCRIPTIONS, CURRENCYCODE,PRICE,  TAXDISCOUNT,"
            + " NETVALUEINCLUDINGTAX, WORKCENTERCODE, OPERATIONCODE,  LOSSINCREASE1,NETVALUEINWORD, CGSTAXPERS, SGSTAXPERS, IGSTAXPERS,ENTRYWAREHOUSECODE, STEPPRODUCTIONDEMANDCODE order by FULLITEMDESC ";

        try {
            stmt = conn.prepareStatement(qyery);
            result = stmt.executeQuery();
            while (result.next()) {
                PurchaseOrderJobwBean bean = new PurchaseOrderJobwBean();
                NumbertoWord = EnglishNumberToWords.callNumToWord(result.getString("netValueInword"), 0);
                if (NumbertoWord != null && NumbertoWord != "" && NumbertoWord.length() > 0) {
                    bean.setGrossValueInword(NumbertoWord);
                } else {
                    bean.setGrossValueInword(NumbertoWord);
                }
                bean.setFactoryname(result.getString("factoryname"));
                bean.setHsncode(result.getString("hsncode"));
                bean.setBuyercodes(result.getString("buyercodes"));
                bean.setLineremarks(result.getString("lineremarks"));
                bean.setFactoryaddress(result.getString("factoryaddress"));
                bean.setFactorystatecode(result.getString("factorystatecode"));
                bean.setFactorystatename(result.getString("factorystatename"));
                bean.setFactorycstno(result.getString("factorycstno"));
                bean.setFactorycstdate(result.getString("factorycstdate"));
                bean.setFactorygstno(result.getString("factorygstno"));
                bean.setFactoryphoneno(result.getString("factoryphoneno"));
                bean.setFactoryemailaddress(result.getString("factoryemailaddress"));
                bean.setCountercode(result.getString("countercode"));
                bean.setCode(result.getString("code"));
                bean.setOrderline(result.getString("orderline"));
                bean.setSalesorderCode(result.getString("salesorderCode"));
                bean.setItemcodeShortDesc(result.getString("itemcodeShortDesc"));
                bean.setOrdprncustomersuppliercode(result.getString("ordprncustomersuppliercode"));
                bean.setBilltopartyname(result.getString("billtopartyname"));
                bean.setBilltoaddress1(result.getString("billtoaddress1"));
                bean.setBilltoaddress2(result.getString("billtoaddress2"));
                bean.setBilltophoneno(result.getString("billtophoneno"));
                bean.setBilltoemail(result.getString("billtoemail"));
                bean.setBilltogstinnumber(result.getString("billtogstinnumber"));
                bean.setBilltostatecode(result.getString("billtostatecode"));
                bean.setBilltostatename(result.getString("billtostatename"));
                bean.setProvisionalgstinnumber(result.getString("provisionalgstinnumber"));
                //bean.setStepdemandcode(result.getString("stepdemandcode"));
                bean.setProductionordercode(result.getString("productionordercode"));
                bean.setOrderdate(result.getString("orderdate"));
                bean.setItemtypeaficode(result.getString("itemtypeaficode"));
                bean.setItemdescription(result.getString("fullitemdesc"));
                bean.setUserprimaryquantity(result.getBigDecimal("userprimaryquantity"));
                bean.setUserprimaryuomcode(result.getString("userprimaryuomcode"));
                bean.setUseduserprimaryquantity(result.getBigDecimal("useduserprimaryquantity"));
                bean.setProjectcode(result.getString("projectcode"));
                bean.setCostcentercode(result.getString("costcentercode"));
                bean.setIssuewarehousecode(result.getString("issuewarehousecode"));
                bean.setTermsofdeliverycode(result.getString("termsofdeliverycode"));
                bean.setTermsofshippingcode(result.getString("paymentterm"));
                bean.setRequiredduedate(result.getString("requiredduedate"));
                bean.setDescriptions(result.getString("descriptions"));
                bean.setCurrencycode(result.getString("currencycode"));
                bean.setPrice(result.getBigDecimal("price"));
                bean.setNetvalue(result.getBigDecimal("BASICVALUEIE"));
                bean.setCgstaxpers(result.getBigDecimal("CGSTAXPERS"));
                bean.setCgstaxamount(result.getBigDecimal("CGSTAXAMOUNT"));
                bean.setSgstaxpers(result.getBigDecimal("SGSTAXPERS"));
                bean.setSgstaxamount(result.getBigDecimal("SGSTAXAMOUNT"));
                bean.setIgstaxpers(result.getBigDecimal("IGSTAXPERS"));
                bean.setIgstaxamount(result.getBigDecimal("IGSTAXAMOUNT"));
                bean.setTaxdiscount(result.getBigDecimal("TAXDISCOUNT"));
                bean.setNetvalueincludingtax(result.getBigDecimal("GROSSVALUEWOHEADERIE"));
                bean.setWorkcentercode(result.getString("workcentercode"));
                bean.setOperationcode(result.getString("operationcode"));
                bean.setLossincrease1(result.getBigDecimal("lossincrease1"));
                bean.setCreationuser(result.getString("creationuser"));
                bean.setApprovedstatus(result.getString("approvedstatus"));
                bean.setApprovedby(result.getString("approvedby"));
                //bean.setBuyername(result.getString("countercode"));
                bean.setGreigecode(result.getString("descriptions"));
                bean.setOperationname(result.getString("operationname"));
                bean.setGreigeqty(result.getBigDecimal("price"));
                purchaseOrderJobwBean.add(bean);
            }
        } catch (Exception e) {
            System.out.println("JobworkOrderDaoImpl.class (M: JobworkOrderDaoImpl; Exception: " + e.getMessage() + ")");
        } finally {
            if (result != null) {
                result.close();
            }
            result = null;

            if (stmt != null) {
                stmt.close();
            }
            stmt = null;
        }
        return purchaseOrderJobwBean;
    }

    private DataSource db2DataSource() {
        SingleConnectionDataSource dataSource = new SingleConnectionDataSource();
        dataSource.setDriverClassName(env.getProperty("db2.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("db2.datasource.url"));
        dataSource.setUsername(env.getProperty("db2.datasource.username"));
        dataSource.setPassword(env.getProperty("db2.datasource.password"));
        dataSource.setAutoCommit(true);
        return dataSource;
    }
}
