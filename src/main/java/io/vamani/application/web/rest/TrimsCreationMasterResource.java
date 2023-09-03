package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.TrimsCreationDetails;
import io.vamani.application.domain.TrimsCreationMaster;
import io.vamani.application.domain.TrimsTemplateDetails;
import io.vamani.application.domain.TrimsTemplateMaster;
import io.vamani.application.model.Master;
import io.vamani.application.model.TrimsCreationMasterBean;
import io.vamani.application.model.TrimsTemplateDetailsBean;
import io.vamani.application.model.TrimsTemplateMasterBean;
import io.vamani.application.repository.TrimsCreationDetailsRepository;
import io.vamani.application.repository.TrimsCreationMasterRepository;
import io.vamani.application.repository.TrimsTemplateMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.time.Instant;
import java.util.*;

/**
 * REST controller for managing TrimsCreationMaster.
 */
@RestController
@RequestMapping("/api")
public class TrimsCreationMasterResource {

    private final Logger log = LoggerFactory.getLogger(TrimsCreationMasterResource.class);

    private static final String ENTITY_NAME = "trimsCreationMaster";

    private final TrimsCreationMasterRepository trimsCreationMasterRepository;

    @Autowired
    private TrimsCreationDetailsRepository trimsCreationDetailsRepository;

    @Autowired
    private TrimsTemplateMasterRepository trimsTemplateMasterRepository;

    public TrimsCreationMasterResource(TrimsCreationMasterRepository trimsCreationMasterRepository) {
        this.trimsCreationMasterRepository = trimsCreationMasterRepository;
    }

