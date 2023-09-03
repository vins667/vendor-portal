package io.vamani.application.web.rest;

import com.ibm.icu.util.Calendar;
import io.vamani.application.domain.*;
import io.vamani.application.mobile.*;
import io.vamani.application.model.ConveyanceSearchMaster;
import io.vamani.application.model.Master;
import io.vamani.application.model.Message;
import org.apache.commons.collections4.map.HashedMap;
import io.vamani.application.model.TravelApplicationMasterBean;
import io.vamani.application.mssql.domain.EmployeeMatView;
import io.vamani.application.mssql.domain.EmployeeView;
import io.vamani.application.mssql.repository.EmployeeMatViewRepository;
import io.vamani.application.mssql.repository.EmployeeViewRepository;
import io.vamani.application.repository.TravelAccommodationDetailsRepository;
import io.vamani.application.repository.TravelApplicationMasterRepository;
import io.vamani.application.repository.TravelFlightDetailsRepository;
import io.vamani.application.repository.TravelForexDetailsRepository;
import io.vamani.application.repository.TravelLuggageDetailsRepository;
import io.vamani.application.repository.TravelPassengerDetailsRepository;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.codahale.metrics.annotation.Timed;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/**
 * REST controller for managing {@link io.vamani.application.domain.TravelApplicationMaster}.
 */
@RestController
@RequestMapping("/api")
public class TravelApplicationMasterResource {

    private final Logger log = LoggerFactory.getLogger(TravelApplicationMasterResource.class);

    private static final String ENTITY_NAME = "travelApplicationMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    TravelFlightDetailsRepository travelFlightDetailsRepository;

    @Autowired
    TravelAccommodationDetailsRepository travelAccommodationRepository;

    @Autowired
    TravelForexDetailsRepository travelForexDetailsRepository;

    @Autowired
    TravelLuggageDetailsRepository travelLuggageDetailsRepository;

    @Autowired
    EmployeeViewRepository employeeViewRepository;

    @Autowired
    EmployeeMatViewRepository employeeMatViewRepository;

    @Autowired
    TravelPassengerDetailsRepository travelPassengerDetailsRepository;


    private final TravelApplicationMasterRepository travelApplicationMasterRepository;

    public TravelApplicationMasterResource(TravelApplicationMasterRepository travelApplicationMasterRepository) {
        this.travelApplicationMasterRepository = travelApplicationMasterRepository;
    }

