package io.vamani.application.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A VcutStylePlanUpload.
 */
@Entity
@Table(name = "vcut_style_plan_sch_upload")
public class VcutStylePlanSchUpload {
    @Id
    @SequenceGenerator(name="vcutStylePlanSchUploadSeq", sequenceName="vcut_style_plan_sch_upload_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="vcutStylePlanSchUploadSeq")
    private Long id;

    @Column(name = "plan_id")
    private Long planId;

    @Column(name = "plan_date")
    private LocalDate planDate;

    @Column(name = "factory")
    private String factory;

    @Column(name = "line")
    private String line;

    @Column(name = "style")
    private String style;

    @Column(name = "smv")
    private Double smv;

    @Column(name = "hour")
    private String hour;

    @Column(name = "hour_desc")
    private String hourDesc;

    @Column(name = "hourly_planned")
    private Long hourlyPlanned;

    @Column(name = "hourly_actual")
    private Long hourlyActual;

    @Column(name = "cum_planned")
    private Long cumPlanned;

    @Column(name = "cum_actual")
    private Long cumActual;

    @Column(name = "variance_hourly")
    private Long varianceHourly;

    @Column(name = "variance_cum")
    private Long varianceCum;

    @Column(name = "actual_eff_hourly")
    private Double actualEffHourly;

    @Column(name = "actual_eff_cum")
    private Double actualEffCum;

    @Column(name = "ftp_rate_hourly")
    private Double ftpRateHourly;

    @Column(name = "ftp_rate_cum")
    private Double ftpRateCum;

    @Column(name = "dhu_rate_hourly")
    private Double dhuRateHourly;

    @Column(name = "dhu_rate_cum")
    private Double dhuRateCum;

    @Column(name = "ftp")
    private Long ftp;

    @Column(name = "rectify")
    private Long rectify;

    @Column(name = "rejected")
    private Long rejected;

    @Column(name = "altered")
    private Long altered;

    @Column(name = "process_date")
    private Timestamp processDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public LocalDate getPlanDate() {
        return planDate;
    }

    public void setPlanDate(LocalDate planDate) {
        this.planDate = planDate;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Double getSmv() {
        return smv;
    }

    public void setSmv(Double smv) {
        this.smv = smv;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getHourDesc() {
        return hourDesc;
    }

    public void setHourDesc(String hourDesc) {
        this.hourDesc = hourDesc;
    }

    public Long getHourlyPlanned() {
        return hourlyPlanned;
    }

    public void setHourlyPlanned(Long hourlyPlanned) {
        this.hourlyPlanned = hourlyPlanned;
    }

    public Long getHourlyActual() {
        return hourlyActual;
    }

    public void setHourlyActual(Long hourlyActual) {
        this.hourlyActual = hourlyActual;
    }

    public Long getCumPlanned() {
        return cumPlanned;
    }

    public void setCumPlanned(Long cumPlanned) {
        this.cumPlanned = cumPlanned;
    }

    public Long getCumActual() {
        return cumActual;
    }

    public void setCumActual(Long cumActual) {
        this.cumActual = cumActual;
    }

    public Long getVarianceHourly() {
        return varianceHourly;
    }

    public void setVarianceHourly(Long varianceHourly) {
        this.varianceHourly = varianceHourly;
    }

    public Long getVarianceCum() {
        return varianceCum;
    }

    public void setVarianceCum(Long varianceCum) {
        this.varianceCum = varianceCum;
    }

    public Double getActualEffHourly() {
        return actualEffHourly;
    }

    public void setActualEffHourly(Double actualEffHourly) {
        this.actualEffHourly = actualEffHourly;
    }

    public Double getActualEffCum() {
        return actualEffCum;
    }

    public void setActualEffCum(Double actualEffCum) {
        this.actualEffCum = actualEffCum;
    }

    public Double getFtpRateHourly() {
        return ftpRateHourly;
    }

    public void setFtpRateHourly(Double ftpRateHourly) {
        this.ftpRateHourly = ftpRateHourly;
    }

    public Double getFtpRateCum() {
        return ftpRateCum;
    }

    public void setFtpRateCum(Double ftpRateCum) {
        this.ftpRateCum = ftpRateCum;
    }

    public Double getDhuRateHourly() {
        return dhuRateHourly;
    }

    public void setDhuRateHourly(Double dhuRateHourly) {
        this.dhuRateHourly = dhuRateHourly;
    }

    public Double getDhuRateCum() {
        return dhuRateCum;
    }

    public void setDhuRateCum(Double dhuRateCum) {
        this.dhuRateCum = dhuRateCum;
    }

    public Long getFtp() {
        return ftp;
    }

    public void setFtp(Long ftp) {
        this.ftp = ftp;
    }

    public Long getRectify() {
        return rectify;
    }

    public void setRectify(Long rectify) {
        this.rectify = rectify;
    }

    public Long getRejected() {
        return rejected;
    }

    public void setRejected(Long rejected) {
        this.rejected = rejected;
    }

    public Long getAltered() {
        return altered;
    }

    public void setAltered(Long altered) {
        this.altered = altered;
    }

    public Timestamp getProcessDate() {
        return processDate;
    }

    public void setProcessDate(Timestamp processDate) {
        this.processDate = processDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VcutStylePlanSchUpload that = (VcutStylePlanSchUpload) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(planId, that.planId) &&
            Objects.equals(planDate, that.planDate) &&
            Objects.equals(factory, that.factory) &&
            Objects.equals(line, that.line) &&
            Objects.equals(style, that.style) &&
            Objects.equals(smv, that.smv) &&
            Objects.equals(hour, that.hour) &&
            Objects.equals(hourDesc, that.hourDesc) &&
            Objects.equals(hourlyPlanned, that.hourlyPlanned) &&
            Objects.equals(hourlyActual, that.hourlyActual) &&
            Objects.equals(cumPlanned, that.cumPlanned) &&
            Objects.equals(cumActual, that.cumActual) &&
            Objects.equals(varianceHourly, that.varianceHourly) &&
            Objects.equals(varianceCum, that.varianceCum) &&
            Objects.equals(actualEffHourly, that.actualEffHourly) &&
            Objects.equals(actualEffCum, that.actualEffCum) &&
            Objects.equals(ftpRateHourly, that.ftpRateHourly) &&
            Objects.equals(ftpRateCum, that.ftpRateCum) &&
            Objects.equals(dhuRateHourly, that.dhuRateHourly) &&
            Objects.equals(dhuRateCum, that.dhuRateCum) &&
            Objects.equals(ftp, that.ftp) &&
            Objects.equals(rectify, that.rectify) &&
            Objects.equals(rejected, that.rejected) &&
            Objects.equals(altered, that.altered) &&
            Objects.equals(processDate, that.processDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, planId, planDate, factory, line, style, smv, hour, hourDesc, hourlyPlanned, hourlyActual, cumPlanned, cumActual, varianceHourly, varianceCum, actualEffHourly, actualEffCum, ftpRateHourly, ftpRateCum, dhuRateHourly, dhuRateCum, ftp, rectify, rejected, altered, processDate);
    }
}
