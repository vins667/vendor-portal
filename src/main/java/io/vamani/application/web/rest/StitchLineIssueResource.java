package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.config.Constants;
import io.vamani.application.db2.domain.*;
import io.vamani.application.db2.repository.*;
import io.vamani.application.domain.*;
import io.vamani.application.model.*;
import io.vamani.application.repository.StitchLineIssueDetailsRepository;
import io.vamani.application.repository.CutPlanBundleRepository;
import io.vamani.application.repository.StitchLineIssueRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
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
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

/**
 * REST controller for managing {@link StitchLineIssue}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class StitchLineIssueResource {

    private final Logger log = LoggerFactory.getLogger(StitchLineIssueResource.class);

    private static final String ENTITY_NAME = "stitchLineIssue";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    FactoryRepository factoryRepository;

    private final StitchLineIssueRepository stitchLineIssueRepository;

    @Autowired
    private StitchLineIssueDetailsRepository stitchLineIssueDetailsRepository;

    @Autowired
    private CutPlanBundleRepository cutPlanBundleRepository;

    @Autowired
    private StocktransactionRepository stocktransactionRepository;

    @Autowired
    private StocktransactionimportRepository stocktransactionimportRepository;

    @Autowired
    private AdstorageimportRepository adstorageimportRepository;

    @Autowired
    private AdstorageRepository adstorageRepository;

    @Autowired
    private ProductiondemandRepository productiondemandRepository;

    @Autowired
    private ProductionreservationRepository productionreservationRepository;

    @Autowired
    private LogicalwarehouseRepository logicalwarehouseRepository;

    @Autowired
    private CostcenterRepository costcenterRepository;

    public StitchLineIssueResource(StitchLineIssueRepository stitchLineIssueRepository) {
        this.stitchLineIssueRepository = stitchLineIssueRepository;
    }

    /**
     * {@code POST  /stitch-line-issues} : Create a new stitchLineIssue.
     *
     * @param stitchLineIssue the stitchLineIssue to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new stitchLineIssue, or with status {@code 400 (Bad Request)} if the stitchLineIssue has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/stitch-line-issues")
    public ResponseEntity<StitchLineIssue> createStitchLineIssue(@Valid @RequestBody StitchLineIssueBean stitchLineIssueBean) throws URISyntaxException {
        log.debug("REST request to save StitchLineIssue : {}", stitchLineIssueBean);
        if (stitchLineIssueBean.getId() != null) {
            throw new BadRequestAlertException("A new stitchLineIssue cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StitchLineIssue stitchLineIssue = new StitchLineIssue();
        BeanUtils.copyProperties(stitchLineIssueBean, stitchLineIssue);
        stitchLineIssue.setCreatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
        stitchLineIssue.setCreateddate(Instant.now());
        StitchLineIssue result = stitchLineIssueRepository.save(stitchLineIssue);
        if (result != null && stitchLineIssueBean.getStitchLineIssueDetails() != null && stitchLineIssueBean.getStitchLineIssueDetails().size() > 0) {
            for (StitchLineIssueDetailsBean stitchLineIssueDetailsBean : stitchLineIssueBean.getStitchLineIssueDetails()) {
                if (stitchLineIssueDetailsBean.getSubcode01() != null && stitchLineIssueDetailsBean.getSubcode01().length() > 0) {
                    StitchLineIssueDetails stitchLineIssueDetails = new StitchLineIssueDetails();
                    BeanUtils.copyProperties(stitchLineIssueDetailsBean, stitchLineIssueDetails);
                    stitchLineIssueDetails.setId(null);
                    stitchLineIssueDetails.setDecosubcode01(stitchLineIssueDetailsBean.getSubcode01());
                    stitchLineIssueDetails.setDecosubcode02(stitchLineIssueDetailsBean.getSubcode02());
                    stitchLineIssueDetails.setDecosubcode03(stitchLineIssueDetailsBean.getSubcode03());
                    stitchLineIssueDetails.setDecosubcode04(stitchLineIssueDetailsBean.getSubcode04());
                    stitchLineIssueDetails.setDecosubcode05(stitchLineIssueDetailsBean.getSubcode05());
                    stitchLineIssueDetails.setDecosubcode06(stitchLineIssueDetailsBean.getSubcode06());
                    stitchLineIssueDetails.setDecosubcode07(stitchLineIssueDetailsBean.getSubcode07());
                    stitchLineIssueDetails.setDecosubcode08(stitchLineIssueDetailsBean.getSubcode08());
                    stitchLineIssueDetails.setDecosubcode09(stitchLineIssueDetailsBean.getSubcode09());
                    stitchLineIssueDetails.setDecosubcode10(stitchLineIssueDetailsBean.getSubcode10());
                    stitchLineIssueDetails.setBaseprimaryquantityunit(stitchLineIssueDetailsBean.getPrimaryquantity());
                    stitchLineIssueDetails.setBaseprimaryunitcode(stitchLineIssueDetailsBean.getPrimaryuomcode());
                    stitchLineIssueDetails.setBasesecondaryquantityunit(stitchLineIssueDetailsBean.getSecondaryquantity());
                    stitchLineIssueDetails.setBasesecondaryunitcode(stitchLineIssueDetailsBean.getSecondaryuomcode());
                    stitchLineIssueDetails.setLine(stitchLineIssue.getLine());
                    stitchLineIssueDetails.setLineDesc(stitchLineIssue.getLineDesc());
                    stitchLineIssueDetails.setStocktransactionid(null);
                    CutPlanBundle cutPlanBundle = cutPlanBundleRepository.findById(stitchLineIssueDetailsBean.getId()).orElse(null);
                    if (cutPlanBundle != null) {
                        stitchLineIssueDetails.setBundleCode(cutPlanBundle.getBundleCode());
                        stitchLineIssueDetails.setCutPlanBundleId(cutPlanBundle.getId());
                    }
                    stitchLineIssueDetails.setStitchLineIssue(result);
                    stitchLineIssueDetailsRepository.save(stitchLineIssueDetails);
                }
            }
        }
        return ResponseEntity.created(new URI("/api/stitch-line-issues/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /stitch-line-issues} : Updates an existing stitchLineIssue.
     *
     * @param stitchLineIssue the stitchLineIssue to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated stitchLineIssue,
     * or with status {@code 400 (Bad Request)} if the stitchLineIssue is not valid,
     * or with status {@code 500 (Internal Server Error)} if the stitchLineIssue couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/stitch-line-issues")
    public ResponseEntity<StitchLineIssue> updateStitchLineIssue(@Valid @RequestBody StitchLineIssueBean stitchLineIssueBean) throws URISyntaxException {
        log.debug("REST request to update StitchLineIssue : {}", stitchLineIssueBean);
        if (stitchLineIssueBean.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StitchLineIssue lineIssue = stitchLineIssueRepository.findById(stitchLineIssueBean.getId()).orElse(null);
        StitchLineIssue stitchLineIssue = new StitchLineIssue();
        BeanUtils.copyProperties(stitchLineIssueBean, stitchLineIssue);
        stitchLineIssue.setCreatedby(lineIssue.getCreatedby());
        stitchLineIssue.setCreateddate(lineIssue.getCreateddate());
        stitchLineIssue.setLastupdatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
        stitchLineIssue.setLastupdateddate(Instant.now());
        StitchLineIssue result = stitchLineIssueRepository.save(stitchLineIssue);
        if (result != null && stitchLineIssueBean.getStitchLineIssueDetails() != null && stitchLineIssueBean.getStitchLineIssueDetails().size() > 0) {
            for (StitchLineIssueDetailsBean stitchLineIssueDetailsBean : stitchLineIssueBean.getStitchLineIssueDetails()) {
                if (stitchLineIssueDetailsBean.getSubcode01() != null && stitchLineIssueDetailsBean.getSubcode01().length() > 0) {
                    StitchLineIssueDetails stitchLineIssueDetails = new StitchLineIssueDetails();
                    BeanUtils.copyProperties(stitchLineIssueDetailsBean, stitchLineIssueDetails);
                    stitchLineIssueDetails.setId(stitchLineIssueDetailsBean.getDetailId());
                    stitchLineIssueDetails.setDecosubcode01(stitchLineIssueDetailsBean.getSubcode01());
                    stitchLineIssueDetails.setDecosubcode02(stitchLineIssueDetailsBean.getSubcode02());
                    stitchLineIssueDetails.setDecosubcode03(stitchLineIssueDetailsBean.getSubcode03());
                    stitchLineIssueDetails.setDecosubcode04(stitchLineIssueDetailsBean.getSubcode04());
                    stitchLineIssueDetails.setDecosubcode05(stitchLineIssueDetailsBean.getSubcode05());
                    stitchLineIssueDetails.setDecosubcode06(stitchLineIssueDetailsBean.getSubcode06());
                    stitchLineIssueDetails.setDecosubcode07(stitchLineIssueDetailsBean.getSubcode07());
                    stitchLineIssueDetails.setDecosubcode08(stitchLineIssueDetailsBean.getSubcode08());
                    stitchLineIssueDetails.setDecosubcode09(stitchLineIssueDetailsBean.getSubcode09());
                    stitchLineIssueDetails.setDecosubcode10(stitchLineIssueDetailsBean.getSubcode10());
                    stitchLineIssueDetails.setBaseprimaryquantityunit(stitchLineIssueDetailsBean.getPrimaryquantity());
                    stitchLineIssueDetails.setBaseprimaryunitcode(stitchLineIssueDetailsBean.getPrimaryuomcode());
                    stitchLineIssueDetails.setLine(stitchLineIssue.getLine());
                    stitchLineIssueDetails.setLineDesc(stitchLineIssue.getLineDesc());
                    stitchLineIssueDetails.setBasesecondaryquantityunit(stitchLineIssueDetailsBean.getSecondaryquantity());
                    stitchLineIssueDetails.setBasesecondaryunitcode(stitchLineIssueDetailsBean.getSecondaryuomcode());

                    CutPlanBundle cutPlanBundle = cutPlanBundleRepository.findById(stitchLineIssueDetailsBean.getId()).orElse(null);
                    if (cutPlanBundle != null) {
                        stitchLineIssueDetails.setBundleCode(cutPlanBundle.getBundleCode());
                        stitchLineIssueDetails.setCutPlanBundleId(cutPlanBundle.getId());
                    }
                    stitchLineIssueDetails.setStitchLineIssue(result);
                    stitchLineIssueDetailsRepository.save(stitchLineIssueDetails);
                }
            }
        }
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, stitchLineIssue.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /stitch-line-issues} : get all the stitchLineIssuees.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of stitchLineIssuees in body.
     */
    @GetMapping("/stitch-line-issues")
    public ResponseEntity<List<StitchLineIssue>> getAllStitchLineIssuees(Pageable pageable) {
        log.debug("REST request to get a page of StitchLineIssuees");
        Page<StitchLineIssue> page = stitchLineIssueRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cut-plan-entries} : get all the cutPlanEntries.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cutPlanEntries in body.
     */
    @PostMapping("/stitch-line-issues-filter")
    public ResponseEntity<List<StitchLineIssue>> getAllStitchLineIssueesFilter(@RequestBody CutPlanSearch search) {
        log.debug("REST request to get a page of CutPlanEntries");
        String login = SecurityUtils.getCurrentUserLogin().orElse(null);
        String type = "%";
        if (search.getPono() != null) {
            type = "%" + search.getPono().toUpperCase() + "%";
        }
        String style = "%";
        if (search.getStyle() != null) {
            style = "%" + search.getStyle().toUpperCase() + "%";
        }
        String color = "%";
        if (search.getColor() != null) {
            color = "%" + search.getColor().toUpperCase() + "%";
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
        Page<StitchLineIssue> page = stitchLineIssueRepository.findAllByTypeAndPonoAndStyle(style, color, login, search.getPage());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }


    /**
     * {@code GET  /stitch-line-issues/:id} : get the "id" stitchLineIssue.
     *
     * @param id the id of the stitchLineIssue to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the stitchLineIssue, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/stitch-line-issues/{id}")
    public ResponseEntity<StitchLineIssueBean> getStitchLineIssue(@PathVariable Long id) {
        log.debug("REST request to get StitchLineIssue : {}", id);
        StitchLineIssueBean stitchLineIssueBean = new StitchLineIssueBean();
        StitchLineIssue stitchLineIssue = stitchLineIssueRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(stitchLineIssue, stitchLineIssueBean);
        List<StitchLineIssueDetails> stitchLineIssueDetails = stitchLineIssueDetailsRepository.findAllByStitchLineIssueId(id);
        List<StitchLineIssueDetailsBean> stitchLineIssueDetailsBeans = new ArrayList<>();
        for (StitchLineIssueDetails cutIssueStitchDetail : stitchLineIssueDetails) {
            StitchLineIssueDetailsBean stitchLineIssueDetailsBean = new StitchLineIssueDetailsBean();
            BeanUtils.copyProperties(cutIssueStitchDetail, stitchLineIssueDetailsBean);
            stitchLineIssueDetailsBean.setSubcode01(cutIssueStitchDetail.getDecosubcode01());
            stitchLineIssueDetailsBean.setSubcode02(cutIssueStitchDetail.getDecosubcode02());
            stitchLineIssueDetailsBean.setSubcode03(cutIssueStitchDetail.getDecosubcode03());
            stitchLineIssueDetailsBean.setSubcode04(cutIssueStitchDetail.getDecosubcode04());
            stitchLineIssueDetailsBean.setSubcode05(cutIssueStitchDetail.getDecosubcode05());
            stitchLineIssueDetailsBean.setSubcode06(cutIssueStitchDetail.getDecosubcode06());
            stitchLineIssueDetailsBean.setSubcode07(cutIssueStitchDetail.getDecosubcode07());
            stitchLineIssueDetailsBean.setSubcode08(cutIssueStitchDetail.getDecosubcode08());
            stitchLineIssueDetailsBean.setSubcode09(cutIssueStitchDetail.getDecosubcode09());
            stitchLineIssueDetailsBean.setSubcode10(cutIssueStitchDetail.getDecosubcode10());
            stitchLineIssueDetailsBean.setPrimaryquantity(cutIssueStitchDetail.getBaseprimaryquantityunit());
            stitchLineIssueDetailsBean.setPrimaryuomcode(cutIssueStitchDetail.getBaseprimaryunitcode());
            stitchLineIssueDetailsBean.setSecondaryquantity(cutIssueStitchDetail.getBasesecondaryquantityunit());
            stitchLineIssueDetailsBean.setSecondaryuomcode(cutIssueStitchDetail.getBasesecondaryunitcode());
            stitchLineIssueDetailsBean.setId(cutIssueStitchDetail.getCutPlanBundleId());
            stitchLineIssueDetailsBean.setLine(cutIssueStitchDetail.getLine());
            stitchLineIssueDetailsBean.setLineDesc(cutIssueStitchDetail.getLineDesc());
            stitchLineIssueDetailsBean.setDetailId(cutIssueStitchDetail.getId());
            stitchLineIssueDetailsBeans.add(stitchLineIssueDetailsBean);
        }
        stitchLineIssueBean.setStitchLineIssueDetails(stitchLineIssueDetailsBeans);
        return ResponseUtil.wrapOrNotFound(Optional.of(stitchLineIssueBean));
    }

    /**
     * {@code DELETE  /stitch-line-issues/:id} : delete the "id" stitchLineIssue.
     *
     * @param id the id of the stitchLineIssue to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/stitch-line-issues/{id}")
    public ResponseEntity<Void> deleteStitchLineIssue(@PathVariable Long id) {
        log.debug("REST request to delete StitchLineIssue : {}", id);
        stitchLineIssueRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code DELETE  /cut-plan-issue-stitches/:id} : delete the "id" cutPlanIssueStitch.
     *
     * @param id the id of the cutPlanIssueStitch to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/stitch-line-issue-details/{id}")
    public ResponseEntity<Void> deleteStitchLineIssueDetails(@PathVariable Long id) {
        log.debug("REST request to delete StitchLineIssueDetails : {}", id);
        stitchLineIssueDetailsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    @PostMapping("/stitch-line-issues-transfer")
    public ResponseEntity<StitchLineIssueDetailsBean> postCutBundleEntry(@RequestBody StitchLineIssueDetailsBean stitchLineIssueDetailsBean) throws URISyntaxException {
        StitchLineIssueDetails stitchLineIssueDetail = stitchLineIssueDetailsRepository.findById(stitchLineIssueDetailsBean.getDetailId()).orElse(null);
        StitchLineIssue stitchLineIssue = stitchLineIssueDetail.getStitchLineIssue();
        stitchLineIssue.setLine(stitchLineIssueDetailsBean.getLine());
        stitchLineIssue.setLineDesc(stitchLineIssueDetailsBean.getLineDesc());
        StitchLineIssue result = stitchLineIssueRepository.save(stitchLineIssue);
        if (result != null) {
            List<StitchLineIssueDetails> stitchLineIssueDetails = stitchLineIssueDetailsRepository.findAllByStitchLineIssueId(result.getId());
            for (StitchLineIssueDetails lineIssueDetail : stitchLineIssueDetails) {
                Stocktransactionimport stocktransactionimport = stocktransactionimportRepository.findById(new StocktransactionimportId(Constants.COMPANY_CODE, lineIssueDetail.getStocktransactionid().toString(), 1)).orElse(null);
                if (stocktransactionimport != null) {
                    Stocktransaction stocktransaction = stocktransactionRepository.findById(new StocktransactionId(Constants.COMPANY_CODE, stocktransactionimport.getNowtrnnumbertransactionnumber(), 1)).orElse(null);
                    if (stocktransaction != null) {
                        Adstorage adstorage = adstorageRepository.findById(new AdstorageId(stocktransaction.getAbsuniqueid(), "StockTransaction", "Sewing", "Sewing")).orElse(null);
                        adstorage.setValuestring(stitchLineIssueDetailsBean.getLine());
                        Adstorage adstorage1 = adstorageRepository.save(adstorage);
                        if (adstorage1 != null) {
                            lineIssueDetail.setLine(stitchLineIssueDetailsBean.getLine());
                            lineIssueDetail.setLineDesc(stitchLineIssueDetailsBean.getLineDesc());
                            stitchLineIssueDetailsRepository.save(lineIssueDetail);
                        }
                    }
                }
            }
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(stitchLineIssueDetailsBean));
    }


    @GetMapping("/stitch-line-issues-post/{id}")
    public ResponseEntity<StitchLineIssueBean> postCutBundleEntry(@PathVariable Long id) throws URISyntaxException {
        log.debug("REST request to save CutBundleEntry : {}", id);
        StitchLineIssueBean stitchLineIssueBean = new StitchLineIssueBean();
        StitchLineIssue stitchLineIssue = stitchLineIssueRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(stitchLineIssue, stitchLineIssueBean);
        String costcentercode = costcenterRepository.findSewingCostCenter(stitchLineIssue.getPlantCode().trim());
        List<StitchLineIssueDetails> stitchLineIssueDetails = stitchLineIssueDetailsRepository.findAllByStitchLineIssueId(id);
        List<StitchLineIssueDetailsBean> stitchLineIssueDetailsBeans = new ArrayList<>();
        Logicalwarehouse logicalwarehouse = null;
        if(stitchLineIssue.getProductionCode().startsWith("SG")) {
            logicalwarehouse = logicalwarehouseRepository.findInTransitWarehouseByPlantCode(stitchLineIssue.getPlantCode());
        } else {
            logicalwarehouse = logicalwarehouseRepository.findInTransitHFCWarehouseByPlantCode(stitchLineIssue.getPlantCode());
        }
        if (logicalwarehouse != null) {
            for (StitchLineIssueDetails stitchLineIssueDetail : stitchLineIssueDetails) {
                if (stitchLineIssueDetail.getStocktransactionid() == null) {
                    String demandcode = productionreservationRepository.findProductionDemandCode(Constants.COMPANY_CODE, stitchLineIssue.getProductionCode(), stitchLineIssueDetail.getItemtypecode(), stitchLineIssueDetail.getDecosubcode01().trim(), stitchLineIssueDetail.getDecosubcode02().trim(), stitchLineIssueDetail.getDecosubcode03().trim(), stitchLineIssueDetail.getDecosubcode04().trim(), stitchLineIssueDetail.getDecosubcode05().trim(), stitchLineIssueDetail.getDecosubcode06().trim(), stitchLineIssueDetail.getDecosubcode07().trim(), stitchLineIssueDetail.getDecosubcode08().trim(), stitchLineIssueDetail.getDecosubcode09().trim(), stitchLineIssueDetail.getDecosubcode10().trim());
                    if (demandcode != null) {
                        Productiondemand productiondemand = productiondemandRepository.findByDemandCode(Constants.COMPANY_CODE, demandcode);
                        if (productiondemand != null) {
                            List<Productionreservation> productionreservations = productionreservationRepository.findAllProductionResverationByProductiondemandCodeAndCpt(productiondemand.getId().getCompanycode(), productiondemand.getId().getCountercode(), productiondemand.getId().getCode(), stitchLineIssue.getProductionCode());
                            if (productionreservations != null && productionreservations.size() > 0) {
                                Long stockTransactionDestinationId = stocktransactionimportRepository.getNextSequence();
                                Long parentAdditionIdIssue = adstorageimportRepository.getNextSequence();
                                Stocktransactionimport stocktransactionimport = to(Constants.COMPANY_CODE, productiondemand, stitchLineIssue, stitchLineIssueDetail, stockTransactionDestinationId, parentAdditionIdIssue, 1, "M02", "0", logicalwarehouse, productionreservations.get(0).getId().getReservationline(), costcentercode);
                                Stocktransactionimport resultdestination = stocktransactionimportRepository.save(stocktransactionimport);
                                if (resultdestination != null) {
                                    Adstorageimport adstorageimportBundleNo = new Adstorageimport(new AdstorageimportId("StockTransaction", parentAdditionIdIssue, "StockTransaction", "InterfaceBundleNumber", "InterfaceBundleNumber"), 0, 0, 0, 0, stitchLineIssueDetail.getBundleCode(), 0, (short) 0, null, new BigDecimal(0), 0L, null, null, 0L);
                                    adstorageimportRepository.save(adstorageimportBundleNo);

                                    Adstorageimport adstorageimportDestinationIssue = new Adstorageimport(new AdstorageimportId("StockTransaction", parentAdditionIdIssue, "StockTransaction", "Destination", "Destination"), 0, 0, 0, 0, stitchLineIssue.getDestination(), 0, (short) 0, null, new BigDecimal(0), 0L, null, null, 0L);
                                    adstorageimportRepository.save(adstorageimportDestinationIssue);

                                    Adstorageimport adstorageimportLineIssue = new Adstorageimport(new AdstorageimportId("StockTransaction", parentAdditionIdIssue, "StockTransaction", "Sewing", "Sewing"), 0, 0, 0, 0, stitchLineIssue.getLine(), 0, (short) 0, null, new BigDecimal(0), 0L, null, null, 0L);
                                    adstorageimportRepository.save(adstorageimportLineIssue);

                                    stocktransactionimport.setImportstatus(1);
                                    stocktransactionimportRepository.save(stocktransactionimport);

                                    stitchLineIssueDetail.setStocktransactionid(stockTransactionDestinationId);
                                    stitchLineIssueDetail = stitchLineIssueDetailsRepository.save(stitchLineIssueDetail);
                                    StitchLineIssueDetailsBean stitchLineIssueDetailsBean = new StitchLineIssueDetailsBean();
                                    BeanUtils.copyProperties(stitchLineIssueDetail, stitchLineIssueDetailsBean);
                                    stitchLineIssueDetailsBeans.add(stitchLineIssueDetailsBean);
                                }
                            }
                        } else {
                            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "Not suitable Demand exist for size.")).build();
                        }
                    } else {
                        return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "Not suitable Demand exist for size.")).build();
                    }
                }
                if (stitchLineIssueDetails.size() == stitchLineIssueDetailsBeans.size()) {
                    stitchLineIssue.setPostedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                    stitchLineIssue.setPostedDate(Instant.now());
                    stitchLineIssueRepository.save(stitchLineIssue);
                    stitchLineIssueBean.setStitchLineIssueDetails(stitchLineIssueDetailsBeans);
                }
            }
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(stitchLineIssueBean));
    }


    public static Stocktransactionimport to(String companycode, Productiondemand productiondemand, StitchLineIssue stitchLineIssue, StitchLineIssueDetails stitchLineIssueDetail, Long stockTransactionId, Long parentAdditionId, Integer lineNo, String templatecode, String detailType, Logicalwarehouse logicalwarehouse, Integer groupline, String costcentercode) {
        Stocktransactionimport stocktransactionimport = new Stocktransactionimport();
        stocktransactionimport.setId(new StocktransactionimportId(Constants.COMPANY_CODE, stockTransactionId.toString(), lineNo));
        stocktransactionimport.setImportstatus(0);
        stocktransactionimport.setNowtrnnumbertransactionnumber("");
        stocktransactionimport.setTransactionstatus("0");
        stocktransactionimport.setItemtypecompanycode(companycode);
        stocktransactionimport.setItemtypecode(stitchLineIssueDetail.getItemtypecode());
        stocktransactionimport.setTransactiondate(new Date(System.currentTimeMillis()));
        stocktransactionimport.setTransactiontime(new Time(System.currentTimeMillis()));
        stocktransactionimport.setDetailtype(detailType);
        stocktransactionimport.setTemplatecompanycode(companycode);
        stocktransactionimport.setTemplatecode(templatecode);
        stocktransactionimport.setAutoissuetemplatecompanycode("");
        stocktransactionimport.setAutoissuetemplatecode("");
        stocktransactionimport.setStocktransactiontype("1");
        stocktransactionimport.setDecocompanycode(companycode);
        stocktransactionimport.setDecosubcode01(stitchLineIssueDetail.getDecosubcode01());
        stocktransactionimport.setDecosubcode02(stitchLineIssueDetail.getDecosubcode02());
        stocktransactionimport.setDecosubcode03(stitchLineIssueDetail.getDecosubcode03());
        stocktransactionimport.setDecosubcode04(stitchLineIssueDetail.getDecosubcode04());
        stocktransactionimport.setDecosubcode05(stitchLineIssueDetail.getDecosubcode05());
        stocktransactionimport.setDecosubcode06(stitchLineIssueDetail.getDecosubcode06());
        stocktransactionimport.setDecosubcode07(stitchLineIssueDetail.getDecosubcode07());
        stocktransactionimport.setDecosubcode08(stitchLineIssueDetail.getDecosubcode08());
        stocktransactionimport.setDecosubcode09(stitchLineIssueDetail.getDecosubcode09());
        stocktransactionimport.setDecosubcode10(stitchLineIssueDetail.getDecosubcode10());
        stocktransactionimport.setItemdescription("");
        stocktransactionimport.setLogicalwarehousecompanycode(companycode);
        stocktransactionimport.setLogicalwarehousecode(logicalwarehouse.getId().getCode().trim());
        stocktransactionimport.setTransferallocation("");
        stocktransactionimport.setIssueqtyfrombalance((short) 0);
        stocktransactionimport.setUserprimaryuomcode(stitchLineIssueDetail.getBaseprimaryunitcode());
        stocktransactionimport.setUserprimaryquantity(new BigDecimal(stitchLineIssueDetail.getBaseprimaryquantityunit()));
        stocktransactionimport.setUsersecondaryuomcode(stitchLineIssueDetail.getBasesecondaryunitcode());
        stocktransactionimport.setUsersecondaryquantity(new BigDecimal(stitchLineIssueDetail.getBasesecondaryquantityunit()));
        /*  stocktransactionimport.setUserpackaginguomcode("");
            stocktransactionimport.setUserpackagingquantity("");*/
        stocktransactionimport.setWeightunitofmeasurecode("");
        stocktransactionimport.setWeightgross(new BigDecimal("0"));
        stocktransactionimport.setWeightnet(new BigDecimal("0"));
        stocktransactionimport.setWeightrealnet(new BigDecimal("0"));
        /*  stocktransactionimport.setDerivationcode();
            stocktransactionimport.setDerivationlinenumber();
            stocktransactionimport.setDerivationcomponentlinenumber();*/

        stocktransactionimport.setQualitylvlitemtypecompanycode(companycode);
        stocktransactionimport.setQualitylevelcode(new BigInteger("1"));
        /*  stocktransactionimport.setQualityreasoncompanycode();
            stocktransactionimport.setQualityreasoncode();
            stocktransactionimport.setFirstqualitycontroldate();
            stocktransactionimport.setFirstqualitycontrolcounter();
            stocktransactionimport.setFirstqualitycontrolnumber();*/
        stocktransactionimport.setPhysicalwarehousecompanycode(companycode);
        stocktransactionimport.setPhysicalwarehousecode(logicalwarehouse.getPhysicalwarehousecode());
        stocktransactionimport.setWhslocwhszonephywhscmycode(companycode);
        stocktransactionimport.setWhslocationwarehousezonecode(null);
        stocktransactionimport.setWarehouselocationcode(null);
        stocktransactionimport.setContainercompanycode(companycode);
        stocktransactionimport.setContaineritemtypecode("");
        stocktransactionimport.setContainersubcode01("");
        stocktransactionimport.setContainerelementcompanycode("");
        stocktransactionimport.setContainerelementcode("");
        stocktransactionimport.setLotcompanycode(companycode);
        stocktransactionimport.setLotcode(stitchLineIssueDetail.getLotcode());
        stocktransactionimport.setItemelementcompanycode(companycode);
        stocktransactionimport.setItemelementsubcodekey("");
        stocktransactionimport.setItemelementcode(stitchLineIssueDetail.getElementscode());
        stocktransactionimport.setProjectcode(stitchLineIssueDetail.getProjectcode());
        stocktransactionimport.setCostcentercompanycode(companycode);
        stocktransactionimport.setCostcentercode(costcentercode);
        stocktransactionimport.setStatisticalgroupcompanycode(companycode);
        stocktransactionimport.setStatisticalgroupcode("");
        stocktransactionimport.setCost(new BigDecimal("0"));
        stocktransactionimport.setCurrencycode("");
        stocktransactionimport.setCustomertype("");
        stocktransactionimport.setCustomercode("");
        stocktransactionimport.setSuppliertype("");
        stocktransactionimport.setSuppliercode("");
        stocktransactionimport.setBilldate(null);
        stocktransactionimport.setBilltype((short) 0);
        stocktransactionimport.setBillcounter("");
        stocktransactionimport.setBillcode("");
        stocktransactionimport.setInternaldocumentdate(null);
        stocktransactionimport.setInternaldocumentnumber(0);
        stocktransactionimport.setOrdercountercompanycode("");
        stocktransactionimport.setOrdercountercode(productiondemand.getId().getCountercode());
        stocktransactionimport.setOrdercode(productiondemand.getId().getCode());
        stocktransactionimport.setOrderline(Integer.parseInt(groupline.toString()));
        stocktransactionimport.setOrdersubline(0);
        stocktransactionimport.setOrdercomponentline(0);
        stocktransactionimport.setOrderdeliveryline(0);
        stocktransactionimport.setProductionordercode(stitchLineIssue.getProductionCode());
        stocktransactionimport.setReturncode("");
        stocktransactionimport.setReturnline(0);
        stocktransactionimport.setTokencode("");
        stocktransactionimport.setEnvcodeskipblcexp("");
        stocktransactionimport.setAduniqueid(parentAdditionId);
        stocktransactionimport.setAduniqueidforautoissue(parentAdditionId);
        stocktransactionimport.setCreationdatetime(Timestamp.from(Instant.now()));
        stocktransactionimport.setCreationuser("system");
        stocktransactionimport.setLastupdatedatetime(null);
        stocktransactionimport.setLastupdateuser(null);
        stocktransactionimport.setAbsuniqueid(0L);
        return stocktransactionimport;
    }

    @GetMapping("/stitch-line-issues-pdf/{id}")
    public void getIssuePdf(@PathVariable Long id, HttpServletResponse response){
        if(id != null && id.intValue()>0){
            try{
                // DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                List<StitchLineIssueBean> stitchLineIssueBeans = new ArrayList<>();
                StitchLineIssue stitchLineIssue = stitchLineIssueRepository.findById(id).orElse(null);
                StitchLineIssueBean stitchLineIssueBean = new StitchLineIssueBean();
                BeanUtils.copyProperties(stitchLineIssue, stitchLineIssueBean);
                Factory factory = factoryRepository.findById(stitchLineIssueBean.getPlantCode()).orElse(null);
                String add1 = (factory.getAddressline1() != null ? factory.getAddressline1().trim() : "") + (factory.getAddressline2() != null ? factory.getAddressline2().trim() : "");
                stitchLineIssueBean.setPlantAddress(add1);
                stitchLineIssueBean.setCreateddate(Date.from(stitchLineIssue.getCreateddate()));
                Factory factoryDest = factoryRepository.findById(stitchLineIssueBean.getDestinationPlantCode()!= null ? stitchLineIssueBean.getDestinationPlantCode() : stitchLineIssueBean.getPlantCode()).orElse(null);
                String add2 = (factoryDest.getAddressline1() != null ? factoryDest.getAddressline1().trim() : "") + (factoryDest.getAddressline2() != null ? factoryDest.getAddressline2().trim() : "");
                stitchLineIssueBean.setDestinationPlantAddress(add2);
                if (stitchLineIssueBean != null && stitchLineIssueBean.getId() > 0) {
                    List<StitchLineIssueDetails> stitchLineIssueDetails = stitchLineIssueDetailsRepository.findAllByStitchLineIssueId(stitchLineIssueBean.getId());
                    if (stitchLineIssueDetails != null && stitchLineIssueDetails.size() > 0) {
                        List<StitchLineIssueDetailsBean> stitchLineIssueDetailsBeans = new ArrayList<>();
                        for (StitchLineIssueDetails lines : stitchLineIssueDetails) {
                            StitchLineIssueDetailsBean stitchLineIssueDetailsBean = new StitchLineIssueDetailsBean();
                            BeanUtils.copyProperties(lines, stitchLineIssueDetailsBean);
                            stitchLineIssueDetailsBean.setProductionCode(stitchLineIssueBean.getProductionCode());
                            stitchLineIssueDetailsBean.setSubcode09(lines.getDecosubcode07());
                            stitchLineIssueDetailsBean.setSubcode10(lines.getDecosubcode08());
                            stitchLineIssueDetailsBean.setSubcode01(lines.getDecosubcode01());
                            stitchLineIssueDetailsBeans.add(stitchLineIssueDetailsBean);
                        }
                        stitchLineIssueBean.setStitchLineIssueDetails(stitchLineIssueDetailsBeans);
                    }
                    stitchLineIssueBeans.add(stitchLineIssueBean);
                    String path = applicationProperties.getTemplatePath() + "jasper/";
                    JasperDesign jasperDesign = JRXmlLoader.load(path + "/IssueToLine.jrxml");
                    JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                    Map<String, Object> parameters = new HashMap<>();
                    JRDataSource jrDataSource = new JRBeanCollectionDataSource(stitchLineIssueBeans);
                    parameters.put("datasource", jrDataSource);
                    parameters.put("SUBREPORT_DIR", path);
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
                    response.setContentType("application/x-pdf");
                    response.setHeader("Content-Disposition", "attachment; filename=IssueToLine.pdf");
                    final OutputStream outputStream = response.getOutputStream();
                    JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    @PostMapping("/stitch-line-issues-report")
    @Timed
    public @ResponseBody
    void getLeaveDiffReport(@Valid @RequestBody(required = false) MasterSearch search, HttpServletResponse response) throws JRException, IOException, ParseException {
        List<Object[]> listObject = stitchLineIssueRepository.findAllByProject(search.getCode());

        List<StitchIssueLineBean> stitchLineIssueBeans = new ArrayList<>();
        if (listObject != null && listObject.size() > 0) {
            for (Object[] object : listObject) {
                StitchIssueLineBean stitchIssueLineBean = new StitchIssueLineBean();
                stitchIssueLineBean.setPlantCode(object[0].toString());
                stitchIssueLineBean.setPlantDescription(object[1].toString());
                stitchIssueLineBean.setProjectcode(object[2].toString());
                stitchIssueLineBean.setStyle(object[3].toString());
                stitchIssueLineBean.setColor(object[4].toString());
                stitchIssueLineBean.setDestination(object[5].toString());
                stitchIssueLineBean.setSizeCode(object[6].toString());
                stitchIssueLineBean.setLine(object[7].toString());
                stitchIssueLineBean.setLineDescription(object[8].toString());
                stitchIssueLineBean.setBundleCode(object[9].toString());
                stitchIssueLineBean.setCutPlanBundleId(((BigInteger) object[10]).longValue());
                stitchIssueLineBean.setBaseprimaryquantityunit(new BigDecimal((Double) object[11]));
                stitchIssueLineBean.setProductCode(object[12].toString());
                stitchIssueLineBean.setProductId(((BigInteger) object[13]).longValue());
                stitchLineIssueBeans.add(stitchIssueLineBean);
            }
        }
        String path = applicationProperties.getTemplatePath() + "jasper/";
        JasperDesign jasperDesign = JRXmlLoader.load(path + "/StitchLineIssue.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String, Object> parameters = new HashMap<String, Object>();
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(stitchLineIssueBeans);

        parameters.put("datasource", jrDataSource);
        parameters.put("SUBREPORT_DIR", path);
        parameters.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=stitchLineIssue.xlsx");

        final OutputStream outputStream = response.getOutputStream();
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
        exporter.exportReport();
    }
}
