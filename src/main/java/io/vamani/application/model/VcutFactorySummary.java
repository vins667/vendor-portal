package io.vamani.application.model;

import io.vamani.application.domain.VcutFactoryAccess;
import io.vamani.application.domain.VcutSessionMaster;
import io.vamani.application.mobile.VcutTvDefectBreakup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A Vcut_factory_summary.
 */

public class VcutFactorySummary implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String factory;

    private String line;

    private String buyer;

    private String style;

    private String poNo;

    private Double smv;

    private Long operators;

    private Long helpers;

    private Long quantity;

    private Double planEff;

    private Long achieved;

    private Double achEff;

    private Long balance;

    private Long ftt;

    private Long rectified;

    private Long alter;

    private Long rejected;

    private String activeFactory;

    private Date startTime;

    private List<VcutFactoryAccess> vcutFactoryAccesses;

    private VcutSessionMasterBean vcutSessionMaster;

    private List<VcutFactorySummary> vcutFactorySummaries;

    private List<VcutFactoryLineBreakup> vcutFactoryLineBreakups;

    private List<VcutFactoryLineBreakup> hourlyFactoryBreakups;

    private List<VcutTvDefectBreakup> vcutTvDefectBreakups;

    private List<VcutTvDefectBreakup> vcutTvDefectOBBreakups;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFactory() {
        return factory;
    }

    public VcutFactorySummary factory(String factory) {
        this.factory = factory;
        return this;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public Double getSmv() {
        return smv;
    }

    public VcutFactorySummary smv(Double smv) {
        this.smv = smv;
        return this;
    }

    public void setSmv(Double smv) {
        this.smv = smv;
    }

    public Long getOperators() {
        return operators;
    }

    public VcutFactorySummary operators(Long operators) {
        this.operators = operators;
        return this;
    }

    public void setOperators(Long operators) {
        this.operators = operators;
    }

    public Long getHelpers() {
        return helpers;
    }

    public VcutFactorySummary helpers(Long helpers) {
        this.helpers = helpers;
        return this;
    }

    public void setHelpers(Long helpers) {
        this.helpers = helpers;
    }

    public Long getQuantity() {
        return quantity;
    }

    public VcutFactorySummary quantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getPlanEff() {
        return planEff;
    }

    public VcutFactorySummary planEff(Double plan_eff) {
        this.planEff = planEff;
        return this;
    }

    public void setPlanEff(Double planEff) {
        this.planEff = planEff;
    }

    public Long getAchieved() {
        return achieved;
    }

    public VcutFactorySummary achieved(Long achieved) {
        this.achieved = achieved;
        return this;
    }

    public void setAchieved(Long achieved) {
        this.achieved = achieved;
    }

    public Long getBalance() {
        return balance;
    }

    public VcutFactorySummary balance(Long balance) {
        this.balance = balance;
        return this;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Long getFtt() {
        return ftt;
    }

    public VcutFactorySummary ftt(Long ftt) {
        this.ftt = ftt;
        return this;
    }

    public void setFtt(Long ftt) {
        this.ftt = ftt;
    }

    public Long getRectified() {
        return rectified;
    }

    public VcutFactorySummary rectified(Long rectified) {
        this.rectified = rectified;
        return this;
    }

    public void setRectified(Long rectified) {
        this.rectified = rectified;
    }

    public Long getAlter() {
        return alter;
    }

    public VcutFactorySummary alter(Long alter) {
        this.alter = alter;
        return this;
    }

    public void setAlter(Long alter) {
        this.alter = alter;
    }

    public Long getRejected() {
        return rejected;
    }

    public VcutFactorySummary rejected(Long rejected) {
        this.rejected = rejected;
        return this;
    }

    public void setRejected(Long rejected) {
        this.rejected = rejected;
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

    public String getPoNo() {
        return poNo;
    }

    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public Double getAchEff() {
        return achEff;
    }

    public void setAchEff(Double achEff) {
        this.achEff = achEff;
    }

    public VcutSessionMasterBean getVcutSessionMaster() {
        return vcutSessionMaster;
    }

    public void setVcutSessionMaster(VcutSessionMasterBean vcutSessionMaster) {
        this.vcutSessionMaster = vcutSessionMaster;
    }

    public List<VcutFactorySummary> getVcutFactorySummaries() {
        return vcutFactorySummaries;
    }

    public void setVcutFactorySummaries(List<VcutFactorySummary> vcutFactorySummaries) {
        this.vcutFactorySummaries = vcutFactorySummaries;
    }

    public List<VcutFactoryLineBreakup> getHourlyFactoryBreakups() {
        return hourlyFactoryBreakups;
    }

    public void setHourlyFactoryBreakups(List<VcutFactoryLineBreakup> hourlyFactoryBreakups) {
        this.hourlyFactoryBreakups = hourlyFactoryBreakups;
    }

    public List<VcutFactoryLineBreakup> getVcutFactoryLineBreakups() {
        return vcutFactoryLineBreakups;
    }

    public void setVcutFactoryLineBreakups(List<VcutFactoryLineBreakup> vcutFactoryLineBreakups) {
        this.vcutFactoryLineBreakups = vcutFactoryLineBreakups;
    }

    public String getActiveFactory() {
        return activeFactory;
    }

    public void setActiveFactory(String activeFactory) {
        this.activeFactory = activeFactory;
    }

    public List<VcutFactoryAccess> getVcutFactoryAccesses() {
        return vcutFactoryAccesses;
    }

    public void setVcutFactoryAccesses(List<VcutFactoryAccess> vcutFactoryAccesses) {
        this.vcutFactoryAccesses = vcutFactoryAccesses;
    }

    public List<VcutTvDefectBreakup> getVcutTvDefectBreakups() {
        return vcutTvDefectBreakups;
    }

    public void setVcutTvDefectBreakups(List<VcutTvDefectBreakup> vcutTvDefectBreakups) {
        this.vcutTvDefectBreakups = vcutTvDefectBreakups;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public List<VcutTvDefectBreakup> getVcutTvDefectOBBreakups() {
        return vcutTvDefectOBBreakups;
    }

    public void setVcutTvDefectOBBreakups(List<VcutTvDefectBreakup> vcutTvDefectOBBreakups) {
        this.vcutTvDefectOBBreakups = vcutTvDefectOBBreakups;
    }
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VcutFactorySummary)) {
            return false;
        }
        return id != null && id.equals(((VcutFactorySummary) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "VcutFactorySummary{" +
            "id=" + getId() +
            ", factory='" + getFactory() + "'" +
            ", smv=" + getSmv() +
            ", operators=" + getOperators() +
            ", helpers=" + getHelpers() +
            ", quantity=" + getQuantity() +
            ", plan_eff=" + getPlanEff() +
            ", achieved=" + getAchieved() +
            ", balance=" + getBalance() +
            ", ftt=" + getFtt() +
            ", rectified=" + getRectified() +
            ", alter=" + getAlter() +
            ", rejected=" + getRejected() +
            "}";
    }
}
