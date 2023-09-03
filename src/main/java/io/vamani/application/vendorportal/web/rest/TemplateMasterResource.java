package io.vamani.application.vendorportal.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.vendorportal.domain.TemplateDetails;
import io.vamani.application.vendorportal.domain.TemplateDetailsBreakup;
import io.vamani.application.vendorportal.domain.TemplateDetailsBreakupId;
import io.vamani.application.vendorportal.domain.TemplateMaster;
import io.vamani.application.vendorportal.model.TemplateDetailsBean;
import io.vamani.application.vendorportal.model.TemplateMasterBean;
import io.vamani.application.vendorportal.repository.TemplateDetailsBreakupRepository;
import io.vamani.application.vendorportal.repository.TemplateDetailsRepository;
import io.vamani.application.vendorportal.repository.TemplateMasterRepository;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * REST controller for managing TemplateMaster.
 */
@RestController
@RequestMapping("/api")
public class TemplateMasterResource {

    private final Logger log = LoggerFactory.getLogger(TemplateMasterResource.class);

    private static final String ENTITY_NAME = "templateMaster";

    private final TemplateMasterRepository templateMasterRepository;

    @Autowired
    private TemplateDetailsRepository templateDetailsRepository;

    @Autowired
    private TemplateDetailsBreakupRepository templateDetailsBreakupRepository;

    public TemplateMasterResource(TemplateMasterRepository templateMasterRepository) {
        this.templateMasterRepository = templateMasterRepository;
    }

