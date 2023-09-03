package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.*;
import io.vamani.application.model.FabricCreationMasterBean;
import io.vamani.application.model.Master;
import io.vamani.application.repository.FabricCreationContentDetailsRepository;
import io.vamani.application.repository.FabricCreationMasterRepository;
import io.vamani.application.repository.FabricCreationWarpDetailsRepository;
import io.vamani.application.repository.FabricCreationWeftDetailsRepository;
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
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing FabricCreationMaster.
 */
@RestController
@RequestMapping("/api")
public class FabricCreationMasterResource {

    private final Logger log = LoggerFactory.getLogger(FabricCreationMasterResource.class);

    private static final String ENTITY_NAME = "fabricCreationMaster";

    private final FabricCreationMasterRepository fabricCreationMasterRepository;

    @Autowired
    private FabricCreationWarpDetailsRepository fabricCreationWarpDetailsRepository;

    @Autowired
    private FabricCreationWeftDetailsRepository fabricCreationWeftDetailsRepository;

    @Autowired
    private FabricCreationContentDetailsRepository fabricCreationContentDetailsRepository;

    public FabricCreationMasterResource(FabricCreationMasterRepository fabricCreationMasterRepository) {
        this.fabricCreationMasterRepository = fabricCreationMasterRepository;
    }

