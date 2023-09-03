package io.vamani.application.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.config.Constants;
import io.vamani.application.db2.domain.*;
import io.vamani.application.db2.repository.*;
import io.vamani.application.domain.*;
import io.vamani.application.domain.PackingProductionEntry;
import io.vamani.application.model.*;
import io.vamani.application.repository.PackingLineIssueDetailsRepository;
import io.vamani.application.repository.PackingProductionEntryRepository;
import io.vamani.application.repository.PackingProgressDetailsRepository;
import io.vamani.application.repository.PackingProgressEntryRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
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

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link PackingProductionEntry}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PackingProductionEntryResource {
    private final Logger log = LoggerFactory.getLogger(PackingProductionEntryResource.class);

    private static final String ENTITY_NAME = "packingProgressEntry";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PackingProductionEntryRepository packingProductionEntryRepository;

    @Autowired
    private PackingProgressEntryRepository packingProgressEntryRepository;

    @Autowired
    private PackingProgressDetailsRepository packingProgressDetailsRepository;

    @Autowired
    private PackingLineIssueDetailsRepository packingLineIssueDetailsRepository;

    @Autowired
    private ProductiondemandstepRepository productiondemandstepRepository;

    @Autowired
    private AdstorageimportRepository adstorageimportRepository;

    @Autowired
    private ProductionprogressimportRepository productionprogressimportRepository;

    @Autowired
    private StocktransactionimportRepository stocktransactionimportRepository;

    @Autowired
    private ProductiondemandRepository productiondemandRepository;

    @Autowired
    private LogicalwarehouseRepository logicalwarehouseRepository;

    public PackingProductionEntryResource(PackingProductionEntryRepository packingProductionEntryRepository) {
        this.packingProductionEntryRepository = packingProductionEntryRepository;
    }
    /**
     * {@code POST  /packing-production-entries} : Create a new packingProductionEntry.
     *
     * @param packingProductionEntry the packingProductionEntry to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new packingProductionEntry, or with status {@code 400 (Bad Request)} if the packingProductionEntry has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/packing-production-entries")
    public ResponseEntity<PackingProductionEntryBean> createPackingProductionEntry(@Valid @RequestBody PackingProductionEntryBean packingProductionEntryBean) throws URISyntaxException {
        log.debug("REST request to save PackingProductionEntry : {}", packingProductionEntryBean);
        if (packingProductionEntryBean.getId() != null) {
            throw new BadRequestAlertException("A new packingProductionEntry cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PackingProductionEntry packingProductionEntry = new PackingProductionEntry();
        BeanUtils.copyProperties(packingProductionEntryBean, packingProductionEntry);
        packingProductionEntry.setCreatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
        packingProductionEntry.setCreateddate(Instant.now());
        PackingProductionEntry result = packingProductionEntryRepository.save(packingProductionEntry);
        if (result != null && packingProductionEntryBean.getPackingProgressEntry() != null) {
            PackingProgressEntryBean packingProgressEntryBean = packingProductionEntryBean.getPackingProgressEntry();
            PackingProgressEntry packingProgressEntry = new PackingProgressEntry();
            BeanUtils.copyProperties(packingProgressEntryBean, packingProgressEntry);
            packingProgressEntry.setId(null);
            packingProgressEntry.setProgressEntryBy(SecurityUtils.getCurrentUserLogin().orElse(null));
            packingProgressEntry.setProgressEntryDate(Instant.now());
            packingProgressEntry.setPackingProductionEntry(result);
            PackingProgressEntry resultPacking = packingProgressEntryRepository.save(packingProgressEntry);
            if (resultPacking != null && packingProgressEntryBean.getStitchIssuePackDetails() != null && packingProgressEntryBean.getStitchIssuePackDetails().size() > 0) {
                for (StitchIssuePackDetailsBean stitchIssuePackDetailsBean : packingProgressEntryBean.getStitchIssuePackDetails()) {
                    PackingProgressDetails packingProgressDetails = new PackingProgressDetails();
                    BeanUtils.copyProperties(stitchIssuePackDetailsBean, packingProgressDetails);
                    packingProgressDetails.setPackingProgressEntry(resultPacking);
                    packingProgressDetailsRepository.save(packingProgressDetails);
                }
            }
        }
        return getPackingProductionEntry(result.getId());
    }

    /**
     * {@code PUT  /packing-production-entries} : Updates an existing packingProductionEntry.
     *
     * @param packingProductionEntry the packingProductionEntry to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated packingProductionEntry,
     * or with status {@code 400 (Bad Request)} if the packingProductionEntry is not valid,
     * or with status {@code 500 (Internal Server Error)} if the packingProductionEntry couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/packing-production-entries")
    public ResponseEntity<PackingProductionEntryBean> updatePackingProductionEntry(@Valid @RequestBody PackingProductionEntryBean packingProductionEntryBean) throws URISyntaxException {
        log.debug("REST request to update PackingProductionEntry : {}", packingProductionEntryBean);
        if (packingProductionEntryBean.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        PackingProductionEntry packingProductionEntry = new PackingProductionEntry();
        BeanUtils.copyProperties(packingProductionEntryBean, packingProductionEntry);
        packingProductionEntry.setUpdatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
        packingProductionEntry.setUpdateddate(Instant.now());
        PackingProductionEntry result = packingProductionEntryRepository.save(packingProductionEntry);
        if (result != null && packingProductionEntryBean.getPackingProgressEntry() != null) {
            PackingProgressEntryBean packingProgressEntryBean = packingProductionEntryBean.getPackingProgressEntry();
            PackingProgressEntry packingProgressEntry = new PackingProgressEntry();
            BeanUtils.copyProperties(packingProgressEntryBean, packingProgressEntry);
            packingProgressEntry.setProgressEntryBy(SecurityUtils.getCurrentUserLogin().orElse(null));
            packingProgressEntry.setProgressEntryDate(Instant.now());
            packingProgressEntry.setPackingProductionEntry(result);
            PackingProgressEntry resultPacking = packingProgressEntryRepository.save(packingProgressEntry);
            if (resultPacking != null && packingProgressEntryBean.getStitchIssuePackDetails() != null && packingProgressEntryBean.getStitchIssuePackDetails().size() > 0) {
                for (StitchIssuePackDetailsBean stitchIssuePackDetailsBean : packingProgressEntryBean.getStitchIssuePackDetails()) {
                    PackingProgressDetails packingProgressDetails = new PackingProgressDetails();
                    BeanUtils.copyProperties(stitchIssuePackDetailsBean, packingProgressDetails);
                    packingProgressDetails.setPackingProgressEntry(resultPacking);
                    packingProgressDetailsRepository.save(packingProgressDetails);
                }
            }
        }

        return getPackingProductionEntry(result.getId());
    }

    /**
     * {@code GET  /packing-production-entries} : get all the packingProductionEntryes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of packingProductionEntryes in body.
     */
    @GetMapping("/packing-production-entries")
    public ResponseEntity<List<PackingProductionEntry>> getAllPackingProductionEntryes(Pageable pageable) {
        log.debug("REST request to get a page of PackingProductionEntryes");
        Page<PackingProductionEntry> page = packingProductionEntryRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cut-plan-entries} : get all the cutPlanEntries.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cutPlanEntries in body.
     */
    @PostMapping("/packing-production-entries-filter")
    public ResponseEntity<List<PackingProductionEntry>> getAllPackingProductionEntryesFilter(@RequestBody CutPlanSearch search) {
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
        Page<PackingProductionEntry> page = packingProductionEntryRepository.findAllByTypeAndPonoAndStyle(style, color, login, search.getPage());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /*

    /**
     * {@code GET  /packing-production-entries/:id} : get the "id" packingProductionEntry.
     *
     * @param id the id of the packingProductionEntry to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the packingProductionEntry, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/packing-production-entries/{id}")
    public ResponseEntity<PackingProductionEntryBean> getPackingProductionEntry(@PathVariable Long id) {
        log.debug("REST request to get PackingProductionEntry : {}", id);
        PackingProductionEntryBean packingProductionEntryBean = new PackingProductionEntryBean();
        PackingProductionEntry packingProductionEntry = packingProductionEntryRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(packingProductionEntry, packingProductionEntryBean);
        List<PackingProgressEntry> packingProgressEntries = packingProgressEntryRepository.findAllByPackingProductionEntryId(id);
        List<PackingProgressEntryBean> packingProgressEntryBeans = new ArrayList<>();
        List<Object[]> operations = productiondemandstepRepository.findAllOperation(Constants.COMPANY_CODE, packingProductionEntry.getProductionordercode());
        Long prevId = 0L;
        for (Object[] operation : operations) {
            boolean exist = false;
            for(PackingProgressEntry packingProgressEntry : packingProgressEntries) {
                if(operation[0].toString().equalsIgnoreCase(packingProgressEntry.getOperationCode())) {
                    exist = true;
                    PackingProgressEntryBean packingProgressEntryBean = new PackingProgressEntryBean();
                    BeanUtils.copyProperties(packingProgressEntry, packingProgressEntryBean);
                    packingProgressEntryBean.setScannedBy("PCS");
                    prevId = packingProgressEntry.getId();
                    List<PackingProgressDetails> packingProgressDetails = packingProgressDetailsRepository.findPackingProgressDetailsByPackingProgressEntryId(packingProgressEntry.getId());
                    List<StitchIssuePackDetailsBean> stitchIssuePackDetails = new ArrayList<>();
                    for (PackingProgressDetails packingProgressDetail : packingProgressDetails) {
                        StitchIssuePackDetailsBean stitchIssuePackDetailsBean = new StitchIssuePackDetailsBean();
                        BeanUtils.copyProperties(packingProgressDetail, stitchIssuePackDetailsBean);
                        stitchIssuePackDetails.add(stitchIssuePackDetailsBean);
                    }
                    packingProgressEntryBean.setStitchIssuePackDetails(stitchIssuePackDetails);
                    packingProgressEntryBeans.add(packingProgressEntryBean);
                }
            }
            if(exist == false) {
                PackingProgressEntryBean packingProgressEntryBean = new PackingProgressEntryBean();
                packingProgressEntryBean.setOperationCode(operation[0].toString());
                packingProgressEntryBean.setOperationDescription(operation[1].toString());
                packingProgressEntryBean.setScannedBy("PCS");
                if (prevId != 0) {
                    List<PackingProgressDetails> packingProgressDetails = packingProgressDetailsRepository.findPackingProgressDetailsByPackingProgressEntryId(prevId);
                    List<StitchIssuePackDetailsBean> stitchIssuePackDetails = new ArrayList<>();
                    for (PackingProgressDetails packingProgressDetail : packingProgressDetails) {
                        StitchIssuePackDetailsBean stitchIssuePackDetailsBean = new StitchIssuePackDetailsBean();
                        BeanUtils.copyProperties(packingProgressDetail, stitchIssuePackDetailsBean);
                        stitchIssuePackDetailsBean.setId(null);
                        stitchIssuePackDetailsBean.setCreatedby(null);
                        stitchIssuePackDetailsBean.setCreateddate(null);
                        stitchIssuePackDetails.add(stitchIssuePackDetailsBean);
                    }
                    packingProgressEntryBean.setStitchIssuePackDetails(stitchIssuePackDetails);
                }
                packingProgressEntryBeans.add(packingProgressEntryBean);
            }
        }
        packingProductionEntryBean.setPackingProgressEntries(packingProgressEntryBeans);
        return ResponseUtil.wrapOrNotFound(Optional.of(packingProductionEntryBean));
    }

    /**
     * {@code GET  /cut-bundle-entries/:id} : get the "id" cutBundleEntry.
     *
     * @param id the id of the cutBundleEntry to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cutBundleEntry, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/packing-production-entries-bundle/{id}")
    public ResponseEntity<List<StitchIssuePackDetailsBean>> getStitchStickByBundle(@PathVariable Long id) {
        log.debug("REST request to get CutBundleEntry : {}", id);
        List<StitchIssuePackDetailsBean> stitchIssuePackDetailsBeans = new ArrayList<>();
        List<PackingLineIssueDetails> packingLineIssueDetails = packingLineIssueDetailsRepository.findPackingLineIssueDetailsByBundleId(id);
        if (packingLineIssueDetails != null) {
            for (PackingLineIssueDetails packingLineIssueDetail : packingLineIssueDetails) {
                List<PackingProgressDetails> packingProgressDetails = packingProgressDetailsRepository.findPackingProgressDetailsByPieceId(packingLineIssueDetail.getCutPlanBundleDetailsId());
                if (packingProgressDetails != null && packingProgressDetails.size()>0) {
                } else {
                    StitchIssuePackDetailsBean stitchIssuePackDetailsBean = new StitchIssuePackDetailsBean();
                    BeanUtils.copyProperties(packingLineIssueDetail, stitchIssuePackDetailsBean);
                    stitchIssuePackDetailsBean.setId(null);
                    stitchIssuePackDetailsBean.setCreatedby(null);
                    stitchIssuePackDetailsBean.setCreateddate(null);
                    stitchIssuePackDetailsBean.setLastupdatedby(null);
                    stitchIssuePackDetailsBean.setLastupdateddate(null);

                    stitchIssuePackDetailsBean.setPackingLineIssueDetailsId(packingLineIssueDetail.getId());
                    stitchIssuePackDetailsBean.setCutPlanBundleDetailsId(packingLineIssueDetail.getCutPlanBundleDetailsId());
                    stitchIssuePackDetailsBean.setCutPlanBundleId(packingLineIssueDetail.getCutPlanBundleId());
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
    @GetMapping("/packing-production-entries-piece/{id}")
    public ResponseEntity<List<StitchIssuePackDetailsBean>> getStitchStickByPiece(@PathVariable Long id) {
        log.debug("REST request to get CutBundleEntry : {}", id);
        List<StitchIssuePackDetailsBean> stitchIssuePackDetailsBeans = new ArrayList<>();
        PackingLineIssueDetails packingLineIssueDetail = packingLineIssueDetailsRepository.findPackingLineIssueDetailsByPostedPieceId(id);
        if (packingLineIssueDetail != null) {
            List<PackingProgressDetails> packingProgressDetails = packingProgressDetailsRepository.findPackingProgressDetailsByPieceId(packingLineIssueDetail.getCutPlanBundleDetailsId());
            if (packingProgressDetails != null && packingProgressDetails.size()>0) {
                return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "Piece Number already used.")).build();
            } else {
                StitchIssuePackDetailsBean stitchIssuePackDetailsBean = new StitchIssuePackDetailsBean();
                BeanUtils.copyProperties(packingLineIssueDetail, stitchIssuePackDetailsBean);
                stitchIssuePackDetailsBean.setId(null);
                stitchIssuePackDetailsBean.setCreatedby(null);
                stitchIssuePackDetailsBean.setCreateddate(null);
                stitchIssuePackDetailsBean.setLastupdatedby(null);
                stitchIssuePackDetailsBean.setLastupdateddate(null);

                stitchIssuePackDetailsBean.setPackingLineIssueDetailsId(packingLineIssueDetail.getId());
                stitchIssuePackDetailsBean.setCutPlanBundleDetailsId(packingLineIssueDetail.getCutPlanBundleDetailsId());
                stitchIssuePackDetailsBean.setCutPlanBundleId(packingLineIssueDetail.getCutPlanBundleId());
                stitchIssuePackDetailsBeans.add(stitchIssuePackDetailsBean);
            }
        } else {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "Invalid Piece Number.")).build();
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(stitchIssuePackDetailsBeans));
    }

    /**
     * {@code DELETE  /packing-production-entries/:id} : delete the "id" packingProductionEntry.
     *
     * @param id the id of the packingProductionEntry to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/packing-progress-details/{id}")
    public ResponseEntity<Void> deletePackingProgressDetails(@PathVariable Long id) {
        log.debug("REST request to delete PackingProgressDetails : {}", id);
        packingProgressDetailsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/packing-production-entries-post/{id}")
    public ResponseEntity<PackingProductionEntryBean> postPackingProductionEntry(@PathVariable Long id) throws URISyntaxException {
        log.debug("REST request to save CutBundleEntry : {}", id);
        PackingProgressEntryBean packingProgressEntryBean = new PackingProgressEntryBean();
        PackingProgressEntry packingProgressEntry = packingProgressEntryRepository.findById(id).orElse(null);
        PackingProductionEntry packingProductionEntry = packingProgressEntry.getPackingProductionEntry();
        BeanUtils.copyProperties(packingProgressEntry, packingProgressEntryBean);
        List<Object[]> packingLineIssueDetails = packingProgressDetailsRepository.findAllObjectByPackingProgressEntryId(id);
        List<StitchIssuePackDetailsBean> packingLineIssueDetailsBeans = new ArrayList<>();
        for (Object[] packingLineIssueDetail : packingLineIssueDetails) {
            List<Productiondemandstep> productiondemandsteps = productiondemandstepRepository.findByProductionorderAndOperationcodeAndColorSize(Constants.COMPANY_CODE, packingProductionEntry.getProductionordercode(), packingProgressEntry.getOperationCode(), packingLineIssueDetail[6].toString(), packingLineIssueDetail[7].toString());
            if (productiondemandsteps != null && productiondemandsteps.size() > 0) {
                Productiondemandstep productiondemandstep = productiondemandsteps.get(0);
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
                productionprogressimport.setProductionordercode(packingProductionEntry.getProductionordercode());
                productionprogressimport.setGroupstepnumber(productiondemandstep.getGroupstepnumber());
                productionprogressimport.setGroupline(0);
                productionprogressimport.setDemandcountercode(productiondemandstep.getId().getProductiondemandcountercode());
                productionprogressimport.setDemandcode(productiondemandstep.getId().getProductiondemandcode());
                productionprogressimport.setElementitemtypeaficode(null);
                productionprogressimport.setElementelementsubcodekey(null);
                productionprogressimport.setElementelementcode(null);
                productionprogressimport.setStepnumber(productiondemandstep.getId().getStepnumber());
                productionprogressimport.setWorkcentercode(productiondemandstep.getWorkcentercode());
                productionprogressimport.setOperationcode(productiondemandstep.getOperationcode());
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
                productionprogressimport.setProgresspartialenddate(new java.sql.Date(Date.from(packingProgressEntry.getEndDate().atZone(ZoneId.of("Asia/Kolkata")).toInstant()).getTime()));
                productionprogressimport.setProgresspartialendtime(new SimpleDateFormat("HH:mm:ss").format(Date.from(packingProgressEntry.getEndDate().atZone(ZoneId.of("Asia/Kolkata")).toInstant()).getTime()));
                productionprogressimport.setProgressenddate(null);
                productionprogressimport.setProgressendtime(null);
                productionprogressimport.setCalshiftdailyinformation(1);
                productionprogressimport.setQueueportionclosed((short) 0);
                productionprogressimport.setPreprocessportionclosed((short) 0);
                productionprogressimport.setProcessportionclosed((short) 0);
                productionprogressimport.setPostprocessportionclosed((short) 0);
                productionprogressimport.setQueuerecordedmachinetime(new BigDecimal("0"));
                productionprogressimport.setPreprocessrecordedmachinetime(new BigDecimal("0"));
                productionprogressimport.setProcessrecordedmachinetime(new BigDecimal(packingProgressEntry.getTotalHour()));
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

                productionprogressimport.setPrimaryqty(new BigDecimal((Double) packingLineIssueDetail[13]));
                productionprogressimport.setPrimaryuomcode(packingLineIssueDetail[11].toString());

                productionprogressimport.setSecondaryqty(new BigDecimal((Double) packingLineIssueDetail[14]));
                productionprogressimport.setSecondaryuomcode(packingLineIssueDetail[12].toString());

                productionprogressimport.setPackagingqty(new BigDecimal("0"));
                productionprogressimport.setPackaginguomcode(null);

                productionprogressimport.setQualityitemtypecompanycode(Constants.COMPANY_CODE);
                productionprogressimport.setQualityitemtypecode("PKG");

                productionprogressimport.setQualitycode(BigDecimal.valueOf(1).toBigInteger());
                productionprogressimport.setQualityreasoncompanycode(Constants.COMPANY_CODE);
                productionprogressimport.setQualityreasoncode(null);
                productionprogressimport.setMachinecode("");
                productionprogressimport.setDyelotweight(new BigDecimal("0"));
                productionprogressimport.setDyelotweightumcode(null);
                productionprogressimport.setCreationdatetime(Timestamp.from(Instant.now().atZone(ZoneId.of("Asia/Kolkata")).toInstant()));
                productionprogressimport.setCreationuser(SecurityUtils.getCurrentUserLogin().orElse(null));
                productionprogressimport.setLastupdatedatetime(null);
                productionprogressimport.setLastupdateuser(null);
                productionprogressimport.setAbsuniqueid(0L);
                Productionprogressimport resultimport = productionprogressimportRepository.save(productionprogressimport);
                if (resultimport != null) {
                    Adstorageimport adstorageimportNoOperator = new Adstorageimport(new AdstorageimportId("ProductionProgress", parentAdditionId, "ProductionProgress", "Noofoperators", "Noofoperators"), 0, 0, 0, 1, null, packingProgressEntry.getNoCutters().intValue(), (short) 0, null, new BigDecimal(0), 0L, null, null, 0L);
                    adstorageimportRepository.save(adstorageimportNoOperator);

                    Adstorageimport adstorageimportDestination = new Adstorageimport(new AdstorageimportId("ProductionProgress", parentAdditionId, "ProductionProgress", "Destination", "Destination"), 0, 0, 0, 0, packingProductionEntry.getDestination(), 0, (short) 0, null, new BigDecimal(0), 0L, null, null, 0L);
                    adstorageimportRepository.save(adstorageimportDestination);

                    resultimport.setImportstatus(0);
                    resultimport = productionprogressimportRepository.save(resultimport);
                    if(resultimport != null && packingProgressEntry.getLastProgress() != null && packingProgressEntry.getLastProgress().equalsIgnoreCase("Y")) {
                        Productiondemand productiondemand = productiondemandRepository.findByDemandCode(Constants.COMPANY_CODE, productiondemandstep.getId().getProductiondemandcode());
                        if (productiondemand != null) {
                            Long stockTransactionDestinationId = stocktransactionimportRepository.getNextSequence();
                            int linNo = 0;
                            Long parentAdditionStockId = adstorageimportRepository.getNextSequence();
                            Stocktransactionimport stocktransactionimport = to(Constants.COMPANY_CODE, productiondemand, stockTransactionDestinationId, ++linNo, "M01", "0", new BigDecimal((Double) packingLineIssueDetail[13]), packingProductionEntry.getCountercode(), productiondemandstep.getProductionordercode(), "0", 1, "", "", parentAdditionStockId);
                            Stocktransactionimport stockResult = stocktransactionimportRepository.save(stocktransactionimport);
                            if (stockResult != null) {
                                Adstorageimport adstorageimportDestination2 = new Adstorageimport(new AdstorageimportId("StockTransaction", parentAdditionStockId, "StockTransaction", "Destination", "Destination"), 0, 0, 0, 0, packingProductionEntry.getDestination(), 0, (short) 0, null, new BigDecimal(0), 0L, null, null, 0L);
                                adstorageimportRepository.save(adstorageimportDestination2);
                                stockResult.setImportstatus(1);
                                stockResult = stocktransactionimportRepository.save(stockResult);
                            }
                        }
                    }
                }
            }
        }
        packingProgressEntry.setProgressPostedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        packingProgressEntry.setProgressPostedDate(Instant.now());
        packingProgressEntryRepository.save(packingProgressEntry);
        if (packingProgressEntry != null && packingProgressEntry.getLastProgress() != null && packingProgressEntry.getLastProgress().equalsIgnoreCase("Y")) {
            packingProductionEntry.setProgressPostedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
            packingProductionEntry.setProgressPostedDate(Instant.now());
            packingProductionEntryRepository.save(packingProductionEntry);
        }
        return getPackingProductionEntry(packingProductionEntry.getId());
    }

    public static Stocktransactionimport to(String companycode, Productiondemand productiondemand, Long stockTransactionId, Integer lineNo, String templatecode, String detailType, BigDecimal issuedquantity, String productionordercountercode, String productionordercode, String groupline, Integer qualitylevelcode, String zone, String location, Long parentAdditionId) {
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        Stocktransactionimport stocktransactionimport = new Stocktransactionimport();
        stocktransactionimport.setId(new StocktransactionimportId(Constants.COMPANY_CODE, stockTransactionId.toString(), lineNo));
        stocktransactionimport.setImportstatus(0);
        stocktransactionimport.setNowtrnnumbertransactionnumber("");
        stocktransactionimport.setTransactionstatus("0");
        stocktransactionimport.setItemtypecompanycode(companycode);
        stocktransactionimport.setItemtypecode(productiondemand.getItemtypeaficode());
        stocktransactionimport.setTransactiondate(new java.sql.Date(System.currentTimeMillis()));
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
        stocktransactionimport.setLogicalwarehousecode(productiondemand.getEntrywarehousecode());
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
        stocktransactionimport.setPhysicalwarehousecode(productiondemand.getEntrylocwhszonephywhscode());
        stocktransactionimport.setWhslocwhszonephywhscmycode(companycode);
        stocktransactionimport.setWhslocationwarehousezonecode(zone);
        stocktransactionimport.setWarehouselocationcode(location);
        stocktransactionimport.setContainercompanycode(companycode);
        stocktransactionimport.setContaineritemtypecode("");
        stocktransactionimport.setContainersubcode01("");
        stocktransactionimport.setContainerelementcompanycode("");
        stocktransactionimport.setContainerelementcode("");
        stocktransactionimport.setLotcompanycode(companycode);
        stocktransactionimport.setLotcode(null);
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
}
