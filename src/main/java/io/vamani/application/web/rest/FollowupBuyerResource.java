package io.vamani.application.web.rest;

import io.vamani.application.domain.BillRegisterMaster;
import io.vamani.application.domain.FollowupBuyer;
import io.vamani.application.model.FollowupBuyerBean;
import io.vamani.application.model.JobWorkFollowupBean;
import io.vamani.application.model.MasterSearch;
import io.vamani.application.repository.FollowupBuyerRepository;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * REST controller for managing {@link FollowupBuyer}.
 */
@RestController
@RequestMapping("/api")
public class FollowupBuyerResource {

    private final Logger log = LoggerFactory.getLogger(FollowupBuyerResource.class);

    private static final String ENTITY_NAME = "followupBuyer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FollowupBuyerRepository followupBuyerRepository;

    public FollowupBuyerResource(FollowupBuyerRepository followupBuyerRepository) {
        this.followupBuyerRepository = followupBuyerRepository;
    }

    /**
     * {@code POST  /followup-buyers} : Create a new followupBuyer.
     *
     * @param followupBuyerBean the followupBuyer to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new followupBuyer, or with status {@code 400 (Bad Request)} if the followupBuyer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/followup-buyers")
    public ResponseEntity<FollowupBuyer> createFollowupBuyer(@Valid @RequestBody FollowupBuyerBean followupBuyerBean) throws URISyntaxException, InvocationTargetException, IllegalAccessException {
        log.debug("REST request to save FollowupBuyer : {}", followupBuyerBean);
        if (followupBuyerBean.getId() != null) {
            throw new BadRequestAlertException("A new followupBuyer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FollowupBuyer followupBuyer =new FollowupBuyer();
        BeanUtils.copyProperties(followupBuyerBean, followupBuyer);
        followupBuyer.setCreatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
        followupBuyer.setCreateddate(Instant.now());
        FollowupBuyer result = followupBuyerRepository.save(followupBuyer);
        return ResponseEntity.created(new URI("/api/followup-buyers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);

    }

  /*  @PostMapping("/select-followup-buyers")
    public ResponseEntity<List<FollowupBuyer>>getAllBuyerCustom(@Valid @RequestBody FollowupBuyerBean followupBuyerBean){
        FollowupBuyer followupBuyer=followupBuyerRepository.findByCode(SecurityUtils.getCurrentUserLogin().orElse(null)).orElse(null);
    String buyercode="%";
    String buyername="%";
    String flage="%";
        if (followupBuyerBean.getBuyercode() != null) {
            buyercode ="%" + followupBuyerBean.getBuyercode() + "%";
        }
        if (followupBuyerBean.getBuyername() != null) {
            buyername = "%" + followupBuyerBean.getBuyername() + "%";
        }
        if (followupBuyerBean.getFlag() != null) {
            flage = "%" + followupBuyerBean.getFlag() + "%";
        }
    }*/

    /**
     * {@code PUT  /followup-buyers} : Updates an existing followupBuyer.
     *
     * @param followupBuyerBean the followupBuyer to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated followupBuyer,
     * or with status {@code 400 (Bad Request)} if the followupBuyer is not valid,
     * or with status {@code 500 (Internal Server Error)} if the followupBuyer couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/followup-buyers")
    public ResponseEntity<FollowupBuyer> updateFollowupBuyer(@Valid @RequestBody FollowupBuyerBean followupBuyerBean) throws URISyntaxException {
        log.debug("REST request to update FollowupBuyer : {}", followupBuyerBean);
        if (followupBuyerBean.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(followupBuyerBean.getId(), followupBuyerBean.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }
        if (!followupBuyerRepository.existsById(followupBuyerBean.getId())) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        FollowupBuyer followupBuyer = new FollowupBuyer();
        org.springframework.beans.BeanUtils.copyProperties(followupBuyerBean, followupBuyer);
        followupBuyer.setUpdatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
        followupBuyer.setUpdateddate(Instant.now());
        FollowupBuyer result = followupBuyerRepository.save(followupBuyer);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, followupBuyer.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /followup-buyers} : get all the followupBuyers.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of followupBuyers in body.
     */
    @GetMapping("/followup-buyers")
    public ResponseEntity<List<FollowupBuyer>> getAllFollowupBuyers(Pageable pageable) {
        log.debug("REST request to get a page of FollowupBuyers");
        Page<FollowupBuyer> page = followupBuyerRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /followup-buyers} : get all the followupBuyers.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of followupBuyers in body.
     */
    @PostMapping("/followup-buyers-filter")
    public ResponseEntity<List<FollowupBuyer>> getAllFollowupBuyers(@RequestBody MasterSearch search) {
        log.debug("REST request to get a page of FollowupBuyers");
        String buyercode = "%";
        if (search.getCode() != null && search.getCode()!=null) {
            buyercode = "%"+search.getCode().toUpperCase()+"%";
        }
        String buyername = "%";
        if (search.getDescription() != null && search.getDescription()!=null) {
            buyername = "%"+search.getDescription().toUpperCase()+"%";
        }
        search.setPage(
            PageRequest.of(
                search.getPageNo(),
                search.getSize(),
                search.getSortType() != null && search.getSortType().equalsIgnoreCase("desc")
                    ? Sort.by(search.getSort()).descending()
                    : Sort.by(search.getSort()).ascending()
            )
        );
        Page<FollowupBuyer> page = followupBuyerRepository.findByBuyercodeAndBuyername(buyercode, buyername, search.getPage());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /followup-buyers/:id} : get the "id" followupBuyer.
     *
     * @param id the id of the followupBuyer to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the followupBuyer, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/followup-buyers/{id}")
    public ResponseEntity<FollowupBuyer> getFollowupBuyer(@PathVariable Long id) {
        log.debug("REST request to get FollowupBuyer : {}", id);
        Optional<FollowupBuyer> followupBuyer = followupBuyerRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(followupBuyer);
    }

    /**
     * {@code DELETE  /followup-buyers/:id} : delete the "id" followupBuyer.
     *
     * @param id the id of the followupBuyer to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/followup-buyers/{id}")
    public ResponseEntity<Void> deleteFollowupBuyer(@PathVariable Long id) {
        log.debug("REST request to delete FollowupBuyer : {}", id);
        followupBuyerRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
