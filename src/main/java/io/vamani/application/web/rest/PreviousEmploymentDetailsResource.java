package io.vamani.application.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import org.apache.commons.collections4.map.HashedMap;
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
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.domain.PreviousEmploymentDetails;
import io.vamani.application.domain.TdsYearMaster;
import io.vamani.application.model.PreviousEmploymentDetailsBean;
import io.vamani.application.model.TdsDeclarationSearch;
import io.vamani.application.mssql.domain.EmployeeMatView;
import io.vamani.application.mssql.repository.EmployeeMatViewRepository;
import io.vamani.application.repository.PreviousEmploymentDetailsRepository;
import io.vamani.application.repository.TdsYearMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link PreviousEmploymentDetails}.
 */
@RestController
@RequestMapping("/api")
public class PreviousEmploymentDetailsResource {

    private final Logger log = LoggerFactory.getLogger(PreviousEmploymentDetailsResource.class);

    private static final String ENTITY_NAME = "previousEmploymentDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PreviousEmploymentDetailsRepository previousEmploymentDetailsRepository;
    
    @Autowired
    private  TdsYearMasterRepository tdsYearMasterRepository;
    
    @Autowired
    private EmployeeMatViewRepository employeeMatViewRepository;

    public PreviousEmploymentDetailsResource(PreviousEmploymentDetailsRepository previousEmploymentDetailsRepository) {
        this.previousEmploymentDetailsRepository = previousEmploymentDetailsRepository;
    }

    /**
     * {@code POST  /previous-employment-details} : Create a new previousEmploymentDetails.
     *
     * @param previousEmploymentDetails the previousEmploymentDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new previousEmploymentDetails, or with status {@code 400 (Bad Request)} if the previousEmploymentDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/previous-employment-details")
    public ResponseEntity<PreviousEmploymentDetails> createPreviousEmploymentDetails(@Valid @RequestBody PreviousEmploymentDetails previousEmploymentDetails) throws URISyntaxException {
        log.debug("REST request to save PreviousEmploymentDetails : {}", previousEmploymentDetails);
        List<TdsYearMaster> tdsYearMastersList = tdsYearMasterRepository.findByActive();
        if(tdsYearMastersList != null && tdsYearMastersList.size()>0) {
        	TdsYearMaster tdsYearMasters = tdsYearMastersList.get(0);
        
        if (previousEmploymentDetails.getId() != null) {
            throw new BadRequestAlertException("A new previousEmploymentDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        previousEmploymentDetails.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        previousEmploymentDetails.setCardNo(SecurityUtils.getCurrentUserLogin().orElse(null));
        previousEmploymentDetails.setFinanceYear(tdsYearMasters.getCode());
        previousEmploymentDetails.setCreatedDate(Instant.now());
        previousEmploymentDetails.setCreatedDate(Instant.now());
        PreviousEmploymentDetails result = previousEmploymentDetailsRepository.save(previousEmploymentDetails);
        return ResponseEntity.created(new URI("/api/previous-employment-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
        } else {
        	return ResponseEntity.badRequest()
                    .headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "", "Active year not exist")).build();
        }
    }

    /**
     * {@code PUT  /previous-employment-details} : Updates an existing previousEmploymentDetails.
     *
     * @param previousEmploymentDetails the previousEmploymentDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated previousEmploymentDetails,
     * or with status {@code 400 (Bad Request)} if the previousEmploymentDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the previousEmploymentDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/previous-employment-details")
    public ResponseEntity<PreviousEmploymentDetails> updatePreviousEmploymentDetails(@Valid @RequestBody PreviousEmploymentDetails previousEmploymentDetails) throws URISyntaxException {
        log.debug("REST request to update PreviousEmploymentDetails : {}", previousEmploymentDetails);
        if (previousEmploymentDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        previousEmploymentDetails.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        previousEmploymentDetails.setLastUpdatedDate(Instant.now());
        PreviousEmploymentDetails result = previousEmploymentDetailsRepository.save(previousEmploymentDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, previousEmploymentDetails.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /previous-employment-details} : get all the previousEmploymentDetails.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of previousEmploymentDetails in body.
     */
    @GetMapping("/previous-employment-details")
    public ResponseEntity<List<PreviousEmploymentDetails>> getAllPreviousEmploymentDetails(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of PreviousEmploymentDetails");
        List<PreviousEmploymentDetails> previousEmploymentDetails = previousEmploymentDetailsRepository.findAllByCardNo(SecurityUtils.getCurrentUserLogin().orElse(null));
        return ResponseEntity.ok().body(previousEmploymentDetails);
    }

