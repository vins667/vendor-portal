package io.vamani.application.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.db2.domain.Finfinancialyear;
import io.vamani.application.db2.model.PlantinvoiceSearch;
import io.vamani.application.db2.repository.FinfinancialyearRepository;
import io.vamani.application.domain.BillRegisterDetails;
import io.vamani.application.domain.BillRegisterImport;
import io.vamani.application.domain.BillRegisterImportDetails;
import io.vamani.application.model.*;
import io.vamani.application.repository.BillRegisterDetailsRepository;
import io.vamani.application.repository.BillRegisterImportDetailsRepository;
import io.vamani.application.repository.BillRegisterImportRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.util.DateUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/**
 * REST controller for managing {@link BillRegisterImport}.
 */
@RestController
@RequestMapping("/api")
public class BillRegisterImportResource {

    private final Logger log = LoggerFactory.getLogger(BillRegisterImportResource.class);

    private static final String ENTITY_NAME = "billRegisterImport";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BillRegisterImportRepository billRegisterImportRepository;
    private final BillRegisterImportDetailsRepository billRegisterDetailsRepository;

    @Autowired
    private FinfinancialyearRepository finfinancialyearRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

    public BillRegisterImportResource(BillRegisterImportRepository billRegisterImportRepository, BillRegisterImportDetailsRepository billRegisterDetailsRepository) {
        this.billRegisterImportRepository = billRegisterImportRepository;
        this.billRegisterDetailsRepository = billRegisterDetailsRepository;
    }

