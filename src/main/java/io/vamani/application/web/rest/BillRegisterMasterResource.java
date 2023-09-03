package io.vamani.application.web.rest;

import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.db2.domain.Finfinancialyear;
import io.vamani.application.db2.repository.FinfinancialyearRepository;
import io.vamani.application.domain.BillRegister;
import io.vamani.application.domain.BillRegisterDetails;
import io.vamani.application.domain.BillRegisterMaster;
import io.vamani.application.model.BillRegisterDetailsBean;
import io.vamani.application.model.BillRegisterMasterBean;
import io.vamani.application.model.BillRegisterSearch;
import io.vamani.application.repository.BillRegisterDetailsRepository;
import io.vamani.application.repository.BillRegisterMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.util.DateUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
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
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * REST controller for managing {@link BillRegisterMaster}.
 */
@RestController
@RequestMapping("/api")
public class BillRegisterMasterResource {

    private final Logger log = LoggerFactory.getLogger(BillRegisterMasterResource.class);

    private static final String ENTITY_NAME = "billRegisterMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    private BillRegisterDetailsRepository billRegisterDetailsRepository;
    @Autowired
    private final BillRegisterMasterRepository billRegisterMasterRepository;

    @Autowired
    private FinfinancialyearRepository finfinancialyearRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

    public BillRegisterMasterResource(BillRegisterMasterRepository billRegisterMasterRepository, BillRegisterDetailsRepository billRegisterDetailsRepository) {
        this.billRegisterMasterRepository = billRegisterMasterRepository;
        this.billRegisterDetailsRepository = billRegisterDetailsRepository;
    }

