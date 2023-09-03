package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.AuditQuesBuyerMapping;
import io.vamani.application.domain.AuditQuesBuyerMappingDtls;
import io.vamani.application.domain.VendorAuditQuesDetails;
import io.vamani.application.domain.VendorAuditQuesMasterWD;
import io.vamani.application.model.*;
import io.vamani.application.repository.AuditQuesBuyerMappingDtlsRepository;
import io.vamani.application.repository.AuditQuesBuyerMappingRepository;
import io.vamani.application.repository.VendorAuditQuesDetailsRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.vendorportal.domain.BuyerMaster;
import io.vamani.application.vendorportal.repository.BuyerMasterRepository;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/**
 * REST controller for managing AuditQuesBuyerMapping.
 */
@RestController
@RequestMapping("/api")
public class AuditQuesBuyerMappingResource {

    private final Logger log = LoggerFactory.getLogger(AuditQuesBuyerMappingResource.class);

    private static final String ENTITY_NAME = "auditQuesBuyerMapping";

    private final AuditQuesBuyerMappingRepository auditQuesBuyerMappingRepository;

    @Autowired
    private AuditQuesBuyerMappingDtlsRepository auditQuesBuyerMappingDtlsRepository;

    @Autowired
    private BuyerMasterRepository buyerMasterRepository;

    @Autowired
    private VendorAuditQuesDetailsRepository vendorAuditQuesDetailsRepository;

    public AuditQuesBuyerMappingResource(AuditQuesBuyerMappingRepository auditQuesBuyerMappingRepository) {
        this.auditQuesBuyerMappingRepository = auditQuesBuyerMappingRepository;
    }

