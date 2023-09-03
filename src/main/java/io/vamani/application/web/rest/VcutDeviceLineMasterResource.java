package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.User;
import io.vamani.application.domain.VcutDeviceLineMaster;
import io.vamani.application.domain.VcutUserDeviceMaster;
import io.vamani.application.domain.YarnCountMaster;
import io.vamani.application.model.EmployeeSearch;
import io.vamani.application.mssql.domain.EmployeeView;
import io.vamani.application.mssql.repository.EmployeeViewRepository;
import io.vamani.application.repository.UserRepository;
import io.vamani.application.repository.VcutDeviceLineMasterRepository;
import io.vamani.application.repository.VcutUserDeviceMasterRepository;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.sql.Time;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link VcutDeviceLineMaster}.
 */
@RestController
@RequestMapping("/api")
public class VcutDeviceLineMasterResource {

    private final Logger log = LoggerFactory.getLogger(VcutDeviceLineMasterResource.class);

    private static final String ENTITY_NAME = "vcutDeviceLineMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VcutUserDeviceMasterRepository vcutUserDeviceMasterRepository;

    private final VcutDeviceLineMasterRepository vcutDeviceLineMasterRepository;

    public VcutDeviceLineMasterResource(VcutDeviceLineMasterRepository vcutDeviceLineMasterRepository) {
        this.vcutDeviceLineMasterRepository = vcutDeviceLineMasterRepository;
    }