    /**
     * {@code POST  /bill-register-masters} : Create a new billRegisterMaster.
     *
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new billRegisterMaster, or with status {@code 400 (Bad Request)} if the billRegisterMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bill-register-masters")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BillRegisterMaster> createBillRegisterMaster(@Valid @RequestBody BillRegisterMasterBean billRegisterMasterBean) throws URISyntaxException {
        log.debug("REST request to save BillRegisterMaster : {}", billRegisterMasterBean);
        if (billRegisterMasterBean.getId() != null) {
            throw new BadRequestAlertException("A new billRegisterMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Finfinancialyear finfinancialyear = finfinancialyearRepository.findByDate(DateUtils.asDate(billRegisterMasterBean.getBilldate()));
        if(finfinancialyear != null) {
            List<BillRegisterMaster> billRegisterMasters = billRegisterMasterRepository.findAllByBillNumberAndSupplierAndBilldate(billRegisterMasterBean.getBillnumber(), billRegisterMasterBean.getCustomersuppliercode(), DateUtils.asLocalDate(finfinancialyear.getFromdate()), DateUtils.asLocalDate(finfinancialyear.getTodate()));
            if(billRegisterMasters != null && billRegisterMasters.size()>0) {
                throw new BadRequestAlertException("Bill Number Already exist", ENTITY_NAME, "400");
            }
        } else {
            throw new BadRequestAlertException("In-valid bill date", ENTITY_NAME, "400");
        }
        BillRegisterMaster billRegisterMaster = new BillRegisterMaster();
        BeanUtils.copyProperties(billRegisterMasterBean, billRegisterMaster);
        billRegisterMaster.setCreatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
        billRegisterMaster.setCreateddate(Instant.now());
        BillRegisterMaster result = billRegisterMasterRepository.save(billRegisterMaster);
        if (result != null && billRegisterMasterBean.getBillRegisterDetailsBeans() != null && billRegisterMasterBean.getBillRegisterDetailsBeans().size() > 0) {
            for (BillRegisterDetailsBean billRegisterDetailsBean : billRegisterMasterBean.getBillRegisterDetailsBeans()) {
                if (billRegisterDetailsBean.getCode() != null && billRegisterDetailsBean.getCode().length() > 0) {
                    BillRegisterDetails billRegisterDetails = new BillRegisterDetails();
                    BeanUtils.copyProperties(billRegisterDetailsBean, billRegisterDetails);
                    billRegisterDetails.setSubmitdate(billRegisterMaster.getSubmitDate());
                    billRegisterDetails.setReceiveDate(billRegisterMaster.getReceiveDate());
                    billRegisterDetails.setBillRegisterMaster(result);
                    billRegisterDetailsRepository.save(billRegisterDetails);
                }
            }
        }
        return ResponseEntity.created(new URI("/api/bill-register-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bill-register-masters} : Updates an existing billRegisterMaster.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated billRegisterMaster,
     * or with status {@code 400 (Bad Request)} if the billRegisterMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the billRegisterMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bill-register-masters/{id}")
    public ResponseEntity<BillRegisterMaster> updateBillRegisterMaster(@PathVariable(value = "id", required = false) final Long id,
                                                                       @Valid @RequestBody BillRegisterMasterBean billRegisterMasterBean) throws URISyntaxException {
        log.debug("REST request to update BillRegisterMaster : {}", billRegisterMasterBean);
        if (billRegisterMasterBean.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, billRegisterMasterBean.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }
        if (!billRegisterMasterRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        Finfinancialyear finfinancialyear = finfinancialyearRepository.findByDate(DateUtils.asDate(billRegisterMasterBean.getBilldate()));
        if(finfinancialyear != null) {
            List<BillRegisterMaster> billRegisterMasters = billRegisterMasterRepository.findAllByBillNumberAndSupplierAndBilldateAndNotId(billRegisterMasterBean.getBillnumber(), billRegisterMasterBean.getCustomersuppliercode(), DateUtils.asLocalDate(finfinancialyear.getFromdate()), DateUtils.asLocalDate(finfinancialyear.getTodate()), billRegisterMasterBean.getId());
            if(billRegisterMasters != null && billRegisterMasters.size()>0) {
                throw new BadRequestAlertException("Bill Number Already exist", ENTITY_NAME, "400");
            }
        } else {
            throw new BadRequestAlertException("In-valid bill date", ENTITY_NAME, "400");
        }
        BillRegisterMaster billRegisterMaster = new BillRegisterMaster();
        BeanUtils.copyProperties(billRegisterMasterBean, billRegisterMaster);
        billRegisterMaster.setUpdatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
        billRegisterMaster.setUpdateddate(Instant.now());
        BillRegisterMaster result = billRegisterMasterRepository.save(billRegisterMaster);
        if (result != null && billRegisterMasterBean.getBillRegisterDetailsBeans() != null && billRegisterMasterBean.getBillRegisterDetailsBeans().size() > 0) {
            for (BillRegisterDetailsBean billRegisterDetailsBean : billRegisterMasterBean.getBillRegisterDetailsBeans()) {
                if (billRegisterDetailsBean.getCode() != null && billRegisterDetailsBean.getCode().length() > 0) {
                    BillRegisterDetails billRegisterDetails = new BillRegisterDetails();
                    BeanUtils.copyProperties(billRegisterDetailsBean, billRegisterDetails);
                    billRegisterDetails.setSubmitdate(billRegisterMaster.getSubmitDate());
                    billRegisterDetails.setReceiveDate(billRegisterMaster.getReceiveDate());
                    billRegisterDetails.setBillRegisterMaster(result);
                    billRegisterDetailsRepository.save(billRegisterDetails);
                }
            }
        }
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, billRegisterMaster.getId().toString()))
            .body(result);
    }

    @PostMapping("/bill-register-masters-received")
    public ResponseEntity<List<BillRegisterMasterBean>> updateBillRegisterMasters(@Valid @RequestBody List<BillRegisterMasterBean> billRegisterMasterBeans) throws URISyntaxException {
        log.debug("REST request to save BillRegisterMaster : {}", billRegisterMasterBeans);
        List<BillRegisterMasterBean> registerMasterBeans = new ArrayList<>();
        for(BillRegisterMasterBean billRegisterMasterBean : billRegisterMasterBeans) {
            if (billRegisterMasterBean.getReceived() != null && billRegisterMasterBean.getReceived().booleanValue() == true && billRegisterMasterBean.getReceiveDate() == null) {
                BillRegisterMaster registerMaster = billRegisterMasterRepository.findById(billRegisterMasterBean.getId()).orElse(null);
                registerMaster.setReceiveDate(LocalDate.now());
                BillRegisterMaster result = billRegisterMasterRepository.save(registerMaster);
                if (result != null) {
                    List<BillRegisterDetails> billRegisterDetails = billRegisterDetailsRepository.findAllByBillRegisterMaster(result.getId());
                    for (BillRegisterDetails billRegisterDetail : billRegisterDetails) {
                        billRegisterDetail.setReceiveDate(result.getReceiveDate());
                        billRegisterDetailsRepository.save(billRegisterDetail);
                    }
                    BillRegisterMasterBean registerMasterBean = new BillRegisterMasterBean();
                    BeanUtils.copyProperties(result, registerMasterBean);
                    if (result.getReceiveDate() != null) {
                        registerMasterBean.setReceived(true);
                    } else {
                        registerMasterBean.setReceived(false);
                    }

                    if (result.getSubmitDate() != null) {
                        registerMasterBean.setSubmitted(true);
                    } else {
                        registerMasterBean.setSubmitted(false);
                    }
                    registerMasterBeans.add(registerMasterBean);
                }
            } else if (billRegisterMasterBean.getSubmitted() != null && billRegisterMasterBean.getSubmitted().booleanValue() == true && billRegisterMasterBean.getSubmitDate() == null) {
                BillRegisterMaster registerMaster = billRegisterMasterRepository.findById(billRegisterMasterBean.getId()).orElse(null);
                registerMaster.setSubmitDate(LocalDate.now());
                BillRegisterMaster result = billRegisterMasterRepository.save(registerMaster);
                if (result != null) {
                    List<BillRegisterDetails> billRegisterDetails = billRegisterDetailsRepository.findAllByBillRegisterMaster(result.getId());
                    for (BillRegisterDetails billRegisterDetail : billRegisterDetails) {
                        billRegisterDetail.setSubmitdate(result.getSubmitDate());
                        billRegisterDetailsRepository.save(billRegisterDetail);
                    }
                    BillRegisterMasterBean registerMasterBean = new BillRegisterMasterBean();
                    BeanUtils.copyProperties(result, registerMasterBean);
                    if (result.getReceiveDate() != null) {
                        registerMasterBean.setReceived(true);
                    } else {
                        registerMasterBean.setReceived(false);
                    }

                    if (result.getSubmitDate() != null) {
                        registerMasterBean.setSubmitted(true);
                    } else {
                        registerMasterBean.setSubmitted(false);
                    }
                    registerMasterBeans.add(registerMasterBean);
                }
            } else {
                registerMasterBeans.add(billRegisterMasterBean);
            }
        }
        return ResponseEntity.created(new URI("/api/bill-register-masters-filter"))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, ""))
            .body(registerMasterBeans);
    }

    /**
     * {@code GET  /bill-register-masters} : get all the billRegisterMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of billRegisterMasters in body.
     */
    @GetMapping("/bill-register-masters")
    public ResponseEntity<List<BillRegisterMaster>> getAllBillRegisterMasters(Pageable pageable) {
        log.debug("REST request to get a page of BillRegisterMasters");
        Page<BillRegisterMaster> page = billRegisterMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bill-register-masters/:id} : get the "id" billRegisterMaster.
     *
     * @param id the id of the billRegisterMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the billRegisterMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bill-register-masters/{id}")
    public ResponseEntity<BillRegisterMasterBean> getBillRegisterMaster(@PathVariable Long id) {
        log.debug("REST request to get BillRegisterMaster : {}", id);
        BillRegisterMaster billRegisterMaster = billRegisterMasterRepository.findById(id).orElse(null);
        BillRegisterMasterBean billRegisterMasterBean = new BillRegisterMasterBean();
        BeanUtils.copyProperties(billRegisterMaster, billRegisterMasterBean);
        List<BillRegisterDetailsBean> billRegisterDetailsBeans = new ArrayList<>();
        List<BillRegisterDetails> billRegisterDetails = billRegisterDetailsRepository.findAllByBillRegisterMaster(id);
        for (BillRegisterDetails billRegisterDetail : billRegisterDetails) {
            BillRegisterDetailsBean billRegisterDetailsBean = new BillRegisterDetailsBean();
            BeanUtils.copyProperties(billRegisterDetail, billRegisterDetailsBean);
            billRegisterDetailsBeans.add(billRegisterDetailsBean);
        }
        billRegisterMasterBean.setBillRegisterDetailsBeans(billRegisterDetailsBeans);
        return ResponseUtil.wrapOrNotFound(Optional.of(billRegisterMasterBean));
    }

