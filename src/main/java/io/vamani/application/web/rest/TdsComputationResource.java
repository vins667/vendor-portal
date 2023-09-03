package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.domain.*;
import io.vamani.application.model.*;
import io.vamani.application.mssql.domain.*;
import io.vamani.application.mssql.model.EmployeeViewBean;
import io.vamani.application.mssql.repository.*;
import io.vamani.application.repository.*;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
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

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;

/**
 * REST controller for managing TdsComputation.
 */
@RestController
@RequestMapping("/api")
public class TdsComputationResource {

    private final Logger log = LoggerFactory.getLogger(TdsComputationResource.class);

    private static final String ENTITY_NAME = "tdsComputation";

    private final TdsComputationRepository tdsComputationRepository;

    @Autowired
    private EmployeeViewRepository employeeViewRepository;

    @Autowired
    private EmployeeMatViewRepository employeeMatViewRepository;

    @Autowired
    private TdsYearMasterRepository tdsYearMasterRepository;

    @Autowired
    private TdsDeclarationRepository tdsDeclarationRepository;

    @Autowired
    private TdsGroupDetailsRepository tdsGroupDetailsRepository;

    @Autowired
    private TdsSlabMasterRepository tdsSlabMasterRepository;

    @Autowired
    private MonthlyFinanceRepository monthlyFinanceRepository;

    @Autowired
    private SalaryRateRepository salaryRateRepository;

    @Autowired
    private PreviousEmploymentDetailsRepository previousEmploymentDetailsRepository;

    @Autowired
    private TdsDeclarationBreakupRepository tdsDeclarationBreakupRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private MonthlyFinanceRepository monthlyRepository;
    private final MonthlyDetailRepository monthlyDetailRepository;
    private final FactoryMasterRepository factoryMasterRepository;
    private final TdsGroupMasterRepository tdsGroupMasterRepository;

    public TdsComputationResource(TdsComputationRepository tdsComputationRepository,
                                  MonthlyDetailRepository monthlyDetailRepository,
                                  FactoryMasterRepository factoryMasterRepository,
                                  TdsGroupMasterRepository tdsGroupMasterRepository) {
        this.tdsComputationRepository = tdsComputationRepository;
        this.monthlyDetailRepository = monthlyDetailRepository;
        this.factoryMasterRepository = factoryMasterRepository;
        this.tdsGroupMasterRepository = tdsGroupMasterRepository;
    }

