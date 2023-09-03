package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.config.Constants;
import io.vamani.application.db2.domain.*;
import io.vamani.application.db2.model.BalanceBean;
import io.vamani.application.db2.repository.*;
import io.vamani.application.domain.CutPlanEntry;
import io.vamani.application.domain.CutPlanEntryDetails;
import io.vamani.application.model.CutFabricIssueBean;
import io.vamani.application.model.CutPlanEntryDetailsBean;
import io.vamani.application.repository.CutPlanEntryDetailsRepository;
import io.vamani.application.repository.CutPlanEntryRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CutFabricIssueResource {

    private final Logger log = LoggerFactory.getLogger(CutFabricIssueResource.class);

    private static final String ENTITY_NAME = "CutFabricIssue";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    private CutPlanEntryRepository cutPlanEntryRepository;

    @Autowired
    private CutPlanEntryDetailsRepository cutPlanEntryDetailsRepository;

    @Autowired
    private StocktransactionimportRepository stocktransactionimportRepository;

    @Autowired
    private StocktransactionRepository stocktransactionRepository;

    @Autowired
    private AdstorageimportRepository adstorageimportRepository;

    @Autowired
    private ProductionreservationRepository productionreservationRepository;

    @Autowired
    private LogicalwarehouseRepository logicalwarehouseRepository;

    @Autowired
    private WarehouseitemcostRepository warehouseitemcostRepository;

    @Autowired
    private ResourcesRepository resourcesRepository;

    @Autowired
    private CostcenterRepository costcenterRepository;

    @PostMapping("/cut-fabric-issue-detail-save")
    public ResponseEntity<CutFabricIssueBean> SaveCutFabric(@Valid @RequestBody List<CutPlanEntryDetailsBean> cutPlanEntryDetails) throws URISyntaxException {
        log.debug("REST request to save CutPlanEntry : {}", cutPlanEntryDetails);
        if (cutPlanEntryDetails != null && cutPlanEntryDetails.size() > 0) {
            CutPlanEntryDetails cutPlanEntryDetail = cutPlanEntryDetailsRepository.findById(cutPlanEntryDetails.get(0).getId()).orElse(null);
            CutPlanEntry cutPlanEntry = cutPlanEntryDetail.getCutPlanEntry();
            String costcentercode = costcenterRepository.findCuttingCostCenter(cutPlanEntry.getPlantCode());
            for (CutPlanEntryDetailsBean cutPlanEntryDetailsBean : cutPlanEntryDetails) {
                if (cutPlanEntryDetailsBean.getIssuedDate() != null && cutPlanEntryDetailsBean.getIssuedBy() != null) {
                } else if (cutPlanEntryDetailsBean.getAllowPlies() != null && cutPlanEntryDetailsBean.getAllowPlies() == true) {
                    if (cutPlanEntryDetailsBean.getSplitTransactionnumber() != null) {
                        Page<Stocktransaction> page = stocktransactionRepository.findAllByElementCode(cutPlanEntryDetailsBean.getElementscode(), PageRequest.of(0, 1, Sort.by(Sort.Direction.ASC, "id.transactionnumber")));
                        Stocktransaction stocktransaction = null;
                        if (page != null && page.getContent().size() > 0) {
                            stocktransaction = page.getContent().get(0);
                        }
                        if(stocktransaction != null) {
                            Long parentAdditionId = adstorageimportRepository.getNextSequence();

                            Long stockId = stocktransactionimportRepository.getNextSequence();
                            cutPlanEntryDetailsBean.setBaseprimaryquantityunit(stocktransaction.getBaseprimaryquantity().doubleValue());
                            cutPlanEntryDetailsBean.setBasesecondaryquantityunit(stocktransaction.getBasesecondaryquantity().doubleValue());

                            Long groupLine = productionreservationRepository.maxGroupLine(Constants.COMPANY_CODE, cutPlanEntry.getProductionCode(), cutPlanEntryDetailsBean.getItemtypecode(), cutPlanEntryDetailsBean.getDecosubcode01(), cutPlanEntryDetailsBean.getDecosubcode02(), cutPlanEntryDetailsBean.getDecosubcode03(), cutPlanEntryDetailsBean.getDecosubcode04(), cutPlanEntryDetailsBean.getDecosubcode05(), cutPlanEntryDetailsBean.getDecosubcode06(), cutPlanEntryDetailsBean.getDecosubcode07(), cutPlanEntryDetailsBean.getDecosubcode08(), cutPlanEntryDetailsBean.getDecosubcode09(), cutPlanEntryDetailsBean.getDecosubcode10());
                            Stocktransactionimport stocktransactionimport = to(Constants.COMPANY_CODE, cutPlanEntryDetailsBean, stockId, 1, "M02", "0", cutPlanEntry.getPorductionCounterCode(), cutPlanEntry.getProductionCode(), groupLine, parentAdditionId, costcentercode, stocktransaction.getItemelementcode());
                            Stocktransactionimport stockResult = stocktransactionimportRepository.save(stocktransactionimport);

                            Adstorageimport adstorageimportplan = new Adstorageimport(new AdstorageimportId("StockTransaction", parentAdditionId, "StockTransaction", "CutPlanNo", "CutPlanNo"), 0, 0, 0, 0, cutPlanEntry.getId().toString(), 0, (short) 0, null, new BigDecimal(0), 0L, null, null, 0L);
                            adstorageimportRepository.save(adstorageimportplan);

                            Adstorageimport adstorageimport = new Adstorageimport(new AdstorageimportId("StockTransaction", parentAdditionId, "StockTransaction", "CuttingTable", "CuttingTable"), 0, 0, 0, 0, cutPlanEntryDetailsBean.getResourceCode(), 0, (short) 0, null, new BigDecimal(0), 0L, null, null, 0L);
                            adstorageimportRepository.save(adstorageimport);

                            Adstorageimport adstorageimportDestination = new Adstorageimport(new AdstorageimportId("StockTransaction", parentAdditionId, "StockTransaction", "Destination", "Destination"), 0, 0, 0, 0, cutPlanEntry.getDestination(), 0, (short) 0, null, new BigDecimal(0), 0L, null, null, 0L);
                            adstorageimportRepository.save(adstorageimportDestination);

                            stockResult.setImportstatus(1);
                            stocktransactionimportRepository.save(stockResult);

                            if (stockResult != null) {
                                CutPlanEntryDetails entryDetails = cutPlanEntryDetailsRepository.findById(cutPlanEntryDetailsBean.getId()).orElse(null);
                                entryDetails.setResourceCode(cutPlanEntryDetailsBean.getResourceCode());
                                entryDetails.setResourceDescription(cutPlanEntryDetailsBean.getResourceDescription());
                                entryDetails.setIssuedBy(SecurityUtils.getCurrentUserLogin().get());
                                entryDetails.setIssuedDate(Instant.now());
                                entryDetails.setTransactionId(stockId);
                                cutPlanEntryDetailsRepository.save(entryDetails);
                            }
                        } else {
                            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Issue in Fabric split. Please check in now before issue."))
                                .build();
                        }
                    } else {
                        Long parentAdditionId = adstorageimportRepository.getNextSequence();

                        Long stockId = stocktransactionimportRepository.getNextSequence();
                        Long groupLine = productionreservationRepository.maxGroupLine(Constants.COMPANY_CODE, cutPlanEntry.getProductionCode(), cutPlanEntryDetailsBean.getItemtypecode(), cutPlanEntryDetailsBean.getDecosubcode01(), cutPlanEntryDetailsBean.getDecosubcode02(), cutPlanEntryDetailsBean.getDecosubcode03(), cutPlanEntryDetailsBean.getDecosubcode04(), cutPlanEntryDetailsBean.getDecosubcode05(), cutPlanEntryDetailsBean.getDecosubcode06(), cutPlanEntryDetailsBean.getDecosubcode07(), cutPlanEntryDetailsBean.getDecosubcode08(), cutPlanEntryDetailsBean.getDecosubcode09(), cutPlanEntryDetailsBean.getDecosubcode10());
                        Stocktransactionimport stocktransactionimport = to(Constants.COMPANY_CODE, cutPlanEntryDetailsBean, stockId, 1, "M02", "0", cutPlanEntry.getPorductionCounterCode(), cutPlanEntry.getProductionCode(), groupLine, parentAdditionId, costcentercode, null);
                        Stocktransactionimport stockResult = stocktransactionimportRepository.save(stocktransactionimport);

                        Adstorageimport adstorageimportplan = new Adstorageimport(new AdstorageimportId("StockTransaction", parentAdditionId, "StockTransaction", "CutPlanNo", "CutPlanNo"), 0, 0, 0, 0, cutPlanEntry.getId().toString(), 0, (short) 0, null, new BigDecimal(0), 0L, null, null, 0L);
                        adstorageimportRepository.save(adstorageimportplan);

                        Adstorageimport adstorageimport = new Adstorageimport(new AdstorageimportId("StockTransaction", parentAdditionId, "StockTransaction", "CuttingTable", "CuttingTable"), 0, 0, 0, 0, cutPlanEntryDetailsBean.getResourceCode(), 0, (short) 0, null, new BigDecimal(0), 0L, null, null, 0L);
                        adstorageimportRepository.save(adstorageimport);

                        Adstorageimport adstorageimportDestination = new Adstorageimport(new AdstorageimportId("StockTransaction", parentAdditionId, "StockTransaction", "Destination", "Destination"), 0, 0, 0, 0, cutPlanEntry.getDestination(), 0, (short) 0, null, new BigDecimal(0), 0L, null, null, 0L);
                        adstorageimportRepository.save(adstorageimportDestination);

                        stockResult.setImportstatus(1);
                        stocktransactionimportRepository.save(stockResult);

                        if (stockResult != null) {
                            CutPlanEntryDetails entryDetails = cutPlanEntryDetailsRepository.findById(cutPlanEntryDetailsBean.getId()).orElse(null);
                            entryDetails.setResourceCode(cutPlanEntryDetailsBean.getResourceCode());
                            entryDetails.setResourceDescription(cutPlanEntryDetailsBean.getResourceDescription());
                            entryDetails.setIssuedBy(SecurityUtils.getCurrentUserLogin().get());
                            entryDetails.setIssuedDate(Instant.now());
                            entryDetails.setTransactionId(stockId);
                            cutPlanEntryDetailsRepository.save(entryDetails);
                        }
                    }
                } else {
                    CutPlanEntryDetails entryDetails = cutPlanEntryDetailsRepository.findById(cutPlanEntryDetailsBean.getId()).orElse(null);
                    entryDetails.setResourceCode(cutPlanEntryDetailsBean.getResourceCode());
                    entryDetails.setResourceDescription(cutPlanEntryDetailsBean.getResourceDescription());
                    cutPlanEntryDetailsRepository.save(entryDetails);
                }
            }
        }
        CutFabricIssueBean result = null;
        return ResponseEntity.ok(result);
    }

    /**
     * GET  /menu-access-masters : get all the menuAccessMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of menuAccessMasters in body
     */
    @PostMapping("/splitfabricstock")
    @Timed
    public ResponseEntity<List<Stocktransactionimport>> splitFabric(@RequestBody CutPlanEntryDetailsBean cutPlanEntryDetailsBean) throws InterruptedException {
        CutPlanEntryDetails cutPlanEntryDetails = cutPlanEntryDetailsRepository.findById(cutPlanEntryDetailsBean.getId()).orElse(null);
        BalanceBean balances = new BalanceBean(cutPlanEntryDetails);
        log.debug("REST request to get a page of balances");
        DecimalFormat df = new DecimalFormat("#.####");
        DecimalFormat df2 = new DecimalFormat("#.###");
        boolean error = true;
        List<Stocktransactionimport> stocktransactionimports = new ArrayList<>();
        Balance originStock = new Balance();
        BeanUtils.copyProperties(balances, originStock);
        BigDecimal orgStock = balances.getBaseprimaryquantityunit().subtract(new BigDecimal(balances.getSplitEndBits()));
        BigDecimal perc = originStock.getBasesecondaryquantityunit().divide(originStock.getBaseprimaryquantityunit(), 3, RoundingMode.CEILING);
        BigDecimal orgSecStock = new BigDecimal(df2.format((orgStock.multiply(perc)).doubleValue()));

        originStock.setBaseprimaryquantityunit(balances.getBaseprimaryquantityunit());
        originStock.setBasesecondaryquantityunit(balances.getBasesecondaryquantityunit());

        double costValue = 0.0;
        if (originStock != null && originStock.getLogicalwarehousecode() != null) {
            Logicalwarehouse logicalwarehouse = logicalwarehouseRepository.findById(new LogicalwarehouseId(Constants.COMPANY_CODE, originStock.getLogicalwarehousecode())).orElse(null);
            if (logicalwarehouse != null && logicalwarehouse.getWarehouseaccountinggroupcode() != null) {
                List<Warehouseitemcost> warehouseitemcosts = warehouseitemcostRepository.findByAccgroupcodeAndItemcode(Constants.COMPANY_CODE, logicalwarehouse.getWarehouseaccountinggroupcode().trim(), originStock.getItemtypecode(), originStock.getDecosubcode01().trim(), originStock.getDecosubcode02().trim(), originStock.getDecosubcode03().trim(), originStock.getDecosubcode04().trim(), originStock.getDecosubcode05().trim(), originStock.getDecosubcode06().trim(), originStock.getDecosubcode07().trim(), originStock.getDecosubcode08().trim(), originStock.getDecosubcode09().trim(), originStock.getDecosubcode10().trim(), originStock.getQualitylevelcode());
                if (warehouseitemcosts != null && warehouseitemcosts.size() > 0) {
                    Warehouseitemcost warehouseitemcost = warehouseitemcosts.get(0);
                    if (warehouseitemcost.getDynamicaveragecostunitvalue() != null && warehouseitemcost.getDynamicaveragecostunitvalue().doubleValue() > 0) {
                        costValue = new Double(df.format(warehouseitemcost.getDynamicaveragecostunitvalue().doubleValue()));
                    } else if (warehouseitemcost.getWeightedaveragecost() != null && warehouseitemcost.getWeightedaveragecost().doubleValue() > 0) {
                        costValue = new Double(df.format(warehouseitemcost.getWeightedaveragecost().doubleValue()));
                    }
                }
            }
        }

        Long stockTransactionId = stocktransactionimportRepository.getNextSequence();

        Stocktransactionimport stocktransactionimportorigintotal = splitTo(originStock, stockTransactionId, 1, "G10", "0", "1", "1", new BigDecimal(0), null, originStock.getElementscode(), 0L);
        stocktransactionimportorigintotal.setImportstatus(0); // Inactive
        Stocktransactionimport resultorigintotal = stocktransactionimportRepository.save(stocktransactionimportorigintotal);

        BigDecimal destStock = new BigDecimal(balances.getSplitEndBits());
        BigDecimal destSecStock = balances.getBasesecondaryquantityunit().subtract(orgSecStock);
        originStock.setBaseprimaryquantityunit(destStock);
        originStock.setBasesecondaryquantityunit(destSecStock);
        Stocktransactionimport stocktransactionimportorigin = splitTo(originStock, stockTransactionId, 2, "G09", "0", "2", "1", new BigDecimal(costValue),"INR", originStock.getElementscode(), 0L);
        stocktransactionimportorigin.setImportstatus(0); // Inactive
        Stocktransactionimport resultorigin = stocktransactionimportRepository.save(stocktransactionimportorigin);
        stocktransactionimportRepository.updateStockFlag(stockTransactionId.toString());

        Long stockTransactionDestinationId = stocktransactionimportRepository.getNextSequence();

        Balance destinationStock = new Balance();
        BeanUtils.copyProperties(balances, destinationStock);
        destinationStock.setBaseprimaryquantityunit(orgStock);
        destinationStock.setBasesecondaryquantityunit(orgSecStock);

        Thread.sleep(2000);

        Long parentAdditionId = adstorageimportRepository.getNextSequence();
        Stocktransactionimport stocktransactionimportdestination = splitTo(destinationStock, stockTransactionDestinationId, 1, "G09", "0", "0", "1", new BigDecimal(costValue), "INR", null, parentAdditionId);
        stocktransactionimportdestination.setImportstatus(0);
        Stocktransactionimport resultdestination = stocktransactionimportRepository.save(stocktransactionimportdestination);

        Adstorageimport adstorageimportDestination = new Adstorageimport(new AdstorageimportId("StockTransaction", parentAdditionId, "StockTransaction", "OriginalElementCode", "OriginalElementCode"), 0, 0, 0, 0, originStock.getElementscode(), 0, (short) 0, null, new BigDecimal(0), 0L, null, null, 0L);
        adstorageimportRepository.save(adstorageimportDestination);
        stocktransactionimportdestination.setImportstatus(1);
        stocktransactionimportRepository.save(stocktransactionimportdestination);

        List<Stocktransactionimport> stocktransactionimportlst = stocktransactionimportRepository.findAllByTransactionnumber(stockTransactionId.toString());
        if (stocktransactionimportlst != null && stocktransactionimportlst.size() > 0) {
            for (Stocktransactionimport stocktransactionimport : stocktransactionimportlst) {
                if (stocktransactionimport.getImportstatus() == 2) {
                    stocktransactionimport.setExist(true);
                } else {
                    stocktransactionimport.setExist(false);
                    error = false;
                }
                stocktransactionimports.add(stocktransactionimport);
            }
        }

        Thread.sleep(2000);
        List<Stocktransactionimport> stocktransactionimportlstdest = stocktransactionimportRepository.findAllByTransactionnumber(stockTransactionDestinationId.toString());
        if (stocktransactionimportlstdest != null && stocktransactionimportlstdest.size() > 0) {
            for (Stocktransactionimport stocktransactionimport : stocktransactionimportlstdest) {
                stocktransactionimport.setExist(true);
                error = true;
                stocktransactionimports.add(stocktransactionimport);
            }
        }
        if (error == false) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "", "Record not split, Please contact BI team. with Reference no: " + stockTransactionId + " and " + stockTransactionDestinationId + ".")).body(null);
        } else {
            cutPlanEntryDetails.setSplitFlag("Y");
            cutPlanEntryDetails.setSplitTransactionnumber(stockTransactionId);
            cutPlanEntryDetailsRepository.save(cutPlanEntryDetails);
            return ResponseEntity.ok().body(stocktransactionimports);
        }
    }

    public static Stocktransactionimport splitTo(Balance balance, Long stockTransactionId, Integer lineNo, String templatecode, String transactionstatus, String detailType, String stocktransactiontype, BigDecimal costValue, String currencyCode, String elementcode, Long parentAdditionId) {
        Stocktransactionimport stocktransactionimport = new Stocktransactionimport();
        stocktransactionimport.setId(new StocktransactionimportId(Constants.COMPANY_CODE, stockTransactionId.toString(), lineNo));
        stocktransactionimport.setImportstatus(1);
        stocktransactionimport.setNowtrnnumbertransactionnumber("");
        stocktransactionimport.setTransactionstatus(transactionstatus);
        stocktransactionimport.setItemtypecompanycode(Constants.COMPANY_CODE);
        stocktransactionimport.setItemtypecode(balance.getItemtypecode());
        stocktransactionimport.setTransactiondate(new Date(System.currentTimeMillis()));
        stocktransactionimport.setTransactiontime(new Time(System.currentTimeMillis()));
        stocktransactionimport.setDetailtype(detailType);
        stocktransactionimport.setTemplatecompanycode(Constants.COMPANY_CODE);
        stocktransactionimport.setTemplatecode(templatecode);
        // stocktransactionimport.setStocktransactiontype("1");
        stocktransactionimport.setStocktransactiontype(stocktransactiontype);
        stocktransactionimport.setDecocompanycode(Constants.COMPANY_CODE);
        stocktransactionimport.setDecosubcode01(balance.getDecosubcode01());
        stocktransactionimport.setDecosubcode02(balance.getDecosubcode02());
        stocktransactionimport.setDecosubcode03(balance.getDecosubcode03());
        stocktransactionimport.setDecosubcode04(balance.getDecosubcode04());
        stocktransactionimport.setDecosubcode05(balance.getDecosubcode05());
        stocktransactionimport.setDecosubcode06(balance.getDecosubcode06());
        stocktransactionimport.setDecosubcode07(balance.getDecosubcode07());
        stocktransactionimport.setDecosubcode08(balance.getDecosubcode08());
        stocktransactionimport.setDecosubcode09(balance.getDecosubcode09());
        stocktransactionimport.setDecosubcode10(balance.getDecosubcode10());
        stocktransactionimport.setItemdescription("");
        stocktransactionimport.setLogicalwarehousecompanycode(Constants.COMPANY_CODE);
        stocktransactionimport.setLogicalwarehousecode(balance.getLogicalwarehousecode());
        stocktransactionimport.setTransferallocation("");
        stocktransactionimport.setIssueqtyfrombalance((short) 0);
        stocktransactionimport.setUserprimaryuomcode(balance.getBaseprimaryunitcode());
        stocktransactionimport.setUserprimaryquantity(balance.getBaseprimaryquantityunit());
        stocktransactionimport.setUsersecondaryuomcode(balance.getBasesecondaryunitcode());
        stocktransactionimport.setUsersecondaryquantity(balance.getBasesecondaryquantityunit());
        stocktransactionimport.setUserpackaginguomcode(balance.getPackagingcode());
        stocktransactionimport.setUserpackagingquantity(balance.getPackagingquantityunit());
        stocktransactionimport.setWeightunitofmeasurecode("");
        stocktransactionimport.setWeightgross(new BigDecimal("0"));
        stocktransactionimport.setWeightnet(new BigDecimal("0"));
        stocktransactionimport.setWeightrealnet(new BigDecimal("0"));
        stocktransactionimport.setDerivationcode(null);
        stocktransactionimport.setDerivationlinenumber(0);
        stocktransactionimport.setDerivationcomponentlinenumber(0);
        stocktransactionimport.setQualitylvlitemtypecompanycode(Constants.COMPANY_CODE);
        stocktransactionimport.setQualitylevelcode(balance.getQualitylevelcode());
        stocktransactionimport.setQualityreasoncompanycode(Constants.COMPANY_CODE);
        stocktransactionimport.setQualityreasoncode(null);
        stocktransactionimport.setFirstqualitycontroldate(null);
        stocktransactionimport.setFirstqualitycontrolcounter(null);
        stocktransactionimport.setFirstqualitycontrolnumber(null);
        stocktransactionimport.setPhysicalwarehousecompanycode(Constants.COMPANY_CODE);
        stocktransactionimport.setPhysicalwarehousecode(balance.getPhysicalwarehousecode());
        stocktransactionimport.setWhslocwhszonephywhscmycode(Constants.COMPANY_CODE);
        stocktransactionimport.setWhslocationwarehousezonecode(balance.getWhslocationwarehousezonecode());
        stocktransactionimport.setWarehouselocationcode(balance.getWarehouselocationcode());
        stocktransactionimport.setContainercompanycode(Constants.COMPANY_CODE);
        stocktransactionimport.setContaineritemtypecode(balance.getContaineritemtypecode());
        stocktransactionimport.setContainersubcode01(balance.getContainersubcode01());
        stocktransactionimport.setContainerelementcompanycode(Constants.COMPANY_CODE);
        stocktransactionimport.setContainerelementcode(balance.getContainerelementcode());
        stocktransactionimport.setLotcompanycode(Constants.COMPANY_CODE);
        stocktransactionimport.setLotcode(balance.getLotcode());
        stocktransactionimport.setItemelementcompanycode(Constants.COMPANY_CODE);
        stocktransactionimport.setItemelementsubcodekey(balance.getElementssubcodekey());
        stocktransactionimport.setItemelementcode(elementcode);
        stocktransactionimport.setProjectcode(balance.getProjectcode());
        stocktransactionimport.setCostcentercompanycode(Constants.COMPANY_CODE);
        stocktransactionimport.setCostcentercode("");
        stocktransactionimport.setStatisticalgroupcompanycode(Constants.COMPANY_CODE);
        stocktransactionimport.setStatisticalgroupcode(balance.getStatisticalgroupcode());
        stocktransactionimport.setCost(costValue);
        stocktransactionimport.setCurrencycode(currencyCode);
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
        stocktransactionimport.setOrdercountercompanycode(Constants.COMPANY_CODE);
        stocktransactionimport.setOrdercountercode(null);
        stocktransactionimport.setOrdercode(null);
        stocktransactionimport.setOrderline(null);
        stocktransactionimport.setOrdersubline(null);
        stocktransactionimport.setOrdercomponentline(null);
        stocktransactionimport.setOrderdeliveryline(null);
        stocktransactionimport.setProductionordercode(null);
        stocktransactionimport.setReturncode(null);
        stocktransactionimport.setReturnline(null);
        stocktransactionimport.setTokencode(null);
        stocktransactionimport.setAduniqueid(parentAdditionId);
        stocktransactionimport.setAduniqueidforautoissue(parentAdditionId);
        stocktransactionimport.setAbsuniqueid(0L);
        stocktransactionimport.setCreationdatetime(Timestamp.from(Instant.now()));
        stocktransactionimport.setCreationuser("system");
        stocktransactionimport.setLastupdatedatetime(null);
        stocktransactionimport.setLastupdateuser(null);

        return stocktransactionimport;
    }

    public static Stocktransactionimport to(String companycode, CutPlanEntryDetailsBean balance, Long stockTransactionId, Integer lineNo, String templatecode, String detailType, String productionordercountercode, String productionordercode, Long groupline, Long parentAdditionId, String costcentercode, String elementscode) {
        Instant s = Instant.now();
        LocalDateTime ldt = LocalDateTime.ofInstant(s, ZoneId.of("Asia/Kolkata"));
        Stocktransactionimport stocktransactionimport = new Stocktransactionimport();
        stocktransactionimport.setId(new StocktransactionimportId(companycode, stockTransactionId.toString(), lineNo));
        stocktransactionimport.setImportstatus(0);
        stocktransactionimport.setNowtrnnumbertransactionnumber("");
        stocktransactionimport.setTransactionstatus("0");
        stocktransactionimport.setItemtypecompanycode(companycode);
        stocktransactionimport.setItemtypecode(balance.getItemtypecode());
        stocktransactionimport.setTransactiondate(new Date(java.util.Date.from(ldt.atZone(ZoneId.of("Asia/Kolkata")).toInstant()).getTime()));
        stocktransactionimport.setTransactiontime(new Time(java.util.Date.from(ldt.atZone(ZoneId.of("Asia/Kolkata")).toInstant()).getTime()));
        stocktransactionimport.setDetailtype(detailType);
        stocktransactionimport.setTemplatecompanycode(companycode);
        stocktransactionimport.setTemplatecode(templatecode);
        stocktransactionimport.setAutoissuetemplatecompanycode("");
        stocktransactionimport.setAutoissuetemplatecode("");
        stocktransactionimport.setStocktransactiontype("1");
        stocktransactionimport.setDecocompanycode(companycode);
        stocktransactionimport.setDecosubcode01(balance.getDecosubcode01());
        stocktransactionimport.setDecosubcode02(balance.getDecosubcode02());
        stocktransactionimport.setDecosubcode03(balance.getDecosubcode03());
        stocktransactionimport.setDecosubcode04(balance.getDecosubcode04());
        stocktransactionimport.setDecosubcode05(balance.getDecosubcode05());
        stocktransactionimport.setDecosubcode06(balance.getDecosubcode06());
        stocktransactionimport.setDecosubcode07(balance.getDecosubcode07());
        stocktransactionimport.setDecosubcode08(balance.getDecosubcode08());
        stocktransactionimport.setDecosubcode09(balance.getDecosubcode09());
        stocktransactionimport.setDecosubcode10(balance.getDecosubcode10());
        stocktransactionimport.setItemdescription("");
        stocktransactionimport.setLogicalwarehousecompanycode(companycode);
        stocktransactionimport.setLogicalwarehousecode(balance.getLogicalwarehousecode());
        stocktransactionimport.setTransferallocation("");
        stocktransactionimport.setIssueqtyfrombalance((short) 0);
        stocktransactionimport.setUserprimaryuomcode(balance.getBaseprimaryunitcode());
        stocktransactionimport.setUserprimaryquantity(new BigDecimal(balance.getBaseprimaryquantityunit()));
        stocktransactionimport.setUsersecondaryuomcode(balance.getBasesecondaryunitcode());
        stocktransactionimport.setUsersecondaryquantity(new BigDecimal(balance.getBasesecondaryquantityunit()));
        stocktransactionimport.setUserpackaginguomcode(balance.getPackagingcode());
        stocktransactionimport.setUserpackagingquantity(new BigDecimal(balance.getPackagingquantityunit()));
        stocktransactionimport.setWeightunitofmeasurecode("");
        stocktransactionimport.setWeightgross(new BigDecimal("0"));
        stocktransactionimport.setWeightnet(new BigDecimal("0"));
        stocktransactionimport.setWeightrealnet(new BigDecimal("0"));
        /*  stocktransactionimport.setDerivationcode();
            stocktransactionimport.setDerivationlinenumber();
            stocktransactionimport.setDerivationcomponentlinenumber();*/

        stocktransactionimport.setQualitylvlitemtypecompanycode(companycode);
        stocktransactionimport.setQualitylevelcode(BigInteger.valueOf(balance.getQualitylevelcode().longValue()));
        /*  stocktransactionimport.setQualityreasoncompanycode();
            stocktransactionimport.setQualityreasoncode();
            stocktransactionimport.setFirstqualitycontroldate();
            stocktransactionimport.setFirstqualitycontrolcounter();
            stocktransactionimport.setFirstqualitycontrolnumber();*/
        stocktransactionimport.setPhysicalwarehousecompanycode(companycode);
        stocktransactionimport.setPhysicalwarehousecode(balance.getPhysicalwarehousecode());
        stocktransactionimport.setWhslocwhszonephywhscmycode(companycode);
        stocktransactionimport.setWhslocationwarehousezonecode(balance.getWhslocationwarehousezonecode());
        stocktransactionimport.setWarehouselocationcode(balance.getWarehouselocationcode());
        stocktransactionimport.setContainercompanycode(companycode);
        stocktransactionimport.setContaineritemtypecode("");
        stocktransactionimport.setContainersubcode01("");
        stocktransactionimport.setContainerelementcompanycode("");
        stocktransactionimport.setContainerelementcode("");
        stocktransactionimport.setLotcompanycode(companycode);
        stocktransactionimport.setLotcode(balance.getLotcode());
        stocktransactionimport.setItemelementcompanycode(companycode);
        stocktransactionimport.setItemelementsubcodekey("");
        stocktransactionimport.setItemelementcode(elementscode != null && elementscode.length() > 0 ? elementscode : balance.getElementscode());
        stocktransactionimport.setProjectcode(balance.getProjectcode());
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
        stocktransactionimport.setOrdercountercode(productionordercountercode);
        stocktransactionimport.setOrdercode(productionordercode);
        stocktransactionimport.setOrderline(groupline.intValue());
        stocktransactionimport.setOrdersubline(0);
        stocktransactionimport.setOrdercomponentline(0);
        stocktransactionimport.setOrderdeliveryline(0);
        stocktransactionimport.setProductionordercode(productionordercode);
        stocktransactionimport.setReturncode("");
        stocktransactionimport.setReturnline(0);
        stocktransactionimport.setTokencode("");
        stocktransactionimport.setEnvcodeskipblcexp("");
        stocktransactionimport.setAduniqueid(parentAdditionId);
        stocktransactionimport.setAduniqueidforautoissue(parentAdditionId);
        stocktransactionimport.setCreationdatetime(Timestamp.from(ldt.atZone(ZoneId.of("Asia/Kolkata")).toInstant()));
        stocktransactionimport.setCreationuser("system");
        stocktransactionimport.setLastupdatedatetime(null);
        stocktransactionimport.setLastupdateuser(null);
        stocktransactionimport.setAbsuniqueid(0L);
        stocktransactionimport.setCostcentercompanycode(companycode);
        // stocktransactionimport.setCostcentercode("151160");
        return stocktransactionimport;
    }
}
