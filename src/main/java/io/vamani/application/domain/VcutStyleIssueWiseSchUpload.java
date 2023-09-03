package io.vamani.application.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
/**
 * A VcutStylePlanUpload.
 */
@Entity
@Table(name = "vcut_style_issue_wise_sch_upload")
public class VcutStyleIssueWiseSchUpload {
    @Id
    @SequenceGenerator(name="vcutStyleIssueWiseSchUploadSeq", sequenceName="vcut_style_issue_wise_sch_upload_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.AUTO, generator="vcutStyleIssueWiseSchUploadSeq")
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

    @Column(name = "vcut_operation_issue_master_id")
    private Long vcutOperationIssueMasterId;

    @Column(name = "vcut_operation_issue_master_desc")
    private String vcutOperationIssueMasterDesc;

    @Column(name = "hourly_defect_count")
    private Long hourlyDefectCount;

    @Column(name = "cum_defect_count")
    private Long cumDefectCount;

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

    public Long getVcutOperationIssueMasterId() {
        return vcutOperationIssueMasterId;
    }

    public void setVcutOperationIssueMasterId(Long vcutOperationIssueMasterId) {
        this.vcutOperationIssueMasterId = vcutOperationIssueMasterId;
    }

    public String getVcutOperationIssueMasterDesc() {
        return vcutOperationIssueMasterDesc;
    }

    public void setVcutOperationIssueMasterDesc(String vcutOperationIssueMasterDesc) {
        this.vcutOperationIssueMasterDesc = vcutOperationIssueMasterDesc;
    }

    public Long getHourlyDefectCount() {
        return hourlyDefectCount;
    }

    public void setHourlyDefectCount(Long hourlyDefectCount) {
        this.hourlyDefectCount = hourlyDefectCount;
    }

    public Long getCumDefectCount() {
        return cumDefectCount;
    }

    public void setCumDefectCount(Long cumDefectCount) {
        this.cumDefectCount = cumDefectCount;
    }

    public Timestamp getProcessDate() {
        return processDate;
    }

    public void setProcessDate(Timestamp processDate) {
        this.processDate = processDate;
    }
}