    /**
     * {@code POST  /vcut-device-line-masters} : Create a new vcutDeviceLineMaster.
     *
     * @param vcutDeviceLineMaster the vcutDeviceLineMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vcutDeviceLineMaster, or with status {@code 400 (Bad Request)} if the vcutDeviceLineMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/vcut-device-line-masters")
    public ResponseEntity<VcutDeviceLineMaster> createVcutDeviceLineMaster(@Valid @RequestBody VcutDeviceLineMaster vcutDeviceLineMaster) throws URISyntaxException {
        log.debug("REST request to save VcutDeviceLineMaster : {}", vcutDeviceLineMaster);
        if (vcutDeviceLineMaster.getId() != null) {
            throw new BadRequestAlertException("A new vcutDeviceLineMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VcutDeviceLineMaster cutDeviceLineMaster = vcutDeviceLineMasterRepository.findByLine(vcutDeviceLineMaster.getLine().toUpperCase().trim());
        if(cutDeviceLineMaster != null) {
            return ResponseEntity.badRequest()
                .headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "Line No already Exist"))
                .build();
        } else {
            VcutDeviceLineMaster cutDeviceLineMasterDeviceId = vcutDeviceLineMasterRepository.findByDeviceId(vcutDeviceLineMaster.getDeviceId().toUpperCase().trim());
            if(cutDeviceLineMasterDeviceId != null) {
                return ResponseEntity.badRequest()
                    .headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "Device Id already assigned to other device"))
                    .build();
            } else {
                vcutDeviceLineMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                vcutDeviceLineMaster.setCreatedDate(Instant.now());
                VcutDeviceLineMaster result = vcutDeviceLineMasterRepository.save(vcutDeviceLineMaster);
                if (result != null) {
                    for (VcutUserDeviceMaster bean : vcutDeviceLineMaster.getVcutUserDeviceMaster()) {
                        bean.setVcutDeviceLineMaster(result);
                        bean.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                        bean.setCreatedDate(Instant.now());
                        vcutUserDeviceMasterRepository.save(bean);
                    }
                }
                return ResponseEntity.created(new URI("/api/vcut-device-line-masters/" + result.getId()))
                    .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                    .body(result);
            }
        }
    }

    /**
     * post method to get the Employee collection based on parameters employee ID & employee Name
     */
    @PostMapping("/vcut-device-line-masters-search-employees")
    @Timed
    public ResponseEntity<List<VcutUserDeviceMaster>> GetSearchEmpListBtId(@Valid @RequestBody EmployeeSearch search, UriComponentsBuilder uriBuilder) {
        String code = "%";
        String name = "%";
        if (search.getEmpCode() != null) {
            code = search.getEmpCode().trim().toUpperCase() + "%";
        }
        if (search.getName() != null) {
            name = search.getName().trim().toUpperCase() + "%";
        }
        Page<User> page = null;
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize()));
        page = userRepository.findAllCodeAndName(code, name, search.getPage());
        List<VcutUserDeviceMaster> deviceMasters = new ArrayList<>();
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder, page);
        if (page != null && page.getContent() != null) {
            for (User user : page.getContent()) {
                VcutUserDeviceMaster deviceMaster = new VcutUserDeviceMaster();
                deviceMaster.setUser(user);
                deviceMasters.add(deviceMaster);
            }
        }
        return ResponseEntity.ok().headers(headers).body(deviceMasters);
    }

    /**
     * {@code PUT  /vcut-device-line-masters} : Updates an existing vcutDeviceLineMaster.
     *
     * @param vcutDeviceLineMaster the vcutDeviceLineMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vcutDeviceLineMaster,
     * or with status {@code 400 (Bad Request)} if the vcutDeviceLineMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vcutDeviceLineMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/vcut-device-line-masters")
    public ResponseEntity<VcutDeviceLineMaster> updateVcutDeviceLineMaster(@Valid @RequestBody VcutDeviceLineMaster vcutDeviceLineMaster) throws URISyntaxException {
        log.debug("REST request to update VcutDeviceLineMaster : {}", vcutDeviceLineMaster);
        if (vcutDeviceLineMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VcutDeviceLineMaster cutDeviceLineMasterDeviceId = vcutDeviceLineMasterRepository.findByDeviceId(vcutDeviceLineMaster.getDeviceId().toUpperCase().trim());
        if (cutDeviceLineMasterDeviceId != null && vcutDeviceLineMaster.getId().longValue() != cutDeviceLineMasterDeviceId.getId().longValue()) {
            return ResponseEntity.badRequest()
                .headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "Device Id already assigned to other device"))
                .build();
        } else {
            vcutDeviceLineMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
            vcutDeviceLineMaster.setCreatedDate(Instant.now());
            VcutDeviceLineMaster result = vcutDeviceLineMasterRepository.save(vcutDeviceLineMaster);
            if (result != null) {
                for (VcutUserDeviceMaster bean : vcutDeviceLineMaster.getVcutUserDeviceMaster()) {
                    bean.setVcutDeviceLineMaster(result);
                    bean.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                    bean.setCreatedDate(Instant.now());
                    vcutUserDeviceMasterRepository.save(bean);
                }
            }
            return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, vcutDeviceLineMaster.getId().toString()))
                .body(result);
        }
    }

    /**
     * {@code GET  /vcut-device-line-masters} : get all the vcutDeviceLineMasters.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vcutDeviceLineMasters in body.
     */
    @GetMapping("/vcut-device-line-masters")
    public ResponseEntity<List<VcutDeviceLineMaster>> getAllVcutDeviceLineMasters(Pageable pageable) {
        log.debug("REST request to get a page of VcutDeviceLineMasters");
        Page<VcutDeviceLineMaster> page = vcutDeviceLineMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /vcut-device-line-masters/:id} : get the "id" vcutDeviceLineMaster.
     *
     * @param id the id of the vcutDeviceLineMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vcutDeviceLineMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/vcut-device-line-masters/{id}")
    public ResponseEntity<VcutDeviceLineMaster> getVcutDeviceLineMaster(@PathVariable Long id) {
        log.debug("REST request to get VcutDeviceLineMaster : {}", id); 
        VcutDeviceLineMaster vcutDeviceLineMaster = vcutDeviceLineMasterRepository.findByVcutDeviceLine(id);
        vcutDeviceLineMaster.setVcutUserDeviceMaster(vcutUserDeviceMasterRepository.findByVcutDeviceLineMaster(id));
        return ResponseUtil.wrapOrNotFound(Optional.of(vcutDeviceLineMaster));
    }

    /**
     * {@code DELETE  /vcut-device-line-masters/:id} : delete the "id" vcutDeviceLineMaster.
     *
     * @param id the id of the vcutDeviceLineMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/vcut-device-line-masters/{id}")
    public ResponseEntity<Void> deleteVcutDeviceLineMaster(@PathVariable Long id) {
        log.debug("REST request to delete VcutDeviceLineMaster : {}", id);
        vcutUserDeviceMasterRepository.deleteAllDetailById(id);
        vcutDeviceLineMasterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
