package io.vamani.application.web.rest;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.domain.*;
import io.vamani.application.model.*;
import io.vamani.application.mssql.domain.EmployeeMatView;
import io.vamani.application.mssql.model.EmployeeViewBean;
import io.vamani.application.mssql.repository.EmployeeMatViewRepository;
import io.vamani.application.repository.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.commons.collections4.map.HashedMap;
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
import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.mssql.domain.EmployeeView;
import io.vamani.application.mssql.repository.EmployeeViewRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;

/**
 * REST controller for managing TdsDeclaration.
 */
@RestController
@RequestMapping("/api")
public class TdsDeclarationResource {

    private final Logger log = LoggerFactory.getLogger(TdsDeclarationResource.class);

    private static final String ENTITY_NAME = "tdsDeclaration";

    private final TdsDeclarationRepository tdsDeclarationRepository;

    @Autowired
    private TdsDeclarationBreakupRepository tdsDeclarationBreakupRepository;

    @Autowired
    private EmployeeViewRepository employeeViewRepository;

    @Autowired
    private EmployeeMatViewRepository employeeMatViewRepository;

    @Autowired
    private TdsGroupDetailsRepository tdsGroupDetailsRepository;

    @Autowired
    private TdsYearMasterRepository tdsYearMasterRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private PreviousEmploymentDetailsRepository previousEmploymentDetailsRepository;

    public TdsDeclarationResource(TdsDeclarationRepository tdsDeclarationRepository) {
        this.tdsDeclarationRepository = tdsDeclarationRepository;
    }

