package io.vamani.application.model;

import java.io.Serializable;
import java.util.List;

public class CutPlanBundleMatrixBean implements Serializable {
    private Long id;
    private String porductionCounterCode;
    private String productionCode;
    private String plantCode;
    private String style;
    private String color;
    private String sizeCode;
    private String destination;
    private String destinationDesc;
    private Double allotedQty;
    private Double bundledQty;
    private Double balanceQty;
    private Double bundleSize;
    private Double bundlePcs;
    private List<CutPlanBundleMatrixBreakup> cutPlanBundleMatrixBreakups;
    private List<CutPlanBundleMatrixBreakup> cutPlanBundleMatrixExistBreakups;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPorductionCounterCode() {
        return porductionCounterCode;
    }

    public void setPorductionCounterCode(String porductionCounterCode) {
        this.porductionCounterCode = porductionCounterCode;
    }

    public String getProductionCode() {
        return productionCode;
    }

    public void setProductionCode(String productionCode) {
        this.productionCode = productionCode;
    }

    public String getPlantCode() {
        return plantCode;
    }

    public void setPlantCode(String plantCode) {
        this.plantCode = plantCode;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSizeCode() {
        return sizeCode;
    }

    public void setSizeCode(String sizeCode) {
        this.sizeCode = sizeCode;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestinationDesc() {
        return destinationDesc;
    }

    public void setDestinationDesc(String destinationDesc) {
        this.destinationDesc = destinationDesc;
    }

    public Double getAllotedQty() {
        return allotedQty;
    }

    public void setAllotedQty(Double allotedQty) {
        this.allotedQty = allotedQty;
    }

    public Double getBundledQty() {
        return bundledQty;
    }

    public void setBundledQty(Double bundledQty) {
        this.bundledQty = bundledQty;
    }

    public Double getBalanceQty() {
        return balanceQty;
    }

    public void setBalanceQty(Double balanceQty) {
        this.balanceQty = balanceQty;
    }

    public Double getBundleSize() {
        return bundleSize;
    }

    public void setBundleSize(Double bundleSize) {
        this.bundleSize = bundleSize;
    }

    public Double getBundlePcs() {
        return bundlePcs;
    }

    public void setBundlePcs(Double bundlePcs) {
        this.bundlePcs = bundlePcs;
    }

    public List<CutPlanBundleMatrixBreakup> getCutPlanBundleMatrixBreakups() {
        return cutPlanBundleMatrixBreakups;
    }

    public void setCutPlanBundleMatrixBreakups(List<CutPlanBundleMatrixBreakup> cutPlanBundleMatrixBreakups) {
        this.cutPlanBundleMatrixBreakups = cutPlanBundleMatrixBreakups;
    }

    public List<CutPlanBundleMatrixBreakup> getCutPlanBundleMatrixExistBreakups() {
        return cutPlanBundleMatrixExistBreakups;
    }

    public void setCutPlanBundleMatrixExistBreakups(List<CutPlanBundleMatrixBreakup> cutPlanBundleMatrixExistBreakups) {
        this.cutPlanBundleMatrixExistBreakups = cutPlanBundleMatrixExistBreakups;
    }
}
