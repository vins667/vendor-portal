package io.vamani.application.web.rest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import javax.validation.Valid;
import com.codahale.metrics.annotation.Timed;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.domain.*;
import io.vamani.application.mobile.VcutOperationIssueMasterBean;
import io.vamani.application.mobile.VcutOperationMasterBean;
import io.vamani.application.mobile.VcutStylePlanUploadBean;
import io.vamani.application.mobile.VcutStylePlanUploadMaster;
import io.vamani.application.model.*;
import io.vamani.application.model.VcutMainEntryIssueDetails;
import io.vamani.application.repository.*;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.util.DateUtils;
import io.vamani.application.util.MD5UrlEncryption;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.domain.VcutPlanChangeMaster;
import io.vamani.application.domain.VcutStylePlanUpload;
import io.vamani.application.mobile.VcutStylePlanUploadBean;
import io.vamani.application.model.Master;
import io.vamani.application.model.Message;
import io.vamani.application.repository.VcutStylePlanUploadRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link VcutStylePlanUpload}.
 */
@RestController
@RequestMapping("/api")
public class VcutStylePlanUploadResource {

    private final Logger log = LoggerFactory.getLogger(VcutStylePlanUploadResource.class);

    private static final String ENTITY_NAME = "vcutStylePlanUpload";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VcutStylePlanUploadRepository vcutStylePlanUploadRepository;

    @Autowired
    private VcutOperationIssueMasterRepository vcutOperationIssueMasterRepository;

    @Autowired
    private VcutOperationMasterRepository vcutOperationMasterRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private VcutMainEntryMasterRepository vcutMainEntryMasterRepository;

    @Autowired
    private VcutSessionMasterRepository vcutSessionMasterRepository;

    @Autowired
    private VcutMainEntryAllowRepository vcutMainEntryAllowRepository;

    @Autowired
    private VcutStyleImageRepository vcutStyleImageRepository;

    public VcutStylePlanUploadResource(VcutStylePlanUploadRepository vcutStylePlanUploadRepository) {
        this.vcutStylePlanUploadRepository = vcutStylePlanUploadRepository;
    }

