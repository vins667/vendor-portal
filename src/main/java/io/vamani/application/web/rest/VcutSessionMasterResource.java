package io.vamani.application.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.domain.VcutSessionDetails;
import io.vamani.application.domain.VcutSessionMaster;
import io.vamani.application.model.VcutSessionMasterBean;
import io.vamani.application.repository.VcutSessionDetailsRepository;
import io.vamani.application.repository.VcutSessionMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;

/**
 * REST controller for managing VcutSessionMaster.
 */
@RestController
@RequestMapping("/api")
public class VcutSessionMasterResource {

    private final Logger log = LoggerFactory.getLogger(VcutSessionMasterResource.class);

    private static final String ENTITY_NAME = "vcutSessionMaster";

    @Autowired
    private  VcutSessionDetailsRepository vcutSessionDetailsRepository;
    
    private final VcutSessionMasterRepository vcutSessionMasterRepository;
    
    public VcutSessionMasterResource(VcutSessionMasterRepository vcutSessionMasterRepository) {
        this.vcutSessionMasterRepository = vcutSessionMasterRepository;
    }

   
    
    /**
     * POST  /vcut-session-masters : Create a new vcutSessionMaster.
     *
     * @param vcutSessionMaster the vcutSessionMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vcutSessionMaster, or with status 400 (Bad Request) if the vcutSessionMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/vcut-session-masters")
    @Timed
    public ResponseEntity<VcutSessionMaster> createVcutSessionMaster(@Valid @RequestBody VcutSessionMasterBean vcutSessionMasterBean) throws URISyntaxException {
        log.debug("REST request to save VcutSessionMaster : {}", vcutSessionMasterBean);
        if (vcutSessionMasterBean.getId() != null) {
            throw new BadRequestAlertException("A new vcutSessionMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VcutSessionMaster vcutSessionMaster = new VcutSessionMaster();
        BeanUtils.copyProperties(vcutSessionMasterBean, vcutSessionMaster);
        vcutSessionMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        vcutSessionMaster.setCreatedDate(Instant.now());
        VcutSessionMaster result = vcutSessionMasterRepository.save(vcutSessionMaster);
        if(result!=null) {
        	for(VcutSessionDetails vcutSessionDetails:vcutSessionMasterBean.getVcutSessionDetails()) {
            	if(vcutSessionDetails!=null && vcutSessionDetails.getStartTime()!=null) {
            		vcutSessionDetails.setVcutSessionMaster(result);
                	vcutSessionDetails.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                	vcutSessionDetails.setCreatedDate(Instant.now());
                	vcutSessionDetailsRepository.save(vcutSessionDetails);
            	}
            }
        }
        return ResponseEntity.created(new URI("/api/vcut-session-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /vcut-session-masters : Updates an existing vcutSessionMaster.
     *
     * @param vcutSessionMaster the vcutSessionMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vcutSessionMaster,
     * or with status 400 (Bad Request) if the vcutSessionMaster is not valid,
     * or with status 500 (Internal Server Error) if the vcutSessionMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vcut-session-masters")
    @Timed
    public ResponseEntity<VcutSessionMaster> updateVcutSessionMaster(@Valid @RequestBody VcutSessionMasterBean vcutSessionMasterBean) throws URISyntaxException {
        log.debug("REST request to update VcutSessionMaster : {}", vcutSessionMasterBean);
        if (vcutSessionMasterBean.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VcutSessionMaster vcutSessionMaster = new VcutSessionMaster();
        BeanUtils.copyProperties(vcutSessionMasterBean, vcutSessionMaster);
        vcutSessionMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        vcutSessionMaster.setLastUpdatedDate(Instant.now());
        VcutSessionMaster result = vcutSessionMasterRepository.save(vcutSessionMaster);
        if(result!=null) {
        	for(VcutSessionDetails vcutSessionDetails:vcutSessionMasterBean.getVcutSessionDetails()) {
            	vcutSessionDetails.setVcutSessionMaster(result);
            	vcutSessionDetails.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
            	vcutSessionDetails.setLastUpdatedDate(Instant.now());
            	vcutSessionDetailsRepository.save(vcutSessionDetails);
            }
        }
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, vcutSessionMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /vcut-session-masters : get all the vcutSessionMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of vcutSessionMasters in body
     */
    @GetMapping("/vcut-session-masters")
    @Timed
    public ResponseEntity<List<VcutSessionMaster>> getAllVcutSessionMasters(Pageable pageable) {
        log.debug("REST request to get a page of VcutSessionMasters");
        Page<VcutSessionMaster> page = vcutSessionMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/vcut-session-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /vcut-session-masters/:id : get the "id" vcutSessionMaster.
     *
     * @param id the id of the vcutSessionMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vcutSessionMaster, or with status 404 (Not Found)
     */
    @GetMapping("/vcut-session-masters/{id}")
    @Timed
    public ResponseEntity<VcutSessionMasterBean> getVcutSessionMaster(@PathVariable Long id) {
        log.debug("REST request to get VcutSessionMaster : {}", id);
        VcutSessionMaster vcutSessionMaster = vcutSessionMasterRepository.findVcutSessionMaster(id);
        VcutSessionMasterBean vcutSessionMasterBean = new VcutSessionMasterBean();
        BeanUtils.copyProperties(vcutSessionMaster, vcutSessionMasterBean);
        vcutSessionMasterBean.setVcutSessionDetails(vcutSessionDetailsRepository.findVcutSessionMasterId(vcutSessionMaster.getId()));
        return ResponseUtil.wrapOrNotFound(Optional.of(vcutSessionMasterBean));
    }

    /**
     * DELETE  /vcut-session-masters/:id : delete the "id" vcutSessionMaster.
     *
     * @param id the id of the vcutSessionMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/vcut-session-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteVcutSessionMaster(@PathVariable Long id) {
        log.debug("REST request to delete VcutSessionMaster : {}", id);
        vcutSessionDetailsRepository.deleteAllByVcutSessionMasterId(id);
        vcutSessionMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
