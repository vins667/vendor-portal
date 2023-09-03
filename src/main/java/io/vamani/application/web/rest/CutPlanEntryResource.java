package io.vamani.application.web.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.config.Constants;
import io.vamani.application.db2.domain.*;
import io.vamani.application.db2.model.BalanceBean;
import io.vamani.application.db2.model.MarkerBean;
import io.vamani.application.db2.repository.*;
import io.vamani.application.domain.*;
import io.vamani.application.model.*;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.*;

/**
 * REST controller for managing {@link io.vamani.application.domain.CutPlanEntry}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CutPlanEntryResource {

    private final Logger log = LoggerFactory.getLogger(CutPlanEntryResource.class);

    private static final String ENTITY_NAME = "cutPlanEntry";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CutPlanEntryRepository cutPlanEntryRepository;

    @Autowired
    private CutPlanEntryDetailsRepository cutPlanEntryDetailsRepository;

    @Autowired
    private MarkerEntryDetailsRepository markerEntryDetailsRepository;

    @Autowired
    private ProductionorderRepository productionorderRepository;

    @Autowired
    private ProductiondemandRepository productiondemandRepository;

    @Autowired
    private ProductiondemandstepRepository productiondemandstepRepository;

    @Autowired
    private ProductionprogressimportRepository productionprogressimportRepository;

    @Autowired
    private AdstorageimportRepository adstorageimportRepository;

    @Autowired
    private ResourcesRepository resourcesRepository;

    @Autowired
    private CutPlanProgressRepository cutPlanProgressRepository;

    @Autowired
    private CutPlanProgressEntryRepository cutPlanProgressEntryRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

    public CutPlanEntryResource(CutPlanEntryRepository cutPlanEntryRepository) {
        this.cutPlanEntryRepository = cutPlanEntryRepository;
    }

    /**
     * {@code POST  /cut-plan-entries} : Create a new cutPlanEntry.
     *
     * @param cutPlanEntry the cutPlanEntry to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cutPlanEntry, or with status {@code 400 (Bad Request)} if the cutPlanEntry has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cut-plan-entries")
    public ResponseEntity<CutPlanEntry> createCutPlanEntry(@Valid @RequestBody CutPlanEntryBean cutPlanEntryBean) throws URISyntaxException {
        log.debug("REST request to save CutPlanEntry : {}", cutPlanEntryBean);
        if (cutPlanEntryBean.getId() != null) {
            throw new BadRequestAlertException("A new cutPlanEntry cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        CutPlanEntry cutPlanEntry = new CutPlanEntry();
        BeanUtils.copyProperties(cutPlanEntryBean, cutPlanEntry);
        Double fabricRequired = Double.parseDouble(decimalFormat.format(cutPlanEntryBean.getNoPlies() * cutPlanEntryBean.getMarkerMasterEntry().getLength()));
        cutPlanEntry.setFabricRequired(fabricRequired);
        cutPlanEntry.setSubcode01(cutPlanEntryBean.getItemcode().getOrdersubcode01());
        cutPlanEntry.setSubcode02(cutPlanEntryBean.getItemcode().getOrdersubcode02());
        cutPlanEntry.setSubcode03(cutPlanEntryBean.getItemcode().getOrdersubcode03());
        cutPlanEntry.setSubcode04(cutPlanEntryBean.getItemcode().getOrdersubcode04());
        cutPlanEntry.setSubcode05(cutPlanEntryBean.getItemcode().getOrdersubcode05());
        cutPlanEntry.setSubcode06(cutPlanEntryBean.getItemcode().getOrdersubcode06());
        cutPlanEntry.setSubcode07(cutPlanEntryBean.getItemcode().getOrdersubcode07());
        cutPlanEntry.setSubcode08(cutPlanEntryBean.getItemcode().getOrdersubcode08());
        cutPlanEntry.setSubcode09(cutPlanEntryBean.getItemcode().getOrdersubcode09());
        cutPlanEntry.setSubcode10(cutPlanEntryBean.getItemcode().getOrdersubcode10());
        cutPlanEntry.setSummerizedDescription(cutPlanEntryBean.getItemcode().getSubcode01Description());
        cutPlanEntry.setMarkerLength(cutPlanEntryBean.getMarkerBean().getMarkerLength());
        cutPlanEntry.setLotNo(cutPlanEntryBean.getMarkerBean().getLotcode().trim());
        cutPlanEntry.setNoRolls(cutPlanEntryBean.getMarkerBean().getNoRolls());
        cutPlanEntry.setEndBits(cutPlanEntryBean.getMarkerBean().getEndBits());
        MarkerMasterEntry markerMasterEntry = new MarkerMasterEntry();
        BeanUtils.copyProperties(cutPlanEntryBean.getMarkerMasterEntry(), markerMasterEntry);
        cutPlanEntry.setMarkerMasterEntry(markerMasterEntry);
        cutPlanEntry.setCreatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
        cutPlanEntry.setCreateddate(Instant.now());
        CutPlanEntry result = cutPlanEntryRepository.save(cutPlanEntry);
        if (result != null && cutPlanEntryBean.getMarkerBean().getBalances() != null && cutPlanEntryBean.getMarkerBean().getBalances().size() > 0) {
            for (BalanceBean balanceBean : cutPlanEntryBean.getMarkerBean().getBalances()) {
                CutPlanEntryDetails cutPlanEntryDetails = new CutPlanEntryDetails(balanceBean);
                cutPlanEntryDetails.setCutPlanEntry(result);
                cutPlanEntryDetailsRepository.save(cutPlanEntryDetails);
            }
        }
        return ResponseEntity.created(new URI("/api/cut-plan-entries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cut-plan-entries} : Updates an existing cutPlanEntry.
     *
     * @param cutPlanEntry the cutPlanEntry to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cutPlanEntry,
     * or with status {@code 400 (Bad Request)} if the cutPlanEntry is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cutPlanEntry couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cut-plan-entries")
    public ResponseEntity<CutPlanEntry> updateCutPlanEntry(@Valid @RequestBody CutPlanEntryBean cutPlanEntryBean) throws URISyntaxException {
        log.debug("REST request to update CutPlanEntry : {}", cutPlanEntryBean);
        if (cutPlanEntryBean.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        CutPlanEntry cutPlanEntry = new CutPlanEntry();
        BeanUtils.copyProperties(cutPlanEntryBean, cutPlanEntry);
        Double fabricRequired = Double.parseDouble(decimalFormat.format(cutPlanEntryBean.getNoPlies() * cutPlanEntryBean.getMarkerMasterEntry().getLength()));
        cutPlanEntry.setFabricRequired(fabricRequired);
        cutPlanEntry.setMarkerLength(cutPlanEntryBean.getMarkerBean().getMarkerLength());
        cutPlanEntry.setLotNo(cutPlanEntryBean.getMarkerBean().getLotcode().trim());
        cutPlanEntry.setNoRolls(cutPlanEntryBean.getMarkerBean().getNoRolls());
        cutPlanEntry.setEndBits(cutPlanEntryBean.getMarkerBean().getEndBits());
        MarkerMasterEntry markerMasterEntry = new MarkerMasterEntry();
        BeanUtils.copyProperties(cutPlanEntryBean.getMarkerMasterEntry(), markerMasterEntry);
        cutPlanEntry.setMarkerMasterEntry(markerMasterEntry);
        cutPlanEntry.setCreatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
        cutPlanEntry.setCreateddate(Instant.now());
        CutPlanEntry result = cutPlanEntryRepository.save(cutPlanEntry);
        if (result != null && cutPlanEntryBean.getMarkerBean().getBalances() != null && cutPlanEntryBean.getMarkerBean().getBalances().size() > 0) {
            cutPlanEntryDetailsRepository.deleteAllByCutPlanEntryId(result.getId());
            for (BalanceBean balanceBean : cutPlanEntryBean.getMarkerBean().getBalances()) {
                CutPlanEntryDetails cutPlanEntryDetails = new CutPlanEntryDetails(balanceBean);
                cutPlanEntryDetails.setCutPlanEntry(result);
                cutPlanEntryDetailsRepository.save(cutPlanEntryDetails);
            }
        }
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, cutPlanEntry.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cut-plan-entries} : Updates an existing cutPlanEntry.
     *
     * @param cutPlanEntry the cutPlanEntry to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cutPlanEntry,
     * or with status {@code 400 (Bad Request)} if the cutPlanEntry is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cutPlanEntry couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cut-plan-progresses")
    public ResponseEntity<CutPlanEntryBean> updateCutPlanProgresses(@Valid @RequestBody CutPlanEntryBean cutPlanEntryBean) throws URISyntaxException {
        log.debug("REST request to update CutPlanEntry : {}", cutPlanEntryBean);
        if (cutPlanEntryBean.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        CutPlanEntry cutPlanEntry = cutPlanEntryRepository.findById(cutPlanEntryBean.getId()).orElse(null);
        cutPlanEntry.setActualNoPlies(cutPlanEntryBean.getActualNoPlies());
        CutPlanEntry result = cutPlanEntryRepository.save(cutPlanEntry);
        if (result != null && cutPlanEntryBean.getMarkerBean().getBalances() != null && cutPlanEntryBean.getMarkerBean().getBalances().size() > 0) {
            if(cutPlanEntryBean.getCutPlanProgressEntry() != null) {
                CutPlanProgressEntry cutPlanProgressEntry = new CutPlanProgressEntry();
                BeanUtils.copyProperties(cutPlanEntryBean.getCutPlanProgressEntry(), cutPlanProgressEntry);
                cutPlanProgressEntry.setProgressEntryBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                cutPlanProgressEntry.setProgressEntryDate(Instant.now());
                cutPlanProgressEntry.setCutPlanEntry(result);
                cutPlanProgressEntryRepository.save(cutPlanProgressEntry);
            }
            for (BalanceBean balanceBean : cutPlanEntryBean.getMarkerBean().getBalances()) {
                CutPlanEntryDetails cutPlanEntryDetails = cutPlanEntryDetailsRepository.findById(balanceBean.getDetailId()).orElse(null);
                if(cutPlanEntryDetails != null) {
                    cutPlanEntryDetails.setActualNoPlies(balanceBean.getActualNoPlies());
                    cutPlanEntryDetails.setActualRollQty(balanceBean.getActualRollQty());
                    cutPlanEntryDetails.setActualEndBits(balanceBean.getActualEndBits());
                    cutPlanEntryDetails.setCutPlanEntry(result);
                    cutPlanEntryDetailsRepository.save(cutPlanEntryDetails);
                }
            }
        }
        return getCutPlanEntryProgress(cutPlanEntryBean.getId());
    }

    /**
     * {@code GET  /cut-plan-entries/:id} : get the "id" cutPlanEntry.
     *
     * @param id the id of the cutPlanEntry to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cutPlanEntry, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cut-plan-progresses-post/{id}")
    public ResponseEntity<CutPlanEntryBean> getCutPlanEntryPost(@PathVariable Long id) {
        log.debug("REST request to get CutPlanEntry : {}", id);
        CutPlanEntryBean cutPlanEntryBean = new CutPlanEntryBean();
        CutPlanProgressEntry cutPlanProgressEntry = cutPlanProgressEntryRepository.findById(id).orElse(null);
        CutPlanEntry cutPlanEntry = cutPlanProgressEntry.getCutPlanEntry();

        String resourceCode = null;
        List<CutPlanEntryDetails> cutPlanEntryDetails = cutPlanEntryDetailsRepository.findAllByCutPlanEntryId(cutPlanEntry.getId());
        List<BalanceBean> balanceBeans = new ArrayList<>();
        for (CutPlanEntryDetails cutPlanEntryDetail : cutPlanEntryDetails) {
            BalanceBean balanceBean = new BalanceBean(cutPlanEntryDetail);
            if(resourceCode == null) {
                resourceCode = cutPlanEntryDetail.getResourceCode();
            }
            balanceBeans.add(balanceBean);
        }

        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        MarkerMasterEntry markerMasterEntry = cutPlanEntry.getMarkerMasterEntry();
        List<MarkerEntryDetails> markerEntryDetails = markerEntryDetailsRepository.findMarkerDetailById(cutPlanEntry.getMarkerMasterEntry().getId());
        if(markerEntryDetails != null) {
            for (MarkerEntryDetails markerEntryDetail : markerEntryDetails) {
                if (markerEntryDetail.getSizeQty() != null && markerEntryDetail.getSizeQty().intValue() > 0) {
                    List<Object[]> demandDetails = null;
                    if(cutPlanEntry.getProductionCode() != null && cutPlanEntry.getProductionCode().startsWith("CP")) {
                        demandDetails = productiondemandRepository.getDemandDetails(Constants.COMPANY_CODE, cutPlanEntry.getProductionCode(), cutPlanEntry.getStyle().trim(), cutPlanEntry.getColor(), markerEntryDetail.getSizeCode(), cutPlanProgressEntry.getOperationCode());
                    } else {
                        demandDetails = productiondemandRepository.getHCDemandDetails(Constants.COMPANY_CODE, cutPlanEntry.getProductionCode(), cutPlanEntry.getStyle().trim(), cutPlanEntry.getColor(), markerEntryDetail.getSizeCode(), cutPlanProgressEntry.getOperationCode());
                    }

                    if (demandDetails != null && demandDetails.size() > 0) {
                        Object[] object = demandDetails.get(0);
                        Productiondemand productiondemand = productiondemandRepository.findByDemandCode(Constants.COMPANY_CODE, object[2].toString());

                        Long parentAdditionId = adstorageimportRepository.getNextSequence();
                        Long progressId = productionprogressimportRepository.getNextSequence();
                        Productionprogressimport productionprogressimport = new Productionprogressimport();
                        productionprogressimport.setId(new ProductionprogressimportId(Constants.COMPANY_CODE, "", progressId.intValue()));
                        productionprogressimport.setImportstatus(5);
                        productionprogressimport.setNowprogressnumber(null);
                        productionprogressimport.setDeleteprogressprogressnumber(null);
                        productionprogressimport.setExternalprogressnumber(null);
                        productionprogressimport.setProgressloaddate(new java.sql.Date(java.util.Date.from(Instant.now().atZone(ZoneId.of("Asia/Kolkata")).toInstant()).getTime()));
                        productionprogressimport.setProgresstemplatecode("V02");
                        productionprogressimport.setPartialstep((short) 0);
                        productionprogressimport.setOperationtype("1");
                        productionprogressimport.setProductionordercode(cutPlanEntry.getProductionCode());

                        if (object[3] != null && object[3].toString().length() > 0) {
                            productionprogressimport.setGroupstepnumber(Integer.parseInt(object[3].toString()));
                        } else {
                            productionprogressimport.setGroupstepnumber(0);
                        }

                        productionprogressimport.setGroupline(0);

                        if (object[1] != null && object[1].toString().length() > 0) {
                            productionprogressimport.setDemandcountercode(object[1].toString());
                        } else {
                            productionprogressimport.setDemandcountercode(null);
                        }

                        if (object[2] != null && object[2].toString().length() > 0) {
                            productionprogressimport.setDemandcode(object[2].toString());
                        } else {
                            productionprogressimport.setDemandcode(null);
                        }
                        if (object[4] != null && object[4].toString().length() > 0) {
                            productionprogressimport.setElementitemtypeaficode(object[4].toString());
                        } else {
                            productionprogressimport.setElementitemtypeaficode(null);
                        }
                        productionprogressimport.setElementelementsubcodekey(null);
                        productionprogressimport.setElementelementcode(null);
                        productionprogressimport.setStepnumber(0);
                        productionprogressimport.setWorkcentercode(object[10].toString());
                        if (object[5] != null && object[5].toString().length() > 0) {
                            productionprogressimport.setOperationcode(object[5].toString());
                        } else {
                            productionprogressimport.setOperationcode(null);
                        }
                        productionprogressimport.setDatasetcode(null);
                        productionprogressimport.setSuppliertype("2");
                        productionprogressimport.setSuppliercode(null);
                        productionprogressimport.setProgresstype("1");
                        productionprogressimport.setProgressstartqueuedate(null);
                        productionprogressimport.setProgressstartqueuetime(null);
                        productionprogressimport.setProgressstartpreprocessdate(null);
                        productionprogressimport.setProgressstartpreprocesstime(null);
                        productionprogressimport.setProgressstartprocessdate(null);
                        productionprogressimport.setProgressstartprocesstime(null);
                        productionprogressimport.setProgressstartpostprocessdate(null);
                        productionprogressimport.setProgressstartpostprocesstime(null);
                        productionprogressimport.setProgresspartialenddate(new java.sql.Date(Date.from(cutPlanProgressEntry.getEndDate().atZone(ZoneId.of("Asia/Kolkata")).toInstant()).getTime()));
                        productionprogressimport.setProgresspartialendtime(new SimpleDateFormat("HH:mm:ss").format(Date.from(cutPlanProgressEntry.getEndDate().atZone(ZoneId.of("Asia/Kolkata")).toInstant()).getTime()));
                        productionprogressimport.setProgressenddate(null);
                        productionprogressimport.setProgressendtime(null);
                        productionprogressimport.setCalshiftdailyinformation(1);
                        productionprogressimport.setQueueportionclosed((short) 0);
                        productionprogressimport.setPreprocessportionclosed((short) 0);
                        productionprogressimport.setProcessportionclosed((short) 0);
                        productionprogressimport.setPostprocessportionclosed((short) 0);
                        productionprogressimport.setQueuerecordedmachinetime(new BigDecimal("0"));
                        productionprogressimport.setPreprocessrecordedmachinetime(new BigDecimal("0"));
                        productionprogressimport.setProcessrecordedmachinetime(new BigDecimal(cutPlanProgressEntry.getTotalHour()));
                        productionprogressimport.setPostprocessrecordedmachinetime(new BigDecimal("0"));
                        productionprogressimport.setCostelementcompanycode(Constants.COMPANY_CODE);
                        productionprogressimport.setCostelementitemtypecode(null);
                        productionprogressimport.setCostelementsubcode01(null);
                        productionprogressimport.setCostelementuomcode(null);
                        productionprogressimport.setQueuercordedcostelements(new BigDecimal("0"));
                        productionprogressimport.setPreprocessrcordedcostelements(new BigDecimal("0"));
                        productionprogressimport.setProcessrcordedcostelements(new BigDecimal("0"));
                        productionprogressimport.setPostprocessrcordedcostelements(new BigDecimal("0"));
                        productionprogressimport.setAduniqueid(parentAdditionId);
                        Double primaryquantity = markerEntryDetail.getSizeQty().doubleValue() * cutPlanEntry.getActualNoPlies().doubleValue();
                        if (markerMasterEntry.getBodyFabric() != null && markerMasterEntry.getBodyFabric().booleanValue() == true) {
                            productionprogressimport.setPrimaryqty(new BigDecimal(primaryquantity));
                        } else {
                            productionprogressimport.setPrimaryqty(new BigDecimal(0));
                        }

                        if (object[6] != null && object[6].toString().length() > 0) {
                            productionprogressimport.setPrimaryuomcode(object[6].toString());
                        } else {
                            productionprogressimport.setPrimaryuomcode(null);
                        }

                        Double secondaryQuantity = 0.0;
                        if (object[8].toString() != null && object[9].toString() != null) {
                            if (markerMasterEntry.getBodyFabric() != null && markerMasterEntry.getBodyFabric().booleanValue() == true) {
                                Double ratio = Double.parseDouble(object[9].toString()) / Double.parseDouble(object[8].toString());
                                secondaryQuantity = new Double(decimalFormat.format(ratio * (markerEntryDetail.getSizeQty().doubleValue() * cutPlanEntry.getActualNoPlies().doubleValue())));
                                productionprogressimport.setSecondaryqty(new BigDecimal(secondaryQuantity));
                            } else {
                                productionprogressimport.setSecondaryqty(new BigDecimal(0));
                            }
                        }

                        if (object[7] != null && object[7].toString().length() > 0) {
                            productionprogressimport.setSecondaryuomcode(object[7].toString());
                        } else {
                            productionprogressimport.setSecondaryuomcode(null);
                        }
                        productionprogressimport.setPackagingqty(new BigDecimal("0"));
                        productionprogressimport.setPackaginguomcode(null);
                        productionprogressimport.setQualityitemtypecompanycode(Constants.COMPANY_CODE);
                        if (object[4] != null && object[4].toString().length() > 0) {
                            productionprogressimport.setQualityitemtypecode(object[4].toString());
                        } else {
                            productionprogressimport.setQualityitemtypecode(null);
                        }
                        productionprogressimport.setQualitycode(BigDecimal.valueOf(1).toBigInteger());
                        productionprogressimport.setQualityreasoncompanycode(Constants.COMPANY_CODE);
                        productionprogressimport.setQualityreasoncode(null);
                        productionprogressimport.setMachinecode(resourceCode);
                        // productionprogressimport.setOperatorcode(cuttingBitEntryBean.getOperatorcode());
                        productionprogressimport.setDyelotweight(new BigDecimal("0"));
                        productionprogressimport.setDyelotweightumcode(null);
                        productionprogressimport.setCreationdatetime(Timestamp.from(Instant.now().atZone(ZoneId.of("Asia/Kolkata")).toInstant()));
                        productionprogressimport.setCreationuser(SecurityUtils.getCurrentUserLogin().orElse(null));
                        productionprogressimport.setLastupdatedatetime(null);
                        productionprogressimport.setLastupdateuser(null);
                        productionprogressimport.setAbsuniqueid(0L);
                        Productionprogressimport resultimport = productionprogressimportRepository.save(productionprogressimport);
                        if (resultimport != null) {

                            Adstorageimport adstorageimportplan = new Adstorageimport(new AdstorageimportId("ProductionProgress", parentAdditionId, "ProductionProgress", "CutPlanNo", "CutPlanNo"), 0, 0, 0, 0, cutPlanEntry.getId().toString(), 0, (short) 0, null, new BigDecimal(0), 0L, null, null, 0L);
                            adstorageimportRepository.save(adstorageimportplan);

                            Adstorageimport adstorageimportNoOperator = new Adstorageimport(new AdstorageimportId("ProductionProgress", parentAdditionId, "ProductionProgress", "Noofoperators", "Noofoperators"), 0, 0, 0, 1, null, cutPlanProgressEntry.getNoCutters().intValue(), (short) 0, null, new BigDecimal(0), 0L, null, null, 0L);
                            adstorageimportRepository.save(adstorageimportNoOperator);

                            Adstorageimport adstorageimportDestination = new Adstorageimport(new AdstorageimportId("ProductionProgress", parentAdditionId, "ProductionProgress", "Destination", "Destination"), 0, 0, 0, 0, cutPlanEntry.getDestination(), 0, (short) 0, null, new BigDecimal(0), 0L, null, null, 0L);
                            adstorageimportRepository.save(adstorageimportDestination);

                            resultimport.setImportstatus(0);
                            resultimport = productionprogressimportRepository.save(resultimport);

                            CutPlanProgress cutPlanProgress = new CutPlanProgress();

                            cutPlanProgress.setProductionCode(resultimport.getProductionordercode().trim());
                            cutPlanProgress.setGroupstepnumber(resultimport.getGroupstepnumber().longValue());
                            cutPlanProgress.setDemandcountercode(resultimport.getDemandcountercode().trim());
                            cutPlanProgress.setDemandcode(resultimport.getDemandcode().trim());
                            cutPlanProgress.setResourcecode(resultimport.getMachinecode());
                            cutPlanProgress.setWorkcentercode(resultimport.getWorkcentercode().trim());
                            cutPlanProgress.setOperationcode(resultimport.getOperationcode().trim());
                            cutPlanProgress.setPrimaryquantity(primaryquantity);
                            cutPlanProgress.setPrimaryuomcode(resultimport.getPrimaryuomcode());
                            cutPlanProgress.setSecondaryquantity(secondaryQuantity);
                            cutPlanProgress.setSecondaryuomcode(resultimport.getSecondaryuomcode());
                            cutPlanProgress.setItemtypecode(productiondemand.getItemtypeaficode());
                            cutPlanProgress.setSubcode01(productiondemand.getSubcode01());
                            cutPlanProgress.setSubcode02(productiondemand.getSubcode02());
                            cutPlanProgress.setSubcode03(productiondemand.getSubcode03());
                            cutPlanProgress.setSubcode04(productiondemand.getSubcode04());
                            cutPlanProgress.setSubcode05(productiondemand.getSubcode05());
                            cutPlanProgress.setSubcode06(productiondemand.getSubcode06());
                            cutPlanProgress.setSubcode07(productiondemand.getSubcode07());
                            cutPlanProgress.setSubcode08(productiondemand.getSubcode08());
                            cutPlanProgress.setSubcode09(productiondemand.getSubcode09());
                            cutPlanProgress.setSubcode10(productiondemand.getSubcode10());
                            cutPlanProgress.setProductionprogressimportid(resultimport.getId().getProgressnumber().longValue());
                            cutPlanProgress.setCreatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
                            cutPlanProgress.setCreateddate(Instant.now());
                            cutPlanProgress.setCutPlanEntry(cutPlanEntry);
                            cutPlanProgressRepository.save(cutPlanProgress);
                        }
                    }
                }
            }
        }

        cutPlanProgressEntry.setProgressPostedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        cutPlanProgressEntry.setProgressPostedDate(Instant.now());
        cutPlanProgressEntry = cutPlanProgressEntryRepository.save(cutPlanProgressEntry);

        if (cutPlanProgressEntry.getLastProgress() != null && cutPlanProgressEntry.getLastProgress().equalsIgnoreCase("Y")) {
            cutPlanEntry.setProgressPostedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
            cutPlanEntry.setProgressPostedDate(Instant.now());
            cutPlanEntry = cutPlanEntryRepository.save(cutPlanEntry);
        }

        BeanUtils.copyProperties(cutPlanEntry, cutPlanEntryBean);

        Map<String, Long> orderQtyMap = new HashMap<>();
        Productionorder productionorder = productionorderRepository.findById(new ProductionorderId(Constants.COMPANY_CODE, cutPlanEntry.getProductionCode())).orElse(null);
        String[] demandSplitter = productionorder.getDemandlist().split(";");
        List<Productiondemand> productiondemands = productiondemandRepository.findByIds(Arrays.asList(demandSplitter));
        for (Productiondemand productiondemand : productiondemands) {
            if (productiondemand.getSubcode07() != null && productiondemand.getSubcode07().trim().equalsIgnoreCase(cutPlanEntry.getColor().trim()) && orderQtyMap.containsKey(productiondemand.getSubcode08().trim())) {
                Long qty = orderQtyMap.get(productiondemand.getSubcode08().trim());
                qty += productiondemand.getUserprimaryquantity().longValue();
                orderQtyMap.put(productiondemand.getSubcode08().trim(), qty);
            } else if (productiondemand.getSubcode07() != null && productiondemand.getSubcode07().trim().equalsIgnoreCase(cutPlanEntry.getColor().trim())) {
                Long qty = 0L;
                qty += productiondemand.getUserprimaryquantity().longValue();
                orderQtyMap.put(productiondemand.getSubcode08().trim(), qty);
            }
        }

        Map<String, Long> plannedQtyMap = new HashMap<>();
        List<Object[]> sizeQty = cutPlanEntryRepository.findAllSizeByProductionOrder(cutPlanEntry.getProductionCode().trim(), cutPlanEntry.getStyle().trim(), cutPlanEntry.getColor().trim(), cutPlanEntry.getDestination().trim(), cutPlanEntry.getSubcode01(), cutPlanEntry.getSubcode02(), cutPlanEntry.getSubcode03(), cutPlanEntry.getSubcode04(), cutPlanEntry.getSubcode05(), cutPlanEntry.getSubcode06(), cutPlanEntry.getSubcode07(), cutPlanEntry.getSubcode08(), cutPlanEntry.getSubcode09(), cutPlanEntry.getSubcode10(), id);
        if (sizeQty != null && sizeQty.size() > 0) {
            for (Object[] objects : sizeQty) {
                plannedQtyMap.put(objects[0].toString(), new Double(objects[1].toString()).longValue());
            }
        }

        MarkerMasterEntryBean markerMasterEntryBean = new MarkerMasterEntryBean();
        BeanUtils.copyProperties(cutPlanEntry.getMarkerMasterEntry(), markerMasterEntryBean);

        List<MarkerEntryDetailsBean> markerEntryDetailsBeans = new ArrayList<>();
        for (MarkerEntryDetails markerEntryDetail : markerEntryDetails) {
            MarkerEntryDetailsBean markerEntryDetailsBean = new MarkerEntryDetailsBean();
            BeanUtils.copyProperties(markerEntryDetail, markerEntryDetailsBean);
            if (orderQtyMap.containsKey(markerEntryDetailsBean.getSizeCode().trim())) {
                Double orderQty = orderQtyMap.get(markerEntryDetailsBean.getSizeCode().trim()).doubleValue();
                if (cutPlanEntry.getTolerance() != null && cutPlanEntry.getTolerance().doubleValue() > 0) {
                    orderQty = orderQty.doubleValue() + ((orderQty.doubleValue() * cutPlanEntry.getTolerance().doubleValue()) / 100);
                    orderQty = Math.ceil(orderQty);
                }
                markerEntryDetailsBean.setOrderQty(orderQty);
            } else {
                markerEntryDetailsBean.setOrderQty(0.0);
            }

            if (plannedQtyMap.containsKey(markerEntryDetailsBean.getSizeCode().trim())) {
                markerEntryDetailsBean.setPlannedQty(plannedQtyMap.get(markerEntryDetailsBean.getSizeCode().trim()).doubleValue());
            } else {
                markerEntryDetailsBean.setPlannedQty(0.0);
            }
            if (markerEntryDetailsBean.getSizeQty() != null && markerEntryDetailsBean.getSizeQty().doubleValue() > 0) {
                markerEntryDetailsBean.setPliesQty(markerEntryDetailsBean.getSizeQty() * cutPlanEntry.getNoPlies().doubleValue());
            }
            markerEntryDetailsBeans.add(markerEntryDetailsBean);
        }
        markerMasterEntryBean.setMarkerEntryDetails(markerEntryDetailsBeans);
        cutPlanEntryBean.setMarkerMasterEntry(markerMasterEntryBean);

        MarkerBean markerBean = new MarkerBean();
        markerBean.setMarkerId(markerMasterEntryBean.getId());
        markerBean.setMarkercode(markerMasterEntryBean.getMarkerCode());
        markerBean.setMarkerLength(markerMasterEntryBean.getLength());
        markerBean.setLotcode(cutPlanEntry.getLotNo());
        markerBean.setNoRolls(cutPlanEntry.getNoRolls());
        markerBean.setNoPlies(cutPlanEntry.getNoPlies());
        markerBean.setEndBits(cutPlanEntry.getEndBits());

        markerBean.setBalances(balanceBeans);
        cutPlanEntryBean.setMarkerBean(markerBean);

        return getCutPlanEntryProgress(cutPlanEntry.getId());
    }

    /**
     * {@code GET  /cut-plan-entries} : get all the cutPlanEntries.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cutPlanEntries in body.
     */
    @GetMapping("/cut-plan-entries")
    public ResponseEntity<List<CutPlanEntry>> getAllCutPlanEntries(Pageable pageable) {
        log.debug("REST request to get a page of CutPlanEntries");
        Page<CutPlanEntry> page = cutPlanEntryRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cut-plan-entries} : get all the cutPlanEntries.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cutPlanEntries in body.
     */
    @PostMapping("/cut-plan-entries-filter")
    public ResponseEntity<List<CutPlanEntry>> getAllCutPlanEntriesFilter(@RequestBody CutPlanSearch search) {
        log.debug("REST request to get a page of CutPlanEntries");
        String login = SecurityUtils.getCurrentUserLogin().orElse(null);
        String style = "%";
        if (search.getStyle() != null) {
            style = "%" + search.getStyle().toUpperCase() + "%";
        }
        String pono = "%";
        if (search.getPono() != null) {
            pono = "%" + search.getPono().toUpperCase() + "%";
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
        Page<CutPlanEntry> page = null;
        if (search.getStatus() != null && search.getStatus().equalsIgnoreCase("P")) {
            page = cutPlanEntryRepository.findAllByPlanAndPonoAndStylePending("%", pono, style, color, login, search.getPage());
        } else if (search.getStatus() != null && search.getStatus().equalsIgnoreCase("R")) {
            page = cutPlanEntryRepository.findAllByPlanAndPonoAndStyle("%", pono, style, color, login, search.getPage());
        } else {
            page = cutPlanEntryRepository.findAllByPonoAndStyle(pono, style, color, login, search.getPage());
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cut-plan-entries} : get all the cutPlanEntries.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cutPlanEntries in body.
     */
    @PostMapping("/cut-plan-entries-issue-filter")
    public ResponseEntity<List<CutPlanEntryBean>> getAllCutPlanIssueEntriesFilter(@RequestBody CutPlanSearch search) {
        log.debug("REST request to get a page of CutPlanEntries");
        String login = SecurityUtils.getCurrentUserLogin().orElse(null);
        String plan = "%";
        if (search.getId() != null) {
            plan = "%" + search.getId().toString() + "%";
        }
        String style = "%";
        if (search.getStyle() != null) {
            style = "%" + search.getStyle().toUpperCase() + "%";
        }
        String pono = "%";
        if (search.getPono() != null) {
            pono = "%" + search.getPono().toUpperCase() + "%";
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
        Page<CutPlanEntry> page = null;
        Page<Object[]> page1 = null;
        HttpHeaders headers = null;
        List<CutPlanEntryBean> cutPlanEntryBeans = new ArrayList<>();
        if (search.getStatus() != null && search.getStatus().equalsIgnoreCase("P") || search.getStatus().equalsIgnoreCase("R")) {
            if (search.getStatus().equalsIgnoreCase("P")) {
                page1 = cutPlanEntryRepository.findAllByPlanAndPonoAndStylePendingIssue(plan, pono, style, color, login, search.getPage());
            } else {
                page1 = cutPlanEntryRepository.findAllByPlanAndPonoAndStyleIssued(plan, pono, style, color, login, search.getPage());
            }
            for (Object[] object : page1.getContent()) {
                CutPlanEntryBean cutPlanEntryBean = new CutPlanEntryBean();
                cutPlanEntryBean.setId((Long) object[0]);
                cutPlanEntryBean.setPlantCode(object[1] != null ? object[1].toString() : "");
                cutPlanEntryBean.setPlantDescription(object[2] != null ? object[2].toString() : "");
                cutPlanEntryBean.setProductionCode(object[3] != null ? object[3].toString() : "");
                cutPlanEntryBean.setStyle(object[4] != null ? object[4].toString() : "");
                cutPlanEntryBean.setColor(object[5] != null ? object[5].toString() : "");
                cutPlanEntryBean.setColorDesc(object[6] != null ? object[6].toString() : "");
                cutPlanEntryBean.setDestination(object[7] != null ? object[7].toString() : "");
                cutPlanEntryBean.setDestinationDesc(object[8] != null ? object[8].toString() : "");
                cutPlanEntryBean.setSummerizedDescription(object[9] != null ? object[9].toString() : "");
                List<CutPlanEntryDetails> cutPlanEntryDetails = cutPlanEntryDetailsRepository.findAllByCutPlanEntryId(cutPlanEntryBean.getId());
                int issuedRoll = 0;
                int notIssuedRoll = 0;
                for (CutPlanEntryDetails planEntryDetails : cutPlanEntryDetails) {
                    if (planEntryDetails.getIssuedBy() != null && planEntryDetails.getIssuedBy().length() > 0) {
                        ++issuedRoll;
                    } else {
                        ++notIssuedRoll;
                    }
                }
                if (issuedRoll > 0 && notIssuedRoll > 0) {
                    cutPlanEntryBean.setStatus("Partial");
                } else if (issuedRoll > 0) {
                    cutPlanEntryBean.setStatus("Issued");
                } else if (notIssuedRoll > 0) {
                    cutPlanEntryBean.setStatus("Pending");
                }
                cutPlanEntryBeans.add(cutPlanEntryBean);
            }
            headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page1);
        } else {
            page = cutPlanEntryRepository.findAllByPlanAndPonoAndStyle(plan, pono, style, color, login, search.getPage());
            for (CutPlanEntry cutPlanEntry : page.getContent()) {
                CutPlanEntryBean cutPlanEntryBean = new CutPlanEntryBean();
                BeanUtils.copyProperties(cutPlanEntry, cutPlanEntryBean);
                List<CutPlanEntryDetails> cutPlanEntryDetails = cutPlanEntryDetailsRepository.findAllByCutPlanEntryId(cutPlanEntry.getId());
                int issuedRoll = 0;
                int notIssuedRoll = 0;
                for (CutPlanEntryDetails planEntryDetails : cutPlanEntryDetails) {
                    if (planEntryDetails.getIssuedBy() != null && planEntryDetails.getIssuedBy().length() > 0) {
                        ++issuedRoll;
                    } else {
                        ++notIssuedRoll;
                    }
                }
                if (issuedRoll > 0 && notIssuedRoll > 0) {
                    cutPlanEntryBean.setStatus("Partial");
                } else if (issuedRoll > 0) {
                    cutPlanEntryBean.setStatus("Issued");
                } else if (notIssuedRoll > 0) {
                    cutPlanEntryBean.setStatus("Pending");
                }
                cutPlanEntryBeans.add(cutPlanEntryBean);
            }
            headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        }
        return ResponseEntity.ok().headers(headers).body(cutPlanEntryBeans);
    }

    /**
     * {@code GET  /cut-plan-entries/:id} : get the "id" cutPlanEntry.
     *
     * @param id the id of the cutPlanEntry to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cutPlanEntry, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cut-plan-entries/{id}")
    public ResponseEntity<CutPlanEntryBean> getCutPlanEntry(@PathVariable Long id) {
        log.debug("REST request to get CutPlanEntry : {}", id);
        CutPlanEntryBean cutPlanEntryBean = new CutPlanEntryBean();
        CutPlanEntry cutPlanEntry = cutPlanEntryRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(cutPlanEntry, cutPlanEntryBean);
        Fullitemkeydecoder fullitemkeydecoder = new Fullitemkeydecoder();
        fullitemkeydecoder.setId(new FullitemkeydecoderId(Constants.COMPANY_CODE, cutPlanEntry.getItemtypecode(), cutPlanEntry.getSubcode01(), cutPlanEntry.getSubcode02(), cutPlanEntry.getSubcode03(), cutPlanEntry.getSubcode04(), cutPlanEntry.getSubcode05(), cutPlanEntry.getSubcode06(), cutPlanEntry.getSubcode07(), cutPlanEntry.getSubcode08(), cutPlanEntry.getSubcode09(), cutPlanEntry.getSubcode10()));
        fullitemkeydecoder.setOrdersubcode01(cutPlanEntry.getSubcode01());
        fullitemkeydecoder.setOrdersubcode02(cutPlanEntry.getSubcode02());
        fullitemkeydecoder.setOrdersubcode03(cutPlanEntry.getSubcode03());
        fullitemkeydecoder.setOrdersubcode04(cutPlanEntry.getSubcode04());
        fullitemkeydecoder.setOrdersubcode05(cutPlanEntry.getSubcode05());
        fullitemkeydecoder.setOrdersubcode06(cutPlanEntry.getSubcode06());
        fullitemkeydecoder.setOrdersubcode07(cutPlanEntry.getSubcode07());
        fullitemkeydecoder.setOrdersubcode08(cutPlanEntry.getSubcode08());
        fullitemkeydecoder.setOrdersubcode09(cutPlanEntry.getSubcode09());
        fullitemkeydecoder.setOrdersubcode10(cutPlanEntry.getSubcode10());
        cutPlanEntryBean.setItemcode(fullitemkeydecoder);
        cutPlanEntryBean.setActualNoPlies(cutPlanEntryBean.getActualNoPlies() != null ? cutPlanEntryBean.getActualNoPlies() : cutPlanEntryBean.getNoPlies());

        Map<String, Long> orderQtyMap = new HashMap<>();
        Productionorder productionorder = productionorderRepository.findById(new ProductionorderId(Constants.COMPANY_CODE, cutPlanEntry.getProductionCode())).orElse(null);
        String[] demandSplitter = productionorder.getDemandlist().split(";");
        List<Productiondemand> productiondemands = productiondemandRepository.findByIds(Arrays.asList(demandSplitter));
        for (Productiondemand productiondemand : productiondemands) {
            if (productiondemand.getSubcode07() != null && productiondemand.getSubcode07().trim().equalsIgnoreCase(cutPlanEntry.getColor().trim()) && orderQtyMap.containsKey(productiondemand.getSubcode08().trim())) {
                Long qty = orderQtyMap.get(productiondemand.getSubcode08().trim());
                qty += productiondemand.getUserprimaryquantity().longValue();
                orderQtyMap.put(productiondemand.getSubcode08().trim(), qty);
            } else if (productiondemand.getSubcode07() != null && productiondemand.getSubcode07().trim().equalsIgnoreCase(cutPlanEntry.getColor().trim())) {
                Long qty = 0L;
                qty += productiondemand.getUserprimaryquantity().longValue();
                orderQtyMap.put(productiondemand.getSubcode08().trim(), qty);
            }
        }

        Map<String, Long> plannedQtyMap = new HashMap<>();
        List<Object[]> sizeQty = cutPlanEntryRepository.findAllSizeByProductionOrder(cutPlanEntry.getProductionCode().trim(), cutPlanEntry.getStyle().trim(), cutPlanEntry.getColor().trim(), cutPlanEntry.getDestination().trim(), cutPlanEntry.getSubcode01(), cutPlanEntry.getSubcode02(), cutPlanEntry.getSubcode03(), cutPlanEntry.getSubcode04(), cutPlanEntry.getSubcode05(), cutPlanEntry.getSubcode06(), cutPlanEntry.getSubcode07(), cutPlanEntry.getSubcode08(), cutPlanEntry.getSubcode09(), cutPlanEntry.getSubcode10(), id);
        if (sizeQty != null && sizeQty.size() > 0) {
            for (Object[] objects : sizeQty) {
                plannedQtyMap.put(objects[0].toString(), new Double(objects[1].toString()).longValue());
            }
        }

        MarkerMasterEntryBean markerMasterEntryBean = new MarkerMasterEntryBean();
        BeanUtils.copyProperties(cutPlanEntry.getMarkerMasterEntry(), markerMasterEntryBean);
        List<MarkerEntryDetails> markerEntryDetails = markerEntryDetailsRepository.findMarkerDetailById(markerMasterEntryBean.getId());

        List<MarkerEntryDetailsBean> markerEntryDetailsBeans = new ArrayList<>();
        for (MarkerEntryDetails markerEntryDetail : markerEntryDetails) {
            MarkerEntryDetailsBean markerEntryDetailsBean = new MarkerEntryDetailsBean();
            BeanUtils.copyProperties(markerEntryDetail, markerEntryDetailsBean);
            if (orderQtyMap.containsKey(markerEntryDetailsBean.getSizeCode().trim())) {
                Double orderQty = orderQtyMap.get(markerEntryDetailsBean.getSizeCode().trim()).doubleValue();
                if (cutPlanEntry.getTolerance() != null && cutPlanEntry.getTolerance().doubleValue() > 0) {
                    orderQty = orderQty.doubleValue() + ((orderQty.doubleValue() * cutPlanEntry.getTolerance().doubleValue()) / 100);
                    orderQty = Math.ceil(orderQty);
                }
                markerEntryDetailsBean.setOrderQty(orderQty);
            } else {
                markerEntryDetailsBean.setOrderQty(0.0);
            }

            if (plannedQtyMap.containsKey(markerEntryDetailsBean.getSizeCode().trim())) {
                markerEntryDetailsBean.setPlannedQty(plannedQtyMap.get(markerEntryDetailsBean.getSizeCode().trim()).doubleValue());
            } else {
                markerEntryDetailsBean.setPlannedQty(0.0);
            }
            if (markerEntryDetailsBean.getSizeQty() != null && markerEntryDetailsBean.getSizeQty().doubleValue() > 0) {
                markerEntryDetailsBean.setPliesQty(markerEntryDetailsBean.getSizeQty() * cutPlanEntry.getNoPlies().doubleValue());
                markerEntryDetailsBean.setActualPliesQty(markerEntryDetailsBean.getSizeQty() * cutPlanEntryBean.getActualNoPlies().doubleValue());
            }
            markerEntryDetailsBeans.add(markerEntryDetailsBean);
        }
        markerMasterEntryBean.setMarkerEntryDetails(markerEntryDetailsBeans);
        cutPlanEntryBean.setMarkerMasterEntry(markerMasterEntryBean);

        MarkerBean markerBean = new MarkerBean();
        markerBean.setMarkerId(markerMasterEntryBean.getId());
        markerBean.setMarkercode(markerMasterEntryBean.getMarkerCode());
        markerBean.setMarkerLength(markerMasterEntryBean.getLength());
        markerBean.setLotcode(cutPlanEntry.getLotNo());
        markerBean.setNoRolls(cutPlanEntry.getNoRolls());
        markerBean.setNoPlies(cutPlanEntry.getNoPlies());
        markerBean.setEndBits(cutPlanEntry.getEndBits());

        List<CutPlanEntryDetails> cutPlanEntryDetails = cutPlanEntryDetailsRepository.findAllByCutPlanEntryId(id);
        List<BalanceBean> balanceBeans = new ArrayList<>();
        String resourceCode = null;
        String resourceDescription = null;
        int noRollIssue = 0;
        for (CutPlanEntryDetails cutPlanEntryDetail : cutPlanEntryDetails) {
            if(resourceCode == null) {
                resourceCode = cutPlanEntryDetail.getResourceCode();
                resourceDescription = cutPlanEntryDetail.getResourceDescription();
            }
            BalanceBean balanceBean = new BalanceBean(cutPlanEntryDetail);
            if(cutPlanEntryDetail.getIssuedBy() == null){
                ++noRollIssue;
            }
            balanceBeans.add(balanceBean);
        }
        cutPlanEntryBean.setStatus(null);
        if(noRollIssue >0) {
            cutPlanEntryBean.setStatus("Pending");
        }
        cutPlanEntryBean.setResourceCode(resourceCode);
        cutPlanEntryBean.setResourceDescription(resourceDescription);
        markerBean.setBalances(balanceBeans);
        cutPlanEntryBean.setMarkerBean(markerBean);

        return ResponseUtil.wrapOrNotFound(Optional.of(cutPlanEntryBean));
    }

    /**
     * {@code GET  /cut-plan-entries/:id} : get the "id" cutPlanEntry.
     *
     * @param id the id of the cutPlanEntry to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cutPlanEntry, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cut-plan-entries-progress/{id}")
    public ResponseEntity<CutPlanEntryBean> getCutPlanEntryProgress(@PathVariable Long id) {
        log.debug("REST request to get CutPlanEntry : {}", id);
        CutPlanEntryBean cutPlanEntryBean = new CutPlanEntryBean();
        CutPlanEntry cutPlanEntry = cutPlanEntryRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(cutPlanEntry, cutPlanEntryBean);
        Fullitemkeydecoder fullitemkeydecoder = new Fullitemkeydecoder();
        fullitemkeydecoder.setId(new FullitemkeydecoderId(Constants.COMPANY_CODE, cutPlanEntry.getItemtypecode(), cutPlanEntry.getSubcode01(), cutPlanEntry.getSubcode02(), cutPlanEntry.getSubcode03(), cutPlanEntry.getSubcode04(), cutPlanEntry.getSubcode05(), cutPlanEntry.getSubcode06(), cutPlanEntry.getSubcode07(), cutPlanEntry.getSubcode08(), cutPlanEntry.getSubcode09(), cutPlanEntry.getSubcode10()));
        fullitemkeydecoder.setOrdersubcode01(cutPlanEntry.getSubcode01());
        fullitemkeydecoder.setOrdersubcode02(cutPlanEntry.getSubcode02());
        fullitemkeydecoder.setOrdersubcode03(cutPlanEntry.getSubcode03());
        fullitemkeydecoder.setOrdersubcode04(cutPlanEntry.getSubcode04());
        fullitemkeydecoder.setOrdersubcode05(cutPlanEntry.getSubcode05());
        fullitemkeydecoder.setOrdersubcode06(cutPlanEntry.getSubcode06());
        fullitemkeydecoder.setOrdersubcode07(cutPlanEntry.getSubcode07());
        fullitemkeydecoder.setOrdersubcode08(cutPlanEntry.getSubcode08());
        fullitemkeydecoder.setOrdersubcode09(cutPlanEntry.getSubcode09());
        fullitemkeydecoder.setOrdersubcode10(cutPlanEntry.getSubcode10());
        cutPlanEntryBean.setItemcode(fullitemkeydecoder);
        cutPlanEntryBean.setActualNoPlies(cutPlanEntryBean.getActualNoPlies() != null ? cutPlanEntryBean.getActualNoPlies() : cutPlanEntryBean.getNoPlies());

        Map<String, Long> orderQtyMap = new HashMap<>();
        Productionorder productionorder = productionorderRepository.findById(new ProductionorderId(Constants.COMPANY_CODE, cutPlanEntry.getProductionCode())).orElse(null);
        String[] demandSplitter = productionorder.getDemandlist().split(";");
        List<Productiondemand> productiondemands = productiondemandRepository.findByIds(Arrays.asList(demandSplitter));
        for (Productiondemand productiondemand : productiondemands) {
            if(productionorder.getId().getCode() != null && productionorder.getId().getCode().startsWith("CP")) {
                if (productiondemand.getSubcode07() != null && productiondemand.getSubcode07().trim().equalsIgnoreCase(cutPlanEntry.getColor().trim()) && orderQtyMap.containsKey(productiondemand.getSubcode08().trim())) {
                    Long qty = orderQtyMap.get(productiondemand.getSubcode08().trim());
                    qty += productiondemand.getUserprimaryquantity().longValue();
                    orderQtyMap.put(productiondemand.getSubcode08().trim(), qty);
                } else if (productiondemand.getSubcode07() != null && productiondemand.getSubcode07().trim().equalsIgnoreCase(cutPlanEntry.getColor().trim())) {
                    Long qty = 0L;
                    qty += productiondemand.getUserprimaryquantity().longValue();
                    orderQtyMap.put(productiondemand.getSubcode08().trim(), qty);
                }
            } else {
                if (productiondemand.getSubcode05() != null && productiondemand.getSubcode05().trim().equalsIgnoreCase(cutPlanEntry.getColor().trim()) && orderQtyMap.containsKey(productiondemand.getSubcode08().trim())) {
                    Long qty = orderQtyMap.get(productiondemand.getSubcode06().trim());
                    qty += productiondemand.getUserprimaryquantity().longValue();
                    orderQtyMap.put(productiondemand.getSubcode06().trim(), qty);
                } else if (productiondemand.getSubcode05() != null && productiondemand.getSubcode05().trim().equalsIgnoreCase(cutPlanEntry.getColor().trim())) {
                    Long qty = 0L;
                    qty += productiondemand.getUserprimaryquantity().longValue();
                    orderQtyMap.put(productiondemand.getSubcode06().trim(), qty);
                }
            }
        }

        Map<String, Long> plannedQtyMap = new HashMap<>();
        List<Object[]> sizeQty = cutPlanEntryRepository.findAllSizeByProductionOrder(cutPlanEntry.getProductionCode().trim(), cutPlanEntry.getStyle().trim(), cutPlanEntry.getColor().trim(), cutPlanEntry.getDestination().trim(), cutPlanEntry.getSubcode01(), cutPlanEntry.getSubcode02(), cutPlanEntry.getSubcode03(), cutPlanEntry.getSubcode04(), cutPlanEntry.getSubcode05(), cutPlanEntry.getSubcode06(), cutPlanEntry.getSubcode07(), cutPlanEntry.getSubcode08(), cutPlanEntry.getSubcode09(), cutPlanEntry.getSubcode10(), id);
        if (sizeQty != null && sizeQty.size() > 0) {
            for (Object[] objects : sizeQty) {
                plannedQtyMap.put(objects[0].toString(), new Double(objects[1].toString()).longValue());
            }
        }

        MarkerMasterEntryBean markerMasterEntryBean = new MarkerMasterEntryBean();
        BeanUtils.copyProperties(cutPlanEntry.getMarkerMasterEntry(), markerMasterEntryBean);
        List<MarkerEntryDetails> markerEntryDetails = markerEntryDetailsRepository.findMarkerDetailById(markerMasterEntryBean.getId());

        List<MarkerEntryDetailsBean> markerEntryDetailsBeans = new ArrayList<>();
        for (MarkerEntryDetails markerEntryDetail : markerEntryDetails) {
            MarkerEntryDetailsBean markerEntryDetailsBean = new MarkerEntryDetailsBean();
            BeanUtils.copyProperties(markerEntryDetail, markerEntryDetailsBean);
            if (orderQtyMap.containsKey(markerEntryDetailsBean.getSizeCode().trim())) {
                Double orderQty = orderQtyMap.get(markerEntryDetailsBean.getSizeCode().trim()).doubleValue();
                if (cutPlanEntry.getTolerance() != null && cutPlanEntry.getTolerance().doubleValue() > 0) {
                    orderQty = orderQty.doubleValue() + ((orderQty.doubleValue() * cutPlanEntry.getTolerance().doubleValue()) / 100);
                    orderQty = Math.ceil(orderQty);
                }
                markerEntryDetailsBean.setOrderQty(orderQty);
            } else {
                markerEntryDetailsBean.setOrderQty(0.0);
            }

            if (plannedQtyMap.containsKey(markerEntryDetailsBean.getSizeCode().trim())) {
                markerEntryDetailsBean.setPlannedQty(plannedQtyMap.get(markerEntryDetailsBean.getSizeCode().trim()).doubleValue());
            } else {
                markerEntryDetailsBean.setPlannedQty(0.0);
            }
            if (markerEntryDetailsBean.getSizeQty() != null && markerEntryDetailsBean.getSizeQty().doubleValue() > 0) {
                markerEntryDetailsBean.setPliesQty(markerEntryDetailsBean.getSizeQty() * cutPlanEntry.getNoPlies().doubleValue());
                markerEntryDetailsBean.setActualPliesQty(markerEntryDetailsBean.getSizeQty() * cutPlanEntryBean.getActualNoPlies().doubleValue());
            }
            markerEntryDetailsBeans.add(markerEntryDetailsBean);
        }
        markerMasterEntryBean.setMarkerEntryDetails(markerEntryDetailsBeans);
        cutPlanEntryBean.setMarkerMasterEntry(markerMasterEntryBean);

        MarkerBean markerBean = new MarkerBean();
        markerBean.setMarkerId(markerMasterEntryBean.getId());
        markerBean.setMarkercode(markerMasterEntryBean.getMarkerCode());
        markerBean.setMarkerLength(markerMasterEntryBean.getLength());
        markerBean.setLotcode(cutPlanEntry.getLotNo());
        markerBean.setNoRolls(cutPlanEntry.getNoRolls());
        markerBean.setNoPlies(cutPlanEntry.getNoPlies());
        markerBean.setEndBits(cutPlanEntry.getEndBits());

        List<CutPlanEntryDetails> cutPlanEntryDetails = cutPlanEntryDetailsRepository.findAllByCutPlanEntryId(id);
        List<BalanceBean> balanceBeans = new ArrayList<>();
        String resourceCode = null;
        String resourceDescription = null;
        int noRollIssue = 0;
        for (CutPlanEntryDetails cutPlanEntryDetail : cutPlanEntryDetails) {
            if(resourceCode == null) {
                resourceCode = cutPlanEntryDetail.getResourceCode();
                resourceDescription = cutPlanEntryDetail.getResourceDescription();
            }
            BalanceBean balanceBean = new BalanceBean(cutPlanEntryDetail);
            if(cutPlanEntryDetail.getIssuedBy() == null){
                ++noRollIssue;
            }
            balanceBeans.add(balanceBean);
        }
        cutPlanEntryBean.setStatus(null);
        if(noRollIssue >0) {
            cutPlanEntryBean.setStatus("Pending");
        }
        cutPlanEntryBean.setResourceCode(resourceCode);
        cutPlanEntryBean.setResourceDescription(resourceDescription);
        markerBean.setBalances(balanceBeans);
        cutPlanEntryBean.setMarkerBean(markerBean);

        List<CutPlanProgressEntry> cutPlanProgressEntries = cutPlanProgressEntryRepository.findAllByCutPlanEntryId(id);
        List<Object[]> operations = productiondemandstepRepository.findAllOperation(Constants.COMPANY_CODE, cutPlanEntry.getProductionCode());
        List<CutPlanProgressEntryBean> cutPlanProgressEntryBeans = new ArrayList<>();
        for (Object[] operation : operations) {
            CutPlanProgressEntryBean cutPlanProgressEntryBean = new CutPlanProgressEntryBean();
            if (cutPlanProgressEntries != null && cutPlanProgressEntries.size() > 0) {
                for (CutPlanProgressEntry cutPlanProgressEntry : cutPlanProgressEntries) {
                    if (cutPlanProgressEntry.getOperationCode() != null && operation[0].toString().trim().equalsIgnoreCase(cutPlanProgressEntry.getOperationCode().trim())) {
                        BeanUtils.copyProperties(cutPlanProgressEntry, cutPlanProgressEntryBean);
                    }
                }
            }
            cutPlanProgressEntryBean.setOperationCode(operation[0].toString());
            cutPlanProgressEntryBean.setOperationDescription(operation[1].toString());
            cutPlanProgressEntryBeans.add(cutPlanProgressEntryBean);
        }
        cutPlanEntryBean.setCutPlanProgressEntryBeans(cutPlanProgressEntryBeans);
        // List<CutPlanProgressEntry> cutPlanProgressEntries =

        return ResponseUtil.wrapOrNotFound(Optional.of(cutPlanEntryBean));
    }

    /**
     * {@code GET  /cut-plan-entries} : get all the cutPlanEntries.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cutPlanEntries in body.
     */
    @PostMapping("/cut-plan-progresses-filter")
    public ResponseEntity<List<CutPlanEntryBean>> getAllCutPlanProgressesFilter(@RequestBody CutPlanSearch search) {
        log.debug("REST request to get a page of CutPlanEntries");
        String login = SecurityUtils.getCurrentUserLogin().orElse(null);
        String plan = "%";
        if (search.getId() != null) {
            plan = "%" + search.getId().toString() + "%";
        }
        String style = "%";
        if (search.getStyle() != null) {
            style = "%" + search.getStyle().toUpperCase() + "%";
        }
        String pono = "%";
        if (search.getPono() != null) {
            pono = "%" + search.getPono().toUpperCase() + "%";
        }
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), search.getSortType() != null && search.getSortType().equalsIgnoreCase("desc") ? Sort.by(search.getSort()).descending() : Sort.by(search.getSort()).ascending()));
        Page<CutPlanEntry> page = null;
        if (search.getStatus() != null && search.getStatus().equalsIgnoreCase("E")) {
            page = cutPlanEntryRepository.findAllByPlanAndPonoAndStyleProgressPending(plan, pono, style, login, search.getPage());
        } else if (search.getStatus() != null && search.getStatus().equalsIgnoreCase("A")) {
            page = cutPlanEntryRepository.findAllByPlanAndPonoAndStyleProgressProgressed(plan, pono, style, login, search.getPage());
        } else if (search.getStatus() != null && search.getStatus().equalsIgnoreCase("P")) {
            page = cutPlanEntryRepository.findAllByPlanAndPonoAndStyleProgressPosted(plan, pono, style, login, search.getPage());
        }
        List<CutPlanEntryBean> cutPlanEntryBeans = new ArrayList<>();
        for (CutPlanEntry cutPlanEntry : page.getContent()) {
            CutPlanEntryBean cutPlanEntryBean = new CutPlanEntryBean();
            BeanUtils.copyProperties(cutPlanEntry, cutPlanEntryBean);
            cutPlanEntryBeans.add(cutPlanEntryBean);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(cutPlanEntryBeans);
    }

    /**
     * {@code GET  /cut-plan-entries/:id} : get the "id" cutPlanEntry.
     *
     * @param id the id of the cutPlanEntry to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cutPlanEntry, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cut-plan-entries-issue/{id}")
    public ResponseEntity<CutPlanEntryBean> getCutPlanEntryIssue(@PathVariable Long id) {
        log.debug("REST request to get CutPlanEntry : {}", id);
        CutPlanEntryBean cutPlanEntryBean = new CutPlanEntryBean();
        CutPlanEntry cutPlanEntry = cutPlanEntryRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(cutPlanEntry, cutPlanEntryBean);

        List<CutPlanEntryDetailsBean> cutPlanEntryDetailsBeans = new ArrayList<CutPlanEntryDetailsBean>();
        List<CutPlanEntryDetails> cutPlanEntryDetails = cutPlanEntryDetailsRepository.findAllByCutPlanEntryIdOrderByRoll(id);
        if (cutPlanEntryDetails != null && cutPlanEntryDetails.size() > 0) {
            for (CutPlanEntryDetails cutPlanEntryDetail : cutPlanEntryDetails) {
                CutPlanEntryDetailsBean cutPlanEntryDetailsBean = new CutPlanEntryDetailsBean();
                BeanUtils.copyProperties(cutPlanEntryDetail, cutPlanEntryDetailsBean);
                cutPlanEntryDetailsBean.setSummerizedDescription(cutPlanEntryDetailsBean.getSummerizedDescription());
                if (cutPlanEntryDetailsBean.getSplitFlag() == null && cutPlanEntryDetailsBean.getSplitNoPlies() != null) {
                    cutPlanEntryDetailsBean.setAllowPlies(false);
                } else {
                    cutPlanEntryDetailsBean.setAllowPlies(true);
                }
                cutPlanEntryDetailsBeans.add(cutPlanEntryDetailsBean);
            }
        }
        cutPlanEntryBean.setCutPlanEntryDetailsBeans(cutPlanEntryDetailsBeans);
        return ResponseUtil.wrapOrNotFound(Optional.of(cutPlanEntryBean));
    }



    /**
     * {@code GET  /cut-plan-entries/:id} : get the "id" cutPlanEntry.
     *
     * @param id the id of the cutPlanEntry to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cutPlanEntry, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cut-plan-entries-issue-pdf/{id}")
    public void getCutPlanEntryIssuePdf(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get CutPlanEntry : {}", id);
        if (id != null && id.intValue() > 0) {
            try {
                List<CutPlanEntryBean> cutPlanEntryBeans = new ArrayList<>();
                CutPlanEntryBean cutPlanEntryBean = new CutPlanEntryBean();
                CutPlanEntry cutPlanEntry = cutPlanEntryRepository.findById(id).orElse(null);
                BeanUtils.copyProperties(cutPlanEntry, cutPlanEntryBean);

                List<CutPlanEntryDetailsBean> cutPlanEntryDetailsBeans = new ArrayList<CutPlanEntryDetailsBean>();
                List<CutPlanEntryDetails> cutPlanEntryDetails = cutPlanEntryDetailsRepository.findAllByCutPlanEntryIdOrderByRoll(id);
                if (cutPlanEntryDetails != null && cutPlanEntryDetails.size() > 0) {
                    for (CutPlanEntryDetails cutPlanEntryDetail : cutPlanEntryDetails) {
                        CutPlanEntryDetailsBean cutPlanEntryDetailsBean = new CutPlanEntryDetailsBean();
                        BeanUtils.copyProperties(cutPlanEntryDetail, cutPlanEntryDetailsBean);
                        cutPlanEntryDetailsBean.setSummerizedDescription(cutPlanEntryDetailsBean.getSummerizedDescription());
                        if (cutPlanEntryDetailsBean.getSplitFlag() == null && cutPlanEntryDetailsBean.getSplitNoPlies() != null) {
                            cutPlanEntryDetailsBean.setAllowPlies(false);
                        } else {
                            cutPlanEntryDetailsBean.setAllowPlies(true);
                        }
                        cutPlanEntryDetailsBeans.add(cutPlanEntryDetailsBean);
                    }
                }
                cutPlanEntryBean.setCutPlanEntryDetailsBeans(cutPlanEntryDetailsBeans);
                cutPlanEntryBeans.add(cutPlanEntryBean);
                String path = applicationProperties.getTemplatePath() + "jasper/";
                JasperDesign jasperDesign = JRXmlLoader.load(path + "/FabricIssue.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                Map<String, Object> parameters = new HashMap<>();
                JRDataSource jrDataSource = new JRBeanCollectionDataSource(cutPlanEntryBeans);
                parameters.put("datasource", jrDataSource);
                parameters.put("SUBREPORT_DIR", path);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
                response.setContentType("application/x-pdf");
                response.setHeader("Content-Disposition", "attachment; filename=FabricIssue.pdf");
                final OutputStream outputStream = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


    /**
     * {@code GET  /cut-plan-entries/:id} : get the "id" cutPlanEntry.
     *
     * @param id the id of the cutPlanEntry to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cutPlanEntry, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cut-plan-entries-release/{id}")
    public ResponseEntity<CutPlanEntryBean> getCutPlanEntryRelease(@PathVariable Long id) {
        log.debug("REST request to get CutPlanEntry : {}", id);
        CutPlanEntryBean cutPlanEntryBean = new CutPlanEntryBean();
        CutPlanEntry cutPlanEntry = cutPlanEntryRepository.findById(id).orElse(null);
        cutPlanEntry.setReleaseBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        cutPlanEntry.setReleaseDate(Instant.now());
        cutPlanEntry = cutPlanEntryRepository.save(cutPlanEntry);
        BeanUtils.copyProperties(cutPlanEntry, cutPlanEntryBean);

        Map<String, Long> orderQtyMap = new HashMap<>();
        Productionorder productionorder = productionorderRepository.findById(new ProductionorderId(Constants.COMPANY_CODE, cutPlanEntry.getProductionCode())).orElse(null);
        String[] demandSplitter = productionorder.getDemandlist().split(";");
        List<Productiondemand> productiondemands = productiondemandRepository.findByIds(Arrays.asList(demandSplitter));
        for (Productiondemand productiondemand : productiondemands) {
            if (productiondemand.getSubcode07() != null && productiondemand.getSubcode07().trim().equalsIgnoreCase(cutPlanEntry.getColor().trim()) && orderQtyMap.containsKey(productiondemand.getSubcode08().trim())) {
                Long qty = orderQtyMap.get(productiondemand.getSubcode08().trim());
                qty += productiondemand.getUserprimaryquantity().longValue();
                orderQtyMap.put(productiondemand.getSubcode08().trim(), qty);
            } else if (productiondemand.getSubcode07() != null && productiondemand.getSubcode07().trim().equalsIgnoreCase(cutPlanEntry.getColor().trim())) {
                Long qty = 0L;
                qty += productiondemand.getUserprimaryquantity().longValue();
                orderQtyMap.put(productiondemand.getSubcode08().trim(), qty);
            }
        }

        Map<String, Long> plannedQtyMap = new HashMap<>();
        List<Object[]> sizeQty = cutPlanEntryRepository.findAllSizeByProductionOrder(cutPlanEntry.getProductionCode().trim(), cutPlanEntry.getStyle().trim(), cutPlanEntry.getColor().trim(), cutPlanEntry.getDestination().trim(), cutPlanEntry.getSubcode01(), cutPlanEntry.getSubcode02(), cutPlanEntry.getSubcode03(), cutPlanEntry.getSubcode04(), cutPlanEntry.getSubcode05(), cutPlanEntry.getSubcode06(), cutPlanEntry.getSubcode07(), cutPlanEntry.getSubcode08(), cutPlanEntry.getSubcode09(), cutPlanEntry.getSubcode10(), id);
        if (sizeQty != null && sizeQty.size() > 0) {
            for (Object[] objects : sizeQty) {
                plannedQtyMap.put(objects[0].toString(), new Double(objects[1].toString()).longValue());
            }
        }

        MarkerMasterEntryBean markerMasterEntryBean = new MarkerMasterEntryBean();
        BeanUtils.copyProperties(cutPlanEntry.getMarkerMasterEntry(), markerMasterEntryBean);
        List<MarkerEntryDetails> markerEntryDetails = markerEntryDetailsRepository.findMarkerDetailById(markerMasterEntryBean.getId());

        List<MarkerEntryDetailsBean> markerEntryDetailsBeans = new ArrayList<>();
        for (MarkerEntryDetails markerEntryDetail : markerEntryDetails) {
            MarkerEntryDetailsBean markerEntryDetailsBean = new MarkerEntryDetailsBean();
            BeanUtils.copyProperties(markerEntryDetail, markerEntryDetailsBean);
            if (orderQtyMap.containsKey(markerEntryDetailsBean.getSizeCode().trim())) {
                Double orderQty = orderQtyMap.get(markerEntryDetailsBean.getSizeCode().trim()).doubleValue();
                if (cutPlanEntry.getTolerance() != null && cutPlanEntry.getTolerance().doubleValue() > 0) {
                    orderQty = orderQty.doubleValue() + ((orderQty.doubleValue() * cutPlanEntry.getTolerance().doubleValue()) / 100);
                    orderQty = Math.ceil(orderQty);
                }
                markerEntryDetailsBean.setOrderQty(orderQty);
            } else {
                markerEntryDetailsBean.setOrderQty(0.0);
            }

            if (plannedQtyMap.containsKey(markerEntryDetailsBean.getSizeCode().trim())) {
                markerEntryDetailsBean.setPlannedQty(plannedQtyMap.get(markerEntryDetailsBean.getSizeCode().trim()).doubleValue());
            } else {
                markerEntryDetailsBean.setPlannedQty(0.0);
            }
            if (markerEntryDetailsBean.getSizeQty() != null && markerEntryDetailsBean.getSizeQty().doubleValue() > 0) {
                markerEntryDetailsBean.setPliesQty(markerEntryDetailsBean.getSizeQty() * cutPlanEntry.getNoPlies().doubleValue());
            }
            markerEntryDetailsBeans.add(markerEntryDetailsBean);
        }
        markerMasterEntryBean.setMarkerEntryDetails(markerEntryDetailsBeans);
        cutPlanEntryBean.setMarkerMasterEntry(markerMasterEntryBean);

        MarkerBean markerBean = new MarkerBean();
        markerBean.setMarkerId(markerMasterEntryBean.getId());
        markerBean.setMarkercode(markerMasterEntryBean.getMarkerCode());
        markerBean.setMarkerLength(markerMasterEntryBean.getLength());
        markerBean.setLotcode(cutPlanEntry.getLotNo());
        markerBean.setNoRolls(cutPlanEntry.getNoRolls());
        markerBean.setNoPlies(cutPlanEntry.getNoPlies());
        markerBean.setEndBits(cutPlanEntry.getEndBits());

        List<CutPlanEntryDetails> cutPlanEntryDetails = cutPlanEntryDetailsRepository.findAllByCutPlanEntryId(id);
        List<BalanceBean> balanceBeans = new ArrayList<>();
        for (CutPlanEntryDetails cutPlanEntryDetail : cutPlanEntryDetails) {
            BalanceBean balanceBean = new BalanceBean(cutPlanEntryDetail);
            balanceBeans.add(balanceBean);
        }
        markerBean.setBalances(balanceBeans);
        cutPlanEntryBean.setMarkerBean(markerBean);

        return ResponseUtil.wrapOrNotFound(Optional.of(cutPlanEntryBean));
    }

    /**
     * {@code DELETE  /cut-plan-entries/:id} : delete the "id" cutPlanEntry.
     *
     * @param id the id of the cutPlanEntry to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cut-plan-entries/{id}")
    public ResponseEntity<Void> deleteCutPlanEntry(@PathVariable Long id) {
        log.debug("REST request to delete CutPlanEntry : {}", id);
        cutPlanEntryRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

	@PostMapping("/cut-fabric-issue-details")
	public List<CutPlanEntryDetailsBean> GetCutplanListById(@Valid @RequestBody Master search) {
		List<CutPlanEntryDetailsBean> cutPlanEntryDetailsBeans = new ArrayList<CutPlanEntryDetailsBean>();
		CutPlanEntry c = cutPlanEntryRepository.findById(Long.parseLong(search.getCode())).orElse(null);
		List<CutPlanEntryDetails> cutPlanEntryDetails = cutPlanEntryDetailsRepository.findAllByCutPlanEntryIdOrderByRoll(Long.parseLong(search.getCode()));
		if (cutPlanEntryDetails != null && cutPlanEntryDetails.size() > 0) {
			for (CutPlanEntryDetails cutPlanEntryDetail : cutPlanEntryDetails) {
                CutPlanEntryDetailsBean cutPlanEntryDetailsBean = new CutPlanEntryDetailsBean();
                BeanUtils.copyProperties(cutPlanEntryDetail, cutPlanEntryDetailsBean);
                cutPlanEntryDetailsBean.setSummerizedDescription(cutPlanEntryDetailsBean.getSummerizedDescription());
                cutPlanEntryDetailsBean.setAllowPlies(true);
                cutPlanEntryDetailsBeans.add(cutPlanEntryDetailsBean);
			}
		}
		return cutPlanEntryDetailsBeans;
	}
}
