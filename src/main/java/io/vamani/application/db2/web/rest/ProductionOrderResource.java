package io.vamani.application.db2.web.rest;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.validation.Valid;

import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.config.Constants;
import io.vamani.application.db2.domain.*;
import io.vamani.application.db2.model.*;
import io.vamani.application.db2.repository.*;
import io.vamani.application.domain.CutBundleEntry;
import io.vamani.application.domain.CutPlanBundle;
import io.vamani.application.domain.CutPlanProgressEntry;
import io.vamani.application.domain.MarkerMasterEntry;
import io.vamani.application.model.*;
import io.vamani.application.repository.*;
import io.vamani.application.web.rest.util.PaginationUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping("/api")
public class ProductionOrderResource {

    private final Logger log = LoggerFactory.getLogger(ProductionOrderResource.class);

    private static final String ENTITY_NAME = "prodcutionOrder";

    private final ProductionorderRepository productionorderRepository;

    @Autowired
    private ProductionreservationRepository productionreservationRepository;

    @Autowired
    private ProductiondemandRepository productiondemandRepository;

    @Autowired
    private MarkerMasterEntryRepository markerMasterEntryRepository;

    @Autowired
    private CutPlanEntryRepository cutPlanEntryRepository;

    @Autowired
    private CutBundleEntryRepository cutBundleEntryRepository;

    @Autowired
    private CutPlanBundleRepository cutPlanBundleRepository;

    @Autowired
    private CutPlanBundleDetailsRepository cutPlanBundleDetailsRepository;

    @Autowired
    private CutPlanEntryDetailsRepository cutPlanEntryDetailsRepository;

    @Autowired
    private SalesorderlineRepository salesorderlineRepository;

    @Autowired
    ApplicationProperties applicationProperties;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UsergenericgroupRepository usergenericgroupRepository;

    @Autowired
    private ProductiondemandstepRepository productiondemandstepRepository;

    @Autowired
    private PurchaseorderlineRepository purchaseorderlineRepository;

    public ProductionOrderResource(ProductionorderRepository productionorderRepository) {
        this.productionorderRepository = productionorderRepository;
    }

    @PostMapping("/db2-productionorder-project")
    @Timed
    public Master GetAllProjectByPo(@Valid @RequestBody Master search) {
        Master master = new Master();
        Productionorder productionorder = productionorderRepository.findById(new ProductionorderId(Constants.COMPANY_CODE, search.getName())).orElse(null);
        String[] demandSplitter = productionorder.getDemandlist().split(";");
        List<Productiondemand> productiondemands = productiondemandRepository.findByIds(Arrays.asList(demandSplitter));
        if (productiondemands != null && productiondemands.size() > 0) {
            Productiondemand productiondemand = productiondemands.get(0);
            master.setName(productiondemand.getProjectcode().trim());
            master.setDesc(productiondemand.getSubcode01().trim());
            master.setItemType(productiondemand.getSubcode03().trim());
            Usergenericgroup usergenericgroup = usergenericgroupRepository.findById(new UsergenericgroupId(Constants.COMPANY_CODE, "PRC", productiondemand.getSubcode03().trim())).orElse(null);
            if (usergenericgroup != null) {
                master.setItemName(usergenericgroup.getLongdescription());
            }
            List<Object[]> buyers = salesorderlineRepository.fetchBuyerByProject(productiondemand.getProjectcode().trim());
            if (buyers != null && buyers.size() > 0) {
                Object[] buyer = buyers.get(0);
                master.setBuyer(buyer[0].toString());
                master.setBuyerName(buyer[1].toString());
            }
        }
        return master;
    }

    @PostMapping("/db2-productiondemand-countries")
    @Timed
    public List<Master> GetAllCountryByStyle(@Valid @RequestBody Master search) {
        List<Master> master = new ArrayList<Master>();
        Productionorder productionorder = productionorderRepository.findById(new ProductionorderId(Constants.COMPANY_CODE, search.getName())).orElse(null);
        String[] demandSplitter = productionorder.getDemandlist().split(";");
        List<Object[]> objectList = productiondemandRepository.getAllCountryByStyle(search.getDesc().trim(), Arrays.asList(demandSplitter));
        for (Object obj : objectList) {
            Master bean = new Master();
            Object[] objects = (Object[]) obj;
            bean.setName(objects[0].toString());
            bean.setDesc(objects[1].toString());
            master.add(bean);
        }
        return master;
    }

    @PostMapping("/db2-productiondemand-colors")
    @Timed
    public List<Master> GetAllColorByCountry(@Valid @RequestBody Master search) {
        List<Master> master = new ArrayList<Master>();
        List<Object[]> objectList = null;
        Productionorder productionorder = productionorderRepository.findById(new ProductionorderId(Constants.COMPANY_CODE, search.getId())).orElse(null);
        if (productionorder != null && productionorder.getProductionordercountercode().trim().equalsIgnoreCase("SGPO")) {
            String[] demandSplitter = productionorder.getDemandlist().split(";");
            objectList = productiondemandRepository.getAllSewColorByStyle(search.getName(), Arrays.asList(demandSplitter));
        } else if (productionorder != null && productionorder.getProductionordercountercode().trim().equalsIgnoreCase("PKPO")) {
            String[] demandSplitter = productionorder.getDemandlist().split(";");
            objectList = productiondemandRepository.getAllSewColorByStyle(search.getName(), Arrays.asList(demandSplitter));
        } else {
            String[] demandSplitter = productionorder.getDemandlist().split(";");
            objectList = productiondemandRepository.getAllColorByStyle(search.getName(), Arrays.asList(demandSplitter));
        }
        for (Object obj : objectList) {
            Master bean = new Master();
            Object[] objects = (Object[]) obj;
            bean.setName(objects[0].toString().trim());
            bean.setDesc(objects[1].toString().trim());
            master.add(bean);
        }
        return master;
    }