    /**
     * {@code POST  /travel-application-masters} : Create a new
     * travelApplicationMaster.
     *
     * @param travelApplicationMaster the travelApplicationMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     * body the new travelApplicationMaster, or with status
     * {@code 400 (Bad Request)} if the travelApplicationMaster has already
     * an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/travel-application-masters")
    public ResponseEntity<TravelApplicationMaster> createTravelApplicationMaster(
        @Valid @RequestBody TravelApplicationMasterBean travelApplicationMasterBean) throws URISyntaxException {
        log.debug("REST request to save TravelApplicationMaster : {}", travelApplicationMasterBean);
        if (travelApplicationMasterBean.getId() != null) {
            throw new BadRequestAlertException("A new travelApplicationMaster cannot already have an ID", ENTITY_NAME,
                "idexists");
        }
        TravelApplicationMaster travelApplicationMaster = new TravelApplicationMaster();
        BeanUtils.copyProperties(travelApplicationMasterBean, travelApplicationMaster, "travelFlightDetails",
            "travelAccommodationDetails", "travelForexDetails", "travelLuggageDetails");
        travelApplicationMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        travelApplicationMaster.setEmpCode(SecurityUtils.getCurrentUserLogin().orElse(null));
        travelApplicationMaster.setCreatedDate(Instant.now());
        travelApplicationMaster.setStatus("E");
        TravelApplicationMaster result = travelApplicationMasterRepository.save(travelApplicationMaster);
        if (result != null) {
            if (travelApplicationMasterBean.getTravelPassengerDetails() != null && travelApplicationMasterBean.getTravelPassengerDetails().size() > 0) {
                for (TravelPassengerDetails bean5 : travelApplicationMasterBean.getTravelPassengerDetails()) {
                    if (bean5.getPassengerName() != null && bean5.getPassengerName().length() > 0) {
                        bean5.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                        bean5.setCreatedDate(Instant.now());
                        bean5.setTravelApplicationMaster(result);
                        travelPassengerDetailsRepository.save(bean5);
                    }
                }
            }
            if (travelApplicationMasterBean.getTravelFlightDetails() != null && travelApplicationMasterBean.getTravelFlightDetails().size() > 0) {
                for (TravelFlightDetails bean : travelApplicationMasterBean.getTravelFlightDetails()) {
                    if (bean.getTravelDate() != null) {
                        bean.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                        bean.setCreatedDate(Instant.now());
                        bean.setTravelApplicationMaster(result);
                        travelFlightDetailsRepository.save(bean);
                    }
                }
            }
            if (travelApplicationMasterBean.getTravelAccommodationDetails() != null && travelApplicationMasterBean.getTravelAccommodationDetails().size() > 0) {
                for (TravelAccommodationDetails bean2 : travelApplicationMasterBean.getTravelAccommodationDetails()) {
                    if (bean2.getAccommodationDate() != null) {
                        bean2.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                        bean2.setCreatedDate(Instant.now());
                        bean2.setTravelApplicationMaster(result);
                        travelAccommodationRepository.save(bean2);
                    }
                }
            }
            if (travelApplicationMasterBean.getTravelLuggageDetails() != null && travelApplicationMasterBean.getTravelLuggageDetails().size() > 0) {
                for (TravelLuggageDetails bean4 : travelApplicationMasterBean.getTravelLuggageDetails()) {
                    if (bean4.getLuggageType() != null && bean4.getLuggageType().length() > 0) {
                        bean4.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                        bean4.setCreatedDate(Instant.now());
                        bean4.setTravelApplicationMaster(result);
                        travelLuggageDetailsRepository.save(bean4);
                    }
                }
            }
            if (travelApplicationMasterBean.getTravelForexDetails() != null && travelApplicationMasterBean.getTravelForexDetails().size() > 0) {
                for (TravelForexDetails bean3 : travelApplicationMasterBean.getTravelForexDetails()) {
                    if (bean3.getForexType() != null) {
                        bean3.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                        bean3.setCreatedDate(Instant.now());
                        bean3.setTravelApplicationMaster(result);
                        travelForexDetailsRepository.save(bean3);
                    }
                }
            }
        }
        return ResponseEntity.created(new URI("/api/travel-application-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /travel-application-masters} : Updates an existing travelApplicationMaster.
     *
     * @param travelApplicationMaster the travelApplicationMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated travelApplicationMaster,
     * or with status {@code 400 (Bad Request)} if the travelApplicationMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the travelApplicationMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/travel-application-masters")
    public ResponseEntity<TravelApplicationMaster> updateTravelApplicationMaster(@Valid @RequestBody TravelApplicationMasterBean travelApplicationMasterBean) throws URISyntaxException {
        log.debug("REST request to update TravelApplicationMaster : {}", travelApplicationMasterBean);
        if (travelApplicationMasterBean.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TravelApplicationMaster travelApplicationMaster = new TravelApplicationMaster();
        BeanUtils.copyProperties(travelApplicationMasterBean, travelApplicationMaster, "travelFlightDetails",
            "travelAccommodationDetails", "travelForexDetails", "travelLuggageDetails");
        travelApplicationMaster.setLastUpdatedDate(Instant.now());
        travelApplicationMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        travelApplicationMaster.setEmpCode(SecurityUtils.getCurrentUserLogin().orElse(null));
        travelApplicationMaster.setStatus("E");
        TravelApplicationMaster result = travelApplicationMasterRepository.save(travelApplicationMaster);
        if (result != null) {
            if (travelApplicationMasterBean.getTravelPassengerDetails() != null && travelApplicationMasterBean.getTravelPassengerDetails().size() > 0) {
                for (TravelPassengerDetails bean : travelApplicationMasterBean.getTravelPassengerDetails()) {
                    if (bean.getPassengerName() != null && bean.getPassengerName().length() > 0) {
                        if (bean != null && bean.getId() != null) {
                            bean.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                            bean.setLastUpdatedDate(Instant.now());
                        } else {
                            bean.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                            bean.setCreatedDate(Instant.now());
                        }
                        bean.setTravelApplicationMaster(result);
                        travelPassengerDetailsRepository.save(bean);
                    }
                }
            }
            if (travelApplicationMasterBean.getTravelFlightDetails() != null && travelApplicationMasterBean.getTravelFlightDetails().size() > 0) {
                for (TravelFlightDetails bean : travelApplicationMasterBean.getTravelFlightDetails()) {
                    if (bean.getTravelDate() != null) {
                        if (bean != null && bean.getId() != null) {
                            bean.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                            bean.setLastUpdatedDate(Instant.now());
                        } else {
                            bean.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                            bean.setCreatedDate(Instant.now());
                        }
                        bean.setTravelApplicationMaster(result);
                        travelFlightDetailsRepository.save(bean);
                    }
                }
            }
            if (travelApplicationMasterBean.getTravelAccommodationDetails() != null && travelApplicationMasterBean.getTravelAccommodationDetails().size() > 0) {
                for (TravelAccommodationDetails bean : travelApplicationMasterBean.getTravelAccommodationDetails()) {
                    if (bean.getAccommodationDate() != null) {
                        if (bean != null && bean.getId() != null) {
                            bean.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                            bean.setLastUpdatedDate(Instant.now());
                        } else {
                            bean.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                            bean.setCreatedDate(Instant.now());
                        }
                        bean.setTravelApplicationMaster(result);
                        travelAccommodationRepository.save(bean);
                    }
                }
            }
            if (travelApplicationMasterBean.getTravelLuggageDetails() != null && travelApplicationMasterBean.getTravelLuggageDetails().size() > 0) {
                for (TravelLuggageDetails bean : travelApplicationMasterBean.getTravelLuggageDetails()) {
                    if (bean.getLuggageType() != null && bean.getLuggageType().length() > 0) {
                        if (bean != null && bean.getId() != null) {
                            bean.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                            bean.setLastUpdatedDate(Instant.now());
                        } else {
                            bean.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                            bean.setCreatedDate(Instant.now());
                        }
                        bean.setTravelApplicationMaster(result);
                        travelLuggageDetailsRepository.save(bean);
                    }
                }
            }
            if (travelApplicationMasterBean.getTravelForexDetails() != null && travelApplicationMasterBean.getTravelForexDetails().size() > 0) {
                for (TravelForexDetails bean3 : travelApplicationMasterBean.getTravelForexDetails()) {
                    if (bean3.getForexType() != null) {
                        if (bean3 != null && bean3.getId() != null) {
                            bean3.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                            bean3.setLastUpdatedDate(Instant.now());
                        } else {
                            bean3.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                            bean3.setCreatedDate(Instant.now());
                        }
                        bean3.setTravelApplicationMaster(result);
                        travelForexDetailsRepository.save(bean3);
                    }
                }
            }
        }
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, travelApplicationMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /travel-application-masters} : get all the travelApplicationMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of travelApplicationMasters in body.
     */
    @GetMapping("/travel-application-masters")
    public ResponseEntity<List<TravelApplicationMaster>> getAllTravelApplicationMasters(Pageable pageable) {
        log.debug("REST request to get a page of TravelApplicationMasters");
        Page<TravelApplicationMaster> page = travelApplicationMasterRepository.findAll(SecurityUtils.getCurrentUserLogin().orElse(null), pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }


    /**
     * {@code GET  /travel-application-masters/:id} : get the "id"
     * travelApplicationMaster. @param id the id of the travelApplicationMaster to
     * retrieve.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with
     * body`the travelApplicationMaster, or with status
     * {@code 404 (Not Found)}.
     */
    @GetMapping("/travel-application-masters/{id}")
    public ResponseEntity<TravelApplicationMasterBean> getTravelApplicationMaster(@PathVariable Long id) {
        log.debug("REST request to get TravelApplicationMaster : {}", id);
        TravelApplicationMasterBean bean = new TravelApplicationMasterBean();
        TravelApplicationMaster travelApplicationMaster = travelApplicationMasterRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(travelApplicationMaster, bean);
        bean.setTravelAccommodationDetails(travelAccommodationRepository.findByTravelApplicationMasterId(travelApplicationMaster.getId()));
        bean.setTravelFlightDetails(travelFlightDetailsRepository.findByTravelApplicationMasterId(travelApplicationMaster.getId()));
        bean.setTravelForexDetails(travelForexDetailsRepository.findByTravelApplicationMasterId(travelApplicationMaster.getId()));
        bean.setTravelLuggageDetails(travelLuggageDetailsRepository.findByTravelApplicationMasterId(travelApplicationMaster.getId()));
        bean.setTravelPassengerDetails(travelPassengerDetailsRepository.findByTravelApplicationMasterId(travelApplicationMaster.getId()));
        return ResponseUtil.wrapOrNotFound(Optional.of(bean));
    }

    /**
     * {@code GET  /travel-application-masters/:id} : get the "id"
     * travelApplicationMaster.
     *
     * @param id the id of the travelApplicationMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     * the travelApplicationMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/travel-application-masters-view/{id}")
    public ResponseEntity<TravelApplicationMasterBean> getTravelApplicationMasterById(@PathVariable Long id) {
        log.debug("REST request to get TravelApplicationMaster : {}", id);
        TravelApplicationMasterBean bean = new TravelApplicationMasterBean();
        TravelApplicationMaster travelApplicationMaster = travelApplicationMasterRepository.findById(id).orElse(null);
        bean.setName(employeeMatViewRepository.findByLogins(travelApplicationMaster.getEmpCode()).getName());
        BeanUtils.copyProperties(travelApplicationMaster, bean);
        bean.setTravelAccommodationDetails(travelAccommodationRepository.findByTravelApplicationMasterId(travelApplicationMaster.getId()));
        bean.setTravelFlightDetails(travelFlightDetailsRepository.findByTravelApplicationMasterId(travelApplicationMaster.getId()));
        bean.setTravelForexDetails(travelForexDetailsRepository.findByTravelApplicationMasterId(travelApplicationMaster.getId()));
        bean.setTravelLuggageDetails(travelLuggageDetailsRepository.findByTravelApplicationMasterId(travelApplicationMaster.getId()));
        bean.setTravelPassengerDetails(travelPassengerDetailsRepository.findByTravelApplicationMasterId(travelApplicationMaster.getId()));
        return ResponseUtil.wrapOrNotFound(Optional.of(bean));
    }

    /**
     * {@code DELETE  /travel-application-masters/:id} : delete the "id" travelApplicationMaster.
     *
     * @param id the id of the travelApplicationMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/travel-application-masters/{id}")
    public ResponseEntity<Void> deleteTravelApplicationMaster(@PathVariable Long id) {
        log.debug("REST request to delete TravelApplicationMaster : {}", id);
        travelFlightDetailsRepository.deleteFlightDtilById(id);
        travelForexDetailsRepository.deleteForexById(id);
        travelLuggageDetailsRepository.deleteLuggageById(id);
        travelAccommodationRepository.deleteHotelById(id);
        travelApplicationMasterRepository.deleteById(id);
        travelPassengerDetailsRepository.deletePassengerById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    @PostMapping("/travel-application-masters-hod")
    @Timed
    public ResponseEntity<List<TravelApplicationMasterBean>> getAllHrTravelApplicationMasterHod(@Valid @RequestBody ConveyanceSearchMaster search) {
        log.debug("REST request to get a page of travelApplicationMaster");
        String empCode = SecurityUtils.getCurrentUserLogin().orElse(null);
        Map<String, List<TravelApplicationMaster>> map = new HashedMap<>();
        List<TravelApplicationMasterBean> travelApplicationMasterBean = new ArrayList<TravelApplicationMasterBean>();
        Page<TravelApplicationMaster> page = null;
        if (search.getStatus() != null && search.getStatus().equalsIgnoreCase("E")) {
            page = travelApplicationMasterRepository.findAllByHODApprovedByPending(empCode, search.getPage());
            for (TravelApplicationMaster travelApplicationMaster : page.getContent()) {
                if (map.containsKey(travelApplicationMaster.getEmpCode())) {
                    List<TravelApplicationMaster> list = map.get(travelApplicationMaster.getEmpCode());
                    list.add(travelApplicationMaster);
                    map.put(travelApplicationMaster.getEmpCode(), list);
                } else {
                    List<TravelApplicationMaster> list = new ArrayList<>();
                    list.add(travelApplicationMaster);
                    map.put(travelApplicationMaster.getEmpCode(), list);
                }
            }
            if (page.getContent() != null && page.getContent().size() > 0) {
                List<EmployeeView> employeeMatView = employeeViewRepository.findAllByLogins(new ArrayList(map.keySet()));
                for (EmployeeView bean : employeeMatView) {
                    List<TravelApplicationMaster> travelApplicationMasters = map.get(bean.getLogin());
                    for (TravelApplicationMaster travelApplicationMaster : travelApplicationMasters) {
                        TravelApplicationMasterBean qryBean = new TravelApplicationMasterBean();
                        qryBean.setName(bean.getName());
                        BeanUtils.copyProperties(travelApplicationMaster, qryBean);
                        travelApplicationMasterBean.add(qryBean);
                    }
                }
            }
        } else if (search.getStatus() != null && search.getStatus().equalsIgnoreCase("A")) {
            page = travelApplicationMasterRepository.findAllByHODApproved(search.getConveyanceDate(), search.getConveyanceDateTo(), empCode, search.getPage());
            for (TravelApplicationMaster travelApplicationMaster : page.getContent()) {
                if (map.containsKey(travelApplicationMaster.getEmpCode())) {
                    List<TravelApplicationMaster> list = map.get(travelApplicationMaster.getEmpCode());
                    list.add(travelApplicationMaster);
                    map.put(travelApplicationMaster.getEmpCode(), list);
                } else {
                    List<TravelApplicationMaster> list = new ArrayList<>();
                    list.add(travelApplicationMaster);
                    map.put(travelApplicationMaster.getEmpCode(), list);
                }
            }
            if (page.getContent() != null && page.getContent().size() > 0) {
                List<EmployeeView> employeeMatView = employeeViewRepository.findAllByLogins(new ArrayList(map.keySet()));
                for (EmployeeView bean : employeeMatView) {
                    List<TravelApplicationMaster> travelApplicationMasters = map.get(bean.getLogin());
                    for (TravelApplicationMaster travelApplicationMaster : travelApplicationMasters) {
                        TravelApplicationMasterBean qryBean = new TravelApplicationMasterBean();
                        qryBean.setName(bean.getName());
                        BeanUtils.copyProperties(travelApplicationMaster, qryBean);
                        travelApplicationMasterBean.add(qryBean);
                    }
                }
            }
        } else {
            page = travelApplicationMasterRepository.findAllByHODApprovedByRejected(search.getConveyanceDate(), search.getConveyanceDateTo(), empCode, search.getPage());
            for (TravelApplicationMaster travelApplicationMaster : page.getContent()) {
                if (map.containsKey(travelApplicationMaster.getEmpCode())) {
                    List<TravelApplicationMaster> list = map.get(travelApplicationMaster.getEmpCode());
                    list.add(travelApplicationMaster);
                    map.put(travelApplicationMaster.getEmpCode(), list);
                } else {
                    List<TravelApplicationMaster> list = new ArrayList<>();
                    list.add(travelApplicationMaster);
                    map.put(travelApplicationMaster.getEmpCode(), list);
                }
            }
            if (page.getContent() != null && page.getContent().size() > 0) {
                List<EmployeeView> employeeMatView = employeeViewRepository.findAllByLogins(new ArrayList(map.keySet()));
                for (EmployeeView bean : employeeMatView) {
                    List<TravelApplicationMaster> travelApplicationMasters = map.get(bean.getLogin());
                    for (TravelApplicationMaster travelApplicationMaster : travelApplicationMasters) {
                        TravelApplicationMasterBean qryBean = new TravelApplicationMasterBean();
                        qryBean.setName(bean.getName());
                        BeanUtils.copyProperties(travelApplicationMaster, qryBean);
                        travelApplicationMasterBean.add(qryBean);
                    }
                }
            }
        }
        HttpHeaders headers = PaginationUtil
            .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(travelApplicationMasterBean);
    }

    @PutMapping("/travel-application-masters-update-hr")
    public ResponseEntity<Message> updateTravelApplicationMasterHr(
        @Valid @RequestBody TravelApplicationMasterBean travelApplicationMasterBean) throws URISyntaxException {
        log.debug("REST request to update ConveyanceMaster : {}", travelApplicationMasterBean);
        if (travelApplicationMasterBean.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TravelApplicationMaster travelApplicationMaster = new TravelApplicationMaster();
        BeanUtils.copyProperties(travelApplicationMasterBean, travelApplicationMaster, "travelFlightDetails",
            "travelAccommodationDetails", "travelForexDetails", "travelLuggageDetails");
        travelApplicationMaster.setHrApprovedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        travelApplicationMaster.setHrApprovedDate(Instant.now());
        travelApplicationMaster.setStatus("A");
        TravelApplicationMaster result = travelApplicationMasterRepository.save(travelApplicationMaster);
        if (result != null) {
            if (travelApplicationMasterBean.getTravelPassengerDetails() != null && travelApplicationMasterBean.getTravelPassengerDetails().size() > 0) {
                for (TravelPassengerDetails bean : travelApplicationMasterBean.getTravelPassengerDetails()) {
                    if (bean.getPassengerName() != null && bean.getPassengerName().length() > 0) {
                        if (bean != null && bean.getId() != null) {
                            bean.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                            bean.setLastUpdatedDate(Instant.now());
                        } else {
                            bean.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                            bean.setCreatedDate(Instant.now());
                        }
                        bean.setTravelApplicationMaster(result);
                        travelPassengerDetailsRepository.save(bean);
                    }
                }
            }
            if (travelApplicationMasterBean.getTravelFlightDetails() != null && travelApplicationMasterBean.getTravelFlightDetails().size() > 0) {
                for (TravelFlightDetails bean : travelApplicationMasterBean.getTravelFlightDetails()) {
                    if (bean.getTravelDate() != null) {
                        if (bean != null && bean.getId() != null) {
                            bean.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                            bean.setLastUpdatedDate(Instant.now());
                        } else {
                            bean.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                            bean.setCreatedDate(Instant.now());
                        }
                        bean.setTravelApplicationMaster(result);
                        travelFlightDetailsRepository.save(bean);
                    }
                }
            }
            if (travelApplicationMasterBean.getTravelAccommodationDetails() != null && travelApplicationMasterBean.getTravelAccommodationDetails().size() > 0) {
                for (TravelAccommodationDetails bean : travelApplicationMasterBean.getTravelAccommodationDetails()) {
                    if (bean.getAccommodationDate() != null) {
                        if (bean != null && bean.getId() != null) {
                            bean.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                            bean.setLastUpdatedDate(Instant.now());
                        } else {
                            bean.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                            bean.setCreatedDate(Instant.now());
                        }
                        bean.setTravelApplicationMaster(result);
                        travelAccommodationRepository.save(bean);
                    }
                }
            }
            if (travelApplicationMasterBean.getTravelLuggageDetails() != null && travelApplicationMasterBean.getTravelLuggageDetails().size() > 0) {
                for (TravelLuggageDetails bean : travelApplicationMasterBean.getTravelLuggageDetails()) {
                    if (bean.getLuggageType() != null && bean.getLuggageType().length() > 0) {
                        if (bean != null && bean.getId() != null) {
                            bean.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                            bean.setLastUpdatedDate(Instant.now());
                        } else {
                            bean.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                            bean.setCreatedDate(Instant.now());
                        }
                        bean.setTravelApplicationMaster(result);
                        travelLuggageDetailsRepository.save(bean);
                    }
                }
            }
            if (travelApplicationMasterBean.getTravelForexDetails() != null && travelApplicationMasterBean.getTravelForexDetails().size() > 0) {
                for (TravelForexDetails bean3 : travelApplicationMasterBean.getTravelForexDetails()) {
                    if (bean3.getForexType() != null) {
                        if (bean3 != null && bean3.getId() != null) {
                            bean3.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                            bean3.setLastUpdatedDate(Instant.now());
                        } else {
                            bean3.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                            bean3.setCreatedDate(Instant.now());
                        }
                        bean3.setTravelApplicationMaster(result);
                        travelForexDetailsRepository.save(bean3);
                    }
                }
            }
        }
        return ResponseEntity.ok().body(new Message("success", "Save Successfully!"));
    }

    @PostMapping("/travel-application-masters-hr")
    @Timed
    public ResponseEntity<List<TravelApplicationMasterBean>> getAllHrTravelApplicationMaster(@Valid @RequestBody ConveyanceSearchMaster search) {
        log.debug("REST request to get a page of travelApplicationMaster");
        Map<String, List<TravelApplicationMaster>> map = new HashedMap<>();
        List<TravelApplicationMasterBean> travelApplicationMasterBean = new ArrayList<TravelApplicationMasterBean>();
        Page<TravelApplicationMaster> page = null;
        if(search.getStatus() != null && search.getStatus().equalsIgnoreCase("P")) {
            page = travelApplicationMasterRepository.findAllByHrApprovedByPending(search.getPage());
            for(TravelApplicationMaster travelApplicationMaster:page.getContent()) {
                if(map.containsKey(travelApplicationMaster.getEmpCode())) {
                    List<TravelApplicationMaster> list = map.get(travelApplicationMaster.getEmpCode());
                    list.add(travelApplicationMaster);
                    map.put(travelApplicationMaster.getEmpCode(), list);
                }else {
                    List<TravelApplicationMaster> list = new ArrayList<>();
                    list.add(travelApplicationMaster);
                    map.put(travelApplicationMaster.getEmpCode(), list);
                }
            }
            if(page.getContent()!=null && page.getContent().size()>0) {
                List<EmployeeMatView> employeeMatView = employeeMatViewRepository.findAllByLogins(new ArrayList(map.keySet()));
                for(EmployeeMatView bean:employeeMatView) {
                    List<TravelApplicationMaster> travelApplicationMasters = map.get(bean.getLogin());
                    for(TravelApplicationMaster travelApplicationMaster : travelApplicationMasters) {
                        TravelApplicationMasterBean qryBean =  new TravelApplicationMasterBean();
                        qryBean.setName(bean.getName());
                        BeanUtils.copyProperties(travelApplicationMaster, qryBean);
                        travelApplicationMasterBean.add(qryBean);
                    }
                }
            }
        }else if(search.getStatus() != null && search.getStatus().equalsIgnoreCase("C")) {
            page = travelApplicationMasterRepository.findAllByHrApproved(search.getConveyanceDate(), search.getConveyanceDateTo(), SecurityUtils.getCurrentUserLogin().orElse(null), search.getPage());
            for(TravelApplicationMaster travelApplicationMaster:page.getContent()) {
                if(map.containsKey(travelApplicationMaster.getEmpCode())) {
                    List<TravelApplicationMaster> list = map.get(travelApplicationMaster.getEmpCode());
                    list.add(travelApplicationMaster);
                    map.put(travelApplicationMaster.getEmpCode(), list);
                }else {
                    List<TravelApplicationMaster> list = new ArrayList<>();
                    list.add(travelApplicationMaster);
                    map.put(travelApplicationMaster.getEmpCode(), list);
                }
            }
            if(page.getContent()!=null && page.getContent().size()>0) {
                List<EmployeeMatView> employeeMatView = employeeMatViewRepository.findAllByLogins(new ArrayList(map.keySet()));
                for(EmployeeMatView bean:employeeMatView) {
                    List<TravelApplicationMaster> travelApplicationMasters = map.get(bean.getLogin());
                    for(TravelApplicationMaster travelApplicationMaster : travelApplicationMasters) {
                        TravelApplicationMasterBean qryBean =  new TravelApplicationMasterBean();
                        qryBean.setName(bean.getName());
                        BeanUtils.copyProperties(travelApplicationMaster, qryBean);
                        travelApplicationMasterBean.add(qryBean);
                    }
                }
            }
        }else {
            page = travelApplicationMasterRepository.findAllByHrApprovedByRejected(search.getConveyanceDate(), search.getConveyanceDateTo(), SecurityUtils.getCurrentUserLogin().orElse(null), search.getPage());
            for(TravelApplicationMaster travelApplicationMaster:page.getContent()) {
                if(map.containsKey(travelApplicationMaster.getEmpCode())) {
                    List<TravelApplicationMaster> list = map.get(travelApplicationMaster.getEmpCode());
                    list.add(travelApplicationMaster);
                    map.put(travelApplicationMaster.getEmpCode(), list);
                }else {
                    List<TravelApplicationMaster> list = new ArrayList<>();
                    list.add(travelApplicationMaster);
                    map.put(travelApplicationMaster.getEmpCode(), list);
                }
            }
            if(page.getContent()!=null && page.getContent().size()>0) {
                List<EmployeeMatView> employeeMatView = employeeMatViewRepository.findAllByLogins(new ArrayList(map.keySet()));
                for(EmployeeMatView bean:employeeMatView) {
                    List<TravelApplicationMaster> travelApplicationMasters = map.get(bean.getLogin());
                    for(TravelApplicationMaster travelApplicationMaster : travelApplicationMasters) {
                        TravelApplicationMasterBean qryBean =  new TravelApplicationMasterBean();
                        qryBean.setName(bean.getName());
                        BeanUtils.copyProperties(travelApplicationMaster, qryBean);
                        travelApplicationMasterBean.add(qryBean);
                    }
                }
            }
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(travelApplicationMasterBean);
    }

    /**
     * {@code Approve  /travel-application-masters/:id} : approve the "id" travelApplicationMaster.
     *
     * @param id the id of the travelApplicationMaster to approve.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @GetMapping("/travel-application-masters-hod-approve/{id}/{flag}")
    public ResponseEntity<TravelApplicationMaster> getTravelApplicationMasterApprove(@PathVariable Long id, @PathVariable String flag) {
        log.debug("REST request to approve TravelApplicationMaster : {}", id);
        TravelApplicationMaster travelApplicationMaster = travelApplicationMasterRepository.findById(id).orElse(null);
        travelApplicationMaster.setStatus(flag);
        travelApplicationMaster.setHodApprovedDate(Instant.now());
        TravelApplicationMaster result = travelApplicationMasterRepository.save(travelApplicationMaster);
        return ResponseEntity.ok().body(result);
    }



    /**
     * {@code GET  /conveyance-masters} : get all the conveyanceMasters.
     *
     * @param pageable    the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder  a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of conveyanceMasters in body.
     */
    @PostMapping("/travel-application-masters-mobile-qry")
    @Timed
    public ResponseEntity<List<TravelApplicationMasterMobileBean>> getAllTravelApplicationMasters(@Valid @RequestBody Master search) throws ParseException {
        log.debug("REST request to get a page of ConveyanceMasters");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yyyy");
        Date date = simpleDateFormat.parse(search.getDesc());
        com.ibm.icu.util.Calendar calendar = com.ibm.icu.util.Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(com.ibm.icu.util.Calendar.DATE, 1);
        calendar.set(com.ibm.icu.util.Calendar.HOUR, 0);
        calendar.set(com.ibm.icu.util.Calendar.MINUTE, 0);
        calendar.set(com.ibm.icu.util.Calendar.SECOND, 0);
        calendar.set(com.ibm.icu.util.Calendar.MILLISECOND, 0);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Instant fromDate = format.parse(format.format(calendar.getTime())).toInstant();

        calendar.set(com.ibm.icu.util.Calendar.DATE, calendar.getActualMaximum(com.ibm.icu.util.Calendar.DAY_OF_MONTH));
        calendar.set(com.ibm.icu.util.Calendar.HOUR, 23);
        calendar.set(com.ibm.icu.util.Calendar.MINUTE, 59);
        calendar.set(com.ibm.icu.util.Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 59);

        Instant toDate = format.parse(format.format(calendar.getTime())).toInstant();


        List<TravelApplicationMaster> page = null;
        page = travelApplicationMasterRepository.findByMonthYear(fromDate, toDate, SecurityUtils.getCurrentUserLogin().orElse(null));
        List<TravelApplicationMasterMobileBean> travelApplicationMasterMobileBeans = new ArrayList<>();
        if (page != null && page.size() > 0) {
            for (TravelApplicationMaster travelApplicationMaster : page) {
                TravelApplicationMasterMobileBean bean = new TravelApplicationMasterMobileBean();
                BeanUtils.copyProperties(travelApplicationMaster, bean);
                bean.setTravelFromdateFormat(format.format(Date.from(travelApplicationMaster.getTravelFromdate())));
                bean.setTravelTodateFormat(format.format(Date.from(travelApplicationMaster.getTravelTodate())));
                travelApplicationMasterMobileBeans.add(bean);
            }
        }
        return ResponseEntity.ok().body(travelApplicationMasterMobileBeans);
    }

    /**
     * {@code GET  /travel-application-masters/:id} : get the "id"
     * travelApplicationMaster. @param id the id of the travelApplicationMaster to
     * retrieve.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with
     * body`the travelApplicationMaster, or with status
     * {@code 404 (Not Found)}.
     */
    @GetMapping("/travel-application-masters-mobile/{id}")
    public ResponseEntity<TravelApplicationMasterMobileBean> getTravelApplicationMasterMobile(@PathVariable Long id) {
        log.debug("REST request to get TravelApplicationMaster : {}", id);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        TravelApplicationMasterMobileBean bean = new TravelApplicationMasterMobileBean();
        TravelApplicationMaster travelApplicationMaster = travelApplicationMasterRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(travelApplicationMaster, bean);
        bean.setTravelFromdateFormat(simpleDateFormat.format(Date.from(travelApplicationMaster.getTravelFromdate())));
        bean.setTravelTodateFormat(simpleDateFormat.format(Date.from(travelApplicationMaster.getTravelTodate())));
        bean.setTravelAccommodationDetails(travelAccommodationRepository.findByTravelApplicationMasterId(travelApplicationMaster.getId()).stream()
            .map(travelAccommodation -> new TravelAccommodationDetailsMobile(travelAccommodation)).collect(Collectors.toList()));

        bean.setTravelFlightDetails(travelFlightDetailsRepository.findByTravelApplicationMasterId(travelApplicationMaster.getId()).stream()
            .map(travelFlight -> new TravelFlightDetailsMobile(travelFlight)).collect(Collectors.toList()));

        bean.setTravelForexDetails(travelForexDetailsRepository.findByTravelApplicationMasterId(travelApplicationMaster.getId()).stream()
            .map(travelForex -> new TravelForexDetailsMobile(travelForex)).collect(Collectors.toList()));

        bean.setTravelLuggageDetails(travelLuggageDetailsRepository.findByTravelApplicationMasterId(travelApplicationMaster.getId()).stream()
            .map(travelLuggage -> new TravelLuggageDetailsMobile(travelLuggage)).collect(Collectors.toList()));

        bean.setTravelPassengerDetails(travelPassengerDetailsRepository.findByTravelApplicationMasterId(travelApplicationMaster.getId()).stream()
            .map(travelPassenger -> new TravelPassengerDetailsMobile(travelPassenger)).collect(Collectors.toList()));
        return ResponseUtil.wrapOrNotFound(Optional.of(bean));
    }

    /**
     * {@code POST  /travel-application-masters} : Create a new
     * travelApplicationMaster.
     *
     * @param travelApplicationMaster the travelApplicationMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     * body the new travelApplicationMaster, or with status
     * {@code 400 (Bad Request)} if the travelApplicationMaster has already
     * an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/travel-application-masters-mobile")
    public ResponseEntity<TravelApplicationMaster> createTravelApplicationMasterMobile(
        @Valid @RequestBody TravelApplicationMasterMobileBean travelApplicationMasterMobileBean) throws URISyntaxException, ParseException {
        log.debug("REST request to save TravelApplicationMaster : {}", travelApplicationMasterMobileBean);
        if (travelApplicationMasterMobileBean.getId() != null) {
            throw new BadRequestAlertException("A new travelApplicationMaster cannot already have an ID", ENTITY_NAME,
                "idexists");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        TravelApplicationMaster travelApplicationMaster = new TravelApplicationMaster();
        BeanUtils.copyProperties(travelApplicationMasterMobileBean, travelApplicationMaster, "travelFlightDetails",
            "travelAccommodationDetails", "travelForexDetails", "travelLuggageDetails");
        travelApplicationMaster.setTravelFromdate(simpleDateFormat.parse(travelApplicationMasterMobileBean.getTravelFromdateFormat()).toInstant());
        travelApplicationMaster.setTravelTodate(simpleDateFormat.parse(travelApplicationMasterMobileBean.getTravelTodateFormat()).toInstant());
        travelApplicationMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        travelApplicationMaster.setEmpCode(SecurityUtils.getCurrentUserLogin().orElse(null));
        travelApplicationMaster.setCreatedDate(Instant.now());
        travelApplicationMaster.setStatus("E");
        TravelApplicationMaster result = travelApplicationMasterRepository.save(travelApplicationMaster);
        if (result != null) {
            if (travelApplicationMasterMobileBean.getTravelPassengerDetails() != null && travelApplicationMasterMobileBean.getTravelPassengerDetails().size() > 0) {
                for (TravelPassengerDetailsMobile bean5 : travelApplicationMasterMobileBean.getTravelPassengerDetails()) {
                    if (bean5.getPassengerName() != null && bean5.getPassengerName().length() > 0) {
                        TravelPassengerDetails travelPassengerDetails = new TravelPassengerDetails();
                        BeanUtils.copyProperties(bean5, travelPassengerDetails);
                        travelPassengerDetails.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                        travelPassengerDetails.setCreatedDate(Instant.now());
                        travelPassengerDetails.setTravelApplicationMaster(result);
                        travelPassengerDetailsRepository.save(travelPassengerDetails);
                    }
                }
            }
            if (travelApplicationMasterMobileBean.getTravelFlightDetails() != null && travelApplicationMasterMobileBean.getTravelFlightDetails().size() > 0) {
                for (TravelFlightDetailsMobile bean : travelApplicationMasterMobileBean.getTravelFlightDetails()) {
                    if (bean.getTravelDate() != null) {
                        TravelFlightDetails travelFlightDetails = new TravelFlightDetails();
                        BeanUtils.copyProperties(bean, travelFlightDetails);

                        if (bean.getArrivalDateFormat() != null) {
                            travelFlightDetails.setArrivalDate(simpleDateFormat.parse(bean.getArrivalDateFormat()).toInstant());
                        }
                        if (bean.getDepartureDateFormat() != null) {
                            travelFlightDetails.setDepartureDate(simpleDateFormat.parse(bean.getDepartureDateFormat()).toInstant());
                        }
                        if (bean.getTravelDateFormat() != null) {
                            travelFlightDetails.setTravelDate(simpleDateFormat.parse(bean.getTravelDateFormat()).toInstant());
                        }
                        travelFlightDetails.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                        travelFlightDetails.setCreatedDate(Instant.now());
                        travelFlightDetails.setTravelApplicationMaster(result);
                        travelFlightDetailsRepository.save(travelFlightDetails);
                    }
                }
            }
            if (travelApplicationMasterMobileBean.getTravelAccommodationDetails() != null && travelApplicationMasterMobileBean.getTravelAccommodationDetails().size() > 0) {
                for (TravelAccommodationDetailsMobile bean2 : travelApplicationMasterMobileBean.getTravelAccommodationDetails()) {
                    if (bean2.getAccommodationDate() != null) {
                        TravelAccommodationDetails travelAccommodationDetails = new TravelAccommodationDetails();
                        BeanUtils.copyProperties(bean2, travelAccommodationDetails);
                        if (bean2.getAccommodationDateFormat() != null) {
                            travelAccommodationDetails.setAccommodationDate(simpleDateFormat.parse(bean2.getAccommodationDateFormat()).toInstant());
                        }
                        travelAccommodationDetails.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                        travelAccommodationDetails.setCreatedDate(Instant.now());
                        travelAccommodationDetails.setTravelApplicationMaster(result);
                        travelAccommodationRepository.save(travelAccommodationDetails);
                    }
                }
            }
            if (travelApplicationMasterMobileBean.getTravelLuggageDetails() != null && travelApplicationMasterMobileBean.getTravelLuggageDetails().size() > 0) {
                for (TravelLuggageDetailsMobile bean4 : travelApplicationMasterMobileBean.getTravelLuggageDetails()) {
                    if (bean4.getLuggageType() != null && bean4.getLuggageType().length() > 0) {
                        TravelLuggageDetails travelLuggageDetails = new TravelLuggageDetails();
                        BeanUtils.copyProperties(bean4, travelLuggageDetails);

                        travelLuggageDetails.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                        travelLuggageDetails.setCreatedDate(Instant.now());
                        travelLuggageDetails.setTravelApplicationMaster(result);
                        travelLuggageDetailsRepository.save(travelLuggageDetails);
                    }
                }
            }
            if (travelApplicationMasterMobileBean.getTravelForexDetails() != null && travelApplicationMasterMobileBean.getTravelForexDetails().size() > 0) {
                for (TravelForexDetailsMobile bean3 : travelApplicationMasterMobileBean.getTravelForexDetails()) {
                    if (bean3.getForexType() != null) {

                        TravelForexDetails travelForexDetails = new TravelForexDetails();
                        BeanUtils.copyProperties(bean3, travelForexDetails);
                        travelForexDetails.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                        travelForexDetails.setCreatedDate(Instant.now());
                        travelForexDetails.setTravelApplicationMaster(result);
                        travelForexDetailsRepository.save(travelForexDetails);
                    }
                }
            }
        }
        return ResponseEntity.created(new URI("/api/travel-application-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /travel-application-masters} : Updates an existing travelApplicationMaster.
     *
     * @param travelApplicationMaster the travelApplicationMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated travelApplicationMaster,
     * or with status {@code 400 (Bad Request)} if the travelApplicationMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the travelApplicationMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/travel-application-masters-mobile")
    public ResponseEntity<TravelApplicationMaster> updateTravelApplicationMasterMobile(@Valid @RequestBody TravelApplicationMasterMobileBean travelApplicationMasterMobileBean) throws URISyntaxException, ParseException {
        log.debug("REST request to update TravelApplicationMaster : {}", travelApplicationMasterMobileBean);
        if (travelApplicationMasterMobileBean.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        TravelApplicationMaster travelApplicationMaster = travelApplicationMasterRepository.findById(travelApplicationMasterMobileBean.getId()).orElse(null);
        BeanUtils.copyProperties(travelApplicationMasterMobileBean, travelApplicationMaster, "travelFlightDetails",
            "travelAccommodationDetails", "travelForexDetails", "travelLuggageDetails", "createdDate", "createdBy", "lastUpdatedBy", "lastUpdatedDate");
        travelApplicationMaster.setTravelFromdate(simpleDateFormat.parse(travelApplicationMasterMobileBean.getTravelFromdateFormat()).toInstant());
        travelApplicationMaster.setTravelTodate(simpleDateFormat.parse(travelApplicationMasterMobileBean.getTravelTodateFormat()).toInstant());
        travelApplicationMaster.setLastUpdatedDate(Instant.now());
        travelApplicationMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        travelApplicationMaster.setEmpCode(SecurityUtils.getCurrentUserLogin().orElse(null));
        travelApplicationMaster.setStatus("E");
        TravelApplicationMaster result = travelApplicationMasterRepository.save(travelApplicationMaster);
        if (result != null) {
            if (travelApplicationMasterMobileBean.getTravelPassengerDetails() != null && travelApplicationMasterMobileBean.getTravelPassengerDetails().size() > 0) {
                for (TravelPassengerDetailsMobile bean5 : travelApplicationMasterMobileBean.getTravelPassengerDetails()) {
                    if (bean5.getPassengerName() != null && bean5.getPassengerName().length() > 0) {
                        TravelPassengerDetails travelPassengerDetails = null;
                        if (bean5 != null && bean5.getId() != null) {
                            travelPassengerDetails = travelPassengerDetailsRepository.findById(bean5.getId()).orElse(null);
                            BeanUtils.copyProperties(bean5, travelPassengerDetails, "createdDate", "createdBy");

                            travelPassengerDetails.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                            travelPassengerDetails.setLastUpdatedDate(Instant.now());
                        } else {
                            travelPassengerDetails = new TravelPassengerDetails();
                            BeanUtils.copyProperties(bean5, travelPassengerDetails);
                            travelPassengerDetails.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                            travelPassengerDetails.setCreatedDate(Instant.now());
                        }
                        travelPassengerDetails.setTravelApplicationMaster(result);
                        travelPassengerDetailsRepository.save(travelPassengerDetails);
                    }
                }
            }
            if (travelApplicationMasterMobileBean.getTravelFlightDetails() != null && travelApplicationMasterMobileBean.getTravelFlightDetails().size() > 0) {
                for (TravelFlightDetailsMobile bean : travelApplicationMasterMobileBean.getTravelFlightDetails()) {
                    if (bean.getTravelDate() != null) {
                        TravelFlightDetails travelFlightDetails = null;
                        if (bean != null && bean.getId() != null) {
                            travelFlightDetails = travelFlightDetailsRepository.findById(bean.getId()).orElse(null);
                            BeanUtils.copyProperties(bean, travelFlightDetails, "createdDate", "createdBy");
                            travelFlightDetails.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                            travelFlightDetails.setLastUpdatedDate(Instant.now());
                        } else {
                            travelFlightDetails = new TravelFlightDetails();
                            BeanUtils.copyProperties(bean, travelFlightDetails);
                            travelFlightDetails.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                            travelFlightDetails.setCreatedDate(Instant.now());
                        }
                        if (bean.getArrivalDateFormat() != null) {
                            travelFlightDetails.setArrivalDate(simpleDateFormat.parse(bean.getArrivalDateFormat()).toInstant());
                        }
                        if (bean.getDepartureDateFormat() != null) {
                            travelFlightDetails.setDepartureDate(simpleDateFormat.parse(bean.getDepartureDateFormat()).toInstant());
                        }
                        if (bean.getTravelDateFormat() != null) {
                            travelFlightDetails.setTravelDate(simpleDateFormat.parse(bean.getTravelDateFormat()).toInstant());
                        }
                        travelFlightDetails.setTravelApplicationMaster(result);
                        travelFlightDetailsRepository.save(travelFlightDetails);
                    }
                }
            }
            if (travelApplicationMasterMobileBean.getTravelAccommodationDetails() != null && travelApplicationMasterMobileBean.getTravelAccommodationDetails().size() > 0) {
                for (TravelAccommodationDetailsMobile bean2 : travelApplicationMasterMobileBean.getTravelAccommodationDetails()) {
                    if (bean2.getAccommodationDate() != null) {
                        TravelAccommodationDetails travelAccommodationDetails = null;
                        if (bean2 != null && bean2.getId() != null) {
                            travelAccommodationDetails = travelAccommodationRepository.findById(bean2.getId()).orElse(null);
                            BeanUtils.copyProperties(bean2, travelAccommodationDetails, "createdDate", "createdBy");
                            travelAccommodationDetails.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                            travelAccommodationDetails.setLastUpdatedDate(Instant.now());

                        } else {
                            travelAccommodationDetails = new TravelAccommodationDetails();
                            BeanUtils.copyProperties(bean2, travelAccommodationDetails);
                            travelAccommodationDetails.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                            travelAccommodationDetails.setCreatedDate(Instant.now());
                        }
                        if (bean2.getAccommodationDateFormat() != null) {
                            travelAccommodationDetails.setAccommodationDate(simpleDateFormat.parse(bean2.getAccommodationDateFormat()).toInstant());
                        }
                        travelAccommodationDetails.setTravelApplicationMaster(result);
                        travelAccommodationRepository.save(travelAccommodationDetails);
                    }
                }
            }
            if (travelApplicationMasterMobileBean.getTravelLuggageDetails() != null && travelApplicationMasterMobileBean.getTravelLuggageDetails().size() > 0) {
                for (TravelLuggageDetailsMobile bean4 : travelApplicationMasterMobileBean.getTravelLuggageDetails()) {
                    if (bean4.getLuggageType() != null && bean4.getLuggageType().length() > 0) {
                        TravelLuggageDetails travelLuggageDetails = null;
                        if(bean4 != null && bean4.getId() != null) {
                            travelLuggageDetails = travelLuggageDetailsRepository.findById(bean4.getId()).orElse(null);
                            BeanUtils.copyProperties(bean4, travelLuggageDetails, "createdDate", "createdBy");
                            travelLuggageDetails.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                            travelLuggageDetails.setLastUpdatedDate(Instant.now());
                        } else {
                            travelLuggageDetails = new TravelLuggageDetails();
                            BeanUtils.copyProperties(bean4, travelLuggageDetails);
                            travelLuggageDetails.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                            travelLuggageDetails.setCreatedDate(Instant.now());
                        }
                        travelLuggageDetails.setTravelApplicationMaster(result);
                        travelLuggageDetailsRepository.save(travelLuggageDetails);
                    }
                }
            }
            if (travelApplicationMasterMobileBean.getTravelForexDetails() != null && travelApplicationMasterMobileBean.getTravelForexDetails().size() > 0) {
                for (TravelForexDetailsMobile bean3 : travelApplicationMasterMobileBean.getTravelForexDetails()) {
                    if (bean3.getForexType() != null) {

                        TravelForexDetails travelForexDetails = new TravelForexDetails();
                        if (bean3 != null && bean3.getId() != null) {
                            travelForexDetails = travelForexDetailsRepository.findById(bean3.getId()).orElse(null);
                            BeanUtils.copyProperties(bean3, travelForexDetails, "createdDate", "createdBy");
                            travelForexDetails.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                            travelForexDetails.setLastUpdatedDate(Instant.now());
                        } else {
                            travelForexDetails = new TravelForexDetails();
                            BeanUtils.copyProperties(bean3, travelForexDetails);
                            travelForexDetails.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                            travelForexDetails.setCreatedDate(Instant.now());
                        }
                        travelForexDetails.setTravelApplicationMaster(result);
                        travelForexDetailsRepository.save(travelForexDetails);
                    }
                }
            }
        }
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, travelApplicationMaster.getId().toString()))
            .body(result);
    }
}
