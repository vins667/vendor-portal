package io.vamani.application.scheduler;

import io.vamani.application.domain.*;
import io.vamani.application.mobile.VcutTvDefectBreakup;
import io.vamani.application.mobile.VcutTvDefectBreakupSummary;
import io.vamani.application.model.VcutFactoryLineBreakup;
import io.vamani.application.repository.*;
import io.vamani.application.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

@Component
@EnableScheduling
public class VcutStylePlanSchedular {
    @Autowired
    private VcutStylePlanUploadRepository vcutStylePlanUploadRepository;

    @Autowired
    private VcutMainEntryMasterRepository vcutMainEntryMasterRepository;

    @Autowired
    private VcutStylePlanSessionBreakupRepository vcutStylePlanSessionBreakupRepository;

    @Autowired
    private VcutStylePlanSchUploadRepository vcutStylePlanSchUploadRepository;

    @Autowired
    private VcutOperationIssueMasterRepository vcutOperationIssueMasterRepository;

    @Autowired
    private VcutOperationMasterRepository vcutOperationMasterRepository;

    @Autowired
    private VcutStyleIssueWiseSchUploadRepository vcutStyleIssueWiseSchUploadRepository;

    @Autowired
    private VcutStyleOperationWiseSchUploadRepository vcutStyleOperationWiseSchUploadRepository;

