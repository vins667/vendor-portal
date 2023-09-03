package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.domain.VehicleMaster;
import io.vamani.application.model.LeaveSearch;
import io.vamani.application.model.Message;
import io.vamani.application.mssql.domain.EmployeeView;
import io.vamani.application.mssql.repository.EmployeeViewRepository;
import io.vamani.application.repository.VehicleMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.service.MailService;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * REST controller for managing VehicleMaster.
 */
@RestController
@RequestMapping("/api")
public class VehicleMasterResource {

    private final Logger log = LoggerFactory.getLogger(VehicleMasterResource.class);

    private static final String ENTITY_NAME = "vehicleMaster";

    private final VehicleMasterRepository vehicleMasterRepository;

    @Autowired
    private EmployeeViewRepository employeeViewRepository;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private MailService mailService;

    @Autowired
    private ApplicationProperties applicationProperties;

    public VehicleMasterResource(VehicleMasterRepository vehicleMasterRepository) {
        this.vehicleMasterRepository = vehicleMasterRepository;
    }

    /**
     * POST  /vehicle-masters : Create a new vehicleMaster.
     *
     * @param vehicleMaster the vehicleMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vehicleMaster, or with status 400 (Bad Request) if the vehicleMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/vehicle-masters")
    @Timed
    public ResponseEntity<VehicleMaster> createVehicleMaster(@Valid @RequestBody VehicleMaster vehicleMaster) throws URISyntaxException {
        log.debug("REST request to save VehicleMaster : {}", vehicleMaster);
        if (vehicleMaster.getId() != null) {
            throw new BadRequestAlertException("A new vehicleMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        vehicleMaster.setPlaceFrom(vehicleMaster.getPlaceFrom().toUpperCase());
        vehicleMaster.setPlaceTo(vehicleMaster.getPlaceTo().toUpperCase());
        vehicleMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        vehicleMaster.setCreatedDate(Instant.now());
        VehicleMaster result = vehicleMasterRepository.save(vehicleMaster);
        EmployeeView requester = employeeViewRepository.findById(vehicleMaster.getUser().getLogin().toLowerCase()).orElse(null);
        EmployeeView approver = employeeViewRepository.findById(vehicleMaster.getHodApprovedBy().toLowerCase()).orElse(null);
        if (approver != null && approver.getEmail() != null && approver.getEmail().length() > 0) {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm");
            Locale locale = Locale.forLanguageTag("en");
            Context context = new Context(locale);
            context.setVariable("vehicleTypeName", (result.getVehicleType() != null && result.getVehicleType().equalsIgnoreCase("NC")) ? "Normal Vehicle" : "Luxury Vehicle");
            context.setVariable("empCode", requester.getCardNo());
            context.setVariable("name", requester.getName());
            context.setVariable("noVehicle", result.getNoVehicle());
            context.setVariable("vehicleDate", format.format(Date.from(result.getVehicleDate())));
            context.setVariable("placeFrom", result.getPlaceFrom());
            context.setVariable("placeTo", result.getPlaceTo());
            context.setVariable("flag", result.getFlag());
            context.setVariable("purpose", result.getPurpose());
            context.setVariable("url", applicationProperties.getUrl());
            String content = null;
            String subject = "Vehicle sanction request for " + ((result.getVehicleType() != null && result.getVehicleType().equalsIgnoreCase("NC")) ? "Normal Vehicle" : "Luxury Vehicle");
            try {
                content = templateEngine.process("mail/vehicle_request_mail", context);
                mailService.sendEmail(approver.getEmail(), subject, content, false, true);
                //mailService.sendEmail("vivekjaiswal@vamanioverseas.com", subject, content, false, true);
            } catch (Exception e) {
            }
        }
        return ResponseEntity.created(new URI("/api/vehicle-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /vehicle-masters : Updates an existing vehicleMaster.
     *
     * @param !vehicleMaster the vehicleMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vehicleMaster,
     * or with status 400 (Bad Request) if the vehicleMaster is not valid,
     * or with status 500 (Internal Server Error) if the vehicleMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vehicle-masters")
    @Timed
    public ResponseEntity<Message> updateVehicleMaster(@Valid @RequestBody VehicleMaster[] vehicleMasters) throws URISyntaxException {
        log.debug("REST request to update VehicleMaster : {}", vehicleMasters);
        int ctr = 0;
        String error = "";
        for (VehicleMaster vehicleMaster : vehicleMasters) {
            vehicleMaster.setHodApprovedDate(Instant.now());
            VehicleMaster result = vehicleMasterRepository.save(vehicleMaster);
            if (result != null) {
                ++ctr;
                EmployeeView requester = employeeViewRepository.findById(vehicleMaster.getUser().getLogin().toLowerCase()).orElse(null);
                if (requester != null && requester != null) {
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
                    Locale locale = Locale.forLanguageTag("en");
                    Context context = new Context(locale);
                    context.setVariable("vehicleTypeName", (result.getVehicleType() != null && result.getVehicleType().equalsIgnoreCase("NC")) ? "Normal Vehicle" : "Luxury Vehicle");
                    context.setVariable("empCode", requester.getCardNo());
                    context.setVariable("name", requester.getName());
                    context.setVariable("noVehicle", result.getNoVehicle());
                    context.setVariable("vehicleDate", format.format(Date.from(result.getVehicleDate())));
                    context.setVariable("placeFrom", result.getPlaceFrom());
                    context.setVariable("placeTo", result.getPlaceTo());
                    context.setVariable("flag", result.getFlag());
                    context.setVariable("purpose", result.getPurpose());
                    context.setVariable("url", applicationProperties.getUrl());
                    String content = null;
                    String subject = null;
                    if (result.getFlag().equalsIgnoreCase("A")) {
                        subject = "Vehicle Approved for " + ((result.getVehicleType() != null && result.getVehicleType().equalsIgnoreCase("NC")) ? "Normal Vehicle" : "Luxury Vehicle");
                    } else if (result.getFlag().equalsIgnoreCase("R")) {
                        subject = "Vehicle Rejected for " + ((result.getVehicleType() != null && result.getVehicleType().equalsIgnoreCase("NC")) ? "Normal Vehicle" : "Luxury Vehicle");
                    }
                    try {
                        content = templateEngine.process("mail/vehicle_request_mail", context);
                        mailService.sendEmail(requester.getEmail(), subject, content, false, true);
                        if (result.getFlag() != null && result.getFlag().equalsIgnoreCase("A")) {
                            mailService.sendEmail("transport@vamanioverseas.com", subject, content, false, true);
                        }
                    } catch (Exception e) {
                    }
                }
            } else {
                error += vehicleMaster.getId().toString() + ", ";
            }
        }
        if (ctr == vehicleMasters.length) {
            return ResponseEntity.ok()
                .body(new Message("success", "Save Successfully!"));
        } else {
            return ResponseEntity.ok()
                .body(new Message("error", "Issue saving in these id's " + error));
        }
    }

    /**
     * GET  /vehicle-masters : get all the vehicleMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of vehicleMasters in body
     */
    @GetMapping("/vehicle-masters")
    @Timed
    public ResponseEntity<List<VehicleMaster>> getAllVehicleMasters(@PageableDefault(value = Integer.MAX_VALUE) Pageable pageable) {
        log.debug("REST request to get a page of VehicleMasters");
        Page<VehicleMaster> page = vehicleMasterRepository.findAll(SecurityUtils.getCurrentUserLogin().orElse(null), pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/vehicle-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /leave-masters : get all the leaveMasters.
     *
     * @param !pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of leaveMasters in body
     */
    @PostMapping("/vehicle-masters-hod")
    @Timed
    public ResponseEntity<List<VehicleMaster>> getAllLeaveMastersByHod(@Valid @RequestBody LeaveSearch search) {
        log.debug("REST request to get a page of vehicleMasters");
        String empCode = "%";
        if (search.getEmpCode() != null) {
            empCode = search.getEmpCode() + "%";
        }
        Page<VehicleMaster> page = null;
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("id").descending()));
        if(search.getLeaveStatus() != null && search.getLeaveStatus().equalsIgnoreCase("P")) {
            page = vehicleMasterRepository.findAllByHodApprovedByPending(SecurityUtils.getCurrentUserLogin().orElse(null), Date.from(search.getLeaveDateFrom()), Date.from(search.getLeaveDateTo()), empCode, search.getPage());
        } else if(search.getLeaveStatus() != null && search.getLeaveStatus().equalsIgnoreCase("A")) {
            page = vehicleMasterRepository.findAllByHodApprovedByApproved(SecurityUtils.getCurrentUserLogin().orElse(null), Date.from(search.getLeaveDateFrom()), Date.from(search.getLeaveDateTo()), empCode, search.getPage());
        } else {
            page = vehicleMasterRepository.findAllByHodApprovedByRejected(SecurityUtils.getCurrentUserLogin().orElse(null), Date.from(search.getLeaveDateFrom()), Date.from(search.getLeaveDateTo()), empCode, search.getLeaveStatus(), search.getPage());
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/vehicle-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }   /**
     * GET  /leave-masters : get all the leaveMasters.
     *
     * @param !pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of leaveMasters in body
     */
    @PostMapping("/vehicle-masters-transport")
    @Timed
    public ResponseEntity<List<VehicleMaster>> getAllLeaveMastersByTransport(@Valid @RequestBody LeaveSearch search) {
        log.debug("REST request to get a page of vehicleMasters");
        String empCode = "%";
        if (search.getEmpCode() != null) {
            empCode = search.getEmpCode() + "%";
        }
        Page<VehicleMaster> page = null;
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("id").descending()));
        if(search.getLeaveStatus() != null && search.getLeaveStatus().equalsIgnoreCase("P")) {
            page = vehicleMasterRepository.findAllByHrApprovedByPending(Date.from(search.getLeaveDateFrom()), Date.from(search.getLeaveDateTo()), empCode, search.getPage());
        } else if(search.getLeaveStatus() != null && search.getLeaveStatus().equalsIgnoreCase("A")) {
            page = vehicleMasterRepository.findAllByHrApprovedByApproved(Date.from(search.getLeaveDateFrom()), Date.from(search.getLeaveDateTo()), empCode, "C", search.getPage());
        } else {
            page = vehicleMasterRepository.findAllByHrApprovedByApproved(Date.from(search.getLeaveDateFrom()), Date.from(search.getLeaveDateTo()), empCode, "R", search.getPage());
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/vehicle-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * PUT  /vehicle-masters : Updates an existing vehicleMaster.
     *
     * @param !vehicleMaster the vehicleMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vehicleMaster,
     * or with status 400 (Bad Request) if the vehicleMaster is not valid,
     * or with status 500 (Internal Server Error) if the vehicleMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vehicle-masters-transport")
    @Timed
    public ResponseEntity<VehicleMaster> updateVehicleMasterByTransport(@Valid @RequestBody VehicleMaster vehicleMaster) throws URISyntaxException {
        log.debug("REST request to update VehicleMaster : {}", vehicleMaster);
        int ctr = 0;
        String error = "";
        vehicleMaster.setAdminApprovedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        vehicleMaster.setAdminApprovedDate(Instant.now());
        VehicleMaster result = vehicleMasterRepository.save(vehicleMaster);
        if (result != null) {
            EmployeeView requester = employeeViewRepository.findById(vehicleMaster.getUser().getLogin().toLowerCase()).orElse(null);
            if (requester != null && requester != null) {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
                Locale locale = Locale.forLanguageTag("en");
                Context context = new Context(locale);
                context.setVariable("vehicleTypeName", (result.getVehicleType() != null && result.getVehicleType().equalsIgnoreCase("NC")) ? "Normal Vehicle" : "Luxury Vehicle");
                context.setVariable("empCode", requester.getCardNo());
                context.setVariable("name", requester.getName());
                context.setVariable("noVehicle", result.getNoVehicle());
                context.setVariable("vehicleDate", format.format(Date.from(result.getVehicleDate())));
                context.setVariable("placeFrom", result.getPlaceFrom());
                context.setVariable("placeTo", result.getPlaceTo());
                context.setVariable("flag", result.getFlag());
                context.setVariable("purpose", result.getPurpose());
                context.setVariable("vehicle", result.getVehicleNumber());
                context.setVariable("driver", result.getDriverName());
                context.setVariable("adminRemarks", result.getAdminRemarks());
                context.setVariable("url", applicationProperties.getUrl());
                String content = null;
                String subject = null;
                if (result.getFlag().equalsIgnoreCase("C")) {
                    subject = "Vehicle Approved by Transport for " + ((result.getVehicleType() != null && result.getVehicleType().equalsIgnoreCase("NC")) ? "Normal Vehicle" : "Luxury Vehicle");
                } else if (result.getFlag().equalsIgnoreCase("R")) {
                    subject = "Vehicle Rejected by Transport for " + ((result.getVehicleType() != null && result.getVehicleType().equalsIgnoreCase("NC")) ? "Normal Vehicle" : "Luxury Vehicle");
                }
                try {
                    content = templateEngine.process("mail/vehicle_request_mail", context);
                    mailService.sendEmail(requester.getEmail(), subject, content, false, true);
                    //mailService.sendEmail("vivekjaiswal@vamanioverseas.com", subject, content, false, true);
                } catch (Exception e) {
                }
            }
        } else {
            error += vehicleMaster.getId().toString() + ", ";
        }
        return ResponseEntity.created(new URI("/api/vehicle-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * GET  /vehicle-masters/:id : get the "id" vehicleMaster.
     *
     * @param id the id of the vehicleMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vehicleMaster, or with status 404 (Not Found)
     */
    @GetMapping("/vehicle-masters/{id}")
    @Timed
    public ResponseEntity<VehicleMaster> getVehicleMaster(@PathVariable Long id) {
        log.debug("REST request to get VehicleMaster : {}", id);
        Optional<VehicleMaster> vehicleMaster = vehicleMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(vehicleMaster);
    }

    /**
     * DELETE  /vehicle-masters/:id : delete the "id" vehicleMaster.
     *
     * @param id the id of the vehicleMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/vehicle-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteVehicleMaster(@PathVariable Long id) {
        log.debug("REST request to delete VehicleMaster : {}", id);

        vehicleMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
