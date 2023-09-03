package io.vamani.application.web.rest;

import io.vamani.application.domain.*;
import io.vamani.application.model.KnitCreationSearchBean;
import io.vamani.application.model.Master;
import io.vamani.application.repository.KnitCreationMasterRepository;
import io.vamani.application.repository.KnitProcessMasterRepository;
import io.vamani.application.repository.KnitTypeMasterRepository;
import io.vamani.application.repository.YarnCountMasterRepository;
import io.vamani.application.repository.YarnTypeMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import com.codahale.metrics.annotation.Timed;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link KnitCreationMaster}.
 */
@RestController
@RequestMapping("/api")
public class KnitCreationMasterResource {

    private final Logger log = LoggerFactory.getLogger(KnitCreationMasterResource.class);

    private static final String ENTITY_NAME = "knitCreationMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final KnitCreationMasterRepository knitCreationMasterRepository;
    
    @Autowired
    private YarnCountMasterRepository yarnCountMasterRepository;
    
    @Autowired
    private YarnTypeMasterRepository yarnTypeMasterRepository;
    
    @Autowired
    private KnitTypeMasterRepository knitTypeMasterRepository;
    
    @Autowired
    private KnitProcessMasterRepository knitProcessMasterRepository;
    
    public KnitCreationMasterResource(KnitCreationMasterRepository knitCreationMasterRepository) {
        this.knitCreationMasterRepository = knitCreationMasterRepository;
    }

