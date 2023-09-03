package io.vamani.application.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.domain.AssetAuditDetails;
import io.vamani.application.domain.AssetAuditSoftwareDetails;
import io.vamani.application.model.AssetAuditDetailsBean;
import io.vamani.application.model.AssetAuditSearch;
import io.vamani.application.model.HardwareQueryBean;
import io.vamani.application.model.HardwareSearchQuery;
import io.vamani.application.model.SoftwareKeyDetailsBean;
import io.vamani.application.model.SoftwareKeyDetailsSearch;
import io.vamani.application.model.SoftwareQueryBean;
import io.vamani.application.model.SoftwareSearchQuery;
import io.vamani.application.repository.AssetAuditDetailsRepository;
import io.vamani.application.repository.AssetAuditSoftwareDetailsRepository;
import io.vamani.application.repository.AssetAuditSoftwareKeyDetailsRepository;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;

/**
 * REST controller for managing AssetAuditDetails.
 */
@RestController
@RequestMapping("/api")
public class AssetAuditDetailsResource {

    private final Logger log = LoggerFactory.getLogger(AssetAuditDetailsResource.class);

    private static final String ENTITY_NAME = "assetAuditDetails";

    private final AssetAuditDetailsRepository assetAuditDetailsRepository;

    @Autowired
    private AssetAuditSoftwareDetailsRepository assetAuditSoftwareDetailsRepository;

    @Autowired
    private AssetAuditSoftwareKeyDetailsRepository assetAuditSoftwareKeyDetailsRepository;

    public AssetAuditDetailsResource(AssetAuditDetailsRepository assetAuditDetailsRepository) {
        this.assetAuditDetailsRepository = assetAuditDetailsRepository;
    }