    /**
     * POST  /fabric-creation-masters : Create a new fabricCreationMaster.
     *
     * @param fabricCreationMaster the fabricCreationMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fabricCreationMaster, or with status 400 (Bad Request) if the fabricCreationMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/fabric-creation-masters")
    @Timed
    public ResponseEntity<FabricCreationMaster> createFabricCreationMaster(@Valid @RequestBody FabricCreationMasterBean fabricCreationMasterBean) throws URISyntaxException {
        log.debug("REST request to save FabricCreationMaster : {}", fabricCreationMasterBean);
        String currentUser = SecurityUtils.getCurrentUserLogin().orElse(null);
        FabricCreationMaster creationMaster = fabricCreationMasterRepository.findByDescription(fabricCreationMasterBean.getDescription());
        if (creationMaster != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Description Already Exist!")).body(null);
        }
        FabricCreationMaster fabricCreationMaster = new FabricCreationMaster();
        BeanUtils.copyProperties(fabricCreationMasterBean, fabricCreationMaster, "fabricCreationWeftDetails", "fabricCreationWarpDetails", "fabricCreationContentDetails");
        if (fabricCreationMaster.getId() != null) {
            throw new BadRequestAlertException("A new fabricCreationMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Long currentSeries = fabricCreationMasterRepository.findMaxSeries(fabricCreationMaster.getFabricSubstractMaster().getCode());
        fabricCreationMaster.setCode(fabricCreationMaster.getFabricSubstractMaster().getCode() + StringUtils.leftPad(currentSeries.toString(), 4, "0"));
        fabricCreationMaster.setStatus("Y");
        fabricCreationMaster.setCreatedBy(currentUser);
        fabricCreationMaster.setCreatedDate(Instant.now());
        FabricCreationMaster result = fabricCreationMasterRepository.save(fabricCreationMaster);
        if (result != null) {
            if (fabricCreationMasterBean.getFabricCreationWarpDetails() != null && fabricCreationMasterBean.getFabricCreationWarpDetails().size() > 0) {
                long ctr = 0L;
                for (FabricCreationWarpDetails fabricCreationWarpDetail : fabricCreationMasterBean.getFabricCreationWarpDetails()) {
                    if (fabricCreationWarpDetail.getWarp1() != null && fabricCreationWarpDetail.getWarp1().length() > 0 &&
                        fabricCreationWarpDetail.getWarp2() != null && fabricCreationWarpDetail.getWarp2().length() > 0 &&
                        fabricCreationWarpDetail.getFabricUomMaster() != null) {
                        fabricCreationWarpDetail.setId(new FabricCreationWarpDetailsId(++ctr, result));
                        fabricCreationWarpDetailsRepository.save(fabricCreationWarpDetail);
                    }
                }
            }
            if (fabricCreationMasterBean.getFabricCreationWeftDetails() != null && fabricCreationMasterBean.getFabricCreationWeftDetails().size() > 0) {
                long ctr = 0L;
                for (FabricCreationWeftDetails fabricCreationWeftDetail : fabricCreationMasterBean.getFabricCreationWeftDetails()) {
                    if (fabricCreationWeftDetail.getWeft1() != null && fabricCreationWeftDetail.getWeft1().length() > 0 &&
                        fabricCreationWeftDetail.getWeft2() != null && fabricCreationWeftDetail.getWeft2().length() > 0 &&
                        fabricCreationWeftDetail.getFabricUomMaster() != null) {
                        fabricCreationWeftDetail.setId(new FabricCreationWeftDetailsId(++ctr, result));
                        fabricCreationWeftDetailsRepository.save(fabricCreationWeftDetail);
                    }
                }
            }
            if (fabricCreationMasterBean.getFabricCreationContentDetails() != null && fabricCreationMasterBean.getFabricCreationContentDetails().size() > 0) {
                long ctr = 0L;
                for (FabricCreationContentDetails fabricCreationContentDetails : fabricCreationMasterBean.getFabricCreationContentDetails()) {
                    if (fabricCreationContentDetails.getFabricContentMaster() != null && fabricCreationContentDetails.getPercentage() != null &&
                        fabricCreationContentDetails.getPercentage()>0) {
                        fabricCreationContentDetails.setId(new FabricCreationContentDetailsId(++ctr, result));
                        fabricCreationContentDetailsRepository.save(fabricCreationContentDetails);
                    }
                }
            }
        }
        return ResponseEntity.created(new URI("/api/fabric-creation-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /fabric-creation-masters : Updates an existing fabricCreationMaster.
     *
     * @param fabricCreationMaster the fabricCreationMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fabricCreationMaster,
     * or with status 400 (Bad Request) if the fabricCreationMaster is not valid,
     * or with status 500 (Internal Server Error) if the fabricCreationMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/fabric-creation-masters")
    @Timed
    public ResponseEntity<FabricCreationMaster> updateFabricCreationMaster(@Valid @RequestBody FabricCreationMasterBean fabricCreationMasterBean) throws URISyntaxException {
        log.debug("REST request to update FabricCreationMaster : {}", fabricCreationMasterBean);
        FabricCreationMaster creationMaster = fabricCreationMasterRepository.findByDescription(fabricCreationMasterBean.getDescription());
        if(creationMaster != null && creationMaster.getId().longValue() != fabricCreationMasterBean.getId().longValue()) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Description Already Exist!")).body(null);
        }
        String currentUser = SecurityUtils.getCurrentUserLogin().orElse(null);
        FabricCreationMaster fabricCreationMaster = new FabricCreationMaster();
        BeanUtils.copyProperties(fabricCreationMasterBean, fabricCreationMaster, "fabricCreationWeftDetails", "fabricCreationWarpDetails", "fabricCreationContentDetails");
        if (fabricCreationMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        fabricCreationWarpDetailsRepository.deleteAllByFabricCreationMasterId(fabricCreationMaster.getId());
        fabricCreationWeftDetailsRepository.deleteAllByFabricCreationMasterId(fabricCreationMaster.getId());
        fabricCreationContentDetailsRepository.deleteAllByFabricCreationMasterId(fabricCreationMaster.getId());

        fabricCreationMaster.setLastUpdatedBy(currentUser);
        fabricCreationMaster.setLastUpdatedDate(Instant.now());
        FabricCreationMaster result = fabricCreationMasterRepository.save(fabricCreationMaster);
        if (result != null) {
            if (fabricCreationMaster.getFabricCreationWarpDetails() != null && fabricCreationMaster.getFabricCreationWarpDetails().size() > 0) {
                long ctr = 0L;
                for (FabricCreationWarpDetails fabricCreationWarpDetail : fabricCreationMaster.getFabricCreationWarpDetails()) {
                    if (fabricCreationWarpDetail.getWarp1() != null && fabricCreationWarpDetail.getWarp1().length() > 0 &&
                        fabricCreationWarpDetail.getWarp2() != null && fabricCreationWarpDetail.getWarp2().length() > 0 &&
                        fabricCreationWarpDetail.getFabricUomMaster() != null) {
                        fabricCreationWarpDetail.setId(new FabricCreationWarpDetailsId(++ctr, result));
                        fabricCreationWarpDetailsRepository.save(fabricCreationWarpDetail);
                    }
                }
            }
            if (fabricCreationMasterBean.getFabricCreationWeftDetails() != null && fabricCreationMaster.getFabricCreationWeftDetails().size() > 0) {
                long ctr = 0L;
                for (FabricCreationWeftDetails fabricCreationWeftDetail : fabricCreationMaster.getFabricCreationWeftDetails()) {
                    if (fabricCreationWeftDetail.getWeft1() != null && fabricCreationWeftDetail.getWeft1().length() > 0 &&
                        fabricCreationWeftDetail.getWeft2() != null && fabricCreationWeftDetail.getWeft2().length() > 0 &&
                        fabricCreationWeftDetail.getFabricUomMaster() != null) {
                        fabricCreationWeftDetail.setId(new FabricCreationWeftDetailsId(++ctr, result));
                        fabricCreationWeftDetailsRepository.save(fabricCreationWeftDetail);
                    }
                }
            }
            if (fabricCreationMasterBean.getFabricCreationContentDetails() != null && fabricCreationMaster.getFabricCreationContentDetails().size() > 0) {
                long ctr = 0L;
                for (FabricCreationContentDetails fabricCreationContentDetails : fabricCreationMaster.getFabricCreationContentDetails()) {
                    if (fabricCreationContentDetails.getFabricContentMaster() != null && fabricCreationContentDetails.getPercentage() != null &&
                        fabricCreationContentDetails.getPercentage()>0) {
                        fabricCreationContentDetails.setId(new FabricCreationContentDetailsId(++ctr, result));
                        fabricCreationContentDetailsRepository.save(fabricCreationContentDetails);
                    }
                }
            }
        }
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fabricCreationMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /fabric-creation-masters : get all the fabricCreationMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of fabricCreationMasters in body
     */
    @GetMapping("/fabric-creation-masters")
    @Timed
    public ResponseEntity<List<FabricCreationMaster>> getAllFabricCreationMasters(Pageable pageable) {
        log.debug("REST request to get a page of FabricCreationMasters");
        Page<FabricCreationMaster> page = fabricCreationMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/fabric-creation-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * POST  /fabric-creation-masters : get all the fabricCreationMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of fabricCreationMasters in body
     */
    @PostMapping("/fabric-creation-masters-custom")
    public ResponseEntity<List<FabricCreationMaster>> searchAllFabricCreationMasters(@RequestBody Master search) {
        log.debug("REST request to get a page of FabricContentMasters");
        String desc = "%";
        if (search.getDesc() != null) {
            desc = search.getDesc().toUpperCase() + "%";
        }
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("description").ascending()));
        Page<FabricCreationMaster> page = fabricCreationMasterRepository.findAllByCodeAndDesc(desc, desc, search.getPage());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/fabric-content-masters-search");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /fabric-creation-masters/:id : get the "id" fabricCreationMaster.
     *
     * @param id the id of the fabricCreationMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fabricCreationMaster, or with status 404 (Not Found)
     */
    @GetMapping("/fabric-creation-masters/{id}")
    @Timed
    public ResponseEntity<FabricCreationMaster> getFabricCreationMaster(@PathVariable Long id) {
        log.debug("REST request to get FabricCreationMaster : {}", id);
        Optional<FabricCreationMaster> fabricCreationMaster = fabricCreationMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(fabricCreationMaster);
    }

    /**
     * DELETE  /fabric-creation-masters/:id : delete the "id" fabricCreationMaster.
     *
     * @param id the id of the fabricCreationMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/fabric-creation-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteFabricCreationMaster(@PathVariable Long id) {
        log.debug("REST request to delete FabricCreationMaster : {}", id);

        fabricCreationWarpDetailsRepository.deleteAllByFabricCreationMasterId(id);
        fabricCreationWeftDetailsRepository.deleteAllByFabricCreationMasterId(id);
        fabricCreationContentDetailsRepository.deleteAllByFabricCreationMasterId(id);
        fabricCreationMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
