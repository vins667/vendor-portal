package io.vamani.application.web.rest;

import io.vamani.application.domain.VcutPlanChangeMaster;
import io.vamani.application.mobile.VcutPlanChangeMasterMobile;
import io.vamani.application.repository.VcutPlanChangeMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link VcutPlanChangeMaster}.
 */
@RestController
@RequestMapping("/api")
public class VcutPlanChangeMasterResource {

    private final Logger log = LoggerFactory.getLogger(VcutPlanChangeMasterResource.class);

    private static final String ENTITY_NAME = "vcutPlanChangeMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VcutPlanChangeMasterRepository vcutPlanChangeMasterRepository;

    public VcutPlanChangeMasterResource(VcutPlanChangeMasterRepository vcutPlanChangeMasterRepository) {
        this.vcutPlanChangeMasterRepository = vcutPlanChangeMasterRepository;
    }

    /**
     * {@code POST  /vcut-plan-change-masters} : Create a new vcutPlanChangeMaster.
     *
     * @param vcutPlanChangeMaster the vcutPlanChangeMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vcutPlanChangeMaster, or with status {@code 400 (Bad Request)} if the vcutPlanChangeMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/vcut-plan-change-masters")
    public ResponseEntity<VcutPlanChangeMaster> createVcutPlanChangeMaster(@Valid @RequestBody VcutPlanChangeMaster vcutPlanChangeMaster) throws URISyntaxException {
        log.debug("REST request to save VcutPlanChangeMaster : {}", vcutPlanChangeMaster);
        if (vcutPlanChangeMaster.getId() != null) {
            throw new BadRequestAlertException("A new vcutPlanChangeMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        vcutPlanChangeMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        vcutPlanChangeMaster.setCreatedDate(Instant.now());
        VcutPlanChangeMaster result = vcutPlanChangeMasterRepository.save(vcutPlanChangeMaster);
        return ResponseEntity.created(new URI("/api/vcut-plan-change-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /vcut-plan-change-masters} : Updates an existing vcutPlanChangeMaster.
     *
     * @param vcutPlanChangeMaster the vcutPlanChangeMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vcutPlanChangeMaster,
     * or with status {@code 400 (Bad Request)} if the vcutPlanChangeMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vcutPlanChangeMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/vcut-plan-change-masters")
    public ResponseEntity<VcutPlanChangeMaster> updateVcutPlanChangeMaster(@Valid @RequestBody VcutPlanChangeMaster vcutPlanChangeMaster) throws URISyntaxException {
        log.debug("REST request to update VcutPlanChangeMaster : {}", vcutPlanChangeMaster);
        if (vcutPlanChangeMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        vcutPlanChangeMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        vcutPlanChangeMaster.setLastUpdatedDate(Instant.now());
        VcutPlanChangeMaster result = vcutPlanChangeMasterRepository.save(vcutPlanChangeMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, vcutPlanChangeMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /vcut-plan-change-masters} : get all the vcutPlanChangeMasters.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vcutPlanChangeMasters in body.
     */
    @GetMapping("/vcut-plan-change-masters")
    public ResponseEntity<List<VcutPlanChangeMaster>> getAllVcutPlanChangeMasters(@PageableDefault(value = Integer.MAX_VALUE)  @SortDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.debug("REST request to get a page of VcutPlanChangeMasters");
        Page<VcutPlanChangeMaster> page = vcutPlanChangeMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /vcut-plan-change-masters} : get all the vcutPlanChangeMasters.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vcutPlanChangeMasters in body.
     */
    @GetMapping("/vcut-plan-change-masters-mobile")
    public ResponseEntity<List<VcutPlanChangeMasterMobile>> getAllMvcutPlanChangeMasters() {
        log.debug("REST request to get a page of VcutPlanChangeMasters");
        List<VcutPlanChangeMasterMobile> vcutPlanChangeMasterMobiles = new ArrayList<>();
        List<VcutPlanChangeMaster> vcutPlanChangeMasters = vcutPlanChangeMasterRepository.findAll();
        for (VcutPlanChangeMaster vcutPlanChangeMaster : vcutPlanChangeMasters) {
            VcutPlanChangeMasterMobile vcutPlanChangeMasterMobile = new VcutPlanChangeMasterMobile();
            BeanUtils.copyProperties(vcutPlanChangeMaster, vcutPlanChangeMasterMobile);
            vcutPlanChangeMasterMobiles.add(vcutPlanChangeMasterMobile);
        }
        return ResponseEntity.ok().body(vcutPlanChangeMasterMobiles);
    }

    /**
     * {@code GET  /vcut-plan-change-masters/:id} : get the "id" vcutPlanChangeMaster.
     *
     * @param id the id of the vcutPlanChangeMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vcutPlanChangeMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/vcut-plan-change-masters/{id}")
    public ResponseEntity<VcutPlanChangeMaster> getVcutPlanChangeMaster(@PathVariable Long id) {
        log.debug("REST request to get VcutPlanChangeMaster : {}", id);
        Optional<VcutPlanChangeMaster> vcutPlanChangeMaster = vcutPlanChangeMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(vcutPlanChangeMaster);
    }

    /**
     * {@code DELETE  /vcut-plan-change-masters/:id} : delete the "id" vcutPlanChangeMaster.
     *
     * @param id the id of the vcutPlanChangeMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/vcut-plan-change-masters/{id}")
    public ResponseEntity<Void> deleteVcutPlanChangeMaster(@PathVariable Long id) {
        log.debug("REST request to delete VcutPlanChangeMaster : {}", id);
        vcutPlanChangeMasterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