    /**
     * GET  /tds-computations : get all the tdsComputations.
     *
     * @param @pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tdsComputations in body
     */
    @GetMapping("/tds-computations")
    @Timed
    public ResponseEntity<TdsComputationBean> getAllTdsComputations() {
        log.debug("REST request to get a page of TdsComputations");
        String empCode = SecurityUtils.getCurrentUserLogin().orElse(null);
        //String empCode = "00007";
        TdsComputationBean tdsComputationBean = new TdsComputationBean();
        EmployeeView employeeView = employeeViewRepository.findByCardNo(empCode).orElse(null);
        if(employeeView != null) {
            tdsComputationBean.setCardNo(employeeView.getCardNo());
            tdsComputationBean.setName(employeeView.getName());
            tdsComputationBean.setDesignation(employeeView.getDesCodeDesc());
            tdsComputationBean.setPanNo(employeeView.getPan());
            tdsComputationBean.setDateOfBirth(employeeView.getDob());
            tdsComputationBean.setContactNumber(employeeView.getPhone());
            tdsComputationBean.setEmailId(employeeView.getEmail());
            tdsComputationBean.setAddress(employeeView.getAdd1());
        }
        List<TdsYearMaster> tdsYearMasters = tdsYearMasterRepository.findByActive();
        String year = "";
        String financeYearRange = "";
        TdsYearMaster tdsYearMaster = null;
        if(tdsYearMasters != null && tdsYearMasters.size()>0){
            year = tdsYearMasters.get(0).getCode();
            financeYearRange = tdsYearMasters.get(0).getFinanceYear();
            tdsYearMaster = tdsYearMasters.get(0);
        }
        List<TdsDeclaration> tdsDeclaration = tdsDeclarationRepository.findAllByCardNo(empCode, Integer.parseInt(year));
        TdsDeclaration tdsDeclarationChk = null;
        if (tdsDeclaration.size() > 0) {
            tdsDeclarationChk = tdsDeclaration.get(0);
            BeanUtils.copyProperties(tdsDeclarationChk, tdsComputationBean);
            if (tdsDeclarationChk.getTempLock() != null && tdsDeclarationChk.getTempLock().equalsIgnoreCase("Y")) {
                tdsComputationBean.setLocked(true);
            }
        }
        TdsComputation tdsComputation = tdsComputationRepository.findByCardNoAndYear(empCode, year);
        Map<Long, Double> deductionMap = new HashMap<Long, Double>();
        if (tdsComputation != null) {
            BeanUtils.copyProperties(tdsComputation, tdsComputationBean);
            double earnTotal = 0.0;
            if (tdsComputationBean.getEarnAmount1() != null && tdsComputationBean.getEarnAmount1().doubleValue() > 0) {
                earnTotal += tdsComputationBean.getEarnAmount1().doubleValue();
            }
            if (tdsComputationBean.getEarnAmount2() != null && tdsComputationBean.getEarnAmount2().doubleValue() > 0) {
                earnTotal += tdsComputationBean.getEarnAmount2().doubleValue();
            }
            if (tdsComputationBean.getEarnAmount3() != null && tdsComputationBean.getEarnAmount3().doubleValue() > 0) {
                earnTotal += tdsComputationBean.getEarnAmount3().doubleValue();
            }
            if (tdsComputationBean.getEarnAmount4() != null && tdsComputationBean.getEarnAmount4().doubleValue() > 0) {
                earnTotal += tdsComputationBean.getEarnAmount4().doubleValue();
            }
            if (tdsComputationBean.getEarnAmount5() != null && tdsComputationBean.getEarnAmount5().doubleValue() > 0) {
                earnTotal += tdsComputationBean.getEarnAmount5().doubleValue();
            }
            if (tdsComputationBean.getEarnAmount6() != null && tdsComputationBean.getEarnAmount6().doubleValue() > 0) {
                earnTotal += tdsComputationBean.getEarnAmount6().doubleValue();
            }
            if (tdsComputationBean.getEarnAmount7() != null && tdsComputationBean.getEarnAmount7().doubleValue() > 0) {
                earnTotal += tdsComputationBean.getEarnAmount7().doubleValue();
            }
            if (tdsComputationBean.getEarnAmount8() != null && tdsComputationBean.getEarnAmount8().doubleValue() > 0) {
                earnTotal += tdsComputationBean.getEarnAmount8().doubleValue();
            }
            if (tdsComputationBean.getEarnAmount9() != null && tdsComputationBean.getEarnAmount9().doubleValue() > 0) {
                earnTotal += tdsComputationBean.getEarnAmount9().doubleValue();
            }
            if (tdsComputationBean.getEarnAmount10() != null && tdsComputationBean.getEarnAmount10().doubleValue() > 0) {
                earnTotal += tdsComputationBean.getEarnAmount10().doubleValue();
            }

            tdsComputationBean.setTotalEarnAmount(earnTotal);

            tdsComputationBean.setFinancialYearRange(financeYearRange);
            deductionMap = convertInMap(tdsComputationBean);
        }

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
            if (deductionMap.containsKey(tdsGroupMasterBean.getId())) ;
            {
                tdsGroupMasterBean.setExemptAmount(deductionMap.get(tdsGroupMasterBean.getId()));
            }
            List<TdsGroupDetailsBean> tdsGroupList = new ArrayList<TdsGroupDetailsBean>();
            Double totalValue = 0.0;
            for (TdsGroupDetails tdsGroupDetail : tdsGroupDetails) {
                if (key.longValue() == tdsGroupDetail.getTdsGroupMaster().getId().longValue()) {
                    TdsGroupDetailsBean bean = new TdsGroupDetailsBean();
                    BeanUtils.copyProperties(tdsGroupDetail, bean);
                    if (tdsDeclaration.size() != 0) {
                        for (TdsDeclaration tdsDeclarations : tdsDeclaration) {
                            if (tdsDeclarations.getTdsGroupDetails().getId() == tdsGroupDetail.getId()) {
                                bean.setAmount(tdsDeclarations.getAmount());
                            }
                        }
                    }
                    totalValue += bean.getAmount();
                    tdsGroupList.add(bean);
                }
            }
            tdsGroupMasterBean.setTdsGroupDetailsBean(tdsGroupList);
            tdsGroupMasterBean.setTotalValue(totalValue);
            groupMasterBeans.add(tdsGroupMasterBean);
        }
        List<TdsSlabMaster> tdsSlabMasters = null;
        LocalDate dob = employeeView.getDob().toLocalDate();
        Period diff = Period.between(dob, LocalDate.now());
        if (tdsYearMaster != null && tdsYearMaster.getRegime() != null && tdsYearMaster.getRegime().booleanValue() == true) {
            if (tdsDeclarationChk != null && tdsDeclarationChk.getRegimeType() != null && tdsDeclarationChk.getRegimeType().equalsIgnoreCase("OLD")) {
                if (employeeView.getMf().equalsIgnoreCase("1")) {
                    tdsSlabMasters = tdsSlabMasterRepository.findAllByAgeEndAndGenderAndRegimeType(diff.getYears(), "M", year, "OLD");
                } else {
                    tdsSlabMasters = tdsSlabMasterRepository.findAllByAgeEndAndGenderAndRegimeType(diff.getYears(), "F", year, "OLD");
                }
            } else {
                if (employeeView.getMf().equalsIgnoreCase("1")) {
                    tdsSlabMasters = tdsSlabMasterRepository.findAllByAgeEndAndGenderAndRegimeType(diff.getYears(), "M", year, "NEW");
                } else {
                    tdsSlabMasters = tdsSlabMasterRepository.findAllByAgeEndAndGenderAndRegimeType(diff.getYears(), "F", year, "NEW");
                }
            }
        } else {
            if (employeeView.getMf().equalsIgnoreCase("1")) {
                tdsSlabMasters = tdsSlabMasterRepository.findAllByAgeEndAndGender(diff.getYears(), "M", year);
            } else {
                tdsSlabMasters = tdsSlabMasterRepository.findAllByAgeEndAndGender(diff.getYears(), "F", year);
            }
        }
        tdsComputationBean.setTdsSlabMasters(tdsSlabMasters);
        tdsComputationBean.setGroupMasterBeans(groupMasterBeans);
        return ResponseEntity.ok().body(tdsComputationBean);
    }

    private List<TdsSlabMaster> getValidTdsSlab(List<TdsSlabMaster> tdsSlabMasters, String gender, int age, String regimeType, Boolean regime) {
        List<TdsSlabMaster> slabMasterList = new ArrayList<>();
        for (TdsSlabMaster tdsSlabMaster : tdsSlabMasters) {
            if(regime != null && regime.booleanValue() == true) {
                regimeType = regimeType != null ? regimeType : "NEW";
                if (age >= tdsSlabMaster.getAgeStart().intValue() && age <= tdsSlabMaster.getAgeEnd().intValue() && gender.equalsIgnoreCase(tdsSlabMaster.getGender()) && regimeType != null && regimeType.equalsIgnoreCase(tdsSlabMaster.getRegimeType())) {
                    slabMasterList.add(tdsSlabMaster);
                }
            } else {
                if (age >= tdsSlabMaster.getAgeStart().intValue() && age <= tdsSlabMaster.getAgeEnd().intValue() && gender.equalsIgnoreCase(tdsSlabMaster.getGender())) {
                    slabMasterList.add(tdsSlabMaster);
                }
            }
        }
        Collections.sort(slabMasterList);
        return slabMasterList;
    }

    /**
     * GET  /tds-computations : get all the tdsComputations.
     *
     * @param @pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tdsComputations in body
     */
    @PostMapping("/tds-computations-entry")
    @Timed
    public ResponseEntity<List<TdsComputation>> getAllTdsComputationsAll(@Valid @RequestBody TdsDeclarationSearch search) {
        log.debug("REST request to get a page of TdsComputations");
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("id").ascending()));
        String cardNo = "%";
        if (search.getCardNo() != null) {
            cardNo = "%" + search.getCardNo() + "%";
        }
        Page<TdsComputation> page = tdsComputationRepository.findAllByCardNo(cardNo.toUpperCase(), cardNo.toUpperCase(), search.getYear(), search.getPage());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tds-computations-entry");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /tds-computations/:id : get the "id" tdsComputation.
     *
     * @param @id the id of the tdsComputation to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tdsComputation, or with status 404 (Not Found)
     */
    @GetMapping("/tds-computations-entry-process-all")
    @Timed
    public ResponseEntity<Void> getTdsComputationProcessAll() {
        List<TdsYearMaster> tdsYearMasters = tdsYearMasterRepository.findByActive();
        if (tdsYearMasters != null && tdsYearMasters.size() > 0) {
            TdsYearMaster tdsYearMaster = tdsYearMasters.get(0);
            Double salarySlab = tdsYearMaster.getSalarySlab();
            TdsGroupDetails pfGroup = null;
            TdsGroupDetails vpfGroup = null;
            if (salarySlab != null && salarySlab.doubleValue() > 0) {
                Map<String, Integer> monthList = new HashMap<>();
                monthList.put("04-" + tdsYearMaster.getCode(), 12);
                monthList.put("05-" + tdsYearMaster.getCode(), 11);
                monthList.put("06-" + tdsYearMaster.getCode(), 10);
                monthList.put("07-" + tdsYearMaster.getCode(), 9);
                monthList.put("08-" + tdsYearMaster.getCode(), 8);
                monthList.put("09-" + tdsYearMaster.getCode(), 7);
                monthList.put("10-" + tdsYearMaster.getCode(), 6);
                monthList.put("11-" + tdsYearMaster.getCode(), 5);
                monthList.put("12-" + tdsYearMaster.getCode(), 4);
                monthList.put("01-" + (Integer.parseInt(tdsYearMaster.getCode()) + 1), 3);
                monthList.put("02-" + (Integer.parseInt(tdsYearMaster.getCode()) + 1), 2);
                monthList.put("03-" + (Integer.parseInt(tdsYearMaster.getCode()) + 1), 1);

                Map<String, Integer> monthListNew = new HashMap<>();
                monthListNew.put("04-" + tdsYearMaster.getCode(), 11);
                monthListNew.put("05-" + tdsYearMaster.getCode(), 10);
                monthListNew.put("06-" + tdsYearMaster.getCode(), 9);
                monthListNew.put("07-" + tdsYearMaster.getCode(), 8);
                monthListNew.put("08-" + tdsYearMaster.getCode(), 7);
                monthListNew.put("09-" + tdsYearMaster.getCode(), 6);
                monthListNew.put("10-" + tdsYearMaster.getCode(), 5);
                monthListNew.put("11-" + tdsYearMaster.getCode(), 4);
                monthListNew.put("12-" + tdsYearMaster.getCode(), 3);
                monthListNew.put("01-" + (Integer.parseInt(tdsYearMaster.getCode())+1), 2);
                monthListNew.put("02-" + (Integer.parseInt(tdsYearMaster.getCode())+1), 1);
                monthListNew.put("03-" + (Integer.parseInt(tdsYearMaster.getCode())+1), 0);

                List<TdsGroupDetails> tdsGroupDetails = tdsGroupDetailsRepository.findAllYear(Integer.parseInt(tdsYearMaster.getCode()));
                Map<Long, Double> tdsGroupMasterMap = new TreeMap<>();
                Map<Long, TdsGroupMaster> tdsGroupMap = new TreeMap<>();
                for (TdsGroupDetails tdsGroupDetail : tdsGroupDetails) {
                    if (tdsGroupDetail.getPerkCode().equalsIgnoreCase("80C-PF")) {
                        pfGroup = tdsGroupDetail;
                    }
                    if (tdsGroupDetail.getPerkCode().equalsIgnoreCase("80C-VPF")) {
                        vpfGroup = tdsGroupDetail;
                    }
                    tdsGroupMasterMap.put(tdsGroupDetail.getTdsGroupMaster().getId().longValue(), 0.0);
                    tdsGroupMap.put(tdsGroupDetail.getTdsGroupMaster().getId().longValue(), tdsGroupDetail.getTdsGroupMaster());
                }
                List<TdsSlabMaster> tdsSlabMasters = tdsSlabMasterRepository.findByYear(tdsYearMaster.getCode());
                List<EmployeeView> tdsSlabEmployees = employeeViewRepository.findAllCardNosByTotalSalary(salarySlab);
                for (EmployeeView employeeView : tdsSlabEmployees) {
                    int balanceMonth = 0;
                    for (Long key : tdsGroupMasterMap.keySet()) {
                        tdsGroupMasterMap.put(key, 0.0);
                    }
                    List<MonthlyFinance> monthlyFinances = monthlyRepository.findAllByPan(employeeView.getPan(), new SimpleDateFormat("MM-yyyy").format(new Date()));
                    TdsComputation tdsComputation = tdsComputationRepository.findByCardNoAndYear(employeeView.getCardNo(), tdsYearMaster.getCode());
                    if (tdsComputation != null) {
                    } else {
                        tdsComputation = new TdsComputation();
                        tdsComputation.setCardNo(employeeView.getCardNo());
                        tdsComputation.setFinancialYear(tdsYearMaster.getCode());
                    }
                    tdsComputation.setName(employeeView.getName());
                    Double basic = 0.0;
                    Double hra = 0.0;
                    Double convAll = 0.0;
                    Double others = 0.0;
                    Double splAllow = 0.0;
                    Double medical = 0.0;

                    Double TDSDeducted = 0.0;

                    Double rentDeclare = 0.0;
                    Double rentExempt = 0.0;

                    Double pf = 0.0;
                    Double vpf = 0.0;
                    String regimeType = "NEW";
                    Boolean indivisibleLock = true;
                    List<Double> minExemptList = new ArrayList<>();
                    DecimalFormat df = new DecimalFormat("#.##");

                    if (monthlyFinances != null && monthlyFinances.size() > 0) {
                        for (MonthlyFinance monthlyFinance : monthlyFinances) {
                            //Basic
                            if (monthlyFinance.getAll1() != null && monthlyFinance.getAll1().equalsIgnoreCase("Basic")) {
                                basic += monthlyFinance.getEarn1() + monthlyFinance.getArr1();
                            } else if (monthlyFinance.getAll2() != null && monthlyFinance.getAll2().equalsIgnoreCase("Basic")) {
                                basic += monthlyFinance.getEarn2() + monthlyFinance.getArr2();
                            } else if (monthlyFinance.getAll3() != null && monthlyFinance.getAll3().equalsIgnoreCase("Basic")) {
                                basic += monthlyFinance.getEarn3() + monthlyFinance.getArr3();
                            } else if (monthlyFinance.getAll4() != null && monthlyFinance.getAll4().equalsIgnoreCase("Basic")) {
                                basic += monthlyFinance.getEarn4() + monthlyFinance.getArr4();
                            } else if (monthlyFinance.getAll5() != null && monthlyFinance.getAll5().equalsIgnoreCase("Basic")) {
                                basic += monthlyFinance.getEarn5() + monthlyFinance.getArr5();
                            } else if (monthlyFinance.getAll6() != null && monthlyFinance.getAll6().equalsIgnoreCase("Basic")) {
                                basic += monthlyFinance.getEarn6() + monthlyFinance.getArr6();
                            } else if (monthlyFinance.getAll7() != null && monthlyFinance.getAll7().equalsIgnoreCase("Basic")) {
                                basic += monthlyFinance.getEarn7() + monthlyFinance.getArr7();
                            }

                            //HRA
                            if (monthlyFinance.getAll1() != null && monthlyFinance.getAll1().equalsIgnoreCase("HRA")) {
                                hra += monthlyFinance.getEarn1() + monthlyFinance.getArr1();
                            } else if (monthlyFinance.getAll2() != null && monthlyFinance.getAll2().equalsIgnoreCase("HRA")) {
                                hra += monthlyFinance.getEarn2() + monthlyFinance.getArr2();
                            } else if (monthlyFinance.getAll3() != null && monthlyFinance.getAll3().equalsIgnoreCase("HRA")) {
                                hra += monthlyFinance.getEarn3() + monthlyFinance.getArr3();
                            } else if (monthlyFinance.getAll4() != null && monthlyFinance.getAll4().equalsIgnoreCase("HRA")) {
                                hra += monthlyFinance.getEarn4() + monthlyFinance.getArr4();
                            } else if (monthlyFinance.getAll5() != null && monthlyFinance.getAll5().equalsIgnoreCase("HRA")) {
                                hra += monthlyFinance.getEarn5() + monthlyFinance.getArr5();
                            } else if (monthlyFinance.getAll6() != null && monthlyFinance.getAll6().equalsIgnoreCase("HRA")) {
                                hra += monthlyFinance.getEarn6() + monthlyFinance.getArr6();
                            } else if (monthlyFinance.getAll7() != null && monthlyFinance.getAll7().equalsIgnoreCase("HRA")) {
                                hra += monthlyFinance.getEarn7() + monthlyFinance.getArr7();
                            }

                            //Conv
                            if (monthlyFinance.getAll1() != null && monthlyFinance.getAll1().equalsIgnoreCase("Conv. Allow.")) {
                                convAll += monthlyFinance.getEarn1() + monthlyFinance.getArr1();
                            } else if (monthlyFinance.getAll2() != null && monthlyFinance.getAll2().equalsIgnoreCase("Conv. Allow.")) {
                                convAll += monthlyFinance.getEarn2() + monthlyFinance.getArr2();
                            } else if (monthlyFinance.getAll3() != null && monthlyFinance.getAll3().equalsIgnoreCase("Conv. Allow.")) {
                                convAll += monthlyFinance.getEarn3() + monthlyFinance.getArr3();
                            } else if (monthlyFinance.getAll4() != null && monthlyFinance.getAll4().equalsIgnoreCase("Conv. Allow.")) {
                                convAll += monthlyFinance.getEarn4() + monthlyFinance.getArr4();
                            } else if (monthlyFinance.getAll5() != null && monthlyFinance.getAll5().equalsIgnoreCase("Conv. Allow.")) {
                                convAll += monthlyFinance.getEarn5() + monthlyFinance.getArr5();
                            } else if (monthlyFinance.getAll6() != null && monthlyFinance.getAll6().equalsIgnoreCase("Conv. Allow.")) {
                                convAll += monthlyFinance.getEarn6() + monthlyFinance.getArr6();
                            } else if (monthlyFinance.getAll7() != null && monthlyFinance.getAll7().equalsIgnoreCase("Conv. Allow.")) {
                                convAll += monthlyFinance.getEarn7() + monthlyFinance.getArr7();
                            }

                            //Others
                            if (monthlyFinance.getAll1() != null &&
                                !monthlyFinance.getAll1().equalsIgnoreCase("Basic") &&
                                !monthlyFinance.getAll1().equalsIgnoreCase("HRA") &&
                                !monthlyFinance.getAll1().equalsIgnoreCase("Conv. Allow.") &&
                                !monthlyFinance.getAll1().equalsIgnoreCase("Spl Allow") &&
                                !monthlyFinance.getAll1().equalsIgnoreCase("Spl Allow.") &&
                                !monthlyFinance.getAll1().equalsIgnoreCase("Medical")) {
                                others += monthlyFinance.getEarn1() + monthlyFinance.getArr1();
                            }
                            if (monthlyFinance.getAll2() != null &&
                                !monthlyFinance.getAll2().equalsIgnoreCase("Basic") &&
                                !monthlyFinance.getAll2().equalsIgnoreCase("HRA") &&
                                !monthlyFinance.getAll2().equalsIgnoreCase("Conv. Allow.") &&
                                !monthlyFinance.getAll2().equalsIgnoreCase("Spl Allow") &&
                                !monthlyFinance.getAll2().equalsIgnoreCase("Spl Allow.") &&
                                !monthlyFinance.getAll2().equalsIgnoreCase("Medical")) {
                                others += monthlyFinance.getEarn2() + monthlyFinance.getArr2();
                            }
                            if (monthlyFinance.getAll3() != null &&
                                !monthlyFinance.getAll3().equalsIgnoreCase("Basic") &&
                                !monthlyFinance.getAll3().equalsIgnoreCase("HRA") &&
                                !monthlyFinance.getAll3().equalsIgnoreCase("Conv. Allow.") &&
                                !monthlyFinance.getAll3().equalsIgnoreCase("Spl Allow") &&
                                !monthlyFinance.getAll3().equalsIgnoreCase("Spl Allow.") &&
                                !monthlyFinance.getAll3().equalsIgnoreCase("Medical")) {
                                others += monthlyFinance.getEarn3() + monthlyFinance.getArr3();
                            }
                            if (monthlyFinance.getAll4() != null &&
                                !monthlyFinance.getAll4().equalsIgnoreCase("Basic") &&
                                !monthlyFinance.getAll4().equalsIgnoreCase("HRA") &&
                                !monthlyFinance.getAll4().equalsIgnoreCase("Conv. Allow.") &&
                                !monthlyFinance.getAll4().equalsIgnoreCase("Spl Allow") &&
                                !monthlyFinance.getAll4().equalsIgnoreCase("Spl Allow.") &&
                                !monthlyFinance.getAll4().equalsIgnoreCase("Medical")) {
                                others += monthlyFinance.getEarn4() + monthlyFinance.getArr4();
                            }
                            if (monthlyFinance.getAll5() != null &&
                                !monthlyFinance.getAll5().equalsIgnoreCase("Basic") &&
                                !monthlyFinance.getAll5().equalsIgnoreCase("HRA") &&
                                !monthlyFinance.getAll5().equalsIgnoreCase("Conv. Allow.") &&
                                !monthlyFinance.getAll5().equalsIgnoreCase("Spl Allow") &&
                                !monthlyFinance.getAll5().equalsIgnoreCase("Spl Allow.") &&
                                !monthlyFinance.getAll5().equalsIgnoreCase("Medical")) {
                                others += monthlyFinance.getEarn5() + monthlyFinance.getArr5();
                            }
                            if (monthlyFinance.getAll6() != null &&
                                !monthlyFinance.getAll6().equalsIgnoreCase("Basic") &&
                                !monthlyFinance.getAll6().equalsIgnoreCase("HRA") &&
                                !monthlyFinance.getAll6().equalsIgnoreCase("Conv. Allow.") &&
                                !monthlyFinance.getAll6().equalsIgnoreCase("Spl Allow") &&
                                !monthlyFinance.getAll6().equalsIgnoreCase("Spl Allow.") &&
                                !monthlyFinance.getAll6().equalsIgnoreCase("Medical")) {
                                others += monthlyFinance.getEarn6() + monthlyFinance.getArr6();
                            }

                            if (monthlyFinance.getAll7() != null &&
                                !monthlyFinance.getAll7().equalsIgnoreCase("Basic") &&
                                !monthlyFinance.getAll7().equalsIgnoreCase("HRA") &&
                                !monthlyFinance.getAll7().equalsIgnoreCase("Conv. Allow.") &&
                                !monthlyFinance.getAll7().equalsIgnoreCase("Spl Allow") &&
                                !monthlyFinance.getAll7().equalsIgnoreCase("Spl Allow.") &&
                                !monthlyFinance.getAll7().equalsIgnoreCase("Medical")) {
                                others += monthlyFinance.getEarn7() + monthlyFinance.getArr7();
                            }

                            //Special Allows
                            if (monthlyFinance.getAll1() != null && (monthlyFinance.getAll1().equalsIgnoreCase("Spl Allow") || monthlyFinance.getAll1().equalsIgnoreCase("Spl Allow."))) {
                                splAllow += monthlyFinance.getEarn1() + monthlyFinance.getArr1();
                            } else if (monthlyFinance.getAll2() != null && (monthlyFinance.getAll2().equalsIgnoreCase("Spl Allow") || monthlyFinance.getAll2().equalsIgnoreCase("Spl Allow."))) {
                                splAllow += monthlyFinance.getEarn2() + monthlyFinance.getArr2();
                            } else if (monthlyFinance.getAll3() != null && (monthlyFinance.getAll3().equalsIgnoreCase("Spl Allow") || monthlyFinance.getAll3().equalsIgnoreCase("Spl Allow."))) {
                                splAllow += monthlyFinance.getEarn3() + monthlyFinance.getArr3();
                            } else if (monthlyFinance.getAll4() != null && (monthlyFinance.getAll4().equalsIgnoreCase("Spl Allow") || monthlyFinance.getAll4().equalsIgnoreCase("Spl Allow."))) {
                                splAllow += monthlyFinance.getEarn4() + monthlyFinance.getArr4();
                            } else if (monthlyFinance.getAll5() != null && (monthlyFinance.getAll5().equalsIgnoreCase("Spl Allow") || monthlyFinance.getAll5().equalsIgnoreCase("Spl Allow."))) {
                                splAllow += monthlyFinance.getEarn5() + monthlyFinance.getArr5();
                            } else if (monthlyFinance.getAll6() != null && (monthlyFinance.getAll6().equalsIgnoreCase("Spl Allow") || monthlyFinance.getAll6().equalsIgnoreCase("Spl Allow."))) {
                                splAllow += monthlyFinance.getEarn6() + monthlyFinance.getArr6();
                            } else if (monthlyFinance.getAll7() != null && (monthlyFinance.getAll7().equalsIgnoreCase("Spl Allow") || monthlyFinance.getAll6().equalsIgnoreCase("Spl Allow."))) {
                                splAllow += monthlyFinance.getEarn7() + monthlyFinance.getArr7();
                            }

                            //Medical
                            if (monthlyFinance.getAll1() != null && monthlyFinance.getAll1().equalsIgnoreCase("Medical")) {
                                medical += monthlyFinance.getEarn1() + monthlyFinance.getArr1();
                            } else if (monthlyFinance.getAll2() != null && monthlyFinance.getAll2().equalsIgnoreCase("Medical")) {
                                medical += monthlyFinance.getEarn2() + monthlyFinance.getArr2();
                            } else if (monthlyFinance.getAll3() != null && monthlyFinance.getAll3().equalsIgnoreCase("Medical")) {
                                medical += monthlyFinance.getEarn3() + monthlyFinance.getArr3();
                            } else if (monthlyFinance.getAll4() != null && monthlyFinance.getAll4().equalsIgnoreCase("Medical")) {
                                medical += monthlyFinance.getEarn4() + monthlyFinance.getArr4();
                            } else if (monthlyFinance.getAll5() != null && monthlyFinance.getAll5().equalsIgnoreCase("Medical")) {
                                medical += monthlyFinance.getEarn5() + monthlyFinance.getArr5();
                            } else if (monthlyFinance.getAll6() != null && monthlyFinance.getAll6().equalsIgnoreCase("Medical")) {
                                medical += monthlyFinance.getEarn6() + monthlyFinance.getArr6();
                            } else if (monthlyFinance.getAll7() != null && monthlyFinance.getAll7().equalsIgnoreCase("Medical")) {
                                medical += monthlyFinance.getEarn7() + monthlyFinance.getArr7();
                            }

                            //TDS
                            if (monthlyFinance.getDall1() != null && monthlyFinance.getDall1().equalsIgnoreCase("T D S")) {
                                TDSDeducted += monthlyFinance.getDed1();
                            } else if (monthlyFinance.getDall2() != null && monthlyFinance.getDall2().equalsIgnoreCase("T D S")) {
                                TDSDeducted += monthlyFinance.getDed2();
                            } else if (monthlyFinance.getDall3() != null && monthlyFinance.getDall3().equalsIgnoreCase("T D S")) {
                                TDSDeducted += monthlyFinance.getDed3();
                            } else if (monthlyFinance.getDall4() != null && monthlyFinance.getDall4().equalsIgnoreCase("T D S")) {
                                TDSDeducted += monthlyFinance.getDed4();
                            } else if (monthlyFinance.getDall5() != null && monthlyFinance.getDall5().equalsIgnoreCase("T D S")) {
                                TDSDeducted += monthlyFinance.getDed5();
                            } else if (monthlyFinance.getDall6() != null && monthlyFinance.getDall6().equalsIgnoreCase("T D S")) {
                                TDSDeducted += monthlyFinance.getDed6();
                            }

                            //PF
                            if (monthlyFinance.getDall1() != null && monthlyFinance.getDall1().equalsIgnoreCase("P F")) {
                                pf += monthlyFinance.getDed1();
                            } else if (monthlyFinance.getDall2() != null && monthlyFinance.getDall2().equalsIgnoreCase("P F")) {
                                pf += monthlyFinance.getDed2();
                            } else if (monthlyFinance.getDall3() != null && monthlyFinance.getDall3().equalsIgnoreCase("P F")) {
                                pf += monthlyFinance.getDed3();
                            } else if (monthlyFinance.getDall4() != null && monthlyFinance.getDall4().equalsIgnoreCase("P F")) {
                                pf += monthlyFinance.getDed4();
                            } else if (monthlyFinance.getDall5() != null && monthlyFinance.getDall5().equalsIgnoreCase("P F")) {
                                pf += monthlyFinance.getDed5();
                            } else if (monthlyFinance.getDall6() != null && monthlyFinance.getDall6().equalsIgnoreCase("P F")) {
                                pf += monthlyFinance.getDed6();
                            }

                            //VPF
                            if (monthlyFinance.getDall1() != null && (monthlyFinance.getDall1().equalsIgnoreCase("E P S") || monthlyFinance.getDall1().equalsIgnoreCase("V P F"))) {
                                vpf += monthlyFinance.getDed1();
                            } else if (monthlyFinance.getDall2() != null && (monthlyFinance.getDall2().equalsIgnoreCase("E P S") || monthlyFinance.getDall2().equalsIgnoreCase("V P F"))) {
                                vpf += monthlyFinance.getDed2();
                            } else if (monthlyFinance.getDall3() != null && (monthlyFinance.getDall3().equalsIgnoreCase("E P S") || monthlyFinance.getDall3().equalsIgnoreCase("V P F"))) {
                                vpf += monthlyFinance.getDed3();
                            } else if (monthlyFinance.getDall4() != null && (monthlyFinance.getDall4().equalsIgnoreCase("E P S") || monthlyFinance.getDall4().equalsIgnoreCase("V P F"))) {
                                vpf += monthlyFinance.getDed4();
                            } else if (monthlyFinance.getDall5() != null && (monthlyFinance.getDall5().equalsIgnoreCase("E P S") || monthlyFinance.getDall5().equalsIgnoreCase("V P F"))) {
                                vpf += monthlyFinance.getDed5();
                            } else if (monthlyFinance.getDall6() != null && (monthlyFinance.getDall6().equalsIgnoreCase("E P S") || monthlyFinance.getDall6().equalsIgnoreCase("V P F"))) {
                                vpf += monthlyFinance.getDed6();
                            }
                        }
                    }
                    SalaryRate salaryRate = salaryRateRepository.findByLastDetail(employeeView.getEmpCode(), employeeView.getEmpCode());
                    if (monthlyFinances.size() > 0 && salaryRate != null) {
                        balanceMonth = monthListNew.get(monthlyFinances.get(monthlyFinances.size() - 1).getId().getMonthYear());
                        basic += (salaryRate.getAmt1() * balanceMonth);
                        hra += (salaryRate.getAmt2() * balanceMonth);
                        convAll += (salaryRate.getAmt3() * balanceMonth);
                        others += ((salaryRate.getAmt4() * balanceMonth) + (salaryRate.getAmt7() * balanceMonth));
                        splAllow += (salaryRate.getAmt5() * balanceMonth);
                        medical += (salaryRate.getAmt6() * balanceMonth);
                        vpf += (employeeView.getPfVolFix() * balanceMonth);
                        if (employeeView.getPfallowed() == 1) {
                            double amt = salaryRate.getAmt1() * 12 / 100;
                            if (amt > 1800) {
                                amt = 1800;
                            }
                            pf += (amt * balanceMonth);
                        }
                    } else if (salaryRate != null) {
                        balanceMonth = monthList.get(new SimpleDateFormat("MM-yyyy").format(new Date()));
                        basic += (salaryRate.getAmt1() * balanceMonth);
                        hra += (salaryRate.getAmt2() * balanceMonth);
                        convAll += (salaryRate.getAmt3() * balanceMonth);
                        others += ((salaryRate.getAmt4() * balanceMonth) + (salaryRate.getAmt7() * balanceMonth));
                        splAllow += (salaryRate.getAmt5() * balanceMonth);
                        medical += (salaryRate.getAmt6() * balanceMonth);
                        vpf += (employeeView.getPfVolFix() * balanceMonth);
                        if (employeeView.getPfallowed() == 1) {
                            double amt = salaryRate.getAmt1() * 12 / 100;
                            if (amt > 1800) {
                                amt = 1800;
                            }
                            pf += (amt * balanceMonth);
                        }
                    }

                    if (tdsGroupDetails != null && tdsGroupDetails.size() > 0) {
                        for (TdsGroupDetails tdsGroupDetail : tdsGroupDetails) {
                            if (tdsGroupDetail.getPerkCode() != null && tdsGroupDetail.getPerkCode().equalsIgnoreCase("80C-PF")) {
                                TdsDeclarationBreakup tdsDeclarationBreakup = tdsDeclarationBreakupRepository.findByCardNoAndTdsGroupDetails(employeeView.getCardNo(), tdsGroupDetail.getId(), 0L);
                                if (tdsDeclarationBreakup != null) {
                                    tdsDeclarationBreakup.setAmount(pf);
                                    tdsDeclarationBreakupRepository.save(tdsDeclarationBreakup);
                                } else {
                                    tdsDeclarationBreakup = new TdsDeclarationBreakup();
                                    tdsDeclarationBreakup.setCardNo(employeeView.getCardNo());
                                    tdsDeclarationBreakup.setAmount(pf);
                                    tdsDeclarationBreakup.setCreatedBy("AUTO");
                                    tdsDeclarationBreakup.setCreatedDate(Instant.now());
                                    tdsDeclarationBreakup.setTdsGroupDetails(tdsGroupDetail);
                                    tdsDeclarationBreakup.setPreviousEmpDtlsId(0L);
                                    tdsDeclarationBreakupRepository.save(tdsDeclarationBreakup);
                                }
                            } else if (tdsGroupDetail.getPerkCode() != null && tdsGroupDetail.getPerkCode().equalsIgnoreCase("80C-VPF")) {
                                TdsDeclarationBreakup tdsDeclarationBreakup = tdsDeclarationBreakupRepository.findByCardNoAndTdsGroupDetails(employeeView.getCardNo(), tdsGroupDetail.getId(), 0L);
                                if (tdsDeclarationBreakup != null) {
                                    tdsDeclarationBreakup.setAmount(vpf);
                                    tdsDeclarationBreakupRepository.save(tdsDeclarationBreakup);
                                } else {
                                    tdsDeclarationBreakup = new TdsDeclarationBreakup();
                                    tdsDeclarationBreakup.setCardNo(employeeView.getCardNo());
                                    tdsDeclarationBreakup.setAmount(vpf);
                                    tdsDeclarationBreakup.setCreatedBy("AUTO");
                                    tdsDeclarationBreakup.setCreatedDate(Instant.now());
                                    tdsDeclarationBreakup.setTdsGroupDetails(tdsGroupDetail);
                                    tdsDeclarationBreakup.setPreviousEmpDtlsId(0L);
                                    tdsDeclarationBreakupRepository.save(tdsDeclarationBreakup);
                                }
                            }
                        }
                    }

                    List<PreviousEmploymentDetails> previousEmploymentDetails = previousEmploymentDetailsRepository.findAllByCardNoAndYear(employeeView.getCardNo(), tdsYearMaster.getCode());
                    if (previousEmploymentDetails != null && previousEmploymentDetails.size() > 0) {
                        for (PreviousEmploymentDetails employmentDetails : previousEmploymentDetails) {
                            if (employmentDetails.getBasic() != null && employmentDetails.getBasic() > 0) {
                                basic += employmentDetails.getBasic();
                            }
                            if (employmentDetails.getHra() != null && employmentDetails.getHra() > 0) {
                                hra += employmentDetails.getHra();
                            }
                            if (employmentDetails.getCta() != null && employmentDetails.getCta() > 0) {
                                convAll += employmentDetails.getCta();
                            }
                            if (employmentDetails.getOthers() != null && employmentDetails.getOthers() > 0) {
                                others += employmentDetails.getOthers();
                            }
                            if (employmentDetails.getSpa() != null && employmentDetails.getSpa() > 0) {
                                splAllow += employmentDetails.getSpa();
                            }
                            if (employmentDetails.getMda() != null && employmentDetails.getMda() > 0) {
                                medical += employmentDetails.getMda();
                            }


                            if (employmentDetails.getEpf() != null && employmentDetails.getEpf() > 0) {
                                pf += employmentDetails.getEpf();
                            }
                            if (employmentDetails.getVpf() != null && employmentDetails.getVpf() > 0) {
                                vpf += employmentDetails.getVpf();
                            }

                            if (employmentDetails.getMonthRent() != null && employmentDetails.getMonthRent() > 0) {
                                rentDeclare += employmentDetails.getMonthRent();
                            }

                            if (employmentDetails.getTds() != null && employmentDetails.getTds() > 0) {
                                TDSDeducted += employmentDetails.getTds();
                            }
                            if (tdsGroupDetails != null && tdsGroupDetails.size() > 0) {
                                for (TdsGroupDetails tdsGroupDetail : tdsGroupDetails) {
                                    if (tdsGroupDetail.getPerkCode() != null && tdsGroupDetail.getPerkCode().equalsIgnoreCase("80C-PF")) {
                                        TdsDeclarationBreakup tdsDeclarationBreakup = tdsDeclarationBreakupRepository.findByCardNoAndTdsGroupDetails(employeeView.getCardNo(), tdsGroupDetail.getId(), employmentDetails.getId());
                                        if (tdsDeclarationBreakup != null) {
                                            tdsDeclarationBreakup.setAmount(employmentDetails.getEpf());
                                            tdsDeclarationBreakupRepository.save(tdsDeclarationBreakup);
                                        } else {
                                            tdsDeclarationBreakup = new TdsDeclarationBreakup();
                                            tdsDeclarationBreakup.setCardNo(employeeView.getCardNo());
                                            tdsDeclarationBreakup.setAmount(employmentDetails.getVpf());
                                            tdsDeclarationBreakup.setCreatedBy("AUTO");
                                            tdsDeclarationBreakup.setCreatedDate(Instant.now());
                                            tdsDeclarationBreakup.setTdsGroupDetails(tdsGroupDetail);
                                            tdsDeclarationBreakup.setPreviousEmpDtlsId(employmentDetails.getId());
                                            tdsDeclarationBreakupRepository.save(tdsDeclarationBreakup);
                                        }
                                    } else if (tdsGroupDetail.getPerkCode() != null && tdsGroupDetail.getPerkCode().equalsIgnoreCase("80C-VPF")) {
                                        TdsDeclarationBreakup tdsDeclarationBreakup = tdsDeclarationBreakupRepository.findByCardNoAndTdsGroupDetails(employeeView.getCardNo(), tdsGroupDetail.getId(), employmentDetails.getId());
                                        if (tdsDeclarationBreakup != null) {
                                            tdsDeclarationBreakup.setAmount(vpf);
                                            tdsDeclarationBreakupRepository.save(tdsDeclarationBreakup);
                                        } else {
                                            tdsDeclarationBreakup = new TdsDeclarationBreakup();
                                            tdsDeclarationBreakup.setCardNo(employeeView.getCardNo());
                                            tdsDeclarationBreakup.setAmount(vpf);
                                            tdsDeclarationBreakup.setCreatedBy("AUTO");
                                            tdsDeclarationBreakup.setCreatedDate(Instant.now());
                                            tdsDeclarationBreakup.setTdsGroupDetails(tdsGroupDetail);
                                            tdsDeclarationBreakup.setPreviousEmpDtlsId(employmentDetails.getId());
                                            tdsDeclarationBreakupRepository.save(tdsDeclarationBreakup);
                                        }
                                    }
                                }
                            }
                        }
                    }

                    tdsComputation.setEarnLabel1("Basic");
                    tdsComputation.setEarnAmount1(basic);
                    tdsComputation.setEarnLabel2("House Rent Allowance");
                    tdsComputation.setEarnAmount2(hra);
                    tdsComputation.setEarnLabel3("Conveyance/Transport Allowance");
                    tdsComputation.setEarnAmount3(convAll);
                    tdsComputation.setEarnLabel4("Others");
                    tdsComputation.setEarnAmount4(others);
                    tdsComputation.setEarnLabel5("Special Allowance");
                    tdsComputation.setEarnAmount5(splAllow);
                    tdsComputation.setEarnLabel6("Medical Allowance");
                    tdsComputation.setEarnAmount6(medical);
                    tdsComputation.setPendingMonth(balanceMonth);

                    Double incentive = 0.0;
                    Double previousEmployer = 0.0;
                    List<TdsDeclaration> tdsDeclaration = tdsDeclarationRepository.findAllByCardNo(employeeView.getCardNo(), Integer.parseInt(tdsYearMaster.getCode()));
                    if (tdsDeclaration.size() > 0) {
                        TdsDeclaration tdsDeclarations = tdsDeclaration.get(0);

                        tdsComputation.setIncentiveAmount(tdsDeclarations.getIncentiveAmount() != null ? tdsDeclarations.getIncentiveAmount() : new BigDecimal(0));
                        tdsComputation.setPreviousEmployerAmount(tdsDeclarations.getPreviousEmployerAmount() != null ? tdsDeclarations.getPreviousEmployerAmount() : new BigDecimal(0));
                        tdsComputation.setPreviousEmployerTdsDeduction(tdsDeclarations.getPreviousEmployerTdsDeduction() != null ? tdsDeclarations.getPreviousEmployerTdsDeduction() : new BigDecimal(0));
                        incentive = tdsComputation.getIncentiveAmount().doubleValue();
                        previousEmployer = tdsComputation.getPreviousEmployerAmount().doubleValue();
                        if (tdsDeclarations.getTempLock() != null && tdsDeclarations.getTempLock().equalsIgnoreCase("Y")) {
                        } else {
                            indivisibleLock = false;
                        }
                        regimeType = tdsDeclarations.getRegimeType();
                        if (tdsYearMaster.getRegime() != null && tdsYearMaster.getRegime().booleanValue() == true) {
                            if(tdsDeclarations.getRegimeType() != null && tdsDeclarations.getRegimeType().equalsIgnoreCase("OLD")) {
                                if (tdsDeclarations.getMonthRent() != null && tdsDeclarations.getMonthRent().length() > 0 && StringUtils.isNumeric(tdsDeclarations.getMonthRent()) && Double.parseDouble(tdsDeclarations.getMonthRent()) > 0) {
                                    rentDeclare += Double.parseDouble(tdsDeclarations.getMonthRent()) * (monthlyFinances.size() + balanceMonth);
                                    String rentAddress = "";
                                    if (tdsDeclarations.getLandLoardAddress() != null) {
                                        rentAddress = tdsDeclarations.getLandLoardAddress().toUpperCase();
                                    }
                                    minExemptList.add(hra);
                                    minExemptList.add(rentDeclare - ((basic) * 0.1));
                                    if (rentAddress.contains("DELHI") || rentAddress.contains("MUMBAI") || rentAddress.contains("CHENNAI") || rentAddress.contains("KOLKATA")) {
                                        minExemptList.add(basic * 0.5);
                                    } else {
                                        minExemptList.add(basic * 0.4);
                                    }
                                    rentExempt = Collections.min(minExemptList);
                                    if (rentDeclare > rentExempt) {
                                    } else if (rentDeclare < rentExempt) {
                                        rentExempt = rentDeclare;
                                    }
                                }
                            }
                        } else {
                            if (tdsDeclarations.getMonthRent() != null && tdsDeclarations.getMonthRent().length() > 0 && StringUtils.isNumeric(tdsDeclarations.getMonthRent()) && Double.parseDouble(tdsDeclarations.getMonthRent()) > 0) {
                                rentDeclare += Double.parseDouble(tdsDeclarations.getMonthRent()) * (monthlyFinances.size() + balanceMonth);
                                String rentAddress = "";
                                if (tdsDeclarations.getLandLoardAddress() != null) {
                                    rentAddress = tdsDeclarations.getLandLoardAddress().toUpperCase();
                                }
                                minExemptList.add(hra);
                                minExemptList.add(rentDeclare - ((basic) * 0.1));
                                if (rentAddress.contains("DELHI") || rentAddress.contains("MUMBAI") || rentAddress.contains("CHENNAI") || rentAddress.contains("KOLKATA")) {
                                    minExemptList.add(basic * 0.5);
                                } else {
                                    minExemptList.add(basic * 0.4);
                                }
                                rentExempt = Collections.min(minExemptList);
                                if (rentDeclare > rentExempt) {
                                } else if (rentDeclare < rentExempt) {
                                    rentExempt = rentDeclare;
                                }
                            }
                        }

                        boolean pfExist = false;
                        boolean vpfExist = false;
                        for (TdsDeclaration declaration : tdsDeclaration) {
                            if (declaration.getTdsGroupDetails().getPerkCode().equalsIgnoreCase("80C-PF")) {
                                pfExist = true;
                                declaration.setAmount(pf);
                                tdsDeclarationRepository.save(declaration);
                            } else if (declaration.getTdsGroupDetails().getPerkCode().equalsIgnoreCase("80C-VPF")) {
                                vpfExist = true;
                                declaration.setAmount(vpf);
                                tdsDeclarationRepository.save(declaration);
                            }
                        }

                        if (pfExist == false) {
                            TdsDeclaration declarationPF = new TdsDeclaration();
                            BeanUtils.copyProperties(tdsDeclarations, declarationPF);
                            declarationPF.setAmount(pf);
                            declarationPF.setId(null);
                            declarationPF.setTdsGroupDetails(pfGroup);
                            TdsDeclaration result = tdsDeclarationRepository.save(declarationPF);
                            tdsDeclaration.add(result);
                        }
                        if (vpfExist == false) {
                            TdsDeclaration declarationVPF = new TdsDeclaration();
                            BeanUtils.copyProperties(tdsDeclarations, declarationVPF);
                            declarationVPF.setAmount(vpf);
                            declarationVPF.setId(null);
                            declarationVPF.setTdsGroupDetails(vpfGroup);
                            TdsDeclaration result = tdsDeclarationRepository.save(declarationVPF);
                            tdsDeclaration.add(result);
                        }
                    } else {
                        tdsComputation.setIncentiveAmount(new BigDecimal(0));
                        tdsComputation.setPreviousEmployerAmount(new BigDecimal(0));
                        tdsComputation.setPreviousEmployerTdsDeduction(new BigDecimal(0));
                        incentive = tdsComputation.getIncentiveAmount().doubleValue();
                        previousEmployer = tdsComputation.getPreviousEmployerAmount().doubleValue();
                        for (TdsGroupDetails groupDetails : tdsGroupDetails) {
                            TdsDeclaration declaration = new TdsDeclaration();
                            declaration.setCardNo(employeeView.getCardNo());
                            declaration.setTdsGroupDetails(groupDetails);
                            if (declaration.getTdsGroupDetails().getPerkCode().equalsIgnoreCase("80C-PF")) {
                                declaration.setAmount(pf);
                            } else if (declaration.getTdsGroupDetails().getPerkCode().equalsIgnoreCase("80C-VPF")) {
                                declaration.setAmount(vpf);
                            } else {
                                declaration.setAmount(0.0);
                            }
                            declaration.setCreatedBy("AUTO");
                            declaration.setCreatedDate(Instant.now());
                            TdsDeclaration result = tdsDeclarationRepository.save(declaration);
                            tdsDeclaration.add(result);
                        }
                    }

                    tdsComputation.setRentDeclare(rentDeclare);

                    tdsComputation.setRentExempt(rentExempt);

                    tdsComputation.setEarnTotal((basic + hra + convAll + others + splAllow + medical + previousEmployer + incentive) - rentExempt);

                    if ((regimeType != null && regimeType.equalsIgnoreCase("OLD")) || (tdsYearMaster.getRegime() != null && tdsYearMaster.getRegime().booleanValue() == false)) {
                        if (tdsYearMaster.getStandardDeduction() != null && tdsYearMaster.getStandardDeduction().doubleValue() > 0) {
                            tdsComputation.setStandardDeduction(tdsYearMaster.getStandardDeduction());
                            tdsComputation.setStandardTotal(tdsComputation.getEarnTotal() - tdsYearMaster.getStandardDeduction());
                        } else {
                            tdsComputation.setStandardDeduction(0.0);
                            tdsComputation.setStandardTotal(tdsComputation.getEarnTotal() - 0.0);
                        }
                    } else {
                        if (tdsYearMaster.getStandardDeduction() != null && tdsYearMaster.getStandardDeduction().doubleValue() > 0) {
                            tdsComputation.setStandardDeduction(tdsYearMaster.getStandardDeduction());
                            tdsComputation.setStandardTotal(tdsComputation.getEarnTotal() - tdsYearMaster.getStandardDeduction());
                        } else {
                            tdsComputation.setStandardDeduction(0.0);
                            tdsComputation.setStandardTotal(tdsComputation.getEarnTotal() - 0.0);
                        }
                    }

                    for (Long key : tdsGroupMasterMap.keySet()) {
                        for (TdsDeclaration tdsDeclarations : tdsDeclaration) {
                            if (tdsDeclarations.getTdsGroupDetails()!= null && tdsDeclarations.getTdsGroupDetails().getTdsGroupMaster() != null && key != null && tdsDeclarations.getTdsGroupDetails().getTdsGroupMaster().getId().longValue() == key.longValue()) {
                                if (tdsDeclarations.getAmount() != null) {
                                    Double value = tdsGroupMasterMap.get(key);
                                    if (tdsDeclarations.getTdsGroupDetails().getPerkLimit() != null && Double.parseDouble(tdsDeclarations.getTdsGroupDetails().getPerkLimit()) > 0) {
                                        if (tdsDeclarations.getAmount().doubleValue() > Double.parseDouble(tdsDeclarations.getTdsGroupDetails().getPerkLimit())) {
                                            tdsGroupMasterMap.put(key, value + Double.parseDouble(tdsDeclarations.getTdsGroupDetails().getPerkLimit()));
                                        } else {
                                            tdsGroupMasterMap.put(key, value + tdsDeclarations.getAmount());
                                        }
                                    } else {
                                        tdsGroupMasterMap.put(key, value + tdsDeclarations.getAmount());
                                    }
                                }
                            }
                        }
                    }

                    int ctr = 0;
                    Double deductIncome = 0.0;
                    for (Long key : tdsGroupMasterMap.keySet()) {
                        if (ctr == 0) {
                            tdsComputation.setDeductCode1(key + "");
                            if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                                tdsComputation.setDeductAmount1(tdsGroupMap.get(key).getGroupLimit());
                                deductIncome += tdsGroupMap.get(key).getGroupLimit();
                            } else {
                                tdsComputation.setDeductAmount1(tdsGroupMasterMap.get(key).doubleValue());
                                deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                            }
                            ++ctr;
                        } else if (ctr == 1) {
                            tdsComputation.setDeductCode2(key + "");
                            if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                                tdsComputation.setDeductAmount2(tdsGroupMap.get(key).getGroupLimit());
                                deductIncome += tdsGroupMap.get(key).getGroupLimit();
                            } else {
                                tdsComputation.setDeductAmount2(tdsGroupMasterMap.get(key).doubleValue());
                                deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                            }
                            ++ctr;
                        } else if (ctr == 2) {
                            tdsComputation.setDeductCode3(key + "");
                            if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                                tdsComputation.setDeductAmount3(tdsGroupMap.get(key).getGroupLimit());
                                deductIncome += tdsGroupMap.get(key).getGroupLimit();
                            } else {
                                tdsComputation.setDeductAmount3(tdsGroupMasterMap.get(key).doubleValue());
                                deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                            }
                            ++ctr;
                        } else if (ctr == 3) {
                            tdsComputation.setDeductCode4(key + "");
                            if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                                tdsComputation.setDeductAmount4(tdsGroupMap.get(key).getGroupLimit());
                                deductIncome += tdsGroupMap.get(key).getGroupLimit();
                            } else {
                                tdsComputation.setDeductAmount4(tdsGroupMasterMap.get(key).doubleValue());
                                deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                            }
                            ++ctr;
                        } else if (ctr == 4) {
                            tdsComputation.setDeductCode5(key + "");
                            if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                                tdsComputation.setDeductAmount5(tdsGroupMap.get(key).getGroupLimit());
                                deductIncome += tdsGroupMap.get(key).getGroupLimit();
                            } else {
                                tdsComputation.setDeductAmount5(tdsGroupMasterMap.get(key).doubleValue());
                                deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                            }
                            ++ctr;
                        } else if (ctr == 5) {
                            tdsComputation.setDeductCode6(key + "");
                            if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                                tdsComputation.setDeductAmount6(tdsGroupMap.get(key).getGroupLimit());
                                deductIncome += tdsGroupMap.get(key).getGroupLimit();
                            } else {
                                tdsComputation.setDeductAmount6(tdsGroupMasterMap.get(key).doubleValue());
                                deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                            }
                            ++ctr;
                        } else if (ctr == 6) {
                            tdsComputation.setDeductCode7(key + "");
                            if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                                tdsComputation.setDeductAmount7(tdsGroupMap.get(key).getGroupLimit());
                                deductIncome += tdsGroupMap.get(key).getGroupLimit();
                            } else {
                                tdsComputation.setDeductAmount7(tdsGroupMasterMap.get(key).doubleValue());
                                deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                            }
                            ++ctr;
                        } else if (ctr == 7) {
                            tdsComputation.setDeductCode8(key + "");
                            if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                                tdsComputation.setDeductAmount8(tdsGroupMap.get(key).getGroupLimit());
                                deductIncome += tdsGroupMap.get(key).getGroupLimit();
                            } else {
                                tdsComputation.setDeductAmount8(tdsGroupMasterMap.get(key).doubleValue());
                                deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                            }
                            ++ctr;
                        } else if (ctr == 8) {
                            tdsComputation.setDeductCode9(key + "");
                            if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                                tdsComputation.setDeductAmount9(tdsGroupMap.get(key).getGroupLimit());
                                deductIncome += tdsGroupMap.get(key).getGroupLimit();
                            } else {
                                tdsComputation.setDeductAmount9(tdsGroupMasterMap.get(key).doubleValue());
                                deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                            }
                            ++ctr;
                        } else if (ctr == 9) {
                            tdsComputation.setDeductCode10(key + "");
                            if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                                tdsComputation.setDeductAmount10(tdsGroupMap.get(key).getGroupLimit());
                                deductIncome += tdsGroupMap.get(key).getGroupLimit();
                            } else {
                                tdsComputation.setDeductAmount10(tdsGroupMasterMap.get(key).doubleValue());
                                deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                            }
                            ++ctr;
                        } else if (ctr == 10) {
                            tdsComputation.setDeductCode11(key + "");
                            if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                                tdsComputation.setDeductAmount11(tdsGroupMap.get(key).getGroupLimit());
                                deductIncome += tdsGroupMap.get(key).getGroupLimit();
                            } else {
                                tdsComputation.setDeductAmount11(tdsGroupMasterMap.get(key).doubleValue());
                                deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                            }
                            ++ctr;
                        } else if (ctr == 11) {
                            tdsComputation.setDeductCode12(key + "");
                            if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                                tdsComputation.setDeductAmount12(tdsGroupMap.get(key).getGroupLimit());
                                deductIncome += tdsGroupMap.get(key).getGroupLimit();
                            } else {
                                tdsComputation.setDeductAmount12(tdsGroupMasterMap.get(key).doubleValue());
                                deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                            }
                            ++ctr;
                        } else if (ctr == 12) {
                            tdsComputation.setDeductCode13(key + "");
                            if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                                tdsComputation.setDeductAmount13(tdsGroupMap.get(key).getGroupLimit());
                                deductIncome += tdsGroupMap.get(key).getGroupLimit();
                            } else {
                                tdsComputation.setDeductAmount13(tdsGroupMasterMap.get(key).doubleValue());
                                deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                            }
                            ++ctr;
                        } else if (ctr == 13) {
                            tdsComputation.setDeductCode14(key + "");
                            if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                                tdsComputation.setDeductAmount14(tdsGroupMap.get(key).getGroupLimit());
                                deductIncome += tdsGroupMap.get(key).getGroupLimit();
                            } else {
                                tdsComputation.setDeductAmount14(tdsGroupMasterMap.get(key).doubleValue());
                                deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                            }
                            ++ctr;
                        } else if (ctr == 14) {
                            tdsComputation.setDeductCode15(key + "");
                            if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                                tdsComputation.setDeductAmount15(tdsGroupMap.get(key).getGroupLimit());
                                deductIncome += tdsGroupMap.get(key).getGroupLimit();
                            } else {
                                tdsComputation.setDeductAmount15(tdsGroupMasterMap.get(key).doubleValue());
                                deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                            }
                            ++ctr;
                        } else if (ctr == 15) {
                            tdsComputation.setDeductCode16(key + "");
                            if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                                tdsComputation.setDeductAmount16(tdsGroupMap.get(key).getGroupLimit());
                                deductIncome += tdsGroupMap.get(key).getGroupLimit();
                            } else {
                                tdsComputation.setDeductAmount16(tdsGroupMasterMap.get(key).doubleValue());
                                deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                            }
                        } else if (ctr == 16) {
                            tdsComputation.setDeductCode17(key + "");
                            if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                                tdsComputation.setDeductAmount17(tdsGroupMap.get(key).getGroupLimit());
                                deductIncome += tdsGroupMap.get(key).getGroupLimit();
                            } else {
                                tdsComputation.setDeductAmount17(tdsGroupMasterMap.get(key).doubleValue());
                                deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                            }
                            ++ctr;
                        } else if (ctr == 17) {
                            tdsComputation.setDeductCode18(key + "");
                            if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                                tdsComputation.setDeductAmount18(tdsGroupMap.get(key).getGroupLimit());
                                deductIncome += tdsGroupMap.get(key).getGroupLimit();
                            } else {
                                tdsComputation.setDeductAmount18(tdsGroupMasterMap.get(key).doubleValue());
                                deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                            }
                            ++ctr;
                        } else if (ctr == 18) {
                            tdsComputation.setDeductCode19(key + "");
                            if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                                tdsComputation.setDeductAmount19(tdsGroupMap.get(key).getGroupLimit());
                                deductIncome += tdsGroupMap.get(key).getGroupLimit();
                            } else {
                                tdsComputation.setDeductAmount19(tdsGroupMasterMap.get(key).doubleValue());
                                deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                            }
                            ++ctr;
                        } else if (ctr == 19) {
                            tdsComputation.setDeductCode20(key + "");
                            if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                                tdsComputation.setDeductAmount20(tdsGroupMap.get(key).getGroupLimit());
                                deductIncome += tdsGroupMap.get(key).getGroupLimit();
                            } else {
                                tdsComputation.setDeductAmount20(tdsGroupMasterMap.get(key).doubleValue());
                                deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                            }
                            ++ctr;
                        }
                    }
                    if ((regimeType != null && regimeType.equalsIgnoreCase("OLD")) || (tdsYearMaster.getRegime() != null && tdsYearMaster.getRegime().booleanValue() == false)) {
                        if (tdsYearMaster.getStandardDeduction() != null && tdsYearMaster.getStandardDeduction().doubleValue() > 0) {
                            tdsComputation.setTotalTaxIncome(tdsComputation.getStandardTotal() - deductIncome);
                        } else {
                            tdsComputation.setTotalTaxIncome(tdsComputation.getEarnTotal() - deductIncome);
                        }
                    } else {
                        tdsComputation.setTotalTaxIncome(tdsComputation.getStandardTotal() - 0.0);
                    }
                    List<TdsSlabMaster> slabMasterList = null;
                    LocalDate dob = employeeView.getDob().toLocalDate();
                    Period diff = Period.between(dob, LocalDate.now());
                    if (employeeView.getMf().equalsIgnoreCase("1")) {
                        slabMasterList = getValidTdsSlab(tdsSlabMasters, "M", diff.getYears(), regimeType, tdsYearMaster.getRegime());
                    } else {
                        slabMasterList = getValidTdsSlab(tdsSlabMasters, "F", diff.getYears(), regimeType, tdsYearMaster.getRegime());
                    }

                    Double taxValue = 0.0;
                    Double cess = 0.0;
                    if (slabMasterList.size() > 0) {
                        if (tdsComputation.getTotalTaxIncome() > slabMasterList.get(0).getExemptionLimit()) {
                            Double totalIncome = tdsComputation.getTotalTaxIncome();
                            Double balance = 0.0;
                            Double previousBalance = 0.0;
                            for (TdsSlabMaster tdsSlabMaster : slabMasterList) {
                                if (totalIncome >= tdsSlabMaster.getAmountStart() && totalIncome >= tdsSlabMaster.getAmountEnd() && previousBalance == 0) {
                                    totalIncome = tdsComputation.getTotalTaxIncome() - tdsSlabMaster.getAmountEnd();
                                    previousBalance = tdsSlabMaster.getAmountEnd();
                                    balance = tdsSlabMaster.getAmountEnd();
                                    Double taxValueTemp = (balance * tdsSlabMaster.getTaxPercentage()) / 100;
                                    Double cessTemp = (taxValueTemp * tdsSlabMaster.getTaxSurcharge()) / 100;
                                    taxValue += taxValueTemp;
                                    cess += cessTemp;
                                } else if (previousBalance < tdsSlabMaster.getAmountStart() && (totalIncome + previousBalance) >= tdsSlabMaster.getAmountEnd()) {
                                    totalIncome = tdsComputation.getTotalTaxIncome() - tdsSlabMaster.getAmountEnd();
                                    balance = tdsSlabMaster.getAmountEnd() - previousBalance;
                                    previousBalance = tdsSlabMaster.getAmountEnd();
                                    Double taxValueTemp = (balance * tdsSlabMaster.getTaxPercentage()) / 100;
                                    Double cessTemp = (taxValueTemp * tdsSlabMaster.getTaxSurcharge()) / 100;
                                    taxValue += taxValueTemp;
                                    cess += cessTemp;
                                } else if (totalIncome > 0 && previousBalance < tdsSlabMaster.getAmountStart() && (totalIncome + previousBalance) < tdsSlabMaster.getAmountEnd()) {
                                    balance = totalIncome;
                                    previousBalance = tdsSlabMaster.getAmountEnd();
                                    totalIncome = tdsComputation.getTotalTaxIncome() - tdsSlabMaster.getAmountEnd();
                                    Double taxValueTemp = (balance * tdsSlabMaster.getTaxPercentage()) / 100;
                                    Double cessTemp = (taxValueTemp * tdsSlabMaster.getTaxSurcharge()) / 100;
                                    taxValue += taxValueTemp;
                                    cess += cessTemp;
                                    totalIncome = 0.0;
                                }
                            }
                        } else {
                            taxValue = 0.0;
                            cess = 0.0;
                        }
                    }
                    tdsComputation.setTaxValue(Double.valueOf(df.format(taxValue)));
                    tdsComputation.setCessValue(Double.valueOf(df.format(cess)));
                    tdsComputation.setTotalTaxLiability(Double.valueOf(df.format(taxValue + cess)));
                    tdsComputation.setTaxDeductValue(TDSDeducted);
                    tdsComputation.setBalanceTaxValue(Double.valueOf(df.format((taxValue + cess) - TDSDeducted - tdsComputation.getPreviousEmployerTdsDeduction().doubleValue())));
                    tdsComputation.setProcessDate(Instant.now());
                    TdsComputation result = tdsComputationRepository.save(tdsComputation);
                }
            }
        }
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, "")).build();
    }

    /**
     * GET  /tds-computations/:id : get the "id" tdsComputation.
     *
     * @param @id the id of the tdsComputation to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tdsComputation, or with status 404 (Not Found)
     */
    @GetMapping("/tds-computations-entry-process/{id}")
    @Timed
    public ResponseEntity<Void> getTdsComputationProcess(@PathVariable Long id) {
        TdsComputation tdsComputation = tdsComputationRepository.findById(id).orElse(null);
        TdsYearMaster tdsYearMaster = tdsYearMasterRepository.findByCode(tdsComputation.getFinancialYear());
        Double salarySlab = tdsYearMaster.getSalarySlab();
        TdsGroupDetails pfGroup = null;
        TdsGroupDetails vpfGroup = null;
        if (salarySlab != null && salarySlab.doubleValue() > 0) {

            Map<String, Integer> monthList = new HashMap<>();
            monthList.put("04-" + tdsYearMaster.getCode(), 12);
            monthList.put("05-" + tdsYearMaster.getCode(), 11);
            monthList.put("06-" + tdsYearMaster.getCode(), 10);
            monthList.put("07-" + tdsYearMaster.getCode(), 9);
            monthList.put("08-" + tdsYearMaster.getCode(), 8);
            monthList.put("09-" + tdsYearMaster.getCode(), 7);
            monthList.put("10-" + tdsYearMaster.getCode(), 6);
            monthList.put("11-" + tdsYearMaster.getCode(), 5);
            monthList.put("12-" + tdsYearMaster.getCode(), 4);
            monthList.put("01-" + (Integer.parseInt(tdsYearMaster.getCode())+1), 3);
            monthList.put("02-" + (Integer.parseInt(tdsYearMaster.getCode())+1), 2);
            monthList.put("03-" + (Integer.parseInt(tdsYearMaster.getCode())+1), 1);

            Map<String, Integer> monthListNew = new HashMap<>();
            monthListNew.put("04-" + tdsYearMaster.getCode(), 11);
            monthListNew.put("05-" + tdsYearMaster.getCode(), 10);
            monthListNew.put("06-" + tdsYearMaster.getCode(), 9);
            monthListNew.put("07-" + tdsYearMaster.getCode(), 8);
            monthListNew.put("08-" + tdsYearMaster.getCode(), 7);
            monthListNew.put("09-" + tdsYearMaster.getCode(), 6);
            monthListNew.put("10-" + tdsYearMaster.getCode(), 5);
            monthListNew.put("11-" + tdsYearMaster.getCode(), 4);
            monthListNew.put("12-" + tdsYearMaster.getCode(), 3);
            monthListNew.put("01-" + (Integer.parseInt(tdsYearMaster.getCode())+1), 2);
            monthListNew.put("02-" + (Integer.parseInt(tdsYearMaster.getCode())+1), 1);
            monthListNew.put("03-" + (Integer.parseInt(tdsYearMaster.getCode())+1), 0);

            List<TdsGroupDetails> tdsGroupDetails = tdsGroupDetailsRepository.findAllYear(Integer.parseInt(tdsYearMaster.getCode()));
            Map<Long, Double> tdsGroupMasterMap = new TreeMap<>();
            Map<Long, TdsGroupMaster> tdsGroupMap = new TreeMap<>();
            for (TdsGroupDetails tdsGroupDetail : tdsGroupDetails) {
                if(tdsGroupDetail.getPerkCode().equalsIgnoreCase("80C-PF")) {
                    pfGroup = tdsGroupDetail;
                }
                if(tdsGroupDetail.getPerkCode().equalsIgnoreCase("80C-VPF")) {
                    vpfGroup = tdsGroupDetail;
                }
                tdsGroupMasterMap.put(tdsGroupDetail.getTdsGroupMaster().getId().longValue(), 0.0);
                tdsGroupMap.put(tdsGroupDetail.getTdsGroupMaster().getId().longValue(), tdsGroupDetail.getTdsGroupMaster());
            }
            List<TdsSlabMaster> tdsSlabMasters = tdsSlabMasterRepository.findByYear(tdsYearMaster.getCode());
            EmployeeView employeeView = employeeViewRepository.findByCardNo(tdsComputation.getCardNo()).orElse(null);

            int balanceMonth = 0;
            for (Long key : tdsGroupMasterMap.keySet()) {
                tdsGroupMasterMap.put(key, 0.0);
            }
            List<MonthlyFinance> monthlyFinances = monthlyRepository.findAllByPan(employeeView.getPan(), new SimpleDateFormat("MM-yyyy").format(new Date()));

            Double basic = 0.0;
            Double hra = 0.0;
            Double convAll = 0.0;
            Double others = 0.0;
            Double splAllow = 0.0;
            Double medical = 0.0;

            Double TDSDeducted = 0.0;

            Double rentDeclare = 0.0;
            Double rentExempt = 0.0;

            Double pf = 0.0;
            Double vpf = 0.0;
            String regimeType = "NEW";
            Boolean indivisibleLock = true;
            List<Double> minExemptList = new ArrayList<>();
            DecimalFormat df = new DecimalFormat("#.##");

            if (monthlyFinances != null && monthlyFinances.size() > 0) {
                for (MonthlyFinance monthlyFinance : monthlyFinances) {
                    //Basic
                    if (monthlyFinance.getAll1() != null && monthlyFinance.getAll1().equalsIgnoreCase("Basic")) {
                        basic += monthlyFinance.getEarn1() + monthlyFinance.getArr1();
                    } else if (monthlyFinance.getAll2() != null && monthlyFinance.getAll2().equalsIgnoreCase("Basic")) {
                        basic += monthlyFinance.getEarn2() + monthlyFinance.getArr2();
                    } else if (monthlyFinance.getAll3() != null && monthlyFinance.getAll3().equalsIgnoreCase("Basic")) {
                        basic += monthlyFinance.getEarn3() + monthlyFinance.getArr3();
                    } else if (monthlyFinance.getAll4() != null && monthlyFinance.getAll4().equalsIgnoreCase("Basic")) {
                        basic += monthlyFinance.getEarn4() + monthlyFinance.getArr4();
                    } else if (monthlyFinance.getAll5() != null && monthlyFinance.getAll5().equalsIgnoreCase("Basic")) {
                        basic += monthlyFinance.getEarn5() + monthlyFinance.getArr5();
                    } else if (monthlyFinance.getAll6() != null && monthlyFinance.getAll6().equalsIgnoreCase("Basic")) {
                        basic += monthlyFinance.getEarn6() + monthlyFinance.getArr6();
                    } else if (monthlyFinance.getAll7() != null && monthlyFinance.getAll7().equalsIgnoreCase("Basic")) {
                        basic += monthlyFinance.getEarn7() + monthlyFinance.getArr7();
                    }

                    //HRA
                    if (monthlyFinance.getAll1() != null && monthlyFinance.getAll1().equalsIgnoreCase("HRA")) {
                        hra += monthlyFinance.getEarn1() + monthlyFinance.getArr1();
                    } else if (monthlyFinance.getAll2() != null && monthlyFinance.getAll2().equalsIgnoreCase("HRA")) {
                        hra += monthlyFinance.getEarn2() + monthlyFinance.getArr2();
                    } else if (monthlyFinance.getAll3() != null && monthlyFinance.getAll3().equalsIgnoreCase("HRA")) {
                        hra += monthlyFinance.getEarn3() + monthlyFinance.getArr3();
                    } else if (monthlyFinance.getAll4() != null && monthlyFinance.getAll4().equalsIgnoreCase("HRA")) {
                        hra += monthlyFinance.getEarn4() + monthlyFinance.getArr4();
                    } else if (monthlyFinance.getAll5() != null && monthlyFinance.getAll5().equalsIgnoreCase("HRA")) {
                        hra += monthlyFinance.getEarn5() + monthlyFinance.getArr5();
                    } else if (monthlyFinance.getAll6() != null && monthlyFinance.getAll6().equalsIgnoreCase("HRA")) {
                        hra += monthlyFinance.getEarn6() + monthlyFinance.getArr6();
                    } else if (monthlyFinance.getAll7() != null && monthlyFinance.getAll7().equalsIgnoreCase("HRA")) {
                        hra += monthlyFinance.getEarn7() + monthlyFinance.getArr7();
                    }

                    //Conv
                    if (monthlyFinance.getAll1() != null && monthlyFinance.getAll1().equalsIgnoreCase("Conv. Allow.")) {
                        convAll += monthlyFinance.getEarn1() + monthlyFinance.getArr1();
                    } else if (monthlyFinance.getAll2() != null && monthlyFinance.getAll2().equalsIgnoreCase("Conv. Allow.")) {
                        convAll += monthlyFinance.getEarn2() + monthlyFinance.getArr2();
                    } else if (monthlyFinance.getAll3() != null && monthlyFinance.getAll3().equalsIgnoreCase("Conv. Allow.")) {
                        convAll += monthlyFinance.getEarn3() + monthlyFinance.getArr3();
                    } else if (monthlyFinance.getAll4() != null && monthlyFinance.getAll4().equalsIgnoreCase("Conv. Allow.")) {
                        convAll += monthlyFinance.getEarn4() + monthlyFinance.getArr4();
                    } else if (monthlyFinance.getAll5() != null && monthlyFinance.getAll5().equalsIgnoreCase("Conv. Allow.")) {
                        convAll += monthlyFinance.getEarn5() + monthlyFinance.getArr5();
                    } else if (monthlyFinance.getAll6() != null && monthlyFinance.getAll6().equalsIgnoreCase("Conv. Allow.")) {
                        convAll += monthlyFinance.getEarn6() + monthlyFinance.getArr6();
                    } else if (monthlyFinance.getAll7() != null && monthlyFinance.getAll7().equalsIgnoreCase("Conv. Allow.")) {
                        convAll += monthlyFinance.getEarn7() + monthlyFinance.getArr7();
                    }

                    //Others
                    if (monthlyFinance.getAll1() != null &&
                        !monthlyFinance.getAll1().equalsIgnoreCase("Basic") &&
                        !monthlyFinance.getAll1().equalsIgnoreCase("HRA") &&
                        !monthlyFinance.getAll1().equalsIgnoreCase("Conv. Allow.") &&
                        !monthlyFinance.getAll1().equalsIgnoreCase("Spl Allow") &&
                        !monthlyFinance.getAll1().equalsIgnoreCase("Spl Allow.") &&
                        !monthlyFinance.getAll1().equalsIgnoreCase("Medical")) {
                        others += monthlyFinance.getEarn1() + monthlyFinance.getArr1();
                    }
                    if (monthlyFinance.getAll2() != null &&
                        !monthlyFinance.getAll2().equalsIgnoreCase("Basic") &&
                        !monthlyFinance.getAll2().equalsIgnoreCase("HRA") &&
                        !monthlyFinance.getAll2().equalsIgnoreCase("Conv. Allow.") &&
                        !monthlyFinance.getAll2().equalsIgnoreCase("Spl Allow") &&
                        !monthlyFinance.getAll2().equalsIgnoreCase("Spl Allow.") &&
                        !monthlyFinance.getAll2().equalsIgnoreCase("Medical")) {
                        others += monthlyFinance.getEarn2() + monthlyFinance.getArr2();
                    }
                    if (monthlyFinance.getAll3() != null &&
                        !monthlyFinance.getAll3().equalsIgnoreCase("Basic") &&
                        !monthlyFinance.getAll3().equalsIgnoreCase("HRA") &&
                        !monthlyFinance.getAll3().equalsIgnoreCase("Conv. Allow.") &&
                        !monthlyFinance.getAll3().equalsIgnoreCase("Spl Allow") &&
                        !monthlyFinance.getAll3().equalsIgnoreCase("Spl Allow.") &&
                        !monthlyFinance.getAll3().equalsIgnoreCase("Medical")) {
                        others += monthlyFinance.getEarn3() + monthlyFinance.getArr3();
                    }
                    if (monthlyFinance.getAll4() != null &&
                        !monthlyFinance.getAll4().equalsIgnoreCase("Basic") &&
                        !monthlyFinance.getAll4().equalsIgnoreCase("HRA") &&
                        !monthlyFinance.getAll4().equalsIgnoreCase("Conv. Allow.") &&
                        !monthlyFinance.getAll4().equalsIgnoreCase("Spl Allow") &&
                        !monthlyFinance.getAll4().equalsIgnoreCase("Spl Allow.") &&
                        !monthlyFinance.getAll4().equalsIgnoreCase("Medical")) {
                        others += monthlyFinance.getEarn4() + monthlyFinance.getArr4();
                    }
                    if (monthlyFinance.getAll5() != null &&
                        !monthlyFinance.getAll5().equalsIgnoreCase("Basic") &&
                        !monthlyFinance.getAll5().equalsIgnoreCase("HRA") &&
                        !monthlyFinance.getAll5().equalsIgnoreCase("Conv. Allow.") &&
                        !monthlyFinance.getAll5().equalsIgnoreCase("Spl Allow") &&
                        !monthlyFinance.getAll5().equalsIgnoreCase("Spl Allow.") &&
                        !monthlyFinance.getAll5().equalsIgnoreCase("Medical")) {
                        others += monthlyFinance.getEarn5() + monthlyFinance.getArr5();
                    }
                    if (monthlyFinance.getAll6() != null &&
                        !monthlyFinance.getAll6().equalsIgnoreCase("Basic") &&
                        !monthlyFinance.getAll6().equalsIgnoreCase("HRA") &&
                        !monthlyFinance.getAll6().equalsIgnoreCase("Conv. Allow.") &&
                        !monthlyFinance.getAll6().equalsIgnoreCase("Spl Allow") &&
                        !monthlyFinance.getAll6().equalsIgnoreCase("Spl Allow.") &&
                        !monthlyFinance.getAll6().equalsIgnoreCase("Medical")) {
                        others += monthlyFinance.getEarn6() + monthlyFinance.getArr6();
                    }

                    if (monthlyFinance.getAll7() != null &&
                        !monthlyFinance.getAll7().equalsIgnoreCase("Basic") &&
                        !monthlyFinance.getAll7().equalsIgnoreCase("HRA") &&
                        !monthlyFinance.getAll7().equalsIgnoreCase("Conv. Allow.") &&
                        !monthlyFinance.getAll7().equalsIgnoreCase("Spl Allow") &&
                        !monthlyFinance.getAll7().equalsIgnoreCase("Spl Allow.") &&
                        !monthlyFinance.getAll7().equalsIgnoreCase("Medical")) {
                        others += monthlyFinance.getEarn7() + monthlyFinance.getArr7();
                    }

                    //Special Allows
                    if (monthlyFinance.getAll1() != null && (monthlyFinance.getAll1().equalsIgnoreCase("Spl Allow") || monthlyFinance.getAll1().equalsIgnoreCase("Spl Allow."))) {
                        splAllow += monthlyFinance.getEarn1() + monthlyFinance.getArr1();
                    } else if (monthlyFinance.getAll2() != null && (monthlyFinance.getAll2().equalsIgnoreCase("Spl Allow") || monthlyFinance.getAll2().equalsIgnoreCase("Spl Allow."))) {
                        splAllow += monthlyFinance.getEarn2() + monthlyFinance.getArr2();
                    } else if (monthlyFinance.getAll3() != null && (monthlyFinance.getAll3().equalsIgnoreCase("Spl Allow") || monthlyFinance.getAll3().equalsIgnoreCase("Spl Allow."))) {
                        splAllow += monthlyFinance.getEarn3() + monthlyFinance.getArr3();
                    } else if (monthlyFinance.getAll4() != null && (monthlyFinance.getAll4().equalsIgnoreCase("Spl Allow") || monthlyFinance.getAll4().equalsIgnoreCase("Spl Allow."))) {
                        splAllow += monthlyFinance.getEarn4() + monthlyFinance.getArr4();
                    } else if (monthlyFinance.getAll5() != null && (monthlyFinance.getAll5().equalsIgnoreCase("Spl Allow") || monthlyFinance.getAll5().equalsIgnoreCase("Spl Allow."))) {
                        splAllow += monthlyFinance.getEarn5() + monthlyFinance.getArr5();
                    } else if (monthlyFinance.getAll6() != null && (monthlyFinance.getAll6().equalsIgnoreCase("Spl Allow") || monthlyFinance.getAll6().equalsIgnoreCase("Spl Allow."))) {
                        splAllow += monthlyFinance.getEarn6() + monthlyFinance.getArr6();
                    } else if (monthlyFinance.getAll7() != null && (monthlyFinance.getAll7().equalsIgnoreCase("Spl Allow") || monthlyFinance.getAll6().equalsIgnoreCase("Spl Allow."))) {
                        splAllow += monthlyFinance.getEarn7() + monthlyFinance.getArr7();
                    }

                    //Medical
                    if (monthlyFinance.getAll1() != null && monthlyFinance.getAll1().equalsIgnoreCase("Medical")) {
                        medical += monthlyFinance.getEarn1() + monthlyFinance.getArr1();
                    } else if (monthlyFinance.getAll2() != null && monthlyFinance.getAll2().equalsIgnoreCase("Medical")) {
                        medical += monthlyFinance.getEarn2() + monthlyFinance.getArr2();
                    } else if (monthlyFinance.getAll3() != null && monthlyFinance.getAll3().equalsIgnoreCase("Medical")) {
                        medical += monthlyFinance.getEarn3() + monthlyFinance.getArr3();
                    } else if (monthlyFinance.getAll4() != null && monthlyFinance.getAll4().equalsIgnoreCase("Medical")) {
                        medical += monthlyFinance.getEarn4() + monthlyFinance.getArr4();
                    } else if (monthlyFinance.getAll5() != null && monthlyFinance.getAll5().equalsIgnoreCase("Medical")) {
                        medical += monthlyFinance.getEarn5() + monthlyFinance.getArr5();
                    } else if (monthlyFinance.getAll6() != null && monthlyFinance.getAll6().equalsIgnoreCase("Medical")) {
                        medical += monthlyFinance.getEarn6() + monthlyFinance.getArr6();
                    } else if (monthlyFinance.getAll7() != null && monthlyFinance.getAll7().equalsIgnoreCase("Medical")) {
                        medical += monthlyFinance.getEarn7() + monthlyFinance.getArr7();
                    }

                    //TDS
                    if (monthlyFinance.getDall1() != null && monthlyFinance.getDall1().equalsIgnoreCase("T D S")) {
                        TDSDeducted += monthlyFinance.getDed1();
                    } else if (monthlyFinance.getDall2() != null && monthlyFinance.getDall2().equalsIgnoreCase("T D S")) {
                        TDSDeducted += monthlyFinance.getDed2();
                    } else if (monthlyFinance.getDall3() != null && monthlyFinance.getDall3().equalsIgnoreCase("T D S")) {
                        TDSDeducted += monthlyFinance.getDed3();
                    } else if (monthlyFinance.getDall4() != null && monthlyFinance.getDall4().equalsIgnoreCase("T D S")) {
                        TDSDeducted += monthlyFinance.getDed4();
                    } else if (monthlyFinance.getDall5() != null && monthlyFinance.getDall5().equalsIgnoreCase("T D S")) {
                        TDSDeducted += monthlyFinance.getDed5();
                    } else if (monthlyFinance.getDall6() != null && monthlyFinance.getDall6().equalsIgnoreCase("T D S")) {
                        TDSDeducted += monthlyFinance.getDed6();
                    }

                    //PF
                    if (monthlyFinance.getDall1() != null && monthlyFinance.getDall1().equalsIgnoreCase("P F")) {
                        pf += monthlyFinance.getDed1();
                    } else if (monthlyFinance.getDall2() != null && monthlyFinance.getDall2().equalsIgnoreCase("P F")) {
                        pf += monthlyFinance.getDed2();
                    } else if (monthlyFinance.getDall3() != null && monthlyFinance.getDall3().equalsIgnoreCase("P F")) {
                        pf += monthlyFinance.getDed3();
                    } else if (monthlyFinance.getDall4() != null && monthlyFinance.getDall4().equalsIgnoreCase("P F")) {
                        pf += monthlyFinance.getDed4();
                    } else if (monthlyFinance.getDall5() != null && monthlyFinance.getDall5().equalsIgnoreCase("P F")) {
                        pf += monthlyFinance.getDed5();
                    } else if (monthlyFinance.getDall6() != null && monthlyFinance.getDall6().equalsIgnoreCase("P F")) {
                        pf += monthlyFinance.getDed6();
                    }

                    //VPF
                    if (monthlyFinance.getDall1() != null && (monthlyFinance.getDall1().equalsIgnoreCase("E P S") || monthlyFinance.getDall1().equalsIgnoreCase("V P F"))) {
                        vpf += monthlyFinance.getDed1();
                    } else if (monthlyFinance.getDall2() != null && (monthlyFinance.getDall2().equalsIgnoreCase("E P S") || monthlyFinance.getDall2().equalsIgnoreCase("V P F"))) {
                        vpf += monthlyFinance.getDed2();
                    } else if (monthlyFinance.getDall3() != null && (monthlyFinance.getDall3().equalsIgnoreCase("E P S") || monthlyFinance.getDall3().equalsIgnoreCase("V P F"))) {
                        vpf += monthlyFinance.getDed3();
                    } else if (monthlyFinance.getDall4() != null && (monthlyFinance.getDall4().equalsIgnoreCase("E P S") || monthlyFinance.getDall4().equalsIgnoreCase("V P F"))) {
                        vpf += monthlyFinance.getDed4();
                    } else if (monthlyFinance.getDall5() != null && (monthlyFinance.getDall5().equalsIgnoreCase("E P S") || monthlyFinance.getDall5().equalsIgnoreCase("V P F"))) {
                        vpf += monthlyFinance.getDed5();
                    } else if (monthlyFinance.getDall6() != null && (monthlyFinance.getDall6().equalsIgnoreCase("E P S") || monthlyFinance.getDall6().equalsIgnoreCase("V P F"))) {
                        vpf += monthlyFinance.getDed6();
                    }
                }
            }
            SalaryRate salaryRate = salaryRateRepository.findByLastDetail(employeeView.getEmpCode(), employeeView.getEmpCode());
            if (monthlyFinances.size() > 0 && salaryRate != null) {
                balanceMonth = monthListNew.get(monthlyFinances.get(monthlyFinances.size() - 1).getId().getMonthYear());
                basic += (salaryRate.getAmt1() * balanceMonth);
                hra += (salaryRate.getAmt2() * balanceMonth);
                convAll += (salaryRate.getAmt3() * balanceMonth);
                others += ((salaryRate.getAmt4() * balanceMonth) + (salaryRate.getAmt7() * balanceMonth));
                splAllow += (salaryRate.getAmt5() * balanceMonth);
                medical += (salaryRate.getAmt6() * balanceMonth);
                vpf += (employeeView.getPfVolFix() * balanceMonth);
                if (employeeView.getPfallowed() == 1) {
                    double amt = salaryRate.getAmt1() * 12 / 100;
                    if (amt > 1800) {
                        amt = 1800;
                    }
                    pf += (amt * balanceMonth);
                }
            } else if (salaryRate != null) {
                balanceMonth = monthList.get(new SimpleDateFormat("MM-yyyy").format(new Date()));
                basic += (salaryRate.getAmt1() * balanceMonth);
                hra += (salaryRate.getAmt2() * balanceMonth);
                convAll += (salaryRate.getAmt3() * balanceMonth);
                others += ((salaryRate.getAmt4() * balanceMonth) + (salaryRate.getAmt7() * balanceMonth));
                splAllow += (salaryRate.getAmt5() * balanceMonth);
                medical += (salaryRate.getAmt6() * balanceMonth);
                vpf += (employeeView.getPfVolFix() * balanceMonth);
                if (employeeView.getPfallowed() == 1) {
                    double amt = salaryRate.getAmt1() * 12 / 100;
                    if (amt > 1800) {
                        amt = 1800;
                    }
                    pf += (amt * balanceMonth);
                }
            }
            if (tdsGroupDetails != null && tdsGroupDetails.size() > 0) {
                for (TdsGroupDetails tdsGroupDetail : tdsGroupDetails) {
                    if (tdsGroupDetail.getPerkCode() != null && tdsGroupDetail.getPerkCode().equalsIgnoreCase("80C-PF")) {
                        TdsDeclarationBreakup tdsDeclarationBreakup = tdsDeclarationBreakupRepository.findByCardNoAndTdsGroupDetails(employeeView.getCardNo(), tdsGroupDetail.getId(), 0L);
                        if (tdsDeclarationBreakup != null) {
                            tdsDeclarationBreakup.setAmount(pf);
                            tdsDeclarationBreakupRepository.save(tdsDeclarationBreakup);
                        } else {
                            tdsDeclarationBreakup = new TdsDeclarationBreakup();
                            tdsDeclarationBreakup.setCardNo(employeeView.getCardNo());
                            tdsDeclarationBreakup.setAmount(pf);
                            tdsDeclarationBreakup.setCreatedBy("AUTO");
                            tdsDeclarationBreakup.setCreatedDate(Instant.now());
                            tdsDeclarationBreakup.setTdsGroupDetails(tdsGroupDetail);
                            tdsDeclarationBreakup.setPreviousEmpDtlsId(0L);
                            tdsDeclarationBreakupRepository.save(tdsDeclarationBreakup);
                        }
                    } else if (tdsGroupDetail.getPerkCode() != null && tdsGroupDetail.getPerkCode().equalsIgnoreCase("80C-VPF")) {
                        TdsDeclarationBreakup tdsDeclarationBreakup = tdsDeclarationBreakupRepository.findByCardNoAndTdsGroupDetails(employeeView.getCardNo(), tdsGroupDetail.getId(), 0L);
                        if (tdsDeclarationBreakup != null) {
                            tdsDeclarationBreakup.setAmount(vpf);
                            tdsDeclarationBreakupRepository.save(tdsDeclarationBreakup);
                        } else {
                            tdsDeclarationBreakup = new TdsDeclarationBreakup();
                            tdsDeclarationBreakup.setCardNo(employeeView.getCardNo());
                            tdsDeclarationBreakup.setAmount(vpf);
                            tdsDeclarationBreakup.setCreatedBy("AUTO");
                            tdsDeclarationBreakup.setCreatedDate(Instant.now());
                            tdsDeclarationBreakup.setTdsGroupDetails(tdsGroupDetail);
                            tdsDeclarationBreakup.setPreviousEmpDtlsId(0L);
                            tdsDeclarationBreakupRepository.save(tdsDeclarationBreakup);
                        }
                    }
                }
            }


            List<PreviousEmploymentDetails> previousEmploymentDetails = previousEmploymentDetailsRepository.findAllByCardNoAndYear(employeeView.getCardNo(), tdsYearMaster.getCode());
            if (previousEmploymentDetails != null && previousEmploymentDetails.size() > 0) {
                for (PreviousEmploymentDetails employmentDetails : previousEmploymentDetails) {
                    if (employmentDetails.getBasic() != null && employmentDetails.getBasic() > 0) {
                        basic += employmentDetails.getBasic();
                    }
                    if (employmentDetails.getHra() != null && employmentDetails.getHra() > 0) {
                        hra += employmentDetails.getHra();
                    }
                    if (employmentDetails.getCta() != null && employmentDetails.getCta() > 0) {
                        convAll += employmentDetails.getCta();
                    }
                    if (employmentDetails.getOthers() != null && employmentDetails.getOthers() > 0) {
                        others += employmentDetails.getOthers();
                    }
                    if (employmentDetails.getSpa() != null && employmentDetails.getSpa() > 0) {
                        splAllow += employmentDetails.getSpa();
                    }
                    if (employmentDetails.getMda() != null && employmentDetails.getMda() > 0) {
                        medical += employmentDetails.getMda();
                    }


                    if (employmentDetails.getEpf() != null && employmentDetails.getEpf() > 0) {
                        pf += employmentDetails.getEpf();
                    }
                    if (employmentDetails.getVpf() != null && employmentDetails.getVpf() > 0) {
                        vpf += employmentDetails.getVpf();
                    }

                    if (employmentDetails.getMonthRent() != null && employmentDetails.getMonthRent() > 0) {
                        rentDeclare += employmentDetails.getMonthRent();
                    }

                    if (employmentDetails.getTds() != null && employmentDetails.getTds() > 0) {
                        TDSDeducted += employmentDetails.getTds();
                    }

                    if (tdsGroupDetails != null && tdsGroupDetails.size() > 0) {
                        for (TdsGroupDetails tdsGroupDetail : tdsGroupDetails) {
                            if (tdsGroupDetail.getPerkCode() != null && tdsGroupDetail.getPerkCode().equalsIgnoreCase("80C-PF")) {
                                TdsDeclarationBreakup tdsDeclarationBreakup = tdsDeclarationBreakupRepository.findByCardNoAndTdsGroupDetails(employeeView.getCardNo(), tdsGroupDetail.getId(), employmentDetails.getId());
                                if (tdsDeclarationBreakup != null) {
                                    tdsDeclarationBreakup.setAmount(employmentDetails.getEpf());
                                    tdsDeclarationBreakupRepository.save(tdsDeclarationBreakup);
                                } else {
                                    tdsDeclarationBreakup = new TdsDeclarationBreakup();
                                    tdsDeclarationBreakup.setCardNo(employeeView.getCardNo());
                                    tdsDeclarationBreakup.setAmount(employmentDetails.getVpf());
                                    tdsDeclarationBreakup.setCreatedBy("AUTO");
                                    tdsDeclarationBreakup.setCreatedDate(Instant.now());
                                    tdsDeclarationBreakup.setTdsGroupDetails(tdsGroupDetail);
                                    tdsDeclarationBreakup.setPreviousEmpDtlsId(employmentDetails.getId());
                                    tdsDeclarationBreakupRepository.save(tdsDeclarationBreakup);
                                }
                            } else if (tdsGroupDetail.getPerkCode() != null && tdsGroupDetail.getPerkCode().equalsIgnoreCase("80C-VPF")) {
                                TdsDeclarationBreakup tdsDeclarationBreakup = tdsDeclarationBreakupRepository.findByCardNoAndTdsGroupDetails(employeeView.getCardNo(), tdsGroupDetail.getId(), employmentDetails.getId());
                                if (tdsDeclarationBreakup != null) {
                                    tdsDeclarationBreakup.setAmount(vpf);
                                    tdsDeclarationBreakupRepository.save(tdsDeclarationBreakup);
                                } else {
                                    tdsDeclarationBreakup = new TdsDeclarationBreakup();
                                    tdsDeclarationBreakup.setCardNo(employeeView.getCardNo());
                                    tdsDeclarationBreakup.setAmount(vpf);
                                    tdsDeclarationBreakup.setCreatedBy("AUTO");
                                    tdsDeclarationBreakup.setCreatedDate(Instant.now());
                                    tdsDeclarationBreakup.setTdsGroupDetails(tdsGroupDetail);
                                    tdsDeclarationBreakup.setPreviousEmpDtlsId(employmentDetails.getId());
                                    tdsDeclarationBreakupRepository.save(tdsDeclarationBreakup);
                                }
                            }
                        }
                    }
                }
            }

            tdsComputation.setEarnLabel1("Basic");
            tdsComputation.setEarnAmount1(basic);
            tdsComputation.setEarnLabel2("House Rent Allowance");
            tdsComputation.setEarnAmount2(hra);
            tdsComputation.setEarnLabel3("Conveyance/Transport Allowance");
            tdsComputation.setEarnAmount3(convAll);
            tdsComputation.setEarnLabel4("Others");
            tdsComputation.setEarnAmount4(others);
            tdsComputation.setEarnLabel5("Special Allowance");
            tdsComputation.setEarnAmount5(splAllow);
            tdsComputation.setEarnLabel6("Medical Allowance");
            tdsComputation.setEarnAmount6(medical);
            tdsComputation.setPendingMonth(balanceMonth);

            Double incentive = 0.0;
            Double previousEmployer = 0.0;
            List<TdsDeclaration> tdsDeclaration = tdsDeclarationRepository.findAllByCardNo(employeeView.getCardNo(), Integer.parseInt(tdsYearMaster.getCode()));
            if (tdsDeclaration.size() > 0) {
                TdsDeclaration tdsDeclarations = tdsDeclaration.get(0);

                tdsComputation.setIncentiveAmount(tdsDeclarations.getIncentiveAmount() != null ? tdsDeclarations.getIncentiveAmount() : new BigDecimal(0));
                tdsComputation.setPreviousEmployerAmount(tdsDeclarations.getPreviousEmployerAmount() != null ? tdsDeclarations.getPreviousEmployerAmount() : new BigDecimal(0));
                tdsComputation.setPreviousEmployerTdsDeduction(tdsDeclarations.getPreviousEmployerTdsDeduction() != null ? tdsDeclarations.getPreviousEmployerTdsDeduction() : new BigDecimal(0));
                incentive = tdsComputation.getIncentiveAmount().doubleValue();
                previousEmployer = tdsComputation.getPreviousEmployerAmount().doubleValue();

                if (tdsDeclarations.getTempLock() != null && tdsDeclarations.getTempLock().equalsIgnoreCase("Y")) {
                } else {
                    indivisibleLock = false;
                }
                regimeType = tdsDeclarations.getRegimeType();
                if (tdsYearMaster.getRegime() != null && tdsYearMaster.getRegime().booleanValue() == true) {
                    if(tdsDeclarations.getRegimeType() != null && tdsDeclarations.getRegimeType().equalsIgnoreCase("OLD")) {
                        if (tdsDeclarations.getMonthRent() != null && tdsDeclarations.getMonthRent().length() > 0 && StringUtils.isNumeric(tdsDeclarations.getMonthRent()) && Double.parseDouble(tdsDeclarations.getMonthRent()) > 0) {
                            rentDeclare += Double.parseDouble(tdsDeclarations.getMonthRent()) * (monthlyFinances.size() + balanceMonth);
                            String rentAddress = "";
                            if (tdsDeclarations.getLandLoardAddress() != null) {
                                rentAddress = tdsDeclarations.getLandLoardAddress().toUpperCase();
                            }
                            minExemptList.add(hra);
                            minExemptList.add(rentDeclare - ((basic) * 0.1));
                            if (rentAddress.contains("DELHI") || rentAddress.contains("MUMBAI") || rentAddress.contains("CHENNAI") || rentAddress.contains("KOLKATA")) {
                                minExemptList.add(basic * 0.5);
                            } else {
                                minExemptList.add(basic * 0.4);
                            }
                            rentExempt = Collections.min(minExemptList);
                            if (rentDeclare > rentExempt) {
                            } else if (rentDeclare < rentExempt) {
                                rentExempt = rentDeclare;
                            }
                        }
                    }
                } else {
                    if (tdsDeclarations.getMonthRent() != null && tdsDeclarations.getMonthRent().length() > 0 && StringUtils.isNumeric(tdsDeclarations.getMonthRent()) && Double.parseDouble(tdsDeclarations.getMonthRent()) > 0) {
                        rentDeclare += Double.parseDouble(tdsDeclarations.getMonthRent()) * (monthlyFinances.size() + balanceMonth);
                        String rentAddress = "";
                        if (tdsDeclarations.getLandLoardAddress() != null) {
                            rentAddress = tdsDeclarations.getLandLoardAddress().toUpperCase();
                        }
                        minExemptList.add(hra);
                        minExemptList.add(rentDeclare - ((basic) * 0.1));
                        if (rentAddress.contains("DELHI") || rentAddress.contains("MUMBAI") || rentAddress.contains("CHENNAI") || rentAddress.contains("KOLKATA")) {
                            minExemptList.add(basic * 0.5);
                        } else {
                            minExemptList.add(basic * 0.4);
                        }
                        rentExempt = Collections.min(minExemptList);
                        if (rentDeclare > rentExempt) {
                        } else if (rentDeclare < rentExempt) {
                            rentExempt = rentDeclare;
                        }
                    }
                }

                boolean pfExist = false;
                boolean vpfExist = false;
                for (TdsDeclaration declaration : tdsDeclaration) {
                    if (declaration.getTdsGroupDetails().getPerkCode().equalsIgnoreCase("80C-PF")) {
                        pfExist = true;
                        declaration.setAmount(pf);
                        tdsDeclarationRepository.save(declaration);
                    } else if (declaration.getTdsGroupDetails().getPerkCode().equalsIgnoreCase("80C-VPF")) {
                        vpfExist = true;
                        declaration.setAmount(vpf);
                        tdsDeclarationRepository.save(declaration);
                    }
                }

                if (pfExist == false) {
                    TdsDeclaration declarationPF = new TdsDeclaration();
                    BeanUtils.copyProperties(tdsDeclarations, declarationPF);
                    declarationPF.setAmount(pf);
                    declarationPF.setId(null);
                    declarationPF.setTdsGroupDetails(pfGroup);
                    TdsDeclaration result = tdsDeclarationRepository.save(declarationPF);
                    tdsDeclaration.add(result);
                }
                if (vpfExist == false) {
                    TdsDeclaration declarationVPF = new TdsDeclaration();
                    BeanUtils.copyProperties(tdsDeclarations, declarationVPF);
                    declarationVPF.setAmount(vpf);
                    declarationVPF.setId(null);
                    declarationVPF.setTdsGroupDetails(vpfGroup);
                    TdsDeclaration result = tdsDeclarationRepository.save(declarationVPF);
                    tdsDeclaration.add(result);
                }
            } else {
                tdsComputation.setIncentiveAmount(new BigDecimal(0));
                tdsComputation.setPreviousEmployerAmount(new BigDecimal(0));
                tdsComputation.setPreviousEmployerTdsDeduction(new BigDecimal(0));
                incentive = tdsComputation.getIncentiveAmount().doubleValue();
                previousEmployer = tdsComputation.getPreviousEmployerAmount().doubleValue();
                for (TdsGroupDetails groupDetails : tdsGroupDetails) {
                    TdsDeclaration declaration = new TdsDeclaration();
                    declaration.setCardNo(employeeView.getCardNo());
                    declaration.setTdsGroupDetails(groupDetails);
                    if (declaration.getTdsGroupDetails().getPerkCode().equalsIgnoreCase("80C-PF")) {
                        declaration.setAmount(pf);
                    } else if (declaration.getTdsGroupDetails().getPerkCode().equalsIgnoreCase("80C-VPF")) {
                        declaration.setAmount(vpf);
                    } else {
                        declaration.setAmount(0.0);
                    }
                    declaration.setCreatedBy("AUTO");
                    declaration.setCreatedDate(Instant.now());
                    TdsDeclaration result = tdsDeclarationRepository.save(declaration);
                    tdsDeclaration.add(result);
                }
            }


            tdsComputation.setRentDeclare(rentDeclare);

            tdsComputation.setRentExempt(rentExempt);

            tdsComputation.setEarnTotal((basic + hra + convAll + others + splAllow + medical + previousEmployer + incentive) - rentExempt);

            if ((regimeType != null && regimeType.equalsIgnoreCase("OLD")) || (tdsYearMaster.getRegime() != null && tdsYearMaster.getRegime().booleanValue() == false)) {
                if (tdsYearMaster.getStandardDeduction() != null && tdsYearMaster.getStandardDeduction().doubleValue() > 0) {
                    tdsComputation.setStandardDeduction(tdsYearMaster.getStandardDeduction());
                    tdsComputation.setStandardTotal(tdsComputation.getEarnTotal() - tdsYearMaster.getStandardDeduction());
                } else {
                    tdsComputation.setStandardDeduction(0.0);
                    tdsComputation.setStandardTotal(tdsComputation.getEarnTotal() - 0.0);
                }
            } else {
                if (tdsYearMaster.getStandardDeduction() != null && tdsYearMaster.getStandardDeduction().doubleValue() > 0) {
                    tdsComputation.setStandardDeduction(tdsYearMaster.getStandardDeduction());
                    tdsComputation.setStandardTotal(tdsComputation.getEarnTotal() - tdsYearMaster.getStandardDeduction());
                } else {
                    tdsComputation.setStandardDeduction(0.0);
                    tdsComputation.setStandardTotal(tdsComputation.getEarnTotal() - 0.0);
                }
            }

            for (Long key : tdsGroupMasterMap.keySet()) {
                for (TdsDeclaration tdsDeclarations : tdsDeclaration) {
                    if (tdsDeclarations.getTdsGroupDetails()!= null && tdsDeclarations.getTdsGroupDetails().getTdsGroupMaster() != null && key != null && tdsDeclarations.getTdsGroupDetails().getTdsGroupMaster().getId().longValue() == key.longValue()) {
                        if (tdsDeclarations.getAmount() != null) {
                            Double value = tdsGroupMasterMap.get(key);
                            if (tdsDeclarations.getTdsGroupDetails().getPerkLimit() != null && Double.parseDouble(tdsDeclarations.getTdsGroupDetails().getPerkLimit()) > 0) {
                                if (tdsDeclarations.getAmount().doubleValue() > Double.parseDouble(tdsDeclarations.getTdsGroupDetails().getPerkLimit())) {
                                    tdsGroupMasterMap.put(key, value + Double.parseDouble(tdsDeclarations.getTdsGroupDetails().getPerkLimit()));
                                } else {
                                    tdsGroupMasterMap.put(key, value + tdsDeclarations.getAmount());
                                }
                            } else {
                                tdsGroupMasterMap.put(key, value + tdsDeclarations.getAmount());
                            }
                        }
                    }
                }
            }

            int ctr = 0;
            Double deductIncome = 0.0;
            for (Long key : tdsGroupMasterMap.keySet()) {
                if (ctr == 0) {
                    tdsComputation.setDeductCode1(key + "");
                    if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                        tdsComputation.setDeductAmount1(tdsGroupMap.get(key).getGroupLimit());
                        deductIncome += tdsGroupMap.get(key).getGroupLimit();
                    } else {
                        tdsComputation.setDeductAmount1(tdsGroupMasterMap.get(key).doubleValue());
                        deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                    }
                    ++ctr;
                } else if (ctr == 1) {
                    tdsComputation.setDeductCode2(key + "");
                    if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                        tdsComputation.setDeductAmount2(tdsGroupMap.get(key).getGroupLimit());
                        deductIncome += tdsGroupMap.get(key).getGroupLimit();
                    } else {
                        tdsComputation.setDeductAmount2(tdsGroupMasterMap.get(key).doubleValue());
                        deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                    }
                    ++ctr;
                } else if (ctr == 2) {
                    tdsComputation.setDeductCode3(key + "");
                    if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                        tdsComputation.setDeductAmount3(tdsGroupMap.get(key).getGroupLimit());
                        deductIncome += tdsGroupMap.get(key).getGroupLimit();
                    } else {
                        tdsComputation.setDeductAmount3(tdsGroupMasterMap.get(key).doubleValue());
                        deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                    }
                    ++ctr;
                } else if (ctr == 3) {
                    tdsComputation.setDeductCode4(key + "");
                    if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                        tdsComputation.setDeductAmount4(tdsGroupMap.get(key).getGroupLimit());
                        deductIncome += tdsGroupMap.get(key).getGroupLimit();
                    } else {
                        tdsComputation.setDeductAmount4(tdsGroupMasterMap.get(key).doubleValue());
                        deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                    }
                    ++ctr;
                } else if (ctr == 4) {
                    tdsComputation.setDeductCode5(key + "");
                    if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                        tdsComputation.setDeductAmount5(tdsGroupMap.get(key).getGroupLimit());
                        deductIncome += tdsGroupMap.get(key).getGroupLimit();
                    } else {
                        tdsComputation.setDeductAmount5(tdsGroupMasterMap.get(key).doubleValue());
                        deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                    }
                    ++ctr;
                } else if (ctr == 5) {
                    tdsComputation.setDeductCode6(key + "");
                    if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                        tdsComputation.setDeductAmount6(tdsGroupMap.get(key).getGroupLimit());
                        deductIncome += tdsGroupMap.get(key).getGroupLimit();
                    } else {
                        tdsComputation.setDeductAmount6(tdsGroupMasterMap.get(key).doubleValue());
                        deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                    }
                    ++ctr;
                } else if (ctr == 6) {
                    tdsComputation.setDeductCode7(key + "");
                    if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                        tdsComputation.setDeductAmount7(tdsGroupMap.get(key).getGroupLimit());
                        deductIncome += tdsGroupMap.get(key).getGroupLimit();
                    } else {
                        tdsComputation.setDeductAmount7(tdsGroupMasterMap.get(key).doubleValue());
                        deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                    }
                    ++ctr;
                } else if (ctr == 7) {
                    tdsComputation.setDeductCode8(key + "");
                    if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                        tdsComputation.setDeductAmount8(tdsGroupMap.get(key).getGroupLimit());
                        deductIncome += tdsGroupMap.get(key).getGroupLimit();
                    } else {
                        tdsComputation.setDeductAmount8(tdsGroupMasterMap.get(key).doubleValue());
                        deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                    }
                    ++ctr;
                } else if (ctr == 8) {
                    tdsComputation.setDeductCode9(key + "");
                    if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                        tdsComputation.setDeductAmount9(tdsGroupMap.get(key).getGroupLimit());
                        deductIncome += tdsGroupMap.get(key).getGroupLimit();
                    } else {
                        tdsComputation.setDeductAmount9(tdsGroupMasterMap.get(key).doubleValue());
                        deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                    }
                    ++ctr;
                } else if (ctr == 9) {
                    tdsComputation.setDeductCode10(key + "");
                    if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                        tdsComputation.setDeductAmount10(tdsGroupMap.get(key).getGroupLimit());
                        deductIncome += tdsGroupMap.get(key).getGroupLimit();
                    } else {
                        tdsComputation.setDeductAmount10(tdsGroupMasterMap.get(key).doubleValue());
                        deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                    }
                    ++ctr;
                } else if (ctr == 10) {
                    tdsComputation.setDeductCode11(key + "");
                    if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                        tdsComputation.setDeductAmount11(tdsGroupMap.get(key).getGroupLimit());
                        deductIncome += tdsGroupMap.get(key).getGroupLimit();
                    } else {
                        tdsComputation.setDeductAmount11(tdsGroupMasterMap.get(key).doubleValue());
                        deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                    }
                    ++ctr;
                } else if (ctr == 11) {
                    tdsComputation.setDeductCode12(key + "");
                    if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                        tdsComputation.setDeductAmount12(tdsGroupMap.get(key).getGroupLimit());
                        deductIncome += tdsGroupMap.get(key).getGroupLimit();
                    } else {
                        tdsComputation.setDeductAmount12(tdsGroupMasterMap.get(key).doubleValue());
                        deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                    }
                    ++ctr;
                } else if (ctr == 12) {
                    tdsComputation.setDeductCode13(key + "");
                    if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                        tdsComputation.setDeductAmount13(tdsGroupMap.get(key).getGroupLimit());
                        deductIncome += tdsGroupMap.get(key).getGroupLimit();
                    } else {
                        tdsComputation.setDeductAmount13(tdsGroupMasterMap.get(key).doubleValue());
                        deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                    }
                    ++ctr;
                } else if (ctr == 13) {
                    tdsComputation.setDeductCode14(key + "");
                    if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                        tdsComputation.setDeductAmount14(tdsGroupMap.get(key).getGroupLimit());
                        deductIncome += tdsGroupMap.get(key).getGroupLimit();
                    } else {
                        tdsComputation.setDeductAmount14(tdsGroupMasterMap.get(key).doubleValue());
                        deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                    }
                    ++ctr;
                } else if (ctr == 14) {
                    tdsComputation.setDeductCode15(key + "");
                    if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                        tdsComputation.setDeductAmount15(tdsGroupMap.get(key).getGroupLimit());
                        deductIncome += tdsGroupMap.get(key).getGroupLimit();
                    } else {
                        tdsComputation.setDeductAmount15(tdsGroupMasterMap.get(key).doubleValue());
                        deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                    }
                    ++ctr;
                } else if (ctr == 15) {
                    tdsComputation.setDeductCode16(key + "");
                    if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                        tdsComputation.setDeductAmount16(tdsGroupMap.get(key).getGroupLimit());
                        deductIncome += tdsGroupMap.get(key).getGroupLimit();
                    } else {
                        tdsComputation.setDeductAmount16(tdsGroupMasterMap.get(key).doubleValue());
                        deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                    }
                } else if (ctr == 16) {
                    tdsComputation.setDeductCode17(key + "");
                    if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                        tdsComputation.setDeductAmount17(tdsGroupMap.get(key).getGroupLimit());
                        deductIncome += tdsGroupMap.get(key).getGroupLimit();
                    } else {
                        tdsComputation.setDeductAmount17(tdsGroupMasterMap.get(key).doubleValue());
                        deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                    }
                    ++ctr;
                } else if (ctr == 17) {
                    tdsComputation.setDeductCode18(key + "");
                    if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                        tdsComputation.setDeductAmount18(tdsGroupMap.get(key).getGroupLimit());
                        deductIncome += tdsGroupMap.get(key).getGroupLimit();
                    } else {
                        tdsComputation.setDeductAmount18(tdsGroupMasterMap.get(key).doubleValue());
                        deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                    }
                    ++ctr;
                } else if (ctr == 18) {
                    tdsComputation.setDeductCode19(key + "");
                    if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                        tdsComputation.setDeductAmount19(tdsGroupMap.get(key).getGroupLimit());
                        deductIncome += tdsGroupMap.get(key).getGroupLimit();
                    } else {
                        tdsComputation.setDeductAmount19(tdsGroupMasterMap.get(key).doubleValue());
                        deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                    }
                    ++ctr;
                } else if (ctr == 19) {
                    tdsComputation.setDeductCode20(key + "");
                    if (tdsGroupMap.get(key).getGroupLimit() != null && tdsGroupMasterMap.get(key).doubleValue() > 0 && (tdsGroupMasterMap.get(key).doubleValue() > tdsGroupMap.get(key).getGroupLimit().doubleValue())) {
                        tdsComputation.setDeductAmount20(tdsGroupMap.get(key).getGroupLimit());
                        deductIncome += tdsGroupMap.get(key).getGroupLimit();
                    } else {
                        tdsComputation.setDeductAmount20(tdsGroupMasterMap.get(key).doubleValue());
                        deductIncome += tdsGroupMasterMap.get(key).doubleValue();
                    }
                    ++ctr;
                }
            }

            if ((regimeType != null && regimeType.equalsIgnoreCase("OLD")) || (tdsYearMaster.getRegime() != null && tdsYearMaster.getRegime().booleanValue() == false)) {
                if (tdsYearMaster.getStandardDeduction() != null && tdsYearMaster.getStandardDeduction().doubleValue() > 0) {
                    tdsComputation.setTotalTaxIncome(tdsComputation.getStandardTotal() - deductIncome);
                } else {
                    tdsComputation.setTotalTaxIncome(tdsComputation.getEarnTotal() - deductIncome);
                }
            } else {
                tdsComputation.setTotalTaxIncome(tdsComputation.getStandardTotal() - 0.0);
            }
            List<TdsSlabMaster> slabMasterList = null;
            LocalDate dob = employeeView.getDob().toLocalDate();
            Period diff = Period.between(dob, LocalDate.now());
            if (employeeView.getMf().equalsIgnoreCase("1")) {
                slabMasterList = getValidTdsSlab(tdsSlabMasters, "M", diff.getYears(), regimeType, tdsYearMaster.getRegime());
            } else {
                slabMasterList = getValidTdsSlab(tdsSlabMasters, "F", diff.getYears(), regimeType, tdsYearMaster.getRegime());
            }

            Double taxValue = 0.0;
            Double cess = 0.0;
            if (slabMasterList.size() > 0) {
                if (tdsComputation.getTotalTaxIncome() > slabMasterList.get(0).getExemptionLimit()) {
                    Double totalIncome = tdsComputation.getTotalTaxIncome();
                    Double balance = 0.0;
                    Double previousBalance = 0.0;
                    for (TdsSlabMaster tdsSlabMaster : slabMasterList) {
                        if (totalIncome >= tdsSlabMaster.getAmountStart() && totalIncome >= tdsSlabMaster.getAmountEnd() && previousBalance == 0) {
                            totalIncome = tdsComputation.getTotalTaxIncome() - tdsSlabMaster.getAmountEnd();
                            previousBalance = tdsSlabMaster.getAmountEnd();
                            balance = tdsSlabMaster.getAmountEnd();
                            Double taxValueTemp = (balance * tdsSlabMaster.getTaxPercentage()) / 100;
                            Double cessTemp = (taxValueTemp * tdsSlabMaster.getTaxSurcharge()) / 100;
                            taxValue += taxValueTemp;
                            cess += cessTemp;
                        } else if (previousBalance < tdsSlabMaster.getAmountStart() && (totalIncome + previousBalance) >= tdsSlabMaster.getAmountEnd()) {
                            totalIncome = tdsComputation.getTotalTaxIncome() - tdsSlabMaster.getAmountEnd();
                            balance = tdsSlabMaster.getAmountEnd() - previousBalance;
                            previousBalance = tdsSlabMaster.getAmountEnd();
                            Double taxValueTemp = (balance * tdsSlabMaster.getTaxPercentage()) / 100;
                            Double cessTemp = (taxValueTemp * tdsSlabMaster.getTaxSurcharge()) / 100;
                            taxValue += taxValueTemp;
                            cess += cessTemp;
                        } else if (totalIncome > 0 && previousBalance < tdsSlabMaster.getAmountStart() && (totalIncome + previousBalance) < tdsSlabMaster.getAmountEnd()) {
                            balance = totalIncome;
                            previousBalance = tdsSlabMaster.getAmountEnd();
                            totalIncome = tdsComputation.getTotalTaxIncome() - tdsSlabMaster.getAmountEnd();
                            Double taxValueTemp = (balance * tdsSlabMaster.getTaxPercentage()) / 100;
                            Double cessTemp = (taxValueTemp * tdsSlabMaster.getTaxSurcharge()) / 100;
                            taxValue += taxValueTemp;
                            cess += cessTemp;
                            totalIncome = 0.0;
                        }
                    }
                } else {
                    taxValue = 0.0;
                    cess = 0.0;
                }
            }
            tdsComputation.setTaxValue(Double.valueOf(df.format(taxValue)));
            tdsComputation.setCessValue(Double.valueOf(df.format(cess)));
            tdsComputation.setTotalTaxLiability(Double.valueOf(df.format(taxValue + cess)));
            tdsComputation.setTaxDeductValue(TDSDeducted);
            tdsComputation.setBalanceTaxValue(Double.valueOf(df.format((taxValue + cess) - TDSDeducted - tdsComputation.getPreviousEmployerTdsDeduction().doubleValue())));
            tdsComputation.setProcessDate(Instant.now());
            TdsComputation result = tdsComputationRepository.save(tdsComputation);
        }
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, "")).build();
    }

    /**
     * GET  /tds-computations/:id : get the "id" tdsComputation.
     *
     * @param id the id of the tdsComputation to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tdsComputation, or with status 404 (Not Found)
     */
    @GetMapping("/tds-computations-entry/{id}")
    @Timed
    public ResponseEntity<TdsComputationBean> getTdsComputation(@PathVariable Long id) {
        log.debug("REST request to get TdsComputation : {}", id);
        TdsComputationBean tdsComputationBean = new TdsComputationBean();
        TdsComputation tdsComputation = tdsComputationRepository.findById(id).orElse(null);
        EmployeeView employeeView = employeeViewRepository.findByCardNo(tdsComputation.getCardNo()).orElse(null);
        if(employeeView != null) {
            tdsComputationBean.setCardNo(employeeView.getCardNo());
            tdsComputationBean.setName(employeeView.getName());
            tdsComputationBean.setDesignation(employeeView.getDesCodeDesc());
            tdsComputationBean.setPanNo(employeeView.getPan());
            tdsComputationBean.setDateOfBirth(employeeView.getDob());
            tdsComputationBean.setContactNumber(employeeView.getPhone());
            tdsComputationBean.setEmailId(employeeView.getEmail());
            tdsComputationBean.setAddress(employeeView.getAdd1());
        }
        TdsYearMaster tdsYearMaster = tdsYearMasterRepository.findByCode(tdsComputation.getFinancialYear());
        String year = "";
        String financeYearRange = "";
        if(tdsYearMaster != null){
            year = tdsYearMaster.getCode();
            financeYearRange = tdsYearMaster.getFinanceYear();
        }
        List<TdsDeclaration> tdsDeclaration = tdsDeclarationRepository.findAllByCardNo(tdsComputation.getCardNo(), Integer.parseInt(year));
        TdsDeclaration tdsDeclarationChk = null;
        if (tdsDeclaration.size() > 0) {
            tdsDeclarationChk = tdsDeclaration.get(0);
            BeanUtils.copyProperties(tdsDeclarationChk, tdsComputationBean);
            tdsComputationBean.setRegime(tdsDeclarationChk.getRegimeType() != null && tdsDeclarationChk.getRegimeType().equalsIgnoreCase("OLD") ? true : false);
            if (tdsDeclarationChk.getTempLock() != null && tdsDeclarationChk.getTempLock().equalsIgnoreCase("Y")) {
                tdsComputationBean.setLocked(true);
            }
        }
        Map<Long, Double> deductionMap = new HashMap<Long, Double>();
        if (tdsComputation != null) {
            BeanUtils.copyProperties(tdsComputation, tdsComputationBean);
            tdsComputationBean.setFinancialYearRange(financeYearRange);
            deductionMap = convertInMap(tdsComputationBean);
        }

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
            if (deductionMap.containsKey(tdsGroupMasterBean.getId())) ;
            {
                tdsGroupMasterBean.setExemptAmount(deductionMap.get(tdsGroupMasterBean.getId()));
            }
            List<TdsGroupDetailsBean> tdsGroupList = new ArrayList<TdsGroupDetailsBean>();
            for (TdsGroupDetails tdsGroupDetail : tdsGroupDetails) {
                if (key.longValue() == tdsGroupDetail.getTdsGroupMaster().getId().longValue()) {
                    TdsGroupDetailsBean bean = new TdsGroupDetailsBean();
                    BeanUtils.copyProperties(tdsGroupDetail, bean);
                    if (tdsDeclaration.size() != 0) {
                        for (TdsDeclaration tdsDeclarations : tdsDeclaration) {
                            if (tdsDeclarations.getTdsGroupDetails().getId() == tdsGroupDetail.getId()) {
                                bean.setAmount(tdsDeclarations.getAmount());
                            }
                        }
                    }
                    tdsGroupList.add(bean);
                }
            }
            tdsGroupMasterBean.setTdsGroupDetailsBean(tdsGroupList);
            groupMasterBeans.add(tdsGroupMasterBean);
        }
        List<TdsSlabMaster> tdsSlabMasters = null;
        LocalDate dob = employeeView.getDob().toLocalDate();
        Period diff = Period.between(dob, LocalDate.now());
        if (tdsYearMaster != null && tdsYearMaster.getRegime() != null && tdsYearMaster.getRegime().booleanValue() == true) {
            if (tdsDeclarationChk != null && tdsDeclarationChk.getRegimeType() != null && tdsDeclarationChk.getRegimeType().equalsIgnoreCase("OLD")) {
                if (employeeView.getMf().equalsIgnoreCase("1")) {
                    tdsSlabMasters = tdsSlabMasterRepository.findAllByAgeEndAndGenderAndRegimeType(diff.getYears(), "M", year, "OLD");
                } else {
                    tdsSlabMasters = tdsSlabMasterRepository.findAllByAgeEndAndGenderAndRegimeType(diff.getYears(), "F", year, "OLD");
                }
            } else {
                if (employeeView.getMf().equalsIgnoreCase("1")) {
                    tdsSlabMasters = tdsSlabMasterRepository.findAllByAgeEndAndGenderAndRegimeType(diff.getYears(), "M", year, "NEW");
                } else {
                    tdsSlabMasters = tdsSlabMasterRepository.findAllByAgeEndAndGenderAndRegimeType(diff.getYears(), "F", year, "NEW");
                }
            }
        } else {
            if (employeeView.getMf().equalsIgnoreCase("1")) {
                tdsSlabMasters = tdsSlabMasterRepository.findAllByAgeEndAndGender(diff.getYears(), "M", year);
            } else {
                tdsSlabMasters = tdsSlabMasterRepository.findAllByAgeEndAndGender(diff.getYears(), "F", year);
            }
        }
        tdsComputationBean.setTdsSlabMasters(tdsSlabMasters);
        tdsComputationBean.setGroupMasterBeans(groupMasterBeans);
        return ResponseUtil.wrapOrNotFound(Optional.of(tdsComputationBean));
    }

    private Map<Long, Double> convertInMap(TdsComputationBean tdsComputationBean) {
        Map<Long, Double> map = new TreeMap<Long, Double>();
        if (tdsComputationBean.getDeductCode1() != null && tdsComputationBean.getDeductAmount1() != null) {
            map.put(Long.parseLong(tdsComputationBean.getDeductCode1()), tdsComputationBean.getDeductAmount1());
        }
        if (tdsComputationBean.getDeductCode2() != null && tdsComputationBean.getDeductAmount2() != null) {
            map.put(Long.parseLong(tdsComputationBean.getDeductCode2()), tdsComputationBean.getDeductAmount2());
        }
        if (tdsComputationBean.getDeductCode3() != null && tdsComputationBean.getDeductAmount3() != null) {
            map.put(Long.parseLong(tdsComputationBean.getDeductCode3()), tdsComputationBean.getDeductAmount3());
        }
        if (tdsComputationBean.getDeductCode4() != null && tdsComputationBean.getDeductAmount4() != null) {
            map.put(Long.parseLong(tdsComputationBean.getDeductCode4()), tdsComputationBean.getDeductAmount4());
        }
        if (tdsComputationBean.getDeductCode5() != null && tdsComputationBean.getDeductAmount5() != null) {
            map.put(Long.parseLong(tdsComputationBean.getDeductCode5()), tdsComputationBean.getDeductAmount5());
        }
        if (tdsComputationBean.getDeductCode6() != null && tdsComputationBean.getDeductAmount6() != null) {
            map.put(Long.parseLong(tdsComputationBean.getDeductCode6()), tdsComputationBean.getDeductAmount6());
        }
        if (tdsComputationBean.getDeductCode7() != null && tdsComputationBean.getDeductAmount7() != null) {
            map.put(Long.parseLong(tdsComputationBean.getDeductCode7()), tdsComputationBean.getDeductAmount7());
        }
        if (tdsComputationBean.getDeductCode8() != null && tdsComputationBean.getDeductAmount8() != null) {
            map.put(Long.parseLong(tdsComputationBean.getDeductCode8()), tdsComputationBean.getDeductAmount8());
        }
        if (tdsComputationBean.getDeductCode9() != null && tdsComputationBean.getDeductAmount9() != null) {
            map.put(Long.parseLong(tdsComputationBean.getDeductCode9()), tdsComputationBean.getDeductAmount9());
        }
        if (tdsComputationBean.getDeductCode10() != null && tdsComputationBean.getDeductAmount10() != null) {
            map.put(Long.parseLong(tdsComputationBean.getDeductCode10()), tdsComputationBean.getDeductAmount10());
        }
        if (tdsComputationBean.getDeductCode11() != null && tdsComputationBean.getDeductAmount11() != null) {
            map.put(Long.parseLong(tdsComputationBean.getDeductCode11()), tdsComputationBean.getDeductAmount11());
        }
        if (tdsComputationBean.getDeductCode12() != null && tdsComputationBean.getDeductAmount12() != null) {
            map.put(Long.parseLong(tdsComputationBean.getDeductCode12()), tdsComputationBean.getDeductAmount12());
        }
        if (tdsComputationBean.getDeductCode13() != null && tdsComputationBean.getDeductAmount13() != null) {
            map.put(Long.parseLong(tdsComputationBean.getDeductCode13()), tdsComputationBean.getDeductAmount13());
        }
        if (tdsComputationBean.getDeductCode14() != null && tdsComputationBean.getDeductAmount14() != null) {
            map.put(Long.parseLong(tdsComputationBean.getDeductCode14()), tdsComputationBean.getDeductAmount14());
        }
        if (tdsComputationBean.getDeductCode15() != null && tdsComputationBean.getDeductAmount15() != null) {
            map.put(Long.parseLong(tdsComputationBean.getDeductCode15()), tdsComputationBean.getDeductAmount15());
        }
        if (tdsComputationBean.getDeductCode16() != null && tdsComputationBean.getDeductAmount16() != null) {
            map.put(Long.parseLong(tdsComputationBean.getDeductCode16()), tdsComputationBean.getDeductAmount16());
        }
        if (tdsComputationBean.getDeductCode17() != null && tdsComputationBean.getDeductAmount17() != null) {
            map.put(Long.parseLong(tdsComputationBean.getDeductCode17()), tdsComputationBean.getDeductAmount17());
        }
        if (tdsComputationBean.getDeductCode18() != null && tdsComputationBean.getDeductAmount18() != null) {
            map.put(Long.parseLong(tdsComputationBean.getDeductCode18()), tdsComputationBean.getDeductAmount18());
        }
        if (tdsComputationBean.getDeductCode19() != null && tdsComputationBean.getDeductAmount19() != null) {
            map.put(Long.parseLong(tdsComputationBean.getDeductCode19()), tdsComputationBean.getDeductAmount19());
        }
        if (tdsComputationBean.getDeductCode20() != null && tdsComputationBean.getDeductAmount20() != null) {
            map.put(Long.parseLong(tdsComputationBean.getDeductCode20()), tdsComputationBean.getDeductAmount20());
        }

        return map;
    }

    @PostMapping("/tds-computations-entry-export")
    @Timed
    public @ResponseBody void getTdsComputationReportSummary(HttpServletResponse response) throws JRException, IOException {
        log.debug("REST request to get a page of TdsComputations");
        List<TdsComputationReportSummaryBean> tdsComputationReportSummaryBeans = new ArrayList<>();
        List<TdsYearMaster> tdsYearMasters = tdsYearMasterRepository.findByActive();
        if (tdsYearMasters != null && tdsYearMasters.size() > 0) {
            TdsYearMaster tdsYearMaster = tdsYearMasters.get(0);
            List<Object> listObject = tdsComputationRepository.findAllTdsComputaion(tdsYearMaster.getCode());
            Map<String, TdsComputationReportSummaryBean> map = new HashMap<>();
            for (Object object : listObject) {
                Object[] objects = (Object[]) object;
                TdsComputationReportSummaryBean bean = new TdsComputationReportSummaryBean();
                bean.setTotalTaxLiability(Double.parseDouble(objects[1].toString()));
                bean.setBalanceTaxValue(Double.parseDouble(objects[2].toString()));
                bean.setTotalTaxIncome(Double.parseDouble(objects[3].toString()));
                bean.setTaxDeductValue(Double.parseDouble(objects[4].toString()));
                map.put(objects[0].toString(), bean);
            }
            List<EmployeeView> employeeViews = employeeViewRepository.findAllByLogins(new ArrayList<String>(map.keySet()));
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            for (EmployeeView employeeView : employeeViews) {
                TdsComputationReportSummaryBean bean = map.get(employeeView.getCardNo());
                BeanUtils.copyProperties(employeeView, bean);
                bean.setDoj(format.format(Date.from(employeeView.getDoj().toInstant())));
                tdsComputationReportSummaryBeans.add(bean);
            }
            Collections.sort(tdsComputationReportSummaryBeans, Comparator.comparing(TdsComputationReportSummaryBean::getFactoryDesc).thenComparing(TdsComputationReportSummaryBean::getCardNo));

            String path = applicationProperties.getTemplatePath() + "jasper/";
            JasperDesign jasperDesign = JRXmlLoader.load(path + "/TdsComputationReportSummary.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            Map<String, Object> parameters = new HashMap<>();
            JRDataSource jrDataSource = new JRBeanCollectionDataSource(tdsComputationReportSummaryBeans);

            parameters.put("datasource", jrDataSource);
            parameters.put("SUBREPORT_DIR", path);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=TdsComputationReportSummary.xlsx");

            final OutputStream outputStream = response.getOutputStream();
            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
            exporter.exportReport();
        }
    }

    /**
     * GET  /tds-declarations : get all the tdsDeclarations.
     *
     * @param !pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tdsDeclarations in body
     */
    @PostMapping("/tds-computation-report-export")
    @Timed
    public @ResponseBody void getAllTdsDeclarationsExportXLSX(@RequestBody MasterSearch search, HttpServletResponse response) throws JRException, IOException, ParseException {
        log.debug("REST request to get a page of TdsDeclarations");
        List<TdsSummaryReportBean> tdsSummaryReportBeans = new ArrayList<>();
        String finYear = search.getParaString1();
        BigDecimal salAmount = search.getParaNumber1();
        List<String> groups = search.getParameters1();
        List<String> factories = search.getParameters2();
        TdsYearMaster tdsYearMaster = tdsYearMasterRepository.findByCode(finYear);
        if (groups != null && groups.size() > 0 && (factories == null || (factories != null && factories.size() == 0))) {
            factories = factoryMasterRepository.findAllFactoryAndGroupCode(groups);
        }
        Date now = new Date();
        if (tdsYearMaster != null) {
            ZonedDateTime zonedDateTime = tdsYearMaster.getStartDate().atStartOfDay(ZoneId.systemDefault());
            List<EmployeeMatView> employeeMatViews = null;
            if (factories != null && factories.size() > 0) {
                employeeMatViews = employeeMatViewRepository.findAllByJoiningAndTotalSalary(zonedDateTime, salAmount.doubleValue(), factories);
            } else {
                employeeMatViews = employeeMatViewRepository.findAllByJoiningAndTotalSalary(zonedDateTime, salAmount.doubleValue());
            }

            List<TdsGroupMaster> tdsGroupMasters = tdsGroupMasterRepository.findAllYear(Integer.parseInt(tdsYearMaster.getCode()));
            String id80DD = "";
            String id80E = "";
            for (TdsGroupMaster groupMaster : tdsGroupMasters) {
                if (groupMaster.getGroupCode().equals("80E")) {
                    id80E = groupMaster.getId() + "";
                }

                if (groupMaster.getGroupCode().equals("80DD & 80 U")) {
                    id80DD = groupMaster.getId() + "";
                }
            }
            List<MonthlyDetail> monthlyDetails = monthlyDetailRepository.findAll();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-yyyy");
            SimpleDateFormat simpleDateFormatMMM = new SimpleDateFormat("MMM-yy");
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

            Map<String, TdsSummaryReportBean> map = new HashMap<>();
            List<TdsDeclaration> tdsDeclarations = tdsDeclarationRepository.findAllByCardNoLike("%", Integer.parseInt(tdsYearMaster.getCode()));
            Map<String, TdsDeclaration> tdsDeclarationMap = new LinkedHashMap<>();
            for (TdsDeclaration declaration : tdsDeclarations) {
                tdsDeclarationMap.put(declaration.getCardNo(), declaration);
            }
            List<Object> listObject = tdsComputationRepository.findAllTdsComputaion(tdsYearMaster.getCode());
            for (Object object : listObject) {
                Object[] objects = (Object[]) object;
                TdsSummaryReportBean bean = new TdsSummaryReportBean();
                bean.setTotalTaxLiability(Double.parseDouble(objects[1].toString()));
                bean.setBalanceTaxValue(Double.parseDouble(objects[2].toString()));
                bean.setTotalTaxIncome(Double.parseDouble(objects[3].toString()));
                bean.setTaxDeductValue(Double.parseDouble(objects[4].toString()));
                bean.setRentExempt(Double.parseDouble(objects[5].toString()));
                bean.setPendingMonth(Integer.parseInt(objects[6].toString()));
                Double value80E = 0.0;
                if (objects[7] != null && objects[7].toString().equalsIgnoreCase(id80E)) {
                    value80E = objects[8] != null ? Double.parseDouble(objects[8].toString()) : 0.0;
                } else if (objects[9] != null && objects[9].toString().equalsIgnoreCase(id80E)) {
                    value80E = objects[10] != null ? Double.parseDouble(objects[10].toString()) : 0.0;
                } else if (objects[11] != null && objects[11].toString().equalsIgnoreCase(id80E)) {
                    value80E = objects[12] != null ? Double.parseDouble(objects[12].toString()) : 0.0;
                } else if (objects[13] != null && objects[13].toString().equalsIgnoreCase(id80E)) {
                    value80E = objects[14] != null ? Double.parseDouble(objects[14].toString()) : 0.0;
                } else if (objects[15] != null && objects[15].toString().equalsIgnoreCase(id80E)) {
                    value80E = objects[16] != null ? Double.parseDouble(objects[16].toString()) : 0.0;
                } else if (objects[17] != null && objects[17].toString().equalsIgnoreCase(id80E)) {
                    value80E = objects[18] != null ? Double.parseDouble(objects[18].toString()) : 0.0;
                }
                bean.setUpload80EAmount(value80E);

                Double value80DD = 0.0;
                if (objects[7] != null && objects[7].toString().equalsIgnoreCase(id80DD)) {
                    value80DD = objects[8] != null ? Double.parseDouble(objects[8].toString()) : 0.0;
                } else if (objects[9] != null && objects[9].toString().equalsIgnoreCase(id80DD)) {
                    value80DD = objects[10] != null ? Double.parseDouble(objects[10].toString()) : 0.0;
                } else if (objects[11] != null && objects[11].toString().equalsIgnoreCase(id80DD)) {
                    value80DD = objects[12] != null ? Double.parseDouble(objects[12].toString()) : 0.0;
                } else if (objects[13] != null && objects[13].toString().equalsIgnoreCase(id80DD)) {
                    value80DD = objects[14] != null ? Double.parseDouble(objects[14].toString()) : 0.0;
                } else if (objects[15] != null && objects[15].toString().equalsIgnoreCase(id80DD)) {
                    value80DD = objects[16] != null ? Double.parseDouble(objects[16].toString()) : 0.0;
                } else if (objects[17] != null && objects[17].toString().equalsIgnoreCase(id80DD)) {
                    value80DD = objects[18] != null ? Double.parseDouble(objects[18].toString()) : 0.0;
                }
                bean.setUpload80DDAmount(value80DD);

                Double totalAmount = (objects[8] != null ? Double.parseDouble(objects[8].toString()) : 0.0) + (value80DD = objects[10] != null ? Double.parseDouble(objects[10].toString()) : 0.0) + (value80DD = objects[12] != null ? Double.parseDouble(objects[12].toString()) : 0.0) + (value80DD = objects[14] != null ? Double.parseDouble(objects[14].toString()) : 0.0) + (value80DD = objects[16] != null ? Double.parseDouble(objects[16].toString()) : 0.0) + (value80DD = objects[18] != null ? Double.parseDouble(objects[18].toString()) : 0.0);
                bean.setUploadInformationAmount(totalAmount - value80E - value80DD);
                map.put(objects[0].toString(), bean);
            }
            for(EmployeeMatView employeeMatView : employeeMatViews) {
                SalaryRate salaryRate = salaryRateRepository.findByLastDetail(employeeMatView.getEmpCode(), employeeMatView.getEmpCode());
                List<MonthlyFinance> monthlyFinances = monthlyRepository.findAllByPan(employeeMatView.getPan(), new SimpleDateFormat("MM-yyyy").format(new Date()));
                int counter = 0;
                int balCounter = 0;
                Double balanceTax = null;
                Double expectedMonthly = 0.0;
                for (MonthlyDetail monthlyDetail : monthlyDetails) {
                     TdsSummaryReportBean beanTemp = map.get(employeeMatView.getCardNo());
                    // BeanUtils.copyProperties(beanTemp, bean);
                    TdsSummaryReportBean bean = new TdsSummaryReportBean();
                    BeanUtils.copyProperties(employeeMatView, bean);
                    bean.setDoj(employeeMatView.getDoj() != null ? format.format(Date.from(employeeMatView.getDoj().toInstant())) : "");
                    bean.setResignDate(employeeMatView.getResignDate() != null ? format.format(Date.from(employeeMatView.getResignDate().toInstant())) : "");
                    bean.setPaidDays(31.0);
                    if (beanTemp != null) {
                        bean.setTotalTaxLiability(beanTemp.getTotalTaxLiability());
                        bean.setBalanceTaxValue(beanTemp.getBalanceTaxValue());
                        bean.setTotalTaxIncome(beanTemp.getTotalTaxIncome());
                        bean.setTaxDeductValue(beanTemp.getTaxDeductValue());
                        bean.setUpload80DDAmount(beanTemp.getUpload80DDAmount());
                        bean.setUpload80EAmount(beanTemp.getUpload80EAmount());
                        bean.setUploadInformationAmount(beanTemp.getUploadInformationAmount());
                        bean.setPendingMonth(beanTemp.getPendingMonth());
                        bean.setRentExempt(beanTemp.getRentExempt());
                    }
                    if (salaryRate != null) {
                        bean.setBasic(salaryRate.getAmt1());
                        bean.setHra(salaryRate.getAmt2());
                        bean.setConvAllow(salaryRate.getAmt3());
                        bean.setOthers(salaryRate.getAmt4() + salaryRate.getAmt7());
                        bean.setSpecialAllow(salaryRate.getAmt5());
                        bean.setMedical(salaryRate.getAmt6());
                        bean.setGrossSalary(salaryRate.getTotSal());
                    }
                    if (tdsDeclarationMap.containsKey(bean.getCardNo())) {
                        TdsDeclaration declaration = tdsDeclarationMap.get(bean.getCardNo());
                        if(declaration.getRegimeType() != null && declaration.getRegimeType().equals("NEW")) {
                            bean.setRegime("New");
                            bean.setUploadInformationAmount(null);
                            bean.setUploadInformation("NO");
                        } else {
                            bean.setRegime("Old");
                            if(bean.getUploadInformationAmount()>0) {
                                bean.setUploadInformation("YES");
                            } else {
                                bean.setUploadInformation("NO");
                            }
                        }
                        bean.setPreviousEmployerSalary(declaration.getPreviousEmployerAmount() != null ? declaration.getPreviousEmployerAmount().doubleValue() : 0.0);
                        bean.setPreviousEmployerTax(declaration.getPreviousEmployerTdsDeduction() != null ? declaration.getPreviousEmployerTdsDeduction().doubleValue() : 0.0);
                    } else {
                        bean.setRegime("No Information");
                        bean.setUploadInformationAmount(null);
                        bean.setPreviousEmployerSalary(0.0);
                        bean.setPreviousEmployerTax(0.0);
                    }
                    bean.setMonthYear(simpleDateFormatMMM.format(simpleDateFormat.parse(monthlyDetail.getMonthYear())));
                    bean.setMonthNo(monthlyDetail.getMonthNo());
                    if (balanceTax == null) {
                        balanceTax = bean.getBalanceTaxValue();
                        if (bean.getBalanceTaxValue() != null && bean.getPendingMonth() != null && bean.getPendingMonth() > 0) {
                            expectedMonthly = bean.getBalanceTaxValue() / bean.getPendingMonth();
                            expectedMonthly = Double.parseDouble(((int) (Math.ceil(expectedMonthly / 100.0)) * 100) + "");
                        }
                    }
                    boolean exist = false;
                    for (MonthlyFinance monthlyFinance : monthlyFinances) {
                        if(monthlyFinance.getId().getMonthYear().equalsIgnoreCase(monthlyDetail.getMonthYear())) {
                            Double TDSDEDUCTED = 0.0;
                            if (monthlyFinance.getDall1() != null && monthlyFinance.getDall1().equalsIgnoreCase("T D S")) {
                                TDSDEDUCTED = monthlyFinance.getDed1();
                            } else if (monthlyFinance.getDall2() != null && monthlyFinance.getDall2().equalsIgnoreCase("T D S")) {
                                TDSDEDUCTED = monthlyFinance.getDed2();
                            } else if (monthlyFinance.getDall3() != null && monthlyFinance.getDall3().equalsIgnoreCase("T D S")) {
                                TDSDEDUCTED = monthlyFinance.getDed3();
                            } else if (monthlyFinance.getDall4() != null && monthlyFinance.getDall4().equalsIgnoreCase("T D S")) {
                                TDSDEDUCTED = monthlyFinance.getDed4();
                            } else if (monthlyFinance.getDall5() != null && monthlyFinance.getDall5().equalsIgnoreCase("T D S")) {
                                TDSDEDUCTED = monthlyFinance.getDed5();
                            } else if (monthlyFinance.getDall6() != null && monthlyFinance.getDall6().equalsIgnoreCase("T D S")) {
                                TDSDEDUCTED = monthlyFinance.getDed6();
                            }
                            bean.setTdsAmount(TDSDEDUCTED);
                            exist = true;
                            ++counter;
                        }
                    }
                    if (exist == false && !simpleDateFormat.format(now).equalsIgnoreCase(monthlyDetail.getMonthYear()) && now.after(format.parse("01-" + monthlyDetail.getMonthYear()))) {
                        ++counter;
                    } else if (balanceTax!= null && bean.getPendingMonth() != null && balanceTax > 0 && exist == false && balCounter != bean.getPendingMonth() - 1) {
                        bean.setTdsAmount(expectedMonthly);
                        balanceTax -= expectedMonthly;
                        ++balCounter;
                        ++counter;
                    } else if (bean.getPendingMonth() != null && exist == false && balCounter == bean.getPendingMonth() - 1) {
                        bean.setTdsAmount(balanceTax);
                        ++balCounter;
                        ++counter;
                    }
                    tdsSummaryReportBeans.add(bean);
                }
            }
        }
        String path = applicationProperties.getTemplatePath() + "jasper/";
        JasperDesign jasperDesign = JRXmlLoader.load(path + "/TDSComputationReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String, Object> parameters = new HashMap<String, Object>();
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(tdsSummaryReportBeans);

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
 }

