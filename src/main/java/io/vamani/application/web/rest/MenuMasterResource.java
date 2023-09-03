package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.MenuMaster;
import io.vamani.application.model.Master;
import io.vamani.application.repository.MenuMasterRepository;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing MenuMaster.
 */
@RestController
@RequestMapping("/api")
public class MenuMasterResource {

    private final Logger log = LoggerFactory.getLogger(MenuMasterResource.class);

    private static final String ENTITY_NAME = "menuMaster";

    private final MenuMasterRepository menuMasterRepository;

    public MenuMasterResource(MenuMasterRepository menuMasterRepository) {
        this.menuMasterRepository = menuMasterRepository;
    }

    /**
     * POST  /menu-masters : Create a new menuMaster.
     *
     * @param menuMaster the menuMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new menuMaster, or with status 400 (Bad Request) if the menuMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/menu-masters")
    @Timed
    public ResponseEntity<MenuMaster> createMenuMaster(@Valid @RequestBody MenuMaster menuMaster) throws URISyntaxException {
        log.debug("REST request to save MenuMaster : {}", menuMaster);
        if (menuMaster.getId() != null) {
            throw new BadRequestAlertException("A new menuMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MenuMaster result = menuMasterRepository.save(menuMaster);
        return ResponseEntity.created(new URI("/api/menu-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /menu-masters : Updates an existing menuMaster.
     *
     * @param menuMaster the menuMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated menuMaster,
     * or with status 400 (Bad Request) if the menuMaster is not valid,
     * or with status 500 (Internal Server Error) if the menuMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/menu-masters")
    @Timed
    public ResponseEntity<MenuMaster> updateMenuMaster(@Valid @RequestBody MenuMaster menuMaster) throws URISyntaxException {
        log.debug("REST request to update MenuMaster : {}", menuMaster);
        if (menuMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MenuMaster result = menuMasterRepository.save(menuMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, menuMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /menu-masters : get all the menuMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of menuMasters in body
     */
    @GetMapping("/menu-masters")
    @Timed
    public ResponseEntity<List<MenuMaster>> getAllMenuMasters(@PageableDefault(value = Integer.MAX_VALUE) Pageable pageable) {
        log.debug("REST request to get a page of MenuMasters");
        Page<MenuMaster> page = menuMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/menu-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /menu-masters/:id : get the "id" menuMaster.
     *
     * @param id the id of the menuMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the menuMaster, or with status 404 (Not Found)
     */
    @GetMapping("/menu-masters/{id}")
    @Timed
    public ResponseEntity<MenuMaster> getMenuMaster(@PathVariable Long id) {
        log.debug("REST request to get MenuMaster : {}", id);
        Optional<MenuMaster> menuMaster = menuMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(menuMaster);
    }

    /**
     * GET  /menu-masters/:id : get the "id" menuMaster.
     *
     * @param id the id of the menuMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the menuMaster, or with status 404 (Not Found)
     */
    @PostMapping("/menu-masters-qlik")
    @Timed
    public ResponseEntity<MenuMaster> getMenuMasterQlik(@RequestBody Master master) {
        log.debug("REST request to get MenuMaster : {}", master);
        MenuMaster menuMaster = menuMasterRepository.findByTypeAndMenuLink(master.getId(), master.getDesc()+"/"+master.getId());
        return ResponseUtil.wrapOrNotFound(Optional.of(menuMaster));
    }

    /**
     * DELETE  /menu-masters/:id : delete the "id" menuMaster.
     *
     * @param id the id of the menuMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/menu-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteMenuMaster(@PathVariable Long id) {
        log.debug("REST request to delete MenuMaster : {}", id);

        menuMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