    /**
     * {@code POST  /vcut-style-plan-uploads} : Create a new vcutStylePlanUpload.
     *
     * @param vcutStylePlanUpload the vcutStylePlanUpload to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vcutStylePlanUpload, or with status {@code 400 (Bad Request)} if the vcutStylePlanUpload has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/vcut-style-plan-uploads")
    public ResponseEntity<VcutStylePlanUpload> createVcutStylePlanUpload(@Valid @RequestBody VcutStylePlanUpload vcutStylePlanUpload) throws URISyntaxException {
        log.debug("REST request to save VcutStylePlanUpload : {}", vcutStylePlanUpload);
        if (vcutStylePlanUpload.getId() != null) {
            throw new BadRequestAlertException("A new vcutStylePlanUpload cannot already have an ID", ENTITY_NAME, "idexists");
        }
        vcutStylePlanUpload.setFactory(vcutStylePlanUpload.getPlantCode());
        vcutStylePlanUpload.setCreateBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        vcutStylePlanUpload.setCreatedDate(Instant.now());
        VcutStylePlanUpload result = vcutStylePlanUploadRepository.save(vcutStylePlanUpload);
        return ResponseEntity.created(new URI("/api/vcut-style-plan-uploads/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /vcut-style-plan-uploads} : Updates an existing vcutStylePlanUpload.
     *
     * @param vcutStylePlanUpload the vcutStylePlanUpload to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vcutStylePlanUpload,
     * or with status {@code 400 (Bad Request)} if the vcutStylePlanUpload is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vcutStylePlanUpload couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/vcut-style-plan-uploads")
    public ResponseEntity<VcutStylePlanUpload> updateVcutStylePlanUpload(@Valid @RequestBody VcutStylePlanUpload vcutStylePlanUpload) throws URISyntaxException {
        log.debug("REST request to update VcutStylePlanUpload : {}", vcutStylePlanUpload);
        if (vcutStylePlanUpload.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        vcutStylePlanUpload.setFactory(vcutStylePlanUpload.getPlantCode());
        VcutStylePlanUpload result = vcutStylePlanUploadRepository.save(vcutStylePlanUpload);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, vcutStylePlanUpload.getId().toString()))
            .body(result);
    }

    @PostMapping("/vcut-style-plan-uploads-qry")
    @Timed
    public ResponseEntity<List<VcutStylePlanUpload>> getAllVcutStylePlans(@Valid @RequestBody VcutStylePlanUploadSearch search)  {
        log.debug("REST request to get a page of VcutStylePlanUploads");
        String style = "%";
        String poNo = "%";
        if (search.getStyle() != null) {
        	style = search.getStyle() + "%";
        }
        if (search.getPoNo() != null) {
        	poNo = search.getPoNo() + "%";
        }
         Page<VcutStylePlanUpload> page = null;
         search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("planDate").descending()));
         page = vcutStylePlanUploadRepository.findByPlanDateAndStyleNoAndPoNo(search.getPlanDate(),search.getPlanDateTo(),style,poNo,search.getPage());
         HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /vcut-style-plan-uploads} : get all the vcutStylePlanUploads.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vcutStylePlanUploads in body.
     */
    @GetMapping("/vcut-style-plan-uploads")
    public ResponseEntity<List<VcutStylePlanUpload>> getAllVcutStylePlanUploads(Pageable pageable) {
        log.debug("REST request to get a page of VcutStylePlanUploads");
        Page<VcutStylePlanUpload> page = vcutStylePlanUploadRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
        * {@code GET  /vcut-style-plan-uploads} : get all the vcutStylePlanUploads.
        *

        * @param @pageable the pagination information.

        * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vcutStylePlanUploads in body.
     */
    @PostMapping("/vcut-style-plan-uploads-sync")
    public ResponseEntity<VcutStylePlanUploadBean> getAllVcutStylePlanUploads(@RequestBody Master master) throws NoSuchAlgorithmException, ParseException {
        log.debug("REST request to get a page of VcutStylePlanUploads");
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        LocalDate localDate = LocalDate.of(Integer.parseInt(format.format(now)), now.getMonth() + 1, now.getDate());
        List<VcutStylePlanUpload> vcutStylePlanUploads = vcutStylePlanUploadRepository.findByPlanDateAndLineNo(localDate, master.getDesc());
        List<VcutOperationIssueMaster> vcutOperationIssueMastersList = vcutOperationIssueMasterRepository.findAll();
        Map<Long, VcutOperationIssueMaster> operationIssueMasterMap = new HashMap<>();
        if(vcutStylePlanUploads != null && vcutStylePlanUploads.size()>0) {
            VcutStylePlanUploadBean vcutStylePlanUploadBean = new VcutStylePlanUploadBean();
            List<VcutStylePlanUploadMaster> vcutStylePlanUploadMasters = new ArrayList<>();
            for(VcutStylePlanUpload vcutStylePlanUpload : vcutStylePlanUploads) {
                VcutStylePlanUploadMaster vcutStylePlanUploadMaster = new VcutStylePlanUploadMaster();
                BeanUtils.copyProperties(vcutStylePlanUpload, vcutStylePlanUploadMaster);
                VcutStyleImage vcutStyleImage = vcutStyleImageRepository.findByStyle(vcutStylePlanUpload.getStyle());
                if(vcutStyleImage != null) {
                    vcutStylePlanUploadMaster.setImageBack(MD5UrlEncryption.fakeUploadUrl("style-sketch/" + vcutStyleImage.getBackImage()));
                    vcutStylePlanUploadMaster.setImageFront(MD5UrlEncryption.fakeUploadUrl("style-sketch/" + vcutStyleImage.getFrontImage()));
                }
                List<VcutOperationMaster> vcutOperationMasters = vcutOperationMasterRepository.findByStyle(vcutStylePlanUpload.getStyle());
                List<VcutOperationMasterBean> vcutOperationMasterBeans = new ArrayList<>();
                for (VcutOperationMaster vcutOperationMaster : vcutOperationMasters) {
                    VcutOperationMasterBean vcutOperationMasterBean = new VcutOperationMasterBean();
                    BeanUtils.copyProperties(vcutOperationMaster, vcutOperationMasterBean);
                    vcutOperationMasterBeans.add(vcutOperationMasterBean);
                }
                vcutStylePlanUploadMaster.setVcutOperationMasters(vcutOperationMasterBeans);

                List<VcutMainEntryMasterBean> vcutMainEntryMasterBeans = new ArrayList<>();

                int ftt = 0;

                int alter = 0;

                int reject = 0;

                int rectified = 0;

                int done = 0;

                List<VcutMainEntryMaster> vcutMainEntryMasters  = vcutMainEntryMasterRepository.findByPlanId(vcutStylePlanUpload.getId());
                SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                for(VcutMainEntryMaster entryMaster : vcutMainEntryMasters) {
                    VcutMainEntryMasterBean masterBean = new VcutMainEntryMasterBean();
                    masterBean.setId(entryMaster.getId());
                    masterBean.setEntryType(entryMaster.getEntryType());
                    if(entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("FTT")) {
                        ++ftt;
                        ++done;
                    } else if(entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("REJ")) {
                        ++reject;
                    }  else if(entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("ALT")) {
                        if(entryMaster.getRectifiedDate() != null) {
                            ++rectified;
                            ++done;
                        } else {
                            ++alter;
                        }
                    }
                    masterBean.setEntryTime(format1.format(Date.from(entryMaster.getEntryTime())));
                    masterBean.setEntryBy(entryMaster.getEntryBy());
                    if(entryMaster.getRectifiedDate() != null) {
                        masterBean.setRectifiedDate(format1.format(Date.from(entryMaster.getRectifiedDate())));
                        masterBean.setRectifiedBy(entryMaster.getRectifiedBy());
                    }
                    masterBean.setPlanId(entryMaster.getVcutStylePlanUpload().getId());
                    if (entryMaster.getVcutOperationMaster() != null) {
                        masterBean.setOperationId(entryMaster.getVcutOperationMaster().getId());
                        masterBean.setOperationDescription(entryMaster.getVcutOperationMaster().getDescription());
                        masterBean.setOperationDescriptionLocal(entryMaster.getVcutOperationMaster().getDescriptionLocal());
                    }
                    if (entryMaster.getVcutMainEntryIssueDetails() != null && entryMaster.getVcutMainEntryIssueDetails().size() > 0) {
                        List<VcutMainEntryIssueDetails> issueDetails = new ArrayList<>();
                        Map<Long, List<io.vamani.application.domain.VcutMainEntryIssueDetails>> listMap = new HashMap<>();
                        for(io.vamani.application.domain.VcutMainEntryIssueDetails details : entryMaster.getVcutMainEntryIssueDetails()) {
                            if(listMap.containsKey(details.getVcutOperationIssueMasterId())) {
                                List<io.vamani.application.domain.VcutMainEntryIssueDetails> vcutMainEntryIssueDetails = listMap.get(details.getVcutOperationIssueMasterId());
                                vcutMainEntryIssueDetails.add(details);
                                listMap.put(details.getVcutOperationIssueMasterId(), vcutMainEntryIssueDetails);
                            } else {
                                List<io.vamani.application.domain.VcutMainEntryIssueDetails> vcutMainEntryIssueDetails = new ArrayList<>();
                                vcutMainEntryIssueDetails.add(details);
                                listMap.put(details.getVcutOperationIssueMasterId(), vcutMainEntryIssueDetails);
                            }
                        }

                        for(Long key : listMap.keySet()) {
                            List<io.vamani.application.domain.VcutMainEntryIssueDetails> vcutMainEntryIssueDetails = listMap.get(key);
                            VcutMainEntryIssueDetails entryIssueDetails = new VcutMainEntryIssueDetails();
                            entryIssueDetails.setIssueId(key);
                            if(vcutMainEntryIssueDetails != null && vcutMainEntryIssueDetails.size()>0) {
                                if(operationIssueMasterMap.containsKey(key)) {
                                    VcutOperationIssueMaster vcutOperationIssueMaster = operationIssueMasterMap.get(key);
                                    entryIssueDetails.setIssueDescription(vcutOperationIssueMaster.getDescription());
                                    entryIssueDetails.setIssueDescriptionLocal(vcutOperationIssueMaster.getDescriptionLocal());
                                } else {
                                    VcutOperationIssueMaster vcutOperationIssueMaster = vcutOperationIssueMasterRepository.findById(key).orElse(null);
                                    entryIssueDetails.setIssueDescription(vcutOperationIssueMaster.getDescription());
                                    entryIssueDetails.setIssueDescriptionLocal(vcutOperationIssueMaster.getDescriptionLocal());
                                    operationIssueMasterMap.put(key, vcutOperationIssueMaster);
                                }
                                List<VcutMainEntryCoordinateDetails> coordinateDetails = new ArrayList<>();
                                for (io.vamani.application.domain.VcutMainEntryIssueDetails details : vcutMainEntryIssueDetails) {
                                    if(details.getCoordinateX() != null && details.getCoordinateY() != null) {
                                        VcutMainEntryCoordinateDetails entryCoordinateDetails = new VcutMainEntryCoordinateDetails();
                                        entryCoordinateDetails.setCoordinateX(details.getCoordinateX());
                                        entryCoordinateDetails.setCoordinateY(details.getCoordinateY());
                                        entryCoordinateDetails.setCoordinateType(details.getCoordinateType());
                                        coordinateDetails.add(entryCoordinateDetails);
                                    }
                                }
                                entryIssueDetails.setCoordinateDetails(coordinateDetails);
                            }
                            issueDetails.add(entryIssueDetails);
                        }
                        masterBean.setIssueDetails(issueDetails);
                    }
                    vcutMainEntryMasterBeans.add(masterBean);
                }
                vcutStylePlanUploadMaster.setVcutMainEntryMasterBeans(vcutMainEntryMasterBeans);
                vcutStylePlanUploadMaster.setAlter(alter);
                vcutStylePlanUploadMaster.setFtt(ftt);
                vcutStylePlanUploadMaster.setRectified(rectified);
                vcutStylePlanUploadMaster.setReject(reject);
                vcutStylePlanUploadMaster.setDone(done);

                vcutStylePlanUploadMasters.add(vcutStylePlanUploadMaster);
            }
            vcutStylePlanUploadBean.setVcutStylePlanUploads(vcutStylePlanUploadMasters);
            List<VcutOperationIssueMasterBean> operationIssueMasters = new ArrayList<>();
            for(VcutOperationIssueMaster vcutOperationIssueMaster : vcutOperationIssueMastersList) {
                VcutOperationIssueMasterBean bean = new VcutOperationIssueMasterBean();
                BeanUtils.copyProperties(vcutOperationIssueMaster, bean);
                operationIssueMasters.add(bean);
            }
            vcutStylePlanUploadBean.setOperationIssueMasters(operationIssueMasters);
            vcutStylePlanUploadBean.setVcutSessionMasters(vcutSessionMasterRepository.findAll());
            VcutMainEntryAllow vcutMainEntryAllow = vcutMainEntryAllowRepository.findByMaxValue();
            if(vcutMainEntryAllow != null) {
                vcutStylePlanUploadBean.setMinuteUpdate(vcutMainEntryAllow.getMinuteUpdate());
            }
            return ResponseEntity.ok().body(vcutStylePlanUploadBean);
        } else {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "No Plan found")).build();
        }
    }

    /**
     * {@code GET  /vcut-style-plan-uploads} : get all the vcutStylePlanUploads.
     *

     * @param @pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vcutStylePlanUploads in body.
     */
    @PostMapping("/vcut-style-plan-uploads-sync-by-date")
    public ResponseEntity<VcutStylePlanUploadBean> getAllVcutStylePlanUploadsByDate(@RequestBody Master master) throws NoSuchAlgorithmException, ParseException {
        log.debug("REST request to get a page of VcutStylePlanUploads");
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = format.parse(master.getId());
        LocalDate localDate = DateUtils.asLocalDate(date);
        Map<Long, VcutOperationIssueMaster> operationIssueMasterMap = new HashMap<>();
        List<VcutStylePlanUpload> vcutStylePlanUploads = vcutStylePlanUploadRepository.findByPlanDateAndLineNo(localDate, master.getDesc());
        List<VcutOperationIssueMaster> vcutOperationIssueMastersList = vcutOperationIssueMasterRepository.findAll();
        if(vcutStylePlanUploads != null && vcutStylePlanUploads.size()>0) {
            VcutStylePlanUploadBean vcutStylePlanUploadBean = new VcutStylePlanUploadBean();
            List<VcutStylePlanUploadMaster> vcutStylePlanUploadMasters = new ArrayList<>();
            for(VcutStylePlanUpload vcutStylePlanUpload : vcutStylePlanUploads) {
                VcutStylePlanUploadMaster vcutStylePlanUploadMaster = new VcutStylePlanUploadMaster();
                BeanUtils.copyProperties(vcutStylePlanUpload, vcutStylePlanUploadMaster);
                VcutStyleImage vcutStyleImage = vcutStyleImageRepository.findByStyle(vcutStylePlanUpload.getStyle());
                if(vcutStyleImage != null) {
                    vcutStylePlanUploadMaster.setImageBack(MD5UrlEncryption.fakeUploadUrl("style-sketch/" + vcutStyleImage.getBackImage()));
                    vcutStylePlanUploadMaster.setImageFront(MD5UrlEncryption.fakeUploadUrl("style-sketch/" + vcutStyleImage.getFrontImage()));
                }
                List<VcutOperationMaster> vcutOperationMasters = vcutOperationMasterRepository.findByStyle(vcutStylePlanUpload.getStyle());
                List<VcutOperationMasterBean> vcutOperationMasterBeans = new ArrayList<>();
                for (VcutOperationMaster vcutOperationMaster : vcutOperationMasters) {
                    VcutOperationMasterBean vcutOperationMasterBean = new VcutOperationMasterBean();
                    BeanUtils.copyProperties(vcutOperationMaster, vcutOperationMasterBean);
                    vcutOperationMasterBeans.add(vcutOperationMasterBean);
                }
                vcutStylePlanUploadMaster.setVcutOperationMasters(vcutOperationMasterBeans);

                List<VcutMainEntryMasterBean> vcutMainEntryMasterBeans = new ArrayList<>();

                int ftt = 0;

                int alter = 0;

                int reject = 0;

                int rectified = 0;

                int done = 0;

                List<VcutMainEntryMaster> vcutMainEntryMasters  = vcutMainEntryMasterRepository.findByPlanId(vcutStylePlanUpload.getId());
                SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                for(VcutMainEntryMaster entryMaster : vcutMainEntryMasters) {
                    VcutMainEntryMasterBean masterBean = new VcutMainEntryMasterBean();
                    masterBean.setId(entryMaster.getId());
                    masterBean.setEntryType(entryMaster.getEntryType());
                    if(entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("FTT")) {
                        ++ftt;
                        ++done;
                    } else if(entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("REJ")) {
                        ++reject;
                    }  else if(entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("ALT")) {
                        if(entryMaster.getRectifiedDate() != null) {
                            ++rectified;
                            ++done;
                        } else {
                            ++alter;
                        }
                    }
                    masterBean.setEntryTime(format1.format(Date.from(entryMaster.getEntryTime())));
                    masterBean.setEntryBy(entryMaster.getEntryBy());
                    if(entryMaster.getRectifiedDate() != null) {
                        masterBean.setRectifiedDate(format1.format(Date.from(entryMaster.getRectifiedDate())));
                        masterBean.setRectifiedBy(entryMaster.getRectifiedBy());
                    }
                    masterBean.setPlanId(entryMaster.getVcutStylePlanUpload().getId());
                    if (entryMaster.getVcutOperationMaster() != null) {
                        masterBean.setOperationId(entryMaster.getVcutOperationMaster().getId());
                        masterBean.setOperationDescription(entryMaster.getVcutOperationMaster().getDescription());
                        masterBean.setOperationDescriptionLocal(entryMaster.getVcutOperationMaster().getDescriptionLocal());
                    }
                    if (entryMaster.getVcutMainEntryIssueDetails() != null && entryMaster.getVcutMainEntryIssueDetails().size() > 0) {
                        List<VcutMainEntryIssueDetails> issueDetails = new ArrayList<>();
                        Map<Long, List<io.vamani.application.domain.VcutMainEntryIssueDetails>> listMap = new HashMap<>();
                        for(io.vamani.application.domain.VcutMainEntryIssueDetails details : entryMaster.getVcutMainEntryIssueDetails()) {
                            if(listMap.containsKey(details.getVcutOperationIssueMasterId())) {
                                List<io.vamani.application.domain.VcutMainEntryIssueDetails> vcutMainEntryIssueDetails = listMap.get(details.getVcutOperationIssueMasterId());
                                vcutMainEntryIssueDetails.add(details);
                                listMap.put(details.getVcutOperationIssueMasterId(), vcutMainEntryIssueDetails);
                            } else {
                                List<io.vamani.application.domain.VcutMainEntryIssueDetails> vcutMainEntryIssueDetails = new ArrayList<>();
                                vcutMainEntryIssueDetails.add(details);
                                listMap.put(details.getVcutOperationIssueMasterId(), vcutMainEntryIssueDetails);
                            }
                        }

                        for(Long key : listMap.keySet()) {
                            List<io.vamani.application.domain.VcutMainEntryIssueDetails> vcutMainEntryIssueDetails = listMap.get(key);
                            VcutMainEntryIssueDetails entryIssueDetails = new VcutMainEntryIssueDetails();
                            entryIssueDetails.setIssueId(key);
                            if(vcutMainEntryIssueDetails != null && vcutMainEntryIssueDetails.size()>0) {
                                if(operationIssueMasterMap.containsKey(key)) {
                                    VcutOperationIssueMaster vcutOperationIssueMaster = operationIssueMasterMap.get(key);
                                    entryIssueDetails.setIssueDescription(vcutOperationIssueMaster.getDescription());
                                    entryIssueDetails.setIssueDescriptionLocal(vcutOperationIssueMaster.getDescriptionLocal());
                                } else {
                                    VcutOperationIssueMaster vcutOperationIssueMaster = vcutOperationIssueMasterRepository.findById(key).orElse(null);
                                    entryIssueDetails.setIssueDescription(vcutOperationIssueMaster.getDescription());
                                    entryIssueDetails.setIssueDescriptionLocal(vcutOperationIssueMaster.getDescriptionLocal());
                                    operationIssueMasterMap.put(key, vcutOperationIssueMaster);
                                }
                                List<VcutMainEntryCoordinateDetails> coordinateDetails = new ArrayList<>();
                                for (io.vamani.application.domain.VcutMainEntryIssueDetails details : vcutMainEntryIssueDetails) {
                                    if(details.getCoordinateX() != null && details.getCoordinateY() != null) {
                                        VcutMainEntryCoordinateDetails entryCoordinateDetails = new VcutMainEntryCoordinateDetails();
                                        entryCoordinateDetails.setCoordinateX(details.getCoordinateX());
                                        entryCoordinateDetails.setCoordinateY(details.getCoordinateY());
                                        entryCoordinateDetails.setCoordinateType(details.getCoordinateType());
                                        coordinateDetails.add(entryCoordinateDetails);
                                    }
                                }
                                entryIssueDetails.setCoordinateDetails(coordinateDetails);
                            }
                            issueDetails.add(entryIssueDetails);
                        }
                        masterBean.setIssueDetails(issueDetails);
                    }
                    vcutMainEntryMasterBeans.add(masterBean);
                }
                vcutStylePlanUploadMaster.setVcutMainEntryMasterBeans(vcutMainEntryMasterBeans);
                vcutStylePlanUploadMaster.setAlter(alter);
                vcutStylePlanUploadMaster.setFtt(ftt);
                vcutStylePlanUploadMaster.setRectified(rectified);
                vcutStylePlanUploadMaster.setReject(reject);
                vcutStylePlanUploadMaster.setDone(done);

                vcutStylePlanUploadMasters.add(vcutStylePlanUploadMaster);
            }
            vcutStylePlanUploadBean.setVcutStylePlanUploads(vcutStylePlanUploadMasters);
            List<VcutOperationIssueMasterBean> operationIssueMasters = new ArrayList<>();
            for(VcutOperationIssueMaster vcutOperationIssueMaster : vcutOperationIssueMastersList) {
                VcutOperationIssueMasterBean bean = new VcutOperationIssueMasterBean();
                BeanUtils.copyProperties(vcutOperationIssueMaster, bean);
                operationIssueMasters.add(bean);
            }
            vcutStylePlanUploadBean.setOperationIssueMasters(operationIssueMasters);
            vcutStylePlanUploadBean.setVcutSessionMasters(vcutSessionMasterRepository.findAll());
            VcutMainEntryAllow vcutMainEntryAllow = vcutMainEntryAllowRepository.findByMaxValue();
            if(vcutMainEntryAllow != null) {
                vcutStylePlanUploadBean.setMinuteUpdate(vcutMainEntryAllow.getMinuteUpdate());
            }
            return ResponseEntity.ok().body(vcutStylePlanUploadBean);
        } else {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "No Plan found")).build();
        }
    }

    /**
     * {@code GET  /vcut-style-plan-uploads/:id} : get the "id" vcutStylePlanUpload.
     *
     * @param id the id of the vcutStylePlanUpload to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vcutStylePlanUpload, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/vcut-style-plan-uploads/{id}")
    public ResponseEntity<VcutStylePlanUpload> getVcutStylePlanUpload(@PathVariable Long id) {
        log.debug("REST request to get VcutStylePlanUpload : {}", id);
        Optional<VcutStylePlanUpload> vcutStylePlanUpload = vcutStylePlanUploadRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(vcutStylePlanUpload);
    }

    /**
     * {@code DELETE  /vcut-style-plan-uploads/:id} : delete the "id" vcutStylePlanUpload.
     *
     * @param id the id of the vcutStylePlanUpload to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/vcut-style-plan-uploads/{id}")
    public ResponseEntity<Void> deleteVcutStylePlanUpload(@PathVariable Long id) {
        log.debug("REST request to delete VcutStylePlanUpload : {}", id);
        vcutStylePlanUploadRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * POST  /asset-file-upload-masters : Create a new assetFileUploadMaster.
     *
     * @param !assetFileUploadMaster the assetFileUploadMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new assetFileUploadMaster, or with status 400 (Bad Request) if the assetFileUploadMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping(value = "/vcut-style-plan-uploads-excel", consumes = {"multipart/form-data"})
    @Timed
    public ResponseEntity<Message> createAssetFileUploadMaster(@RequestParam(required = false) MultipartFile[] file) throws IOException, ParseException {
        String errorLog = "";
        log.debug("REST request to save StyleExcelUpload : {}");
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        try {
            if (file != null && file.length > 0) {
                for (MultipartFile multipartFile : file) {
                    byte[] bytes = multipartFile.getBytes();
                    Path path = Paths.get(UPLOADED_FOLDER + "styles/" + multipartFile.getOriginalFilename());
                    Files.write(path, bytes);

                    String fileName = multipartFile.getOriginalFilename();

                    File readFile = new File(UPLOADED_FOLDER + "styles/" + fileName);

                    FileInputStream inputStream = new FileInputStream(readFile);

                    Workbook workbook = null;

                    //Find the file extension by splitting file name in substring  and getting only extension name

                    String fileExtensionName = fileName.substring(fileName.indexOf("."));

                    //Check condition if the file is xlsx file

                    if (fileExtensionName.equals(".xlsx")) {
                        //If it is xlsx file then create object of XSSFWorkbook class
                        workbook = new XSSFWorkbook(inputStream);
                    } else {
                        workbook = new HSSFWorkbook(inputStream);
                    }
                    //Read sheet inside the workbook by its name

                    Sheet sheet = workbook.getSheetAt(0);

                    //Find number of rows in excel file

                    int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
                    for (int i = 4; i < rowCount + 1; i++) {
                        Row row = sheet.getRow(i);
                        if(row.getCell(0) != null && row.getCell(0).getStringCellValue() != null && row.getCell(0).getStringCellValue().length()>0) {
                            VcutStylePlanUpload vcutStylePlanUpload = new VcutStylePlanUpload();

                            if (row.getCell(0) != null) {
                                row.getCell(0).setCellType(CellType.STRING);
                                vcutStylePlanUpload.setFactory(row.getCell(0).getStringCellValue().trim());
                            }

                            if (row.getCell(1) != null) {
                                row.getCell(1).setCellType(CellType.STRING);
                                vcutStylePlanUpload.setFloor(row.getCell(1).getStringCellValue().trim());
                            }

                            if (row.getCell(2) != null) {
                                row.getCell(2).setCellType(CellType.STRING);
                                vcutStylePlanUpload.setLineNo(row.getCell(2).getStringCellValue().trim());
                            }

                            if (row.getCell(3) != null) {
                                row.getCell(3).setCellType(CellType.STRING);
                                vcutStylePlanUpload.setBuyerName(row.getCell(3).getStringCellValue().trim());
                            }

                            if (row.getCell(4) != null) {
                                row.getCell(4).setCellType(CellType.STRING);
                                vcutStylePlanUpload.setPoNo(row.getCell(4).getStringCellValue().trim());
                            }

                            if (row.getCell(5) != null) {
                                row.getCell(5).setCellType(CellType.STRING);
                                vcutStylePlanUpload.setStyle(row.getCell(5).getStringCellValue().trim());
                            }

                            if (row.getCell(6) != null) {
                                row.getCell(6).setCellType(CellType.NUMERIC);
                                vcutStylePlanUpload.setDays(new Double(row.getCell(6).getNumericCellValue()).intValue());
                            }

                            if (row.getCell(7) != null) {
                                row.getCell(7).setCellType(CellType.STRING);
                                vcutStylePlanUpload.setItemName(row.getCell(7).getStringCellValue().trim());
                            }

                            if (row.getCell(8) != null) {
                                row.getCell(8).setCellType(CellType.NUMERIC);
                                vcutStylePlanUpload.setSmv(row.getCell(8).getNumericCellValue());
                            }

                            if (row.getCell(9) != null) {
                                row.getCell(9).setCellType(CellType.STRING);
                                vcutStylePlanUpload.setColorName(row.getCell(9).getStringCellValue().trim());
                            }

                            if (row.getCell(10) != null) {
                                row.getCell(10).setCellType(CellType.NUMERIC);
                                vcutStylePlanUpload.setOperators(new Double(row.getCell(10).getNumericCellValue()).intValue());
                            }

                            if (row.getCell(11) != null) {
                                row.getCell(11).setCellType(CellType.NUMERIC);
                                vcutStylePlanUpload.setHelpers(new Double(row.getCell(11).getNumericCellValue()).intValue());
                            }

                            if (row.getCell(12) != null) {
                                row.getCell(12).setCellType(CellType.NUMERIC);
                                vcutStylePlanUpload.setWorkingHours(new Double(row.getCell(12).getNumericCellValue()).intValue());
                            }

                            if (row.getCell(13) != null) {
                                row.getCell(13).setCellType(CellType.NUMERIC);
                                vcutStylePlanUpload.setQuantity(new Double(row.getCell(13).getNumericCellValue()).intValue());
                            }

                            if (row.getCell(14) != null) {
                                Date plannedDate = row.getCell(14).getDateCellValue();
                                vcutStylePlanUpload.setPlanDate(plannedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                            }

                            if (row.getCell(15) != null) {
                                row.getCell(15).setCellType(CellType.NUMERIC);
                                if (row.getCell(15).getCellStyle().getDataFormatString().contains("%")) {
                                    // Detect Percent Values
                                    Double value = row.getCell(15).getNumericCellValue() * 100;
                                    vcutStylePlanUpload.setKickOff(value);
                                }
                            }
                            Date now = new Date();
                            SimpleDateFormat format = new SimpleDateFormat("yyyy");
                            LocalDate localDate = LocalDate.of(Integer.parseInt(format.format(now)), now.getMonth() + 1, now.getDate());
                            if (vcutStylePlanUpload.getPlanDate().isBefore(localDate)) {
                                if (errorLog.length() > 0) {
                                    errorLog += ", Row# " + (i + 1) + " past date";
                                } else {
                                    errorLog += "Row# " + (i + 1) + " past date";
                                }
                            } else {
                                VcutStylePlanUpload stylePlanUpload = vcutStylePlanUploadRepository.findByPlanDateAndStyleNo(vcutStylePlanUpload.getPlanDate(), vcutStylePlanUpload.getStyle().toUpperCase(), vcutStylePlanUpload.getLineNo());
                                if (stylePlanUpload != null) {
                                    vcutStylePlanUpload.setId(stylePlanUpload.getId());
                                    vcutStylePlanUpload.setVcutSessionMasterId(stylePlanUpload.getVcutSessionMasterId());
                                    vcutStylePlanUpload.setActivePlan(stylePlanUpload.getActivePlan());
                                    vcutStylePlanUpload.setVcutPlanChangeMaster(new VcutPlanChangeMaster(1L));
                                    vcutStylePlanUpload.setCreateBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                                    vcutStylePlanUpload.setCreatedDate(Instant.now());
                                } else {
                                    vcutStylePlanUpload.setVcutPlanChangeMaster(new VcutPlanChangeMaster(1L));
                                    vcutStylePlanUpload.setCreateBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                                    vcutStylePlanUpload.setCreatedDate(Instant.now());
                                }
                                vcutStylePlanUploadRepository.save(vcutStylePlanUpload);
                            }
                        }
                    }
                }
            }
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(new Message("ERROR", e.getMessage()));
        }
        return ResponseEntity.ok().body(new Message("SUCCESS", errorLog));
    }



    /**
     * {@code PUT  /vcut-style-plan-uploads} : Updates an existing vcutStylePlanUpload.
     *
     * @param vcutStylePlanUpload the vcutStylePlanUpload to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vcutStylePlanUpload,
     * or with status {@code 400 (Bad Request)} if the vcutStylePlanUpload is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vcutStylePlanUpload couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @GetMapping("/vcut-style-plan-uploads-active/{id}")
    public ResponseEntity<VcutStylePlanUpload> updateVcutStylePlanUploadMobile(@PathVariable Long id) throws URISyntaxException {
        VcutStylePlanUpload result = vcutStylePlanUploadRepository.findById(id).orElse(null);
        if(result != null) {
            List<VcutStylePlanUpload> vcutStylePlanUploads = vcutStylePlanUploadRepository.findByPlanDateAndLineNo(result.getPlanDate(), result.getLineNo());
            for (VcutStylePlanUpload vcutStylePlanUpload : vcutStylePlanUploads) {
                if (vcutStylePlanUpload.getId().longValue() == id) {
                    vcutStylePlanUpload.setActivePlan(true);
                    result = vcutStylePlanUploadRepository.save(vcutStylePlanUpload);
                } else {
                    vcutStylePlanUpload.setActivePlan(false);
                    vcutStylePlanUploadRepository.save(vcutStylePlanUpload);
                }
            }
            return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
        } else {
            return ResponseEntity.badRequest()
                .headers(HeaderUtil.createFailureAlert(applicationName, true, ENTITY_NAME, "400", "Data not exist!"))//(applicationName, false, ENTITY_NAME, vcutStylePlanUpload.getId().toString()))
                .body(null);
        }
    }
}