    /**
     * {@code DELETE  /bill-register-masters/:id} : delete the "id" billRegisterMaster.
     *
     * @param id the id of the billRegisterMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bill-register-masters/{id}")
    public ResponseEntity<Void> deleteBillRegisterMaster(@PathVariable Long id) {
        log.debug("REST request to delete BillRegisterMaster : {}", id);
        billRegisterDetailsRepository.deleteAllByBillRegisterMaster(id);
        billRegisterMasterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    @DeleteMapping("/bill-register-details/{id}")
    public ResponseEntity<Void> deleteBillRegisterDetails(@PathVariable Long id){
        log.debug("REST request to delete BillRegister : {}",id);
        billRegisterDetailsRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName,false,ENTITY_NAME,id.toString()))
            .build();
    }

    @PostMapping("/bill-registers-master-filter")
    public ResponseEntity<List<BillRegisterMasterBean>> getAllBillRegisterFilter(@RequestBody BillRegisterSearch search) {
        log.debug("REST request to get a page of BillRegisterMasterResource");
        String billType = "%";
        if (search.getBillType() != null && !search.getBillType().equals("undefined")) {
            billType = "%" + search.getBillType() + "%";
        }
        String invoice = "%";
        if (search.getInvoiceCode() != null) {
            invoice = "%" + search.getInvoiceCode().toUpperCase() + "%";
        }
        String supplierName = "%";
        if(search.getSupplierName() != null) {
            supplierName = "%" + search.getSupplierName().toUpperCase() + "%";
        }
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), search.getSortType() != null && search.getSortType().equalsIgnoreCase("desc") ? Sort.by(search.getSort()).descending() : Sort.by(search.getSort()).ascending()));
        Page<BillRegisterMaster> page = null;
        if (search.getFlag() != null && search.getFlag().equals("P")) {
            if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("INV")) {
                page = billRegisterMasterRepository.findAllByBilldatePending(billType, invoice, search.getInvoiceDateFrom(), search.getInvoiceDateTo(), supplierName, supplierName, search.getPage());
            } else if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("SUB")) {
                LocalDate fromDate = search.getInvoiceDateFrom().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate toDate = search.getInvoiceDateTo().atZone(ZoneId.systemDefault()).toLocalDate();
                page = billRegisterMasterRepository.findAllByBilldatePendingSub(billType, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
            } else if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("REC")) {
                LocalDate fromDate = search.getInvoiceDateFrom().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate toDate = search.getInvoiceDateTo().atZone(ZoneId.systemDefault()).toLocalDate();
                page = billRegisterMasterRepository.findAllByBilldatePendingRec(billType, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
            } else {
                page = billRegisterMasterRepository.findAllByBillnumberPending(billType, invoice, supplierName, supplierName, search.getPage());
            }
        } else if (search.getFlag() != null && search.getFlag().equals("S")) {
            if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("INV")) {
                page = billRegisterMasterRepository.findAllByBilldateSubmitted(billType, invoice, search.getInvoiceDateFrom(), search.getInvoiceDateTo(), supplierName, supplierName, search.getPage());
            } else if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("SUB")) {
                LocalDate fromDate = search.getInvoiceDateFrom().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate toDate = search.getInvoiceDateTo().atZone(ZoneId.systemDefault()).toLocalDate();
                page = billRegisterMasterRepository.findAllByBilldateSubmittedSub(billType, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
            } else if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("REC")) {
                LocalDate fromDate = search.getInvoiceDateFrom().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate toDate = search.getInvoiceDateTo().atZone(ZoneId.systemDefault()).toLocalDate();
                page = billRegisterMasterRepository.findAllByBilldateSubmittedRec(billType, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
            } else {
                page = billRegisterMasterRepository.findAllByBillnumberSubmitted(billType, invoice, supplierName, supplierName, search.getPage());
            }
        } else if (search.getFlag() != null && search.getFlag().equals("Q")) {
            if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("INV")) {
                page = billRegisterMasterRepository.findAllByBilldateQuery(billType, invoice, search.getInvoiceDateFrom(), search.getInvoiceDateTo(), supplierName, supplierName, search.getPage());
            } else if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("SUB")) {
                LocalDate fromDate = search.getInvoiceDateFrom().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate toDate = search.getInvoiceDateTo().atZone(ZoneId.systemDefault()).toLocalDate();
                page = billRegisterMasterRepository.findAllByBilldateQuerySub(billType, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
            } else if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("REC")) {
                LocalDate fromDate = search.getInvoiceDateFrom().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate toDate = search.getInvoiceDateTo().atZone(ZoneId.systemDefault()).toLocalDate();
                page = billRegisterMasterRepository.findAllByBilldateQueryRec(billType, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
            } else {
                page = billRegisterMasterRepository.findAllByBillnumberQuery(billType, invoice, supplierName, supplierName, search.getPage());
            }
        } else {
            if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("INV")) {
                page = billRegisterMasterRepository.findAllByBilldate(billType, invoice, search.getInvoiceDateFrom(), search.getInvoiceDateTo(), supplierName, supplierName, search.getPage());
            } else if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("SUB")) {
                LocalDate fromDate = search.getInvoiceDateFrom().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate toDate = search.getInvoiceDateTo().atZone(ZoneId.systemDefault()).toLocalDate();
                page = billRegisterMasterRepository.findAllByBilldateSub(billType, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
            } else if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("REC")) {
                LocalDate fromDate = search.getInvoiceDateFrom().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate toDate = search.getInvoiceDateTo().atZone(ZoneId.systemDefault()).toLocalDate();
                page = billRegisterMasterRepository.findAllByBilldateRec(billType, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
            } else {
                page = billRegisterMasterRepository.findAllByBillnumber(billType, invoice, supplierName, supplierName, search.getPage());
            }
        }
        List<BillRegisterMasterBean> billRegisterMasterBeans = new ArrayList<>();
        for (BillRegisterMaster billRegisterMaster : page.getContent()) {
            BillRegisterMasterBean billRegisterMasterBean = new BillRegisterMasterBean();
            BeanUtils.copyProperties(billRegisterMaster, billRegisterMasterBean);
            if (billRegisterMaster.getReceiveDate() != null) {
                billRegisterMasterBean.setReceived(true);
            } else {
                billRegisterMasterBean.setReceived(false);
            }
            if (billRegisterMaster.getSubmitDate() != null) {
                billRegisterMasterBean.setSubmitted(true);
            } else {
                billRegisterMasterBean.setSubmitted(false);
            }
            billRegisterMasterBeans.add(billRegisterMasterBean);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(billRegisterMasterBeans);
    }

    @PostMapping("/bill-registers-master-filter-report")
    public @ResponseBody void getAllBillRegisterFilterReport(@RequestBody BillRegisterSearch search, HttpServletResponse response) throws JRException, IOException {
        log.debug("REST request to get a page of BillRegisterMasterResource");
        String billType = "%";
        if (search.getBillType() != null && !search.getBillType().equals("undefined")) {
            billType = "%" + search.getBillType() + "%";
        }
        String invoice = "%";
        if (search.getInvoiceCode() != null) {
            invoice = "%" + search.getInvoiceCode().toUpperCase() + "%";
        }
        String supplierName = "%";
        if(search.getSupplierName() != null) {
            supplierName = "%" + search.getSupplierName().toUpperCase() + "%";
        }
        search.setPage(
            PageRequest.of(
                0,
                Integer.MAX_VALUE,
                search.getSortType() != null && search.getSortType().equalsIgnoreCase("desc")
                    ? Sort.by(search.getSort()).descending()
                    : Sort.by(search.getSort()).ascending()

            )
        );
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Page<BillRegisterMaster> page = null;
        if (search.getFlag() != null && search.getFlag().equals("P")) {
            if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("INV")) {
                page = billRegisterMasterRepository.findAllByBilldatePending(billType, invoice, search.getInvoiceDateFrom(), search.getInvoiceDateTo(), supplierName, supplierName, search.getPage());
            } else if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("SUB")) {
                LocalDate fromDate = search.getInvoiceDateFrom().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate toDate = search.getInvoiceDateTo().atZone(ZoneId.systemDefault()).toLocalDate();
                page = billRegisterMasterRepository.findAllByBilldatePendingSub(billType, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
            } else if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("REC")) {
                LocalDate fromDate = search.getInvoiceDateFrom().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate toDate = search.getInvoiceDateTo().atZone(ZoneId.systemDefault()).toLocalDate();
                page = billRegisterMasterRepository.findAllByBilldatePendingRec(billType, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
            } else {
                page = billRegisterMasterRepository.findAllByBillnumberPending(billType, invoice, supplierName, supplierName, search.getPage());
            }
        } else if (search.getFlag() != null && search.getFlag().equals("S")) {
            if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("INV")) {
                page = billRegisterMasterRepository.findAllByBilldateSubmitted(billType, invoice, search.getInvoiceDateFrom(), search.getInvoiceDateTo(), supplierName, supplierName, search.getPage());
            } else if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("SUB")) {
                LocalDate fromDate = search.getInvoiceDateFrom().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate toDate = search.getInvoiceDateTo().atZone(ZoneId.systemDefault()).toLocalDate();
                page = billRegisterMasterRepository.findAllByBilldateSubmittedSub(billType, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
            } else if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("REC")) {
                LocalDate fromDate = search.getInvoiceDateFrom().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate toDate = search.getInvoiceDateTo().atZone(ZoneId.systemDefault()).toLocalDate();
                page = billRegisterMasterRepository.findAllByBilldateSubmittedRec(billType, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
            } else {
                page = billRegisterMasterRepository.findAllByBillnumberSubmitted(billType, invoice, supplierName, supplierName, search.getPage());
            }
        } else if (search.getFlag() != null && search.getFlag().equals("Q")) {
            if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("INV")) {
                page = billRegisterMasterRepository.findAllByBilldateQuery(billType, invoice, search.getInvoiceDateFrom(), search.getInvoiceDateTo(), supplierName, supplierName, search.getPage());
            } else if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("SUB")) {
                LocalDate fromDate = search.getInvoiceDateFrom().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate toDate = search.getInvoiceDateTo().atZone(ZoneId.systemDefault()).toLocalDate();
                page = billRegisterMasterRepository.findAllByBilldateQuerySub(billType, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
            } else if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("REC")) {
                LocalDate fromDate = search.getInvoiceDateFrom().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate toDate = search.getInvoiceDateTo().atZone(ZoneId.systemDefault()).toLocalDate();
                page = billRegisterMasterRepository.findAllByBilldateQueryRec(billType, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
            } else {
                LocalDate fromDate = search.getInvoiceDateFrom().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate toDate = search.getInvoiceDateTo().atZone(ZoneId.systemDefault()).toLocalDate();
                page = billRegisterMasterRepository.findAllByBillnumberQuery(billType, invoice, supplierName, supplierName, search.getPage());
            }
        } else {
            if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("INV")) {
                page = billRegisterMasterRepository.findAllByBilldate(billType, invoice, search.getInvoiceDateFrom(), search.getInvoiceDateTo(), supplierName, supplierName, search.getPage());
            } else if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("SUB")) {
                LocalDate fromDate = search.getInvoiceDateFrom().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate toDate = search.getInvoiceDateTo().atZone(ZoneId.systemDefault()).toLocalDate();
                page = billRegisterMasterRepository.findAllByBilldateSub(billType, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
            } else if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("SUB")) {
                LocalDate fromDate = search.getInvoiceDateFrom().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate toDate = search.getInvoiceDateTo().atZone(ZoneId.systemDefault()).toLocalDate();
                page = billRegisterMasterRepository.findAllByBilldateRec(billType, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
            } else {
                page = billRegisterMasterRepository.findAllByBillnumber(billType, invoice, supplierName, supplierName, search.getPage());
            }
        }
        List<BillRegisterMasterBean> billRegisterMasterBeans = new ArrayList<>();

        for (BillRegisterMaster billRegisterMaster : page.getContent()) {
            BillRegisterMasterBean billRegisterMasterBean = new BillRegisterMasterBean();
            BeanUtils.copyProperties(billRegisterMaster, billRegisterMasterBean);
            billRegisterMasterBean.setBilldateFormat(simpleDateFormat.format(DateUtils.asDate(billRegisterMaster.getBilldate())));
            billRegisterMasterBeans.add(billRegisterMasterBean);
        }

        String path = applicationProperties.getTemplatePath() + "jasper/";
        JasperDesign jasperDesign = JRXmlLoader.load(path + "/bill_register.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String, Object> parameters = new HashMap<String, Object>();
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(billRegisterMasterBeans);

        parameters.put("datasource", jrDataSource);
        parameters.put("SUBREPORT_DIR", path);
        parameters.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=TDS.xlsx");

        final OutputStream outputStream = response.getOutputStream();
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
        exporter.exportReport();
    }

    @PostMapping("/bill-registers-filter")
    public ResponseEntity<BillRegisterMaster> partialUpdateBillRegister(@PathVariable(value = "id", required = false) final Long id,
                                                                        @NotNull @RequestBody BillRegisterMaster billRegisterMaster) throws URISyntaxException {
        log.debug("REST request to partial update BillRegister partially : {},{}", id, billRegisterMaster);
        if (billRegisterMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }
        if (!Objects.equals(id, billRegisterMaster.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }
        if (!billRegisterMasterRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        Optional<BillRegisterMaster> result = billRegisterMasterRepository
            .findById(billRegisterMaster.getId())
            .map(existingBillRegisterMaster -> {
                if (billRegisterMaster.getBilltype() != null) {
                    existingBillRegisterMaster.setBilltype(billRegisterMaster.getBilltype());
                }
                if (billRegisterMaster.getBillnumber() != null) {
                    existingBillRegisterMaster.setBillnumber(billRegisterMaster.getBillnumber());
                }
                if (billRegisterMaster.getBilldate() != null) {
                    existingBillRegisterMaster.setBilldate(billRegisterMaster.getBilldate());
                }
                if (billRegisterMaster.getCustomersuppliertype() != null) {
                    existingBillRegisterMaster.setCustomersuppliertype(billRegisterMaster.getCustomersuppliertype());
                }
                if (billRegisterMaster.getCustomersuppliercode() != null) {
                    existingBillRegisterMaster.setCustomersuppliercode(billRegisterMaster.getCustomersuppliercode());
                }
                if (billRegisterMaster.getCustomersuppliername() != null) {
                    existingBillRegisterMaster.setCustomersuppliername(billRegisterMaster.getCustomersuppliername());
                }
                if (billRegisterMaster.getCreatedby() != null) {
                    existingBillRegisterMaster.setCreatedby(billRegisterMaster.getCreatedby());
                }
                if (billRegisterMaster.getCreateddate() != null) {
                    existingBillRegisterMaster.setCreateddate(billRegisterMaster.getCreateddate());
                }
                if (billRegisterMaster.getUpdatedby() != null) {
                    existingBillRegisterMaster.setUpdatedby(billRegisterMaster.getUpdatedby());
                }
                if (billRegisterMaster.getUpdateddate() != null) {
                    existingBillRegisterMaster.setUpdateddate(billRegisterMaster.getUpdateddate());
                }

                return existingBillRegisterMaster;
            })
            .map(billRegisterMasterRepository::save);
        return ResponseUtil.wrapOrNotFound(
            result, HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, billRegisterMaster.getId().toString())
        );
    }
}
