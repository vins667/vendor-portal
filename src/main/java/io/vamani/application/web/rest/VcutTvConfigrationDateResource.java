package io.vamani.application.web.rest;

import io.vamani.application.domain.*;
import io.vamani.application.mobile.*;
import io.vamani.application.model.Master;
import io.vamani.application.model.VcutFactoryLineBreakup;
import io.vamani.application.repository.*;
import io.vamani.application.util.DateUtils;
import io.vamani.application.util.MD5UrlEncryption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * REST controller for managing {@link VcutStylePlanUpload}.
 */
@RestController
@RequestMapping("/api")
public class VcutTvConfigrationDateResource {

    private final Logger log = LoggerFactory.getLogger(VcutTvConfigrationDateResource.class);
    final VcutTvConfigrationRepository vcutTvConfigrationRepository;

    @Autowired
    private VcutStylePlanUploadRepository vcutStylePlanUploadRepository;

    @Autowired
    private VcutStylePlanSessionBreakupRepository vcutStylePlanSessionBreakupRepository;

    @Autowired
    private VcutMainEntryMasterRepository vcutMainEntryMasterRepository;

    @Autowired
    private VcutOperationIssueMasterRepository vcutOperationIssueMasterRepository;

    @Autowired
    private VcutOperationMasterRepository vcutOperationMasterRepository;

    @Autowired
    private VcutStyleImageRepository vcutStyleImageRepository;

    public VcutTvConfigrationDateResource(VcutTvConfigrationRepository vcutTvConfigrationRepository) {
        this.vcutTvConfigrationRepository = vcutTvConfigrationRepository;
    }

    /**
     * {@code GET  /vcut-style-plan-uploads} : get all the vcutStylePlanUploads.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vcutStylePlanUploads in body.
     */
    @PostMapping("/vcut-tv-configrations")
    public ResponseEntity<List<VcutStylePlanUpload>> getAllVcutTvConfigrations(@RequestBody Master master) throws ParseException {
        log.debug("REST request to get a page of VcutTvConfigrations");
        Date planDate = new SimpleDateFormat("dd/MM/yyyy").parse(master.getId());
        LocalDate localDate = DateUtils.asLocalDate(planDate);
        List<VcutStylePlanUpload> page = vcutStylePlanUploadRepository.findByPlanDateAndLineNo(localDate, master.getDesc());
        return ResponseEntity.ok().body(page);
    }