    /**
     * POST  /template-masters : Create a new templateMaster.
     *
     * @param templateMaster the templateMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new templateMaster, or with status 400 (Bad Request) if the templateMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/template-masters")
    @Timed
    public ResponseEntity<TemplateMaster> createTemplateMaster(@Valid @RequestBody TemplateMasterBean templateMasterBean) throws URISyntaxException {
        log.debug("REST request to save TemplateMaster : {}", templateMasterBean);
        if (templateMasterBean.getId() != null) {
            throw new BadRequestAlertException("A new templateMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        String user = SecurityUtils.getCurrentUserLogin().orElse(null);
        TemplateMaster templateMaster = new TemplateMaster();
        BeanUtils.copyProperties(templateMasterBean, templateMaster, "templateDetails");
        templateMaster.setFlag("Y");
        templateMaster.setCreatedBy(user);
        templateMaster.setCreatedDate(Instant.now());
        TemplateMaster result = templateMasterRepository.save(templateMaster);
        if (result != null) {
            for (TemplateDetailsBean templateDetailsBean : templateMasterBean.getTemplateDetails()) {
                if(templateDetailsBean.getSpecification() != null && templateDetailsBean.getSpecification().length()>0) {
                    TemplateDetails templateDetails = new TemplateDetails();
                    BeanUtils.copyProperties(templateDetailsBean, templateDetails);
                    templateDetails.setTemplateMaster(result);
                    TemplateDetails resultDetails = templateDetailsRepository.save(templateDetails);
                    if (resultDetails != null && resultDetails.getFieldType() != null && resultDetails.getFieldType().equalsIgnoreCase("D") && templateDetailsBean.getFieldValue() != null && templateDetailsBean.getFieldValue().length() > 0) {
                        String[] splitValues = templateDetailsBean.getFieldValue().split(",");
                        Long ctr = 0L;
                        for (String value : splitValues) {
                            TemplateDetailsBreakup templateDetailsBreakup = new TemplateDetailsBreakup();
                            templateDetailsBreakup.setId(new TemplateDetailsBreakupId(++ctr, resultDetails.getId()));
                            templateDetailsBreakup.setDescription(value.trim().toUpperCase());
                            templateDetailsBreakupRepository.save(templateDetailsBreakup);
                        }
                    }
                }
            }
        }
        return ResponseEntity.created(new URI("/api/template-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /template-masters : Updates an existing templateMaster.
     *
     * @param templateMaster the templateMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated templateMaster,
     * or with status 400 (Bad Request) if the templateMaster is not valid,
     * or with status 500 (Internal Server Error) if the templateMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/template-masters")
    @Timed
    public ResponseEntity<TemplateMaster> updateTemplateMaster(@Valid @RequestBody TemplateMasterBean templateMasterBean) throws URISyntaxException {
        log.debug("REST request to update TemplateMaster : {}", templateMasterBean);
        String user = SecurityUtils.getCurrentUserLogin().orElse(null);
        TemplateMaster templateMaster = new TemplateMaster();
        BeanUtils.copyProperties(templateMasterBean, templateMaster, "templateDetails");
        if (templateMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        templateMaster.setLastUpdateBy(user);
        templateMaster.setLastUpdatedDate(Instant.now());
        TemplateMaster result = templateMasterRepository.save(templateMaster);
        if (result != null) {
            for (TemplateDetailsBean templateDetailsBean : templateMasterBean.getTemplateDetails()) {
                if(templateDetailsBean.getSpecification() != null && templateDetailsBean.getSpecification().length()>0) {
                    if(templateDetailsBean.getId() != null) {
                        templateDetailsBreakupRepository.deleteAllByTemplateDetailsId(templateDetailsBean.getId());
                    }
                    TemplateDetails templateDetails = new TemplateDetails();
                    BeanUtils.copyProperties(templateDetailsBean, templateDetails);
                    templateDetails.setTemplateMaster(result);
                    TemplateDetails resultDetails = templateDetailsRepository.save(templateDetails);
                    if (resultDetails != null && resultDetails.getFieldType() != null && resultDetails.getFieldType().equalsIgnoreCase("D") && templateDetailsBean.getFieldValue() != null && templateDetailsBean.getFieldValue().length() > 0) {
                        String[] splitValues = templateDetailsBean.getFieldValue().split(",");
                        Long ctr = 0L;
                        for (String value : splitValues) {
                            TemplateDetailsBreakup templateDetailsBreakup = new TemplateDetailsBreakup();
                            templateDetailsBreakup.setId(new TemplateDetailsBreakupId(++ctr, resultDetails.getId()));
                            templateDetailsBreakup.setDescription(value.trim().toUpperCase());
                            templateDetailsBreakupRepository.save(templateDetailsBreakup);
                        }
                    }
                }
            }
        }
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, templateMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /template-masters : get all the templateMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of templateMasters in body
     */
    @GetMapping("/template-masters")
    @Timed
    public ResponseEntity<List<TemplateMaster>> getAllTemplateMasters(Pageable pageable) {
        log.debug("REST request to get a page of TemplateMasters");
        Page<TemplateMaster> page = templateMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/template-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /template-masters/:id : get the "id" templateMaster.
     *
     * @param id the id of the templateMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the templateMaster, or with status 404 (Not Found)
     */
    @GetMapping("/template-masters/{id}")
    @Timed
    public ResponseEntity<TemplateMaster> getTemplateMaster(@PathVariable Long id) {
        log.debug("REST request to get TemplateMaster : {}", id);
        Optional<TemplateMaster> templateMaster = templateMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(templateMaster);
    }

    /**
     * DELETE  /template-masters/:id : delete the "id" templateMaster.
     *
     * @param id the id of the templateMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/template-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteTemplateMaster(@PathVariable Long id) {
        log.debug("REST request to delete TemplateMaster : {}", id);

        templateMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }


    /**
     * POST  /template-masters : Create a new templateMaster.
     *
     * @param templateMaster the templateMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new templateMaster, or with status 400 (Bad Request) if the templateMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/template-masters-active")
    @Timed
    public ResponseEntity<TemplateMaster> activeTemplateMaster(@Valid @RequestBody TemplateMasterBean templateMaster) throws URISyntaxException {
        TemplateMaster master = templateMasterRepository.findById(templateMaster.getId()).orElse(null);
        master.setFlag(templateMaster.getFlag());

        master.setLastUpdateBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        master.setCreatedDate(Instant.now());
        TemplateMaster result = templateMasterRepository.save(master);

        return ResponseEntity.created(new URI("/api/template-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * GET  /template-masters/:id : get the "id" templateMaster.
     *
     * @param id the id of the templateMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the templateMaster, or with status 404 (Not Found)
     */
    @GetMapping("/template-masters-copy/{id}")
    @Timed
    public ResponseEntity<TemplateMaster> copyTemplateMaster(@PathVariable Long id) {
        log.debug("REST request to get TemplateMaster : {}", id);
        TemplateMaster templateMaster = templateMasterRepository.findById(id).orElse(null);
        templateMaster.setId(null);
        Set<TemplateDetails> templateDetails = new HashSet<>();
        for (TemplateDetails templateDetail : templateMaster.getTemplateDetails()) {
            templateDetail.setId(null);
            templateDetail.setTemplateMaster(null);
            templateDetails.add(templateDetail);
        }
        templateMaster.setTemplateDetails(templateDetails);
        return ResponseUtil.wrapOrNotFound(Optional.of(templateMaster));
    }
}