    /**
     * POST  /asset-audit-details : Create a new assetAuditDetails.
     *
     * @param assetAuditDetails the assetAuditDetails to create
     * @return the ResponseEntity with status 201 (Created) and with body the new assetAuditDetails, or with status 400 (Bad Request) if the assetAuditDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/asset-audit-details")
    @Timed
    public ResponseEntity<AssetAuditDetails> createAssetAuditDetails(@Valid @RequestBody AssetAuditDetails assetAuditDetails) throws URISyntaxException {
        log.debug("REST request to save AssetAuditDetails : {}", assetAuditDetails);
        if (assetAuditDetails.getId() != null) {
            throw new BadRequestAlertException("A new assetAuditDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AssetAuditDetails result = assetAuditDetailsRepository.save(assetAuditDetails);
        return ResponseEntity.created(new URI("/api/asset-audit-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /asset-audit-details : Updates an existing assetAuditDetails.
     *
     * @param assetAuditDetails the assetAuditDetails to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated assetAuditDetails,
     * or with status 400 (Bad Request) if the assetAuditDetails is not valid,
     * or with status 500 (Internal Server Error) if the assetAuditDetails couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/asset-audit-details")
    @Timed
    public ResponseEntity<AssetAuditDetails> updateAssetAuditDetails(@Valid @RequestBody AssetAuditDetails assetAuditDetails) throws URISyntaxException {
        log.debug("REST request to update AssetAuditDetails : {}", assetAuditDetails);
        if (assetAuditDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AssetAuditDetails result = assetAuditDetailsRepository.save(assetAuditDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, assetAuditDetails.getId().toString()))
            .body(result);
    }

    /**
     * GET  /asset-audit-details : get all the assetAuditDetails.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of assetAuditDetails in body
     */
    @GetMapping("/asset-audit-details")
    @Timed
    public ResponseEntity<List<AssetAuditDetails>> getAllAssetAuditDetails(Pageable pageable) {
        log.debug("REST request to get a page of AssetAuditDetails");
        Page<AssetAuditDetails> page = assetAuditDetailsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/asset-audit-details");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /leave-masters : get all the leaveMasters.
     *
     * @param !pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of leaveMasters in body
     */
    @PostMapping("/asset-audit-details-custom")
    @Timed
    public ResponseEntity<List<AssetAuditDetails>> getAllAssetAuditDetails(@Valid @RequestBody AssetAuditSearch search) {
        log.debug("REST request to get a page of AssetAuditDetails");
        String uuid = "%";
        String name = "%";
        String ip = "%";
        String serial = "%";
        String assetCode = "%";
        if (search.getUuid() != null) {
            uuid = search.getUuid() + "%";
        }
        if (search.getName() != null) {
            name = search.getName() + "%";
        }
        if (search.getIp() != null) {
            ip = search.getIp() + "%";
        }
        if (search.getSerial() != null) {
            serial = search.getSerial() + "%";
        }
        if (search.getAssetCode() != null) {
            assetCode = search.getAssetCode() + "%";
        }
        Page<AssetAuditDetails> page = null;
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("systemId").ascending()));
        page = assetAuditDetailsRepository.findByFilter(uuid, name, ip, serial, assetCode, search.getPage());

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/asset-audit-details-custom");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    
    @PostMapping("/asset-audit-details-qry")
    @Timed
    public ResponseEntity<List<SoftwareQueryBean>> getAllSoftwareSerachQueries(@Valid @RequestBody SoftwareSearchQuery search) {
        log.debug("REST request to get a page of SoftwareQueries");
        List<SoftwareQueryBean> softwareQueryBeans = new ArrayList<>();
        String assetCode = "%";
        String publisher = "%";
        String software = "%";
        if (search.getAssetCode() != null) {
        	assetCode = "%" + search.getAssetCode().toUpperCase() + "%";
        }
        if (search.getPublisher() != null) {
        	publisher = "%" + search.getPublisher() + "%";
        }
        if (search.getSoftware() != null) {
        	publisher = "%" + search.getSoftware() + "%";
        }
        
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize()));
        Page<Object[]> list = assetAuditDetailsRepository.findAllByFilter(assetCode,publisher,software,search.getPage());
        for(Object[] objects:list.getContent()) {
        	SoftwareQueryBean objBean = new SoftwareQueryBean();
        	if(objects[0] != null) {
        		objBean.setAssetCode(objects[0].toString());
        	}
        	if(objects[1] != null) {
            	objBean.setAssetName(objects[1].toString());
        	}
        	if(objects[2] != null) {
            	objBean.setPublisher(objects[2].toString());
        	}
        	if(objects[3] != null) {
            	objBean.setPublisherName(objects[3].toString());
        	}
        	if(objects[4] != null) {
            	objBean.setAssetIp(objects[4].toString());
        	}
        	softwareQueryBeans.add(objBean);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(list, "/api/asset-audit-details-qry");
        return ResponseEntity.ok().headers(headers).body(softwareQueryBeans);
    }
    
    @PostMapping("/asset-audit-details-hardware")
    @Timed
    public ResponseEntity<List<HardwareQueryBean>> getAllHardwareSerachQueries(@Valid @RequestBody HardwareSearchQuery search) {
        log.debug("REST request to get a page of SoftwareQueries");
        String sql="";
        List<HardwareQueryBean> hardwareQueryBean = new ArrayList<>();
        Long minMemory = 0L;
        Long maxMemory = Long.MAX_VALUE;
        if (search.getMemoryMin() != null) {
            minMemory = search.getMemoryMin();
        }
        if (search.getMemoryMax() != null) {
            maxMemory = search.getMemoryMax();
        }

        Long minStorage = 0L;
        Long maxStorage = Long.MAX_VALUE;
        if (search.getStorageMin() != null) {
            minStorage = search.getStorageMin();
        }
        if (search.getStorageMax() != null) {
            maxStorage = search.getStorageMax();
        }

        search.setPage(PageRequest.of(search.getPageNo(), search.getSize()));
        Page<Object[]> list = assetAuditDetailsRepository.findAllHardwareByFilter(minMemory, maxMemory, minStorage, maxStorage, search.getPage());
        for(Object[] objects:list.getContent()) {
        	HardwareQueryBean objBean = new HardwareQueryBean();
        	if(objects[0] != null) {
        		objBean.setAssetCode(objects[0].toString());
        	}
        	if(objects[1] != null) {
            	objBean.setIpAddress(objects[1].toString());
        	}
        	if(objects[2] != null) {
            	objBean.setHardDisk(objects[2].toString());
        	}
        	if(objects[2] != null) {
            	objBean.setMemory(objects[3].toString());
        	}
        	if(objects[2] != null) {
            	objBean.setOsName(objects[4].toString());
        	}
        	hardwareQueryBean.add(objBean);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(list, "/api/asset-audit-details-hardware");
        return ResponseEntity.ok().headers(headers).body(hardwareQueryBean);
    }

    @PostMapping("/asset-audit-details-key")
    @Timed
    public ResponseEntity<List<SoftwareKeyDetailsBean>> getAllSoftwareKeySerachQueries(@Valid @RequestBody SoftwareKeyDetailsSearch search) {
        log.debug("REST request to get a page of SoftwareQueries");
        List<SoftwareKeyDetailsBean> softwareKeyDetailsBean = new ArrayList<>();
        String name = "%";
        String key = "%";
        if (search.getName() != null) {
        	name = "%" + search.getName().trim() + "%";
        }
        if (search.getJhiKey() != null) {
        	key = "%" + search.getJhiKey().trim() + "%";
        }
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize()));
        Page<Object[]> list = assetAuditDetailsRepository.findAllSoftwareKeyByFilter(name,key,search.getPage());
        for(Object[] objects:list.getContent()) {
        	SoftwareKeyDetailsBean objBean = new SoftwareKeyDetailsBean();
        	if(objects[0] != null) {
        		objBean.setAssetCode(objects[0].toString());
        	}
        	if(objects[1] != null) {
            	objBean.setName(objects[1].toString());
        	}
        	if(objects[2] != null) {
            	objBean.setJhiKey(objects[2].toString());
        	}
        	softwareKeyDetailsBean.add(objBean);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(list, "/api/asset-audit-details-key");
        return ResponseEntity.ok().headers(headers).body(softwareKeyDetailsBean);
    }
    /**
     * GET  /asset-audit-details/:id : get the "id" assetAuditDetails.
     *
     * @param id the id of the assetAuditDetails to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the assetAuditDetails, or with status 404 (Not Found)
     */
    @GetMapping("/asset-audit-details/{id}")
    @Timed
    public ResponseEntity<AssetAuditDetailsBean> getAssetAuditDetails(@PathVariable Long id) {
        log.debug("REST request to get AssetAuditDetails : {}", id);
        AssetAuditDetails assetAuditDetails = assetAuditDetailsRepository.findById(id).orElse(null);
        AssetAuditDetailsBean assetAuditDetailsBean = new AssetAuditDetailsBean();
        BeanUtils.copyProperties(assetAuditDetails, assetAuditDetailsBean);
        assetAuditDetailsBean.setAssetAuditSoftwareDetailsList(assetAuditSoftwareDetailsRepository.findAllBySystemId(assetAuditDetails.getSystemId()));
        assetAuditDetailsBean.setAssetAuditSoftwareKeyDetails(assetAuditSoftwareKeyDetailsRepository.findAllBySystemId(assetAuditDetails.getSystemId()));
        return ResponseUtil.wrapOrNotFound(Optional.of(assetAuditDetailsBean));
    }


    /**
     * GET  /asset-audit-details/:id : get the "systemId" assetAuditDetails.
     *
     * @param !id the id of the assetAuditDetails to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the assetAuditDetails, or with status 404 (Not Found)
     */
    @GetMapping("/asset-audit-details-custom/{systemId}")
    @Timed
    public ResponseEntity <List<AssetAuditSoftwareDetails>> getAssetAuditSoftwareDetails(@PathVariable Long systemId) {
        log.debug("REST request to get AssetAuditSoftwareDetails : {}", systemId);
        List<AssetAuditSoftwareDetails> assetAuditSoftwareDetails = assetAuditDetailsRepository.findSoftwareByFilter(systemId);
        return ResponseUtil.wrapOrNotFound(Optional.of(assetAuditSoftwareDetails));
    }

    /**
     * DELETE  /asset-audit-details/:id : delete the "id" assetAuditDetails.
     *
     * @param id the id of the assetAuditDetails to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/asset-audit-details/{id}")
    @Timed
    public ResponseEntity<Void> deleteAssetAuditDetails(@PathVariable Long id) {
        log.debug("REST request to delete AssetAuditDetails : {}", id);

        assetAuditDetailsRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
