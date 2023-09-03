package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.domain.FactoryMaster;
import io.vamani.application.domain.PollDetails;
import io.vamani.application.domain.PollMaster;
import io.vamani.application.model.PollReport;
import io.vamani.application.repository.PollDetailsRepository;
import io.vamani.application.repository.PollMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import java.time.Instant;
import java.util.*;

/**
 * REST controller for managing PollMaster.
 */
@RestController
@RequestMapping("/api")
public class PollMasterResource {

    private final Logger log = LoggerFactory.getLogger(PollMasterResource.class);

    private static final String ENTITY_NAME = "pollMaster";

    private final PollMasterRepository pollMasterRepository;

    @Autowired
    private PollDetailsRepository pollDetailsRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

    public PollMasterResource(PollMasterRepository pollMasterRepository) {
        this.pollMasterRepository = pollMasterRepository;
    }

    /**
     * POST  /poll-masters : Create a new pollMaster.
     *
     * @param pollMaster the pollMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pollMaster, or with status 400 (Bad Request) if the pollMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/poll-masters")
    @Timed
    public ResponseEntity<PollMaster> createPollMaster(@Valid @RequestBody PollMaster pollMaster) throws URISyntaxException {
        log.debug("REST request to save PollMaster : {}", pollMaster);
        if (pollMaster.getId() != null) {
            throw new BadRequestAlertException("A new pollMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        pollMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        pollMaster.setCreatedDate(Instant.now());
        PollMaster result = pollMasterRepository.save(pollMaster);
        if (result != null && pollMaster.getPollDetails().size() > 0) {
            for (PollDetails pollDetails : pollMaster.getPollDetails()) {
                pollDetails.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                pollDetails.setCreatedDate(Instant.now());
                pollDetails.setPollMaster(result);
                pollDetailsRepository.save(pollDetails);
            }
        }
        result.setPollDetails(pollDetailsRepository.findAllByPollMasterOrderByIdAsc(result));
        return ResponseEntity.created(new URI("/api/poll-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /poll-masters : Updates an existing pollMaster.
     *
     * @param pollMaster the pollMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pollMaster,
     * or with status 400 (Bad Request) if the pollMaster is not valid,
     * or with status 500 (Internal Server Error) if the pollMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/poll-masters")
    @Timed
    public ResponseEntity<PollMaster> updatePollMaster(@Valid @RequestBody PollMaster pollMaster) throws URISyntaxException {
        log.debug("REST request to update PollMaster : {}", pollMaster);
        if (pollMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        pollMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        pollMaster.setCreatedDate(Instant.now());
        PollMaster result = pollMasterRepository.save(pollMaster);
        pollDetailsRepository.deleteAllByPollMasterId(result.getId());
        if (result != null && pollMaster.getPollDetails().size() > 0) {
            for (PollDetails pollDetails : pollMaster.getPollDetails()) {
                pollDetails.setId(null);
                pollDetails.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                pollDetails.setCreatedDate(Instant.now());
                pollDetails.setPollMaster(result);
                pollDetailsRepository.save(pollDetails);
            }
        }
        result.setPollDetails(pollDetailsRepository.findAllByPollMasterOrderByIdAsc(result));
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pollMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /poll-masters : get all the pollMasters.
     *
     * @param pageable the pagination information
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of pollMasters in body
     */
    @GetMapping("/poll-masters")
    @Timed
    public ResponseEntity<List<PollMaster>> getAllPollMasters(@PageableDefault(value = Integer.MAX_VALUE) Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of PollMasters");
        Page<PollMaster> page;
        if (eagerload) {
            page = pollMasterRepository.findAllWithEagerRelationships(pageable);
        } else {
            page = pollMasterRepository.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format("/api/poll-masters?eagerload=%b", eagerload));
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /poll-masters/:id : get the "id" pollMaster.
     *
     * @param id the id of the pollMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pollMaster, or with status 404 (Not Found)
     */
    @GetMapping("/poll-masters/{id}")
    @Timed
    public ResponseEntity<PollMaster> getPollMaster(@PathVariable Long id) {
        log.debug("REST request to get PollMaster : {}", id);
        PollMaster pollMaster = pollMasterRepository.findOneWithEagerRelationships(id).orElse(null);
        pollMaster.setPollDetails(pollDetailsRepository.findAllByPollMasterOrderByIdAsc(pollMaster));
        return ResponseUtil.wrapOrNotFound(Optional.of(pollMaster));
    }

    /**
     * PUBLISH  /poll-masters/:id : publish the "id" pollMaster.
     *
     * @param id the id of the pollMaster to publish
     * @return the ResponseEntity with status 200 (OK)
     */
    @GetMapping("/poll-masters-publish/{id}")
    @Timed
    public ResponseEntity<PollMaster> publishPollMaster(@PathVariable Long id) {
        log.debug("REST request to publish PollMaster : {}", id);

        PollMaster pollMaster = pollMasterRepository.findOneWithEagerRelationships(id).orElse(null);
        pollMaster.setApprovedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        pollMaster.setApprovedDate(Instant.now());
        pollMaster.setFlag("A");
        PollMaster result = pollMasterRepository.save(pollMaster);
        result.setPollDetails(pollDetailsRepository.findAllByPollMasterOrderByIdAsc(pollMaster));
        for (FactoryMaster factoryMaster : result.getFactoryMasters()) {
            List<PollMaster> pollMasters = pollMasterRepository.findAllByFactoryMasters(factoryMaster.getId());
            for (PollMaster master : pollMasters) {
                if(pollMaster.getId().longValue() != master.getId().longValue()) {
                    master.endDate(Instant.now());
                    master.setFlag("C");
                    pollMasterRepository.save(master);
                }
            }
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(result));
    }

    /**
     * DELETE  /poll-masters/:id : delete the "id" pollMaster.
     *
     * @param id the id of the pollMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/poll-masters/{id}")
    @Timed
    public ResponseEntity<Void> deletePollMaster(@PathVariable Long id) {
        log.debug("REST request to delete PollMaster : {}", id);

        pollMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /leave-masters : get all the leaveMasters.
     *
     * @param !pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of leaveMasters in body
     */
    @GetMapping("/poll-masters-report/{id}")
    @Timed
    public @ResponseBody void downloadSalarySlip(@PathVariable Long id, HttpServletResponse response) throws JRException, IOException {
        log.debug("REST request to get a page of PollMasters");
        List<Object[]> objects = pollMasterRepository.countCreatedBy(id);
        List<PollReport> pollReports = new ArrayList<>();
        for (Object[] o : objects) {
            PollReport pollReport = new PollReport();
            pollReport.setPollText(o[0].toString());
            pollReport.setPollOption(o[1].toString());
            pollReport.setCount(Long.parseLong(o[2].toString()));
            pollReports.add(pollReport);
        }
        String path = applicationProperties.getTemplatePath()+"jasper/";
        JasperDesign jasperDesign = JRXmlLoader.load(path+"/Polls.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String,Object> parameters = new HashMap<>();
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(pollReports);


        parameters.put("datasource", jrDataSource);
        parameters.put("SUBREPORT_DIR",path);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, jrDataSource);
        response.setContentType("application/x-pdf");
        response.setHeader("Content-Disposition","attachment; filename=Polls.pdf");

        final OutputStream outputStream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint,outputStream);
    }
}