    @PostMapping("/db2-productiondemand-quantities")
    @Timed
    public List<CutQuantity> getAllQuantityByColorAndCountry(@RequestBody BalanceSuggestionSearch search) {
        List<CutQuantity> cutQuantities = new ArrayList<>();
        Productionorder productionorder = productionorderRepository.findById(new ProductionorderId(Constants.COMPANY_CODE, search.getProductionorder())).orElse(null);
        String[] demandSplitter = productionorder.getDemandlist().split(";");
        List<Productiondemand> productiondemands = productiondemandRepository.findByIds(Arrays.asList(demandSplitter));
        if (productiondemands != null && productiondemands.size() > 0) {
            search.setProjectcode(productiondemands.get(0).getProjectcode());
        }
        DecimalFormat decimalFormat = new DecimalFormat("#");

        List<Object[]> sizes = null;
        if (productionorder != null && (productionorder.getProductionordercountercode().trim().equalsIgnoreCase("SGPO") || productionorder.getProductionordercountercode().trim().equalsIgnoreCase("PKPO"))) {
            sizes = productiondemandRepository.getAllSewQuantityByColorAndCountry(search.getStyle(), search.getColor(), Arrays.asList(demandSplitter));
        } else if (productionorder != null && (productionorder.getProductionordercountercode().trim().equalsIgnoreCase("HCPO") || productionorder.getProductionordercountercode().trim().equalsIgnoreCase("HSPO") || productionorder.getProductionordercountercode().trim().equalsIgnoreCase("HPPO"))) {
            sizes = productiondemandRepository.getAllHFQuantityByColorAndCountry(search.getStyle(), search.getColor(), Arrays.asList(demandSplitter));
        } else {
            sizes = productiondemandRepository.getAllQuantityByColorAndCountry(search.getStyle(), search.getColor(), Arrays.asList(demandSplitter));
        }
        BigDecimal tolerancePerc = new BigDecimal(0);//productiondemandRepository.getToleranceQuantity(search.getProjectcode());
        for (Object[] size : sizes) {
            if (size != null && size.length == 2) {
                CutQuantity cutQuantity = new CutQuantity();
                cutQuantity.setSizeCode(size[0].toString());
                cutQuantity.setOrderQuantity(new BigDecimal(size[1].toString()));
                cutQuantity.setToleranceQuantity(tolerancePerc);
                if (tolerancePerc != null && tolerancePerc.doubleValue() > 0) {
                    Double value = cutQuantity.getOrderQuantity().doubleValue() + ((cutQuantity.getOrderQuantity().doubleValue() * tolerancePerc.doubleValue()) / 100);
                    cutQuantity.setNetQuantity(new BigDecimal(decimalFormat.format(value)));
                } else {
                    cutQuantity.setNetQuantity(cutQuantity.getOrderQuantity());
                }
                cutQuantities.add(cutQuantity);
            }
        }
        return cutQuantities;
    }

