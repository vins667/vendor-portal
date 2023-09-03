package io.vamani.application.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.domain.*;
import io.vamani.application.mobile.VcutTvDefectBreakup;
import io.vamani.application.mobile.VcutTvDefectBreakupSummary;
import io.vamani.application.mobile.VcutTvImageSummary;
import io.vamani.application.mobile.VcutTvcCordinate;
import io.vamani.application.model.VcutMainEntryIssueDetails;
import io.vamani.application.model.*;
import io.vamani.application.repository.*;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.util.MD5UrlEncryption;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

/**
 * REST controller for managing {@link VcutMainEntryMaster}.
 */
@RestController
@RequestMapping("/api")
public class VcutMainEntryMasterResource {

    private final Logger log = LoggerFactory.getLogger(VcutMainEntryMasterResource.class);

    private static final String ENTITY_NAME = "vcutMainEntryMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VcutMainEntryMasterRepository vcutMainEntryMasterRepository;

    @Autowired
    private VcutMainEntryMasterEntityRepository vcutMainEntryMasterEntityRepository;

    @Autowired
    private VcutMainEntryIssueDetailsRepository vcutMainEntryIssueDetailsRepository;

    @Autowired
    private VcutStylePlanUploadRepository vcutStylePlanUploadRepository;

    @Autowired
    private VcutSessionMasterRepository vcutSessionMasterRepository;

    @Autowired
    private VcutSessionDetailsRepository vcutSessionDetailsRepository;

    @Autowired
    private VcutFactoryAccessRepository vcutFactoryAccessRepository;

    @Autowired
    private VcutStylePlanSessionBreakupRepository vcutStylePlanSessionBreakupRepository;

    @Autowired
    private VcutOperationIssueMasterRepository vcutOperationIssueMasterRepository;

    @Autowired
    private VcutOperationMasterRepository vcutOperationMasterRepository;

    @Autowired
    private VcutStyleImageRepository vcutStyleImageRepository;

    @Autowired
    private CutPlanBundleDetailsRepository cutPlanBundleDetailsRepository;

    @Autowired
    private VcutOperationPassDetailsRepository vcutOperationPassDetailsRepository;

    public VcutMainEntryMasterResource(VcutMainEntryMasterRepository vcutMainEntryMasterRepository) {
        this.vcutMainEntryMasterRepository = vcutMainEntryMasterRepository;
    }

    /**
     * {@code POST  /vcut-main-entry-masters} : Create a new vcutMainEntryMaster.
     *
     * @param vcutMainEntryMaster the vcutMainEntryMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vcutMainEntryMaster, or with status {@code 400 (Bad Request)} if the vcutMainEntryMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/vcut-main-entry-masters")
    public ResponseEntity<VcutMainEntryMaster> createVcutMainEntryMaster(@Valid @RequestBody VcutMainEntryMasterBean vcutMainEntryMaster) throws URISyntaxException, ParseException {
        log.debug("REST request to save VcutMainEntryMaster : {}", vcutMainEntryMaster);
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Instant entryTime = format.parse(vcutMainEntryMaster.getEntryTime()).toInstant();
        VcutMainEntryMaster entryMaster = vcutMainEntryMasterRepository.findByPlanIdAndTime(vcutMainEntryMaster.getPlanId(), entryTime);
        if (entryMaster != null) {
            if (entryMaster.getRectifiedDate() == null && vcutMainEntryMaster.getRectifiedDate() != null && vcutMainEntryMaster.getRectifiedDate().length() > 0) {
                Instant rectifiedTime = format.parse(vcutMainEntryMaster.getRectifiedDate()).toInstant();
                entryMaster.setRectifiedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                entryMaster.setRectifiedDate(rectifiedTime);
            } else if (vcutMainEntryMaster.getEntryType() != null && vcutMainEntryMaster.getEntryType().equalsIgnoreCase("ALT") && vcutMainEntryMaster.getRectifiedDate() == null) {
                entryMaster.setRectifiedBy(null);
                entryMaster.setRectifiedDate(null);
            }
            if (vcutMainEntryMaster.getOperationId() != null) {
                entryMaster.setVcutOperationMaster(new VcutOperationMaster(vcutMainEntryMaster.getOperationId()));
            }
            VcutMainEntryMaster result = vcutMainEntryMasterRepository.save(entryMaster);
            if (vcutMainEntryMaster.getEntryType() != null && vcutMainEntryMaster.getEntryType().equalsIgnoreCase("ALT") && vcutMainEntryMaster.getStatus() != null && vcutMainEntryMaster.getStatus().equalsIgnoreCase("N")) {
                vcutMainEntryIssueDetailsRepository.deleteAllByVcutId(vcutMainEntryMaster.getId());
                if (vcutMainEntryMaster != null && vcutMainEntryMaster.getIssueDetails() != null && vcutMainEntryMaster.getIssueDetails().size() > 0) {
                    for (VcutMainEntryIssueDetails vcutMainEntryIssueDetails : vcutMainEntryMaster.getIssueDetails()) {
                        Long issueId = vcutMainEntryIssueDetails.getIssueId();
                        if (vcutMainEntryIssueDetails.getCoordinateDetails() != null && vcutMainEntryIssueDetails.getCoordinateDetails().size() > 0) {
                            for (VcutMainEntryCoordinateDetails entryCoordinateDetails : vcutMainEntryIssueDetails.getCoordinateDetails()) {
                                io.vamani.application.domain.VcutMainEntryIssueDetails issueDetails = new io.vamani.application.domain.VcutMainEntryIssueDetails();
                                issueDetails.setCoordinateX(entryCoordinateDetails.getCoordinateX());
                                issueDetails.setCoordinateY(entryCoordinateDetails.getCoordinateY());
                                issueDetails.setCoordinateType(entryCoordinateDetails.getCoordinateType());
                                issueDetails.setVcutMainEntryMasterId(result.getId());
                                issueDetails.setVcutOperationIssueMasterId(issueId);
                                vcutMainEntryIssueDetailsRepository.save(issueDetails);
                            }
                        } else {
                            io.vamani.application.domain.VcutMainEntryIssueDetails issueDetails = new io.vamani.application.domain.VcutMainEntryIssueDetails();
                            issueDetails.setVcutMainEntryMasterId(result.getId());
                            issueDetails.setVcutOperationIssueMasterId(issueId);
                            vcutMainEntryIssueDetailsRepository.save(issueDetails);
                        }
                    }
                }
            }
            List<VcutOperationPassDetails> vcutOperationPassDetails = vcutOperationPassDetailsRepository.findAllByProductIdAndOperation(entryMaster.getCutPlanBundleDetailsId(), entryMaster.getFinalOperation());
            for(VcutOperationPassDetails vcutOperationPassDetail : vcutOperationPassDetails) {
                vcutOperationPassDetail.setRequestType("REC");
                vcutOperationPassDetailsRepository.save(vcutOperationPassDetail);
            }
            return ResponseEntity.created(new URI("/api/vcut-main-entry-masters/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
        } else {
            entryMaster = new VcutMainEntryMaster();
            entryMaster.setEntryTime(entryTime);
            entryMaster.setEntryBy(SecurityUtils.getCurrentUserLogin().orElse(null));
            entryMaster.setEntryType(vcutMainEntryMaster.getEntryType());
            entryMaster.setCutPlanBundleDetailsId(vcutMainEntryMaster.getCutPlanBundleDetailsId());
            entryMaster.setCutPlanBundleId(vcutMainEntryMaster.getCutPlanBundleId());
            entryMaster.setFinalOperation(vcutMainEntryMaster.getFinalOperation());
            entryMaster.setVcutStylePlanUpload(new VcutStylePlanUpload(vcutMainEntryMaster.getPlanId()));
            if (vcutMainEntryMaster.getRectifiedDate() != null && vcutMainEntryMaster.getRectifiedDate().length() > 0) {
                Instant rectifiedTime = format.parse(vcutMainEntryMaster.getRectifiedDate()).toInstant();
                entryMaster.setRectifiedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                entryMaster.setRectifiedDate(rectifiedTime);
            }
            if (vcutMainEntryMaster.getOperationId() != null) {
                entryMaster.setVcutOperationMaster(new VcutOperationMaster(vcutMainEntryMaster.getOperationId()));
            }
            VcutMainEntryMaster result = vcutMainEntryMasterRepository.save(entryMaster);
            if (result != null) {
                if (result.getCutPlanBundleDetailsId() != null) {
                    CutPlanBundleDetails cutPlanBundleDetail = cutPlanBundleDetailsRepository.findById(result.getCutPlanBundleDetailsId()).orElse(null);
                    if (cutPlanBundleDetail != null) {
                        VcutStylePlanUpload vcutStylePlanUpload = vcutStylePlanUploadRepository.findById(vcutMainEntryMaster.getPlanId()).orElse(null);
                        VcutOperationPassDetails vcutOperationPassDetails = new VcutOperationPassDetails();
                        BeanUtils.copyProperties(cutPlanBundleDetail, vcutOperationPassDetails);
                        vcutOperationPassDetails.setCutPlanBundleDetailsId(cutPlanBundleDetail.getId());
                        vcutOperationPassDetails.setCutPlanBundleId(cutPlanBundleDetail.getCutPlanBundle().getId());
                        vcutOperationPassDetails.setOperation(result.getFinalOperation());
                        vcutOperationPassDetails.setProductionCounterCode(vcutStylePlanUpload.getPoNoCounter());
                        vcutOperationPassDetails.setProductionCode(vcutStylePlanUpload.getPoNo());
                        vcutOperationPassDetails.setVcutStylePlanUploadId(vcutStylePlanUpload.getId());
                        vcutOperationPassDetails.setRequestType(result.getEntryType());
                        vcutOperationPassDetails.setId(null);
                        vcutOperationPassDetails.setCreatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
                        vcutOperationPassDetails.setCreateddate(Instant.now());
                        vcutOperationPassDetailsRepository.save(vcutOperationPassDetails);
                    }
                }
                if (vcutMainEntryMaster != null && vcutMainEntryMaster.getIssueDetails() != null && vcutMainEntryMaster.getIssueDetails().size() > 0) {
                    for (VcutMainEntryIssueDetails vcutMainEntryIssueDetails : vcutMainEntryMaster.getIssueDetails()) {
                        Long issueId = vcutMainEntryIssueDetails.getIssueId();
                        if (vcutMainEntryIssueDetails.getCoordinateDetails() != null && vcutMainEntryIssueDetails.getCoordinateDetails().size() > 0) {
                            for (VcutMainEntryCoordinateDetails entryCoordinateDetails : vcutMainEntryIssueDetails.getCoordinateDetails()) {
                                io.vamani.application.domain.VcutMainEntryIssueDetails issueDetails = new io.vamani.application.domain.VcutMainEntryIssueDetails();
                                issueDetails.setCoordinateX(entryCoordinateDetails.getCoordinateX());
                                issueDetails.setCoordinateY(entryCoordinateDetails.getCoordinateY());
                                issueDetails.setCoordinateType(entryCoordinateDetails.getCoordinateType());
                                issueDetails.setVcutMainEntryMasterId(result.getId());
                                issueDetails.setVcutOperationIssueMasterId(issueId);
                                vcutMainEntryIssueDetailsRepository.save(issueDetails);
                            }
                        } else {
                            io.vamani.application.domain.VcutMainEntryIssueDetails issueDetails = new io.vamani.application.domain.VcutMainEntryIssueDetails();
                            issueDetails.setVcutMainEntryMasterId(result.getId());
                            issueDetails.setVcutOperationIssueMasterId(issueId);
                            vcutMainEntryIssueDetailsRepository.save(issueDetails);
                        }
                    }
                }
            }
            return ResponseEntity.created(new URI("/api/vcut-main-entry-masters/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
        }
    }

    /**
     * {@code PUT  /vcut-main-entry-masters} : Updates an existing vcutMainEntryMaster.
     *
     * @param vcutMainEntryMaster the vcutMainEntryMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vcutMainEntryMaster,
     * or with status {@code 400 (Bad Request)} if the vcutMainEntryMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vcutMainEntryMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/vcut-main-entry-masters")
    public ResponseEntity<VcutMainEntryMaster> updateVcutMainEntryMaster(@Valid @RequestBody VcutMainEntryMaster vcutMainEntryMaster) throws URISyntaxException {
        log.debug("REST request to update VcutMainEntryMaster : {}", vcutMainEntryMaster);
        if (vcutMainEntryMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VcutMainEntryMaster result = vcutMainEntryMasterRepository.save(vcutMainEntryMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, vcutMainEntryMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /vcut-main-entry-masters} : get all the vcutMainEntryMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vcutMainEntryMasters in body.
     */
    @GetMapping("/vcut-main-entry-masters")
    public ResponseEntity<List<VcutMainEntryMaster>> getAllVcutMainEntryMasters(Pageable pageable) {
        log.debug("REST request to get a page of VcutMainEntryMasters");
        Page<VcutMainEntryMaster> page = vcutMainEntryMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /vcut-main-entry-masters/:id} : get the "id" vcutMainEntryMaster.
     *
     * @param id the id of the vcutMainEntryMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vcutMainEntryMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/vcut-main-entry-masters/{id}")
    public ResponseEntity<VcutMainEntryMaster> getVcutMainEntryMaster(@PathVariable Long id) {
        log.debug("REST request to get VcutMainEntryMaster : {}", id);
        Optional<VcutMainEntryMaster> vcutMainEntryMaster = vcutMainEntryMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(vcutMainEntryMaster);
    }

