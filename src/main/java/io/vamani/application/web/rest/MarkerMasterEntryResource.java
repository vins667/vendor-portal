package io.vamani.application.web.rest;

import io.vamani.application.config.Constants;
import io.vamani.application.db2.domain.Productiondemand;
import io.vamani.application.db2.domain.Productionorder;
import io.vamani.application.db2.domain.ProductionorderId;
import io.vamani.application.db2.model.FullitemkeydecoderBean;
import io.vamani.application.db2.repository.ProductiondemandRepository;
import io.vamani.application.db2.repository.ProductionorderRepository;
import io.vamani.application.db2.repository.ViewcostanalysisselectionicsRepository;
import io.vamani.application.domain.CutPlanEntry;
import io.vamani.application.domain.MarkerEntryDetails;
import io.vamani.application.domain.MarkerMasterEntry;
import io.vamani.application.model.*;
import io.vamani.application.repository.CutPlanEntryRepository;
import io.vamani.application.repository.MarkerEntryDetailsRepository;
import io.vamani.application.repository.MarkerMasterEntryRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.micrometer.core.annotation.Timed;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ibm.icu.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.*;

/**
 * REST controller for managing {@link io.vamani.application.domain.MarkerMasterEntry}.
 */
@RestController
@RequestMapping("/api")
public class MarkerMasterEntryResource {

    private final Logger log = LoggerFactory.getLogger(MarkerMasterEntryResource.class);

    private static final String ENTITY_NAME = "markerMasterEntry";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MarkerMasterEntryRepository markerMasterEntryRepository;

    @Autowired
    private MarkerEntryDetailsRepository markerEntryDetailsRepository;

    @Autowired
    private CutPlanEntryRepository cutPlanEntryRepository;

    @Autowired
    private ProductionorderRepository productionorderRepository;

    @Autowired
    private ViewcostanalysisselectionicsRepository viewcostanalysisselectionicsRepository;

    @Autowired
    private ProductiondemandRepository productiondemandRepository;

    public MarkerMasterEntryResource(MarkerMasterEntryRepository markerMasterEntryRepository) {
        this.markerMasterEntryRepository = markerMasterEntryRepository;
    }

