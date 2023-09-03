package io.vamani.application.web.rest;

import io.vamani.application.config.Constants;
import io.vamani.application.db2.domain.*;
import io.vamani.application.db2.repository.*;
import io.vamani.application.domain.*;
import io.vamani.application.model.*;
import io.vamani.application.repository.*;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.iconnect.myapp.domain.CutBundleEntry}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CutBundleEntryResource {

    private final Logger log = LoggerFactory.getLogger(CutBundleEntryResource.class);

    private static final String ENTITY_NAME = "cutBundleEntry";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CutBundleEntryRepository cutBundleEntryRepository;

    @Autowired
    private CutPlanBundleRepository cutPlanBundleRepository;


    @Autowired
    private CutPlanBundleDetailsRepository cutPlanBundleDetailsRepository;

    @Autowired
    private ProductiondemandRepository productiondemandRepository;

    @Autowired
    private StocktransactionimportRepository stocktransactionimportRepository;

    @Autowired
    private StocktransactionRepository stocktransactionRepository;

    @Autowired
    private AdstorageimportRepository adstorageimportRepository;

    @Autowired
    private WarehousezoneRepository warehousezoneRepository;

    @Autowired
    private WarehouselocationRepository warehouselocationRepository;

    @Autowired
    private LogicalwarehouseRepository logicalwarehouseRepository;

    @Autowired
    private CutIssueStitchDetailsRepository cutIssueStitchDetailsRepository;

    @Autowired
    private StitchLineIssueDetailsRepository stitchLineIssueDetailsRepository;

    @Autowired
    private CutBundleLockRepository cutBundleLockRepository;

    @Autowired
    private VcutDeviceLineMasterRepository vcutDeviceLineMasterRepository;

    @Autowired
    private VcutStylePlanUploadRepository vcutStylePlanUploadRepository;

    @Autowired
    private VcutOperationMasterRepository vcutOperationMasterRepository;

    @Autowired
    private VcutOperationPassDetailsRepository vcutOperationPassDetailsRepository;

    public CutBundleEntryResource(CutBundleEntryRepository cutBundleEntryRepository) {
        this.cutBundleEntryRepository = cutBundleEntryRepository;
    }

    /**
     * {@code POST  /cut-bundle-entries} : Create a new cutBundleEntry.
     *
     * @param cutBundleEntry the cutBundleEntry to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cutBundleEntry, or with status {@code 400 (Bad Request)} if the cutBundleEntry has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cut-bundle-entries")
    public ResponseEntity<List<CutPlanBundleMatrixBean>> createCutBundleEntry(@Valid @RequestBody List<CutPlanBundleMatrixBean> cutPlanBundleMatrixBeans) throws URISyntaxException {
        log.debug("REST request to save CutBundleEntry : {}", cutPlanBundleMatrixBeans);
        List<CutPlanBundleMatrixBean> cutBundleEntries = new ArrayList<>();
        for (CutPlanBundleMatrixBean cutPlanBundleMatrixBean : cutPlanBundleMatrixBeans) {
            CutBundleEntry cutBundleEntry = null;
            if (cutPlanBundleMatrixBean.getId() != null) {
                cutBundleEntry = cutBundleEntryRepository.findById(cutPlanBundleMatrixBean.getId()).orElse(null);
                cutBundleEntry.setBundleSize(cutPlanBundleMatrixBean.getBundleSize());
                cutBundleEntry.setBundlePcs(cutPlanBundleMatrixBean.getBundlePcs());
                cutBundleEntry.setDestination(cutPlanBundleMatrixBean.getDestination());
                cutBundleEntry.setDestinationDesc(cutPlanBundleMatrixBean.getDestinationDesc());
                cutBundleEntry.setSaveFlag(true);
                cutBundleEntry.setLastupdatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
                cutBundleEntry.setLastupdateddate(Instant.now());
                CutBundleEntry result = cutBundleEntryRepository.save(cutBundleEntry);
                if (result != null) {
                    cutPlanBundleMatrixBean.setId(result.getId());
                    cutBundleEntries.add(cutPlanBundleMatrixBean);
                }
            } else {
                cutBundleEntry = new CutBundleEntry();
                cutBundleEntry.setPorductionCounterCode(cutPlanBundleMatrixBean.getPorductionCounterCode());
                cutBundleEntry.setProductionCode(cutPlanBundleMatrixBean.getProductionCode());
                cutBundleEntry.setPlantCode(cutPlanBundleMatrixBean.getPlantCode());
                cutBundleEntry.setStyle(cutPlanBundleMatrixBean.getStyle());
                cutBundleEntry.setColor(cutPlanBundleMatrixBean.getColor());
                cutBundleEntry.setSize(cutPlanBundleMatrixBean.getSizeCode());
                cutBundleEntry.setBundleSize(cutPlanBundleMatrixBean.getBundleSize());
                cutBundleEntry.setBundlePcs(cutPlanBundleMatrixBean.getBundlePcs());
                cutBundleEntry.setDestination(cutPlanBundleMatrixBean.getDestination());
                cutBundleEntry.setDestinationDesc(cutPlanBundleMatrixBean.getDestinationDesc());
                cutBundleEntry.setSaveFlag(true);
                cutBundleEntry.setCreatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
                cutBundleEntry.setCreateddate(Instant.now());
                CutBundleEntry result = cutBundleEntryRepository.save(cutBundleEntry);
                if (result != null) {
                    cutPlanBundleMatrixBean.setId(result.getId());
                    cutBundleEntries.add(cutPlanBundleMatrixBean);
                }
            }
        }

        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, ""))
            .body(cutBundleEntries);
    }

    private CutBundleLock saveCutBundle(CutBundleLock cutBundleLock) {
        return cutBundleLockRepository.saveAndFlush(cutBundleLock);
    }

    /**
     * {@code POST  /cut-bundle-entries} : Create a new cutBundleEntry.
     *
     * @param cutBundleEntry the cutBundleEntry to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cutBundleEntry, or with status {@code 400 (Bad Request)} if the cutBundleEntry has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cut-bundle-entries-lock")
    public  ResponseEntity<List<CutPlanBundleMatrixBean>> saveCutBundle(@Valid @RequestBody List<CutPlanBundleMatrixBean> cutPlanBundleMatrixBeans) throws URISyntaxException {
        CutBundleLockId id = null;
        if(cutPlanBundleMatrixBeans != null && cutPlanBundleMatrixBeans.size()>0) {
            CutPlanBundleMatrixBean cutPlanBundleMatrixBean = cutPlanBundleMatrixBeans.get(0);
            CutBundleEntry cutBundleEntry = cutBundleEntryRepository.findById(cutPlanBundleMatrixBean.getId()).orElse(null);
            if (cutBundleEntry != null) {
                id = new CutBundleLockId(cutBundleEntry.getProductionCode().trim(), cutBundleEntry.getPlantCode().trim());
                CutBundleLock cutBundleLock = cutBundleLockRepository.findById(id).orElse(null);
                if(cutBundleLock != null) {
                    return ResponseEntity.badRequest().headers(io.vamani.application.web.rest.util.HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Already locked by " + cutBundleLock.getLockedBy() + "!")).body(null);
                } else {
                    cutBundleLock = new CutBundleLock();
                    cutBundleLock.setId(id);
                    cutBundleLock.setLockedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                    cutBundleLock.setLockedDate(Instant.now());
                    CutBundleLock bundleLock = cutBundleLockRepository.saveAndFlush(cutBundleLock);
                }
            }
        }
        return ResponseEntity.ok().headers(io.vamani.application.web.rest.util.HeaderUtil.createFailureAlert(ENTITY_NAME, "", "")).build();
    }

    /**
     * {@code POST  /cut-bundle-entries} : Create a new cutBundleEntry.
     *
     * @param cutBundleEntry the cutBundleEntry to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cutBundleEntry, or with status {@code 400 (Bad Request)} if the cutBundleEntry has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cut-bundle-entries-post")
    public ResponseEntity<List<CutPlanBundleMatrixBean>> postCutBundleEntry(@Valid @RequestBody List<CutPlanBundleMatrixBean> cutPlanBundleMatrixBeans) throws URISyntaxException {
        log.debug("REST request to save CutBundleEntry : {}", cutPlanBundleMatrixBeans);
        List<CutPlanBundleMatrixBean> cutBundleEntries = new ArrayList<>();
        Logicalwarehouse logicalwarehouse = null;

        CutBundleLockId id = null;
        for (CutPlanBundleMatrixBean cutPlanBundleMatrixBean : cutPlanBundleMatrixBeans) {
            CutBundleEntry cutBundleEntry = cutBundleEntryRepository.findById(cutPlanBundleMatrixBean.getId()).orElse(null);
            String pcs = null;
            if (cutPlanBundleMatrixBean != null && cutPlanBundleMatrixBean.getProductionCode().startsWith("CP")) {
                pcs = cutPlanBundleRepository.findMaxPcByDetails(cutPlanBundleMatrixBean.getProductionCode(), cutPlanBundleMatrixBean.getPlantCode(), cutPlanBundleMatrixBean.getStyle(), cutPlanBundleMatrixBean.getColor(), cutPlanBundleMatrixBean.getSizeCode());
            } else {
                pcs = cutPlanBundleRepository.findMaxHFPcByDetails(cutPlanBundleMatrixBean.getProductionCode(), cutPlanBundleMatrixBean.getPlantCode(), cutPlanBundleMatrixBean.getStyle(), cutPlanBundleMatrixBean.getColor(), cutPlanBundleMatrixBean.getSizeCode());
            }

            if (cutPlanBundleMatrixBean.getProductionCode().startsWith("CP")) {
                logicalwarehouse = logicalwarehouseRepository.findInTransitCUTWarehouseByPlantCode(cutPlanBundleMatrixBean.getPlantCode());
            } else {
                logicalwarehouse = logicalwarehouseRepository.findInTransitHFCUTWarehouseByPlantCode(cutPlanBundleMatrixBean.getPlantCode());
            }
            Integer x = 0;
            if(pcs != null) {
                x = Integer.parseInt(pcs);
            }
            if (cutBundleEntry != null && (cutBundleEntry.getBundleSize() != null && cutBundleEntry.getBundleSize().doubleValue() > 0) &&
                (cutBundleEntry.getBundlePcs() != null && cutBundleEntry.getBundlePcs().doubleValue() > 0) &&
                (cutPlanBundleMatrixBean.getBundleSize() != null && cutPlanBundleMatrixBean.getBundleSize().doubleValue() > 0) &&
                (cutPlanBundleMatrixBean.getBundlePcs() != null && cutPlanBundleMatrixBean.getBundlePcs().doubleValue() > 0)) {
                if(id == null) {
                    id = new CutBundleLockId(cutBundleEntry.getProductionCode().trim(), cutBundleEntry.getPlantCode().trim());
                }
                if (cutPlanBundleMatrixBean.getCutPlanBundleMatrixBreakups() != null && cutPlanBundleMatrixBean.getCutPlanBundleMatrixBreakups().size() > 0) {
                    List<Productiondemand> productiondemands = null;
                    if(cutPlanBundleMatrixBean.getProductionCode() != null && cutPlanBundleMatrixBean.getProductionCode().startsWith("CP")) {
                        productiondemands = productiondemandRepository.getDemandDetailsByCode(Constants.COMPANY_CODE, cutPlanBundleMatrixBean.getProductionCode(), cutPlanBundleMatrixBean.getStyle(), cutPlanBundleMatrixBean.getColor(), cutPlanBundleMatrixBean.getSizeCode());
                    } else {
                        productiondemands = productiondemandRepository.getHCDemandDetailsByCode(Constants.COMPANY_CODE, cutPlanBundleMatrixBean.getProductionCode(), cutPlanBundleMatrixBean.getStyle(), cutPlanBundleMatrixBean.getColor(), cutPlanBundleMatrixBean.getSizeCode());
                    }
                    Productiondemand productiondemand = productiondemands.get(0);
                    if (logicalwarehouse != null && productiondemands != null) {
                        String zone = warehousezoneRepository.findWarehousezone(Constants.COMPANY_CODE, logicalwarehouse.getId().getCode());
                        String location = warehouselocationRepository.fetchWarehouselocation(Constants.COMPANY_CODE,  logicalwarehouse.getId().getCode(), zone);
                        for(CutPlanBundleMatrixBreakup cutPlanBundleMatrixBreakup : cutPlanBundleMatrixBean.getCutPlanBundleMatrixBreakups()) {
                            Long stockTransactionDestinationId = stocktransactionimportRepository.getNextSequence();
                            int linNo = 0;
                            Long parentAdditionId = adstorageimportRepository.getNextSequence();
                            Stocktransactionimport stocktransactionimport = to(Constants.COMPANY_CODE, productiondemand, stockTransactionDestinationId, ++linNo, "M01", "0", BigDecimal.valueOf(cutPlanBundleMatrixBreakup.getBundlePcs()), cutPlanBundleMatrixBean.getPorductionCounterCode(), cutPlanBundleMatrixBean.getProductionCode(), "0", 1, zone, location, parentAdditionId, logicalwarehouse);
                            Stocktransactionimport stockResult = stocktransactionimportRepository.save(stocktransactionimport);
                            if (stockResult != null) {
                                Adstorageimport adstorageimportBundleNo = new Adstorageimport(new AdstorageimportId("StockTransaction", parentAdditionId, "StockTransaction", "InterfaceBundleNumber", "InterfaceBundleNumber"), 0, 0, 0, 0, cutPlanBundleMatrixBreakup.getBundle(), 0, (short) 0, null, new BigDecimal(0), 0L, null, null, 0L);
                                adstorageimportRepository.save(adstorageimportBundleNo);

                                Adstorageimport adstorageimportDestination = new Adstorageimport(new AdstorageimportId("StockTransaction", parentAdditionId, "StockTransaction", "Destination", "Destination"), 0, 0, 0, 0, cutPlanBundleMatrixBean.getDestination(), 0, (short) 0, null, new BigDecimal(0), 0L, null, null, 0L);
                                adstorageimportRepository.save(adstorageimportDestination);
                                stockResult.setImportstatus(1);
                                stockResult = stocktransactionimportRepository.save(stockResult);
                                if(stockResult != null) {
                                    CutPlanBundle cutPlanBundle = new CutPlanBundle();
                                    cutPlanBundle.setProductionCode(stockResult.getProductionordercode());
                                    cutPlanBundle.setGroupstepnumber(stockResult.getOrderline().longValue());
                                    cutPlanBundle.setDemandcountercode(stockResult.getOrdercountercode());
                                    cutPlanBundle.setDemandcode(stockResult.getOrdercode());
                                    cutPlanBundle.setPlantCode(cutPlanBundleMatrixBean.getPlantCode());
                                    cutPlanBundle.setDestination(cutPlanBundleMatrixBean.getDestination());
                                    cutPlanBundle.setItemtypecode(stockResult.getItemtypecode());
                                    cutPlanBundle.setSubcode01(stockResult.getDecosubcode01());
                                    cutPlanBundle.setSubcode02(stockResult.getDecosubcode02());
                                    cutPlanBundle.setSubcode03(stockResult.getDecosubcode03());
                                    cutPlanBundle.setSubcode04(stockResult.getDecosubcode04());
                                    cutPlanBundle.setSubcode05(stockResult.getDecosubcode05());
                                    cutPlanBundle.setSubcode06(stockResult.getDecosubcode06());
                                    cutPlanBundle.setSubcode07(stockResult.getDecosubcode07());
                                    cutPlanBundle.setSubcode08(stockResult.getDecosubcode08());
                                    cutPlanBundle.setSubcode09(stockResult.getDecosubcode09());
                                    cutPlanBundle.setSubcode10(stockResult.getDecosubcode10());
                                    cutPlanBundle.setLogicalwarehousecode(stockResult.getLogicalwarehousecode());
                                    cutPlanBundle.setPrimaryquantity(stockResult.getUserprimaryquantity().doubleValue());
                                    cutPlanBundle.setPrimaryuomcode(stockResult.getUserprimaryuomcode());
                                    cutPlanBundle.setSecondaryquantity(stockResult.getUsersecondaryquantity().doubleValue());
                                    cutPlanBundle.setSecondaryuomcode(stockResult.getUsersecondaryuomcode());
                                    cutPlanBundle.setPhysicalwarehousecode(stockResult.getPhysicalwarehousecode());
                                    cutPlanBundle.setWhslocationwarehousezonecode(stockResult.getWhslocationwarehousezonecode());
                                    cutPlanBundle.setWarehouselocationcode(stockResult.getWarehouselocationcode());
                                    cutPlanBundle.setLotcode(stockResult.getLotcode());
                                    cutPlanBundle.setProjectcode(stockResult.getProjectcode());
                                    cutPlanBundle.setBundleCode(cutPlanBundleMatrixBreakup.getBundle());
                                    cutPlanBundle.setStocktransactionid(Long.parseLong(stockResult.getId().getTransactionnumber()));
                                    cutPlanBundle.setCreatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
                                    cutPlanBundle.setCreateddate(Instant.now());
                                    CutPlanBundle resultBundle = cutPlanBundleRepository.save(cutPlanBundle);
                                    if (resultBundle != null) {
                                        for (int i = 0; i < stockResult.getUserprimaryquantity().intValue(); i++) {
                                            CutPlanBundleDetails cutPlanBundleDetail = new CutPlanBundleDetails();
                                            cutPlanBundleDetail.setProductionCode(stockResult.getProductionordercode());
                                            cutPlanBundleDetail.setGroupstepnumber(stockResult.getOrderline().longValue());
                                            cutPlanBundleDetail.setDemandcountercode(stockResult.getOrdercountercode());
                                            cutPlanBundleDetail.setDemandcode(stockResult.getOrdercode());
                                            cutPlanBundleDetail.setDestination(cutPlanBundleMatrixBean.getDestination());
                                            cutPlanBundleDetail.setItemtypecode(stockResult.getItemtypecode());
                                            cutPlanBundleDetail.setSubcode01(stockResult.getDecosubcode01());
                                            cutPlanBundleDetail.setSubcode02(stockResult.getDecosubcode02());
                                            cutPlanBundleDetail.setSubcode03(stockResult.getDecosubcode03());
                                            cutPlanBundleDetail.setSubcode04(stockResult.getDecosubcode04());
                                            cutPlanBundleDetail.setSubcode05(stockResult.getDecosubcode05());
                                            cutPlanBundleDetail.setSubcode06(stockResult.getDecosubcode06());
                                            cutPlanBundleDetail.setSubcode07(stockResult.getDecosubcode07());
                                            cutPlanBundleDetail.setSubcode08(stockResult.getDecosubcode08());
                                            cutPlanBundleDetail.setSubcode09(stockResult.getDecosubcode09());
                                            cutPlanBundleDetail.setSubcode10(stockResult.getDecosubcode10());
                                            cutPlanBundleDetail.setLogicalwarehousecode(stockResult.getLogicalwarehousecode());
                                            cutPlanBundleDetail.setPrimaryquantity(1.0);
                                            cutPlanBundleDetail.setPrimaryuomcode(stockResult.getUserprimaryuomcode());

                                            cutPlanBundleDetail.setSecondaryquantity((stockResult.getUsersecondaryquantity().doubleValue()/stockResult.getUserprimaryquantity().doubleValue()));
                                            cutPlanBundleDetail.setSecondaryuomcode(stockResult.getUsersecondaryuomcode());
                                            cutPlanBundleDetail.setPhysicalwarehousecode(stockResult.getPhysicalwarehousecode());
                                            cutPlanBundleDetail.setWhslocationwarehousezonecode(stockResult.getWhslocationwarehousezonecode());
                                            cutPlanBundleDetail.setWarehouselocationcode(stockResult.getWarehouselocationcode());
                                            cutPlanBundleDetail.setLotcode(stockResult.getLotcode());
                                            cutPlanBundleDetail.setProjectcode(stockResult.getProjectcode());
                                            cutPlanBundleDetail.setProductCode("P" + (++x));
                                            cutPlanBundleDetail.setCreatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
                                            cutPlanBundleDetail.setCreateddate(Instant.now());
                                            cutPlanBundleDetail.setCutPlanBundle(resultBundle);
                                            CutPlanBundleDetails resultCutPlanBundleDetails = cutPlanBundleDetailsRepository.save(cutPlanBundleDetail);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            cutBundleEntry.setBundleSize(null);
            cutBundleEntry.setBundlePcs(null);
            cutBundleEntry.setSaveFlag(null);
            cutBundleEntry.setLastupdatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
            cutBundleEntry.setLastupdateddate(Instant.now());
            CutBundleEntry result = cutBundleEntryRepository.save(cutBundleEntry);
            if (result != null) {
                cutPlanBundleMatrixBean.setId(result.getId());
                cutBundleEntries.add(cutPlanBundleMatrixBean);
            }
        }
        cutBundleLockRepository.deleteById(id);

        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, ""))
            .body(cutBundleEntries);
    }

    public static Stocktransactionimport to(String companycode, Productiondemand productiondemand, Long stockTransactionId, Integer lineNo, String templatecode, String detailType, BigDecimal issuedquantity, String productionordercountercode, String productionordercode, String groupline, Integer qualitylevelcode, String zone, String location, Long parentAdditionId, Logicalwarehouse logicalwarehouse) {
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        Stocktransactionimport stocktransactionimport = new Stocktransactionimport();
        stocktransactionimport.setId(new StocktransactionimportId(Constants.COMPANY_CODE, stockTransactionId.toString(), lineNo));
        stocktransactionimport.setImportstatus(0);
        stocktransactionimport.setNowtrnnumbertransactionnumber("");
        stocktransactionimport.setTransactionstatus("0");
        stocktransactionimport.setItemtypecompanycode(companycode);
        stocktransactionimport.setItemtypecode(productiondemand.getItemtypeaficode());
        stocktransactionimport.setTransactiondate(new Date(System.currentTimeMillis()));
        stocktransactionimport.setTransactiontime(new Time(System.currentTimeMillis()));
        stocktransactionimport.setDetailtype(detailType);
        stocktransactionimport.setTemplatecompanycode(companycode);
        stocktransactionimport.setTemplatecode(templatecode);
        stocktransactionimport.setAutoissuetemplatecompanycode("");
        stocktransactionimport.setAutoissuetemplatecode("");
        stocktransactionimport.setStocktransactiontype("6");
        stocktransactionimport.setDecocompanycode(companycode);
        stocktransactionimport.setDecosubcode01(productiondemand.getSubcode01());
        stocktransactionimport.setDecosubcode02(productiondemand.getSubcode02());
        stocktransactionimport.setDecosubcode03(productiondemand.getSubcode03());
        stocktransactionimport.setDecosubcode04(productiondemand.getSubcode04());
        stocktransactionimport.setDecosubcode05(productiondemand.getSubcode05());
        stocktransactionimport.setDecosubcode06(productiondemand.getSubcode06());
        stocktransactionimport.setDecosubcode07(productiondemand.getSubcode07());
        stocktransactionimport.setDecosubcode08(productiondemand.getSubcode08());
        stocktransactionimport.setDecosubcode09(productiondemand.getSubcode09());
        stocktransactionimport.setDecosubcode10(productiondemand.getSubcode10());
        stocktransactionimport.setItemdescription("");
        stocktransactionimport.setLogicalwarehousecompanycode(companycode);
        stocktransactionimport.setLogicalwarehousecode(logicalwarehouse.getId().getCode());
        stocktransactionimport.setTransferallocation("");
        stocktransactionimport.setIssueqtyfrombalance((short) 0);
        stocktransactionimport.setUserprimaryuomcode(productiondemand.getUserprimaryuomcode());
        stocktransactionimport.setUserprimaryquantity(issuedquantity);
        stocktransactionimport.setUsersecondaryuomcode(productiondemand.getUsersecondaryuomcode());
        Double ratio = productiondemand.getUsersecondaryquantity().doubleValue()/productiondemand.getUserprimaryquantity().doubleValue();
        Double secondaryQuantity = new Double(decimalFormat.format((issuedquantity.doubleValue()*ratio)));
        stocktransactionimport.setUsersecondaryquantity(new BigDecimal(secondaryQuantity));
        stocktransactionimport.setUserpackaginguomcode(null);
        stocktransactionimport.setUserpackagingquantity(new BigDecimal("0"));
        stocktransactionimport.setWeightunitofmeasurecode("");
        stocktransactionimport.setWeightgross(new BigDecimal("0"));
        stocktransactionimport.setWeightnet(new BigDecimal("0"));
        stocktransactionimport.setWeightrealnet(new BigDecimal("0"));
        stocktransactionimport.setDerivationcode(null);
        stocktransactionimport.setDerivationlinenumber(null);
        stocktransactionimport.setDerivationcomponentlinenumber(null);
        stocktransactionimport.setQualitylvlitemtypecompanycode(companycode);
        stocktransactionimport.setQualitylevelcode(BigDecimal.valueOf(qualitylevelcode).toBigInteger());
        /*  stocktransactionimport.setQualityreasoncompanycode();
            stocktransactionimport.setQualityreasoncode();
            stocktransactionimport.setFirstqualitycontroldate();
            stocktransactionimport.setFirstqualitycontrolcounter();
            stocktransactionimport.setFirstqualitycontrolnumber();*/
        stocktransactionimport.setPhysicalwarehousecompanycode(companycode);
        stocktransactionimport.setPhysicalwarehousecode(logicalwarehouse.getPhysicalwarehousecode());
        stocktransactionimport.setWhslocwhszonephywhscmycode(companycode);
        stocktransactionimport.setWhslocationwarehousezonecode(zone);
        stocktransactionimport.setWarehouselocationcode(location);
        stocktransactionimport.setContainercompanycode(companycode);
        stocktransactionimport.setContaineritemtypecode("");
        stocktransactionimport.setContainersubcode01("");
        stocktransactionimport.setContainerelementcompanycode("");
        stocktransactionimport.setContainerelementcode("");
        stocktransactionimport.setLotcompanycode(companycode);
        stocktransactionimport.setLotcode(productionordercode.startsWith("CP") ? productionordercode : null);
        stocktransactionimport.setItemelementcompanycode(companycode);
        stocktransactionimport.setItemelementsubcodekey("");
        stocktransactionimport.setItemelementcode("");
        stocktransactionimport.setProjectcode(productiondemand.getProjectcode());
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
        stocktransactionimport.setOrdercountercode(productiondemand.getId().getCountercode());
        stocktransactionimport.setOrdercode(productiondemand.getId().getCode());
        stocktransactionimport.setOrderline(Integer.parseInt(groupline));
        stocktransactionimport.setOrdersubline(0);
        stocktransactionimport.setOrdercomponentline(0);
        stocktransactionimport.setOrderdeliveryline(0);
        stocktransactionimport.setProductionordercode(productionordercode);
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
     * {@code GET  /cut-bundle-entries} : get all the cutBundleEntries.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cutBundleEntries in body.
     */
    @PostMapping("/cut-bundle-entries-filter")
    public ResponseEntity<List<CutPlanEntryBean>> getAllCutBundleEntries(@RequestBody CutPlanSearch search) {
        log.debug("REST request to get a page of CutBundleEntries");
        String login = SecurityUtils.getCurrentUserLogin().orElse(null);
        String style = "%";
        if (search.getStyle() != null) {
            style = "%" + search.getStyle().toUpperCase() + "%";
        }
        String pono = "%";
        if (search.getPono() != null) {
            pono = "%" + search.getPono().toUpperCase() + "%";
        }
        List<CutPlanEntryBean> cutPlanEntryBeans = new ArrayList<>();
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), search.getSortType() != null && search.getSortType().equalsIgnoreCase("desc") ? Sort.by(search.getSort()).descending() : Sort.by(search.getSort()).ascending()));
        Page<Object[]> page = cutBundleEntryRepository.findAllByProductionorderAndStyle(pono, style, login, search.getPage());
        for(Object[] objects : page.getContent()) {
            CutPlanEntryBean cutPlanEntryBean = new CutPlanEntryBean();
            cutPlanEntryBean.setPorductionCounterCode(objects[0].toString());
            cutPlanEntryBean.setProductionCode(objects[1].toString());
            cutPlanEntryBean.setPlantCode(objects[2].toString());
            cutPlanEntryBean.setStyle(objects[3].toString());
            cutPlanEntryBean.setColor(objects[4].toString());
            cutPlanEntryBean.setDestination(objects[5].toString());
            cutPlanEntryBean.setDestinationDesc(objects[6].toString());
            if (objects[7] != null && Boolean.parseBoolean(objects[7].toString()) == true) {
                cutPlanEntryBean.setStatus("Pending");
            } else {
                cutPlanEntryBean.setStatus("Updated");
            }
            cutPlanEntryBeans.add(cutPlanEntryBean);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(cutPlanEntryBeans);
    }

    /**
     * {@code GET  /cut-bundle-entries/:id} : get the "id" cutBundleEntry.
     *
     * @param id the id of the cutBundleEntry to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cutBundleEntry, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cut-bundle-entries/{id}")
    public ResponseEntity<CutBundleEntry> getCutBundleEntry(@PathVariable Long id) {
        log.debug("REST request to get CutBundleEntry : {}", id);
        Optional<CutBundleEntry> cutBundleEntry = cutBundleEntryRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(cutBundleEntry);
    }

    /**
     * {@code GET  /cut-bundle-entries/:id} : get the "id" cutBundleEntry.
     *
     * @param id the id of the cutBundleEntry to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cutBundleEntry, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cut-plan-bundles/{id}")
    public ResponseEntity<CutPlanBundleEntity> getCutPlanBundle(@PathVariable Long id) {
        log.debug("REST request to get CutBundleEntry : {}", id);
        CutPlanBundleEntity cutBundleEntry = new CutPlanBundleEntity();
        CutIssueStitchDetails cutIssueStitchDetails = cutIssueStitchDetailsRepository.findCutIssueStitchDetailsByBundleId(id);
        if(cutIssueStitchDetails != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "Bundle Number already used.")).build();
        } else {
            CutPlanBundle cutPlanBundle = cutPlanBundleRepository.findById(id).orElse(null);
            if (cutPlanBundle != null) {
                BeanUtils.copyProperties(cutPlanBundle, cutBundleEntry);
                List<Object[]> objects = cutPlanBundleDetailsRepository.findAllProductCodeById(cutBundleEntry.getId());
                if (objects != null && objects.size() == 2) {
                    Object[] object = objects.get(0);
                    cutBundleEntry.setStartPiece(object[1].toString());
                    Object[] object2 = objects.get(1);
                    cutBundleEntry.setEndPiece(object2[1].toString());
                    String productcode = (cutBundleEntry.getSubcode01() != null ? cutBundleEntry.getSubcode01().trim() : "")
                        + (cutBundleEntry.getSubcode02() != null ? " " + cutBundleEntry.getSubcode02().trim() : "")
                        + (cutBundleEntry.getSubcode03() != null ? " " + cutBundleEntry.getSubcode03().trim() : "")
                        + (cutBundleEntry.getSubcode04() != null ? " " + cutBundleEntry.getSubcode04().trim() : "")
                        + (cutBundleEntry.getSubcode05() != null ? " " + cutBundleEntry.getSubcode04().trim() : "")
                        + (cutBundleEntry.getSubcode06() != null ? " " + cutBundleEntry.getSubcode06().trim() : "")
                        + (cutBundleEntry.getSubcode07() != null ? " " + cutBundleEntry.getSubcode07().trim() : "")
                        + (cutBundleEntry.getSubcode08() != null ? " " + cutBundleEntry.getSubcode08().trim() : "")
                        + (cutBundleEntry.getSubcode09() != null ? " " + cutBundleEntry.getSubcode09().trim() : "")
                        + (cutBundleEntry.getSubcode10() != null ? " " + cutBundleEntry.getSubcode10().trim() : "");
                    cutBundleEntry.setSummerizeddescription(productcode);
                }
            } else {
                return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "In-valid Bundle number.")).build();
            }
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(cutBundleEntry));
    }

    /**
     * {@code GET  /cut-bundle-entries/:id} : get the "id" cutBundleEntry.
     *
     * @param id the id of the cutBundleEntry to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cutBundleEntry, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/cut-plan-bundles-stitch")
    public ResponseEntity<CutPlanBundleEntity> getCutPlanBundleStitch(@RequestBody CutPlanSearch search) {
        log.debug("REST request to get CutBundleEntry : {}", search);
        CutPlanBundleEntity cutBundleEntry = new CutPlanBundleEntity();
        StitchLineIssueDetails stitchLineIssueDetails = stitchLineIssueDetailsRepository.findStitchLineIssueDetailsByBundleId(search.getId());
        if(stitchLineIssueDetails != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "Bundle Number already used.")).build();
        } else {
            CutPlanBundle cutPlanBundle = cutPlanBundleRepository.findById(search.getId()).orElse(null);
            if (cutPlanBundle != null) {

                if(search.getStyle().trim().equalsIgnoreCase(cutPlanBundle.getSubcode01().trim()) &&
                    ((cutPlanBundle.getItemtypecode().equals("CPT") && search.getColor().trim().equalsIgnoreCase(cutPlanBundle.getSubcode07().trim())) || (cutPlanBundle.getItemtypecode().equals("HFC") && search.getColor().trim().equalsIgnoreCase(cutPlanBundle.getSubcode05().trim())))&&
                    search.getDestination().trim().equalsIgnoreCase(cutPlanBundle.getDestination())) {
                    BeanUtils.copyProperties(cutPlanBundle, cutBundleEntry);
                    Logicalwarehouse logicalwarehouse = null;
                    if (cutBundleEntry.getProductionCode().startsWith("CP")) {
                        logicalwarehouse = logicalwarehouseRepository.findInTransitWarehouseByPlantCode(search.getPlantCode());
                    } else {
                        logicalwarehouse = logicalwarehouseRepository.findInTransitHFCWarehouseByPlantCode(search.getPlantCode());
                    }

                    if(logicalwarehouse != null) {
                        List<Stocktransaction> stocktransactions = stocktransactionRepository.findByProductionOrderAndDemandAndBundleAndWarehouse(Constants.COMPANY_CODE, cutPlanBundle.getProductionCode(), cutBundleEntry.getDemandcountercode(), cutBundleEntry.getDemandcode(), cutBundleEntry.getBundleCode(), logicalwarehouse.getId().getCode());
                        if(stocktransactions != null && stocktransactions.size()>0) {
                            List<Object[]> objects = cutPlanBundleDetailsRepository.findAllProductCodeById(cutBundleEntry.getId());
                            if (objects != null && objects.size() == 2) {
                                Object[] object = objects.get(0);
                                cutBundleEntry.setStartPiece(object[1].toString());
                                Object[] object2 = objects.get(1);
                                cutBundleEntry.setEndPiece(object2[1].toString());
                                String productcode = (cutBundleEntry.getSubcode01() != null ? cutBundleEntry.getSubcode01().trim() : "")
                                    + (cutBundleEntry.getSubcode02() != null ? " " + cutBundleEntry.getSubcode02().trim() : "")
                                    + (cutBundleEntry.getSubcode03() != null ? " " + cutBundleEntry.getSubcode03().trim() : "")
                                    + (cutBundleEntry.getSubcode04() != null ? " " + cutBundleEntry.getSubcode04().trim() : "")
                                    + (cutBundleEntry.getSubcode05() != null ? " " + cutBundleEntry.getSubcode04().trim() : "")
                                    + (cutBundleEntry.getSubcode06() != null ? " " + cutBundleEntry.getSubcode06().trim() : "")
                                    + (cutBundleEntry.getSubcode07() != null ? " " + cutBundleEntry.getSubcode07().trim() : "")
                                    + (cutBundleEntry.getSubcode08() != null ? " " + cutBundleEntry.getSubcode08().trim() : "")
                                    + (cutBundleEntry.getSubcode09() != null ? " " + cutBundleEntry.getSubcode09().trim() : "")
                                    + (cutBundleEntry.getSubcode10() != null ? " " + cutBundleEntry.getSubcode10().trim() : "");
                                cutBundleEntry.setSummerizeddescription(productcode);
                                cutBundleEntry.setElementscode(stocktransactions.get(0).getItemelementcode());
                            }
                        } else {
                            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "Bundle number not available in stock.")).build();
                        }
                    }
                } else {
                    return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "Mismatched bundle no.")).build();
                }
            } else {
                return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "In-valid Bundle number.")).build();
            }
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(cutBundleEntry));
    }

    @PostMapping("/stitch-piece-scan")
    public ResponseEntity<CutBundleDetailsBean> findAllByBundleId(@RequestBody Master master) {
        CutBundleDetailsBean cutBundleDetailsBean = new CutBundleDetailsBean();
        VcutDeviceLineMaster vcutDeviceLineMaster = vcutDeviceLineMasterRepository.findByDeviceId(master.getDesc().toUpperCase());
        if (vcutDeviceLineMaster != null) {
            CutPlanBundleDetails cutPlanBundleDetail = cutBundleEntryRepository.findByCutBundleDetailsId(master.getKey(), vcutDeviceLineMaster.getLine());
            if (cutPlanBundleDetail != null) {
                VcutStylePlanUpload vcutStylePlanUpload = vcutStylePlanUploadRepository.findById(master.getPlanId()).orElse(null);
                if (vcutStylePlanUpload != null) {
                    if (vcutStylePlanUpload.getStyle() != null && cutPlanBundleDetail.getProjectcode() != null && vcutStylePlanUpload.getStyle().trim().equalsIgnoreCase(cutPlanBundleDetail.getSubcode01().trim())) {
                        List<VcutOperationMaster> vcutOperationMasters = vcutOperationMasterRepository.findByStyleAndInspection(vcutStylePlanUpload.getStyle());
                        if (vcutOperationMasters != null && vcutOperationMasters.size() > 1) {
                            int i = 0;
                            boolean exist = false;
                            for (VcutOperationMaster vcutOperationMaster : vcutOperationMasters) {
                                ++i;
                                List<VcutOperationPassDetails> vcutOperationPassDetails = vcutOperationPassDetailsRepository.findAllByProductIdAndOperation(master.getKey(), vcutOperationMaster.getDescription());
                                if (vcutOperationPassDetails != null && vcutOperationPassDetails.size() > 0) {
                                    VcutOperationPassDetails vcutOperationPassDetail = vcutOperationPassDetails.get(0);
                                    if (vcutOperationPassDetail.getRequestType() != null && vcutOperationPassDetail.getRequestType().equalsIgnoreCase("ALT")) {
                                        exist = true;
                                        BeanUtils.copyProperties(cutPlanBundleDetail, cutBundleDetailsBean);
                                        cutBundleDetailsBean.setOperationType("FINAL");
                                        cutBundleDetailsBean.setOperation(vcutOperationMaster.getDescription());
                                        cutBundleDetailsBean.setCutPlanBundleId(cutPlanBundleDetail.getCutPlanBundle().getId());
                                        cutBundleDetailsBean.setProductionCounterCode(vcutStylePlanUpload.getPoNoCounter());
                                        cutBundleDetailsBean.setProductionCode(vcutStylePlanUpload.getPoNo());
                                        cutBundleDetailsBean.setRequestType(vcutOperationPassDetail.getRequestType());
                                        cutBundleDetailsBean.setPlanId(master.getPlanId());
                                    }
                                } else if (i == vcutOperationMasters.size() && exist == false) {
                                    exist = true;
                                    BeanUtils.copyProperties(cutPlanBundleDetail, cutBundleDetailsBean);
                                    cutBundleDetailsBean.setOperationType("FINAL");
                                    cutBundleDetailsBean.setOperation(vcutOperationMaster.getDescription());
                                    cutBundleDetailsBean.setCutPlanBundleId(cutPlanBundleDetail.getCutPlanBundle().getId());
                                    cutBundleDetailsBean.setProductionCounterCode(vcutStylePlanUpload.getPoNoCounter());
                                    cutBundleDetailsBean.setProductionCode(vcutStylePlanUpload.getPoNo());
                                    cutBundleDetailsBean.setPlanId(master.getPlanId());
                                } else if (exist == false) {
                                    exist = true;
                                    BeanUtils.copyProperties(cutPlanBundleDetail, cutBundleDetailsBean);
                                    cutBundleDetailsBean.setOperation(vcutOperationMaster.getDescription());
                                    cutBundleDetailsBean.setCutPlanBundleId(cutPlanBundleDetail.getCutPlanBundle().getId());
                                    cutBundleDetailsBean.setProductionCounterCode(vcutStylePlanUpload.getPoNoCounter());
                                    cutBundleDetailsBean.setProductionCode(vcutStylePlanUpload.getPoNo());
                                    cutBundleDetailsBean.setPlanId(master.getPlanId());
                                }
                            }
                            if (cutBundleDetailsBean.getId() == null) {
                                return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "Piece not attached in current line.")).build();
                            }
                        } else {
                            List<VcutOperationPassDetails> vcutOperationPassDetails = vcutOperationPassDetailsRepository.findAllByProductIdAndOperation(master.getKey(), "FINAL");
                            if (vcutOperationPassDetails != null && vcutOperationPassDetails.size() > 0) {
                                VcutOperationPassDetails vcutOperationPassDetail = vcutOperationPassDetails.get(0);
                                if (vcutOperationPassDetail.getRequestType() != null && vcutOperationPassDetail.getRequestType().equalsIgnoreCase("ALT")) {
                                    BeanUtils.copyProperties(cutPlanBundleDetail, cutBundleDetailsBean);
                                    cutBundleDetailsBean.setOperationType("FINAL");
                                    cutBundleDetailsBean.setOperation("FINAL");
                                    cutBundleDetailsBean.setCutPlanBundleId(cutPlanBundleDetail.getCutPlanBundle().getId());
                                    cutBundleDetailsBean.setProductionCounterCode(vcutStylePlanUpload.getPoNoCounter());
                                    cutBundleDetailsBean.setProductionCode(vcutStylePlanUpload.getPoNo());
                                    cutBundleDetailsBean.setRequestType(vcutOperationPassDetail.getRequestType());
                                    cutBundleDetailsBean.setPlanId(master.getPlanId());
                                } else {
                                    return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "Piece not attached in current line.")).build();
                                }
                            } else {
                                BeanUtils.copyProperties(cutPlanBundleDetail, cutBundleDetailsBean);
                                cutBundleDetailsBean.setOperationType("FINAL");
                                cutBundleDetailsBean.setOperation("FINAL");
                                cutBundleDetailsBean.setCutPlanBundleId(cutPlanBundleDetail.getCutPlanBundle().getId());
                                cutBundleDetailsBean.setProductionCounterCode(vcutStylePlanUpload.getPoNoCounter());
                                cutBundleDetailsBean.setProductionCode(vcutStylePlanUpload.getPoNo());
                                cutBundleDetailsBean.setPlanId(master.getPlanId());
                            }
                        }
                    } else {
                        return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "Style not linked with line no.")).build();
                    }
                } else {
                    return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "Plan not exist.")).build();
                }
            } else {
                return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "Piece not attached in current line.")).build();
            }
        } else {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "Device not found.")).build();
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(cutBundleDetailsBean));
    }

    @PostMapping("/stitch-piece-operation-save")
    public ResponseEntity<VcutOperationPassDetails> saveVcutOperationPassDetails(@RequestBody CutBundleDetailsBean cutBundleDetailsBean) {
        log.debug("REST request to save CutBundleEntry : {}");
        VcutOperationPassDetails vcutOperationPassDetails = new VcutOperationPassDetails();
        BeanUtils.copyProperties(cutBundleDetailsBean, vcutOperationPassDetails);
        vcutOperationPassDetails.setCutPlanBundleDetailsId(cutBundleDetailsBean.getId());
        vcutOperationPassDetails.setVcutStylePlanUploadId(cutBundleDetailsBean.getPlanId());
        vcutOperationPassDetails.setCutPlanBundleId(cutBundleDetailsBean.getCutPlanBundleId());
        vcutOperationPassDetails.setId(null);
        vcutOperationPassDetails.setCreatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
        vcutOperationPassDetails.setCreateddate(Instant.now());
        VcutOperationPassDetails result = vcutOperationPassDetailsRepository.save(vcutOperationPassDetails);
        return ResponseUtil.wrapOrNotFound(Optional.of(result));
    }

    /**
     * {@code DELETE  /cut-bundle-entries/:id} : delete the "id" cutBundleEntry.
     *
     * @param id the id of the cutBundleEntry to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cut-bundle-entries/{id}")
    public ResponseEntity<Void> deleteCutBundleEntry(@PathVariable Long id) {
        log.debug("REST request to delete CutBundleEntry : {}", id);
        cutBundleEntryRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
