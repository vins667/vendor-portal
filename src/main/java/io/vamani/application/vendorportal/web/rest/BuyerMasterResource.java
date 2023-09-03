package io.vamani.application.vendorportal.web.rest;

import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.domain.FabricContentMaster;
import io.vamani.application.model.Master;
import io.vamani.application.vendorportal.domain.BuyerMaster;
import io.vamani.application.vendorportal.repository.BuyerMasterRepository;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing BuyerMaster.
 */
@RestController
@RequestMapping("/api")
public class BuyerMasterResource {

    private final Logger log = LoggerFactory.getLogger(BuyerMasterResource.class);

    private static final String ENTITY_NAME = "buyerMaster";

    private final BuyerMasterRepository buyerMasterRepository;

    public BuyerMasterResource(BuyerMasterRepository buyerMasterRepository) {
        this.buyerMasterRepository = buyerMasterRepository;
    }

    /**
     * POST  /buyer-masters : Create a new buyerMaster.
     *
     * @param buyerMaster the buyerMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new buyerMaster, or with status 400 (Bad Request) if the buyerMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/buyer-masters")
    public ResponseEntity<BuyerMaster> createBuyerMaster(@Valid @RequestBody BuyerMaster buyerMaster) throws URISyntaxException {
        log.debug("REST request to save BuyerMaster : {}", buyerMaster);
        if (buyerMaster.getId() != null) {
            throw new BadRequestAlertException("A new buyerMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BuyerMaster result = buyerMasterRepository.save(buyerMaster);
        return ResponseEntity.created(new URI("/api/buyer-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /buyer-masters : Updates an existing buyerMaster.
     *
     * @param buyerMaster the buyerMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated buyerMaster,
     * or with status 400 (Bad Request) if the buyerMaster is not valid,
     * or with status 500 (Internal Server Error) if the buyerMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/buyer-masters")
    public ResponseEntity<BuyerMaster> updateBuyerMaster(@Valid @RequestBody BuyerMaster buyerMaster) throws URISyntaxException {
        log.debug("REST request to update BuyerMaster : {}", buyerMaster);
        if (buyerMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BuyerMaster result = buyerMasterRepository.save(buyerMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, buyerMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /buyer-masters : get all the buyerMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of buyerMasters in body
     */
    @GetMapping("/buyer-masters")
    public ResponseEntity<List<BuyerMaster>> getAllBuyerMasters(Pageable pageable) {
        log.debug("REST request to get a page of BuyerMasters");
        Page<BuyerMaster> page = buyerMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/buyer-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /buyer-masters/:id : get the "id" buyerMaster.
     *
     * @param id the id of the buyerMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the buyerMaster, or with status 404 (Not Found)
     */
    @GetMapping("/buyer-masters/{id}")
    public ResponseEntity<BuyerMaster> getBuyerMaster(@PathVariable Long id) {
        log.debug("REST request to get BuyerMaster : {}", id);
        Optional<BuyerMaster> buyerMaster = buyerMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(buyerMaster);
    }

    /**
     * DELETE  /buyer-masters/:id : delete the "id" buyerMaster.
     *
     * @param id the id of the buyerMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/buyer-masters/{id}")
    public ResponseEntity<Void> deleteBuyerMaster(@PathVariable Long id) {
        log.debug("REST request to delete BuyerMaster : {}", id);
        buyerMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code POST  /fabric-content-masters} : get all the fabricContentMasters.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fabricContentMasters in body.
     */
    @PostMapping("/buyer-masters-search")
    public ResponseEntity<List<BuyerMaster>> searchAllFBuyerMasters(@RequestBody Master search) {
        log.debug("REST request to get a page of FabricContentMasters");
        String desc = "%";
        if (search.getDesc() != null) {
            desc = search.getDesc().toUpperCase() + "%";
        }
        if (search.getSize() == 0) {
            search.setSize(Integer.MAX_VALUE);
        }
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("buyerName").ascending()));
        Page<BuyerMaster> page = buyerMasterRepository.findAllByCodeAndDesc(desc, desc, search.getPage());
        HttpHeaders headers = io.vamani.application.web.rest.util.PaginationUtil.generatePaginationHttpHeaders(page, "/api/buyer-masters-search");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
