package io.vamani.application.web.rest;

import io.vamani.application.domain.TrimTemplateDetailsBreakupId;
import io.vamani.application.domain.TrimsTemplateDetails;
import io.vamani.application.domain.TrimsTemplateDetailsBreakup;
import io.vamani.application.domain.TrimsTemplateMaster;
import io.vamani.application.model.TrimsTemplateMasterBean;
import io.vamani.application.model.TrimsTemplateDetailsBean;
import io.vamani.application.repository.TrimsTemplateDetailsBreakupRepository;
import io.vamani.application.repository.TrimsTemplateDetailsRepository;
import io.vamani.application.repository.TrimsTemplateMasterRepository;
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
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.*;

/**
 * REST controller for managing
 * {@link TrimsTemplateMaster}.
 */
@RestController
@RequestMapping("/api")
public class TrimsTemplateMasterResource {

    private final Logger log = LoggerFactory.getLogger(TrimsTemplateMasterResource.class);

    private static final String ENTITY_NAME = "trimsTemplateMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TrimsTemplateMasterRepository trimsTemplateMasterRepository;

    @Autowired
    private TrimsTemplateDetailsRepository trimsTemplateDetailsRepository;

    @Autowired
    private TrimsTemplateDetailsBreakupRepository trimsTemplateDetailsBreakupRepository;

    public TrimsTemplateMasterResource(TrimsTemplateMasterRepository trimsTemplateMasterRepository) {
        this.trimsTemplateMasterRepository = trimsTemplateMasterRepository;
    }

