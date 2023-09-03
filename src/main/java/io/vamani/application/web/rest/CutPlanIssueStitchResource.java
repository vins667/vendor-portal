package io.vamani.application.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.config.Constants;
import io.vamani.application.db2.domain.*;
import io.vamani.application.db2.repository.*;
import io.vamani.application.domain.*;
import io.vamani.application.model.CutIssueStitchDetailsBean;
import io.vamani.application.model.CutPlanBundleMatrixBreakup;
import io.vamani.application.model.CutPlanIssueStitchBean;
import io.vamani.application.model.CutPlanSearch;
import io.vamani.application.repository.CutIssueStitchDetailsRepository;
import io.vamani.application.repository.CutPlanBundleRepository;
import io.vamani.application.repository.CutPlanIssueStitchRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
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
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.*;

/**
 * REST controller for managing {@link CutPlanIssueStitch}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CutPlanIssueStitchResource {

    private final Logger log = LoggerFactory.getLogger(CutPlanIssueStitchResource.class);

    private static final String ENTITY_NAME = "cutPlanIssueStitch";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CutPlanIssueStitchRepository cutPlanIssueStitchRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private CutIssueStitchDetailsRepository cutIssueStitchDetailsRepository;

    @Autowired
    private CutPlanBundleRepository cutPlanBundleRepository;

    @Autowired
    FactoryRepository factoryRepository;

    @Autowired
    private StocktransactionRepository stocktransactionRepository;

    @Autowired
    private StocktransactionimportRepository stocktransactionimportRepository;

    @Autowired
    private AdstorageimportRepository adstorageimportRepository;

    @Autowired
    private ProductiondemandRepository productiondemandRepository;

    @Autowired
    private LogicalwarehouseRepository logicalwarehouseRepository;

    @Autowired
    private StocktransactiontemplateRepository stocktransactiontemplateRepository;

    public CutPlanIssueStitchResource(CutPlanIssueStitchRepository cutPlanIssueStitchRepository) {
        this.cutPlanIssueStitchRepository = cutPlanIssueStitchRepository;
    }

    /**
     * {@code POST  /cut-plan-issue-stitches} : Create a new cutPlanIssueStitch.
     *
     * @param cutPlanIssueStitch the cutPlanIssueStitch to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cutPlanIssueStitch, or with status {@code 400 (Bad Request)} if the cutPlanIssueStitch has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cut-plan-issue-stitches")
    public ResponseEntity<CutPlanIssueStitch> createCutPlanIssueStitch(@Valid @RequestBody CutPlanIssueStitchBean cutPlanIssueStitchBean) throws URISyntaxException {
        log.debug("REST request to save CutPlanIssueStitch : {}", cutPlanIssueStitchBean);
        if (cutPlanIssueStitchBean.getId() != null) {
            throw new BadRequestAlertException("A new cutPlanIssueStitch cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CutPlanIssueStitch cutPlanIssueStitch = new CutPlanIssueStitch();
        BeanUtils.copyProperties(cutPlanIssueStitchBean, cutPlanIssueStitch);
        cutPlanIssueStitch.setCreatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
        cutPlanIssueStitch.setCreateddate(Instant.now());
        CutPlanIssueStitch result = cutPlanIssueStitchRepository.save(cutPlanIssueStitch);
        if (result != null && cutPlanIssueStitchBean.getCutIssueStitchDetails() != null && cutPlanIssueStitchBean.getCutIssueStitchDetails().size() > 0) {
            for (CutIssueStitchDetailsBean cutIssueStitchDetailsBean : cutPlanIssueStitchBean.getCutIssueStitchDetails()) {
                if (cutIssueStitchDetailsBean.getSubcode01() != null && cutIssueStitchDetailsBean.getSubcode01().length() > 0) {
                    CutIssueStitchDetails cutIssueStitchDetails = new CutIssueStitchDetails();
                    BeanUtils.copyProperties(cutIssueStitchDetailsBean, cutIssueStitchDetails);
                    cutIssueStitchDetails.setId(null);
                    cutIssueStitchDetails.setDecosubcode01(cutIssueStitchDetailsBean.getSubcode01());
                    cutIssueStitchDetails.setDecosubcode02(cutIssueStitchDetailsBean.getSubcode02());
                    cutIssueStitchDetails.setDecosubcode03(cutIssueStitchDetailsBean.getSubcode03());
                    cutIssueStitchDetails.setDecosubcode04(cutIssueStitchDetailsBean.getSubcode04());
                    cutIssueStitchDetails.setDecosubcode05(cutIssueStitchDetailsBean.getSubcode05());
                    cutIssueStitchDetails.setDecosubcode06(cutIssueStitchDetailsBean.getSubcode06());
                    cutIssueStitchDetails.setDecosubcode07(cutIssueStitchDetailsBean.getSubcode07());
                    cutIssueStitchDetails.setDecosubcode08(cutIssueStitchDetailsBean.getSubcode08());
                    cutIssueStitchDetails.setDecosubcode09(cutIssueStitchDetailsBean.getSubcode09());
                    cutIssueStitchDetails.setDecosubcode10(cutIssueStitchDetailsBean.getSubcode10());
                    cutIssueStitchDetails.setBaseprimaryquantityunit(cutIssueStitchDetailsBean.getPrimaryquantity());
                    cutIssueStitchDetails.setBaseprimaryunitcode(cutIssueStitchDetailsBean.getPrimaryuomcode());
                    cutIssueStitchDetails.setBasesecondaryquantityunit(cutIssueStitchDetailsBean.getSecondaryquantity());
                    cutIssueStitchDetails.setBasesecondaryunitcode(cutIssueStitchDetailsBean.getSecondaryuomcode());

                    CutPlanBundle cutPlanBundle = cutPlanBundleRepository.findById(cutIssueStitchDetailsBean.getId()).orElse(null);
                    if(cutPlanBundle != null) {
                        cutIssueStitchDetails.setBundleCode(cutPlanBundle.getBundleCode());
                        cutIssueStitchDetails.setCutPlanBundleId(cutPlanBundle.getId());
                    }
                    cutIssueStitchDetails.setCutPlanIssueStitch(result);
                    cutIssueStitchDetails.setStocktransactionid(null);
                    cutIssueStitchDetailsRepository.save(cutIssueStitchDetails);
                }
            }
        }
        return ResponseEntity.created(new URI("/api/cut-plan-issue-stitches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cut-plan-issue-stitches} : Updates an existing cutPlanIssueStitch.
     *
     * @param cutPlanIssueStitch the cutPlanIssueStitch to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cutPlanIssueStitch,
     * or with status {@code 400 (Bad Request)} if the cutPlanIssueStitch is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cutPlanIssueStitch couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cut-plan-issue-stitches")
    public ResponseEntity<CutPlanIssueStitch> updateCutPlanIssueStitch(@Valid @RequestBody CutPlanIssueStitchBean cutPlanIssueStitchBean) throws URISyntaxException {
        log.debug("REST request to update CutPlanIssueStitch : {}", cutPlanIssueStitchBean);
        if (cutPlanIssueStitchBean.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        CutPlanIssueStitch cutPlanIssueStitch = new CutPlanIssueStitch();
        BeanUtils.copyProperties(cutPlanIssueStitchBean, cutPlanIssueStitch);
        cutPlanIssueStitch.setLastupdatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
        cutPlanIssueStitch.setLastupdateddate(Instant.now());
        CutPlanIssueStitch result = cutPlanIssueStitchRepository.save(cutPlanIssueStitch);
        if (result != null && cutPlanIssueStitchBean.getCutIssueStitchDetails() != null && cutPlanIssueStitchBean.getCutIssueStitchDetails().size() > 0) {
            for (CutIssueStitchDetailsBean cutIssueStitchDetailsBean : cutPlanIssueStitchBean.getCutIssueStitchDetails()) {
                if (cutIssueStitchDetailsBean.getSubcode01() != null && cutIssueStitchDetailsBean.getSubcode01().length() > 0) {
                    CutIssueStitchDetails cutIssueStitchDetails = new CutIssueStitchDetails();
                    BeanUtils.copyProperties(cutIssueStitchDetailsBean, cutIssueStitchDetails);
                    cutIssueStitchDetails.setId(cutIssueStitchDetailsBean.getDetailId());
                    cutIssueStitchDetails.setDecosubcode01(cutIssueStitchDetailsBean.getSubcode01());
                    cutIssueStitchDetails.setDecosubcode02(cutIssueStitchDetailsBean.getSubcode02());
                    cutIssueStitchDetails.setDecosubcode03(cutIssueStitchDetailsBean.getSubcode03());
                    cutIssueStitchDetails.setDecosubcode04(cutIssueStitchDetailsBean.getSubcode04());
                    cutIssueStitchDetails.setDecosubcode05(cutIssueStitchDetailsBean.getSubcode05());
                    cutIssueStitchDetails.setDecosubcode06(cutIssueStitchDetailsBean.getSubcode06());
                    cutIssueStitchDetails.setDecosubcode07(cutIssueStitchDetailsBean.getSubcode07());
                    cutIssueStitchDetails.setDecosubcode08(cutIssueStitchDetailsBean.getSubcode08());
                    cutIssueStitchDetails.setDecosubcode09(cutIssueStitchDetailsBean.getSubcode09());
                    cutIssueStitchDetails.setDecosubcode10(cutIssueStitchDetailsBean.getSubcode10());
                    cutIssueStitchDetails.setBaseprimaryquantityunit(cutIssueStitchDetailsBean.getPrimaryquantity());
                    cutIssueStitchDetails.setBaseprimaryunitcode(cutIssueStitchDetailsBean.getPrimaryuomcode());
                    cutIssueStitchDetails.setBasesecondaryquantityunit(cutIssueStitchDetailsBean.getSecondaryquantity());
                    cutIssueStitchDetails.setBasesecondaryunitcode(cutIssueStitchDetailsBean.getSecondaryuomcode());

                    CutPlanBundle cutPlanBundle = cutPlanBundleRepository.findById(cutIssueStitchDetailsBean.getId()).orElse(null);
                    if(cutPlanBundle != null) {
                        cutIssueStitchDetails.setBundleCode(cutPlanBundle.getBundleCode());
                        cutIssueStitchDetails.setCutPlanBundleId(cutPlanBundle.getId());
                    }
                    cutIssueStitchDetails.setStocktransactionid(null);
                    cutIssueStitchDetails.setCutPlanIssueStitch(result);
                    cutIssueStitchDetailsRepository.save(cutIssueStitchDetails);
                }
            }
        }
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, cutPlanIssueStitch.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cut-plan-issue-stitches} : get all the cutPlanIssueStitches.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cutPlanIssueStitches in body.
     */
    @GetMapping("/cut-plan-issue-stitches")
    public ResponseEntity<List<CutPlanIssueStitch>> getAllCutPlanIssueStitches(Pageable pageable) {
        log.debug("REST request to get a page of CutPlanIssueStitches");
        Page<CutPlanIssueStitch> page = cutPlanIssueStitchRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cut-plan-entries} : get all the cutPlanEntries.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cutPlanEntries in body.
     */
    @PostMapping("/cut-plan-issue-stitches-filter")
    public ResponseEntity<List<CutPlanIssueStitch>> getAllCutPlanIssueStitchesFilter(@RequestBody CutPlanSearch search) {
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
        Page<CutPlanIssueStitch> page = cutPlanIssueStitchRepository.findAllByTypeAndPonoAndStyle(type, style, color, login, search.getPage());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cut-plan-entries} : get all the cutPlanEntries.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cutPlanEntries in body.
     */
    @PostMapping("/cut-plan-reciept-stitches-filter")
    public ResponseEntity<List<CutPlanIssueStitch>> getAllCutPlanRecieptStitchesFilter(@RequestBody CutPlanSearch search) {
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
        Page<CutPlanIssueStitch> page = cutPlanIssueStitchRepository.findAllByTypeAndPonoAndStylePostedOnly(type, style, color, login, search.getPage());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cut-plan-issue-stitches/:id} : get the "id" cutPlanIssueStitch.
     *
     * @param id the id of the cutPlanIssueStitch to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cutPlanIssueStitch, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cut-plan-issue-stitches/{id}")
    public ResponseEntity<CutPlanIssueStitchBean> getCutPlanIssueStitch(@PathVariable Long id) {
        log.debug("REST request to get CutPlanIssueStitch : {}", id);
        CutPlanIssueStitchBean cutPlanIssueStitchBean = new CutPlanIssueStitchBean();
        CutPlanIssueStitch cutPlanIssueStitch = cutPlanIssueStitchRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(cutPlanIssueStitch, cutPlanIssueStitchBean);
        List<CutIssueStitchDetails> cutIssueStitchDetails = cutIssueStitchDetailsRepository.findAllByCutPlanIssueStitchId(id);
        List<CutIssueStitchDetailsBean> cutIssueStitchDetailsBeans = new ArrayList<>();
        for (CutIssueStitchDetails cutIssueStitchDetail : cutIssueStitchDetails) {
            CutIssueStitchDetailsBean cutIssueStitchDetailsBean = new CutIssueStitchDetailsBean();
            BeanUtils.copyProperties(cutIssueStitchDetail, cutIssueStitchDetailsBean);
            cutIssueStitchDetailsBean.setSubcode01(cutIssueStitchDetail.getDecosubcode01());
            cutIssueStitchDetailsBean.setSubcode02(cutIssueStitchDetail.getDecosubcode02());
            cutIssueStitchDetailsBean.setSubcode03(cutIssueStitchDetail.getDecosubcode03());
            cutIssueStitchDetailsBean.setSubcode04(cutIssueStitchDetail.getDecosubcode04());
            cutIssueStitchDetailsBean.setSubcode05(cutIssueStitchDetail.getDecosubcode05());
            cutIssueStitchDetailsBean.setSubcode06(cutIssueStitchDetail.getDecosubcode06());
            cutIssueStitchDetailsBean.setSubcode07(cutIssueStitchDetail.getDecosubcode07());
            cutIssueStitchDetailsBean.setSubcode08(cutIssueStitchDetail.getDecosubcode08());
            cutIssueStitchDetailsBean.setSubcode09(cutIssueStitchDetail.getDecosubcode09());
            cutIssueStitchDetailsBean.setSubcode10(cutIssueStitchDetail.getDecosubcode10());
            cutIssueStitchDetailsBean.setPrimaryquantity(cutIssueStitchDetail.getBaseprimaryquantityunit());
            cutIssueStitchDetailsBean.setPrimaryuomcode(cutIssueStitchDetail.getBaseprimaryunitcode());
            cutIssueStitchDetailsBean.setSecondaryquantity(cutIssueStitchDetail.getBasesecondaryquantityunit());
            cutIssueStitchDetailsBean.setSecondaryuomcode(cutIssueStitchDetail.getBasesecondaryunitcode());
            cutIssueStitchDetailsBean.setId(cutIssueStitchDetail.getCutPlanBundleId());
            cutIssueStitchDetailsBean.setDetailId(cutIssueStitchDetail.getId());
            cutIssueStitchDetailsBeans.add(cutIssueStitchDetailsBean);
        }
        cutPlanIssueStitchBean.setCutIssueStitchDetails(cutIssueStitchDetailsBeans);
        return ResponseUtil.wrapOrNotFound(Optional.of(cutPlanIssueStitchBean));
    }

    /**
     * {@code GET  /cut-plan-issue-stitches/:id} : get the "id" cutPlanIssueStitch.
     *
     * @param id the id of the cutPlanIssueStitch to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cutPlanIssueStitch, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cut-plan-reciept-stitches/{id}")
    public ResponseEntity<CutPlanIssueStitchBean> getCutPlanRecieptStitch(@PathVariable Long id) {
        log.debug("REST request to get CutPlanIssueStitch : {}", id);
        CutPlanIssueStitchBean cutPlanIssueStitchBean = new CutPlanIssueStitchBean();
        CutPlanIssueStitch cutPlanIssueStitch = cutPlanIssueStitchRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(cutPlanIssueStitch, cutPlanIssueStitchBean);
        List<CutIssueStitchDetails> cutIssueStitchDetails = cutIssueStitchDetailsRepository.findAllByCutPlanIssueStitchId(id);
        List<CutIssueStitchDetailsBean> cutIssueStitchDetailsBeans = new ArrayList<>();
        for (CutIssueStitchDetails cutIssueStitchDetail : cutIssueStitchDetails) {
            CutIssueStitchDetailsBean cutIssueStitchDetailsBean = new CutIssueStitchDetailsBean();
            BeanUtils.copyProperties(cutIssueStitchDetail, cutIssueStitchDetailsBean);
            cutIssueStitchDetailsBean.setSubcode01(cutIssueStitchDetail.getDecosubcode01());
            cutIssueStitchDetailsBean.setSubcode02(cutIssueStitchDetail.getDecosubcode02());
            cutIssueStitchDetailsBean.setSubcode03(cutIssueStitchDetail.getDecosubcode03());
            cutIssueStitchDetailsBean.setSubcode04(cutIssueStitchDetail.getDecosubcode04());
            cutIssueStitchDetailsBean.setSubcode05(cutIssueStitchDetail.getDecosubcode05());
            cutIssueStitchDetailsBean.setSubcode06(cutIssueStitchDetail.getDecosubcode06());
            cutIssueStitchDetailsBean.setSubcode07(cutIssueStitchDetail.getDecosubcode07());
            cutIssueStitchDetailsBean.setSubcode08(cutIssueStitchDetail.getDecosubcode08());
            cutIssueStitchDetailsBean.setSubcode09(cutIssueStitchDetail.getDecosubcode09());
            cutIssueStitchDetailsBean.setSubcode10(cutIssueStitchDetail.getDecosubcode10());
            cutIssueStitchDetailsBean.setPrimaryquantity(cutIssueStitchDetail.getBaseprimaryquantityunit());
            cutIssueStitchDetailsBean.setPrimaryuomcode(cutIssueStitchDetail.getBaseprimaryunitcode());
            cutIssueStitchDetailsBean.setSecondaryquantity(cutIssueStitchDetail.getBasesecondaryquantityunit());
            cutIssueStitchDetailsBean.setSecondaryuomcode(cutIssueStitchDetail.getBasesecondaryunitcode());
            cutIssueStitchDetailsBean.setId(cutIssueStitchDetail.getCutPlanBundleId());
            cutIssueStitchDetailsBean.setDetailId(cutIssueStitchDetail.getId());
            cutIssueStitchDetailsBeans.add(cutIssueStitchDetailsBean);
        }
        cutPlanIssueStitchBean.setCutIssueStitchDetails(cutIssueStitchDetailsBeans);
        return ResponseUtil.wrapOrNotFound(Optional.of(cutPlanIssueStitchBean));
    }

    @GetMapping("/cut-plan-issue-stitches-post/{id}")
    public ResponseEntity<CutPlanIssueStitchBean> postCutBundleEntry(@PathVariable Long id) throws URISyntaxException {
        log.debug("REST request to save CutBundleEntry : {}", id);
        CutPlanIssueStitchBean cutPlanIssueStitchBean = new CutPlanIssueStitchBean();
        CutPlanIssueStitch cutPlanIssueStitch = cutPlanIssueStitchRepository.findById(id).orElse(null);
        List<CutIssueStitchDetails> cutIssueStitchDetails = cutIssueStitchDetailsRepository.findAllByCutPlanIssueStitchId(id);
        List<CutIssueStitchDetailsBean> cutIssueStitchDetailsBeans = new ArrayList<>();
        for (CutIssueStitchDetails cutIssueStitchDetail : cutIssueStitchDetails) {
            if (cutIssueStitchDetail.getStocktransactionid() == null) {
                CutPlanBundle cutPlanBundle = cutPlanBundleRepository.findById(cutIssueStitchDetail.getCutPlanBundleId()).orElse(null);
                if (cutPlanBundle != null) {
                    Logicalwarehouse logicalwarehouse = logicalwarehouseRepository.findById(new LogicalwarehouseId(Constants.COMPANY_CODE, "ALLCPTIN")).orElse(null);
                    List<Stocktransaction> stocktransactions = stocktransactionRepository.findByProductionOrderAndDemandAndBundle(Constants.COMPANY_CODE, cutPlanBundle.getProductionCode(), cutPlanBundle.getDemandcountercode(), cutPlanBundle.getDemandcode(), cutPlanBundle.getBundleCode());
                    if (stocktransactions != null && stocktransactions.size()>0) {
                        Stocktransaction stocktransaction = stocktransactions.get(0);
                        int linNo = 0;
                        Long stockTransactionDestinationId = stocktransactionimportRepository.getNextSequence();
                        Long parentAdditionIdReciept = adstorageimportRepository.getNextSequence();

                        Stocktransactiontemplate stocktransactiontemplate = stocktransactiontemplateRepository.findById("T04").orElse(null);
                        Stocktransactionimport stocktransactionimportReciept = to(Constants.COMPANY_CODE, stocktransaction, stockTransactionDestinationId, ++linNo, "T04", "1", parentAdditionIdReciept, logicalwarehouse, false, stocktransactiontemplate.getStocktransactiontype());
                        Stocktransactionimport stockResultReciept = stocktransactionimportRepository.save(stocktransactionimportReciept);

                        Long parentAdditionIdIssue = adstorageimportRepository.getNextSequence();

                        Stocktransactiontemplate stocktransactiontemplate2 = stocktransactiontemplateRepository.findById("T03").orElse(null);
                        Stocktransactionimport stocktransactionimportIssue = to(Constants.COMPANY_CODE, stocktransaction, stockTransactionDestinationId, ++linNo, "T03", "2", parentAdditionIdIssue, logicalwarehouse, false, stocktransactiontemplate2.getStocktransactiontype());
                        Stocktransactionimport stockResultIssue = stocktransactionimportRepository.save(stocktransactionimportIssue);
                        if (stockResultReciept != null && stockResultIssue != null) {
                            Adstorageimport adstorageimportBundleNoReciept = new Adstorageimport(new AdstorageimportId("StockTransaction", parentAdditionIdReciept, "StockTransaction", "InterfaceBundleNumber", "InterfaceBundleNumber"), 0, 0, 0, 0, cutPlanBundle.getBundleCode(), 0, (short) 0, null, new BigDecimal(0), 0L, null, null, 0L);
                            adstorageimportRepository.save(adstorageimportBundleNoReciept);

                            Adstorageimport adstorageimportBundleNoIssue = new Adstorageimport(new AdstorageimportId("StockTransaction", parentAdditionIdIssue, "StockTransaction", "InterfaceBundleNumber", "InterfaceBundleNumber"), 0, 0, 0, 0, cutPlanBundle.getBundleCode(), 0, (short) 0, null, new BigDecimal(0), 0L, null, null, 0L);
                            adstorageimportRepository.save(adstorageimportBundleNoIssue);

                            Adstorageimport adstorageimportDestination = new Adstorageimport(new AdstorageimportId("StockTransaction", parentAdditionIdReciept, "StockTransaction", "Destination", "Destination"), 0, 0, 0, 0, cutPlanBundle.getDestination(), 0, (short) 0, null, new BigDecimal(0), 0L, null, null, 0L);
                            adstorageimportRepository.save(adstorageimportDestination);

                            Adstorageimport adstorageimportDestinationIssue = new Adstorageimport(new AdstorageimportId("StockTransaction", parentAdditionIdIssue, "StockTransaction", "Destination", "Destination"), 0, 0, 0, 0, cutPlanBundle.getDestination(), 0, (short) 0, null, new BigDecimal(0), 0L, null, null, 0L);
                            adstorageimportRepository.save(adstorageimportDestinationIssue);

                            stockResultReciept.setImportstatus(1);
                            stockResultReciept = stocktransactionimportRepository.save(stockResultReciept);

                            stockResultIssue.setImportstatus(1);
                            stockResultIssue = stocktransactionimportRepository.save(stockResultIssue);

                            cutIssueStitchDetail.setCuttingwarehousecode(logicalwarehouse.getId().getCode().trim());
                            cutIssueStitchDetail.setStocktransactionid(Long.parseLong(stockResultReciept.getId().getTransactionnumber()));
                            CutIssueStitchDetails resultDetails = cutIssueStitchDetailsRepository.save(cutIssueStitchDetail);
                            if(resultDetails != null) {
                                CutIssueStitchDetailsBean cutIssueStitchDetailsBean = new CutIssueStitchDetailsBean();
                                BeanUtils.copyProperties(resultDetails, cutIssueStitchDetailsBean);
                                cutIssueStitchDetailsBean.setSubcode01(resultDetails.getDecosubcode01());
                                cutIssueStitchDetailsBean.setSubcode02(resultDetails.getDecosubcode02());
                                cutIssueStitchDetailsBean.setSubcode03(resultDetails.getDecosubcode03());
                                cutIssueStitchDetailsBean.setSubcode04(resultDetails.getDecosubcode04());
                                cutIssueStitchDetailsBean.setSubcode05(resultDetails.getDecosubcode05());
                                cutIssueStitchDetailsBean.setSubcode06(resultDetails.getDecosubcode06());
                                cutIssueStitchDetailsBean.setSubcode07(resultDetails.getDecosubcode07());
                                cutIssueStitchDetailsBean.setSubcode08(resultDetails.getDecosubcode08());
                                cutIssueStitchDetailsBean.setSubcode09(resultDetails.getDecosubcode09());
                                cutIssueStitchDetailsBean.setSubcode10(resultDetails.getDecosubcode10());
                                cutIssueStitchDetailsBean.setId(resultDetails.getCutPlanBundleId());
                                cutIssueStitchDetailsBeans.add(cutIssueStitchDetailsBean);
                            }
                        }
                    }
                }
            }
        }
        cutPlanIssueStitch.setPostedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        cutPlanIssueStitch.setPostedDate(Instant.now());
        cutPlanIssueStitch = cutPlanIssueStitchRepository.save(cutPlanIssueStitch);
        BeanUtils.copyProperties(cutPlanIssueStitch, cutPlanIssueStitchBean);
        cutPlanIssueStitchBean.setCutIssueStitchDetails(cutIssueStitchDetailsBeans);
        return ResponseUtil.wrapOrNotFound(Optional.of(cutPlanIssueStitchBean));
    }

    @GetMapping("/cut-plan-reciept-stitches-post/{id}")
    public ResponseEntity<CutPlanIssueStitchBean> postCutBundleEntryReciept(@PathVariable Long id) throws URISyntaxException {
        log.debug("REST request to save CutBundleEntry : {}", id);
        CutPlanIssueStitchBean cutPlanIssueStitchBean = new CutPlanIssueStitchBean();
        CutPlanIssueStitch cutPlanIssueStitch = cutPlanIssueStitchRepository.findById(id).orElse(null);
        List<CutIssueStitchDetails> cutIssueStitchDetails = cutIssueStitchDetailsRepository.findAllByCutPlanIssueStitchId(id);
        List<CutIssueStitchDetailsBean> cutIssueStitchDetailsBeans = new ArrayList<>();
        int ctr = 0;
        for (CutIssueStitchDetails cutIssueStitchDetail : cutIssueStitchDetails) {
            if (cutIssueStitchDetail.getStocktransactionrecieptid() == null) {
                CutPlanBundle cutPlanBundle = cutPlanBundleRepository.findById(cutIssueStitchDetail.getCutPlanBundleId()).orElse(null);
                if (cutPlanBundle != null) {
                    Logicalwarehouse logicalwarehouse = null;
                    if (cutPlanBundle.getProductionCode().startsWith("CP")) {
                        logicalwarehouse = logicalwarehouseRepository.findInTransitWarehouseByPlantCode(cutPlanIssueStitch.getDestinationPlantCode());
                    } else {
                        logicalwarehouse = logicalwarehouseRepository.findInTransitHFCWarehouseByPlantCode(cutPlanIssueStitch.getDestinationPlantCode());
                    }
                    Logicalwarehouse logicalwarehouseReciept = logicalwarehouseRepository.findById(new LogicalwarehouseId(Constants.COMPANY_CODE, cutIssueStitchDetail.getCuttingwarehousecode())).orElse(null);
                    List<Stocktransaction> stocktransactions = stocktransactionRepository.findByProductionOrderAndDemandAndBundle(Constants.COMPANY_CODE, cutPlanBundle.getProductionCode(), cutPlanBundle.getDemandcountercode(), cutPlanBundle.getDemandcode(), cutPlanBundle.getBundleCode());
                    if (logicalwarehouseReciept != null && logicalwarehouse != null && stocktransactions != null && stocktransactions.size()>0) {
                        Stocktransaction stocktransaction = stocktransactions.get(0);
                        int linNo = 0;
                        Long stockTransactionDestinationId = stocktransactionimportRepository.getNextSequence();
                        Long parentAdditionIdReciept = adstorageimportRepository.getNextSequence();

                        Stocktransactiontemplate stocktransactiontemplate = stocktransactiontemplateRepository.findById("T04").orElse(null);
                        Stocktransactionimport stocktransactionimportReciept = to(Constants.COMPANY_CODE, stocktransaction, stockTransactionDestinationId, ++linNo, "T04", "1", parentAdditionIdReciept, logicalwarehouseReciept, true, stocktransactiontemplate.getStocktransactiontype());
                        Stocktransactionimport stockResultReciept = stocktransactionimportRepository.save(stocktransactionimportReciept);

                        Long parentAdditionIdIssue = adstorageimportRepository.getNextSequence();
                        Stocktransactiontemplate stocktransactiontemplate2 = stocktransactiontemplateRepository.findById("T03").orElse(null);
                        Stocktransactionimport stocktransactionimportIssue = to(Constants.COMPANY_CODE, stocktransaction, stockTransactionDestinationId, ++linNo, "T03", "2", parentAdditionIdIssue, logicalwarehouse, false, stocktransactiontemplate2.getStocktransactiontype());
                        Stocktransactionimport stockResultIssue = stocktransactionimportRepository.save(stocktransactionimportIssue);
                        if (stockResultReciept != null && stockResultIssue != null) {
                            Adstorageimport adstorageimportBundleNoReciept = new Adstorageimport(new AdstorageimportId("StockTransaction", parentAdditionIdReciept, "StockTransaction", "InterfaceBundleNumber", "InterfaceBundleNumber"), 0, 0, 0, 0, cutPlanBundle.getBundleCode(), 0, (short) 0, null, new BigDecimal(0), 0L, null, null, 0L);
                            adstorageimportRepository.save(adstorageimportBundleNoReciept);

                            Adstorageimport adstorageimportBundleNoIssue = new Adstorageimport(new AdstorageimportId("StockTransaction", parentAdditionIdIssue, "StockTransaction", "InterfaceBundleNumber", "InterfaceBundleNumber"), 0, 0, 0, 0, cutPlanBundle.getBundleCode(), 0, (short) 0, null, new BigDecimal(0), 0L, null, null, 0L);
                            adstorageimportRepository.save(adstorageimportBundleNoIssue);

                            Adstorageimport adstorageimportDestination = new Adstorageimport(new AdstorageimportId("StockTransaction", parentAdditionIdReciept, "StockTransaction", "Destination", "Destination"), 0, 0, 0, 0, cutPlanBundle.getDestination(), 0, (short) 0, null, new BigDecimal(0), 0L, null, null, 0L);
                            adstorageimportRepository.save(adstorageimportDestination);

                            Adstorageimport adstorageimportDestinationIssue = new Adstorageimport(new AdstorageimportId("StockTransaction", parentAdditionIdIssue, "StockTransaction", "Destination", "Destination"), 0, 0, 0, 0, cutPlanBundle.getDestination(), 0, (short) 0, null, new BigDecimal(0), 0L, null, null, 0L);
                            adstorageimportRepository.save(adstorageimportDestinationIssue);

                            stockResultReciept.setImportstatus(1);
                            stockResultReciept = stocktransactionimportRepository.save(stockResultReciept);

                            stockResultIssue.setImportstatus(1);
                            stockResultIssue = stocktransactionimportRepository.save(stockResultIssue);

                            cutIssueStitchDetail.setStitchwarehousecode(logicalwarehouse.getId().getCode().trim());
                            cutIssueStitchDetail.setStocktransactionrecieptid(Long.parseLong(stockResultReciept.getId().getTransactionnumber()));
                            CutIssueStitchDetails resultDetails = cutIssueStitchDetailsRepository.save(cutIssueStitchDetail);
                            if(resultDetails != null) {
                                ++ctr;
                                CutIssueStitchDetailsBean cutIssueStitchDetailsBean = new CutIssueStitchDetailsBean();
                                BeanUtils.copyProperties(resultDetails, cutIssueStitchDetailsBean);
                                cutIssueStitchDetailsBean.setSubcode01(resultDetails.getDecosubcode01());
                                cutIssueStitchDetailsBean.setSubcode02(resultDetails.getDecosubcode02());
                                cutIssueStitchDetailsBean.setSubcode03(resultDetails.getDecosubcode03());
                                cutIssueStitchDetailsBean.setSubcode04(resultDetails.getDecosubcode04());
                                cutIssueStitchDetailsBean.setSubcode05(resultDetails.getDecosubcode05());
                                cutIssueStitchDetailsBean.setSubcode06(resultDetails.getDecosubcode06());
                                cutIssueStitchDetailsBean.setSubcode07(resultDetails.getDecosubcode07());
                                cutIssueStitchDetailsBean.setSubcode08(resultDetails.getDecosubcode08());
                                cutIssueStitchDetailsBean.setSubcode09(resultDetails.getDecosubcode09());
                                cutIssueStitchDetailsBean.setSubcode10(resultDetails.getDecosubcode10());
                                cutIssueStitchDetailsBean.setId(resultDetails.getCutPlanBundleId());
                                cutIssueStitchDetailsBeans.add(cutIssueStitchDetailsBean);
                            }
                        }
                    } else {
                        return ResponseEntity.badRequest().headers(io.vamani.application.web.rest.util.HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Stock not exist")).build();
                    }
                }
            }
        }
        if(cutIssueStitchDetails.size() == ctr) {
            cutPlanIssueStitch.setRecieptPostedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
            cutPlanIssueStitch.setRecieptPostedDate(Instant.now());
            cutPlanIssueStitch = cutPlanIssueStitchRepository.save(cutPlanIssueStitch);
            BeanUtils.copyProperties(cutPlanIssueStitch, cutPlanIssueStitchBean);
            cutPlanIssueStitchBean.setCutIssueStitchDetails(cutIssueStitchDetailsBeans);
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(cutPlanIssueStitchBean));
    }

    public static Stocktransactionimport to(String companycode, Stocktransaction stocktransaction, Long stockTransactionId, Integer lineNo, String templatecode, String detailType, Long parentAdditionId, Logicalwarehouse logicalwarehousecode, boolean isReciept, String stocktransactiontype) {
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        Stocktransactionimport stocktransactionimport = new Stocktransactionimport();
        stocktransactionimport.setId(new StocktransactionimportId(Constants.COMPANY_CODE, stockTransactionId.toString(), lineNo));
        stocktransactionimport.setImportstatus(0);
        stocktransactionimport.setNowtrnnumbertransactionnumber("");
        stocktransactionimport.setTransactionstatus("0");
        stocktransactionimport.setItemtypecompanycode(companycode);
        stocktransactionimport.setItemtypecode(stocktransaction.getItemtypecode());
        stocktransactionimport.setTransactiondate(new Date(System.currentTimeMillis()));
        stocktransactionimport.setTransactiontime(new Time(System.currentTimeMillis()));
        stocktransactionimport.setDetailtype(detailType);
        stocktransactionimport.setTemplatecompanycode(companycode);
        stocktransactionimport.setTemplatecode(templatecode);
        stocktransactionimport.setAutoissuetemplatecompanycode("");
        stocktransactionimport.setAutoissuetemplatecode("");
        stocktransactionimport.setStocktransactiontype(stocktransactiontype);
        stocktransactionimport.setDecocompanycode(companycode);
        stocktransactionimport.setDecosubcode01(stocktransaction.getDecosubcode01());
        stocktransactionimport.setDecosubcode02(stocktransaction.getDecosubcode02());
        stocktransactionimport.setDecosubcode03(stocktransaction.getDecosubcode03());
        stocktransactionimport.setDecosubcode04(stocktransaction.getDecosubcode04());
        stocktransactionimport.setDecosubcode05(stocktransaction.getDecosubcode05());
        stocktransactionimport.setDecosubcode06(stocktransaction.getDecosubcode06());
        stocktransactionimport.setDecosubcode07(stocktransaction.getDecosubcode07());
        stocktransactionimport.setDecosubcode08(stocktransaction.getDecosubcode08());
        stocktransactionimport.setDecosubcode09(stocktransaction.getDecosubcode09());
        stocktransactionimport.setDecosubcode10(stocktransaction.getDecosubcode10());
        stocktransactionimport.setItemdescription("");
        stocktransactionimport.setLogicalwarehousecompanycode(companycode);
        if (templatecode != null && templatecode.equalsIgnoreCase("T04")) {
            if(isReciept == true) {
                stocktransactionimport.setLogicalwarehousecode(logicalwarehousecode.getId().getCode());
            } else {
                stocktransactionimport.setLogicalwarehousecode(stocktransaction.getLogicalwarehousecode());
            }
        } else {
            stocktransactionimport.setLogicalwarehousecode(logicalwarehousecode.getId().getCode());
        }
        stocktransactionimport.setTransferallocation("");
        stocktransactionimport.setIssueqtyfrombalance((short) 0);
        stocktransactionimport.setUserprimaryuomcode(stocktransaction.getUserprimaryuomcode());
        stocktransactionimport.setUserprimaryquantity(stocktransaction.getUserprimaryquantity());
        stocktransactionimport.setUsersecondaryuomcode(stocktransaction.getUsersecondaryuomcode());
        stocktransactionimport.setUsersecondaryquantity(stocktransaction.getUsersecondaryquantity());
        stocktransactionimport.setUserpackaginguomcode(stocktransaction.getUserpackaginguomcode());
        stocktransactionimport.setUserpackagingquantity(stocktransaction.getUserpackagingquantity());
        stocktransactionimport.setWeightunitofmeasurecode("");
        stocktransactionimport.setWeightgross(new BigDecimal("0"));
        stocktransactionimport.setWeightnet(new BigDecimal("0"));
        stocktransactionimport.setWeightrealnet(new BigDecimal("0"));
        stocktransactionimport.setDerivationcode(null);
        stocktransactionimport.setDerivationlinenumber(null);
        stocktransactionimport.setDerivationcomponentlinenumber(null);
        stocktransactionimport.setQualitylvlitemtypecompanycode(companycode);
        stocktransactionimport.setQualitylevelcode(stocktransaction.getQualitylevelcode());
        /*  stocktransactionimport.setQualityreasoncompanycode();
            stocktransactionimport.setQualityreasoncode();
            stocktransactionimport.setFirstqualitycontroldate();
            stocktransactionimport.setFirstqualitycontrolcounter();
            stocktransactionimport.setFirstqualitycontrolnumber();*/
        stocktransactionimport.setPhysicalwarehousecompanycode(companycode);
        if (templatecode != null && templatecode.equalsIgnoreCase("T04")) {
            if(isReciept == true) {
                stocktransactionimport.setPhysicalwarehousecode(logicalwarehousecode.getPhysicalwarehousecode());
            } else {
                stocktransactionimport.setPhysicalwarehousecode(stocktransaction.getPhysicalwarehousecode());
            }
        } else {
            stocktransactionimport.setPhysicalwarehousecode(logicalwarehousecode.getPhysicalwarehousecode());
        }
        stocktransactionimport.setWhslocwhszonephywhscmycode(companycode);
        stocktransactionimport.setWhslocationwarehousezonecode(stocktransaction.getWhslocationwarehousezonecode());
        stocktransactionimport.setWarehouselocationcode(stocktransaction.getWarehouselocationcode());
        stocktransactionimport.setContainercompanycode(companycode);
        stocktransactionimport.setContaineritemtypecode(stocktransaction.getContaineritemtypecode());
        stocktransactionimport.setContainersubcode01(stocktransaction.getContainersubcode01());
        stocktransactionimport.setContainerelementcompanycode(stocktransaction.getContainerelementcompanycode());
        stocktransactionimport.setContainerelementcode(stocktransaction.getContainerelementcode());
        stocktransactionimport.setLotcompanycode(companycode);
        stocktransactionimport.setLotcode(stocktransaction.getLotcode());
        stocktransactionimport.setItemelementcompanycode(companycode);
        stocktransactionimport.setItemelementsubcodekey(stocktransaction.getItemelementsubcodekey());
        stocktransactionimport.setItemelementcode(stocktransaction.getItemelementcode());
        stocktransactionimport.setProjectcode(stocktransaction.getProjectcode());
        stocktransactionimport.setCostcentercompanycode(companycode);
        stocktransactionimport.setCostcentercode(stocktransaction.getCostcentercode());
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
        stocktransactionimport.setOrdercountercode(stocktransaction.getOrdercountercode());
        stocktransactionimport.setOrdercode(stocktransaction.getOrdercode());
        stocktransactionimport.setOrderline(stocktransaction.getOrderline());
        stocktransactionimport.setOrdersubline(0);
        stocktransactionimport.setOrdercomponentline(0);
        stocktransactionimport.setOrderdeliveryline(0);
        stocktransactionimport.setProductionordercode(stocktransaction.getProductionordercode());
        stocktransactionimport.setReturncode("");
        stocktransactionimport.setReturnline(0);
        stocktransactionimport.setTokencode("RECEIPT");
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

    /**
     * {@code DELETE  /cut-plan-issue-stitches/:id} : delete the "id" cutPlanIssueStitch.
     *
     * @param id the id of the cutPlanIssueStitch to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cut-plan-issue-stitches/{id}")
    public ResponseEntity<Void> deleteCutPlanIssueStitch(@PathVariable Long id) {
        log.debug("REST request to delete CutPlanIssueStitch : {}", id);
        cutPlanIssueStitchRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code DELETE  /cut-plan-issue-stitches/:id} : delete the "id" cutPlanIssueStitch.
     *
     * @param id the id of the cutPlanIssueStitch to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cut-issue-stitch-details/{id}")
    public ResponseEntity<Void> deleteCutIssueStitchDetails(@PathVariable Long id) {
        log.debug("REST request to delete CutIssueStitchDetails : {}", id);
        cutIssueStitchDetailsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/cut-plan-issue-stich-pdf/{id}")
    void getIssuePdf(@PathVariable Long id, HttpServletResponse response) {
        if (id != null && id.intValue() > 0) {
            try {
                List<CutPlanIssueStitchBean> cutPlanIssueStitchBeans = new ArrayList<>();
                CutPlanIssueStitch cutPlanIssueStitch = cutPlanIssueStitchRepository.findById(id).orElse(null);
                CutPlanIssueStitchBean cutPlanIssueStitchBean = new CutPlanIssueStitchBean();
                BeanUtils.copyProperties(cutPlanIssueStitch, cutPlanIssueStitchBean);
                Factory factory = factoryRepository.findById(cutPlanIssueStitchBean.getPlantCode()).orElse(null);
                String add1 = (factory.getAddressline1() != null ? factory.getAddressline1().trim() : "") + (factory.getAddressline2() != null ? factory.getAddressline2().trim() : "");
                cutPlanIssueStitchBean.setCreateddate(Date.from(cutPlanIssueStitch.getCreateddate()));
                cutPlanIssueStitchBean.setPlantAddress(add1);
                Factory factoryDestination = factoryRepository.findById(cutPlanIssueStitchBean.getDestinationPlantCode() != null ? cutPlanIssueStitchBean.getDestinationPlantCode() : cutPlanIssueStitchBean.getPlantCode()).orElse(null);
                String add2 = (factoryDestination.getAddressline1() != null ? factoryDestination.getAddressline1().trim() : "") + (factoryDestination.getAddressline2() != null ? factoryDestination.getAddressline2().trim() : "");
                cutPlanIssueStitchBean.setDestinationPlantAddress(add2);
                if (cutPlanIssueStitchBean != null && cutPlanIssueStitchBean.getId() > 0) {
                    List<CutIssueStitchDetails> cutIssueStitchDetails = cutIssueStitchDetailsRepository.findAllByCutPlanIssueStitchId(cutPlanIssueStitchBean.getId());
                    if (cutIssueStitchDetails != null && cutIssueStitchDetails.size() > 0) {
                        List<CutIssueStitchDetailsBean> cutIssueStitchDetailsBeans = new ArrayList<>();
                        for (CutIssueStitchDetails lines : cutIssueStitchDetails) {
                            CutIssueStitchDetailsBean cutIssueStitchDetailsBean = new CutIssueStitchDetailsBean();
                            BeanUtils.copyProperties(lines, cutIssueStitchDetailsBean);
                            cutIssueStitchDetailsBean.setSubcode09(lines.getLotcode() != null && lines.getLotcode().startsWith("CP") ? lines.getDecosubcode07() : lines.getDecosubcode05());
                            cutIssueStitchDetailsBean.setSubcode01(lines.getDecosubcode01());
                            CutPlanBundle cutPlanBundle = cutPlanBundleRepository.findById(lines.getCutPlanBundleId()).orElse(null);
                            cutIssueStitchDetailsBean.setProductionordercode(cutPlanBundle.getProductionCode());
                            cutIssueStitchDetailsBeans.add(cutIssueStitchDetailsBean);
                        }
                        cutPlanIssueStitchBean.setCutIssueStitchDetails(cutIssueStitchDetailsBeans);
                    }
                    cutPlanIssueStitchBeans.add(cutPlanIssueStitchBean);
                    String path = applicationProperties.getTemplatePath() + "jasper/";
                    JasperDesign jasperDesign = JRXmlLoader.load(path + "/CutPlanIssueStich.jrxml");
                    JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                    Map<String, Object> parameters = new HashMap<>();
                    JRDataSource jrDataSource = new JRBeanCollectionDataSource(cutPlanIssueStitchBeans);
                    parameters.put("datasource", jrDataSource);
                    parameters.put("SUBREPORT_DIR", path);
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
                    response.setContentType("application/x-pdf");
                    response.setHeader("Content-Disposition", "attachment; filename=CutPlanIssueStich.pdf");
                    final OutputStream outputStream = response.getOutputStream();
                    JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
