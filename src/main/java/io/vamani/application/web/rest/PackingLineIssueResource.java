package io.vamani.application.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.config.Constants;
import io.vamani.application.db2.domain.*;
import io.vamani.application.db2.repository.*;
import io.vamani.application.domain.*;
import io.vamani.application.model.CutPlanSearch;
import io.vamani.application.model.PackingLineIssueBean;
import io.vamani.application.model.StitchIssuePackDetailsBean;
import io.vamani.application.repository.*;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

/**
 * REST controller for managing {@link PackingLineIssue}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PackingLineIssueResource {

    private final Logger log = LoggerFactory.getLogger(PackingLineIssueResource.class);

    private static final String ENTITY_NAME = "packingLineIssue";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PackingLineIssueRepository packingLineIssueRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    FactoryRepository factoryRepository;

    @Autowired
    private PackingLineIssueDetailsRepository packingLineIssueDetailsRepository;

    @Autowired
    private CutPlanBundleRepository cutPlanBundleRepository;

    @Autowired
    private StocktransactionRepository stocktransactionRepository;

    @Autowired
    private StocktransactionimportRepository stocktransactionimportRepository;

    @Autowired
    private AdstorageimportRepository adstorageimportRepository;

    @Autowired
    private AdstorageRepository adstorageRepository;

    @Autowired
    private ProductiondemandRepository productiondemandRepository;

    @Autowired
    private ProductionreservationRepository productionreservationRepository;

    @Autowired
    private LogicalwarehouseRepository logicalwarehouseRepository;

    @Autowired
    private StitchIssuePackDetailsRepository stitchIssuePackDetailsRepository;

    @Autowired
    private PackingLineIssueTransactionRepository packingLineIssueTransactionRepository;

    @Autowired
    private CostcenterRepository costcenterRepository;

    public PackingLineIssueResource(PackingLineIssueRepository packingLineIssueRepository) {
        this.packingLineIssueRepository = packingLineIssueRepository;
    }

    /**
     * {@code POST  /packing-line-issues} : Create a new packingLineIssue.
     *
     * @param packingLineIssue the packingLineIssue to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new packingLineIssue, or with status {@code 400 (Bad Request)} if the packingLineIssue has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/packing-line-issues")
    public ResponseEntity<PackingLineIssue> createPackingLineIssue(@Valid @RequestBody PackingLineIssueBean packingLineIssueBean) throws URISyntaxException {
        log.debug("REST request to save PackingLineIssue : {}", packingLineIssueBean);
        if (packingLineIssueBean.getId() != null) {
            throw new BadRequestAlertException("A new packingLineIssue cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PackingLineIssue packingLineIssue = new PackingLineIssue();
        BeanUtils.copyProperties(packingLineIssueBean, packingLineIssue);
        packingLineIssue.setCreatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
        packingLineIssue.setCreateddate(Instant.now());
        PackingLineIssue result = packingLineIssueRepository.save(packingLineIssue);
        if (result != null && packingLineIssueBean.getStitchIssuePackDetails() != null && packingLineIssueBean.getStitchIssuePackDetails().size() > 0) {
            for (StitchIssuePackDetailsBean packingLineIssueDetailsBean : packingLineIssueBean.getStitchIssuePackDetails()) {
                    PackingLineIssueDetails packingLineIssueDetails = new PackingLineIssueDetails();
                    BeanUtils.copyProperties(packingLineIssueDetailsBean, packingLineIssueDetails);
                    packingLineIssueDetails.setId(null);
                    packingLineIssueDetails.setPackingLineIssue(result);
                    packingLineIssueDetailsRepository.save(packingLineIssueDetails);
            }
        }
        return ResponseEntity.created(new URI("/api/packing-line-issues/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /packing-line-issues} : Updates an existing packingLineIssue.
     *
     * @param packingLineIssue the packingLineIssue to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated packingLineIssue,
     * or with status {@code 400 (Bad Request)} if the packingLineIssue is not valid,
     * or with status {@code 500 (Internal Server Error)} if the packingLineIssue couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/packing-line-issues")
    public ResponseEntity<PackingLineIssue> updatePackingLineIssue(@Valid @RequestBody PackingLineIssueBean packingLineIssueBean) throws URISyntaxException {
        log.debug("REST request to update PackingLineIssue : {}", packingLineIssueBean);
        if (packingLineIssueBean.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        PackingLineIssue packingLineIssue = new PackingLineIssue();
        BeanUtils.copyProperties(packingLineIssueBean, packingLineIssue);
        packingLineIssue.setLastupdatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
        packingLineIssue.setLastupdateddate(Instant.now());
        PackingLineIssue result = packingLineIssueRepository.save(packingLineIssue);
        if (result != null && packingLineIssueBean.getStitchIssuePackDetails() != null && packingLineIssueBean.getStitchIssuePackDetails().size() > 0) {
            for (StitchIssuePackDetailsBean packingLineIssueDetailsBean : packingLineIssueBean.getStitchIssuePackDetails()) {
                PackingLineIssueDetails packingLineIssueDetails = new PackingLineIssueDetails();
                BeanUtils.copyProperties(packingLineIssueDetailsBean, packingLineIssueDetails);
                packingLineIssueDetails.setPackingLineIssue(result);
                packingLineIssueDetailsRepository.save(packingLineIssueDetails);
            }
        }
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, packingLineIssue.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /packing-line-issues} : get all the packingLineIssuees.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of packingLineIssuees in body.
     */
    @GetMapping("/packing-line-issues")
    public ResponseEntity<List<PackingLineIssue>> getAllPackingLineIssuees(Pageable pageable) {
        log.debug("REST request to get a page of PackingLineIssuees");
        Page<PackingLineIssue> page = packingLineIssueRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cut-plan-entries} : get all the cutPlanEntries.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cutPlanEntries in body.
     */
    @PostMapping("/packing-line-issues-filter")
    public ResponseEntity<List<PackingLineIssue>> getAllPackingLineIssueesFilter(@RequestBody CutPlanSearch search) {
        log.debug("REST request to get a page of CutPlanEntries");
        String login = SecurityUtils.getCurrentUserLogin().orElse(null);
        String type = "%";
        if (search.getPono() != null) {
            type = "%" + search.getPono().toUpperCase() + "%";
        }
        String style = "%";
        if (search.getStyle() != null) {
            style = "%" + search.getStyle().toUpperCase() + "%";
        }
        String color = "%";
        if (search.getColor() != null) {
            color = "%" + search.getColor().toUpperCase() + "%";
        }
        search.setPage(
            PageRequest.of(
                search.getPageNo(),
                search.getSize(),
                search.getSortType() != null && search.getSortType().equalsIgnoreCase("desc")
                    ? Sort.by(search.getSort()).descending()
                    : Sort.by(search.getSort()).ascending()
            )
        );
        Page<PackingLineIssue> page = packingLineIssueRepository.findAllByTypeAndPonoAndStyle(style, color, login, search.getPage());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }


    /**
     * {@code GET  /packing-line-issues/:id} : get the "id" packingLineIssue.
     *
     * @param id the id of the packingLineIssue to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the packingLineIssue, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/packing-line-issues/{id}")
    public ResponseEntity<PackingLineIssueBean> getPackingLineIssue(@PathVariable Long id) {
        log.debug("REST request to get PackingLineIssue : {}", id);
        PackingLineIssueBean packingLineIssueBean = new PackingLineIssueBean();
        PackingLineIssue packingLineIssue = packingLineIssueRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(packingLineIssue, packingLineIssueBean);
        List<PackingLineIssueDetails> packingLineIssueDetails = packingLineIssueDetailsRepository.findAllByPackingLineIssueId(id);
        List<StitchIssuePackDetailsBean> packingLineIssueDetailsBeans = new ArrayList<>();
        for (PackingLineIssueDetails cutIssuePackingDetail : packingLineIssueDetails) {
            StitchIssuePackDetailsBean packingLineIssueDetailsBean = new StitchIssuePackDetailsBean();
            BeanUtils.copyProperties(cutIssuePackingDetail, packingLineIssueDetailsBean);
            packingLineIssueDetailsBeans.add(packingLineIssueDetailsBean);
        }
        packingLineIssueBean.setStitchIssuePackDetails(packingLineIssueDetailsBeans);
        return ResponseUtil.wrapOrNotFound(Optional.of(packingLineIssueBean));
    }

    /**
     * {@code DELETE  /packing-line-issues/:id} : delete the "id" packingLineIssue.
     *
     * @param id the id of the packingLineIssue to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/packing-line-issues/{id}")
    public ResponseEntity<Void> deletePackingLineIssue(@PathVariable Long id) {
        log.debug("REST request to delete PackingLineIssue : {}", id);
        packingLineIssueRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code DELETE  /cut-plan-issue-packinges/:id} : delete the "id" cutPlanIssuePacking.
     *
     * @param id the id of the cutPlanIssuePacking to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/packing-line-issue-details/{id}")
    public ResponseEntity<Void> deletePackingLineIssueDetails(@PathVariable Long id) {
        log.debug("REST request to delete PackingLineIssueDetails : {}", id);
        packingLineIssueDetailsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/packing-line-issues-post/{id}")
    public ResponseEntity<PackingLineIssueBean> postCutBundleEntry(@PathVariable Long id) throws URISyntaxException {
        log.debug("REST request to save CutBundleEntry : {}", id);
        PackingLineIssueBean packingLineIssueBean = new PackingLineIssueBean();
        PackingLineIssue packingLineIssue = packingLineIssueRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(packingLineIssue, packingLineIssueBean);
        String costcentercode = costcenterRepository.findPackingCostCenter(packingLineIssue.getPlantCode());
        List<Object[]> packingLineIssueDetails = packingLineIssueDetailsRepository.findAllObjectByPackingLineIssueId(id);
        List<StitchIssuePackDetailsBean> packingLineIssueDetailsBeans = new ArrayList<>();
        Logicalwarehouse logicalwarehouse = null;
        if (packingLineIssue.getProductionCode().startsWith("PK")) {
            logicalwarehouse = logicalwarehouseRepository.findInTransitSEGWarehouseByPlantCode(packingLineIssue.getPlantCode());
        } else {
            logicalwarehouse = logicalwarehouseRepository.findInTransitHFSEGWarehouseByPlantCode(packingLineIssue.getPlantCode());
        }
        if (logicalwarehouse != null) {
            int ctr = 0;
            for (Object[] packingLineIssueDetail : packingLineIssueDetails) {
                String demandcode = productionreservationRepository.findProductionDemandCode(Constants.COMPANY_CODE, packingLineIssue.getProductionCode(), packingLineIssueDetail[0].toString(), packingLineIssueDetail[1].toString().trim(), packingLineIssueDetail[2].toString().trim(), packingLineIssueDetail[3].toString().trim(), packingLineIssueDetail[4].toString().trim(), packingLineIssueDetail[5].toString().trim(), packingLineIssueDetail[6].toString().trim(), packingLineIssueDetail[7].toString().trim(), packingLineIssueDetail[8].toString().trim(), packingLineIssueDetail[9].toString().trim(), packingLineIssueDetail[10].toString().trim());
                if (demandcode != null) {
                    Productiondemand productiondemand = productiondemandRepository.findByDemandCode(Constants.COMPANY_CODE, demandcode);
                    if (productiondemand != null) {
                        List<Productionreservation> productionreservations = productionreservationRepository.findAllProductionResverationByProductiondemandCodeAndSEG(productiondemand.getId().getCompanycode(), productiondemand.getId().getCountercode(), productiondemand.getId().getCode(), packingLineIssue.getProductionCode());
                        if (productionreservations != null && productionreservations.size() > 0) {
                            Long stockTransactionDestinationId = stocktransactionimportRepository.getNextSequence();
                            Long parentAdditionIdIssue = adstorageimportRepository.getNextSequence();
                            Stocktransactionimport stocktransactionimport = to(Constants.COMPANY_CODE, productiondemand, packingLineIssue, packingLineIssueDetail, stockTransactionDestinationId, parentAdditionIdIssue, 1, "M02", "0", logicalwarehouse, productionreservations.get(0).getId().getReservationline(), costcentercode);
                            Stocktransactionimport resultdestination = stocktransactionimportRepository.save(stocktransactionimport);
                            if (resultdestination != null) {
                                Adstorageimport adstorageimportBundleNo = new Adstorageimport(new AdstorageimportId("StockTransaction", parentAdditionIdIssue, "StockTransaction", "InterfaceBundleNumber", "InterfaceBundleNumber"), 0, 0, 0, 0, packingLineIssue.getId().toString(), 0, (short) 0, null, new BigDecimal(0), 0L, null, null, 0L);
                                adstorageimportRepository.save(adstorageimportBundleNo);

                                Adstorageimport adstorageimportDestinationIssue = new Adstorageimport(new AdstorageimportId("StockTransaction", parentAdditionIdIssue, "StockTransaction", "Destination", "Destination"), 0, 0, 0, 0, packingLineIssue.getDestination(), 0, (short) 0, null, new BigDecimal(0), 0L, null, null, 0L);
                                adstorageimportRepository.save(adstorageimportDestinationIssue);

                                Adstorageimport adstorageimportLineIssue = new Adstorageimport(new AdstorageimportId("StockTransaction", parentAdditionIdIssue, "StockTransaction", "Sewing", "Sewing"), 0, 0, 0, 0, packingLineIssue.getLine(), 0, (short) 0, null, new BigDecimal(0), 0L, null, null, 0L);
                                adstorageimportRepository.save(adstorageimportLineIssue);

                                stocktransactionimport.setImportstatus(1);
                                stocktransactionimportRepository.save(stocktransactionimport);

                                PackingLineIssueTransaction packingLineIssueTransaction = new PackingLineIssueTransaction();
                                packingLineIssueTransaction.setProductionCode(packingLineIssue.getProductionCode());
                                packingLineIssueTransaction.setGroupstepnumber(productionreservations.get(0).getId().getReservationline().longValue());
                                packingLineIssueTransaction.setDemandcountercode(productiondemand.getId().getCountercode());
                                packingLineIssueTransaction.setDemandcode(productiondemand.getId().getCode());
                                packingLineIssueTransaction.setItemtypecode(packingLineIssueDetail[0].toString());
                                packingLineIssueTransaction.setSubcode01(packingLineIssueDetail[1].toString());
                                packingLineIssueTransaction.setSubcode02(packingLineIssueDetail[2].toString());
                                packingLineIssueTransaction.setSubcode03(packingLineIssueDetail[3].toString());
                                packingLineIssueTransaction.setSubcode04(packingLineIssueDetail[4].toString());
                                packingLineIssueTransaction.setSubcode05(packingLineIssueDetail[5].toString());
                                packingLineIssueTransaction.setSubcode06(packingLineIssueDetail[6].toString());
                                packingLineIssueTransaction.setSubcode07(packingLineIssueDetail[7].toString());
                                packingLineIssueTransaction.setSubcode08(packingLineIssueDetail[8].toString());
                                packingLineIssueTransaction.setSubcode09(packingLineIssueDetail[9].toString());
                                packingLineIssueTransaction.setSubcode10(packingLineIssueDetail[10].toString());
                                packingLineIssueTransaction.setLogicalwarehousecode(logicalwarehouse.getId().getCode());
                                packingLineIssueTransaction.setPhysicalwarehousecode(logicalwarehouse.getPhysicalwarehousecode());
                                packingLineIssueTransaction.setPrimaryuomcode(packingLineIssueDetail[11].toString());
                                packingLineIssueTransaction.setSecondaryuomcode(packingLineIssueDetail[12].toString());
                                packingLineIssueTransaction.setPrimaryquantity((Double) packingLineIssueDetail[13]);
                                packingLineIssueTransaction.setSecondaryquantity((Double) packingLineIssueDetail[14]);
                                packingLineIssueTransaction.setStocktransactionid(Long.parseLong(resultdestination.getId().getTransactionnumber()));
                                packingLineIssueTransaction.setPackingLineIssueId(packingLineIssue.getId());
                                PackingLineIssueTransaction resultDetails = packingLineIssueTransactionRepository.save(packingLineIssueTransaction);
                                ++ctr;
                            }
                        }
                    } else {
                        return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "Not suitable Demand exist for size.")).build();
                    }
                } else {
                    return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "Not suitable Demand exist for size.")).build();
                }
                if (packingLineIssueDetails.size() == ctr) {
                    packingLineIssue.setPostedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                    packingLineIssue.setPostedDate(Instant.now());
                    packingLineIssueRepository.save(packingLineIssue);
                }
            }
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(packingLineIssueBean));
    }

    public static Stocktransactionimport to(String companycode, Productiondemand productiondemand, PackingLineIssue packingLineIssue, Object[] packingLineIssueDetail, Long stockTransactionId, Long parentAdditionId, Integer lineNo, String templatecode, String detailType, Logicalwarehouse logicalwarehouse, Integer groupline, String costcentercode) {
        Stocktransactionimport stocktransactionimport = new Stocktransactionimport();
        stocktransactionimport.setId(new StocktransactionimportId(Constants.COMPANY_CODE, stockTransactionId.toString(), lineNo));
        stocktransactionimport.setImportstatus(0);
        stocktransactionimport.setNowtrnnumbertransactionnumber("");
        stocktransactionimport.setTransactionstatus("0");
        stocktransactionimport.setItemtypecompanycode(companycode);
        stocktransactionimport.setItemtypecode(packingLineIssueDetail[0].toString());
        stocktransactionimport.setTransactiondate(new Date(System.currentTimeMillis()));
        stocktransactionimport.setTransactiontime(new Time(System.currentTimeMillis()));
        stocktransactionimport.setDetailtype(detailType);
        stocktransactionimport.setTemplatecompanycode(companycode);
        stocktransactionimport.setTemplatecode(templatecode);
        stocktransactionimport.setAutoissuetemplatecompanycode("");
        stocktransactionimport.setAutoissuetemplatecode("");
        stocktransactionimport.setStocktransactiontype("1");
        stocktransactionimport.setDecocompanycode(companycode);
        stocktransactionimport.setDecosubcode01(packingLineIssueDetail[1].toString());
        stocktransactionimport.setDecosubcode02(packingLineIssueDetail[2].toString());
        stocktransactionimport.setDecosubcode03(packingLineIssueDetail[3].toString());
        stocktransactionimport.setDecosubcode04(packingLineIssueDetail[4].toString());
        stocktransactionimport.setDecosubcode05(packingLineIssueDetail[5].toString());
        stocktransactionimport.setDecosubcode06(packingLineIssueDetail[6].toString());
        stocktransactionimport.setDecosubcode07(packingLineIssueDetail[7].toString());
        stocktransactionimport.setDecosubcode08(packingLineIssueDetail[8].toString());
        stocktransactionimport.setDecosubcode09(packingLineIssueDetail[9].toString());
        stocktransactionimport.setDecosubcode10(packingLineIssueDetail[10].toString());
        stocktransactionimport.setItemdescription("");
        stocktransactionimport.setLogicalwarehousecompanycode(companycode);
        stocktransactionimport.setLogicalwarehousecode(logicalwarehouse.getId().getCode().trim());
        stocktransactionimport.setTransferallocation("");
        stocktransactionimport.setIssueqtyfrombalance((short) 0);

        stocktransactionimport.setUserprimaryuomcode(packingLineIssueDetail[11].toString());
        stocktransactionimport.setUserprimaryquantity(new BigDecimal((Double) packingLineIssueDetail[13]));
        stocktransactionimport.setUsersecondaryuomcode(packingLineIssueDetail[12].toString());
        stocktransactionimport.setUsersecondaryquantity(new BigDecimal((Double) packingLineIssueDetail[14]));
        /*  stocktransactionimport.setUserpackaginguomcode("");
            stocktransactionimport.setUserpackagingquantity("");*/
        stocktransactionimport.setWeightunitofmeasurecode("");
        stocktransactionimport.setWeightgross(new BigDecimal("0"));
        stocktransactionimport.setWeightnet(new BigDecimal("0"));
        stocktransactionimport.setWeightrealnet(new BigDecimal("0"));
        /*  stocktransactionimport.setDerivationcode();
            stocktransactionimport.setDerivationlinenumber();
            stocktransactionimport.setDerivationcomponentlinenumber();*/

        stocktransactionimport.setQualitylvlitemtypecompanycode(companycode);
        stocktransactionimport.setQualitylevelcode(new BigInteger("1"));
        /*  stocktransactionimport.setQualityreasoncompanycode();
            stocktransactionimport.setQualityreasoncode();
            stocktransactionimport.setFirstqualitycontroldate();
            stocktransactionimport.setFirstqualitycontrolcounter();
            stocktransactionimport.setFirstqualitycontrolnumber();*/
        stocktransactionimport.setPhysicalwarehousecompanycode(companycode);
        stocktransactionimport.setPhysicalwarehousecode(logicalwarehouse.getPhysicalwarehousecode());
        stocktransactionimport.setWhslocwhszonephywhscmycode(companycode);
        stocktransactionimport.setWhslocationwarehousezonecode(null);
        stocktransactionimport.setWarehouselocationcode(null);
        stocktransactionimport.setContainercompanycode(companycode);
        stocktransactionimport.setContaineritemtypecode("");
        stocktransactionimport.setContainersubcode01("");
        stocktransactionimport.setContainerelementcompanycode("");
        stocktransactionimport.setContainerelementcode("");
        stocktransactionimport.setLotcompanycode(companycode);
        stocktransactionimport.setLotcode("");
        stocktransactionimport.setItemelementcompanycode(companycode);
        stocktransactionimport.setItemelementsubcodekey("");
        stocktransactionimport.setItemelementcode("");
        stocktransactionimport.setProjectcode(packingLineIssue.getProjectcode());
        stocktransactionimport.setCostcentercompanycode(companycode);
        stocktransactionimport.setCostcentercode(costcentercode);
        stocktransactionimport.setStatisticalgroupcompanycode(companycode);
        stocktransactionimport.setStatisticalgroupcode("");
        stocktransactionimport.setCost(new BigDecimal("0"));
        stocktransactionimport.setCurrencycode("");
        stocktransactionimport.setCustomertype("");
        stocktransactionimport.setCustomercode("");
        stocktransactionimport.setSuppliertype("");
        stocktransactionimport.setSuppliercode("");
        stocktransactionimport.setBilldate(null);
        stocktransactionimport.setBilltype((short) 0);
        stocktransactionimport.setBillcounter("");
        stocktransactionimport.setBillcode("");
        stocktransactionimport.setInternaldocumentdate(null);
        stocktransactionimport.setInternaldocumentnumber(0);
        stocktransactionimport.setOrdercountercompanycode("");
        stocktransactionimport.setOrdercountercode(productiondemand.getId().getCountercode());
        stocktransactionimport.setOrdercode(productiondemand.getId().getCode());
        stocktransactionimport.setOrderline(Integer.parseInt(groupline.toString()));
        stocktransactionimport.setOrdersubline(0);
        stocktransactionimport.setOrdercomponentline(0);
        stocktransactionimport.setOrderdeliveryline(0);
        stocktransactionimport.setProductionordercode(packingLineIssue.getProductionCode());
        stocktransactionimport.setReturncode("");
        stocktransactionimport.setReturnline(0);
        stocktransactionimport.setTokencode("");
        stocktransactionimport.setEnvcodeskipblcexp("");
        stocktransactionimport.setAduniqueid(parentAdditionId);
        stocktransactionimport.setAduniqueidforautoissue(parentAdditionId);
        stocktransactionimport.setCreationdatetime(Timestamp.from(Instant.now()));
        stocktransactionimport.setCreationuser("system");
        stocktransactionimport.setLastupdatedatetime(null);
        stocktransactionimport.setLastupdateuser(null);
        stocktransactionimport.setAbsuniqueid(0L);
        return stocktransactionimport;
    }

    /**
     * {@code GET  /cut-bundle-entries/:id} : get the "id" cutBundleEntry.
     *
     * @param id the id of the cutBundleEntry to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cutBundleEntry, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/stitch-issue-packing-details-bundle/{id}")
    public ResponseEntity<List<StitchIssuePackDetailsBean>> getStitchStickByBundle(@PathVariable Long id) {
        log.debug("REST request to get CutBundleEntry : {}", id);
        List<StitchIssuePackDetailsBean> stitchIssuePackDetailsBeans = new ArrayList<>();
        List<StitchIssuePackDetails> stitchIssuePackDetails = stitchIssuePackDetailsRepository.findStitchIssuePackDetailsByBundleId(id);
        if (stitchIssuePackDetails != null) {
            for (StitchIssuePackDetails stitchIssuePackDetail : stitchIssuePackDetails) {
                PackingLineIssueDetails packingLineIssueDetails = packingLineIssueDetailsRepository.findPackingLineIssueDetailsByPieceId(stitchIssuePackDetail.getCutPlanBundleDetailsId());
                if (packingLineIssueDetails != null) {
                } else {
                    StitchIssuePackDetailsBean stitchIssuePackDetailsBean = new StitchIssuePackDetailsBean();
                    BeanUtils.copyProperties(stitchIssuePackDetail, stitchIssuePackDetailsBean);
                    stitchIssuePackDetailsBean.setId(null);
                    stitchIssuePackDetailsBean.setCreatedby(null);
                    stitchIssuePackDetailsBean.setCreateddate(null);
                    stitchIssuePackDetailsBean.setLastupdatedby(null);
                    stitchIssuePackDetailsBean.setLastupdateddate(null);
                    stitchIssuePackDetailsBean.setStitchIssuePackDetailsId(stitchIssuePackDetail.getId());
                    stitchIssuePackDetailsBean.setStitchStockDetailsId(stitchIssuePackDetail.getStitchStockDetailsId());
                    stitchIssuePackDetailsBeans.add(stitchIssuePackDetailsBean);
                }
            }
        } else {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "Invalid Bundle Number.")).build();
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(stitchIssuePackDetailsBeans));
    }

    /**
     * {@code GET  /cut-bundle-entries/:id} : get the "id" cutBundleEntry.
     *
     * @param id the id of the cutBundleEntry to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cutBundleEntry, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/stitch-issue-packing-details-piece/{id}")
    public ResponseEntity<List<StitchIssuePackDetailsBean>> getStitchStickByPiece(@PathVariable Long id) {
        log.debug("REST request to get CutBundleEntry : {}", id);
        List<StitchIssuePackDetailsBean> stitchIssuePackDetailsBeans = new ArrayList<>();
        StitchIssuePackDetails stitchIssuePackDetail = stitchIssuePackDetailsRepository.findStitchIssuePackDetailsByPieceId(id);
        if (stitchIssuePackDetail != null) {
            PackingLineIssueDetails packingLineIssueDetails = packingLineIssueDetailsRepository.findPackingLineIssueDetailsByPieceId(stitchIssuePackDetail.getCutPlanBundleDetailsId());
            if (packingLineIssueDetails != null) {
                return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "Piece Number already used.")).build();
            } else {
                StitchIssuePackDetailsBean stitchIssuePackDetailsBean = new StitchIssuePackDetailsBean();
                BeanUtils.copyProperties(stitchIssuePackDetail, stitchIssuePackDetailsBean);
                stitchIssuePackDetailsBean.setId(null);
                stitchIssuePackDetailsBean.setCreatedby(null);
                stitchIssuePackDetailsBean.setCreateddate(null);
                stitchIssuePackDetailsBean.setLastupdatedby(null);
                stitchIssuePackDetailsBean.setLastupdateddate(null);

                stitchIssuePackDetailsBean.setStitchStockDetailsId(stitchIssuePackDetail.getStitchStockDetailsId());
                stitchIssuePackDetailsBean.setStitchIssuePackDetailsId(stitchIssuePackDetail.getId());
                stitchIssuePackDetailsBeans.add(stitchIssuePackDetailsBean);
            }
        } else {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "Invalid Piece Number.")).build();
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(stitchIssuePackDetailsBeans));
    }

    @GetMapping("/packing-line-issues-pdf/{id}")
    void GetPackingLineIssuePdf(@PathVariable Long id, HttpServletResponse response) {
        if (id != null && id.intValue() > 0) {
            try {
                List<PackingLineIssueBean> packingLineIssueBeans = new ArrayList<>();
                PackingLineIssue packingLineIssue = packingLineIssueRepository.findById(id).orElse(null);
                if (packingLineIssue != null && packingLineIssue.getId() > 0) {
                    PackingLineIssueBean packingLineIssueBean = new PackingLineIssueBean();
                    BeanUtils.copyProperties(packingLineIssue, packingLineIssueBean);
                    Factory factory = factoryRepository.findById(packingLineIssueBean.getPlantCode()).orElse(null);
                    String add1 = (factory.getAddressline1() != null ? factory.getAddressline1().trim() : "") + (factory.getAddressline2() != null ? factory.getAddressline2().trim() : "");
                    packingLineIssueBean.setPlantAddress(add1);
                    packingLineIssueBean.setCreateddatenew(Date.from(packingLineIssue.getCreateddate()));
                    Factory factoryDest = factoryRepository.findById(packingLineIssueBean.getDestinationPlantCode() != null ? packingLineIssueBean.getDestinationPlantCode() : packingLineIssueBean.getPlantCode()).orElse(null);
                    String add2 = (factoryDest.getAddressline1() != null ? factoryDest.getAddressline1().trim() : "") + (factoryDest.getAddressline2() != null ? factoryDest.getAddressline2().trim() : "");
                    packingLineIssueBean.setDestinationPlantAddress(add2);

                    List<StitchIssuePackDetails> stitchIssuePackDetails = stitchIssuePackDetailsRepository.findAllByStitchIssuePackId(packingLineIssueBean.getId());
                    if (stitchIssuePackDetails != null && stitchIssuePackDetails.size() > 0) {
                        List<StitchIssuePackDetailsBean> stitchIssuePackDetailsBeans = new ArrayList<>();
                        for (StitchIssuePackDetails lines : stitchIssuePackDetails) {
                            StitchIssuePackDetailsBean stitchIssuePackDetailsBean = new StitchIssuePackDetailsBean();
                            CutPlanBundle cutPlanBundle = cutPlanBundleRepository.findById(lines.getCutPlanBundleId()).orElse(null);
                            BeanUtils.copyProperties(lines, stitchIssuePackDetailsBean);
                            stitchIssuePackDetailsBean.setProductionCode(packingLineIssueBean.getProductionCode());
                            stitchIssuePackDetailsBean.setBundleCode(cutPlanBundle.getBundleCode());
                            stitchIssuePackDetailsBeans.add(stitchIssuePackDetailsBean);
                        }
                        packingLineIssueBean.setStitchIssuePackDetails(stitchIssuePackDetailsBeans);
                    }
                    packingLineIssueBeans.add(packingLineIssueBean);
                    String path = applicationProperties.getTemplatePath() + "jasper/";
                    JasperDesign jasperDesign = JRXmlLoader.load(path + "/PackLineIssueRpt.jrxml");
                    //load(path + "/IssueToLine.jrxml");
                    JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                    Map<String, Object> parameters = new HashMap<>();
                    JRDataSource jrDataSource = new JRBeanCollectionDataSource(packingLineIssueBeans);
                    parameters.put("datasource", jrDataSource);
                    parameters.put("SUBREPORT_DIR", path);
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
                    response.setContentType("application/x-pdf");
                    response.setHeader("Content-Disposition", "attachment; filename=PackLineIssueRpt.pdf");
                    final OutputStream outputStream = response.getOutputStream();
                    JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
