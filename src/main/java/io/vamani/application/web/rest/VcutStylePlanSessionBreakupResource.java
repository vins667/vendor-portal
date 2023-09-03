package io.vamani.application.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.vamani.application.domain.VcutStylePlanSessionBreakup;
import io.vamani.application.domain.VcutStylePlanSessionBreakupId;
import io.vamani.application.domain.VcutStylePlanUpload;
import io.vamani.application.model.VcutStylePlanSessionBreakupBean;
import io.vamani.application.repository.VcutStylePlanSessionBreakupRepository;
import io.vamani.application.repository.VcutStylePlanUploadRepository;
import io.vamani.application.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class VcutStylePlanSessionBreakupResource {

    private final Logger log = LoggerFactory.getLogger(VcutStylePlanSessionBreakupResource.class);

    private static final String ENTITY_NAME = "vcutStylePlanSessionBreakup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VcutStylePlanSessionBreakupRepository vcutStylePlanSessionBreakupRepository;

    @Autowired
    private VcutStylePlanUploadRepository vcutStylePlanUploadRepository;

    public VcutStylePlanSessionBreakupResource(VcutStylePlanSessionBreakupRepository vcutStylePlanSessionBreakupRepository) {
        this.vcutStylePlanSessionBreakupRepository = vcutStylePlanSessionBreakupRepository;
    }

    /**
     * {@code POST  /vcut-style-plan-uploads} : Create a new vcutStylePlanSessionBreakup.
     *
     * @param !vcutStylePlanSessionBreakup the vcutStylePlanUpload to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vcutStylePlanUpload, or with status {@code 400 (Bad Request)} if the vcutStylePlanUpload has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/vcut-style-plan-session-breakup")
    public ResponseEntity<List<VcutStylePlanSessionBreakup>> createVcutStylePlanSessionBreakup(@Valid @RequestBody VcutStylePlanSessionBreakupBean[] vcutStylePlanSessionBreakups) throws URISyntaxException {
        log.debug("REST request to save VcutStylePlanUpload : {}", vcutStylePlanSessionBreakups);
        List<VcutStylePlanSessionBreakup> stylePlanSessionBreakups = new ArrayList<>();
        if (vcutStylePlanSessionBreakups != null && vcutStylePlanSessionBreakups.length > 0) {
            VcutStylePlanUpload vcutStylePlanUpload = vcutStylePlanUploadRepository.findById(vcutStylePlanSessionBreakups[0].getVcutStylePlanUploadId()).orElse(null);
            vcutStylePlanUpload.setVcutSessionMasterId(vcutStylePlanSessionBreakups[0].getSessionId());
            vcutStylePlanUploadRepository.save(vcutStylePlanUpload);
            vcutStylePlanSessionBreakupRepository.deleteByPlanId(vcutStylePlanSessionBreakups[0].getVcutStylePlanUploadId());
            for (VcutStylePlanSessionBreakupBean vcutStylePlanSessionBreakupBean : vcutStylePlanSessionBreakups) {
                VcutStylePlanSessionBreakup vcutStylePlanSessionBreakup = new VcutStylePlanSessionBreakup();
                VcutStylePlanSessionBreakupId vcutStylePlanSessionBreakupId = new VcutStylePlanSessionBreakupId();
                BeanUtils.copyProperties(vcutStylePlanSessionBreakupBean, vcutStylePlanSessionBreakup);
                vcutStylePlanSessionBreakupId.setStartTime(vcutStylePlanSessionBreakupBean.getStartTime());
                vcutStylePlanSessionBreakupId.setVcutStylePlanUploadId(vcutStylePlanSessionBreakupBean.getVcutStylePlanUploadId());
                vcutStylePlanSessionBreakup.setId(vcutStylePlanSessionBreakupId);
                if (vcutStylePlanSessionBreakup.getId().getStartTime() != null && vcutStylePlanSessionBreakup.getId().getVcutStylePlanUploadId() != null && vcutStylePlanSessionBreakup.getEndTime() != null) {
                    vcutStylePlanSessionBreakup.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                    vcutStylePlanSessionBreakup.setCreatedDate(Instant.now());
                    VcutStylePlanSessionBreakup result = vcutStylePlanSessionBreakupRepository.save(vcutStylePlanSessionBreakup);
                    if (result != null) {
                        stylePlanSessionBreakups.add(result);
                    }
                }
            }
        }
        return ResponseEntity.created(new URI("/api/vcut-style-plan-uploads/" + ""))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, "1"))
            .body(stylePlanSessionBreakups);
    }



    /**
     * {@code GET  /vcut-style-plan-uploads} : get all the vcutStylePlanUploads.
     *

     * @param !pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vcutStylePlanUploads in body.
     */
    @GetMapping("/vcut-style-plan-session-breakup/{id}")
    public ResponseEntity<List<VcutStylePlanSessionBreakup>> getAllVcutStylePlanSessionBreakup(@PathVariable Long id) {
        log.debug("REST request to get a page of VcutStylePlanUploads");
        List<VcutStylePlanSessionBreakup> page = vcutStylePlanSessionBreakupRepository.findAllByVcutPlanId(id);
        return ResponseEntity.ok().body(page);
    }
}
