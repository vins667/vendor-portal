package io.vamani.application.web.rest;

import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.jhipster.web.util.HeaderUtil;
import io.vamani.application.domain.StitchCostHeadMaster;
import io.vamani.application.domain.StitchCostSubHeadMaster;
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
    
    
    @GetMapping("/stitch-cost-head-masters")
    public ResponseEntity<List<StitchCostHeadMaster>> getAllFabricContentMasters() {
        log.debug("REST request to get a page of FabricContentMasters");
        List<StitchCostHeadMaster> response=service.getAllStitchCostHeadMaster();
        return ResponseEntity.ok().body(response);
    }
    
    @PutMapping("/stitch-cost-sub-head-update")
    public ResponseEntity<StitchCostSubHeadMaster> updateStitchCostSubHeadMaster(@Valid @RequestBody List<StitchCostSubHeadMaster> stitchCostSubHeadMaster) throws URISyntaxException {
        log.debug("REST request to update FabricContentMaster : {}", stitchCostSubHeadMaster);
        if (stitchCostSubHeadMaster.get(0).getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StitchCostSubHeadMaster result = service.getUpdate(stitchCostSubHeadMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

}