    /**
     * {@code POST  /marker-master-entries} : Create a new markerMasterEntry.
     *
     * @param markerMasterEntry the markerMasterEntry to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new markerMasterEntry, or with status {@code 400 (Bad Request)} if the markerMasterEntry has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @PostMapping("/marker-master-entries")
    public ResponseEntity<List<MarkerMasterEntry>> createMarkerMasterEntry(@Valid @RequestBody MarkerMasterEntryBean[] markerMasterEntryBeans) throws URISyntaxException, IllegalAccessException, InvocationTargetException {
        log.debug("REST request to save MarkerMasterEntry : {}", markerMasterEntryBeans);
        List<MarkerMasterEntry> markerMasterEntries = new ArrayList<>();
        for(MarkerMasterEntryBean markerMasterEntryBean : markerMasterEntryBeans) {
            if (markerMasterEntryBean != null && markerMasterEntryBean.getId() != null) {
                MarkerMasterEntry markerMasterEntry = new MarkerMasterEntry();
                BeanUtils.copyProperties(markerMasterEntryBean, markerMasterEntry);
                markerMasterEntry.setItemCode(markerMasterEntryBean.getItemCode().getSummarizeddescription().trim());
                markerMasterEntry.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                markerMasterEntry.setLastUpdatedDate(Instant.now());
                MarkerMasterEntry result = markerMasterEntryRepository.save(markerMasterEntry);
                if (result != null && markerMasterEntryBean.getMarkerEntryDetails() != null
                    && markerMasterEntryBean.getMarkerEntryDetails().size() > 0) {
                    for (MarkerEntryDetailsBean markerEntryDetailsBean : markerMasterEntryBean.getMarkerEntryDetails()) {
                        MarkerEntryDetails markerEntryDetails = new MarkerEntryDetails();
                        BeanUtils.copyProperties(markerEntryDetailsBean, markerEntryDetails);
                        markerEntryDetails.lastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                        markerEntryDetails.lastUpdatedDate(Instant.now());
                        markerEntryDetails.setMarkerMasterEntry(result);
                        if (markerEntryDetails.getSizeQty() != null && markerEntryDetails.getSizeQty() > 0) {
                            markerEntryDetailsRepository.save(markerEntryDetails);
                        }
                    }
                    markerMasterEntries.add(result);
                }
            } else {
                MarkerMasterEntry markerMasterEntry = new MarkerMasterEntry();
                BeanUtils.copyProperties(markerMasterEntryBean, markerMasterEntry);
                if (markerMasterEntry != null && markerMasterEntry.getLength().doubleValue() > 0) {
                    markerMasterEntry.setOrderStatus("E");
                    markerMasterEntry.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                    markerMasterEntry.setCreatedDate(Instant.now());
                    markerMasterEntry.setItemCode(markerMasterEntryBean.getItemCode().getSummarizeddescription().trim());
                    markerMasterEntry.setSubcode01(markerMasterEntryBean.getItemCode().getOrdersubcode01().trim());
                    markerMasterEntry.setSubcode02(markerMasterEntryBean.getItemCode().getOrdersubcode02().trim());
                    markerMasterEntry.setSubcode03(markerMasterEntryBean.getItemCode().getOrdersubcode03().trim());
                    markerMasterEntry.setSubcode04(markerMasterEntryBean.getItemCode().getOrdersubcode04().trim());
                    markerMasterEntry.setSubcode05(markerMasterEntryBean.getItemCode().getOrdersubcode05().trim());
                    markerMasterEntry.setSubcode06(markerMasterEntryBean.getItemCode().getOrdersubcode06().trim());
                    markerMasterEntry.setSubcode07(markerMasterEntryBean.getItemCode().getOrdersubcode07().trim());
                    markerMasterEntry.setSubcode08(markerMasterEntryBean.getItemCode().getOrdersubcode08().trim());
                    markerMasterEntry.setSubcode09(markerMasterEntryBean.getItemCode().getOrdersubcode09().trim());
                    markerMasterEntry.setSubcode10(markerMasterEntryBean.getItemCode().getOrdersubcode10().trim());
                    markerMasterEntry.setItemType(markerMasterEntryBean.getItemCode().getItemtypecompanycode().trim());

                    String NewMarkerCode = markerMasterEntryRepository.getNewMarkerCode(markerMasterEntry.getStyle(), markerMasterEntry.getColor());
                    markerMasterEntry.setMarkerCode("M" + NewMarkerCode);

                    MarkerMasterEntry result = markerMasterEntryRepository.save(markerMasterEntry);
                    if (result != null && markerMasterEntryBean.getMarkerEntryDetails() != null && markerMasterEntryBean.getMarkerEntryDetails().size() > 0) {
                        for (MarkerEntryDetailsBean markerEntryDetailsBean : markerMasterEntryBean.getMarkerEntryDetails()) {
                            MarkerEntryDetails markerEntryDetails = new MarkerEntryDetails();
                            BeanUtils.copyProperties(markerEntryDetailsBean, markerEntryDetails);
                            markerEntryDetails.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                            markerEntryDetails.setCreatedDate(Instant.now());
                            markerEntryDetails.setMarkerMasterEntry(result);
                            if (markerEntryDetails.getSizeQty() != null && markerEntryDetails.getSizeQty() > 0) {
                                MarkerEntryDetails detailResult = markerEntryDetailsRepository.save(markerEntryDetails);
                            } else {
                                markerEntryDetails.setSizeQty(0);
                                MarkerEntryDetails detailResult = markerEntryDetailsRepository.save(markerEntryDetails);
                            }
                        }
                    }
                    markerMasterEntries.add(result);
                }
            }
        }
        return ResponseEntity.created(new URI("/api/marker-master-entries"))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, ""))
            .body(markerMasterEntries);
    }

    /**
     * {@code PUT  /marker-master-entries} : Updates an existing markerMasterEntry.
     *
     * @param markerMasterEntry the markerMasterEntry to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated markerMasterEntry,
     * or with status {@code 400 (Bad Request)} if the markerMasterEntry is not valid,
     * or with status {@code 500 (Internal Server Error)} if the markerMasterEntry couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
	@PutMapping("/marker-master-entries")
	public ResponseEntity<MarkerMasterEntry> updateMarkerMasterEntry(@Valid @RequestBody MarkerMasterEntryBean markerMasterEntryBean)
			throws URISyntaxException, IllegalAccessException, InvocationTargetException {
		log.debug("REST request to update MarkerMasterEntry : {}", markerMasterEntryBean);
		if (markerMasterEntryBean.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		MarkerMasterEntry markerMasterEntry = new MarkerMasterEntry();
		BeanUtils.copyProperties(markerMasterEntryBean, markerMasterEntry);
        markerMasterEntry.setItemCode(markerMasterEntryBean.getItemCode().getSummarizeddescription().trim());
		markerMasterEntry.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
		markerMasterEntry.setLastUpdatedDate(Instant.now());
		MarkerMasterEntry result = markerMasterEntryRepository.save(markerMasterEntry);
		if (result != null && markerMasterEntryBean.getMarkerEntryDetails() != null
				&& markerMasterEntryBean.getMarkerEntryDetails().size() > 0) {
			for (MarkerEntryDetailsBean markerEntryDetailsBean : markerMasterEntryBean.getMarkerEntryDetails()) {
				MarkerEntryDetails markerEntryDetails = new MarkerEntryDetails();
				BeanUtils.copyProperties(markerEntryDetailsBean, markerEntryDetails);
				markerEntryDetails.lastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
				markerEntryDetails.lastUpdatedDate(Instant.now());
				markerEntryDetails.setMarkerMasterEntry(result);
				if (markerEntryDetails.getSizeQty() != null && markerEntryDetails.getSizeQty() > 0) {
					markerEntryDetailsRepository.save(markerEntryDetails);
				}
			}
		}
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME,
				markerMasterEntry.getId().toString())).body(result);
	}

    /**
     * {@code GET  /marker-master-entries} : get all the markerMasterEntries.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of markerMasterEntries in body.
     */
    @GetMapping("/marker-master-entries")
    public ResponseEntity<List<MarkerMasterEntry>> getAllMarkerMasterEntries(Pageable pageable) {
        log.debug("REST request to get a page of MarkerMasterEntries");
        Page<MarkerMasterEntry> page = markerMasterEntryRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /marker-master-entries} : get all the markerMasterEntries.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of markerMasterEntries in body.
     */
    @PostMapping("/marker-master-entries-filter")
    public ResponseEntity<List<MarkerMasterEntry>> getAllMarkerMasterEntriesFilter(@RequestBody MarkerMasterSearch search) {
        log.debug("REST request to get a page of MarkerMasterEntries");
        String login = SecurityUtils.getCurrentUserLogin().orElse(null);
        String style = "%";
        if (search.getStyle() != null) {
            style = "%" + search.getStyle().toUpperCase() + "%";
        }
        String color = "%";
        if (search.getColor() != null) {
            color = "%" + search.getColor().toUpperCase() + "%";
        }
        List<MarkerMasterEntry> markerMasterEntries = new ArrayList<>();
        search.setPage(
            PageRequest.of(
                search.getPageNo(),
                search.getSize(),
                search.getSortType() != null && search.getSortType().equalsIgnoreCase("desc")
                    ? Sort.by(search.getSort()).descending()
                    : Sort.by(search.getSort()).ascending()
            )
        );
        Page<Object[]> page = markerMasterEntryRepository.findAllByStyle(style, color, login, search.getPage());
        for (Object[] objects : page.getContent()) {
            MarkerMasterEntry markerMasterEntry = new MarkerMasterEntry();
            markerMasterEntry.setPlantCode(objects[0].toString());
            markerMasterEntry.setPlantDescription(objects[1].toString());
            markerMasterEntry.setStyle(objects[2].toString());
            markerMasterEntry.setColor(objects[3].toString());
            markerMasterEntry.setColorDesc(objects[4].toString());
            markerMasterEntry.setItemCode(objects[5].toString());
            markerMasterEntry.setSubcode01(objects[6].toString());
            markerMasterEntry.setSubcode02(objects[7].toString());
            markerMasterEntry.setSubcode03(objects[8].toString());
            markerMasterEntry.setSubcode04(objects[9].toString());
            markerMasterEntry.setSubcode05(objects[10].toString());
            markerMasterEntry.setSubcode06(objects[11].toString());
            markerMasterEntry.setSubcode07(objects[12].toString());
            markerMasterEntry.setSubcode08(objects[13].toString());
            markerMasterEntry.setSubcode09(objects[14].toString());
            markerMasterEntry.setSubcode10(objects[15].toString());
            markerMasterEntry.setWidth(objects[16].toString());
            markerMasterEntry.setBodyFabric(objects[17] != null ? (Boolean) objects[17] : false);
            markerMasterEntry.setItemType(objects[18].toString());
            markerMasterEntry.setPlannedAvg((Double) objects[19]);
            markerMasterEntries.add(markerMasterEntry);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(markerMasterEntries);
    }

    @PostMapping("/marker-entry-approval-filter")
    public ResponseEntity<List<MarkerMasterEntryBean>> getAllMarkerListByStatus(@RequestBody MarkerMasterSearch search) {
        log.debug("REST request to get a page of MarkerMasterEntries");
        String login = SecurityUtils.getCurrentUserLogin().orElse(null);

        String projectcode = "%";
        if (search.getProjectcode() != null) {
            projectcode = "%" + search.getProjectcode().toUpperCase() + "%";
        }
        String color = "%";
        if (search.getColor() != null) {
            color = "%" + search.getColor().toUpperCase() + "%";
        }

        String status = "";
        if (search.getStatus() != null) {
            status = search.getStatus().toUpperCase();
        }

        List<MarkerMasterEntryBean> markerMasterEntries = new ArrayList<>();
        search.setPage(
            PageRequest.of(
                search.getPageNo(),
                search.getSize(),
                search.getSortType() != null && search.getSortType().equalsIgnoreCase("desc")
                    ? Sort.by(search.getSort()).descending()
                    : Sort.by(search.getSort()).ascending()
            )
        );
        Page<Object[]> page = markerMasterEntryRepository.findAllByStatus(projectcode, color, status, login, search.getPage());
        for (Object[] objects : page.getContent()) {
            MarkerMasterEntryBean markerMasterEntry = new MarkerMasterEntryBean();
            markerMasterEntry.setPlantCode(objects[0].toString());
            markerMasterEntry.setPlantDescription(objects[1].toString());
            markerMasterEntry.setStyle(objects[2].toString());
            markerMasterEntry.setColor(objects[3].toString());
            markerMasterEntry.setColorDesc(objects[4].toString());
            //markerMasterEntry.setItemCode(objects[5].toString());

            FullitemkeydecoderBean fullkeyBean = new FullitemkeydecoderBean();
            fullkeyBean.setSummarizeddescription(objects[5].toString());
            fullkeyBean.setItemtypecompanycode(objects[18].toString());
            fullkeyBean.setOrdersubcode01(objects[6].toString());
            fullkeyBean.setOrdersubcode02(objects[7].toString());
            fullkeyBean.setOrdersubcode03(objects[8].toString());
            fullkeyBean.setOrdersubcode04(objects[9].toString());
            fullkeyBean.setOrdersubcode05(objects[10].toString());
            fullkeyBean.setOrdersubcode06(objects[11].toString());
            fullkeyBean.setOrdersubcode07(objects[12].toString());
            fullkeyBean.setOrdersubcode08(objects[13].toString());
            fullkeyBean.setOrdersubcode09(objects[14].toString());
            fullkeyBean.setOrdersubcode10(objects[15].toString());
            markerMasterEntry.setItemCode(fullkeyBean);

            markerMasterEntry.setSubcode01(objects[6].toString());
            markerMasterEntry.setSubcode02(objects[7].toString());
            markerMasterEntry.setSubcode03(objects[8].toString());
            markerMasterEntry.setSubcode04(objects[9].toString());
            markerMasterEntry.setSubcode05(objects[10].toString());
            markerMasterEntry.setSubcode06(objects[11].toString());
            markerMasterEntry.setSubcode07(objects[12].toString());
            markerMasterEntry.setSubcode08(objects[13].toString());
            markerMasterEntry.setSubcode09(objects[14].toString());
            markerMasterEntry.setSubcode10(objects[15].toString());
            markerMasterEntry.setWidth(objects[16].toString());
            markerMasterEntry.setBodyFabric(objects[17] != null ? (Boolean) objects[17] : false);
            markerMasterEntry.setItemType(objects[18].toString());
            markerMasterEntry.setPlannedAvg((Double) objects[19]);
            markerMasterEntry.setActualAvg((Double) objects[20]);
            markerMasterEntry.setMarkerCode(objects[21].toString());
            markerMasterEntry.setId(new BigInteger(objects[22].toString()).longValue());
            markerMasterEntry.setApprovalFlag(objects[23].toString());

            List<MarkerEntryDetails> markerEntryDetails = markerEntryDetailsRepository.findMarkerDetailById(markerMasterEntry.getId());
            if(markerEntryDetails != null && markerEntryDetails.size()>0) {

                List<MarkerEntryDetailsBean> markerEntryDetailsBeans = new ArrayList<MarkerEntryDetailsBean>();
                for (MarkerEntryDetails markerEntryDetail : markerEntryDetails) {
                    MarkerEntryDetailsBean bean = new MarkerEntryDetailsBean();
                    BeanUtils.copyProperties(markerEntryDetail, bean);
                    markerEntryDetailsBeans.add(bean);
                }
                markerMasterEntry.setMarkerEntryDetails(markerEntryDetailsBeans);
            }
            markerMasterEntries.add(markerMasterEntry);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(markerMasterEntries);
    }

    /**
     * {@code GET  /marker-master-entries/:id} : get the "id" markerMasterEntry.
     *
     * @param id the id of the markerMasterEntry to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the markerMasterEntry, or with status {@code 404 (Not Found)}.
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
	@GetMapping("/marker-master-entries/{id}")
	public ResponseEntity<MarkerMasterEntryBean> getMarkerMasterEntry(@PathVariable Long id) throws IllegalAccessException, InvocationTargetException {
		log.debug("REST request to get MarkerMasterEntry : {}", id);
		MarkerMasterEntry markerMasterEntry = markerMasterEntryRepository.findById(id).orElse(null);
		MarkerMasterEntryBean markerMasterEntryBean = new MarkerMasterEntryBean();
		if (markerMasterEntry != null) {
			BeanUtils.copyProperties(markerMasterEntry, markerMasterEntryBean, "itemCode");
			FullitemkeydecoderBean fullkeyBean = new FullitemkeydecoderBean();
			fullkeyBean.setSummarizeddescription(markerMasterEntry.getItemCode());
			markerMasterEntryBean.setItemCode(fullkeyBean);
			Long markerUsedCounter = cutPlanEntryRepository.countAllByMarkerMasterEntryId(id);
            if (markerUsedCounter > 0) {
                markerMasterEntryBean.setSaveDisabled(true);
            } else {
                markerMasterEntryBean.setSaveDisabled(false);
            }
			List<MarkerEntryDetails> markerEntryDetails = markerEntryDetailsRepository.findMarkerDetailById(markerMasterEntry.getId());
			if (markerEntryDetails != null && markerEntryDetails.size() > 0) {
				List<MarkerEntryDetailsBean> markerEntryDetailsBeans = new ArrayList<MarkerEntryDetailsBean>();
				for (MarkerEntryDetails markerEntryDetail : markerEntryDetails) {
					MarkerEntryDetailsBean bean = new MarkerEntryDetailsBean();
					BeanUtils.copyProperties(markerEntryDetail, bean);
                    markerEntryDetailsBeans.add(bean);
				}
				markerMasterEntryBean.setMarkerEntryDetails(markerEntryDetailsBeans);
			}
		}
		return ResponseUtil.wrapOrNotFound(Optional.of(markerMasterEntryBean));
	}

    /**
     * {@code GET  /marker-master-entries/:id} : get the "id" markerMasterEntry.
     *
     * @param id the id of the markerMasterEntry to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the markerMasterEntry, or with status {@code 404 (Not Found)}.
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @PostMapping("/marker-master-entries-fetch")
    public List<MarkerMasterEntryBean> getMarkerMasterEntryFetch(@RequestBody MarkerMasterEntryBean markerMasterEntry) throws IllegalAccessException, InvocationTargetException {
        log.debug("REST request to get MarkerMasterEntry : {}", markerMasterEntry);

        List<MarkerMasterEntry> markerMasterEntries = markerMasterEntryRepository.getMarkerByStyleAndColors(markerMasterEntry.getStyle().trim(), markerMasterEntry.getColor().trim(), markerMasterEntry.getItemCode().getOrdersubcode01().trim(), markerMasterEntry.getItemCode().getOrdersubcode02().trim(), markerMasterEntry.getItemCode().getOrdersubcode03().trim(), markerMasterEntry.getItemCode().getOrdersubcode04().trim(), markerMasterEntry.getItemCode().getOrdersubcode05().trim(), markerMasterEntry.getItemCode().getOrdersubcode06().trim(), markerMasterEntry.getItemCode().getOrdersubcode07().trim(), markerMasterEntry.getItemCode().getOrdersubcode08().trim(), markerMasterEntry.getItemCode().getOrdersubcode09().trim(), markerMasterEntry.getItemCode().getOrdersubcode10().trim(), markerMasterEntry.getPlantCode());
        List<MarkerMasterEntryBean> markerMasterEntryBeans = new ArrayList<>();
        if(markerMasterEntries != null && markerMasterEntries.size() >0) {
            for (MarkerMasterEntry markerMaster : markerMasterEntries) {
                MarkerMasterEntryBean markerMasterEntryBean = new MarkerMasterEntryBean();
                BeanUtils.copyProperties(markerMaster, markerMasterEntryBean, "itemCode");

                FullitemkeydecoderBean fullkeyBean = new FullitemkeydecoderBean();
                fullkeyBean.setSummarizeddescription(markerMaster.getItemCode());
                fullkeyBean.setItemtypecompanycode(markerMaster.getItemType());
                fullkeyBean.setOrdersubcode01(markerMaster.getSubcode01());
                fullkeyBean.setOrdersubcode02(markerMaster.getSubcode02());
                fullkeyBean.setOrdersubcode03(markerMaster.getSubcode03());
                fullkeyBean.setOrdersubcode04(markerMaster.getSubcode04());
                fullkeyBean.setOrdersubcode05(markerMaster.getSubcode05());
                fullkeyBean.setOrdersubcode06(markerMaster.getSubcode06());
                fullkeyBean.setOrdersubcode07(markerMaster.getSubcode07());
                fullkeyBean.setOrdersubcode08(markerMaster.getSubcode08());
                fullkeyBean.setOrdersubcode09(markerMaster.getSubcode09());
                fullkeyBean.setOrdersubcode10(markerMaster.getSubcode10());
                markerMasterEntryBean.setItemCode(fullkeyBean);

                Long markerUsedCounter = cutPlanEntryRepository.countAllByMarkerMasterEntryId(markerMaster.getId());
                if (markerUsedCounter > 0) {
                    markerMasterEntryBean.setSaveDisabled(true);
                } else {
                    markerMasterEntryBean.setSaveDisabled(false);
                }

                List<MarkerEntryDetails> markerEntryDetails = markerEntryDetailsRepository.findMarkerDetailById(markerMaster.getId());
                if (markerEntryDetails != null && markerEntryDetails.size() > 0) {
                    List<MarkerEntryDetailsBean> markerEntryDetailsBeans = new ArrayList<MarkerEntryDetailsBean>();
                    for (MarkerEntryDetails markerEntryDetail : markerEntryDetails) {
                        MarkerEntryDetailsBean bean = new MarkerEntryDetailsBean();
                        BeanUtils.copyProperties(markerEntryDetail, bean);
                        markerEntryDetailsBeans.add(bean);
                    }
                    markerMasterEntryBean.setMarkerEntryDetails(markerEntryDetailsBeans);
                }
                markerMasterEntryBeans.add(markerMasterEntryBean);
            }
        }
        return markerMasterEntryBeans;
    }

    /**
     * {@code GET  /marker-master-entries/:id} : get the "id" markerMasterEntry.
     *
     * @param id the id of the markerMasterEntry to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the markerMasterEntry, or with status {@code 404 (Not Found)}.
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @PostMapping("/marker-master-entries-fetch-approved")
    public List<MarkerMasterEntryBean> getMarkerMasterEntryFetchApproval(@RequestBody MarkerMasterEntryBean markerMasterEntry) throws IllegalAccessException, InvocationTargetException {
        log.debug("REST request to get MarkerMasterEntry : {}", markerMasterEntry);

        List<MarkerMasterEntry> markerMasterEntries = markerMasterEntryRepository.getMarkerByStyleAndColorsApproval(markerMasterEntry.getStyle().trim(), markerMasterEntry.getColor().trim(), markerMasterEntry.getItemCode().getOrdersubcode01().trim(), markerMasterEntry.getItemCode().getOrdersubcode02().trim(), markerMasterEntry.getItemCode().getOrdersubcode03().trim(), markerMasterEntry.getItemCode().getOrdersubcode04().trim(), markerMasterEntry.getItemCode().getOrdersubcode05().trim(), markerMasterEntry.getItemCode().getOrdersubcode06().trim(), markerMasterEntry.getItemCode().getOrdersubcode07().trim(), markerMasterEntry.getItemCode().getOrdersubcode08().trim(), markerMasterEntry.getItemCode().getOrdersubcode09().trim(), markerMasterEntry.getItemCode().getOrdersubcode10().trim(), markerMasterEntry.getPlantCode());
        List<MarkerMasterEntryBean> markerMasterEntryBeans = new ArrayList<>();
        if(markerMasterEntries != null && markerMasterEntries.size() >0) {
            for (MarkerMasterEntry markerMaster : markerMasterEntries) {
                MarkerMasterEntryBean markerMasterEntryBean = new MarkerMasterEntryBean();
                BeanUtils.copyProperties(markerMaster, markerMasterEntryBean, "itemCode");

                FullitemkeydecoderBean fullkeyBean = new FullitemkeydecoderBean();
                fullkeyBean.setSummarizeddescription(markerMaster.getItemCode());
                fullkeyBean.setItemtypecompanycode(markerMaster.getItemType());
                fullkeyBean.setOrdersubcode01(markerMaster.getSubcode01());
                fullkeyBean.setOrdersubcode02(markerMaster.getSubcode02());
                fullkeyBean.setOrdersubcode03(markerMaster.getSubcode03());
                fullkeyBean.setOrdersubcode04(markerMaster.getSubcode04());
                fullkeyBean.setOrdersubcode05(markerMaster.getSubcode05());
                fullkeyBean.setOrdersubcode06(markerMaster.getSubcode06());
                fullkeyBean.setOrdersubcode07(markerMaster.getSubcode07());
                fullkeyBean.setOrdersubcode08(markerMaster.getSubcode08());
                fullkeyBean.setOrdersubcode09(markerMaster.getSubcode09());
                fullkeyBean.setOrdersubcode10(markerMaster.getSubcode10());
                markerMasterEntryBean.setItemCode(fullkeyBean);

                Long markerUsedCounter = cutPlanEntryRepository.countAllByMarkerMasterEntryId(markerMaster.getId());
                if (markerUsedCounter > 0) {
                    markerMasterEntryBean.setSaveDisabled(true);
                } else {
                    markerMasterEntryBean.setSaveDisabled(false);
                }

                List<MarkerEntryDetails> markerEntryDetails = markerEntryDetailsRepository.findMarkerDetailById(markerMaster.getId());
                if (markerEntryDetails != null && markerEntryDetails.size() > 0) {
                    List<MarkerEntryDetailsBean> markerEntryDetailsBeans = new ArrayList<MarkerEntryDetailsBean>();
                    for (MarkerEntryDetails markerEntryDetail : markerEntryDetails) {
                        MarkerEntryDetailsBean bean = new MarkerEntryDetailsBean();
                        BeanUtils.copyProperties(markerEntryDetail, bean);
                        markerEntryDetailsBeans.add(bean);
                    }
                    markerMasterEntryBean.setMarkerEntryDetails(markerEntryDetailsBeans);
                }
                markerMasterEntryBeans.add(markerMasterEntryBean);
            }
        }
        return markerMasterEntryBeans;
    }

    /**
     * {@code GET  /marker-master-entries/:id} : get the "id" markerMasterEntry.
     *
     * @param id the id of the markerMasterEntry to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the markerMasterEntry, or with status {@code 404 (Not Found)}.
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @PostMapping("/marker-master-entries-fetch-avg")
    public MarkerMasterEntryBean getMarkerMasterEntryFetchAvg(@RequestBody MarkerMasterEntryBean markerMasterEntry) throws IllegalAccessException, InvocationTargetException {
        log.debug("REST request to get MarkerMasterEntry : {}", markerMasterEntry);
        Double avg = viewcostanalysisselectionicsRepository.findAllAvgByProjectcodeAndItem(markerMasterEntry.getStyle(), markerMasterEntry.getItemCode().getOrdersubcode01().trim(), markerMasterEntry.getItemCode().getOrdersubcode02().trim(), markerMasterEntry.getItemCode().getOrdersubcode03().trim(), markerMasterEntry.getItemCode().getOrdersubcode04().trim(), markerMasterEntry.getItemCode().getOrdersubcode05().trim(), markerMasterEntry.getItemCode().getOrdersubcode06().trim(), markerMasterEntry.getItemCode().getOrdersubcode07().trim(), markerMasterEntry.getItemCode().getOrdersubcode08().trim(), markerMasterEntry.getItemCode().getOrdersubcode09().trim(), markerMasterEntry.getItemCode().getOrdersubcode10().trim());
        if(avg > 0 ) {
            markerMasterEntry.setPlannedAvg(avg);
        }
        return markerMasterEntry;
    }

    /**
     * {@code DELETE  /marker-master-entries/:id} : delete the "id" markerMasterEntry.
     *
     * @param id the id of the markerMasterEntry to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/marker-master-entries/{id}")
    public ResponseEntity<Void> deleteMarkerMasterEntry(@PathVariable Long id) {
        log.debug("REST request to delete MarkerMasterEntry : {}", id);
        markerMasterEntryRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

	@PostMapping("/marker-master-entities-list")
	@Timed
	public MarkerMasterEntry getAllMarkerByKey(@Valid @RequestBody Master search) {
		String markerCount = markerMasterEntryRepository.getAllMarkerByKey(search.getId().trim(), search.getDesc().trim());
		MarkerMasterEntry markerMasterEntry = new MarkerMasterEntry();
		markerMasterEntry.setMarkerCode(markerCount);
		return markerMasterEntry;
	}

    @PostMapping("/fetch-marker-master-entities")
    @Timed
    public List<MarkerMasterEntry> getAllMarkersByStyleAndColor(@RequestBody BalanceSuggestionSearch search) {
        return markerMasterEntryRepository.getMarkerByStyleAndColors(
            search.getStyle().trim(), search.getColor().trim(), search.getSubcode01().trim(),
            search.getSubcode02().trim(), search.getSubcode03().trim(), search.getSubcode04().trim(),
            search.getSubcode05().trim(), search.getSubcode06().trim(), search.getSubcode07().trim(),
            search.getSubcode08().trim(), search.getSubcode09().trim(), search.getSubcode10().trim(), search.getPlantCode());
    }

    /**
     * {@code GET  /marker-master-entries/:id} : get the "id" markerMasterEntry.
     *
     * @param id the id of the markerMasterEntry to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the markerMasterEntry, or with status {@code 404 (Not Found)}.
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @PostMapping("/marker-master-entries-cutting")
    public ResponseEntity<MarkerMasterEntryBean> getMarkerMasterEntry(@RequestBody CutPlanSearch cutPlanSearch) throws IllegalAccessException, InvocationTargetException {
        log.debug("REST request to get MarkerMasterEntry : {}", cutPlanSearch);
        MarkerMasterEntry markerMasterEntry = markerMasterEntryRepository.findById(cutPlanSearch.getId()).orElse(null);

        Map<String, Long> orderQtyMap = new HashMap<>();
        Productionorder productionorder = productionorderRepository.findById(new ProductionorderId(Constants.COMPANY_CODE, cutPlanSearch.getPono())).orElse(null);
        if(productionorder != null) {
            String[] demandSplitter = productionorder.getDemandlist().split(";");
            List<Productiondemand> productiondemands = productiondemandRepository.findByIds(Arrays.asList(demandSplitter));
            for (Productiondemand productiondemand : productiondemands) {
                if (productiondemand.getSubcode07() != null && productiondemand.getSubcode07().trim().equalsIgnoreCase(cutPlanSearch.getColor().trim()) && orderQtyMap.containsKey(productiondemand.getSubcode08().trim())) {
                    Long qty = orderQtyMap.get(productiondemand.getSubcode08().trim());
                    qty += productiondemand.getUserprimaryquantity().longValue();
                    orderQtyMap.put(productiondemand.getSubcode08().trim(), qty);
                } else if (productiondemand.getSubcode07() != null && productiondemand.getSubcode07().trim().equalsIgnoreCase(cutPlanSearch.getColor().trim())) {
                    Long qty = 0L;
                    qty += productiondemand.getUserprimaryquantity().longValue();
                    orderQtyMap.put(productiondemand.getSubcode08().trim(), qty);
                }
            }
        }

        Map<String, Long> plannedQtyMap = new HashMap<>();
        List<Object[]> sizeQty = cutPlanEntryRepository.findAllSizeByProductionOrder(cutPlanSearch.getPono().trim());
        if (sizeQty != null && sizeQty.size() > 0) {
            for (Object[] objects : sizeQty) {
                plannedQtyMap.put(objects[0].toString(), new Double(objects[1].toString()).longValue());
            }
        }

        MarkerMasterEntryBean markerMasterEntryBean = new MarkerMasterEntryBean();
        if (markerMasterEntry != null) {
            BeanUtils.copyProperties(markerMasterEntry, markerMasterEntryBean, "itemCode");
            FullitemkeydecoderBean fullkeyBean = new FullitemkeydecoderBean();
            fullkeyBean.setSummarizeddescription(markerMasterEntry.getItemCode());
            markerMasterEntryBean.setItemCode(fullkeyBean);
            Long markerUsedCounter = cutPlanEntryRepository.countAllByMarkerMasterEntryId(cutPlanSearch.getId());
            if (markerUsedCounter > 0) {
                markerMasterEntryBean.setSaveDisabled(true);
            } else {
                markerMasterEntryBean.setSaveDisabled(false);
            }
            Double totalQty = 0.0;
            List<MarkerEntryDetails> markerEntryDetails = markerEntryDetailsRepository.findMarkerDetailById(markerMasterEntry.getId());
            if (markerEntryDetails != null && markerEntryDetails.size() > 0) {
                List<MarkerEntryDetailsBean> markerEntryDetailsBeans = new ArrayList<MarkerEntryDetailsBean>();
                for (MarkerEntryDetails markerEntryDetail : markerEntryDetails) {
                    totalQty += markerEntryDetail.getSizeQty();
                    MarkerEntryDetailsBean bean = new MarkerEntryDetailsBean();
                    BeanUtils.copyProperties(markerEntryDetail, bean);
                    if (orderQtyMap.containsKey(bean.getSizeCode().trim())) {
                        Double orderQty = orderQtyMap.get(bean.getSizeCode().trim()).doubleValue();
                        if (cutPlanSearch.getTolPer() != null && cutPlanSearch.getTolPer().doubleValue() > 0) {
                            orderQty = orderQty.doubleValue() + ((orderQty.doubleValue() * cutPlanSearch.getTolPer().doubleValue()) / 100);
                            orderQty = Math.ceil(orderQty);
                        }
                        bean.setOrderQty(orderQty);
                    } else {
                        bean.setOrderQty(0.0);
                    }

                    if (plannedQtyMap.containsKey(bean.getSizeCode().trim())) {
                        bean.setPlannedQty(plannedQtyMap.get(bean.getSizeCode().trim()).doubleValue());
                    } else {
                        bean.setPlannedQty(0.0);
                    }
                    markerEntryDetailsBeans.add(bean);
                }
                if (markerMasterEntryBean.getActualAvg() == null) {
                    Double actualAvg = markerMasterEntryBean.getLength().doubleValue() / totalQty.doubleValue();
                    actualAvg = new Double(new DecimalFormat("#.##").format(actualAvg));
                    markerMasterEntryBean.setActualAvg(actualAvg);
                }
                markerMasterEntryBean.setMarkerEntryDetails(markerEntryDetailsBeans);
            }
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(markerMasterEntryBean));
    }

    /**
     * {@code GET  /marker-master-entries-forward/:id} : get the "id" markerMasterEntry.
     *
     * @param id the id of the markerMasterEntry to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the markerMasterEntry, or with status {@code 404 (Not Found)}.
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @GetMapping("/marker-master-entries-forward/{id}")
    public ResponseEntity<MarkerMasterEntryBean> getMarkerMasterEntryForward(@PathVariable Long id) throws IllegalAccessException, InvocationTargetException {
        log.debug("REST request to get MarkerMasterEntry : {}", id);
        MarkerMasterEntry markerMasterEntry = markerMasterEntryRepository.findById(id).orElse(null);
        if(markerMasterEntry.getPlannedAvg().doubleValue() == markerMasterEntry.getActualAvg().doubleValue()) {
            markerMasterEntry.setApprovalFlag("A");
            markerMasterEntry.setApprovedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
            markerMasterEntry.setApprovedDate(Instant.now());
        } else {
            markerMasterEntry.setApprovalFlag("P");
        }
        markerMasterEntry = markerMasterEntryRepository.save(markerMasterEntry);
        MarkerMasterEntryBean markerMasterEntryBean = new MarkerMasterEntryBean();
        if (markerMasterEntry != null) {
            BeanUtils.copyProperties(markerMasterEntry, markerMasterEntryBean, "itemCode");
            FullitemkeydecoderBean fullkeyBean = new FullitemkeydecoderBean();
            fullkeyBean.setSummarizeddescription(markerMasterEntry.getItemCode());
            markerMasterEntryBean.setItemCode(fullkeyBean);
            Long markerUsedCounter = cutPlanEntryRepository.countAllByMarkerMasterEntryId(id);
            if (markerUsedCounter > 0) {
                markerMasterEntryBean.setSaveDisabled(true);
            } else {
                markerMasterEntryBean.setSaveDisabled(false);
            }
            List<MarkerEntryDetails> markerEntryDetails = markerEntryDetailsRepository.findMarkerDetailById(markerMasterEntry.getId());
            if (markerEntryDetails != null && markerEntryDetails.size() > 0) {
                List<MarkerEntryDetailsBean> markerEntryDetailsBeans = new ArrayList<MarkerEntryDetailsBean>();
                for (MarkerEntryDetails markerEntryDetail : markerEntryDetails) {
                    MarkerEntryDetailsBean bean = new MarkerEntryDetailsBean();
                    BeanUtils.copyProperties(markerEntryDetail, bean);
                    markerEntryDetailsBeans.add(bean);
                }
                markerMasterEntryBean.setMarkerEntryDetails(markerEntryDetailsBeans);
            }
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(markerMasterEntryBean));
    }

    /**
     * {@code GET  /marker-master-entries-approval/:id} : get the "id" markerMasterEntry.
     *
     * @param id the id of the markerMasterEntry to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the markerMasterEntry, or with status {@code 404 (Not Found)}.
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @GetMapping("/marker-master-entries-approval/{id}")
    public ResponseEntity<MarkerMasterEntryBean> getMarkerMasterEntryApproval(@PathVariable Long id) throws IllegalAccessException, InvocationTargetException {
        log.debug("REST request to get MarkerMasterEntry : {}", id);
        MarkerMasterEntry markerMasterEntry = markerMasterEntryRepository.findById(id).orElse(null);
        markerMasterEntry.setApprovalFlag("A");
        markerMasterEntry.setApprovedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        markerMasterEntry.setApprovedDate(Instant.now());
        markerMasterEntry = markerMasterEntryRepository.save(markerMasterEntry);
        MarkerMasterEntryBean markerMasterEntryBean = new MarkerMasterEntryBean();
        if (markerMasterEntry != null) {
            BeanUtils.copyProperties(markerMasterEntry, markerMasterEntryBean, "itemCode");
            FullitemkeydecoderBean fullkeyBean = new FullitemkeydecoderBean();
            fullkeyBean.setSummarizeddescription(markerMasterEntry.getItemCode());
            markerMasterEntryBean.setItemCode(fullkeyBean);
            Long markerUsedCounter = cutPlanEntryRepository.countAllByMarkerMasterEntryId(id);
            if (markerUsedCounter > 0) {
                markerMasterEntryBean.setSaveDisabled(true);
            } else {
                markerMasterEntryBean.setSaveDisabled(false);
            }
            List<MarkerEntryDetails> markerEntryDetails = markerEntryDetailsRepository.findMarkerDetailById(markerMasterEntry.getId());
            if (markerEntryDetails != null && markerEntryDetails.size() > 0) {
                List<MarkerEntryDetailsBean> markerEntryDetailsBeans = new ArrayList<MarkerEntryDetailsBean>();
                for (MarkerEntryDetails markerEntryDetail : markerEntryDetails) {
                    MarkerEntryDetailsBean bean = new MarkerEntryDetailsBean();
                    BeanUtils.copyProperties(markerEntryDetail, bean);
                    markerEntryDetailsBeans.add(bean);
                }
                markerMasterEntryBean.setMarkerEntryDetails(markerEntryDetailsBeans);
            }
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(markerMasterEntryBean));
    }

    /**
     * {@code GET  /marker-master-entries-reject/:id} : get the "id" markerMasterEntry.
     *
     * @param id the id of the markerMasterEntry to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the markerMasterEntry, or with status {@code 404 (Not Found)}.
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @GetMapping("/marker-master-entries-reject/{id}")
    public ResponseEntity<MarkerMasterEntryBean> getMarkerMasterEntryReject(@PathVariable Long id) throws IllegalAccessException, InvocationTargetException {
        log.debug("REST request to get MarkerMasterEntry : {}", id);
        MarkerMasterEntry markerMasterEntry = markerMasterEntryRepository.findById(id).orElse(null);
        markerMasterEntry.setApprovalFlag("R");
        markerMasterEntry.setApprovedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        markerMasterEntry.setApprovedDate(Instant.now());
        MarkerMasterEntryBean markerMasterEntryBean = new MarkerMasterEntryBean();
        if (markerMasterEntry != null) {
            BeanUtils.copyProperties(markerMasterEntry, markerMasterEntryBean, "itemCode");
            FullitemkeydecoderBean fullkeyBean = new FullitemkeydecoderBean();
            fullkeyBean.setSummarizeddescription(markerMasterEntry.getItemCode());
            markerMasterEntryBean.setItemCode(fullkeyBean);
            Long markerUsedCounter = cutPlanEntryRepository.countAllByMarkerMasterEntryId(id);
            if (markerUsedCounter > 0) {
                markerMasterEntryBean.setSaveDisabled(true);
            } else {
                markerMasterEntryBean.setSaveDisabled(false);
            }
            List<MarkerEntryDetails> markerEntryDetails = markerEntryDetailsRepository.findMarkerDetailById(markerMasterEntry.getId());
            if (markerEntryDetails != null && markerEntryDetails.size() > 0) {
                List<MarkerEntryDetailsBean> markerEntryDetailsBeans = new ArrayList<MarkerEntryDetailsBean>();
                for (MarkerEntryDetails markerEntryDetail : markerEntryDetails) {
                    MarkerEntryDetailsBean bean = new MarkerEntryDetailsBean();
                    BeanUtils.copyProperties(markerEntryDetail, bean);
                    markerEntryDetailsBeans.add(bean);
                }
                markerMasterEntryBean.setMarkerEntryDetails(markerEntryDetailsBeans);
            }
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(markerMasterEntryBean));
    }

    /**
     * {@code GET  /marker-master-entries-reject/:id} : get the "id" markerMasterEntry.
     *
     * @param id the id of the markerMasterEntry to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the markerMasterEntry, or with status {@code 404 (Not Found)}.
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @GetMapping("/marker-master-entries-return/{id}")
    public ResponseEntity<MarkerMasterEntryBean> getMarkerMasterEntryReturn(@PathVariable Long id) throws IllegalAccessException, InvocationTargetException {
        log.debug("REST request to get MarkerMasterEntry : {}", id);
        MarkerMasterEntry markerMasterEntry = markerMasterEntryRepository.findById(id).orElse(null);
        markerMasterEntry.setApprovalFlag(null);
        markerMasterEntry = markerMasterEntryRepository.save(markerMasterEntry);
        MarkerMasterEntryBean markerMasterEntryBean = new MarkerMasterEntryBean();
        if (markerMasterEntry != null) {
            BeanUtils.copyProperties(markerMasterEntry, markerMasterEntryBean, "itemCode");
            FullitemkeydecoderBean fullkeyBean = new FullitemkeydecoderBean();
            fullkeyBean.setSummarizeddescription(markerMasterEntry.getItemCode());
            markerMasterEntryBean.setItemCode(fullkeyBean);
            Long markerUsedCounter = cutPlanEntryRepository.countAllByMarkerMasterEntryId(id);
            if (markerUsedCounter > 0) {
                markerMasterEntryBean.setSaveDisabled(true);
            } else {
                markerMasterEntryBean.setSaveDisabled(false);
            }
            List<MarkerEntryDetails> markerEntryDetails = markerEntryDetailsRepository.findMarkerDetailById(markerMasterEntry.getId());
            if (markerEntryDetails != null && markerEntryDetails.size() > 0) {
                List<MarkerEntryDetailsBean> markerEntryDetailsBeans = new ArrayList<MarkerEntryDetailsBean>();
                for (MarkerEntryDetails markerEntryDetail : markerEntryDetails) {
                    MarkerEntryDetailsBean bean = new MarkerEntryDetailsBean();
                    BeanUtils.copyProperties(markerEntryDetail, bean);
                    markerEntryDetailsBeans.add(bean);
                }
                markerMasterEntryBean.setMarkerEntryDetails(markerEntryDetailsBeans);
            }
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(markerMasterEntryBean));
    }
}