    /**
     * {@code POST  /bill-register-masters} : Create a new billRegisterImport.
     *
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new billRegisterImport, or with status {@code 400 (Bad Request)} if the billRegisterImport has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bill-register-imports")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BillRegisterImport> createBillRegisterImport(@Valid @RequestBody BillRegisterImportBean billRegisterImportBean) throws URISyntaxException {
        log.debug("REST request to save BillRegisterImport : {}", billRegisterImportBean);
        if (billRegisterImportBean.getId() != null) {
            throw new BadRequestAlertException("A new billRegisterImport cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Finfinancialyear finfinancialyear = finfinancialyearRepository.findByDate(DateUtils.asDate(billRegisterImportBean.getBilldate()));
        if (finfinancialyear != null) {
            List<BillRegisterImport> billRegisterImports = billRegisterImportRepository.findAllByBillNumberAndSupplierAndBilldate(billRegisterImportBean.getBillnumber(), billRegisterImportBean.getCustomersuppliercode(), DateUtils.asLocalDate(finfinancialyear.getFromdate()), DateUtils.asLocalDate(finfinancialyear.getTodate()));
            if (billRegisterImports != null && billRegisterImports.size() > 0) {
                throw new BadRequestAlertException("Bill Number Already exist", ENTITY_NAME, "400");
            }
        } else {
            throw new BadRequestAlertException("In-valid bill date", ENTITY_NAME, "400");
        }
        if (billRegisterImportBean.getBillRegisterDetailsBeans() != null && billRegisterImportBean.getBillRegisterDetailsBeans().size() > 0) {
            int i = 0;
            for (BillRegisterImportDetailsBean billRegisterDetailsBean : billRegisterImportBean.getBillRegisterDetailsBeans()) {
                if (billRegisterDetailsBean.getSummarizeddescription() != null && billRegisterDetailsBean.getSummarizeddescription().length() > 0) {
                    if (billRegisterDetailsBean.getQuantity() == null) {
                        return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "Quantity can't be blank in Line# " + (i + 1))).build();
                    } else if (billRegisterDetailsBean.getQuantity() != null && billRegisterDetailsBean.getQuantity().doubleValue() == 0) {
                        return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "Quantity can't be zero in Line# " + (i + 1))).build();
                    } else if (billRegisterImportBean.getBilltype() != null && (billRegisterImportBean.getBilltype().equalsIgnoreCase("CHA") || billRegisterImportBean.getBilltype().equalsIgnoreCase("FORWARDER") || billRegisterImportBean.getBilltype().equalsIgnoreCase("TRANSPORTER"))) {
                        List<BillRegisterImport> billRegisterImports = billRegisterImportRepository.findAllByBillnumberAndBillDate(billRegisterDetailsBean.getCode(), billRegisterDetailsBean.getOrderdate());
                        if (billRegisterImports != null && billRegisterImports.size() > 0) {
                            BillRegisterImport registerImport = billRegisterImports.get(0);
                            BigDecimal allowedQuantity = registerImport.getTotalQuantity();
                            BigDecimal alreadyAssigned = billRegisterDetailsRepository.totalByBilltypeAndBillcode(billRegisterImportBean.getBilltype(), billRegisterDetailsBean.getCode(), billRegisterDetailsBean.getOrderdate());
                            if ((alreadyAssigned.doubleValue() + billRegisterDetailsBean.getQuantity().doubleValue()) > allowedQuantity.doubleValue()) {
                                return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "Quantity can't be more than allowed quantity " + (allowedQuantity.doubleValue() - alreadyAssigned.doubleValue()))).build();
                            }
                        }
                    }
                }
                ++i;
            }
        } else {
            return ResponseEntity.badRequest()
                .headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "Details can't be blank.")).build();
        }
        BillRegisterImport billRegisterImport = new BillRegisterImport();
        BeanUtils.copyProperties(billRegisterImportBean, billRegisterImport);
        billRegisterImport.setCreatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
        billRegisterImport.setCreateddate(Instant.now());
        BillRegisterImport result = billRegisterImportRepository.save(billRegisterImport);
        if (result != null && billRegisterImportBean.getBillRegisterDetailsBeans() != null && billRegisterImportBean.getBillRegisterDetailsBeans().size() > 0) {
            for (BillRegisterImportDetailsBean billRegisterDetailsBean : billRegisterImportBean.getBillRegisterDetailsBeans()) {
                if (billRegisterDetailsBean.getSummarizeddescription() != null && billRegisterDetailsBean.getSummarizeddescription().length() > 0) {
                    BillRegisterImportDetails billRegisterImportDetails = new BillRegisterImportDetails();
                    BeanUtils.copyProperties(billRegisterDetailsBean, billRegisterImportDetails);
                    billRegisterImportDetails.setSubmitdate(billRegisterImport.getSubmitDate());
                    billRegisterImportDetails.setReceiveDate(billRegisterImport.getReceiveDate());
                    billRegisterImportDetails.setBillRegisterImport(result);
                    billRegisterDetailsRepository.save(billRegisterImportDetails);
                }
            }
        }
        return ResponseEntity.created(new URI("/api/bill-register-imports/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString())).body(result);
    }

    /**
     * {@code PUT  /bill-register-imports} : Updates an existing billRegisterImport.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated billRegisterImport,
     * or with status {@code 400 (Bad Request)} if the billRegisterImport is not valid,
     * or with status {@code 500 (Internal Server Error)} if the billRegisterImport couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bill-register-imports/{id}")
    public ResponseEntity<BillRegisterImport> updateBillRegisterImport(@PathVariable(value = "id", required = false) final Long id, @Valid @RequestBody BillRegisterImportBean billRegisterImportBean) throws URISyntaxException {
        log.debug("REST request to update BillRegisterImport : {}", billRegisterImportBean);
        if (billRegisterImportBean.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, billRegisterImportBean.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }
        if (!billRegisterImportRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        Finfinancialyear finfinancialyear = finfinancialyearRepository.findByDate(DateUtils.asDate(billRegisterImportBean.getBilldate()));
        if (finfinancialyear != null) {
            List<BillRegisterImport> billRegisterImports = billRegisterImportRepository.findAllByBillNumberAndSupplierAndBilldateAndNotId(billRegisterImportBean.getBillnumber(), billRegisterImportBean.getCustomersuppliercode(), DateUtils.asLocalDate(finfinancialyear.getFromdate()), DateUtils.asLocalDate(finfinancialyear.getTodate()), billRegisterImportBean.getId());
            if (billRegisterImports != null && billRegisterImports.size() > 0) {
                throw new BadRequestAlertException("Bill Number Already exist", ENTITY_NAME, "400");
            }
        } else {
            throw new BadRequestAlertException("In-valid bill date", ENTITY_NAME, "400");
        }
        BillRegisterImport billRegisterImport = new BillRegisterImport();
        BeanUtils.copyProperties(billRegisterImportBean, billRegisterImport);
        billRegisterImport.setUpdatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
        billRegisterImport.setUpdateddate(Instant.now());
        BillRegisterImport result = billRegisterImportRepository.save(billRegisterImport);
        if (result != null && billRegisterImportBean.getBillRegisterDetailsBeans() != null && billRegisterImportBean.getBillRegisterDetailsBeans().size() > 0) {
            for (BillRegisterImportDetailsBean billRegisterDetailsBean : billRegisterImportBean.getBillRegisterDetailsBeans()) {
                if (billRegisterDetailsBean.getSummarizeddescription() != null && billRegisterDetailsBean.getSummarizeddescription().length() > 0) {
                    BillRegisterImportDetails billRegisterDetails = new BillRegisterImportDetails();
                    BeanUtils.copyProperties(billRegisterDetailsBean, billRegisterDetails);
                    billRegisterDetails.setSubmitdate(billRegisterImport.getSubmitDate());
                    billRegisterDetails.setReceiveDate(billRegisterImport.getReceiveDate());
                    billRegisterDetails.setBillRegisterImport(result);
                    billRegisterDetailsRepository.save(billRegisterDetails);
                }
            }
        }
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, billRegisterImport.getId().toString())).body(result);
    }

    @PostMapping("/bill-register-import-received")
    public ResponseEntity<List<BillRegisterImportBean>> updateBillRegisterImports(@Valid @RequestBody List<BillRegisterImportBean> billRegisterImportBeans) throws URISyntaxException {
        log.debug("REST request to save BillRegisterImport : {}", billRegisterImportBeans);
        List<BillRegisterImportBean> registerMasterBeans = new ArrayList<>();
        for (BillRegisterImportBean billRegisterImportBean : billRegisterImportBeans) {
            if (billRegisterImportBean.getReceived() != null && billRegisterImportBean.getReceived().booleanValue() == true && billRegisterImportBean.getReceiveDate() == null) {
                BillRegisterImport registerMaster = billRegisterImportRepository.findById(billRegisterImportBean.getId()).orElse(null);
                registerMaster.setReceiveDate(LocalDate.now());
                BillRegisterImport result = billRegisterImportRepository.save(registerMaster);
                if (result != null) {
                    List<BillRegisterImportDetails> billRegisterDetails = billRegisterDetailsRepository.findAllByBillRegisterMaster(result.getId());
                    for (BillRegisterImportDetails billRegisterDetail : billRegisterDetails) {
                        billRegisterDetail.setReceiveDate(result.getReceiveDate());
                        billRegisterDetailsRepository.save(billRegisterDetail);
                    }
                    BillRegisterImportBean registerMasterBean = new BillRegisterImportBean();
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
            } else if (billRegisterImportBean.getSubmitted() != null && billRegisterImportBean.getSubmitted().booleanValue() == true && billRegisterImportBean.getSubmitDate() == null) {
                BillRegisterImport registerMaster = billRegisterImportRepository.findById(billRegisterImportBean.getId()).orElse(null);
                registerMaster.setSubmitDate(LocalDate.now());
                BillRegisterImport result = billRegisterImportRepository.save(registerMaster);
                if (result != null) {
                    List<BillRegisterImportDetails> billRegisterDetails = billRegisterDetailsRepository.findAllByBillRegisterMaster(result.getId());
                    for (BillRegisterImportDetails billRegisterDetail : billRegisterDetails) {
                        billRegisterDetail.setSubmitdate(result.getSubmitDate());
                        billRegisterDetailsRepository.save(billRegisterDetail);
                    }
                    BillRegisterImportBean registerMasterBean = new BillRegisterImportBean();
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
                registerMasterBeans.add(billRegisterImportBean);
            }
        }
        return ResponseEntity.created(new URI("/api/bill-register-imports-filter")).headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, "")).body(registerMasterBeans);
    }

    /**
     * {@code GET  /bill-register-imports} : get all the billRegisterImports.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of billRegisterImports in body.
     */
    @GetMapping("/bill-register-imports")
    public ResponseEntity<List<BillRegisterImport>> getAllBillRegisterImports(Pageable pageable) {
        log.debug("REST request to get a page of BillRegisterImports");
        Page<BillRegisterImport> page = billRegisterImportRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bill-register-imports/:id} : get the "id" billRegisterImport.
     *
     * @param id the id of the billRegisterImport to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the billRegisterImport, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bill-register-imports/{id}")
    public ResponseEntity<BillRegisterImportBean> getBillRegisterImport(@PathVariable Long id) {
        log.debug("REST request to get BillRegisterImport : {}", id);
        BillRegisterImport billRegisterImport = billRegisterImportRepository.findById(id).orElse(null);
        BillRegisterImportBean billRegisterImportBean = new BillRegisterImportBean();
        BeanUtils.copyProperties(billRegisterImport, billRegisterImportBean);
        List<BillRegisterImportDetailsBean> billRegisterDetailsBeans = new ArrayList<>();
        List<BillRegisterImportDetails> billRegisterDetails = billRegisterDetailsRepository.findAllByBillRegisterMaster(id);
        for (BillRegisterImportDetails billRegisterDetail : billRegisterDetails) {
            BillRegisterImportDetailsBean billRegisterDetailsBean = new BillRegisterImportDetailsBean();
            BeanUtils.copyProperties(billRegisterDetail, billRegisterDetailsBean);
            billRegisterDetailsBeans.add(billRegisterDetailsBean);
        }
        billRegisterImportBean.setBillRegisterDetailsBeans(billRegisterDetailsBeans);
        return ResponseUtil.wrapOrNotFound(Optional.of(billRegisterImportBean));
    }