    /**
     * {@code GET  /previous-employment-details/:id} : get the "id" previousEmploymentDetails.
     *
     * @param id the id of the previousEmploymentDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the previousEmploymentDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/previous-employment-details/{id}")
    public ResponseEntity<PreviousEmploymentDetailsBean> getPreviousEmploymentDetails(@PathVariable Long id) {
        log.debug("REST request to get PreviousEmploymentDetails : {}", id);
        PreviousEmploymentDetails previousEmploymentDetails = previousEmploymentDetailsRepository.findById(id).orElse(null);
        PreviousEmploymentDetailsBean employmentDetailsBean = new PreviousEmploymentDetailsBean();
        BeanUtils.copyProperties(previousEmploymentDetails, employmentDetailsBean);
        TdsYearMaster tdsYearMaster = tdsYearMasterRepository.findByCode(previousEmploymentDetails.getFinanceYear());
        employmentDetailsBean.setTdsYearMaster(tdsYearMaster);
        return ResponseUtil.wrapOrNotFound(Optional.of(employmentDetailsBean));
    }
    
    /**
     * {@code GET  /previous-employment-details/:id} : get the "id" previousEmploymentDetails.
     *
     * @param id the id of the previousEmploymentDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the previousEmploymentDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/previous-employment-details-view/{id}")
    public ResponseEntity<PreviousEmploymentDetailsBean> getPreviousEmploymentEmployment(@PathVariable Long id) {
        log.debug("REST request to get PreviousEmploymentDetails : {}", id);
        PreviousEmploymentDetails previousEmploymentDetails = previousEmploymentDetailsRepository.findById(id).orElse(null);
        PreviousEmploymentDetailsBean employmentDetailsBean = new PreviousEmploymentDetailsBean();
        BeanUtils.copyProperties(previousEmploymentDetails, employmentDetailsBean);
        employmentDetailsBean.setTdsYearMaster(tdsYearMasterRepository.findByCode(previousEmploymentDetails.getFinanceYear()));
        BeanUtils.copyProperties(employeeMatViewRepository.findByLogins(previousEmploymentDetails.getCardNo()), employmentDetailsBean);
        return ResponseUtil.wrapOrNotFound(Optional.of(employmentDetailsBean));
    }
    
    @PostMapping("/previous-employment-details-qry")
    @Timed
    public ResponseEntity<List<PreviousEmploymentDetailsBean>> getAllPreviousEmploymentQry(@Valid @RequestBody TdsDeclarationSearch search) {
        log.debug("REST request to get a page of PreviousEmploymentDetails");
        Map<String, PreviousEmploymentDetails> map = new HashedMap<>();
        List<PreviousEmploymentDetailsBean> previousEmploymentQryBean = new ArrayList<PreviousEmploymentDetailsBean>();
        String cardNo = "%";
        if (search.getCardNo() != null) {
        	cardNo = search.getCardNo().trim() + "%";
        }
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("id").descending()));
        Page<PreviousEmploymentDetails> page = previousEmploymentDetailsRepository.findAllByYearAndCardNo(search.getYear(),cardNo,search.getPage());
        for(PreviousEmploymentDetails previousEmployment:page.getContent()) {
        	map.put(previousEmployment.getCardNo(), previousEmployment);
        }
        List<EmployeeMatView> employeeMatView = employeeMatViewRepository.findAllByLogins(new ArrayList(map.keySet()));
        for(EmployeeMatView bean:employeeMatView) {
        	PreviousEmploymentDetailsBean qryBean =  new PreviousEmploymentDetailsBean();
        	PreviousEmploymentDetails previousEmploymentDetail = map.get(bean.getLogin());
        	BeanUtils.copyProperties(previousEmploymentDetail, qryBean);
        	BeanUtils.copyProperties(bean, qryBean);
        	previousEmploymentQryBean.add(qryBean);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(),page);
        return ResponseEntity.ok().headers(headers).body(previousEmploymentQryBean);
    }

    /**
     * {@code DELETE  /previous-employment-details/:id} : delete the "id" previousEmploymentDetails.
     *
     * @param id the id of the previousEmploymentDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/previous-employment-details/{id}")
    public ResponseEntity<Void> deletePreviousEmploymentDetails(@PathVariable Long id) {
        log.debug("REST request to delete PreviousEmploymentDetails : {}", id);
        previousEmploymentDetailsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