   // @Scheduled(cron = "0 0 4 * * ?")  // vinay 11-10-23
    public void pushData() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat simpleDateFormatParse = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormatWithTime = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DATE, -1);
        Date previousDate = calendar.getTime();
        List<VcutStylePlanUpload> vcutStylePlanUploads = vcutStylePlanUploadRepository.findByPlanDatePrevAll(LocalDate.parse(simpleDateFormatParse.format(previousDate)));
        for (VcutStylePlanUpload vcutStylePlanUpload : vcutStylePlanUploads) {
            Date planDate = DateUtils.asDate(vcutStylePlanUpload.getPlanDate());
            List<VcutMainEntryMaster> vcutMainEntryMasters = vcutMainEntryMasterRepository.findByPlanId(vcutStylePlanUpload.getId());
            List<VcutStylePlanSessionBreakup> stylePlanSessionBreakups = vcutStylePlanSessionBreakupRepository.findAllByVcutPlanId(vcutStylePlanUpload.getId());
            if (vcutStylePlanUpload.getVcutSessionMasterId() != null) {
                if (stylePlanSessionBreakups != null && stylePlanSessionBreakups.size() > 0) {
                    long i = 0L;
                    Long totPlanValue = 0L;
                    Long totHourFtt = 0L;
                    Long totHourActual = 0L;
                    Long totHourAlt = 0L;
                    Long totHourRej = 0L;
                    Long totHourRec = 0L;
                    List<VcutFactoryLineBreakup> vcutFactoryLineBreakups = new ArrayList<>();

                    String activeHour = "N";
                    for (VcutStylePlanSessionBreakup vcutStylePlanSessionBreakup : stylePlanSessionBreakups) {
                        VcutStylePlanSchUpload vcutStylePlanSchUpload = new VcutStylePlanSchUpload();
                        vcutStylePlanSchUpload.setPlanId(vcutStylePlanUpload.getId());
                        vcutStylePlanSchUpload.setPlanDate(vcutStylePlanUpload.getPlanDate());
                        vcutStylePlanSchUpload.setFactory(vcutStylePlanUpload.getFactory());
                        vcutStylePlanSchUpload.setLine(vcutStylePlanUpload.getLineNo());
                        vcutStylePlanSchUpload.setStyle(vcutStylePlanUpload.getStyle());
                        vcutStylePlanSchUpload.setSmv(vcutStylePlanUpload.getSmv());
                        if (vcutStylePlanSessionBreakup.getType() != null && (vcutStylePlanSessionBreakup.getType().equalsIgnoreCase("LUNCH") || vcutStylePlanSessionBreakup.getType().equalsIgnoreCase("TEA"))) {
                            vcutStylePlanSchUpload.setHour("B");
                        } else {
                            ++i;
                            vcutStylePlanSchUpload.setHour(i + "");
                        }
                        vcutStylePlanSchUpload.setHourDesc(vcutStylePlanSessionBreakup.getId().getStartTime() + " - " + vcutStylePlanSessionBreakup.getEndTime());
                        Long planValue = vcutStylePlanSessionBreakup.getPlanQuantity().longValue();
                        totPlanValue += planValue;
                        vcutStylePlanSchUpload.setHourlyPlanned(planValue);
                        vcutStylePlanSchUpload.setCumPlanned(totPlanValue);

                        Long hourFtt = 0L;
                        Long hourRec = 0L;
                        Long hourAlt = 0L;
                        Long hourRej = 0L;
                        Instant startDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + vcutStylePlanSessionBreakup.getId().getStartTime())).toInstant();
                        Instant endDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + vcutStylePlanSessionBreakup.getEndTime())).toInstant();
                        if (vcutMainEntryMasters != null && vcutMainEntryMasters.size() > 0) {
                            for (VcutMainEntryMaster entryMaster : vcutMainEntryMasters) {
                                if (entryMaster.getEntryTime().equals(startDate) || (entryMaster.getEntryTime().isAfter(startDate) && entryMaster.getEntryTime().isBefore(endDate))) {
                                    if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("FTT")) {
                                        ++hourFtt;
                                    } else if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("ALT") && entryMaster.getRectifiedDate() != null) {
                                        ++hourRec;
                                    } else if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("REJ")) {
                                        ++hourRej;
                                    } else if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("ALT")) {
                                        ++hourAlt;
                                    }
                                }
                            }
                        }
                        Long hourActual = hourFtt + hourRec;
                        totHourActual += hourActual;
                        totHourFtt += hourFtt;
                        totHourAlt += hourAlt;
                        totHourRec += hourRec;
                        totHourRej += hourRej;
                        vcutStylePlanSchUpload.setFtp(hourFtt);
                        vcutStylePlanSchUpload.setRectify(hourRec);
                        vcutStylePlanSchUpload.setRejected(hourRej);
                        vcutStylePlanSchUpload.setAltered(hourAlt);
                        vcutStylePlanSchUpload.setHourlyActual(hourActual);
                        vcutStylePlanSchUpload.setCumActual(totHourActual);
                        vcutStylePlanSchUpload.setVarianceHourly(hourActual - planValue);
                        vcutStylePlanSchUpload.setVarianceCum(totHourActual - totPlanValue);

                        if (hourActual != null && hourActual > 0 && planValue != null && planValue > 0) {
                            double percentageEff = (planValue.doubleValue() / vcutStylePlanUpload.getKickOff()) * 100;
                            double percentageEffHour = (hourActual.doubleValue() / percentageEff) * 100;
                            vcutStylePlanSchUpload.setActualEffHourly(percentageEffHour);
                        } else {
                            vcutStylePlanSchUpload.setActualEffHourly(0.0);
                        }
                        if (totHourActual > 0) {
                            double percentageEff = (totPlanValue.doubleValue() / vcutStylePlanUpload.getKickOff()) * 100;
                            double percentageEffHour = (totHourActual.doubleValue() / percentageEff) * 100;
                            vcutStylePlanSchUpload.setActualEffCum(percentageEffHour);
                        } else {
                            vcutStylePlanSchUpload.setActualEffCum(0.0);
                        }

                        if (hourActual.doubleValue() > 0) {
                            double fttRateHour = (hourFtt.doubleValue() * 100) / hourActual.doubleValue();
                            vcutStylePlanSchUpload.setFtpRateHourly(fttRateHour);
                        } else {
                            vcutStylePlanSchUpload.setFtpRateHourly(0.0);
                        }

                        if (totHourFtt.doubleValue() > 0 && totHourActual > 0) {
                            double fttRateHour = (totHourFtt.doubleValue() * 100) / totHourActual.doubleValue();
                            vcutStylePlanSchUpload.setFtpRateCum(fttRateHour);
                        } else {
                            vcutStylePlanSchUpload.setFtpRateCum(0.0);
                        }

                        if ((hourRej + hourAlt + hourRec) > 0) {
                            vcutStylePlanSchUpload.setDhuRateHourly(((hourRej.doubleValue() + hourAlt.doubleValue() + hourRec.doubleValue()) * 100) / (hourFtt.doubleValue() + hourRej.doubleValue() + hourAlt.doubleValue() + hourRec.doubleValue()));
                        } else {
                            vcutStylePlanSchUpload.setDhuRateHourly(0.0);
                        }

                        if ((totHourRej + totHourAlt + totHourRec) > 0) {
                            vcutStylePlanSchUpload.setDhuRateCum(((totHourRej.doubleValue() + totHourAlt.doubleValue() + totHourRec.doubleValue()) * 100) / (totHourFtt.doubleValue() + totHourRej.doubleValue() + totHourAlt.doubleValue() + totHourRec.doubleValue()));
                        } else {
                            vcutStylePlanSchUpload.setDhuRateCum(0.0);
                        }
                        vcutStylePlanSchUpload.setProcessDate(Timestamp.from(Instant.now()));
                        vcutStylePlanSchUploadRepository.save(vcutStylePlanSchUpload);
                    }
                    if (vcutMainEntryMasters != null && vcutMainEntryMasters.size() > 0) {
                        Instant startDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + stylePlanSessionBreakups.get(0).getId().getStartTime())).toInstant();
                        Instant endDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + stylePlanSessionBreakups.get(stylePlanSessionBreakups.size() - 1).getEndTime())).toInstant();

                        Long hourFtt = 0L;
                        Long hourRec = 0L;
                        Long hourRej = 0L;
                        Long hourAlt = 0L;
                        for (VcutMainEntryMaster entryMaster : vcutMainEntryMasters) {
                            if (entryMaster.getEntryTime().isBefore(startDate) || entryMaster.getEntryTime().isAfter(endDate)) {
                                if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("FTT")) {
                                    ++hourFtt;
                                } else if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("ALT") && entryMaster.getRectifiedDate() != null) {
                                    ++hourRec;
                                } else if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("REJ")) {
                                    ++hourRej;
                                } else if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("ALT")) {
                                    ++hourAlt;
                                }
                            }
                        }
                        if (hourFtt > 0 || hourRec > 0) {
                            ++i;
                            VcutStylePlanSchUpload vcutStylePlanSchUpload = new VcutStylePlanSchUpload();
                            vcutStylePlanSchUpload.setPlanId(vcutStylePlanUpload.getId());
                            vcutStylePlanSchUpload.setPlanDate(vcutStylePlanUpload.getPlanDate());
                            vcutStylePlanSchUpload.setFactory(vcutStylePlanUpload.getFactory());
                            vcutStylePlanSchUpload.setLine(vcutStylePlanUpload.getLineNo());
                            vcutStylePlanSchUpload.setStyle(vcutStylePlanUpload.getStyle());
                            vcutStylePlanSchUpload.setSmv(vcutStylePlanUpload.getSmv());
                            vcutStylePlanSchUpload.setHour("***");
                            vcutStylePlanSchUpload.setHourDesc("************");
                            Long hourActual = hourFtt + hourRec;
                            totHourActual += hourActual;
                            totHourFtt += hourFtt;
                            totHourRec += hourRec;
                            totHourRej += hourRej;
                            totHourAlt += hourAlt;
                            vcutStylePlanSchUpload.setHourlyPlanned(0L);
                            vcutStylePlanSchUpload.setFtp(hourFtt);
                            vcutStylePlanSchUpload.setRectify(hourRec);
                            vcutStylePlanSchUpload.setRejected(hourRej);
                            vcutStylePlanSchUpload.setAltered(hourAlt);
                            vcutStylePlanSchUpload.setCumPlanned(totPlanValue);
                            vcutStylePlanSchUpload.setHourlyActual(hourActual);
                            vcutStylePlanSchUpload.setCumActual(totHourActual);
                            vcutStylePlanSchUpload.setVarianceHourly(hourActual - 0L);
                            vcutStylePlanSchUpload.setVarianceCum(totHourActual - totPlanValue);

                            vcutStylePlanSchUpload.setActualEffHourly(0.0);

                            if (totHourActual > 0) {
                                double percentageEff = (totPlanValue.doubleValue() / vcutStylePlanUpload.getKickOff()) * 100;
                                double percentageEffHour = (totHourActual.doubleValue() / percentageEff) * 100;
                                vcutStylePlanSchUpload.setActualEffCum(percentageEffHour);
                            } else {
                                vcutStylePlanSchUpload.setActualEffCum(0.0);
                            }

                            if (hourActual.doubleValue() > 0) {
                                double fttRateHour = (hourFtt.doubleValue() * 100) / hourActual.doubleValue();
                                vcutStylePlanSchUpload.setFtpRateHourly(fttRateHour);
                            } else {
                                vcutStylePlanSchUpload.setFtpRateHourly(0.0);
                            }
                            if (totHourFtt.doubleValue() > 0 && totHourActual > 0) {
                                double fttRateHour = (totHourFtt.doubleValue() * 100) / totHourActual.doubleValue();
                                vcutStylePlanSchUpload.setFtpRateCum(fttRateHour);
                            } else {
                                vcutStylePlanSchUpload.setFtpRateCum(0.0);
                            }

                            if ((hourRej + hourAlt + hourRec) > 0) {
                                vcutStylePlanSchUpload.setDhuRateHourly(((hourRej.doubleValue() + hourAlt.doubleValue() + hourRec.doubleValue()) * 100) / (hourFtt.doubleValue() + hourRej.doubleValue() + hourAlt.doubleValue() + hourRec.doubleValue()));
                            } else {
                                vcutStylePlanSchUpload.setDhuRateHourly(0.0);
                            }

                            if ((totHourRej + totHourAlt + totHourRec) > 0) {
                                vcutStylePlanSchUpload.setDhuRateCum(((totHourRej.doubleValue() + totHourAlt.doubleValue() + totHourRec.doubleValue()) * 100) / (totHourFtt.doubleValue() + totHourRej.doubleValue() + totHourAlt.doubleValue() + totHourRec.doubleValue()));
                            } else {
                                vcutStylePlanSchUpload.setDhuRateCum(0.0);
                            }
                            vcutStylePlanSchUpload.setProcessDate(Timestamp.from(Instant.now()));
                            vcutStylePlanSchUploadRepository.save(vcutStylePlanSchUpload);
                        }
                    }
                }
            }
        }
    }
    @Scheduled(cron = "0 0 4 * * ?")
    public void pushDefectData() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat simpleDateFormatParse = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormatWithTime = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DATE, -1);
        Date previousDate = calendar.getTime();
        List<VcutStylePlanUpload> vcutStylePlanUploads = vcutStylePlanUploadRepository.findByPlanDatePrevAll(LocalDate.parse(simpleDateFormatParse.format(previousDate)));
        for (VcutStylePlanUpload vcutStylePlanUpload : vcutStylePlanUploads) {
            Date planDate = DateUtils.asDate(vcutStylePlanUpload.getPlanDate());
            Map<Long, Long> defectMap = new HashMap<>(); //vivek
            Map<Long, Map<String, List<Instant>>> defectDateMap = new HashMap<>();
            Map<Long, Long> defectOBMap = new HashMap<>(); //vivek
            Map<Long, Map<String, List<Instant>>> defectOBDateMap = new HashMap<>();
            Map<Long, VcutOperationIssueMaster> issueMap = new HashMap<>();
            Map<Long, VcutOperationMaster> operationMap = new HashMap<>();
            Map<String, List<VcutStylePlanSessionBreakup>> styleSessionMap = new HashMap<>();
            //Defects Issue Wise
            List<Object[]> vcutMainDefectsMasters = vcutMainEntryMasterRepository.findByDefectsPlanId(vcutStylePlanUpload.getId());
            List<VcutStylePlanSessionBreakup> stylePlanSessionBreakupsAll = vcutStylePlanSessionBreakupRepository.findAllByVcutPlanId(vcutStylePlanUpload.getId());
            styleSessionMap.put(vcutStylePlanUpload.getStyle(), stylePlanSessionBreakupsAll);
            if (stylePlanSessionBreakupsAll != null && stylePlanSessionBreakupsAll.size() > 0) {
                for (Object[] objects : vcutMainDefectsMasters) {
                    if (defectMap.containsKey(Long.parseLong(objects[1].toString()))) {
                        Long defectCount = defectMap.get(Long.parseLong(objects[1].toString()));
                        ++defectCount;
                        defectMap.put(Long.parseLong(objects[1].toString()), defectCount);

                        Map<String, List<Instant>> styleMap = defectDateMap.get(Long.parseLong(objects[1].toString()));
                        if (styleMap.containsKey(vcutStylePlanUpload.getStyle())) {
                            List<Instant> dates = styleMap.get(vcutStylePlanUpload.getStyle());
                            Instant date = Instant.parse(objects[0].toString());
                            dates.add(date);
                            styleMap.put(vcutStylePlanUpload.getStyle(), dates);
                        } else {
                            List<Instant> dates = new ArrayList<>();
                            Instant date = Instant.parse(objects[0].toString());
                            dates.add(date);
                            styleMap.put(vcutStylePlanUpload.getStyle(), dates);
                        }
                        defectDateMap.put(Long.parseLong(objects[1].toString()), styleMap);
                    } else {
                        defectMap.put(Long.parseLong(objects[1].toString()), 1L);

                        Map<String, List<Instant>> styleMap = new HashMap<>();
                        List<Instant> dates = new ArrayList<>();
                        Instant date = Instant.parse(objects[0].toString());
                        dates.add(date);
                        styleMap.put(vcutStylePlanUpload.getStyle(), dates);
                        defectDateMap.put(Long.parseLong(objects[1].toString()), styleMap);
                    }
                }
            }
            // Defects Issue Wise
            for (Long dKey : defectMap.keySet()) {
                VcutOperationIssueMaster vcutOperationIssueMaster = null;
                if (issueMap.containsKey(dKey)) {
                    vcutOperationIssueMaster = issueMap.get(dKey);
                } else {
                    vcutOperationIssueMaster = vcutOperationIssueMasterRepository.findById(dKey).orElse(null);
                    issueMap.put(dKey, vcutOperationIssueMaster);
                }

                Map<String, List<Instant>> dates = defectDateMap.get(dKey);
                Long srNo = 0L;
                Long previousDefect = 0L;
                List<VcutTvDefectBreakupSummary> summaries = new ArrayList<>();
                String activeHour = "N";
                for (String sKey : styleSessionMap.keySet()) {
                    List<VcutStylePlanSessionBreakup> stylePlanSessionBreakups = styleSessionMap.get(sKey);
                    Instant startDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + stylePlanSessionBreakups.get(0).getId().getStartTime())).toInstant();
                    Instant endDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + stylePlanSessionBreakups.get(stylePlanSessionBreakups.size() - 1).getEndTime())).toInstant();
                    Long hour = 0L;
                    for (VcutStylePlanSessionBreakup vcutStylePlanSessionBreakup : stylePlanSessionBreakups) {
                        Instant rowstartDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + vcutStylePlanSessionBreakup.getId().getStartTime())).toInstant();
                        Instant rowendDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + vcutStylePlanSessionBreakup.getEndTime())).toInstant();

                        Long dCounter = 0L;
                        if (dates.containsKey(sKey)) {
                            for (Instant date : dates.get(sKey)) {
                                if (date.equals(rowstartDate) || (date.isAfter(rowstartDate) && date.isBefore(rowendDate))) {
                                    ++dCounter;
                                }
                            }
                        }
                        VcutTvDefectBreakupSummary vcutTvDefectBreakupSummary = new VcutTvDefectBreakupSummary();
                        vcutTvDefectBreakupSummary.setId(dKey);
                        vcutTvDefectBreakupSummary.setDescription(vcutOperationIssueMaster.getDescription());
                        vcutTvDefectBreakupSummary.setDescriptionLocal(vcutOperationIssueMaster.getDescriptionLocal());
                        vcutTvDefectBreakupSummary.setStyle(sKey);
                        if (vcutStylePlanSessionBreakup.getType() != null && (vcutStylePlanSessionBreakup.getType().equalsIgnoreCase("LUNCH") || vcutStylePlanSessionBreakup.getType().equalsIgnoreCase("TEA"))) {
                            vcutTvDefectBreakupSummary.setHours("B");
                        } else {
                            ++hour;
                            vcutTvDefectBreakupSummary.setHours(hour + "");
                        }
                        vcutTvDefectBreakupSummary.setCountDefect(dCounter);
                        previousDefect += dCounter;
                        vcutTvDefectBreakupSummary.setCountDefectCum(previousDefect);
                        vcutTvDefectBreakupSummary.setStartDate(Date.from(startDate));
                        vcutTvDefectBreakupSummary.setSrNo(++srNo);
                        vcutTvDefectBreakupSummary.setStartTime(vcutStylePlanSessionBreakup.getId().getStartTime());
                        vcutTvDefectBreakupSummary.setEndTime(vcutStylePlanSessionBreakup.getEndTime());
                        summaries.add(vcutTvDefectBreakupSummary);
                    }

                    Long dCounter = 0L;
                    if (dates.containsKey(sKey)) {
                        for (Instant date : dates.get(sKey)) {
                            if (date.isBefore(startDate) || date.isAfter(endDate)) {
                                ++dCounter;
                            }
                        }
                    }

                    if(dCounter>0) {
                        VcutTvDefectBreakupSummary vcutTvDefectBreakupSummary = new VcutTvDefectBreakupSummary();
                        vcutTvDefectBreakupSummary.setId(dKey);
                        vcutTvDefectBreakupSummary.setDescription(vcutOperationIssueMaster.getDescription());
                        vcutTvDefectBreakupSummary.setDescriptionLocal(vcutOperationIssueMaster.getDescriptionLocal());
                        vcutTvDefectBreakupSummary.setStyle(sKey);
                        vcutTvDefectBreakupSummary.setSessionSpl("YES");
                        ++hour;
                        vcutTvDefectBreakupSummary.setHours("***");
                        vcutTvDefectBreakupSummary.setCountDefect(dCounter);
                        previousDefect += dCounter;
                        vcutTvDefectBreakupSummary.setCountDefectCum(previousDefect);
                        vcutTvDefectBreakupSummary.setStartDate(Date.from(startDate));
                        vcutTvDefectBreakupSummary.setSrNo(++srNo);
                        summaries.add(vcutTvDefectBreakupSummary);
                    }
                }
                Collections.sort(summaries, Comparator.comparing(VcutTvDefectBreakupSummary::getStartDate).thenComparing(VcutTvDefectBreakupSummary::getSrNo));
                Long prev = 0L;
                for (VcutTvDefectBreakupSummary summary : summaries) {
                    prev += summary.getCountDefect();
                    VcutStyleIssueWiseSchUpload vcutStyleIssueWiseSchUpload = new VcutStyleIssueWiseSchUpload();
                    vcutStyleIssueWiseSchUpload.setPlanId(vcutStylePlanUpload.getId());
                    vcutStyleIssueWiseSchUpload.setPlanDate(vcutStylePlanUpload.getPlanDate());
                    vcutStyleIssueWiseSchUpload.setFactory(vcutStylePlanUpload.getFactory());
                    vcutStyleIssueWiseSchUpload.setLine(vcutStylePlanUpload.getLineNo());
                    vcutStyleIssueWiseSchUpload.setStyle(vcutStylePlanUpload.getStyle());
                    vcutStyleIssueWiseSchUpload.setSmv(vcutStylePlanUpload.getSmv());
                    vcutStyleIssueWiseSchUpload.setHour(summary.getHours());
                    if(summary.getHours() != null && summary.getHours().equalsIgnoreCase("***")) {
                        vcutStyleIssueWiseSchUpload.setHourDesc("************");
                    } else {
                        vcutStyleIssueWiseSchUpload.setHourDesc(summary.getStartTime() + " - " + summary.getEndTime());
                    }
                    vcutStyleIssueWiseSchUpload.setVcutOperationIssueMasterId(summary.getId());
                    vcutStyleIssueWiseSchUpload.setVcutOperationIssueMasterDesc(summary.getDescriptionLocal());
                    vcutStyleIssueWiseSchUpload.setHourlyDefectCount(summary.getCountDefect());
                    vcutStyleIssueWiseSchUpload.setCumDefectCount(prev);
                    vcutStyleIssueWiseSchUpload.setProcessDate(Timestamp.from(Instant.now()));
                    vcutStyleIssueWiseSchUploadRepository.save(vcutStyleIssueWiseSchUpload);
                }
            }

            // Defects OB Wise
            List<Object[]> vcutMainDefectsOBMasters = vcutMainEntryMasterRepository.findByDefectsOperationPlanId(vcutStylePlanUpload.getId());
            if (stylePlanSessionBreakupsAll != null && stylePlanSessionBreakupsAll.size() > 0) {
                for (Object[] objects : vcutMainDefectsOBMasters) {
                    if (defectOBMap.containsKey(Long.parseLong(objects[1].toString()))) {
                        Long defectCount = defectOBMap.get(Long.parseLong(objects[1].toString()));
                        ++defectCount;
                        defectOBMap.put(Long.parseLong(objects[1].toString()), defectCount);

                        Map<String, List<Instant>> styleMap = defectOBDateMap.get(Long.parseLong(objects[1].toString()));
                        if (styleMap.containsKey(vcutStylePlanUpload.getStyle())) {
                            List<Instant> dates = styleMap.get(vcutStylePlanUpload.getStyle());
                            Instant date = Instant.parse(objects[0].toString());
                            dates.add(date);
                            styleMap.put(vcutStylePlanUpload.getStyle(), dates);
                        } else {
                            List<Instant> dates = new ArrayList<>();
                            Instant date = Instant.parse(objects[0].toString());
                            dates.add(date);
                            styleMap.put(vcutStylePlanUpload.getStyle(), dates);
                        }
                        defectOBDateMap.put(Long.parseLong(objects[1].toString()), styleMap);
                    } else {
                        defectOBMap.put(Long.parseLong(objects[1].toString()), 1L);

                        Map<String, List<Instant>> styleMap = new HashMap<>();
                        List<Instant> dates = new ArrayList<>();
                        Instant date = Instant.parse(objects[0].toString());
                        dates.add(date);
                        styleMap.put(vcutStylePlanUpload.getStyle(), dates);
                        defectOBDateMap.put(Long.parseLong(objects[1].toString()), styleMap);
                    }
                }
            }
            // Defects OB Wise
            for (Long dKey : defectOBMap.keySet()) {
                VcutOperationMaster vcutOperationMaster = null;
                if (operationMap.containsKey(dKey)) {
                    vcutOperationMaster = operationMap.get(dKey);
                } else {
                    vcutOperationMaster = vcutOperationMasterRepository.findById(dKey).orElse(null);
                    operationMap.put(dKey, vcutOperationMaster);
                }
                VcutTvDefectBreakup vcutTvDefectBreakup = new VcutTvDefectBreakup();
                vcutTvDefectBreakup.setId(dKey);
                vcutTvDefectBreakup.setDescription(vcutOperationMaster.getDescription());
                vcutTvDefectBreakup.setDescriptionLocal(vcutOperationMaster.getDescriptionLocal());
                vcutTvDefectBreakup.setDefectCount(defectOBMap.get(dKey));

                Map<String, List<Instant>> dates = defectOBDateMap.get(dKey);
                Long srNo = 0L;
                Long previousDefect = 0L;
                List<VcutTvDefectBreakupSummary> summaries = new ArrayList<>();
                String activeHour = "N";
                for (String sKey : styleSessionMap.keySet()) {
                    List<VcutStylePlanSessionBreakup> stylePlanSessionBreakups = styleSessionMap.get(sKey);
                    Instant startDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + stylePlanSessionBreakups.get(0).getId().getStartTime())).toInstant();
                    Instant endDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + stylePlanSessionBreakups.get(stylePlanSessionBreakups.size() - 1).getEndTime())).toInstant();
                    Long hour = 0L;
                    for (VcutStylePlanSessionBreakup vcutStylePlanSessionBreakup : stylePlanSessionBreakups) {
                        Instant rowstartDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + vcutStylePlanSessionBreakup.getId().getStartTime())).toInstant();
                        Instant rowendDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + vcutStylePlanSessionBreakup.getEndTime())).toInstant();

                        Long dCounter = 0L;
                        if (dates.containsKey(sKey)) {
                            for (Instant date : dates.get(sKey)) {
                                if (date.equals(rowstartDate) || (date.isAfter(rowstartDate) && date.isBefore(rowendDate))) {
                                    ++dCounter;
                                }
                            }
                        }
                        VcutTvDefectBreakupSummary vcutTvDefectBreakupSummary = new VcutTvDefectBreakupSummary();
                        vcutTvDefectBreakupSummary.setId(dKey);
                        vcutTvDefectBreakupSummary.setDescription(vcutOperationMaster.getDescription());
                        vcutTvDefectBreakupSummary.setDescriptionLocal(vcutOperationMaster.getDescriptionLocal());
                        vcutTvDefectBreakupSummary.setStyle(sKey);
                        if (vcutStylePlanSessionBreakup.getType() != null && (vcutStylePlanSessionBreakup.getType().equalsIgnoreCase("LUNCH") || vcutStylePlanSessionBreakup.getType().equalsIgnoreCase("TEA"))) {
                            vcutTvDefectBreakupSummary.setHours("B");
                        } else {
                            ++hour;
                            vcutTvDefectBreakupSummary.setHours(hour + "");
                        }
                        vcutTvDefectBreakupSummary.setCountDefect(dCounter);
                        previousDefect += dCounter;
                        vcutTvDefectBreakupSummary.setCountDefectCum(previousDefect);
                        vcutTvDefectBreakupSummary.setStartDate(Date.from(startDate));
                        vcutTvDefectBreakupSummary.setStartTime(vcutStylePlanSessionBreakup.getId().getStartTime());
                        vcutTvDefectBreakupSummary.setEndTime(vcutStylePlanSessionBreakup.getEndTime());
                        vcutTvDefectBreakupSummary.setSrNo(++srNo);
                        summaries.add(vcutTvDefectBreakupSummary);
                    }

                    Long dCounter = 0L;
                    if (dates.containsKey(sKey)) {
                        for (Instant date : dates.get(sKey)) {
                            if (date.isBefore(startDate) || date.isAfter(endDate)) {
                                ++dCounter;
                            }
                        }
                    }

                    if(dCounter>0) {
                        VcutTvDefectBreakupSummary vcutTvDefectBreakupSummary = new VcutTvDefectBreakupSummary();
                        vcutTvDefectBreakupSummary.setId(dKey);
                        vcutTvDefectBreakupSummary.setDescription(vcutOperationMaster.getDescription());
                        vcutTvDefectBreakupSummary.setDescriptionLocal(vcutOperationMaster.getDescriptionLocal());
                        vcutTvDefectBreakupSummary.setStyle(sKey);
                        vcutTvDefectBreakupSummary.setSessionSpl("YES");
                        vcutTvDefectBreakupSummary.setHours("***");
                        vcutTvDefectBreakupSummary.setCountDefect(dCounter);
                        previousDefect += dCounter;
                        vcutTvDefectBreakupSummary.setCountDefectCum(previousDefect);
                        vcutTvDefectBreakupSummary.setStartDate(Date.from(startDate));
                        vcutTvDefectBreakupSummary.setSrNo(++srNo);
                        summaries.add(vcutTvDefectBreakupSummary);
                    }
                }
                Collections.sort(summaries, Comparator.comparing(VcutTvDefectBreakupSummary::getStartDate).thenComparing(VcutTvDefectBreakupSummary::getSrNo));
                Long prev = 0L;
                for (VcutTvDefectBreakupSummary summary : summaries) {
                    prev += summary.getCountDefect();
                    VcutStyleOperationWiseSchUpload vcutStyleIssueWiseSchUpload = new VcutStyleOperationWiseSchUpload();
                    vcutStyleIssueWiseSchUpload.setPlanId(vcutStylePlanUpload.getId());
                    vcutStyleIssueWiseSchUpload.setPlanDate(vcutStylePlanUpload.getPlanDate());
                    vcutStyleIssueWiseSchUpload.setFactory(vcutStylePlanUpload.getFactory());
                    vcutStyleIssueWiseSchUpload.setLine(vcutStylePlanUpload.getLineNo());
                    vcutStyleIssueWiseSchUpload.setStyle(vcutStylePlanUpload.getStyle());
                    vcutStyleIssueWiseSchUpload.setSmv(vcutStylePlanUpload.getSmv());
                    vcutStyleIssueWiseSchUpload.setHour(summary.getHours());
                    if(summary.getHours() != null && summary.getHours().equalsIgnoreCase("***")) {
                        vcutStyleIssueWiseSchUpload.setHourDesc("************");
                    } else {
                        vcutStyleIssueWiseSchUpload.setHourDesc(summary.getStartTime() + " - " + summary.getEndTime());
                    }
                    vcutStyleIssueWiseSchUpload.setVcutOperationMasterId(summary.getId());
                    vcutStyleIssueWiseSchUpload.setVcutOperationMasterDesc(summary.getDescriptionLocal());
                    vcutStyleIssueWiseSchUpload.setHourlyDefectCount(summary.getCountDefect());
                    vcutStyleIssueWiseSchUpload.setCumDefectCount(prev);
                    vcutStyleIssueWiseSchUpload.setProcessDate(Timestamp.from(Instant.now()));
                    vcutStyleOperationWiseSchUploadRepository.save(vcutStyleIssueWiseSchUpload);
                }
            }
        }
    }

    private Integer removeIndex(List<VcutTvDefectBreakup> vcutTvDefectBreakups) {
        Map<Integer, Long> removeList = new HashMap<>();
        for (VcutTvDefectBreakup vcutTvDefectBreakup : vcutTvDefectBreakups) {
            int index = 0;
            for (VcutTvDefectBreakupSummary vcutTvDefectBreakupSummary : vcutTvDefectBreakup.getSummaries()) {
                if (vcutTvDefectBreakupSummary.getSessionSpl() != null && vcutTvDefectBreakupSummary.getSessionSpl().equalsIgnoreCase("YES")) {
                    if (removeList.containsKey(index)) {
                        if (vcutTvDefectBreakupSummary.getCountDefect() > removeList.get(index)) {
                            removeList.put(index, vcutTvDefectBreakupSummary.getCountDefect());
                        }
                    } else {
                        removeList.put(index, vcutTvDefectBreakupSummary.getCountDefect());
                    }
                }
                ++index;
            }
        }
        for (Integer index : removeList.keySet()) {
            Long value = removeList.get(index);
            if(value == 0L) {
                return index;
            }
        }
        return null;
    }
}