    /**
     * {@code DELETE  /bill-register-imports/:id} : delete the "id" billRegisterImport.
     *
     * @param id the id of the billRegisterImport to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bill-register-imports/{id}")
    public ResponseEntity<Void> deleteBillRegisterImport(@PathVariable Long id) {
        log.debug("REST request to delete BillRegisterImport : {}", id);
        billRegisterDetailsRepository.deleteAllByBillRegisterMaster(id);
        billRegisterImportRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    @DeleteMapping("/bill-register-import-details/{id}")
    public ResponseEntity<Void> deleteBillRegisterDetails(@PathVariable Long id) {
        log.debug("REST request to delete BillRegister : {}", id);
        billRegisterDetailsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    @PostMapping("/bill-register-imports-filter")
    public ResponseEntity<List<BillRegisterImportBean>> getAllBillRegisterFilter(@RequestBody BillRegisterSearch search) {
        log.debug("REST request to get a page of BillRegisterImportResource");
        String billType = "%";
        if (search.getBillType() != null && !search.getBillType().equals("undefined")) {
            billType = "%" + search.getBillType() + "%";
        }
        String invoice = "%";
        if (search.getInvoiceCode() != null) {
            invoice = "%" + search.getInvoiceCode().toUpperCase() + "%";
        }
        String supplierName = "%";
        if (search.getSupplierName() != null) {
            supplierName = "%" + search.getSupplierName().toUpperCase() + "%";
        }
        List<String> billTypes = new ArrayList<>();
        if (search.getSelectedItems() != null && search.getSelectedItems().size() > 0) {
            billTypes = search.getSelectedItems().stream().map(value -> value.getId()).collect(Collectors.toList());
        }
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), search.getSortType() != null && search.getSortType().equalsIgnoreCase("desc") ? Sort.by(search.getSort()).descending() : Sort.by(search.getSort()).ascending()));
        Page<BillRegisterImport> page = null;
        if (search.getFlag() != null && search.getFlag().equals("P")) {
            if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("INV")) {
                if (billTypes.size() > 0) {
                    page = billRegisterImportRepository.findAllByBilldatePending(billTypes, invoice, DateUtils.asLocalDate(search.getInvoiceDateFrom()), DateUtils.asLocalDate(search.getInvoiceDateTo()), supplierName, supplierName, search.getPage());
                } else {
                    page = billRegisterImportRepository.findAllByBilldatePending(billType, invoice, DateUtils.asLocalDate(search.getInvoiceDateFrom()), DateUtils.asLocalDate(search.getInvoiceDateTo()), supplierName, supplierName, search.getPage());
                }
            } else if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("SUB")) {
                LocalDate fromDate = search.getInvoiceDateFrom().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate toDate = search.getInvoiceDateTo().atZone(ZoneId.systemDefault()).toLocalDate();
                if (billTypes.size() > 0) {
                    page = billRegisterImportRepository.findAllByBilldatePendingSub(billTypes, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
                } else {
                    page = billRegisterImportRepository.findAllByBilldatePendingSub(billType, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
                }
            } else if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("REC")) {
                LocalDate fromDate = search.getInvoiceDateFrom().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate toDate = search.getInvoiceDateTo().atZone(ZoneId.systemDefault()).toLocalDate();
                if (billTypes.size() > 0) {
                    page = billRegisterImportRepository.findAllByBilldatePendingRec(billTypes, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
                } else {
                    page = billRegisterImportRepository.findAllByBilldatePendingRec(billType, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
                }
            } else {
                if (billTypes.size() > 0) {
                    page = billRegisterImportRepository.findAllByBillnumberPending(billTypes, invoice, supplierName, supplierName, search.getPage());
                } else {
                    page = billRegisterImportRepository.findAllByBillnumberPending(billType, invoice, supplierName, supplierName, search.getPage());
                }
            }
        } else if (search.getFlag() != null && search.getFlag().equals("S")) {
            if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("INV")) {
                if (billTypes.size() > 0) {
                    page = billRegisterImportRepository.findAllByBilldateSubmitted(billTypes, invoice, DateUtils.asLocalDate(search.getInvoiceDateFrom()), DateUtils.asLocalDate(search.getInvoiceDateTo()), supplierName, supplierName, search.getPage());
                } else {
                    page = billRegisterImportRepository.findAllByBilldateSubmitted(billType, invoice, DateUtils.asLocalDate(search.getInvoiceDateFrom()), DateUtils.asLocalDate(search.getInvoiceDateTo()), supplierName, supplierName, search.getPage());
                }
            } else if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("SUB")) {
                LocalDate fromDate = search.getInvoiceDateFrom().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate toDate = search.getInvoiceDateTo().atZone(ZoneId.systemDefault()).toLocalDate();
                if (billTypes.size() > 0) {
                    page = billRegisterImportRepository.findAllByBilldateSubmittedSub(billTypes, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
                } else {
                    page = billRegisterImportRepository.findAllByBilldateSubmittedSub(billType, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
                }
            } else if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("REC")) {
                LocalDate fromDate = search.getInvoiceDateFrom().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate toDate = search.getInvoiceDateTo().atZone(ZoneId.systemDefault()).toLocalDate();
                if (billTypes.size() > 0) {
                    page = billRegisterImportRepository.findAllByBilldateSubmittedRec(billTypes, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
                } else {
                    page = billRegisterImportRepository.findAllByBilldateSubmittedRec(billType, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
                }
            } else {
                if (billTypes.size() > 0) {
                    page = billRegisterImportRepository.findAllByBillnumberSubmitted(billTypes, invoice, supplierName, supplierName, search.getPage());
                } else {
                    page = billRegisterImportRepository.findAllByBillnumberSubmitted(billType, invoice, supplierName, supplierName, search.getPage());
                }
            }
        } else {
            if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("INV")) {
                if (billTypes.size() > 0) {
                    page = billRegisterImportRepository.findAllByBilldate(billTypes, invoice, DateUtils.asLocalDate(search.getInvoiceDateFrom()), DateUtils.asLocalDate(search.getInvoiceDateTo()), supplierName, supplierName, search.getPage());
                } else {
                    page = billRegisterImportRepository.findAllByBilldate(billType, invoice, DateUtils.asLocalDate(search.getInvoiceDateFrom()), DateUtils.asLocalDate(search.getInvoiceDateTo()), supplierName, supplierName, search.getPage());
                }
            } else if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("SUB")) {
                LocalDate fromDate = search.getInvoiceDateFrom().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate toDate = search.getInvoiceDateTo().atZone(ZoneId.systemDefault()).toLocalDate();
                if (billTypes.size() > 0) {
                    page = billRegisterImportRepository.findAllByBilldateSub(billTypes, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
                } else {
                    page = billRegisterImportRepository.findAllByBilldateSub(billType, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
                }
            } else if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("REC")) {
                LocalDate fromDate = search.getInvoiceDateFrom().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate toDate = search.getInvoiceDateTo().atZone(ZoneId.systemDefault()).toLocalDate();
                if (billTypes.size() > 0) {
                    page = billRegisterImportRepository.findAllByBilldateRec(billTypes, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
                } else {
                    page = billRegisterImportRepository.findAllByBilldateRec(billType, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
                }
            } else {
                if (billTypes.size() > 0) {
                    page = billRegisterImportRepository.findAllByBillnumber(billTypes, invoice, supplierName, supplierName, search.getPage());
                } else {
                    page = billRegisterImportRepository.findAllByBillnumber(billType, invoice, supplierName, supplierName, search.getPage());
                }
            }
        }
        List<BillRegisterImportBean> billRegisterImportBeans = new ArrayList<>();
        for (BillRegisterImport billRegisterImport : page.getContent()) {
            BillRegisterImportBean billRegisterImportBean = new BillRegisterImportBean();
            BeanUtils.copyProperties(billRegisterImport, billRegisterImportBean);
            if (billRegisterImport.getReceiveDate() != null) {
                billRegisterImportBean.setReceived(true);
            } else {
                billRegisterImportBean.setReceived(false);
            }
            if (billRegisterImport.getSubmitDate() != null) {
                billRegisterImportBean.setSubmitted(true);
            } else {
                billRegisterImportBean.setSubmitted(false);
            }
            billRegisterImportBeans.add(billRegisterImportBean);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(billRegisterImportBeans);
    }

    @PostMapping("/bill-register-imports-supplier-filter")
    @com.codahale.metrics.annotation.Timed
    public ResponseEntity<List<BuyerRegisterPurchaseLineBean>> getAllPurchaseorderlineFilter(@RequestBody PlantinvoiceSearch search) throws UnsupportedEncodingException {
        log.debug("REST request to get a page of plantinvoice");
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("billnumber")));
        String code = "%";
        if (search.getCode() != null) {
            code = "%" + search.getCode().toUpperCase() + "%";
        }
        Page<Object[]> page = billRegisterImportRepository.findAllBySupplierCodeAndCILike(code, search.getPage());
        List<BuyerRegisterPurchaseLineBean> buyerRegisterPurchaseLineBeans = new ArrayList<>();
        for (Object[] objects : page.getContent()) {
            BuyerRegisterPurchaseLineBean buyerRegisterPurchaseLineBean = new BuyerRegisterPurchaseLineBean();
            buyerRegisterPurchaseLineBean.setCode(objects[0] != null ? objects[0].toString().trim() : null);
            buyerRegisterPurchaseLineBean.setOrderdate(objects[1] != null ? (LocalDate) objects[1] : null);
            buyerRegisterPurchaseLineBean.setProjectcode(objects[2] != null ? objects[2].toString().trim() : null);
            buyerRegisterPurchaseLineBean.setSummarizeddescription(objects[3].toString().trim());
            buyerRegisterPurchaseLineBean.setUserprimaryuomcode(objects[4] != null ? objects[4].toString().trim() : null);
            buyerRegisterPurchaseLineBean.setUserprimaryquantity((BigDecimal) objects[5]);
            buyerRegisterPurchaseLineBean.setPrice((BigDecimal) objects[6]);
            buyerRegisterPurchaseLineBean.setValue((BigDecimal) objects[7]);
            buyerRegisterPurchaseLineBeans.add(buyerRegisterPurchaseLineBean);
        }
        HttpHeaders headers = io.vamani.application.web.rest.util.PaginationUtil.generatePaginationHttpHeaders(page, "/api/productionorders");
        return ResponseEntity.ok().headers(headers).body(buyerRegisterPurchaseLineBeans);
    }

    @PostMapping("/bill-register-imports-filter-report")
    public @ResponseBody void getAllBillRegisterFilterReport(@RequestBody BillRegisterSearch search, HttpServletResponse response) throws JRException, IOException {
        log.debug("REST request to get a page of BillRegisterImportResource");
        String billType = "%";
        if (search.getBillType() != null && !search.getBillType().equals("undefined")) {
            billType = "%" + search.getBillType() + "%";
        }
        String invoice = "%";
        if (search.getInvoiceCode() != null) {
            invoice = "%" + search.getInvoiceCode().toUpperCase() + "%";
        }
        String supplierName = "%";
        if (search.getSupplierName() != null) {
            supplierName = "%" + search.getSupplierName().toUpperCase() + "%";
        }
        search.setPage(PageRequest.of(0, Integer.MAX_VALUE, search.getSortType() != null && search.getSortType().equalsIgnoreCase("desc") ? Sort.by(search.getSort()).descending() : Sort.by(search.getSort()).ascending()

        ));
        List<String> billTypes = new ArrayList<>();
        if (search.getSelectedItems() != null && search.getSelectedItems().size() > 0) {
            billTypes = search.getSelectedItems().stream().map(value -> value.getId()).collect(Collectors.toList());
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Page<Object[]> page = null;
        if (search.getFlag() != null && search.getFlag().equals("P")) {
            if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("INV")) {
                if (billTypes.size() > 0) {
                    page = billRegisterImportRepository.findAllByBilldatePendingForReport(billTypes, invoice, DateUtils.asLocalDate(search.getInvoiceDateFrom()), DateUtils.asLocalDate(search.getInvoiceDateTo()), supplierName, supplierName, search.getPage());
                } else {
                    page = billRegisterImportRepository.findAllByBilldatePendingForReport(billType, invoice, DateUtils.asLocalDate(search.getInvoiceDateFrom()), DateUtils.asLocalDate(search.getInvoiceDateTo()), supplierName, supplierName, search.getPage());
                }
            } else if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("SUB")) {
                LocalDate fromDate = search.getInvoiceDateFrom().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate toDate = search.getInvoiceDateTo().atZone(ZoneId.systemDefault()).toLocalDate();
                if (billTypes.size() > 0) {
                    page = billRegisterImportRepository.findAllByBilldatePendingSubForReport(billTypes, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
                } else {
                    page = billRegisterImportRepository.findAllByBilldatePendingSubForReport(billType, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
                }
            } else if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("REC")) {
                LocalDate fromDate = search.getInvoiceDateFrom().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate toDate = search.getInvoiceDateTo().atZone(ZoneId.systemDefault()).toLocalDate();
                if (billTypes.size() > 0) {
                    page = billRegisterImportRepository.findAllByBilldatePendingRecForReport(billTypes, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
                } else {
                    page = billRegisterImportRepository.findAllByBilldatePendingRecForReport(billType, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
                }
            } else {
                if (billTypes.size() > 0) {
                    page = billRegisterImportRepository.findAllByBillnumberPendingForReport(billTypes, invoice, supplierName, supplierName, search.getPage());
                } else {
                    page = billRegisterImportRepository.findAllByBillnumberPendingForReport(billType, invoice, supplierName, supplierName, search.getPage());
                }
            }
        } else if (search.getFlag() != null && search.getFlag().equals("S")) {
            if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("INV")) {
                if (billTypes.size() > 0) {
                    page = billRegisterImportRepository.findAllByBilldateSubmittedForReport(billTypes, invoice, DateUtils.asLocalDate(search.getInvoiceDateFrom()), DateUtils.asLocalDate(search.getInvoiceDateTo()), supplierName, supplierName, search.getPage());
                } else {
                    page = billRegisterImportRepository.findAllByBilldateSubmittedForReport(billType, invoice, DateUtils.asLocalDate(search.getInvoiceDateFrom()), DateUtils.asLocalDate(search.getInvoiceDateTo()), supplierName, supplierName, search.getPage());
                }
            } else if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("SUB")) {
                LocalDate fromDate = search.getInvoiceDateFrom().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate toDate = search.getInvoiceDateTo().atZone(ZoneId.systemDefault()).toLocalDate();
                if (billTypes.size() > 0) {
                    page = billRegisterImportRepository.findAllByBilldateSubmittedSubForReport(billTypes, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
                } else {
                    page = billRegisterImportRepository.findAllByBilldateSubmittedSubForReport(billType, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
                }
            } else if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("REC")) {
                LocalDate fromDate = search.getInvoiceDateFrom().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate toDate = search.getInvoiceDateTo().atZone(ZoneId.systemDefault()).toLocalDate();
                if (billTypes.size() > 0) {
                    page = billRegisterImportRepository.findAllByBilldateSubmittedRecForReport(billTypes, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
                } else {
                    page = billRegisterImportRepository.findAllByBilldateSubmittedRecForReport(billType, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
                }
            } else {
                if (billTypes.size() > 0) {
                    page = billRegisterImportRepository.findAllByBillnumberSubmittedForReport(billTypes, invoice, supplierName, supplierName, search.getPage());
                } else {
                    page = billRegisterImportRepository.findAllByBillnumberSubmittedForReport(billType, invoice, supplierName, supplierName, search.getPage());
                }
            }
        } else {
            if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("INV")) {
                if (billTypes.size() > 0) {
                    page = billRegisterImportRepository.findAllByBilldateForReport(billTypes, invoice, DateUtils.asLocalDate(search.getInvoiceDateFrom()), DateUtils.asLocalDate(search.getInvoiceDateTo()), supplierName, supplierName, search.getPage());
                } else {
                    page = billRegisterImportRepository.findAllByBilldateForReport(billType, invoice, DateUtils.asLocalDate(search.getInvoiceDateFrom()), DateUtils.asLocalDate(search.getInvoiceDateTo()), supplierName, supplierName, search.getPage());
                }
            } else if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("SUB")) {
                LocalDate fromDate = search.getInvoiceDateFrom().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate toDate = search.getInvoiceDateTo().atZone(ZoneId.systemDefault()).toLocalDate();
                if (billTypes.size() > 0) {
                    page = billRegisterImportRepository.findAllByBilldateSubForReport(billTypes, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
                } else {
                    page = billRegisterImportRepository.findAllByBilldateSubForReport(billType, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
                }
            } else if (search.getInvoiceDateFrom() != null && search.getInvoiceDateTo() != null && search.getDateType().equalsIgnoreCase("REC")) {
                LocalDate fromDate = search.getInvoiceDateFrom().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate toDate = search.getInvoiceDateTo().atZone(ZoneId.systemDefault()).toLocalDate();
                if (billTypes.size() > 0) {
                    page = billRegisterImportRepository.findAllByBilldateRecForReport(billTypes, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
                } else {
                    page = billRegisterImportRepository.findAllByBilldateRecForReport(billType, invoice, fromDate, toDate, supplierName, supplierName, search.getPage());
                }
            } else {
                if (billTypes.size() > 0) {
                    page = billRegisterImportRepository.findAllByBillnumberForReport(billTypes, invoice, supplierName, supplierName, search.getPage());
                } else {
                    page = billRegisterImportRepository.findAllByBillnumberForReport(billType, invoice, supplierName, supplierName, search.getPage());
                }
            }
        }
        List<BillRegisterImportReportBean> billRegisterImportBeans = new ArrayList<>();

        for (Object[] billRegisterImport : page.getContent()) {
            BillRegisterImportReportBean billRegisterImportBean = new BillRegisterImportReportBean();
            billRegisterImportBean.setId((Long) billRegisterImport[0]);
            if(billRegisterImport[1] != null) {
                billRegisterImportBean.setBilldateFormat(simpleDateFormat.format(DateUtils.asDate((LocalDate) billRegisterImport[1])));
            }
            billRegisterImportBean.setSummarizeddescription(billRegisterImport[2] != null ? billRegisterImport[2].toString() : "");
            billRegisterImportBean.setCustomersuppliername(billRegisterImport[3] != null ? billRegisterImport[3].toString() : "");
            billRegisterImportBean.setProjectcode(billRegisterImport[4] != null ? billRegisterImport[4].toString() : "");
            billRegisterImportBean.setBilltype(billRegisterImport[5] != null ? billRegisterImport[5].toString() : "");
            billRegisterImportBean.setBillnumber(billRegisterImport[6] != null ? billRegisterImport[6].toString() : "");
            if(billRegisterImport[7] != null) {
                billRegisterImportBean.setOrderDateFormat(simpleDateFormat.format(DateUtils.asDate((LocalDate) billRegisterImport[7])));
            }
            billRegisterImportBean.setGrossvalue(billRegisterImport[8] != null ? (BigDecimal) billRegisterImport[8] : null);
            billRegisterImportBean.setQuantity(billRegisterImport[9] != null ? (BigDecimal) billRegisterImport[9] : null);
            billRegisterImportBean.setPrice(billRegisterImport[10] != null ? (BigDecimal) billRegisterImport[10] : null);
            billRegisterImportBean.setFxsuppliername(billRegisterImport[11] != null ? billRegisterImport[11].toString() : "");
            billRegisterImportBean.setCode(billRegisterImport[12] != null ? billRegisterImport[12].toString() : "");
            billRegisterImportBean.setRemarks(billRegisterImport[13] != null ? billRegisterImport[13].toString() : "");
            billRegisterImportBeans.add(billRegisterImportBean);
        }

        String path = applicationProperties.getTemplatePath() + "jasper/";
        JasperDesign jasperDesign = JRXmlLoader.load(path + "/bill_register_import.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String, Object> parameters = new HashMap<String, Object>();
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(billRegisterImportBeans);

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
}
