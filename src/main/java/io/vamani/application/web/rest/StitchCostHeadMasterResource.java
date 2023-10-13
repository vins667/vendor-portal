package io.vamani.application.web.rest;

import java.net.URISyntaxException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.jhipster.web.util.HeaderUtil;
import io.vamani.application.model.StitchCostHeadMasterBean;
import io.vamani.application.service.StitchCostHeadMasterService;
import io.vamani.application.web.rest.errors.BadRequestAlertException;

@RestController
@RequestMapping("/api")
public class StitchCostHeadMasterResource {
	
	private final Logger log = LoggerFactory.getLogger(StitchCostHeadMasterResource.class);

    private static final String ENTITY_NAME = "StitchCostHeadMaster";
    
    @Autowired
    private StitchCostHeadMasterService service;

    @Value("${jhipster.clientApp.name}")
    private String applicationName;
    
    
    @GetMapping("/stitch-cost-head-masters/{factory}")
    public ResponseEntity<StitchCostHeadMasterBean> getAllAllStitchCostHead(@PathVariable String factory) {
        log.debug("REST request to get a page of FabricContentMasters",factory);
        return ResponseEntity.ok().body(service.getAllStitchCostHeadMaster(factory));
    }
    
    @PutMapping("/stitch-cost-head-masters-update")
    public ResponseEntity<StitchCostHeadMasterBean> updateStitchCostSubHeadMaster(@Valid @RequestBody StitchCostHeadMasterBean bean) throws URISyntaxException {
        log.debug("REST request to update FabricContentMaster : {}", bean);
        if (bean == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StitchCostHeadMasterBean result = service.getUpdate(bean);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME,""))
            .body(result);
    }

}