    /**
     * POST  /tds-declarations : Create a new tdsDeclaration.
     *
     * @param !tdsDeclaration the tdsDeclaration to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tdsDeclaration, or with status 400 (Bad Request) if the tdsDeclaration has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tds-declarations")
    @Timed
    public ResponseEntity<TdsDeclaration> createTdsDeclaration(@Valid @RequestBody TdsDeclarationBean tdsDeclarationBean) throws URISyntaxException {
        log.debug("REST request to save TdsDeclaration : {}", tdsDeclarationBean);
        String currentUser = SecurityUtils.getCurrentUserLogin().orElse(null);
        TdsDeclaration result=null;
        Integer year = null;
        if(tdsDeclarationBean.getGroupMasterBeans() != null && tdsDeclarationBean.getGroupMasterBeans().size()>0) {
            TdsGroupMasterBean tdsGroupMaster = tdsDeclarationBean.getGroupMasterBeans().get(0);
            year  = tdsGroupMaster.getYear();
        }
        List<TdsDeclaration> tdsDeclarationList = tdsDeclarationRepository.findAllByCardNo(tdsDeclarationBean.getCardNo(), year);
        List<TdsDeclarationBreakup> tdsDeclarationBreakupList = tdsDeclarationBreakupRepository.findAllByCardNo(tdsDeclarationBean.getCardNo(), year);
        if(tdsDeclarationList.size()!=0) {
            Map<Long, TdsDeclaration> groupMap = new HashMap<>();
            for (TdsDeclaration tdsDeclaration : tdsDeclarationList) {
                groupMap.put(tdsDeclaration.getTdsGroupDetails().getId(), tdsDeclaration);
            }
            Map<String, TdsDeclarationBreakup> groupBreakupMap = new HashMap<>();
            for (TdsDeclarationBreakup tdsDeclarationBreakup : tdsDeclarationBreakupList) {
                groupBreakupMap.put("C" + tdsDeclarationBreakup.getPreviousEmpDtlsId() + tdsDeclarationBreakup.getTdsGroupDetails().getId(), tdsDeclarationBreakup);
            }
            for (TdsGroupMasterBean tdsGroupMasterBean : tdsDeclarationBean.getGroupMasterBeans()) {
                for (TdsGroupDetailsBean tdsGroupDetailsBean : tdsGroupMasterBean.getTdsGroupDetailsBean()) {
                    TdsGroupDetails tdsGroupDetails = new TdsGroupDetails();
                    BeanUtils.copyProperties(tdsGroupDetailsBean, tdsGroupDetails);
                    TdsDeclaration tdsDeclaration = new TdsDeclaration();
                    if (groupMap.containsKey(tdsGroupDetails.getId().longValue())) {
                        BeanUtils.copyProperties(groupMap.get(tdsGroupDetails.getId().longValue()), tdsDeclaration);
                    }
                    tdsDeclaration.setMonthRent(tdsDeclarationBean.getMonthRent());
                    tdsDeclaration.setLandLoardName(tdsDeclarationBean.getLandLoardName());
                    tdsDeclaration.setLandLoardPanNo(tdsDeclarationBean.getLandLoardPanNo());
                    tdsDeclaration.setLandLoardAddress(tdsDeclarationBean.getLandLoardAddress());
                    tdsDeclaration.setIncentiveAmount(tdsDeclarationBean.getIncentiveAmount());
                    tdsDeclaration.setPreviousEmployerAmount(tdsDeclarationBean.getPreviousEmployerAmount());
                    tdsDeclaration.setPreviousEmployerTdsDeduction(tdsDeclarationBean.getPreviousEmployerTdsDeduction());
                    tdsDeclaration.setCardNo(tdsDeclarationBean.getCardNo());
                    tdsDeclaration.setAmount(tdsGroupDetailsBean.getAmount());
                    tdsDeclaration.setRegimeType(tdsDeclarationBean.getRegime() != null && tdsDeclarationBean.getRegime().booleanValue() == true ? "OLD" : "NEW");
                    tdsDeclaration.setLastUpdatedBy(currentUser);
                    tdsDeclaration.setLastUpdatedDate(Instant.now());
                    tdsDeclaration.setTdsGroupDetails(tdsGroupDetails);
                    result = tdsDeclarationRepository.save(tdsDeclaration);
                    for (TdsDeclarationBreakupBean tdsDeclarationBreakupBean : tdsGroupDetailsBean.getTdsDeclarationBreakupBeans()) {
                        TdsDeclarationBreakup tdsDeclarationBreakup = new TdsDeclarationBreakup();
                        if (groupBreakupMap.containsKey("C" + tdsDeclarationBreakupBean.getEmployerId() + tdsDeclarationBreakupBean.getGroupDetailId())) {
                            BeanUtils.copyProperties(groupBreakupMap.get("C" + tdsDeclarationBreakupBean.getEmployerId() + tdsDeclarationBreakupBean.getGroupDetailId()), tdsDeclarationBreakup);
                        }
                        tdsDeclarationBreakup.setTdsGroupDetails(tdsGroupDetails);
                        tdsDeclarationBreakup.setPreviousEmpDtlsId(tdsDeclarationBreakupBean.getEmployerId());
                        tdsDeclarationBreakup.setCardNo(tdsDeclarationBean.getCardNo());
                        tdsDeclarationBreakup.setAmount(tdsDeclarationBreakupBean.getAmount());
                        tdsDeclarationBreakup.setLastUpdatedBy(currentUser);
                        tdsDeclarationBreakup.setLastUpdatedDate(Instant.now());

                        tdsDeclarationBreakupRepository.save(tdsDeclarationBreakup);
                    }
                }
            }
        }else {
            for (TdsGroupMasterBean tdsGroupMasterBean : tdsDeclarationBean.getGroupMasterBeans()) {
                for (TdsGroupDetailsBean tdsGroupDetailsBean : tdsGroupMasterBean.getTdsGroupDetailsBean()) {
                    TdsDeclaration tdsDeclaration = new TdsDeclaration();
                    TdsGroupDetails tdsGroupDetails = new TdsGroupDetails();
                    BeanUtils.copyProperties(tdsGroupDetailsBean, tdsGroupDetails);
                    tdsDeclaration.setMonthRent(tdsDeclarationBean.getMonthRent());
                    tdsDeclaration.setLandLoardName(tdsDeclarationBean.getLandLoardName());
                    tdsDeclaration.setLandLoardPanNo(tdsDeclarationBean.getLandLoardPanNo());
                    tdsDeclaration.setLandLoardAddress(tdsDeclarationBean.getLandLoardAddress());
                    tdsDeclaration.setIncentiveAmount(tdsDeclarationBean.getIncentiveAmount());
                    tdsDeclaration.setPreviousEmployerAmount(tdsDeclarationBean.getPreviousEmployerAmount());
                    tdsDeclaration.setPreviousEmployerTdsDeduction(tdsDeclarationBean.getPreviousEmployerTdsDeduction());
                    tdsDeclaration.setTdsGroupDetails(tdsGroupDetails);
                    tdsDeclaration.setCardNo(tdsDeclarationBean.getCardNo());
                    tdsDeclaration.setAmount(tdsGroupDetailsBean.getAmount());
                    tdsDeclaration.setRegimeType(tdsDeclarationBean.getRegime() != null && tdsDeclarationBean.getRegime().booleanValue() == true ? "OLD" : "NEW");
                    tdsDeclaration.setCreatedBy(currentUser);
                    tdsDeclaration.setCreatedDate(Instant.now());

                    result = tdsDeclarationRepository.save(tdsDeclaration);

                    for (TdsDeclarationBreakupBean tdsDeclarationBreakupBean : tdsGroupDetailsBean.getTdsDeclarationBreakupBeans()) {
                        TdsDeclarationBreakup tdsDeclarationBreakup = new TdsDeclarationBreakup();
                        tdsDeclarationBreakup.setCardNo(tdsDeclarationBean.getCardNo());
                        tdsDeclarationBreakup.setAmount(tdsDeclarationBreakupBean.getAmount());
                        tdsDeclarationBreakup.setPreviousEmpDtlsId(tdsDeclarationBreakupBean.getEmployerId());
                        tdsDeclarationBreakup.setTdsGroupDetails(tdsGroupDetails);
                        tdsDeclarationBreakup.setCreatedBy(currentUser);
                        tdsDeclarationBreakup.setCreatedDate(Instant.now());

                        tdsDeclarationBreakupRepository.save(tdsDeclarationBreakup);
                    }
                }
            }
        }
        return ResponseEntity.created(new URI("/api/tds-declarations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tds-declarations : Updates an existing tdsDeclaration.
     *
     * @param tdsDeclaration the tdsDeclaration to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tdsDeclaration,
     * or with status 400 (Bad Request) if the tdsDeclaration is not valid,
     * or with status 500 (Internal Server Error) if the tdsDeclaration couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tds-declarations")
    @Timed
    public ResponseEntity<TdsDeclaration> updateTdsDeclaration(@Valid @RequestBody TdsDeclaration tdsDeclaration) throws URISyntaxException {
        log.debug("REST request to update TdsDeclaration : {}", tdsDeclaration);
        if (tdsDeclaration.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TdsDeclaration result = tdsDeclarationRepository.save(tdsDeclaration);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tdsDeclaration.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tds-declarations : get all the tdsDeclarations.
     *
     * @param !pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tdsDeclarations in body
     */
    @PostMapping("/tds-declarations-qry")
    @Timed
    public ResponseEntity<List<EmployeeViewBean>> getAllTdsDeclarations(@Valid @RequestBody TdsDeclarationSearch search) {
        log.debug("REST request to get a page of TdsDeclarations");
        String userCardNo = "";
        String cardNo = "%";
        TdsYearMaster tdsYearMaster = tdsYearMasterRepository.findByCode(search.getYear());
        if (search.getCardNo() != null && search.getCardNo().length() > 0) {
            cardNo = "%" + search.getCardNo().trim() + "%";
        }
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("cardNo").ascending()));
        List<EmployeeMatView> employeeViewsTemp = null;
        List<EmployeeViewBean> employeeViews = new ArrayList<>();
        if (search.getStatus() != null && search.getStatus().equalsIgnoreCase("F")) {
            Page<String> page = tdsDeclarationRepository.findCardNo(cardNo, Integer.parseInt(search.getYear()), search.getPage());
            employeeViewsTemp = employeeViewRepository.findAllCardNo(new ArrayList<String>(page.getContent()));
            List<Object> tdsList = tdsDeclarationRepository.findCardNo(page.getContent(), Integer.parseInt(search.getYear()));

            Map<String, String> tdsMap = new HashMap<>();
            for (Object ob : tdsList) {
                Object[] tds = (Object[]) ob;
                tdsMap.put(tds[0].toString(), tds[1].toString());
            }

            if (tdsMap.keySet() != null && tdsMap.keySet().size() > 0) {
                for (EmployeeMatView employeeView : employeeViewsTemp) {
                    EmployeeViewBean employeeViewBean = new EmployeeViewBean();
                    BeanUtils.copyProperties(employeeView, employeeViewBean);
                    employeeViewBean.setTempLock(tdsMap.get(employeeViewBean.getCardNo()));
                    employeeViews.add(employeeViewBean);
                }
            }
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tds-declarations-qry");
            return ResponseEntity.ok().headers(headers).body(employeeViews);
        } else {
            List<String> cardNos = tdsDeclarationRepository.findByCardNo(Integer.parseInt(search.getYear()));
            Page<EmployeeMatView> employeeViewsPage = employeeViewRepository.findAllCardNoNotFill(cardNo, cardNos, tdsYearMaster.getSalarySlab(), search.getPage());
            for (EmployeeMatView employeeView : employeeViewsPage.getContent()) {
                EmployeeViewBean employeeViewBean = new EmployeeViewBean();
                BeanUtils.copyProperties(employeeView, employeeViewBean);
                if (tdsYearMaster.getTempLock()) {
                    employeeViewBean.setTempLock("Y");
                }
                employeeViews.add(employeeViewBean);
            }
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(employeeViewsPage, "/api/tds-declarations-qry");
            return ResponseEntity.ok().headers(headers).body(employeeViews);
        }
    }

    /**
     * GET  /tds-declarations : get all the tdsDeclarations.
     *
     * @param !pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tdsDeclarations in body
     */
    @PostMapping("/tds-declarations-qry-export")
    @Timed
    public @ResponseBody void getAllTdsDeclarationsExportXLSX(@Valid @RequestBody TdsDeclarationSearch search, HttpServletResponse response) throws JRException, IOException {
        log.debug("REST request to get a page of TdsDeclarations");
        String userCardNo = "";
        String cardNo = "%";
        TdsYearMaster tdsYearMaster = tdsYearMasterRepository.findByCode(search.getYear());
        if (search.getCardNo() != null && search.getCardNo().length() > 0) {
            cardNo = "%" + search.getCardNo().trim() + "%";
        }
        search.setPage(PageRequest.of(search.getPageNo(), 10000, Sort.by("cardNo").ascending()));
        List<EmployeeMatView> employeeViewsTemp = null;
        List<EmployeeViewBean> employeeViews = new ArrayList<>();
        String status = "";
        if (search.getStatus() != null && search.getStatus().equalsIgnoreCase("F")) {
            status = "Declaration Filled";
            Page<String> page = tdsDeclarationRepository.findCardNo(cardNo, Integer.parseInt(search.getYear()), search.getPage());
            employeeViewsTemp = employeeViewRepository.findAllCardNo(new ArrayList<String>(page.getContent()));
            List<Object> tdsList = tdsDeclarationRepository.findCardNo(page.getContent(), Integer.parseInt(search.getYear()));

            Map<String, String> tdsMap = new HashMap<>();
            for (Object ob : tdsList) {
                Object[] tds = (Object[]) ob;
                tdsMap.put(tds[0].toString(), tds[1].toString());
            }

            if (tdsMap.keySet() != null && tdsMap.keySet().size() > 0) {
                for (EmployeeMatView employeeView : employeeViewsTemp) {
                    EmployeeViewBean employeeViewBean = new EmployeeViewBean();
                    BeanUtils.copyProperties(employeeView, employeeViewBean);
                    employeeViewBean.setTempLock(tdsMap.get(employeeViewBean.getCardNo()));
                    employeeViews.add(employeeViewBean);
                }
            }
        } else {
            status = "Declaration Pending";
            List<String> cardNos = tdsDeclarationRepository.findByCardNo(Integer.parseInt(search.getYear()));
            Page<EmployeeMatView> employeeViewsPage = employeeViewRepository.findAllCardNoNotFillAndNotResign(cardNo, cardNos, tdsYearMaster.getSalarySlab(), search.getPage());
            for (EmployeeMatView employeeView : employeeViewsPage.getContent()) {
                EmployeeViewBean employeeViewBean = new EmployeeViewBean();
                BeanUtils.copyProperties(employeeView, employeeViewBean);
                if (tdsYearMaster.getTempLock()) {
                    employeeViewBean.setTempLock("Y");
                }
                employeeViews.add(employeeViewBean);
            }
        }
        String path = applicationProperties.getTemplatePath() + "jasper/";
        JasperDesign jasperDesign = JRXmlLoader.load(path + "/tds_query.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("STATUS", status);
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(employeeViews);

        parameters.put("datasource", jrDataSource);
        parameters.put("SUBREPORT_DIR", path);
        parameters.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=TDS.xlsx");

        final OutputStream outputStream = response.getOutputStream();
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
        exporter.exportReport();
    }

    @PostMapping("/tds-declarations-edit")
    @Timed
    public ResponseEntity<TdsDeclarationBean> getAllTdsDeclarationsEdit(@Valid @RequestBody TdsDeclarationSearch search) {
        log.debug("REST request to get a page of TdsDeclarations");
        List<TdsDeclaration> tdsDeclaration = tdsDeclarationRepository.findAllByCardNo(search.getCardNo(), Integer.parseInt(search.getYear()));
        EmployeeMatView employeeView = employeeMatViewRepository.findById(search.getCardNo()).orElse(null);
        TdsDeclarationBean tdsDeclarationBean = new TdsDeclarationBean();
        tdsDeclarationBean.setCardNo(employeeView.getCardNo());
        tdsDeclarationBean.setName(employeeView.getName());
        tdsDeclarationBean.setDesignation(employeeView.getDesCodeDesc());
        tdsDeclarationBean.setPanNo(employeeView.getPan());
        tdsDeclarationBean.setDateOfBirth(employeeView.getDob());
        tdsDeclarationBean.setContactNumber(employeeView.getPhone());
        tdsDeclarationBean.setEmailId(employeeView.getEmail());
        tdsDeclarationBean.setAddress(employeeView.getAdd1());
        if(tdsDeclaration.size()!=0) {
            for(TdsDeclaration tdsDeclarations:tdsDeclaration) {
                BeanUtils.copyProperties(tdsDeclarations, tdsDeclarationBean);
            }
        }
        String year = search.getYear();
        if (year != null && year.length() > 0) {
            List<TdsDeclarationBreakup> tdsDeclarationBreakups = tdsDeclarationBreakupRepository.findAllByCardNo(search.getCardNo(), Integer.parseInt(year));
            Map<String, Double> breakUpMap = new HashMap<>();
            for (TdsDeclarationBreakup tdsDeclarationBreakup : tdsDeclarationBreakups) {
                breakUpMap.put("C" + tdsDeclarationBreakup.getPreviousEmpDtlsId() + "D" + tdsDeclarationBreakup.getTdsGroupDetails().getId(), tdsDeclarationBreakup.getAmount());
            }
            Map<Long, Double> tdsValue = new HashMap<>();
            if (tdsDeclaration.size() > 0) {
                TdsDeclaration tdsDeclarations = tdsDeclaration.get(0);
                tdsDeclarationBean.setTempLock(tdsDeclarations.getTempLock());
                BeanUtils.copyProperties(tdsDeclarations, tdsDeclarationBean);
                tdsDeclarationBean.setRegime(tdsDeclarations.getRegimeType() != null && tdsDeclarations.getRegimeType().equalsIgnoreCase("OLD") ? true : false);

                for (TdsDeclaration declaration : tdsDeclaration) {
                    tdsValue.put(declaration.getTdsGroupDetails().getId(), declaration.getAmount());
                }
            }

            List<PreviousEmploymentDetailBean> previousEmploymentDetailBeans = new ArrayList<>();
            List<PreviousEmploymentDetails> previousEmploymentDetails = previousEmploymentDetailsRepository.findAllByCardNoAndYear(search.getCardNo(), year);
            if(previousEmploymentDetails != null && previousEmploymentDetails.size()>0) {
                for (PreviousEmploymentDetails previousEmploymentDetails1 : previousEmploymentDetails) {
                    PreviousEmploymentDetailBean previousEmploymentDetailBean = new PreviousEmploymentDetailBean();
                    previousEmploymentDetailBean.setId(previousEmploymentDetails1.getId());
                    previousEmploymentDetailBean.setEmployerName(previousEmploymentDetails1.getPreviousEmployer());
                    previousEmploymentDetailBeans.add(previousEmploymentDetailBean);
                }
                PreviousEmploymentDetailBean previousEmploymentDetailBean = new PreviousEmploymentDetailBean();
                previousEmploymentDetailBean.setId(0L);
                previousEmploymentDetailBean.setEmployerName("VAMANI");
                previousEmploymentDetailBeans.add(previousEmploymentDetailBean);
            } else {
                PreviousEmploymentDetailBean previousEmploymentDetailBean = new PreviousEmploymentDetailBean();
                previousEmploymentDetailBean.setId(0L);
                previousEmploymentDetailBean.setEmployerName("VAMANI");
                previousEmploymentDetailBeans.add(previousEmploymentDetailBean);
            }
            if (year != null && year.length() > 0) {
                List<TdsGroupDetails> tdsGroupDetails = tdsGroupDetailsRepository.findAllYear(Integer.parseInt(year));
                Map<Long, TdsGroupMaster> tdsGroupMasterMap = new HashedMap<>();
                for (TdsGroupDetails tdsGroupDetail : tdsGroupDetails) {
                    tdsGroupMasterMap.put(tdsGroupDetail.getTdsGroupMaster().getId().longValue(), tdsGroupDetail.getTdsGroupMaster());
                }
                tdsGroupMasterMap = new TreeMap<>(tdsGroupMasterMap);
                List<TdsGroupMasterBean> groupMasterBeans = new ArrayList<TdsGroupMasterBean>();
                for (Long key : tdsGroupMasterMap.keySet()) {
                    TdsGroupMasterBean tdsGroupMasterBean = new TdsGroupMasterBean();
                    TdsGroupMaster groupMaster = tdsGroupMasterMap.get(key);
                    BeanUtils.copyProperties(groupMaster, tdsGroupMasterBean);
                    List<TdsGroupDetailsBean> tdsGroupList = new ArrayList<TdsGroupDetailsBean>();
                    for (TdsGroupDetails tdsGroupDetail : tdsGroupDetails) {
                        if (key.longValue() == tdsGroupDetail.getTdsGroupMaster().getId().longValue()) {
                            TdsGroupDetailsBean bean = new TdsGroupDetailsBean();
                            BeanUtils.copyProperties(tdsGroupDetail, bean);
                            if (tdsValue.containsKey(tdsGroupDetail.getId())) {
                                bean.setAmount(tdsValue.get(tdsGroupDetail.getId()));
                            } else {
                                bean.setAmount(0.0);
                            }
                            List<TdsDeclarationBreakupBean> tdsDeclarationBreakupBeans = new ArrayList<>();
                            for(PreviousEmploymentDetailBean employmentDetails : previousEmploymentDetailBeans){
                                TdsDeclarationBreakupBean tdsDeclarationBreakupBean = new TdsDeclarationBreakupBean();
                                tdsDeclarationBreakupBean.setEmployerId(employmentDetails.getId());
                                tdsDeclarationBreakupBean.setGroupDetailId(tdsGroupDetail.getId());
                                if (breakUpMap.containsKey("C" + employmentDetails.getId() + "D" + tdsGroupDetail.getId())) {
                                    tdsDeclarationBreakupBean.setAmount(breakUpMap.get("C" + employmentDetails.getId() + "D" + tdsGroupDetail.getId()));
                                } else {
                                    tdsDeclarationBreakupBean.setAmount(0.0);
                                }
                                tdsDeclarationBreakupBeans.add(tdsDeclarationBreakupBean);
                            }
                            bean.setTdsDeclarationBreakupBeans(tdsDeclarationBreakupBeans);
                            tdsGroupList.add(bean);
                        }
                    }
                    tdsGroupMasterBean.setTdsGroupDetailsBean(tdsGroupList);
                    tdsGroupMasterBean.setPreviousEmploymentDetailBeans(previousEmploymentDetailBeans);
                    groupMasterBeans.add(tdsGroupMasterBean);
                }
                tdsDeclarationBean.setGroupMasterBeans(groupMasterBeans);
            }
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(tdsDeclarationBean));
    }

    /**
     * GET  /tds-declarations : get all the tdsDeclarations.
     *
     * @param !pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tdsDeclarations in body
     */
    @GetMapping("/tds-declarations-custom")
    @Timed
    public ResponseEntity<TdsDeclarationBean> getAllTdsDeclarations() {
        log.debug("REST request to get a page of TdsDeclarations");
        String cardNo = SecurityUtils.getCurrentUserLogin().orElse(null);
        List<TdsYearMaster> tdsYearMasters = tdsYearMasterRepository.findByActive();
        String year = null;
        boolean tempLock = false;
        if (tdsYearMasters != null && tdsYearMasters.size() > 0) {
            year = tdsYearMasters.get(0).getCode();
            tempLock = tdsYearMasters.get(0).getTempLock();
        }
        List<TdsDeclaration> tdsDeclaration = tdsDeclarationRepository.findAllByCardNo(cardNo, Integer.parseInt(year));
        List<TdsDeclarationBreakup> tdsDeclarationBreakups = tdsDeclarationBreakupRepository.findAllByCardNo(cardNo, Integer.parseInt(year));
        Map<String, Double> breakUpMap = new HashMap<>();
        for (TdsDeclarationBreakup tdsDeclarationBreakup : tdsDeclarationBreakups) {
            breakUpMap.put("C" + tdsDeclarationBreakup.getPreviousEmpDtlsId() + "D" + tdsDeclarationBreakup.getTdsGroupDetails().getId(), tdsDeclarationBreakup.getAmount());
        }
        EmployeeView employeeView = employeeViewRepository.findById(cardNo).orElse(null);
        TdsDeclarationBean tdsDeclarationBean = new TdsDeclarationBean();
        tdsDeclarationBean.setCardNo(employeeView.getCardNo());
        tdsDeclarationBean.setName(employeeView.getName());
        tdsDeclarationBean.setDesignation(employeeView.getDesCodeDesc());
        tdsDeclarationBean.setPanNo(employeeView.getPan());
        tdsDeclarationBean.setDateOfBirth(employeeView.getDob());
        tdsDeclarationBean.setContactNumber(employeeView.getPhone());
        tdsDeclarationBean.setEmailId(employeeView.getEmail());
        tdsDeclarationBean.setAddress(employeeView.getAdd1());
        if (tempLock) {
            tdsDeclarationBean.setTempLock("Y");
        }
        Map<Long, Double> tdsValue = new HashMap<>();
        if (tdsDeclaration.size() > 0) {
            TdsDeclaration tdsDeclarations = tdsDeclaration.get(0);
            tdsDeclarationBean.setTempLock(tdsDeclarations.getTempLock());
            BeanUtils.copyProperties(tdsDeclarations, tdsDeclarationBean);
            tdsDeclarationBean.setRegime(tdsDeclarations.getRegimeType() != null && tdsDeclarations.getRegimeType().equalsIgnoreCase("OLD") ? true : false);

            for (TdsDeclaration declaration : tdsDeclaration) {
                tdsValue.put(declaration.getTdsGroupDetails().getId(), declaration.getAmount());
            }
        }

        List<PreviousEmploymentDetailBean> previousEmploymentDetailBeans = new ArrayList<>();
        List<PreviousEmploymentDetails> previousEmploymentDetails = previousEmploymentDetailsRepository.findAllByCardNoAndYear(cardNo, year);
        if(previousEmploymentDetails != null && previousEmploymentDetails.size()>0) {
            for (PreviousEmploymentDetails previousEmploymentDetails1 : previousEmploymentDetails) {
                PreviousEmploymentDetailBean previousEmploymentDetailBean = new PreviousEmploymentDetailBean();
                previousEmploymentDetailBean.setId(previousEmploymentDetails1.getId());
                previousEmploymentDetailBean.setEmployerName(previousEmploymentDetails1.getPreviousEmployer());
                previousEmploymentDetailBeans.add(previousEmploymentDetailBean);
            }
            PreviousEmploymentDetailBean previousEmploymentDetailBean = new PreviousEmploymentDetailBean();
            previousEmploymentDetailBean.setId(0L);
            previousEmploymentDetailBean.setEmployerName("VAMANI");
            previousEmploymentDetailBeans.add(previousEmploymentDetailBean);
        } else {
            PreviousEmploymentDetailBean previousEmploymentDetailBean = new PreviousEmploymentDetailBean();
            previousEmploymentDetailBean.setId(0L);
            previousEmploymentDetailBean.setEmployerName("VAMANI");
            previousEmploymentDetailBeans.add(previousEmploymentDetailBean);
        }
        if (year != null && year.length() > 0) {
            List<TdsGroupDetails> tdsGroupDetails = tdsGroupDetailsRepository.findAllYear(Integer.parseInt(year));
            Map<Long, TdsGroupMaster> tdsGroupMasterMap = new HashedMap<>();
            for (TdsGroupDetails tdsGroupDetail : tdsGroupDetails) {
                tdsGroupMasterMap.put(tdsGroupDetail.getTdsGroupMaster().getId().longValue(), tdsGroupDetail.getTdsGroupMaster());
            }
            tdsGroupMasterMap = new TreeMap<>(tdsGroupMasterMap);
            List<TdsGroupMasterBean> groupMasterBeans = new ArrayList<TdsGroupMasterBean>();
            for (Long key : tdsGroupMasterMap.keySet()) {
                TdsGroupMasterBean tdsGroupMasterBean = new TdsGroupMasterBean();
                TdsGroupMaster groupMaster = tdsGroupMasterMap.get(key);
                BeanUtils.copyProperties(groupMaster, tdsGroupMasterBean);
                List<TdsGroupDetailsBean> tdsGroupList = new ArrayList<TdsGroupDetailsBean>();
                for (TdsGroupDetails tdsGroupDetail : tdsGroupDetails) {
                    if (key.longValue() == tdsGroupDetail.getTdsGroupMaster().getId().longValue()) {
                        TdsGroupDetailsBean bean = new TdsGroupDetailsBean();
                        BeanUtils.copyProperties(tdsGroupDetail, bean);
                        if (tdsValue.containsKey(tdsGroupDetail.getId())) {
                            bean.setAmount(tdsValue.get(tdsGroupDetail.getId()));
                        } else {
                            bean.setAmount(0.0);
                        }
                        List<TdsDeclarationBreakupBean> tdsDeclarationBreakupBeans = new ArrayList<>();
                        for(PreviousEmploymentDetailBean employmentDetails : previousEmploymentDetailBeans){
                            TdsDeclarationBreakupBean tdsDeclarationBreakupBean = new TdsDeclarationBreakupBean();
                            tdsDeclarationBreakupBean.setEmployerId(employmentDetails.getId());
                            tdsDeclarationBreakupBean.setGroupDetailId(tdsGroupDetail.getId());
                            if (breakUpMap.containsKey("C" + employmentDetails.getId() + "D" + tdsGroupDetail.getId())) {
                                tdsDeclarationBreakupBean.setAmount(breakUpMap.get("C" + employmentDetails.getId() + "D" + tdsGroupDetail.getId()));
                            } else {
                                tdsDeclarationBreakupBean.setAmount(0.0);
                            }
                            tdsDeclarationBreakupBeans.add(tdsDeclarationBreakupBean);
                        }
                        bean.setTdsDeclarationBreakupBeans(tdsDeclarationBreakupBeans);
                        tdsGroupList.add(bean);
                    }
                }
                tdsGroupMasterBean.setTdsGroupDetailsBean(tdsGroupList);
                tdsGroupMasterBean.setPreviousEmploymentDetailBeans(previousEmploymentDetailBeans);
                groupMasterBeans.add(tdsGroupMasterBean);
            }
            tdsDeclarationBean.setGroupMasterBeans(groupMasterBeans);
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(tdsDeclarationBean));
    }

    /**
     * GET  /tds-declarations/:id : get the "id" tdsDeclaration.
     *
     * @param id the id of the tdsDeclaration to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tdsDeclaration, or with status 404 (Not Found)
     */
    @GetMapping("/tds-declarations/{id}")
    @Timed
    public ResponseEntity<TdsDeclaration> getTdsDeclaration(@PathVariable Long id) {
        log.debug("REST request to get TdsDeclaration : {}", id);
        Optional<TdsDeclaration> tdsDeclaration = tdsDeclarationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tdsDeclaration);
    }

    /**
     * DELETE  /tds-declarations/:id : delete the "id" tdsDeclaration.
     *
     * @param id the id of the tdsDeclaration to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tds-declarations/{id}")
    @Timed
    public ResponseEntity<Void> deleteTdsDeclaration(@PathVariable Long id) {
        log.debug("REST request to delete TdsDeclaration : {}", id);

        tdsDeclarationRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/tds-declarations-lock-all/{year}")
    @Timed
    public ResponseEntity<Void> getAllTdsDeclarationsLockAll(@PathVariable String year) {
        List<Long> GroupIds = tdsGroupDetailsRepository.findAllIdsYear(Integer.parseInt(year));
        tdsDeclarationRepository.updateTempLock(Instant.now(), GroupIds);
        TdsYearMaster tdsYearMaster = tdsYearMasterRepository.findByCode(year);
        tdsYearMaster.setTempLock(true);
        tdsYearMasterRepository.save(tdsYearMaster);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, year)).build();
    }

    @GetMapping("/tds-declarations-unlock-all/{year}")
    @Timed
    public ResponseEntity<Void> getAllTdsDeclarationsUnLock(@PathVariable String year) {
        List<Long> GroupIds = tdsGroupDetailsRepository.findAllIdsYear(Integer.parseInt(year));
        tdsDeclarationRepository.updateUnTempLock(GroupIds);
        TdsYearMaster tdsYearMaster = tdsYearMasterRepository.findByCode(year);
        tdsYearMaster.setTempLock(false);
        tdsYearMasterRepository.save(tdsYearMaster);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, year)).build();
    }

    @PostMapping("/tds-declarations-single-lock")
    @Timed
    public ResponseEntity<TdsDeclarationBean> getAllTdsDeclarationsSingleLock(@Valid @RequestBody TdsDeclarationSearch search) {
        List<TdsDeclaration> tdsDeclarations = tdsDeclarationRepository.findAllByCardNo(search.getCardNo(), Integer.parseInt(search.getYear()));
        if (tdsDeclarations != null && tdsDeclarations.size() > 0) {
            for (TdsDeclaration tdsDeclaration : tdsDeclarations) {
                tdsDeclaration.setTempLock("Y");
                tdsDeclaration.setTempLockTime(Instant.now());
                tdsDeclarationRepository.save(tdsDeclaration);
            }
        } else {
            List<TdsGroupDetails> tdsGroupDetails = tdsGroupDetailsRepository.findAllYear(Integer.parseInt(search.getYear()));
            for (TdsGroupDetails tdsGroupDetail : tdsGroupDetails) {
                TdsDeclaration tdsDeclaration = new TdsDeclaration();
                tdsDeclaration.setCardNo(search.getCardNo());
                tdsDeclaration.setAmount(0.0);
                tdsDeclaration.setTempLock("Y");
                tdsDeclaration.setTempLockTime(Instant.now());
                tdsDeclaration.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                tdsDeclaration.setCreatedDate(Instant.now());
                tdsDeclaration.setTdsGroupDetails(tdsGroupDetail);
                tdsDeclarationRepository.save(tdsDeclaration);
            }
        }
        return getAllTdsDeclarationsEdit(search);
    }

    @PostMapping("/tds-declarations-single-unlock")
    @Timed
    public ResponseEntity<TdsDeclarationBean> getAllTdsDeclarationsSingleUnlock(@Valid @RequestBody TdsDeclarationSearch search) {
        List<TdsDeclaration> tdsDeclarations = tdsDeclarationRepository.findAllByCardNo(search.getCardNo(), Integer.parseInt(search.getYear()));
        if(tdsDeclarations != null && tdsDeclarations.size()>0) {
            for (TdsDeclaration tdsDeclaration : tdsDeclarations) {
                tdsDeclaration.setTempLock(null);
                tdsDeclaration.setTempLockTime(null);
                tdsDeclarationRepository.save(tdsDeclaration);
            }
        } else {
            List<TdsGroupDetails> tdsGroupDetails = tdsGroupDetailsRepository.findAllYear(Integer.parseInt(search.getYear()));
            for (TdsGroupDetails tdsGroupDetail : tdsGroupDetails) {
                TdsDeclaration tdsDeclaration = new TdsDeclaration();
                tdsDeclaration.setCardNo(search.getCardNo());
                tdsDeclaration.setAmount(0.0);
                tdsDeclaration.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                tdsDeclaration.setCreatedDate(Instant.now());
                tdsDeclaration.setTdsGroupDetails(tdsGroupDetail);
                tdsDeclarationRepository.save(tdsDeclaration);
            }
        }
        return getAllTdsDeclarationsEdit(search);
    }

    @PostMapping("/tds-declarations-export")
    @Timed
    public @ResponseBody void getAllTdsDeclarationsExport(@Valid @RequestBody TdsDeclarationSearch search, HttpServletResponse response) throws JRException, IOException {
        log.debug("REST request to get a page of TdsDeclarations");
        List<TdsDeclarationBean> tdsDeclarationBeans = new ArrayList<>();

        String year = search.getYear();
        TdsYearMaster tdsYearMaster = tdsYearMasterRepository.findByCode(year);
        List<TdsGroupDetails> tdsGroupDetails = tdsGroupDetailsRepository.findAllYear(Integer.parseInt(year));
        Map<Long, TdsGroupMaster> tdsGroupMasterMap = new HashedMap<>();
        for (TdsGroupDetails tdsGroupDetail : tdsGroupDetails) {
            tdsGroupMasterMap.put(tdsGroupDetail.getTdsGroupMaster().getId().longValue(), tdsGroupDetail.getTdsGroupMaster());
        }
        tdsGroupMasterMap = new TreeMap<>(tdsGroupMasterMap);

        String cardNo = "%";
        if (search.getCardNo() != null && search.getCardNo().length() > 0) {
            cardNo = "%" + search.getCardNo().trim() + "%";
        }
        List<EmployeeView> employeeViews = null;
        List<TdsDeclaration> tdsDeclarations = tdsDeclarationRepository.findAllByCardNoLike(cardNo, Integer.parseInt(search.getYear()));

        Map<String, ArrayList<TdsDeclaration>> cardNos = new HashMap<>();
        for (TdsDeclaration tdsDeclaration : tdsDeclarations) {
            if (cardNos.containsKey(tdsDeclaration.getCardNo())) {
                ArrayList<TdsDeclaration> list = cardNos.get(tdsDeclaration.getCardNo());
                list.add(tdsDeclaration);
                cardNos.put(tdsDeclaration.getCardNo(), list);
            } else {
                ArrayList<TdsDeclaration> list = new ArrayList();
                list.add(tdsDeclaration);
                cardNos.put(tdsDeclaration.getCardNo(), list);
            }
        }
        if (search.getStatus() != null && search.getStatus().equalsIgnoreCase("F")) {
            employeeViews = employeeViewRepository.findAllEmployeeCardNo(new ArrayList(cardNos.keySet()));
        } else {
            employeeViews = employeeViewRepository.findAllEmployeeCardNoNotIn(new ArrayList(cardNos.keySet()), tdsYearMaster.getSalarySlab());
        }
        for (EmployeeView employeeView : employeeViews) {
            TdsDeclarationBean tdsDeclarationBean = new TdsDeclarationBean();
            tdsDeclarationBean.setCardNo(employeeView.getCardNo());
            tdsDeclarationBean.setName(employeeView.getName());
            tdsDeclarationBean.setDesignation(employeeView.getDesCodeDesc());
            tdsDeclarationBean.setPanNo(employeeView.getPan());
            tdsDeclarationBean.setDateOfBirth(employeeView.getDob());
            tdsDeclarationBean.setContactNumber(employeeView.getPhone());
            tdsDeclarationBean.setEmailId(employeeView.getEmail());
            tdsDeclarationBean.setAddress(employeeView.getAdd1());

            List<TdsGroupMasterBean> groupMasterBeans = new ArrayList<TdsGroupMasterBean>();
            int ctr = 0;
            for (Long key : tdsGroupMasterMap.keySet()) {
                TdsGroupMasterBean tdsGroupMasterBean = new TdsGroupMasterBean();
                TdsGroupMaster groupMaster = tdsGroupMasterMap.get(key);
                BeanUtils.copyProperties(groupMaster, tdsGroupMasterBean);
                List<TdsGroupDetailsBean> tdsGroupList = new ArrayList<TdsGroupDetailsBean>();
                for (TdsGroupDetails tdsGroupDetail : tdsGroupDetails) {
                    if (key.longValue() == tdsGroupDetail.getTdsGroupMaster().getId().longValue()) {
                        TdsGroupDetailsBean bean = new TdsGroupDetailsBean();
                        BeanUtils.copyProperties(tdsGroupDetail, bean);
                        if (cardNos.containsKey(employeeView.getCardNo())) {
                            for (TdsDeclaration tdsDeclaration : cardNos.get(employeeView.getCardNo())) {
                                if (tdsDeclaration.getTdsGroupDetails().getId() == tdsGroupDetail.getId()) {
                                    if (ctr == 0) {
                                        ++ctr;
                                        BeanUtils.copyProperties(tdsDeclaration, tdsDeclarationBean);
                                    }
                                    bean.setAmount(tdsDeclaration.getAmount());
                                }
                            }
                        }
                        tdsGroupList.add(bean);
                    }
                }
                tdsGroupMasterBean.setTdsGroupDetailsBean(tdsGroupList);
                groupMasterBeans.add(tdsGroupMasterBean);
            }
            tdsDeclarationBean.setGroupMasterBeans(groupMasterBeans);
            tdsDeclarationBeans.add(tdsDeclarationBean);
        }
        String path = applicationProperties.getTemplatePath()+"jasper/";
        JasperDesign jasperDesign = JRXmlLoader.load(path+"/TDSDeclaration.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String,Object> parameters = new HashMap<>();
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(tdsDeclarationBeans);


        parameters.put("datasource", jrDataSource);
        parameters.put("SUBREPORT_DIR",path);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, jrDataSource);
        response.setContentType("application/x-pdf");
        response.setHeader("Content-Disposition","attachment; filename=TDSDeclaration.pdf");

        final OutputStream outputStream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint,outputStream);
    }
}