    /**
     * POST  /trims-creation-masters : Create a new trimsCreationMaster.
     *
     * @param !trimsCreationMaster the trimsCreationMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new trimsCreationMaster, or with status 400 (Bad Request) if the trimsCreationMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/trims-creation-masters")
    @Timed
    public ResponseEntity<TrimsCreationMaster> createTrimsCreationMaster(@Valid @RequestBody TrimsCreationMasterBean trimsCreationMasterBean) throws URISyntaxException {
        log.debug("REST request to save TrimsCreationMaster : {}", trimsCreationMasterBean);

        if (trimsCreationMasterBean.getId() != null) {
            throw new BadRequestAlertException("A new trimsCreationMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        String currentUser = SecurityUtils.getCurrentUserLogin().orElse(null);
        TrimsCreationMaster creationMaster = trimsCreationMasterRepository.findByDescription(trimsCreationMasterBean.getDescription());
        if (creationMaster != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Description Already Exist!")).body(null);
        }
        TrimsCreationMaster trimsCreationMaster = new TrimsCreationMaster();
        BeanUtils.copyProperties(trimsCreationMasterBean, trimsCreationMaster);

        Long currentSeries = trimsCreationMasterRepository.findMaxSeries(trimsCreationMasterBean.getTrimsTemplateMaster().getAccessoriesCode());
        trimsCreationMaster.setCode(trimsCreationMasterBean.getTrimsTemplateMaster().getAccessoriesCode() + StringUtils.leftPad(currentSeries.toString(), 4, "0"));
        trimsCreationMaster.setFlag("Y");
        trimsCreationMaster.setCreatedBy(currentUser);
        trimsCreationMaster.setCreatedDate(Instant.now());
        trimsCreationMaster.setTrimsTemplateMaster(trimsTemplateMasterRepository.findById(trimsCreationMasterBean.getTrimsTemplateMaster().getId()).orElse(null));
        TrimsCreationMaster result = trimsCreationMasterRepository.save(trimsCreationMaster);
        if (result != null) {
            if (trimsCreationMasterBean.getTrimsTemplateMaster().getTrimsTemplateDetails() != null) {
                for (TrimsTemplateDetailsBean trimsTemplateDetailsBean : trimsCreationMasterBean.getTrimsTemplateMaster().getTrimsTemplateDetails()) {
                    TrimsTemplateDetails trimsTemplateDetails = new TrimsTemplateDetails();
                    BeanUtils.copyProperties(trimsTemplateDetailsBean, trimsTemplateDetails);
                    TrimsCreationDetails trimsCreationDetails = new TrimsCreationDetails();
                    trimsCreationDetails.setTrimsCreationMaster(result);
                    trimsCreationDetails.setTrimsTemplateDetails(trimsTemplateDetails);
                    if(trimsTemplateDetails.getFieldType() != null && trimsTemplateDetails.getFieldType().equalsIgnoreCase("D")) {
                        trimsCreationDetails.setDetailsValue(trimsTemplateDetailsBean.getFieldValueDropDown());
                    } else {
                        trimsCreationDetails.setDetailsValue(trimsTemplateDetailsBean.getFieldValue());
                    }
                    trimsCreationDetails.setCreatedBy(currentUser);
                    trimsCreationDetails.setCreatedDate(Instant.now());
                    trimsCreationDetailsRepository.save(trimsCreationDetails);
                }
            }
        }
        return ResponseEntity.created(new URI("/api/trims-creation-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /trims-creation-masters : Updates an existing trimsCreationMaster.
     *
     * @param trimsCreationMaster the trimsCreationMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated trimsCreationMaster,
     * or with status 400 (Bad Request) if the trimsCreationMaster is not valid,
     * or with status 500 (Internal Server Error) if the trimsCreationMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/trims-creation-masters")
    @Timed
    public ResponseEntity<TrimsCreationMaster> updateTrimsCreationMaster(@Valid @RequestBody TrimsCreationMasterBean trimsCreationMasterBean) throws URISyntaxException {
        TrimsCreationMaster creationMaster = trimsCreationMasterRepository.findByDescription(trimsCreationMasterBean.getDescription());
        if(creationMaster != null && creationMaster.getId().longValue() != trimsCreationMasterBean.getId().longValue()) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Description Already Exist!")).body(null);
        }
        String currentUser = SecurityUtils.getCurrentUserLogin().orElse(null);
        TrimsCreationMaster trimsCreationMaster = new TrimsCreationMaster();
        BeanUtils.copyProperties(trimsCreationMasterBean, trimsCreationMaster);
        log.debug("REST request to update TrimsCreationMaster : {}", trimsCreationMaster);
        if (trimsCreationMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        trimsCreationMaster.setLastUpdatedBy(currentUser);
        trimsCreationMaster.setLastUpdatedDate(Instant.now());
        trimsCreationMaster.setTrimsTemplateMaster(trimsTemplateMasterRepository.findById(trimsCreationMasterBean.getTrimsTemplateMaster().getId()).orElse(null));
        TrimsCreationMaster result = trimsCreationMasterRepository.save(trimsCreationMaster);
        if(result != null) {
            List<TrimsCreationDetails> trimsCreationDetailsList = trimsCreationDetailsRepository.findAllByTrimsCreationMasterId(result.getId());
            Map<Long, TrimsCreationDetails> creationDetailsMap = new HashMap<>();
            for(TrimsCreationDetails creationDetails : trimsCreationDetailsList) {
                creationDetailsMap.put(creationDetails.getTrimsTemplateDetails().getId(), creationDetails);
            }
            if (trimsCreationMasterBean.getTrimsTemplateMaster().getTrimsTemplateDetails() != null) {
                for (TrimsTemplateDetailsBean trimsTemplateDetailsBean : trimsCreationMasterBean.getTrimsTemplateMaster().getTrimsTemplateDetails()) {
                    TrimsTemplateDetails trimsTemplateDetails = new TrimsTemplateDetails();
                    BeanUtils.copyProperties(trimsTemplateDetailsBean, trimsTemplateDetails);
                    TrimsCreationDetails trimsCreationDetails = new TrimsCreationDetails();
                    trimsCreationDetails.setTrimsCreationMaster(result);
                    trimsCreationDetails.setTrimsTemplateDetails(trimsTemplateDetails);
                    if(trimsTemplateDetails.getFieldType() != null && trimsTemplateDetails.getFieldType().equalsIgnoreCase("D")) {
                        trimsCreationDetails.setDetailsValue(trimsTemplateDetailsBean.getFieldValueDropDown());
                    } else {
                        trimsCreationDetails.setDetailsValue(trimsTemplateDetailsBean.getFieldValue());
                    }
                    if(creationDetailsMap.containsKey(trimsTemplateDetails.getId())) {
                        TrimsCreationDetails creationDetails = creationDetailsMap.get(trimsTemplateDetails.getId());
                        trimsCreationDetails.setId(creationDetails.getId());
                        trimsCreationDetails.setCreatedBy(creationDetails.getCreatedBy());
                        trimsCreationDetails.setCreatedDate(creationDetails.getCreatedDate());
                        trimsCreationDetails.setLastUpdatedBy(currentUser);
                        trimsCreationDetails.setLastUpdatedDate(Instant.now());
                    } else {
                        trimsCreationDetails.setCreatedBy(currentUser);
                        trimsCreationDetails.setCreatedDate(Instant.now());
                    }
                    trimsCreationDetailsRepository.save(trimsCreationDetails);
                }
            }
        }
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, trimsCreationMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /trims-creation-masters : get all the trimsCreationMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of trimsCreationMasters in body
     */
    @GetMapping("/trims-creation-masters")
    @Timed
    public ResponseEntity<List<TrimsCreationMaster>> getAllTrimsCreationMasters(Pageable pageable) {
        log.debug("REST request to get a page of TrimsCreationMasters");
        Page<TrimsCreationMaster> page = trimsCreationMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/trims-creation-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * POST  /fabric-creation-masters : get all the trimsCreationMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of fabricCreationMasters in body
     */
    @PostMapping("/trims-creation-masters-custom")
    public ResponseEntity<List<TrimsCreationMaster>> searchAllTrimsCreationMasters(@RequestBody Master search) {
        log.debug("REST request to get a page of TrimsCreationMasters");
        String desc = "%";
        if (search.getDesc() != null) {
            desc = search.getDesc().toUpperCase() + "%";
        }
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("description").ascending()));
        Page<TrimsCreationMaster> page = trimsCreationMasterRepository.findAllByCodeAndDesc(desc, desc, search.getPage());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/trims-creation-masters-custom");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /trims-creation-masters/:id : get the "id" trimsCreationMaster.
     *
     * @param id the id of the trimsCreationMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the trimsCreationMaster, or with status 404 (Not Found)
     */
    @GetMapping("/trims-creation-masters/{id}")
    @Timed
    public ResponseEntity<TrimsCreationMasterBean> getTrimsCreationMaster(@PathVariable Long id) {
        log.debug("REST request to get TrimsCreationMaster : {}", id);
        TrimsCreationMasterBean trimsCreationMasterBean = new TrimsCreationMasterBean();
        TrimsCreationMaster trimsCreationMaster = trimsCreationMasterRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(trimsCreationMaster, trimsCreationMasterBean);
        List<TrimsCreationDetails> trimsCreationDetailsList = trimsCreationDetailsRepository.findAllByTrimsCreationMasterId(trimsCreationMaster.getId());
        Map<Long, TrimsCreationDetails> creationDetailsMap = new HashMap<>();
        for(TrimsCreationDetails creationDetails : trimsCreationDetailsList) {
            creationDetailsMap.put(creationDetails.getTrimsTemplateDetails().getId(), creationDetails);
        }
        TrimsTemplateMasterBean templateMaster = new TrimsTemplateMasterBean();
        BeanUtils.copyProperties(trimsCreationMaster.getTrimsTemplateMaster(), templateMaster);
        List<TrimsTemplateDetailsBean> trimsTemplateDetailsBeans = new ArrayList<>();
        if (trimsCreationMaster.getTrimsTemplateMaster() != null && trimsCreationMaster.getTrimsTemplateMaster().getTrimsTemplateDetails() != null) {
            for (TrimsTemplateDetails trimsTemplateDetails : trimsCreationMaster.getTrimsTemplateMaster().getTrimsTemplateDetails()) {
                TrimsTemplateDetailsBean templateDetailsBean = new TrimsTemplateDetailsBean();
                BeanUtils.copyProperties(trimsTemplateDetails, templateDetailsBean);
                if(creationDetailsMap.containsKey(trimsTemplateDetails.getId())) {
                    TrimsCreationDetails trimsCreationDetails = creationDetailsMap.get(trimsTemplateDetails.getId());
                    if (trimsTemplateDetails.getFieldType() != null && trimsTemplateDetails.getFieldType().equalsIgnoreCase("D")) {
                        templateDetailsBean.setFieldValueDropDown(trimsCreationDetails.getDetailsValue());
                    } else {
                        templateDetailsBean.setFieldValue(trimsCreationDetails.getDetailsValue());
                    }
                }
                trimsTemplateDetailsBeans.add(templateDetailsBean);
            }
        }
        templateMaster.setTrimsTemplateDetails(trimsTemplateDetailsBeans);
        trimsCreationMasterBean.setTrimsTemplateMaster(templateMaster);
        return ResponseUtil.wrapOrNotFound(Optional.of(trimsCreationMasterBean));
    }

    /**
     * DELETE  /trims-creation-masters/:id : delete the "id" trimsCreationMaster.
     *
     * @param id the id of the trimsCreationMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/trims-creation-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteTrimsCreationMaster(@PathVariable Long id) {
        log.debug("REST request to delete TrimsCreationMaster : {}", id);
        trimsCreationDetailsRepository.deleteAllByTrimsCreationMasterId(id);
        trimsCreationMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