    /**
     * {@code GET  /vcut-style-plan-uploads} : get all the vcutStylePlanUploads.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vcutStylePlanUploads in body.
     */
    @GetMapping("/vcut-tv-dashboard/{id}")
    public ResponseEntity<VcutTvDashboard> getAllVcutTvDashboard(@PathVariable Long id) throws ParseException {
        log.debug("REST request to get a page of VcutTvConfigrations");
        VcutTvDashboard vcutTvDashboard = new VcutTvDashboard();
        VcutStylePlanUpload vcutStylePlanUpload = vcutStylePlanUploadRepository.findById(id).orElse(null);
        if (vcutStylePlanUpload != null) {
            Date currentDate = new Date();
            SimpleDateFormat format = new SimpleDateFormat("dd-MMM hh:mm a");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat dateFormatWithTime = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            vcutTvDashboard.setCurrentDateTime(format.format(currentDate));
            vcutTvDashboard.setDay("Day " + vcutStylePlanUpload.getDays());
            vcutTvDashboard.setBuyerName(vcutStylePlanUpload.getBuyerName());
            vcutTvDashboard.setStyle(vcutStylePlanUpload.getStyle());
            vcutTvDashboard.setColor(vcutStylePlanUpload.getColorName());
            vcutTvDashboard.setPoNo(vcutStylePlanUpload.getPoNo());
            vcutTvDashboard.setOperators(vcutStylePlanUpload.getOperators().longValue());
            vcutTvDashboard.setHelpers(vcutStylePlanUpload.getHelpers().longValue());
            vcutTvDashboard.setDayTarget(vcutStylePlanUpload.getQuantity().longValue());
            vcutTvDashboard.setPlannedEfficiency(vcutStylePlanUpload.getKickOff());
            List<VcutStylePlanSessionBreakup> stylePlanSessionBreakups = vcutStylePlanSessionBreakupRepository.findAllByVcutPlanId(vcutStylePlanUpload.getId());
            if (stylePlanSessionBreakups != null && stylePlanSessionBreakups.size() > 0) {
                long planQuantity = 0;
                for (VcutStylePlanSessionBreakup vcutStylePlanSessionBreakup : stylePlanSessionBreakups) {
                    Date startDate = dateFormatWithTime.parse(dateFormat.format(currentDate) + " " + vcutStylePlanSessionBreakup.getId().getStartTime());
                    Date endDate = dateFormatWithTime.parse(dateFormat.format(currentDate) + " " + vcutStylePlanSessionBreakup.getEndTime());
                    planQuantity += vcutStylePlanSessionBreakup.getPlanQuantity().longValue();
                    if (currentDate.equals(startDate) || (currentDate.after(startDate) && currentDate.before(endDate))) {
                        vcutTvDashboard.setCumulativeTarget(planQuantity);
                    }
                }
                Date startDate = dateFormatWithTime.parse(dateFormat.format(currentDate) + " " + stylePlanSessionBreakups.get(0).getId().getStartTime());
                Date endDate = dateFormatWithTime.parse(dateFormat.format(currentDate) + " " + stylePlanSessionBreakups.get(stylePlanSessionBreakups.size() - 1).getEndTime());
                if (vcutTvDashboard.getCumulativeTarget() == null) {
                    if (currentDate.before(startDate)) {
                        vcutTvDashboard.setCumulativeTarget(0L);
                    } else if (currentDate.after(endDate)) {
                        vcutTvDashboard.setCumulativeTarget(vcutStylePlanUpload.getQuantity().longValue());
                    }
                }

                long diff = currentDate.getTime() - startDate.getTime();
                long diffMinutes = diff / (60 * 1000) % 60;
                long diffHours = diff / (60 * 60 * 1000) % 24;
                long diffDays = diff / (24 * 60 * 60 * 1000);
                vcutTvDashboard.setFinishDays(diffDays + "");
                vcutTvDashboard.setFinishHours(diffHours + "");
                vcutTvDashboard.setFinishMinutes(diffMinutes + "");
            }

            Long lineFtt = 0L;
            Long lineAlt = 0L;
            Long lineRej = 0L;
            Long lineRec = 0L;
            List<VcutMainEntryMaster> vcutMainEntryMasters = vcutMainEntryMasterRepository.findByPlanId(vcutStylePlanUpload.getId());
            if (vcutMainEntryMasters != null && vcutMainEntryMasters.size() > 0) {
                for (VcutMainEntryMaster entryMaster : vcutMainEntryMasters) {
                    if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("FTT")) {
                        ++lineFtt;
                    } else if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("REJ")) {
                        ++lineRej;
                    } else if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("ALT") && entryMaster.getRectifiedDate() != null) {
                        ++lineRec;
                    } else if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("ALT")) {
                        ++lineAlt;
                    }
                }
            }
            vcutTvDashboard.setActualTarget(lineFtt + lineRec);

            vcutTvDashboard.setVarianceTarget(vcutStylePlanUpload.getQuantity().longValue() - (lineFtt + lineRec));
            double percentageEff = (vcutTvDashboard.getCumulativeTarget().doubleValue() / vcutStylePlanUpload.getKickOff()) * 100;
            double ftpPercentage = 0;
            double rectifiedPercentage = 0;
            double percentageEffHour = 0;
            if ((lineFtt + lineRec) > 0) {
                if(lineFtt.doubleValue()>0) {
                    ftpPercentage = ((lineFtt.doubleValue() * 100) / (lineFtt + lineRec));
                }
                if(lineRec.doubleValue()>0) {
                    rectifiedPercentage = ((lineRec.doubleValue() * 100) / (lineAlt + lineRec));
                }
                if (percentageEff > 0) {
                    percentageEffHour = ((lineFtt.doubleValue() + lineRec.doubleValue()) / percentageEff) * 100;
                }
            }
            vcutTvDashboard.setFtpPercentage(new BigDecimal(ftpPercentage).setScale(2, RoundingMode.CEILING).doubleValue());
            vcutTvDashboard.setRectifiedPercentage(new BigDecimal(rectifiedPercentage).setScale(2, RoundingMode.CEILING).doubleValue());
            vcutTvDashboard.setRejected(lineRej);
            vcutTvDashboard.setActualEfficiency(new BigDecimal(percentageEffHour).setScale(2, RoundingMode.CEILING).doubleValue());
        } else {
            return ResponseEntity.badRequest().body(vcutTvDashboard);
        }
        return ResponseEntity.ok().body(vcutTvDashboard);
    }


    /**
     * {@code GET  /vcut-style-plan-uploads} : get all the vcutStylePlanUploads.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vcutStylePlanUploads in body.
     */
    @GetMapping("/vcut-tv-line-summary/{id}")
    public ResponseEntity<VcutTvLineSummary> getAllVcutLineSummary(@PathVariable Long id) throws ParseException {
        log.debug("REST request to get a page of VcutTvConfigrations");
        VcutTvLineSummary vcutTvLineSummary = new VcutTvLineSummary();
        VcutStylePlanUpload vcutStylePlanUpload = vcutStylePlanUploadRepository.findById(id).orElse(null);
        if (vcutStylePlanUpload != null) {
            Date planDate = DateUtils.asDate(vcutStylePlanUpload.getPlanDate());
            Date currentDate = new Date();
            SimpleDateFormat format = new SimpleDateFormat("dd-MMM hh:mm a");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat simpleDateFormatWithTime = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            vcutTvLineSummary.setCurrentDateTime(format.format(currentDate));
            vcutTvLineSummary.setDay("Day " + vcutStylePlanUpload.getDays());
            vcutTvLineSummary.setBuyerName(vcutStylePlanUpload.getBuyerName());
            vcutTvLineSummary.setStyle(vcutStylePlanUpload.getStyle());
            vcutTvLineSummary.setColor(vcutStylePlanUpload.getColorName());
            vcutTvLineSummary.setPoNo(vcutStylePlanUpload.getPoNo());
            List<VcutStylePlanSessionBreakup> stylePlanSessionBreakups = vcutStylePlanSessionBreakupRepository.findAllByVcutPlanId(vcutStylePlanUpload.getId());
            List<VcutMainEntryMaster> vcutMainEntryMasters = vcutMainEntryMasterRepository.findByPlanId(vcutStylePlanUpload.getId());
            if (stylePlanSessionBreakups != null && stylePlanSessionBreakups.size() > 0) {
                List<VcutFactoryLineBreakup> vcutFactoryLineBreakups = new ArrayList<>();
                Long i = 0L;
                Long totPlanValue = 0L;
                Long totHourFtt = 0L;
                Long totHourActual = 0L;
                String activeHour = "N";
                for (VcutStylePlanSessionBreakup vcutStylePlanSessionBreakup : stylePlanSessionBreakups) {
                    VcutFactoryLineBreakup vcutFactoryLineBreakup = new VcutFactoryLineBreakup();
                    if(vcutStylePlanSessionBreakup.getType() != null && (vcutStylePlanSessionBreakup.getType().equalsIgnoreCase("LUNCH") || vcutStylePlanSessionBreakup.getType().equalsIgnoreCase("TEA"))) {
                        vcutFactoryLineBreakup.setId("B");
                    } else {
                        ++i;
                        vcutFactoryLineBreakup.setId(i+"");
                    }
                    vcutFactoryLineBreakup.setHourBreakup(vcutStylePlanSessionBreakup.getId().getStartTime() + " - " + vcutStylePlanSessionBreakup.getEndTime());
                    vcutFactoryLineBreakup.setDisplayFlag(vcutStylePlanSessionBreakup.getType());
                    vcutFactoryLineBreakup.setHourPlan(vcutStylePlanSessionBreakup.getPlanQuantity().longValue());
                    totPlanValue += vcutStylePlanSessionBreakup.getPlanQuantity().longValue();
                    vcutFactoryLineBreakup.setCumPlan(totPlanValue);

                    Long hourFtt = 0L;
                    Long hourRec = 0L;
                    Instant startDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + vcutStylePlanSessionBreakup.getId().getStartTime())).toInstant();
                    Instant endDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + vcutStylePlanSessionBreakup.getEndTime())).toInstant();
                    if (activeHour != null && activeHour.equalsIgnoreCase("Y")) {
                        vcutFactoryLineBreakup.setActiveHour("Z");
                    } else {
                        if (currentDate.toInstant().equals(startDate) || (currentDate.toInstant().isAfter(startDate) && currentDate.toInstant().isBefore(endDate))) {
                            activeHour = "Y";
                            vcutFactoryLineBreakup.setActiveHour("Y");
                        } else {
                            vcutFactoryLineBreakup.setActiveHour("N");
                        }
                    }
                    if (vcutMainEntryMasters != null && vcutMainEntryMasters.size() > 0) {
                        for (VcutMainEntryMaster entryMaster : vcutMainEntryMasters) {
                            if (entryMaster.getEntryTime().equals(startDate) || (entryMaster.getEntryTime().isAfter(startDate) && entryMaster.getEntryTime().isBefore(endDate))) {
                                if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("FTT")) {
                                    ++hourFtt;
                                } else if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("ALT") && entryMaster.getRectifiedDate() != null) {
                                    ++hourRec;
                                }
                            }
                        }
                    }
                    Long hourActual = hourFtt + hourRec;
                    totHourActual += hourActual;
                    totHourFtt += hourFtt;
                    vcutFactoryLineBreakup.setHourActual(hourActual);
                    vcutFactoryLineBreakup.setCumActual(totHourActual);
                    vcutFactoryLineBreakup.setVarianceHour(hourActual - vcutStylePlanSessionBreakup.getPlanQuantity().longValue());
                    vcutFactoryLineBreakup.setVarianceCum(totHourActual - totPlanValue);

                    if (hourActual != null && hourActual > 0 && vcutStylePlanSessionBreakup.getPlanQuantity() != null && vcutStylePlanSessionBreakup.getPlanQuantity().longValue() > 0) {
                        double percentageEff = (vcutStylePlanSessionBreakup.getPlanQuantity().doubleValue() / vcutStylePlanUpload.getKickOff()) * 100;
                        double percentageEffHour = (hourActual.doubleValue() / percentageEff) * 100;
                        vcutFactoryLineBreakup.setEfficiencyPlan(new BigDecimal(percentageEffHour).setScale(2, RoundingMode.CEILING).doubleValue());
                    } else {
                        vcutFactoryLineBreakup.setEfficiencyPlan(0.0);
                    }
                    if (totHourActual > 0) {
                        double percentageEff = (totPlanValue.doubleValue() / vcutStylePlanUpload.getKickOff()) * 100;
                        double percentageEffHour = (totHourActual.doubleValue() / percentageEff) * 100;
                        vcutFactoryLineBreakup.setEfficiencyActual(new BigDecimal(percentageEffHour).setScale(2, RoundingMode.CEILING).doubleValue());
                    } else {
                        vcutFactoryLineBreakup.setEfficiencyActual(0.0);
                    }

                    if (hourActual.doubleValue() > 0) {
                        double fttRateHour = (hourFtt.doubleValue() * 100) / hourActual.doubleValue();
                        vcutFactoryLineBreakup.setFttRatePlan(new BigDecimal(fttRateHour).setScale(2, RoundingMode.CEILING).doubleValue());
                    } else {
                        vcutFactoryLineBreakup.setFttRatePlan(0.0);
                    }

                    if (totHourFtt.doubleValue() > 0 && totHourActual > 0) {
                        double fttRateHour = (totHourFtt.doubleValue() * 100) / totHourActual.doubleValue();
                        vcutFactoryLineBreakup.setFttRateActual(new BigDecimal(fttRateHour).setScale(2, RoundingMode.CEILING).doubleValue());
                    } else {
                        vcutFactoryLineBreakup.setFttRateActual(0.0);
                    }
                    vcutFactoryLineBreakups.add(vcutFactoryLineBreakup);
                }

                Instant startDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + stylePlanSessionBreakups.get(0).getId().getStartTime())).toInstant();
                Instant endDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + stylePlanSessionBreakups.get(stylePlanSessionBreakups.size() - 1).getEndTime())).toInstant();
                long diff = currentDate.getTime() - Date.from(startDate).getTime();
                long diffMinutes = diff / (60 * 1000) % 60;
                long diffHours = diff / (60 * 60 * 1000) % 24;
                long diffDays = diff / (24 * 60 * 60 * 1000);
                vcutTvLineSummary.setFinishDays(diffDays + "");
                vcutTvLineSummary.setFinishHours(diffHours + "");
                vcutTvLineSummary.setFinishMinutes(diffMinutes + "");

                if (vcutMainEntryMasters != null && vcutMainEntryMasters.size() > 0) {
                    Long hourFtt = 0L;
                    Long hourRec = 0L;
                    for (VcutMainEntryMaster entryMaster : vcutMainEntryMasters) {
                        if (entryMaster.getEntryTime().isBefore(startDate) || entryMaster.getEntryTime().isAfter(endDate)) {
                            if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("FTT")) {
                                ++hourFtt;
                            } else if (entryMaster.getEntryType() != null && entryMaster.getEntryType().equalsIgnoreCase("ALT") && entryMaster.getRectifiedDate() != null) {
                                ++hourRec;
                            }
                        }
                    }
                    if (hourFtt > 0 || hourRec > 0) {
                        VcutFactoryLineBreakup vcutFactoryLineBreakup = new VcutFactoryLineBreakup();
                        ++i;
                        vcutFactoryLineBreakup.setId(i+"");
                        vcutFactoryLineBreakup.setHourBreakup("************");
                        vcutFactoryLineBreakup.setDisplayFlag("0");
                        Long hourActual = hourFtt + hourRec;
                        totHourActual += hourActual;
                        totHourFtt += hourFtt;
                        vcutFactoryLineBreakup.setHourPlan(0L);
                        vcutFactoryLineBreakup.setCumPlan(totPlanValue);
                        vcutFactoryLineBreakup.setHourActual(hourActual);
                        vcutFactoryLineBreakup.setCumActual(totHourActual);
                        vcutFactoryLineBreakup.setVarianceHour(hourActual - 0L);
                        vcutFactoryLineBreakup.setVarianceCum(totHourActual - totPlanValue);

                        vcutFactoryLineBreakup.setEfficiencyPlan(0.0);

                        if (totHourActual > 0) {
                            double percentageEff = (totPlanValue.doubleValue() / vcutStylePlanUpload.getKickOff()) * 100;
                            double percentageEffHour = (totHourActual.doubleValue() / percentageEff) * 100;
                            vcutFactoryLineBreakup.setEfficiencyActual(new BigDecimal(percentageEffHour).setScale(2, RoundingMode.CEILING).doubleValue());
                        } else {
                            vcutFactoryLineBreakup.setEfficiencyActual(0.0);
                        }

                        if (hourActual.doubleValue() > 0) {
                            double fttRateHour = (hourFtt.doubleValue() * 100) / hourActual.doubleValue();
                            vcutFactoryLineBreakup.setFttRatePlan(new BigDecimal(fttRateHour).setScale(2, RoundingMode.CEILING).doubleValue());
                        } else {
                            vcutFactoryLineBreakup.setFttRatePlan(0.0);
                        }
                        if (totHourFtt.doubleValue() > 0 && totHourActual > 0) {
                            double fttRateHour = (totHourFtt.doubleValue() * 100) / totHourActual.doubleValue();
                            vcutFactoryLineBreakup.setFttRateActual(new BigDecimal(fttRateHour).setScale(2, RoundingMode.CEILING).doubleValue());
                        } else {
                            vcutFactoryLineBreakup.setFttRateActual(0.0);
                        }
                        vcutFactoryLineBreakups.add(vcutFactoryLineBreakup);
                    }
                }
                vcutTvLineSummary.setVcutFactoryLineBreakups(vcutFactoryLineBreakups);
            }
        } else {
            return ResponseEntity.badRequest().body(vcutTvLineSummary);
        }
        return ResponseEntity.ok().body(vcutTvLineSummary);
    }

    /**
     * {@code GET  /vcut-style-plan-uploads} : get all the vcutStylePlanUploads.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vcutStylePlanUploads in body.
     */
    @GetMapping("/vcut-tv-defect-summary/{id}")
    public ResponseEntity<VcutTvDefectSummary> getAllVcutDefectSummary(@PathVariable Long id) throws ParseException {
        log.debug("REST request to get a page of VcutTvConfigrations");
        VcutTvDefectSummary vcutTvDefectSummary = new VcutTvDefectSummary();

        VcutStylePlanUpload vcutStylePlanUpload = vcutStylePlanUploadRepository.findById(id).orElse(null);
        if (vcutStylePlanUpload != null) {
            Date planDate = DateUtils.asDate(vcutStylePlanUpload.getPlanDate());
            Date currentDate = new Date();
            SimpleDateFormat format = new SimpleDateFormat("dd-MMM hh:mm a");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat simpleDateFormatWithTime = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            vcutTvDefectSummary.setCurrentDateTime(format.format(currentDate));
            vcutTvDefectSummary.setDay("Day " + vcutStylePlanUpload.getDays());
            vcutTvDefectSummary.setBuyerName(vcutStylePlanUpload.getBuyerName());
            vcutTvDefectSummary.setStyle(vcutStylePlanUpload.getStyle());
            vcutTvDefectSummary.setColor(vcutStylePlanUpload.getColorName());
            vcutTvDefectSummary.setPoNo(vcutStylePlanUpload.getPoNo());

            List<VcutStylePlanSessionBreakup> stylePlanSessionBreakups = vcutStylePlanSessionBreakupRepository.findAllByVcutPlanId(vcutStylePlanUpload.getId());
            List<Object[]> vcutMainEntryMasters = vcutMainEntryMasterRepository.findByDefectsPlanId(vcutStylePlanUpload.getId());
            if (stylePlanSessionBreakups != null && stylePlanSessionBreakups.size() > 0) {
                vcutTvDefectSummary.setTotalHour(Long.parseLong(stylePlanSessionBreakups.size() + ""));
                Instant startDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + stylePlanSessionBreakups.get(0).getId().getStartTime())).toInstant();
                long diff = currentDate.getTime() - Date.from(startDate).getTime();
                long diffMinutes = diff / (60 * 1000) % 60;
                long diffHours = diff / (60 * 60 * 1000) % 24;
                long diffDays = diff / (24 * 60 * 60 * 1000);
                vcutTvDefectSummary.setFinishDays(diffDays + "");
                vcutTvDefectSummary.setFinishHours(diffHours + "");
                vcutTvDefectSummary.setFinishMinutes(diffMinutes + "");
                Long totDefects = 0L;
                Map<Long, Long> defectMap = new HashMap<>();
                Map<Long, List<Instant>> defectDateMap = new HashMap<>();
                for (Object[] objects : vcutMainEntryMasters) {
                    ++totDefects;
                    if (defectMap.containsKey(Long.parseLong(objects[1].toString()))) {
                        Long defectCount = defectMap.get(Long.parseLong(objects[1].toString()));
                        ++defectCount;
                        defectMap.put(Long.parseLong(objects[1].toString()), defectCount);

                        List<Instant> dates = defectDateMap.get(Long.parseLong(objects[1].toString()));
                        Instant date = Instant.parse(objects[0].toString());
                        dates.add(date);
                        defectDateMap.put(Long.parseLong(objects[1].toString()), dates);
                    } else {
                        defectMap.put(Long.parseLong(objects[1].toString()), 1L);

                        List<Instant> dates = new ArrayList<>();
                        Instant date = Instant.parse(objects[0].toString());
                        dates.add(date);
                        defectDateMap.put(Long.parseLong(objects[1].toString()), dates);
                    }
                }
                List<VcutTvDefectBreakup> vcutTvDefectBreakups = new ArrayList<>();
                for (long key : defectMap.keySet()) {
                    VcutOperationIssueMaster vcutOperationIssueMaster = vcutOperationIssueMasterRepository.findById(key).orElse(null);
                    VcutTvDefectBreakup vcutTvDefectBreakup = new VcutTvDefectBreakup();
                    vcutTvDefectBreakup.setId(key);
                    vcutTvDefectBreakup.setDescription(vcutOperationIssueMaster.getDescription());
                    vcutTvDefectBreakup.setDescriptionLocal(vcutOperationIssueMaster.getDescriptionLocal());
                    vcutTvDefectBreakup.setDefectCount(defectMap.get(key));
                    List<Instant> dates = defectDateMap.get(key);
                    Long hours = 0L;
                    List<VcutTvDefectBreakupSummary> summaries = new ArrayList<>();
                    for (VcutStylePlanSessionBreakup vcutStylePlanSessionBreakup : stylePlanSessionBreakups) {
                        ++hours;
                        Instant rowstartDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + vcutStylePlanSessionBreakup.getId().getStartTime())).toInstant();
                        Instant rowendDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + vcutStylePlanSessionBreakup.getEndTime())).toInstant();
                        Long lineCtr = 0L;
                        for (Instant date : dates) {
                            if (date.equals(rowstartDate) || (date.isAfter(rowstartDate) && date.isBefore(rowendDate))) {
                                ++lineCtr;
                            }
                        }
                        VcutTvDefectBreakupSummary vcutTvDefectBreakupSummary = new VcutTvDefectBreakupSummary();
                        vcutTvDefectBreakupSummary.setId(key);
                        vcutTvDefectBreakupSummary.setDescription(vcutOperationIssueMaster.getDescription());
                        vcutTvDefectBreakupSummary.setDescriptionLocal(vcutOperationIssueMaster.getDescriptionLocal());
                        vcutTvDefectBreakupSummary.setHours(hours+"");
                        vcutTvDefectBreakupSummary.setCountDefect(lineCtr);
                        summaries.add(vcutTvDefectBreakupSummary);
                    }
                    vcutTvDefectBreakup.setSummaries(summaries);
                    vcutTvDefectBreakups.add(vcutTvDefectBreakup);
                }
                vcutTvDefectSummary.setDefects(totDefects);
                Collections.sort(vcutTvDefectBreakups, Collections.reverseOrder(Comparator.comparing(VcutTvDefectBreakup::getDefectCount)));
                vcutTvDefectSummary.setVcutTvDefectBreakups(vcutTvDefectBreakups);
            }
        } else {
            return ResponseEntity.badRequest().body(vcutTvDefectSummary);
        }
        return ResponseEntity.ok().body(vcutTvDefectSummary);
    }

    /**
     * {@code GET  /vcut-style-plan-uploads} : get all the vcutStylePlanUploads.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vcutStylePlanUploads in body.
     */
    @GetMapping("/vcut-tv-defect-operation-summary/{id}")
    public ResponseEntity<VcutTvDefectSummary> getAllVcutDefectOperationSummary(@PathVariable Long id) throws ParseException {
        log.debug("REST request to get a page of VcutTvConfigrations");
        VcutTvDefectSummary vcutTvDefectSummary = new VcutTvDefectSummary();

        VcutStylePlanUpload vcutStylePlanUpload = vcutStylePlanUploadRepository.findById(id).orElse(null);
        if (vcutStylePlanUpload != null) {
            Date planDate = DateUtils.asDate(vcutStylePlanUpload.getPlanDate());
            Date currentDate = new Date();
            SimpleDateFormat format = new SimpleDateFormat("dd-MMM hh:mm a");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat simpleDateFormatWithTime = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            vcutTvDefectSummary.setCurrentDateTime(format.format(currentDate));
            vcutTvDefectSummary.setDay("Day " + vcutStylePlanUpload.getDays());
            vcutTvDefectSummary.setBuyerName(vcutStylePlanUpload.getBuyerName());
            vcutTvDefectSummary.setStyle(vcutStylePlanUpload.getStyle());
            vcutTvDefectSummary.setColor(vcutStylePlanUpload.getColorName());
            vcutTvDefectSummary.setPoNo(vcutStylePlanUpload.getPoNo());

            List<VcutStylePlanSessionBreakup> stylePlanSessionBreakups = vcutStylePlanSessionBreakupRepository.findAllByVcutPlanId(vcutStylePlanUpload.getId());
            List<Object[]> vcutMainEntryMasters = vcutMainEntryMasterRepository.findByDefectsOperationPlanId(vcutStylePlanUpload.getId());
            if (stylePlanSessionBreakups != null && stylePlanSessionBreakups.size() > 0) {
                vcutTvDefectSummary.setTotalHour(Long.parseLong(stylePlanSessionBreakups.size() + ""));
                Instant startDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + stylePlanSessionBreakups.get(0).getId().getStartTime())).toInstant();
                long diff = currentDate.getTime() - Date.from(startDate).getTime();
                long diffMinutes = diff / (60 * 1000) % 60;
                long diffHours = diff / (60 * 60 * 1000) % 24;
                long diffDays = diff / (24 * 60 * 60 * 1000);
                vcutTvDefectSummary.setFinishDays(diffDays + "");
                vcutTvDefectSummary.setFinishHours(diffHours + "");
                vcutTvDefectSummary.setFinishMinutes(diffMinutes + "");
                Map<Long, Long> defectMap = new HashMap<>();
                Map<Long, List<Instant>> defectDateMap = new HashMap<>();
                Long totDefects = 0L;
                for (Object[] objects : vcutMainEntryMasters) {
                    totDefects += Long.parseLong(objects[2].toString());
                    if (defectMap.containsKey(Long.parseLong(objects[1].toString()))) {
                        Long defectCount = defectMap.get(Long.parseLong(objects[1].toString()));
                        defectCount += Long.parseLong(objects[2].toString());
                        defectMap.put(Long.parseLong(objects[1].toString()), defectCount);

                        List<Instant> dates = defectDateMap.get(Long.parseLong(objects[1].toString()));
                        Instant date = Instant.parse(objects[0].toString());
                        dates.add(date);
                        defectDateMap.put(Long.parseLong(objects[1].toString()), dates);
                    } else {
                        defectMap.put(Long.parseLong(objects[1].toString()), Long.parseLong(objects[2].toString()));

                        List<Instant> dates = new ArrayList<>();
                        Instant date = Instant.parse(objects[0].toString());
                        dates.add(date);
                        defectDateMap.put(Long.parseLong(objects[1].toString()), dates);
                    }
                }
                List<VcutTvDefectBreakup> vcutTvDefectBreakups = new ArrayList<>();
                for (long key : defectMap.keySet()) {
                    VcutOperationMaster vcutOperationMaster = vcutOperationMasterRepository.findById(key).orElse(null);
                    VcutTvDefectBreakup vcutTvDefectBreakup = new VcutTvDefectBreakup();
                    vcutTvDefectBreakup.setId(key);
                    vcutTvDefectBreakup.setDescription(vcutOperationMaster.getDescription());
                    vcutTvDefectBreakup.setDescriptionLocal(vcutOperationMaster.getDescriptionLocal());
                    vcutTvDefectBreakup.setDefectCount(defectMap.get(key));
                    List<Instant> dates = defectDateMap.get(key);
                    Long hours = 0L;
                    List<VcutTvDefectBreakupSummary> summaries = new ArrayList<>();
                    for (VcutStylePlanSessionBreakup vcutStylePlanSessionBreakup : stylePlanSessionBreakups) {
                        ++hours;
                        Instant rowstartDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + vcutStylePlanSessionBreakup.getId().getStartTime())).toInstant();
                        Instant rowendDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + vcutStylePlanSessionBreakup.getEndTime())).toInstant();
                        Long lineCtr = 0L;
                        for (Instant date : dates) {
                            if (date.equals(rowstartDate) || (date.isAfter(rowstartDate) && date.isBefore(rowendDate))) {
                                ++lineCtr;
                            }
                        }
                        VcutTvDefectBreakupSummary vcutTvDefectBreakupSummary = new VcutTvDefectBreakupSummary();
                        vcutTvDefectBreakupSummary.setId(key);
                        vcutTvDefectBreakupSummary.setDescription(vcutOperationMaster.getDescription());
                        vcutTvDefectBreakupSummary.setDescriptionLocal(vcutOperationMaster.getDescriptionLocal());
                        vcutTvDefectBreakupSummary.setHours(hours+"");
                        vcutTvDefectBreakupSummary.setCountDefect(lineCtr);
                        summaries.add(vcutTvDefectBreakupSummary);
                    }
                    vcutTvDefectBreakup.setSummaries(summaries);
                    vcutTvDefectBreakups.add(vcutTvDefectBreakup);
                }
                vcutTvDefectSummary.setDefects(totDefects);
                Collections.sort(vcutTvDefectBreakups, Collections.reverseOrder(Comparator.comparing(VcutTvDefectBreakup::getDefectCount)));
                vcutTvDefectSummary.setVcutTvDefectBreakups(vcutTvDefectBreakups);
            }
        } else {
            return ResponseEntity.badRequest().body(vcutTvDefectSummary);
        }
        return ResponseEntity.ok().body(vcutTvDefectSummary);
    }


    /**
     * {@code GET  /vcut-style-plan-uploads} : get all the vcutStylePlanUploads.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vcutStylePlanUploads in body.
     */
    @GetMapping("/vcut-tv-image-summary/{id}")
    public ResponseEntity<VcutTvImageSummary> getAllVcutImageSummary(@PathVariable Long id) throws ParseException, NoSuchAlgorithmException {
        log.debug("REST request to get a page of VcutTvConfigrations");
        VcutTvImageSummary vcutTvImageSummary = new VcutTvImageSummary();

        VcutStylePlanUpload vcutStylePlanUpload = vcutStylePlanUploadRepository.findById(id).orElse(null);
        if (vcutStylePlanUpload != null) {
            Date planDate = DateUtils.asDate(vcutStylePlanUpload.getPlanDate());
            Date currentDate = new Date();
            SimpleDateFormat format = new SimpleDateFormat("dd-MMM hh:mm a");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat simpleDateFormatWithTime = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            vcutTvImageSummary.setCurrentDateTime(format.format(currentDate));
            vcutTvImageSummary.setDay("Day " + vcutStylePlanUpload.getDays());
            vcutTvImageSummary.setBuyerName(vcutStylePlanUpload.getBuyerName());
            vcutTvImageSummary.setStyle(vcutStylePlanUpload.getStyle());
            vcutTvImageSummary.setColor(vcutStylePlanUpload.getColorName());
            vcutTvImageSummary.setPoNo(vcutStylePlanUpload.getPoNo());
            List<VcutStylePlanSessionBreakup> stylePlanSessionBreakups = vcutStylePlanSessionBreakupRepository.findAllByVcutPlanId(vcutStylePlanUpload.getId());
            if (stylePlanSessionBreakups != null && stylePlanSessionBreakups.size() > 0) {
                Instant startDate = (simpleDateFormatWithTime.parse(simpleDateFormat.format(planDate) + " " + stylePlanSessionBreakups.get(0).getId().getStartTime())).toInstant();
                long diff = currentDate.getTime() - Date.from(startDate).getTime();
                long diffMinutes = diff / (60 * 1000) % 60;
                long diffHours = diff / (60 * 60 * 1000) % 24;
                long diffDays = diff / (24 * 60 * 60 * 1000);
                vcutTvImageSummary.setFinishDays(diffDays + "");
                vcutTvImageSummary.setFinishHours(diffHours + "");
                vcutTvImageSummary.setFinishMinutes(diffMinutes + "");
            }

            VcutStyleImage vcutStyleImage = vcutStyleImageRepository.findByStyle(vcutStylePlanUpload.getStyle());
            if(vcutStyleImage != null) {
                vcutTvImageSummary.setImageBack(MD5UrlEncryption.fakeUploadUrl("style-sketch/" + vcutStyleImage.getBackImage()));
                vcutTvImageSummary.setImageFront(MD5UrlEncryption.fakeUploadUrl("style-sketch/" + vcutStyleImage.getFrontImage()));
            }
            List<Object[]> objectsList = vcutMainEntryMasterRepository.findByDefectsAxisPlanId(vcutStylePlanUpload.getId());
            Map<String, List<VcutTvcCordinate>> cordinateMap = new HashMap<>();
            for (Object[] object : objectsList) {
                if (object[0] != null && cordinateMap.containsKey(object[0].toString())) {
                    List<VcutTvcCordinate> cordinates = cordinateMap.get(object[0].toString());
                    VcutTvcCordinate vcutTvcCordinate = new VcutTvcCordinate();
                    vcutTvcCordinate.setCoordinateType(object[0].toString());
                    vcutTvcCordinate.setCoordinateX(object[1].toString());
                    vcutTvcCordinate.setCoordinateY(object[2].toString());
                    cordinates.add(vcutTvcCordinate);
                    cordinateMap.put(object[0].toString(), cordinates);
                } else {
                    List<VcutTvcCordinate> cordinates = new ArrayList<>();
                    VcutTvcCordinate vcutTvcCordinate = new VcutTvcCordinate();
                    vcutTvcCordinate.setCoordinateType(object[0].toString());
                    vcutTvcCordinate.setCoordinateX(object[1].toString());
                    vcutTvcCordinate.setCoordinateY(object[2].toString());
                    cordinates.add(vcutTvcCordinate);
                    cordinateMap.put(object[0].toString(), cordinates);
                }
            }
            for (String key : cordinateMap.keySet()) {
                if(key != null && key.equalsIgnoreCase("F")) {
                    vcutTvImageSummary.setFrontCordinates(cordinateMap.get(key));
                } else if(key != null && key.equalsIgnoreCase("B")) {
                    vcutTvImageSummary.setBackCordinates(cordinateMap.get(key));
                }
            }
        } else {
            return ResponseEntity.badRequest().body(vcutTvImageSummary);
        }
        return ResponseEntity.ok().body(vcutTvImageSummary);
    }
}