    /**
     * GET  /menu-access-masters : get all the menuAccessMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of menuAccessMasters in body
     */
    @PostMapping("/productionorders")
    @com.codahale.metrics.annotation.Timed
    public ResponseEntity<List<Productionorder>> getAllProduction(@RequestBody ProductionorderSearch productionorderSearch) {
        log.debug("REST request to get a page of Workcenter");
        productionorderSearch.setPage(PageRequest.of(productionorderSearch.getPageNo(), productionorderSearch.getSize(), Sort.by("id.code").descending()));
        String prodordcode = "%";
        if (productionorderSearch.getCode() != null) {
            prodordcode = "%" + productionorderSearch.getCode().toUpperCase() + "%";
        }

        List<String> counters = new ArrayList<>();
        counters.add("CPPO");
        counters.add("HCPO");
        Page<Productionorder> page = productionorderRepository.findByCode(Constants.COMPANY_CODE, counters, prodordcode, productionorderSearch.getPage());

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/productionorders");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /menu-access-masters : get all the menuAccessMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of menuAccessMasters in body
     */
    @PostMapping("/productionorders-stitch")
    @com.codahale.metrics.annotation.Timed
    public ResponseEntity<List<Productionorder>> getAllProductionStitch(@RequestBody ProductionorderSearch productionorderSearch) {
        log.debug("REST request to get a page of Workcenter");
        productionorderSearch.setPage(PageRequest.of(productionorderSearch.getPageNo(), productionorderSearch.getSize(), Sort.by("id.code").descending()));
        String prodordcode = "%";
        if (productionorderSearch.getCode() != null) {
            prodordcode = "%" + productionorderSearch.getCode().toUpperCase() + "%";
        }
        List<String> counters = new ArrayList<>();
        counters.add("SGPO");
        counters.add("HSPO");
        Page<Productionorder> page = productionorderRepository.findByCode(Constants.COMPANY_CODE, counters, prodordcode, productionorderSearch.getPage());

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/productionorders-stitch");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /menu-access-masters : get all the menuAccessMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of menuAccessMasters in body
     */
    @PostMapping("/productionorders-packing")
    @com.codahale.metrics.annotation.Timed
    public ResponseEntity<List<Productionorder>> getAllProductionPacking(@RequestBody ProductionorderSearch productionorderSearch) {
        log.debug("REST request to get a page of Workcenter");
        productionorderSearch.setPage(PageRequest.of(productionorderSearch.getPageNo(), productionorderSearch.getSize(), Sort.by("id.code").descending()));
        String prodordcode = "%";
        if (productionorderSearch.getCode() != null) {
            prodordcode = "%" + productionorderSearch.getCode().toUpperCase() + "%";
        }
        List<String> counters = new ArrayList<>();
        counters.add("PKPO");
        counters.add("HPPO");
        Page<Productionorder> page = productionorderRepository.findByCode(Constants.COMPANY_CODE, counters, prodordcode, productionorderSearch.getPage());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/productionorders-packing");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /menu-access-masters : get all the menuAccessMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of menuAccessMasters in body
     */
    @PostMapping("/balance-suggestions")
    public ResponseEntity<List<MarkerBean>> getAllProduction(@RequestBody BalanceSuggestionSearch search) {
        log.debug("REST request to get a page of Workcenter");
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        List<MarkerMasterEntry> markerMasterEntries = markerMasterEntryRepository.getMarkerByStyleAndColors(
            search.getStyle().trim(), search.getColor().trim(), search.getSubcode01().trim(),
            search.getSubcode02().trim(), search.getSubcode03().trim(), search.getSubcode04().trim(),
            search.getSubcode05().trim(), search.getSubcode06().trim(), search.getSubcode07().trim(),
            search.getSubcode08().trim(), search.getSubcode09().trim(), search.getSubcode10().trim(), search.getPlantCode());

        List<String> rollDetails = null;
        if (search.getId() != null) {
            rollDetails = cutPlanEntryDetailsRepository.findAllElementsByItems(search.getSubcode01().trim(),
                search.getSubcode02().trim(), search.getSubcode03().trim(), search.getSubcode04().trim(),
                search.getSubcode05().trim(), search.getSubcode06().trim(), search.getSubcode07().trim(),
                search.getSubcode08().trim(), search.getSubcode09().trim(), search.getSubcode10().trim(), search.getId());
        } else {
            rollDetails = cutPlanEntryDetailsRepository.findAllElementsByItems(search.getSubcode01().trim(),
                search.getSubcode02().trim(), search.getSubcode03().trim(), search.getSubcode04().trim(),
                search.getSubcode05().trim(), search.getSubcode06().trim(), search.getSubcode07().trim(),
                search.getSubcode08().trim(), search.getSubcode09().trim(), search.getSubcode10().trim());
        }


        Productionorder productionorder = productionorderRepository.findById(new ProductionorderId(Constants.COMPANY_CODE, search.getProductionorder())).orElse(null);
        String[] demandSplitter = productionorder.getDemandlist().split(";");

        List<Balance> balances = null;
        if (rollDetails != null && rollDetails.size() > 0) {
            balances = productionreservationRepository.findAllByProductionDemandAndRolls(search.getColor(), Arrays.asList(demandSplitter), search.getSubcode01(), search.getSubcode02(), search.getSubcode03(), search.getSubcode04(), search.getSubcode05(), search.getSubcode06(), search.getSubcode07(), search.getSubcode08(), search.getSubcode09(), search.getSubcode10(), rollDetails, search.getPlantCode(), "%FCS%");
        } else {
            balances = productionreservationRepository.findAllByProductionDemand(search.getColor(), Arrays.asList(demandSplitter), search.getSubcode01(), search.getSubcode02(), search.getSubcode03(), search.getSubcode04(), search.getSubcode05(), search.getSubcode06(), search.getSubcode07(), search.getSubcode08(), search.getSubcode09(), search.getSubcode10(), search.getPlantCode(), "%FCS%");
        }

        Map<String, List<Balance>> lotMap = new HashMap<>();
        for (Balance balance : balances) {
            if (lotMap.containsKey(balance.getLotcode())) {
                List<Balance> list = lotMap.get(balance.getLotcode());
                if (balance.getBaseprimaryquantityunit().doubleValue() > 0) {
                    list.add(balance);
                    lotMap.put(balance.getLotcode(), list);
                }
            } else {
                List<Balance> list = new ArrayList<>();
                if (balance.getBaseprimaryquantityunit().doubleValue() > 0) {
                    list.add(balance);
                    lotMap.put(balance.getLotcode(), list);
                }
            }
        }

        List<MarkerBean> markerBeans = new ArrayList<>();
        for (MarkerMasterEntry entry : markerMasterEntries) {
            for (String key : lotMap.keySet()) {
                MarkerBean bean = new MarkerBean();
                bean.setMarkerId(entry.getId());
                bean.setMarkercode(entry.getMarkerCode());
                bean.setLotcode(key);
                bean.setNoRolls(Long.parseLong(lotMap.get(key).size() + ""));
                bean.setMarkerLength(entry.getLength());

                List<Balance> balanceBeans = lotMap.get(key);
                List<BalanceBean> balanceBeansEndBit = new ArrayList<>();
                for (Balance balance : balanceBeans) {
                    BalanceBean balanceBean = new BalanceBean();
                    BeanUtils.copyProperties(balance, balanceBean);
                    Long noPlies1 = new BigDecimal(balanceBean.getBaseprimaryquantityunit().doubleValue() / entry.getLength().doubleValue()).longValue();
                    Double endBits = (balanceBean.getBaseprimaryquantityunit().doubleValue() - (noPlies1 * entry.getLength().doubleValue()));
                    balanceBean.setNoPlies(noPlies1);
                    balanceBean.setEndBits(endBits);
                    balanceBeansEndBit.add(balanceBean);
                }
                if (balanceBeansEndBit != null && balanceBeansEndBit.size() > 0) {
                    Collections.sort(balanceBeansEndBit, Comparator.comparing(BalanceBean::getEndBits).thenComparing(Comparator.comparing(BalanceBean::getBaseprimaryquantityunit).reversed()));
                }
                bean.setBalances(balanceBeansEndBit);

                Long noPlies = search.getNoPlies();
                Long cuttedPlies = 0L;
                Double endBits = 0.0;
                Double selectedQuantity = 0.0;
                List<BalanceBean> balanceBeansFinal = new ArrayList<>();
                for (BalanceBean balanceBean : balanceBeansEndBit) {
                    if (noPlies > 0 && noPlies >= balanceBean.getNoPlies()) {
                        balanceBean.setAllowPlies(true);
                        noPlies = noPlies - balanceBean.getNoPlies();
                        cuttedPlies += balanceBean.getNoPlies();
                        endBits += balanceBean.getEndBits();
                        selectedQuantity += balanceBean.getBaseprimaryquantityunit().doubleValue();
                    } else {
                        balanceBean.setAllowPlies(false);
                    }
                    balanceBeansFinal.add(balanceBean);
                }
                if (balanceBeansFinal != null && balanceBeansFinal.size() > 0) {
                    Collections.sort(balanceBeansFinal, Comparator.comparing(BalanceBean::getAllowPlies).reversed().thenComparing(Comparator.comparing(BalanceBean::getEndBits).thenComparing(Comparator.comparing(BalanceBean::getBaseprimaryquantityunit).reversed())));
                }
                bean.setSelecedquantity(new BigDecimal(selectedQuantity));
                bean.setNoPlies(cuttedPlies);
                bean.setEndBits(new Double(decimalFormat.format(endBits)));
                bean.setBalances(balanceBeansFinal);
                markerBeans.add(bean);
            }
        }

        if (markerBeans != null && markerBeans.size() > 0) {
            Collections.sort(markerBeans, Comparator.comparing(MarkerBean::getEndBits));
            markerBeans.get(0).setSuggested(true);
            markerBeans.get(0).setHighlight(true);
        }
        return ResponseEntity.ok().body(markerBeans);
    }

    /**
     * GET  /menu-access-masters : get all the menuAccessMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of menuAccessMasters in body
     */
    @PostMapping("/balance-suggestions-marker")
    public ResponseEntity<List<MarkerBean>> getAllProductionByMarker(@RequestBody BalanceSuggestionSearch search) {
        log.debug("REST request to get a page of Workcenter");
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        MarkerMasterEntry markerMasterEntry = markerMasterEntryRepository.findById(search.getMarkerMasterEntryId()).orElse(null);

        List<String> rollDetails = null;
        if (search.getId() != null) {
            rollDetails = cutPlanEntryDetailsRepository.findAllElementsByItems(search.getSubcode01().trim(),
                search.getSubcode02().trim(), search.getSubcode03().trim(), search.getSubcode04().trim(),
                search.getSubcode05().trim(), search.getSubcode06().trim(), search.getSubcode07().trim(),
                search.getSubcode08().trim(), search.getSubcode09().trim(), search.getSubcode10().trim(), search.getId());
        } else {
            rollDetails = cutPlanEntryDetailsRepository.findAllElementsByItems(search.getSubcode01().trim(),
                search.getSubcode02().trim(), search.getSubcode03().trim(), search.getSubcode04().trim(),
                search.getSubcode05().trim(), search.getSubcode06().trim(), search.getSubcode07().trim(),
                search.getSubcode08().trim(), search.getSubcode09().trim(), search.getSubcode10().trim());
        }


        Productionorder productionorder = productionorderRepository.findById(new ProductionorderId(Constants.COMPANY_CODE, search.getProductionorder())).orElse(null);
        String[] demandSplitter = productionorder.getDemandlist().split(";");

        List<Balance> balances = null;
        if(productionorder.getId().getCode().startsWith("CP")) {
            if (rollDetails != null && rollDetails.size() > 0) {
                balances = productionreservationRepository.findAllByProductionDemandAndRolls(search.getColor(), Arrays.asList(demandSplitter), search.getSubcode01(), search.getSubcode02(), search.getSubcode03(), search.getSubcode04(), search.getSubcode05(), search.getSubcode06(), search.getSubcode07(), search.getSubcode08(), search.getSubcode09(), search.getSubcode10(), rollDetails, search.getPlantCode(), "%FCS%");
            } else {
                balances = productionreservationRepository.findAllByProductionDemand(search.getColor(), Arrays.asList(demandSplitter), search.getSubcode01(), search.getSubcode02(), search.getSubcode03(), search.getSubcode04(), search.getSubcode05(), search.getSubcode06(), search.getSubcode07(), search.getSubcode08(), search.getSubcode09(), search.getSubcode10(), search.getPlantCode(), "%FCS%");
            }
        } else {
            if (rollDetails != null && rollDetails.size() > 0) {
                balances = productionreservationRepository.findAllHFDByProductionDemandAndRolls(search.getColor(), Arrays.asList(demandSplitter), search.getSubcode01(), search.getSubcode02(), search.getSubcode03(), search.getSubcode04(), search.getSubcode05(), search.getSubcode06(), search.getSubcode07(), search.getSubcode08(), search.getSubcode09(), search.getSubcode10(), rollDetails, search.getPlantCode(), "%FCS%");
            } else {
                balances = productionreservationRepository.findAllHFDByProductionDemand(search.getColor(), Arrays.asList(demandSplitter), search.getSubcode01(), search.getSubcode02(), search.getSubcode03(), search.getSubcode04(), search.getSubcode05(), search.getSubcode06(), search.getSubcode07(), search.getSubcode08(), search.getSubcode09(), search.getSubcode10(), search.getPlantCode(), "%FCS%");
            }
        }

        Map<String, List<Balance>> lotMap = new HashMap<>();
        for (Balance balance : balances) {
            if (lotMap.containsKey(balance.getLotcode())) {
                List<Balance> list = lotMap.get(balance.getLotcode());
                if (balance.getBaseprimaryquantityunit().doubleValue() > 0) {
                    list.add(balance);
                    lotMap.put(balance.getLotcode(), list);
                }
            } else {
                List<Balance> list = new ArrayList<>();
                if (balance.getBaseprimaryquantityunit().doubleValue() > 0) {
                    list.add(balance);
                    lotMap.put(balance.getLotcode(), list);
                }
            }
        }

        List<MarkerBean> markerBeans = new ArrayList<>();
        if (markerMasterEntry != null) {
            for (String key : lotMap.keySet()) {
                MarkerBean bean = new MarkerBean();
                bean.setMarkerId(markerMasterEntry.getId());
                bean.setMarkercode(markerMasterEntry.getMarkerCode());
                bean.setLotcode(key);
                bean.setNoRolls(Long.parseLong(lotMap.get(key).size() + ""));
                bean.setMarkerLength(markerMasterEntry.getLength());

                List<Balance> balanceBeans = lotMap.get(key);
                List<BalanceBean> balanceBeansEndBit = new ArrayList<>();
                for (Balance balance : balanceBeans) {
                    BalanceBean balanceBean = new BalanceBean();
                    BeanUtils.copyProperties(balance, balanceBean);
                    Long noPlies1 = new BigDecimal(balanceBean.getBaseprimaryquantityunit().doubleValue() / markerMasterEntry.getLength().doubleValue()).longValue();
                    Double endBits = (balanceBean.getBaseprimaryquantityunit().doubleValue() - (noPlies1 * markerMasterEntry.getLength().doubleValue()));
                    balanceBean.setNoPlies(noPlies1);
                    balanceBean.setEndBits(endBits);
                    balanceBeansEndBit.add(balanceBean);
                }
                if (balanceBeansEndBit != null && balanceBeansEndBit.size() > 0) {
                    Collections.sort(balanceBeansEndBit, Comparator.comparing(BalanceBean::getEndBits).thenComparing(Comparator.comparing(BalanceBean::getBaseprimaryquantityunit).reversed()));
                }
                bean.setBalances(balanceBeansEndBit);

                Long noPlies = search.getNoPlies();
                Long cuttedPlies = 0L;
                Double endBits = 0.0;
                Double selectedQuantity = 0.0;
                List<BalanceBean> balanceBeansFinal = new ArrayList<>();
                for (BalanceBean balanceBean : balanceBeansEndBit) {
                    if (noPlies > 0 && noPlies >= balanceBean.getNoPlies()) {
                        balanceBean.setAllowPlies(true);
                        noPlies = noPlies - balanceBean.getNoPlies();
                        cuttedPlies += balanceBean.getNoPlies();
                        endBits += balanceBean.getEndBits();
                        selectedQuantity += balanceBean.getBaseprimaryquantityunit().doubleValue();
                    } else {
                        balanceBean.setAllowPlies(false);
                    }
                    balanceBeansFinal.add(balanceBean);
                }
                if (balanceBeansFinal != null && balanceBeansFinal.size() > 0) {
                    Collections.sort(balanceBeansFinal, Comparator.comparing(BalanceBean::getAllowPlies).reversed().thenComparing(Comparator.comparing(BalanceBean::getEndBits).thenComparing(Comparator.comparing(BalanceBean::getBaseprimaryquantityunit).reversed())));
                }
                bean.setSelecedquantity(new BigDecimal(selectedQuantity));
                bean.setNoPlies(cuttedPlies);
                bean.setEndBits(new Double(decimalFormat.format(endBits)));
                bean.setBalances(balanceBeansFinal);
                markerBeans.add(bean);
            }
        }

        if (markerBeans != null && markerBeans.size() > 0) {
            Collections.sort(markerBeans, Comparator.comparing(MarkerBean::getEndBits));
            markerBeans.get(0).setSuggested(true);
            markerBeans.get(0).setHighlight(true);
        }
        return ResponseEntity.ok().body(markerBeans);
    }

    @PostMapping("/db2-productionreservation-itemcode")
    @Timed
    public List<FullitemkeydecoderBean> GetAllReservationItemByPo(@Valid @RequestBody Master search) {
        List<FullitemkeydecoderBean> fullitemkeydecoderBean = new ArrayList<FullitemkeydecoderBean>();
        List<Object[]> objectList = productionorderRepository.getAllReservationItemByPo(search.getId().trim(), search.getDesc().trim(), search.getCode().trim());
        for (Object obj : objectList) {
            FullitemkeydecoderBean bean = new FullitemkeydecoderBean();
            Object[] objects = (Object[]) obj;
            bean.setOrdersubcode01(objects[0].toString());
            bean.setOrdersubcode02(objects[1].toString());
            bean.setOrdersubcode03(objects[2].toString());
            bean.setOrdersubcode04(objects[3].toString());
            bean.setOrdersubcode05(objects[4].toString());
            bean.setOrdersubcode06(objects[5].toString());
            bean.setOrdersubcode07(objects[6].toString());
            bean.setOrdersubcode08(objects[7].toString());
            bean.setOrdersubcode09(objects[8].toString());
            bean.setOrdersubcode10(objects[9].toString());
            bean.setSubcode01Description(objects[10].toString());
            fullitemkeydecoderBean.add(bean);
        }
        return fullitemkeydecoderBean;
    }

    @PostMapping("/cut-plan-bundle-itemcode")
    @Timed
    public CutPlanBundleBean GetAllCutPlanBundleByPo(@Valid @RequestBody Master search) {
        CutPlanBundleBean cutPlanBundleBean = new CutPlanBundleBean();
        List<CutPlanBundleSizesBean> cutPlanBundleSizesBeans = new ArrayList<>();
        Productionorder productionorder = productionorderRepository.findByCode(Constants.COMPANY_CODE, search.getCode().trim());
        String[] demandSplitter = productionorder.getDemandlist().split(";");
        List<Productiondemand> productiondemands = null;
        if(search.getCode() != null && search.getCode().startsWith("CP")) {
            productiondemands = productiondemandRepository.findByIdsAndColor(Arrays.asList(demandSplitter), search.getDesc().trim());
        } else {
            productiondemands = productiondemandRepository.findByIdsAndColorForHFD(Arrays.asList(demandSplitter), search.getDesc().trim());
        }
        Map<String, List<Productiondemand>> productiondemandMap = new LinkedHashMap<>();
        List<String> demands = new ArrayList<>();
        if (productiondemands != null) {
            for (Productiondemand productiondemand : productiondemands) {
                demands.add(productiondemand.getId().getCode().trim());
                if(search.getCode() != null && search.getCode().startsWith("CP")) {
                    if (productiondemandMap.containsKey(productiondemand.getSubcode08().trim())) {
                        List<Productiondemand> list = productiondemandMap.get(productiondemand.getSubcode08().trim());
                        list.add(productiondemand);
                        productiondemandMap.put(productiondemand.getSubcode08().trim(), list);
                    } else {
                        List<Productiondemand> list = new ArrayList<>();
                        list.add(productiondemand);
                        productiondemandMap.put(productiondemand.getSubcode08().trim(), list);
                    }
                } else {
                    if (productiondemandMap.containsKey(productiondemand.getSubcode06().trim())) {
                        List<Productiondemand> list = productiondemandMap.get(productiondemand.getSubcode06().trim());
                        list.add(productiondemand);
                        productiondemandMap.put(productiondemand.getSubcode06().trim(), list);
                    } else {
                        List<Productiondemand> list = new ArrayList<>();
                        list.add(productiondemand);
                        productiondemandMap.put(productiondemand.getSubcode06().trim(), list);
                    }
                }
            }
        }
        for (String key : productiondemandMap.keySet()) {
            CutPlanBundleSizesBean cutPlanBundleSizesBean = new CutPlanBundleSizesBean();
            cutPlanBundleSizesBean.setSizeCode(key);
            cutPlanBundleSizesBeans.add(cutPlanBundleSizesBean);
        }
        List<CutPlanBundleDetailsBean> cutPlanBundleDetailsBeans = new ArrayList<>();

        List<CutPlanBundleMatrixBean> cutPlanBundleMatrixBeans = new ArrayList<>();
        Map<String, Double> sizeMinMap = new LinkedHashMap<>();
        List<Object[]> objects = productionorderRepository.getItemsByProductionorderAndProductionDemands(search.getCode().trim(), demands);
        if (objects != null) {
            for (Object[] object : objects) {
                CutPlanBundleDetailsBean cutPlanBundleDetailsBean = new CutPlanBundleDetailsBean();
                cutPlanBundleDetailsBean.setItemtypecode(object[0].toString().trim());
                cutPlanBundleDetailsBean.setSubcode01(object[1].toString().trim());
                cutPlanBundleDetailsBean.setSubcode02(object[2].toString().trim());
                cutPlanBundleDetailsBean.setSubcode03(object[3].toString().trim());
                cutPlanBundleDetailsBean.setSubcode04(object[4].toString().trim());
                cutPlanBundleDetailsBean.setSubcode05(object[5].toString().trim());
                cutPlanBundleDetailsBean.setSubcode06(object[6].toString().trim());
                cutPlanBundleDetailsBean.setSubcode07(object[7].toString().trim());
                cutPlanBundleDetailsBean.setSubcode08(object[8].toString().trim());
                cutPlanBundleDetailsBean.setSubcode09(object[9].toString().trim());
                cutPlanBundleDetailsBean.setSubcode10(object[10].toString().trim());
                String product = (object[1].toString().trim().length() > 0 ? object[1].toString().trim() : "")
                    + (object[2].toString().trim().length() > 0 ? " " + object[2].toString().trim() : "")
                    + (object[3].toString().trim().length() > 0 ? " " + object[3].toString().trim() : "")
                    + (object[4].toString().trim().length() > 0 ? " " + object[4].toString().trim() : "")
                    + (object[5].toString().trim().length() > 0 ? " " + object[5].toString().trim() : "")
                    + (object[6].toString().trim().length() > 0 ? " " + object[6].toString().trim() : "")
                    + (object[7].toString().trim().length() > 0 ? " " + object[7].toString().trim() : "")
                    + (object[8].toString().trim().length() > 0 ? " " + object[8].toString().trim() : "")
                    + (object[9].toString().trim().length() > 0 ? " " + object[9].toString().trim() : "")
                    + (object[10].toString().trim().length() > 0 ? " " + object[10].toString().trim() : "");
                cutPlanBundleDetailsBean.setSummerizeddescription(product);

                List<CutPlanBundleSizesBean> cutPlanBundleSizesBeansLine = new ArrayList<>();
                for (String key : productiondemandMap.keySet()) {
                    CutPlanBundleSizesBean cutPlanBundleSizesBean = new CutPlanBundleSizesBean();
                    cutPlanBundleSizesBean.setSizeCode(key);
                    Double quantity = 0.0;
                    if(search.getCode() != null && search.getCode().startsWith("CP")) {
                        quantity = cutPlanEntryRepository.sumPrimaryQuantityByPOAndSize(search.getPlantCode(), search.getCode().trim(), search.getDesc().trim(), key, object[1].toString().trim(), object[2].toString().trim(), object[3].toString().trim(), object[4].toString().trim(), object[5].toString().trim(), object[6].toString().trim(), object[7].toString().trim(), object[8].toString().trim(), object[9].toString().trim(), object[10].toString().trim(), search.getDestination());
                    } else {
                        quantity = cutPlanEntryRepository.sumHFDPrimaryQuantityByPOAndSize(search.getPlantCode(), search.getCode().trim(), search.getDesc().trim(), key, object[1].toString().trim(), object[2].toString().trim(), object[3].toString().trim(), object[4].toString().trim(), object[5].toString().trim(), object[6].toString().trim(), object[7].toString().trim(), object[8].toString().trim(), object[9].toString().trim(), object[10].toString().trim(), search.getDestination());
                    }
                    cutPlanBundleSizesBean.setQuantity(quantity);
                    cutPlanBundleSizesBeansLine.add(cutPlanBundleSizesBean);

                    if (sizeMinMap.containsKey(key)) {
                        Double quantityLine = sizeMinMap.get(key);
                        if (quantityLine < quantity) {
                            sizeMinMap.put(key, quantityLine);
                        } else {
                            sizeMinMap.put(key, quantity);
                        }
                    } else {
                        sizeMinMap.put(key, quantity);
                    }
                }
                cutPlanBundleDetailsBean.setCutPlanBundleSizesBeans(cutPlanBundleSizesBeansLine);
                cutPlanBundleDetailsBeans.add(cutPlanBundleDetailsBean);
            }
        }
        for (String key : sizeMinMap.keySet()) {
            List<CutPlanBundleMatrixBreakup> cutPlanBundleMatrixExistBreakup = new ArrayList<>();
            CutBundleEntry cutBundleEntry = cutBundleEntryRepository.findByDetails(productionorder.getId().getCode().trim(), search.getPlantCode(), search.getId(), search.getDesc(), search.getDestination(), key);


            List<Object[]> cutPlanBundles = null;
            if(search.getCode() != null && search.getCode().startsWith("CP")) {
                cutPlanBundles = cutPlanBundleRepository.findByNativeDetails(productionorder.getId().getCode().trim(), search.getPlantCode(), search.getId(), search.getDesc(), search.getDestination(), key);
            } else {
                cutPlanBundles = cutPlanBundleRepository.findByNativeHFDetails(productionorder.getId().getCode().trim(), search.getPlantCode(), search.getId(), search.getDesc(), search.getDestination(), key);
            }

            Double bundledQuantity = 0.0;
            for (Object[] object : cutPlanBundles) {
                CutPlanBundleMatrixBreakup cutPlanBundleMatrixBreakup = new CutPlanBundleMatrixBreakup();
                bundledQuantity += new Double(object[1].toString());
                cutPlanBundleMatrixBreakup.setBundle(object[0].toString());
                cutPlanBundleMatrixBreakup.setBundlePcs(new Double(object[1].toString()));
                cutPlanBundleMatrixBreakup.setStartSequence(cutPlanBundleDetailsRepository.findSequenceById(Long.parseLong(object[2].toString())));
                cutPlanBundleMatrixBreakup.setEndSequence(cutPlanBundleDetailsRepository.findSequenceById(Long.parseLong(object[3].toString())));
                cutPlanBundleMatrixExistBreakup.add(cutPlanBundleMatrixBreakup);
            }
            if (cutBundleEntry != null && (cutBundleEntry.getSaveFlag() == null || cutBundleEntry.getSaveFlag() != null && cutBundleEntry.getSaveFlag().booleanValue() == true)) {
                cutPlanBundleBean.setSaveFlag(cutBundleEntry.getSaveFlag());
            }
            CutPlanBundleMatrixBean cutPlanBundleMatrixBean = new CutPlanBundleMatrixBean();
            cutPlanBundleMatrixBean.setPorductionCounterCode(productionorder.getProductionordercountercode());
            cutPlanBundleMatrixBean.setProductionCode(productionorder.getId().getCode());
            cutPlanBundleMatrixBean.setPlantCode(search.getPlantCode());
            cutPlanBundleMatrixBean.setStyle(search.getId().trim());
            cutPlanBundleMatrixBean.setColor(search.getDesc().trim());
            cutPlanBundleMatrixBean.setDestination(search.getDestination().trim());
            cutPlanBundleMatrixBean.setDestinationDesc(search.getDestinationDesc());
            cutPlanBundleMatrixBean.setSizeCode(key);
            cutPlanBundleMatrixBean.setAllotedQty(sizeMinMap.get(key));
            cutPlanBundleMatrixBean.setBundledQty(bundledQuantity);
            cutPlanBundleMatrixBean.setBalanceQty(sizeMinMap.get(key) - bundledQuantity);
            if (cutBundleEntry != null) {
                cutPlanBundleMatrixBean.setId(cutBundleEntry.getId());
                cutPlanBundleMatrixBean.setBundlePcs(cutBundleEntry.getBundlePcs());
                cutPlanBundleMatrixBean.setBundleSize(cutBundleEntry.getBundleSize());
                cutPlanBundleMatrixBean.setDestination(cutBundleEntry.getDestination());
                cutPlanBundleMatrixBean.setDestinationDesc(cutBundleEntry.getDestinationDesc());
            }
            cutPlanBundleMatrixBean.setCutPlanBundleMatrixExistBreakups(cutPlanBundleMatrixExistBreakup);
            cutPlanBundleMatrixBeans.add(cutPlanBundleMatrixBean);
        }

        cutPlanBundleBean.setCutPlanBundleMatrixBeans(cutPlanBundleMatrixBeans);
        cutPlanBundleBean.setCutPlanBundleDetailsBeans(cutPlanBundleDetailsBeans);
        cutPlanBundleBean.setCutPlanBundleSizesBeans(cutPlanBundleSizesBeans);
        return cutPlanBundleBean;
    }

    @PostMapping("/cut-plan-bundle-print")
    @Timed
    public List<TreeViewItem> GetAllCutPlanBundleByPoPrint(@Valid @RequestBody Master search) {
        List<TreeViewItem> treeViewItems = new ArrayList<>();
        List<Object[]> cutPlanBundles = null;
        if (search.getCode().trim().startsWith("CP")) {
            if (search.getBtnType() != null && search.getBtnType().equalsIgnoreCase("B")) {
                cutPlanBundles = cutPlanBundleRepository.findByDetailsBundle(search.getCode().trim(), search.getPlantCode().trim(), search.getId().trim(), search.getDesc().trim(), search.getDestination());
            } else {
                cutPlanBundles = cutPlanBundleRepository.findByDetailsPiece(search.getCode().trim(), search.getPlantCode().trim(), search.getId().trim(), search.getDesc().trim(), search.getDestination());
            }
        } else {
            if (search.getBtnType() != null && search.getBtnType().equalsIgnoreCase("B")) {
                cutPlanBundles = cutPlanBundleRepository.findByHFCDetailsBundle(search.getCode().trim(), search.getPlantCode().trim(), search.getId().trim(), search.getDesc().trim(), search.getDestination());
            } else {
                cutPlanBundles = cutPlanBundleRepository.findByHFCDetailsPiece(search.getCode().trim(), search.getPlantCode().trim(), search.getId().trim(), search.getDesc().trim(), search.getDestination());
            }
        }

        Map<String, List<TreeViewItem>> listMap = new HashMap<>();
        for (Object[] object : cutPlanBundles) {
            if (listMap.containsKey(object[0].toString().trim())) {
                List<TreeViewItem> items = listMap.get(object[0].toString().trim());
                TreeViewItem treeViewItem = new TreeViewItem();
                treeViewItem.setText(object[2].toString());
                treeViewItem.setValue(Long.parseLong(object[1].toString()));
                treeViewItem.setChecked(false);
                items.add(treeViewItem);
                listMap.put(object[0].toString().trim(), items);
            } else {
                List<TreeViewItem> items = new ArrayList<>();
                TreeViewItem treeViewItem = new TreeViewItem();
                treeViewItem.setText(object[2].toString());
                treeViewItem.setValue(Long.parseLong(object[1].toString()));
                treeViewItem.setChecked(false);
                items.add(treeViewItem);
                listMap.put(object[0].toString().trim(), items);
            }
        }
        for (String key : listMap.keySet()) {
            TreeViewItem treeViewItem = new TreeViewItem();
            treeViewItem.setText(key);
            List<TreeViewItem> items = listMap.get(key);
            treeViewItem.setChildren(items);
            treeViewItems.add(treeViewItem);
        }
        return treeViewItems;
    }

    @PostMapping("/cut-plan-bundle-print-exist")
    @Timed
    public List<TreeViewItem> GetAllCutPlanBundleByPoPrintExist(@Valid @RequestBody Master search) {
        List<TreeViewItem> treeViewItems = new ArrayList<>();
        List<Object[]> cutPlanBundles = null;
        if (search.getCode().trim().startsWith("CP")) {
            if (search.getBtnType() != null && search.getBtnType().equalsIgnoreCase("B")) {
                cutPlanBundles = cutPlanBundleRepository.findByDetailsBundleExist(search.getCode().trim(), search.getPlantCode().trim(), search.getId().trim(), search.getDesc().trim(), search.getDestination());
            } else {
                cutPlanBundles = cutPlanBundleRepository.findByDetailsPieceExist(search.getCode().trim(), search.getPlantCode().trim(), search.getId().trim(), search.getDesc().trim(), search.getDestination());
            }
        } else {
            if (search.getBtnType() != null && search.getBtnType().equalsIgnoreCase("B")) {
                cutPlanBundles = cutPlanBundleRepository.findByHFCDetailsBundleExist(search.getCode().trim(), search.getPlantCode().trim(), search.getId().trim(), search.getDesc().trim(), search.getDestination());
            } else {
                cutPlanBundles = cutPlanBundleRepository.findByHFCDetailsPieceExist(search.getCode().trim(), search.getPlantCode().trim(), search.getId().trim(), search.getDesc().trim(), search.getDestination());
            }
        }

        Map<String, List<TreeViewItem>> listMap = new HashMap<>();
        for (Object[] object : cutPlanBundles) {
            if (listMap.containsKey(object[0].toString().trim())) {
                List<TreeViewItem> items = listMap.get(object[0].toString().trim());
                TreeViewItem treeViewItem = new TreeViewItem();
                treeViewItem.setText(object[2].toString());
                treeViewItem.setValue(Long.parseLong(object[1].toString()));
                treeViewItem.setChecked(false);
                items.add(treeViewItem);
                listMap.put(object[0].toString().trim(), items);
            } else {
                List<TreeViewItem> items = new ArrayList<>();
                TreeViewItem treeViewItem = new TreeViewItem();
                treeViewItem.setText(object[2].toString());
                treeViewItem.setValue(Long.parseLong(object[1].toString()));
                treeViewItem.setChecked(false);
                items.add(treeViewItem);
                listMap.put(object[0].toString().trim(), items);
            }
        }
        for (String key : listMap.keySet()) {
            TreeViewItem treeViewItem = new TreeViewItem();
            treeViewItem.setText(key);
            List<TreeViewItem> items = listMap.get(key);
            treeViewItem.setChildren(items);
            treeViewItems.add(treeViewItem);
        }
        return treeViewItems;
    }

    @PostMapping("/cut-plan-bundle-print-bundles")
    @Timed
    public @ResponseBody
    void downloadBundles(@RequestBody List<Long> values, HttpServletResponse response) throws JRException, IOException, ParseException, SQLException {
        String path = applicationProperties.getTemplatePath() + "jasper/";
        JasperDesign jasperDesign = JRXmlLoader.load(path + "/bundle_print.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String, Object> parameters = new HashMap<>();
        String query = "";
        if (values != null && values.size() > 0) {
            int i = 0;
            for (Long aLong : values) {
                if (i == 0) {
                    query += " and id in(" + aLong.longValue();
                    ++i;
                } else {
                    query += ", " + aLong.longValue();
                }
            }
            query += ")";
        }

        parameters.put("query", query);
        parameters.put("SUBREPORT_DIR", path);

        Connection conn = null;
        try {
            cutPlanBundleRepository.updatePrintFlag(values);
            conn = dataSource.getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
            response.setContentType("application/x-pdf");
            response.setHeader("Content-Disposition", "attachment; filename=bundle_print.pdf");
            final OutputStream outputStream = response.getOutputStream();

            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        } catch (SQLException se) {
            log.error(se.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @PostMapping("/cut-plan-bundle-print-pieces")
    @Timed
    public @ResponseBody
    void downloadPieces(@RequestBody List<Long> values, HttpServletResponse response) throws JRException, IOException, ParseException, SQLException {
        String path = applicationProperties.getTemplatePath() + "jasper/";
        JasperDesign jasperDesign = JRXmlLoader.load(path + "/bundle_piece_print.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String, Object> parameters = new HashMap<>();
        String query = "";
        if (values != null && values.size() > 0) {
            int i = 0;
            for (Long aLong : values) {
                if (i == 0) {
                    query += " and b.id in(" + aLong.longValue();
                    ++i;
                } else {
                    query += ", " + aLong.longValue();
                }
            }
            query += ")";
        }

        parameters.put("query", query);
        parameters.put("SUBREPORT_DIR", path);

        Connection conn = null;
        try {
            cutPlanBundleRepository.updatePiecePrintFlag(values);
            conn = dataSource.getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
            response.setContentType("application/x-pdf");
            response.setHeader("Content-Disposition", "attachment; filename=bundle_piece_print.pdf");

            final OutputStream outputStream = response.getOutputStream();

            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        } catch (SQLException se) {
            log.error(se.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @PostMapping("/db2-prodres-marker-itemcode")
    @Timed
    public List<FullitemkeydecoderBean> GetAllMarkerReservationItemByPo(@Valid @RequestBody Master search) {
        List<FullitemkeydecoderBean> fullitemkeydecoderBean = new ArrayList<FullitemkeydecoderBean>();
        List<Object[]> objectList = productionorderRepository.getAllReservationItemByDemand(search.getId(), search.getDesc());
        for (Object obj : objectList) {
            FullitemkeydecoderBean bean = new FullitemkeydecoderBean();
            Object[] objects = (Object[]) obj;
            bean.setOrdersubcode01(objects[0].toString());
            bean.setOrdersubcode02(objects[1].toString());
            bean.setOrdersubcode03(objects[2].toString());
            bean.setOrdersubcode04(objects[3].toString());
            bean.setOrdersubcode05(objects[4].toString());
            bean.setOrdersubcode06(objects[5].toString());
            bean.setOrdersubcode07(objects[6].toString());
            bean.setOrdersubcode08(objects[7].toString());
            bean.setOrdersubcode09(objects[8].toString());
            bean.setOrdersubcode10(objects[9].toString());
            bean.setSubcode01Description(objects[10].toString());
            bean.setSummarizeddescription(objects[10].toString());
            bean.setItemtypecompanycode(objects[11].toString());
            fullitemkeydecoderBean.add(bean);
        }
        return fullitemkeydecoderBean;
    }

    @PostMapping("/db2-sewing-operations")
    @Timed
    public List<SewingProgressEntryBean> getAllMarkerReservationItemByPo(@Valid @RequestBody Master search) {
        List<Object[]> operations = productiondemandstepRepository.findAllOperation(Constants.COMPANY_CODE, search.getCode());
        List<SewingProgressEntryBean> sewingProgressEntryBeans = new ArrayList<>();
        for (Object[] operation : operations) {
            SewingProgressEntryBean sewingProgressEntryBean = new SewingProgressEntryBean();
            /* if (cutPlanProgressEntries != null && cutPlanProgressEntries.size() > 0) {
                for (CutPlanProgressEntry cutPlanProgressEntry : cutPlanProgressEntries) {
                    if (cutPlanProgressEntry.getOperationCode() != null && operation[0].toString().trim().equalsIgnoreCase(cutPlanProgressEntry.getOperationCode().trim())) {
                        BeanUtils.copyProperties(cutPlanProgressEntry, cutPlanProgressEntryBean);
                    }
                }
            } */
            sewingProgressEntryBean.setOperationCode(operation[0].toString());
            sewingProgressEntryBean.setOperationDescription(operation[1].toString());
            sewingProgressEntryBean.setScannedBy("PCS");
            sewingProgressEntryBeans.add(sewingProgressEntryBean);
        }
        return sewingProgressEntryBeans;
    }
}