    /**
     * {@code POST  /trims-template-masters} : Create a new trimsTemplateMaster.
     *
     * @param trimsTemplateMaster the trimsTemplateMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new trimsTemplateMaster, or with status
     *         {@code 400 (Bad Request)} if the trimsTemplateMaster has already an
     *         ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/trims-template-masters")
    public ResponseEntity<TrimsTemplateMaster> createTrimsTemplateMaster(
        @Valid @RequestBody TrimsTemplateMasterBean trimTemplateMaster) throws URISyntaxException {
        log.debug("REST request to save TrimsTemplateMaster : {}", trimTemplateMaster);
        if (trimTemplateMaster.getId() != null) {
            throw new BadRequestAlertException("A new trimsTemplateMaster cannot already have an ID", ENTITY_NAME,
                "idexists");
        }
        TrimsTemplateMaster creationMaster = trimsTemplateMasterRepository.findByDescription(trimTemplateMaster.getDescription().trim().toUpperCase());
        if (creationMaster != null) {
            return ResponseEntity.badRequest().headers(io.vamani.application.web.rest.util.HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Description Already Exist!")).body(null);
        }

        TrimsTemplateMaster creationMaster2 = trimsTemplateMasterRepository.findByCode(trimTemplateMaster.getAccessoriesCode().trim().toUpperCase());
        if (creationMaster2 != null) {
            return ResponseEntity.badRequest().headers(io.vamani.application.web.rest.util.HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Code Already Exist!")).body(null);
        }

        String user = SecurityUtils.getCurrentUserLogin().orElse(null);
        TrimsTemplateMaster trimsTemplateMaster = new TrimsTemplateMaster();
        BeanUtils.copyProperties(trimTemplateMaster, trimsTemplateMaster, "trimsTemplateDetails");
        trimsTemplateMaster.setFlag("Y");
        trimsTemplateMaster.setCreatedBy(user);
        trimsTemplateMaster.setCreatedDate(Instant.now());
        TrimsTemplateMaster result = trimsTemplateMasterRepository.save(trimsTemplateMaster);
        if (result != null) {
            for (TrimsTemplateDetailsBean trimTemplateDetailBean : trimTemplateMaster.getTrimsTemplateDetails()) {
                if (trimTemplateDetailBean.getSpecification() != null && trimTemplateDetailBean.getSpecification().length() > 0) {
                    TrimsTemplateDetails trimsTemplateDetails = new TrimsTemplateDetails();
                    BeanUtils.copyProperties(trimTemplateDetailBean, trimsTemplateDetails);
                    trimsTemplateDetails.setTrimsTemplateMaster(result);
                    trimsTemplateDetails.setRequired(trimTemplateDetailBean.getRequired() != null && trimTemplateDetailBean.getRequired() == true ? true : false);
                    trimsTemplateDetails.setDisplay(trimTemplateDetailBean.getDisplay() != null && trimTemplateDetailBean.getDisplay() == true ? true : false);
                    TrimsTemplateDetails resultDetail = trimsTemplateDetailsRepository.save(trimsTemplateDetails);
                    if (resultDetail != null && resultDetail.getFieldType() != null && resultDetail.getFieldType().equalsIgnoreCase("D") && trimTemplateDetailBean.getFieldValue() != null && trimTemplateDetailBean.getFieldValue().length() > 0) {
                        String[] splitValues = trimTemplateDetailBean.getFieldValue().split(",");
                        Long ctr = 0L;
                        for (String value : splitValues) {
                            TrimsTemplateDetailsBreakup trimsTemplateDetailsBreakup = new TrimsTemplateDetailsBreakup();
                            trimsTemplateDetailsBreakup.setId(new TrimTemplateDetailsBreakupId(++ctr, resultDetail.getId()));
                            trimsTemplateDetailsBreakup.setDescription(value.trim().toUpperCase());
                            trimsTemplateDetailsBreakupRepository.save(trimsTemplateDetailsBreakup);
                        }
                    }
                }
            }
        }
        return ResponseEntity
            .created(new URI("/api/trims-template-masters/" + result.getId())).headers(HeaderUtil
                .createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /trims-template-masters} : Updates an existing
     * trimsTemplateMaster.
     *
     * @param trimsTemplateMaster the trimsTemplateMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated trimsTemplateMaster, or with status
     *         {@code 400 (Bad Request)} if the trimsTemplateMaster is not valid, or
     *         with status {@code 500 (Internal Server Error)} if the
     *         trimsTemplateMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/trims-template-masters")
    public ResponseEntity<TrimsTemplateMaster> updateTrimsTemplateMaster(
        @Valid @RequestBody TrimsTemplateMasterBean trimTemplateMaster) throws URISyntaxException {
        log.debug("REST request to update TrimsTemplateMaster : {}", trimTemplateMaster);
        TrimsTemplateMaster creationMaster = trimsTemplateMasterRepository.findByDescription(trimTemplateMaster.getDescription().trim().toUpperCase());
        if (creationMaster != null && creationMaster.getId().longValue() != trimTemplateMaster.getId().longValue()) {
            return ResponseEntity.badRequest().headers(io.vamani.application.web.rest.util.HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Description Already Exist!")).body(null);
        }

        TrimsTemplateMaster creationMaster2 = trimsTemplateMasterRepository.findByCode(trimTemplateMaster.getAccessoriesCode().trim().toUpperCase());
        if (creationMaster2 != null && creationMaster2.getId().longValue() != trimTemplateMaster.getId().longValue()) {
            return ResponseEntity.badRequest().headers(io.vamani.application.web.rest.util.HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Code Already Exist!")).body(null);
        }

        String user = SecurityUtils.getCurrentUserLogin().orElse(null);
        TrimsTemplateMaster trimsTemplateMaster = new TrimsTemplateMaster();
        BeanUtils.copyProperties(trimTemplateMaster, trimsTemplateMaster, "trimsTemplateDetails");
        if (trimsTemplateMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        trimsTemplateMaster.setLastUpdatedBy(user);
        trimsTemplateMaster.setLastUpdatedDate(Instant.now());
        TrimsTemplateMaster result = trimsTemplateMasterRepository.save(trimsTemplateMaster);
        if (result != null) {
            for (TrimsTemplateDetailsBean trimTemplateDetailBean : trimTemplateMaster.getTrimsTemplateDetails()) {
                if (trimTemplateDetailBean.getSpecification() != null && trimTemplateDetailBean.getSpecification().length() > 0) {
                    TrimsTemplateDetails trimsTemplateDetails = new TrimsTemplateDetails();
                    BeanUtils.copyProperties(trimTemplateDetailBean, trimsTemplateDetails, "trimTemplateDetailsBreakup");
                    trimsTemplateDetails.setTrimsTemplateMaster(result);
                    trimsTemplateDetails.setRequired(trimTemplateDetailBean.getRequired() != null && trimTemplateDetailBean.getRequired() == true ? true : false);
                    trimsTemplateDetails.setDisplay(trimTemplateDetailBean.getDisplay() != null && trimTemplateDetailBean.getDisplay() == true ? true : false);
                    TrimsTemplateDetails resultDetail = trimsTemplateDetailsRepository.save(trimsTemplateDetails);
                    if (resultDetail != null && resultDetail.getFieldType() != null && resultDetail.getFieldType().equalsIgnoreCase("D") && trimTemplateDetailBean.getTrimTemplateDetailsBreakup() != null
                        && trimTemplateDetailBean.getTrimTemplateDetailsBreakup().size() > 0) {
                        for (TrimsTemplateDetailsBreakup trimTemplateDetailsBreakup : trimTemplateDetailBean.getTrimTemplateDetailsBreakup()) {
                            if (trimTemplateDetailsBreakup.getDescription() != null && trimTemplateDetailsBreakup.getDescription().length() > 0) {
                                if (trimTemplateDetailsBreakup.getId() != null && trimTemplateDetailsBreakup.getId().getId() != null &&
                                    trimTemplateDetailsBreakup.getId().getId().longValue() > 0) {
                                } else {
                                    Long id = trimsTemplateDetailsBreakupRepository.findMaxSeries(resultDetail.getId());
                                    trimTemplateDetailsBreakup.setId(new TrimTemplateDetailsBreakupId(id, resultDetail.getId()));
                                }
                                trimsTemplateDetailsBreakupRepository.save(trimTemplateDetailsBreakup);
                            }
                        }
                    }
                }
            }
        }
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME,
            trimsTemplateMaster.getId().toString())).body(result);
    }

    /**
     * {@code GET  /trims-template-masters} : get all the trimsTemplateMasters.
     *
     * @param pageable    the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder  a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of trimsTemplateMasters in body.
     */
    @GetMapping("/trims-template-masters")
    public ResponseEntity<List<TrimsTemplateMaster>> getAllTrimsTemplateMasters(@PageableDefault(value = Integer.MAX_VALUE)  @SortDefault(sort = "accessoriesCode", direction = Sort.Direction.ASC) Pageable pageable,
                                                                                @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of TrimsTemplateMasters");
        Page<TrimsTemplateMaster> page = trimsTemplateMasterRepository.findAll(pageable);
        List<TrimsTemplateMaster> trimsTemplateMasters = new ArrayList<>();
        for (TrimsTemplateMaster trimsTemplateMaster : page.getContent()) {
            List<TrimsTemplateDetails> detailsList = new ArrayList<TrimsTemplateDetails>(trimsTemplateMaster.getTrimsTemplateDetails());
            Collections.sort(detailsList, Comparator.comparing(TrimsTemplateDetails :: getId));
            for (TrimsTemplateDetails templateDetails : detailsList) {
                if (templateDetails.getTrimTemplateDetailsBreakup() != null && templateDetails.getTrimTemplateDetailsBreakup().size() > 0) {
                    List<TrimsTemplateDetailsBreakup> trimsTemplateDetailsBreakups = new ArrayList<>(templateDetails.getTrimTemplateDetailsBreakup());
                    Collections.sort(trimsTemplateDetailsBreakups, Comparator.comparing(TrimsTemplateDetailsBreakup :: getDescription));
                    templateDetails.setTrimTemplateDetailsBreakup(new HashSet<>(trimsTemplateDetailsBreakups));
                }
            }
            trimsTemplateMaster.setTrimsTemplateDetails(new HashSet<>(detailsList));
            trimsTemplateMasters.add(trimsTemplateMaster);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(trimsTemplateMasters);
    }

    /**
     * {@code GET  /trims-template-masters/:id} : get the "id" trimsTemplateMaster.
     *
     * @param id the id of the trimsTemplateMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the trimsTemplateMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/trims-template-masters/{id}")
    public ResponseEntity<TrimsTemplateMaster> getTrimsTemplateMaster(@PathVariable Long id) {
        log.debug("REST request to get TrimsTemplateMaster : {}", id);
        TrimsTemplateMaster trimsTemplateMaster = trimsTemplateMasterRepository.findById(id).orElse(null);
        List<TrimsTemplateDetails> detailsList = new ArrayList<TrimsTemplateDetails>(trimsTemplateMaster.getTrimsTemplateDetails());
        Collections.sort(detailsList, Comparator.comparing(TrimsTemplateDetails :: getId));
        trimsTemplateMaster.setTrimsTemplateDetails(new HashSet<>(detailsList));
        return ResponseUtil.wrapOrNotFound(Optional.of(trimsTemplateMaster));
    }

    /**
     * {@code DELETE  /trims-template-masters/:id} : delete the "id"
     * trimsTemplateMaster.
     *
     * @param id the id of the trimsTemplateMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/trims-template-masters/{id}")
    public ResponseEntity<Void> deleteTrimsTemplateMaster(@PathVariable Long id) {
        log.debug("REST request to delete TrimsTemplateMaster : {}", id);
        trimsTemplateDetailsBreakupRepository.deleteAllByTemplateMasterId(id);
        trimsTemplateDetailsRepository.deleteAllByTemplateMasterId(id);
        trimsTemplateMasterRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