    /**
     * POST  /audit-ques-buyer-mappings : Create a new auditQuesBuyerMapping.
     *
     * @param auditQuesBuyerMapping the auditQuesBuyerMapping to create
     * @return the ResponseEntity with status 201 (Created) and with body the new auditQuesBuyerMapping, or with status 400 (Bad Request) if the auditQuesBuyerMapping has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/audit-ques-buyer-mappings")
    @Timed
    public ResponseEntity<AuditQuesBuyerMapping> createAuditQuesBuyerMapping(@Valid @RequestBody AuctionQuestionBuyerMappingBean auditQuesBuyerMappingBean) throws URISyntaxException {
        log.debug("REST request to save AuditQuesBuyerMapping : {}", auditQuesBuyerMappingBean);
        String currentUser = SecurityUtils.getCurrentUserLogin().orElse(null);
        AuditQuesBuyerMapping result = null;
        if (auditQuesBuyerMappingBean.getVendorAuditQuesMaster() != null && auditQuesBuyerMappingBean.getVendorAuditQuesMaster().getId() != null) {
            AuditQuesBuyerMapping auditQuesBuyerMapping = auditQuesBuyerMappingRepository.findByAuditId(auditQuesBuyerMappingBean.getVendorAuditQuesMaster().getId());
            VendorAuditQuesMasterWD vendorAuditQuesMasterWD = new VendorAuditQuesMasterWD();
            BeanUtils.copyProperties(auditQuesBuyerMappingBean.getVendorAuditQuesMaster(), vendorAuditQuesMasterWD);
            if (auditQuesBuyerMapping != null && auditQuesBuyerMapping.getId() != null) {
                auditQuesBuyerMapping.setVendorAuditQuesMaster(vendorAuditQuesMasterWD);
                auditQuesBuyerMapping.setLastUpdatedBy(currentUser);
                auditQuesBuyerMapping.setLastUpdatedDate(Instant.now());

                result = auditQuesBuyerMappingRepository.save(auditQuesBuyerMapping);
            } else {
                auditQuesBuyerMapping = new AuditQuesBuyerMapping();
                auditQuesBuyerMapping.setVendorAuditQuesMaster(vendorAuditQuesMasterWD);
                auditQuesBuyerMapping.setCreatedBy(currentUser);
                auditQuesBuyerMapping.setCreatedDate(Instant.now());

                result = auditQuesBuyerMappingRepository.save(auditQuesBuyerMapping);
            }
            if (result != null) {
                Map<String, String> groupNameMap = new HashMap<>();
                if (auditQuesBuyerMappingBean.getVendorAuditGroupMasterBean() != null && auditQuesBuyerMappingBean.getVendorAuditGroupMasterBean().size() > 0) {
                    List<Master> masters = auditQuesBuyerMappingBean.getVendorAuditGroupMasterBean().get(0).getInitColumns();
                    for (Master master : masters) {
                        groupNameMap.put(master.getId(), master.getName());
                    }
                }
                List<AuditQuesBuyerMappingDtls> auditQuesBuyerMappingDtls = auditQuesBuyerMappingDtlsRepository.findAllByMasterId(result.getId());
                Map<String, AuditQuesBuyerMappingDtls> buyerMappingDtlsMap = new HashMap<>();
                for (AuditQuesBuyerMappingDtls mappingDtls : auditQuesBuyerMappingDtls) {
                    buyerMappingDtlsMap.put("D" + mappingDtls.getVendorAuditQuesDetailsId().longValue() + "B" + mappingDtls.getBuyerMasterId(), mappingDtls);
                }

                for (VendorAuditGroupMasterBean vendorAuditGroupMasterBean : auditQuesBuyerMappingBean.getVendorAuditGroupMasterBean()) {
                    for (VendorAuditQuesDetailsBean detailsBean : vendorAuditGroupMasterBean.getVendorAuditQuesDetails()) {
                        Map<String, Boolean> buyerMastersMap = detailsBean.getBuyerMasters().stream().collect(Collectors.toMap(BuyerMap::getKey, BuyerMap::getValue));
                        detailsBean.setBuyerMastersMap(buyerMastersMap);
                        for (String buyerCode : detailsBean.getBuyerMastersMap().keySet()) {
                            if (buyerMappingDtlsMap.containsKey("D" + detailsBean.getId().longValue() + "B" + buyerCode)) {
                                AuditQuesBuyerMappingDtls mappingDtls = buyerMappingDtlsMap.get("D" + detailsBean.getId().longValue() + "B" + buyerCode);
                                mappingDtls.setAllowValid(detailsBean.getBuyerMastersMap().get(buyerCode));
                                mappingDtls.setAuditName(groupNameMap.get(buyerCode));
                                mappingDtls.setLastUpdateBy(currentUser);
                                mappingDtls.setLastUpdatedDate(Instant.now());

                                auditQuesBuyerMappingDtlsRepository.save(mappingDtls);
                            } else {
                                AuditQuesBuyerMappingDtls mappingDtls = new AuditQuesBuyerMappingDtls();
                                mappingDtls.setAllowValid(detailsBean.getBuyerMastersMap().get(buyerCode));
                                mappingDtls.setAuditName(groupNameMap.get(buyerCode));
                                mappingDtls.setBuyerMasterId(buyerCode);
                                mappingDtls.setAuditQuesBuyerMapping(result);
                                mappingDtls.setVendorAuditQuesDetailsId(detailsBean.getId());
                                mappingDtls.setCreatedBy(currentUser);
                                mappingDtls.setCreatedDate(Instant.now());

                                auditQuesBuyerMappingDtlsRepository.save(mappingDtls);
                            }
                        }
                    }
                }
            }
        }
        return ResponseEntity.created(new URI("/api/audit-ques-buyer-mappings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /audit-ques-buyer-mappings : Updates an existing auditQuesBuyerMapping.
     *
     * @param auditQuesBuyerMapping the auditQuesBuyerMapping to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated auditQuesBuyerMapping,
     * or with status 400 (Bad Request) if the auditQuesBuyerMapping is not valid,
     * or with status 500 (Internal Server Error) if the auditQuesBuyerMapping couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/audit-ques-buyer-mappings")
    @Timed
    public ResponseEntity<AuditQuesBuyerMapping> updateAuditQuesBuyerMapping(@Valid @RequestBody AuditQuesBuyerMapping auditQuesBuyerMapping) throws URISyntaxException {
        log.debug("REST request to update AuditQuesBuyerMapping : {}", auditQuesBuyerMapping);
        if (auditQuesBuyerMapping.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AuditQuesBuyerMapping result = auditQuesBuyerMappingRepository.save(auditQuesBuyerMapping);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, auditQuesBuyerMapping.getId().toString()))
            .body(result);
    }

    /**
     * GET  /audit-ques-buyer-mappings : get all the auditQuesBuyerMappings.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of auditQuesBuyerMappings in body
     */
    @GetMapping("/audit-ques-buyer-mappings")
    @Timed
    public ResponseEntity<List<AuditQuesBuyerMapping>> getAllAuditQuesBuyerMappings(Pageable pageable) {
        log.debug("REST request to get a page of AuditQuesBuyerMappings");
        Page<AuditQuesBuyerMapping> page = auditQuesBuyerMappingRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/audit-ques-buyer-mappings");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /audit-ques-buyer-mappings/:id : get the "id" auditQuesBuyerMapping.
     *
     * @param id the id of the auditQuesBuyerMapping to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the auditQuesBuyerMapping, or with status 404 (Not Found)
     */
    @GetMapping("/audit-ques-buyer-mappings/{id}")
    @Timed
    public ResponseEntity<AuditQuesBuyerMappingBean> getAuditQuesBuyerMapping(@PathVariable Long id) {
        log.debug("REST request to get AuditQuesBuyerMapping : {}", id);
        AuditQuesBuyerMappingBean bean = new AuditQuesBuyerMappingBean();
        AuditQuesBuyerMapping auditQuesBuyerMapping = auditQuesBuyerMappingRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(auditQuesBuyerMapping, bean);
        List<AuditQuesBuyerMappingDtls> auditQuesBuyerMappingDtls = auditQuesBuyerMappingDtlsRepository.findAllByMasterId(id);
        Set<String> buyerSet = new HashSet<>();
        Map<String, String> auditNameMap = new HashMap<>();
        List<BuyerMap> buyerMaps = new ArrayList<>();

        for (AuditQuesBuyerMappingDtls dtls : auditQuesBuyerMappingDtls) {
            auditNameMap.put(dtls.getBuyerMasterId(), dtls.getAuditName());
            buyerSet.add(dtls.getBuyerMasterId());
            buyerMaps.add(new BuyerMap(dtls.getVendorAuditQuesDetailsId()+dtls.getBuyerMasterId(), dtls.isAllowValid()));
        }
        bean.setBuyerMaps(buyerMaps);
        List<BuyerMaster> buyerMasters = buyerMasterRepository.findAllByCode(new ArrayList<>(buyerSet));
        List<BuyerMasterBean> buyerMastersBeans = new ArrayList<>();
        for (BuyerMaster buyerMaster : buyerMasters) {
            BuyerMasterBean buyerMasterBean = new BuyerMasterBean();
            BeanUtils.copyProperties(buyerMaster, buyerMasterBean);
            buyerMasterBean.setAuditName(auditNameMap.get(buyerMaster.getBuyerCode()));
            buyerMastersBeans.add(buyerMasterBean);
        }
        bean.setBuyerMasters(buyerMastersBeans);
        return ResponseUtil.wrapOrNotFound(Optional.of(bean));
    }

    /**
     * DELETE  /audit-ques-buyer-mappings/:id : delete the "id" auditQuesBuyerMapping.
     *
     * @param id the id of the auditQuesBuyerMapping to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/audit-ques-buyer-mappings/{id}")
    @Timed
    public ResponseEntity<Void> deleteAuditQuesBuyerMapping(@PathVariable Long id) {
        log.debug("REST request to delete AuditQuesBuyerMapping : {}", id);

        auditQuesBuyerMappingRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /audit-ques-buyer-mappings/:id : get the "id" auditQuesBuyerMapping.
     *
     * @param id the id of the auditQuesBuyerMapping to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the auditQuesBuyerMapping, or with status 404 (Not Found)
     */
    @PostMapping("/audit-ques-buyer-mappings-buyer-code")
    @Timed
    public ResponseEntity<AuditQuesBuyerMappingBean> getAuditQuesBuyerMapping(@RequestBody Master master) {
        AuditQuesBuyerMappingBean bean = new AuditQuesBuyerMappingBean();
        AuditQuesBuyerMapping auditQuesBuyerMapping = auditQuesBuyerMappingRepository.findById(Long.parseLong(master.getId())).orElse(null);
        BeanUtils.copyProperties(auditQuesBuyerMapping, bean);
        List<AuditQuesBuyerMappingDtls> auditQuesBuyerMappingDtls = auditQuesBuyerMappingDtlsRepository.findAllByMasterIdAAndBuyerMasterId(Long.parseLong(master.getId()), master.getDesc());
        Set<String> buyerSet = new HashSet<>();
        Map<String, String> auditNameMap = new HashMap<>();
        List<Long> questionsMap = new ArrayList<>();
        for(AuditQuesBuyerMappingDtls auditQuesBuyerMappingDtls1 : auditQuesBuyerMappingDtls) {
            questionsMap.add(auditQuesBuyerMappingDtls1.getVendorAuditQuesDetailsId());
        }
        List<VendorAuditQuesDetails> vendorAuditQuesDetails = vendorAuditQuesDetailsRepository.findAllByDetailsId(questionsMap);
        List<VendorAuditGroupMasterBean> vendorAuditGroupMasterBeans = new ArrayList<>();
        Map<Long, List<VendorAuditQuesDetailsBean>> auditMap = new HashMap<>();
        Map<Long, String> groupNameMap = new HashMap<>();
        for (VendorAuditQuesDetails vendorAuditQuesDetail : vendorAuditQuesDetails) {
            if (auditMap.containsKey(vendorAuditQuesDetail.getAuditGroupMaster().getId())) {
                List<VendorAuditQuesDetailsBean> auditQuesDetails = auditMap.get(vendorAuditQuesDetail.getAuditGroupMaster().getId());
                VendorAuditQuesDetailsBean vendorAuditQuesDetailsBean = new VendorAuditQuesDetailsBean();
                BeanUtils.copyProperties(vendorAuditQuesDetail, vendorAuditQuesDetailsBean);
                auditQuesDetails.add(vendorAuditQuesDetailsBean);
                auditMap.put(vendorAuditQuesDetail.getAuditGroupMaster().getId(), auditQuesDetails);
            } else {
                List<VendorAuditQuesDetailsBean> auditQuesDetails = new ArrayList<>();
                VendorAuditQuesDetailsBean vendorAuditQuesDetailsBean = new VendorAuditQuesDetailsBean();
                BeanUtils.copyProperties(vendorAuditQuesDetail, vendorAuditQuesDetailsBean);
                auditQuesDetails.add(vendorAuditQuesDetailsBean);
                groupNameMap.put(vendorAuditQuesDetail.getAuditGroupMaster().getId(), vendorAuditQuesDetail.getAuditGroupMaster().getDescription());
                auditMap.put(vendorAuditQuesDetail.getAuditGroupMaster().getId(), auditQuesDetails);
            }
        }
        for (Long key : auditMap.keySet()) {
            VendorAuditGroupMasterBean vendorAuditGroupMasterBean = new VendorAuditGroupMasterBean();
            vendorAuditGroupMasterBean.setId(key);
            vendorAuditGroupMasterBean.setGroupName(groupNameMap.get(key));
            vendorAuditGroupMasterBean.setVendorAuditQuesDetails(auditMap.get(key));
            vendorAuditGroupMasterBeans.add(vendorAuditGroupMasterBean);
        }
        bean.setVendorAuditGroupMasterBeans(vendorAuditGroupMasterBeans);
        return ResponseUtil.wrapOrNotFound(Optional.of(bean));
    }
}