    /**
     * {@code DELETE  /vcut-main-entry-masters/:id} : delete the "id" vcutMainEntryMaster.
     *
     * @param id the id of the vcutMainEntryMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/vcut-main-entry-masters/{id}")
    public ResponseEntity<Void> deleteVcutMainEntryMaster(@PathVariable Long id) {
        log.debug("REST request to delete VcutMainEntryMaster : {}", id);
        vcutMainEntryMasterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code DELETE  /vcut-main-entry-masters/:id} : delete the "id" vcutMainEntryMaster.
     *
     * @param id the id of the vcutMainEntryMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/vcut-main-entry-masters-delete")
    public ResponseEntity<Master> deleteVcutMainEntryMaster(@RequestBody Master master) throws ParseException {
        Long planId = Long.parseLong(master.getId());
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Instant date = format.parse(master.getDesc()).toInstant();
        VcutMainEntryMaster vcutMainEntryMaster = vcutMainEntryMasterRepository.findByPlanIdAndTime(planId, date);
        if (vcutMainEntryMaster == null) {
            return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, "Data Already deleted!!!")).body(master);
        }
        master.setName(vcutMainEntryMaster.getEntryType());
        vcutMainEntryIssueDetailsRepository.deleteAllByVcutId(vcutMainEntryMaster.getId());
        vcutMainEntryMasterRepository.deleteById(vcutMainEntryMaster.getId());
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, "Delete Successfully!!!")).body(master);
    }

    /**
     * {@code DELETE  /vcut-main-entry-masters/:id} : delete the "id" vcutMainEntryMaster.
     *
     * @param id the id of the vcutMainEntryMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/vcut-main-entry-masters-rectify")
    public ResponseEntity<Master> rectifyVcutMainEntryMaster(@RequestBody Master master) throws ParseException {
        Long planId = Long.parseLong(master.getId());
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Instant date = format.parse(master.getDesc()).toInstant();
        VcutMainEntryMaster vcutMainEntryMaster = vcutMainEntryMasterRepository.findByPlanIdAndTime(planId, date);
        if (vcutMainEntryMaster == null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, "Data not found")).body(master);
        }
        vcutMainEntryMaster.setRectifiedDate(null);
        vcutMainEntryMaster.setRectifiedBy(null);
        master.setName(vcutMainEntryMaster.getEntryType());
        VcutMainEntryMaster result = vcutMainEntryMasterRepository.save(vcutMainEntryMaster);
        if (result != null) {
            return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, "Save Successfully!!!")).body(master);
        } else {
            return ResponseEntity.badRequest().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, "Data not save")).body(master);
        }
    }


    /**
     * {@code GET  /vcut-factory-summaries} : get all the vcut_factory_summaries.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vcut_factory_summaries in body.
     */
    @GetMapping("/vcut-line-summaries")
    public ResponseEntity<VcutFactorySummary> getAllVcutFactoryLineSummaries() throws ParseException {
        log.debug("REST request to get a page of VcutFactorySummary");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat simpleDateFormatParse = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormatWithTime = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Date currentDate = new Date();
        VcutFactorySummary vcutFactorySummary = new VcutFactorySummary();
        Map<String, Long> plannedHourMap = new LinkedHashMap<>();
        Map<String, Long> plannedMainHourMap = new LinkedHashMap<>();
        Map<String, Long> actualHourMap = new LinkedHashMap<>();
        Map<String, String> activeFlagMap = new LinkedHashMap<>();
        List<VcutFactoryAccess> vcutFactoryAccesses = vcutFactoryAccessRepository.findAllByCardNoAndAppType(SecurityUtils.getCurrentUserLogin().orElse(null), "FACTORY_CUT");
        if (vcutFactoryAccesses != null && vcutFactoryAccesses.size() > 0) {
            vcutFactorySummary.setVcutFactoryAccesses(vcutFactoryAccesses);
            vcutFactorySummary.setActiveFactory(vcutFactoryAccesses.get(0).getId().getFactoryCode());
        }
        if (vcutFactorySummary.getActiveFactory() != null) {
            Date planDate = simpleDateFormatParse.parse(simpleDateFormatParse.format(new Date()));
            List<VcutStylePlanUpload> vcutStylePlanUploads = vcutStylePlanUploadRepository.findByPlanDateAndFactory(LocalDate.parse(simpleDateFormatParse.format(new Date())), vcutFactorySummary.getActiveFactory());
            vcutFactorySummary.setFactory(vcutFactorySummary.getActiveFactory());
            List<VcutFactorySummary> summaryList = new ArrayList<>();
            double smv = 0.0;
            int ctr = 0;
            Long operators = 0L;
            Long helpers = 0L;
            Long dayTarget = 0L;
            Long mainTarget = 0L;
            Long mainTargetPlan = 0L;

            Long totFtt = 0L;
            Long totAlt = 0L;
            Long totRej = 0L;
            Long totRec = 0L;
            Map<String, List<VcutStylePlanUpload>> lineWiseMap = new HashMap<>();
            Map<Long, VcutOperationIssueMaster> issueMap = new HashMap<>();
            Map<Long, VcutOperationMaster> operationMap = new HashMap<>();
            for (VcutStylePlanUpload vcutStylePlanUpload : vcutStylePlanUploads) {
                if (lineWiseMap.containsKey(vcutStylePlanUpload.getLineNo())) {
                    List<VcutStylePlanUpload> stylePlanUploads = lineWiseMap.get(vcutStylePlanUpload.getLineNo());
                    stylePlanUploads.add(vcutStylePlanUpload);
                    lineWiseMap.put(vcutStylePlanUpload.getLineNo(), stylePlanUploads);
                } else {
                    List<VcutStylePlanUpload> stylePlanUploads = new ArrayList<>();
                    stylePlanUploads.add(vcutStylePlanUpload);
                    lineWiseMap.put(vcutStylePlanUpload.getLineNo(), stylePlanUploads);
                }
            }

            for (String key : lineWiseMap.keySet()) {
                Long lineFtt = 0L;
                Long lineAlt = 0L;
                Long lineRej = 0L;
                Long lineRec = 0L;

                double lineSmv = 0.0;
                int lineCtr = 0;
                Long lineOperators = 0L;
                Long lineHelpers = 0L;
                Long lineDayTarget = 0L;
                Long lineMainTarget = 0L;
                Long lineMainTargetPlan = 0L;

                VcutFactorySummary vcutFactorySummaryLine = new VcutFactorySummary();
                vcutFactorySummaryLine.setFactory(vcutFactorySummary.getActiveFactory());
                vcutFactorySummaryLine.setLine(key);
                Long hourCounter = 0L;
                Long PrevSessionId = 0L;
                List<VcutFactorySummary> styleSummaryList = new ArrayList<>();

                Map<Long, Long> defectMap = new HashMap<>(); //vivek
                Map<Long, Map<String, List<Instant>>> defectDateMap = new HashMap<>();
                Map<Long, Long> defectOBMap = new HashMap<>(); //vivek
                Map<Long, Map<String, List<Instant>>> defectOBDateMap = new HashMap<>();
                Map<String, List<VcutStylePlanSessionBreakup>> styleSessionMap = new HashMap<>();
                for (VcutStylePlanUpload vcutStylePlanUpload : lineWiseMap.get(key)) {
                    Long ftt = 0L;
                    Long alt = 0L;
                    Long rej = 0L;
                    Long rec = 0L;

                    List<VcutMainEntryMasterEntity> vcutMainEntryMasters = vcutMainEntryMasterEntityRepository.findByPlanId(vcutStylePlanUpload.getId());
                    List<VcutStylePlanSessionBreakup> stylePlanSessionBreakups = vcutStylePlanSessionBreakupRepository.findAllByVcutPlanId(vcutStylePlanUpload.getId());
                    styleSessionMap.put(vcutStylePlanUpload.getStyle(), stylePlanSessionBreakups);
                    if (vcutMainEntryMasters != null && vcutMainEntryMasters.size() > 0) {
                        for (VcutMainEntryMasterEntity entryMaster : vcutMainEntryMasters) {
                            if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("FTT")) {
                                ++ftt;
                                ++lineFtt;
                                ++totFtt;
                            } else if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("REJ")) {
                                ++rej;
                                ++lineRej;
                                ++totRej;
                            } else if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("ALT") && entryMaster.getRectifiedDate() != null) {
                                ++rec;
                                ++lineRec;
                                ++totRec;
                            } else if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("ALT")) {
                                ++alt;
                                ++lineAlt;
                                ++totAlt;
                            }
                        }
                    }

                    Long lineTarget = Math.round((vcutStylePlanUpload.getQuantity() / vcutStylePlanUpload.getKickOff()) * 100);
                    Long lineTargetMain = Math.round((vcutStylePlanUpload.getQuantity() / vcutStylePlanUpload.getKickOff()) * 100);

                    VcutFactorySummary vcutFactorySummaryStyle = new VcutFactorySummary();
                    vcutFactorySummaryStyle.setId(vcutStylePlanUpload.getId());
                    vcutFactorySummaryStyle.setFactory(vcutFactorySummary.getActiveFactory());
                    vcutFactorySummaryStyle.setLine(vcutStylePlanUpload.getLineNo());
                    vcutFactorySummaryStyle.setBuyer(vcutStylePlanUpload.getBuyerName());
                    vcutFactorySummaryStyle.setStyle(vcutStylePlanUpload.getStyle());
                    vcutFactorySummaryStyle.setPoNo(vcutStylePlanUpload.getPoNo());
                    vcutFactorySummaryStyle.setSmv(vcutStylePlanUpload.getSmv());
                    vcutFactorySummaryStyle.setOperators(vcutStylePlanUpload.getOperators().longValue());
                    vcutFactorySummaryStyle.setHelpers(vcutStylePlanUpload.getHelpers().longValue());
                    vcutFactorySummaryStyle.setQuantity(vcutStylePlanUpload.getQuantity().longValue());
                    vcutFactorySummaryStyle.setPlanEff(vcutStylePlanUpload.getKickOff());
                    Long ach = ftt + rec;
                    vcutFactorySummaryStyle.setAchieved(ach);
                    vcutFactorySummaryStyle.setBalance(vcutStylePlanUpload.getQuantity().longValue() - (ftt + rec));
                    vcutFactorySummaryStyle.setFtt(ftt);
                    vcutFactorySummaryStyle.setRectified(rec);
                    vcutFactorySummaryStyle.setAlter(alt);
                    vcutFactorySummaryStyle.setRejected(rej);

