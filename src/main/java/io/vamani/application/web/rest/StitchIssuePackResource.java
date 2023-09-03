package io.vamani.application.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.config.Constants;
import io.vamani.application.db2.domain.*;
import io.vamani.application.db2.repository.*;
import io.vamani.application.domain.*;
import io.vamani.application.model.CutPlanBundleEntity;
import io.vamani.application.model.StitchIssuePackDetailsBean;
import io.vamani.application.model.StitchIssuePackBean;
import io.vamani.application.model.CutPlanSearch;
import io.vamani.application.repository.*;
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
import org.springframework.context.annotation.Bean;
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
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.*;

/**
 * REST controller for managing {@link StitchIssuePack}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class StitchIssuePackResource {

    private final Logger log = LoggerFactory.getLogger(StitchIssuePackResource.class);

    private static final String ENTITY_NAME = "stitchIssuePack";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StitchIssuePackRepository stitchIssuePackRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    FactoryRepository factoryRepository;

    @Autowired
    private StitchIssuePackDetailsRepository stitchIssuePackDetailsRepository;

    @Autowired
    private CutPlanBundleRepository cutPlanBundleRepository;

    @Autowired
    private StocktransactionRepository stocktransactionRepository;

    @Autowired
    private StocktransactionimportRepository stocktransactionimportRepository;

    @Autowired
    private StitchStockDetailsRepository stitchStockDetailsRepository;

    @Autowired
    private AdstorageimportRepository adstorageimportRepository;

    @Autowired
    private ProductiondemandRepository productiondemandRepository;

    @Autowired
    private LogicalwarehouseRepository logicalwarehouseRepository;

    @Autowired
    private StitchIssuePackTransactionRepository stitchIssuePackTransactionRepository;

    @Autowired
    private StocktransactiontemplateRepository stocktransactiontemplateRepository;

    public StitchIssuePackResource(StitchIssuePackRepository stitchIssuePackRepository) {
        this.stitchIssuePackRepository = stitchIssuePackRepository;
    }

    @PostMapping("/stitch-issue-packs")
    public ResponseEntity<StitchIssuePack> createStitchIssuePack(@Valid @RequestBody StitchIssuePackBean stitchIssuePackBean) throws URISyntaxException {
        log.debug("REST request to save StitchIssuePack : {}", stitchIssuePackBean);
        if (stitchIssuePackBean.getId() != null) {
            throw new BadRequestAlertException("A new stitchIssuePack cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StitchIssuePack stitchIssuePack = new StitchIssuePack();
        BeanUtils.copyProperties(stitchIssuePackBean, stitchIssuePack);
        stitchIssuePack.setCreatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
        stitchIssuePack.setCreateddate(Instant.now());
        StitchIssuePack result = stitchIssuePackRepository.save(stitchIssuePack);
        if (result != null && stitchIssuePackBean.getStitchIssuePackDetails() != null && stitchIssuePackBean.getStitchIssuePackDetails().size() > 0) {
            for (StitchIssuePackDetailsBean stitchIssuePackDetailsBean : stitchIssuePackBean.getStitchIssuePackDetails()) {
                StitchIssuePackDetails stitchIssuePackDetails = new StitchIssuePackDetails();
                BeanUtils.copyProperties(stitchIssuePackDetailsBean, stitchIssuePackDetails);
                stitchIssuePackDetails.setStitchIssuePack(result);
                stitchIssuePackDetails.setCreatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
                stitchIssuePackDetails.setCreateddate(Instant.now());
                stitchIssuePackDetailsRepository.save(stitchIssuePackDetails);
            }
        }
        return ResponseEntity.created(new URI("/api/stitch-issue-packs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /stitch-issue-packs} : Updates an existing stitchIssuePack.
     *
     * @param stitchIssuePack the stitchIssuePack to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated stitchIssuePack,
     * or with status {@code 400 (Bad Request)} if the stitchIssuePack is not valid,
     * or with status {@code 500 (Internal Server Error)} if the stitchIssuePack couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/stitch-issue-packs")
    public ResponseEntity<StitchIssuePack> updateStitchIssuePack(@Valid @RequestBody StitchIssuePackBean stitchIssuePackBean) throws URISyntaxException {
        log.debug("REST request to update StitchIssuePack : {}", stitchIssuePackBean);
        if (stitchIssuePackBean.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        StitchIssuePack stitchIssuePack = new StitchIssuePack();
        BeanUtils.copyProperties(stitchIssuePackBean, stitchIssuePack);
        stitchIssuePack.setLastupdatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
        stitchIssuePack.setLastupdateddate(Instant.now());
        StitchIssuePack result = stitchIssuePackRepository.save(stitchIssuePack);
        if (result != null && stitchIssuePackBean.getStitchIssuePackDetails() != null && stitchIssuePackBean.getStitchIssuePackDetails().size() > 0) {
            for (StitchIssuePackDetailsBean stitchIssuePackDetailsBean : stitchIssuePackBean.getStitchIssuePackDetails()) {
                if (stitchIssuePackDetailsBean.getDecosubcode01() != null && stitchIssuePackDetailsBean.getDecosubcode01().length() > 0) {
                    StitchIssuePackDetails stitchIssuePackDetails = new StitchIssuePackDetails();
                    BeanUtils.copyProperties(stitchIssuePackDetailsBean, stitchIssuePackDetails);
                    stitchIssuePackDetails.setLastupdatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
                    stitchIssuePackDetails.setLastupdateddate(Instant.now());
                    stitchIssuePackDetails.setStitchIssuePack(result);
                    stitchIssuePackDetailsRepository.save(stitchIssuePackDetails);
                }
            }
        }
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, stitchIssuePack.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /stitch-issue-packs} : get all the stitchIssuePackes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of stitchIssuePackes in body.
     */
    @GetMapping("/stitch-issue-packs")
    public ResponseEntity<List<StitchIssuePack>> getAllStitchIssuePackes(Pageable pageable) {
        log.debug("REST request to get a page of StitchIssuePackes");
        Page<StitchIssuePack> page = stitchIssuePackRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @PostMapping("/stitch-issue-packs-filter")
    public ResponseEntity<List<StitchIssuePack>> getAllStitchIssuePackesFilter(@RequestBody CutPlanSearch search) {
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
        Page<StitchIssuePack> page = stitchIssuePackRepository.findAllByTypeAndPonoAndStyle(type, style, color, login, search.getPage());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @PostMapping("/stitch-reciept-packs-filter")
    public ResponseEntity<List<StitchIssuePack>> getAllCutPlanRecieptStitchesFilter(@RequestBody CutPlanSearch search) {
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
        Page<StitchIssuePack> page = stitchIssuePackRepository.findAllByTypeAndPonoAndStylePostedOnly(type, style, color, login, search.getPage());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /stitch-issue-packs/:id} : get the "id" stitchIssuePack.
     *
     * @param id the id of the stitchIssuePack to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the stitchIssuePack, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/stitch-issue-packs/{id}")
    public ResponseEntity<StitchIssuePackBean> getStitchIssuePack(@PathVariable Long id) {
        log.debug("REST request to get StitchIssuePack : {}", id);
        StitchIssuePackBean stitchIssuePackBean = new StitchIssuePackBean();
        StitchIssuePack stitchIssuePack = stitchIssuePackRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(stitchIssuePack, stitchIssuePackBean);
        List<StitchIssuePackDetails> stitchIssuePackDetails = stitchIssuePackDetailsRepository.findAllByStitchIssuePackId(id);
        List<StitchIssuePackDetailsBean> stitchIssuePackDetailsBeans = new ArrayList<>();
        for (StitchIssuePackDetails stitchIssuePackDetail : stitchIssuePackDetails) {
            StitchIssuePackDetailsBean stitchIssuePackDetailsBean = new StitchIssuePackDetailsBean();
            BeanUtils.copyProperties(stitchIssuePackDetail, stitchIssuePackDetailsBean);
            stitchIssuePackDetailsBeans.add(stitchIssuePackDetailsBean);
        }
        stitchIssuePackBean.setStitchIssuePackDetails(stitchIssuePackDetailsBeans);
        return ResponseUtil.wrapOrNotFound(Optional.of(stitchIssuePackBean));
    }

    /**
     * {@code GET  /stitch-issue-packs/:id} : get the "id" stitchIssuePack.
     *
     * @param id the id of the stitchIssuePack to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the stitchIssuePack, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/stitch-reciept-packs/{id}")
    public ResponseEntity<StitchIssuePackBean> getCutPlanRecieptStitch(@PathVariable Long id) {
        log.debug("REST request to get StitchIssuePack : {}", id);
        StitchIssuePackBean stitchIssuePackBean = new StitchIssuePackBean();
        StitchIssuePack stitchIssuePack = stitchIssuePackRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(stitchIssuePack, stitchIssuePackBean);
        List<StitchIssuePackDetails> stitchIssuePackDetails = stitchIssuePackDetailsRepository.findAllByStitchIssuePackId(id);
        List<StitchIssuePackDetailsBean> stitchIssuePackDetailsBeans = new ArrayList<>();
        for (StitchIssuePackDetails stitchIssuePackDetail : stitchIssuePackDetails) {
            StitchIssuePackDetailsBean stitchIssuePackDetailsBean = new StitchIssuePackDetailsBean();
            BeanUtils.copyProperties(stitchIssuePackDetail, stitchIssuePackDetailsBean);
            stitchIssuePackDetailsBeans.add(stitchIssuePackDetailsBean);
        }
        stitchIssuePackBean.setStitchIssuePackDetails(stitchIssuePackDetailsBeans);
        return ResponseUtil.wrapOrNotFound(Optional.of(stitchIssuePackBean));
    }

    @GetMapping("/stitch-issue-packs-post/{id}")
    public ResponseEntity<StitchIssuePackBean> postCutBundleEntry(@PathVariable Long id) throws URISyntaxException {
        log.debug("REST request to save CutBundleEntry : {}", id);
        StitchIssuePackBean stitchIssuePackBean = new StitchIssuePackBean();
        StitchIssuePack stitchIssuePack = stitchIssuePackRepository.findById(id).orElse(null);
        List<Object[]> stitchIssuePackDetails = stitchIssuePackDetailsRepository.findAllObjectByStitchIssuePackId(id);
        List<StitchIssuePackDetailsBean> stitchIssuePackDetailsBeans = new ArrayList<>();
        if(stitchIssuePackDetails != null && stitchIssuePackDetails.size() >0 ) {
            for (Object[] stitchIssuePackDetail : stitchIssuePackDetails) {
                if (stitchIssuePack.getPostedBy() == null && stitchIssuePack.getPostedDate() == null) {
                    Logicalwarehouse logicalwarehouse = logicalwarehouseRepository.findById(new LogicalwarehouseId(Constants.COMPANY_CODE, "ALLSEGIN")).orElse(null);
                    int linNo = 0;
                    Long stockTransactionDestinationId = stocktransactionimportRepository.getNextSequence();
                    Long parentAdditionIdReciept = adstorageimportRepository.getNextSequence();
                    Stocktransactiontemplate stocktransactiontemplate = stocktransactiontemplateRepository.findById("T04").orElse(null);
                    Stocktransactionimport stocktransactionimportReciept = to(Constants.COMPANY_CODE, stitchIssuePackDetail, stockTransactionDestinationId, ++linNo, "T04", "1", parentAdditionIdReciept, logicalwarehouse, false, stitchIssuePack, stocktransactiontemplate.getStocktransactiontype());
                    Stocktransactionimport stockResultReciept = stocktransactionimportRepository.save(stocktransactionimportReciept);

                    Long parentAdditionIdIssue = adstorageimportRepository.getNextSequence();
                    Stocktransactiontemplate stocktransactiontemplate2 = stocktransactiontemplateRepository.findById("T03").orElse(null);
                    Stocktransactionimport stocktransactionimportIssue = to(Constants.COMPANY_CODE, stitchIssuePackDetail, stockTransactionDestinationId, ++linNo, "T03", "2", parentAdditionIdIssue, logicalwarehouse, false, stitchIssuePack, stocktransactiontemplate2.getStocktransactiontype());
                    Stocktransactionimport stockResultIssue = stocktransactionimportRepository.save(stocktransactionimportIssue);
                    if (stockResultReciept != null && stockResultIssue != null) {
                        Adstorageimport adstorageimportBundleNoReciept = new Adstorageimport(new AdstorageimportId("StockTransaction", parentAdditionIdReciept, "StockTransaction", "InterfaceBundleNumber", "InterfaceBundleNumber"), 0, 0, 0, 0, stitchIssuePack.getId().toString(), 0, (short) 0, null, new BigDecimal(0), 0L, null, null, 0L);
                        adstorageimportRepository.save(adstorageimportBundleNoReciept);

                        Adstorageimport adstorageimportBundleNoIssue = new Adstorageimport(new AdstorageimportId("StockTransaction", parentAdditionIdIssue, "StockTransaction", "InterfaceBundleNumber", "InterfaceBundleNumber"), 0, 0, 0, 0, stitchIssuePack.getId().toString(), 0, (short) 0, null, new BigDecimal(0), 0L, null, null, 0L);
                        adstorageimportRepository.save(adstorageimportBundleNoIssue);

                        Adstorageimport adstorageimportDestination = new Adstorageimport(new AdstorageimportId("StockTransaction", parentAdditionIdReciept, "StockTransaction", "Destination", "Destination"), 0, 0, 0, 0, stitchIssuePack.getDestination(), 0, (short) 0, null, new BigDecimal(0), 0L, null, null, 0L);
                        adstorageimportRepository.save(adstorageimportDestination);

                        Adstorageimport adstorageimportDestinationIssue = new Adstorageimport(new AdstorageimportId("StockTransaction", parentAdditionIdIssue, "StockTransaction", "Destination", "Destination"), 0, 0, 0, 0, stitchIssuePack.getDestination(), 0, (short) 0, null, new BigDecimal(0), 0L, null, null, 0L);
                        adstorageimportRepository.save(adstorageimportDestinationIssue);

                        stockResultReciept.setImportstatus(1);
                        stockResultReciept = stocktransactionimportRepository.save(stockResultReciept);

                        stockResultIssue.setImportstatus(1);
                        stockResultIssue = stocktransactionimportRepository.save(stockResultIssue);

                        StitchIssuePackTransaction stitchIssuePackTransaction = new StitchIssuePackTransaction();
                        stitchIssuePackTransaction.setProductionCode(stitchIssuePackDetail[0].toString());
                        stitchIssuePackTransaction.setGroupstepnumber(((BigInteger) stitchIssuePackDetail[1]).longValue());
                        stitchIssuePackTransaction.setDemandcountercode(stitchIssuePackDetail[2].toString());
                        stitchIssuePackTransaction.setDemandcode(stitchIssuePackDetail[3].toString());
                        stitchIssuePackTransaction.setItemtypecode(stitchIssuePackDetail[4].toString());
                        stitchIssuePackTransaction.setSubcode01(stitchIssuePackDetail[5].toString());
                        stitchIssuePackTransaction.setSubcode02(stitchIssuePackDetail[6].toString());
                        stitchIssuePackTransaction.setSubcode03(stitchIssuePackDetail[7].toString());
                        stitchIssuePackTransaction.setSubcode04(stitchIssuePackDetail[8].toString());
                        stitchIssuePackTransaction.setSubcode05(stitchIssuePackDetail[9].toString());
                        stitchIssuePackTransaction.setSubcode06(stitchIssuePackDetail[10].toString());
                        stitchIssuePackTransaction.setSubcode07(stitchIssuePackDetail[11].toString());
                        stitchIssuePackTransaction.setSubcode08(stitchIssuePackDetail[12].toString());
                        stitchIssuePackTransaction.setSubcode09(stitchIssuePackDetail[13].toString());
                        stitchIssuePackTransaction.setSubcode10(stitchIssuePackDetail[14].toString());
                        stitchIssuePackTransaction.setLogicalwarehousecode(stitchIssuePackDetail[15].toString());
                        stitchIssuePackTransaction.setPhysicalwarehousecode(stitchIssuePackDetail[16].toString());
                        stitchIssuePackTransaction.setPrimaryuomcode(stitchIssuePackDetail[17].toString());
                        stitchIssuePackTransaction.setSecondaryuomcode(stitchIssuePackDetail[18].toString());
                        stitchIssuePackTransaction.setPrimaryquantity((Double) stitchIssuePackDetail[19]);
                        stitchIssuePackTransaction.setSecondaryquantity((Double) stitchIssuePackDetail[20]);
                        stitchIssuePackTransaction.setStitchwarehousecode(logicalwarehouse.getId().getCode().trim());
                        stitchIssuePackTransaction.setStocktransactionid(Long.parseLong(stockResultReciept.getId().getTransactionnumber()));
                        stitchIssuePackTransaction.setStitchIssuePackId(stitchIssuePack.getId());
                        StitchIssuePackTransaction resultDetails = stitchIssuePackTransactionRepository.save(stitchIssuePackTransaction);
                    }
                }
            }
            stitchIssuePack.setPostedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
            stitchIssuePack.setPostedDate(Instant.now());
            stitchIssuePack = stitchIssuePackRepository.save(stitchIssuePack);
            BeanUtils.copyProperties(stitchIssuePack, stitchIssuePackBean);
            stitchIssuePackBean.setStitchIssuePackDetails(stitchIssuePackDetailsBeans);
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(stitchIssuePackBean));
    }

    @GetMapping("/stitch-reciept-packs-post/{id}")
    public ResponseEntity<StitchIssuePackBean> postCutBundleEntryReciept(@PathVariable Long id) throws URISyntaxException {
        log.debug("REST request to save CutBundleEntry : {}", id);
        StitchIssuePackBean stitchIssuePackBean = new StitchIssuePackBean();
        StitchIssuePack stitchIssuePack = stitchIssuePackRepository.findById(id).orElse(null);
        List<StitchIssuePackTransaction> stitchIssuePackTransactions = stitchIssuePackTransactionRepository.getStitchIssuePackTransactionByStitchIssuePackId(id);

        int ctr = 0;
        for (StitchIssuePackTransaction stitchIssuePackTransaction : stitchIssuePackTransactions) {
            if (stitchIssuePackTransaction.getStocktransactionrecieptid() == null) {
                Logicalwarehouse logicalwarehouse = null;
                if(stitchIssuePackTransaction.getProductionCode().startsWith("SG")) {
                    logicalwarehouse = logicalwarehouseRepository.findInTransitSEGWarehouseByPlantCode(stitchIssuePack.getDestinationPlantCode());
                } else {
                    logicalwarehouse = logicalwarehouseRepository.findInTransitHFSEGWarehouseByPlantCode(stitchIssuePack.getDestinationPlantCode());
                }

                Logicalwarehouse logicalwarehouseReciept = logicalwarehouseRepository.findById(new LogicalwarehouseId(Constants.COMPANY_CODE, stitchIssuePackTransaction.getStitchwarehousecode())).orElse(null);
                if (logicalwarehouseReciept != null && logicalwarehouse != null) {
                    int linNo = 0;
                    Long stockTransactionDestinationId = stocktransactionimportRepository.getNextSequence();
                    Long parentAdditionIdReciept = adstorageimportRepository.getNextSequence();
                    Stocktransactiontemplate stocktransactiontemplate = stocktransactiontemplateRepository.findById("T04").orElse(null);
                    Stocktransactionimport stocktransactionimportReciept = to(Constants.COMPANY_CODE, stitchIssuePackTransaction, stockTransactionDestinationId, ++linNo, "T04", "1", parentAdditionIdReciept, logicalwarehouseReciept, true, stitchIssuePack, stocktransactiontemplate.getStocktransactiontype());
                    Stocktransactionimport stockResultReciept = stocktransactionimportRepository.save(stocktransactionimportReciept);

                    Long parentAdditionIdIssue = adstorageimportRepository.getNextSequence();
                    Stocktransactiontemplate stocktransactiontemplate2 = stocktransactiontemplateRepository.findById("T03").orElse(null);
                    Stocktransactionimport stocktransactionimportIssue = to(Constants.COMPANY_CODE, stitchIssuePackTransaction, stockTransactionDestinationId, ++linNo, "T03", "2", parentAdditionIdIssue, logicalwarehouse, false, stitchIssuePack, stocktransactiontemplate.getStocktransactiontype());
                    Stocktransactionimport stockResultIssue = stocktransactionimportRepository.save(stocktransactionimportIssue);
                    if (stockResultReciept != null && stockResultIssue != null) {
                        Adstorageimport adstorageimportBundleNoReciept = new Adstorageimport(new AdstorageimportId("StockTransaction", parentAdditionIdReciept, "StockTransaction", "InterfaceBundleNumber", "InterfaceBundleNumber"), 0, 0, 0, 0, stitchIssuePack.getId().toString(), 0, (short) 0, null, new BigDecimal(0), 0L, null, null, 0L);
                        adstorageimportRepository.save(adstorageimportBundleNoReciept);

                        Adstorageimport adstorageimportBundleNoIssue = new Adstorageimport(new AdstorageimportId("StockTransaction", parentAdditionIdIssue, "StockTransaction", "InterfaceBundleNumber", "InterfaceBundleNumber"), 0, 0, 0, 0, stitchIssuePack.getId().toString(), 0, (short) 0, null, new BigDecimal(0), 0L, null, null, 0L);
                        adstorageimportRepository.save(adstorageimportBundleNoIssue);

                        Adstorageimport adstorageimportDestination = new Adstorageimport(new AdstorageimportId("StockTransaction", parentAdditionIdReciept, "StockTransaction", "Destination", "Destination"), 0, 0, 0, 0, stitchIssuePack.getDestination(), 0, (short) 0, null, new BigDecimal(0), 0L, null, null, 0L);
                        adstorageimportRepository.save(adstorageimportDestination);

                        Adstorageimport adstorageimportDestinationIssue = new Adstorageimport(new AdstorageimportId("StockTransaction", parentAdditionIdIssue, "StockTransaction", "Destination", "Destination"), 0, 0, 0, 0, stitchIssuePack.getDestination(), 0, (short) 0, null, new BigDecimal(0), 0L, null, null, 0L);
                        adstorageimportRepository.save(adstorageimportDestinationIssue);

                        stockResultReciept.setImportstatus(1);
                        stockResultReciept = stocktransactionimportRepository.save(stockResultReciept);

                        stockResultIssue.setImportstatus(1);
                        stockResultIssue = stocktransactionimportRepository.save(stockResultIssue);

                        stitchIssuePackTransaction.setPackingwarehousecode(logicalwarehouse.getId().getCode().trim());
                        stitchIssuePackTransaction.setStocktransactionrecieptid(Long.parseLong(stockResultReciept.getId().getTransactionnumber()));
                        StitchIssuePackTransaction resultDetails = stitchIssuePackTransactionRepository.save(stitchIssuePackTransaction);
                    }
                } else {
                    return ResponseEntity.badRequest().headers(io.vamani.application.web.rest.util.HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Stock not exist")).build();
                }
                ++ctr;
            }
        }
        if(stitchIssuePackTransactions.size() == ctr) {
            stitchIssuePack.setRecieptPostedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
            stitchIssuePack.setRecieptPostedDate(Instant.now());
            stitchIssuePack = stitchIssuePackRepository.save(stitchIssuePack);
            BeanUtils.copyProperties(stitchIssuePack, stitchIssuePackBean);
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(stitchIssuePackBean));
    }

    public static Stocktransactionimport to(String companycode, Object[] stocktransaction, Long stockTransactionId, Integer lineNo, String templatecode, String detailType, Long parentAdditionId, Logicalwarehouse logicalwarehousecode, boolean isReciept, StitchIssuePack stitchIssuePack, String stocktransactiontype) {
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        Stocktransactionimport stocktransactionimport = new Stocktransactionimport();
        stocktransactionimport.setId(new StocktransactionimportId(Constants.COMPANY_CODE, stockTransactionId.toString(), lineNo));
        stocktransactionimport.setImportstatus(0);
        stocktransactionimport.setNowtrnnumbertransactionnumber("");
        stocktransactionimport.setTransactionstatus("0");
        stocktransactionimport.setItemtypecompanycode(companycode);
        stocktransactionimport.setItemtypecode(stocktransaction[4].toString());
        stocktransactionimport.setTransactiondate(new Date(System.currentTimeMillis()));
        stocktransactionimport.setTransactiontime(new Time(System.currentTimeMillis()));
        stocktransactionimport.setDetailtype(detailType);
        stocktransactionimport.setTemplatecompanycode(companycode);
        stocktransactionimport.setTemplatecode(templatecode);
        stocktransactionimport.setAutoissuetemplatecompanycode("");
        stocktransactionimport.setAutoissuetemplatecode("");
        stocktransactionimport.setStocktransactiontype(stocktransactiontype);
        stocktransactionimport.setDecocompanycode(companycode);
        stocktransactionimport.setDecosubcode01(stocktransaction[5].toString());
        stocktransactionimport.setDecosubcode02(stocktransaction[6].toString());
        stocktransactionimport.setDecosubcode03(stocktransaction[7].toString());
        stocktransactionimport.setDecosubcode04(stocktransaction[8].toString());
        stocktransactionimport.setDecosubcode05(stocktransaction[9].toString());
        stocktransactionimport.setDecosubcode06(stocktransaction[10].toString());
        stocktransactionimport.setDecosubcode07(stocktransaction[11].toString());
        stocktransactionimport.setDecosubcode08(stocktransaction[12].toString());
        stocktransactionimport.setDecosubcode09(stocktransaction[13].toString());
        stocktransactionimport.setDecosubcode10(stocktransaction[14].toString());
        stocktransactionimport.setItemdescription("");
        stocktransactionimport.setLogicalwarehousecompanycode(companycode);
        if (templatecode != null && templatecode.equalsIgnoreCase("T04")) {
            if(isReciept == true) {
                stocktransactionimport.setLogicalwarehousecode(logicalwarehousecode.getId().getCode());
            } else {
                stocktransactionimport.setLogicalwarehousecode(stocktransaction[15].toString());
            }
        } else {
            stocktransactionimport.setLogicalwarehousecode(logicalwarehousecode.getId().getCode());
        }
        stocktransactionimport.setTransferallocation("");
        stocktransactionimport.setIssueqtyfrombalance((short) 0);
        stocktransactionimport.setUserprimaryuomcode(stocktransaction[17].toString());
        stocktransactionimport.setUserprimaryquantity(new BigDecimal((Double) stocktransaction[19]));
        stocktransactionimport.setUsersecondaryuomcode(stocktransaction[18].toString());
        stocktransactionimport.setUsersecondaryquantity(new BigDecimal((Double) stocktransaction[20]));
        stocktransactionimport.setUserpackaginguomcode(null);
        stocktransactionimport.setUserpackagingquantity(null);
        stocktransactionimport.setWeightunitofmeasurecode("");
        stocktransactionimport.setWeightgross(new BigDecimal("0"));
        stocktransactionimport.setWeightnet(new BigDecimal("0"));
        stocktransactionimport.setWeightrealnet(new BigDecimal("0"));
        stocktransactionimport.setDerivationcode(null);
        stocktransactionimport.setDerivationlinenumber(null);
        stocktransactionimport.setDerivationcomponentlinenumber(null);
        stocktransactionimport.setQualitylvlitemtypecompanycode(companycode);
        stocktransactionimport.setQualitylevelcode(new BigInteger("1"));
        /*  stocktransactionimport.setQualityreasoncompanycode();
            stocktransactionimport.setQualityreasoncode();
            stocktransactionimport.setFirstqualitycontroldate();
            stocktransactionimport.setFirstqualitycontrolcounter();
            stocktransactionimport.setFirstqualitycontrolnumber();*/
        stocktransactionimport.setPhysicalwarehousecompanycode(companycode);
        if (templatecode != null && templatecode.equalsIgnoreCase("T04")) {
            stocktransactionimport.setPhysicalwarehousecode(stocktransaction[16].toString());
        } else {
            stocktransactionimport.setPhysicalwarehousecode(logicalwarehousecode.getPhysicalwarehousecode());
        }
        stocktransactionimport.setWhslocwhszonephywhscmycode(companycode);
        stocktransactionimport.setWhslocationwarehousezonecode("");
        stocktransactionimport.setWarehouselocationcode("");
        stocktransactionimport.setContainercompanycode(companycode);
        stocktransactionimport.setContaineritemtypecode("");
        stocktransactionimport.setContainersubcode01("");
        stocktransactionimport.setContainerelementcompanycode("");
        stocktransactionimport.setContainerelementcode("");
        stocktransactionimport.setLotcompanycode(companycode);
        stocktransactionimport.setLotcode("");
        stocktransactionimport.setItemelementcompanycode(companycode);
        stocktransactionimport.setItemelementsubcodekey("");
        stocktransactionimport.setItemelementcode("");
        stocktransactionimport.setProjectcode(stitchIssuePack.getProjectcode());
        stocktransactionimport.setCostcentercompanycode(companycode);
        stocktransactionimport.setCostcentercode("");
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
        stocktransactionimport.setOrdercountercode(stocktransaction[2].toString());
        stocktransactionimport.setOrdercode(stocktransaction[3].toString());
        stocktransactionimport.setOrderline(Integer.parseInt(stocktransaction[1].toString()));
        stocktransactionimport.setOrdersubline(0);
        stocktransactionimport.setOrdercomponentline(0);
        stocktransactionimport.setOrderdeliveryline(0);
        stocktransactionimport.setProductionordercode(stocktransaction[0].toString());
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

    public static Stocktransactionimport to(String companycode, StitchIssuePackTransaction stitchIssuePackTransaction, Long stockTransactionId, Integer lineNo, String templatecode, String detailType, Long parentAdditionId, Logicalwarehouse logicalwarehousecode, boolean isReciept, StitchIssuePack stitchIssuePack, String stocktransactiontype) {
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        Stocktransactionimport stocktransactionimport = new Stocktransactionimport();
        stocktransactionimport.setId(new StocktransactionimportId(Constants.COMPANY_CODE, stockTransactionId.toString(), lineNo));
        stocktransactionimport.setImportstatus(0);
        stocktransactionimport.setNowtrnnumbertransactionnumber("");
        stocktransactionimport.setTransactionstatus("0");
        stocktransactionimport.setItemtypecompanycode(companycode);
        stocktransactionimport.setItemtypecode(stitchIssuePackTransaction.getItemtypecode());
        stocktransactionimport.setTransactiondate(new Date(System.currentTimeMillis()));
        stocktransactionimport.setTransactiontime(new Time(System.currentTimeMillis()));
        stocktransactionimport.setDetailtype(detailType);
        stocktransactionimport.setTemplatecompanycode(companycode);
        stocktransactionimport.setTemplatecode(templatecode);
        stocktransactionimport.setAutoissuetemplatecompanycode("");
        stocktransactionimport.setAutoissuetemplatecode("");
        stocktransactionimport.setStocktransactiontype(stocktransactiontype);
        stocktransactionimport.setDecocompanycode(companycode);
        stocktransactionimport.setDecosubcode01(stitchIssuePackTransaction.getSubcode01());
        stocktransactionimport.setDecosubcode02(stitchIssuePackTransaction.getSubcode02());
        stocktransactionimport.setDecosubcode03(stitchIssuePackTransaction.getSubcode03());
        stocktransactionimport.setDecosubcode04(stitchIssuePackTransaction.getSubcode04());
        stocktransactionimport.setDecosubcode05(stitchIssuePackTransaction.getSubcode05());
        stocktransactionimport.setDecosubcode06(stitchIssuePackTransaction.getSubcode06());
        stocktransactionimport.setDecosubcode07(stitchIssuePackTransaction.getSubcode07());
        stocktransactionimport.setDecosubcode08(stitchIssuePackTransaction.getSubcode08());
        stocktransactionimport.setDecosubcode09(stitchIssuePackTransaction.getSubcode09());
        stocktransactionimport.setDecosubcode10(stitchIssuePackTransaction.getSubcode10());
        stocktransactionimport.setItemdescription("");
        stocktransactionimport.setLogicalwarehousecompanycode(companycode);
        if (templatecode != null && templatecode.equalsIgnoreCase("T04")) {
            if(isReciept == true) {
                stocktransactionimport.setLogicalwarehousecode(logicalwarehousecode.getId().getCode());
            } else {
                stocktransactionimport.setLogicalwarehousecode(stitchIssuePackTransaction.getStitchwarehousecode());
            }
        } else {
            stocktransactionimport.setLogicalwarehousecode(logicalwarehousecode.getId().getCode());
        }
        stocktransactionimport.setTransferallocation("");
        stocktransactionimport.setIssueqtyfrombalance((short) 0);
        stocktransactionimport.setUserprimaryuomcode(stitchIssuePackTransaction.getPrimaryuomcode());
        stocktransactionimport.setUserprimaryquantity(new BigDecimal(stitchIssuePackTransaction.getPrimaryquantity()));
        stocktransactionimport.setUsersecondaryuomcode(stitchIssuePackTransaction.getSecondaryuomcode());
        stocktransactionimport.setUsersecondaryquantity(new BigDecimal(stitchIssuePackTransaction.getSecondaryquantity()));
        stocktransactionimport.setUserpackaginguomcode(null);
        stocktransactionimport.setUserpackagingquantity(null);
        stocktransactionimport.setWeightunitofmeasurecode("");
        stocktransactionimport.setWeightgross(new BigDecimal("0"));
        stocktransactionimport.setWeightnet(new BigDecimal("0"));
        stocktransactionimport.setWeightrealnet(new BigDecimal("0"));
        stocktransactionimport.setDerivationcode(null);
        stocktransactionimport.setDerivationlinenumber(null);
        stocktransactionimport.setDerivationcomponentlinenumber(null);
        stocktransactionimport.setQualitylvlitemtypecompanycode(companycode);
        stocktransactionimport.setQualitylevelcode(new BigInteger("1"));
        /*  stocktransactionimport.setQualityreasoncompanycode();
            stocktransactionimport.setQualityreasoncode();
            stocktransactionimport.setFirstqualitycontroldate();
            stocktransactionimport.setFirstqualitycontrolcounter();
            stocktransactionimport.setFirstqualitycontrolnumber();*/
        stocktransactionimport.setPhysicalwarehousecompanycode(companycode);
        if (templatecode != null && templatecode.equalsIgnoreCase("T04")) {
            stocktransactionimport.setPhysicalwarehousecode(logicalwarehousecode.getPhysicalwarehousecode());
        } else {
            stocktransactionimport.setPhysicalwarehousecode(logicalwarehousecode.getPhysicalwarehousecode());
        }
        stocktransactionimport.setWhslocwhszonephywhscmycode(companycode);
        stocktransactionimport.setWhslocationwarehousezonecode("");
        stocktransactionimport.setWarehouselocationcode("");
        stocktransactionimport.setContainercompanycode(companycode);
        stocktransactionimport.setContaineritemtypecode("");
        stocktransactionimport.setContainersubcode01("");
        stocktransactionimport.setContainerelementcompanycode("");
        stocktransactionimport.setContainerelementcode("");
        stocktransactionimport.setLotcompanycode(companycode);
        stocktransactionimport.setLotcode("");
        stocktransactionimport.setItemelementcompanycode(companycode);
        stocktransactionimport.setItemelementsubcodekey("");
        stocktransactionimport.setItemelementcode("");
        stocktransactionimport.setProjectcode(stitchIssuePack.getProjectcode());
        stocktransactionimport.setCostcentercompanycode(companycode);
        stocktransactionimport.setCostcentercode("");
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
        stocktransactionimport.setOrdercountercode(stitchIssuePackTransaction.getDemandcountercode());
        stocktransactionimport.setOrdercode(stitchIssuePackTransaction.getDemandcode());
        stocktransactionimport.setOrderline(stitchIssuePackTransaction.getGroupstepnumber().intValue());
        stocktransactionimport.setOrdersubline(0);
        stocktransactionimport.setOrdercomponentline(0);
        stocktransactionimport.setOrderdeliveryline(0);
        stocktransactionimport.setProductionordercode(stitchIssuePackTransaction.getProductionCode());
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
     * {@code DELETE  /stitch-issue-packs/:id} : delete the "id" stitchIssuePack.
     *
     * @param id the id of the stitchIssuePack to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/stitch-issue-packs/{id}")
    public ResponseEntity<Void> deleteStitchIssuePack(@PathVariable Long id) {
        log.debug("REST request to delete StitchIssuePack : {}", id);
        stitchIssuePackRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code DELETE  /stitch-issue-packs/:id} : delete the "id" stitchIssuePack.
     *
     * @param id the id of the stitchIssuePack to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/stitch-issue-pack-details/{id}")
    public ResponseEntity<Void> deleteStitchIssuePackDetails(@PathVariable Long id) {
        log.debug("REST request to delete StitchIssuePackDetails : {}", id);
        stitchIssuePackDetailsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code GET  /cut-bundle-entries/:id} : get the "id" cutBundleEntry.
     *
     * @param id the id of the cutBundleEntry to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cutBundleEntry, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/stitch-stock-details-bundle/{id}")
    public ResponseEntity<List<StitchIssuePackDetailsBean>> getStitchStickByBundle(@PathVariable Long id) {
        log.debug("REST request to get CutBundleEntry : {}", id);
        List<StitchIssuePackDetailsBean> stitchIssuePackDetailsBeans = new ArrayList<>();
        List<StitchStockDetails> stitchStockDetails = stitchStockDetailsRepository.findAllByBundleId(id);
        if (stitchStockDetails != null) {
            for (StitchStockDetails stitchStockDetail : stitchStockDetails) {
                StitchIssuePackDetails stitchIssuePackDetails = stitchIssuePackDetailsRepository.findStitchIssuePackDetailsByStockId(stitchStockDetail.getId());
                if (stitchIssuePackDetails != null) {
                } else {
                    StitchIssuePackDetailsBean stitchIssuePackDetailsBean = new StitchIssuePackDetailsBean();
                    BeanUtils.copyProperties(stitchStockDetail, stitchIssuePackDetailsBean);
                    stitchIssuePackDetailsBean.setId(null);
                    stitchIssuePackDetailsBean.setCreatedby(null);
                    stitchIssuePackDetailsBean.setCreateddate(null);
                    stitchIssuePackDetailsBean.setLastupdatedby(null);
                    stitchIssuePackDetailsBean.setLastupdateddate(null);
                    stitchIssuePackDetailsBean.setItemtypecode(stitchStockDetail.getItemtypecode());
                    stitchIssuePackDetailsBean.setDecosubcode01(stitchStockDetail.getSubcode01());
                    stitchIssuePackDetailsBean.setDecosubcode02(stitchStockDetail.getSubcode02());
                    stitchIssuePackDetailsBean.setDecosubcode03(stitchStockDetail.getSubcode03());
                    stitchIssuePackDetailsBean.setDecosubcode04(stitchStockDetail.getSubcode04());
                    stitchIssuePackDetailsBean.setDecosubcode05(stitchStockDetail.getSubcode05());
                    stitchIssuePackDetailsBean.setDecosubcode06(stitchStockDetail.getSubcode06());
                    stitchIssuePackDetailsBean.setDecosubcode07(stitchStockDetail.getSubcode07());
                    stitchIssuePackDetailsBean.setDecosubcode08(stitchStockDetail.getSubcode08());
                    stitchIssuePackDetailsBean.setDecosubcode09(stitchStockDetail.getSubcode09());
                    stitchIssuePackDetailsBean.setDecosubcode10(stitchStockDetail.getSubcode10());
                    stitchIssuePackDetailsBean.setBaseprimaryquantityunit(stitchStockDetail.getPrimaryquantity());
                    stitchIssuePackDetailsBean.setBaseprimaryunitcode(stitchStockDetail.getPrimaryuomcode());
                    stitchIssuePackDetailsBean.setBasesecondaryquantityunit(stitchStockDetail.getSecondaryquantity());
                    stitchIssuePackDetailsBean.setBasesecondaryunitcode(stitchStockDetail.getSecondaryuomcode());

                    stitchIssuePackDetailsBean.setStitchStockDetailsId(stitchStockDetail.getId());
                    stitchIssuePackDetailsBeans.add(stitchIssuePackDetailsBean);
                }
            }
        } else {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "Invalid Bundle Number.")).build();
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(stitchIssuePackDetailsBeans));
    }

    /**
     * {@code GET  /cut-bundle-entries/:id} : get the "id" cutBundleEntry.
     *
     * @param id the id of the cutBundleEntry to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cutBundleEntry, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/stitch-stock-details-piece/{id}")
    public ResponseEntity<List<StitchIssuePackDetailsBean>> getStitchStickByPiece(@PathVariable Long id) {
        log.debug("REST request to get CutBundleEntry : {}", id);
        List<StitchIssuePackDetailsBean> stitchIssuePackDetailsBeans = new ArrayList<>();
        StitchStockDetails stitchStockDetail = stitchStockDetailsRepository.findAllByPieceId(id);
        if (stitchStockDetail != null) {
            StitchIssuePackDetails stitchIssuePackDetails = stitchIssuePackDetailsRepository.findStitchIssuePackDetailsByStockId(stitchStockDetail.getId());
            if (stitchIssuePackDetails != null) {
                return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "Piece Number already used.")).build();
            } else {
                StitchIssuePackDetailsBean stitchIssuePackDetailsBean = new StitchIssuePackDetailsBean();
                BeanUtils.copyProperties(stitchStockDetail, stitchIssuePackDetailsBean);
                stitchIssuePackDetailsBean.setId(null);
                stitchIssuePackDetailsBean.setCreatedby(null);
                stitchIssuePackDetailsBean.setCreateddate(null);
                stitchIssuePackDetailsBean.setLastupdatedby(null);
                stitchIssuePackDetailsBean.setLastupdateddate(null);
                stitchIssuePackDetailsBean.setItemtypecode(stitchStockDetail.getItemtypecode());
                stitchIssuePackDetailsBean.setDecosubcode01(stitchStockDetail.getSubcode01());
                stitchIssuePackDetailsBean.setDecosubcode02(stitchStockDetail.getSubcode02());
                stitchIssuePackDetailsBean.setDecosubcode03(stitchStockDetail.getSubcode03());
                stitchIssuePackDetailsBean.setDecosubcode04(stitchStockDetail.getSubcode04());
                stitchIssuePackDetailsBean.setDecosubcode05(stitchStockDetail.getSubcode05());
                stitchIssuePackDetailsBean.setDecosubcode06(stitchStockDetail.getSubcode06());
                stitchIssuePackDetailsBean.setDecosubcode07(stitchStockDetail.getSubcode07());
                stitchIssuePackDetailsBean.setDecosubcode08(stitchStockDetail.getSubcode08());
                stitchIssuePackDetailsBean.setDecosubcode09(stitchStockDetail.getSubcode09());
                stitchIssuePackDetailsBean.setDecosubcode10(stitchStockDetail.getSubcode10());
                stitchIssuePackDetailsBean.setBaseprimaryquantityunit(stitchStockDetail.getPrimaryquantity());
                stitchIssuePackDetailsBean.setBaseprimaryunitcode(stitchStockDetail.getPrimaryuomcode());
                stitchIssuePackDetailsBean.setBasesecondaryquantityunit(stitchStockDetail.getSecondaryquantity());
                stitchIssuePackDetailsBean.setBasesecondaryunitcode(stitchStockDetail.getSecondaryuomcode());

                stitchIssuePackDetailsBean.setStitchStockDetailsId(stitchStockDetail.getId());
                stitchIssuePackDetailsBeans.add(stitchIssuePackDetailsBean);
            }
        } else {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "Invalid Piece Number.")).build();
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(stitchIssuePackDetailsBeans));
    }

    @GetMapping("/stitch-issue-packs-pdf/{id}")
    void getStitchPackPdf(@PathVariable Long id, HttpServletResponse response) {
        if (id != null && id.intValue() > 0) {
            try {
                List<StitchIssuePackBean> stitchIssuePackBeans = new ArrayList<>();
                StitchIssuePack stitchIssuePack = stitchIssuePackRepository.findById(id).orElse(null);
                StitchIssuePackBean stitchIssuePackBean = new StitchIssuePackBean();
                BeanUtils.copyProperties(stitchIssuePack, stitchIssuePackBean);
                stitchIssuePackBean.setCreateddatenew(Date.from(stitchIssuePack.getCreateddate()));

                Factory factory = factoryRepository.findById(stitchIssuePackBean.getPlantCode()).orElse(null);
                String add1 = (factory.getAddressline1() != null ? factory.getAddressline1().trim() : "") + (factory.getAddressline2() != null ? factory.getAddressline2().trim() : "");
                stitchIssuePackBean.setPlantAddress(add1);

                Factory factoryDest = factoryRepository.findById(stitchIssuePackBean.getDestinationPlantCode()!= null ? stitchIssuePackBean.getDestinationPlantCode() : stitchIssuePackBean.getPlantCode()).orElse(null);
                String add2 = (factoryDest.getAddressline1() != null ? factoryDest.getAddressline1().trim() : "") + (factoryDest.getAddressline2() != null ? factoryDest.getAddressline2().trim() : "");
                stitchIssuePackBean.setDestinationPlantAddress(add2);

                if (stitchIssuePackBean != null && stitchIssuePackBean.getId() > 0) {
                    List<StitchIssuePackDetails> stitchIssuePackDetails = stitchIssuePackDetailsRepository.findAllByStitchIssuePackId(stitchIssuePackBean.getId());
                    if (stitchIssuePackDetails != null && stitchIssuePackDetails.size() > 0) {
                        List<StitchIssuePackDetailsBean> stitchIssuePackDetailsBeans = new ArrayList<>();
                        for (StitchIssuePackDetails line : stitchIssuePackDetails) {
                            List<StitchStockDetails> stitchStockDetails = stitchStockDetailsRepository.findAllByPieceIds(line.getCutPlanBundleDetailsId());
                            CutPlanBundle cutPlanBundle = cutPlanBundleRepository.findById(line.getCutPlanBundleId()).orElse(null);
                            StitchIssuePackDetailsBean stitchIssuePackDetailsBean = new StitchIssuePackDetailsBean();
                            BeanUtils.copyProperties(line, stitchIssuePackDetailsBean);
                            if (stitchStockDetails != null && stitchStockDetails.size() > 0) {
                                stitchIssuePackDetailsBean.setProductionCode(stitchStockDetails.get(0).getProductionCode());
                            }
                            stitchIssuePackDetailsBean.setBundleCode(cutPlanBundle.getBundleCode());
                            stitchIssuePackDetailsBeans.add(stitchIssuePackDetailsBean);
                        }
                        stitchIssuePackBean.setStitchIssuePackDetails(stitchIssuePackDetailsBeans);
                    }
                    stitchIssuePackBeans.add(stitchIssuePackBean);
                    String path = applicationProperties.getTemplatePath() + "jasper/";
                    JasperDesign jasperDesign = JRXmlLoader.load(path + "/IssueToPackRpt.jrxml");
                    JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                    Map<String, Object> parameters = new HashMap<>();
                    JRDataSource jrDataSource = new JRBeanCollectionDataSource(stitchIssuePackBeans);
                    parameters.put("datasource", jrDataSource);
                    parameters.put("SUBREPORT_DIR", path);
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
                    response.setContentType("application/x-pdf");
                    response.setHeader("Content-Disposition", "attachment; filename=IssueToPackRpt.pdf");
                    final OutputStream outputStream = response.getOutputStream();
                    JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
