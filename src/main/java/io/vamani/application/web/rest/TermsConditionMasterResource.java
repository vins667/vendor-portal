package io.vamani.application.web.rest;

import io.vamani.application.domain.TermsConditionDetails;
import io.vamani.application.domain.TermsConditionMaster;
import io.vamani.application.model.TermsConditionMasterBean;
import io.vamani.application.repository.TermsConditionDetailsRepository;
import io.vamani.application.repository.TermsConditionMasterRepository;
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
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link io.vamani.application.domain.TermsConditionMaster}.
 */
@RestController
@RequestMapping("/api")
public class TermsConditionMasterResource {

    private final Logger log = LoggerFactory.getLogger(TermsConditionMasterResource.class);

    private static final String ENTITY_NAME = "termsConditionMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TermsConditionMasterRepository termsConditionMasterRepository;
    
    @Autowired
    private  TermsConditionDetailsRepository termsConditionDetailsRepository;

    public TermsConditionMasterResource(TermsConditionMasterRepository termsConditionMasterRepository) {
        this.termsConditionMasterRepository = termsConditionMasterRepository;
    }

    /**
     * {@code POST  /terms-condition-masters} : Create a new termsConditionMaster.
     *
     * @param termsConditionMaster the termsConditionMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new termsConditionMaster, or with status {@code 400 (Bad Request)} if the termsConditionMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/terms-condition-masters")
    public ResponseEntity<TermsConditionMaster> createTermsConditionMaster(@Valid @RequestBody TermsConditionMasterBean termsConditionMasterBean) throws URISyntaxException {
        log.debug("REST request to save TermsConditionMaster : {}", termsConditionMasterBean);
        if (termsConditionMasterBean.getId() != null) {
            throw new BadRequestAlertException("A new termsConditionMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TermsConditionMaster termsConditionMaster = new TermsConditionMaster();
        BeanUtils.copyProperties(termsConditionMasterBean, termsConditionMaster);
        termsConditionMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        termsConditionMaster.setCreatedDate(Instant.now());
        TermsConditionMaster result = termsConditionMasterRepository.save(termsConditionMaster);
        if(termsConditionMasterBean.getTermsConditionDetails()!=null) {
        	for(TermsConditionDetails details:termsConditionMasterBean.getTermsConditionDetails()) {
        		if(details.getTermsLine()!=null && !details.getTermsLine().isEmpty()) {
        			TermsConditionDetails bean  = new TermsConditionDetails();
            		BeanUtils.copyProperties(details, bean);
            		bean.setTermsConditionMaster(result);
            		bean.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
            		bean.setCreatedDate(Instant.now());
            		termsConditionDetailsRepository.save(bean);
        		}
        	}
        }
        return ResponseEntity.created(new URI("/api/terms-condition-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /terms-condition-masters} : Updates an existing termsConditionMaster.
     *
     * @param termsConditionMaster the termsConditionMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated termsConditionMaster,
     * or with status {@code 400 (Bad Request)} if the termsConditionMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the termsConditionMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/terms-condition-masters-update")
    public ResponseEntity<TermsConditionMaster> updateTermsConditionMaster(@Valid @RequestBody TermsConditionMasterBean termsConditionMasterBean) throws URISyntaxException {
        log.debug("REST request to update TermsConditionMaster : {}", termsConditionMasterBean);
        if (termsConditionMasterBean.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TermsConditionMaster termsConditionMaster =termsConditionMasterRepository.findAllById(termsConditionMasterBean.getId());
        BeanUtils.copyProperties(termsConditionMasterBean, termsConditionMaster);
        termsConditionMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        termsConditionMaster.setLastUpdatedDate(Instant.now());
        TermsConditionMaster result = termsConditionMasterRepository.save(termsConditionMaster);
        if(termsConditionMasterBean.getTermsConditionDetails()!=null) {
        	for(TermsConditionDetails details:termsConditionMasterBean.getTermsConditionDetails()) {
        		TermsConditionDetails bean  = null;
        		if(details.getId()!=null) {
        			bean=termsConditionDetailsRepository.findById(details.getId()).orElse(null);
        			BeanUtils.copyProperties(details, bean);
            		bean.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
            		bean.setLastUpdatedDate(Instant.now());
            		termsConditionDetailsRepository.save(bean);
        		} else {
        			if(details.getTermsLine()!=null && !details.getTermsLine().isEmpty()) {
        				bean  = new TermsConditionDetails();
            			BeanUtils.copyProperties(details, bean);
                		bean.setTermsConditionMaster(result);
                		bean.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                		bean.setCreatedDate(Instant.now());
                		termsConditionDetailsRepository.save(bean);
        			}
        		}
        		
        	}
        }
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, termsConditionMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /terms-condition-masters} : get all the termsConditionMasters.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of termsConditionMasters in body.
     */
    @GetMapping("/terms-condition-masters")
    public ResponseEntity<List<TermsConditionMaster>> getAllTermsConditionMasters(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of TermsConditionMasters");
        Page<TermsConditionMaster> page = termsConditionMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /terms-condition-masters/:id} : get the "id" termsConditionMaster.
     *
     * @param id the id of the termsConditionMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the termsConditionMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/terms-condition-masters/{id}")
    public ResponseEntity<TermsConditionMasterBean> getTermsConditionMaster(@PathVariable Long id) {
        log.debug("REST request to get TermsConditionMaster : {}", id);
        TermsConditionMasterBean bean = new TermsConditionMasterBean();
        TermsConditionMaster termsConditionMaster = termsConditionMasterRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(termsConditionMaster, bean);
        bean.setTermsConditionDetails(termsConditionDetailsRepository.findAllByTermsConditionMasterId(id));
        return ResponseUtil.wrapOrNotFound(Optional.of(bean));
    }

    /**
     * {@code DELETE  /terms-condition-masters/:id} : delete the "id" termsConditionMaster.
     *
     * @param id the id of the termsConditionMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/terms-condition-masters/{id}")
    public ResponseEntity<Void> deleteTermsConditionMaster(@PathVariable Long id) {
        log.debug("REST request to delete TermsConditionMaster : {}", id);
        termsConditionDetailsRepository.deleteAllDetailById(id);
        termsConditionMasterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