    /**
     * {@code POST  /knit-creation-masters} : Create a new knitCreationMaster.
     *
     * @param knitCreationMaster the knitCreationMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new knitCreationMaster, or with status {@code 400 (Bad Request)} if the knitCreationMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/knit-creation-masters")
    public ResponseEntity<KnitCreationMaster> createKnitCreationMaster(@Valid @RequestBody KnitCreationMaster knitCreationMaster) throws URISyntaxException {
        log.debug("REST request to save KnitCreationMaster : {}", knitCreationMaster);
        if (knitCreationMaster.getId() != null) {
            throw new BadRequestAlertException("A new knitCreationMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        String currentUser = SecurityUtils.getCurrentUserLogin().orElse(null);
        KnitCreationMaster creationMaster = knitCreationMasterRepository.findByDescription(knitCreationMaster.getDescription());
        if (creationMaster != null) {
            return ResponseEntity.badRequest().headers(io.vamani.application.web.rest.util.HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Description Already Exist!")).body(null);
        }
        knitCreationMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        knitCreationMaster.setCreatedDate(Instant.now());
        KnitCreationMaster result = knitCreationMasterRepository.save(knitCreationMaster);
        return ResponseEntity.created(new URI("/api/knit-creation-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /knit-creation-masters} : Updates an existing knitCreationMaster.
     *
     * @param knitCreationMaster the knitCreationMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated knitCreationMaster,
     * or with status {@code 400 (Bad Request)} if the knitCreationMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the knitCreationMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/knit-creation-masters")
    public ResponseEntity<KnitCreationMaster> updateKnitCreationMaster(@Valid @RequestBody KnitCreationMaster knitCreationMaster) throws URISyntaxException {
        log.debug("REST request to update KnitCreationMaster : {}", knitCreationMaster);
        if (knitCreationMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        KnitCreationMaster creationMaster = knitCreationMasterRepository.findByDescription(knitCreationMaster.getDescription());
        if(creationMaster != null && creationMaster.getId().longValue() != knitCreationMaster.getId().longValue()) {
            return ResponseEntity.badRequest().headers(io.vamani.application.web.rest.util.HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Description Already Exist!")).body(null);
        }
        knitCreationMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        knitCreationMaster.setLastUpdatedDate(Instant.now());
        KnitCreationMaster result = knitCreationMasterRepository.save(knitCreationMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, knitCreationMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /knit-creation-masters} : get all the knitCreationMasters.
     *
     * @param !pageable the pagination information.
     * @param !queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of knitCreationMasters in body.
     */
    @PostMapping("/knit-creation-masters-count")
    @Timed
    public ResponseEntity<List<YarnCountMaster>> getAllYarnCountMasters(@Valid @RequestBody KnitCreationSearchBean search ,UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of AssetAuditDetails");
        String code = "%";
        String name = "%";
        if (search.getCode() != null) {
        	code = search.getCode().trim() + "%";
        }
        if (search.getName() != null) {
            name = search.getName().trim() + "%";
        }
        Page<YarnCountMaster> page = null;
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize()));
        page = yarnCountMasterRepository.findByFilter(code,name,search.getPage());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder,page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    
    @PostMapping("/knit-creation-masters-type")
    @Timed
    public ResponseEntity<List<YarnTypeMaster>> getAllYarnTypeMasters(@Valid @RequestBody KnitCreationSearchBean search ,UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of AssetAuditDetails");
        String code = "%";
        String name = "%";
        if (search.getCode() != null) {
        	code = search.getCode().trim() + "%";
        }
        if (search.getName() != null) {
            name = search.getName().trim() + "%";
        }
        Page<YarnTypeMaster> page = null;
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize()));
        page = yarnTypeMasterRepository.findByFilter(code,name,search.getPage());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder,page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    
    @PostMapping("/knit-creation-masters-knit")
    @Timed
    public ResponseEntity<List<KnitTypeMaster>> getAllKnitTypeMasters(@Valid @RequestBody KnitCreationSearchBean search ,UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of AssetAuditDetails");
        String code = "%";
        String name = "%";
        if (search.getCode() != null) {
        	code = search.getCode().trim() + "%";
        }
        if (search.getName() != null) {
            name = search.getName().trim() + "%";
        }
        Page<KnitTypeMaster> page = null;
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize()));
        page = knitTypeMasterRepository.findByFilter(code,name,search.getPage());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder,page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    
    @PostMapping("/knit-creation-masters-process")
    @Timed
    public ResponseEntity<List<KnitProcessMaster>> getAllKnitProcessMasters(@Valid @RequestBody KnitCreationSearchBean search ,UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of AssetAuditDetails");
        String code = "%";
        String name = "%";
        if (search.getCode() != null) {
        	code = search.getCode().trim() + "%";
        }
        if (search.getName() != null) {
            name = search.getName().trim() + "%";
        }
        Page<KnitProcessMaster> page = null;
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize()));
        page = knitProcessMasterRepository.findByFilter(code,name,search.getPage());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder,page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    
    /**
     * {@code GET  /knit-creation-masters} : get all the knitCreationMasters.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of knitCreationMasters in body.
     */
    @GetMapping("/knit-creation-masters")
    public ResponseEntity<List<KnitCreationMaster>> getAllKnitCreationMasters(@PageableDefault(value = Integer.MAX_VALUE)  @SortDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of KnitCreationMasters");
        Page<KnitCreationMaster> page = knitCreationMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * POST  /fabric-creation-masters : get all the knitCreationMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of knitCreationMasters in body
     */
    @PostMapping("/knit-creation-masters-custom")
    public ResponseEntity<List<KnitCreationMaster>> searchAllKnitCreationMasters(@RequestBody Master search) {
        log.debug("REST request to get a page of FabricContentMasters");
        String desc = "%";
        if (search.getDesc() != null) {
            desc = search.getDesc().toUpperCase() + "%";
        }
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("description").ascending()));
        Page<KnitCreationMaster> page = knitCreationMasterRepository.findAllByCodeAndDesc(desc, desc, search.getPage());
        HttpHeaders headers = io.vamani.application.web.rest.util.PaginationUtil.generatePaginationHttpHeaders(page, "/api/fabric-content-masters-search");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /knit-creation-masters/:id} : get the "id" knitCreationMaster.
     *
     * @param id the id of the knitCreationMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the knitCreationMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/knit-creation-masters/{id}")
    public ResponseEntity<KnitCreationMaster> getKnitCreationMaster(@PathVariable Long id) {
        log.debug("REST request to get KnitCreationMaster : {}", id);
        Optional<KnitCreationMaster> knitCreationMaster = knitCreationMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(knitCreationMaster);
    }

    /**
     * {@code DELETE  /knit-creation-masters/:id} : delete the "id" knitCreationMaster.
     *
     * @param id the id of the knitCreationMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/knit-creation-masters/{id}")
    public ResponseEntity<Void> deleteKnitCreationMaster(@PathVariable Long id) {
        log.debug("REST request to delete KnitCreationMaster : {}", id);
        knitCreationMasterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