                    //BreakUp
                    if (vcutStylePlanUpload.getVcutSessionMasterId() != null) {
                        if (stylePlanSessionBreakups != null && stylePlanSessionBreakups.size() > 0) {
                            vcutFactorySummaryStyle.setStartTime(simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + stylePlanSessionBreakups.get(0).getId().getStartTime()));
                            long i = 0L;
                            Long totPlanValue = 0L;
                            Long totHourFtt = 0L;
                            Long totHourActual = 0L;
                            Long totHourAlt = 0L;
                            Long totHourRej = 0L;
                            Long totHourRec = 0L;
                            List<VcutFactoryLineBreakup> vcutFactoryLineBreakups = new ArrayList<>();

                            String activeHour = "N";
                            for (VcutStylePlanSessionBreakup vcutStylePlanSessionBreakup : stylePlanSessionBreakups) {
                                VcutFactoryLineBreakup vcutFactoryLineBreakup = new VcutFactoryLineBreakup();
                                if (vcutStylePlanSessionBreakup.getType() != null && (vcutStylePlanSessionBreakup.getType().equalsIgnoreCase("LUNCH") || vcutStylePlanSessionBreakup.getType().equalsIgnoreCase("TEA"))) {
                                    vcutFactoryLineBreakup.setId("B");
                                } else {
                                    ++i;
                                    vcutFactoryLineBreakup.setId(i + "");
                                }
                                if (plannedHourMap.containsKey(vcutFactoryLineBreakup.getId())) {
                                    Long lineTargetTemp = plannedHourMap.get(vcutFactoryLineBreakup.getId());
                                    lineTargetTemp = lineTargetTemp + (Math.round((vcutStylePlanSessionBreakup.getPlanQuantity().longValue() / vcutStylePlanUpload.getKickOff()) * 100));
                                    plannedHourMap.put(vcutFactoryLineBreakup.getId(), lineTargetTemp);

                                    Long lineTargetMainTemp = plannedMainHourMap.get(vcutFactoryLineBreakup.getId());
                                    lineTargetMainTemp = lineTargetMainTemp + vcutStylePlanSessionBreakup.getPlanQuantity().longValue();
                                    plannedMainHourMap.put(vcutFactoryLineBreakup.getId(), lineTargetMainTemp);
                                } else {
                                    Long lineTargetTemp = Math.round((vcutStylePlanSessionBreakup.getPlanQuantity().longValue() / vcutStylePlanUpload.getKickOff()) * 100);
                                    plannedHourMap.put(vcutFactoryLineBreakup.getId(), lineTargetTemp);

                                    Long lineTargetMainTemp = vcutStylePlanSessionBreakup.getPlanQuantity().longValue();
                                    plannedMainHourMap.put(vcutFactoryLineBreakup.getId(), lineTargetMainTemp);
                                }

                                vcutFactoryLineBreakup.setHourBreakup(vcutStylePlanSessionBreakup.getId().getStartTime() + " - " + vcutStylePlanSessionBreakup.getEndTime());
                                vcutFactoryLineBreakup.setDisplayFlag(vcutStylePlanSessionBreakup.getType());
                                Long planValue = vcutStylePlanSessionBreakup.getPlanQuantity().longValue();
                                totPlanValue += planValue;
                                vcutFactoryLineBreakup.setHourPlan(planValue);
                                vcutFactoryLineBreakup.setCumPlan(totPlanValue);

                                Long hourFtt = 0L;
                                Long hourRec = 0L;
                                Long hourAlt = 0L;
                                Long hourRej = 0L;
                                Instant startDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + vcutStylePlanSessionBreakup.getId().getStartTime())).toInstant();
                                Instant endDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + vcutStylePlanSessionBreakup.getEndTime())).toInstant();
                                if (activeHour != null && activeHour.equalsIgnoreCase("Y")) {
                                    vcutFactoryLineBreakup.setActiveHour("Z");
                                    activeFlagMap.put(vcutFactoryLineBreakup.getId(), "Z");
                                } else {
                                    if (currentDate.toInstant().equals(startDate) || (currentDate.toInstant().isAfter(startDate) && currentDate.toInstant().isBefore(endDate))) {
                                        activeHour = "Y";
                                        vcutFactoryLineBreakup.setActiveHour("Y");
                                        activeFlagMap.put(vcutFactoryLineBreakup.getId(), "Y");
                                    } else {
                                        vcutFactoryLineBreakup.setActiveHour("N");
                                        activeFlagMap.put(vcutFactoryLineBreakup.getId(), "N");
                                    }
                                }
                                if (vcutMainEntryMasters != null && vcutMainEntryMasters.size() > 0) {
                                    for (VcutMainEntryMasterEntity entryMaster : vcutMainEntryMasters) {
                                        if (entryMaster.getEntryTime().equals(startDate) || (entryMaster.getEntryTime().isAfter(startDate) && entryMaster.getEntryTime().isBefore(endDate))) {
                                            if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("FTT")) {
                                                ++hourFtt;
                                            } else if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("ALT") && entryMaster.getRectifiedDate() != null) {
                                                ++hourRec;
                                            } else if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("REJ")) {
                                                ++hourRej;
                                            } else if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("ALT")) {
                                                ++hourAlt;
                                            }
                                        }
                                    }
                                }
                                Long hourActual = hourFtt + hourRec;
                                totHourActual += hourActual;
                                totHourFtt += hourFtt;
                                totHourAlt += hourAlt;
                                totHourRec += hourRec;
                                totHourRej += hourRej;
                                vcutFactoryLineBreakup.setHourActual(hourActual);
                                vcutFactoryLineBreakup.setCumActual(totHourActual);
                                vcutFactoryLineBreakup.setVarianceHour(hourActual - planValue);
                                vcutFactoryLineBreakup.setVarianceCum(totHourActual - totPlanValue);

                                if (actualHourMap.containsKey(vcutFactoryLineBreakup.getId())) {
                                    Long lineTargetTemp = actualHourMap.get(vcutFactoryLineBreakup.getId());
                                    lineTargetTemp = lineTargetTemp + hourActual;
                                    actualHourMap.put(vcutFactoryLineBreakup.getId(), lineTargetTemp);
                                } else {
                                    Long lineTargetTemp = hourActual;
                                    actualHourMap.put(vcutFactoryLineBreakup.getId(), lineTargetTemp);
                                }

                                if (hourActual != null && hourActual > 0 && planValue != null && planValue > 0) {
                                    double percentageEff = (planValue.doubleValue() / vcutStylePlanUpload.getKickOff()) * 100;
                                    double percentageEffHour = (hourActual.doubleValue() / percentageEff) * 100;
                                    vcutFactoryLineBreakup.setEfficiencyPlan(percentageEffHour);
                                } else {
                                    vcutFactoryLineBreakup.setEfficiencyPlan(0.0);
                                }
                                if (totHourActual > 0) {
                                    double percentageEff = (totPlanValue.doubleValue() / vcutStylePlanUpload.getKickOff()) * 100;
                                    double percentageEffHour = (totHourActual.doubleValue() / percentageEff) * 100;
                                    vcutFactoryLineBreakup.setEfficiencyActual(percentageEffHour);
                                    if (activeHour != null && activeHour.equalsIgnoreCase("Y") && vcutFactoryLineBreakup.getActiveHour().equalsIgnoreCase("Y")) {
                                        lineTarget = Math.round((totPlanValue.doubleValue() / vcutStylePlanUpload.getKickOff()) * 100);
                                    }
                                } else {
                                    vcutFactoryLineBreakup.setEfficiencyActual(0.0);
                                }

                                if (hourActual.doubleValue() > 0) {
                                    double fttRateHour = (hourFtt.doubleValue() * 100) / hourActual.doubleValue();
                                    vcutFactoryLineBreakup.setFttRatePlan(fttRateHour);
                                } else {
                                    vcutFactoryLineBreakup.setFttRatePlan(0.0);
                                }

                                if (totHourFtt.doubleValue() > 0 && totFtt > 0) {
                                    double fttRateHour = (totHourFtt.doubleValue() * 100) / totHourActual.doubleValue();
                                    vcutFactoryLineBreakup.setFttRateActual(fttRateHour);
                                } else {
                                    vcutFactoryLineBreakup.setFttRateActual(0.0);
                                }

                                if ((hourRej + hourAlt + hourRec) > 0) {
                                    vcutFactoryLineBreakup.setDhuRatePlan(((hourRej.doubleValue() + hourAlt.doubleValue() + hourRec.doubleValue()) * 100) / (hourFtt.doubleValue() + hourRej.doubleValue() + hourAlt.doubleValue() + hourRec.doubleValue()));
                                } else {
                                    vcutFactoryLineBreakup.setDhuRatePlan(0.0);
                                }

                                if ((totHourRej + totHourAlt + totHourRec) > 0) {
                                    vcutFactoryLineBreakup.setDhuRateActual(((totHourRej.doubleValue() + totHourAlt.doubleValue() + totHourRec.doubleValue()) * 100) / (totHourFtt.doubleValue() + totHourRej.doubleValue() + totHourAlt.doubleValue() + totHourRec.doubleValue()));
                                } else {
                                    vcutFactoryLineBreakup.setDhuRateActual(0.0);
                                }
                                vcutFactoryLineBreakups.add(vcutFactoryLineBreakup);
                            }
                            if (vcutMainEntryMasters != null && vcutMainEntryMasters.size() > 0) {
                                Instant startDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + stylePlanSessionBreakups.get(0).getId().getStartTime())).toInstant();
                                Instant endDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + stylePlanSessionBreakups.get(stylePlanSessionBreakups.size() - 1).getEndTime())).toInstant();

                                Long hourFtt = 0L;
                                Long hourRec = 0L;
                                Long hourRej = 0L;
                                Long hourAlt = 0L;
                                for (VcutMainEntryMasterEntity entryMaster : vcutMainEntryMasters) {
                                    if (entryMaster.getEntryTime().isBefore(startDate) || entryMaster.getEntryTime().isAfter(endDate)) {
                                        if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("FTT")) {
                                            ++hourFtt;
                                        } else if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("ALT") && entryMaster.getRectifiedDate() != null) {
                                            ++hourRec;
                                        } else if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("REJ")) {
                                            ++hourRej;
                                        } else if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("ALT")) {
                                            ++hourAlt;
                                        }
                                    }
                                }
                                if (hourFtt > 0 || hourRec > 0) {
                                    VcutFactoryLineBreakup vcutFactoryLineBreakup = new VcutFactoryLineBreakup();
                                    ++i;
                                    vcutFactoryLineBreakup.setId(i + "");
                                    vcutFactoryLineBreakup.setHourBreakup("************");
                                    vcutFactoryLineBreakup.setDisplayFlag("0");
                                    Long hourActual = hourFtt + hourRec;
                                    totHourActual += hourActual;
                                    totHourFtt += hourFtt;
                                    totHourRec += hourRec;
                                    totHourRej += hourRej;
                                    totHourAlt += hourAlt;
                                    vcutFactoryLineBreakup.setHourPlan(0L);
                                    vcutFactoryLineBreakup.setCumPlan(totPlanValue);
                                    vcutFactoryLineBreakup.setHourActual(hourActual);
                                    vcutFactoryLineBreakup.setCumActual(totHourActual);
                                    vcutFactoryLineBreakup.setVarianceHour(hourActual - 0L);
                                    vcutFactoryLineBreakup.setVarianceCum(totHourActual - totPlanValue);

                                    vcutFactoryLineBreakup.setEfficiencyPlan(0.0);

                                    plannedHourMap.put(vcutFactoryLineBreakup.getId(), 0L);
                                    plannedMainHourMap.put(vcutFactoryLineBreakup.getId(), 0L);

                                    if (totHourActual > 0) {
                                        double percentageEff = (totPlanValue.doubleValue() / vcutStylePlanUpload.getKickOff()) * 100;
                                        double percentageEffHour = (totHourActual.doubleValue() / percentageEff) * 100;
                                        vcutFactoryLineBreakup.setEfficiencyActual(percentageEffHour);
                                    } else {
                                        vcutFactoryLineBreakup.setEfficiencyActual(0.0);
                                    }

                                    if (hourActual.doubleValue() > 0) {
                                        double fttRateHour = (hourFtt.doubleValue() * 100) / hourActual.doubleValue();
                                        vcutFactoryLineBreakup.setFttRatePlan(fttRateHour);
                                    } else {
                                        vcutFactoryLineBreakup.setFttRatePlan(0.0);
                                    }
                                    if (totHourFtt.doubleValue() > 0 && totFtt > 0) {
                                        double fttRateHour = (totHourFtt.doubleValue() * 100) / totHourActual.doubleValue();
                                        vcutFactoryLineBreakup.setFttRateActual(fttRateHour);
                                    } else {
                                        vcutFactoryLineBreakup.setFttRateActual(0.0);
                                    }

                                    if (actualHourMap.containsKey(vcutFactoryLineBreakup.getId())) {
                                        Long lineTargetTemp = actualHourMap.get(vcutFactoryLineBreakup.getId());
                                        lineTargetTemp = lineTargetTemp + hourActual;
                                        actualHourMap.put(vcutFactoryLineBreakup.getId(), lineTargetTemp);
                                    } else {
                                        Long lineTargetTemp = hourActual;
                                        actualHourMap.put(vcutFactoryLineBreakup.getId(), lineTargetTemp);
                                    }

                                    if ((hourRej + hourAlt + hourRec) > 0) {
                                        vcutFactoryLineBreakup.setDhuRatePlan(((hourRej.doubleValue() + hourAlt.doubleValue() + hourRec.doubleValue()) * 100) / (hourFtt.doubleValue() + hourRej.doubleValue() + hourAlt.doubleValue() + hourRec.doubleValue()));
                                    } else {
                                        vcutFactoryLineBreakup.setDhuRatePlan(0.0);
                                    }

                                    if ((totHourRej + totHourAlt + totHourRec) > 0) {
                                        vcutFactoryLineBreakup.setDhuRateActual(((totHourRej.doubleValue() + totHourAlt.doubleValue() + totHourRec.doubleValue()) * 100) / (totHourFtt.doubleValue() + totHourRej.doubleValue() + totHourAlt.doubleValue() + totHourRec.doubleValue()));
                                    } else {
                                        vcutFactoryLineBreakup.setDhuRateActual(0.0);
                                    }

                                    vcutFactoryLineBreakups.add(vcutFactoryLineBreakup);
                                }
                            }
                            vcutFactorySummaryStyle.setVcutFactoryLineBreakups(vcutFactoryLineBreakups);
                        }
                    }

                    double achEff = (ach.doubleValue() * 100) / lineTarget.doubleValue();
                    vcutFactorySummaryStyle.setAchEff(achEff);


                    // Line Wise
                    lineSmv += vcutStylePlanUpload.getSmv();
                    ++lineCtr;
                    lineOperators += vcutStylePlanUpload.getOperators();
                    lineHelpers += vcutStylePlanUpload.getHelpers();
                    lineDayTarget += vcutStylePlanUpload.getQuantity();
                    lineMainTarget += lineTarget;
                    lineMainTargetPlan += lineTargetMain;

                    //Factory Wise
                    smv += vcutStylePlanUpload.getSmv();
                    ++ctr;
                    operators += vcutStylePlanUpload.getOperators();
                    helpers += vcutStylePlanUpload.getHelpers();
                    dayTarget += vcutStylePlanUpload.getQuantity();
                    mainTarget += lineTarget;
                    mainTargetPlan += lineTargetMain;

                    //Defects Issue Wise
                    List<Object[]> vcutMainDefectsMasters = vcutMainEntryMasterRepository.findByDefectsPlanId(vcutStylePlanUpload.getId());
                    if (stylePlanSessionBreakups != null && stylePlanSessionBreakups.size() > 0) {
                        for (Object[] objects : vcutMainDefectsMasters) {
                            if (defectMap.containsKey(Long.parseLong(objects[1].toString()))) {
                                Long defectCount = defectMap.get(Long.parseLong(objects[1].toString()));
                                ++defectCount;
                                defectMap.put(Long.parseLong(objects[1].toString()), defectCount);

                                Map<String, List<Instant>> styleMap = defectDateMap.get(Long.parseLong(objects[1].toString()));
                                if (styleMap.containsKey(vcutStylePlanUpload.getStyle())) {
                                    List<Instant> dates = styleMap.get(vcutStylePlanUpload.getStyle());
                                    Instant date = Instant.parse(objects[0].toString());
                                    dates.add(date);
                                    styleMap.put(vcutStylePlanUpload.getStyle(), dates);
                                } else {
                                    List<Instant> dates = new ArrayList<>();
                                    Instant date = Instant.parse(objects[0].toString());
                                    dates.add(date);
                                    styleMap.put(vcutStylePlanUpload.getStyle(), dates);
                                }
                                defectDateMap.put(Long.parseLong(objects[1].toString()), styleMap);
                            } else {
                                defectMap.put(Long.parseLong(objects[1].toString()), 1L);

                                Map<String, List<Instant>> styleMap = new HashMap<>();
                                List<Instant> dates = new ArrayList<>();
                                Instant date = Instant.parse(objects[0].toString());
                                dates.add(date);
                                styleMap.put(vcutStylePlanUpload.getStyle(), dates);
                                defectDateMap.put(Long.parseLong(objects[1].toString()), styleMap);
                            }
                        }
                    }

                    // Defects OB Wise
                    List<Object[]> vcutMainDefectsOBMasters = vcutMainEntryMasterRepository.findByDefectsOperationPlanId(vcutStylePlanUpload.getId());
                    if (stylePlanSessionBreakups != null && stylePlanSessionBreakups.size() > 0) {
                        for (Object[] objects : vcutMainDefectsOBMasters) {
                            if (defectOBMap.containsKey(Long.parseLong(objects[1].toString()))) {
                                Long defectCount = defectOBMap.get(Long.parseLong(objects[1].toString()));
                                ++defectCount;
                                defectOBMap.put(Long.parseLong(objects[1].toString()), defectCount);

                                Map<String, List<Instant>> styleMap = defectOBDateMap.get(Long.parseLong(objects[1].toString()));
                                if (styleMap.containsKey(vcutStylePlanUpload.getStyle())) {
                                    List<Instant> dates = styleMap.get(vcutStylePlanUpload.getStyle());
                                    Instant date = Instant.parse(objects[0].toString());
                                    dates.add(date);
                                    styleMap.put(vcutStylePlanUpload.getStyle(), dates);
                                } else {
                                    List<Instant> dates = new ArrayList<>();
                                    Instant date = Instant.parse(objects[0].toString());
                                    dates.add(date);
                                    styleMap.put(vcutStylePlanUpload.getStyle(), dates);
                                }
                                defectOBDateMap.put(Long.parseLong(objects[1].toString()), styleMap);
                            } else {
                                defectOBMap.put(Long.parseLong(objects[1].toString()), 1L);

                                Map<String, List<Instant>> styleMap = new HashMap<>();
                                List<Instant> dates = new ArrayList<>();
                                Instant date = Instant.parse(objects[0].toString());
                                dates.add(date);
                                styleMap.put(vcutStylePlanUpload.getStyle(), dates);
                                defectOBDateMap.put(Long.parseLong(objects[1].toString()), styleMap);
                            }
                        }
                    }
                    if (vcutFactorySummaryStyle != null && vcutFactorySummaryStyle.getStartTime() != null) {
                        styleSummaryList.add(vcutFactorySummaryStyle);
                    }
                }
                vcutFactorySummaryLine.setSmv(lineSmv / lineCtr);
                vcutFactorySummaryLine.setOperators(lineOperators);
                vcutFactorySummaryLine.setHelpers(lineHelpers);
                vcutFactorySummaryLine.setQuantity(lineDayTarget);
                if (lineMainTargetPlan > 0 && lineDayTarget > 0) {
                    double planEff = (lineDayTarget.doubleValue() * 100) / lineMainTargetPlan.doubleValue();
                    vcutFactorySummaryLine.setPlanEff(planEff);
                } else {
                    vcutFactorySummaryLine.setPlanEff(0.0);
                }
                Long lineAch = lineFtt + lineRec;
                vcutFactorySummaryLine.setAchieved(lineAch);
                if (lineAch > 0 && lineMainTarget > 0) {
                    double lineAchEff = (lineAch.doubleValue() * 100) / lineMainTarget.doubleValue();
                    vcutFactorySummaryLine.setAchEff(lineAchEff);
                } else {
                    vcutFactorySummaryLine.setAchEff(0.0);
                }
                vcutFactorySummaryLine.setBalance(lineDayTarget - (lineFtt + lineRec));
                vcutFactorySummaryLine.setFtt(lineFtt);
                vcutFactorySummaryLine.setRectified(lineRec);
                vcutFactorySummaryLine.setAlter(lineAlt);
                vcutFactorySummaryLine.setRejected(lineRej);
                if (styleSummaryList != null && styleSummaryList.size()>0) {
                    Collections.sort(styleSummaryList, Comparator.comparing(VcutFactorySummary::getStartTime));
                    vcutFactorySummaryLine.setVcutFactorySummaries(styleSummaryList);
                }
                // Defects Issue Wise
                List<VcutTvDefectBreakup> vcutTvDefectBreakups = new ArrayList<>();
                //Line Breakup
                for (Long dKey : defectMap.keySet()) {
                    VcutOperationIssueMaster vcutOperationIssueMaster = null;
                    if (issueMap.containsKey(dKey)) {
                        vcutOperationIssueMaster = issueMap.get(dKey);
                    } else {
                        vcutOperationIssueMaster = vcutOperationIssueMasterRepository.findById(dKey).orElse(null);
                        issueMap.put(dKey, vcutOperationIssueMaster);
                    }
                    VcutTvDefectBreakup vcutTvDefectBreakup = new VcutTvDefectBreakup();
                    vcutTvDefectBreakup.setId(dKey);
                    vcutTvDefectBreakup.setDescription(vcutOperationIssueMaster.getDescription());
                    vcutTvDefectBreakup.setDescriptionLocal(vcutOperationIssueMaster.getDescriptionLocal());
                    vcutTvDefectBreakup.setDefectCount(defectMap.get(dKey));

                    Map<String, List<Instant>> dates = defectDateMap.get(dKey);
                    Long srNo = 0L;
                    Long previousDefect = 0L;
                    List<VcutTvDefectBreakupSummary> summaries = new ArrayList<>();
                    String activeHour = "N";
                    for (String sKey : styleSessionMap.keySet()) {
                        List<VcutStylePlanSessionBreakup> stylePlanSessionBreakups = styleSessionMap.get(sKey);
                        if(stylePlanSessionBreakups != null && stylePlanSessionBreakups.size()>0) {
                            Instant startDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + stylePlanSessionBreakups.get(0).getId().getStartTime())).toInstant();
                            Instant endDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + stylePlanSessionBreakups.get(stylePlanSessionBreakups.size() - 1).getEndTime())).toInstant();
                            Long hour = 0L;
                            for (VcutStylePlanSessionBreakup vcutStylePlanSessionBreakup : stylePlanSessionBreakups) {
                                Instant rowstartDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + vcutStylePlanSessionBreakup.getId().getStartTime())).toInstant();
                                Instant rowendDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + vcutStylePlanSessionBreakup.getEndTime())).toInstant();

                                Long dCounter = 0L;
                                if (dates.containsKey(sKey)) {
                                    for (Instant date : dates.get(sKey)) {
                                        if (date.equals(rowstartDate) || (date.isAfter(rowstartDate) && date.isBefore(rowendDate))) {
                                            ++dCounter;
                                        }
                                    }
                                }
                                VcutTvDefectBreakupSummary vcutTvDefectBreakupSummary = new VcutTvDefectBreakupSummary();
                                if (activeHour != null && activeHour.equalsIgnoreCase("Y")) {
                                    vcutTvDefectBreakupSummary.setActiveHour("Z");
                                } else {
                                    if (currentDate.toInstant().equals(rowstartDate) || (currentDate.toInstant().isAfter(rowstartDate) && currentDate.toInstant().isBefore(rowendDate))) {
                                        activeHour = "Y";
                                        vcutTvDefectBreakupSummary.setActiveHour("Y");
                                    } else {
                                        vcutTvDefectBreakupSummary.setActiveHour("N");
                                    }
                                }
                                vcutTvDefectBreakupSummary.setId(dKey);
                                vcutTvDefectBreakupSummary.setDescription(vcutOperationIssueMaster.getDescription());
                                vcutTvDefectBreakupSummary.setDescriptionLocal(vcutOperationIssueMaster.getDescriptionLocal());
                                vcutTvDefectBreakupSummary.setStyle(sKey);
                                if (vcutStylePlanSessionBreakup.getType() != null && (vcutStylePlanSessionBreakup.getType().equalsIgnoreCase("LUNCH") || vcutStylePlanSessionBreakup.getType().equalsIgnoreCase("TEA"))) {
                                    vcutTvDefectBreakupSummary.setHours("B");
                                } else {
                                    ++hour;
                                    vcutTvDefectBreakupSummary.setHours(hour + "");
                                }
                                vcutTvDefectBreakupSummary.setCountDefect(dCounter);
                                previousDefect += dCounter;
                                vcutTvDefectBreakupSummary.setCountDefectCum(previousDefect);
                                vcutTvDefectBreakupSummary.setStartDate(Date.from(startDate));
                                vcutTvDefectBreakupSummary.setSrNo(++srNo);
                                vcutTvDefectBreakupSummary.setStartTime(vcutStylePlanSessionBreakup.getId().getStartTime());
                                vcutTvDefectBreakupSummary.setEndTime(vcutStylePlanSessionBreakup.getEndTime());
                                summaries.add(vcutTvDefectBreakupSummary);
                            }

                            Long dCounter = 0L;
                            if (dates.containsKey(sKey)) {
                                for (Instant date : dates.get(sKey)) {
                                    if (date.isBefore(startDate) || date.isAfter(endDate)) {
                                        ++dCounter;
                                    }
                                }
                            }

                            VcutTvDefectBreakupSummary vcutTvDefectBreakupSummary = new VcutTvDefectBreakupSummary();
                            vcutTvDefectBreakupSummary.setId(dKey);
                            vcutTvDefectBreakupSummary.setDescription(vcutOperationIssueMaster.getDescription());
                            vcutTvDefectBreakupSummary.setDescriptionLocal(vcutOperationIssueMaster.getDescriptionLocal());
                            vcutTvDefectBreakupSummary.setStyle(sKey);
                            vcutTvDefectBreakupSummary.setSessionSpl("YES");
                            ++hour;
                            vcutTvDefectBreakupSummary.setHours(hour + "");
                            vcutTvDefectBreakupSummary.setCountDefect(dCounter);
                            previousDefect += dCounter;
                            vcutTvDefectBreakupSummary.setCountDefectCum(previousDefect);
                            vcutTvDefectBreakupSummary.setStartDate(Date.from(startDate));
                            vcutTvDefectBreakupSummary.setSrNo(++srNo);
                            summaries.add(vcutTvDefectBreakupSummary);
                        }
                    }
                    Collections.sort(summaries, Comparator.comparing(VcutTvDefectBreakupSummary::getStartDate).thenComparing(VcutTvDefectBreakupSummary::getSrNo));
                    Long prev = 0L;
                    for (VcutTvDefectBreakupSummary summary : summaries) {
                        prev += summary.getCountDefect();
                        summary.setCountDefectCum(prev);
                    }
                    vcutTvDefectBreakup.setSummaries(summaries);
                    vcutTvDefectBreakups.add(vcutTvDefectBreakup);
                }
                while (true) {
                    Integer index = removeIndex(vcutTvDefectBreakups);
                    if (index == null) {
                        break;
                    } else {
                        List<VcutTvDefectBreakup> tvDefectBreakups = new ArrayList<>();
                        for (VcutTvDefectBreakup vcutTvDefectBreakup : vcutTvDefectBreakups) {
                            List<VcutTvDefectBreakupSummary> summaries = vcutTvDefectBreakup.getSummaries();
                            summaries.remove(index.intValue());
                            vcutTvDefectBreakup.setSummaries(summaries);
                            tvDefectBreakups.add(vcutTvDefectBreakup);
                        }
                        vcutTvDefectBreakups.clear();
                        vcutTvDefectBreakups.addAll(tvDefectBreakups);
                    }
                }
                Collections.sort(vcutTvDefectBreakups, Collections.reverseOrder(Comparator.comparing(VcutTvDefectBreakup::getDefectCount)));
                vcutFactorySummaryLine.setVcutTvDefectBreakups(vcutTvDefectBreakups);


                // Defects OB Wise
                List<VcutTvDefectBreakup> vcutTvDefectOBBreakups = new ArrayList<>();
                //Line Breakup
                for (Long dKey : defectOBMap.keySet()) {
                    VcutOperationMaster vcutOperationMaster = null;
                    if (operationMap.containsKey(dKey)) {
                        vcutOperationMaster = operationMap.get(dKey);
                    } else {
                        vcutOperationMaster = vcutOperationMasterRepository.findById(dKey).orElse(null);
                        operationMap.put(dKey, vcutOperationMaster);
                    }
                    VcutTvDefectBreakup vcutTvDefectBreakup = new VcutTvDefectBreakup();
                    vcutTvDefectBreakup.setId(dKey);
                    vcutTvDefectBreakup.setDescription(vcutOperationMaster.getDescription());
                    vcutTvDefectBreakup.setDescriptionLocal(vcutOperationMaster.getDescriptionLocal());
                    vcutTvDefectBreakup.setDefectCount(defectOBMap.get(dKey));

                    Map<String, List<Instant>> dates = defectOBDateMap.get(dKey);
                    Long srNo = 0L;
                    Long previousDefect = 0L;
                    List<VcutTvDefectBreakupSummary> summaries = new ArrayList<>();
                    String activeHour = "N";
                    for (String sKey : styleSessionMap.keySet()) {
                        List<VcutStylePlanSessionBreakup> stylePlanSessionBreakups = styleSessionMap.get(sKey);
                        if(stylePlanSessionBreakups != null && stylePlanSessionBreakups.size()>0) {
                            Instant startDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + stylePlanSessionBreakups.get(0).getId().getStartTime())).toInstant();
                            Instant endDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + stylePlanSessionBreakups.get(stylePlanSessionBreakups.size() - 1).getEndTime())).toInstant();
                            Long hour = 0L;
                            for (VcutStylePlanSessionBreakup vcutStylePlanSessionBreakup : stylePlanSessionBreakups) {
                                Instant rowstartDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + vcutStylePlanSessionBreakup.getId().getStartTime())).toInstant();
                                Instant rowendDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + vcutStylePlanSessionBreakup.getEndTime())).toInstant();

                                Long dCounter = 0L;
                                if (dates.containsKey(sKey)) {
                                    for (Instant date : dates.get(sKey)) {
                                        if (date.equals(rowstartDate) || (date.isAfter(rowstartDate) && date.isBefore(rowendDate))) {
                                            ++dCounter;
                                        }
                                    }
                                }
                                VcutTvDefectBreakupSummary vcutTvDefectBreakupSummary = new VcutTvDefectBreakupSummary();
                                if (activeHour != null && activeHour.equalsIgnoreCase("Y")) {
                                    vcutTvDefectBreakupSummary.setActiveHour("Z");
                                } else {
                                    if (currentDate.toInstant().equals(rowstartDate) || (currentDate.toInstant().isAfter(rowstartDate) && currentDate.toInstant().isBefore(rowendDate))) {
                                        activeHour = "Y";
                                        vcutTvDefectBreakupSummary.setActiveHour("Y");
                                    } else {
                                        vcutTvDefectBreakupSummary.setActiveHour("N");
                                    }
                                }
                                vcutTvDefectBreakupSummary.setId(dKey);
                                vcutTvDefectBreakupSummary.setDescription(vcutOperationMaster.getDescription());
                                vcutTvDefectBreakupSummary.setDescriptionLocal(vcutOperationMaster.getDescriptionLocal());
                                vcutTvDefectBreakupSummary.setStyle(sKey);
                                if (vcutStylePlanSessionBreakup.getType() != null && (vcutStylePlanSessionBreakup.getType().equalsIgnoreCase("LUNCH") || vcutStylePlanSessionBreakup.getType().equalsIgnoreCase("TEA"))) {
                                    vcutTvDefectBreakupSummary.setHours("B");
                                } else {
                                    ++hour;
                                    vcutTvDefectBreakupSummary.setHours(hour + "");
                                }
                                vcutTvDefectBreakupSummary.setCountDefect(dCounter);
                                previousDefect += dCounter;
                                vcutTvDefectBreakupSummary.setCountDefectCum(previousDefect);
                                vcutTvDefectBreakupSummary.setStartDate(Date.from(startDate));
                                vcutTvDefectBreakupSummary.setStartTime(vcutStylePlanSessionBreakup.getId().getStartTime());
                                vcutTvDefectBreakupSummary.setEndTime(vcutStylePlanSessionBreakup.getEndTime());
                                vcutTvDefectBreakupSummary.setSrNo(++srNo);
                                summaries.add(vcutTvDefectBreakupSummary);
                            }

                            Long dCounter = 0L;
                            if (dates.containsKey(sKey)) {
                                for (Instant date : dates.get(sKey)) {
                                    if (date.isBefore(startDate) || date.isAfter(endDate)) {
                                        ++dCounter;
                                    }
                                }
                            }

                            VcutTvDefectBreakupSummary vcutTvDefectBreakupSummary = new VcutTvDefectBreakupSummary();
                            vcutTvDefectBreakupSummary.setId(dKey);
                            vcutTvDefectBreakupSummary.setDescription(vcutOperationMaster.getDescription());
                            vcutTvDefectBreakupSummary.setDescriptionLocal(vcutOperationMaster.getDescriptionLocal());
                            vcutTvDefectBreakupSummary.setStyle(sKey);
                            vcutTvDefectBreakupSummary.setSessionSpl("YES");
                            ++hour;
                            vcutTvDefectBreakupSummary.setHours(hour + "");
                            vcutTvDefectBreakupSummary.setCountDefect(dCounter);
                            previousDefect += dCounter;
                            vcutTvDefectBreakupSummary.setCountDefectCum(previousDefect);
                            vcutTvDefectBreakupSummary.setStartDate(Date.from(startDate));
                            vcutTvDefectBreakupSummary.setSrNo(++srNo);
                            summaries.add(vcutTvDefectBreakupSummary);
                        }
                    }
                    Collections.sort(summaries, Comparator.comparing(VcutTvDefectBreakupSummary::getStartDate).thenComparing(VcutTvDefectBreakupSummary::getSrNo));
                    Long prev = 0L;
                    for (VcutTvDefectBreakupSummary summary : summaries) {
                        prev += summary.getCountDefect();
                        summary.setCountDefectCum(prev);
                    }
                    vcutTvDefectBreakup.setSummaries(summaries);
                    vcutTvDefectOBBreakups.add(vcutTvDefectBreakup);
                }
                while (true) {
                    Integer index = removeIndex(vcutTvDefectOBBreakups);
                    if (index == null) {
                        break;
                    } else {
                        List<VcutTvDefectBreakup> tvDefectBreakups = new ArrayList<>();
                        for (VcutTvDefectBreakup vcutTvDefectBreakup : vcutTvDefectOBBreakups) {
                            List<VcutTvDefectBreakupSummary> summaries = vcutTvDefectBreakup.getSummaries();
                            summaries.remove(index.intValue());
                            vcutTvDefectBreakup.setSummaries(summaries);
                            tvDefectBreakups.add(vcutTvDefectBreakup);
                        }
                        vcutTvDefectOBBreakups.clear();
                        vcutTvDefectOBBreakups.addAll(tvDefectBreakups);
                    }
                }
                Collections.sort(vcutTvDefectOBBreakups, Collections.reverseOrder(Comparator.comparing(VcutTvDefectBreakup::getDefectCount)));
                vcutFactorySummaryLine.setVcutTvDefectOBBreakups(vcutTvDefectOBBreakups);
                summaryList.add(vcutFactorySummaryLine);
            }
            vcutFactorySummary.setSmv(smv / ctr);
            vcutFactorySummary.setOperators(operators);
            vcutFactorySummary.setHelpers(helpers);
            vcutFactorySummary.setQuantity(dayTarget);
            if (dayTarget > 0 && mainTargetPlan > 0) {
                double planEff = (dayTarget.doubleValue() * 100) / mainTargetPlan.doubleValue();
                vcutFactorySummary.setPlanEff(planEff);
            } else {
                vcutFactorySummary.setPlanEff(0.0);
            }
            Long ach = totFtt + totRec;
            vcutFactorySummary.setAchieved(ach);
            if (ach > 0 && mainTarget > 0) {
                double achEff = (ach.doubleValue() * 100) / mainTarget.doubleValue();
                vcutFactorySummary.setAchEff(achEff);
            } else {
                vcutFactorySummary.setAchEff(0.0);
            }
            vcutFactorySummary.setBalance(dayTarget - (totFtt + totRec));
            vcutFactorySummary.setFtt(totFtt);
            vcutFactorySummary.setRectified(totRec);
            vcutFactorySummary.setAlter(totAlt);
            vcutFactorySummary.setRejected(totRej);
            Collections.sort(summaryList, Comparator.comparing(VcutFactorySummary :: getLine));
            vcutFactorySummary.setVcutFactorySummaries(summaryList);

            List<VcutFactoryLineBreakup> hourlyFactoryBreakups = new ArrayList<>();
            if (plannedHourMap != null && plannedHourMap.keySet().size() > 0) {
                for (String key : plannedHourMap.keySet()) {
                    VcutFactoryLineBreakup vcutFactoryLineBreakup = new VcutFactoryLineBreakup();
                    vcutFactoryLineBreakup.setId(key);
                    vcutFactoryLineBreakup.setActiveHour(activeFlagMap.containsKey(key) ?  activeFlagMap.get(key) : "N");
                    Double lineDayTarget = plannedMainHourMap.get(key).doubleValue();
                    Double lineMainTargetPlan = plannedHourMap.get(key).doubleValue();
                    Double lineAch = actualHourMap.get(key).doubleValue();

                    if (lineMainTargetPlan > 0 && lineDayTarget > 0) {
                        double planEff = (lineDayTarget.doubleValue() * 100) / lineMainTargetPlan.doubleValue();
                        vcutFactoryLineBreakup.setEfficiencyPlan(planEff);
                    } else {
                        vcutFactoryLineBreakup.setEfficiencyPlan(0.0);
                    }

                    if (lineAch > 0 && lineMainTargetPlan > 0) {
                        double lineAchEff = (lineAch.doubleValue() * 100) / lineMainTargetPlan.doubleValue();
                        vcutFactoryLineBreakup.setEfficiencyActual(lineAchEff);
                    } else {
                        vcutFactoryLineBreakup.setEfficiencyActual(0.0);
                    }
                    hourlyFactoryBreakups.add(vcutFactoryLineBreakup);
                }
            }
            vcutFactorySummary.setHourlyFactoryBreakups(hourlyFactoryBreakups);
        }
        return ResponseEntity.ok().body(vcutFactorySummary);
    }

    /**
     * {@code GET  /vcut-factory-summaries} : get all the vcut_factory_summaries.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vcut_factory_summaries in body.
     */
    @PostMapping("/vcut-line-summaries")
    public ResponseEntity<VcutFactorySummary> getAllVcutFactoryLineSummaries(@RequestBody Master master) throws ParseException {
        log.debug("REST request to get a page of VcutFactorySummary");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat simpleDateFormatParse = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormatWithTime = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        VcutFactorySummary vcutFactorySummary = new VcutFactorySummary();
        Map<String, Long> plannedHourMap = new LinkedHashMap<>();
        Map<String, Long> plannedMainHourMap = new LinkedHashMap<>();
        Map<String, Long> actualHourMap = new LinkedHashMap<>();
        Map<String, String> activeFlagMap = new LinkedHashMap<>();
        List<VcutFactoryAccess> vcutFactoryAccesses = vcutFactoryAccessRepository.findAllByCardNoAndAppType(SecurityUtils.getCurrentUserLogin().orElse(null), "FACTORY_CUT");
        if (vcutFactoryAccesses != null && vcutFactoryAccesses.size() > 0) {
            vcutFactorySummary.setVcutFactoryAccesses(vcutFactoryAccesses);
            vcutFactorySummary.setActiveFactory(master.getId());
        }
        if (vcutFactorySummary.getActiveFactory() != null) {
            Date planDate = simpleDateFormatParse.parse(master.getDesc());
            Date currentDate = new Date();
            List<VcutStylePlanUpload> vcutStylePlanUploads = vcutStylePlanUploadRepository.findByPlanDateAndFactory(LocalDate.parse(simpleDateFormatParse.format(planDate)), vcutFactorySummary.getActiveFactory());
            vcutFactorySummary.setFactory(vcutFactorySummary.getActiveFactory());
            List<VcutFactorySummary> summaryList = new ArrayList<>();
            double smv = 0.0;
            int ctr = 0;
            Long operators = 0L;
            Long helpers = 0L;
            Long dayTarget = 0L;
            Long mainTarget = 0L;
            Long mainTargetPlan = 0L;

            Long totFtt = 0L;
            Long totAlt = 0L;
            Long totRej = 0L;
            Long totRec = 0L;
            Map<String, List<VcutStylePlanUpload>> lineWiseMap = new HashMap<>();
            Map<Long, VcutOperationIssueMaster> issueMap = new HashMap<>();
            Map<Long, VcutOperationMaster> operationMap = new HashMap<>();
            for (VcutStylePlanUpload vcutStylePlanUpload : vcutStylePlanUploads) {
                if (lineWiseMap.containsKey(vcutStylePlanUpload.getLineNo())) {
                    List<VcutStylePlanUpload> stylePlanUploads = lineWiseMap.get(vcutStylePlanUpload.getLineNo());
                    stylePlanUploads.add(vcutStylePlanUpload);
                    lineWiseMap.put(vcutStylePlanUpload.getLineNo(), stylePlanUploads);
                } else {
                    List<VcutStylePlanUpload> stylePlanUploads = new ArrayList<>();
                    stylePlanUploads.add(vcutStylePlanUpload);
                    lineWiseMap.put(vcutStylePlanUpload.getLineNo(), stylePlanUploads);
                }
            }

            for (String key : lineWiseMap.keySet()) {
                Long lineFtt = 0L;
                Long lineAlt = 0L;
                Long lineRej = 0L;
                Long lineRec = 0L;

                double lineSmv = 0.0;
                int lineCtr = 0;
                Long lineOperators = 0L;
                Long lineHelpers = 0L;
                Long lineDayTarget = 0L;
                Long lineMainTarget = 0L;
                Long lineMainTargetPlan = 0L;

                VcutFactorySummary vcutFactorySummaryLine = new VcutFactorySummary();
                vcutFactorySummaryLine.setFactory(vcutFactorySummary.getActiveFactory());
                vcutFactorySummaryLine.setLine(key);
                Long hourCounter = 0L;
                Long PrevSessionId = 0L;
                List<VcutFactorySummary> styleSummaryList = new ArrayList<>();

                Map<Long, Long> defectMap = new HashMap<>(); //vivek
                Map<Long, Map<String, List<Instant>>> defectDateMap = new HashMap<>();
                Map<Long, Long> defectOBMap = new HashMap<>(); //vivek
                Map<Long, Map<String, List<Instant>>> defectOBDateMap = new HashMap<>();
                Map<String, List<VcutStylePlanSessionBreakup>> styleSessionMap = new HashMap<>();
                for (VcutStylePlanUpload vcutStylePlanUpload : lineWiseMap.get(key)) {
                    Long ftt = 0L;
                    Long alt = 0L;
                    Long rej = 0L;
                    Long rec = 0L;

                    List<VcutMainEntryMasterEntity> vcutMainEntryMasters = vcutMainEntryMasterEntityRepository.findByPlanId(vcutStylePlanUpload.getId());
                    List<VcutStylePlanSessionBreakup> stylePlanSessionBreakups = vcutStylePlanSessionBreakupRepository.findAllByVcutPlanId(vcutStylePlanUpload.getId());
                    styleSessionMap.put(vcutStylePlanUpload.getStyle(), stylePlanSessionBreakups);
                    if (vcutMainEntryMasters != null && vcutMainEntryMasters.size() > 0) {
                        for (VcutMainEntryMasterEntity entryMaster : vcutMainEntryMasters) {
                            if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("FTT")) {
                                ++ftt;
                                ++lineFtt;
                                ++totFtt;
                            } else if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("REJ")) {
                                ++rej;
                                ++lineRej;
                                ++totRej;
                            } else if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("ALT") && entryMaster.getRectifiedDate() != null) {
                                ++rec;
                                ++lineRec;
                                ++totRec;
                            } else if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("ALT")) {
                                ++alt;
                                ++lineAlt;
                                ++totAlt;
                            }
                        }
                    }

                    Long lineTarget = Math.round((vcutStylePlanUpload.getQuantity() / vcutStylePlanUpload.getKickOff()) * 100);
                    Long lineTargetMain = Math.round((vcutStylePlanUpload.getQuantity() / vcutStylePlanUpload.getKickOff()) * 100);

                    VcutFactorySummary vcutFactorySummaryStyle = new VcutFactorySummary();
                    vcutFactorySummaryStyle.setId(vcutStylePlanUpload.getId());
                    vcutFactorySummaryStyle.setFactory(vcutFactorySummary.getActiveFactory());
                    vcutFactorySummaryStyle.setLine(vcutStylePlanUpload.getLineNo());
                    vcutFactorySummaryStyle.setBuyer(vcutStylePlanUpload.getBuyerName());
                    vcutFactorySummaryStyle.setStyle(vcutStylePlanUpload.getStyle());
                    vcutFactorySummaryStyle.setPoNo(vcutStylePlanUpload.getPoNo());
                    vcutFactorySummaryStyle.setSmv(vcutStylePlanUpload.getSmv());
                    vcutFactorySummaryStyle.setOperators(vcutStylePlanUpload.getOperators().longValue());
                    vcutFactorySummaryStyle.setHelpers(vcutStylePlanUpload.getHelpers().longValue());
                    vcutFactorySummaryStyle.setQuantity(vcutStylePlanUpload.getQuantity().longValue());
                    vcutFactorySummaryStyle.setPlanEff(vcutStylePlanUpload.getKickOff());
                    Long ach = ftt + rec;
                    vcutFactorySummaryStyle.setAchieved(ach);
                    vcutFactorySummaryStyle.setBalance(vcutStylePlanUpload.getQuantity().longValue() - (ftt + rec));
                    vcutFactorySummaryStyle.setFtt(ftt);
                    vcutFactorySummaryStyle.setRectified(rec);
                    vcutFactorySummaryStyle.setAlter(alt);
                    vcutFactorySummaryStyle.setRejected(rej);

                    //BreakUp
                    if (vcutStylePlanUpload.getVcutSessionMasterId() != null) {
                        if (stylePlanSessionBreakups != null && stylePlanSessionBreakups.size() > 0) {
                            vcutFactorySummaryStyle.setStartTime(simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + stylePlanSessionBreakups.get(0).getId().getStartTime()));
                            long i = 0L;
                            Long totPlanValue = 0L;
                            Long totHourFtt = 0L;
                            Long totHourActual = 0L;
                            Long totHourAlt = 0L;
                            Long totHourRej = 0L;
                            Long totHourRec = 0L;
                            List<VcutFactoryLineBreakup> vcutFactoryLineBreakups = new ArrayList<>();

                            String activeHour = "N";
                            for (VcutStylePlanSessionBreakup vcutStylePlanSessionBreakup : stylePlanSessionBreakups) {
                                VcutFactoryLineBreakup vcutFactoryLineBreakup = new VcutFactoryLineBreakup();
                                if (vcutStylePlanSessionBreakup.getType() != null && (vcutStylePlanSessionBreakup.getType().equalsIgnoreCase("LUNCH") || vcutStylePlanSessionBreakup.getType().equalsIgnoreCase("TEA"))) {
                                    vcutFactoryLineBreakup.setId("B");
                                } else {
                                    ++i;
                                    vcutFactoryLineBreakup.setId(i + "");
                                }
                                if (plannedHourMap.containsKey(vcutFactoryLineBreakup.getId())) {
                                    Long lineTargetTemp = plannedHourMap.get(vcutFactoryLineBreakup.getId());
                                    lineTargetTemp = lineTargetTemp + (Math.round((vcutStylePlanSessionBreakup.getPlanQuantity().longValue() / vcutStylePlanUpload.getKickOff()) * 100));
                                    plannedHourMap.put(vcutFactoryLineBreakup.getId(), lineTargetTemp);

                                    Long lineTargetMainTemp = plannedMainHourMap.get(vcutFactoryLineBreakup.getId());
                                    lineTargetMainTemp = lineTargetMainTemp + vcutStylePlanSessionBreakup.getPlanQuantity().longValue();
                                    plannedMainHourMap.put(vcutFactoryLineBreakup.getId(), lineTargetMainTemp);
                                } else {
                                    Long lineTargetTemp = Math.round((vcutStylePlanSessionBreakup.getPlanQuantity().longValue() / vcutStylePlanUpload.getKickOff()) * 100);
                                    plannedHourMap.put(vcutFactoryLineBreakup.getId(), lineTargetTemp);

                                    Long lineTargetMainTemp = vcutStylePlanSessionBreakup.getPlanQuantity().longValue();
                                    plannedMainHourMap.put(vcutFactoryLineBreakup.getId(), lineTargetMainTemp);
                                }

                                vcutFactoryLineBreakup.setHourBreakup(vcutStylePlanSessionBreakup.getId().getStartTime() + " - " + vcutStylePlanSessionBreakup.getEndTime());
                                vcutFactoryLineBreakup.setDisplayFlag(vcutStylePlanSessionBreakup.getType());
                                Long planValue = vcutStylePlanSessionBreakup.getPlanQuantity().longValue();
                                totPlanValue += planValue;
                                vcutFactoryLineBreakup.setHourPlan(planValue);
                                vcutFactoryLineBreakup.setCumPlan(totPlanValue);

                                Long hourFtt = 0L;
                                Long hourRec = 0L;
                                Long hourAlt = 0L;
                                Long hourRej = 0L;
                                Instant startDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + vcutStylePlanSessionBreakup.getId().getStartTime())).toInstant();
                                Instant endDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + vcutStylePlanSessionBreakup.getEndTime())).toInstant();
                                if (activeHour != null && activeHour.equalsIgnoreCase("Y")) {
                                    vcutFactoryLineBreakup.setActiveHour("Z");
                                    activeFlagMap.put(vcutFactoryLineBreakup.getId(), "Z");
                                } else {
                                    if (currentDate.toInstant().equals(startDate) || (currentDate.toInstant().isAfter(startDate) && currentDate.toInstant().isBefore(endDate))) {
                                        activeHour = "Y";
                                        vcutFactoryLineBreakup.setActiveHour("Y");
                                        activeFlagMap.put(vcutFactoryLineBreakup.getId(), "Y");
                                    } else {
                                        vcutFactoryLineBreakup.setActiveHour("N");
                                        activeFlagMap.put(vcutFactoryLineBreakup.getId(), "N");
                                    }
                                }
                                if (vcutMainEntryMasters != null && vcutMainEntryMasters.size() > 0) {
                                    for (VcutMainEntryMasterEntity entryMaster : vcutMainEntryMasters) {
                                        if (entryMaster.getEntryTime().equals(startDate) || (entryMaster.getEntryTime().isAfter(startDate) && entryMaster.getEntryTime().isBefore(endDate))) {
                                            if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("FTT")) {
                                                ++hourFtt;
                                            } else if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("ALT") && entryMaster.getRectifiedDate() != null) {
                                                ++hourRec;
                                            } else if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("REJ")) {
                                                ++hourRej;
                                            } else if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("ALT")) {
                                                ++hourAlt;
                                            }
                                        }
                                    }
                                }
                                Long hourActual = hourFtt + hourRec;
                                totHourActual += hourActual;
                                totHourFtt += hourFtt;
                                totHourAlt += hourAlt;
                                totHourRec += hourRec;
                                totHourRej += hourRej;
                                vcutFactoryLineBreakup.setHourActual(hourActual);
                                vcutFactoryLineBreakup.setCumActual(totHourActual);
                                vcutFactoryLineBreakup.setVarianceHour(hourActual - planValue);
                                vcutFactoryLineBreakup.setVarianceCum(totHourActual - totPlanValue);

                                if (actualHourMap.containsKey(vcutFactoryLineBreakup.getId())) {
                                    Long lineTargetTemp = actualHourMap.get(vcutFactoryLineBreakup.getId());
                                    lineTargetTemp = lineTargetTemp + hourActual;
                                    actualHourMap.put(vcutFactoryLineBreakup.getId(), lineTargetTemp);
                                } else {
                                    Long lineTargetTemp = hourActual;
                                    actualHourMap.put(vcutFactoryLineBreakup.getId(), lineTargetTemp);
                                }

                                if (hourActual != null && hourActual > 0 && planValue != null && planValue > 0) {
                                    double percentageEff = (planValue.doubleValue() / vcutStylePlanUpload.getKickOff()) * 100;
                                    double percentageEffHour = (hourActual.doubleValue() / percentageEff) * 100;
                                    vcutFactoryLineBreakup.setEfficiencyPlan(percentageEffHour);
                                } else {
                                    vcutFactoryLineBreakup.setEfficiencyPlan(0.0);
                                }
                                if (totHourActual > 0) {
                                    double percentageEff = (totPlanValue.doubleValue() / vcutStylePlanUpload.getKickOff()) * 100;
                                    double percentageEffHour = (totHourActual.doubleValue() / percentageEff) * 100;
                                    vcutFactoryLineBreakup.setEfficiencyActual(percentageEffHour);
                                    if (activeHour != null && activeHour.equalsIgnoreCase("Y") && vcutFactoryLineBreakup.getActiveHour().equalsIgnoreCase("Y")) {
                                        lineTarget = Math.round((totPlanValue.doubleValue() / vcutStylePlanUpload.getKickOff()) * 100);
                                    }
                                } else {
                                    vcutFactoryLineBreakup.setEfficiencyActual(0.0);
                                }

                                if (hourActual.doubleValue() > 0) {
                                    double fttRateHour = (hourFtt.doubleValue() * 100) / hourActual.doubleValue();
                                    vcutFactoryLineBreakup.setFttRatePlan(fttRateHour);
                                } else {
                                    vcutFactoryLineBreakup.setFttRatePlan(0.0);
                                }

                                if (totHourFtt.doubleValue() > 0 && totFtt > 0) {
                                    double fttRateHour = (totHourFtt.doubleValue() * 100) / totHourActual.doubleValue();
                                    vcutFactoryLineBreakup.setFttRateActual(fttRateHour);
                                } else {
                                    vcutFactoryLineBreakup.setFttRateActual(0.0);
                                }

                                if ((hourRej + hourAlt + hourRec) > 0) {
                                    vcutFactoryLineBreakup.setDhuRatePlan(((hourRej.doubleValue() + hourAlt.doubleValue() + hourRec.doubleValue()) * 100) / (hourFtt.doubleValue() + hourRej.doubleValue() + hourAlt.doubleValue() + hourRec.doubleValue()));
                                } else {
                                    vcutFactoryLineBreakup.setDhuRatePlan(0.0);
                                }

                                if ((totHourRej + totHourAlt + totHourRec) > 0) {
                                    vcutFactoryLineBreakup.setDhuRateActual(((totHourRej.doubleValue() + totHourAlt.doubleValue() + totHourRec.doubleValue()) * 100) / (totHourFtt.doubleValue() + totHourRej.doubleValue() + totHourAlt.doubleValue() + totHourRec.doubleValue()));
                                } else {
                                    vcutFactoryLineBreakup.setDhuRateActual(0.0);
                                }
                                vcutFactoryLineBreakups.add(vcutFactoryLineBreakup);
                            }
                            if (vcutMainEntryMasters != null && vcutMainEntryMasters.size() > 0) {
                                Instant startDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + stylePlanSessionBreakups.get(0).getId().getStartTime())).toInstant();
                                Instant endDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + stylePlanSessionBreakups.get(stylePlanSessionBreakups.size() - 1).getEndTime())).toInstant();

                                Long hourFtt = 0L;
                                Long hourRec = 0L;
                                Long hourRej = 0L;
                                Long hourAlt = 0L;
                                for (VcutMainEntryMasterEntity entryMaster : vcutMainEntryMasters) {
                                    if (entryMaster.getEntryTime().isBefore(startDate) || entryMaster.getEntryTime().isAfter(endDate)) {
                                        if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("FTT")) {
                                            ++hourFtt;
                                        } else if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("ALT") && entryMaster.getRectifiedDate() != null) {
                                            ++hourRec;
                                        } else if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("REJ")) {
                                            ++hourRej;
                                        } else if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("ALT")) {
                                            ++hourAlt;
                                        }
                                    }
                                }
                                if (hourFtt > 0 || hourRec > 0) {
                                    VcutFactoryLineBreakup vcutFactoryLineBreakup = new VcutFactoryLineBreakup();
                                    ++i;
                                    vcutFactoryLineBreakup.setId(i + "");
                                    vcutFactoryLineBreakup.setHourBreakup("************");
                                    vcutFactoryLineBreakup.setDisplayFlag("0");
                                    Long hourActual = hourFtt + hourRec;
                                    totHourActual += hourActual;
                                    totHourFtt += hourFtt;
                                    totHourRec += hourRec;
                                    totHourRej += hourRej;
                                    totHourAlt += hourAlt;
                                    vcutFactoryLineBreakup.setHourPlan(0L);
                                    vcutFactoryLineBreakup.setCumPlan(totPlanValue);
                                    vcutFactoryLineBreakup.setHourActual(hourActual);
                                    vcutFactoryLineBreakup.setCumActual(totHourActual);
                                    vcutFactoryLineBreakup.setVarianceHour(hourActual - 0L);
                                    vcutFactoryLineBreakup.setVarianceCum(totHourActual - totPlanValue);

                                    vcutFactoryLineBreakup.setEfficiencyPlan(0.0);

                                    plannedHourMap.put(vcutFactoryLineBreakup.getId(), 0L);
                                    plannedMainHourMap.put(vcutFactoryLineBreakup.getId(), 0L);

                                    if (totHourActual > 0) {
                                        double percentageEff = (totPlanValue.doubleValue() / vcutStylePlanUpload.getKickOff()) * 100;
                                        double percentageEffHour = (totHourActual.doubleValue() / percentageEff) * 100;
                                        vcutFactoryLineBreakup.setEfficiencyActual(percentageEffHour);
                                    } else {
                                        vcutFactoryLineBreakup.setEfficiencyActual(0.0);
                                    }

                                    if (hourActual.doubleValue() > 0) {
                                        double fttRateHour = (hourFtt.doubleValue() * 100) / hourActual.doubleValue();
                                        vcutFactoryLineBreakup.setFttRatePlan(fttRateHour);
                                    } else {
                                        vcutFactoryLineBreakup.setFttRatePlan(0.0);
                                    }
                                    if (totHourFtt.doubleValue() > 0 && totFtt > 0) {
                                        double fttRateHour = (totHourFtt.doubleValue() * 100) / totHourActual.doubleValue();
                                        vcutFactoryLineBreakup.setFttRateActual(fttRateHour);
                                    } else {
                                        vcutFactoryLineBreakup.setFttRateActual(0.0);
                                    }

                                    if (actualHourMap.containsKey(vcutFactoryLineBreakup.getId())) {
                                        Long lineTargetTemp = actualHourMap.get(vcutFactoryLineBreakup.getId());
                                        lineTargetTemp = lineTargetTemp + hourActual;
                                        actualHourMap.put(vcutFactoryLineBreakup.getId(), lineTargetTemp);
                                    } else {
                                        Long lineTargetTemp = hourActual;
                                        actualHourMap.put(vcutFactoryLineBreakup.getId(), lineTargetTemp);
                                    }

                                    if ((hourRej + hourAlt + hourRec) > 0) {
                                        vcutFactoryLineBreakup.setDhuRatePlan(((hourRej.doubleValue() + hourAlt.doubleValue() + hourRec.doubleValue()) * 100) / (hourFtt.doubleValue() + hourRej.doubleValue() + hourAlt.doubleValue() + hourRec.doubleValue()));
                                    } else {
                                        vcutFactoryLineBreakup.setDhuRatePlan(0.0);
                                    }

                                    if ((totHourRej + totHourAlt + totHourRec) > 0) {
                                        vcutFactoryLineBreakup.setDhuRateActual(((totHourRej.doubleValue() + totHourAlt.doubleValue() + totHourRec.doubleValue()) * 100) / (totHourFtt.doubleValue() + totHourRej.doubleValue() + totHourAlt.doubleValue() + totHourRec.doubleValue()));
                                    } else {
                                        vcutFactoryLineBreakup.setDhuRateActual(0.0);
                                    }

                                    vcutFactoryLineBreakups.add(vcutFactoryLineBreakup);
                                }
                            }
                            vcutFactorySummaryStyle.setVcutFactoryLineBreakups(vcutFactoryLineBreakups);
                        }
                    }

                    double achEff = (ach.doubleValue() * 100) / lineTarget.doubleValue();
                    vcutFactorySummaryStyle.setAchEff(achEff);


                    // Line Wise
                    lineSmv += vcutStylePlanUpload.getSmv();
                    ++lineCtr;
                    lineOperators += vcutStylePlanUpload.getOperators();
                    lineHelpers += vcutStylePlanUpload.getHelpers();
                    lineDayTarget += vcutStylePlanUpload.getQuantity();
                    lineMainTarget += lineTarget;
                    lineMainTargetPlan += lineTargetMain;

                    //Factory Wise
                    smv += vcutStylePlanUpload.getSmv();
                    ++ctr;
                    operators += vcutStylePlanUpload.getOperators();
                    helpers += vcutStylePlanUpload.getHelpers();
                    dayTarget += vcutStylePlanUpload.getQuantity();
                    mainTarget += lineTarget;
                    mainTargetPlan += lineTargetMain;

                    //Defects Issue Wise
                    List<Object[]> vcutMainDefectsMasters = vcutMainEntryMasterRepository.findByDefectsPlanId(vcutStylePlanUpload.getId());
                    if (stylePlanSessionBreakups != null && stylePlanSessionBreakups.size() > 0) {
                        for (Object[] objects : vcutMainDefectsMasters) {
                            if (defectMap.containsKey(Long.parseLong(objects[1].toString()))) {
                                Long defectCount = defectMap.get(Long.parseLong(objects[1].toString()));
                                ++defectCount;
                                defectMap.put(Long.parseLong(objects[1].toString()), defectCount);

                                Map<String, List<Instant>> styleMap = defectDateMap.get(Long.parseLong(objects[1].toString()));
                                if (styleMap.containsKey(vcutStylePlanUpload.getStyle())) {
                                    List<Instant> dates = styleMap.get(vcutStylePlanUpload.getStyle());
                                    Instant date = Instant.parse(objects[0].toString());
                                    dates.add(date);
                                    styleMap.put(vcutStylePlanUpload.getStyle(), dates);
                                } else {
                                    List<Instant> dates = new ArrayList<>();
                                    Instant date = Instant.parse(objects[0].toString());
                                    dates.add(date);
                                    styleMap.put(vcutStylePlanUpload.getStyle(), dates);
                                }
                                defectDateMap.put(Long.parseLong(objects[1].toString()), styleMap);
                            } else {
                                defectMap.put(Long.parseLong(objects[1].toString()), 1L);

                                Map<String, List<Instant>> styleMap = new HashMap<>();
                                List<Instant> dates = new ArrayList<>();
                                Instant date = Instant.parse(objects[0].toString());
                                dates.add(date);
                                styleMap.put(vcutStylePlanUpload.getStyle(), dates);
                                defectDateMap.put(Long.parseLong(objects[1].toString()), styleMap);
                            }
                        }
                    }

                    // Defects OB Wise
                    List<Object[]> vcutMainDefectsOBMasters = vcutMainEntryMasterRepository.findByDefectsOperationPlanId(vcutStylePlanUpload.getId());
                    if (stylePlanSessionBreakups != null && stylePlanSessionBreakups.size() > 0) {
                        for (Object[] objects : vcutMainDefectsOBMasters) {
                            if (defectOBMap.containsKey(Long.parseLong(objects[1].toString()))) {
                                Long defectCount = defectOBMap.get(Long.parseLong(objects[1].toString()));
                                ++defectCount;
                                defectOBMap.put(Long.parseLong(objects[1].toString()), defectCount);

                                Map<String, List<Instant>> styleMap = defectOBDateMap.get(Long.parseLong(objects[1].toString()));
                                if (styleMap.containsKey(vcutStylePlanUpload.getStyle())) {
                                    List<Instant> dates = styleMap.get(vcutStylePlanUpload.getStyle());
                                    Instant date = Instant.parse(objects[0].toString());
                                    dates.add(date);
                                    styleMap.put(vcutStylePlanUpload.getStyle(), dates);
                                } else {
                                    List<Instant> dates = new ArrayList<>();
                                    Instant date = Instant.parse(objects[0].toString());
                                    dates.add(date);
                                    styleMap.put(vcutStylePlanUpload.getStyle(), dates);
                                }
                                defectOBDateMap.put(Long.parseLong(objects[1].toString()), styleMap);
                            } else {
                                defectOBMap.put(Long.parseLong(objects[1].toString()), 1L);

                                Map<String, List<Instant>> styleMap = new HashMap<>();
                                List<Instant> dates = new ArrayList<>();
                                Instant date = Instant.parse(objects[0].toString());
                                dates.add(date);
                                styleMap.put(vcutStylePlanUpload.getStyle(), dates);
                                defectOBDateMap.put(Long.parseLong(objects[1].toString()), styleMap);
                            }
                        }
                    }
                    if (vcutFactorySummaryStyle != null && vcutFactorySummaryStyle.getStartTime() != null) {
                        styleSummaryList.add(vcutFactorySummaryStyle);
                    }
                }
                vcutFactorySummaryLine.setSmv(lineSmv / lineCtr);
                vcutFactorySummaryLine.setOperators(lineOperators);
                vcutFactorySummaryLine.setHelpers(lineHelpers);
                vcutFactorySummaryLine.setQuantity(lineDayTarget);
                if (lineMainTargetPlan > 0 && lineDayTarget > 0) {
                    double planEff = (lineDayTarget.doubleValue() * 100) / lineMainTargetPlan.doubleValue();
                    vcutFactorySummaryLine.setPlanEff(planEff);
                } else {
                    vcutFactorySummaryLine.setPlanEff(0.0);
                }
                Long lineAch = lineFtt + lineRec;
                vcutFactorySummaryLine.setAchieved(lineAch);
                if (lineAch > 0 && lineMainTarget > 0) {
                    double lineAchEff = (lineAch.doubleValue() * 100) / lineMainTarget.doubleValue();
                    vcutFactorySummaryLine.setAchEff(lineAchEff);
                } else {
                    vcutFactorySummaryLine.setAchEff(0.0);
                }
                vcutFactorySummaryLine.setBalance(lineDayTarget - (lineFtt + lineRec));
                vcutFactorySummaryLine.setFtt(lineFtt);
                vcutFactorySummaryLine.setRectified(lineRec);
                vcutFactorySummaryLine.setAlter(lineAlt);
                vcutFactorySummaryLine.setRejected(lineRej);
                if (styleSummaryList != null && styleSummaryList.size()>0) {
                    Collections.sort(styleSummaryList, Comparator.comparing(VcutFactorySummary::getStartTime));
                    vcutFactorySummaryLine.setVcutFactorySummaries(styleSummaryList);
                }

                // Defects Issue Wise
                List<VcutTvDefectBreakup> vcutTvDefectBreakups = new ArrayList<>();
                //Line Breakup
                for (Long dKey : defectMap.keySet()) {
                    VcutOperationIssueMaster vcutOperationIssueMaster = null;
                    if (issueMap.containsKey(dKey)) {
                        vcutOperationIssueMaster = issueMap.get(dKey);
                    } else {
                        vcutOperationIssueMaster = vcutOperationIssueMasterRepository.findById(dKey).orElse(null);
                        issueMap.put(dKey, vcutOperationIssueMaster);
                    }
                    VcutTvDefectBreakup vcutTvDefectBreakup = new VcutTvDefectBreakup();
                    vcutTvDefectBreakup.setId(dKey);
                    vcutTvDefectBreakup.setDescription(vcutOperationIssueMaster.getDescription());
                    vcutTvDefectBreakup.setDescriptionLocal(vcutOperationIssueMaster.getDescriptionLocal());
                    vcutTvDefectBreakup.setDefectCount(defectMap.get(dKey));

                    Map<String, List<Instant>> dates = defectDateMap.get(dKey);
                    Long srNo = 0L;
                    Long previousDefect = 0L;
                    List<VcutTvDefectBreakupSummary> summaries = new ArrayList<>();
                    String activeHour = "N";
                    for (String sKey : styleSessionMap.keySet()) {
                        List<VcutStylePlanSessionBreakup> stylePlanSessionBreakups = styleSessionMap.get(sKey);
                        Instant startDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + stylePlanSessionBreakups.get(0).getId().getStartTime())).toInstant();
                        Instant endDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + stylePlanSessionBreakups.get(stylePlanSessionBreakups.size() - 1).getEndTime())).toInstant();
                        Long hour = 0L;
                        for (VcutStylePlanSessionBreakup vcutStylePlanSessionBreakup : stylePlanSessionBreakups) {
                            Instant rowstartDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + vcutStylePlanSessionBreakup.getId().getStartTime())).toInstant();
                            Instant rowendDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + vcutStylePlanSessionBreakup.getEndTime())).toInstant();

                            Long dCounter = 0L;
                            if (dates.containsKey(sKey)) {
                                for (Instant date : dates.get(sKey)) {
                                    if (date.equals(rowstartDate) || (date.isAfter(rowstartDate) && date.isBefore(rowendDate))) {
                                        ++dCounter;
                                    }
                                }
                            }
                            VcutTvDefectBreakupSummary vcutTvDefectBreakupSummary = new VcutTvDefectBreakupSummary();
                            if (activeHour != null && activeHour.equalsIgnoreCase("Y")) {
                                vcutTvDefectBreakupSummary.setActiveHour("Z");
                            } else {
                                if (currentDate.toInstant().equals(rowstartDate) || (currentDate.toInstant().isAfter(rowstartDate) && currentDate.toInstant().isBefore(rowendDate))) {
                                    activeHour = "Y";
                                    vcutTvDefectBreakupSummary.setActiveHour("Y");
                                } else {
                                    vcutTvDefectBreakupSummary.setActiveHour("N");
                                }
                            }
                            vcutTvDefectBreakupSummary.setId(dKey);
                            vcutTvDefectBreakupSummary.setDescription(vcutOperationIssueMaster.getDescription());
                            vcutTvDefectBreakupSummary.setDescriptionLocal(vcutOperationIssueMaster.getDescriptionLocal());
                            vcutTvDefectBreakupSummary.setStyle(sKey);
                            if (vcutStylePlanSessionBreakup.getType() != null && (vcutStylePlanSessionBreakup.getType().equalsIgnoreCase("LUNCH") || vcutStylePlanSessionBreakup.getType().equalsIgnoreCase("TEA"))) {
                                vcutTvDefectBreakupSummary.setHours("B");
                            } else {
                                ++hour;
                                vcutTvDefectBreakupSummary.setHours(hour + "");
                            }
                            vcutTvDefectBreakupSummary.setCountDefect(dCounter);
                            previousDefect += dCounter;
                            vcutTvDefectBreakupSummary.setCountDefectCum(previousDefect);
                            vcutTvDefectBreakupSummary.setStartDate(Date.from(startDate));
                            vcutTvDefectBreakupSummary.setStartTime(vcutStylePlanSessionBreakup.getId().getStartTime());
                            vcutTvDefectBreakupSummary.setEndTime(vcutStylePlanSessionBreakup.getEndTime());
                            vcutTvDefectBreakupSummary.setSrNo(++srNo);
                            summaries.add(vcutTvDefectBreakupSummary);
                        }

                        Long dCounter = 0L;
                        if (dates.containsKey(sKey)) {
                            for (Instant date : dates.get(sKey)) {
                                if (date.isBefore(startDate) || date.isAfter(endDate)) {
                                    ++dCounter;
                                }
                            }
                        }

                        VcutTvDefectBreakupSummary vcutTvDefectBreakupSummary = new VcutTvDefectBreakupSummary();
                        vcutTvDefectBreakupSummary.setId(dKey);
                        vcutTvDefectBreakupSummary.setDescription(vcutOperationIssueMaster.getDescription());
                        vcutTvDefectBreakupSummary.setDescriptionLocal(vcutOperationIssueMaster.getDescriptionLocal());
                        vcutTvDefectBreakupSummary.setStyle(sKey);
                        vcutTvDefectBreakupSummary.setSessionSpl("YES");
                        ++hour;
                        vcutTvDefectBreakupSummary.setHours(hour + "");
                        vcutTvDefectBreakupSummary.setCountDefect(dCounter);
                        previousDefect += dCounter;
                        vcutTvDefectBreakupSummary.setCountDefectCum(previousDefect);
                        vcutTvDefectBreakupSummary.setStartDate(Date.from(startDate));
                        vcutTvDefectBreakupSummary.setSrNo(++srNo);
                        summaries.add(vcutTvDefectBreakupSummary);
                    }
                    Collections.sort(summaries, Comparator.comparing(VcutTvDefectBreakupSummary::getStartDate).thenComparing(VcutTvDefectBreakupSummary::getSrNo));
                    Long prev = 0L;
                    for (VcutTvDefectBreakupSummary summary : summaries) {
                        prev += summary.getCountDefect();
                        summary.setCountDefectCum(prev);
                    }
                    vcutTvDefectBreakup.setSummaries(summaries);
                    vcutTvDefectBreakups.add(vcutTvDefectBreakup);
                }
                while (true) {
                    Integer index = removeIndex(vcutTvDefectBreakups);
                    if (index == null) {
                        break;
                    } else {
                        List<VcutTvDefectBreakup> tvDefectBreakups = new ArrayList<>();
                        for (VcutTvDefectBreakup vcutTvDefectBreakup : vcutTvDefectBreakups) {
                            List<VcutTvDefectBreakupSummary> summaries = vcutTvDefectBreakup.getSummaries();
                            summaries.remove(index.intValue());
                            vcutTvDefectBreakup.setSummaries(summaries);
                            tvDefectBreakups.add(vcutTvDefectBreakup);
                        }
                        vcutTvDefectBreakups.clear();
                        vcutTvDefectBreakups.addAll(tvDefectBreakups);
                    }
                }
                Collections.sort(vcutTvDefectBreakups, Collections.reverseOrder(Comparator.comparing(VcutTvDefectBreakup::getDefectCount)));
                vcutFactorySummaryLine.setVcutTvDefectBreakups(vcutTvDefectBreakups);


                // Defects OB Wise
                List<VcutTvDefectBreakup> vcutTvDefectOBBreakups = new ArrayList<>();
                //Line Breakup
                for (Long dKey : defectOBMap.keySet()) {
                    VcutOperationMaster vcutOperationMaster = null;
                    if (operationMap.containsKey(dKey)) {
                        vcutOperationMaster = operationMap.get(dKey);
                    } else {
                        vcutOperationMaster = vcutOperationMasterRepository.findById(dKey).orElse(null);
                        operationMap.put(dKey, vcutOperationMaster);
                    }
                    VcutTvDefectBreakup vcutTvDefectBreakup = new VcutTvDefectBreakup();
                    vcutTvDefectBreakup.setId(dKey);
                    vcutTvDefectBreakup.setDescription(vcutOperationMaster.getDescription());
                    vcutTvDefectBreakup.setDescriptionLocal(vcutOperationMaster.getDescriptionLocal());
                    vcutTvDefectBreakup.setDefectCount(defectOBMap.get(dKey));

                    Map<String, List<Instant>> dates = defectOBDateMap.get(dKey);
                    Long srNo = 0L;
                    Long previousDefect = 0L;
                    List<VcutTvDefectBreakupSummary> summaries = new ArrayList<>();
                    String activeHour = "N";
                    for (String sKey : styleSessionMap.keySet()) {
                        List<VcutStylePlanSessionBreakup> stylePlanSessionBreakups = styleSessionMap.get(sKey);
                        Instant startDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + stylePlanSessionBreakups.get(0).getId().getStartTime())).toInstant();
                        Instant endDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + stylePlanSessionBreakups.get(stylePlanSessionBreakups.size() - 1).getEndTime())).toInstant();
                        Long hour = 0L;
                        for (VcutStylePlanSessionBreakup vcutStylePlanSessionBreakup : stylePlanSessionBreakups) {
                            Instant rowstartDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + vcutStylePlanSessionBreakup.getId().getStartTime())).toInstant();
                            Instant rowendDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + vcutStylePlanSessionBreakup.getEndTime())).toInstant();

                            Long dCounter = 0L;
                            if (dates.containsKey(sKey)) {
                                for (Instant date : dates.get(sKey)) {
                                    if (date.equals(rowstartDate) || (date.isAfter(rowstartDate) && date.isBefore(rowendDate))) {
                                        ++dCounter;
                                    }
                                }
                            }
                            VcutTvDefectBreakupSummary vcutTvDefectBreakupSummary = new VcutTvDefectBreakupSummary();
                            if (activeHour != null && activeHour.equalsIgnoreCase("Y")) {
                                vcutTvDefectBreakupSummary.setActiveHour("Z");
                            } else {
                                if (currentDate.toInstant().equals(rowstartDate) || (currentDate.toInstant().isAfter(rowstartDate) && currentDate.toInstant().isBefore(rowendDate))) {
                                    activeHour = "Y";
                                    vcutTvDefectBreakupSummary.setActiveHour("Y");
                                } else {
                                    vcutTvDefectBreakupSummary.setActiveHour("N");
                                }
                            }
                            vcutTvDefectBreakupSummary.setId(dKey);
                            vcutTvDefectBreakupSummary.setDescription(vcutOperationMaster.getDescription());
                            vcutTvDefectBreakupSummary.setDescriptionLocal(vcutOperationMaster.getDescriptionLocal());
                            vcutTvDefectBreakupSummary.setStyle(sKey);
                            if (vcutStylePlanSessionBreakup.getType() != null && (vcutStylePlanSessionBreakup.getType().equalsIgnoreCase("LUNCH") || vcutStylePlanSessionBreakup.getType().equalsIgnoreCase("TEA"))) {
                                vcutTvDefectBreakupSummary.setHours("B");
                            } else {
                                ++hour;
                                vcutTvDefectBreakupSummary.setHours(hour + "");
                            }
                            vcutTvDefectBreakupSummary.setCountDefect(dCounter);
                            previousDefect += dCounter;
                            vcutTvDefectBreakupSummary.setCountDefectCum(previousDefect);
                            vcutTvDefectBreakupSummary.setStartDate(Date.from(startDate));
                            vcutTvDefectBreakupSummary.setStartTime(vcutStylePlanSessionBreakup.getId().getStartTime());
                            vcutTvDefectBreakupSummary.setEndTime(vcutStylePlanSessionBreakup.getEndTime());
                            vcutTvDefectBreakupSummary.setSrNo(++srNo);
                            summaries.add(vcutTvDefectBreakupSummary);
                        }

                        Long dCounter = 0L;
                        if (dates.containsKey(sKey)) {
                            for (Instant date : dates.get(sKey)) {
                                if (date.isBefore(startDate) || date.isAfter(endDate)) {
                                    ++dCounter;
                                }
                            }
                        }

                        VcutTvDefectBreakupSummary vcutTvDefectBreakupSummary = new VcutTvDefectBreakupSummary();
                        vcutTvDefectBreakupSummary.setId(dKey);
                        vcutTvDefectBreakupSummary.setDescription(vcutOperationMaster.getDescription());
                        vcutTvDefectBreakupSummary.setDescriptionLocal(vcutOperationMaster.getDescriptionLocal());
                        vcutTvDefectBreakupSummary.setStyle(sKey);
                        vcutTvDefectBreakupSummary.setSessionSpl("YES");
                        ++hour;
                        vcutTvDefectBreakupSummary.setHours(hour + "");
                        vcutTvDefectBreakupSummary.setCountDefect(dCounter);
                        previousDefect += dCounter;
                        vcutTvDefectBreakupSummary.setCountDefectCum(previousDefect);
                        vcutTvDefectBreakupSummary.setStartDate(Date.from(startDate));
                        vcutTvDefectBreakupSummary.setSrNo(++srNo);
                        summaries.add(vcutTvDefectBreakupSummary);
                    }
                    Collections.sort(summaries, Comparator.comparing(VcutTvDefectBreakupSummary::getStartDate).thenComparing(VcutTvDefectBreakupSummary::getSrNo));
                    Long prev = 0L;
                    for (VcutTvDefectBreakupSummary summary : summaries) {
                        prev += summary.getCountDefect();
                        summary.setCountDefectCum(prev);
                    }
                    vcutTvDefectBreakup.setSummaries(summaries);
                    vcutTvDefectOBBreakups.add(vcutTvDefectBreakup);
                }
                while (true) {
                    Integer index = removeIndex(vcutTvDefectOBBreakups);
                    if (index == null) {
                        break;
                    } else {
                        List<VcutTvDefectBreakup> tvDefectBreakups = new ArrayList<>();
                        for (VcutTvDefectBreakup vcutTvDefectBreakup : vcutTvDefectOBBreakups) {
                            List<VcutTvDefectBreakupSummary> summaries = vcutTvDefectBreakup.getSummaries();
                            summaries.remove(index.intValue());
                            vcutTvDefectBreakup.setSummaries(summaries);
                            tvDefectBreakups.add(vcutTvDefectBreakup);
                        }
                        vcutTvDefectOBBreakups.clear();
                        vcutTvDefectOBBreakups.addAll(tvDefectBreakups);
                    }
                }
                Collections.sort(vcutTvDefectOBBreakups, Collections.reverseOrder(Comparator.comparing(VcutTvDefectBreakup::getDefectCount)));
                vcutFactorySummaryLine.setVcutTvDefectOBBreakups(vcutTvDefectOBBreakups);
                summaryList.add(vcutFactorySummaryLine);
            }
            vcutFactorySummary.setSmv(smv / ctr);
            vcutFactorySummary.setOperators(operators);
            vcutFactorySummary.setHelpers(helpers);
            vcutFactorySummary.setQuantity(dayTarget);
            if (dayTarget > 0 && mainTargetPlan > 0) {
                double planEff = (dayTarget.doubleValue() * 100) / mainTargetPlan.doubleValue();
                vcutFactorySummary.setPlanEff(planEff);
            } else {
                vcutFactorySummary.setPlanEff(0.0);
            }
            Long ach = totFtt + totRec;
            vcutFactorySummary.setAchieved(ach);
            if (ach > 0 && mainTarget > 0) {
                double achEff = (ach.doubleValue() * 100) / mainTarget.doubleValue();
                vcutFactorySummary.setAchEff(achEff);
            } else {
                vcutFactorySummary.setAchEff(0.0);
            }
            vcutFactorySummary.setBalance(dayTarget - (totFtt + totRec));
            vcutFactorySummary.setFtt(totFtt);
            vcutFactorySummary.setRectified(totRec);
            vcutFactorySummary.setAlter(totAlt);
            vcutFactorySummary.setRejected(totRej);
            Collections.sort(summaryList, Comparator.comparing(VcutFactorySummary :: getLine));
            vcutFactorySummary.setVcutFactorySummaries(summaryList);

            List<VcutFactoryLineBreakup> hourlyFactoryBreakups = new ArrayList<>();
            if (plannedHourMap != null && plannedHourMap.keySet().size() > 0) {
                for (String key : plannedHourMap.keySet()) {
                    VcutFactoryLineBreakup vcutFactoryLineBreakup = new VcutFactoryLineBreakup();
                    vcutFactoryLineBreakup.setId(key);
                    vcutFactoryLineBreakup.setActiveHour(activeFlagMap.containsKey(key) ?  activeFlagMap.get(key) : "N");
                    Double lineDayTarget = plannedMainHourMap.get(key).doubleValue();
                    Double lineMainTargetPlan = plannedHourMap.get(key).doubleValue();
                    Double lineAch = actualHourMap.get(key).doubleValue();

                    if (lineMainTargetPlan > 0 && lineDayTarget > 0) {
                        double planEff = (lineDayTarget.doubleValue() * 100) / lineMainTargetPlan.doubleValue();
                        vcutFactoryLineBreakup.setEfficiencyPlan(planEff);
                    } else {
                        vcutFactoryLineBreakup.setEfficiencyPlan(0.0);
                    }

                    if (lineAch > 0 && lineMainTargetPlan > 0) {
                        double lineAchEff = (lineAch.doubleValue() * 100) / lineMainTargetPlan.doubleValue();
                        vcutFactoryLineBreakup.setEfficiencyActual(lineAchEff);
                    } else {
                        vcutFactoryLineBreakup.setEfficiencyActual(0.0);
                    }
                    hourlyFactoryBreakups.add(vcutFactoryLineBreakup);
                }
            }
            vcutFactorySummary.setHourlyFactoryBreakups(hourlyFactoryBreakups);
        }
        return ResponseEntity.ok().body(vcutFactorySummary);
    }

    /**
     * {@code GET  /vcut-style-plan-uploads} : get all the vcutStylePlanUploads.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vcutStylePlanUploads in body.
     */
    @GetMapping("/vcut-line-images-summaries/{id}")
    public ResponseEntity<VcutTvImageSummary> getAllVcutImageSummary(@PathVariable Long id) throws ParseException, NoSuchAlgorithmException {
        log.debug("REST request to get a page of VcutTvConfigrations");
        VcutTvImageSummary vcutTvImageSummary = new VcutTvImageSummary();

        VcutStylePlanUpload vcutStylePlanUpload = vcutStylePlanUploadRepository.findById(id).orElse(null);
        if (vcutStylePlanUpload != null) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MMM hh:mm a");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat simpleDateFormatWithTime = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            vcutTvImageSummary.setDay("Day " + vcutStylePlanUpload.getDays());
            vcutTvImageSummary.setBuyerName(vcutStylePlanUpload.getBuyerName());
            vcutTvImageSummary.setStyle(vcutStylePlanUpload.getStyle());
            vcutTvImageSummary.setColor(vcutStylePlanUpload.getColorName());
            vcutTvImageSummary.setPoNo(vcutStylePlanUpload.getPoNo());

            VcutStyleImage vcutStyleImage = vcutStyleImageRepository.findByStyle(vcutStylePlanUpload.getStyle());
            if (vcutStyleImage != null) {
                vcutTvImageSummary.setImageBack(MD5UrlEncryption.fakeUploadUrl("style-sketch/" + vcutStyleImage.getBackImage()));
                vcutTvImageSummary.setImageFront(MD5UrlEncryption.fakeUploadUrl("style-sketch/" + vcutStyleImage.getFrontImage()));
            }
            List<Object[]> objectsList = vcutMainEntryMasterRepository.findByDefectsAxisPlanId(vcutStylePlanUpload.getId());
            Map<String, List<VcutTvcCordinate>> cordinateMap = new HashMap<>();
            for (Object[] object : objectsList) {
                if (object[0] != null && cordinateMap.containsKey(object[0].toString())) {
                    List<VcutTvcCordinate> cordinates = cordinateMap.get(object[0].toString());
                    VcutTvcCordinate vcutTvcCordinate = new VcutTvcCordinate();
                    vcutTvcCordinate.setCoordinateType(object[0].toString());
                    vcutTvcCordinate.setCoordinateX(object[1].toString());
                    vcutTvcCordinate.setCoordinateY(object[2].toString());
                    cordinates.add(vcutTvcCordinate);
                    cordinateMap.put(object[0].toString(), cordinates);
                } else {
                    List<VcutTvcCordinate> cordinates = new ArrayList<>();
                    VcutTvcCordinate vcutTvcCordinate = new VcutTvcCordinate();
                    vcutTvcCordinate.setCoordinateType(object[0].toString());
                    vcutTvcCordinate.setCoordinateX(object[1].toString());
                    vcutTvcCordinate.setCoordinateY(object[2].toString());
                    cordinates.add(vcutTvcCordinate);
                    cordinateMap.put(object[0].toString(), cordinates);
                }
            }
            for (String key : cordinateMap.keySet()) {
                if (key != null && key.equalsIgnoreCase("F")) {
                    vcutTvImageSummary.setFrontCordinates(cordinateMap.get(key));
                } else if (key != null && key.equalsIgnoreCase("B")) {
                    vcutTvImageSummary.setBackCordinates(cordinateMap.get(key));
                }
            }
        } else {
            return ResponseEntity.badRequest().body(vcutTvImageSummary);
        }
        return ResponseEntity.ok().body(vcutTvImageSummary);
    }

    @GetMapping("/vcut-factory-manual/{date}")
    public void getAllVcutFactorySummaries(@PathVariable String date) throws ParseException {
        VcutMainEntryMaster entryMaster = new VcutMainEntryMaster();
        entryMaster.setVcutStylePlanUpload(new VcutStylePlanUpload(31L));
        entryMaster.setEntryBy("102000046");
        entryMaster.setEntryTime(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(date).toInstant());
        entryMaster.entryType("FTT");
        vcutMainEntryMasterRepository.save(entryMaster);
    }

    private Integer removeIndex(List<VcutTvDefectBreakup> vcutTvDefectBreakups) {
        Map<Integer, Long> removeList = new HashMap<>();
        for (VcutTvDefectBreakup vcutTvDefectBreakup : vcutTvDefectBreakups) {
            int index = 0;
            for (VcutTvDefectBreakupSummary vcutTvDefectBreakupSummary : vcutTvDefectBreakup.getSummaries()) {
                if (vcutTvDefectBreakupSummary.getSessionSpl() != null && vcutTvDefectBreakupSummary.getSessionSpl().equalsIgnoreCase("YES")) {
                    if (removeList.containsKey(index)) {
                        if (vcutTvDefectBreakupSummary.getCountDefect() > removeList.get(index)) {
                            removeList.put(index, vcutTvDefectBreakupSummary.getCountDefect());
                        }
                    } else {
                        removeList.put(index, vcutTvDefectBreakupSummary.getCountDefect());
                    }
                }
                ++index;
            }
        }
        for (Integer index : removeList.keySet()) {
            Long value = removeList.get(index);
            if(value == 0L) {
                return index;
            }
        }
        return null;
    }
}
