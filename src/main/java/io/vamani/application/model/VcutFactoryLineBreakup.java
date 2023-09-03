package io.vamani.application.model;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A VcutFactoryLineBreakup.
 */
public class VcutFactoryLineBreakup implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String hourBreakup;

    private String displayFlag;

    private Long hourPlan;

    private Long hourActual;

    private Long cumPlan;

    private Long cumActual;

    private Long varianceHour;

    private Long varianceCum;

    private Double efficiencyPlan;

    private Double efficiencyActual;

    private Double fttRatePlan;

    private Double fttRateActual;

    private Double dhuRatePlan;

    private Double dhuRateActual;

    private String activeHour;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHourBreakup() {
        return hourBreakup;
    }

    public VcutFactoryLineBreakup hourBreakup(String hourBreakup) {
        this.hourBreakup = hourBreakup;
        return this;
    }

    public void setHourBreakup(String hourBreakup) {
        this.hourBreakup = hourBreakup;
    }

    public Long getHourPlan() {
        return hourPlan;
    }

    public VcutFactoryLineBreakup hourPlan(Long hourPlan) {
        this.hourPlan = hourPlan;
        return this;
    }

    public void setHourPlan(Long hourPlan) {
        this.hourPlan = hourPlan;
    }

    public Long getHourActual() {
        return hourActual;
    }

    public VcutFactoryLineBreakup hourActual(Long hourActual) {
        this.hourActual = hourActual;
        return this;
    }

    public void setHourActual(Long hourActual) {
        this.hourActual = hourActual;
    }

    public Long getCumPlan() {
        return cumPlan;
    }

    public VcutFactoryLineBreakup cumPlan(Long cumPlan) {
        this.cumPlan = cumPlan;
        return this;
    }

    public void setCumPlan(Long cumPlan) {
        this.cumPlan = cumPlan;
    }

    public Long getCumActual() {
        return cumActual;
    }

    public VcutFactoryLineBreakup cumActual(Long cumActual) {
        this.cumActual = cumActual;
        return this;
    }

    public void setCumActual(Long cumActual) {
        this.cumActual = cumActual;
    }

    public Long getVarianceHour() {
        return varianceHour;
    }

    public VcutFactoryLineBreakup varianceHour(Long varianceHour) {
        this.varianceHour = varianceHour;
        return this;
    }

    public void setVarianceHour(Long varianceHour) {
        this.varianceHour = varianceHour;
    }

    public Long getVarianceCum() {
        return varianceCum;
    }

    public VcutFactoryLineBreakup varianceCum(Long varianceCum) {
        this.varianceCum = varianceCum;
        return this;
    }

    public void setVarianceCum(Long varianceCum) {
        this.varianceCum = varianceCum;
    }

    public String getDisplayFlag() {
        return displayFlag;
    }

    public void setDisplayFlag(String displayFlag) {
        this.displayFlag = displayFlag;
    }

    public Double getEfficiencyPlan() {
        return efficiencyPlan;
    }

    public void setEfficiencyPlan(Double efficiencyPlan) {
        this.efficiencyPlan = efficiencyPlan;
    }

    public Double getEfficiencyActual() {
        return efficiencyActual;
    }

    public void setEfficiencyActual(Double efficiencyActual) {
        this.efficiencyActual = efficiencyActual;
    }

    public Double getFttRatePlan() {
        return fttRatePlan;
    }

    public void setFttRatePlan(Double fttRatePlan) {
        this.fttRatePlan = fttRatePlan;
    }

    public Double getFttRateActual() {
        return fttRateActual;
    }

    public void setFttRateActual(Double fttRateActual) {
        this.fttRateActual = fttRateActual;
    }

    public String getActiveHour() {
        return activeHour;
    }

    public void setActiveHour(String activeHour) {
        this.activeHour = activeHour;
    }

    public Double getDhuRatePlan() {
        return dhuRatePlan;
    }

    public void setDhuRatePlan(Double dhuRatePlan) {
        this.dhuRatePlan = dhuRatePlan;
    }

    public Double getDhuRateActual() {
        return dhuRateActual;
    }

    public void setDhuRateActual(Double dhuRateActual) {
        this.dhuRateActual = dhuRateActual;
    }
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VcutFactoryLineBreakup)) {
            return false;
        }
        return id != null && id.equals(((VcutFactoryLineBreakup) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "VcutFactoryLineBreakup{" +
            "id=" + id +
            ", hourBreakup='" + hourBreakup + '\'' +
            ", displayFlag='" + displayFlag + '\'' +
            ", hourPlan=" + hourPlan +
            ", hourActual=" + hourActual +
            ", cumPlan=" + cumPlan +
            ", cumActual=" + cumActual +
            ", varianceHour=" + varianceHour +
            ", varianceCum=" + varianceCum +
            ", efficiencyPlan=" + efficiencyPlan +
            ", efficiencyActual=" + efficiencyActual +
            ", fttRatePlan=" + fttRatePlan +
            ", fttRateActual=" + fttRateActual +
            '}';
    }
}
