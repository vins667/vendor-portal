package io.vamani.application.scheduler;

import io.vamani.application.domain.*;
import io.vamani.application.mssql.domain.EmployeeView;
import io.vamani.application.mssql.domain.Monthly;
import io.vamani.application.mssql.domain.MonthlyFinance;
import io.vamani.application.mssql.domain.SalaryRate;
import io.vamani.application.mssql.repository.EmployeeViewRepository;
import io.vamani.application.mssql.repository.MonthlyFinanceRepository;
import io.vamani.application.mssql.repository.SalaryRateRepository;
import io.vamani.application.repository.*;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.util.HeaderUtil;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Component
@EnableScheduling
public class TDSSchedular {
    @Autowired
    private EmployeeViewRepository employeeViewRepository;

    @Autowired
    private TdsYearMasterRepository tdsYearMasterRepository;

    @Autowired
    private SalaryRateRepository salaryRateRepository;

    @Autowired
    private MonthlyFinanceRepository monthlyFinanceRepository;

    @Autowired
    private TdsComputationRepository tdsComputationRepository;

    @Autowired
    private TdsDeclarationRepository tdsDeclarationRepository;

    @Autowired
    private TdsGroupDetailsRepository tdsGroupDetailsRepository;

    @Autowired
    private TdsSlabMasterRepository tdsSlabMasterRepository;

    @Autowired
    private PreviousEmploymentDetailsRepository previousEmploymentDetailsRepository;

    @Autowired
    private TdsDeclarationBreakupRepository tdsDeclarationBreakupRepository;

    /* @Scheduled(cron = "0 0 18 * * ?")
    public void pushTDSData() {

        List<TdsYearMaster> tdsYearMasters = tdsYearMasterRepository.findByActive();
        if (tdsYearMasters != null && tdsYearMasters.size() > 0) {
            TdsYearMaster tdsYearMaster = tdsYearMasters.get(0);
            Double salarySlab = tdsYearMaster.getSalarySlab();
            TdsGroupDetails pfGroup = null;
            TdsGroupDetails vpfGroup = null;
            if (salarySlab != null && salarySlab.doubleValue() > 0) {
                Map<String, Integer> monthList = new HashMap<>();
                monthList.put("04-" + tdsYearMaster.getCode(), 11);
                monthList.put("05-" + tdsYearMaster.getCode(), 10);
                monthList.put("06-" + tdsYearMaster.getCode(), 9);
                monthList.put("07-" + tdsYearMaster.getCode(), 8);
                monthList.put("08-" + tdsYearMaster.getCode(), 7);
                monthList.put("09-" + tdsYearMaster.getCode(), 6);
                monthList.put("10-" + tdsYearMaster.getCode(), 5);
                monthList.put("11-" + tdsYearMaster.getCode(), 4);
                monthList.put("12-" + tdsYearMaster.getCode(), 3);
                monthList.put("01-" + (Integer.parseInt(tdsYearMaster.getCode()) + 1), 2);
                monthList.put("02-" + (Integer.parseInt(tdsYearMaster.getCode()) + 1), 1);
                monthList.put("03-" + (Integer.parseInt(tdsYearMaster.getCode()) + 1), 0);
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
                    List<MonthlyFinance> monthlyFinances = monthlyFinanceRepository.findAllByEmpCode(employeeView.getEmpCode());
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
                        balanceMonth = monthList.get(monthlyFinances.get(monthlyFinances.size() - 1).getId().getMonthYear());
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
                    List<TdsDeclaration> tdsDeclaration = tdsDeclarationRepository.findAllByCardNo(employeeView.getCardNo(), Integer.parseInt(tdsYearMaster.getCode()));
                    if (tdsDeclaration.size() > 0) {
                        TdsDeclaration tdsDeclarations = tdsDeclaration.get(0);
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

                    tdsComputation.setEarnTotal((basic + hra + convAll + others + splAllow + medical) - rentExempt);

                    if ((regimeType != null && regimeType.equalsIgnoreCase("OLD")) || (tdsYearMaster.getRegime() != null && tdsYearMaster.getRegime().booleanValue() == false)) {
                        if (tdsYearMaster.getStandardDeduction() != null && tdsYearMaster.getStandardDeduction().doubleValue() > 0) {
                            tdsComputation.setStandardDeduction(tdsYearMaster.getStandardDeduction());
                            tdsComputation.setStandardTotal(tdsComputation.getEarnTotal() - tdsYearMaster.getStandardDeduction());
                        } else {
                            tdsComputation.setStandardDeduction(0.0);
                            tdsComputation.setStandardTotal(tdsComputation.getEarnTotal() - 0.0);
                        }
                    } else {
                        tdsComputation.setStandardDeduction(0.0);
                        tdsComputation.setStandardTotal(tdsComputation.getEarnTotal() - 0.0);
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
                    tdsComputation.setBalanceTaxValue(Double.valueOf(df.format((taxValue + cess) - TDSDeducted)));
                    tdsComputation.setProcessDate(Instant.now());
                    TdsComputation result = tdsComputationRepository.save(tdsComputation);
                }
            }
        }
    }



    private List<TdsSlabMaster> getValidTdsSlab(List<TdsSlabMaster> tdsSlabMasters, String gender, int age, String regimeType, Boolean regime) {
        List<TdsSlabMaster> slabMasterList = new ArrayList<>();
        for (TdsSlabMaster tdsSlabMaster : tdsSlabMasters) {
            if(regime != null && regime.booleanValue() == true) {
                regimeType = regimeType != null ? regimeType : "NEW";
                if (age > tdsSlabMaster.getAgeStart().intValue() && age < tdsSlabMaster.getAgeEnd().intValue() && gender.equalsIgnoreCase(tdsSlabMaster.getGender()) && regimeType != null && regimeType.equalsIgnoreCase(tdsSlabMaster.getRegimeType())) {
                    slabMasterList.add(tdsSlabMaster);
                }
            } else {
                if (age > tdsSlabMaster.getAgeStart().intValue() && age < tdsSlabMaster.getAgeEnd().intValue() && gender.equalsIgnoreCase(tdsSlabMaster.getGender())) {
                    slabMasterList.add(tdsSlabMaster);
                }
            }
        }
        Collections.sort(slabMasterList);
        return slabMasterList;
    } */
}
