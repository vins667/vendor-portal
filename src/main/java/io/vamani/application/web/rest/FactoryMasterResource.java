package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.FactoryMaster;
import io.vamani.application.model.Master;
import io.vamani.application.model.MasterBean;
import io.vamani.application.model.MasterSearch;
import io.vamani.application.repository.FactoryMasterRepository;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing FactoryMaster.
 */
@RestController
@RequestMapping("/api")
public class FactoryMasterResource {

    private final Logger log = LoggerFactory.getLogger(FactoryMasterResource.class);

    private static final String ENTITY_NAME = "factoryMaster";

    private final FactoryMasterRepository factoryMasterRepository;

    public FactoryMasterResource(FactoryMasterRepository factoryMasterRepository) {
        this.factoryMasterRepository = factoryMasterRepository;
    }

    /**
     * POST  /factory-masters : Create a new factoryMaster.
     *
     * @param factoryMaster the factoryMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new factoryMaster, or with status 400 (Bad Request) if the factoryMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/factory-masters")
    @Timed
    public ResponseEntity<FactoryMaster> createFactoryMaster(@Valid @RequestBody FactoryMaster factoryMaster) throws URISyntaxException {
        log.debug("REST request to save FactoryMaster : {}", factoryMaster);
        if (factoryMaster.getId() != null) {
            throw new BadRequestAlertException("A new factoryMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FactoryMaster result = factoryMasterRepository.save(factoryMaster);
        return ResponseEntity.created(new URI("/api/factory-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /factory-masters : Updates an existing factoryMaster.
     *
     * @param factoryMaster the factoryMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated factoryMaster,
     * or with status 400 (Bad Request) if the factoryMaster is not valid,
     * or with status 500 (Internal Server Error) if the factoryMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/factory-masters")
    @Timed
    public ResponseEntity<FactoryMaster> updateFactoryMaster(@Valid @RequestBody FactoryMaster factoryMaster) throws URISyntaxException {
        log.debug("REST request to update FactoryMaster : {}", factoryMaster);
        if (factoryMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FactoryMaster result = factoryMasterRepository.save(factoryMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, factoryMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /factory-masters : get all the factoryMasters.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of factoryMasters in body
     */
    @GetMapping("/factory-masters")
    @Timed
    public List<FactoryMaster> getAllFactoryMasters() {
        log.debug("REST request to get all FactoryMasters");
        return factoryMasterRepository.findAll();
    }

    /**
     * GET  /factory-masters : get all the factoryMasters.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of factoryMasters in body
     */
    @PostMapping("/factory-masters-group")
    @Timed
    public List<FactoryMaster> getAllFactoryMasters(@RequestBody MasterSearch search) {
        log.debug("REST request to get all FactoryMasters");
        return factoryMasterRepository.findAllByGroup(search.getParameters1());
    }

    /**
     * GET  /factory-masters : get all the factoryMasters.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of factoryMasters in body
     */
    @GetMapping("/factory-masters-group")
    @Timed
    public List<MasterBean> getAllFactoryMastersGroup() {
        log.debug("REST request to get all FactoryMasters");
        List<MasterBean> masters = new ArrayList<>();
        List<String> strings = factoryMasterRepository.findAllGroupCode();
        for (String s : strings) {
            MasterBean master = new MasterBean();
            master.setCode(s);
            master.setDesc(s);
            masters.add(master);
        }
        return masters;
    }

    /**
     * GET  /factory-masters : get all the factoryMasters.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of factoryMasters in body
     */
    @GetMapping("/factory-masters-now")
    @Timed
    public List<FactoryMaster> getAllFactoryMastersNow() {
        log.debug("REST request to get all FactoryMasters");
        return factoryMasterRepository.findAllNowFactory();
    }

    /**
     * GET  /factory-masters/:id : get the "id" factoryMaster.
     *
     * @param id the id of the factoryMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the factoryMaster, or with status 404 (Not Found)
     */
    @GetMapping("/factory-masters/{id}")
    @Timed
    public ResponseEntity<FactoryMaster> getFactoryMaster(@PathVariable Long id) {
        log.debug("REST request to get FactoryMaster : {}", id);
        Optional<FactoryMaster> factoryMaster = factoryMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(factoryMaster);
    }

    /**
     * DELETE  /factory-masters/:id : delete the "id" factoryMaster.
     *
     * @param id the id of the factoryMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/factory-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteFactoryMaster(@PathVariable Long id) {
        log.debug("REST request to delete FactoryMaster : {}", id);

        factoryMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
